/***/
package org.eclipse.e4.ui.services.internal.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.internal.services.Activator;
import org.eclipse.e4.ui.internal.services.ServiceMessages;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class EventBroker implements IEventBroker {

    // TBD synchronization
    private Map<EventHandler, Collection<ServiceRegistration<?>>> registrations = new HashMap<EventHandler, Collection<ServiceRegistration<?>>>();

    @Inject
    @Optional
    Logger logger;

    @Inject
    @Optional
    UISynchronize uiSync;

    // the proper method to start EventAdmin is added.
    static {
        EventAdmin eventAdmin = Activator.getDefault().getEventAdmin();
        if (eventAdmin == null) {
            Bundle[] bundles = Activator.getDefault().getBundleContext().getBundles();
            for (Bundle bundle : bundles) {
                if (!"org.eclipse.equinox.event".equals(bundle.getSymbolicName()))
                    continue;
                try {
                    bundle.start(Bundle.START_TRANSIENT);
                } catch (BundleException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public  EventBroker() {
    // placeholder
    }

    @Override
    public boolean send(String topic, Object data) {
        Event event = constructEvent(topic, data);
        Activator activator = Activator.getDefault();
        if (activator == null) {
            if (logger != null) {
                logger.error(NLS.bind(ServiceMessages.NO_EVENT_ADMIN, event.toString()));
            }
            return false;
        }
        EventAdmin eventAdmin = activator.getEventAdmin();
        if (eventAdmin == null) {
            if (logger != null) {
                logger.error(NLS.bind(ServiceMessages.NO_EVENT_ADMIN, event.toString()));
            }
            return false;
        }
        eventAdmin.sendEvent(event);
        return true;
    }

    @Override
    public boolean post(String topic, Object data) {
        Event event = constructEvent(topic, data);
        Activator activator = Activator.getDefault();
        if (activator == null) {
            if (logger != null) {
                logger.error(NLS.bind(ServiceMessages.NO_EVENT_ADMIN, event.toString()));
            }
            return false;
        }
        EventAdmin eventAdmin = activator.getEventAdmin();
        if (eventAdmin == null) {
            if (logger != null) {
                logger.error(NLS.bind(ServiceMessages.NO_EVENT_ADMIN, event.toString()));
            }
            return false;
        }
        eventAdmin.postEvent(event);
        return true;
    }

    @SuppressWarnings("unchecked")
    private Event constructEvent(String topic, Object data) {
        Event event;
        if (data instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) data;
            if (map.containsKey(EventConstants.EVENT_TOPIC) && map.containsKey(IEventBroker.DATA)) {
                return new Event(topic, map);
            }
            Map<String, Object> eventMap = new HashMap(map);
            if (!eventMap.containsKey(EventConstants.EVENT_TOPIC)) {
                eventMap.put(EventConstants.EVENT_TOPIC, topic);
            }
            if (!eventMap.containsKey(IEventBroker.DATA)) {
                eventMap.put(IEventBroker.DATA, data);
            }
            event = new Event(topic, eventMap);
        } else if (data instanceof Dictionary<?, ?>) {
            Dictionary<String, Object> d = (Dictionary<String, Object>) data;
            if (d.get(EventConstants.EVENT_TOPIC) != null && d.get(IEventBroker.DATA) != null) {
                return new Event(topic, d);
            }
            Map<String, Object> map = convertToMap(d);
            if (map.get(EventConstants.EVENT_TOPIC) == null) {
                map.put(EventConstants.EVENT_TOPIC, topic);
            }
            if (map.get(IEventBroker.DATA) == null) {
                map.put(IEventBroker.DATA, map);
            }
            event = new Event(topic, map);
        } else {
            Dictionary<String, Object> d = new Hashtable<String, Object>(2);
            d.put(EventConstants.EVENT_TOPIC, topic);
            if (data != null)
                d.put(IEventBroker.DATA, data);
            event = new Event(topic, d);
        }
        return event;
    }

    private static <K, V> Map<K, V> convertToMap(Dictionary<K, V> source) {
        Map<K, V> map = new Hashtable();
        for (Enumeration<K> keys = source.keys(); keys.hasMoreElements(); ) {
            K key = keys.nextElement();
            map.put(key, source.get(key));
        }
        return map;
    }

    @Override
    public boolean subscribe(String topic, EventHandler eventHandler) {
        return subscribe(topic, null, eventHandler, false);
    }

    @Override
    public boolean subscribe(String topic, String filter, EventHandler eventHandler, boolean headless) {
        BundleContext bundleContext = Activator.getDefault().getBundleContext();
        if (bundleContext == null) {
            if (logger != null) {
                logger.error(NLS.bind(ServiceMessages.NO_BUNDLE_CONTEXT, topic));
            }
            return false;
        }
        String[] topics = new String[] { topic };
        Dictionary<String, Object> d = new Hashtable<String, Object>();
        d.put(EventConstants.EVENT_TOPIC, topics);
        if (filter != null)
            d.put(EventConstants.EVENT_FILTER, filter);
        EventHandler wrappedHandler = new UIEventHandler(eventHandler, headless ? null : uiSync);
        ServiceRegistration<?> registration = bundleContext.registerService(EventHandler.class.getName(), wrappedHandler, d);
        Collection<ServiceRegistration<?>> handled = registrations.get(eventHandler);
        if (handled == null) {
            registrations.put(eventHandler, handled = new ArrayList<ServiceRegistration<?>>());
        }
        handled.add(registration);
        return true;
    }

    @Override
    public boolean unsubscribe(EventHandler eventHandler) {
        Collection<ServiceRegistration<?>> handled = registrations.remove(eventHandler);
        if (handled == null || handled.isEmpty())
            return false;
        for (ServiceRegistration<?> r : handled) {
            r.unregister();
        }
        return true;
    }

    @PreDestroy
    void dispose() {
        Collection<Collection<ServiceRegistration<?>>> values = new ArrayList<Collection<ServiceRegistration<?>>>(registrations.values());
        registrations.clear();
        for (Collection<ServiceRegistration<?>> handled : values) {
            for (ServiceRegistration<?> registration : handled) {
                // System.out.println("EventBroker dispose:" + registration[i] +
                // ")");
                registration.unregister();
            }
        }
    }
}
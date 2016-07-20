/***/
package org.eclipse.e4.ui.internal.workbench;

import java.net.URI;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import org.eclipse.e4.core.commands.ExpressionContext;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.contributions.IContributionFactory;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.ServiceRegistration;

/**
* Default implementation of {@link IWorkbench}
*/
public class E4Workbench implements IWorkbench {

    /**
* The argument for the locale active shell <br>
* <br>
* Value is: <code>localActiveShell</code>
*/
    //$NON-NLS-1$
    public static final String LOCAL_ACTIVE_SHELL = "localActiveShell";

    /**
* The argument for the {@link URI} of the initial workbench model <br>
* <br>
* Value is: <code>initialWorkbenchModelURI</code>
*/
    //$NON-NLS-1$
    public static final String INITIAL_WORKBENCH_MODEL_URI = "initialWorkbenchModelURI";

    /**
* The argument for the {@link Location} of the running instance <br>
* <br>
* Value is: <code>instanceLocation</code>
*/
    //$NON-NLS-1$
    public static final String INSTANCE_LOCATION = "instanceLocation";

    /**
* The argument for the renderer factory to use <br>
* <br>
* Value is: <code>rendererFactoryUri</code>
*/
    //$NON-NLS-1$
    public static final String RENDERER_FACTORY_URI = "rendererFactoryUri";

    /**
* The argument for setting RTL mode <br>
* <br>
* Value is: <code>dir</code>
*/
    //$NON-NLS-1$
    public static final String RTL_MODE = "dir";

    /**
* The argument for the perspective to activate <br>
* <br>
* Value is: <code>perspectiveId</code>
*/
    //$NON-NLS-1$
    public static final String FORCED_PERSPECTIVE_ID = "forcedPerspetiveId";

    //$NON-NLS-1$
    public static final String NO_SAVED_MODEL_FOUND = "NO_SAVED_MODEL_FOUND";

    /**
* The argument for the whether to forcefully show the location in the window title (set on the
* command line)<br>
* <br>
* Value is: <code>forcedShowLocation</code>
*/
    //$NON-NLS-1$
    public static final String FORCED_SHOW_LOCATION = "forcedShowLocation";

    private final String id;

    private ServiceRegistration<?> osgiRegistration;

    IEclipseContext appContext;

    IPresentationEngine renderer;

    MApplication appModel = null;

    private UIEventPublisher uiEventPublisher;

    private boolean restart;

    /**
* @return the {@link IEclipseContext} for the main application
*/
    public IEclipseContext getContext() {
        return appContext;
    }

    /**
* Constructor
*
* @param uiRoot
*            the root UI element
* @param applicationContext
*            the root context
*/
    public  E4Workbench(MApplicationElement uiRoot, IEclipseContext applicationContext) {
        id = createId();
        appContext = applicationContext;
        appContext.set(IWorkbench.class.getName(), this);
        if (uiRoot instanceof MApplication) {
            appModel = (MApplication) uiRoot;
        }
        if (uiRoot instanceof MApplication) {
            init((MApplication) uiRoot);
        }
        uiEventPublisher = new UIEventPublisher(appContext);
        appContext.set(UIEventPublisher.class, uiEventPublisher);
        ((Notifier) uiRoot).eAdapters().add(uiEventPublisher);
        Hashtable<String, Object> properties = new Hashtable();
        //$NON-NLS-1$
        properties.put("id", getId());
        osgiRegistration = Activator.getDefault().getContext().registerService(IWorkbench.class.getName(), this, properties);
    }

    @Override
    public final String getId() {
        return id;
    }

    protected String createId() {
        return UUID.randomUUID().toString();
    }

    /**
* @param uiRoot
*/
    public void createAndRunUI(MApplicationElement uiRoot) {
        // Has someone already created one ?
        instantiateRenderer();
        if (renderer != null) {
            renderer.run(uiRoot, appContext);
        }
    }

    /**
*
*/
    public void instantiateRenderer() {
        renderer = (IPresentationEngine) appContext.get(IPresentationEngine.class.getName());
        if (renderer == null) {
            String presentationURI = (String) appContext.get(IWorkbench.PRESENTATION_URI_ARG);
            if (presentationURI != null) {
                IContributionFactory factory = (IContributionFactory) appContext.get(IContributionFactory.class.getName());
                renderer = (IPresentationEngine) factory.create(presentationURI, appContext);
                appContext.set(IPresentationEngine.class.getName(), renderer);
            }
            if (renderer == null) {
                Logger logger = (Logger) appContext.get(Logger.class.getName());
                //$NON-NLS-1$
                logger.error("Failed to create the presentation engine for URI: " + presentationURI);
            }
        }
    }

    private void init(MApplication appElement) {
        //$NON-NLS-1$
        Activator.trace(Policy.DEBUG_WORKBENCH, "init() workbench", null);
        IEclipseContext context = appElement.getContext();
        if (context != null) {
            context.set(ExpressionContext.ALLOW_ACTIVATION, Boolean.TRUE);
        }
    }

    @Override
    public boolean close() {
        // Fire an E4 lifecycle notification
        UIEvents.publishEvent(UIEvents.UILifeCycle.APP_SHUTDOWN_STARTED, appModel);
        if (renderer != null) {
            renderer.stop();
        }
        if (uiEventPublisher != null && appModel != null) {
            ((Notifier) appModel).eAdapters().remove(uiEventPublisher);
            uiEventPublisher = null;
        }
        if (osgiRegistration != null) {
            osgiRegistration.unregister();
            osgiRegistration = null;
        }
        return true;
    }

    @Override
    public boolean restart() {
        this.restart = true;
        return close();
    }

    /**
* @return <code>true</code> when the workbench should be restarted
*/
    public boolean isRestart() {
        return restart;
    }

    /**
* @return a context that can be used to lookup OSGi services
*/
    public static IEclipseContext getServiceContext() {
        return EclipseContextFactory.getServiceContext(Activator.getDefault().getContext());
    }

    @Override
    public MApplication getApplication() {
        return appModel;
    }

    /**
* Create the context chain. It both creates the chain for the current model, and adds eAdapters
* so it can add new contexts when new model items are added.
*
* @param parentContext
*            The parent context
* @param contextModel
*            needs a context created
* @return a chained {@link IEclipseContext}
*/
    public static IEclipseContext initializeContext(IEclipseContext parentContext, MContext contextModel) {
        final IEclipseContext context;
        if (contextModel.getContext() != null) {
            context = contextModel.getContext();
        } else {
            //$NON-NLS-1$
            context = parentContext.createChild("PartContext(" + contextModel + ')');
        }
        Activator.trace(Policy.DEBUG_CONTEXTS, //$NON-NLS-1$
        "initializeContext(" + parentContext.toString() + ", " + contextModel + ")", //$NON-NLS-1$ //$NON-NLS-2$
        null);
        // fill in the interfaces, so MContributedPart.class.getName() will
        // return the model element, for example.
        ContributionsAnalyzer.populateModelInterfaces(contextModel, context, contextModel.getClass().getInterfaces());
        // declares modifiable variables from the model
        List<String> containedProperties = contextModel.getVariables();
        for (String name : containedProperties) {
            context.declareModifiable(name);
        }
        contextModel.setContext(context);
        return context;
    }
}

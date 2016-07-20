/***/
package org.eclipse.ui.internal.browser;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.XMLMemento;

/**
*
*/
public class BrowserManager extends Observable {

    protected List<IBrowserDescriptor> browsers;

    protected IBrowserDescriptor currentBrowser;

    private IPreferenceChangeListener pcl;

    protected boolean ignorePreferenceChanges = false;

    protected static BrowserManager instance;

    public static BrowserManager getInstance() {
        if (instance == null)
            instance = new BrowserManager();
        return instance;
    }

    private  BrowserManager() {
        pcl = new IEclipsePreferences.IPreferenceChangeListener() {

            @Override
            public void preferenceChange(PreferenceChangeEvent event) {
                String property = event.getKey();
                if (!ignorePreferenceChanges && property.equals("browsers")) {
                    //$NON-NLS-1$
                    loadBrowsers();
                }
                if (!property.equals(WebBrowserPreference.PREF_INTERNAL_WEB_BROWSER_HISTORY)) {
                    setChanged();
                    notifyObservers();
                }
            }
        };
        IScopeContext instanceScope = InstanceScope.INSTANCE;
        IEclipsePreferences prefs = instanceScope.getNode(WebBrowserUIPlugin.PLUGIN_ID);
        prefs.addPreferenceChangeListener(pcl);
    }

    protected static void safeDispose() {
        if (instance == null)
            return;
        instance.dispose();
    }

    protected void dispose() {
        IScopeContext instanceScope = InstanceScope.INSTANCE;
        IEclipsePreferences prefs = instanceScope.getNode(WebBrowserUIPlugin.PLUGIN_ID);
        prefs.removePreferenceChangeListener(pcl);
    }

    public IBrowserDescriptorWorkingCopy createExternalWebBrowser() {
        return new BrowserDescriptorWorkingCopy();
    }

    public List<IBrowserDescriptor> getWebBrowsers() {
        if (browsers == null)
            loadBrowsers();
        return new ArrayList(browsers);
    }

    public void loadBrowsers() {
        //$NON-NLS-1$
        Trace.trace(Trace.FINEST, "Loading web browsers");
        String xmlString = Platform.getPreferencesService().getString(WebBrowserUIPlugin.PLUGIN_ID, "browsers", null, //$NON-NLS-1$
        null);
        if (xmlString != null && xmlString.length() > 0) {
            browsers = new ArrayList();
            try {
                //$NON-NLS-1$
                ByteArrayInputStream in = new ByteArrayInputStream(xmlString.getBytes("utf-8"));
                //$NON-NLS-1$
                Reader reader = new InputStreamReader(in, "utf-8");
                IMemento memento = XMLMemento.createReadRoot(reader);
                //$NON-NLS-1$
                IMemento system = memento.getChild("system");
                if (system != null && WebBrowserUtil.canUseSystemBrowser())
                    browsers.add(new SystemBrowserDescriptor());
                //$NON-NLS-1$
                IMemento[] children = memento.getChildren("external");
                int size = children.length;
                for (int i = 0; i < size; i++) {
                    BrowserDescriptor browser = new BrowserDescriptor();
                    browser.load(children[i]);
                    browsers.add(browser);
                }
                //$NON-NLS-1$
                Integer current = memento.getInteger("current");
                if (current != null) {
                    currentBrowser = browsers.get(current.intValue());
                }
            } catch (Exception e) {
                Trace.trace(Trace.WARNING, "Could not load browsers: " + e.getMessage());
            }
            IBrowserDescriptor system = new SystemBrowserDescriptor();
            if (WebBrowserUtil.canUseSystemBrowser() && !browsers.contains(system)) {
                browsers.add(0, system);
                currentBrowser = system;
                saveBrowsers();
            }
        } else {
            setupDefaultBrowsers();
            saveBrowsers();
        }
        if (currentBrowser == null && browsers.size() > 0)
            currentBrowser = browsers.get(0);
        setChanged();
        notifyObservers();
    }

    protected void saveBrowsers() {
        try {
            ignorePreferenceChanges = true;
            //$NON-NLS-1$
            XMLMemento memento = XMLMemento.createWriteRoot("web-browsers");
            Iterator<IBrowserDescriptor> iterator = browsers.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (obj instanceof BrowserDescriptor) {
                    BrowserDescriptor browser = (BrowserDescriptor) obj;
                    //$NON-NLS-1$
                    IMemento child = memento.createChild("external");
                    browser.save(child);
                } else if (obj instanceof SystemBrowserDescriptor) {
                    //$NON-NLS-1$
                    memento.createChild("system");
                }
            }
            //$NON-NLS-1$
            memento.putInteger("current", browsers.indexOf(currentBrowser));
            StringWriter writer = new StringWriter();
            memento.save(writer);
            String xmlString = writer.getBuffer().toString();
            IScopeContext instanceScope = InstanceScope.INSTANCE;
            IEclipsePreferences prefs = instanceScope.getNode(WebBrowserUIPlugin.PLUGIN_ID);
            //$NON-NLS-1$
            prefs.put("browsers", xmlString);
            prefs.flush();
        } catch (Exception e) {
            Trace.trace(Trace.SEVERE, "Could not save browsers", e);
        }
        ignorePreferenceChanges = false;
    }

    protected void setupDefaultBrowsers() {
        browsers = new ArrayList();
        // add system browser
        if (WebBrowserUtil.canUseSystemBrowser()) {
            IBrowserDescriptor system = new SystemBrowserDescriptor();
            browsers.add(system);
        }
        // handle all the EXTERNAL browsers by criteria and add those too at startup
        WebBrowserUtil.addFoundBrowsers(browsers);
        // by default, if internal is there, that is current, else set the first external one
        if (!browsers.isEmpty() && currentBrowser == null)
            currentBrowser = browsers.get(0);
    }

    protected void addBrowser(IBrowserDescriptor browser) {
        if (browsers == null)
            loadBrowsers();
        if (!browsers.contains(browser))
            browsers.add(browser);
        if (browsers.size() == 1)
            setCurrentWebBrowser(browser);
    }

    protected void removeWebBrowser(IBrowserDescriptor browser) {
        if (browsers == null)
            loadBrowsers();
        browsers.remove(browser);
        if (currentBrowser == null || currentBrowser.equals(browser)) {
            currentBrowser = null;
            if (browsers.size() > 0)
                currentBrowser = browsers.get(0);
        }
    }

    public IBrowserDescriptor getCurrentWebBrowser() {
        if (browsers == null)
            loadBrowsers();
        if (currentBrowser == null && browsers.size() > 0)
            return browsers.get(0);
        return currentBrowser;
    }

    public void setCurrentWebBrowser(IBrowserDescriptor wb) {
        if (wb == null)
            throw new IllegalArgumentException();
        if (browsers.contains(wb))
            currentBrowser = wb;
        else
            throw new IllegalArgumentException();
        saveBrowsers();
    }
}

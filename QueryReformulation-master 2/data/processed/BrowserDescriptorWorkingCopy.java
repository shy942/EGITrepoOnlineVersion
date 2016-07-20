/***/
package org.eclipse.ui.internal.browser;

/**
*
*/
public class BrowserDescriptorWorkingCopy extends BrowserDescriptor implements IBrowserDescriptorWorkingCopy {

    protected BrowserDescriptor browser;

    // creation
    public  BrowserDescriptorWorkingCopy() {
    // do nothing
    }

    // working copy
    public  BrowserDescriptorWorkingCopy(BrowserDescriptor browser) {
        this.browser = browser;
        setInternal(browser);
    }

    @Override
    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException();
        this.name = name;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setParameters(String params) {
        this.parameters = params;
    }

    @Override
    public boolean isWorkingCopy() {
        return true;
    }

    @Override
    public IBrowserDescriptorWorkingCopy getWorkingCopy() {
        return this;
    }

    @Override
    public IBrowserDescriptor save() {
        if (browser != null) {
            browser.setInternal(this);
        } else {
            browser = new BrowserDescriptor();
            browser.setInternal(this);
            BrowserManager.getInstance().addBrowser(browser);
        }
        return browser;
    }
}

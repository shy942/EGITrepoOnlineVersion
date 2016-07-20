/***/
package org.eclipse.ui.internal.browser;

public class SystemBrowserDescriptor implements IBrowserDescriptor {

    @Override
    public String getName() {
        return Messages.prefSystemBrowser;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String getParameters() {
        return null;
    }

    @Override
    public void delete() {
    // ignore
    }

    @Override
    public boolean isWorkingCopy() {
        return false;
    }

    @Override
    public IBrowserDescriptorWorkingCopy getWorkingCopy() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SystemBrowserDescriptor;
    }
}

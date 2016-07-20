/***/
package org.eclipse.ui.internal.browser;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.util.Util;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
*
*/
public class BrowserDescriptorDialog extends Dialog {

    protected IBrowserDescriptorWorkingCopy browser;

    protected boolean isEdit;

    protected Button newPageCheckbox;

    protected Button clearHistoryCheckbox;

    protected Button browseButton;

    protected Text browserNameTextfield;

    protected Text browserLocationTextfield;

    protected Text browserParametersTextfield;

    private Button okButton;

    interface StringModifyListener {

        public void valueChanged(String s);
    }

    public  BrowserDescriptorDialog(Shell parentShell, IBrowserDescriptorWorkingCopy browser) {
        super(parentShell);
        this.browser = browser;
        isEdit = true;
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    public  BrowserDescriptorDialog(Shell parentShell) {
        super(parentShell);
        browser = BrowserManager.getInstance().createExternalWebBrowser();
        isEdit = false;
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        if (isEdit)
            shell.setText(Messages.editExternalBrowser);
        else
            shell.setText(Messages.createBrowser);
    }

    protected Text createText(Composite comp, String txt, final StringModifyListener listener, boolean multiLine) {
        int style = SWT.BORDER;
        if (multiLine) {
            style = SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP;
        }
        final Text text = new Text(comp, style);
        if (multiLine) {
            text.addTraverseListener(new TraverseListener() {

                @Override
                public void keyTraversed(TraverseEvent event) {
                    switch(event.detail) {
                        case SWT.TRAVERSE_RETURN:
                        case SWT.TRAVERSE_TAB_NEXT:
                        case SWT.TRAVERSE_TAB_PREVIOUS:
                            event.doit = true;
                            break;
                    }
                }
            });
        }
        // final Text text = SWTUtil.createMultilineText(comp, style);
        if (txt != null)
            text.setText(txt);
        GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
        data.widthHint = 450;
        if (multiLine) {
            // then expand this control as the dialog resizes
            data.verticalAlignment = SWT.FILL;
            data.grabExcessVerticalSpace = true;
            data.heightHint = convertHeightInCharsToPixels(4);
        }
        text.setLayoutData(data);
        if (listener != null)
            text.addModifyListener(new ModifyListener() {

                @Override
                public void modifyText(ModifyEvent e) {
                    listener.valueChanged(text.getText());
                }
            });
        return text;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Font font = parent.getFont();
        Composite composite = (Composite) super.createDialogArea(parent);
        ((GridLayout) composite.getLayout()).numColumns = 3;
        composite.setFont(font);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, ContextIds.PREF_BROWSER_DIALOG);
        SWTUtil.createLabel(composite, Messages.name).setFont(font);
        browserNameTextfield = createText(composite, browser.getName(), new StringModifyListener() {

            @Override
            public void valueChanged(String s) {
                browser.setName(s);
                validateFields();
            }
        }, false);
        browserNameTextfield.setFont(font);
        new Label(composite, SWT.NONE);
        SWTUtil.createLabel(composite, Messages.location).setFont(font);
        browserLocationTextfield = createText(composite, browser.getLocation(), new StringModifyListener() {

            @Override
            public void valueChanged(String s) {
                browser.setLocation(s);
                validateFields();
            }
        }, false);
        browserLocationTextfield.setFont(font);
        browseButton = SWTUtil.createButton(composite, Messages.browse);
        browseButton.setFont(font);
        browseButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
                dialog.setText(Messages.browseMessage);
                String fname = browserLocationTextfield.getText();
                dialog.setFileName(fname);
                fname = dialog.open();
                if (fname != null)
                    browserLocationTextfield.setText(fname);
            }
        });
        SWTUtil.createLabel(composite, Messages.parameters).setFont(font);
        browserParametersTextfield = createText(composite, browser.getParameters(), new StringModifyListener() {

            @Override
            public void valueChanged(String s) {
                browser.setParameters(s);
            }
        }, true);
        browserParametersTextfield.setFont(font);
        new Label(composite, SWT.NONE);
        new Label(composite, SWT.NONE);
        Label urlLabel = new Label(composite, SWT.NONE);
        urlLabel.setText(NLS.bind(Messages.parametersMessage, IBrowserDescriptor.URL_PARAMETER));
        urlLabel.setFont(font);
        return composite;
    }

    @Override
    protected void okPressed() {
        // do simple field validation to at least ensure target directory entered is valid pathname
        try {
            File file = new File(browser.getLocation());
            if (!(file.isFile() || (Util.isMac() && ExternalBrowserInstance.isMacAppBundle(file)))) {
                WebBrowserUtil.openError(Messages.locationInvalid);
                return;
            }
        } catch (Exception e) {
            WebBrowserUtil.openError(Messages.locationInvalid);
            return;
        }
        browser.save();
        super.okPressed();
    }

    private void setOKButtonEnabled(boolean curIsEnabled) {
        if (okButton == null)
            okButton = getButton(IDialogConstants.OK_ID);
        if (okButton != null)
            okButton.setEnabled(curIsEnabled);
    }

    @Override
    protected Control createButtonBar(Composite parent) {
        Control buttonControl = super.createButtonBar(parent);
        validateFields();
        return buttonControl;
    }

    protected void validateFields() {
        boolean valid = true;
        String name = browserNameTextfield.getText();
        if (name == null || name.trim().length() < 1)
            valid = false;
        String location = browserLocationTextfield.getText();
        if (location == null || location.trim().length() < 1)
            valid = false;
        setOKButtonEnabled(valid);
    }
}

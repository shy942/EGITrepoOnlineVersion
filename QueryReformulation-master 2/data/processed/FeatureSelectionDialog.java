/***/
package org.eclipse.ui.internal.ide;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionDialog;
import com.ibm.icu.text.Collator;

/**
* Dialog to allow the user to select a feature from a list.
*/
public class FeatureSelectionDialog extends SelectionDialog {

    /**
* List width in characters.
*/
    private static final int LIST_WIDTH = 60;

    /**
* List height in characters.
*/
    private static final int LIST_HEIGHT = 10;

    /**
* The feature about infos.
*/
    private AboutInfo[] features;

    /**
* List to display the resolutions.
*/
    private ListViewer listViewer;

    /**
* The help context id
*/
    private String helpContextId;

    /**
* Creates an instance of this dialog to display
* the given features.
* <p>
* There must be at least one feature.
* </p>
*
* @param shell  the parent shell
* @param features  the features to display
* @param primaryFeatureId  the id of the primary feature or null if none
* @param shellTitle  shell title
* @param shellMessage  shell message
* @param helpContextId  help context id
*/
    public  FeatureSelectionDialog(Shell shell, AboutInfo[] features, String primaryFeatureId, String shellTitle, String shellMessage, String helpContextId) {
        super(shell);
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException();
        }
        this.features = features;
        this.helpContextId = helpContextId;
        setTitle(shellTitle);
        setMessage(shellMessage);
        // Sort ascending
        Arrays.sort(features, new Comparator() {

            Collator coll = Collator.getInstance(Locale.getDefault());

            @Override
            public int compare(Object a, Object b) {
                AboutInfo i1, i2;
                String name1, name2;
                i1 = (AboutInfo) a;
                name1 = i1.getFeatureLabel();
                i2 = (AboutInfo) b;
                name2 = i2.getFeatureLabel();
                if (name1 == null) {
                    //$NON-NLS-1$
                    name1 = "";
                }
                if (name2 == null) {
                    //$NON-NLS-1$
                    name2 = "";
                }
                return coll.compare(name1, name2);
            }
        });
        // Find primary feature
        for (int i = 0; i < features.length; i++) {
            if (features[i].getFeatureId().equals(primaryFeatureId)) {
                setInitialSelections(new Object[] { features[i] });
                return;
            }
        }
        // set a safe default
        setInitialSelections(new Object[0]);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(newShell, helpContextId);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        // Create label
        createMessageArea(composite);
        // Create list viewer
        listViewer = new ListViewer(composite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.heightHint = convertHeightInCharsToPixels(LIST_HEIGHT);
        data.widthHint = convertWidthInCharsToPixels(LIST_WIDTH);
        listViewer.getList().setLayoutData(data);
        listViewer.getList().setFont(parent.getFont());
        // Set the label provider
        listViewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                //$NON-NLS-1$
                return element == null ? "" : ((AboutInfo) element).getFeatureLabel();
            }
        });
        // Set the content provider
        listViewer.setContentProvider(ArrayContentProvider.getInstance());
        listViewer.setInput(features);
        // Set the initial selection
        listViewer.setSelection(new StructuredSelection(getInitialElementSelections()), true);
        // Add a selection change listener
        listViewer.addSelectionChangedListener( event -> getOkButton().setEnabled(!event.getSelection().isEmpty()));
        // Add double-click listener
        listViewer.addDoubleClickListener( event -> okPressed());
        return composite;
    }

    @Override
    protected void okPressed() {
        IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
        setResult(selection.toList());
        super.okPressed();
    }
}
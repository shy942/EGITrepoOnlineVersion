/***/
package org.eclipse.ui.internal.wizards.datatransfer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;

/**
*	Page 1 of the base resource import-from-archive Wizard.
*
*	Note that importing from .jar is identical to importing from .zip, so
*	all references to .zip in this class are equally applicable to .jar
*	references.
*
*	@since 3.1
*/
public class WizardArchiveFileResourceImportPage1 extends WizardFileSystemResourceImportPage1 implements Listener {

    ILeveledImportStructureProvider structureProvider;

    // constants
    //$NON-NLS-1$ //$NON-NLS-2$
    private static final String[] FILE_IMPORT_MASK = { "*.jar;*.zip;*.tar;*.tar.gz;*.tgz", "*.*" };

    // dialog store id constants
    //$NON-NLS-1$
    private static final String STORE_SOURCE_NAMES_ID = "WizardZipFileResourceImportPage1.STORE_SOURCE_NAMES_ID";

    //$NON-NLS-1$
    private static final String STORE_OVERWRITE_EXISTING_RESOURCES_ID = "WizardZipFileResourceImportPage1.STORE_OVERWRITE_EXISTING_RESOURCES_ID";

    //$NON-NLS-1$
    private static final String STORE_SELECTED_TYPES_ID = "WizardZipFileResourceImportPage1.STORE_SELECTED_TYPES_ID";

    private final String[] fileImportMask;

    /**
*	Creates an instance of this class
* @param aWorkbench IWorkbench
* @param selection IStructuredSelection
*/
    public  WizardArchiveFileResourceImportPage1(IWorkbench aWorkbench, IStructuredSelection selection) {
        this(aWorkbench, selection, null);
    }

    /**
*	Creates an instance of this class
* @param aWorkbench IWorkbench
* @param selection IStructuredSelection
* @param fileImportMask != null: override default mask
*/
    public  WizardArchiveFileResourceImportPage1(IWorkbench aWorkbench, IStructuredSelection selection, String[] fileImportMask) {
        //$NON-NLS-1$
        super("zipFileImportPage1", aWorkbench, selection);
        setTitle(DataTransferMessages.ArchiveExport_exportTitle);
        setDescription(DataTransferMessages.ArchiveImport_description);
        if (fileImportMask == null)
            this.fileImportMask = FILE_IMPORT_MASK;
        else
            this.fileImportMask = fileImportMask;
    }

    /**
* Called when the user presses the Cancel button. Return a boolean
* indicating permission to close the wizard.
*
* @return boolean
*/
    public boolean cancel() {
        disposeStructureProvider();
        return true;
    }

    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IDataTransferHelpContextIds.ZIP_FILE_IMPORT_WIZARD_PAGE);
    }

    /**
*	Create the options specification widgets. There is only one
* in this case so create no group.
*
*	@param parent org.eclipse.swt.widgets.Composite
*/
    @Override
    protected void createOptionsGroup(Composite parent) {
        // overwrite... checkbox
        overwriteExistingResourcesCheckbox = new Button(parent, SWT.CHECK);
        overwriteExistingResourcesCheckbox.setText(DataTransferMessages.FileImport_overwriteExisting);
        overwriteExistingResourcesCheckbox.setFont(parent.getFont());
    }

    private boolean validateSourceFile(String fileName) {
        if (ArchiveFileManipulations.isTarFile(fileName)) {
            TarFile tarFile = getSpecifiedTarSourceFile(fileName);
            if (tarFile != null) {
                ArchiveFileManipulations.closeTarFile(tarFile, getShell());
                return true;
            }
            return false;
        }
        ZipFile zipFile = getSpecifiedZipSourceFile(fileName);
        if (zipFile != null) {
            ArchiveFileManipulations.closeZipFile(zipFile, getShell());
            return true;
        }
        return false;
    }

    /**
*	Answer a boolean indicating whether the specified source currently exists
*	and is valid (ie.- proper format)
*/
    private boolean ensureZipSourceIsValid() {
        ZipFile specifiedFile = getSpecifiedZipSourceFile();
        if (specifiedFile == null) {
            setErrorMessage(DataTransferMessages.ZipImport_badFormat);
            return false;
        }
        return ArchiveFileManipulations.closeZipFile(specifiedFile, getShell());
    }

    private boolean ensureTarSourceIsValid() {
        TarFile specifiedFile = getSpecifiedTarSourceFile();
        if (specifiedFile == null) {
            setErrorMessage(DataTransferMessages.TarImport_badFormat);
            return false;
        }
        return ArchiveFileManipulations.closeTarFile(specifiedFile, getShell());
    }

    /**
*	Answer a boolean indicating whether the specified source currently exists
*	and is valid (ie.- proper format)
*/
    @Override
    protected boolean ensureSourceIsValid() {
        if (ArchiveFileManipulations.isTarFile(sourceNameField.getText())) {
            return ensureTarSourceIsValid();
        }
        return ensureZipSourceIsValid();
    }

    /**
* The Finish button was pressed.  Try to do the required work now and answer
* a boolean indicating success.  If <code>false</code> is returned then the
* wizard will not close.
*
* @return boolean
*/
    @Override
    public boolean finish() {
        if (!super.finish()) {
            return false;
        }
        disposeStructureProvider();
        return true;
    }

    /**
* Closes the structure provider and sets
* the field to <code>null</code>.
*
* @since 3.4
*/
    private void disposeStructureProvider() {
        ArchiveFileManipulations.closeStructureProvider(structureProvider, getShell());
        structureProvider = null;
    }

    /**
* Returns a content provider for <code>FileSystemElement</code>s that returns
* only files as children.
*/
    @Override
    protected ITreeContentProvider getFileProvider() {
        return new WorkbenchContentProvider() {

            @Override
            public Object[] getChildren(Object o) {
                if (o instanceof MinimizedFileSystemElement) {
                    MinimizedFileSystemElement element = (MinimizedFileSystemElement) o;
                    AdaptableList l = element.getFiles(structureProvider);
                    return l.getChildren(element);
                }
                return new Object[0];
            }
        };
    }

    /**
*	Answer the root FileSystemElement that represents the contents of the
*	currently-specified .zip file.  If this FileSystemElement is not
*	currently defined then create and return it.
*/
    @Override
    protected MinimizedFileSystemElement getFileSystemTree() {
        disposeStructureProvider();
        if (ArchiveFileManipulations.isTarFile(sourceNameField.getText())) {
            TarFile sourceTarFile = getSpecifiedTarSourceFile();
            if (sourceTarFile == null) {
                return null;
            }
            structureProvider = new TarLeveledStructureProvider(sourceTarFile);
            return selectFiles(structureProvider.getRoot(), structureProvider);
        }
        ZipFile sourceFile = getSpecifiedZipSourceFile();
        if (sourceFile == null) {
            return null;
        }
        structureProvider = new ZipLeveledStructureProvider(sourceFile);
        return selectFiles(structureProvider.getRoot(), structureProvider);
    }

    /**
* Returns a content provider for <code>FileSystemElement</code>s that returns
* only folders as children.
*/
    @Override
    protected ITreeContentProvider getFolderProvider() {
        return new WorkbenchContentProvider() {

            @Override
            public Object[] getChildren(Object o) {
                if (o instanceof MinimizedFileSystemElement) {
                    MinimizedFileSystemElement element = (MinimizedFileSystemElement) o;
                    AdaptableList l = element.getFolders(structureProvider);
                    return l.getChildren(element);
                }
                return new Object[0];
            }

            @Override
            public boolean hasChildren(Object o) {
                if (o instanceof MinimizedFileSystemElement) {
                    MinimizedFileSystemElement element = (MinimizedFileSystemElement) o;
                    if (element.isPopulated()) {
                        return getChildren(element).length > 0;
                    }
                    //If we have not populated then wait until asked
                    return true;
                }
                return false;
            }
        };
    }

    /**
*	Answer the string to display as the label for the source specification field
*/
    @Override
    protected String getSourceLabel() {
        return DataTransferMessages.ArchiveImport_fromFile;
    }

    /**
*	Answer a handle to the zip file currently specified as being the source.
*	Return null if this file does not exist or is not of valid format.
*/
    protected ZipFile getSpecifiedZipSourceFile() {
        return getSpecifiedZipSourceFile(sourceNameField.getText());
    }

    /**
*	Answer a handle to the zip file currently specified as being the source.
*	Return null if this file does not exist or is not of valid format.
*/
    private ZipFile getSpecifiedZipSourceFile(String fileName) {
        if (fileName.length() == 0) {
            return null;
        }
        try {
            return new ZipFile(fileName);
        } catch (ZipException e) {
        } catch (IOException e) {
        }
        sourceNameField.setFocus();
        return null;
    }

    /**
*	Answer a handle to the zip file currently specified as being the source.
*	Return null if this file does not exist or is not of valid format.
*/
    protected TarFile getSpecifiedTarSourceFile() {
        return getSpecifiedTarSourceFile(sourceNameField.getText());
    }

    /**
*	Answer a handle to the zip file currently specified as being the source.
*	Return null if this file does not exist or is not of valid format.
*/
    private TarFile getSpecifiedTarSourceFile(String fileName) {
        if (fileName.length() == 0) {
            return null;
        }
        try {
            return new TarFile(fileName);
        } catch (TarException e) {
        } catch (IOException e) {
        }
        sourceNameField.setFocus();
        return null;
    }

    /**
*	Open a FileDialog so that the user can specify the source
*	file to import from
*/
    @Override
    protected void handleSourceBrowseButtonPressed() {
        String selectedFile = queryZipFileToImport();
        if (selectedFile != null) {
            //Be sure it is valid before we go setting any names
            if (!selectedFile.equals(sourceNameField.getText()) && validateSourceFile(selectedFile)) {
                setSourceName(selectedFile);
                selectionGroup.setFocus();
            }
        }
    }

    /**
*  Import the resources with extensions as specified by the user
*/
    @Override
    protected boolean importResources(List fileSystemObjects) {
        ILeveledImportStructureProvider importStructureProvider = null;
        if (ArchiveFileManipulations.isTarFile(sourceNameField.getText())) {
            if (ensureTarSourceIsValid()) {
                TarFile tarFile = getSpecifiedTarSourceFile();
                importStructureProvider = new TarLeveledStructureProvider(tarFile);
            }
        } else if (ensureZipSourceIsValid()) {
            ZipFile zipFile = getSpecifiedZipSourceFile();
            importStructureProvider = new ZipLeveledStructureProvider(zipFile);
        }
        if (importStructureProvider == null) {
            return false;
        }
        ImportOperation operation = new ImportOperation(getContainerFullPath(), importStructureProvider.getRoot(), importStructureProvider, this, fileSystemObjects);
        operation.setContext(getShell());
        if (!executeImportOperation(operation))
            return false;
        ArchiveFileManipulations.closeStructureProvider(importStructureProvider, getShell());
        return true;
    }

    /**
* Initializes the specified operation appropriately.
*/
    @Override
    protected void initializeOperation(ImportOperation op) {
        op.setOverwriteResources(overwriteExistingResourcesCheckbox.getSelection());
    }

    /**
* Opens a file selection dialog and returns a string representing the
* selected file, or <code>null</code> if the dialog was canceled.
*/
    protected String queryZipFileToImport() {
        FileDialog dialog = new FileDialog(sourceNameField.getShell(), SWT.OPEN | SWT.SHEET);
        dialog.setFilterExtensions(this.fileImportMask);
        dialog.setText(DataTransferMessages.ArchiveImportSource_title);
        String currentSourceString = sourceNameField.getText();
        int lastSeparatorIndex = currentSourceString.lastIndexOf(File.separator);
        if (lastSeparatorIndex != -1) {
            dialog.setFilterPath(currentSourceString.substring(0, lastSeparatorIndex));
        }
        return dialog.open();
    }

    /**
*	Repopulate the view based on the currently entered directory.
*/
    @Override
    protected void resetSelection() {
        super.resetSelection();
        setAllSelections(true);
    }

    /**
*	Use the dialog store to restore widget values to the values that they held
*	last time this wizard was used to completion
*/
    @Override
    protected void restoreWidgetValues() {
        IDialogSettings settings = getDialogSettings();
        if (settings != null) {
            String[] sourceNames = settings.getArray(STORE_SOURCE_NAMES_ID);
            if (sourceNames == null) {
                // ie.- no settings stored
                return;
            }
            // set filenames history
            for (int i = 0; i < sourceNames.length; i++) {
                sourceNameField.add(sourceNames[i]);
            }
            // radio buttons and checkboxes
            overwriteExistingResourcesCheckbox.setSelection(settings.getBoolean(STORE_OVERWRITE_EXISTING_RESOURCES_ID));
        }
    }

    /**
* 	Since Finish was pressed, write widget values to the dialog store so that they
*	will persist into the next invocation of this wizard page.
*
*	Note that this method is identical to the one that appears in the superclass.
*	This is necessary because proper overriding of instance variables is not occurring.
*/
    @Override
    protected void saveWidgetValues() {
        IDialogSettings settings = getDialogSettings();
        if (settings != null) {
            // update source names history
            String[] sourceNames = settings.getArray(STORE_SOURCE_NAMES_ID);
            if (sourceNames == null) {
                sourceNames = new String[0];
            }
            sourceNames = addToHistory(sourceNames, sourceNameField.getText());
            settings.put(STORE_SOURCE_NAMES_ID, sourceNames);
            // update specific types to import history
            String[] selectedTypesNames = settings.getArray(STORE_SELECTED_TYPES_ID);
            if (selectedTypesNames == null) {
                selectedTypesNames = new String[0];
            }
            settings.put(STORE_OVERWRITE_EXISTING_RESOURCES_ID, overwriteExistingResourcesCheckbox.getSelection());
        }
    }

    /**
*	Answer a boolean indicating whether self's source specification
*	widgets currently all contain valid values.
*/
    @Override
    protected boolean validateSourceGroup() {
        //If there is nothing being provided to the input then there is a problem
        if (structureProvider == null) {
            setMessage(SOURCE_EMPTY_MESSAGE);
            enableButtonGroup(false);
            return false;
        }
        List resourcesToExport = selectionGroup.getAllWhiteCheckedItems();
        if (resourcesToExport.size() == 0) {
            setErrorMessage(DataTransferMessages.FileImport_noneSelected);
            return false;
        }
        enableButtonGroup(true);
        setErrorMessage(null);
        return true;
    }
}
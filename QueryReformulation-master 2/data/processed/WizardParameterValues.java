/***/
package org.eclipse.ui.internal.registry;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.commands.IParameterValues;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardCategory;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

/**
* Provides the parameter values for a show wizard command.
* <p>
* This class is only intended to be extended by the three inner classes (<code>Export</code>,
* <code>Import</code> and <code>New</code>) defined here.
* </p>
*
* @since 3.2
*/
public abstract class WizardParameterValues implements IParameterValues {

    /**
* Provides the parameter values for export wizards.
*/
    public static final class Export extends WizardParameterValues {

        @Override
        protected IWizardRegistry getWizardRegistry() {
            return PlatformUI.getWorkbench().getExportWizardRegistry();
        }
    }

    /**
* Provides the parameter values for import wizards.
*/
    public static final class Import extends WizardParameterValues {

        @Override
        protected IWizardRegistry getWizardRegistry() {
            return PlatformUI.getWorkbench().getImportWizardRegistry();
        }
    }

    /**
* Provides the parameter values for new wizards.
*/
    public static final class New extends WizardParameterValues {

        @Override
        protected IWizardRegistry getWizardRegistry() {
            return PlatformUI.getWorkbench().getNewWizardRegistry();
        }
    }

    private void addParameterValues(Map values, IWizardCategory wizardCategory) {
        final IWizardDescriptor[] wizardDescriptors = wizardCategory.getWizards();
        for (int i = 0; i < wizardDescriptors.length; i++) {
            final IWizardDescriptor wizardDescriptor = wizardDescriptors[i];
            // Note: using description instead of label for the name
            // to reduce possibilities of key collision in the map
            // final String name = wizardDescriptor.getDescription();
            // by request
            String name = wizardDescriptor.getLabel();
            final String id = wizardDescriptor.getId();
            final String value = (String) values.get(name);
            if (value != null && !value.equals(id)) {
                //$NON-NLS-1$//$NON-NLS-2$
                name = name + " (" + id + ")";
            }
            values.put(name, id);
        }
        final IWizardCategory[] childCategories = wizardCategory.getCategories();
        for (int i = 0; i < childCategories.length; i++) {
            final IWizardCategory childCategory = childCategories[i];
            addParameterValues(values, childCategory);
        }
    }

    @Override
    public Map getParameterValues() {
        final Map values = new HashMap();
        final IWizardRegistry wizardRegistry = getWizardRegistry();
        addParameterValues(values, wizardRegistry.getRootCategory());
        return values;
    }

    /**
* Returns the wizard registry for the concrete
* <code>WizardParameterValues</code> implementation class.
*
* @return The wizard registry for the concrete
*         <code>WizardParameterValues</code> implementation class.
*/
    protected abstract IWizardRegistry getWizardRegistry();
}

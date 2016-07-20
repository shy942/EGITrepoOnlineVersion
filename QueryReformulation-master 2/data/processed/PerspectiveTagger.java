/***/
package org.eclipse.ui.internal;

import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;

public class PerspectiveTagger {

    /**
* Alters known 3.x perspective part folders into their e4 counterparts.
*/
    public static void tagPerspective(MPerspective perspective, EModelService modelService) {
        String id = perspective.getElementId();
        if (id == null) {
            return;
        }
        // see bug 305557
        if (id.equals("org.eclipse.jdt.ui.JavaPerspective")) {
            //$NON-NLS-1$
            tagJavaPerspective(perspective, modelService);
        } else if (id.equals("org.eclipse.team.cvs.ui.cvsPerspective")) {
            //$NON-NLS-1$
            tagCVSPerspective(perspective, modelService);
        } else if (id.equals("org.eclipse.team.ui.TeamSynchronizingPerspective")) {
            //$NON-NLS-1$
            tagTeamPerspective(perspective, modelService);
        } else if (id.equals("org.eclipse.debug.ui.DebugPerspective")) {
            //$NON-NLS-1$
            tagDebugPerspective(perspective, modelService);
        } else if (id.equals("org.eclipse.ui.resourcePerspective")) {
            //$NON-NLS-1$
            tagResourcePerspective(perspective, modelService);
        } else if (id.equals("org.eclipse.pde.ui.PDEPerspective")) {
            //$NON-NLS-1$
            tagPluginDevelopmentPerspective(perspective, modelService);
        }
    }

    static void tagJavaPerspective(MPerspective perspective, EModelService modelService) {
        //$NON-NLS-1$
        MUIElement element = modelService.find("left", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.primaryNavigationStack");
        }
        //$NON-NLS-1$
        element = modelService.find("bottom", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryDataStack");
        }
        //$NON-NLS-1$
        element = modelService.find("right", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryNavigationStack");
        }
    }

    static void tagCVSPerspective(MPerspective perspective, EModelService modelService) {
        //$NON-NLS-1$
        MUIElement element = modelService.find("top", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.primaryNavigationStack");
        }
    }

    static void tagTeamPerspective(MPerspective perspective, EModelService modelService) {
        //$NON-NLS-1$
        MUIElement element = modelService.find("top", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.primaryNavigationStack");
        }
        //$NON-NLS-1$
        element = modelService.find("top2", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryDataStack");
        }
    }

    static void tagDebugPerspective(MPerspective perspective, EModelService modelService) {
        MUIElement element = modelService.find("org.eclipse.debug.internal.ui.NavigatorFolderView", //$NON-NLS-1$
        perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.primaryNavigationStack");
        }
        element = modelService.find("org.eclipse.debug.internal.ui.ConsoleFolderView", //$NON-NLS-1$
        perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryDataStack");
        }
        element = modelService.find("org.eclipse.debug.internal.ui.OutlineFolderView", //$NON-NLS-1$
        perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryNavigationStack");
        }
    }

    static void tagResourcePerspective(MPerspective perspective, EModelService modelService) {
        //$NON-NLS-1$
        MUIElement element = modelService.find("topLeft", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.primaryNavigationStack");
        }
        //$NON-NLS-1$
        element = modelService.find("bottomRight", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryDataStack");
        }
        //$NON-NLS-1$
        element = modelService.find("bottomLeft", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryNavigationStack");
        }
    }

    static void tagPluginDevelopmentPerspective(MPerspective perspective, EModelService modelService) {
        //$NON-NLS-1$
        MUIElement element = modelService.find("topLeft", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.primaryNavigationStack");
        }
        //$NON-NLS-1$
        element = modelService.find("bottomRight", perspective);
        if (element != null) {
            //$NON-NLS-1$
            element.getTags().add("org.eclipse.e4.secondaryDataStack");
        }
    }
}

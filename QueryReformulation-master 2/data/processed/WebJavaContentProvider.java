/***/
package org.eclipse.ui.tests.navigator.jst;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.INavigatorContentExtension;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.IPipelinedTreeContentProvider;
import org.eclipse.ui.navigator.PipelinedShapeModification;
import org.eclipse.ui.navigator.PipelinedViewerUpdate;

public class WebJavaContentProvider implements IPipelinedTreeContentProvider {

    private static final Object[] NO_CHILDREN = new Object[0];

    //$NON-NLS-1$
    private static final String JAVA_EXTENSION_ID = "org.eclipse.jdt.java.ui.javaContent";

    public static Class JAVA_CORE_CLASS;

    public static Class IJAVA_PROJECT_CLASS;

    public static Class IJAVA_ELEMENT_CLASS;

    public static Class IPACKAGE_FRAGMENT_ROOT_CLASS;

    public static Class IPACKAGE_FRAGMENT_CLASS;

    public static Class INTERNAL_CONTAINER_CLASS;

    public static void staticInit(ClassLoader cl) {
        try {
            //$NON-NLS-1$
            JAVA_CORE_CLASS = cl.loadClass("org.eclipse.jdt.core.JavaCore");
            IJAVA_PROJECT_CLASS = cl.loadClass(//$NON-NLS-1$
            "org.eclipse.jdt.core.IJavaProject");
            IJAVA_ELEMENT_CLASS = cl.loadClass(//$NON-NLS-1$
            "org.eclipse.jdt.core.IJavaElement");
            IPACKAGE_FRAGMENT_ROOT_CLASS = cl.loadClass(//$NON-NLS-1$
            "org.eclipse.jdt.core.IPackageFragmentRoot");
            IPACKAGE_FRAGMENT_CLASS = cl.loadClass(//$NON-NLS-1$
            "org.eclipse.jdt.core.IPackageFragment");
            INTERNAL_CONTAINER_CLASS = cl.loadClass(//$NON-NLS-1$
            "org.eclipse.jdt.internal.ui.packageview.ClassPathContainer");
        } catch (Throwable t) {
            INTERNAL_CONTAINER_CLASS = null;
        }
    }

    private CommonViewer commonViewer;

    private ITreeContentProvider delegateContentProvider;

    private final Map compressedNodes = new HashMap();

    public static Object javaCoreCreateResource(IResource res) throws Exception {
        Method m = JAVA_CORE_CLASS.getMethod("create", new Class[] { IResource.class });
        return m.invoke(null, new Object[] { res });
    }

    public static Object javaCoreCreateProject(IResource res) throws Exception {
        Method m = JAVA_CORE_CLASS.getMethod("create", new Class[] { IProject.class });
        return m.invoke(null, new Object[] { res });
    }

    @Override
    public void getPipelinedChildren(Object aParent, Set theCurrentChildren) {
        try {
            if (aParent instanceof IProject) {
                cleanJavaContribution(theCurrentChildren);
                theCurrentChildren.add(getCompressedNode((IProject) aParent));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void cleanJavaContribution(Set theCurrentChildren) throws Exception {
        for (Iterator iter = theCurrentChildren.iterator(); iter.hasNext(); ) {
            Object child = iter.next();
            if (child instanceof IResource && (javaCoreCreateResource((IResource) child) != null))
                iter.remove();
            else if (INTERNAL_CONTAINER_CLASS != null && INTERNAL_CONTAINER_CLASS.isInstance(child))
                iter.remove();
            else if (IJAVA_ELEMENT_CLASS.isInstance(child))
                iter.remove();
        }
    }

    private CompressedJavaProject getCompressedNode(IProject project) {
        CompressedJavaProject result = (CompressedJavaProject) compressedNodes.get(project);
        if (result == null) {
            compressedNodes.put(project, result = new CompressedJavaProject(commonViewer, project));
        }
        return result;
    }

    @Override
    public void getPipelinedElements(Object anInput, Set theCurrentElements) {
    }

    @Override
    public Object getPipelinedParent(Object anObject, Object aSuggestedParent) {
        try {
            Method m = IJAVA_PROJECT_CLASS.getMethod("getProject", new Class[] {});
            if (IPACKAGE_FRAGMENT_ROOT_CLASS.isInstance(anObject)) {
                if (IJAVA_PROJECT_CLASS.isInstance(aSuggestedParent)) {
                    return getCompressedNode((IProject) m.invoke(aSuggestedParent, new Object[] {}));
                } else if (aSuggestedParent instanceof IProject) {
                    return getCompressedNode(((IProject) aSuggestedParent));
                }
            } else if (INTERNAL_CONTAINER_CLASS.isInstance(anObject)) {
                if (IJAVA_PROJECT_CLASS.isInstance(aSuggestedParent)) {
                    return getCompressedNode((IProject) m.invoke(aSuggestedParent, new Object[] {})).getCompressedJavaLibraries();
                } else if (aSuggestedParent instanceof IProject) {
                    return getCompressedNode(((IProject) aSuggestedParent)).getCompressedJavaLibraries();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public PipelinedShapeModification interceptAdd(PipelinedShapeModification anAddModification) {
        return anAddModification;
    }

    @Override
    public PipelinedShapeModification interceptRemove(PipelinedShapeModification aRemoveModification) {
        return aRemoveModification;
    }

    @Override
    public boolean interceptRefresh(PipelinedViewerUpdate aRefreshSynchronization) {
        return false;
    }

    @Override
    public boolean interceptUpdate(PipelinedViewerUpdate anUpdateSynchronization) {
        return false;
    }

    @Override
    public void init(ICommonContentExtensionSite aSite) {
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (delegateContentProvider != null) {
            if (parentElement instanceof CompressedJavaProject) {
                return ((CompressedJavaProject) parentElement).getChildren(delegateContentProvider);
            } else if (parentElement instanceof CompressedJavaLibraries) {
                return ((CompressedJavaLibraries) parentElement).getChildren(delegateContentProvider);
            }
        }
        return NO_CHILDREN;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof CompressedJavaProject)
            return ((CompressedJavaProject) element).getProject();
        if (element instanceof CompressedJavaLibraries)
            return ((CompressedJavaLibraries) element).getCompressedProject();
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return (element instanceof CompressedJavaProject || element instanceof CompressedJavaLibraries);
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return NO_CHILDREN;
    }

    @Override
    public void dispose() {
        compressedNodes.clear();
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (viewer instanceof CommonViewer) {
            commonViewer = (CommonViewer) viewer;
            INavigatorContentService service = commonViewer.getNavigatorContentService();
            INavigatorContentExtension javaext = service.getContentExtensionById(JAVA_EXTENSION_ID);
            if (javaext != null)
                delegateContentProvider = javaext.getContentProvider();
            compressedNodes.clear();
        }
    }

    @Override
    public void restoreState(IMemento aMemento) {
    }

    @Override
    public void saveState(IMemento aMemento) {
    }

    public boolean isClasspathContainer(Object o) {
        return INTERNAL_CONTAINER_CLASS != null && INTERNAL_CONTAINER_CLASS.isInstance(o);
    }
}

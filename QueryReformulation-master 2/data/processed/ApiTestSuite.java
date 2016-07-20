/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.tests.api.workbenchpart.ArbitraryPropertyTest;
import org.eclipse.ui.tests.api.workbenchpart.LifecycleViewTest;
import org.eclipse.ui.tests.api.workbenchpart.OverriddenTitleTest;
import org.eclipse.ui.tests.api.workbenchpart.RawIViewPartTest;
import org.eclipse.ui.tests.api.workbenchpart.ViewPartTitleTest;
import org.eclipse.ui.tests.ide.api.FileEditorInputTest;
import org.eclipse.ui.tests.ide.api.IDETest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ IPageLayoutTest.class, PlatformUITest.class, IWorkbenchTest.class, IWorkbenchWindowTest.class, IWorkbenchPageTest.class, IDeprecatedWorkbenchPageTest.class, IActionFilterTest.class, IPageListenerTest.class, IAggregateWorkingSetTest.class, IPageServiceTest.class, IPerspectiveRegistryTest.class, IPerspectiveDescriptorTest.class, IFileEditorMappingTest.class, IEditorDescriptorTest.class, IEditorRegistryTest.class, IPerspectiveListenerTest.class, IWorkbenchWindowActionDelegateTest.class, IViewActionDelegateTest.class, IViewSiteTest.class, IEditorSiteTest.class, IActionBarsTest.class, IViewPartTest.class, IEditorPartTest.class, IEditorActionBarContributorTest.class, IPartServiceTest.class, ISelectionServiceTest.class, IWorkingSetTest.class, IWorkingSetManagerTest.class, IWorkingSetElementAdapterTests.class, MockWorkingSetTest.class, Bug42616Test.class, StickyViewTest.class, EditorIconTest.class, RawIViewPartTest.class, ViewPartTitleTest.class, OverriddenTitleTest.class, UIJobTest.class, Bug75118Test.class, FileEditorInputTest.class, IDETest.class, IEditorMatchingStrategyTest.class, XMLMementoTest.class, //IWorkbenchPartTestableTests.class,
ArbitraryPropertyTest.class, LifecycleViewTest.class, Bug407422Test.class, MultipleWindowsTest.class })
public class ApiTestSuite {
}

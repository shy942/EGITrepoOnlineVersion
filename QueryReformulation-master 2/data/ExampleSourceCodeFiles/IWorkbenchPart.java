/Users/user/eclipse.platform.ui/bundles/org.eclipse.ui.workbench/Eclipse UI/org/eclipse/ui/IWorkbenchPart.java
org eclipse org eclipse core runtime adaptable org eclipse swt graphics image org eclipse swt widgets composite workbench visual component workbench there subtypes view editor defined code view part code code editor part code view typically navigate hierarchy workspace open editor display properties active editor modifications view saved editor typically edit browse document input object input identified code editor input code modifications editor follow open save close lifecycle model this implemented for convenience base implementation defined code workbench part code lifecycle workbench when extension created instantiate create site call code init site code when visible workbench add presentation calling code create part control parent code create actual widgets fire code opened code event listeners when activated focus call code set focus code fire code activated code event listeners when closed save needed save fails canceled active deactivate fire code closed code event listeners remove presentation controls disposed widget tree call code dispose code after code create part control code called implementor safely reference controls created when closed controls disposed composite this occurs code workbench part dispose code method called free resources define dispose listener control free resources dispose listener invokes method disposed controls point code error code thrown method called code workbench part code code dispose code this signals lifecycle point note lifecycle call init create part control called thus dispose method implementors assume controls created workbench parts implement code adaptable code extensions managed platform adapter manager view part editor part workbench part adaptable property code title code code title image code code title tool tip code workbench part constants adds listener properties workbench has identical listener registered property ids defined link workbench part constants param listener property listener add property listener property listener listener creates controls workbench clients call method workbench calls method for implementors multi step process create controls parent set parent layout needed register global actions site code action bars code register context menus site register selection provider site workbench code selection service code optional param parent parent control create part control composite parent disposes workbench this method called code workbench part code point controls created disposed composite there guarantee create part control called controls created within method release resources fonts images nbsp held deregister listeners workbench clients call method workbench calls method times dispose returns site workbench site code null code workbench initialized after initialization complete code null code remainder life cycle site code null code initialized workbench part site site returns title workbench fire property listener event code code title populate title bar visual container workbench title code null code string title returns title image workbench fire property listener event code code title image populate title bar visual container since image managed callers dispose returned image title image image title image returns title tool text workbench empty string result tool fire property listener event code code tool text populate title bar visual container workbench title tool code null code string title tool tip removes property listener workbench has identical listener registered param listener property listener remove property listener property listener listener asks focus workbench parts assign focus controls contained parent composite clients call method workbench calls method times workbench activate code workbench page activate workbench part code set focus
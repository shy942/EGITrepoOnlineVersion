/Users/user/eclipse.platform.ui/bundles/org.eclipse.ui.workbench/Eclipse UI/org/eclipse/ui/IPageLayout.java
org eclipse layout defines initial layout perspective workbench window this intended implemented clients when perspective opened creates layout single editor area this layout passed perspective factory implementation link org eclipse perspective factory create initial layout page layout additional views content existing editor area initial point reference cases multiple instances view layout these disambiguated secondary layout methods view compound form strong primary secondary strong secondary view multiple instances code multiple true code extension view placeholders secondary wildcards permitted placeholder ids regular view ids matches substring matches single character wildcards primary secondary for placeholder view match occurrence view primary view null secondary note placeholder match view secondary compound simply view example populating layout standard workbench views pre page layout layout get editor area string editor area layout editor area top left project explorer view bookmarks view placeholder folder layout top left layout create folder top left page layout editor area top left add view page layout top left add placeholder page layout bottom left outline view property sheet view folder layout bottom left layout create folder bottom left page layout top left bottom left add view page layout bottom left add view page layout bottom task list view layout add view page layout page layout editor area pre noimplement this intended implemented clients page layout workbench editor area this reference view addition string org eclipse editorss view workbench resource navigator standard component deprecated replaced common navigator framework release deprecated string org eclipse views resource navigator view project explorer string org eclipse navigator project explorer view workbench property sheet standard component string org eclipse views property sheet view workbench content outline standard component string org eclipse views content outline view workbench bookmark navigator standard component string org eclipse views bookmark view view workbench problems view standard component string org eclipse views problem view view workbench progress view standard component string org eclipse views progress view view workbench task list standard component string org eclipse views task list navigate action set code org eclipse navigate action set code string org eclipse navigate action set relationship constant indicating left relative relationship constant indicating relative relationship constant indicating relative relationship constant indicating relative minimum acceptable ratio adding view maximum acceptable ratio adding view fast view ratio width deprecated discontinued support fast views deprecated view ratio width regular fast views variable represent invalid ratios variable represent ratio adds action set layout action set contributed workbench extension point named code org eclipse action set code param action set action set add action set string action set adds view compound layout fast view see link page layout type documentation details compound ids primary view contributed workbench view extension point named code org eclipse views code param view compound view deprecated discontinued support fast views deprecated add fast view string view adds view compound layout fast view width ratio see link page layout type documentation details compound ids primary view contributed workbench view extension point named code org eclipse views code param view compound view param ratio percentage workbench fast view cover deprecated discontinued support fast views deprecated add fast view string view ratio adds wizard shortcut layout these typically rapid navigation wizards for eclipse items file new menu wizard extension contributed workbench wizards extension point named code org eclipse wizards code param wizard add new wizard shortcut string adds perspective shortcut layout these typically rapid navigation wizards for eclipse items window open perspective menu perspective extension contributed workbench perspectives extension point named code org eclipse perspectives code param perspective add perspective shortcut string adds view placeholder layout view placeholder define position view view appears initially invisible user opens view compound matches placeholder view location placeholder see link page layout type documentation details compound ids placeholder wildcards remains layout replaced view primary placeholder wildcards refer view contributed workbench view extension point named code org eclipse views code param view compound view wildcards allowed param relationship position relative reference code code code code code code code code param ratio ratio divide space occupied reference range code code code code values range clipped facilitate direct manipulation for vertical split top ratio current space bottom rest likewise horizontal split left ratio current space rest param reference view folder special editor area returned code editor area code add placeholder string view relationship ratio string adds item show prompter view contributed workbench view extension point named code org eclipse views code param view add show part string adds view shortcut layout these typically rapid navigation views for eclipse items window show view menu view contributed workbench views extension point named code org eclipse views code param view add show view shortcut string adds view compound layout see link page layout type documentation details compound ids primary view contributed workbench view extension point named code org eclipse views code param view compound view param relationship position relative reference code code code code code code code code param ratio ratio divide space occupied reference range code code code code values range clipped facilitate direct manipulation for vertical split top ratio current space bottom rest likewise horizontal split left ratio current space rest param reference view folder special editor area returned code editor area code add view string view relationship ratio string creates adds folder layout position relative size folder expressed relative reference param folder folder this unique layout avoid collision parts param relationship position relative reference code code code code code code code code param ratio ratio divide space occupied reference range code code code code values range clipped facilitate direct manipulation for vertical split top ratio current space bottom rest likewise horizontal split left ratio current space rest param reference view folder special editor area returned code editor area code folder folder layout create folder string folder relationship ratio string creates adds placeholder folder layout position relative size folder expressed relative reference param folder folder this unique layout avoid collision parts param relationship position relative reference code code code code code code code code param ratio ratio divide space occupied reference range code code code code values range clipped facilitate direct manipulation for vertical split top ratio current space bottom rest likewise horizontal split left ratio current space rest param reference view folder special editor area returned code editor area code placeholder folder placeholder folder layout create placeholder folder string folder relationship ratio string returns special identifier editor area layout identifier editor area stored code code editor area automatically layout point reference adding views layout special editor area string editor area returns layout editor area code true code editor area visible code false code editor area visible show hide editor area layout param editor area code true code editor area code false code hide editor area set editor area visible editor area returns number open editors reusing editors preference settings number open editors reusing editors preference settings deprecated returns eclipse deprecated editor reuse threshold sets number open editors reusing editors user preference settings param open editors number open editors deprecated method eclipse deprecated set editor reuse threshold open editors sets layout fixed fixed layout layout parts moved zoomed initial set views closed param fixed code true code layout fixed code false code set fixed fixed returns code true code layout fixed code false code fixed layout layout parts moved zoomed initial set views closed code false code code true code layout fixed code false code fixed returns layout view placeholder compound layout see link page layout type documentation details compound ids returns code null code view placeholder unknown layout param compound view placeholder view layout code null code view layout view layout string adds standalone view compound layout see link page layout type documentation details compound ids standalone view docked views standalone view title optionally hidden hidden controls typically title close button hidden any contributions content view toolbar view menu contributions content description view contributed workbench view extension point named code org eclipse views code param view compound view param title code true code title controls code false code hide param relationship position relative reference code code code code code code code code param ratio ratio divide space occupied reference range code code code code values range clipped facilitate direct manipulation for vertical split top ratio current space bottom rest likewise horizontal split left ratio current space rest param reference view folder special editor area returned code editor area code add standalone view string view title relationship ratio string adds standalone view placeholder layout view placeholder define position view view appears initially invisible user opens view compound matches placeholder view location placeholder see link page layout type documentation details compound ids placeholder wildcards remains layout replaced view primary placeholder wildcards refer view contributed workbench view extension point named code org eclipse views code param view compound view wildcards allowed param relationship position relative reference code code code code code code code code param ratio ratio divide space occupied reference range code code code code values range clipped facilitate direct manipulation for vertical split top ratio current space bottom rest likewise horizontal split left ratio current space rest param reference view folder special editor area returned code editor area code param title true view title false add standalone view placeholder string view relationship ratio string title returns perspective descriptor perspective layed perspective descriptor perspective layed perspective descriptor descriptor returns folder layout view placeholder compound layout see link page layout type documentation details compound ids returns code null code view placeholder unknown layout placeholder folder param compound view placeholder must code null code folder layout code null code placeholder folder layout folder for view string
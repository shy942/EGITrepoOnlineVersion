/Users/user/eclipse.platform.ui/bundles/org.eclipse.ui.workbench/Eclipse UI/org/eclipse/ui/ISizeProvider.java
org eclipse interface implemented objects capable computing preferred size size provider constant infinite size this equal integer ensuring greater integer integer returns bitwise combination flags indicating compute preferred size when called horizontal true usage compute preferred size true computing widths when called horizontal false usage compute size false computing heights these flags optimization each flag control preferred size slows layout algorithm parts minimum set flags constraints function call code flush layout code maximum size returned compute preferred size horizontal width minimum size returned compute preferred size horizontal width indicates compute preferred size perpendicular argument flag argument compute preferred size set perpendicular size expensive compute wrapping parts preferred size verbatim compute preferred size minimum maximum sizes this commonly set predetermined sizes workbench size for compute preferred size horizontal space width preferred size nearest predetermined size note flag sparingly prevent layout caching workbench layout algorithm degrade exponential worst runtime flag compute preferred size compute minimum maximum sizes param width true false determines applies computing widths heights that size flags true calling compute preferred size true bitwise combination size flags width returns size width height workbench preferred size parts overload enforce minimum size maximum size quantized set preferred sizes width true method computes width pixels width false method computes height parallel perpendicular space preferred parallel preferred result this method returns answer equal parallel close preferred parallel return values larger parallel truncated most presentations define minimum size times maximum size applies maximized size flags method controls frequently method called any subclass specializes method specialize size flags compute preferred size width size returns minimum size control compute preferred size width size returns maximum size control examples maintain constant size pixels width size flags grow constraints preferred result size flags enforce width multiple pixels minimum pixels code width preferred result result preferred result preferred result result math max math min result parallel parallel result preferred result code size flags width width maintain minimum area pixels code perpendicular perpendicular code size flags width param width width code true code height code false code computed param parallel space this width pixels width true height pixels width false larger param perpendicular space perpendicular direction measured unbounded pixels this height width true height width false implementations generally ignore argument wrapping widgets note argument meaningful returns flag size flags width param preferred result preferred size control pixels parallel set unknown unbounded returns preferred size control pixels this width width true height width false callers responsible rounding larger parallel parallel permitted indicating preferred size control unbounded size provider size flags compute preferred size width parallel perpendicular preferred result
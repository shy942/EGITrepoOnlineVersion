/Users/user/eclipse.platform.ui/bundles/org.eclipse.jface/src/org/eclipse/jface/viewers/Viewer.java
org eclipse jface viewers org eclipse core runtime assert org eclipse core runtime listener list org eclipse jface util safe runnable org eclipse swt events help event org eclipse swt events help listener org eclipse swt widgets control org eclipse swt widgets item viewer model based adapter widget viewer created adapter pre existing control creating code list viewer code existing code list code control all viewers provide convenience constructor creating control implementing concrete viewer typically involves steps create controls viewer constructor optional initialize controls input input changed define viewer specific update methods support selections code set selection code code selection code viewer input selection provider list selection change listeners element type code selection changed listener code fire selection changed listener list selection changed listeners listener list list request listeners element type code org eclipse swt events help listener code help request listeners handle help request listener list listeners listener list names viewer properties code null code viewer properties set data string values viewer properties code null code viewer properties this array parallels code code field set data object values remembers hooked listener control hooked false help listener control created lazily client listener help listener listener null unique key associating element data widgets org eclipse swt widgets widget set data string object string org eclipse jface viewers creates viewer viewer adds listener requests viewer has identical listener registered param listener listener add help listener help listener listener listeners add listener hooked control control control control null control disposed listener null listener handle help request control add help listener listener hooked true override add selection changed listener selection changed listener listener selection changed listeners add listener notifies listeners requested only listeners registered time method called notified param event event help listener requested org eclipse swt events help event fire help requested help event event object listeners listeners listeners object listener listeners help listener listener requested event notifies selection changed listeners viewer selection changed only listeners registered time method called notified param event selection changed event selection changed listener selection changed fire selection changed selection changed event event object listeners selection changed listeners listeners object listener listeners selection changed listener selection changed listener listener safe runnable safe runnable override selection changed event returns primary control viewer control displays viewer content control control returns property code null code property implementation performs linear search internal table overriding method generally required number small efficient representation viewer properties required override code data code code set data code param key property property code null code property object data string key assert not null key null null length equals key values null override object input override selection selection handles request underlying control behavior fire request event data modified hold viewer param event event handle help request help event event object data event data event data fire help requested event event data data internal hook method called input viewer initially set subsequently changed implementation subclassers override method viewer input set typical populate viewer param input input viewer code null code param input input element code null code input input changed object input object input refreshes viewer completely freshly viewer model refresh removes listener viewer has identical listener registered param listener listener remove help listener help listener listener listeners remove listener listeners size control control control control null control disposed control remove help listener listener hooked false override remove selection changed listener selection changed listener listener selection changed listeners remove listener scrolls viewer control item display relative coordinates returns newly revealed item code null code scrolling occurred viewer doesn represent item based widget param horizontal coordinate param vertical coordinate item scrolled item scroll down null scrolls viewer control item display relative coordinates returns newly revealed item code null code scrolling occurred viewer doesn represent item based widget param horizontal coordinate param vertical coordinate item scrolled item scroll null sets property code null code property removed viewer property replaced property implementation records properties internal table searched linearly overriding method generally required number small efficient representation viewer properties required override code data code code set data code param key property param property code null code property set data string key object assert not null key remove key pair null null length equals key length length null values null string keys string length object values object values length system arraycopy keys system arraycopy keys keys length system arraycopy values values system arraycopy values values values length keys values values add key pair null string key values object length equals key values string keys string length object values object values length system arraycopy keys length system arraycopy values values values length keys length key values values length keys values values sets clears input viewer param input input viewer code null code set input object input viewer implementation code selection provider code method selection viewer making visible this method equivalent code set selection selection false code note implementations set selection revealing tree viewer override set selection selection selection set selection selection false sets selection viewer optionally visible subclasses implement method param selection selection param reveal code true code selection visible code false code set selection selection selection reveal
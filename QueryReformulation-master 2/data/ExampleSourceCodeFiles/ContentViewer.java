/Users/user/eclipse.platform.ui/bundles/org.eclipse.jface/src/org/eclipse/jface/viewers/ContentViewer.java
org eclipse jface viewers org eclipse core runtime assert org eclipse core runtime status org eclipse core runtime status org eclipse jface internal internal policy org eclipse jface util policy org eclipse swt events dispose event org eclipse swt widgets control content viewer model based adapter widget accesses model content provider label provider viewer model consists elements represented objects viewer defines generic infrastructure handling model input updates selections terms elements input querying code content provider code returns elements elements displayed they mapped labels text image viewer code label provider code implementing concrete content viewer typically involves steps create controls viewer constructor optional initialize controls input input changed define viewer specific update methods support selections code set selection code code selection code content viewer viewer this viewer content provider code null code content provider content provider null this viewer input code null code viewer input model viewer content object input null this viewer label provider initially code null code lazily initialized code simple label provider code base label provider label provider null this viewer label provider listener note having viewer register label provider listener label provider avoids define methods internal events label provider listener label provider listener label provider listener initially true set false log when disposed true override label provider changed label provider changed event event control control control control null control disposed log when disposed string message ignored label provider changed notification control diposed this potential memory leak internal policy logging log when disposed false message this logged viewer instance calls policy log log status status policy message runtime exception content viewer handle label provider changed event creates content viewer input content provider label provider content viewer returns content provider viewer code null code view content provider code content viewer code implementation method returns content provider recorded internal variable overriding method generally required overriding subclass code content provider code invoked content provider code null code content provider content provider content provider code content viewer code implementation code input provider code method returns current input viewer code null code viewer input model viewer content override object input input returns label provider viewer code content viewer code implementation method returns label provider recorded internal variable set code set label provider code label provider created remembered returned overriding method generally required overriding subclass code label provider code invoked label provider base label provider label provider label provider null label provider label provider label provider handles dispose event viewer control code content viewer code implementation method disposes viewer label provider content provider subclasses override method perform additional cleanup resources overriding methods invoke code handle dispose code param event dispose event handle dispose dispose event event content provider null content provider input changed input null exception string message exception calling content provider input changed content viewer handle dispose message content provider class name policy log log status status policy message content provider dispose content provider null label provider null label provider remove listener label provider listener label provider dispose label provider null input null handles label provider changed event code content viewer code implementation method calls code label provider changed code complete refresh viewer subclasses reimplement extend param event change event handle label provider changed label provider changed event event label provider changed adds event listener hooks control all subclasses call method control established code content viewer code implementation method hooks dispose events control subclasses override add control hooks code hook control code invoked param control control hook control control control control add dispose listener handle dispose notifies label provider changed code content viewer code implementation method calls code refresh code subclasses reimplement extend label provider changed refresh sets content provider viewer code content viewer code implementation method records content provider internal variable overriding method generally required overriding subclass code set content provider code invoked param content provider content provider content provider set content provider content provider content provider assert not null content provider content provider content provider content provider content provider content provider content provider null object current input input content provider input changed current input null content provider dispose content provider input changed null current input refresh code content viewer code implementation code viewer code method invokes code input changed code content provider code input changed code hook method this method fails viewer content provider subclassers advised override code input changed code method extend method required override set input object input control control control control null control disposed illegal state exception need underlying widget set input has widget disposed assert true content provider null instances content viewer content provider assigned set input method called object input input content provider input changed input input input input call input hook input changed input input sets label provider viewer code content viewer code implementation method ensures label provider connected viewer label provider disconnected viewer overriding method generally required overriding subclass code set label provider code invoked param label provider label provider code null code set label provider base label provider label provider base label provider provider label provider set time label provider provider provider null provider remove listener label provider listener label provider label provider label provider null label provider add listener label provider listener refresh dispose provider refresh items refer stale images provider null internal dispose label provider provider param provider internal dispose label provider base label provider provider provider dispose
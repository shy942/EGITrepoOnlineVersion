/Users/user/eclipse.platform.ui/bundles/org.eclipse.core.commands/src/org/eclipse/core/commands/operations/IUndoableOperation.java
org eclipse core commands operations org eclipse core commands execution exception org eclipse core runtime adaptable org eclipse core runtime progress monitor org eclipse core runtime status undoable operation defines operation executed undone redone operations typically fully defined parameters that created user queried input needed define operation operations determine ability execute undo redo current application they decisions validity based occur operation history that left operation history undoable operation add context operation context equal context add note determining context based equality context matches link undo context matches undo context context param context context add context undo context context returns operation executed current note computation method fast called frequently method optimistic computation returning true perform time consuming computations actual execution operation returning status operation execute time code true code operation executed code false code execute returns operation redone current note computation method fast called frequently method optimistic computation returning true perform time consuming computations actual redo operation returning status operation redone time code true code operation redone code false code redo returns operation undone current note computation method fast called frequently method optimistic computation returning true perform time consuming computations actual undo operation returning status operation undone time code true code operation undone code false code undo dispose operation this method operation longer history implementers method typically unregister listeners dispose execute operation this method called time operation executed param monitor progress monitor code null code reporting progress user param info adaptable code null code caller order supply prompting user when parameter code null code minimally adapter org eclipse swt widgets shell status execution status severity set code code operation successful code code any status assumed represent incompletion execution execution exception exception occurred execution status execute progress monitor monitor adaptable info execution exception returns array contexts assigned operation this method called operation history block avoid deadlock conditions implementers method avoid dispatching waiting threads modify operation history method array contexts undo context contexts return label operation user this label typically combined command strings user undo redo user interfaces string label should code null code string label returns operation matching context context this method called operation history block avoid deadlock conditions implementers method avoid dispatching waiting threads modify operation history method undo context matches undo context param context context question code true code context code false code context undo context context redo operation this method called operation undone param monitor progress monitor code null code reporting progress user param info adaptable code null code caller order supply prompting user when parameter code null code minimally adapter org eclipse swt widgets shell status redo status severity set code code redo successful code code any status assumed represent incompletion redo execution exception exception occurred redo status redo progress monitor monitor adaptable info execution exception remove context operation this method context equal context context list note determining context removing based equality context matches link undo context matches undo context context param context context removed remove context undo context context undo operation this method called operation executed param monitor progress monitor code null code reporting progress user param info adaptable code null code caller order supply prompting user when parameter code null code minimally adapter org eclipse swt widgets shell status undo status severity set code code redo successful code code any status assumed represent incompletion undo execution exception exception occurred undo status undo progress monitor monitor adaptable info execution exception
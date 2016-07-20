/***/
package org.eclipse.ui.tests.keys;

import org.eclipse.core.commands.CommandManager;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.util.Util;

/**
* A binding that can be used for testing purposes. This guarantees that the
* properties tested are inherent to all bindings, and not just a specific type
* of bindings.
*/
final class TestBinding extends Binding {

    /**
* A simple trigger sequence for this test.
*/
    static final class TestTriggerSequence extends TriggerSequence {

        /**
* Constructs a new instance of <code>TestTriggerSequence</code>.
*
* @param myTriggers
*            The triggers to use in constructing this sequence; must
*            not be <code>null</code>.
*/
        public  TestTriggerSequence() {
            super(new Trigger[0]);
        }

        @Override
        public final String format() {
            return toString();
        }

        @Override
        public TriggerSequence[] getPrefixes() {
            return new TriggerSequence[0];
        }
    }

    /**
* A command manager to use for creating commands.
*/
    private static final CommandManager commandManager = new CommandManager();

    /**
* A trigger sequence to be used by all test bindings. This value is never
* <code>null</code>.
*/
    static final TriggerSequence TRIGGER_SEQUENCE = new TestTriggerSequence();

    /**
* Constructs a new instance of <code>TestBinding</code>
*
* @param commandId
*            The command id
* @param schemeId
*            The scheme id
* @param contextId
*            The context id
* @param locale
*            The locale
* @param platform
*            The platform
* @param type
*            The type: SYSTEM or USER
* @param paramaterizations
*            The parameters
*/
     TestBinding(final String commandId, final String schemeId, final String contextId, final String locale, final String platform, final int type, final Parameterization[] parameterizations) {
        super((commandId == null) ? null : new ParameterizedCommand(commandManager.getCommand(commandId), parameterizations), schemeId, contextId, locale, platform, null, type);
    }

    @Override
    public final TriggerSequence getTriggerSequence() {
        return TRIGGER_SEQUENCE;
    }

    @Override
    public final String toString() {
        return Util.ZERO_LENGTH_STRING;
    }
}

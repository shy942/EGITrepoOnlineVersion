/***/
package org.eclipse.ui.internal.actions;

import java.util.Map;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.CommandEvent;
import org.eclipse.core.commands.ICommandListener;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.commands.ICommandImageService;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.services.IServiceLocator;

/**
* Instantiate an action that will execute the command.
* <p>
* This is a legacy bridge class, and should not be used outside of the
* framework. Please use menu contributions to display a command in a menu or
* toolbar.
* </p>
* <p>
* <b>Note:</b> Clients my instantiate, but they must not subclass.
* </p>
*
* @since 3.3
*/
public class CommandAction extends Action {

    private IHandlerService handlerService = null;

    private ParameterizedCommand parameterizedCommand = null;

    private ICommandListener commandListener;

    protected  CommandAction() {
    }

    /**
* Creates the action backed by a command. For commands that don't take
* parameters.
*
* @param serviceLocator
*            The service locator that is closest in lifecycle to this
*            action.
* @param commandIdIn
*            the command id. Must not be <code>null</code>.
*/
    public  CommandAction(IServiceLocator serviceLocator, String commandIdIn) {
        this(serviceLocator, commandIdIn, null);
    }

    /**
* Creates the action backed by a parameterized command. The parameterMap
* must contain only all required parameters, and may contain the optional
* parameters.
*
* @param serviceLocator
*            The service locator that is closest in lifecycle to this
*            action.
* @param commandIdIn
*            the command id. Must not be <code>null</code>.
* @param parameterMap
*            the parameter map. May be <code>null</code>.
*/
    public  CommandAction(IServiceLocator serviceLocator, String commandIdIn, Map parameterMap) {
        if (commandIdIn == null) {
            //$NON-NLS-1$
            throw new NullPointerException("commandIdIn must not be null");
        }
        init(serviceLocator, commandIdIn, parameterMap);
    }

    protected ICommandListener getCommandListener() {
        if (commandListener == null) {
            commandListener = new ICommandListener() {

                @Override
                public void commandChanged(CommandEvent commandEvent) {
                    if (commandEvent.isHandledChanged() || commandEvent.isEnabledChanged()) {
                        if (commandEvent.getCommand().isDefined()) {
                            setEnabled(commandEvent.getCommand().isEnabled());
                        }
                    }
                }
            };
        }
        return commandListener;
    }

    /**
* Build a command from the executable extension information.
*
* @param commandService
*            to get the Command object
* @param commandId
*            the command id for this action
* @param parameterMap
*/
    private void createCommand(ICommandService commandService, String commandId, Map parameterMap) {
        Command cmd = commandService.getCommand(commandId);
        if (!cmd.isDefined()) {
            //$NON-NLS-1$//$NON-NLS-2$
            WorkbenchPlugin.log("Command " + commandId + " is undefined");
            return;
        }
        if (parameterMap == null) {
            parameterizedCommand = new ParameterizedCommand(cmd, null);
            return;
        }
        parameterizedCommand = ParameterizedCommand.generateCommand(cmd, parameterMap);
    }

    public void dispose() {
        // not important for command ID, maybe for command though.
        handlerService = null;
        if (commandListener != null) {
            parameterizedCommand.getCommand().removeCommandListener(commandListener);
            commandListener = null;
        }
        parameterizedCommand = null;
    }

    @Override
    public void runWithEvent(Event event) {
        if (handlerService == null) {
            String commandId = (//$NON-NLS-1$
            parameterizedCommand == null ? //$NON-NLS-1$
            "unknownCommand" : parameterizedCommand.getId());
            WorkbenchPlugin.log(//$NON-NLS-1$
            "Cannot run " + commandId + //$NON-NLS-1$
            " before command action has been initialized");
            return;
        }
        try {
            if (parameterizedCommand != null) {
                handlerService.executeCommand(parameterizedCommand, event);
            }
        } catch (Exception e) {
            WorkbenchPlugin.log(e);
        }
    }

    @Override
    public void run() {
        // hopefully this is never called
        runWithEvent(null);
    }

    protected void init(IServiceLocator serviceLocator, String commandIdIn, Map parameterMap) {
        if (handlerService != null) {
            // already initialized
            return;
        }
        handlerService = serviceLocator.getService(IHandlerService.class);
        ICommandService commandService = serviceLocator.getService(ICommandService.class);
        ICommandImageService commandImageService = serviceLocator.getService(ICommandImageService.class);
        createCommand(commandService, commandIdIn, parameterMap);
        if (parameterizedCommand != null) {
            setId(parameterizedCommand.getId());
            setActionDefinitionId(parameterizedCommand.getId());
            try {
                setText(parameterizedCommand.getName());
            } catch (NotDefinedException e) {
            }
            parameterizedCommand.getCommand().addCommandListener(getCommandListener());
            parameterizedCommand.getCommand().setEnabled(handlerService.getCurrentState());
            setEnabled(parameterizedCommand.getCommand().isEnabled());
            setImageDescriptor(commandImageService.getImageDescriptor(commandIdIn, ICommandImageService.TYPE_DEFAULT));
            setDisabledImageDescriptor(commandImageService.getImageDescriptor(commandIdIn, ICommandImageService.TYPE_DISABLED));
            setHoverImageDescriptor(commandImageService.getImageDescriptor(commandIdIn, ICommandImageService.TYPE_HOVER));
        }
    }

    protected ParameterizedCommand getParameterizedCommand() {
        return parameterizedCommand;
    }

    @Override
    public String getActionDefinitionId() {
        return super.getActionDefinitionId();
    }
}

/***/
package org.eclipse.e4.ui.tests.application;

import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandParameter;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.descriptor.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;

/**
*
*/
public abstract class AbstractModelProcessorImpl {

    @Inject
    @Named("app.base")
    private MApplication application;

    @Inject
    @Named("my.customkey")
    private MCommand command;

    @Execute
    public void run() {
        if (command != null) {
            MCommandParameter param = MCommandsFactory.INSTANCE.createCommandParameter();
            param.setElementId("processor.command." + getSuffix());
            command.getParameters().add(param);
        }
        if (application != null) {
            MPartDescriptor descriptor = MBasicFactory.INSTANCE.createPartDescriptor();
            descriptor.setElementId("processor.descriptor." + getSuffix());
            application.getDescriptors().add(descriptor);
        }
        doRun();
    }

    protected abstract void doRun();

    protected abstract String getSuffix();
}

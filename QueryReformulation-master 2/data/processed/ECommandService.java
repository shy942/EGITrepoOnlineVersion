/***/
package org.eclipse.e4.core.commands;

import java.util.Map;
import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.ParameterizedCommand;

/**
* @noimplement
*/
public interface ECommandService {

    public ParameterizedCommand createCommand(String id, Map<String, Object> parameters);

    /**
* @param id
* @param name
* @param description
* @return
* @noreference
*/
    public Category defineCategory(String id, String name, String description);

    /**
* @param id
* @param name
* @param description
* @param category
* @param parameters
* @return
* @noreference
*/
    public Command defineCommand(String id, String name, String description, Category category, IParameter[] parameters);

    /**
* @param id
* @param name
* @param description
* @param category
* @param parameters
* @param helpContextId
* @return
* @noreference
*/
    public Command defineCommand(String id, String name, String description, Category category, IParameter[] parameters, String helpContextId);

    public Category getCategory(String categoryId);

    public Command getCommand(String commandId);
}

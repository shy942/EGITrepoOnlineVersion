/***/
package org.eclipse.ui.internal.decorators;

/**
* FullTextDecoratorRunnable is the decorator that runs the text
* decoration.
*/
public class FullTextDecoratorRunnable extends FullDecoratorRunnable {

    String result = null;

    String start;

    @Override
    public void run() throws Exception {
        result = decorator.decorateText(start, element);
    }

    /**
* Get the result of the decoration or <code>null</code>
* if there was a failure.
* @return the result
*/
    String getResult() {
        return result;
    }

    /**
* Set the values of the initialString and the decorator
* and object that are going to be used to determine the
* result.
* @param initialString
* @param object
* @param definition
*/
    void setValues(String initialString, Object object, FullDecoratorDefinition definition) {
        setValues(object, definition);
        start = initialString;
        result = null;
    }

    /**
* Clear decorator references.
* @since 3.1
*/
    void clearReferences() {
        decorator = null;
    }
}

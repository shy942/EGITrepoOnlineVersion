/***/
package org.eclipse.e4.ui.css.core.sac;

import org.w3c.css.sac.Parser;

/**
* SAC parser factory interface to get instance of SAC {@link Parser}.
*/
public interface ISACParserFactory {

    /**
* Return default instance of SAC Parser. If preferredParserName is filled,
* it return the instance of SAC Parser registered with this name, otherwise
* this method search teh SAC Parser class name to instanciate into System
* property with key org.w3c.css.sac.parser.
*/
    public Parser makeParser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NullPointerException, ClassCastException;

    /**
* Return instance of SAC Parser registered into the factory with name
* <code>name</code>.
*
* @param name
* @return
* @throws ClassNotFoundException
* @throws IllegalAccessException
* @throws InstantiationException
* @throws NullPointerException
* @throws ClassCastException
*/
    public abstract Parser makeParser(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NullPointerException, ClassCastException;

    ;
}

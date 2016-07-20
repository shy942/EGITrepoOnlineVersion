/***/
package org.eclipse.e4.ui.css.core.impl.sac;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.e4.ui.css.core.SACConstants;
import org.eclipse.e4.ui.css.core.sac.SACParserFactory;
import org.w3c.css.sac.Parser;

/**
* SAC Parser factory implementation. By default, this SAC FActory support
* Flute, SteadyState and Batik SAC Parser.
*/
public class SACParserFactoryImpl extends SACParserFactory {

    private static Map<String, String> parsers = new HashMap();

    static {
        // Register Flute SAC Parser
        registerSACParser(SACConstants.SACPARSER_FLUTE);
        // Register Flute SAC CSS3Parser
        registerSACParser(SACConstants.SACPARSER_FLUTE_CSS3);
        // Register SteadyState SAC Parser
        registerSACParser(SACConstants.SACPARSER_STEADYSTATE);
        // Register Batik SAC Parser
        registerSACParser(SACConstants.SACPARSER_BATIK);
    }

    public  SACParserFactoryImpl() {
        // Flute parser is the default SAC Parser to use.
        super.setPreferredParserName(SACConstants.SACPARSER_BATIK);
    }

    @Override
    public Parser makeParser(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NullPointerException, ClassCastException {
        String classNameParser = parsers.get(name);
        if (classNameParser != null) {
            Class<?> classParser = super.getClass().getClassLoader().loadClass(classNameParser);
            return (Parser) classParser.newInstance();
        }
        throw new IllegalAccessException("SAC parser with name=" + name + " was not registered into SAC parser factory.");
    }

    /**
* Register SAC parser name.
*
* @param parser
*/
    public static void registerSACParser(String parser) {
        registerSACParser(parser, parser);
    }

    /**
* register SAC parser with name <code>name</code> mapped with Class name
* <code>classNameParser</code>.
*
* @param name
* @param classNameParser
*/
    public static void registerSACParser(String name, String classNameParser) {
        parsers.put(name, classNameParser);
    }
}
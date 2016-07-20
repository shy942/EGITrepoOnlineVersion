/***/
package org.eclipse.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.internal.registry.RegistryReader;

/**
* WorkbenchEncoding is a utility class for plug-ins that want to use the list
* of encodings defined by default in the workbench.
*
* @since 3.1
*/
public class WorkbenchEncoding {

    /**
* The method for java.nio.charset.Charset.isSupported(String), or <code>null</code>
* if not present.  Reflection is used here to allow compilation against JCL Foundation (bug 80053).
*/
    private static Method CharsetIsSupportedMethod = null;

    static {
        try {
            //$NON-NLS-1$
            Class charsetClass = Class.forName("java.nio.charset.Charset");
            //$NON-NLS-1$
            CharsetIsSupportedMethod = charsetClass.getMethod("isSupported", new Class[] { String.class });
        } catch (Exception e) {
        }
    }

    private static class EncodingsRegistryReader extends RegistryReader {

        private List encodings;

        /**
* Create a new instance of the receiver.
* @param definedEncodings
*/
        public  EncodingsRegistryReader(List definedEncodings) {
            super();
            encodings = definedEncodings;
        }

        @Override
        protected boolean readElement(IConfigurationElement element) {
            String name = element.getAttribute(IWorkbenchRegistryConstants.ATT_NAME);
            if (name != null) {
                encodings.add(name);
            }
            return true;
        }
    }

    /**
* Get the default encoding from the virtual machine.
*
* @return String
*/
    public static String getWorkbenchDefaultEncoding() {
        //$NON-NLS-1$ //$NON-NLS-2$
        return System.getProperty("file.encoding", "UTF-8");
    }

    /**
* Return the list of encodings defined using the org.eclipse.ui.encodings
* extension point.
*
* @return List of String
*/
    public static List getDefinedEncodings() {
        List definedEncodings = Collections.synchronizedList(new ArrayList());
        EncodingsRegistryReader reader = new EncodingsRegistryReader(definedEncodings);
        reader.readRegistry(Platform.getExtensionRegistry(), PlatformUI.PLUGIN_ID, IWorkbenchRegistryConstants.PL_ENCODINGS);
        //Make it an array in case of concurrency issues with Iterators
        String[] encodings = new String[definedEncodings.size()];
        List invalid = new ArrayList();
        definedEncodings.toArray(encodings);
        for (int i = 0; i < encodings.length; i++) {
            if (!isSupported(encodings[i])) {
                invalid.add(encodings[i]);
            }
        }
        Iterator invalidIterator = invalid.iterator();
        while (invalidIterator.hasNext()) {
            String next = (String) invalidIterator.next();
            WorkbenchPlugin.log(NLS.bind(WorkbenchMessages.WorkbenchEncoding_invalidCharset, next));
            definedEncodings.remove(next);
        }
        return definedEncodings;
    }

    /**
* Returns whether the given encoding is supported in the current runtime.
*
* @param encoding the encoding to test
* @return <code>true</code> if supported or if its support could not be determined,
*   <code>false</code> if not supported
*/
    private static boolean isSupported(String encoding) {
        if (CharsetIsSupportedMethod == null) {
            return true;
        }
        try {
            Object o = CharsetIsSupportedMethod.invoke(null, new Object[] { encoding });
            return Boolean.TRUE.equals(o);
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            return false;
        }
        return true;
    }
}

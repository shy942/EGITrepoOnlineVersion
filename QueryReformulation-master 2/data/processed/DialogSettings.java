/***/
package org.eclipse.jface.dialogs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
* Concrete implementation of a dialog settings (<code>IDialogSettings</code>)
* using a hash table and XML. The dialog store can be read
* from and saved to a stream. All keys and values must be strings or array of
* strings. Primitive types are converted to strings.
* <p>
* This class was not designed to be subclassed.
*
* Here is an example of using a DialogSettings:
* </p>
* <pre>
* <code>
* DialogSettings settings = new DialogSettings("root");
* settings.put("Boolean1",true);
* settings.put("Long1",100);
* settings.put("Array1",new String[]{"aaaa1","bbbb1","cccc1"});
* DialogSettings section = new DialogSettings("sectionName");
* settings.addSection(section);
* section.put("Int2",200);
* section.put("Float2",1.1);
* section.put("Array2",new String[]{"aaaa2","bbbb2","cccc2"});
* settings.save("c:\\temp\\test\\dialog.xml");
* </code>
* </pre>
* @noextend This class is not intended to be subclassed by clients.
*/
public class DialogSettings implements IDialogSettings {

    // The name of the DialogSettings.
    private String name;

    /* A Map of DialogSettings representing each sections in a DialogSettings.
It maps the DialogSettings' name to the DialogSettings */
    private Map<String, IDialogSettings> sections;

    /* A Map with all the keys and values of this sections.
Either the keys an values are restricted to strings. */
    private Map<String, String> items;

    // A Map with all the keys mapped to array of strings.
    private Map<String, String[]> arrayItems;

    //$NON-NLS-1$
    private static final String TAG_SECTION = "section";

    //$NON-NLS-1$
    private static final String TAG_NAME = "name";

    //$NON-NLS-1$
    private static final String TAG_KEY = "key";

    //$NON-NLS-1$
    private static final String TAG_VALUE = "value";

    //$NON-NLS-1$
    private static final String TAG_LIST = "list";

    //$NON-NLS-1$
    private static final String TAG_ITEM = "item";

    /**
* Create an empty dialog settings which loads and saves its
* content to a file.
* Use the methods <code>load(String)</code> and <code>store(String)</code>
* to load and store this dialog settings.
*
* @param sectionName the name of the section in the settings.
*/
    public  DialogSettings(String sectionName) {
        name = sectionName;
        items = new HashMap();
        arrayItems = new HashMap();
        sections = new HashMap();
    }

    @Override
    public IDialogSettings addNewSection(String sectionName) {
        DialogSettings section = new DialogSettings(sectionName);
        addSection(section);
        return section;
    }

    @Override
    public void addSection(IDialogSettings section) {
        sections.put(section.getName(), section);
    }

    /**
* Remove a section in the receiver. If the given section does not exist,
* nothing is done.
*
* @param section
*            the section to be removed. Must not be <code>null</code>.
* @since 3.9
*/
    public void removeSection(IDialogSettings section) {
        if (sections.get(section.getName()) == section) {
            sections.remove(section.getName());
        }
    }

    /**
* Remove a section by name in the receiver. If the given section does not
* exist, nothing is done.
*
* @param sectionName
*            the name of the section to be removed.  Must not be <code>null</code>.
* @return The dialog section removed, or <code>null</code> if it wasn't there.
* @since 3.9
*/
    public IDialogSettings removeSection(String sectionName) {
        return sections.remove(sectionName);
    }

    @Override
    public String get(String key) {
        return items.get(key);
    }

    @Override
    public String[] getArray(String key) {
        return arrayItems.get(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return Boolean.valueOf(items.get(key)).booleanValue();
    }

    @Override
    public double getDouble(String key) throws NumberFormatException {
        String setting = items.get(key);
        if (setting == null) {
            throw new NumberFormatException(//$NON-NLS-1$ //$NON-NLS-2$
            "There is no setting associated with the key \"" + key + "\"");
        }
        return new Double(setting).doubleValue();
    }

    @Override
    public float getFloat(String key) throws NumberFormatException {
        String setting = items.get(key);
        if (setting == null) {
            throw new NumberFormatException(//$NON-NLS-1$ //$NON-NLS-2$
            "There is no setting associated with the key \"" + key + "\"");
        }
        return new Float(setting).floatValue();
    }

    @Override
    public int getInt(String key) throws NumberFormatException {
        String setting = items.get(key);
        if (setting == null) {
            // meet our spec, but this message is clearer.
            throw new NumberFormatException(//$NON-NLS-1$ //$NON-NLS-2$
            "There is no setting associated with the key \"" + key + "\"");
        }
        return Integer.valueOf(setting).intValue();
    }

    @Override
    public long getLong(String key) throws NumberFormatException {
        String setting = items.get(key);
        if (setting == null) {
            //is clearer.
            throw new NumberFormatException(//$NON-NLS-1$ //$NON-NLS-2$
            "There is no setting associated with the key \"" + key + "\"");
        }
        return new Long(setting).longValue();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
* Returns a section with the given name in the given dialog settings. If
* the section doesn't exist yet, then it is first created.
*
* @param settings
*            the parent settings
* @param sectionName
*            the name of the section
* @return the section
*
* @since 3.7
*/
    public static IDialogSettings getOrCreateSection(IDialogSettings settings, String sectionName) {
        IDialogSettings section = settings.getSection(sectionName);
        if (section == null) {
            section = settings.addNewSection(sectionName);
        }
        return section;
    }

    @Override
    public IDialogSettings getSection(String sectionName) {
        return sections.get(sectionName);
    }

    @Override
    public IDialogSettings[] getSections() {
        Collection<IDialogSettings> values = sections.values();
        DialogSettings[] result = new DialogSettings[values.size()];
        values.toArray(result);
        return result;
    }

    @Override
    public void load(Reader r) {
        Document document = null;
        try {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //		parser.setProcessNamespace(true);
            document = parser.parse(new InputSource(r));
            //Strip out any comments first
            Node root = document.getFirstChild();
            while (root.getNodeType() == Node.COMMENT_NODE) {
                document.removeChild(root);
                root = document.getFirstChild();
            }
            load(document, (Element) root);
        } catch (ParserConfigurationException e) {
        } catch (IOException e) {
        } catch (SAXException e) {
        }
    }

    @Override
    public void load(String fileName) throws IOException {
        FileInputStream stream = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, //$NON-NLS-1$
        "utf-8"));
        load(reader);
        reader.close();
    }

    private void load(Document document, Element root) {
        name = root.getAttribute(TAG_NAME);
        NodeList l = root.getElementsByTagName(TAG_ITEM);
        for (int i = 0; i < l.getLength(); i++) {
            Node n = l.item(i);
            if (root == n.getParentNode()) {
                String key = ((Element) l.item(i)).getAttribute(TAG_KEY);
                String value = ((Element) l.item(i)).getAttribute(TAG_VALUE);
                items.put(key, value);
            }
        }
        l = root.getElementsByTagName(TAG_LIST);
        for (int i = 0; i < l.getLength(); i++) {
            Node n = l.item(i);
            if (root == n.getParentNode()) {
                Element child = (Element) l.item(i);
                String key = child.getAttribute(TAG_KEY);
                NodeList list = child.getElementsByTagName(TAG_ITEM);
                List<String> valueList = new ArrayList();
                for (int j = 0; j < list.getLength(); j++) {
                    Element node = (Element) list.item(j);
                    if (child == node.getParentNode()) {
                        valueList.add(node.getAttribute(TAG_VALUE));
                    }
                }
                String[] value = new String[valueList.size()];
                valueList.toArray(value);
                arrayItems.put(key, value);
            }
        }
        l = root.getElementsByTagName(TAG_SECTION);
        for (int i = 0; i < l.getLength(); i++) {
            Node n = l.item(i);
            if (root == n.getParentNode()) {
                //$NON-NLS-1$
                DialogSettings s = new DialogSettings("NoName");
                s.load(document, (Element) n);
                addSection(s);
            }
        }
    }

    @Override
    public void put(String key, String[] value) {
        arrayItems.put(key, value);
    }

    @Override
    public void put(String key, double value) {
        put(key, String.valueOf(value));
    }

    @Override
    public void put(String key, float value) {
        put(key, String.valueOf(value));
    }

    @Override
    public void put(String key, int value) {
        put(key, String.valueOf(value));
    }

    @Override
    public void put(String key, long value) {
        put(key, String.valueOf(value));
    }

    @Override
    public void put(String key, String value) {
        items.put(key, value);
    }

    @Override
    public void put(String key, boolean value) {
        put(key, String.valueOf(value));
    }

    @Override
    public void save(Writer writer) throws IOException {
        final XMLWriter xmlWriter = new XMLWriter(writer);
        save(xmlWriter);
        xmlWriter.flush();
    }

    @Override
    public void save(String fileName) throws IOException {
        FileOutputStream stream = new FileOutputStream(fileName);
        XMLWriter writer = new XMLWriter(stream);
        save(writer);
        writer.close();
    }

    private void save(XMLWriter out) throws IOException {
        HashMap<String, String> attributes = new HashMap(2);
        //$NON-NLS-1$
        attributes.put(TAG_NAME, name == null ? "" : name);
        out.startTag(TAG_SECTION, attributes);
        attributes.clear();
        for (Entry<String, String> entry : items.entrySet()) {
            String key = entry.getKey();
            //$NON-NLS-1$
            attributes.put(TAG_KEY, key == null ? "" : key);
            String string = entry.getValue();
            //$NON-NLS-1$
            attributes.put(TAG_VALUE, string == null ? "" : string);
            out.printTag(TAG_ITEM, attributes, true);
        }
        attributes.clear();
        for (Entry<String, String[]> entry : arrayItems.entrySet()) {
            String key = entry.getKey();
            //$NON-NLS-1$
            attributes.put(TAG_KEY, key == null ? "" : key);
            out.startTag(TAG_LIST, attributes);
            String[] value = entry.getValue();
            attributes.clear();
            if (value != null) {
                for (int index = 0; index < value.length; index++) {
                    String string = value[index];
                    //$NON-NLS-1$
                    attributes.put(TAG_VALUE, string == null ? "" : string);
                    out.printTag(TAG_ITEM, attributes, true);
                }
            }
            out.endTag(TAG_LIST);
            attributes.clear();
        }
        for (Iterator<IDialogSettings> i = sections.values().iterator(); i.hasNext(); ) {
            ((DialogSettings) i.next()).save(out);
        }
        out.endTag(TAG_SECTION);
    }

    /**
* A simple XML writer.  Using this instead of the javax.xml.transform classes allows
* compilation against JCL Foundation (bug 80059).
*/
    private static class XMLWriter extends BufferedWriter {

        /** current number of tabs to use for indent */
        protected int tab;

        /** the xml header */
        //$NON-NLS-1$
        protected static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

        /**
* Create a new XMLWriter
* @param output the stream to write the output to
* @throws IOException
*/
        public  XMLWriter(OutputStream output) throws IOException {
            //$NON-NLS-1$
            this(new OutputStreamWriter(output, "UTF8"));
        }

        /**
* Create a new XMLWriter
* @param output the write to used when writing to
* @throws IOException
*/
        public  XMLWriter(Writer output) throws IOException {
            super(output);
            tab = 0;
            writeln(XML_VERSION);
        }

        private void writeln(String text) throws IOException {
            write(text);
            newLine();
        }

        /**
* write the intended end tag
* @param name the name of the tag to end
* @throws IOException
*/
        public void endTag(String name) throws IOException {
            tab--;
            //$NON-NLS-1$
            printTag("/" + name, null, false);
        }

        private void printTabulation() throws IOException {
            for (int i = 0; i < tab; i++) {
                super.write('\t');
            }
        }

        /**
* write the tag to the stream and format it by itending it and add new line after the tag
* @param name the name of the tag
* @param parameters map of parameters
* @param close should the tag be ended automatically (=> empty tag)
* @throws IOException
*/
        public void printTag(String name, HashMap<String, String> parameters, boolean close) throws IOException {
            printTag(name, parameters, true, true, close);
        }

        private void printTag(String name, HashMap<String, String> parameters, boolean shouldTab, boolean newLine, boolean close) throws IOException {
            StringBuffer sb = new StringBuffer();
            sb.append('<');
            sb.append(name);
            if (parameters != null) {
                for (Entry<String, String> entry : parameters.entrySet()) {
                    //$NON-NLS-1$
                    sb.append(" ");
                    String key = entry.getKey();
                    sb.append(key);
                    //$NON-NLS-1$
                    sb.append("=\"");
                    sb.append(getEscaped(String.valueOf(entry.getValue())));
                    //$NON-NLS-1$
                    sb.append("\"");
                }
            }
            if (close) {
                sb.append('/');
            }
            sb.append('>');
            if (shouldTab) {
                printTabulation();
            }
            if (newLine) {
                writeln(sb.toString());
            } else {
                write(sb.toString());
            }
        }

        /**
* start the tag
* @param name the name of the tag
* @param parameters map of parameters
* @throws IOException
*/
        public void startTag(String name, HashMap<String, String> parameters) throws IOException {
            startTag(name, parameters, true);
            tab++;
        }

        private void startTag(String name, HashMap<String, String> parameters, boolean newLine) throws IOException {
            printTag(name, parameters, true, newLine, false);
        }

        private static void appendEscapedChar(StringBuffer buffer, char c) {
            String replacement = getReplacement(c);
            if (replacement != null) {
                buffer.append('&');
                buffer.append(replacement);
                buffer.append(';');
            } else {
                buffer.append(c);
            }
        }

        private static String getEscaped(String s) {
            StringBuffer result = new StringBuffer(s.length() + 10);
            for (int i = 0; i < s.length(); ++i) {
                appendEscapedChar(result, s.charAt(i));
            }
            return result.toString();
        }

        private static String getReplacement(char c) {
            // being converted to spaces on deserialization
            switch(c) {
                case '<':
                    //$NON-NLS-1$
                    return "lt";
                case '>':
                    //$NON-NLS-1$
                    return "gt";
                case '"':
                    //$NON-NLS-1$
                    return "quot";
                case '\'':
                    //$NON-NLS-1$
                    return "apos";
                case '&':
                    //$NON-NLS-1$
                    return "amp";
                case '\r':
                    //$NON-NLS-1$
                    return "#x0D";
                case '\n':
                    //$NON-NLS-1$
                    return "#x0A";
                case '	':
                    //$NON-NLS-1$
                    return "#x09";
            }
            return null;
        }
    }
}
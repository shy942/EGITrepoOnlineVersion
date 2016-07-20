/***/
package org.eclipse.ui.internal.forms.widgets;

import java.util.HashMap;
import org.eclipse.jface.resource.DeviceResourceException;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class FormFonts {

    private static FormFonts instance;

    public static FormFonts getInstance() {
        if (instance == null)
            instance = new FormFonts();
        return instance;
    }

    private ResourceManagerManger manager = new ResourceManagerManger();

    private HashMap<Font, BoldFontDescriptor> descriptors;

    private  FormFonts() {
    }

    private class BoldFontDescriptor extends FontDescriptor {

        private FontData[] fFontData;

         BoldFontDescriptor(Font font) {
            fFontData = font.getFontData();
            for (FontData element : fFontData) {
                element.setStyle(element.getStyle() | SWT.BOLD);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof BoldFontDescriptor) {
                BoldFontDescriptor desc = (BoldFontDescriptor) obj;
                if (desc.fFontData.length != fFontData.length)
                    return false;
                for (int i = 0; i < fFontData.length; i++) if (!fFontData[i].equals(desc.fFontData[i]))
                    return false;
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            for (FontData element : fFontData) hash = hash * 7 + element.hashCode();
            return hash;
        }

        @Override
        public Font createFont(Device device) throws DeviceResourceException {
            return new Font(device, fFontData);
        }

        @Override
        public void destroyFont(Font previouslyCreatedFont) {
            previouslyCreatedFont.dispose();
        }
    }

    public Font getBoldFont(Display display, Font font) {
        checkHashMaps();
        BoldFontDescriptor desc = new BoldFontDescriptor(font);
        Font result = manager.getResourceManager(display).createFont(desc);
        descriptors.put(result, desc);
        return result;
    }

    public boolean markFinished(Font boldFont, Display display) {
        checkHashMaps();
        BoldFontDescriptor desc = descriptors.get(boldFont);
        if (desc != null) {
            LocalResourceManager resourceManager = manager.getResourceManager(display);
            resourceManager.destroyFont(desc);
            if (resourceManager.find(desc) == null) {
                descriptors.remove(boldFont);
                validateHashMaps();
            }
            return true;
        }
        // if the image was not found, dispose of it for the caller
        boldFont.dispose();
        return false;
    }

    private void checkHashMaps() {
        if (descriptors == null)
            descriptors = new HashMap();
    }

    private void validateHashMaps() {
        if (descriptors.size() == 0)
            descriptors = null;
    }
}
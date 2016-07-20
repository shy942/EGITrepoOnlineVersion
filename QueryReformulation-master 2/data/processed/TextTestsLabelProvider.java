/***/
package org.eclipse.ui.tests.views.properties.tabbed.text;

import java.util.StringTokenizer;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
* Label provider for the title bar for the tabbed property view.
*
* @author Anthony Hunter
*/
public class TextTestsLabelProvider extends LabelProvider {

    /**
* @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
*/
    @Override
    public Image getImage(Object obj) {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
    }

    /**
* @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
*/
    @Override
    public String getText(Object obj) {
        if (obj instanceof ITextSelection) {
            ITextSelection textSelection = (ITextSelection) obj;
            if (textSelection.getLength() != 0) {
                StringTokenizer tokenizer = new StringTokenizer(textSelection.getText());
                int size = 0;
                while (tokenizer.hasMoreTokens()) {
                    size++;
                    tokenizer.nextToken();
                }
                if (size == 1) {
                    return textSelection.getText();
                }
                //$NON-NLS-1$
                return size + " words selected";
            }
        }
        return null;
    }
}

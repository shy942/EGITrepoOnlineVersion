/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
* The ColumnLabelProvider is the label provider for viewers
* that have column support such as {@link TreeViewer} and
* {@link TableViewer}
*
* <p><b>This classes is intended to be subclassed</b></p>
*
* @since 3.3
*
*/
public class ColumnLabelProvider extends CellLabelProvider implements IFontProvider, IColorProvider, ILabelProvider {

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        cell.setText(getText(element));
        Image image = getImage(element);
        cell.setImage(image);
        cell.setBackground(getBackground(element));
        cell.setForeground(getForeground(element));
        cell.setFont(getFont(element));
    }

    @Override
    public Font getFont(Object element) {
        return null;
    }

    @Override
    public Color getBackground(Object element) {
        return null;
    }

    @Override
    public Color getForeground(Object element) {
        return null;
    }

    @Override
    public Image getImage(Object element) {
        return null;
    }

    @Override
    public String getText(Object element) {
        //$NON-NLS-1$
        return element == null ? "" : element.toString();
    }
}

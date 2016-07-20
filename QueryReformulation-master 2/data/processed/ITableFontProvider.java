/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.graphics.Font;

/**
* The ITableFontProvider is a font provider that provides fonts to
* individual cells within tables.
* @since 3.1
*/
public interface ITableFontProvider {

    /**
* Provides a font for the given element at index
* columnIndex.
* @param element The element being displayed
* @param columnIndex The index of the column being displayed
* @return Font
*/
    public Font getFont(Object element, int columnIndex);
}

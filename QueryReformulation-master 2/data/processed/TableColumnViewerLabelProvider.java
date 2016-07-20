/***/
package org.eclipse.jface.viewers;

/**
* TableColumnViewerLabelProvider is the mapping from the table based providers
* to the ViewerLabelProvider.
*
* @since 3.3
* @see ITableLabelProvider
* @see ITableColorProvider
* @see ITableFontProvider
*
*/
class TableColumnViewerLabelProvider extends WrappedViewerLabelProvider {

    private ITableLabelProvider tableLabelProvider;

    private ITableColorProvider tableColorProvider;

    private ITableFontProvider tableFontProvider;

    /**
* Create a new instance of the receiver.
*
* @param labelProvider
*            instance of a table based label provider
* @see ITableLabelProvider
* @see ITableColorProvider
* @see ITableFontProvider
*/
    public  TableColumnViewerLabelProvider(IBaseLabelProvider labelProvider) {
        super(labelProvider);
        if (labelProvider instanceof ITableLabelProvider)
            tableLabelProvider = (ITableLabelProvider) labelProvider;
        if (labelProvider instanceof ITableColorProvider)
            tableColorProvider = (ITableColorProvider) labelProvider;
        if (labelProvider instanceof ITableFontProvider)
            tableFontProvider = (ITableFontProvider) labelProvider;
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        int index = cell.getColumnIndex();
        if (tableLabelProvider == null) {
            cell.setText(getLabelProvider().getText(element));
            cell.setImage(getLabelProvider().getImage(element));
        } else {
            cell.setText(tableLabelProvider.getColumnText(element, index));
            cell.setImage(tableLabelProvider.getColumnImage(element, index));
        }
        if (tableColorProvider == null) {
            if (getColorProvider() != null) {
                cell.setBackground(getColorProvider().getBackground(element));
                cell.setForeground(getColorProvider().getForeground(element));
            }
        } else {
            cell.setBackground(tableColorProvider.getBackground(element, index));
            cell.setForeground(tableColorProvider.getForeground(element, index));
        }
        if (tableFontProvider == null) {
            if (getFontProvider() != null)
                cell.setFont(getFontProvider().getFont(element));
        } else
            cell.setFont(tableFontProvider.getFont(element, index));
    }
}

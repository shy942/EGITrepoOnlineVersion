/***/
package org.eclipse.e4.ui.workbench.renderers.swt;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.e4.ui.workbench.Selector;

public class ToolItemUpdater {

    private List<AbstractContributionItem> itemsToCheck = new ArrayList();

    private final List<AbstractContributionItem> orphanedToolItems = new ArrayList();

    void registerItem(AbstractContributionItem item) {
        if (!itemsToCheck.contains(item)) {
            itemsToCheck.add(item);
        }
    }

    void removeItem(AbstractContributionItem item) {
        itemsToCheck.remove(item);
    }

    public void updateContributionItems(Selector selector) {
        for (final AbstractContributionItem ci : itemsToCheck) {
            if (ci.getModel() != null && ci.getModel().getParent() != null && selector.select(ci.getModel())) {
                ci.updateItemEnablement();
            } else {
                orphanedToolItems.add(ci);
            }
        }
        if (!orphanedToolItems.isEmpty()) {
            itemsToCheck.removeAll(orphanedToolItems);
            orphanedToolItems.clear();
        }
    }
}

/***/
package org.eclipse.ui.internal.quickaccess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.themes.ColorUtil;

/**
* Provides the contents for the quick access shell created by
* {@link SearchField}. This was also used by {@link QuickAccessDialog} prior to
* e4. The SearchField is responsible for handling opening and closing the shell
* as well as setting {@link #setShowAllMatches(boolean)}.
*/
public abstract class QuickAccessContents {

    /**
* When opened in a popup we were given the command used to open it. Now
* that we have a shell, we are just using a hard coded command id.
*/
    //$NON-NLS-1$
    private static final String QUICK_ACCESS_COMMAND_ID = "org.eclipse.ui.window.quickAccess";

    private static final int INITIAL_COUNT_PER_PROVIDER = 5;

    private static final int MAX_COUNT_TOTAL = 20;

    protected Text filterText;

    private QuickAccessProvider[] providers;

    protected Table table;

    protected Label infoLabel;

    private LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources());

    protected String rememberedText;

    /**
* A color for dulled out items created by mixing the table foreground. Will
* be disposed when the {@link #resourceManager} is disposed.
*/
    private Color grayColor;

    private TextLayout textLayout;

    private boolean showAllMatches = false;

    protected boolean resized = false;

    private TriggerSequence keySequence;

    public  QuickAccessContents(QuickAccessProvider[] providers) {
        this.providers = providers;
    }

    /**
* Returns the number of items the table can fit in its current layout
*/
    private int computeNumberOfItems() {
        Rectangle rect = table.getClientArea();
        int itemHeight = table.getItemHeight();
        int headerHeight = table.getHeaderHeight();
        return (rect.height - headerHeight + itemHeight - 1) / (itemHeight + table.getGridLineWidth());
    }

    /**
* Refreshes the contents of the quick access shell
*
* @param filter
*            The filter text to apply to results
*
*/
    public void refresh(String filter) {
        if (table != null) {
            boolean filterTextEmpty = filter.length() == 0;
            // perfect match, to be selected in the table if not null
            QuickAccessElement perfectMatch = getPerfectMatch(filter);
            List<QuickAccessEntry>[] entries = computeMatchingEntries(filter, perfectMatch);
            int selectionIndex = refreshTable(perfectMatch, entries);
            if (table.getItemCount() > 0) {
                table.setSelection(selectionIndex);
                hideHintText();
            } else if (filterTextEmpty) {
                showHintText(QuickAccessMessages.QuickAccess_StartTypingToFindMatches, grayColor);
            } else {
                showHintText(QuickAccessMessages.QuickAccessContents_NoMatchingResults, grayColor);
            }
            // update info as-you-type
            updateInfoLabel();
            updateFeedback(filterTextEmpty, showAllMatches);
        }
    }

    /**
* Allows the quick access content owner to mark a quick access element as
* being a perfect match, putting it at the start of the table.
*
* @param filter
*            the filter text used to find a match
* @return an element to be put at the top of the table or <code>null</code>
*/
    protected abstract QuickAccessElement getPerfectMatch(String filter);

    /**
* Notifies the quick access content owner that the contents of the table
* have been changed.
*
* @param filterTextEmpty
*            whether the filter text used to calculate matches was empty
* @param showAllMatches
*            whether the results were constrained by the size of the dialog
*
*/
    protected abstract void updateFeedback(boolean filterTextEmpty, boolean showAllMatches);

    /**
* Sets whether to display all matches to the current filter or limit the
* results. Will refresh the table contents and update the info label.
*
* @param showAll
*            whether to display all matches
*/
    public void setShowAllMatches(boolean showAll) {
        if (showAllMatches != showAll) {
            showAllMatches = showAll;
            updateInfoLabel();
            refresh(filterText.getText().toLowerCase());
        }
    }

    private void updateInfoLabel() {
        if (infoLabel != null) {
            TriggerSequence sequence = getTriggerSequence();
            boolean forceHide = (getNumberOfFilteredResults() == 0) || (showAllMatches && (table.getItemCount() <= computeNumberOfItems()));
            if (sequence == null || forceHide) {
                //$NON-NLS-1$
                infoLabel.setText("");
            } else if (showAllMatches) {
                infoLabel.setText(NLS.bind(QuickAccessMessages.QuickAccessContents_PressKeyToLimitResults, sequence.format()));
            } else {
                infoLabel.setText(NLS.bind(QuickAccessMessages.QuickAccess_PressKeyToShowAllMatches, sequence.format()));
            }
            infoLabel.getParent().layout(true);
        }
    }

    /**
* Returns the trigger sequence that can be used to open the quick access
* dialog as well as toggle the show all results feature. Can return
* <code>null</code> if no trigger sequence is known.
*
* @return the trigger sequence used to open the quick access or
*         <code>null</code>
*/
    public TriggerSequence getTriggerSequence() {
        if (keySequence == null) {
            IBindingService bindingService = Adapters.adapt(PlatformUI.getWorkbench(), IBindingService.class);
            keySequence = bindingService.getBestActiveBindingFor(QUICK_ACCESS_COMMAND_ID);
        }
        return keySequence;
    }

    /**
* Return whether the shell is currently set to display all matches or limit
* the results.
*
* @return whether all matches will be displayed
*/
    public boolean getShowAllMatches() {
        return showAllMatches;
    }

    private int refreshTable(QuickAccessElement perfectMatch, List<QuickAccessEntry>[] entries) {
        if (table.getItemCount() > entries.length && table.getItemCount() - entries.length > 20) {
            table.removeAll();
        }
        TableItem[] items = table.getItems();
        int selectionIndex = -1;
        int index = 0;
        for (int i = 0; i < providers.length; i++) {
            if (entries[i] != null) {
                boolean firstEntry = true;
                for (Iterator<QuickAccessEntry> it = entries[i].iterator(); it.hasNext(); ) {
                    QuickAccessEntry entry = it.next();
                    entry.firstInCategory = firstEntry;
                    firstEntry = false;
                    if (!it.hasNext()) {
                        entry.lastInCategory = true;
                    }
                    TableItem item;
                    if (index < items.length) {
                        item = items[index];
                        table.clear(index);
                    } else {
                        item = new TableItem(table, SWT.NONE);
                    }
                    if (perfectMatch == entry.element && selectionIndex == -1) {
                        selectionIndex = index;
                    }
                    item.setData(entry);
                    item.setText(0, entry.provider.getName());
                    item.setText(1, entry.element.getLabel());
                    if (Util.isWpf()) {
                        item.setImage(1, entry.getImage(entry.element, resourceManager));
                    }
                    index++;
                }
            }
        }
        if (index < items.length) {
            table.remove(index, items.length - 1);
        }
        if (selectionIndex == -1) {
            selectionIndex = 0;
        }
        return selectionIndex;
    }

    int numberOfFilteredResults;

    /**
* Compute how many items are effectively filtered at a specific point in
* time. So doing, the quick access content can perform operations that
* depends on this number, i.e. hide the info label.
*
* @return number number of elements filtered
*/
    protected int getNumberOfFilteredResults() {
        return numberOfFilteredResults;
    }

    /**
* Returns a list per provider containing matching {@link QuickAccessEntry}
* that should be displayed in the table given a text filter and a perfect
* match entry that should be given priority. The number of items returned
* is affected by {@link #getShowAllMatches()} and the size of the table's
* composite.
*
* @param filter
*            the string text filter to apply, possibly empty
* @param perfectMatch
*            a quick access element that should be given priority or
*            <code>null</code>
* @return the array of lists (one per provider) containg the quick access
*         entries that should be added to the table, possibly empty
*/
    private List<QuickAccessEntry>[] computeMatchingEntries(String filter, QuickAccessElement perfectMatch) {
        // collect matches in an array of lists
        @SuppressWarnings("unchecked") List<QuickAccessEntry>[] entries = new List[providers.length];
        int maxCount = computeNumberOfItems();
        int[] indexPerProvider = new int[providers.length];
        int countPerProvider = Math.min(maxCount / 4, INITIAL_COUNT_PER_PROVIDER);
        int prevPick = 0;
        int countTotal = 0;
        boolean perfectMatchAdded = true;
        if (perfectMatch != null) {
            // reserve one entry for the perfect match
            maxCount--;
            perfectMatchAdded = false;
        }
        boolean done;
        String category = null;
        do {
            // will be set to false if we find a provider with remaining
            // elements
            done = true;
            // check for a category filter, like "Views: "
            Matcher categoryMatcher = getCategoryPattern().matcher(filter);
            if (categoryMatcher.matches()) {
                category = categoryMatcher.group(1);
                //$NON-NLS-1$
                filter = category + " " + categoryMatcher.group(2);
            }
            for (int i = 0; i < providers.length && (showAllMatches || countTotal < maxCount); i++) {
                if (entries[i] == null) {
                    entries[i] = new ArrayList();
                    indexPerProvider[i] = 0;
                }
                int count = 0;
                QuickAccessProvider provider = providers[i];
                // when category is specified, skip providers except the
                // specified one and the previous pick provider
                boolean isPreviousPickProvider = (provider instanceof PreviousPicksProvider);
                if (category != null && !category.equalsIgnoreCase(provider.getName()) && !isPreviousPickProvider) {
                    continue;
                }
                if (filter.length() > 0 || provider.isAlwaysPresent() || showAllMatches) {
                    QuickAccessElement[] sortedElements = provider.getElementsSorted();
                    // count number or previous picks
                    if ((provider instanceof PreviousPicksProvider)) {
                        prevPick = sortedElements.length;
                    }
                    int j = indexPerProvider[i];
                    while (j < sortedElements.length && (showAllMatches || (count < countPerProvider && countTotal < maxCount))) {
                        QuickAccessElement element = sortedElements[j];
                        QuickAccessEntry entry = null;
                        if (filter.length() == 0) {
                            if (i == 0 || showAllMatches) {
                                entry = new QuickAccessEntry(element, provider, new int[0][0], new int[0][0], QuickAccessEntry.MATCH_PERFECT);
                            } else {
                                entry = null;
                            }
                        } else {
                            QuickAccessEntry possibleMatch = element.match(filter, provider);
                            if (possibleMatch != null) {
                                entry = possibleMatch;
                            }
                        }
                        if (entryEnabled(provider, entry)) {
                            entries[i].add(entry);
                            count++;
                            countTotal++;
                            if (i == 0 && entry.element == perfectMatch) {
                                perfectMatchAdded = true;
                                maxCount = MAX_COUNT_TOTAL;
                            }
                        }
                        j++;
                    }
                    indexPerProvider[i] = j;
                    if (j < sortedElements.length) {
                        done = false;
                    }
                }
            }
            // from now on, add one element per provider
            countPerProvider = 1;
        } while ((showAllMatches || countTotal < maxCount) && !done);
        if (!perfectMatchAdded) {
            QuickAccessEntry entry = perfectMatch.match(filter, providers[0]);
            if (entryEnabled(providers[0], entry)) {
                if (entries[0] == null) {
                    entries[0] = new ArrayList();
                    indexPerProvider[0] = 0;
                }
                entries[0].add(entry);
            }
        }
        // number of items matching the filtered search
        numberOfFilteredResults = countTotal - prevPick;
        return entries;
    }

    Pattern categoryPattern;

    /**
* Return a pattern like {@code "^(:?Views|Perspective):\\s?(.*)"}, with all
* the provider names separated by semicolon.
*
* @return Returns the patternProvider.
*/
    protected Pattern getCategoryPattern() {
        if (categoryPattern == null) {
            // build regex like "^(:?Views|Perspective):\\s?(.*)"
            StringBuilder sb = new StringBuilder();
            //$NON-NLS-1$
            sb.append("^(:?");
            for (int i = 0; i < providers.length; i++) {
                if (i != 0)
                    //$NON-NLS-1$
                    sb.append("|");
                sb.append(providers[i].getName());
            }
            //$NON-NLS-1$
            sb.append("):\\s?(.*)");
            String regex = sb.toString();
            categoryPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        }
        return categoryPattern;
    }

    /**
* @param provider
* @param entry
* @return <code>true</code> if the entry is enabled
*/
    private boolean entryEnabled(QuickAccessProvider provider, QuickAccessEntry entry) {
        if (entry == null) {
            return false;
        }
        // also provide the element
        if (provider instanceof PreviousPicksProvider) {
            QuickAccessElement element = entry.element;
            final QuickAccessProvider originalProvider = element.getProvider();
            QuickAccessElement match = originalProvider.getElementForId(element.getId());
            return match != null;
        }
        return true;
    }

    private void doDispose() {
        if (textLayout != null && !textLayout.isDisposed()) {
            textLayout.dispose();
        }
        if (resourceManager != null) {
            // Disposing the resource manager will dispose the color
            resourceManager.dispose();
            resourceManager = null;
        }
    }

    protected IDialogSettings getDialogSettings() {
        final IDialogSettings workbenchDialogSettings = WorkbenchPlugin.getDefault().getDialogSettings();
        IDialogSettings result = workbenchDialogSettings.getSection(getId());
        if (result == null) {
            result = workbenchDialogSettings.addNewSection(getId());
        }
        return result;
    }

    protected String getId() {
        //$NON-NLS-1$
        return "org.eclipse.ui.internal.QuickAccess";
    }

    protected abstract void handleElementSelected(String text, Object selectedElement);

    private void handleSelection() {
        QuickAccessElement selectedElement = null;
        String text = filterText.getText().toLowerCase();
        if (table.getSelectionCount() == 1) {
            QuickAccessEntry entry = (QuickAccessEntry) table.getSelection()[0].getData();
            selectedElement = entry == null ? null : entry.element;
        }
        if (selectedElement != null) {
            doClose();
            handleElementSelected(text, selectedElement);
        }
    }

    /**
* Should be called by the owner of the parent composite when the shell is
* being activated (made visible). This allows the show all keybinding to be
* updated.
*/
    public void preOpen() {
        // Make sure we always start filtering
        setShowAllMatches(false);
        // In case the key binding has changed, update the label
        keySequence = null;
        updateInfoLabel();
    }

    /**
* Informs the owner of the parent composite that the quick access dialog
* should be closed
*/
    protected abstract void doClose();

    /**
* Allows the dialog contents to interact correctly with the text box used to open it
* @param filterText text box to hook up
*/
    public void hookFilterText(Text filterText) {
        this.filterText = filterText;
        filterText.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.keyCode) {
                    case SWT.CR:
                    case SWT.KEYPAD_CR:
                        handleSelection();
                        break;
                    case SWT.ARROW_DOWN:
                        int index = table.getSelectionIndex();
                        if (index != -1 && table.getItemCount() > index + 1) {
                            table.setSelection(index + 1);
                        }
                        break;
                    case SWT.ARROW_UP:
                        index = table.getSelectionIndex();
                        if (index != -1 && index >= 1) {
                            table.setSelection(index - 1);
                        }
                        break;
                    case SWT.ESC:
                        doClose();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            // do nothing
            }
        });
        filterText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                String text = ((Text) e.widget).getText().toLowerCase();
                refresh(text);
            }
        });
    }

    private Text hintText;

    private boolean displayHintText;

    /** Create HintText as child of the given parent composite */
    Text createHintText(Composite composite, int defaultOrientation) {
        hintText = new Text(composite, SWT.FILL);
        hintText.setOrientation(defaultOrientation);
        displayHintText = true;
        return hintText;
    }

    /** Hide the hint text */
    void hideHintText() {
        if (displayHintText) {
            setHintTextToDisplay(false);
        }
    }

    /** Show the hint text with the given color */
    void showHintText(String text, Color color) {
        if (hintText == null) {
            // toolbar hidden
            return;
        }
        hintText.setText(text);
        if (color != null) {
            hintText.setForeground(color);
        }
        if (!displayHintText) {
            setHintTextToDisplay(true);
        }
    }

    /**
* Sets hint text to be displayed and requests the layout
*
* @param toDisplay
*/
    private void setHintTextToDisplay(boolean toDisplay) {
        GridData data = (GridData) hintText.getLayoutData();
        data.exclude = !toDisplay;
        hintText.setVisible(toDisplay);
        hintText.requestLayout();
        this.displayHintText = toDisplay;
    }

    /**
* Creates the table providing the contents for the quick access dialog
*
* @param composite parent composite with {@link GridLayout}
* @param defaultOrientation the window orientation to use for the table {@link SWT#RIGHT_TO_LEFT} or {@link SWT#LEFT_TO_RIGHT}
* @return the created table
*/
    public Table createTable(Composite composite, int defaultOrientation) {
        composite.addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(DisposeEvent e) {
                doDispose();
            }
        });
        Composite tableComposite = new Composite(composite, SWT.NONE);
        GridDataFactory.fillDefaults().grab(true, true).applyTo(tableComposite);
        TableColumnLayout tableColumnLayout = new TableColumnLayout();
        tableComposite.setLayout(tableColumnLayout);
        table = new Table(tableComposite, SWT.SINGLE | SWT.FULL_SELECTION);
        textLayout = new TextLayout(table.getDisplay());
        textLayout.setOrientation(defaultOrientation);
        Font boldFont = resourceManager.createFont(FontDescriptor.createFrom(JFaceResources.getDialogFont()).setStyle(SWT.BOLD));
        textLayout.setFont(table.getFont());
        textLayout.setText(QuickAccessMessages.QuickAccess_AvailableCategories);
        int maxProviderWidth = (textLayout.getBounds().width);
        textLayout.setFont(boldFont);
        for (int i = 0; i < providers.length; i++) {
            QuickAccessProvider provider = providers[i];
            textLayout.setText(provider.getName());
            int width = (textLayout.getBounds().width);
            if (width > maxProviderWidth) {
                maxProviderWidth = width;
            }
        }
        tableColumnLayout.setColumnData(new TableColumn(table, SWT.NONE), new ColumnWeightData(0, maxProviderWidth));
        tableColumnLayout.setColumnData(new TableColumn(table, SWT.NONE), new ColumnWeightData(100, 100));
        table.getShell().addControlListener(new ControlAdapter() {

            @Override
            public void controlResized(ControlEvent e) {
                if (!showAllMatches) {
                    if (!resized) {
                        resized = true;
                        e.display.timerExec(100, new Runnable() {

                            @Override
                            public void run() {
                                if (table != null && !table.isDisposed() && filterText != null && !filterText.isDisposed()) {
                                    refresh(filterText.getText().toLowerCase());
                                }
                                resized = false;
                            }
                        });
                    }
                }
            }
        });
        table.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.ARROW_UP && table.getSelectionIndex() == 0) {
                    filterText.setFocus();
                } else if (e.character == SWT.ESC) {
                    doClose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            // do nothing
            }
        });
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseUp(MouseEvent e) {
                if (table.getSelectionCount() < 1)
                    return;
                if (e.button != 1)
                    return;
                if (table.equals(e.getSource())) {
                    Object o = table.getItem(new Point(e.x, e.y));
                    TableItem selection = table.getSelection()[0];
                    if (selection.equals(o))
                        handleSelection();
                }
            }
        });
        table.addMouseMoveListener(new MouseMoveListener() {

            TableItem lastItem = null;

            @Override
            public void mouseMove(MouseEvent e) {
                if (table.equals(e.getSource())) {
                    Object o = table.getItem(new Point(e.x, e.y));
                    if (lastItem == null ^ o == null) {
                        table.setCursor(o == null ? null : table.getDisplay().getSystemCursor(SWT.CURSOR_HAND));
                    }
                    if (o instanceof TableItem) {
                        if (!o.equals(lastItem)) {
                            lastItem = (TableItem) o;
                            table.setSelection(new TableItem[] { lastItem });
                        }
                    } else if (o == null) {
                        lastItem = null;
                    }
                }
            }
        });
        table.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
            // do nothing
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                handleSelection();
            }
        });
        final TextStyle boldStyle;
        if (PlatformUI.getPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.USE_COLORED_LABELS)) {
            boldStyle = new TextStyle(boldFont, null, null);
            grayColor = resourceManager.createColor(ColorUtil.blend(table.getBackground().getRGB(), table.getForeground().getRGB()));
        } else {
            boldStyle = null;
        }
        Listener listener = new Listener() {

            @Override
            public void handleEvent(Event event) {
                QuickAccessEntry entry = (QuickAccessEntry) event.item.getData();
                if (entry != null) {
                    switch(event.type) {
                        case SWT.MeasureItem:
                            entry.measure(event, textLayout, resourceManager, boldStyle);
                            break;
                        case SWT.PaintItem:
                            entry.paint(event, textLayout, resourceManager, boldStyle, grayColor);
                            break;
                        case SWT.EraseItem:
                            entry.erase(event);
                            break;
                    }
                }
            }
        };
        table.addListener(SWT.MeasureItem, listener);
        table.addListener(SWT.EraseItem, listener);
        table.addListener(SWT.PaintItem, listener);
        return table;
    }

    /**
* Creates a label which will display the key binding to expand
* the search results.
*
* @param parent parent composite with {@link GridLayout}
* @return the created label
*/
    public Label createInfoLabel(Composite parent) {
        infoLabel = new Label(parent, SWT.NONE);
        infoLabel.setFont(parent.getFont());
        infoLabel.setForeground(grayColor);
        infoLabel.setBackground(table.getBackground());
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalAlignment = SWT.RIGHT;
        gd.grabExcessHorizontalSpace = false;
        infoLabel.setLayoutData(gd);
        updateInfoLabel();
        return infoLabel;
    }

    public void resetProviders() {
        for (QuickAccessProvider provider : providers) {
            provider.reset();
        }
    }
}

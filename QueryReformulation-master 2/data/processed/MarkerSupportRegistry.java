/***/
package org.eclipse.ui.views.markers.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.Policy;
import org.eclipse.ui.internal.views.markers.MarkerSupportInternalUtilities;
import org.eclipse.ui.views.markers.MarkerField;

/**
* The ProblemFilterRegistryReader is the registry reader for declarative
* problem filters. See the org.eclipse.ui.markerSupport extension point.
*
* @since 3.2
*
*/
public class MarkerSupportRegistry implements IExtensionChangeHandler {

    //$NON-NLS-1$
    private static final String DESCRIPTION = "onDescription";

    /**
* The enabled attribute.
*/
    //$NON-NLS-1$
    public static final String ENABLED = "enabled";

    //$NON-NLS-1$
    private static final Object ERROR = "ERROR";

    //$NON-NLS-1$
    private static final Object INFO = "INFO";

    //$NON-NLS-1$
    private static final Object WARNING = "WARNING";

    //$NON-NLS-1$
    private static final String MARKER_ID = "markerId";

    /**
* Filter enablement : A zero/negative integer implies that the limit is
* disabled.
*/
    //$NON-NLS-1$
    public static final String FILTER_LIMIT = "filterLimit";

    /**
* The tag for the marker support extension
*/
    //$NON-NLS-1$
    public static final String MARKER_SUPPORT = "markerSupport";

    //$NON-NLS-1$
    private static final Object ON_ANY = "ON_ANY";

    //$NON-NLS-1$
    private static final Object ON_ANY_IN_SAME_CONTAINER = "ON_ANY_IN_SAME_CONTAINER";

    //$NON-NLS-1$
    private static final Object ON_SELECTED_AND_CHILDREN = "ON_SELECTED_AND_CHILDREN";

    //$NON-NLS-1$
    private static final Object ON_SELECTED_ONLY = "ON_SELECTED_ONLY";

    //$NON-NLS-1$
    private static final Object PROBLEM_FILTER = "problemFilter";

    //$NON-NLS-1$
    private static final String SCOPE = "scope";

    //$NON-NLS-1$
    private static final String SELECTED_TYPE = "selectedType";

    //$NON-NLS-1$
    private static final String SEVERITY = "severity";

    /**
* The key for marker type references.
*/
    //$NON-NLS-1$
    public static final String MARKER_TYPE_REFERENCE = "markerTypeReference";

    //$NON-NLS-1$
    private static final String MARKER_CATEGORY = "markerTypeCategory";

    /**
* The markerAttributeMapping element.
*/
    //$NON-NLS-1$
    public static final String ATTRIBUTE_MAPPING = "markerAttributeMapping";

    /**
* The tag for marker grouping.
*/
    //$NON-NLS-1$
    public static final String MARKER_GROUPING = "markerGrouping";

    /**
* The value attribute.
*/
    //$NON-NLS-1$
    public static final String VALUE = "value";

    /**
* The label attribute
*/
    //$NON-NLS-1$
    public static final String LABEL = "label";

    /**
* The attribute grouping element name.
*/
    //$NON-NLS-1$
    public static final String MARKER_ATTRIBUTE_GROUPING = "markerAttributeGrouping";

    /**
* The constant for grouping entries.
*/
    //$NON-NLS-1$
    public static final String MARKER_GROUPING_ENTRY = "markerGroupingEntry";

    //$NON-NLS-1$
    private static final Object SEVERITY_ID = "org.eclipse.ui.ide.severity";

    /**
* The tag for content generators.
*/
    //$NON-NLS-1$
    static final String MARKER_CONTENT_GENERATOR = "markerContentGenerator";

    /**
* The tag for content generator.
*/
    //$NON-NLS-1$
    private static final String MARKER_CONTENT_GENERATOR_EXTENSION = "markerContentGeneratorExtension";

    //$NON-NLS-1$
    private static final String MARKER_FIELD = "markerField";

    //$NON-NLS-1$
    private static final String ATTRIBUTE_CLASS = "class";

    /**
* The bookmarks generator.
*/
    //$NON-NLS-1$
    public static final String BOOKMARKS_GENERATOR = "org.eclipse.ui.ide.bookmarksGenerator";

    /**
* The tasks generator.
*/
    //$NON-NLS-1$
    public static final String TASKS_GENERATOR = "org.eclipse.ui.ide.tasksGenerator";

    /**
* The problems generator.
*/
    //$NON-NLS-1$
    public static final String PROBLEMS_GENERATOR = "org.eclipse.ui.ide.problemsGenerator";

    /**
* The all markers generator.
*/
    //$NON-NLS-1$
    public static final String ALL_MARKERS_GENERATOR = "org.eclipse.ui.ide.allMarkersGenerator";

    /**
* The id for the new markers view.
*/
    //$NON-NLS-1$;
    public static final String MARKERS_ID = "org.eclipse.ui.ide.MarkersView";

    //$NON-NLS-1$
    private static final String ATTRIBUTE_GENERATOR_ID = "generatorId";

    private static MarkerSupportRegistry singleton;

    /**
* Get the instance of the registry.
*
* @return MarkerSupportRegistry
*/
    public static synchronized MarkerSupportRegistry getInstance() {
        if (singleton == null) {
            singleton = new MarkerSupportRegistry();
        }
        return singleton;
    }

    private Map<String, ProblemFilter> registeredFilters = new HashMap();

    private Map<String, MarkerGroup> markerGroups = new HashMap();

    private Map<String, String> categories = new HashMap();

    private Map<String, TableComparator> hierarchyOrders = new HashMap();

    private MarkerType rootType;

    private Map<String, ContentGeneratorDescriptor> generators = new HashMap();

    private Map<String, MarkerField> fields = new HashMap();

    /**
* Create a new instance of the receiver and read the registry.
*/
    private  MarkerSupportRegistry() {
        IExtensionTracker tracker = PlatformUI.getWorkbench().getExtensionTracker();
        IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(IDEWorkbenchPlugin.IDE_WORKBENCH, MARKER_SUPPORT);
        if (point == null) {
            return;
        }
        IExtension[] extensions = point.getExtensions();
        // initial population
        Map<String, Collection<MarkerGroupingEntry>> groupingEntries = new HashMap();
        Map<String, Collection<IConfigurationElement>> generatorExtensions = new HashMap();
        Map<String, MarkerGroupingEntry> entryIDsToEntries = new HashMap();
        Set<AttributeMarkerGrouping> attributeMappings = new HashSet();
        for (int i = 0; i < extensions.length; i++) {
            IExtension extension = extensions[i];
            processExtension(tracker, extension, groupingEntries, entryIDsToEntries, attributeMappings, generatorExtensions);
        }
        postProcessExtensions(groupingEntries, entryIDsToEntries, attributeMappings, generatorExtensions);
        tracker.registerHandler(this, ExtensionTracker.createExtensionPointFilter(point));
    }

    /**
* Process the extension and register the result with the tracker. Fill the
* map of groupingEntries and attribueMappings processed for post
* processing.
*
* @param tracker
* @param extension
* @param groupIDsToEntries
*            Mapping of group names to the markerGroupingEntries registered
*            for them
* @param entryIDsToEntries
*            Mapping of entry ids to entries
* @param attributeMappings
*            the markerAttributeGroupings found
* @param generatorExtensions
*            the markerContentGenerator extensions keyed on group id
* @see #postProcessExtensions(Map, Map, Collection, Map)
*/
    private void processExtension(IExtensionTracker tracker, IExtension extension, Map<String, Collection<MarkerGroupingEntry>> groupIDsToEntries, Map<String, MarkerGroupingEntry> entryIDsToEntries, Collection<AttributeMarkerGrouping> attributeMappings, Map<String, Collection<IConfigurationElement>> generatorExtensions) {
        IConfigurationElement[] elements = extension.getConfigurationElements();
        for (int j = 0; j < elements.length; j++) {
            IConfigurationElement element = elements[j];
            if (element.getName().equals(PROBLEM_FILTER)) {
                ProblemFilter filter = newFilter(element);
                registeredFilters.put(filter.getId(), filter);
                tracker.registerObject(extension, filter, IExtensionTracker.REF_STRONG);
                continue;
            }
            if (element.getName().equals(MARKER_GROUPING)) {
                MarkerGroup group = MarkerGroup.createMarkerGroup(element);
                markerGroups.put(group.getId(), group);
                tracker.registerObject(extension, group, IExtensionTracker.REF_STRONG);
                continue;
            }
            if (element.getName().equals(MARKER_GROUPING_ENTRY)) {
                MarkerGroupingEntry entry = new MarkerGroupingEntry(element);
                String groupName = element.getAttribute(MARKER_GROUPING);
                Collection<MarkerGroupingEntry> entries;
                if (groupIDsToEntries.containsKey(groupName)) {
                    entries = groupIDsToEntries.get(groupName);
                } else {
                    entries = new HashSet();
                }
                entries.add(entry);
                groupIDsToEntries.put(groupName, entries);
                entryIDsToEntries.put(entry.getId(), entry);
                tracker.registerObject(extension, entry, IExtensionTracker.REF_STRONG);
                continue;
            }
            if (element.getName().equals(MARKER_ATTRIBUTE_GROUPING)) {
                AttributeMarkerGrouping grouping = new AttributeMarkerGrouping(element);
                attributeMappings.add(grouping);
                tracker.registerObject(extension, grouping, IExtensionTracker.REF_STRONG);
                continue;
            }
            if (element.getName().equals(MARKER_CATEGORY)) {
                String[] markerTypes = getMarkerTypes(element);
                String categoryName = element.getAttribute(MarkerSupportInternalUtilities.ATTRIBUTE_NAME);
                for (int i = 0; i < markerTypes.length; i++) {
                    categories.put(markerTypes[i], categoryName);
                }
                tracker.registerObject(extension, categoryName, IExtensionTracker.REF_STRONG);
                continue;
            }
            if (element.getName().equals(MARKER_CONTENT_GENERATOR_EXTENSION)) {
                String generatorName = element.getAttribute(ATTRIBUTE_GENERATOR_ID);
                Collection<IConfigurationElement> extensionCollection;
                if (generatorExtensions.containsKey(generatorName)) {
                    extensionCollection = generatorExtensions.get(generatorName);
                } else {
                    extensionCollection = new ArrayList();
                }
                extensionCollection.add(element);
                generatorExtensions.put(generatorName, extensionCollection);
                tracker.registerObject(extension, element, IExtensionTracker.REF_STRONG);
                continue;
            }
            if (element.getName().equals(MARKER_CONTENT_GENERATOR)) {
                ContentGeneratorDescriptor generatorDesc = new ContentGeneratorDescriptor(element);
                generators.put(generatorDesc.getId(), generatorDesc);
                tracker.registerObject(extension, generatorDesc, IExtensionTracker.REF_STRONG);
                continue;
            }
            if (element.getName().equals(MARKER_FIELD)) {
                processMarkerField(tracker, extension, element);
                continue;
            }
        }
    }

    /**
* Create a table of MarkerFields
*
* @param tracker
* @param extension
* @param element
*/
    private void processMarkerField(IExtensionTracker tracker, IExtension extension, IConfigurationElement element) {
        MarkerField field = null;
        try {
            field = (MarkerField) IDEWorkbenchPlugin.createExtension(element, ATTRIBUTE_CLASS);
            field.setConfigurationElement(element);
        } catch (CoreException e) {
            Policy.handle(e);
        }
        if (field != null) {
            fields.put(element.getAttribute(MarkerSupportInternalUtilities.ATTRIBUTE_ID), field);
        }
        tracker.registerObject(extension, field, IExtensionTracker.REF_STRONG);
    }

    /**
* Process the cross references after all of the extensions have been read.
*
* @param groupIDsToEntries
*            Mapping of group names to the markerGroupingEntries registered
*            for them
* @param entryIDsToEntries
*            Mapping of entry names to the mappings for them
* @param attributeMappings
*            the markerAttributeGroupings found
* @param generatorExtensions
*            map of generator id to generator descriptors
*/
    private void postProcessExtensions(Map<String, Collection<MarkerGroupingEntry>> groupIDsToEntries, Map<String, MarkerGroupingEntry> entryIDsToEntries, Collection<AttributeMarkerGrouping> attributeMappings, Map<String, Collection<IConfigurationElement>> generatorExtensions) {
        processGroupingEntries(groupIDsToEntries);
        processAttributeMappings(entryIDsToEntries, attributeMappings);
        postProcessContentGenerators(generatorExtensions);
    }

    /**
* Set up the fields and filters
*
* @param generatorExtensions
*            the extensions to the generators,
*/
    private void postProcessContentGenerators(Map<String, Collection<IConfigurationElement>> generatorExtensions) {
        Iterator<ContentGeneratorDescriptor> generatorIterator = generators.values().iterator();
        while (generatorIterator.hasNext()) {
            ContentGeneratorDescriptor generatorDesc = generatorIterator.next();
            generatorDesc.initializeFromConfigurationElement(this);
            if (generatorExtensions.containsKey(generatorDesc.getId())) {
                generatorDesc.addExtensions(generatorExtensions.get(generatorDesc.getId()));
            }
        }
    }

    /**
* Process the grouping entries into thier required grouping entries.
*
* @param groupingEntries
*/
    private void processGroupingEntries(Map<String, Collection<MarkerGroupingEntry>> groupingEntries) {
        for (Entry<String, Collection<MarkerGroupingEntry>> entry : groupingEntries.entrySet()) {
            String nextGroupId = entry.getKey();
            if (markerGroups.containsKey(nextGroupId)) {
                MarkerGroup group = markerGroups.get(nextGroupId);
                for (MarkerGroupingEntry markerGroupingEntry : entry.getValue()) {
                    markerGroupingEntry.setGroup(group);
                }
            } else {
                for (MarkerGroupingEntry markerGroupingEntry : entry.getValue()) {
                    IDEWorkbenchPlugin.log(//$NON-NLS-1$
                    NLS.bind(//$NON-NLS-1$
                    "markerGroupingEntry {0} defines invalid group {1}", new String[] { markerGroupingEntry.getId(), nextGroupId }));
                }
            }
        }
    }

    /**
* Process the attribute mappings into thier required grouping entries.
*
* @param entryIDsToEntries
* @param attributeMappings
*/
    private void processAttributeMappings(Map<String, MarkerGroupingEntry> entryIDsToEntries, Collection<AttributeMarkerGrouping> attributeMappings) {
        Iterator<AttributeMarkerGrouping> mappingsIterator = attributeMappings.iterator();
        while (mappingsIterator.hasNext()) {
            AttributeMarkerGrouping attributeGrouping = mappingsIterator.next();
            String defaultEntryId = attributeGrouping.getDefaultGroupingEntry();
            if (defaultEntryId != null) {
                if (entryIDsToEntries.containsKey(defaultEntryId)) {
                    MarkerGroupingEntry entry = entryIDsToEntries.get(defaultEntryId);
                    entry.setAsDefault(attributeGrouping.getMarkerType());
                } else {
                    IDEWorkbenchPlugin.log(NLS.bind(//$NON-NLS-1$
                    "Reference to invalid markerGroupingEntry {0}", defaultEntryId));
                }
            }
            IConfigurationElement[] mappings = attributeGrouping.getElement().getChildren(ATTRIBUTE_MAPPING);
            for (int i = 0; i < mappings.length; i++) {
                String entryId = mappings[i].getAttribute(MARKER_GROUPING_ENTRY);
                if (entryIDsToEntries.containsKey(entryId)) {
                    MarkerGroupingEntry entry = entryIDsToEntries.get(entryId);
                    entry.getMarkerGroup().mapAttribute(attributeGrouping, entry, mappings[i].getAttribute(VALUE));
                } else {
                    IDEWorkbenchPlugin.log(NLS.bind(//$NON-NLS-1$
                    "Reference to invaild markerGroupingEntry {0}", defaultEntryId));
                }
            }
        }
    }

    /**
* Get the markerTypes defined in element.
*
* @param element
* @return String[]
*/
    private String[] getMarkerTypes(IConfigurationElement element) {
        IConfigurationElement[] types = element.getChildren(MARKER_TYPE_REFERENCE);
        String[] ids = new String[types.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = types[i].getAttribute(MarkerSupportInternalUtilities.ATTRIBUTE_ID);
        }
        return ids;
    }

    @Override
    public void addExtension(IExtensionTracker tracker, IExtension extension) {
        Map<String, Collection<MarkerGroupingEntry>> groupIDsToEntries = new HashMap();
        Map<String, MarkerGroupingEntry> entryIDsToEntries = new HashMap();
        Map<String, Collection<IConfigurationElement>> generatorExtensions = new HashMap();
        Set<AttributeMarkerGrouping> attributeMappings = new HashSet();
        processExtension(tracker, extension, groupIDsToEntries, entryIDsToEntries, attributeMappings, generatorExtensions);
        postProcessExtensions(groupIDsToEntries, entryIDsToEntries, attributeMappings, generatorExtensions);
    }

    /**
* Get the collection of currently registered filters.
*
* @return Collection of ProblemFilter
*/
    public Collection<ProblemFilter> getRegisteredFilters() {
        Collection<ProblemFilter> filteredFilters = new ArrayList();
        Iterator<ProblemFilter> registeredIterator = registeredFilters.values().iterator();
        while (registeredIterator.hasNext()) {
            ProblemFilter next = registeredIterator.next();
            if (next.isFilteredOutByActivity()) {
                continue;
            }
            filteredFilters.add(next);
        }
        return filteredFilters;
    }

    /**
* Get the constant for scope from element. Return -1 if there is no value.
*
* @param element
* @return int one of MarkerView#ON_ANY MarkerView#ON_SELECTED_ONLY
*         MarkerView#ON_SELECTED_AND_CHILDREN
*         MarkerView#ON_ANY_IN_SAME_CONTAINER
*/
    private int getScopeValue(IConfigurationElement element) {
        String scope = element.getAttribute(SCOPE);
        if (scope == null) {
            return -1;
        }
        if (scope.equals(ON_ANY)) {
            return MarkerFilter.ON_ANY;
        }
        if (scope.equals(ON_SELECTED_ONLY)) {
            return MarkerFilter.ON_SELECTED_ONLY;
        }
        if (scope.equals(ON_SELECTED_AND_CHILDREN)) {
            return MarkerFilter.ON_SELECTED_AND_CHILDREN;
        }
        if (scope.equals(ON_ANY_IN_SAME_CONTAINER)) {
            return MarkerFilter.ON_ANY_IN_SAME_CONTAINER;
        }
        return -1;
    }

    /**
* Get the constant for scope from element. Return -1 if there is no value.
*
* @param element
* @return int one of MarkerView#ON_ANY MarkerView#ON_SELECTED_ONLY
*         MarkerView#ON_SELECTED_AND_CHILDREN
*         MarkerView#ON_ANY_IN_SAME_CONTAINER
*/
    private int getSeverityValue(IConfigurationElement element) {
        String severity = element.getAttribute(SEVERITY);
        if (severity == null) {
            return -1;
        }
        if (severity.equals(INFO)) {
            return ProblemFilter.SEVERITY_INFO;
        }
        if (severity.equals(WARNING)) {
            return ProblemFilter.SEVERITY_WARNING;
        }
        if (severity.equals(ERROR)) {
            return ProblemFilter.SEVERITY_ERROR;
        }
        return -1;
    }

    /**
* Read the problem filters in the receiver.
*
* @param element
*            the filter element
* @return ProblemFilter
*/
    private ProblemFilter newFilter(IConfigurationElement element) {
        ProblemFilter filter = new ProblemFilter(element.getAttribute(MarkerSupportInternalUtilities.ATTRIBUTE_NAME));
        filter.createContributionFrom(element);
        String enabledValue = element.getAttribute(ENABLED);
        filter.setEnabled(enabledValue == null || Boolean.valueOf(enabledValue).booleanValue());
        int scopeValue = getScopeValue(element);
        if (scopeValue >= 0) {
            filter.setOnResource(scopeValue);
        }
        String description = element.getAttribute(DESCRIPTION);
        if (description != null) {
            boolean contains = true;
            if (description.charAt(0) == '!') {
                // does not contain flag
                description = description.substring(1, description.length());
                contains = false;
            }
            filter.setContains(contains);
            filter.setDescription(description);
        }
        int severityValue = getSeverityValue(element);
        if (severityValue > 0) {
            filter.setSelectBySeverity(true);
            filter.setSeverity(severityValue);
        } else {
            filter.setSelectBySeverity(false);
        }
        List<MarkerType> selectedTypes = new ArrayList();
        IConfigurationElement[] types = element.getChildren(SELECTED_TYPE);
        for (int j = 0; j < types.length; j++) {
            String markerId = types[j].getAttribute(MARKER_ID);
            if (markerId != null) {
                MarkerType type = filter.getMarkerType(markerId);
                if (type == null) {
                    IStatus status = new Status(IStatus.WARNING, IDEWorkbenchPlugin.IDE_WORKBENCH, IStatus.WARNING, NLS.bind(MarkerMessages.ProblemFilterRegistry_nullType, new Object[] { markerId, filter.getName() }), null);
                    IDEWorkbenchPlugin.getDefault().getLog().log(status);
                } else {
                    selectedTypes.add(type);
                }
            }
        }
        if (selectedTypes.size() > 0) {
            // specified
            filter.setSelectedTypes(selectedTypes);
        }
        return filter;
    }

    @Override
    public void removeExtension(IExtension extension, Object[] objects) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof ProblemFilter) {
                registeredFilters.remove(objects[i]);
                continue;
            }
            if (objects[i] instanceof MarkerGroup) {
                markerGroups.remove(((MarkerGroup) objects[i]).getId());
                continue;
            }
            if (objects[i] instanceof MarkerGroupingEntry) {
                MarkerGroupingEntry entry = (MarkerGroupingEntry) objects[i];
                entry.getMarkerGroup().remove(entry);
                continue;
            }
            if (objects[i] instanceof AttributeMarkerGrouping) {
                AttributeMarkerGrouping entry = (AttributeMarkerGrouping) objects[i];
                entry.unmap();
                continue;
            }
            if (objects[i] instanceof String) {
                removeValues((String) objects[i], categories);
                continue;
            }
            if (objects[i] instanceof MarkerField) {
                fields.remove(MarkerSupportInternalUtilities.getId((MarkerField) objects[i]));
                continue;
            }
            if (objects[i] instanceof ContentGeneratorDescriptor) {
                generators.remove(((ContentGeneratorDescriptor) objects[i]).getId());
                continue;
            }
            if (objects[i] instanceof IConfigurationElement) {
                IConfigurationElement element = (IConfigurationElement) objects[i];
                ContentGeneratorDescriptor generatorDesc = generators.get(element.getAttribute(ATTRIBUTE_GENERATOR_ID));
                generatorDesc.removeExtension(element);
                continue;
            }
        }
    }

    /**
* Remove the value from all of the collection sets in cache. If the
* collection is empty remove the key as well.
*
* @param value
* @param cache
*/
    private void removeValues(String value, Map<String, String> cache) {
        Collection<String> keysToRemove = new ArrayList();
        for (Entry<String, String> entry : cache.entrySet()) {
            if (entry.getValue().equals(value)) {
                keysToRemove.add(entry.getKey());
            }
        }
        for (String toRemove : keysToRemove) {
            cache.remove(toRemove);
        }
    }

    /**
* Get the category associated with marker. Return <code>null</code> if
* there are none.
*
* @param marker
* @return String or <code>null</code>
*/
    public String getCategory(IMarker marker) {
        try {
            return getCategory(marker.getType());
        } catch (CoreException e) {
            Policy.handle(e);
        }
        return null;
    }

    /**
* Get the category associated with markerType. Return <code>null</code>
* if there are none.
*
* @param markerType
* @return String or <code>null</code>
*/
    public String getCategory(String markerType) {
        if (categories.containsKey(markerType)) {
            return categories.get(markerType);
        }
        return null;
    }

    /**
* Return the TableSorter that corresponds to type.
*
* @param type
* @return TableSorter
*/
    public TableComparator getSorterFor(String type) {
        if (hierarchyOrders.containsKey(type)) {
            return hierarchyOrders.get(type);
        }
        TableComparator sorter = findSorterInChildren(type, getRootType());
        if (sorter == null) {
            return new TableComparator(new IField[0], new int[0], new int[0]);
        }
        return sorter;
    }

    /**
* Return the list of root marker types.
*
* @return List of MarkerType.
*/
    private MarkerType getRootType() {
        if (rootType == null) {
            rootType = (MarkerTypesModel.getInstance()).getType(IMarker.PROBLEM);
        }
        return rootType;
    }

    /**
* Find the best match sorter for typeName in the children. If it cannot be
* found then return <code>null</code>.
*
* @param typeName
* @param type
* @return TableSorter or <code>null</code>.
*/
    private TableComparator findSorterInChildren(String typeName, MarkerType type) {
        MarkerType[] types = type.getAllSubTypes();
        TableComparator defaultSorter = null;
        if (hierarchyOrders.containsKey(type.getId())) {
            defaultSorter = hierarchyOrders.get(type.getId());
        }
        for (int i = 0; i < types.length; i++) {
            MarkerType[] subtypes = types[i].getAllSubTypes();
            for (int j = 0; j < subtypes.length; j++) {
                TableComparator sorter = findSorterInChildren(typeName, subtypes[j]);
                if (sorter != null) {
                    return sorter;
                }
            }
        }
        return defaultSorter;
    }

    /**
* Return the FieldMarkerGroups in the receiver.
*
* @return Collection of {@link MarkerGroup}
*/
    public Collection<MarkerGroup> getMarkerGroups() {
        return markerGroups.values();
    }

    /**
* Return the default groupfield.
*
* @return IField
*/
    IField getDefaultGroupField() {
        return markerGroups.get(SEVERITY_ID).getField();
    }

    /**
* Get the generator descriptor for id
*
* @param id
* @return ContentGeneratorDescriptor or <code>null</code>.
*/
    public ContentGeneratorDescriptor getContentGenDescriptor(String id) {
        if (id != null && generators.containsKey(id)) {
            return generators.get(id);
        }
        return null;
    }

    /**
* Return the default content generator descriptor.
*
* @return ContentGeneratorDescriptor
*/
    public ContentGeneratorDescriptor getDefaultContentGenDescriptor() {
        return generators.get(PROBLEMS_GENERATOR);
    }

    /**
* Get the markerGroup associated with categoryName
*
* @param categoryName
* @return FieldMarkerGroup or <code>null</code>
*/
    public MarkerGroup getMarkerGroup(String categoryName) {
        if (markerGroups.containsKey(categoryName)) {
            return markerGroups.get(categoryName);
        }
        return null;
    }

    /**
* Return the field that maps to id.
*
* @param id
* @return {@link MarkerField} or <code>null</code>
*/
    public MarkerField getField(String id) {
        if (fields.containsKey(id)) {
            return fields.get(id);
        }
        return null;
    }
}

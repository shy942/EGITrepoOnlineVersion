/***/
package org.eclipse.core.databinding;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.util.Policy;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.Activator;
import org.eclipse.core.internal.databinding.BindingMessages;
import org.eclipse.core.internal.databinding.ClassLookupSupport;
import org.eclipse.core.internal.databinding.Pair;
import org.eclipse.core.internal.databinding.conversion.CharacterToStringConverter;
import org.eclipse.core.internal.databinding.conversion.IdentityConverter;
import org.eclipse.core.internal.databinding.conversion.IntegerToStringConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToBigDecimalConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToBigIntegerConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToByteConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToDoubleConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToFloatConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToIntegerConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToLongConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToShortConverter;
import org.eclipse.core.internal.databinding.conversion.ObjectToStringConverter;
import org.eclipse.core.internal.databinding.conversion.StringToByteConverter;
import org.eclipse.core.internal.databinding.conversion.StringToCharacterConverter;
import org.eclipse.core.internal.databinding.conversion.StringToShortConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import com.ibm.icu.text.NumberFormat;

/* package */
class UpdateStrategy {

    //$NON-NLS-1$
    private static final String BOOLEAN_CLASS = "boolean.class";

    //$NON-NLS-1$
    private static final String SHORT_CLASS = "short.class";

    //$NON-NLS-1$
    private static final String BYTE_CLASS = "byte.class";

    //$NON-NLS-1$
    private static final String DOUBLE_CLASS = "double.class";

    //$NON-NLS-1$
    private static final String FLOAT_CLASS = "float.class";

    //$NON-NLS-1$
    private static final String INTEGER_CLASS = "int.class";

    //$NON-NLS-1$
    private static final String LONG_CLASS = "long.class";

    //$NON-NLS-1$
    private static final String CHARACTER_CLASS = "char.class";

    private static Map converterMap;

    private static Class autoboxed(Class clazz) {
        if (clazz == Float.TYPE)
            return Float.class;
        else if (clazz == Double.TYPE)
            return Double.class;
        else if (clazz == Short.TYPE)
            return Short.class;
        else if (clazz == Integer.TYPE)
            return Integer.class;
        else if (clazz == Long.TYPE)
            return Long.class;
        else if (clazz == Byte.TYPE)
            return Byte.class;
        else if (clazz == Boolean.TYPE)
            return Boolean.class;
        else if (clazz == Character.TYPE)
            return Character.class;
        return clazz;
    }

    protected IConverter converter;

    protected final void checkAssignable(Object toType, Object fromType, String errorString) {
        Boolean assignableFromModelToModelConverter = isAssignableFromTo(fromType, toType);
        if (assignableFromModelToModelConverter != null && !assignableFromModelToModelConverter.booleanValue()) {
            throw new BindingException(errorString + " Expected: " + fromType + ", actual: " + //$NON-NLS-1$//$NON-NLS-2$
            toType);
        }
    }

    /**
* Tries to create a converter that can convert from values of type
* fromType. Returns <code>null</code> if no converter could be created.
* Either toType or modelDescription can be <code>null</code>, but not
* both.
*
* @param fromType
* @param toType
* @return an IConverter, or <code>null</code> if unsuccessful
*/
    protected IConverter createConverter(Object fromType, Object toType) {
        if (!(fromType instanceof Class) || !(toType instanceof Class)) {
            return new DefaultConverter(fromType, toType);
        }
        Class toClass = (Class) toType;
        Class originalToClass = toClass;
        if (toClass.isPrimitive()) {
            toClass = autoboxed(toClass);
        }
        Class fromClass = (Class) fromType;
        Class originalFromClass = fromClass;
        if (fromClass.isPrimitive()) {
            fromClass = autoboxed(fromClass);
        }
        if (!((Class) toType).isPrimitive() && toClass.isAssignableFrom(fromClass)) {
            return new IdentityConverter(originalFromClass, originalToClass);
        }
        if (((Class) fromType).isPrimitive() && ((Class) toType).isPrimitive() && fromType.equals(toType)) {
            return new IdentityConverter(originalFromClass, originalToClass);
        }
        Map converterMap = getConverterMap();
        Class[] supertypeHierarchyFlattened = ClassLookupSupport.getTypeHierarchyFlattened(fromClass);
        for (int i = 0; i < supertypeHierarchyFlattened.length; i++) {
            Class currentFromClass = supertypeHierarchyFlattened[i];
            if (currentFromClass == toType) {
                // converting to toType is just a widening
                return new IdentityConverter(fromClass, toClass);
            }
            Pair key = new Pair(getKeyForClass(fromType, currentFromClass), getKeyForClass(toType, toClass));
            Object converterOrClassname = converterMap.get(key);
            if (converterOrClassname instanceof IConverter) {
                return (IConverter) converterOrClassname;
            } else if (converterOrClassname instanceof String) {
                String classname = (String) converterOrClassname;
                Class converterClass;
                try {
                    converterClass = Class.forName(classname);
                    IConverter result = (IConverter) converterClass.newInstance();
                    converterMap.put(key, result);
                    return result;
                } catch (Exception e) {
                    Policy.getLog().log(new Status(IStatus.ERROR, Policy.JFACE_DATABINDING, 0, "Error while instantiating default converter", e));
                }
            }
        }
        // runtime.
        if (fromClass.isAssignableFrom(toClass)) {
            return new IdentityConverter(originalFromClass, originalToClass);
        }
        return new DefaultConverter(fromType, toType);
    }

    private static synchronized Map getConverterMap() {
        // using string-based lookup avoids loading of too many classes
        if (converterMap == null) {
            // NumberFormat to be shared across converters for the formatting of
            // integer values
            NumberFormat integerFormat = NumberFormat.getIntegerInstance();
            // NumberFormat to be shared across converters for formatting non
            // integer values
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            converterMap = new HashMap();
            // Standard and Boxed Types
            converterMap.put(new Pair("java.util.Date", "java.lang.String"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "org.eclipse.core.internal.databinding.conversion.DateToStringConverter");
            converterMap.put(new Pair("java.lang.String", "java.lang.Boolean"), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            "org.eclipse.core.internal.databinding.conversion.StringToBooleanConverter");
            converterMap.put(new Pair("java.lang.String", "java.lang.Byte"), //$NON-NLS-1$//$NON-NLS-2$
            StringToByteConverter.toByte(integerFormat, false));
            converterMap.put(new Pair("java.lang.String", "java.util.Date"), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            "org.eclipse.core.internal.databinding.conversion.StringToDateConverter");
            converterMap.put(new Pair("java.lang.String", "java.lang.Short"), //$NON-NLS-1$//$NON-NLS-2$
            StringToShortConverter.toShort(integerFormat, false));
            converterMap.put(new Pair("java.lang.String", "java.lang.Character"), //$NON-NLS-1$//$NON-NLS-2$
            StringToCharacterConverter.toCharacter(false));
            converterMap.put(new Pair("java.lang.String", "java.lang.Integer"), //$NON-NLS-1$//$NON-NLS-2$
            StringToNumberConverter.toInteger(integerFormat, false));
            converterMap.put(new Pair("java.lang.String", "java.lang.Double"), //$NON-NLS-1$//$NON-NLS-2$
            StringToNumberConverter.toDouble(numberFormat, false));
            converterMap.put(new Pair("java.lang.String", "java.lang.Long"), //$NON-NLS-1$//$NON-NLS-2$
            StringToNumberConverter.toLong(integerFormat, false));
            converterMap.put(new Pair("java.lang.String", "java.lang.Float"), //$NON-NLS-1$//$NON-NLS-2$
            StringToNumberConverter.toFloat(numberFormat, false));
            converterMap.put(new Pair("java.lang.String", "java.math.BigInteger"), //$NON-NLS-1$//$NON-NLS-2$
            StringToNumberConverter.toBigInteger(integerFormat));
            converterMap.put(new Pair("java.lang.String", "java.math.BigDecimal"), //$NON-NLS-1$//$NON-NLS-2$
            StringToNumberConverter.toBigDecimal(numberFormat));
            converterMap.put(new Pair("java.lang.Integer", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            NumberToStringConverter.fromInteger(integerFormat, false));
            converterMap.put(new Pair("java.lang.Long", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            NumberToStringConverter.fromLong(integerFormat, false));
            converterMap.put(new Pair("java.lang.Double", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            NumberToStringConverter.fromDouble(numberFormat, false));
            converterMap.put(new Pair("java.lang.Float", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            NumberToStringConverter.fromFloat(numberFormat, false));
            converterMap.put(new Pair("java.math.BigInteger", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            NumberToStringConverter.fromBigInteger(integerFormat));
            converterMap.put(new Pair("java.math.BigDecimal", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            NumberToStringConverter.fromBigDecimal(numberFormat));
            converterMap.put(new Pair("java.lang.Byte", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            IntegerToStringConverter.fromByte(integerFormat, false));
            converterMap.put(new Pair("java.lang.Short", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            IntegerToStringConverter.fromShort(integerFormat, false));
            converterMap.put(new Pair("java.lang.Character", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$
            CharacterToStringConverter.fromCharacter(false));
            converterMap.put(new Pair("java.lang.Object", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            "org.eclipse.core.internal.databinding.conversion.ObjectToStringConverter");
            // Integer.class
            converterMap.put(new Pair("java.lang.String", INTEGER_CLASS), //$NON-NLS-1$
            StringToNumberConverter.toInteger(integerFormat, true));
            converterMap.put(new Pair(INTEGER_CLASS, "java.lang.Integer"), //$NON-NLS-1$
            new IdentityConverter(Integer.class, Integer.class));
            converterMap.put(new Pair(INTEGER_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Integer.class, Object.class));
            converterMap.put(new Pair(INTEGER_CLASS, "java.lang.String"), //$NON-NLS-1$
            NumberToStringConverter.fromInteger(integerFormat, true));
            // Byte.class
            converterMap.put(new Pair("java.lang.String", BYTE_CLASS), //$NON-NLS-1$
            StringToByteConverter.toByte(integerFormat, true));
            converterMap.put(new Pair(BYTE_CLASS, "java.lang.Byte"), //$NON-NLS-1$
            new IdentityConverter(Byte.class, Byte.class));
            converterMap.put(new Pair(BYTE_CLASS, "java.lang.String"), //$NON-NLS-1$
            IntegerToStringConverter.fromByte(integerFormat, true));
            converterMap.put(new Pair(BYTE_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Byte.class, Object.class));
            // Double.class
            converterMap.put(new Pair("java.lang.String", DOUBLE_CLASS), //$NON-NLS-1$
            StringToNumberConverter.toDouble(numberFormat, true));
            converterMap.put(new Pair(DOUBLE_CLASS, "java.lang.String"), //$NON-NLS-1$
            NumberToStringConverter.fromDouble(numberFormat, true));
            converterMap.put(new Pair(DOUBLE_CLASS, "java.lang.Double"), //$NON-NLS-1$
            new IdentityConverter(Double.class, Double.class));
            converterMap.put(new Pair(DOUBLE_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Double.class, Object.class));
            // Boolean.class
            converterMap.put(new Pair("java.lang.String", BOOLEAN_CLASS), //$NON-NLS-1$ //$NON-NLS-2$
            "org.eclipse.core.internal.databinding.conversion.StringToBooleanPrimitiveConverter");
            converterMap.put(new Pair(BOOLEAN_CLASS, "java.lang.Boolean"), //$NON-NLS-1$
            new IdentityConverter(Boolean.class, Boolean.class));
            converterMap.put(new Pair(BOOLEAN_CLASS, "java.lang.String"), //$NON-NLS-1$
            new ObjectToStringConverter(Boolean.class));
            converterMap.put(new Pair(BOOLEAN_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Boolean.class, Object.class));
            // Float.class
            converterMap.put(new Pair("java.lang.String", FLOAT_CLASS), //$NON-NLS-1$
            StringToNumberConverter.toFloat(numberFormat, true));
            converterMap.put(new Pair(FLOAT_CLASS, "java.lang.String"), //$NON-NLS-1$
            NumberToStringConverter.fromFloat(numberFormat, true));
            converterMap.put(new Pair(FLOAT_CLASS, "java.lang.Float"), //$NON-NLS-1$
            new IdentityConverter(Float.class, Float.class));
            converterMap.put(new Pair(FLOAT_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Float.class, Object.class));
            // Short.class
            converterMap.put(new Pair("java.lang.String", SHORT_CLASS), //$NON-NLS-1$
            StringToShortConverter.toShort(integerFormat, true));
            converterMap.put(new Pair(SHORT_CLASS, "java.lang.Short"), //$NON-NLS-1$
            new IdentityConverter(Short.class, Short.class));
            converterMap.put(new Pair(SHORT_CLASS, "java.lang.String"), //$NON-NLS-1$
            IntegerToStringConverter.fromShort(integerFormat, true));
            converterMap.put(new Pair(SHORT_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Short.class, Object.class));
            // Long.class
            converterMap.put(new Pair("java.lang.String", LONG_CLASS), //$NON-NLS-1$
            StringToNumberConverter.toLong(integerFormat, true));
            converterMap.put(new Pair(LONG_CLASS, "java.lang.String"), //$NON-NLS-1$
            NumberToStringConverter.fromLong(integerFormat, true));
            converterMap.put(new Pair(LONG_CLASS, "java.lang.Long"), //$NON-NLS-1$
            new IdentityConverter(Long.class, Long.class));
            converterMap.put(new Pair(LONG_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Long.class, Object.class));
            // Character.class
            converterMap.put(new Pair("java.lang.String", CHARACTER_CLASS), //$NON-NLS-1$
            StringToCharacterConverter.toCharacter(true));
            converterMap.put(new Pair(CHARACTER_CLASS, "java.lang.Character"), //$NON-NLS-1$
            new IdentityConverter(Character.class, Character.class));
            converterMap.put(new Pair(CHARACTER_CLASS, "java.lang.String"), //$NON-NLS-1$
            CharacterToStringConverter.fromCharacter(true));
            converterMap.put(new Pair(CHARACTER_CLASS, "java.lang.Object"), //$NON-NLS-1$
            new IdentityConverter(Character.class, Object.class));
            // Miscellaneous
            converterMap.put(new Pair("org.eclipse.core.runtime.IStatus", "java.lang.String"), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            "org.eclipse.core.internal.databinding.conversion.StatusToStringConverter");
            addNumberToByteConverters(converterMap, integerFormat, integerClasses);
            addNumberToByteConverters(converterMap, numberFormat, floatClasses);
            addNumberToShortConverters(converterMap, integerFormat, integerClasses);
            addNumberToShortConverters(converterMap, numberFormat, floatClasses);
            addNumberToIntegerConverters(converterMap, integerFormat, integerClasses);
            addNumberToIntegerConverters(converterMap, numberFormat, floatClasses);
            addNumberToLongConverters(converterMap, integerFormat, integerClasses);
            addNumberToLongConverters(converterMap, numberFormat, floatClasses);
            addNumberToFloatConverters(converterMap, integerFormat, integerClasses);
            addNumberToFloatConverters(converterMap, numberFormat, floatClasses);
            addNumberToDoubleConverters(converterMap, integerFormat, integerClasses);
            addNumberToDoubleConverters(converterMap, numberFormat, floatClasses);
            addNumberToBigIntegerConverters(converterMap, integerFormat, integerClasses);
            addNumberToBigIntegerConverters(converterMap, numberFormat, floatClasses);
            addNumberToBigDecimalConverters(converterMap, integerFormat, integerClasses);
            addNumberToBigDecimalConverters(converterMap, numberFormat, floatClasses);
        }
        return converterMap;
    }

    private static final Class[] integerClasses = new Class[] { byte.class, Byte.class, short.class, Short.class, int.class, Integer.class, long.class, Long.class, BigInteger.class };

    private static final Class[] floatClasses = new Class[] { float.class, Float.class, double.class, Double.class, BigDecimal.class };

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToByteConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(Byte.class) && !fromType.equals(byte.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, BYTE_CLASS), new NumberToByteConverter(numberFormat, fromType, true));
                map.put(new Pair(fromName, Byte.class.getName()), new NumberToByteConverter(numberFormat, fromType, false));
            }
        }
    }

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToShortConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(Short.class) && !fromType.equals(short.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, SHORT_CLASS), new NumberToShortConverter(numberFormat, fromType, true));
                map.put(new Pair(fromName, Short.class.getName()), new NumberToShortConverter(numberFormat, fromType, false));
            }
        }
    }

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToIntegerConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(Integer.class) && !fromType.equals(int.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, INTEGER_CLASS), new NumberToIntegerConverter(numberFormat, fromType, true));
                map.put(new Pair(fromName, Integer.class.getName()), new NumberToIntegerConverter(numberFormat, fromType, false));
            }
        }
    }

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToLongConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(Long.class) && !fromType.equals(long.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, LONG_CLASS), new NumberToLongConverter(numberFormat, fromType, true));
                map.put(new Pair(fromName, Long.class.getName()), new NumberToLongConverter(numberFormat, fromType, false));
            }
        }
    }

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToFloatConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(Float.class) && !fromType.equals(float.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, FLOAT_CLASS), new NumberToFloatConverter(numberFormat, fromType, true));
                map.put(new Pair(fromName, Float.class.getName()), new NumberToFloatConverter(numberFormat, fromType, false));
            }
        }
    }

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToDoubleConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(Double.class) && !fromType.equals(double.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, DOUBLE_CLASS), new NumberToDoubleConverter(numberFormat, fromType, true));
                map.put(new Pair(fromName, Double.class.getName()), new NumberToDoubleConverter(numberFormat, fromType, false));
            }
        }
    }

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToBigIntegerConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(BigInteger.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, BigInteger.class.getName()), new NumberToBigIntegerConverter(numberFormat, fromType));
            }
        }
    }

    /**
* Registers converters to boxed and unboxed types from a list of from
* classes.
*
* @param map
* @param numberFormat
* @param fromTypes
*/
    private static void addNumberToBigDecimalConverters(Map map, NumberFormat numberFormat, Class[] fromTypes) {
        for (int i = 0; i < fromTypes.length; i++) {
            Class fromType = fromTypes[i];
            if (!fromType.equals(BigDecimal.class)) {
                String fromName = (fromType.isPrimitive()) ? getKeyForClass(fromType, null) : fromType.getName();
                map.put(new Pair(fromName, BigDecimal.class.getName()), new NumberToBigDecimalConverter(numberFormat, fromType));
            }
        }
    }

    private static String getKeyForClass(Object originalValue, Class filteredValue) {
        if (originalValue instanceof Class) {
            Class originalClass = (Class) originalValue;
            if (originalClass.equals(int.class)) {
                return INTEGER_CLASS;
            } else if (originalClass.equals(byte.class)) {
                return BYTE_CLASS;
            } else if (originalClass.equals(boolean.class)) {
                return BOOLEAN_CLASS;
            } else if (originalClass.equals(double.class)) {
                return DOUBLE_CLASS;
            } else if (originalClass.equals(float.class)) {
                return FLOAT_CLASS;
            } else if (originalClass.equals(long.class)) {
                return LONG_CLASS;
            } else if (originalClass.equals(short.class)) {
                return SHORT_CLASS;
            }
        }
        return filteredValue.getName();
    }

    /**
* Returns {@link Boolean#TRUE} if the from type is assignable to the to
* type, or {@link Boolean#FALSE} if it not, or <code>null</code> if
* unknown.
*
* @param fromType
* @param toType
* @return whether fromType is assignable to toType, or <code>null</code>
*         if unknown
*/
    protected Boolean isAssignableFromTo(Object fromType, Object toType) {
        if (fromType instanceof Class && toType instanceof Class) {
            Class toClass = (Class) toType;
            if (toClass.isPrimitive()) {
                toClass = autoboxed(toClass);
            }
            Class fromClass = (Class) fromType;
            if (fromClass.isPrimitive()) {
                fromClass = autoboxed(fromClass);
            }
            return toClass.isAssignableFrom(fromClass) ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }

    /**
* @param ex
*            the exception, that was caught
* @return the validation status
*/
    protected IStatus logErrorWhileSettingValue(Exception ex) {
        IStatus errorStatus = ValidationStatus.error(BindingMessages.getString(BindingMessages.VALUEBINDING_ERROR_WHILE_SETTING_VALUE), ex);
        Policy.getLog().log(errorStatus);
        return errorStatus;
    }

    /**
* Converts the value from the source type to the destination type.
* <p>
* Default implementation will use the setConverter(IConverter), if one
* exists. If no converter exists no conversion occurs.
* </p>
*
* @param value
* @return the converted value
*/
    public Object convert(Object value) {
        if (converter != null) {
            try {
                return converter.convert(value);
            } catch (Exception ex) {
                Policy.getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, ex.getMessage(), ex));
            }
        }
        return value;
    }

    /*
* Default converter implementation, does not perform any conversion.
*/
    static final class DefaultConverter implements IConverter {

        private final Object toType;

        private final Object fromType;

        /**
* @param fromType
* @param toType
*/
         DefaultConverter(Object fromType, Object toType) {
            this.toType = toType;
            this.fromType = fromType;
        }

        @Override
        public Object convert(Object fromObject) {
            return fromObject;
        }

        @Override
        public Object getFromType() {
            return fromType;
        }

        @Override
        public Object getToType() {
            return toType;
        }
    }
}

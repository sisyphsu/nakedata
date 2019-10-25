package com.github.sisyphsu.datatube.convertor.codec;

import com.github.sisyphsu.datatube.convertor.Codec;
import com.github.sisyphsu.datatube.convertor.Converter;
import com.github.sisyphsu.datatube.reflect.XType;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Reference's codec
 *
 * @author sulin
 * @since 2019-05-13 18:39:05
 */
public final class ReferenceCodec extends Codec {

    /**
     * Convert Any Object to Reference
     */
    @Converter(extensible = true)
    public Reference toReference(Object obj, XType type) {
        XType<?> paramType = type.getParameterizedType();
        Object value;
        if (paramType.isPure() && paramType.getRawType().isAssignableFrom(obj.getClass())) {
            value = obj; // compatible
        } else {
            value = convert(obj, paramType); // convert
        }
        // build reference
        Class<?> refClass = type.getRawType();
        if (refClass == SoftReference.class || refClass == Reference.class) {
            return new SoftReference<>(value);
        }
        if (refClass == WeakReference.class) {
            return new WeakReference<>(value);
        }
        throw new IllegalArgumentException("Unsupport Reference: " + refClass);
    }

    /**
     * Convert Reference to Object
     */
    @Converter
    public Object toObject(Reference<?> ref, XType<?> type) {
        Object obj = ref.get();
        if (type.isPure() && type.getRawType().isInstance(obj)) {
            return obj;
        }
        return convert(obj, type);
    }

}
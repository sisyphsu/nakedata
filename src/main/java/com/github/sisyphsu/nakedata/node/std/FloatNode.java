package com.github.sisyphsu.nakedata.node.std;

import com.github.sisyphsu.nakedata.DataType;
import com.github.sisyphsu.nakedata.node.Node;

/**
 * FloatNode represents float and Float.
 *
 * @author sulin
 * @since 2019-05-08 21:00:21
 */
public final class FloatNode extends Node {

    public final static FloatNode NULL = new FloatNode(0);
    public final static FloatNode ZERO = new FloatNode(0);

    private final float value;

    private FloatNode(float value) {
        this.value = value;
    }

    public static FloatNode valueOf(float f) {
        if (f == 0) {
            return ZERO;
        }
        return new FloatNode(f);
    }

    public static FloatNode valueOf(Float f) {
        if (f == null) {
            return NULL;
        }
        return valueOf(f.floatValue());
    }

    public float getValue() {
        return value;
    }

    @Override
    public DataType dataType() {
        return DataType.FLOAT;
    }

    @Override
    public boolean isNull() {
        return this == NULL;
    }

}

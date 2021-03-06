package com.github.smartbuf.node.basic;

import com.github.smartbuf.node.Node;
import com.github.smartbuf.node.NodeType;

/**
 * DoubleNode represents double and Double.
 *
 * @author sulin
 * @since 2019-05-08 21:00:27
 */
public final class DoubleNode extends Node {

    public final static DoubleNode ZERO = new DoubleNode(0);

    private final double value;

    private DoubleNode(double value) {
        this.value = value;
    }

    public static DoubleNode valueOf(double d) {
        if (d == 0) {
            return ZERO;
        }
        return new DoubleNode(d);
    }

    public static DoubleNode valueOf(Double d) {
        return valueOf(d.doubleValue());
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public NodeType type() {
        return NodeType.DOUBLE;
    }
}

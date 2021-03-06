package com.github.smartbuf.node.basic;

import com.github.smartbuf.node.Node;
import com.github.smartbuf.node.NodeType;

/**
 * StringNode represents String.
 *
 * @author sulin
 * @since 2019-05-08 21:00:34
 */
public final class StringNode extends Node {

    public final static StringNode EMPTY = new StringNode("");

    private final String value;

    private StringNode(String value) {
        this.value = value;
    }

    public static StringNode valueOf(String str) {
        if (str.isEmpty()) {
            return EMPTY;
        }
        return new StringNode(str);
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public NodeType type() {
        return NodeType.STRING;
    }
}

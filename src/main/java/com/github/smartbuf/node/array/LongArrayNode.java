package com.github.smartbuf.node.array;

import com.github.smartbuf.node.Node;
import com.github.smartbuf.node.NodeType;

/**
 * LongArrayNode represents long[]
 *
 * @author sulin
 * @since 2019-11-03 14:47:09
 */
public final class LongArrayNode extends Node {

    private final long[] data;

    public LongArrayNode(long[] data) {
        this.data = data;
    }

    @Override
    public Object value() {
        return data;
    }

    @Override
    public NodeType type() {
        return NodeType.ARRAY_LONG;
    }
}

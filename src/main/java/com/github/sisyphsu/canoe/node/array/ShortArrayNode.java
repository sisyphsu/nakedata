package com.github.sisyphsu.canoe.node.array;

import com.github.sisyphsu.canoe.Const;
import com.github.sisyphsu.canoe.node.Node;

/**
 * @author sulin
 * @since 2019-11-03 14:46:51
 */
public final class ShortArrayNode extends Node {

    private final short[] data;

    public ShortArrayNode(short[] data) {
        this.data = data;
    }

    @Override
    public Object value() {
        return data;
    }

    @Override
    public byte type() {
        return Const.TYPE_NARRAY_SHORT;
    }
}

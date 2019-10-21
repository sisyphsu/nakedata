package com.github.sisyphsu.nakedata.node.std;

import com.github.sisyphsu.nakedata.node.Node;
import com.github.sisyphsu.nakedata.node.NodeType;

/**
 * BooleanNode represents boolean and Boolean.
 *
 * @author sulin
 * @since 2019-05-08 21:00:07
 */
public final class BooleanNode extends Node {

    public final static BooleanNode TRUE  = new BooleanNode();
    public final static BooleanNode FALSE = new BooleanNode();

    private BooleanNode() {
    }

    public static BooleanNode valueOf(boolean b) {
        return b ? TRUE : FALSE;
    }

    public static BooleanNode valueOf(Boolean b) {
        return valueOf(b.booleanValue());
    }

    @Override
    public NodeType type() {
        return NodeType.BOOL;
    }

    @Override
    public boolean booleanValue() {
        return this == TRUE;
    }

}

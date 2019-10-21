package com.github.sisyphsu.nakedata.node.std;

import com.github.sisyphsu.nakedata.node.NodeType;
import com.github.sisyphsu.nakedata.node.Node;

/**
 * SymbolNode represents constant String or Enum etc.
 *
 * @author sulin
 * @since 2019-06-04 20:23:31
 */
public final class SymbolNode extends Node {

    private String data;

    private SymbolNode(String data) {
        this.data = data;
    }

    public static SymbolNode valueOf(String data) {
        // TODO should cache
        return new SymbolNode(data);
    }

    @Override
    public NodeType type() {
        return NodeType.SYMBOL;
    }

    @Override
    public String stringValue() {
        return data;
    }
    
}

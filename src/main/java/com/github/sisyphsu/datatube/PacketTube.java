package com.github.sisyphsu.datatube;

import com.github.sisyphsu.datatube.transport.Input;
import com.github.sisyphsu.datatube.transport.Output;

/**
 * StreamTube provides packet-mode implementation of "datatube" protocol.
 *
 * @author sulin
 * @since 2019-10-25 14:41:05
 */
public class PacketTube {

    private IOReader reader;
    private IOWriter writer;
    private Input    input;
    private Output   output;

    public <T> void write(T obj) {
    }

    public <T> T read(Class<T> clz) {
        return null;
    }

    public void close() {
    }

}
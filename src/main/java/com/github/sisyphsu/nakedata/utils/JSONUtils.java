package com.github.sisyphsu.nakedata.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sisyphsu.nakedata.jackson.NJsonNodeFactory;

/**
 * JSON utils based on Jackson
 *
 * @author sulin
 * @since 2019-04-25 11:48:24
 */
public class JSONUtils {

    public static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setNodeFactory(new NJsonNodeFactory());
    }

    /**
     * Parse Object to JsonNode
     *
     * @param obj Source object
     * @param <T> JsonNode's subclass
     * @return Result
     */
    public static <T extends JsonNode> T toJsonNode(Object obj) {
        return MAPPER.valueToTree(obj);
    }

}

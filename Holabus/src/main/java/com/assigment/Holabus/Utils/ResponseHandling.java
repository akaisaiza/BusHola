package com.assigment.Holabus.Utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandling {
    public static Map<?, ?> addMessage(String key, String value) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(key, value);
        return responseBody;
    }
}

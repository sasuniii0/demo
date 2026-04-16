package com.example.demo.util;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class XmlParser {
    public JSONObject toJson(String xml) {
        if (xml == null || xml.isBlank()) {
            throw new RuntimeException("XML is empty");
        }

        return XML.toJSONObject(xml);
    }

    public JSONObject findObject(JSONObject json, String key) {

        if (json.has(key)) return json.getJSONObject(key);

        for (String k : json.keySet()) {
            Object value = json.get(k);

            if (value instanceof JSONObject obj) {
                JSONObject result = findObject(obj, key);
                if (result != null) return result;
            }

            if (value instanceof org.json.JSONArray arr) {
                for (int i = 0; i < arr.length(); i++) {
                    Object item = arr.get(i);
                    if (item instanceof JSONObject obj) {
                        JSONObject result = findObject(obj, key);
                        if (result != null) return result;
                    }
                }
            }
        }

        return null;
    }
}

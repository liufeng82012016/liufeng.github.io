package com.my.liufeng.gateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T read(String jsonStr, TypeReference<T> type) {
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (JsonProcessingException e) {
            log.info("parse json fail.str:{}", jsonStr, e);
            return null;
        }
    }
}

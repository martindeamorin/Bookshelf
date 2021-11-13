package com.example.bookshelf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler <T> {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", responseObj);
            String jsonReponse = new JsonParser(map)
                    .getParseResult();
            return new ResponseEntity<>(jsonReponse, status);
    }
}
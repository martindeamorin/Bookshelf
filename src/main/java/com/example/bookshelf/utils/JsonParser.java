package com.example.bookshelf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JsonParser <T> {
    private String parseResult; 
    
    public JsonParser(T object) throws JsonProcessingException{
        this.parseResult = this.objectToJson(object);
    }
    public String objectToJson(T object) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    
}

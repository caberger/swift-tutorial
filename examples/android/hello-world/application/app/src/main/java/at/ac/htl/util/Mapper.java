package at.ac.htl.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.CompletionException;

public class Mapper<T> {
    ObjectMapper mapper;
    final Class<? extends T> clazz;

    public Mapper(Class<? extends T> clazz) {
        this.clazz = clazz;
        mapper = new ObjectMapper();
    }
    public T fromResource(byte[] json) {
        T model = null;
        try {
            model = mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new CompletionException(e);
        }
        return model;
    }
    public byte[] toResource(T model) {
        byte[] bytes = null;
        try {
            bytes = mapper.writeValueAsBytes(model);
        } catch (JsonProcessingException e) {
            throw new CompletionException(e);
        }
        return bytes;
    }
    public T clone(T model) {
        return fromResource(toResource(model));
    }
}

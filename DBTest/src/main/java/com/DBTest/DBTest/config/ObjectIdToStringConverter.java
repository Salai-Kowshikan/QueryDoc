package com.DBTest.DBTest.config;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ObjectIdToStringConverter implements Converter<ObjectId, String> {
    @Override
    public String convert(ObjectId source) {
        return source.toHexString();
    }
}
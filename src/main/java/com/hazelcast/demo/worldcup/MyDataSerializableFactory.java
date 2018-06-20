package com.hazelcast.demo.worldcup;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyDataSerializableFactory implements DataSerializableFactory {

        @Override
        public IdentifiedDataSerializable create(int id) {
        if (id == ApplicationConstants.CLASS_SENTIMENT) {
            return new Sentiment();
        }

        log.error("create() -> {}", id);
                return null;
    }

}
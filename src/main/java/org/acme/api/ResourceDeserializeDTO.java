package org.acme.api;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class ResourceDeserializeDTO extends ObjectMapperDeserializer<ResourceDTO> {
    public ResourceDeserializeDTO() {
        super(ResourceDTO.class);
    }

}

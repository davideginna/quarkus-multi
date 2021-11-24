package org.acme.api;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class ResourceProducer {
    @Inject
    @Channel("sender")
    Emitter<ResourceDTO> resourceDTOEmitter;

    public CompletableFuture<Void> send(ResourceDTO resourceDTO) {
        return resourceDTOEmitter.send(resourceDTO).toCompletableFuture();
    }
}

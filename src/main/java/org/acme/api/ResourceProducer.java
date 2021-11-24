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
        Random random = new Random();
        var metadata = Metadata.of(
                OutgoingKafkaRecordMetadata.builder()
                        .withPartition(random.ints(0, 3).findFirst().getAsInt())//number of partiton

        );

        var message = Message.of(resourceDTO, metadata);
        resourceDTOEmitter.send(message);
        return CompletableFuture.completedFuture(null);
        //return resourceDTOEmitter.send(resourceDTO).toCompletableFuture();
    }
}

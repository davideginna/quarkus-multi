package org.acme.api;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class ResourceProducer {
    @Inject
    @Channel("sender")
    @Broadcast
    Emitter<ResourceDTO> resourceDTOEmitter;

    public CompletableFuture<Void> send(ResourceDTO resourceDTO) {
        return resourceDTOEmitter.send(resourceDTO).toCompletableFuture();
    }
}

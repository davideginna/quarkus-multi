package org.acme.api;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ResourceProducer {
    @Inject
    @Channel("sender")
    Emitter<ResourceDTO> resourceDTOEmitter;

    public void send(ResourceDTO resourceDTO) {
        resourceDTOEmitter.send(resourceDTO);
    }
}

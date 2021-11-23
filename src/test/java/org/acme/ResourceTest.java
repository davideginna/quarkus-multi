package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.api.ResourceDTO;
import org.acme.api.ResourceProducer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Date;

@QuarkusTest
//@QuarkusTestResource(KafkaResource.class)
public class ResourceTest {
    @Inject
    ResourceProducer resourceProducer;

    @Test
    public void test() {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setMessage("message " + new Date());
        resourceProducer.send(resourceDTO);
    }

}

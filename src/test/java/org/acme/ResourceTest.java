package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.api.ResourceDTO;
import org.acme.api.ResourceProducer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@QuarkusTest
//@QuarkusTestResource(KafkaResource.class)
public class ResourceTest {
    @Inject
    ResourceProducer resourceProducer;

    @Test
    public void test() {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setMessage("message " + new Date());
        var result1 = resourceProducer.send(resourceDTO);
        var result2 = resourceProducer.send(resourceDTO);
        var result3 = resourceProducer.send(resourceDTO);
        try {
            CompletableFuture.allOf(result1, result2, result3);
            // wait for consumer
            Thread.sleep(10000);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

}

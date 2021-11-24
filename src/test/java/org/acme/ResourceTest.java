package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.api.ResourceDTO;
import org.acme.api.ResourceProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@QuarkusTest
public class ResourceTest {
    @Inject
    ResourceProducer resourceProducer;

    @Test
    public void test() {
        var result1 = resourceProducer.send(new ResourceDTO());
        var result2 = resourceProducer.send(new ResourceDTO());
        var result3 = resourceProducer.send(new ResourceDTO());
        try {
            CompletableFuture.allOf(result1, result2, result3);
            // wait for consumer
            Thread.sleep(10000);
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }

}

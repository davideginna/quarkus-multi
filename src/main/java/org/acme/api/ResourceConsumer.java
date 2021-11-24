package org.acme.api;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class ResourceConsumer {

    @Incoming("event")
    @Retry(delay = 20, delayUnit = ChronoUnit.SECONDS, maxRetries = 0)
    @Blocking(ordered = false)
    @Acknowledgment(Acknowledgment.Strategy.MANUAL)
    public CompletionStage<Void> process(Message<ResourceDTO> incomingMessage) {
        try {
            var metadata = incomingMessage.getMetadata(IncomingKafkaRecordMetadata.class);
//            log.info("AtomicLong:" + wip.incrementAndGet());
            System.out.println("[concurrent-test]" + Thread.currentThread().getName() + " - start - ");
            metadata.ifPresent(entry -> System.out.println("[concurrent-test]" + Thread.currentThread().getName() + " partition " + entry.getPartition()));

            Thread.sleep(2000);

            //processMessage(incomingMessage);
            System.out.println("[concurrent-test]" + Thread.currentThread().getName() + " - end - ");


//            wip.decrementAndGet();

            return incomingMessage.ack();
        } catch (Exception e) {
            return incomingMessage.nack(e);
        }
    }

}

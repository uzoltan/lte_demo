package com.example.lte_demo_consumer;

import java.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class LteDemoConsumerApplication {

  private static final Logger logger = LoggerFactory.getLogger(LteDemoConsumerApplication.class);

  private final MyProperties props;

  @Autowired
  public LteDemoConsumerApplication(MyProperties properties) {
    this.props = properties;
    consumeServerSentEvent();
  }

  public static void main(String[] args) {
    SpringApplication.run(LteDemoConsumerApplication.class, args);
  }

  public void consumeServerSentEvent() {
    WebClient client = WebClient.create(props.getProviderUrl());
    ParameterizedTypeReference<String> type = new ParameterizedTypeReference<String>() {
    };

    Flux<String> eventStream = client.get().uri("/stream").retrieve().bodyToFlux(type);

    eventStream.subscribe(content -> logger.info("Time: {}, content[{}] ", LocalTime.now(), content),
                          error -> logger.error("Error receiving SSE: {}", error), () -> logger.info("Event stream completed"));
    System.out.println("Subscribed to event provider!");
  }
}

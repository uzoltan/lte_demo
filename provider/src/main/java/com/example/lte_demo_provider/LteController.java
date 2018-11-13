package com.example.lte_demo_provider;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class LteController {

  private final MyProperties props;

  @Autowired
  public LteController(MyProperties properties) {
    this.props = properties;
  }

  @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> streamFlux() {
    return Flux.interval(Duration.ofMillis(props.getMessageIntervalInMillis())).map(sequence -> LteDemoProviderApplication.randomString);
  }

}

package com.example.lte_demo_consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my.prop")
public class MyProperties {

  private String providerUrl = "http://localhost:8200";

  public MyProperties() {
  }

  public String getProviderUrl() {
    return providerUrl;
  }

  public void setProviderUrl(String providerUrl) {
    this.providerUrl = providerUrl;
  }
}

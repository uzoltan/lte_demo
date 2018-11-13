package com.example.lte_demo_provider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my.prop")
public class MyProperties {

  private Integer messageInBytes = 100;
  private Integer messageIntervalInMillis = 10;

  public MyProperties() {
  }

  public Integer getMessageInBytes() {
    return messageInBytes;
  }

  public void setMessageInBytes(Integer messageInBytes) {
    this.messageInBytes = messageInBytes;
  }

  public Integer getMessageIntervalInMillis() {
    return messageIntervalInMillis;
  }

  public void setMessageIntervalInMillis(Integer messageIntervalInMillis) {
    this.messageIntervalInMillis = messageIntervalInMillis;
  }
}

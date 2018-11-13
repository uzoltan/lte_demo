package com.example.lte_demo_provider;

import java.util.Random;
import java.util.ServiceConfigurationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LteDemoProviderApplication {

  static String randomString;
  private final MyProperties props;

  @Autowired
  public LteDemoProviderApplication(MyProperties properties) {
    this.props = properties;
    generateTheString();
  }

  public static void main(String[] args) {
    SpringApplication.run(LteDemoProviderApplication.class, args);
  }

  public void generateTheString() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = props.getMessageInBytes() - 5;
    if (targetStringLength < 1) {
      throw new ServiceConfigurationError("Message length in bytes must be a positive number!");
    }
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
      int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
      buffer.append((char) randomLimitedInt);
    }
    randomString = buffer.toString();
  }
}

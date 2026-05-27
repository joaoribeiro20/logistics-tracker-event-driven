package com.joao.logistics_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LogisticsTrackerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LogisticsTrackerApplication.class, args);
  }
}

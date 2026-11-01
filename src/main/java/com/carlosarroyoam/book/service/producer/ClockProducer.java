package com.carlosarroyoam.book.service.producer;

import java.time.Clock;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

public class ClockProducer {
  @Produces
  @Dependent
  public Clock createClock() {
    return Clock.systemDefaultZone();
  }
}

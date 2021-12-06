package ratelimiter;

import java.time.Duration;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class RateLimiter {

  protected int capacity;

  protected Duration limitForPeriod;

  abstract Optional<String> getToken(Thread treadAqcuiringAToken);

  protected String getOrderableId(Thread treadAqcuiringAToken) {

    return String.format("%s-%d", treadAqcuiringAToken.getName().toLowerCase(), treadAqcuiringAToken.getId());
  }
}

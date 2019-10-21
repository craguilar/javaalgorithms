package numbers;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

public class NumberAlgorithmsTest {

  @Test
  public void findSumOfMultiplesOfNumbersTest() {

    Instant start = Instant.now();
    int sum = NumberAlgorithms.findSumOfMultiplesOfNumbers(new int[] { 3, 5 }, 10);
    assertEquals(23, sum);
    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println("Time Elapsed: " + timeElapsed + " ms");
  }

  @Test
  public void findSumOfFiboEvenNumbersTest() {
    long sum = NumberAlgorithms.findSumOfFiboEvenNumbers(21);
    assertEquals(10, sum);
  }

  @Test
  public void findLargestPrimeFactorTest(){
    long biggestPrime = NumberAlgorithms.findLargestPrimeFactor(9);
    assertEquals(3,biggestPrime);
  }
}
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
  public void findLargestPrimeFactorTest() {
    long biggestPrime = NumberAlgorithms.findLargestPrimeFactor(9);
    assertEquals(3, biggestPrime);
  }

  @Test
  public void fiboTestCorrectness() {
    assertEquals(0, NumberAlgorithms.fibo(0));
    assertEquals(1, NumberAlgorithms.fibo(1));
    assertEquals(1, NumberAlgorithms.fibo(2));
    assertEquals(2, NumberAlgorithms.fibo(3));
    assertEquals(3, NumberAlgorithms.fibo(4));
    assertEquals(4181, NumberAlgorithms.fibo(19));
    assertEquals(259695496911122585L, NumberAlgorithms.fibo(85));
    assertEquals(2880067194370816120L, NumberAlgorithms.fibo(90));
    assertEquals(7540113804746346429L, NumberAlgorithms.fibo(92));
  }
}
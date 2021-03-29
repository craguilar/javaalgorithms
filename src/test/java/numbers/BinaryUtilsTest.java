package numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinaryUtilsTest {

  @Test
  public void sumBinary() {
    assertEquals("01", BinaryUtils.sumBinary("1", "0"));
    assertEquals("10", BinaryUtils.sumBinary("1", "1"));
    assertEquals("011101010101010111", BinaryUtils.sumBinary("11101010101010101", "10"));
    assertEquals("1000", BinaryUtils.sumBinary("111", "1"));
  }
}

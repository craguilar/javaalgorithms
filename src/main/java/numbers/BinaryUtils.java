package numbers;

public class BinaryUtils {

  public static String sumBinary(String binaryNum1, String binaryNum2) {

    return sumTwoBinaryArrays(convertStringToBinary(binaryNum1), convertStringToBinary(binaryNum2));
  }

  private static String sumTwoBinaryArrays(char[] arr1, char[] arr2) {
    // Do the binary sum , scenarios to handler are the carry .
    int maxLength = arr1.length >= arr2.length ? arr1.length : arr2.length;
    int countArr1 = arr1.length - 1; // 2
    int countArr2 = arr2.length - 1; // 1
    char[] result = new char[maxLength + 1]; // 4, [1,0,1,0]
    int carry = 0;
    for (int i = result.length - 1; i >= 0; i--) {

      int val1 = (countArr1 >= 0 ? arr1[countArr1] : '0') - '0'; // 1
      int val2 = (countArr2 >= 0 ? arr2[countArr2] : '0') - '0'; // 0
      int value = carry + val1 + val2; // 2
      result[i] = (value % 2) == 1 ? '1' : '0';
      carry = value > 1 ? 1 : 0; // 1
      countArr1--;
      countArr2--;
    }
    return new String(result);
  }

  private static char[] convertStringToBinary(String binaryString) {
    char[] binary = new char[binaryString.length()];
    for (int i = 0; i < binaryString.length(); i++) {
      binary[i] = binaryString.charAt(i);
    }
    return binary;

  }
}

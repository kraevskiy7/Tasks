import java.math.BigInteger;

public class FactorialDigitSum {
    public static void main(String[] args) {
        // Calculate 100!
        BigInteger factorial = BigInteger.ONE; // Start with 1
        for (int i = 2; i <= 100; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i)); // Multiply current factorial by i
        }

        // Convert factorial to string to iterate through digits
        String factorialStr = factorial.toString();
        int sumOfDigits = 0;

        // Calculate the sum of digits
        for (char digit : factorialStr.toCharArray()) {
            sumOfDigits += Character.getNumericValue(digit); // Convert char to int and add to sum
        }

        // Print the result
        System.out.println("Sum of digits in 100! is: " + sumOfDigits);
    }
}

import java.util.Scanner;

public class BracketExpressions {
    // Function to calculate the nth Catalan number
    private static long catalan(int n) {
        // Use dynamic programming to calculate the Catalan numbers
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: C(0) = 1

        for (int i = 1; i <= n; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N (number of pairs of brackets): ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("N must be a non-negative integer.");
        } else {
            // Calculate and print the result
            System.out.println("Number of valid bracket expressions: " + catalan(n));
        }

        scanner.close();
    }
}

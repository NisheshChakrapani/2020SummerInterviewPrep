public class FactorialTrailingZeros {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            System.out.println(i + ": " + factorial(i) + " --> " + trailingZerosInFactorial(i));
        }
    }

    public static long factorial(int n) {
        if (n == 1) return 1;
        return (long)n*(factorial(n-1));
    }

    private static int trailingZerosInFactorial(int n) {
        int counter = 0;
        for (int i = 5; i <= n; i+=5) {
            int temp = i;
            while (temp % 10 == 5) {
                counter++;
                temp /= 5;
            }
            temp = i;
            while (temp > 0 && temp % 10 == 0) {
                counter++;
                temp /= 10;
            }
        }
        return counter;
    }
}

public class FindLargestSumOfSubarrayWithNegatives {
    //Find the subarray of an int array with the largest sum possible, given that the array can
    //contain both positive and negative numbers, and return this sum
    public static void main(String[] args) {
        int[] arr = {2, -8, 3, -2, 4, -10};
        System.out.println(largestSubarraySum(arr));

        int[] arr2 = {8, 7, -16, -5, 4, -3, 12, -14, 8, -1, 9};
        System.out.println(largestSubarraySum(arr2));
    }

    private static int largestSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];

        int bestSum = Integer.MIN_VALUE;
        int currSum = arr[0];
        int i = 0;

        while (i < arr.length) {
            if (currSum > bestSum) bestSum = currSum;
            if (currSum < 0) {
                i++;
                if (i < arr.length) currSum = arr[i];
            } else {
                i++;
                if (i < arr.length) currSum += arr[i];
            }
        }

        return bestSum;
    }
}

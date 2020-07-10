import java.awt.*;

public class FindSubarrayToSort {
    //Find indices n and m to make a subarray which, when sorted, would make the entire array sorted
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        Point p = findSubarray(arr);
        System.out.println("n: " + p.x + ", m: " + p.y);

        int[] arr2 = {1, 2, 4, 7, 10, 17, 7, 12, 6, 7, 16, 18, 19};
        Point p2 = findSubarray(arr2);
        System.out.println("n: " + p2.x + ", m: " + p2.y);

        int[] arr3 = {1, 2, 4, 7, 10, 17, 7, 12, 6, 3, 16, 18, 19};
        Point p3 = findSubarray(arr3);
        System.out.println("n: " + p3.x + ", m: " + p3.y);
    }

    private static Point findSubarray(int[] arr) {
        if (arr == null || arr.length <= 1) return new Point(-1, -1);
        int leftEndpoint = 0, rightEndpoint = arr.length-1;
        while (arr[leftEndpoint+1] >= arr[leftEndpoint]) {
            leftEndpoint++;
            if (leftEndpoint == arr.length-1) return new Point(-1, -1);
        }
        while (arr[rightEndpoint-1] <= arr[rightEndpoint]) rightEndpoint--;

        int n = leftEndpoint+1, m = rightEndpoint-1;
        int subArrMin = Integer.MAX_VALUE, subArrMax = Integer.MIN_VALUE;
        for (int i = leftEndpoint+1; i < rightEndpoint; i++) {
            if (arr[i] < subArrMin) {
                subArrMin = arr[i];
            }
            if (arr[i] > subArrMax) {
                subArrMax = arr[i];
            }
        }

        while (subArrMin < arr[leftEndpoint] || subArrMax > arr[rightEndpoint]) {
            if (subArrMin < arr[leftEndpoint]) {
                if (leftEndpoint > 0) {
                    leftEndpoint--;
                    n--;
                    if (arr[leftEndpoint+1] < subArrMin) {
                        subArrMin = arr[leftEndpoint+1];
                    } else if (arr[leftEndpoint+1] > subArrMax) {
                        subArrMax = arr[leftEndpoint+1];
                    }
                }
            }
            if (subArrMax > arr[rightEndpoint]) {
                if (rightEndpoint < arr.length-1) {
                    rightEndpoint++;
                    m++;
                    if (arr[rightEndpoint-1] < subArrMin) {
                        subArrMin = arr[rightEndpoint-1];
                    } else if (arr[rightEndpoint-1] > subArrMax) {
                        subArrMax = arr[rightEndpoint-1];
                    }
                }
            }
        }

        return new Point(n, m);
    }
}

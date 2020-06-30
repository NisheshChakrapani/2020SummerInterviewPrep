public class MagicIndex {
    public static void main(String[] args) {
        int[] arr1 = {-12, -6, -6, 0, 2, 2, 5, 7, 10};
        System.out.println(magicIndex(arr1));

        int[] arr2 = {-12, -6, 2, 2, 2, 2, 5, 9, 10};
        System.out.println(magicIndex(arr2));
    }

    public static int magicIndex(int[] arr) {
        return magicIndex(arr, 0, arr.length-1);
    }

    private static int magicIndex(int[] arr, int low, int high) {
        if (high < low) return -1;

        int mid = (low + high) / 2;
        int midValue = arr[mid];
        if (mid == arr[mid]) return mid;

        int leftBound = Math.min(mid - 1, midValue);
        int left = magicIndex(arr, low, leftBound);
        if (left >= 0) return left;

        int rightBound = Math.max(mid + 1, midValue);
        int right = magicIndex(arr, rightBound, high);
        return right;
    }
}

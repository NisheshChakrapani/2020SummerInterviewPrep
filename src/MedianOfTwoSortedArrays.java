public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 7, 10, 13, 18, 30};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0;
        if (nums1 == null || nums1.length == 0) return medianOfSorted(nums2);
        if (nums2 == null || nums2.length == 0) return medianOfSorted(nums1);

        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int n = nums1.length;
        int m = nums2.length;
        int lo = 0;
        int hi = n;
        while (lo <= hi) {
            int i = (lo + hi) / 2;
            int j = (n + m + 1) / 2 - i;

            int iPrev, iCurr, jPrev, jCurr;
            if (i == 0) iPrev = Integer.MIN_VALUE;
            else iPrev = nums1[i-1];
            if (i == n) iCurr = Integer.MAX_VALUE;
            else iCurr = nums1[i];
            if (j == 0) jPrev = Integer.MIN_VALUE;
            else jPrev = nums2[j-1];
            if (j == m) jCurr = Integer.MAX_VALUE;
            else jCurr = nums2[j];

            if (iPrev <= jCurr && jPrev <= iCurr) {
                if ((n + m) % 2 == 0) {
                    return ((double)Math.max(iPrev, jPrev) + Math.min(iCurr, jCurr)) / 2;
                } else {
                    return Math.max(iPrev, jPrev);
                }
            } else if (iPrev > jCurr) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        return 0;
    }

    private static double medianOfSorted(int[] arr) {
        if (arr.length % 2 == 1) return arr[arr.length/2];
        return ((double)(arr[arr.length/2] + arr[arr.length/2 - 1])) / 2;
    }
}

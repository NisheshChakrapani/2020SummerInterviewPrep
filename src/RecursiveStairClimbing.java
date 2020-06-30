public class RecursiveStairClimbing {
    public static void main(String[] args) {
        int n = 1000;
//        long startTimeUp = System.currentTimeMillis();
//        stairCombosUp(n);
//        long elapsedTimeUp = System.currentTimeMillis() - startTimeUp;
//
//        long startTimeDown = System.currentTimeMillis();
//        stairCombosDown(n);
//        long elapsedTimeDown = System.currentTimeMillis() - startTimeDown;

        long startTimeDynamic = System.currentTimeMillis();
        stairCombosDownDynamic(n);
        long elapsedTimeDynamic = System.currentTimeMillis() - startTimeDynamic;

//        System.out.println(elapsedTimeUp + " ms for going up, n = " + n +  ".");
//        System.out.println(elapsedTimeDown + " ms for going down, n = " + n + ".");
        System.out.println(elapsedTimeDynamic + " ms for going down dynamically, n = " + n + ".");
    }

    public static int stairCombosUp(int numStairs) {
        if (numStairs < 0) return 0;
        return stairCombosUp(numStairs, 0);
    }
    private static int stairCombosUp(int n, int currStair) {
        if (currStair > n) return 0;
        if (currStair == n) return 1;
        return stairCombosUp(n, currStair + 1)
                + stairCombosUp(n, currStair + 2)
                + stairCombosUp(n, currStair + 3);
    }

    public static int stairCombosDown(int numStairs) {
        if (numStairs < 0) return 0;
        return stairCombosDownRecurse(numStairs);
    }
    private static int stairCombosDownRecurse(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        return stairCombosDownRecurse(n - 1)
                + stairCombosDownRecurse(n - 2)
                + stairCombosDownRecurse(n - 3);
    }

    private static int stairCombosDownDynamic(int numStairs) {
        if (numStairs < 0) return 0;
        if (numStairs <= 1) return 1;
        return stairCombosDownDynamic(numStairs, new int[numStairs]);
    }
    private static int stairCombosDownDynamic(int n, int[] arr) {
        if (n < 0) return 0;
        if (n == 0) return 1;

        int sum1, sum2, sum3;
        if (n-2 >= 0 && arr[n-2] != 0) sum1 = arr[n-2];
        else sum1 = stairCombosDownDynamic(n-1, arr);
        if (n-3 >= 0 && arr[n-3] != 0) sum2 = arr[n-3];
        else sum2 = stairCombosDownDynamic(n-2, arr);
        if (n-4 >= 0 && arr[n-4] != 0) sum3 = arr[n-4];
        else sum3 = stairCombosDownDynamic(n-3, arr);

        arr[n-1] = sum1 + sum2 + sum3;
        return arr[n-1];
    }
}

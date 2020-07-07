public class MIntoNBitwise {
    public static void main(String[] args) {
        System.out.println("n: " + Integer.toBinaryString(1024));
        System.out.println("m: " + Integer.toBinaryString(19));
        System.out.println("result: " + Integer.toBinaryString(mIntoN(1024, 19, 2, 6)));
        System.out.println();
        System.out.println("n: " + Integer.toBinaryString(1024));
        System.out.println("m: " + Integer.toBinaryString(19));
        System.out.println("result: " + Integer.toBinaryString(mIntoNBetter(1024, 19, 2, 6)));
    }

    //8 operations
    public static int mIntoN(int n, int m, int i, int j) {
        int end = (~(0xFFFFFFFF << i)) & n;
        n = n >> (j+i-1);
        n = n << (j+i-1);
        m = m << i;
        n = n | m;
        n = n | end;
        return n;
    }

    //6 operations
    public static int mIntoNBetter(int n, int m, int i, int j) {
        int mask = (0xFFFFFFFF >> (32-i)) | (0xFFFFFFFF << (j+i-1));
        n = n & mask;
        m = m << i;
        n = n | m;
        return n;
    }
}

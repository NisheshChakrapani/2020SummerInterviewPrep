import java.awt.*;

public class BinarySearch2D {
    public static void main(String[] args) {
        testSet1();
        testSet2();
    }

    public static void testSet1() {
        int[][] matrix1 = {{2, 3, 5, 6, 8},
                          {4, 7, 8, 9, 12},
                          {9, 11, 13, 15, 19},
                          {13, 14, 16, 17, 22}};

        Point p11 = binarySearch2D(matrix1, 11);
        Point p12 = binarySearch2D(matrix1, 17);
        Point p13 = binarySearch2D(matrix1, 12);
        Point p14 = binarySearch2D(matrix1, 1);
        Point p15 = binarySearch2D(matrix1, 10);
        System.out.println(p11);
        System.out.println(p12);
        System.out.println(p13);
        System.out.println(p14);
        System.out.println(p15);
        System.out.println();
    }

    public static void testSet2() {
        int[][] matrix2 = {
                {1, 5, 8, 9},
                {2, 6, 12, 13},
                {5, 9, 15, 20},
                {8, 17, 18, 31}};
        Point p21 = binarySearch2D(matrix2, 9);
        Point p22 = binarySearch2D(matrix2, 2);
        Point p23 = binarySearch2D(matrix2, 15);
        Point p24 = binarySearch2D(matrix2, 16);
        Point p25 = binarySearch2D(matrix2, 32);
        Point p26 = binarySearch2D(matrix2, 30);
        System.out.println(p21);
        System.out.println(p22);
        System.out.println(p23);
        System.out.println(p24);
        System.out.println(p25);
        System.out.println(p26);
        System.out.println();
    }

    public static Point binarySearch2D(int[][] matrix, int target) {
        if (matrix == null) return null;
        return binarySearch2D(matrix, 0, matrix.length-1, 0, matrix[0].length-1, target);
    }

    private static int binarySearchRow(int[] row, int lo, int hi, int target) {
        if (hi < lo) return -1;
        if (target == row[lo]) return lo;
        if (target == row[hi]) return hi;
        int mid = (hi+lo)/2;
        if (target == row[mid]) return mid;
        if (target > row[mid]) return binarySearchRow(row, mid+1, hi-1, target);
        else return binarySearchRow(row, lo+1, mid-1, target);
    }

    private static int binarySearchCol(int[][] matrix, int col, int lo, int hi, int target) {
        if (hi < lo) return -1;
        if (target == matrix[lo][col]) return lo;
        if (target == matrix[hi][col]) return hi;
        int mid = (hi+lo)/2;
        if (target == matrix[mid][col]) return mid;
        if (target > matrix[mid][col]) return binarySearchCol(matrix, col, mid+1, hi-1, target);
        else return binarySearchCol(matrix, col, lo+1, mid-1, target);
    }

    private static Point binarySearch2D(int[][] matrix, int startRow, int endRow, int startCol,
                                      int endCol, int target) {
        if (endRow < startRow || endCol < startCol) return null;
        if (endRow - startRow == 1) {
            Point p1 = new Point(startRow, binarySearchRow(matrix[startRow], startCol, endCol,
                    target));
            if (p1.y != -1) return p1;
            Point p2 = new Point(endRow, binarySearchRow(matrix[endRow], startCol, endCol,
                    target));
            if (p2.y != -1) return p2;
            return null;
        } //If only two rows binary search both
        if (endCol - startCol == 1) {
            Point p1 = new Point(binarySearchCol(matrix, startCol, startRow, endRow, target),
                    startCol);
            if (p1.x != -1) return p1;
            Point p2 = new Point(binarySearchCol(matrix, endCol, startRow, endRow, target),
                    endCol);
            if (p2.x != -1) return p2;
            return null;
        } //If only two columns binary search both
        if (endRow == startRow) {
            Point p = new Point(startRow, binarySearchRow(matrix[startRow], startCol, endCol,
                    target));
            if (p.y != -1) return p;
            return null;
        }
        if (endCol == startCol) {
            Point p = new Point(binarySearchCol(matrix, startCol, startRow, endRow, target),
                    startCol);
            if (p.x != -1) return p;
        }

        int i = startRow;
        boolean rowsLeft = true, colsLeft = true;
        while (rowsLeft && colsLeft) {
            if (matrix[i][i] == target) return new Point(i, i);
            if (matrix[i][i] > target) break;
            if (i == endRow) rowsLeft = false;
            if (i == endCol) colsLeft = false;
            i++;
        }

        if (!rowsLeft && !colsLeft) {
            return null;
        }
        if (rowsLeft && colsLeft) {
            Point topRightSearch = binarySearch2D(matrix, startRow, i-1, i, endCol, target);
            if (topRightSearch != null) return topRightSearch;
            Point bottomLeftSearch = binarySearch2D(matrix, i, endRow, startCol, i-1, target);
            if (bottomLeftSearch != null) return bottomLeftSearch;
        } else if (rowsLeft) {
            Point searchRows = binarySearch2D(matrix, i+1, endRow, startCol, endCol, target);
            if (searchRows != null) return null;
        } else if (colsLeft) {
            Point searchCols = binarySearch2D(matrix, startRow, endRow, i+1, endCol, target);
            if (searchCols != null) return null;
        }
        return null;
    }
}

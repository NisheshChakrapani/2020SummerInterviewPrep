import java.util.Arrays;

public class LargestBlackPerimeter {
    private static class Tuple {
        int rowString;
        int colString;
        public Tuple(int rowString, int colString) {
            this.rowString = rowString;
            this.colString = colString;
        }

        @Override
        public String toString() {
            return "{r=" + rowString + ",c=" + colString + "}";
        }
    }

    //Find largest subsquare of a square matrix filled with either black (true) or white (false)
    //squares, where the entire perimeter of the subsquare is black
    public static Tuple[][] stringsOfBlack;
    public static void main(String[] args) {
        boolean[][] m1 = {
                {true, false, false, true, true},
                {false, true, true, false, true},
                {true, true, true, false, false},
                {true, false, true, true, true},
                {true, true, true, false, false}};
        //solveBruteForce(m1);
        //solveOptimized(m1);

        boolean[][] m2 = new boolean[50][50];
        for(int i = 0; i < m2.length; i++) {
            for (int j = 0; j < m2[i].length; j++) {
                m2[i][j] = Math.random() < 0.8;
            }
        }

        long start = System.currentTimeMillis();
        solveBruteForce(m2);
        System.out.println("Brute force: " + (System.currentTimeMillis() - start) + " ms");
        start = System.currentTimeMillis();
        solveOptimized(m2);
        System.out.println("Optimized: " + (System.currentTimeMillis() - start) + " ms");
    }

    //prints top-left and bottom-right corner coordinates
    private static void solveBruteForce(boolean[][] matrix) {
        int largest = 0;
        int topLeftX = -1, topLeftY = -1, bottomRightX = -1, bottomRightY = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) {
                    int temp = largestFrom(matrix, i, j);
                    if (temp > largest) {
                        largest = temp;
                        topLeftX = i;
                        topLeftY = j;
                        bottomRightX = (i+largest-1);
                        bottomRightY = (j+largest-1);
                    }
                }
            }
        }

        if (largest == 0) System.out.println("No black squares found");
        else {
            System.out.println("(" + topLeftX + ", " + topLeftY + "), " +
                    "(" + bottomRightX + ", " + bottomRightY + ")");
        }
    }
    private static int largestFrom(boolean[][] matrix, int i, int j) {
        int largest = 1;
        int i2 = i, j2 = j;
        while ((i2+1) < matrix.length && (j2+1) < matrix[i2].length) {
            i2++;
            j2++;
            if (perimeterAllBlack(matrix, i, j, i2, j2)) largest = (i2-i)+1;
        }

        return largest;
    }
    private static boolean perimeterAllBlack(boolean[][] matrix, int i, int j, int i2, int j2) {
        for (int x = j; x < j2; x++) if (!matrix[i][x] || !matrix[i2][x]) return false;
        for (int y = i; y < i2; y++) if (!matrix[y][j] || !matrix[y][j2]) return false;
        return true;
    }

    //prints top-left and bottom-right corner coordinates
    private static void solveOptimized(boolean[][] matrix) {
        setupTupleMatrix(matrix);
        int largest = 0;
        int topLeftX = -1, topLeftY = -1, bottomRightX = -1, bottomRightY = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) {
                    int n = matrix.length - Math.max(i, j);
                    if (n <= largest) continue;
                    if (!(stringsOfBlack[i][j].rowString < n || stringsOfBlack[i][j].colString < n
                            || stringsOfBlack[i+n-1][j].rowString < n
                            || stringsOfBlack[i][j+n-1].colString < n)) {
                        largest = n;
                        topLeftX = i;
                        topLeftY = j;
                        bottomRightX = i+n-1;
                        bottomRightY = j+n-1;
                    }
                }
            }
        }

        if (largest == 0) System.out.println("No black squares found");
        else {
            System.out.println("(" + topLeftX + ", " + topLeftY + "), " +
                    "(" + bottomRightX + ", " + bottomRightY + ")");
        }
    }

    //Stores the longest sequence of black squares (both row-wise and column-wise) from every given
    //point in the original matrix
    private static void setupTupleMatrix(boolean[][] matrix) {
        stringsOfBlack = new Tuple[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int row = largestRowSequence(matrix, i, j);
                int col = largestColSequence(matrix, i, j);
                stringsOfBlack[i][j] = new Tuple(row, col);
            }
        }
    }
    private static int largestRowSequence(boolean[][] matrix, int i, int j) {
        int counter = 0;
        while (j+counter < matrix[i].length && matrix[i][j+counter]) counter++;
        return counter;
    }
    private static int largestColSequence(boolean[][] matrix, int i, int j) {
        int counter = 0;
        while (i+counter < matrix.length && matrix[i+counter][j]) counter++;
        return counter;
    }
}

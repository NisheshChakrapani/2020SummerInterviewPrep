public class RobotGridPaths {
    public static void main(String[] args) {
        System.out.println(numRobotPaths(2, 2));
        System.out.println(numRobotPaths(3, 4));
        System.out.println(numRobotPaths(6, 4));
        System.out.println(numRobotPaths(5, 5));
        System.out.println(numRobotPaths(30, 20));
        System.out.println(numRobotPaths(60, 60));
    }

    public static long numRobotPaths(int rows, int cols) {
        return numRobotPaths(0, 0, new long[rows][cols]);
    }

    private static long numRobotPaths(int x, int y, long[][] grid) {
        if (x == grid.length-1 && y == grid[0].length-1) return 1;
        if (x >= grid.length || y >= grid[0].length) return 0;

        long pathsDown, pathsRight;
        if (x+1 < grid.length && grid[x+1][y] != 0) pathsDown = grid[x+1][y];
        else pathsDown = numRobotPaths(x+1, y, grid);
        if (y+1 < grid[0].length && grid[x][y+1] != 0) pathsRight = grid[x][y+1];
        else pathsRight = numRobotPaths(x, y+1, grid);

        grid[x][y] = pathsDown + pathsRight;
        return grid[x][y];
    }
}

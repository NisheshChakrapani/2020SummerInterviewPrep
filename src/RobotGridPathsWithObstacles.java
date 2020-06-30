import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RobotGridPathsWithObstacles {
    public static void main(String[] args) {
        Set<Point> obstacles1 = new HashSet<>();
        obstacles1.add(new Point(0, 1));
        System.out.println(numRobotPaths(2, 2, obstacles1));

        Set<Point> obstacles2 = new HashSet<>();
        obstacles2.add(new Point(1, 1));
        System.out.println(numRobotPaths(3, 3, obstacles2));

        Set<Point> obstacles3 = new HashSet<>();
        obstacles3.add(new Point(1, 2));
        System.out.println(numRobotPaths(3, 4, obstacles3));
    }

    public static long numRobotPaths(int rows, int cols, Set<Point> obstacles) {
        long[][] grid = new long[rows][cols];
        return numRobotPaths(0, 0, new long[rows][cols], obstacles);
    }

    private static long numRobotPaths(int x, int y, long[][] grid, Set<Point> obstacles) {
        if (obstacles.contains(new Point(x, y))) return 0;
        if (x == grid.length-1 && y == grid[0].length-1) return 1;
        if (x >= grid.length || y >= grid[0].length) return 0;

        long pathsDown, pathsRight;
        if (x+1 < grid.length && grid[x+1][y] != 0) pathsDown = grid[x+1][y];
        else pathsDown = numRobotPaths(x+1, y, grid, obstacles);
        if (y+1 < grid[0].length && grid[x][y+1] != 0) pathsRight = grid[x][y+1];
        else pathsRight = numRobotPaths(x, y+1, grid, obstacles);

        grid[x][y] = pathsDown + pathsRight;
        return grid[x][y];
    }
}

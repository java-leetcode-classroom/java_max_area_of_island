import java.util.HashSet;
import java.util.Objects;

public class Solution {
    static class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return row == pair.row && col == pair.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
    public int maxAreaOfIsland(int[][] grid) {
        int area = 0;
        int ROW = grid.length;
        int COL = grid[0].length;
        HashSet<Pair> visit = new HashSet<>();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                int total = dfsCount(grid, row, col, visit);
                if (total > area) {
                    area = total;
                }
            }
        }
        return area;
    }
    public int dfsCount(int [][]grid, int row, int col, HashSet<Pair> visit)  {
        int ROW = grid.length;
        int COL = grid[0].length;
        Pair cell = new Pair(row, col);
        if (row < 0 || row >= ROW || col < 0 || col >= COL || grid[row][col] == 0 || visit.contains(cell)) {
            return 0;
        }
        visit.add(cell);
        return 1 + dfsCount(grid,row-1, col, visit)+ dfsCount(grid,row+1, col, visit) +
                dfsCount(grid,row, col+1, visit) + dfsCount(grid,row, col-1, visit);
    }
}

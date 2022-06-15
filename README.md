# java_max_area_of_island

You are given an `m x n` binary matrix `grid`. An island is a group of `1`'s (representing land) connected **4-directionally** (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The **area** of an island is the number of cells with a value `1` in the island.

Return *the maximum **area** of an island in* `grid`. If there is no island, return `0`.

## Examples

**Example 1:**

![https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg](https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg)

```
Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

```

**Example 2:**

```
Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0

```

**Constraints:**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 50`
- `grid[i][j]` is either `0` or `1`.

## 解析

這題與 [200. Number of Islands](https://www.notion.so/200-Number-of-Islands-d899c8c1616c42e6840565875dba06c9) 類似

不同的是這次找出最大的 island 面積，也就是連結 cell 的個數

題目給定一個 m by n 的 0-1 矩陣 grid , 1 代表陸地，0代表海洋

定義水平或垂直方向都是 1 的 cell 是相連的

這些連結的 cell 所形成的群叫作 island

求最大的 island 所包含的 cell 個數

直觀的作法是透過 dfs 來累計 cell 個數

對於 grid 上的每一個值為 1 的 cell 用 dfs 去尋找相連的 cell 並且累計

就是以下個關係式

dfs(row, col) = grid[row][col] + dfs(row-1, col) + dfs(row+1, col) + dfs(row, col - 1) + dfs(row, col+1)

要注意的是已經累加過的cell 不能重複加，所以需要一個 hashset 去做紀錄

![](https://i.imgur.com/6SihfLS.png)

## 程式碼
```java
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

```
## 困難點

1. 理解如何找尋連結的 cell

## Solve Point

- [x]  Understand how to calculate connected area
- [x]  Use HashMap to reduce TimeComplexity
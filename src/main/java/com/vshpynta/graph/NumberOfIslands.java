package com.vshpynta.graph;

import java.util.Deque;
import java.util.LinkedList;

//200. Number of Islands: https://leetcode.com/problems/number-of-islands/description/
public class NumberOfIslands {

    private static final int[][] ADJACENT_CELLS = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}; //left, right, top, bottom

    // grid = [
    //  ["1","1","1","1","0"],
    //  ["1","1","0","1","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","0","0","0"]
    // ]
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        var rowN = grid.length;
        var colN = grid[0].length;
        var visited = new int[rowN][colN];
        var numIslands = 0;
        for (int rowI = 0; rowI < rowN; rowI++) {
            for (int colI = 0; colI < colN; colI++) {
                if (grid[rowI][colI] == '1' && visited[rowI][colI] == 0) { //if is 'land' and is not visited yet
                    numIslands++;
                    bfsVisitAdjacentLands(grid, visited, rowI, colI);
                } else {
                    visited[rowI][colI] = 1;
                }
            }
        }

        return numIslands;
    }

    private static void bfsVisitAdjacentLands(char[][] grid, int[][] visited, int rowIndex, int colIndex) {
        Deque<int[]> landsQueue = new LinkedList<>();
        landsQueue.push(new int[]{rowIndex, colIndex});
        while (!landsQueue.isEmpty()) {
            var land = landsQueue.pop();
            var rowI = land[0];
            var colI = land[1];

            visited[rowI][colI] = 1;
            addAdjacentLands(landsQueue, grid, visited, rowI, colI);
        }
    }

    private static void addAdjacentLands(Deque<int[]> landsQueue,
                                         char[][] grid,
                                         int[][] visited,
                                         int rowIndex,
                                         int colIndex) {
        var gridRowsN = grid.length;
        var gridColsN = grid[0].length;

        for (int[] adjacentCell : ADJACENT_CELLS) {
            var rowI = rowIndex + adjacentCell[0];
            var colI = colIndex + adjacentCell[1];
            if (rowI >= 0 && rowI < gridRowsN && colI >= 0 && colI < gridColsN) { //is inside grid
                if (visited[rowI][colI] == 0 && grid[rowI][colI] == '1') { // is not visited land
                    landsQueue.push(new int[]{rowI, colI}); //add not visited adjacent land
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '0'},
                {'1', '0', '1'}
        }));
    }
}

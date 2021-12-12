/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all 
four edges of the grid are all surrounded by water.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

class Solution {
    // We'll use this array to determine which nodes belong to an island we've already visited
    private char[][] visited; 
    private int numRows;
    private int numCols;
    
    public int numIslands(char[][] grid) {
        int numIslands = 0;
        numRows = grid.length;
        numCols = grid[0].length;
        
        // If the map doesn't exist or it's empty, then it can't have any islands
        if(grid == null || grid.length == 0){
            return numIslands;
        }
        
        // Initialize the visited array based on the size of the map
        visited = new char[numRows][numCols];
        
        // Loop through each node. If we find a piece of land that we haven't visited before, then
        // increment the number of islands and visit each piece of land on the island until we find the edges
        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numCols; c++){

                if(grid[r][c] == '1' && visited[r][c] != '1'){
                    numIslands++;
                    exploreIsland(grid, r, c);
                }

                visited[r][c] = '1';
            }
        }
        
        return numIslands;
    }
    
    public void exploreIsland(char[][] grid, int thisRow, int thisCol){
        // We're done exploring if thisRow or thisCol is less than 0, 
        // is equal to the number of rows / cols respectively in the grid, 
        // is water ('0'), 
        // or has already been visited
        if(thisRow < 0 || thisCol < 0 
           || thisRow >= numRows || thisCol >= numCols 
           || grid[thisRow][thisCol] == '0'
           || visited[thisRow][thisCol] == '1'
          ){
            return;
        }
        
        // Flag this piece of land as visited
        visited[thisRow][thisCol] = '1';
        
        // Search up
        exploreIsland(grid, thisRow-1, thisCol);
        // Search down
        exploreIsland(grid, thisRow+1, thisCol);
        // Search left
        exploreIsland(grid, thisRow, thisCol-1);
        // Search right
        exploreIsland(grid, thisRow, thisCol+1);
    }
}
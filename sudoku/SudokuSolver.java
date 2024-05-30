/**
 * 
 *  This is the solver.
 * 
 *  @author: Horia-George Dună
 *   @id: 1949284
 *  @author: Radu-Cristian Sarău
 *   @id: 1939149
 */

public class SudokuSolver {

    /**
     *Initializes the SudokuSolver with the provided SudokuGrid.
     */
    public SudokuSolver(SudokuGrid grid) {
        
    }

    public int solved = 0;
    public int noSolution = 0;
    
    /**
     * Uses a recursive strategy to solve the Sudoku puzzle.
     */
    
     
    public boolean solve(SudokuGrid grid) {

        if (grid.findEmptyCell().x == -1 && grid.findEmptyCell().y == -1) {

            if (solved == 0) {
                grid.print();
            }
            solved++;
            return true;
            
        } else {
            /*
            The solve(SudokuGrid grid) method uses a recursive strategy to find a solution:
            if the recursive call returns true, it has a solution and returns true.
            Otherwise, it undos what was filled in and continues with the next digit.
            */
            boolean ok = false;

            int i;
            int j;
            i = grid.findEmptyCell().x;
            j = grid.findEmptyCell().y;

            
            while (!ok) {
                int oldVal = grid.grid[i][j];
                grid.fillCell(i, j);
                
                /*  If the method "fillCell" doesn't change the grid, it means there is no value 
                 * for the box with coordinates (i, j) for which the grid is solvable. Therefore, 
                 * the method "solve" returns "false".
                 */
                if (grid.grid[i][j] == oldVal) {
                    grid.grid[i][j] = 0;
                    noSolution++;
                    return false;
                }
                
                /*  The method solve is called in order to compute whether the grid is solvable. If
                 * so, the boolean variable ok is assigned the value true so that we know the grid
                 * is solved.
                 */
                if (solve(grid)) {
                    ok = true;
                }

                /*  If the grid is unsolvable and the box at coordinates (i, j) has the highest
                 * value, the box is assgined the value 0 and the program goes back one recursion.
                 */
                if (grid.grid[i][j] == 9 && !ok) {
                    grid.grid[i][j] = 0;
                    return false;
                }
            }

            /* If the grid is solvable, the "solve" method returns "true".
             */
            return true;
        }
      
    }

    public static void main(String[] args) {

        /*
         * Calls solve() to solve the puzzle and prints the solution or a message if no solution is 
         * found
         */
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);

        if (!solver.solve(grid)) {
            System.out.println("No solution found");
        }
    
    }
}

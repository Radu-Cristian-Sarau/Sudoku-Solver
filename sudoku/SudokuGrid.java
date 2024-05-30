import java.awt.Point;

/**
 * Class SudokuGrid. This class retains data about the sudoku grid that has to be solved and 
 * @author Horia-George Dună
 * @id 1949284
 * @author Radu-Cristian Sarău
 * @id 1939149
*/

public class SudokuGrid {
    private static final int SIZE = 9;
    private static final int DIGIT_RANGE = 9;

    public int[][] grid;
    private int rEmpty;
    private int cEmpty; // Coordinates of the last found empty cell

    /**
     *  Initializes the grid and sets rEmpty and cEmpty to -1.
     */
    public SudokuGrid() {

        grid = new int[][] {   
            {0, 9, 0, 7, 3, 0, 4, 0, 0},
            {0, 0, 0, 0, 1, 0, 5, 0, 0},
            {3, 0, 4, 0, 0, 6, 9, 0, 0},
            {0, 0, 0, 0, 0, 2, 6, 4, 0},
            {0, 3, 0, 6, 5, 1, 0, 8, 0},
            {0, 0, 6, 9, 0, 7, 0, 0, 0},
            {5, 8, 2, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 3, 0, 2, 5},
            {6, 0, 3, 0, 7, 0, 8, 0, 0} 
        };

        rEmpty = -1;
        cEmpty = -1;
    }

    /**
     * Creates a copy of the SudokuGrid and returns it.
     */
    public SudokuGrid copy() {
        // This is a new grid that the current grid will be copied into.
        SudokuGrid newGrid = new SudokuGrid(); 
        newGrid = this;
        return newGrid;
    }

    /**
     * Finds the next empty cell in reading order and returns its coordinates as a Point.
     */
    public Point findEmptyCell() {
        // Boolean variable expressing whether the next empty cell is found or not.
        boolean foundEmptyCell = false;
        // Variable that points to the next empty cell. returns {-1, -1} if empty cell not found.
        Point emptyCell = new Point();

        rEmpty = -1;
        cEmpty = -1;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    foundEmptyCell = true;
                    rEmpty = i;
                    cEmpty = j;
                    break;
                }
            }
            if (foundEmptyCell) {
                break;
            }
        }
        
        emptyCell.x = rEmpty;
        emptyCell.y = cEmpty;

        return emptyCell;
    }

    /**
     * Prints the Sudoku grid.
     */
    public void print() {
        String border = "+-----------------+";
        int i;
        int j;
        
        System.out.println(border);
        for (i = 0; i < SIZE; i++) {
            for (j = 0; j < SIZE; j++) {
                if (j == 0) {
                    System.out.print("|" + grid[i][j] + " ");
                } else if ((j + 1) % 3 == 0) {
                    System.out.print(grid[i][j] + "|");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println(border);
            }
        }

    }

    /**
     * Fills the cell at row r and column c with a number that doesn't create a conflict.
     * @param r is the row of the cell we want to fill
     * @param c is the column of the cell we want to print
     */
    public void fillCell(int r, int c) {
        
        for (int i = grid[r][c] + 1; i <= DIGIT_RANGE; i++) {
            if  (!givesConflict(r, c, i)) {
                this.grid[r][c] = i;
                break;
            }
        }
        
    }

    /**
     * Checks if filling the number d in the cell at row r and column c causes a conflict by using
     * methods rowConflict, colConflict, and boxConflict.
     * @param r is the row that is checked for conflict
     * @param c is the column checked for conflict
     * @param d is the number we want to put at coordinates 'r' and 'c' in the grid
     */
    public boolean givesConflict(int r, int c, int d) {
        
        if (rowConflict(r, d) || colConflict(c, d) || boxConflict(r, c, d)) {
            return true;
        }
        return false;
    }

    private boolean rowConflict(int r, int d) {
        // Checks if there is a conflict in the row r when filling the number d
        for (int j = 0; j < SIZE; j++) {
            if (d == grid[r][j]) {
                return true;
            }
        }
        return false;
    }

    private boolean colConflict(int c, int d) {
        // Checks if there is a conflict in the column c when filling the number d
        for (int i = 0; i < SIZE; i++) {
            if (d == grid[i][c]) {
                return true;
            }
        } 
        return false;
    }

    private boolean boxConflict(int r, int c, int d) {
        // Checks if there is a conflict in the 3x3 box containing the cell at row r and column c
        // when filling the number d
        int i;
        int j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (d == grid[(r / 3) * 3 + i][(c / 3) * 3 + j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
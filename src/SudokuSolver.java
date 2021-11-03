public class SudokuSolver
{
    private int[][] board;

    public SudokuSolver(int[][] b)
    {
        board = b;
    }

    public SudokuSolver()
    {
        board = new int[9][9];
    }

    private boolean inRow(int row, int solution)
    {
        for (int i = 0; i < 9; i++)
        {
            if(board[row][i] == solution)
            {
                return true;
            }
        }
        return false;
    }

    private boolean inCol(int col, int solution)
    {
        for (int i = 0; i < 9; i++)
        {
            if(board[i][col] == solution)
            {
                return true;
            }
        }
        return false;
    }

    private boolean inBox(int row, int col, int solution)
    {
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int i = boxRowStart; i < boxRowStart + 3; i++)
        {
            for (int j = boxColStart; j < boxColStart + 3; j++)
            {
                if (board[i][j] == solution)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPossibleSol(int row, int col, int solution)
    {
        return !inRow(row,solution) && !inCol(col,solution) && !inBox(row,col,solution);
    }

    public boolean solve()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if(board[i][j] == 0)
                {
                    for(int solution = 1; solution <= 9; solution++)
                    {
                        if (isPossibleSol(i, j, solution))
                        {
                            board[i][j] = solution;
                            if (solve())
                            {
                                return true;
                            }
                            else
                            {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard()
    {
        for (int i = 0; i < 9; i++)
        {
            if (i % 3 == 0 && i != 0) 
            {
                System.out.println("----------|---------|----------");
            }
            for (int j = 0; j < 9; j++)
            {
                if (j % 3 == 0)
                {
                    System.out.print("|");
                }
                if (board[i][j] == 0)
                {
                    System.out.print(" " + "-" + " ");
                }
                else
                {
                    System.out.print(" " + board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public int[][] getBoard()
    {
        return board;
    }
}

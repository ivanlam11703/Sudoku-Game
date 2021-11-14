import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Board 
{
    private SudokuSolver solver;
    private JFrame boardFrame;
    private JMenuBar menubar;
    private JMenu file;
    private JMenuItem solve;
    private Grid g;
    private boolean successfulLoad;
    
    public Board(String filename)
    {
        solver = loadGame(filename);
        if(successfulLoad)
        {
            g = new Grid(filename);
            System.out.println("Board loaded:\n");
            solver.printBoard();
        }
        else
        {
            System.out.println("Failed to load grid.");
            System.exit(0);
        }

        boardFrame = new JFrame("Sudoku");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setSize(1000,1000);

        menubar = new JMenuBar();
        file = new JMenu("File");
        menubar.add(file);
        solve = new JMenuItem("Solve");
        file.add(solve);

        boardFrame.setJMenuBar(menubar);
        boardFrame.setVisible(true);
        boardFrame.getContentPane().add(g);

        createSolveActionListener();
    }

    private void createSolveActionListener()
    {
        solve.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                solver.printBoard();
                System.out.println("\nAttempting to solve...\n");
                if (solver.solve())
                {
                        System.out.println("Solution found!\n");
                        solver.printBoard();
                        g.paintSolution();               
                }
                else
                {
                    System.out.println("No solution could be found for this board.");
                }
            }
        });
    }

    private int[][] convertTextTo2DArr(String inputFilename)
    {
        int[][] newBoard = new int[9][9];
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(inputFilename));
            for (int i = 0; i < 9; i++)
            {
                String[] strArr = br.readLine().split(",");
                for (int j = 0; j < strArr.length; j++)
                {
                    newBoard[i][j] = Integer.parseInt(strArr[j]);
                }
            }
            br.close();
            successfulLoad = true;
            return newBoard;
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(boardFrame, "Failed to load file.");
            successfulLoad = false;
        }
        return newBoard;
    }

    private SudokuSolver loadGame(String inputFilename)
    {
        SudokuSolver newSolver = new SudokuSolver(convertTextTo2DArr(inputFilename));
        return newSolver;
    }
}

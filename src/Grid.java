import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class Grid extends JPanel
{
    private JTextField[][] textFields;
    private int[][] numbers, solution;
    private SudokuSolver solver;

    public Grid(String filename)
    {
        numbers = convertTextTo2DArr(filename);
        solution = convertTextTo2DArr(filename);
        solver = new SudokuSolver(solution);
        if(solver.solve());
        {
            solution = solver.getBoard();
        }

        textFields = new JTextField[9][9];
        setLayout(new GridLayout(9,9));
        createTextFields();
    }

    private void createTextFields()
    {
        BoxPressListener listener = new BoxPressListener(this);
        Dimension fieldSize = new Dimension(100,100);
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                JTextField textField = new JTextField();
                textField.setOpaque(true);
                textField.setFont(new Font("Times New Roman", Font.PLAIN, 40));
                if(numbers[i][j] == 0)
                {
                    textField.setText("-");
                }
                else
                {
                    textField.setText(Integer.toString(numbers[i][j]));
                }
                textField.addMouseListener(listener);
                textField.setPreferredSize(fieldSize);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                textField.setBackground(Color.WHITE);
                textField.setEditable(false);
                if (i == 0)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.BLACK));
                }
                if (i == 8)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                }
                if (j == 0)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.BLACK));
                }
                if (j == 8)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
                }
                if(i == 0 && j == 0)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.BLACK));
                }
                if (i == 0 && j == 8)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(4, 1, 1, 4, Color.BLACK));
                }
                if (i == 8 && j == 0)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 4, 4, 1, Color.BLACK));
                }
                if (i == 8 && j == 8)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                }
                if ((i != 0 && i != 8) && (j == 2 || j == 5))
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
                }
                if (i == 0 && (j == 2 || j == 5))
                {
                    textField.setBorder(BorderFactory.createMatteBorder(4, 1, 1, 4, Color.BLACK));
                }
                if (i == 8 && (j == 2 || j == 5))
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                }
                if((i == 2 || i == 5) && (j != 0 && j != 8))
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                }
                if ((i == 2 || i == 5) && (j == 2 || j == 5 || j == 8))
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                }
                if ((i == 2 || i == 5) && j == 0)
                {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 4, 4, 1, Color.BLACK));
                }
                add(textField);
                textFields[i][j] = textField;
            }
        }
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
            return newBoard;
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        return newBoard;
    }

    public void textFieldPressed(JTextField textField)
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (textField == textFields[i][j])
                {
                    System.out.println("Pressed [" + i + "] [" + j + "]");
                    System.out.println("Number in box: " + textFields[i][j].getText());
                    if (textField.getText().equals("-"))
                    {
                        textField.setBackground(Color.LIGHT_GRAY);
                        try
                        {
                            String in = JOptionPane.showInputDialog("Input a number.");
                            if (in == null || in.equals(""))
                            {
                                textFields[i][j].setBackground(Color.WHITE);
                            }
                            int input = Integer.parseInt(in);
                            if(checkSolution(input,i,j))
                            {
                                textFields[i][j].setText(Integer.toString(input));
                                numbers[i][j] = input;
                                textFields[i][j].setBackground(Color.WHITE);
                                if (Arrays.deepEquals(solution, numbers))
                                {
                                    JOptionPane.showMessageDialog(null,"You've completed this puzzle!");
                                }
                            }
                            else
                            {
                                textFields[i][j].setBackground(Color.WHITE);
                            }
                        }
                        catch(NumberFormatException e)
                        {
                            e.printStackTrace();
                        }  
                    }
                }
            }
        }
    }

    private boolean checkSolution(int input, int row, int col)
    {
        if (input == solution[row][col])
        {
            return true;
        }
        return false;
    }

    public void paintSolution()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if(numbers[i][j] == 0)
                {
                    textFields[i][j].setText(Integer.toString(solution[i][j]));
                    textFields[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }
}
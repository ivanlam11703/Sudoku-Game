public class Game
{
    public static void main(String[] args) 
    {
        if (args.length != 1)
        {
            System.out.println("Format is \"java Board nameOfSudokuTxtFile.txt");
            System.exit(0);
        }
        new Board("C:\\Users\\candy\\Desktop\\Java Stuff\\Sudoku-Game\\files\\" + args[0]);
    }
}
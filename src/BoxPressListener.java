import java.awt.event.*;
import javax.swing.*;

public class BoxPressListener extends MouseAdapter
{
    private Grid grid;

    public BoxPressListener(Grid g)
    {
        grid = g;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            grid.textFieldPressed((JTextField)e.getSource());
        }
    }
}

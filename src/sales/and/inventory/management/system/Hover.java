package sales.and.inventory.management.system;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Hover extends JButton implements MouseListener
{

    private Cursor defaultCursor;
    private Cursor handCursor;

    public Hover()
    {
        super();

        init();
    }

    public Hover(Action a)
    {
        super(a);

        init();
    }

    public Hover(Icon icon)
    {
        super(icon);

        init();
    }

    public Hover(String text, Icon icon)
    {
        super(text, icon);

        init();
    }

    public Hover(String text)
    {
        super(text);

        init();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        this.setCursor(handCursor);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        this.setCursor(defaultCursor);
    }

    private void init()
    {
        defaultCursor = this.getCursor();
        handCursor = new Cursor(Cursor.HAND_CURSOR);

        addMouseListener(this);
    }

}
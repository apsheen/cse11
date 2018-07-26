import objectdraw.*;
import java.awt.Color;

public class CrazyOrb extends ActiveObject
{
    //canvas
    private DrawingCanvas canvas;

    //store the lines, starting size
    private Line hline, vline;
    private double objSize;

    //how long to delay between grow/shrink - milleseconds
    private static int DELAY_TIME = 75;

    //object parts
    private FilledOval filled;
    private FramedOval framed;

    //shrink or grow? 1 - grow; -1 - shrink
    private int filledchange = 0;
    private int framedchange = 0;
    
    public CrazyOrb(double xLoc, double yLoc, double size, DrawingCanvas canvas, Line hLine, Line vLine)
    {
        filled = new FilledOval(xLoc, yLoc, size, size, canvas);
        framed = new FramedOval(xLoc, yLoc, size, size, canvas);

        //colors for upper left quadrant
        if(((xLoc + (size / 2)) < vLine.getStart().getX()) && ((yLoc + (size / 2)) < hLine.getStart().getY()))
        {
            filled.setColor(Color.CYAN);
            framed.setColor(Color.BLUE);
        }

        //colors for upper right quadrant
        if(((xLoc + (size / 2)) > vLine.getStart().getX()) && ((yLoc + (size / 2)) < hLine.getStart().getY()))
        {
            filled.setColor(Color.MAGENTA);
            framed.setColor(Color.PINK);
        }
        
        //colors for lower left quadrant
        if(((xLoc + (size / 2)) < vLine.getStart().getX()) && ((yLoc + (size / 2)) > hLine.getStart().getY()))
        {
            filled.setColor(Color.YELLOW);
            framed.setColor(Color.GREEN);
        }

        //colors for lower right quadrant
        if(((xLoc + (size / 2)) > vLine.getStart().getX()) && ((yLoc + (size / 2)) > hLine.getStart().getY()))
        {
            filled.setColor(Color.BLACK);
            framed.setColor(Color.GRAY);
        }

        filledchange = 1;
        framedchange = -1;

        objSize = size;
        hline = hLine;
        vline = vLine;

        start();
    }

    public void run() 
    {
        while(true)
        {
            filled.move(-1 * filledchange, -1 * filledchange);
            filled.setSize(filled.getWidth() + (2 * filledchange), filled.getHeight() + (2 * filledchange));

            framed.move(-1 * framedchange, -1 * framedchange);
            framed.setSize(framed.getWidth() + (2 * framedchange), framed.getHeight() + (2 * framedchange));

            if(framed.getWidth() >= (1.5 * objSize))
            {
                framedchange = -1;
                filledchange = 1;
            }

            if(framed.getWidth() <= (0.5 * objSize))
            {
                framedchange = 1;
                filledchange = -1;
            }

            //colors for upper left quadrant
            if(((filled.getX() + (objSize / 2)) < vline.getStart().getX()) && ((filled.getY() + (objSize / 2)) < hline.getStart().getY()))
            {
                filled.setColor(Color.CYAN);
                framed.setColor(Color.BLUE);
            }

            //colors for upper right quadrant
            if(((filled.getX() + (objSize / 2)) > vline.getStart().getX()) && ((filled.getY() + (objSize / 2)) < hline.getStart().getY()))
            {
                filled.setColor(Color.MAGENTA);
                framed.setColor(Color.PINK);
            }
            
            //colors for lower left quadrant
            if(((filled.getX() + (objSize / 2)) < vline.getStart().getX()) && ((filled.getY() + (objSize / 2)) > hline.getStart().getY()))
            {
                filled.setColor(Color.YELLOW);
                framed.setColor(Color.GREEN);
            }

            //colors for lower right quadrant
            if(((filled.getX() + (objSize / 2)) > vline.getStart().getX()) && ((filled.getY() + (objSize / 2)) > hline.getStart().getY()))
            {
                filled.setColor(Color.BLACK);
                framed.setColor(Color.GRAY);
            }
            
            pause(DELAY_TIME);
        }
    }
}
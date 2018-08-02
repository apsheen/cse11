import objectdraw.*;
import java.awt.Color;

public class EC_CrazyOrb extends ActiveObject
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

    //cycle toggle - toggle is "global"
    private static boolean toggle;
    private int cycle; //1 = blue, 2 = pink, 3 = yellow, 4 = black
    
    //constructor
    public EC_CrazyOrb(double xLoc, double yLoc, double size, DrawingCanvas canvas, Line hLine, Line vLine, boolean cycleMode)
    {
        //if updating toggle (mouse enter), size will be 0, do not create a new orb
        if(size != 0)
        {
            filled = new FilledOval(xLoc, yLoc, size, size, canvas);
            framed = new FramedOval(xLoc, yLoc, size, size, canvas);

            //colors for upper left quadrant
            if(((xLoc + (size / 2)) < vLine.getStart().getX()) && ((yLoc + (size / 2)) < hLine.getStart().getY()))
            {
                filled.setColor(Color.CYAN);
                framed.setColor(Color.BLUE);
                cycle = 1;
            }

            //colors for upper right quadrant
            if(((xLoc + (size / 2)) > vLine.getStart().getX()) && ((yLoc + (size / 2)) < hLine.getStart().getY()))
            {
                filled.setColor(Color.MAGENTA);
                framed.setColor(Color.PINK);
                cycle = 2;
            }
            
            //colors for lower left quadrant
            if(((xLoc + (size / 2)) < vLine.getStart().getX()) && ((yLoc + (size / 2)) > hLine.getStart().getY()))
            {
                filled.setColor(Color.YELLOW);
                framed.setColor(Color.GREEN);
                cycle = 3;
            }

            //colors for lower right quadrant
            if(((xLoc + (size / 2)) > vLine.getStart().getX()) && ((yLoc + (size / 2)) > hLine.getStart().getY()))
            {
                filled.setColor(Color.BLACK);
                framed.setColor(Color.GRAY);
                cycle = 0;
            }

            start();
        }

        //initialize thread's variables, update variables
        filledchange = 1;
        framedchange = -1;
        cycle = 0;
        objSize = size;
        hline = hLine;
        vline = vLine;
        toggle = cycleMode;
    }

    //thread
    public void run() 
    {
        //run forever
        while(true)
        {
            //cycle colors mode
            if(toggle == true)
            {
                //keep centered
                filled.move(-1 * filledchange, -1 * filledchange);
                filled.setSize(filled.getWidth() + (2 * filledchange), filled.getHeight() + (2 * filledchange));
                framed.move(-1 * framedchange, -1 * framedchange);
                framed.setSize(framed.getWidth() + (2 * framedchange), framed.getHeight() + (2 * framedchange));

                //when to shrink/grow, change color as needed
                if((framed.getWidth() >= (1.5 * objSize)) || (framed.getWidth() <= (0.5 * objSize)))
                {
                    if(framed.getWidth() >= (1.5 * objSize))
                    {
                        framedchange = -1;
                        filledchange = 1;
                    }

                    else if(framed.getWidth() <= (0.5 * objSize))
                    {
                        framedchange = 1;
                        filledchange = -1;
                    }

                    if(cycle == 0)
                    {
                        filled.setColor(Color.CYAN);
                        framed.setColor(Color.BLUE);
                    }

                    else if(cycle == 1)
                    {
                        filled.setColor(Color.MAGENTA);
                        framed.setColor(Color.PINK);
                    }

                    else if(cycle == 2)
                    {
                        filled.setColor(Color.YELLOW);
                        framed.setColor(Color.GREEN);
                    }

                    else if(cycle == 3)
                    {
                        filled.setColor(Color.BLACK);
                        framed.setColor(Color.GRAY);
                    }

                    cycle = (cycle + 1) % 4;
                }
            }

            //not cycling colors
            else if(toggle == false)
            {
                //keep centered
                filled.move(-1 * filledchange, -1 * filledchange);
                filled.setSize(filled.getWidth() + (2 * filledchange), filled.getHeight() + (2 * filledchange));
                framed.move(-1 * framedchange, -1 * framedchange);
                framed.setSize(framed.getWidth() + (2 * framedchange), framed.getHeight() + (2 * framedchange));

                //when to shrink
                if(framed.getWidth() >= (1.5 * objSize))
                {
                    framedchange = -1;
                    filledchange = 1;
                }

                //when to grow
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
                    cycle = 1;
                }

                //colors for upper right quadrant
                if(((filled.getX() + (objSize / 2)) > vline.getStart().getX()) && ((filled.getY() + (objSize / 2)) < hline.getStart().getY()))
                {
                    filled.setColor(Color.MAGENTA);
                    framed.setColor(Color.PINK);
                    cycle = 2;
                }
                
                //colors for lower left quadrant
                if(((filled.getX() + (objSize / 2)) < vline.getStart().getX()) && ((filled.getY() + (objSize / 2)) > hline.getStart().getY()))
                {
                    filled.setColor(Color.YELLOW);
                    framed.setColor(Color.GREEN);
                    cycle = 3;
                }

                //colors for lower right quadrant
                if(((filled.getX() + (objSize / 2)) > vline.getStart().getX()) && ((filled.getY() + (objSize / 2)) > hline.getStart().getY()))
                {
                    filled.setColor(Color.BLACK);
                    framed.setColor(Color.GRAY);
                    cycle = 0;
                }
            }

            pause(DELAY_TIME); //don't grow or shrink too fast, delay thread
        }
    }
}
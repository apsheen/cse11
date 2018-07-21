import Acme.*;
import objectdraw.*;
import java.awt.Color;

public class FlippingMickey extends WindowController 
{
    //to create the window
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 750;

    //positioning of the instructions
    private static final int INSTR1_X = 50;
    private static final int INSTR1_Y = 50;
    private static final int INSTR2_X = INSTR1_X;
    private static final int INSTR2_Y = INSTR1_Y + 20;
    private static final int INSTR3_X = INSTR1_X;
    private static final int INSTR3_Y = INSTR2_Y + 20;

    //how long to click and hold in order for it to flip
    private static final int FLIP_PRESS_THRESHOLD = 500;

    //instructions
    private static final String INSTR_LINE_1 =
            "Click to display a Mickey silhouette centered at the mouse click.";
    
    private static final String INSTR_LINE_2 =
            "Mouse press in any part of the image and drag to move image around.";
    
    private static final String INSTR_LINE_3 =
            "Mouse click for more than 0.5 seconds in left ear to flip left, " +
            "right ear to flip right, face to flip up/down.";
    
    private Text instruction1;
    private Text instruction2;
    private Text instruction3;
    
    //import Mickey and Timer
    private Mickey mickey;
    private Timer timer = new Timer();

    //is the mouse click inside mickey?
    private boolean grabbed = false;

    //last position that it was clicked at
    private Location lastPos;

    //did the mouse exit the window?
    private boolean reset = false;

    //if need to create new mickey / is there a mickey to delete?
    private boolean mickeyReset = true;
    private boolean mickeyCreated = false;

    //show instructions
    public void begin()
    {
        instruction1 = new Text(INSTR_LINE_1, INSTR1_X, INSTR1_Y, canvas);
        instruction2 = new Text(INSTR_LINE_2, INSTR2_X, INSTR2_Y, canvas);
        instruction3 = new Text(INSTR_LINE_3, INSTR3_X, INSTR3_Y, canvas);
    }

    //make sure the instructions are gone, create a new mickey if needed
    //if clicked for more than half a second in one part, flip as needed - see Mickey.java
    public void onMouseClick(Location point) 
    {
        instruction1.setText(" ");
        instruction2.setText(" ");
        instruction3.setText(" ");

        if(mickeyReset == true)
        {
            mickey = new Mickey(point, canvas);

            mickeyReset = false;
            mickeyCreated = true;
        }

        else if(mickeyCreated == true)
        {
            if(timer.elapsedMilliseconds() >= 500)
            {
                if(mickey.inFace(point))
                {
                    mickey.flipUpDown();
                }

                else if(mickey.inLeftEar(point))
                {
                    mickey.flipLeft();
                }

                else if(mickey.inRightEar(point))
                {
                    mickey.flipRight();
                }
            }
        }
    }

    //see if mickey was grabbed, reset timer for flipping
    public void onMousePress(Location point)
    {
        if(mickeyCreated = true)
        {
            if(mickey.contains(point))
            {
                grabbed = true;
            }

            else
            {
                grabbed = false;
            }
        }

        lastPos = point;

        timer.reset();
    }

    //if grabbed, drag figure
    public void onMouseDrag(Location point)
    {
        if(grabbed)
        {
            mickey.move(point.getX() - lastPos.getX(), point.getY() - lastPos.getY());
        }

        lastPos = point;
    }

    //remove everything from the canvas
    public void onMouseExit(Location point)
    {
        mickeyCreated = true;

        if(reset == false)
        {
            instruction1.setText(" ");
            instruction2.setText(" ");
            instruction3.setText(" ");
            reset = true;
        }

        if(mickeyCreated == true)
        {
            mickey.removeFromCanvas();
            mickeyCreated = false;
        }
    }

    //reset everything, put the instructions
    public void onMouseEnter(Location point)
    {
        if(reset == true)
        {
            instruction1.setText(INSTR_LINE_1);
            instruction2.setText(INSTR_LINE_2);
            instruction3.setText(INSTR_LINE_3);

            reset = false;
            mickeyReset = true;
            mickeyCreated = false;
        }
    }
    
    //set up Acme mainframe
    public static void main(String[] args) 
    {
        new Acme.MainFrame(new FlippingMickey(), args, FRAME_WIDTH, FRAME_HEIGHT);
    } 
}
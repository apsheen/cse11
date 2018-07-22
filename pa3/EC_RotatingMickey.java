import Acme.*;
import objectdraw.*;
import java.awt.Color;

public class EC_RotatingMickey extends WindowController 
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
    private static final int INSTR4_X = INSTR1_X;
    private static final int INSTR4_Y = INSTR3_Y + 20;

    //how long to click and hold in order for it to flip
    private static final int FLIP_PRESS_THRESHOLD = 500;

    //instructions
    private static final String INSTR_LINE_1 =
            "Click to display a Mickey silhouette centered at the mouse click.";
    
    private static final String INSTR_LINE_2 =
            "Mouse press in any part of the image and drag to move image around.";
    
    private static final String INSTR_LINE_3 =
            "Mouse click for more than 0.5 seconds in left ear to rotate left, right ear to rotate right.";

    private static final String INSTR_LINE_4 = 
            "Mouse click for more than 0.5 seconds in face to center image on canvas upright.";
    
    private Text instruction1;
    private Text instruction2;
    private Text instruction3;
    private Text instruction4;
    
    //import Mickey and Timer
    private EC_Mickey mickey;
    private Timer timer = new Timer();

    //is the mouse click inside mickey?
    private boolean grabbed = false;

    //last position that it was clicked at
    private Location lastPos;

    private Location location;

    //did the mouse exit the window?
    private boolean reset = false;

    //if need to create new mickey / is there a mickey to delete?
    private boolean mickeyReset = true;
    private boolean mickeyCreated = false;

    //TODO - additional variables you might need and - skeletons

    //show instructions
    public void begin()
    {
        instruction1 = new Text(INSTR_LINE_1, INSTR1_X, INSTR1_Y, canvas);
        instruction2 = new Text(INSTR_LINE_2, INSTR2_X, INSTR2_Y, canvas);
        instruction3 = new Text(INSTR_LINE_3, INSTR3_X, INSTR3_Y, canvas);
        instruction4 = new Text(INSTR_LINE_4, INSTR4_X, INSTR4_Y, canvas);
    }

    //make sure the instructions are gone, create a new mickey if needed
    //if clicked for more than half a second in one part, rotate as needed - see EC_Mickey.java
    public void onMouseClick(Location point) 
    {
        instruction1.setText(" ");
        instruction2.setText(" ");
        instruction3.setText(" ");
        instruction4.setText(" ");

        if(mickeyReset == true)
        {
            mickey = new EC_Mickey(point, canvas);

            mickeyReset = false;
            mickeyCreated = true;
        }

        else if(mickeyCreated == true)
        {
            if(timer.elapsedMilliseconds() >= 500)
            {
                if(mickey.inFace(point))
                {
                    mickey.centerFace(FRAME_WIDTH, FRAME_HEIGHT);
                }

                else if(mickey.inLeftEar(point))
                {
                    mickey.turnLeft();
                }

                else if(mickey.inRightEar(point))
                {
                    mickey.turnRight();
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
        if(reset == false)
        {
            instruction1.setText(" ");
            instruction2.setText(" ");
            instruction3.setText(" ");
            instruction4.setText(" ");
            reset = true;

            if(mickeyCreated == true)
            {
                mickey.removeFromCanvas();
                mickeyCreated = false;
            }
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
            instruction4.setText(INSTR_LINE_4);

            reset = false;
            mickeyReset = true;
            mickeyCreated = false;
        }
    }
    
    //set up Acme mainframe
    public static void main(String[] args) 
    {
        new Acme.MainFrame(new EC_RotatingMickey(), args, FRAME_WIDTH, FRAME_HEIGHT);
    } 
}
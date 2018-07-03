import Acme.*;
import objectdraw.*;
import java.awt.Color;

public class DraggingMickey extends WindowController
{
    //position of instructions
    private static final int INSTR1_X = 50;
    private static final int INSTR1_Y = 50;
    private static final int INSTR2_X = INSTR1_X;
    private static final int INSTR2_Y = INSTR1_Y + 20;
    
    //mickey's positioning
    private static final int FACE_RADIUS = 50;
    private static final int EAR_RADIUS = 30;
    private static final int EAR_OFFSET = 50; // Center of each ear is this offset up and over (x and y) from center of face.
    
    //mickey's parts
    private FilledOval head;
    private FilledOval leftEar;
    private FilledOval rightEar;

    //instructions
    private static final String INSTR1_TEXT = "Click to display a Mickey silhouette centered at the mouse click.";
    private static final String INSTR2_TEXT = "Mouse press in any part of the image and drag to move image around.";
    private Text instruction1;
    private Text instruction2;

    //to create the window
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 750;

    //was Mickey grabbed?
    private boolean grabbed = false;

    //last position of mouse
    private Location lastPos;

    //did the mouse exit the window?
    private boolean reset = false;

    //if need to create new mickey / is there a mickey to delete?
    private boolean mickeyReset = true;
    private boolean mickeyCreated = false;

    //origin of ears
    private Location right;
    private Location left;

    //origin of face
    private Location face;

    //start by showing instructions
    public void begin() 
    {
        instruction1 = new Text(INSTR1_TEXT, INSTR1_X, INSTR1_Y, canvas);
        instruction2 = new Text(INSTR2_TEXT, INSTR2_X, INSTR2_Y, canvas);
    }

    //if Mickey has been drawn, check to see if in the sillohette, make sure text is "gone"
    public void onMousePress(Location point)
    {
        instruction1.setText(" ");
        instruction2.setText(" ");

        if(mickeyReset == true)
        {
            face = new Location(point.getX() - (FACE_RADIUS / 2), point.getY() - (FACE_RADIUS / 2));
            right = new Location(face.getX() + ((EAR_OFFSET) - (EAR_RADIUS / 2)), face.getY() - (EAR_RADIUS / 2));
            left = new Location(face.getX() - (EAR_RADIUS / 2), face.getY() - (EAR_RADIUS / 2));

            leftEar = new FilledOval(left, EAR_RADIUS, EAR_RADIUS, canvas);
            rightEar = new FilledOval(right, EAR_RADIUS, EAR_RADIUS, canvas);
            head = new FilledOval(face, FACE_RADIUS, FACE_RADIUS, canvas);

            mickeyReset = false;
            mickeyCreated = true;
        }

        else
        {
            if((head.contains(point) == true) || (rightEar.contains(point) == true) || (leftEar.contains(point) == true))
            {
                grabbed = true;
            }

            else
            {
                grabbed = false;
            }
        }

        lastPos = point;
    }

    //dragging, turn RGB as doing so
    public void onMouseDrag(Location point)
    {
        if(grabbed) 
        {
            leftEar.move(point.getX() - lastPos.getX(), point.getY() - lastPos.getY());
            leftEar.setColor(Color.RED);
            rightEar.move(point.getX() - lastPos.getX(), point.getY() - lastPos.getY());
            rightEar.setColor(Color.GREEN);
            head.move(point.getX() - lastPos.getX(), point.getY() - lastPos.getY());
            head.setColor(Color.BLUE);
        }

        lastPos = point;
    }

    //return figure to black
    public void onMouseRelease(Location point)
    { 
        leftEar.setColor(Color.BLACK);
        rightEar.setColor(Color.BLACK);
        head.setColor(Color.BLACK);
    }

    //remove entire figure/reset
    public void onMouseExit(Location point)
    {
        if(reset == false)
        {
            instruction1.setText(" ");
            instruction2.setText(" ");
            reset = true;
        }

        if(mickeyCreated == true)
        {
            head.removeFromCanvas();
            rightEar.removeFromCanvas();
            leftEar.removeFromCanvas();
        }
    }

    //show instructions
    public void onMouseEnter(Location point)
    {
        if(reset == true)
        {
            instruction1.setText(INSTR1_TEXT);
            instruction2.setText(INSTR2_TEXT);
            reset = false;
            mickeyReset = true;
            mickeyCreated = false;
        }
    }

    //Acme canvas
    public static void main(String[] args) 
    {
        new Acme.MainFrame(new DraggingMickey(), args, FRAME_WIDTH, FRAME_HEIGHT);
    }
}
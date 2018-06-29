import Acme.*;
import objectdraw.*;

public class DraggingMickey extends WindowController
{
    private static final int INSTR1_X = 50;
    private static final int INSTR1_Y = 50;
    private static final int INSTR2_X = INSTR1_X;
    private static final int INSTR2_Y = INSTR1_Y + 20;

    private static final int FACE_RADIUS = 50;
    private static final int EAR_RADIUS = 30;
    private static final int EAR_OFFSET = 50; // Center of each ear is this offset up and over (x and y) from center of face.
    
    private static final String INSTR1_TEXT = "Click to display a Mickey silhouette centered at the mouse click.";
    private static final String INSTR2_TEXT = "Mouse press in any part of the image and drag to move image around.";

    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 750;

    public void begin() 
    {
        new Text(INSTR1_TEXT, INSTR1_X, INSTR1_Y, canvas);
        new Text(INSTR2_TEXT, INSTR2_X, INSTR2_Y, canvas);
    }

    public static void main(String[] args) 
    {
        new Acme.MainFrame(new DraggingMickey(), args, FRAME_WIDTH, FRAME_HEIGHT);
    }
}
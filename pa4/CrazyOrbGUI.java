import Acme.*;
import objectdraw.*;

public class CrazyOrbGUI extends WindowController
{
    //window dimensions
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    //size of the object
    private static final int OBJ_DIAMETER = 60;

    //lines to divide into four quadrants
    private Line hLine, vLine;
    private Location hStart, hEnd, vStart, vEnd;

    //were any lines grabbed? which ones?
    private boolean hGrabbed, vGrabbed;

    //last point
    private Location lastPos;

    //line positioning (fraction)
    private double hPos = 0.5;
    private double vPos = 0.5;

    //divide screen into four quadrants - two line objects
    public void begin()
    {
        hLine = new Line(0, (canvas.getHeight() / 2), canvas.getWidth(), (canvas.getHeight() / 2), canvas);
        vLine = new Line((canvas.getWidth() / 2), 0, (canvas.getWidth() / 2), canvas.getHeight(), canvas);
        
        hGrabbed = false;
        vGrabbed = false;
    }

    //were the lines grabbed? which ones?
    public void onMousePress(Location point)
    {
        if(hLine.contains(point))
        {
            hGrabbed = true;
        }

        if(vLine.contains(point))
        {
            vGrabbed = true;
        }

        lastPos = point;

        new CrazyOrb(point.getX() - (OBJ_DIAMETER / 2), point.getY() - (OBJ_DIAMETER / 2), OBJ_DIAMETER, canvas, hLine, vLine);
    }

    //move the lines along with the mouse, but cannot go out of the window - 6 pixel threshold
    public void onMouseDrag(Location point)
    {
        if(hGrabbed)
        {
            if((hLine.getStart().getY() >= 6) && (hLine.getEnd().getY() <= (canvas.getHeight() - 6)))
            {
                hLine.move(0, point.getY() - lastPos.getY());
                hPos = hLine.getStart().getY() / canvas.getHeight();
            }
        }

        if(vGrabbed)
        {
            if((vLine.getStart().getX() >= 6) && (vLine.getEnd().getX() <= (canvas.getWidth() - 6)))
            {
                vLine.move(point.getX() - lastPos.getX(), 0);
                vPos = vLine.getStart().getX() / canvas.getWidth();
            }
        }

        lastPos = point;
        hGrabbed = false;
        vGrabbed = false;
    }

    //set up Acme mainframe
    public static void main(String[] args) 
    {
        new Acme.MainFrame(new CrazyOrbGUI(), args, FRAME_WIDTH, FRAME_HEIGHT);
    } 

     //if size of the window is changed, the lines extend to fit
     public void paint(java.awt.Graphics g)
     {
        super.paint(g);
 
        hLine.setStart(0, hPos * canvas.getHeight());
        hLine.setEnd(canvas.getWidth(), hPos * canvas.getHeight());
        vLine.setStart(vPos * canvas.getWidth(), 0);
        vLine.setEnd(vPos * canvas.getWidth(), canvas.getHeight());
     }
}
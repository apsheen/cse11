import objectdraw.*;
import java.awt.Color;

public class Mickey 
{   
    //Mickey's components for build
    private Location face, right, left;
    private FilledOval leftEar, rightEar, head;

    //dimensions of mickey
    private static final int FACE_RADIUS = 50;
    private static final int EAR_RADIUS = 30;
    private static final int EAR_OFFSET = 50;
    
    //canvas
    private DrawingCanvas canvas;

    //was the mouse click in the mickey?
    private boolean grabbed;

    //up is true; down is false
    private boolean upordown;

    //left is true; right is false
    private boolean leftorright;

    //which ear?
    private boolean whichear;

    //constructor
    public Mickey(Location point, DrawingCanvas canvas) 
    {
        face = new Location(point.getX() - (FACE_RADIUS / 2), point.getY() - (FACE_RADIUS / 2));
        right = new Location(face.getX() + ((EAR_OFFSET) - (EAR_RADIUS / 2)), face.getY() - (EAR_RADIUS / 2));
        left = new Location(face.getX() - (EAR_RADIUS / 2), face.getY() - (EAR_RADIUS / 2));

        leftEar = new FilledOval(left, EAR_RADIUS, EAR_RADIUS, canvas);
        leftEar.setColor(Color.RED);
        rightEar = new FilledOval(right, EAR_RADIUS, EAR_RADIUS, canvas);
        rightEar.setColor(Color.GREEN);
        head = new FilledOval(face, FACE_RADIUS, FACE_RADIUS, canvas);
        head.setColor(Color.BLUE);

        upordown = true;
        leftorright = true;
        whichear = true;
    }

    //is the location in the mickey figure
    public boolean contains(Location point) 
    {
        grabbed = false;

        if((head.contains(point) == true) || (rightEar.contains(point) == true) || (leftEar.contains(point) == true))
        {
            grabbed = true;
        }

        return grabbed;
    }

    //is the click in the face part of the mickey figure?
    public boolean inFace(Location point) 
    {
        return head.contains(point);
    }

    //is the click in the left ear of the mickey figure? - which side is the left
    public boolean inLeftEar(Location point) 
    {
        if(leftorright == true)
        {
            whichear = leftEar.contains(point); 
        }

        else if(leftorright == false)
        {
            whichear = rightEar.contains(point); 
        }

        return whichear;
    }

    //is the click in the right ear of the mickey figure? - which side is the right
    public boolean inRightEar(Location point) 
    {
        if(leftorright == true)
        {
            whichear = rightEar.contains(point);
        }

        else if(leftorright == false)
        {
            whichear = leftEar.contains(point);
        }

        return whichear;
    }

    //moving the mickey figure - as it is dragged
    public void move(double dx, double dy) 
    {
        leftEar.move(dx, dy);
        rightEar.move(dx, dy);
        head.move(dx, dy);
    }

    //removing the mickey figure
    public void removeFromCanvas()
    {
        head.removeFromCanvas();
        rightEar.removeFromCanvas();
        leftEar.removeFromCanvas();
    }

    //flipping the figure to the left - check which side is currently left to see which one needs to be moved
    public void flipLeft() 
    {
        if(leftorright == true)
        {
            rightEar.move(-(EAR_OFFSET + FACE_RADIUS) , 0);
            head.move(-(FACE_RADIUS) , 0);
            leftorright = false;
        }

        else if(leftorright == false)
        {
            leftEar.move(-(EAR_OFFSET + FACE_RADIUS) , 0);
            head.move(-(FACE_RADIUS) , 0);
            leftorright = true;
        }
    }

    //flipping the figure to the right - check which side is currently right to see which one needs to be moved
    public void flipRight() 
    {
        if(leftorright == false)
        {
            rightEar.move((EAR_OFFSET + FACE_RADIUS) , 0);
            head.move(FACE_RADIUS , 0);
            leftorright = true;
        }

        else if(leftorright == true)
        {
            leftEar.move((EAR_OFFSET + FACE_RADIUS) , 0);
            head.move(FACE_RADIUS , 0);
            leftorright = false;
        }
    }

    //flipping the figure down or up, depending on current orientation
    public void flipUpDown() 
    {
        if(upordown == true)
        {
            rightEar.move(0, FACE_RADIUS);
            leftEar.move(0, FACE_RADIUS);
            upordown = false;
        }

        else if(upordown == false)
        {
            rightEar.move(0, -(FACE_RADIUS));
            leftEar.move(0, -(FACE_RADIUS));
            upordown = true;
        }
    }
}
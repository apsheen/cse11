import objectdraw.*;
import java.awt.Color;

public class EC_Mickey 
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

    //current position:
    //0 - upright; 1 - 90 clockwise; 2 - 180 clockwise; 3 - 270 clockwise
    private static int angle;
    
    //TODO - additional variables you might need and - skeletons

    //constructor
    public EC_Mickey(Location point, DrawingCanvas canvas) 
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

        angle = 0;
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

    //is the click in the left ear of the mickey figure?
    public boolean inLeftEar(Location point) 
    {
        return leftEar.contains(point);
    }

    //is the click in the right ear of the mickey figure?
    public boolean inRightEar(Location point) 
    {
        return rightEar.contains(point);
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

    //if held on left ear, turns 90deg counterclockwise
    public void turnLeft() 
    {
        if(angle == 0)
        {
            leftEar.move(0, FACE_RADIUS);
            rightEar.move(-(FACE_RADIUS), 0);

            angle = 3;
        }

        else if(angle == 1)
        {
            leftEar.move(-(FACE_RADIUS), 0);
            rightEar.move(0, -(FACE_RADIUS));

            angle = 0;
        }

        else if(angle == 2)
        {
            leftEar.move(0, -(FACE_RADIUS));
            rightEar.move(FACE_RADIUS, 0);

            angle = 1;
        }

        else if(angle == 3)
        {
            leftEar.move(FACE_RADIUS, 0);
            rightEar.move(0, FACE_RADIUS);

            angle = 2;
        }
    }

    //if held on right ear, turns 90deg clockwise
    public void turnRight() 
    {
        if(angle == 0)
        {
            leftEar.move(FACE_RADIUS, 0);
            rightEar.move(0, FACE_RADIUS);
        }

        else if(angle == 1)
        {
            leftEar.move(0, FACE_RADIUS);
            rightEar.move(-(FACE_RADIUS), 0);
        }

        else if(angle == 2)
        {
            leftEar.move(-(FACE_RADIUS), 0);
            rightEar.move(0, -(FACE_RADIUS));
        }

        else if(angle == 3)
        {
            leftEar.move(0, -(FACE_RADIUS));
            rightEar.move(FACE_RADIUS, 0);
        }

        angle = (angle + 1) % 4;
    }

    //returns to center of canvas
    public void centerFace(double x, double y) 
    {
        leftEar.move((x / 2) - (FACE_RADIUS / 2) - head.getX(), (y / 2) - (FACE_RADIUS / 2) - head.getY());
        rightEar.move((x / 2) - (FACE_RADIUS / 2) - head.getX(), (y / 2) - (FACE_RADIUS / 2) - head.getY());
        head.move((x / 2) - (FACE_RADIUS / 2) - head.getX(), (y / 2) - (FACE_RADIUS / 2) - head.getY());
    }
}
//Turtle writes:
//CSE11
//2018
//UC SAN DIEGO

import turtleClasses.*;
import java.awt.Color;

public class EC_CS11TurtleGraphics extends Turtle 
{ 
    private final static int CHAR_WIDTH = 40; 
    private final static int CHAR_HEIGHT = 80;
    private final static int PADDING_BETWEEN_CHARS = 40;
    private final static int PADDING_BETWEEN_LINES = 40;
    private final static int CHAR_SPACING = CHAR_WIDTH + PADDING_BETWEEN_CHARS;
    private final static int LINE_SPACING = CHAR_HEIGHT + PADDING_BETWEEN_LINES;

    private final static int START_X_1 = 400;
    private final static int START_X_2 = 400;
    private final static int START_X_3 = 80;
    private final static int START_Y = 80;

    private final static int PEN_WIDTH = 10;

    private final static int WORLD_WIDTH = 1080;
    private final static int WORLD_HEIGHT = 480;
    private final static int HALF_CHAR_WIDTH = CHAR_WIDTH / 2; 
    private final static int HALF_CHAR_HEIGHT = CHAR_HEIGHT / 2;

    private final static int A_SIDE = 82;
    private final static int A_ANGLE = 14;
    private final static int HALF_A_SIDE = 41;

    private final static int N_DIAGONAL = 89;
    private final static int N_ANGLE = 26;

    private final static int D_EDGE = 28;
    private final static int D_ANGLE = 45;

    private final static int DELAY = 200;

    public EC_CS11TurtleGraphics(World w, int delay)
    {
        super(w, delay);
    }

    private void drawC(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        forward(CHAR_WIDTH);
        backward(CHAR_WIDTH);
        turnRight();
        forward(CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
    }

    private void drawS(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        forward(CHAR_WIDTH);
        backward(CHAR_WIDTH);
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnRight();
        forward(CHAR_WIDTH);
    }

    private void draw1(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        forward(HALF_CHAR_WIDTH);
        penDown();
        turn(135);
        forward(HALF_CHAR_WIDTH);
        backward(HALF_CHAR_WIDTH);
        turn(-45);
        forward(CHAR_HEIGHT);
        turnRight();
        forward(HALF_CHAR_WIDTH);
        backward(CHAR_WIDTH);
    }

    private void draw2(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        forward(CHAR_WIDTH);
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnRight();
        forward(CHAR_WIDTH);
        turnLeft();
        forward(HALF_CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
    }

    private void draw0O(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        forward(CHAR_WIDTH);
        turnRight();
        forward(CHAR_HEIGHT);
        turnRight();
        forward(CHAR_WIDTH);
        turnRight();
        forward(CHAR_HEIGHT);
    }

    private void draw8(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnRight();
        forward(CHAR_WIDTH);
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnRight();
        forward(CHAR_WIDTH);
        turnLeft();
        forward(HALF_CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
    }

    private void drawU(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        turnRight();
        forward(CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
        turnLeft();
        forward(CHAR_HEIGHT);
    }

    private void drawA(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        forward(HALF_CHAR_WIDTH);
        penDown();
        turn(90 + A_ANGLE);
        forward(A_SIDE);
        backward(A_SIDE);
        turn(-A_ANGLE * 2);
        forward(A_SIDE);
        backward(HALF_A_SIDE);
        turn(90 + A_ANGLE);
        forward(HALF_CHAR_WIDTH);
    }
    
    private void drawN(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        turnRight();
        forward(CHAR_HEIGHT);
        backward(CHAR_HEIGHT);
        turn(-N_ANGLE);
        forward(N_DIAGONAL);
        turn(-(180 - N_ANGLE));
        forward(CHAR_HEIGHT);
    }

    private void drawD(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        turnRight();
        forward(CHAR_HEIGHT);
        turnLeft();
        forward(HALF_CHAR_WIDTH);
        turn(-D_ANGLE);
        forward(D_EDGE);
        turn(-D_ANGLE);
        forward(HALF_CHAR_HEIGHT);
        turn(-D_ANGLE);
        forward(D_EDGE);
        turn(-D_ANGLE);
        forward(HALF_CHAR_WIDTH);
    }

    private void drawI(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        forward(CHAR_WIDTH);
        backward(HALF_CHAR_WIDTH);
        turnRight();
        forward(CHAR_HEIGHT);
        turnRight();
        forward(HALF_CHAR_WIDTH);
        backward(CHAR_WIDTH);
    }

    private void drawE(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        forward(CHAR_WIDTH);
        backward(CHAR_WIDTH);
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
        backward(CHAR_WIDTH);
        turnRight();
        forward(HALF_CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
    }

    private void drawG(int x, int y)
    {
        penUp();
        moveTo(x, y);
        turnToFace(getXPos() + 1, getYPos());
        penDown();
        forward(CHAR_WIDTH);
        backward(CHAR_WIDTH);
        turnRight();
        forward(CHAR_HEIGHT);
        turnLeft();
        forward(CHAR_WIDTH);
        turnLeft();
        forward(HALF_CHAR_WIDTH);
        turnLeft();
        forward(10);
    }

    public static void main(String [] args)
    {
        int startX1 = START_X_1;
        int startX2 = START_X_2;
        int startX3 = START_X_3;
        int startY = START_Y;

        int x, y;

        World w = new World (WORLD_WIDTH, WORLD_HEIGHT);
        EC_CS11TurtleGraphics myTurtle = new EC_CS11TurtleGraphics(w, DELAY);
        
        myTurtle.setPenWidth(PEN_WIDTH);
        myTurtle.setPenColor(Color.BLACK);

        myTurtle.drawC(x = startX1, y = startY);
        myTurtle.drawS(x += CHAR_SPACING, y);
        myTurtle.draw1(x += CHAR_SPACING, y);
        myTurtle.draw1(x += CHAR_SPACING, y);

        myTurtle.setPenColor(Color.BLUE);

        myTurtle.draw2(x = startX2, y += LINE_SPACING);
        myTurtle.draw0O(x += CHAR_SPACING, y);
        myTurtle.draw1(x += CHAR_SPACING, y);
        myTurtle.draw8(x += CHAR_SPACING, y);

        myTurtle.setPenColor(Color.GREEN);

        myTurtle.drawU(x = startX3, y += LINE_SPACING);
        myTurtle.drawC(x += CHAR_SPACING, y);

        myTurtle.setPenColor(Color.BLUE);

        myTurtle.drawS(x = x + CHAR_SPACING + CHAR_SPACING, y);
        myTurtle.drawA(x += CHAR_SPACING, y);
        myTurtle.drawN(x += CHAR_SPACING, y);

        myTurtle.setPenColor(Color.BLACK);

        myTurtle.drawD(x = x + CHAR_SPACING + CHAR_SPACING, y);
        myTurtle.drawI(x += CHAR_SPACING, y);
        myTurtle.drawE(x += CHAR_SPACING, y);
        myTurtle.drawG(x += CHAR_SPACING, y);
        myTurtle.draw0O(x += CHAR_SPACING, y);
    }
}
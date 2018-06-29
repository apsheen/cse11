import objectdraw.*;
import Acme.*;

// This program allows the user to click on the ball and
// drag it around the screen.  Releasing the mouse drops
// the ball.  Points are awarded when the ball is placed
// in the basket.
public class BestBasketBall extends WindowController{

        // dimensions and locations for the hoop
        private static final int HOOP_TOP = 50;
        private static final int HOOP_HEIGHT = 60;
        private static final int HOOP_WIDTH = 100;
        
        // dimensions and locations for the score display
        private static final int DISPLAY_FONT_SIZE = 16;
        private static final int DISPLAY_TOP = 350;
        
        // dimensions and locations for the ball
        private static final int BALL_SIZE = 35;
        private static final int BALL_TOP = DISPLAY_TOP-2*BALL_SIZE;

        // the Text object which displays the count
        private Text display;

        // the oval that represent the hoop
        private FramedOval hoop;

        // the number of points
        private int score = 0;
        
        // Last position of mouse while dragging
        private Location lastPoint;
        
        // the ball itself
        private FilledOval ball;
        
        // remembers whether the ball was touched when the button was pressed
        private boolean ballGrabbed;

        private static final int FRAME_WIDTH = 750;
        private static final int FRAME_HEIGHT = 750;

        // initialize the counter and the text message
        public void begin()
        {
                
                display = new Text("Take a shot.",
                                                        0, DISPLAY_TOP, canvas);
                display.setFontSize(DISPLAY_FONT_SIZE);
                display.move( (canvas.getWidth()-display.getWidth())/2, 0 );

                
                hoop = new FramedOval( (canvas.getWidth()-HOOP_WIDTH)/2, HOOP_TOP,
                                                                HOOP_WIDTH, HOOP_HEIGHT, 
                                                                canvas);
                
                ball = new FilledOval( (canvas.getWidth()-BALL_SIZE)/2, BALL_TOP,
                                BALL_SIZE, BALL_SIZE, canvas);
                
        }
        
        public void onMousePress(Location point){
                lastPoint = point;
                ballGrabbed = ball.contains(point);
        }
        
        // Move the basketball as the mouse is dragged
        public void onMouseDrag(Location point){
                if ( ballGrabbed ) {
                        ball.move( point.getX()-lastPoint.getX(),
                                       point.getY()-lastPoint.getY());
                }
                lastPoint = point;
        }

        // increment the counter if player scores and update the text
        // appropriately
        public void onMouseRelease(Location point)
        { 
                if (ballGrabbed && hoop.contains(point)) {
                        
                        score = score + 2;
                        display.setText("You have scored " + score + " points.");
                        
                } else {
                        
                        display.setText("WHOOPS!  You Missed.");
                }
                
                ball.moveTo( (canvas.getWidth()-BALL_SIZE)/2, BALL_TOP );
                display.moveTo( (canvas.getWidth() - display.getWidth()) / 2,
                                                DISPLAY_TOP);
        }

        public static void main(String[] args) 
        {
                new Acme.MainFrame(new BestBasketBall(), args, FRAME_WIDTH, FRAME_HEIGHT);
        }
}
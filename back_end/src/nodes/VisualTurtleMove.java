package nodes;
import apis.CanvasAPI;

public class VisualTurtleMove extends VisualCommand {
    private double myXDelta;
    private double myYDelta;
    public VisualTurtleMove(double pixels, double currHeading){
        double orientation = Math.toRadians(currHeading);
        double deltaX = pixels * Math.sin(orientation);
        double deltaY = - pixels * Math.cos(orientation);
        myXDelta = deltaX;
        myYDelta = deltaY;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turtleMove(myXDelta, myYDelta);
    }

    public double getXDelta(){
        return myXDelta;
    }
    public double getYDelta(){
        return myYDelta;
    }
}

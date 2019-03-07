package GUI.Tabs;

import GUI.Turtle.DisplayView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class TurtleViewTabExplorer extends TabExplorer {

    public static final String PEN_PROPERTIES = "Pen Properties";
    public static final String TURTLE_PROPERTIES = "Turtle Properties";
    private SLogoTab myPenProperties;
    private SLogoTab myTurtleProperties;

    public TurtleViewTabExplorer(){
        myPenProperties = new SLogoTab(PEN_PROPERTIES);
        myTurtleProperties = new SLogoTab(TURTLE_PROPERTIES);
        getTabs().addAll(myPenProperties, myTurtleProperties);
    }

    public Consumer<DisplayView> getTabAccess(){
        return (x) -> {
            updatePenPropertiesTab(x);
            updateTurtleProperties(x);
        };
    }

    private void updateTurtleProperties(DisplayView x) {
        myTurtleProperties.clearContents();
        myTurtleProperties.addContents(String.format("Heading: %.2f degrees", x.getRotate()));
        myTurtleProperties.addContents(String.format("Y Coordinate: %.2f", -1 * x.getTranslateY() + 0.001));
        myTurtleProperties.addContents(String.format("X Coordinate: %.2f", x.getTranslateX()));
        myTurtleProperties.addContents(String.format("Showing turtle properties for turtle %d", x.getTurtleId()));
    }

    private void updatePenPropertiesTab(DisplayView x) {
        myPenProperties.clearContents();
        Color color = (Color) x.getMyPen().getMyColor();
        myPenProperties.addContents(String.format("Pen Color: [%d, %d, %d]", (int) color.getRed(),
                (int) color.getGreen(), (int) color.getBlue()));
        myPenProperties.addContents(String.format("Pen Size: %.2f", x.getMyPen().getMyWidth()));
        myPenProperties.addContents(String.format("Pen Down: %b", x.getMyPen().isDown()));
    }
}

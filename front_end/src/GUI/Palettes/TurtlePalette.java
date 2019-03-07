package GUI.Palettes;

import GUI.Turtle.DisplayView;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.function.Function;

public class TurtlePalette<T extends Node> extends ScrollPane {

    VBox myVBox;
    Map<Integer, TurtlePaletteElement> myPaletteElements;

    public TurtlePalette(){
        myVBox = new VBox();
        myPaletteElements = new TreeMap<>();
        myVBox.getChildren().addAll(myPaletteElements.values());
        setContent(myVBox);
        populateStartingElements();
    }

    private void populateStartingElements() {
        this.addPaletteElement(new TurtlePaletteElement(1, "BasicTurtleView"));
        this.addPaletteElement(new TurtlePaletteElement(2, "AdvancedTurtleView"));
        this.addPaletteElement(new TurtlePaletteElement(4, "BasicTurtleView"));
        this.addPaletteElement(new TurtlePaletteElement(3, "AdvancedTurtleView"));
    }

    public void addPaletteElement(TurtlePaletteElement element){
        myPaletteElements.put(element.getMyIndex(), element);
        myVBox.getChildren().clear();
        myVBox.getChildren().addAll(myPaletteElements.values());
    }

    public void removePaletteElement(TurtlePaletteElement element){
        myPaletteElements.remove(element.getMyIndex());
    }

    public String getContent(int index) {
        return myPaletteElements.get(index).getMyTurtleType();
    }

    public Function<Integer, String> turtleLookupAccess(){
        return (x) -> getContent(x);
    }
}
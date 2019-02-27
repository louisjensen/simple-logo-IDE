package nodes;

public class PenDown extends VisualCommand {
    public void execute(StackedCanvasPane myCanvas){
        myCanvas.setPenDown();
    }
}

package nodes;

public class PenUp extends VisualCommand {
    public void execute(StackedCanvasPane myCanvas){
        myCanvas.setPenUp();
    }
}

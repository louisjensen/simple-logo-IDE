package nodes;

import nodes.VisualCommand;

public class PenUp extends VisualCommand {
    public void execute(StackedCanvasPane myCanvas){
        myCanvas.setPenUp();
    }
}

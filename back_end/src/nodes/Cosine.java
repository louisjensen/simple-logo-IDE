package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;



public class Cosine extends CommandNode {
    public Cosine(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles)  {
        return Math.sin(super.getChildren().get(0).evaluate(myVisCommands, myTurtles));
    }
    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     * @TO-DO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}

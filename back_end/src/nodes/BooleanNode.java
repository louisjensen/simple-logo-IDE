package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;
import turtle.Turtle;

import java.util.List;



public abstract class BooleanNode extends CommandNode {
    private static final int INVALID_INPUT = 0;
    public BooleanNode(String name){
        super(name);
    }


    protected double getFirstExpression(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
    }
    protected double getSecondExpression(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
    }

    @Override
    public abstract double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles);

    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }
}

package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;


public class ArcTangent extends CommandNode {
    private static final int NO_INPUT = 0;
    public ArcTangent(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        try {
            return Math.atan(super.getChildren().get(0).evaluate(myVisCommands, myTurtles));
        }
        catch(IllegalArgumentException e) {
            return NO_INPUT;
        }
    }

    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }

}

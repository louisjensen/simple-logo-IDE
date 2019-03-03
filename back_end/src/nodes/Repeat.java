package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class Repeat extends CommandNode{
    private static final int NUM_ITERATIONS = 0;
    private static final int FIRST_COMMAND = 1;
    public Repeat(String a) {
        super(a);
    }

    /**
     * TODO - Initalize repcount and update with each iteration
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        //initialize repcount potentially
        double ret = 0;
        int numIterations = (int)super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        for (int iter = NUM_ITERATIONS; iter < numIterations; iter++){
            for (int currChild = FIRST_COMMAND; currChild < super.getChildren().size(); currChild++)
                ret = super.getChildren().get(currChild).evaluate(myVisCommands, myTurtles);
            //update repcount
        }
        return ret;
    }

    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }
}
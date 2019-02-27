


package main;
import java.util.ArrayList;
import java.util.List;

import back_end.exceptions.InvalidCommandException;
import back_end.nodes.CommandNode;
import back_end.nodes.VisualCommand;
import back_end.Parser;
import turtle.ImmutableTurtleState;

public class CommandController {
    private Parser myParser;
    private static final int INVALID_COMMAND = 0;
    List<VisualCommand> myVisualCommands;

    public CommandController(){
        myParser = new Parser();
    }

    public double execute(String command){
        CommandNode myNode;
        myVisualCommands = new ArrayList<VisualCommand>();
        try{
            myNode = myParser.parse(command).get(0); // note of change! This is changed now because their could be many commands in a list that come from a back_end.parser.
        }
        catch(ArithmeticException e){
         return INVALID_COMMAND;
       } catch (InvalidCommandException e) {
            return INVALID_COMMAND;
        }
        try {
            return myNode.evaluate(myVisualCommands);
        }
        catch(Exception e) {
            System.out.println("Invalid Calculation");
            return INVALID_COMMAND;
        }
        //pass list of visual commands to vis
        //would need to write
        //for (back_end.nodes.VisualCommand c: myVisualCommands)
        // c.execute(myCanvas);
    }
    public List<VisualCommand> getMyVisualCommands(){
        return myVisualCommands;
    }
    public void updateTurtle(ImmutableTurtleState currentState) {

    }

}

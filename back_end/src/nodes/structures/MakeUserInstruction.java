package nodes.structures;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;


public class MakeUserInstruction extends CommandNode {
    private UserCreated myUserCreatedItems;
    public MakeUserInstruction(String commandName) {
        super(commandName);
    }
    public MakeUserInstruction(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        String commandName = super.getChildren().get(0).getName();
        CommandNode variableNames = super.getChildren().get(1);
        CommandNode commands = super.getChildren().get(2);
//        myUserCreatedItems.addUserCommand(commandName, List, )
        return 1.0;
    }
    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
    @Override
    public boolean needsName(){
        return true;
    }

    @Override
    public boolean needsUserCreated(){ return true;}
}
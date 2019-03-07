package nodes;

import apis.GetVariableValue;
import apis.ImmutableVisualCommand;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;

public class Variable extends CommandNode {
    private String myVarName;
    private double myValue;
    private static final double NOT_ASSIGNED = 0;
    private static final String NOT_ASSIGNED_ERROR = "WARNING: VARIABLE DOES NOT EXIST";
    public Variable(String variableName){
        super(variableName);
        myVarName = variableName;
        myValue = NOT_ASSIGNED;
        System.out.println(NOT_ASSIGNED_ERROR);
    }
    public Variable(String variableName, UserCreated myUserCreated){
        super(variableName);
        myVarName = variableName;
        try {
            myValue = myUserCreated.getValue(myVarName);
        }
        catch(Exception e) {
            System.out.println(NOT_ASSIGNED_ERROR);
        }
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myValue;
    }

//    @Override
}

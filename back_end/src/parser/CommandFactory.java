package parser;

import apis.AddVariable;
import nodes.CommandNode;
import nodes.ConstantNode;
import nodes.MakeVariable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory {

    public CommandNode makeCommand(double d) {
        return new ConstantNode("" + d);
    }

    public CommandNode makeCommand(String c) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Class myCommandClass = Class.forName("nodes." + c);
        Constructor myConstructor = myCommandClass.getConstructor(String.class);
        CommandNode myCommandNode = (CommandNode) myConstructor.newInstance(c);
        return myCommandNode;
    }

    public CommandNode makeCommand(String c, AddVariable av) {
        System.out.println("in make command");
        return new MakeVariable(c, av);
    }
}

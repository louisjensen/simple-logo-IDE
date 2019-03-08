package parser;

import exceptions.InvalidInputException;
import exceptions.InvalidListException;
import exceptions.TooFewInputsException;
import nodes.CommandNode;

import java.util.ArrayList;
import java.util.List;

//  todo:  actually use  mySplitCommand
public class Parser {
    private CommandFactory myCommandFactory;
    private String myCurrentCommand;
    private String[] mySplitCommand;
    private UserCreated myUserCreated;
    private Validator myValidator;

    private static final String LIST_NODE_NAME = "ListNode";

    public Parser(UserCreated userCreated) {
        myUserCreated = userCreated;
        myCommandFactory = new CommandFactory();
        myValidator = new Validator();
    }

    public List<CommandNode> parse(String input) throws InvalidInputException {
        myCurrentCommand = input;
        myCurrentCommand = myValidator.removeComments(input);
        mySplitCommand = splitUpCurrentCommand();
        List<CommandNode> topLevelCommands = new ArrayList<>();
        while(myCurrentCommand.length() > 0) {
            topLevelCommands.add(makeNodeTree());
        }
        return topLevelCommands;
    }

    private String[] splitUpCurrentCommand() {
        return myCurrentCommand.split("\\s+"); // todo: replace this with props file
    }

    private CommandNode makeNodeTree() throws InvalidInputException {
        String[] commandSplit = myCurrentCommand.trim().split("\\s+");
        String currentValue = commandSplit[0];
        String currentCommandKey = myValidator.getCommandKey(currentValue,  myUserCreated);
        CommandNode currentNode = makeGeneralCommand(currentCommandKey);
        int expectedNumberOfParameters = myValidator.getExpectedNumberOfParameters(currentValue);
        if(currentNode.needsName()) { // this means the current node is looking for a variable or method name
            addNameChild(currentNode, commandSplit[1]);
        }
        if(currentNode.isMethodDeclaration()) { // special case where we want the children to be a bit different
            return makeMethodDeclarationNode(currentNode, commandSplit[1]);
        }
        for(int i = getStartIndex(currentNode); i <= expectedNumberOfParameters; i++) {
            commandSplit = myCurrentCommand.split("\\s+");
            if(commandSplit[0].length()  == 0) {
                throw new TooFewInputsException();  // this takes care of the issue where it  tries to parse an empty string
            }
            addChild(currentNode, commandSplit[0]);
        }
        return currentNode;
    }

    private CommandNode makeMethodDeclarationNode(CommandNode currentNode, String command) throws InvalidInputException {
        addNameChild(currentNode, command);
        currentNode.addChild(makeNameListTree());
        updateMyCurrentCommand();
        currentNode.addChild(myCommandFactory.makeNameNode(myCurrentCommand.substring(myCurrentCommand.indexOf("[") + 1, myCurrentCommand.indexOf("]"))));
        myCurrentCommand = "";
        return currentNode;
    }

    private  CommandNode makeGeneralCommand(String command) throws InvalidInputException {
        CommandNode currentNode;
        if(myUserCreated.containsCommand(command)) {
            currentNode = myCommandFactory.makeCommand("UserInstruction", myUserCreated);
            addNameChild(currentNode, command);
            currentNode.addChild(makeListTree());
            currentNode.addChild(makeListNodeFromList(parse(myUserCreated.getCommand(command))));
        } else {
            currentNode = myCommandFactory.makeCommand(command, myUserCreated);
            updateMyCurrentCommand();
        }
        return currentNode;
    }

    private CommandNode makeNameListTree() throws InvalidInputException {
        CommandNode parent = makeHeadOfList();
        String[] splitCommand = myCurrentCommand.trim().split("\\s+");
        String child = splitCommand[0];
        while(!myValidator.isListEnd(child)) {
            addNameChild(parent, child);
            splitCommand = myCurrentCommand.trim().split("\\s+");
            child = splitCommand[0];
        }
        return parent;
    }

    private CommandNode makeHeadOfList() throws InvalidInputException {
        if(myValidator.hasListEnd(myCurrentCommand)) {
            throw new InvalidListException();
        }
        updateMyCurrentCommand();
        return myCommandFactory.makeCommand(LIST_NODE_NAME, myUserCreated);
    }

    private CommandNode makeListNodeFromList(List<CommandNode> commands) throws InvalidInputException {
        CommandNode head = myCommandFactory.makeCommand(LIST_NODE_NAME, myUserCreated);
        for(CommandNode command : commands) {
            head.addChild(command);
        }
        return head;
    }

    private void addNameChild(CommandNode currentNode, String s) {
        currentNode.addChild(myCommandFactory.makeNameNode(s));
        updateMyCurrentCommand();
    }

    private int getStartIndex(CommandNode currentNode) {
        if(currentNode.needsName() || currentNode.isMethodDeclaration()) {
            return 2;
        } else {
            return 1;
        }
    }

    private void addVariableChild(CommandNode currentNode, String child) throws InvalidInputException {
        myValidator.validateVariableName(child);
        currentNode.addChild(myCommandFactory.makeVariableNode(child, myUserCreated));
    }

    private void addChild(CommandNode currentNode, String child) throws InvalidInputException {
        if (myValidator.isDouble(child)) {
            currentNode.addChild(myCommandFactory.makeCommand(Double.parseDouble(child)));
        } else if (myValidator.isListStart(child)) {
            currentNode.addChild(makeListTree());
        } else if (myValidator.isVariable(child)) {
            addVariableChild(currentNode, child);
        } else {
            currentNode.addChild(makeNodeTree());
            return;
        }
        updateMyCurrentCommand();
    }

    private CommandNode makeListTree() throws InvalidInputException {
        CommandNode parent = makeHeadOfList();
        String[] splitCommand = myCurrentCommand.trim().split("\\s+");
        String child = splitCommand[0];
        while(!myValidator.isListEnd(child)) {
            addChild(parent, child);
            splitCommand = myCurrentCommand.trim().split("\\s+");
            child = splitCommand[0];
        }
        return parent;
    }

    private void updateMyCurrentCommand() {
        String[] split = myCurrentCommand.split(" ");
        myCurrentCommand = "";
        for(int i = 1; i < split.length; i++) {
            myCurrentCommand += split[i] + " ";
        }
        myCurrentCommand = myCurrentCommand.trim();
    }

    public void updateLanguage(String newLanguage) {
        myValidator.updateLanguage(newLanguage);
    }

}

package NonGUI;

/**
 *
 */
public interface InternalBackEnd {

    /**
     * Evaluating a specific command
     */
    public void evaluateCommand();

    /**
     * Traversing CommandTree generated by Parser in order to generate the commands and understand the order in which
     * they should happen
     */
    public void evaluateCommandTree();

    /**
     * Retrieving root of CommandTree after Parser generates it from reading
     */
    public CommandNode getCommandTree();

    /**
     * Saves the root of a CommandTree in a map that has String keys for the method name and CommandTree nodes for the
     * entries
     */
    public void saveUserDefinedCommand();

    /**
     * Saves a user-defined variable in a map that has String keys for the variable name and value of the variable
     */
    public void saveUserVariable();

}
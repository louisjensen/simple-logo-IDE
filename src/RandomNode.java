public class RandomNode extends CommandNode {
    public RandomNode(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate() {
        double childValue = super.getChildren().get(0).evaluate();
        return Math.floor(Math.random()*childValue) + 1;
    }
    /**
     * Adds an addend to this SumNode's list of Children as Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
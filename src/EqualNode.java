public class EqualNode extends BooleanNode{

    public EqualNode(String a) {
        super(a);
    }

    @Override
    public double evaluate() {
        if (super.getFirstExpression() == super.getSecondExpression())
            return ONE;
        return ZERO;
    }

    /**
     * Adds an addend to this SumNode's list of Children as Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}

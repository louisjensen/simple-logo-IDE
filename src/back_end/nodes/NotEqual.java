package nodes;

import java.util.List;


public class NotEqual extends BooleanNode{

    public NotEqual(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        if (super.getFirstExpression(myVisCommands) != super.getSecondExpression(myVisCommands))
            return ONE;
        return ZERO;
    }

    /**
     * Adds an addend to this back_end.nodes.SumNode's list of Children as back_end.parser.Parser reads them in
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
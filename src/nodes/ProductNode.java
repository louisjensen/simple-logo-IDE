package nodes;
import nodes.VisualCommand;

import java.util.List;


public class ProductNode extends CommandNode {
    public ProductNode(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        double firstExpression = super.getChildren().get(0).evaluate(myVisCommands);
        double secondExpression = super.getChildren().get(1).evaluate(myVisCommands);
        return firstExpression * secondExpression;
    }

    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
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
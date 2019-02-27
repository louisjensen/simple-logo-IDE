

package nodes;
import java.util.List;
public class Minus extends CommandNode {
    private static final int NO_INPUT = 0;
    public Minus(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        return -1.0 * super.getChildren().get(0).evaluate(myVisCommands);
    }
    /**
     * Adds an addend to this back_end.nodes.SumNode's list of Children as back_end.parser.Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}

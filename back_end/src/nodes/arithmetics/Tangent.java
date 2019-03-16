package nodes.arithmetics;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Tangent extends CommandNode {
    public Tangent(String commandName) {
        super(commandName);
    }
    /**
     * @return Tangent value of child node
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
                return Math.tan(super.getChildren().get(0).evaluate(myVisCommands, myTurtles));
    }
}
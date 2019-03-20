package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualPenUp extends VisualCommand {
    private final int myID;
    public VisualPenUp(int id){
        myID = id;
    }
    /**
     * Invokes front end to set the pen associated with turtle myID up
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenUp(myID);
    }
}
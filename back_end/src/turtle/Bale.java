package turtle;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
  * Bale extends the specific implementation of ListNode, ArrayList, because it was not necessary to implement the abstract methods
  * in ListNode. Bale is biological jargon for a group of turtles :)
 */

public class Bale extends ArrayList<Turtle> {
    private int myActiveID;
    public Bale(){
        myActiveID = 0;
    }
    private List<Turtle> getActiveTurtles(){
        List<Turtle> activeTurtles = new ArrayList<>();
        for (Turtle t: this) {
            if (t.isActive())
                activeTurtles.add(t);
        }
        return activeTurtles;
    }

    public void makeTurtlesUpTo(int maxID){
        for (int k = size(); k < maxID; k++)
            this.add(new Turtle(k));
    }

    public void setActiveTurtles(List<Integer> myTurtleIDs){
        for (Turtle t: this)
            if (myTurtleIDs.contains(t.getID()))
                t.setActive(true);
            else
                t.setActive(false);
    }
    public void setAllInactive(){
        for (Turtle t: this)
            t.setActive(false);
    }

    public List<ImmutableVisualCommand> act(List<String> myTurtleMethods,  Object[] actualParams){
        List<ImmutableVisualCommand> myVisCommands = new ArrayList<ImmutableVisualCommand>();
        for (Turtle t: this.getActiveTurtles()) {
            setActiveID(t.getID());
            for (String m: myTurtleMethods) {
                ImmutableVisualCommand vis = t.turtleAction(m, actualParams);
                myVisCommands.add(vis);
            }
        }
        return myVisCommands;
    }

    public Turtle getLastActiveTurtle(){
        return this.get(size() - 1);
    }

    private void setActiveID(int id){
        myActiveID = id;
    }

    public int getActiveID(){
        return myActiveID;
    }

}

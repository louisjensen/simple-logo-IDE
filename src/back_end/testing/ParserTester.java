package back_end.testing;

import main.CommandController;

public class ParserTester {
    public static void main(String[] args){
        String testOne = "back_end.nodes.SumNode back_end.nodes.SumNode 3 10 1";
        CommandController myController = new CommandController();
      double resultOne = myController.execute(testOne);
        System.out.println(testOne + ": " + resultOne);
    }
}

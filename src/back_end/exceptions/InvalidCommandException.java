package back_end.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String reason) {
        System.out.println("Invalid Command: " + reason);
    }
}

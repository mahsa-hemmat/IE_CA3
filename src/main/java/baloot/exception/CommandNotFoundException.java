package baloot.exception;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String message) {
            super("No Comment With Given Id is Found");
        }
}

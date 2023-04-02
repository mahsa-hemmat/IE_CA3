package baloot.exception;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String message) {
            super("no comment with given id is found");
        }
}

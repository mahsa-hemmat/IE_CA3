package baloot.exception;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException() {
        super("Incorrect Password");
    }

}

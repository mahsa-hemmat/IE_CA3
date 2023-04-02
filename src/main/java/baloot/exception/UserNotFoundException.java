package baloot.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super("no user with given username is found");
    }
}

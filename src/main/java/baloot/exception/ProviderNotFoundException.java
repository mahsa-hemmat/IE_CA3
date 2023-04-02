package baloot.exception;

public class ProviderNotFoundException extends Exception{
    public ProviderNotFoundException(int id) {
        super("no provider with id " + id + " is found");
    }
}

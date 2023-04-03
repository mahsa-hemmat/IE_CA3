package baloot.exception;

public class DiscountHasExpiredException extends Exception{
    public DiscountHasExpiredException() {
        super("submitted discount has expired");
    }

}

package baloot.exception;

public class DiscountHasExpiredException extends Exception{
    public DiscountHasExpiredException() {
        super("Submitted Discount Has Expired");
    }

}

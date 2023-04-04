package baloot.exception;

public class InvalidDiscountException extends Exception{
    public InvalidDiscountException(String code) {
        super("No Discount With Code "+ code +" is Found");
    }
}

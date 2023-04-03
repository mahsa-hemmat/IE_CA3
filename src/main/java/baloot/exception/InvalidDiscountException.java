package baloot.exception;

public class InvalidDiscountException extends Exception{
    public InvalidDiscountException(String code) {
        super("no discount with code "+ code +" is found");
    }
}

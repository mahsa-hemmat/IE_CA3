package baloot.exception;

public class NotEnoughCreditException extends Exception{
    public NotEnoughCreditException(String message) {
        super("not enough credit to buy this item");
        }

}

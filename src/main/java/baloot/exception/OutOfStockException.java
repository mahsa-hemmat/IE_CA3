package baloot.exception;

public class OutOfStockException extends Exception{
    public OutOfStockException(int id) {
        super("commodity with  id " + id + " is out of stock");
    }
}

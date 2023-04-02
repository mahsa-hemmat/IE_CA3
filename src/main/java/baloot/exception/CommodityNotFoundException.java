package baloot.exception;

public class CommodityNotFoundException extends Exception{
    public CommodityNotFoundException(int id) {
        super("no commodity with id "+ id +" is found");
    }
}

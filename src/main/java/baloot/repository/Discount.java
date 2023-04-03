package baloot.repository;

public class Discount {
    private String discountCode;
    private int discount;
    private Boolean alreadyUsed = false;

    public Discount(){}
    public Discount(String discountCode, int discount, Boolean alreadyUsed){
        this.discountCode = discountCode;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public Boolean getAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(Boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }

    public String getDiscountCode() {
        return discountCode;
    }
}

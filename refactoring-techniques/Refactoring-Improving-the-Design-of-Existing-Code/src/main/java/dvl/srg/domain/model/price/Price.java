package dvl.srg.domain.model.price;

public abstract class Price {

    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

}

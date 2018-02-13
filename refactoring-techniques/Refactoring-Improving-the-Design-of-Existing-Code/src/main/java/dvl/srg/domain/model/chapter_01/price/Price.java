package dvl.srg.domain.model.chapter_01.price;

public abstract class Price {

    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

}

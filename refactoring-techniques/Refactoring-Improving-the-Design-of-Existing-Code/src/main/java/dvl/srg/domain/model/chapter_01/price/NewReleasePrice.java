package dvl.srg.domain.model.chapter_01.price;

import dvl.srg.domain.model.chapter_01.Movie;

public class NewReleasePrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }
}
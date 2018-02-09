package dvl.srg.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private static final Movie DEADPOOL_2 = new Movie("Deadpool 2", Movie.NEW_RELEASE);
    private static final Movie THE_HULK = new Movie("The Hulk", Movie.CHILDRENS);
    private static final Movie THOR_RAGNAROK = new Movie("Thor Ragnarok", Movie.CHILDRENS);
    private static final Movie GAME_OF_THRONES = new Movie("Game Of Thrones", Movie.REGULAR);

    private final Customer customer = new Customer("John");

    @Test
    public void testBasicChildrenRental() {
        customer.addRental(new Rental(THOR_RAGNAROK, 2));
        assertEquals(customer.statement(), expectedMessageFor("Thor Ragnarok", 1.5, 1.5, 1));
    }

    @Test

    public void testDiscountChildrensRentals() {
        customer.addRental(new Rental(THOR_RAGNAROK, 4));
        assertEquals(customer.statement(), expectedMessageFor("Thor Ragnarok", 3.0, 3.0, 1));
    }

    @Test
    public void testBasicNewReleaseRental() {
        customer.addRental(new Rental(DEADPOOL_2, 1));
        assertEquals(customer.statement(), expectedMessageFor("Deadpool 2", 3.0, 3.0, 1));
    }

    @Test
    public void testNotDiscountNewReleaseRentalsButBonusFrequentRenterPoints() {
        customer.addRental(new Rental(DEADPOOL_2, 4));
        assertEquals(customer.statement(), expectedMessageFor("Deadpool 2", 12.0, 12.0, 2));
    }

    @Test
    public void testBasicRegularRental() {
        customer.addRental(new Rental(GAME_OF_THRONES, 2));
        assertEquals(customer.statement(), expectedMessageFor("Game Of Thrones", 2.0, 2.0, 1));
    }

    @Test
    public void testDiscountRegularRental() {
        customer.addRental(new Rental(GAME_OF_THRONES, 4));
        assertEquals(customer.statement(), expectedMessageFor("Game Of Thrones", 5.0, 5.0, 1));
    }

    @Test
    public void testSumVariousRentals() {
        customer.addRental(new Rental(THOR_RAGNAROK, 2));
        customer.addRental(new Rental(GAME_OF_THRONES, 1));
        customer.addRental(new Rental(DEADPOOL_2, 3));

        assertEquals(customer.statement(), "Rental record for John\n\tThor Ragnarok\t1.5\n\tGame Of Thrones\t2.0\n\tDeadpool 2\t9.0\nAmount owed is 12.5\nYou earned 4 frequent renter points");
    }

    private static String expectedMessageFor(String rental, double price, double total, int renterPointsEarned) {
        return "Rental record for John\n\t" + rental + "\t" + price + "\nAmount owed is " + total + "\nYou earned " + renterPointsEarned + " frequent renter points";
    }
}
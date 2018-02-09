package dvl.srg.domain.model;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental rental) {
        _rentals.add(rental);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();
        String result = "Rental record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {

            Rental each = (Rental) rentals.nextElement();
            // add frequent renter points
            frequentRenterPoints += each.getFrequentRenterPoints();

            // avoid performance issues
            double charge = each.getCharge();
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(charge) + "\n";
            totalAmount += charge;

        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
}

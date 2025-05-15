package edu.cse231.models.Payments;

import java.util.Date;

import edu.cse231.interfaces.PaymentProcessor;

public class CashPayment extends Payment implements PaymentProcessor {

    public CashPayment(double amount, Date date) {
        super(amount, date);
    }

    @Override
    public boolean process() {
        // Simulate cash payment processing
        // In a real system, this might just involve validating the amount received
        // and updating the status.
        setStatus(PaymentStatus.COMPLETED);
        return true; // Assume cash payment is always successful in this simulation
    }
}

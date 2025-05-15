package edu.cse231.models.Payments;

import edu.cse231.interfaces.PaymentProcessor;

import java.util.Date;

class CreditPayment extends Payment implements PaymentProcessor {

    // Add specific attributes for credit card if needed (e.g., card number, expiry)
    // private String cardNumberLast4; // Example

    public CreditPayment(double amount, Date date) {
        super(amount, date);
        // Initialize credit card specific attributes here if added
    }

    @Override
    public boolean process() {
        // Simulate credit card payment processing
        // This would typically involve interacting with a payment gateway API.
        // For this example, we'll just simulate success/failure.
        boolean simulatedSuccess = Math.random() > 0.1; // 90% chance of success

        if (simulatedSuccess) {
            setStatus(PaymentStatus.COMPLETED);
            // In a real system, you'd get a real transaction ID from the gateway
            // setTransactionId("GATEWAY_TXN_" + UUID.randomUUID().toString());
            return true;
        } else {
            setStatus(PaymentStatus.FAILED);
            return false;
        }
    }
}
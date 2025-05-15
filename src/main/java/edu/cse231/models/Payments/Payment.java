package edu.cse231.models.Payments;

import java.util.Date;
import java.util.UUID;

import edu.cse231.interfaces.PaymentProcessor;

enum PaymentStatus {
    PENDING, COMPLETED, FAILED, REFUNDED
}

public abstract class Payment implements PaymentProcessor {

    private double amount;
    private Date date;
    private String transactionId;
    private PaymentStatus status;

    public Payment(double amount, Date date) {
        this.amount = amount;
        this.date = date;
        this.status = PaymentStatus.PENDING;
        this.transactionId = UUID.randomUUID().toString(); // Generate a transaction ID on creation
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    protected void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    protected void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public boolean refund() {
        if (status != PaymentStatus.COMPLETED) {
            return false;
        }
        // In a real system, this would interact with a payment gateway
        // For this simulation, we'll just mark it as refunded
        setStatus(PaymentStatus.REFUNDED);
        return true;
    }
}

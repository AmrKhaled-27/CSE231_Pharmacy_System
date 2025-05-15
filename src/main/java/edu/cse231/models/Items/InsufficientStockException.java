package edu.cse231.models.Items;

public class InsufficientStockException extends Exception {

    public InsufficientStockException(String message) {
        super(message);
    }
}

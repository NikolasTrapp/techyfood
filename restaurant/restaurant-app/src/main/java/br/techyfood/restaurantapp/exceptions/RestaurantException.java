package br.techyfood.restaurantapp.exceptions;

public class RestaurantException extends RuntimeException {

    public RestaurantException(String message) {
        super(message);
    }

    public RestaurantException(String message, Throwable cause) {
        super(message, cause);
    }

}

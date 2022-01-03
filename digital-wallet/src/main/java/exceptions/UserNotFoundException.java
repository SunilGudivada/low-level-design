package exceptions;

/**
 * Custom Exception if the user is not present in the system.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

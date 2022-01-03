package exceptions;

/**
 * Custom Exception if balance is wallet is not sufficient to make the transaction
 */
public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String message){
        super(message);
    }
}

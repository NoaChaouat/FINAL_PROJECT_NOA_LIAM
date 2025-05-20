package liam_zimmerman_noa_chaouat;

public class ExceptionUserMessage extends Exception {

    private static final String PRE_MESSAGE = "ERROR: ";

    public ExceptionUserMessage(String message) {
        super(PRE_MESSAGE + message);
    }
}

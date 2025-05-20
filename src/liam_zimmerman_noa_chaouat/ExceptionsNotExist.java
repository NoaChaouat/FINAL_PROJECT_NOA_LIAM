package liam_zimmerman_noa_chaouat;

public class ExceptionsNotExist extends ExceptionUserMessage {

    public ExceptionsNotExist(String whoNotExit, String whereNotExit) {
        super(whoNotExit + " not found in the " +whereNotExit);
    }
}

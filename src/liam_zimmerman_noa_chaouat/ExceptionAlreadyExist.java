package liam_zimmerman_noa_chaouat;

public class ExceptionAlreadyExist extends ExceptionUserMessage {

    public ExceptionAlreadyExist(String whoIsExit, String whereIsExit) {
        super(whoIsExit + " already exist in the " +whereIsExit);
    }
}

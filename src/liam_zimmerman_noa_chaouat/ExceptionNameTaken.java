package liam_zimmerman_noa_chaouat;

public class ExceptionNameTaken extends ExceptionUserMessage {

    public ExceptionNameTaken(String entityType) {
        super(entityType + " Name Taken Choose Another Name. Please enter different name.");
    }
}

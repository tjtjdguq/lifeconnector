package util.file.exception;

public class MissingImplementException extends Exception{
    public MissingImplementException() {
        super("DAO interface is not implemented.");
    }
}

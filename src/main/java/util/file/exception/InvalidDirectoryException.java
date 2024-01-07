package util.file.exception;

public class InvalidDirectoryException extends Exception {
    public InvalidDirectoryException() {
        super("Unable to make directory for excel file");
    }
}

package Database;

public class InvalidPermissionsException extends Exception {
    public InvalidPermissionsException() {
        super("Invalid permissions");
    }
}

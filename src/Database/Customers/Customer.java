package Database.Customers;

import Database.InvalidPermissionsException;

public interface Customer {
    void addUser(String username, String password, String contact, String email) throws Exception;
    void editUserInformation(String username, String objectKey, String objectValue) throws InvalidPermissionsException;
    void removeUser(String username) throws InvalidPermissionsException;
    boolean isUsernameTaken(String username) throws InvalidPermissionsException;
    boolean validateLogin(String username, String password);
    Customers[] getAllUsers() throws InvalidPermissionsException;
    Customers getUser(String username);
}

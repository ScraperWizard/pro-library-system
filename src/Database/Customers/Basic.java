package Database.Customers;

import Database.InvalidPermissionsException;


public class Basic implements Customer {
    Customers globalCustomersObject;

    public Basic() {
        globalCustomersObject = new Customers();
    }

    @Override
    public void addUser(String username, String password, String contact, String email) throws Exception {
        throw new InvalidPermissionsException();
    }

    @Override
    public void editUserInformation(String username, String objectKey, String objectValue) throws InvalidPermissionsException {
        throw new InvalidPermissionsException();
    }

    @Override
    public void removeUser(String username) throws InvalidPermissionsException {
        throw new InvalidPermissionsException();
    }

    @Override
    public boolean isUsernameTaken(String username) throws InvalidPermissionsException {
        throw new InvalidPermissionsException();
    }

    @Override
    public boolean validateLogin(String username, String password) {
        return globalCustomersObject.validateLogin(username, password);
    }

    @Override
    public Customers[] getAllUsers() throws InvalidPermissionsException {
        throw new InvalidPermissionsException();
    }

    @Override
    public Customers getUser(String username) {
        return globalCustomersObject.getUser(username);
    }
}

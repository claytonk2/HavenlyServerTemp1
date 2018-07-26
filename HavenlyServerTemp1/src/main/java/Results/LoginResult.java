package Results;

import Model.User;

public class LoginResult {
    public String firstName;
    public String lastName;
    public String password;
    public String username;
    public String error;
    // add the data in here after pr do it seperate so load times arent slow.
    public LoginResult(String error){
        this.error = error;
    }
    public LoginResult(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.username = user.getUsername();
    }
}

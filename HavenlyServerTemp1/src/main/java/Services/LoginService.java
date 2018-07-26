package Services;

import Database.Daos;
import Model.User;
import Results.LoginResult;
import Results.RegisterResult;

public class LoginService {
    public LoginResult login(User user){
        try {
            User check = Daos.instance().userDao.read(user.getUsername());
            if (check != null) {
                try {
                    if(check.getPassword().toLowerCase().equals(user.getPassword().toLowerCase())){
                        return new LoginResult(check);
                    }
                    else{
                        return new LoginResult("Incorrect Password");
                    }

                } catch (Exception e) {
                    return new LoginResult("Server Error");
                }
            } else {
                return new LoginResult("Username Does Not Exist");
            }
        }
        catch(Exception e){
            return new LoginResult("Server Error");
        }
    }
}

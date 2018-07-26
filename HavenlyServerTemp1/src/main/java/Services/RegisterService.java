package Services;

import Database.Daos;
import Model.User;
import Results.LoginResult;
import Results.RegisterResult;

public class RegisterService {
    public RegisterResult register(User user) {
        try{
            User check = Daos.instance().userDao.read(user.getUsername().toLowerCase());
            if (check == null) {
                try {
                    Daos.instance().userDao.create(user);
                    return new RegisterResult("success");
                } catch (Exception e) {
                    return new RegisterResult("Server Error");
                }
        } else {
            return new RegisterResult("Username Exists Already");
        }
    }
        catch(Exception e){
            return new RegisterResult("Server Error");
    }
}
        }

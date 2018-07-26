package Database;

import Model.User;


import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface IUserDao {
    User read(String username);
    void create(User user);//todo should we return a database access result or something to see if theres an error?
    void update(User user) throws Exception;
    void delete(String username);
    void clear();
}

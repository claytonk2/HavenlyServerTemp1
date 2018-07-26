package Database;

import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelUserDao implements IUserDao {
    private RelDatabaseAccess database;
    public RelUserDao(){
         database = new RelDatabaseAccess();
    }
    @Override
    public User read(String username) {
        String sql = "SELECT username, password, firstName, lastName FROM Users WHERE username = ?";
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(4);
        createTable.add(username);
        Object ret = database.read(createTable);
        List<List<Object>> des = (List<List<Object>>)ret; // might change this bcs it should only return one;
        //List<PlayerDTO> list = new ArrayList<>();
        try {
            //for (int i = 0; i < des.size(); i++) {
              return new User((String) des.get(0).get(2), (String) des.get(0).get(3), (String) des.get(0).get(1), ((String) des.get(0).get(0)));
           // }
        }
        catch (Exception e){}
        // todo deserialize and return
        return null;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO Users(username, password, firstName, lastName) VALUES(?,?,?,?)";
        List<String> info = new ArrayList<>();
        info.add(user.getUsername());
        info.add(user.getPassword());
        info.add(user.getFirstName());
        info.add(user.getLastName());
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(info);
        database.create(createTable);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Users SET password = ? WHERE username = ?"; //WHERE username = ?, password = ?, userId = ?"
        List<String> info = new ArrayList<>();
        info.add((user.getPassword()));
        info.add(user.getUsername());
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(info);
        database.update(createTable);
    }

    @Override
    public void delete(String username) {
        String sql = "DELETE FROM Users WHERE username = ?";
        List<Object> deleteRow = new ArrayList<>();
        deleteRow.add(sql);
        deleteRow.add(username);
        database.delete(deleteRow);
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM Users";
        List<Object> deleteRow = new ArrayList<>();
        deleteRow.add(sql);
        database.delete(deleteRow);
    }
}

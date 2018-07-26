package Database;

public class Daos  {
    public IUserDao userDao;
    private static Daos _instance;
    public static Daos instance() {
        if(_instance == null)
            _instance = new Daos();
        return _instance;
    }
    private Daos(){
        userDao = new RelUserDao();
    }
}

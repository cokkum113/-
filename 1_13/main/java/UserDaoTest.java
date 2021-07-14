import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoTest
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        ConnectionMaker connectionMaker = new NaverConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("amy");

    }
}

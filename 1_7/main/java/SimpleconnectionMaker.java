import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleconnectionMaker
{
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:1234/springbook", "root","6203"
        );
        return c;
    }
}

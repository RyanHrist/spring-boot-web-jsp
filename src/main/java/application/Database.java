package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Database {

    public static Connection connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://eatngreetdb.ctuwvkfyzjbp.us-east-2.rds.amazonaws.com:3306/engdb?user=EatnGreet&password=Eatngreet6");
        return connection;
    }


}
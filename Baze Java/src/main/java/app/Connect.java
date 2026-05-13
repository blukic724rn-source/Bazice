package app;


import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/baze?useSSL=false&serverTimezone=Europe/Belgrade&allowPublicKeyRetrieval=true";
    private static final String user = "root";
    private static final String passwo = "bazeprojekat1";


    public static Connection getKonekcija() throws Exception {
        return DriverManager.getConnection(URL, user, passwo);
    }
}

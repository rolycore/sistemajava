package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection con;

    public Connection getConnection() {
        try {
            //String access = "jdbc:ucanaccess://D:/ventas.accdb";
            String myBD = "jdbc:mysql://localhost:3306/venta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", "root");
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}

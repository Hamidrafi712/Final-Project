/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem.helper;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author user
 */
public class ConnectionHelper {
    //Deklarasi Variable untuk connect ke Database
    private static final String DB_NAME = "db_toko";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/"+ DB_NAME;
    
    //Membuat method untuk mengembalikan nilai Connection
    //juga melalukan pelemparan atau throws ke class SQLExction bila terjadi error
    public static Connection getConnection() throws SQLException{ //digunakan unutk membangun koneksi
        DriverManager.registerDriver(new Driver()); //database server yang dikoneksikan
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //mengoneksikan dengan database server
        return connection; //mengembalikan nilaii conection
    }
}

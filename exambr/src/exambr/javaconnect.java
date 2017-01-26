/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exambr;

/**
 *
 * @author oluoch
 */

import javax.swing.*;
import java.sql.*;

public class javaconnect {

    Connection conn = null;

    public static Connection ConnecrDb(){

       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/exam" ,"root" ,"");
return conn;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null ;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author danilo
 */

    public class FabricaConexao {

    static Connection con = null;
    
//    String Driver = "jdbc:mysql://localhost/db_Bazar";
//    String Usuario = "root";
//    String Senha = "";

    public static Connection pegaConexao() {
        if (con == null) {
            try {
                
                con = DriverManager.getConnection("jdbc:mysql://localhost/db_bazar","root","oitk7nk7");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return con;
    }
}
    


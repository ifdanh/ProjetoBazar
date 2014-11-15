/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazar;

import java.sql.Connection;

/**
 *
 * @author danilo
 */
public class ClienteDAO {
    Connection con;
    public ClienteDAO() {
        con = FabricaConexao.pegaConexao();
    }
    
    
    
}

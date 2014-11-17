package bazar;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author danilo
 */
public class CategoriaDAO {

    Connection con;

    public CategoriaDAO() {
        con = FabricaConexao.pegaConexao();
    }

    public void Salvar(Categoria categoria) {
        String sql = "INSERT INTO Categoria ("
                + "nome,"
                + "status"
                + " VALUES (?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getStatus());
            ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void Alterar(Categoria categoria) {

        String sql = "UPDATE Categoria SET"
                + "nome=?,"
                + "status=?"
                + " WHERE codigo=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getStatus());
            ps.setInt(3, categoria.getCodigo());
            ps.executeQuery();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void Deletar(Categoria categoria) {
    
        String sql = "DELETE FROM Categoria WHERE codigo=?";

        try {
        PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoria.getCodigo());
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
        
        }
    
    }

}

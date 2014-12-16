/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andregaldino
 */
public class PerfilDao {

    private Connection con;
    
    public PerfilDao() {
        this.con = FabricaConexao.pegaConexao();
    }
    
    public void salvar(Perfil   perfil) throws SQLException{
        String sql = "INSERT INTO perfil (funcao, status)"
                + " VALUES (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, perfil.getFuncao());
            ps.setInt(2, perfil.getStatus());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void alterar(Perfil perfil) throws SQLException{
        String sql = "UPDATE perfil set "
                + "funcao = ?,"
                + "status = ?"
                + " WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, perfil.getFuncao());
            ps.setInt(2, perfil.getStatus());
            ps.setInt(3, perfil.getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deletar(Perfil perfil) throws SQLException{
        String sql = "DELETE FROM perfil WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, perfil.getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Perfil> selecionarTodos(boolean ativos){
        List<Perfil> listap = new ArrayList();
        String sql;
        if (ativos) {
            sql = "SELECT * FROM perfil WHERE status = 1";
        }else{
            sql = "SELECT * FROM perfil WHERE status = 0";  
        }
        
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                
                while (rs.next()) {                    
                    listap.add(populaperfil(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
        return listap;
    }
    
    public List<Perfil> selecionarTodos(){
        List<Perfil> listap = new ArrayList();
        String sql = "SELECT * FROM perfil";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                
                while (rs.next()) {                    
                    listap.add(populaperfil(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listap;
    }
    
    private Perfil populaperfil(ResultSet linha){
       Perfil p = new Perfil();
       try {
           p.setFuncao(linha.getString("funcao"));
           p.setStatus(linha.getInt("status"));
           p.setCodigo(linha.getInt("codigo"));
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }finally{
           return p;
       }       
   }
    
    
    
}

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
public class FuncionarioDao {
    
    private Connection con; 
    
    public FuncionarioDao() {
        this.con = FabricaConexao.pegaConexao();
    }
    
    
    
    public void salvar(Funcionario funcionario){
        String sql = "INSERT INTO Funcionario ("
                + "nome,sobrenome,login,senha,email,"
                + "telefone,celular,status,descricao,"
                + "fk_perfil,fk_endereco)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSobrenome());
            ps.setString(3, funcionario.getLogin());
            ps.setString(4, funcionario.getSenha());
            ps.setString(5, funcionario.getEmail());
            ps.setString(6, funcionario.getTelefone());
            ps.setString(7, funcionario.getCelular());
            ps.setInt(8, funcionario.getStatus());
            ps.setString(9, funcionario.getDescricao());
            ps.setInt(10, funcionario.getCargo().getCodigo());
            ps.setInt(11, funcionario.getEndereco().getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void alterar(Funcionario funcionario){
        String sql = "UPDATE Funcionario set "
                + "nome = ?,"
                + "sobrenome = ?,"
                + "login = ?,"
                + "senha = ?,"
                + "email = ?,"
                + "telefone = ?,"
                + "celular = ?,"
                + "status = ?,"
                + "descricao = ?,"
                + "fk_perfil = ?,"
                + "fk_endereco = ?"
                + " WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSobrenome());
            ps.setString(3, funcionario.getLogin());
            ps.setString(4, funcionario.getSenha());
            ps.setString(5, funcionario.getEmail());
            ps.setString(6, funcionario.getTelefone());
            ps.setString(7, funcionario.getCelular());
            ps.setInt(8, funcionario.getStatus());
            ps.setString(9, funcionario.getDescricao());
            ps.setInt(10, funcionario.getCargo().getCodigo());
            ps.setInt(11, funcionario.getEndereco().getCodigo());
            ps.setInt(12, funcionario.getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deletar(Funcionario funcionario){
        String sql = "DELETE FROM Funcionario WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, funcionario.getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Funcionario> selecionarTodos(boolean ativos){
        List<Funcionario> listaf = new ArrayList();
        if (ativos) {
            String sql = "SELECT * FROM Funcionario WHERE status = 1";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                
                while (rs.next()) {                    
                    listaf.add(populafuncionario(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else{
            String sql = "SELECT * FROM Funcionario WHERE status = 0";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                
                while (rs.next()) {                    
                    listaf.add(populafuncionario(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return listaf;
    }
    
    public List<Funcionario> selecionarTodos(){
        List<Funcionario> listaf = new ArrayList();
        String sql = "SELECT * FROM Funcionario WHERE status = 1";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                
                while (rs.next()) {                    
                    listaf.add(populafuncionario(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listaf;
    }
    
    
   private Funcionario populafuncionario(ResultSet linha){
       Funcionario f = new Funcionario();
       try {
           f.setNome(linha.getString("nome"));
           f.setSobrenome(linha.getString("sobrenome"));
           f.setLogin(linha.getString("login"));
           f.setSenha(linha.getString("senha"));
           f.setEmail(linha.getString("email"));
           f.setTelefone(linha.getString("telefone"));
           f.setCelular(linha.getString("celular"));
           f.setStatus(linha.getInt("status"));
           f.setDescricao(linha.getString("descricao"));
           
           Endereco e = new Endereco();
           e.setCodigo(linha.getInt("fk_endereco"));
           
           Perfil p = new Perfil();
           p.setCodigo(linha.getInt("fk_perfil"));
           
           f.setCargo(p);
           f.setEndereco(e);
           
           f.setCodigo(linha.getInt("codigo"));
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }finally{
           return f;
       }       
   }
    
    
    
    
    
}

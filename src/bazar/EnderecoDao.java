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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andregaldino
 */
public class EnderecoDao {
    private Connection con;
    
    public EnderecoDao() {
        this.con = FabricaConexao.pegaConexao();
    }
    
    public int salvar(Endereco  endereco){
        String sql = "INSERT INTO endereco (rua,numero,bairro,cidade,estado,cep,complemento)"
                + " VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, endereco.getRua());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getCidade());
            ps.setString(5, endereco.getEstado());
            ps.setString(6, endereco.getCep());
            ps.setString(7, endereco.getComplemento());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int codigo = 0;
            while(rs.next()){
                codigo = rs.getInt(1);
            }
            return codigo;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public void alterar(Endereco endereco){
        String sql = "UPDATE endereco set "
                + "rua = ?,"
                + "numero = ?, "
                + "bairro = ?, "
                + "cidade = ?, "
                + "estado = ?, "
                + "cep = ?, "
                + "complemento = ? "
                + " WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, endereco.getRua());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getCidade());
            ps.setString(5, endereco.getEstado());
            ps.setString(6, endereco.getCep());
            ps.setString(7, endereco.getComplemento());
            ps.setInt(8, endereco.getCodigo());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deletar(Endereco endereco){
        String sql = "DELETE FROM endereco WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, endereco.getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Endereco> selecionarTodos(){
        List<Endereco> listae = new ArrayList();
        String sql = "SELECT * FROM endereco";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                
                while (rs.next()) {                    
                    listae.add(populaendereco(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listae;
    }
    
    
    
   private Endereco populaendereco(ResultSet linha){
       Endereco end = new Endereco();
       try {
           end.setRua(linha.getString("rua"));
           end.setBairro(linha.getString("bairro"));
           end.setCidade(linha.getString("cidade"));
           end.setEstado(linha.getString("estado"));
           end.setCep(linha.getString("cep"));
           end.setComplemento(linha.getString("complemento"));
           end.setNumero(linha.getInt("numero"));
           end.setCodigo(linha.getInt("codigo"));
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }finally{
           return end;
       }       
   }
    
}

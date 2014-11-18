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
 * @author pc138587
 */
public class ItemvendaDao {
     private Connection con;
    
    public ItemvendaDao() {
        this.con = FabricaConexao.pegaConexao();
    }
    
    public void salvar(Itemvenda   item, int codVenda){
        String sql = "INSERT INTO itemvenda (valorProduto, quantidade, fk_venda, fk_produto)"
                + " VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, item.getVelorProduto());
            ps.setInt(2, item.getQuantidade());
            ps.setInt(3, codVenda);
            ps.setInt(4, item.getItem().getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void alterar(Itemvenda item){
        String sql = "UPDATE Perfil set "
                + "valorProduto = ?, "
                + "quantidade = ? "
                + "WHERE fk_venda = ? AND fk_produto = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, item.getVelorProduto());
            ps.setInt(2, item.getQuantidade());
            ps.setInt(3, item.getPedido().getCodigo());
            ps.setInt(4, item.getItem().getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deletar(Itemvenda item, int codVenda){
        String sql = "DELETE FROM itemvenda WHERE fk_venda = ? AND fk_produto = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codVenda);
            ps.setInt(2, item.getItem().getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
      
    public List<Itemvenda> selecionarTodos(){
        List<Itemvenda> listai = new ArrayList();
        String sql = "SELECT * FROM itemvenda";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);

                while (rs.next()) {                    
                    listai.add(populaitem(rs));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listai;
    }
    
    private Itemvenda populaitem(ResultSet linha){
       Itemvenda i = new Itemvenda();
       try {
            i.setQuantidade(linha.getInt("quantidade"));
            i.setVelorProduto(linha.getDouble("valorProduto"));

            Produto p = new Produto();
            p.setCodigo(linha.getInt("fk_produto"));

            //Venda v = new Venda();
            //v.setCodigo(linha.getInt("fk_venda"));
                      
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }finally{
           return i;
       }       
   }
    
}

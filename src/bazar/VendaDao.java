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
public class VendaDao {
     private Connection con;
    
    public VendaDao() {
        this.con = FabricaConexao.pegaConexao();
    }
    
    public void salvar(Venda venda) throws SQLException{
        String sql = "INSERT INTO venda (valorTotal, formaPagamento,data , status, fk_cliente, fk_funcionario)"
                + " VALUES (?,?,?,?,?,?)";
        try {
            this.con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, venda.getValorTotal());
            ps.setString(2, venda.getFormaPagamento());
            ps.setDate(3, venda.getData());
            ps.setInt(4, venda.getStatus());
            ps.setInt(5, venda.getCliente().getCodigo());
            ps.setInt(6, venda.getFuncionario().getCodigo());
                        
            ps.execute();
            //Por causa da agregação com o itemvenda, é obrigatorio remover cada itemvenda antes de remover a venda
            //usando o con.setAutoCommit(false) inicío uma transação e só termina se tudo ocorrer bem 
            //com o salvar de itemvendaDao
           ResultSet rs = ps.getGeneratedKeys();
           if(rs.next())
               venda.setCodigo(rs.getInt(1));
           
           for(Itemvenda item : venda.getItensVenda()){
               new ItemvendaDao().salvar(item, venda.getCodigo());
           }
           
          this.con.commit();
           
        } catch (SQLException e) {
            this.con.rollback();
            System.out.println(e.getMessage());
        }
    }
    
    public void alterar(Venda venda){
        String sql = "UPDATE venda set "
                + "valorTotal = ?, "
                + "formaPagamento = ?, "
                + "data = ?, "
                + "status = ?, "
                + "fk_cliente = ?, "
                + "fk_funcionario = ? "
                + "WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, venda.getValorTotal());
            ps.setString(2, venda.getFormaPagamento());
            ps.setDate(3, venda.getData());
            ps.setInt(4, venda.getStatus());
            ps.setInt(5, venda.getCliente().getCodigo());
            ps.setInt(6, venda.getFuncionario().getCodigo());
            ps.setInt(7, venda.getCodigo());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deletar(Venda venda) throws SQLException{
        String sql = "DELETE FROM venda WHERE codigo = ?";
        
        try {
            this.con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, venda.getCodigo());
            
            //Por causa da agregação com o itemvenda, é obrigatorio remover cada itemvenda antes de remover a venda
            //usando o con.setAutoCommit(false) inicío uma transação e só termina se tudo ocorrer bem 
            //com o deletar de itemvendaDao
            ResultSet rs = ps.getGeneratedKeys();
           if(rs.next())
               venda.setCodigo(rs.getInt(1));
           
           for(Itemvenda item : venda.getItensVenda()){
               new ItemvendaDao().deletar(item, venda.getCodigo());
           }
            
            ps.execute();
            this.con.commit();
        } catch (SQLException e) {
            this.con.rollback();
            System.out.println(e.getMessage());
        }
    }
     /**
     * @return List<Venda>
     * @param status 
     * 0 - para status Aberto
     * 1 - para Status Fechado - pagamanto não concluido
     * 2 - para Status Fechado - pagamanto concluido
     * 3 - para Status Fechado - fiado
     * 4 - para Status Cancelado
     */
    public List<Venda> selecionarTodos(int status){
        List<Venda> listav = new ArrayList();
        String sql;
        switch(status){
            case 0:
                 sql = "SELECT * FROM venda WHERE status = 0";
                break;
            case 1:
                sql = "SELECT * FROM venda WHERE status = 1"; 
                break;
            case 2:
                sql = "SELECT * FROM venda WHERE status = 2";
            case 3:
                sql = "SELECT * FROM venda WHERE status = 3";
                break;
            case 4:
                sql = "SELECT * FROM venda WHERE status = 4";
            default:
                sql = "SELECT * FROM venda";
                break;
        }
        
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {                    
                    listav.add(populavenda(rs));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
        
        return listav;
    }
    
    public List<Venda> selecionarTodos(){
        List<Venda> listav = new ArrayList();
        String sql = "SELECT * FROM venda";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                
                while (rs.next()) {                    
                    listav.add(populavenda(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listav;
    }
    
    private Venda populavenda(ResultSet linha){
       Venda v = new Venda();
       try {
           v.setFormaPagamento(linha.getString("formaPagamento"));
           v.setCodigo(linha.getInt("codigo"));
           v.setData(linha.getDate("data"));
           v.setStatus(linha.getInt("status"));
           
           Cliente c = new Cliente();
           c.setCodigo(linha.getInt("fk_cliente"));
           
           Funcionario f = new Funcionario();
           f.setCodigo(linha.getInt("fk_funcionario"));

           v.setCliente(c);
           v.setFuncionario(f);
           
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }finally{
           return v;
       }       
   }
    
}

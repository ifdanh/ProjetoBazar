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
 * @author danilo
 */
public class ProdutoDAO {

    Connection con;

    public ProdutoDAO() {
        con = FabricaConexao.pegaConexao();
    }

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (Codigo,nome,valorcusto,valorvenda,estoquemin,estoque,status,fk_categoria)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setFloat(3, produto.getValorCusto());
            ps.setFloat(4, produto.getValorVenda());
            ps.setInt(5, produto.getEstoqueMin());
            ps.setInt(6, produto.getEstoque());
            ps.setInt(7, produto.getStatus());
            ps.setInt(8, produto.getCategoria().getCodigo());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void alterar(Produto produto) {
        String sql = "UPDATE produto set "
                + "nome= ?,"
                + "valorcusto= ?,"
                + "valorvenda= ?,"
                + "estoquemin= ?,"
                + "estoque= ?,"
                + "status= ?"
                + "fk_categoria = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getValorCusto());
            ps.setFloat(3, produto.getValorVenda());
            ps.setInt(4, produto.getEstoqueMin());
            ps.setInt(5, produto.getEstoque());
            ps.setInt(6, produto.getStatus());
            ps.setInt(7, produto.getCategoria().getCodigo());
            ps.setInt(8, produto.getCodigo());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletar(Produto produto) {
        String sql = "DELETE FROM produto WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getCodigo());

            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Produto> selecionarTodos(boolean ativos) {
        List<Produto> listap = new ArrayList();
        String sql;
        if (ativos) {
            sql = "SELECT * FROM produto WHERE status = 1";
        } else {
            sql = "SELECT * FROM produto WHERE status = 0";
        }
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);

                while (rs.next()) {
                    listap.add(populaproduto(rs));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listap;
    }

    public List<Produto> selecionarTodos() {
        List<Produto> listap = new ArrayList();
        String sql = "SELECT * FROM produto";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                listap.add(populaproduto(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listap;
    }

    private Produto populaproduto(ResultSet linha) {
        Produto p = new Produto();
        try {

            p.setCodigo(linha.getInt("Codigo"));
            p.setNome(linha.getString("nome"));
            p.setValorCusto(linha.getFloat("valorcusto"));
            p.setValorVenda(linha.getFloat("valorvenda"));
            p.setEstoqueMin(linha.getInt("estoquemin"));
            p.setEstoque(linha.getInt("estoque"));
            p.setStatus(linha.getInt("status"));
            Categoria c =new Categoria();
            c.setCodigo(linha.getInt("fk_categoria"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return p;
        }
    }

}

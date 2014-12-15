package bazar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void salvar(Categoria categoria) {
        String sql = "INSERT INTO categoria ("
                + "nome,"
                + "status)"
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

    public void alterar(Categoria categoria) {

        String sql = "UPDATE categoria SET "
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

    public void deletar(Categoria categoria) {

        String sql = "DELETE FROM categoria WHERE codigo=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoria.getCodigo());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public List<Categoria> selecionarTodos() {
        List<Categoria> listac = new ArrayList();
        String sql = "SELECT * FROM categoria";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                listac.add((Categoria) rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listac;
    }

    public List<Categoria> selecionarTodos(boolean ativos) {
        List<Categoria> listac = new ArrayList();
        String sql;
        if (ativos) {
            sql = "SELECT * FROM categoria WHERE status = 1";
        } else {
            sql = "SELECT * FROM categoria WHERE status = 0";
        }
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);

                while (rs.next()) {
                    listac.add(populacategoria(rs));
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listac;
    }

    public Categoria selecionarCategoria(int codigo) {
        Categoria c = new Categoria();
        String sql = "SELECT * FROM categoria WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c = populacategoria(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    
    private Categoria populacategoria(ResultSet linha) {
       Categoria c = new Categoria();
        try {
            c.setNome(linha.getString("nome"));
            c.setStatus(linha.getInt("status"));
            c.setCodigo(linha.getInt("codigo"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return c;
        }
    }

}

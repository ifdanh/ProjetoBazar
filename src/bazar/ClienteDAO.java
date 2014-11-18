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
public class ClienteDAO {

    Connection con;

    public ClienteDAO() {
        con = FabricaConexao.pegaConexao();
    }

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO Cliente ("
                + "nome,sobrenome,genero,cpf,rg,"
                + "email,telefone,celular,nascimento,"
                + "status,descricao,fk_endereco)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSobrenome());
            ps.setString(3, cliente.getGenero());
            ps.setString(4, cliente.getCpf());
            ps.setString(5, cliente.getRg());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getTelefone());
            ps.setString(8, cliente.getCelular());
            ps.setString(9, cliente.getNascimento());
            ps.setInt(10, cliente.getStatus());
            ps.setString(11, cliente.getDescricao());
            ps.setInt(12, cliente.getEndereco().getCodigo());

            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void alterar(Cliente cliente) {
        String sql = "UPDATE Cliente set "
                + "nome=?,"
                + "sobrenome=?,"
                + "genero=?,"
                + "cpf=?,"
                + "rg=?,"
                + "email=?,"
                + "telefone=?,"
                + "celular=?,"
                + "nascimento=?,"
                + "status=?,"
                + "descricao=?,"
                + "fk_endereco =?"
                + " WHERE codigo = ?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSobrenome());
            ps.setString(3, cliente.getGenero());
            ps.setString(4, cliente.getCpf());
            ps.setString(5, cliente.getRg());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getTelefone());
            ps.setString(8, cliente.getCelular());
            ps.setString(9, cliente.getNascimento());
            ps.setInt(10, cliente.getStatus());
            ps.setString(11, cliente.getDescricao());
            ps.setInt(12, cliente.getEndereco().getCodigo());
            ps.setInt(13, cliente.getCodigo());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletar(Cliente cliente) {
        String sql = "DELETE FROM Cliente WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getCodigo());

            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cliente> selecionarTodos() {
        List<Cliente> listac = new ArrayList();
        String sql = "SELECT * FROM Cliente";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                listac.add(populacliente(rs));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listac;
    }

    public List<Cliente> selecionarTodos(boolean ativos) {
        List<Cliente> listac = new ArrayList();
        String sql;
        if (ativos) {
            sql = "SELECT * FROM Cliente WHERE status = 1";
        } else {
            sql = "SELECT * FROM Cliente WHERE status = 0";
        }
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);

                while (rs.next()) {
                    listac.add(populacliente(rs));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return listac;
    }

    private Cliente populacliente(ResultSet linha) {
        Cliente c = new Cliente();
        try {
            c.setCodigo(linha.getInt("codigo"));
            c.setNome(linha.getString("nome"));
            c.setSobrenome(linha.getString("sobrenome"));
            c.setGenero(linha.getString("genero"));
            c.setCpf(linha.getString("cpf"));
            c.setRg(linha.getString("rg"));
            c.setEmail(linha.getString("email"));
            c.setTelefone(linha.getString("telefone"));
            c.setCelular(linha.getString("celular"));
            c.setNascimento(linha.getString("nascimento"));
            c.setStatus(linha.getInt("status"));
            c.setDescricao(linha.getString("descricao"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return c;
        }
    }

}

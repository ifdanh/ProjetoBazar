/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazar;

import java.sql.Connection;
import java.sql.Date;
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

    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente ("
                + "nome,sobrenome,genero,cpf,rg,"
                + "email,telefone,celular,nascimento,"
                + "status,descricao,fk_endereco)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            this.con.setAutoCommit(false);

            EnderecoDao daoendereco = new EnderecoDao();
            cliente.getEndereco().setCodigo(daoendereco.salvar(cliente.getEndereco()));

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSobrenome());
            ps.setString(3, cliente.getGenero());
            ps.setString(4, cliente.getCpf());
            ps.setString(5, cliente.getRg());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getTelefone());
            ps.setString(8, cliente.getCelular());
            ps.setDate(9, new Date(cliente.getNascimento().getTime()));
            ps.setInt(10, cliente.getStatus());
            ps.setString(11, cliente.getDescricao());
            ps.setInt(12, cliente.getEndereco().getCodigo());

            ps.execute();
            this.con.commit();
        } catch (SQLException e) {
            this.con.rollback();
            System.out.println(e.getMessage());
        }
    }

    public void alterar(Cliente cliente) {
        String sql = "UPDATE cliente set "
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
            ps.setDate(9, new Date(cliente.getNascimento().getTime()));
            ps.setInt(10, cliente.getStatus());
            ps.setString(11, cliente.getDescricao());
            ps.setInt(12, cliente.getEndereco().getCodigo());
            ps.setInt(13, cliente.getCodigo());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletar(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE codigo = ?";
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
        String sql = "SELECT "
                + "c.codigo as ccodigo, "
                + "c.nome as cnome, "
                + "c.sobrenome as csnome, "
                + "c.email as cemail, "
                + "c.genero as cgenero, "
                + "c.cpf as ccpf, "
                + "c.rg as crg, "
                + "c.nascimento as cdata, "
                + "c.descricao as cdesc, "
                + "c.telefone as ctelefone, "
                + "c.celular as ccelular, "
                + "c.status as cstatus, "
                + "c.fk_endereco, "
                + "e.codigo as ecodigo, "
                + "e.rua as erua, "
                + "e.numero as enumero, "
                + "e.bairro as ebairro, "
                + "e.cidade as ecidade, "
                + "e.estado as eestado, "
                + "e.cep as ecep, "
                + "e.complemento as ecomp "
                + "from cliente as c "
                + "join endereco as e "
                + "on e.codigo = c.fk_endereco ";
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

    public Cliente selecionarCliente(int codigo) {
        String sql = "SELECT "
                + "c.codigo as ccodigo, "
                + "c.nome as cnome, "
                + "c.sobrenome as csnome, "
                + "c.email as cemail, "
                + "c.genero as cgenero, "
                + "c.cpf as ccpf, "
                + "c.rg as crg, "
                + "c.nascimento as cdata, "
                + "c.descricao as cdesc, "
                + "c.telefone as ctelefone, "
                + "c.celular as ccelular, "
                + "c.status as cstatus, "
                + "c.fk_endereco, "
                + "e.codigo as ecodigo, "
                + "e.rua as erua, "
                + "e.numero as enumero, "
                + "e.bairro as ebairro, "
                + "e.cidade as ecidade, "
                + "e.estado as eestado, "
                + "e.cep as ecep, "
                + "e.complemento as ecomp "
                + "from cliente as c "
                + "join endereco as e "
                + "on e.codigo = c.fk_endereco "
                + "WHERE c.codigo = ?";
        Cliente objCliente = new Cliente();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                objCliente = populacliente(rs);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return objCliente;
    }

    public List<Cliente> selecionarTodos(boolean ativos) {
        List<Cliente> listac = new ArrayList();
        String sql;
        if (ativos) {
            sql = "SELECT "
                    + "c.codigo as ccodigo, "
                    + "c.nome as cnome, "
                    + "c.sobrenome as csnome, "
                    + "c.email as cemail, "
                    + "c.genero as cgenero, "
                    + "c.cpf as ccpf, "
                    + "c.rg as crg, "
                    + "c.nascimento as cdata, "
                    + "c.descricao as cdesc, "
                    + "c.telefone as ctelefone, "
                    + "c.celular as ccelular, "
                    + "c.status as cstatus, "
                    + "c.fk_endereco, "
                    + "e.codigo as ecodigo, "
                    + "e.rua as erua, "
                    + "e.numero as enumero, "
                    + "e.bairro as ebairro, "
                    + "e.cidade as ecidade, "
                    + "e.estado as eestado, "
                    + "e.cep as ecep, "
                    + "e.complemento as ecomp "
                    + "from cliente as c "
                    + "join endereco as e "
                    + "on e.codigo = c.fk_endereco "
                    + "WHERE c.status = 1 ";
        } else {
            sql = "SELECT "
                    + "c.codigo as ccodigo, "
                    + "c.nome as cnome, "
                    + "c.sobrenome as csnome, "
                    + "c.email as cemail, "
                    + "c.genero as cgenero, "
                    + "c.cpf as ccpf, "
                    + "c.rg as crg, "
                    + "c.nascimento as cdata, "
                    + "c.descricao as cdesc, "
                    + "c.telefone as ctelefone, "
                    + "c.celular as ccelular, "
                    + "c.status as cstatus, "
                    + "c.fk_endereco, "
                    + "e.codigo as ecodigo, "
                    + "e.rua as erua, "
                    + "e.numero as enumero, "
                    + "e.bairro as ebairro, "
                    + "e.cidade as ecidade, "
                    + "e.estado as eestado, "
                    + "e.cep as ecep, "
                    + "e.complemento as ecomp "
                    + "from cliente as c "
                    + "join endereco as e "
                    + "on e.codigo = c.fk_endereco "
                    + "WHERE c.status = 0 ";
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
            c.setCodigo(linha.getInt("ccodigo"));
            c.setNome(linha.getString("cnome"));
            c.setSobrenome(linha.getString("csnome"));
            c.setGenero(linha.getString("cgenero"));
            c.setCpf(linha.getString("ccpf"));
            c.setRg(linha.getString("crg"));
            c.setEmail(linha.getString("cemail"));
            c.setTelefone(linha.getString("ctelefone"));
            c.setCelular(linha.getString("ccelular"));
            c.setNascimento(linha.getDate("cdata"));
            c.setStatus(linha.getInt("cstatus"));
            c.setDescricao(linha.getString("cdesc"));

            Endereco e = new Endereco();
            e.setCodigo(linha.getInt("fk_endereco"));
            e.setRua(linha.getString("erua"));
            e.setBairro(linha.getString("ebairro"));
            e.setCidade(linha.getString("ecidade"));
            e.setEstado(linha.getString("eestado"));
            e.setComplemento(linha.getString("ecomp"));
            e.setCep(linha.getString("ecep"));
            e.setNumero(linha.getInt("enumero"));
            
            c.setEndereco(e);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return c;
        }
    }

}

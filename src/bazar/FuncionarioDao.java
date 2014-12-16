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
    
    
    
    public void salvar(Funcionario funcionario) throws SQLException{
        String sql = "INSERT INTO funcionario ("
                + "nome,sobrenome,login,senha,email,"
                + "telefone,celular,status,descricao,"
                + "fk_perfil,fk_endereco)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            this.con.setAutoCommit(false);
            EnderecoDao daoenderecao = new EnderecoDao();
            funcionario.getEndereco().setCodigo(daoenderecao.salvar(funcionario.getEndereco()));
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
             this.con.commit();
        } catch (SQLException e) {
            this.con.rollback();
            System.out.println(e.getMessage());
        }
    }
    
    public void alterar(Funcionario funcionario){
        String sql = "UPDATE funcionario set "
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
    
    public void deletar(Funcionario funcionario) throws SQLException{
        String sql = "DELETE FROM funcionario WHERE codigo = ?";
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
        String sql;
        if (ativos) {
            sql = "SELECT f.codigo as fcodigo, " +
            "f.nome as fnome, " +
            "f.sobrenome as fsnome, " +
            "f.email as femail, " +
            "f.login as flogin, " +
            "f.senha as fsenha, " +
            "f.telefone as ftelefone, " +
            "f.celular as fcelular, " +
            "f.descricao as fdesc, " +
            "f.status as fstatus, " +
            "f.fk_perfil, " +
            "f.fk_endereco, " +
            "e.codigo as ecodigo, " +
            "e.rua as erua, " +
            "e.numero as enumero, " +
            "e.bairro as ebairro, " +
            "e.cidade as ecidade, " +
            "e.estado as eestado, " +
            "e.cep as ecep, " +
            "e.complemento as ecomp, " +
            "p.codigo as pcodigo, " +
            "p.funcao as pfuncao, " +
            "p.status as pstatus from funcionario as f  " +
            "join endereco as e  " +
            "on e.codigo = f.fk_endereco  " +
            "join perfil as p " +
            "on f.fk_perfil = p.codigo " +
            "WHERE f.status = 1" ;
        }else{
           sql = "SELECT f.codigo as fcodigo, " +
            "f.nome as fnome, " +
            "f.sobrenome as fsnome, " +
            "f.email as femail, " +
            "f.login as flogin, " +
            "f.senha as fsenha, " +
            "f.telefone as ftelefone, " +
            "f.celular as fcelular, " +
            "f.descricao as fdesc, " +
            "f.status as fstatus, " +
            "f.fk_perfil, " +
            "f.fk_endereco, " +
            "e.codigo as ecodigo, " +
            "e.rua as erua, " +
            "e.numero as enumero, " +
            "e.bairro as ebairro, " +
            "e.cidade as ecidade, " +
            "e.estado as eestado, " +
            "e.cep as ecep, " +
            "e.complemento as ecomp, " +
            "p.codigo as pcodigo, " +
            "p.funcao as pfuncao, " +
            "p.status as pstatus from funcionario as f  " +
            "join endereco as e  " +
            "on e.codigo = f.fk_endereco  " +
            "join perfil as p " +
            "on f.fk_perfil = p.codigo " +
            "WHERE f.status = 0" ;
        }
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
    
    public List<Funcionario> selecionarTodos(){
        List<Funcionario> listaf = new ArrayList();
        String sql = "SELECT f.codigo as fcodigo, " +
            "f.nome as fnome, " +
            "f.sobrenome as fsnome, " +
            "f.email as femail, " +
            "f.login as flogin, " +
            "f.senha as fsenha, " +
            "f.telefone as ftelefone, " +
            "f.celular as fcelular, " +
            "f.descricao as fdesc, " +
            "f.status as fstatus, " +
            "f.fk_perfil, " +
            "f.fk_endereco, " +
            "e.codigo as ecodigo, " +
            "e.rua as erua, " +
            "e.numero as enumero, " +
            "e.bairro as ebairro, " +
            "e.cidade as ecidade, " +
            "e.estado as eestado, " +
            "e.cep as ecep, " +
            "e.complemento as ecomp, " +
            "p.codigo as pcodigo, " +
            "p.funcao as pfuncao, " +
            "p.status as pstatus from funcionario as f  " +
            "join endereco as e  " +
            "on e.codigo = f.fk_endereco  " +
            "join perfil as p " +
            "on f.fk_perfil = p.codigo ";
        
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
    
    public Funcionario selecionarFuncionario(int codigo){
        Funcionario ObjFuncionario = new Funcionario();
        //String sql = "SELECT * FROM funcionario WHERE codigo = ?";
        String sql = "SELECT f.codigo as fcodigo, " +
            "f.nome as fnome, " +
            "f.sobrenome as fsnome, " +
            "f.email as femail, " +
            "f.login as flogin, " +
            "f.senha as fsenha, " +
            "f.telefone as ftelefone, " +
            "f.celular as fcelular, " +
            "f.status as fstatus, " +
            "f.descricao as fdesc, " +
            "f.fk_perfil, " +
            "f.fk_endereco, " +
            "e.codigo as ecodigo, " +
            "e.rua as erua, " +
            "e.numero as enumero, " +
            "e.bairro as ebairro, " +
            "e.cidade as ecidade, " +
            "e.estado as eestado, " +
            "e.cep as ecep, " +
            "e.complemento as ecomp, " +
            "p.codigo as pcodigo, " +
            "p.funcao as pfuncao, " +
            "p.status as pstatus from funcionario as f  " +
            "join endereco as e  " +
            "on e.codigo = f.fk_endereco  " +
            "join perfil as p " +
            "on f.fk_perfil = p.codigo " +
            "WHERE f.codigo = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, codigo);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {                    
                    ObjFuncionario = populafuncionario(rs);
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return ObjFuncionario;
    }
    
    
   private Funcionario populafuncionario(ResultSet linha){
       Funcionario f = new Funcionario();
       try {
           f.setCodigo(linha.getInt("fcodigo"));
           f.setNome(linha.getString("fnome"));
           f.setSobrenome(linha.getString("fsnome"));
           f.setLogin(linha.getString("flogin"));
           f.setSenha(linha.getString("fsenha"));
           f.setEmail(linha.getString("femail"));
           f.setTelefone(linha.getString("ftelefone"));
           f.setCelular(linha.getString("fcelular"));
           f.setStatus(linha.getInt("fstatus"));
           f.setDescricao(linha.getString("fdesc"));
           
           Endereco e = new Endereco();
           e.setCodigo(linha.getInt("fk_endereco"));
           e.setRua(linha.getString("erua"));
           e.setBairro(linha.getString("ebairro"));
           e.setCidade(linha.getString("ecidade"));
           e.setEstado(linha.getString("eestado"));
           e.setComplemento(linha.getString("ecomp"));
           e.setCep(linha.getString("ecep"));
           e.setNumero(linha.getInt("enumero"));
           
           Perfil p = new Perfil();
           p.setCodigo(linha.getInt("fk_perfil"));
           p.setFuncao(linha.getString("pfuncao"));
           p.setStatus(linha.getInt("pstatus"));
           f.setCargo(p);
           f.setEndereco(e);
           
           
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }finally{
           return f;
       }       
   }
    
    
    
    
    
}

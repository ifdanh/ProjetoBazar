/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazar;

/**
 *
 * @author danilo
 */
public class Produto {
    
    private Integer codigo;
    private Integer estoqueMin;
    private Integer estoque;
    private Integer status;
    private String nome;
    private String descrisao;
    private Float valorCusto;
    private Float valorVenda;
    private Categoria categoria;

    public Produto() {
        this.categoria = new Categoria();
    }
    
    

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the estoqueMin
     */
    public Integer getEstoqueMin() {
        return estoqueMin;
    }

    /**
     * @param estoqueMin the estoqueMin to set
     */
    public void setEstoqueMin(Integer estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    /**
     * @return the estoque
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descrisao
     */
    public String getDescrisao() {
        return descrisao;
    }

    /**
     * @param descrisao the descrisao to set
     */
    public void setDescrisao(String descrisao) {
        this.descrisao = descrisao;
    }

    /**
     * @return the valorCusto
     */
    public Float getValorCusto() {
        return valorCusto;
    }

    /**
     * @param valorCusto the valorCusto to set
     */
    public void setValorCusto(Float valorCusto) {
        this.valorCusto = valorCusto;
    }

    /**
     * @return the valorVenda
     */
    public Float getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(Float valorVenda) {
        this.valorVenda = valorVenda;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
   
    
}

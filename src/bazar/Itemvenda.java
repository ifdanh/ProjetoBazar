/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazar;

/**
 *
 * @author pc138587
 */
public class Itemvenda {
    private Double valorProduto;
    private Integer quantidade;
    private Produto item;
    private Venda pedido;
    private Double subTotal;

    public Itemvenda(){
        this.item = new Produto();
        this.pedido = new Venda();
    }
    
    
    /**
     * @return the velorProduto
     */
    public Double getValorProduto() {
        return valorProduto;
    }

    /**
     * @param valorProduto the velorProduto to set
     */
    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the item
     */
    public Produto getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Produto item) {
        this.item = item;
    }

    /**
     * @return the pedido
     */
    public Venda getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Venda pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the subTotal
     */
    public Double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
}

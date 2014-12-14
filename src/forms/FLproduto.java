/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import bazar.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andregaldino
 */
public class FLproduto extends javax.swing.JInternalFrame {
    
    List<bazar.Produto> listaProduto = new ArrayList();
    /**
     * Creates new form FLproduto
     */
    public FLproduto() {
        initComponents();
    }
    
    private void atualizaGrid(){
        bazar.ProdutoDAO daoproduto = new ProdutoDAO();
        listaProduto = daoproduto.selecionarTodos(true);
        
        DefaultTableModel modelo = (DefaultTableModel) gridProduto.getModel();
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        
        for(bazar.Produto p : listaProduto){
            modelo.addRow(new Object[]{p.getCodigo(),p.getNome(),p.getValorVenda()
            ,p.getEstoque(),p.getCategoria().getCodigo()});
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridProduto = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNovo.setText("Cadastrar");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 55));

        btnEditar.setText("Alterar");
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 0, 160, 55));

        btnRemover.setText("Deletar");
        getContentPane().add(btnRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 0, 160, 55));

        gridProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "Preço", "Estoque", "Categoria", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        gridProduto.setMaximumSize(new java.awt.Dimension(1024, 0));
        gridProduto.setMinimumSize(new java.awt.Dimension(1024, 0));
        jScrollPane2.setViewportView(gridProduto);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, 850, 310));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 61, 610, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        FCFuncionario objCadastro = new FCFuncionario();
        objCadastro.setVisible(true);
        this.atualizaGrid();
    }//GEN-LAST:event_btnNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JTable gridProduto;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}

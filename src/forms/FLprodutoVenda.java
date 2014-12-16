/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import bazar.Produto;
import bazar.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andregaldino
 */
public class FLprodutoVenda extends javax.swing.JFrame {

    List<Produto> listaProdutos = new ArrayList();
    List<Produto> listaNovaProdutos = new ArrayList();
    List<Integer> listaCodCategoria = new ArrayList();
    Produto item;

    public FLprodutoVenda() {
        initComponents();
        this.CarregaCBcategoria();
        this.atualizaGrid();

        txtNomeP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                int ccodigo = listaCodCategoria.get(cbCategoriaV.getSelectedIndex());
                //listaProdutos = new ProdutoDAO().selecionarProdutosNomeCategoria(txtNomeP.getText(), ccodigo);
                listaNovaProdutos.removeAll(listaNovaProdutos);
               
                for (Produto p : listaProdutos) {
                    if (p.getNome().contains(txtNomeP.getText())) {
                       listaNovaProdutos.add(p);
                    }
                }
                
                atualizaGrid(listaNovaProdutos);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int ccodigo = listaCodCategoria.get(cbCategoriaV.getSelectedIndex());
                //listaProdutos = new ProdutoDAO().selecionarProdutosNomeCategoria(txtNomeP.getText(), ccodigo);
               listaNovaProdutos.removeAll(listaNovaProdutos);
               
                for (Produto p : listaProdutos) {
                    if (p.getNome().contains(txtNomeP.getText())) {
                       listaNovaProdutos.add(p);
                    }
                }
                
                atualizaGrid(listaNovaProdutos);
                //atualizaGrid(listaProdutos);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                int ccodigo = listaCodCategoria.get(cbCategoriaV.getSelectedIndex());
                //listaProdutos = new ProdutoDAO().selecionarProdutosNomeCategoria(txtNomeP.getText(), ccodigo);
                //atualizaGrid(listaProdutos);
                
               listaNovaProdutos.removeAll(listaNovaProdutos);
               
                for (Produto p : listaProdutos) {
                    if (p.getNome().contains(txtNomeP.getText())) {
                       listaNovaProdutos.add(p);
                    }
                }
                
                atualizaGrid(listaNovaProdutos);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridProdutosV = new javax.swing.JTable();
        btnSelecionar = new javax.swing.JButton();
        bntCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbCategoriaV = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta de Produto"));

        jLabel1.setText("Nome :");

        gridProdutosV.setModel(new javax.swing.table.DefaultTableModel(
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
        gridProdutosV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gridProdutosVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gridProdutosV);

        btnSelecionar.setText("Selecionar");

        bntCancelar.setText("Cancelar");
        bntCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Categoria :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSelecionar)
                                .addGap(18, 18, 18)
                                .addComponent(bntCancelar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNomeP, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 69, Short.MAX_VALUE))
                                    .addComponent(cbCategoriaV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbCategoriaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionar)
                    .addComponent(bntCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bntCancelarActionPerformed

    private void gridProdutosVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gridProdutosVMouseClicked
        int linhaSelecionada = gridProdutosV.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) gridProdutosV.getModel();
        int codigo = ((Integer) modelo.getValueAt(linhaSelecionada, 0));
        
        for (Produto p : listaProdutos) {
            if (p.getCodigo() == codigo) {
                item = p;
            }
        }
        
    }//GEN-LAST:event_gridProdutosVMouseClicked

    private void atualizaGrid() {
        bazar.ProdutoDAO daoproduto = new ProdutoDAO();
        listaProdutos = daoproduto.selecionarTodos(true);

        DefaultTableModel modelo = (DefaultTableModel) gridProdutosV.getModel();
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        for (Produto p : listaProdutos) {
            modelo.addRow(new Object[]{p.getCodigo(), p.getNome(), p.getValorVenda(), p.getEstoque(), p.getCategoria().getNome(), p.getDescricao()});
        }
    }

    private void atualizaGrid(List<Produto> listan) {

        DefaultTableModel modelo = (DefaultTableModel) gridProdutosV.getModel();
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        for (Produto p : listan) {
            modelo.addRow(new Object[]{p.getCodigo(), p.getNome(), p.getValorVenda(), p.getEstoque(), p.getCategoria().getNome(), p.getDescricao()});
        }
    }

    private void CarregaCBcategoria() {
        for (bazar.Categoria c : new bazar.CategoriaDAO().selecionarTodos(true)) {
            cbCategoriaV.addItem(c.getNome());
            listaCodCategoria.add(c.getCodigo());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FLprodutoVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FLprodutoVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FLprodutoVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FLprodutoVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FLprodutoVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCancelar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JComboBox cbCategoriaV;
    private javax.swing.JTable gridProdutosV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtNomeP;
    // End of variables declaration//GEN-END:variables
}
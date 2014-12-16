package forms;

import bazar.Funcionario;
import bazar.FuncionarioDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andregaldino
 */
public class FLfuncionario extends javax.swing.JInternalFrame {

    List<bazar.Funcionario> listaFuncionarios = new ArrayList();
    private int codigo;

    public FLfuncionario() {
        initComponents();
        atualizaGrid();
    }

    private void atualizaGrid() {
        bazar.FuncionarioDao daofuncionario = new FuncionarioDao();
        listaFuncionarios = daofuncionario.selecionarTodos();
        DefaultTableModel modelo = (DefaultTableModel) gridFuncionarios.getModel();

        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        for (bazar.Funcionario f : listaFuncionarios) {
            modelo.addRow(new Object[]{f.getCodigo(), f.getNome() + f.getSobrenome(), f.getLogin(), f.getTelefone(), f.getCelular(), f.getCargo().getFuncao()
            });
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

        jSeparator1 = new javax.swing.JSeparator();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridFuncionarios = new javax.swing.JTable();

        btnNovo.setText("Cadastrar");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setText("Alterar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnRemover.setText("Deletar");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        gridFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "Login", "Telefone", "Celular", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        gridFuncionarios.setMaximumSize(new java.awt.Dimension(1024, 0));
        gridFuncionarios.setMinimumSize(new java.awt.Dimension(1024, 0));
        gridFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gridFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(gridFuncionarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 3, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        FCFuncionario objCadastro = new FCFuncionario();
        objCadastro.setVisible(true);
        this.atualizaGrid();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void gridFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gridFuncionariosMouseClicked
        int linhaSelecionada = gridFuncionarios.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) gridFuncionarios.getModel();
        codigo = (Integer) modelo.getValueAt(linhaSelecionada, 0);
    }//GEN-LAST:event_gridFuncionariosMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (codigo > 0) {
            FCFuncionario objEditar = new FCFuncionario(codigo);
            objEditar.setVisible(true);
            this.atualizaGrid();
        } else {
            JOptionPane.showMessageDialog(null, "Seleciona o Funcionario na Tabela");
        }
        codigo = 0;
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (codigo > 0) {
            int respostaR = JOptionPane.showConfirmDialog(this, "Deseja Realmente Remover o Cliente ?");
            //0 - Sim | 1 - Não | 2 - Cancelar
            if (respostaR == 0) {
                try {
                    new FuncionarioDao().deletar(new FuncionarioDao().selecionarFuncionario(codigo));
                    JOptionPane.showMessageDialog(null, "Funcionario Removido com Sucesso");
                } catch (SQLException ex) {
                    Logger.getLogger(FLfuncionario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Não foi possivel remover o Funcionario. Contate o Desenvolvedor!");
                }
                this.atualizaGrid();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleciona o Funcionario na Tabela");
        }
        codigo = 0;
        
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JTable gridFuncionarios;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}

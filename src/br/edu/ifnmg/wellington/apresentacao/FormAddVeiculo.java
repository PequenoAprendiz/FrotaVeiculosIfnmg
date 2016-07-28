/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.apresentacao;

import br.edu.ifnmg.wellington.entidade.Habilitacao;
import br.edu.ifnmg.wellington.entidade.Veiculo;
import br.edu.ifnmg.wellington.exception.CampoObrigatorioException;
import br.edu.ifnmg.wellington.exception.VerificarVeiculoDuplicado;
import br.edu.ifnmg.wellington.negocio.HabilitacaoBO;
import br.edu.ifnmg.wellington.negocio.UsuarioBO;
import br.edu.ifnmg.wellington.negocio.VeiculoBO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Were
 */
public class FormAddVeiculo extends javax.swing.JFrame {

    private Veiculo veiculoEmEdicao;
    private VeiculoBO veiculoBO;
    private Home home;
    int flagEditarVeiculo = 0;
    List<Habilitacao> habilitacoes;
    String item = " ";

    public FormAddVeiculo(Home telaHome) throws SQLException {
        this.home = telaHome;
        initComponents();
        this.carregaComboHabilitacao();
    }

    public FormAddVeiculo(Veiculo v, Home telaHome, int flag) throws SQLException {
        this.home = telaHome;
        this.veiculoEmEdicao = v;
        this.flagEditarVeiculo = flag;
        initComponents();
        this.carregaComboHabilitacao();
        this.preencherCamposTela(veiculoEmEdicao);
    }

    public FormAddVeiculo() throws SQLException {
        initComponents();
        this.carregaComboHabilitacao();

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
        txtPlaca = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnSalvarVeiculo = new javax.swing.JButton();
        cbxHabilitacao = new javax.swing.JComboBox<>();
        lblHabilitação = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Veículo");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setText("Placa:");

        try {
            txtPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("****_***")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel2.setText("Modelo");

        jLabel3.setText("Marca");

        btnCancelar.setText("Voltar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvarVeiculo.setText("Salvar");
        btnSalvarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarVeiculoActionPerformed(evt);
            }
        });

        cbxHabilitacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblHabilitação.setText("Habilitação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblHabilitação))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(cbxHabilitacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(127, 127, 127))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxHabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHabilitação))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(138, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarVeiculoActionPerformed
        if (flagEditarVeiculo != 1) {
            try {
                this.incluirVeiculo();
                this.home.carregarTabelaDeVeiculos();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FormAddVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FormAddVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FormAddVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.editarVeiculo();                
            } catch (SQLException ex) {
                Logger.getLogger(FormAddVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.flagEditarVeiculo = 0;
        }
    }//GEN-LAST:event_btnSalvarVeiculoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limparCamposTelaNovoVeiculo();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvarVeiculo;
    private javax.swing.JComboBox<String> cbxHabilitacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHabilitação;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JFormattedTextField txtPlaca;
    // End of variables declaration//GEN-END:variables

    private void incluirVeiculo() throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        try {
            this.veiculoBO = new VeiculoBO();
            this.getItemComboHabiliatacao(cbxHabilitacao.getSelectedItem().toString());
            this.recuperarCamposTelaVeiculo();
            this.veiculoBO.incluirUsuario(this.veiculoEmEdicao);
            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!", "Novo Veículo", JOptionPane.INFORMATION_MESSAGE);
            this.limparCamposTelaNovoVeiculo();
        } catch (CampoObrigatorioException c) {
            String mensagen = "Por favor, Preencha todos os campos!\n" + c.getMessage();
            JOptionPane.showMessageDialog(this, mensagen, "Novo Veículo", JOptionPane.ERROR_MESSAGE);
        } catch (VerificarVeiculoDuplicado v) {
            JOptionPane.showMessageDialog(this, v.getMessage(), "Novo Veículo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recuperarCamposTelaVeiculo() {
        if (this.veiculoEmEdicao == null) {
            this.veiculoEmEdicao = new Veiculo();
        }

        if (txtPlaca.getText().equals("    _   ")) {
            throw new CampoObrigatorioException("Campo Placa vazio!");
        } else {
            this.veiculoEmEdicao.setPlaca(txtPlaca.getText());
        }

        if (txtModelo.getText().equals("")) {
            throw new CampoObrigatorioException("Campo Modelo vazio!");
        } else {
            this.veiculoEmEdicao.setModelo(txtModelo.getText());
        }
        
         if (txtMarca.getText().equals("")) {
            throw new CampoObrigatorioException("Campo MARCA vazio!");
        } else {
            this.veiculoEmEdicao.setMarca(txtMarca.getText());
        }

        if (cbxHabilitacao.getSelectedItem().equals("")) {
            throw new CampoObrigatorioException("Selecione o tipo de habilitação");
        } else {
            int posicaoSelecionada = 0;
            for (Habilitacao h : habilitacoes) {
                if (h.getNome().equals(item)) {
                    posicaoSelecionada = h.getId();
                    veiculoEmEdicao.setTipoHabilitacao(posicaoSelecionada);
                }
            }
        }
    }

    private void limparCamposTelaNovoVeiculo() {
        this.txtMarca.setText("");
        this.txtModelo.setText("");
        this.txtPlaca.setText("");
        this.cbxHabilitacao.setSelectedIndex(0);
    }

    private void preencherCamposTela(Veiculo veiculoEmEdicao) {
        this.txtMarca.setText(veiculoEmEdicao.getMarca());
        this.txtModelo.setText(veiculoEmEdicao.getModelo());
        this.txtPlaca.setText(veiculoEmEdicao.getPlaca());
        this.cbxHabilitacao.setSelectedItem(veiculoEmEdicao.getTipoHabilitacao());
    }

    private void editarVeiculo() throws SQLException {
        try {
            this.veiculoBO = new VeiculoBO();
            int i = JOptionPane.showConfirmDialog(this, "Tem Certeza?", "Edição de veículo", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.NO_OPTION) {
                this.dispose();
            } else {
                this.getItemComboHabiliatacao(cbxHabilitacao.getSelectedItem().toString());
                this.recuperarCamposTelaVeiculo();
                this.veiculoBO.atualizarVeiculo(veiculoEmEdicao);
                JOptionPane.showMessageDialog(this, "Alterado com sucesso!", "Edição de veículo", JOptionPane.INFORMATION_MESSAGE);
                this.limparCamposTelaNovoVeiculo();
                this.home.carregarTabelaDeVeiculos();
                this.dispose();
            }
        } catch (RuntimeException r) {
            JOptionPane.showMessageDialog(this, "algum campo está vazio", "Edição de veículo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void carregaComboHabilitacao() throws SQLException {
        this.habilitacoes = new ArrayList<>();
        HabilitacaoBO habilitacaoBO = new HabilitacaoBO();
        this.habilitacoes = habilitacaoBO.buscarHabilitacoes();
        this.cbxHabilitacao.removeAllItems();
        this.cbxHabilitacao.addItem("");
        for (Habilitacao h : this.habilitacoes) {
            cbxHabilitacao.addItem(h.getNome());
        }

    }

    private void getItemComboHabiliatacao(String toString) {
        this.item = toString;
    }
}

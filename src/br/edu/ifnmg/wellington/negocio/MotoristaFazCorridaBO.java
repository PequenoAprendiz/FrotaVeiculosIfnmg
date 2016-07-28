/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.negocio;

import br.edu.ifnmg.wellington.entidade.Corrida;
import br.edu.ifnmg.wellington.entidade.Habilitacao;
import br.edu.ifnmg.wellington.entidade.Motorista;
import br.edu.ifnmg.wellington.entidade.MotoristaFazCorrida;
import br.edu.ifnmg.wellington.entidade.Veiculo;
import br.edu.ifnmg.wellington.exception.CorridaCorridaSemMotoristas;
import br.edu.ifnmg.wellington.exception.MotoristaEstaEmOutraCorrida;
import br.edu.ifnmg.wellington.exception.MotoristaNaoPodeDigirVeiculoException;
import br.edu.ifnmg.wellington.persistencia.MotoristaFazCorridaDAO;
import com.sun.org.apache.xml.internal.security.utils.EncryptionConstants;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Were
 */
public class MotoristaFazCorridaBO {

    private List<Motorista> motoristas;
    private List<Veiculo> veiculos;
    private List<Corrida> corridas;
    private List<Habilitacao> habilitacoes;
    List<MotoristaFazCorrida> motoristaFazCorridas;
    private HabilitacaoBO habilitacaBO;
    private VeiculoBO veiculoBO;
    private MotoristaBO motoristaBO;
    private MotoristaFazCorrida motoristaParaNovaCorrida;
    MotoristaFazCorridaDAO motoristaFazCorridaDAO = new MotoristaFazCorridaDAO();
    int habilitacaoMotorista = 0;
    int habilitacaoVeiculo = 0;
    String habVeiculo = "";
    String habMotorista = "";

    public void definirDadosCorrida(MotoristaFazCorrida motoristaParaNovaCorrida, List<Corrida> corridas, List<Veiculo> veiculos, List<Motorista> motoristas) throws SQLException {
        this.corridas = corridas;
        this.veiculos = veiculos;
        this.motoristas = motoristas;
        this.motoristaParaNovaCorrida = motoristaParaNovaCorrida;
        this.verificarMotoristaEmOutrasCorridas(motoristaParaNovaCorrida);
        this.carregaListaHabilitacao();
        this.recuperarHabilitacoes();

    }

    public void verificarMotoristaEmOutrasCorridas(MotoristaFazCorrida motoristaParaNovaCorrida) throws SQLException {
        this.motoristaFazCorridas = new ArrayList<>();
        this.motoristaFazCorridas = motoristaFazCorridaDAO.buscarCorridas();
        //if (motoristaFazCorridas != null) {
        for (MotoristaFazCorrida m : this.motoristaFazCorridas) {
            if (m.getIdMotorista() == motoristaParaNovaCorrida.getIdMotorista() && m.getIdCorrida() == motoristaParaNovaCorrida.getIdCorrida()) {
                throw new MotoristaEstaEmOutraCorrida("Motorista selecionado já está nesta corrida!");
            }

            if (m.getIdCorrida() != motoristaParaNovaCorrida.getIdCorrida() && m.getIdMotorista() == motoristaParaNovaCorrida.getIdMotorista()) {
                throw new MotoristaEstaEmOutraCorrida("Motorista selecionado já está em outra corrida!");
            }
        }
        // }
    }

    private void carregaListaHabilitacao() throws SQLException {
        this.habilitacoes = new ArrayList<>();
        HabilitacaoBO habilitacaoBO = new HabilitacaoBO();
        this.habilitacoes = habilitacaoBO.buscarHabilitacoes();
    }

    private void recuperarHabilitacoes() throws SQLException {
        int idMotorista = this.motoristaParaNovaCorrida.getIdMotorista();

        for (Motorista m : this.motoristas) {
            if (m.getId() == idMotorista) {
                this.habilitacaoMotorista = m.getTipoHabilitacao();
            }
        }
        for (Corrida c : this.corridas) {
            for (Veiculo v : veiculos) {
                if (c.getIdVeiculo() == v.getId()) {
                    this.habilitacaoVeiculo = v.getTipoHabilitacao();
                }
            }
        }
        for (Habilitacao h : this.habilitacoes) {
            if (habilitacaoMotorista == h.getId()) {
                habMotorista = h.getNome();
            }
            if (this.habilitacaoVeiculo == h.getId()) {
                habVeiculo = h.getNome();
            }
        }
        this.inserir(habMotorista, habVeiculo);
    }

    private void inserir(String habMotorista, String habVeiculo) throws SQLException {
        if (habMotorista.equals("E") && (habVeiculo.equals("E") || habVeiculo.equals("D") || habVeiculo.equals("C") || habVeiculo.equals("B") || habVeiculo.equals("A"))) {
            motoristaFazCorridaDAO.insert(this.motoristaParaNovaCorrida);
        } else if (habMotorista.equals("D") && (habVeiculo.equals("D") || habVeiculo.equals("C") || habMotorista.equals("B") || habVeiculo.equals("A"))) {
            motoristaFazCorridaDAO.insert(this.motoristaParaNovaCorrida);
        } else if (habMotorista.equals("C") && (habVeiculo.equals("C") || habVeiculo.equals("B") || habMotorista.equals("A"))) {
            motoristaFazCorridaDAO.insert(this.motoristaParaNovaCorrida);
        } else if (habMotorista.equals("B") && (habVeiculo.equals("B") || habVeiculo.equals("A"))) {
            motoristaFazCorridaDAO.insert(this.motoristaParaNovaCorrida);
        } else if (habMotorista.equals("A") && habVeiculo.equals("A")) {
            motoristaFazCorridaDAO.insert(this.motoristaParaNovaCorrida);
        } else {
            throw new MotoristaNaoPodeDigirVeiculoException("A habilitação do motorista não corresponde com a neccessária para o véiculo desta corrida!");
        }
    }

    public void deletar(int idcorrida) throws SQLException {
        this.motoristaFazCorridaDAO.removerCorrida(idcorrida);
    }

}

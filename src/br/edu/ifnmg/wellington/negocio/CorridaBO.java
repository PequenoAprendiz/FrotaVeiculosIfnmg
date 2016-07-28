/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.negocio;

import br.edu.ifnmg.wellington.entidade.Corrida;
import br.edu.ifnmg.wellington.exception.VerificarVeiculoDuplicado;
import br.edu.ifnmg.wellington.exception.VerificarVeiculoEmCorrida;
import br.edu.ifnmg.wellington.persistencia.CorridaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Were
 */
public class CorridaBO {

    public void incluirCorrida(Corrida corridaEmEdicao) throws SQLException, Exception {
        CorridaDAO corridaDAO = new CorridaDAO();
        this.verificaVeiculoEmCorrida(corridaEmEdicao);
        corridaDAO.incluirCorrida(corridaEmEdicao);
    }

    public List<Corrida> buscarCorridas() throws SQLException {
        CorridaDAO corridaDAO = new CorridaDAO();
        return corridaDAO.buscarCorridas();
    }

    public void atualizarCorrida(Corrida corridaEmEdicao) throws Exception {
        CorridaDAO corridaDAO = new CorridaDAO();
        corridaDAO.atualizarCorrida(corridaEmEdicao);
    }

    public void deletarCorrida(Corrida corridaSelecionado) throws SQLException {
        CorridaDAO corridaDAO = new CorridaDAO();
        corridaDAO.removerCorrida(corridaSelecionado);
    }

    public void verificaVeiculoEmCorrida(Corrida corridaEmEdicao) throws SQLException, Exception {
        CorridaDAO corridaDAO = new CorridaDAO();
        List<Corrida> corridas = corridaDAO.buscarCorridas();
        for(Corrida c: corridas){
            if(c.getIdVeiculo() == corridaEmEdicao.getIdVeiculo()){
                throw new VerificarVeiculoEmCorrida("O veículo com a placa selecionada já está em uma corrida!");                 
            }
        }
    }

}

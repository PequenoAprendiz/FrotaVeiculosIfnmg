/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.negocio;

import br.edu.ifnmg.wellington.entidade.Veiculo;
import br.edu.ifnmg.wellington.exception.VerificarVeiculoDuplicado;
import br.edu.ifnmg.wellington.persistencia.VeiculoDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Were
 */
public class VeiculoBO {

    private VeiculoBO veiculoBO;
    private List<Veiculo> veiculos;

    public void incluirUsuario(Veiculo veiculoEmEdicao) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        this.verificarVeiculoDuplicado(veiculoEmEdicao);
        veiculoDAO.incluirVeiculo(veiculoEmEdicao);
    }

    public void verificarVeiculoDuplicado(Veiculo veiculoEmEdicao) throws SQLException {
        this.veiculoBO = new VeiculoBO();
        this.veiculos = veiculoBO.buscarVeiculos();
        String placaRecuperada = veiculoEmEdicao.getPlaca();
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placaRecuperada)) {
                throw new VerificarVeiculoDuplicado();
            }
        }
        
    }
    
    public List<Veiculo> buscarVeiculos() throws SQLException {
    VeiculoDAO veiculoDAO = new VeiculoDAO();
        return veiculoDAO.buscarVeiculos();    
    }

    public void atualizarVeiculo(Veiculo veiculoEmEdicao) throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.atualizar(veiculoEmEdicao);
    }
        
    public void deletarVeiculo(Veiculo veiculoEmEdicao) throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.removerUsuario(veiculoEmEdicao);
    }
    
}

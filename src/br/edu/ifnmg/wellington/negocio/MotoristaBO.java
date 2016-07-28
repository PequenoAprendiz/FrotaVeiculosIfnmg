/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.negocio;

import br.edu.ifnmg.wellington.entidade.Motorista;
import br.edu.ifnmg.wellington.entidade.Veiculo;
import br.edu.ifnmg.wellington.exception.VerificarMotoristaDuplicado;
import br.edu.ifnmg.wellington.exception.VerificarVeiculoDuplicado;
import br.edu.ifnmg.wellington.persistencia.MotoristaDAO;
import br.edu.ifnmg.wellington.persistencia.VeiculoDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Were
 */
public class MotoristaBO {

    private MotoristaBO motoristaBO ;
     private List<Motorista> motoristas;

    public List<Motorista> buscarMotoristas() throws SQLException {
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.buscarMotoristas();
    }

    public void incluirMotorista(Motorista motoristaEmEdicao) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        this.verificarMotoristaDuplicado(motoristaEmEdicao);
        motoristaDAO.incluirMotorista(motoristaEmEdicao);
    }

    public void verificarMotoristaDuplicado(Motorista motoristaEmEdicao) throws SQLException {
        this.motoristaBO = new MotoristaBO();
        this.motoristas = motoristaBO.buscarMotoristas();
        String NomeMotoristaRecuperado = motoristaEmEdicao.getNome();
        for (Motorista m : motoristas) {
            if (m.getNome().equals(NomeMotoristaRecuperado)) {
                throw new VerificarMotoristaDuplicado();
            }
        }
    }
    
    public void atualizarMotorista(Motorista motoristaEmEdicao) throws SQLException {
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        motoristaDAO.atualizar(motoristaEmEdicao);
    }

    public void deletarMotorista(Motorista motoristaEmEdicao) throws SQLException {
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        motoristaDAO.removerUsuario(motoristaEmEdicao);
    }

}

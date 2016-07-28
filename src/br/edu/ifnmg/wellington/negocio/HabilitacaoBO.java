/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.negocio;

import br.edu.ifnmg.wellington.entidade.Habilitacao;
import br.edu.ifnmg.wellington.persistencia.HabilitacaoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Were
 */
public class HabilitacaoBO {
     public List<Habilitacao> buscarHabilitacoes() throws SQLException {
        HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
        return habilitacaoDAO.buscarHabilitacoes();
    }
}

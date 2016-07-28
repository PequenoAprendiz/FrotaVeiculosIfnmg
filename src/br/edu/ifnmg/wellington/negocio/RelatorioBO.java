/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.negocio;

import br.edu.ifnmg.wellington.entidade.Relatorio;
import br.edu.ifnmg.wellington.persistencia.RelatorioDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Were
 */
public class RelatorioBO {

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Relatorio> totalCorridaPorMotorista() throws SQLException{
        RelatorioDAO relatorioTotalCorrida = new RelatorioDAO();
        return relatorioTotalCorrida.recuperaTotalCorridaPorMotorista();
    }
    
    public List<Relatorio> totalCorridaVeiculos() throws SQLException{
        RelatorioDAO relatorioTotalCorridaVeiculos = new RelatorioDAO();
        return relatorioTotalCorridaVeiculos.recuperaTotalCorridaVeiculos();
    }
}

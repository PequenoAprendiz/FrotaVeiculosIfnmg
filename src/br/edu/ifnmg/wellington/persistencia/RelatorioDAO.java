/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.persistencia;

import br.edu.ifnmg.wellington.entidade.Relatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Were
 */
public class RelatorioDAO {

public static final String SQL_TOTAL_CORRIDA_VEICULOS = "select  v.modelo, v.placa, count(mfc.id_corrida), sum(c.quilometragem) from veiculo v join corrida c on c.id_veiculo  = v.id  join motoristafazcorrida mfc on mfc.id_corrida  = c.id group by v.modelo , v.placa ";
public static final String SQL_TOTAL_CORRIDA_POR_MOTORISTA = "SELECT M.NOME, H.NOME, COUNT(MFC.ID_MOTORISTA) FROM MOTORISTAFAZCORRIDA MFC JOIN MOTORISTA M ON M.ID = MFC.ID_MOTORISTA  JOIN CORRIDA C ON C.ID = MFC.ID_CORRIDA  JOIN HABILITACAO H ON H.ID = M.ID_HABILITACAO WHERE C.DATACHEGADA   IS NOT NULL  GROUP BY M.NOME, H.NOME";

    public List<Relatorio> recuperaTotalCorridaPorMotorista() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Relatorio> listaTotalCorridas = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_TOTAL_CORRIDA_POR_MOTORISTA);

            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Relatorio resultadoTotal = this.extrairLinhaResultado(resultado);
                //Adiciona um item à lista que será retornada
                listaTotalCorridas.add(resultadoTotal);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaTotalCorridas;
    }

    private Relatorio extrairLinhaResultado(ResultSet resultado) throws SQLException {
        Relatorio totalCorridasMotorista = new Relatorio();
        totalCorridasMotorista.setNomeMotorista(resultado.getString(1));
        totalCorridasMotorista.setHabilitacao(resultado.getString(2));
        totalCorridasMotorista.setTotalCorridasPorMotorista(resultado.getInt(3));

        return totalCorridasMotorista;
    }

    
     public List<Relatorio> recuperaTotalCorridaVeiculos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Relatorio> listaTotalCorridaVeiculos = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_TOTAL_CORRIDA_VEICULOS);

            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Relatorio resultadoTotal = this.extrairLinhaResultadoRecuperaTotalCorridaVeiculos(resultado);
                //Adiciona um item à lista que será retornada
                listaTotalCorridaVeiculos.add(resultadoTotal);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaTotalCorridaVeiculos;
    }

    private Relatorio extrairLinhaResultadoRecuperaTotalCorridaVeiculos(ResultSet resultado) throws SQLException {
        Relatorio totalCorridasVeiculo = new Relatorio();
        totalCorridasVeiculo.setModelo(resultado.getString(1));
        totalCorridasVeiculo.setPlaca(resultado.getString(2));
        totalCorridasVeiculo.setTotalCorridasVeiculo(resultado.getInt(3));
        totalCorridasVeiculo.setSomatorioQuilometragem(resultado.getInt(4));
        return totalCorridasVeiculo;
    }

}

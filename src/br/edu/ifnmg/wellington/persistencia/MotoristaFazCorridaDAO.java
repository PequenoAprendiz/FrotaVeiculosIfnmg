/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.persistencia;

import br.edu.ifnmg.wellington.entidade.MotoristaFazCorrida;
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
public class MotoristaFazCorridaDAO {

    private static final String SQL_INSERT = "INSERT INTO MOTORISTAFAZCORRIDA (ID_CORRIDA , ID_MOTORISTA) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM MOTORISTAFAZCORRIDA WHERE ID_CORRIDA = ?";
    private static final String SQL_SELECT = "SELECT ID_CORRIDA, ID_MOTORISTA  FROM MOTORISTAFAZCORRIDA";

    public void insert(MotoristaFazCorrida motoristaParaNovaCorrida) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setInt(1, motoristaParaNovaCorrida.getIdCorrida());
            comando.setInt(2, motoristaParaNovaCorrida.getIdMotorista());

            //Executa o comando
            comando.execute();
            //Persiste o comando no banco de dados
            conexao.commit();
            //System.out.println("Manobra cadastrada com sucesso!");
        } catch (Exception e) {
            //Caso aconteça alguma exeção é feito um rollback para o banco de
            //dados retornar ao seu estado anterior.
            if (conexao != null) {
                conexao.rollback();
            }
            throw e;
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
    }

    public void removerCorrida(int idcorrida) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        ///id = 4;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_DELETE);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setInt(1, idcorrida);
            //Executa o comando
            comando.execute();
            conexao.commit();

        } catch (Exception e) {
            //Caso aconteça alguma exeção é feito um rollback para o banco de
            //dados retornar ao seu estado anterior.
            if (conexao != null) {
                conexao.rollback();
            }
            throw e;
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
    }

    public List<MotoristaFazCorrida> buscarCorridas() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<MotoristaFazCorrida> corridas = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                MotoristaFazCorrida mmotoristafazcorrida = this.extrairLinhaResultadoBuscarTodasCorridas(resultado);
                //Adiciona um item à lista que será retornada
                corridas.add(mmotoristafazcorrida);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return corridas;
    }

    private MotoristaFazCorrida extrairLinhaResultadoBuscarTodasCorridas(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        MotoristaFazCorrida m = new MotoristaFazCorrida();
        m.setIdCorrida(resultado.getInt(1));
        m.setIdMotorista(resultado.getInt(2));
        return m;

    }
}

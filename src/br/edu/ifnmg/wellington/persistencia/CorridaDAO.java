/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.persistencia;

import br.edu.ifnmg.wellington.entidade.Corrida;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Were
 */
public class CorridaDAO {

    private static final String SQL_INSERT = "INSERT INTO CORRIDA (DESTINO, DATASAIDA, PREVISAOCHEGADA, QUILOMETRAGEM, ID_VEICULO ) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_CORRIDA = "SELECT ID, DESTINO, DATASAIDA, PREVISAOCHEGADA, DATACHEGADA, QUILOMETRAGEM, ID_VEICULO  FROM CORRIDA ORDER BY DATACHEGADA";
    private static final String SQL_UPDATE_CORRIDA = "UPDATE CORRIDA SET DESTINO = ?,  PREVISAOCHEGADA = ?, DATACHEGADA = ?, QUILOMETRAGEM = ?, ID_VEICULO = ?   WHERE ID = ?";
    private static final String SQL_DELETE_CORRIDA = "DELETE FROM CORRIDA WHERE ID = ?";

    public void incluirCorrida(Corrida corrida) throws SQLException, Exception {
        Connection conexao = null;
        PreparedStatement comando = null;
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, corrida.getDestino());

            Date dataSys = new Date(System.currentTimeMillis());
            comando.setDate(2, dataSys);

            java.sql.Date dataPrevisaoChegada = new java.sql.Date(formatador.parse(corrida.getPrevisaoCheagada()).getTime());
            comando.setDate(3, dataPrevisaoChegada);

            comando.setDouble(4, corrida.getQuilometragem());
            comando.setInt(5, corrida.getIdVeiculo());
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

    public List<Corrida> buscarCorridas() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Corrida> corridas = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_CORRIDA);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Corrida corrida = this.extrairLinhaResultadoBuscarTodasCorridas(resultado);
                //Adiciona um item à lista que será retornada
                corridas.add(corrida);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return corridas;
    }

    private Corrida extrairLinhaResultadoBuscarTodasCorridas(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Corrida c = new Corrida();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");

        c.setIdcorrida(resultado.getInt(1));
        c.setDestino(resultado.getString(2));

        c.setDataSaida(formatador.format(resultado.getDate(3)));
        c.setPrevisaoCheagada(formatador.format(resultado.getDate(4).getTime()));
        if (resultado.getDate(5) == null) {
            c.setDataChegada(resultado.getString(5));
        } else {
            c.setDataChegada(formatador.format(resultado.getDate(5).getTime()));
        }
        c.setQuilometragem(resultado.getInt(6));
        c.setIdVeiculo(resultado.getInt(7));
        return c;

    }

    public void atualizarCorrida(Corrida corridaEmEdicao) throws SQLException, Exception {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_UPDATE_CORRIDA);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, corridaEmEdicao.getDestino());

//            Date dataSys = new Date(System.currentTimeMillis());
//            comando.setDate(2, dataSys);

            java.sql.Date dataPrevisaoChegada = new java.sql.Date(formatador.parse(corridaEmEdicao.getPrevisaoCheagada()).getTime());
            comando.setDate(2, dataPrevisaoChegada);
            
            java.sql.Date dataChegada = new java.sql.Date(formatador.parse(corridaEmEdicao.getDataChegada()).getTime());
            comando.setDate(3, dataChegada);
            comando.setDouble(4, corridaEmEdicao.getQuilometragem());
            comando.setInt(5, corridaEmEdicao.getIdVeiculo());
            comando.setInt(6, corridaEmEdicao.getIdcorrida());
            //Executa o comando
            comando.execute();
            //Persiste o comando no banco de dados
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
       public void removerCorrida(Corrida corridaEmEdicao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        ///id = 4;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_DELETE_CORRIDA);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setInt(1, corridaEmEdicao.getIdcorrida());
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
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.persistencia;

import br.edu.ifnmg.wellington.entidade.Habilitacao;
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
public class HabilitacaoDAO {

    private static final String SQL_INSERT = "INSERT INTO HABILITACAO (NOME) VALUES (?)";
    private static final String SQL_SELECT_HABILITACAO = "SELECT ID, NOME FROM HABILITACAO";
    // private static final String SQL_UPDATE_MOTORISTA = "UPDATE MOTORISTA SET NOME = ?, TIPOHABILITACAO = ? WHERE ID = ?";
    private static final String SQL_DELETE_HABILITACAO = "DELETE FROM HABILITACAO WHERE ID = ?";

    public void incluirHabilitacao(Habilitacao habilitacao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, habilitacao.getNome());
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

    public List<Habilitacao> buscarHabilitacoes() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Habilitacao> habilitacoes = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_HABILITACAO);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Habilitacao habilitacao = this.extrairLinhaResultadoBuscarTodasHabilitacoes(resultado);
                //Adiciona um item à lista que será retornada
                habilitacoes.add(habilitacao);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return habilitacoes;
    }

    private Habilitacao extrairLinhaResultadoBuscarTodasHabilitacoes(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Habilitacao h = new Habilitacao();
        h.setId(resultado.getInt(1));
        h.setNome(resultado.getString(2));        
        return h;
    }

//    public void atualizar(Habilitacao habilitacao) throws SQLException {
//        Connection conexao = null;
//        PreparedStatement comando = null;
//        try {
//            //Recupera a conexão
//            conexao = BancoDadosUtil.getConnection();
//            //Cria o comando de inserir dados
//            comando = conexao.prepareStatement(SQL_UPDATE_HABILITACAO);
//            //Atribui os parâmetros (Note que no BD o index inicia por 1)     
//            comando.setString(1, habilitacao.getNome());           
//            comando.setInt(2, habilitacao.getId());
//            //Executa o comando
//            comando.execute();
//            //Persiste o comando no banco de dados
//            conexao.commit();
//        } catch (Exception e) {
//            //Caso aconteça alguma exeção é feito um rollback para o banco de
//            //dados retornar ao seu estado anterior.
//            if (conexao != null) {
//                conexao.rollback();
//            }
//            throw e;
//        } finally {
//            //Todo objeto que referencie o banco de dados deve ser fechado
//            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
//        }
//    }

    public void removerUsuario(Habilitacao habilitacao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        ///id = 4;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_DELETE_HABILITACAO);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setInt(1, habilitacao.getId());
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

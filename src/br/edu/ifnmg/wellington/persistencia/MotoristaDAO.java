/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.persistencia;

import br.edu.ifnmg.wellington.entidade.Motorista;
import br.edu.ifnmg.wellington.entidade.Veiculo;
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
public class MotoristaDAO {

    private static final String SQL_INSERT = "INSERT INTO MOTORISTA (NOME, ID_HABILITACAO ) VALUES (?,?)";
    private static final String SQL_SELECT_MOTORISTA = "SELECT ID, NOME, ID_HABILITACAO FROM MOTORISTA";
    private static final String SQL_UPDATE_MOTORISTA = "UPDATE MOTORISTA SET NOME = ?, ID_HABILITACAO = ? WHERE ID = ?";
    private static final String SQL_DELETE_MOTORISTA= "DELETE FROM MOTORISTA WHERE ID = ?";

    public void incluirMotorista(Motorista motorista) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, motorista.getNome());
            comando.setInt(2, motorista.getTipoHabilitacao());
            
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

    public List<Motorista> buscarMotoristas() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Motorista> motoristas = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_MOTORISTA);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Motorista motorista = this.extrairLinhaResultadoBuscarTodosMotoristas(resultado);
                //Adiciona um item à lista que será retornada
                motoristas.add(motorista);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return motoristas;
    }

    private Motorista extrairLinhaResultadoBuscarTodosMotoristas(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Motorista m = new Motorista();
        m.setId(resultado.getInt(1));
        m.setNome(resultado.getString(2));
        m.setTipoHabilitacao(resultado.getInt(3));        
        return m;
    }

    public void atualizar(Motorista motorista) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_UPDATE_MOTORISTA);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)     
            comando.setString(1, motorista.getNome());
            comando.setInt(2, motorista.getTipoHabilitacao());           
            comando.setInt(3, motorista.getId());
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

    public void removerUsuario(Motorista motorista) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        ///id = 4;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_DELETE_MOTORISTA);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setInt(1, motorista.getId());
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

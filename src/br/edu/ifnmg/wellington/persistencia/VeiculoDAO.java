/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.persistencia;

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
public class VeiculoDAO {

    private static final String SQL_INSERT = "INSERT INTO VEICULO (MARCA, MODELO,PLACA, ID_HABILITACAO) VALUES (?, ?, ?,?)";
    private static final String SQL_SELECT_VEICULOS = "SELECT ID, MARCA, MODELO, PLACA, ID_HABILITACAO  FROM VEICULO";
    private static final String SQL_UPDATE_VEICULOS = "UPDATE VEICULO SET MARCA = ?, MODELO = ?, PLACA = ?, ID_HABILITACAO = ? WHERE ID = ?";
    private static final String SQL_DELETE_VEICULO = "DELETE FROM VEICULO WHERE PLACA = ?";
    private static final String SQL_SELECT_VEICULOS_DISPONIVEIS = "SELECT  V.PLACA, V.MODELO FROM VEICULO V JOIN CORRIDA C ON V.ID = C.ID_VEICULO WHERE C.DATACHEGADA  = NULL ";
  
    public void incluirVeiculo(Veiculo veiculoEmEdicao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, veiculoEmEdicao.getMarca());
            comando.setString(2, veiculoEmEdicao.getModelo());
            comando.setString(3, veiculoEmEdicao.getPlaca());
            comando.setInt(4, veiculoEmEdicao.getTipoHabilitacao());
            

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

    public List<Veiculo> buscarVeiculos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_VEICULOS);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Veiculo veiculo = this.extrairLinhaResultadoBuscarTodosVeiculos(resultado);
                //Adiciona um item à lista que será retornada
                veiculos.add(veiculo);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return veiculos;
    }
    public List<Veiculo> buscarVeiculosDisponiveis() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_VEICULOS_DISPONIVEIS);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Veiculo veiculo = this.extrairLinhaResultadoBuscarTodosVeiculos(resultado);
                //Adiciona um item à lista que será retornada
                veiculos.add(veiculo);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return veiculos;
    }

    private Veiculo extrairLinhaResultadoBuscarTodosVeiculos(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Veiculo v = new Veiculo();
        v.setId(resultado.getInt(1));
        v.setMarca(resultado.getString(2));
        v.setModelo(resultado.getString(3));
        v.setPlaca(resultado.getString(4));
        v.setTipoHabilitacao(resultado.getInt(5));
        return v;
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_UPDATE_VEICULOS);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)     
            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setString(3, veiculo.getPlaca());
            comando.setInt(4, veiculo.getTipoHabilitacao());
            comando.setInt(5, veiculo.getId());
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

    public void removerUsuario(Veiculo veiculo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        ///id = 4;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_DELETE_VEICULO);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, veiculo.getPlaca());
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

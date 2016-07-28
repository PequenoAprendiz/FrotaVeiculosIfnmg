/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.persistencia;

import br.edu.ifnmg.wellington.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class UsuarioDAO {

    private static final String SQL_INSERT = "INSERT INTO USUARIO (LOGIN, SENHA,TIPO) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_USUARIOS = "SELECT ID, LOGIN, SENHA, TIPO FROM USUARIO";
    private static final String SQL_SELECT_USUARIO = "SELECT ID, LOGIN, SENHA, TIPO FROM USUARIO WHERE LOGIN = ?";
    private static final String SQL_UPDATE_USUARIO = "UPDATE USUARIO SET LOGIN = ?, SENHA = ?, TIPO = ? WHERE ID = ?";
    private static final String SQL_DELETE_USUARIO = "DELETE FROM USUARIO WHERE LOGIN = ?";

    public void incluirUsuario(Usuario usuarioEmEdicao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, usuarioEmEdicao.getLogin());
            comando.setString(2, usuarioEmEdicao.getSenha());
            comando.setInt(3, usuarioEmEdicao.getTipo());

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

    public List<Usuario> buscarUsuarios() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_USUARIOS);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Usuario usuario = this.extrairLinhaResultadoBuscarTodosUsuarios(resultado);
                //Adiciona um item à lista que será retornada
                listaUsuarios.add(usuario);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaUsuarios;
    }

    private Usuario extrairLinhaResultadoBuscarTodosUsuarios(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Usuario usuario = new Usuario();
        usuario.setId(resultado.getInt(1));
        usuario.setLogin(resultado.getString(2));
        usuario.setSenha(resultado.getString(3));
        usuario.setTipo(resultado.getInt(4));
        return usuario;
    }

    public Boolean buscarUsuarioPorLogin(String login) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario usuario = new Usuario();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_USUARIO);

            comando.setString(1, login);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                usuario = this.buscarUsuarioPorLogin(resultado);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }

        if (usuario.getLogin().equals(null)) {
            return true;
        }

        return false;

    }

    private Usuario buscarUsuarioPorLogin(ResultSet resultado) throws SQLException {
        Usuario u = new Usuario();
        u.setId(resultado.getInt(1));
        u.setLogin(resultado.getString(2));
        u.setSenha(resultado.getString(3));
        u.setTipo(resultado.getInt(4));
        return u;
    }
    
    
     public void atualizar(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_UPDATE_USUARIO);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)     
            comando.setString(1, usuario.getLogin());
            comando.setString(2, usuario.getSenha());
            comando.setInt(3, usuario.getTipo());
            comando.setInt(4, usuario.getId());
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

     
     public void removerUsuario(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        ///id = 4;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_DELETE_USUARIO);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, usuario.getLogin());
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

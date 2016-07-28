/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.negocio;

import br.edu.ifnmg.wellington.entidade.Usuario;
import br.edu.ifnmg.wellington.entidade.Veiculo;
import br.edu.ifnmg.wellington.exception.VerificarUsuarioDuplicado;
import br.edu.ifnmg.wellington.exception.VerificarVeiculoDuplicado;
import br.edu.ifnmg.wellington.persistencia.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Wellington
 */
public class UsuarioBO {

    private UsuarioBO usuarioBO;
    private List<Usuario> usuarios;

    public void incluirUsuario(Usuario usuarioEmEdicao) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioEmEdicao.setSenha(this.exemploMD5(usuarioEmEdicao.getSenha()));
        usuarioDAO.incluirUsuario(usuarioEmEdicao);
    }

    public String converterHexadecimalParaString(byte[] valorHexadecimal) {
        StringBuilder valorConvertido = new StringBuilder();
        for (byte caracter : valorHexadecimal) {
            valorConvertido.append(String.format("%02X", 0xFF & caracter));
        }
        return valorConvertido.toString();
    }

    public String exemploMD5(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String texto = senha;
        MessageDigest algoritmo = MessageDigest.getInstance("MD5");
        byte[] codigoHashHexaDecimal = algoritmo.digest(texto.getBytes("UTF-8"));
        String codigoHashFinal = converterHexadecimalParaString(codigoHashHexaDecimal);
        return codigoHashFinal;
    }

    public void verificauUsuarioDuplicado(Usuario usuarioEmEdicao) throws SQLException {
        this.usuarioBO = new UsuarioBO();
        this.usuarios = usuarioBO.buscarUsuarios();
        String nomeUsuario = usuarioEmEdicao.getLogin();
        for (Usuario user : usuarios) {
            if (user.getLogin().equals(nomeUsuario) || user.getLogin() == null) {
                throw new VerificarUsuarioDuplicado();
            }
        }
    }

    public List<Usuario> buscarUsuarios() throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarUsuarios();
    }

    public Boolean buscarUsuarioPorLogin(String login) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarUsuarioPorLogin(login);
    }

    public void atualizarUsuario(Usuario usuarioSelecionado) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioSelecionado.setSenha(this.exemploMD5(usuarioSelecionado.getSenha()));
        usuarioDAO.atualizar(usuarioSelecionado);
    }

    public void deletarUsuario(Usuario usuarioSelecionado) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.removerUsuario(usuarioSelecionado);
    }
}

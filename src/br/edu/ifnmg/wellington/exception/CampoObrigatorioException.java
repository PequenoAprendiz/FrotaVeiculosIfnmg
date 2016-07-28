package br.edu.ifnmg.wellington.exception;

/**
 *
 * @author Wellington
 */
public class CampoObrigatorioException extends FrotaVeiculosException{
    public CampoObrigatorioException(String mensagen){
         super("Favor informar o(s) campo(s) obrigatório(s): "+mensagen);
    }    
}

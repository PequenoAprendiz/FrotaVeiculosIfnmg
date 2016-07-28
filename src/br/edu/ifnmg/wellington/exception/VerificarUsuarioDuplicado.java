/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.exception;

/**
 *
 * @author Were
 */
public class VerificarUsuarioDuplicado extends FrotaVeiculosException{
     
    public VerificarUsuarioDuplicado() {
        super("Já existe um usuario com este login, tente outro!");
    }
     
}

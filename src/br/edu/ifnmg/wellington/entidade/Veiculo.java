/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.wellington.entidade;

/**
 *
 * @author Were
 */
public class Veiculo {

    private int id;
    private String marca;
    private String modelo;
    private String placa;
    private int idHabilitacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public int getTipoHabilitacao() {
        return idHabilitacao;
    }

    public void setTipoHabilitacao(int tipoHabilitacao) {
        this.idHabilitacao = tipoHabilitacao;
    }
}

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
public class Corrida {
    private int idcorrida;
    private String destino;
    private String dataSaida;
    private String previsaoCheagada;
    private String dataChegada;
    private int quilometragem;
    private int idVeiculo;

   
    public int getIdcorrida() {
        return idcorrida;
    }

    public void setIdcorrida(int idcorrida) {
        this.idcorrida = idcorrida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getPrevisaoCheagada() {
        return previsaoCheagada;
    }

    public void setPrevisaoCheagada(String previsaoCheagada) {
        this.previsaoCheagada = previsaoCheagada;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }
    
     public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    
}

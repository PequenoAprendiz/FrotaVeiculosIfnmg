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
public class Relatorio {

     private String nomeMotorista;
     private int totalCorridasPorMotorista;
     private String habilitacao;
     private String modelo;
     private String placa;
     private int totalCorridasVeiculo;
     private int somatorioQuilometragem;

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public int getTotalCorridasPorMotorista() {
        return totalCorridasPorMotorista;
    }

    public void setTotalCorridasPorMotorista(int totalCorridasPorMotorista) {
        this.totalCorridasPorMotorista = totalCorridasPorMotorista;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
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

    public int getTotalCorridasVeiculo() {
        return totalCorridasVeiculo;
    }

    public void setTotalCorridasVeiculo(int totalCorridasVeiculo) {
        this.totalCorridasVeiculo = totalCorridasVeiculo;
    }

    public int getSomatorioQuilometragem() {
        return somatorioQuilometragem;
    }

    public void setSomatorioQuilometragem(int somatorioQuilometragem) {
        this.somatorioQuilometragem = somatorioQuilometragem;
    }

   }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

//Classe normal, construtor, get e setters, toString.

/**
 *
 * @author plocabral
 */
public class Carro {

    int numero_chassi;
    String nome;
    String cor;
    int ano;
    int potencia_cv;

    public Carro() {
    }

    public Carro(int numero_chassi, String nome, String cor, int ano, int potencia_cv) {
        this.numero_chassi = numero_chassi;
        this.nome = nome;
        this.cor = cor;
        this.ano = ano;
        this.potencia_cv = potencia_cv;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getNumero_chassi() {
        return numero_chassi;
    }

    public void setNumero_chassi(int numero_chassi) {
        this.numero_chassi = numero_chassi;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPotencia_cv() {
        return potencia_cv;
    }

    public void setPotencia_cv(int potencia_cv) {
        this.potencia_cv = potencia_cv;
    }

    @Override
    public String toString() {
        return "Carro{" + "numero_chassi=" + numero_chassi + ", nome=" + nome + ", cor=" + cor + ", ano=" + ano + ", potencia_cv=" + potencia_cv + '}';
    }

}

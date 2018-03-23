/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.time.LocalDate;

/**
 *
 * @author plocabral
 */
public class Venda {
    
    private Pessoa p;
    private Carro c;
    private LocalDate data_venda;    
    private Float valor;

    public Venda(Pessoa p, Carro c, LocalDate data_venda) {
        this.p = p;
        this.c = c;
        this.data_venda = data_venda;
        //this.valor = this.c.getValor();
    }

    public Venda() {
    }

    public Pessoa getP() {
        return p;
    }

    public void setP(Pessoa p) {
        this.p = p;
    }

    public Carro getC() {
        return c;
    }

    public void setC(Carro c) {
        this.c = c;
    }

    public LocalDate getData_venda() {
        return data_venda;
    }

    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Venda{" + "p=" + p + ", c=" + c + ", data_venda=" + data_venda + ", valor=" + valor + '}';
    }
    
}

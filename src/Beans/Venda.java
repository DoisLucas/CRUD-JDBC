/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author plocabral
 */
public class Venda {

    private Pessoa p;
    private Carro c;
    private String data_venda;

    public Venda(Pessoa p, Carro c) {
        this.p = p;
        this.c = c;
        this.data_venda = java.time.LocalDate.now().toString();
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

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    @Override
    public String toString() {
        return "Venda{" + "p=" + p + ", c=" + c + ", data_venda=" + data_venda + '}';
    }

}

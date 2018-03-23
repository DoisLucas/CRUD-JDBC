/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Beans.Carro;
import Beans.Pessoa;
import java.util.ArrayList;

/**
 *
 * @author plocabral
 */
public class Singleton {
    
    ArrayList<Pessoa> todas_pessoas = new ArrayList<>();
    ArrayList<Carro> todos_carros = new ArrayList<>();
  
    private Singleton() {
    }
    
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private static class SingletonHolder {

        private static final Singleton INSTANCE = new Singleton();
    }

    public ArrayList<Pessoa> getTodas_pessoas() {
        return todas_pessoas;
    }

    public void setTodas_pessoas(ArrayList<Pessoa> todas_pessoas) {
        this.todas_pessoas = todas_pessoas;
    }

    public ArrayList<Carro> getTodos_carros() {
        return todos_carros;
    }

    public void setTodos_carros(ArrayList<Carro> todos_carros) {
        this.todos_carros = todos_carros;
    }

}

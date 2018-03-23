/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Beans.Carro;
import Beans.Pessoa;
import DAOs.CarroDAO;
import DAOs.PessoaDAO;
import java.util.Scanner;

/**
 *
 * @author plocabral
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Main m = new Main();
        m.menu();
    }

    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    public void menu() {

        System.out.println("1) Cadastrar Carro");
        System.out.println("2) Listar Todos Carros");
        System.out.println("3) Excluir Carro");
        System.out.println("4) Alterar Carro");
        System.out.println("5) Cadastrar Pessoa");
        System.out.println("6) Listar Todas Pessoas");
        System.out.println("7) Excluir Pessoa");
        System.out.println("8) Alterar Pessoa");
        System.out.println("9) Realizar Venda");
        System.out.println("10) Mostar Vendas");
        System.out.println("11) Listar Carro de Pessoa");
        System.out.println("12) Sair");
        System.out.print("Opção: ");
        int op = getScanner().nextInt();

        switch (op) {

            case 1:
                add_carro();
            case 2:
                listar_carro();
            case 3:
                excluir_carro();
            case 4:
                alterar_carro();
            case 5:
                add_pessoa();
            case 6:
                listar_pessoa();
            case 7:
                excluir_pessoa();
            case 8:
            //aterar_pessoa();
            case 9:
            //realizar_venda();
            case 10:
            //mostrar_venda();
            case 11:
            //listar_carro_pessoa();
            case 12:
                System.exit(0);
            default:
                System.out.println("Digite uma opção valida!");
                menu();

        }
    }

    private void add_carro() {

        System.out.print("\nDigite o numero do chassi do carro: ");
        int chassi = getScanner().nextInt();
        System.out.print("Digite o nome do carro: ");
        String nome = getScanner().nextLine();
        System.out.print("Digite a cor do carro: ");
        String cor = getScanner().next();
        System.out.print("Digite o ano do carro: ");
        int ano = getScanner().nextInt();
        System.out.print("Digite a potencia do carro: ");
        int potencia = getScanner().nextInt();

        //Instanciei um carro, normal...
        Carro c = new Carro(chassi, nome, cor, ano, potencia);

        //Instanciando a classe DAO do Carro, chamando o metodo add_carro e passando como parametro o carro
        //criado acima
        CarroDAO cdao = new CarroDAO();
        cdao.add_carro(c);

        menu();
    }

    public void listar_carro() {

        //Instanciando a classe DAO do Carro
        CarroDAO cdao = new CarroDAO();

        System.out.println("\t\n--- Todos os Carros ---\n");

        //Passando um for no arraylist que o metodo mostrar_carros retorna
        for (Carro c : cdao.mostrar_carros()) {
            System.out.println("Numero do Chassi: " + c.getNumero_chassi());
            System.out.println("Nome: " + c.getNome());
            System.out.println("Cor: " + c.getCor());
            System.out.println("Ano: " + c.getAno());
            System.out.println("Potência (CV): " + c.getPotencia_cv() + "\n");
        }
        menu();
    }

    public void excluir_carro() {

        CarroDAO cdao = new CarroDAO();
        
        System.out.print("\nDigite o número do chassi do carro para excluir: ");
        int numero_chassi = getScanner().nextInt();

        //Metodo que remove o carro pelo numero do chassi
        cdao.delete_carro(numero_chassi);
        menu();

    }

    public void alterar_carro() {
        
        //Tem varias forma de fazer essa alteração, escolhi encontrar o carro  chamando o metodo achar_carro da classe DAO, 
        //mostrar ele, e dar a liberdade de alterar todas as informações, em seguida passando essas novas informaçoes
        //pro metodos altera_carro da classe CarroDAO

        CarroDAO cdao = new CarroDAO();

        System.out.print("\nDigite o número do chassi do carro para alterar: ");
        int numero_chassi = getScanner().nextInt();

        Carro c = cdao.achar_carro(numero_chassi);

        System.out.println("\nAlterando Informações do Carro: \n");
        System.out.println("Numero do Chassi: " + c.getNumero_chassi());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Cor: " + c.getCor());
        System.out.println("Ano: " + c.getAno());
        System.out.println("Potência (CV): " + c.getPotencia_cv() + "\n");

        System.out.println("Digite as novas informações: \n");

        System.out.print("Nome: ");
        String nome = getScanner().nextLine();
        System.out.print("Cor: ");
        String cor = getScanner().next();
        System.out.print("Ano: ");
        int ano = getScanner().nextInt();
        System.out.print("Potencia (CV): ");
        int potencia = getScanner().nextInt();

        //Passando como parametro as informações e o numero do chassi do carro que foi digitado e posteriormente encontrado
        cdao.alterar_carro(c.getNumero_chassi(), nome, cor, ano, potencia);
        menu();
    }
    
    public void add_pessoa(){
        
        System.out.print("\nDigite o nome da Pessoa: ");
        String nome = getScanner().nextLine();
        System.out.print("Digite o CPF da Pessoa: ");
        int cpf = getScanner().nextInt();
        System.out.print("Digite o RG da Pessoa: ");
        int rg = getScanner().nextInt();
        System.out.print("Digite a idade da Pessoa: ");
        int idade = getScanner().nextInt();
        
        //Instanciei um carro, normal...
        Pessoa p = new Pessoa(cpf, rg, idade, nome);
        
         //Instanciando a classe DAO do Carro, chamando o metodo add_carro e passando como parametro o carro
        //criado acima
        PessoaDAO pdao = new PessoaDAO();
        pdao.add_pessoa(p);
        
        menu();
    }
    
    public void listar_pessoa(){
        
        //Instanciando a classe DAO de Pessoa
        PessoaDAO pdao = new PessoaDAO();
        
        System.out.println("\t\n--- Todas as Pessoas ---\n");
        
        //Passando um for no arraylist que o metodo mostrar_carros retorna
        for (Pessoa p : pdao.mostrar_pessoas()) {
            System.out.println("Nome: " + p.getNome());
            System.out.println("CPF: " + p.getCpf());
            System.out.println("RG: " + p.getRg());
            System.out.println("Idade: " + p.getIdade() + "\n");
        }
        menu();
    }
    
    public void excluir_pessoa(){
        
        PessoaDAO pdao = new PessoaDAO();      
        System.out.print("\nDigite o CPF da pessoa para remove-lá: ");
        
        int cpf = getScanner().nextInt();   
        pdao.delete_pessoa(cpf);
        menu();
    }

}

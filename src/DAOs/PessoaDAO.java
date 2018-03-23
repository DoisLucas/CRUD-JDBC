/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Connection;

/**
 *
 * @author plocabral
 */
public class PessoaDAO {
    
    /*
    * A Classe DAO vai ser responsavel de fazer todas as operações CRUD desse objeto no banco,
    * O recomendado e ter um Classe DAO pra cada entidade do seu projeto, CarroDAO, PessoaDAO, etc...
    */
    private Connection con = null;

    //Sempre que instacia ele vai pegar a conexao com banco, da classe que criamos BancoConnection
    public PessoaDAO() {
        con = BancoConnection.getConnection();
    }
    
}

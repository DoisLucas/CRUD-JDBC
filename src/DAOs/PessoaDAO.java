/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Beans.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
    public void add_pessoa(Pessoa p) {
        
        String sql = "INSERT INTO pessoa (cpf, rg, idade, nome) VALUES (?, ?, ?, ?)";
        
        try {
            //O preparedStatement serve pra preparar a query que criei acima, subistituindo os '?' pelos valores que eu quero,
            //serve pra nao usar querys fixas e unicas, onde o '?' pode receber qualquer valor.
            //OBS: Só pode usar a notação '?' nos dados.
            PreparedStatement stmt = con.prepareStatement(sql);

            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo cpf da pessoa.
            stmt.setInt(1, p.getCpf());

            //Digo que no 2º '?' ele vai ser trocado pelo rg da pessoa, e assim por diante.
            stmt.setInt(2, p.getRg());
            stmt.setInt(3, p.getIdade());
            stmt.setString(4, p.getNome());

            //Query preparada com os devidos '?' substituidos pelos valores do obj carro, agora eu executo essa Query com o metodo execute().
            stmt.execute();
            System.out.println("\nPessoa Adicionada no Banco de Dados\n");

            //Pronto aqui ele já inseriu no banco.
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }     
    }
    
}

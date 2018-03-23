/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Beans.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author plocabral
 */
public class CarroDAO {

    /*
    * A Classe DAO vai ser responsavel de fazer todas as operações CRUD desse objeto no banco,
    * O recomendado e ter um Classe DAO pra cada entidade do seu projeto, CarroDAO, PessoaDAO, etc...
     */
    private Connection con = null;

    //Sempre que instacia ele vai pegar a conexao com banco, da classe que criamos BancoConnection.
    public CarroDAO() {
        con = BancoConnection.getConnection();
    }

    //Metodo que recebe um carro pra adicionar no banco!
    public void add_carro(Carro c) {
        //Aqui eu monto a query que vai adicionar o carro, tem que saber o basico de QUERYS SQL, 
        //os nomes da tabela, e os campos tem que estar igual a do banco.

        String sql = "INSERT INTO carro (numero_chassi, nome, cor, ano, potencia) VALUES (?, ?, ?, ?, ?)";

        try {

            //O preparedStatement serve pra preparar a query que criei acima, subistituindo os '?' pelos valores que eu quero,
            //serve pra nao usar querys fixas e unicas, onde o '?' pode receber qualquer valor.
            //OBS: Só pode usar a notação '?' nos dados.
            PreparedStatement stmt = con.prepareStatement(sql);

            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo chassi do carro.
            stmt.setInt(1, c.getNumero_chassi());

            //Digo que no 2º '?' ele vai ser trocado pelo nome do carro, e assim por diante.
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getCor());
            stmt.setInt(4, c.getAno());
            stmt.setInt(5, c.getPotencia_cv());

            //Query preparada com os devidos '?' substituidos pelos valores do obj carro, agora eu executo essa Query com o metodo execute().
            stmt.execute();
            System.out.println("\nCarro Adicionado no Banco de Dados\n");

            //Pronto aqui ele já inseriu no banco, WOLFADO PLUS!
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }

    }

    //Metodo que lista todos os carros que retorna um arraylist de carros, necessitando fazer a listagem no Main.
    public ArrayList<Carro> mostrar_carros() {

        //ArrayList que irei retornar
        ArrayList<Carro> retorno = new ArrayList<>();

        //Query que irei lançar, padrão.
        String sql = "SELECT * FROM carro";

        try {

            //Passo a Query que vai ser preparada e executada.
            PreparedStatement stmt = con.prepareStatement(sql);

            //Executo essa Query e atribuo o resultado a rs. Onde ira guardar todo resultado que for pego no banco, 
            //ele guarda o resultado de uma pesquisa numa estrutura de dados que pode ser percorrida. 
            ResultSet rs = stmt.executeQuery();

            //Percorro o resultado enquanto tiver proximo.
            while (rs.next()) {

                //Instacio um tipo carro pra criar o carro e adicionar no ArrayList que irei retornar.
                Carro c = new Carro();
                //Chamo o Setters padrão do carro, e no parametro coloco o rs.getTipo("nome_da_coluna_igual_do_banco");              
                c.setNumero_chassi(rs.getInt("numero_chassi"));
                c.setNome(rs.getString("nome"));
                c.setCor(rs.getString("cor"));
                c.setAno(rs.getInt("ano"));
                c.setPotencia_cv(rs.getInt("potencia"));
                //Adiciono no ArrayList.
                retorno.add(c);
                //Repete esse processo ate acabar o ResultSet, e no final o ARRAY vai ficar cheio com todos os carros.
            }

            rs.close();
            //Retorno o ARRAY com todos os carros.
            return retorno;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        return null;
    }

    public void delete_carro(int chassi) {

        String sql = "DELETE FROM carro WHERE numero_chassi = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo chassi do carro que recebemos no parametro.
            stmt.setInt(1, chassi);
            stmt.executeUpdate();
            System.out.println("\nCarro Deletado do Banco de Dados\n");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }

    }

    public Carro achar_carro(int chassi) {

        Carro c = new Carro();
        String sql = "SELECT * FROM carro WHERE numero_chassi = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo chassi do carro que recebemos no parametro.
            stmt.setInt(1, chassi);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Chamo o Setters padrão do carro, e no parametro coloco o rs.getTipo("nome_da_coluna_igual_do_banco");              
                c.setNumero_chassi(rs.getInt("numero_chassi"));
                c.setNome(rs.getString("nome"));
                c.setCor(rs.getString("cor"));
                c.setAno(rs.getInt("ano"));
                c.setPotencia_cv(rs.getInt("potencia"));
            }
            return c;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public void alterar_carro(int chassi, String nome, String cor, int ano, int potencia) {

        String sql = "UPDATE carro SET nome = ?, cor = ?, ano = ?, potencia = ? WHERE numero_chassi = ?";

        try {

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cor);
            stmt.setInt(3, ano);
            stmt.setInt(4, potencia);
            stmt.setInt(5, chassi);
            stmt.executeUpdate();
            System.out.println("\nCarro Editado no Banco de Dados\n");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }

    }

}

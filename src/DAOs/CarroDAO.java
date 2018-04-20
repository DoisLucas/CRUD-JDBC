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

        String sql = "INSERT INTO carro (numero_chassi, nome, cor, ano, potencia, valor) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = BancoConnection.getConnection();
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
            stmt.setDouble(6, c.getValor());

            //Query preparada com os devidos '?' substituidos pelos valores do obj carro, agora eu executo essa Query com o metodo execute().
            stmt.execute();
            System.out.println("\nCarro Adicionado no Banco de Dados\n");

            //Pronto aqui ele já inseriu no banco.
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            BancoConnection.closeConnection(con);
        }

    }

    //Metodo que lista todos os carros que retorna um arraylist de carros, necessitando fazer a listagem no Main.
    public ArrayList<Carro> mostrar_carros() {

        //ArrayList que irei retornar
        ArrayList<Carro> retorno = new ArrayList<>();

        //Query que irei lançar, retorna todos os carros (incluido os já vendidos).
        String sql = "SELECT * FROM carro";
        //Caso queira mostrar apenas os carros disponivel para venda sem listar os que já foram vendidos.
        String sql1 = "SELECT * from carro EXCEPT SELECT c.numero_chassi, c.nome, c.cor, c.ano, c.potencia, c.valor FROM carro as c, venda as v where v.id_carro_fk = c.numero_chassi";

        try {
            con = BancoConnection.getConnection();
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
                c.setValor(rs.getDouble("valor"));
                //Adiciono no ArrayList.
                retorno.add(c);
                //Repete esse processo ate acabar o ResultSet, e no final o ARRAY vai ficar cheio com todos os carros.
            }

            rs.close();
            //Retorno o ARRAY com todos os carros.
            return retorno;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            BancoConnection.closeConnection(con);
        }
    }

    //Metodo que deleta o carro pelo numero do chassi passado pelo parametro.
    public void delete_carro(int chassi) {
     
        String sql1 = "DELETE FROM carro WHERE numero_chassi = ?";
        String sql2 = "DELETE FROM venda WHERE id_carro_fk = ?";

        try {
            con = BancoConnection.getConnection();
            //Removendo todas as vendas do carro
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo chassi do carro que recebemos no parametro.
            stmt2.setInt(1, chassi);
            stmt2.executeUpdate();

            //Excluindo realmete o carro
            PreparedStatement stmt1 = con.prepareStatement(sql1);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo chassi do carro que recebemos no parametro.
            stmt1.setInt(1, chassi);
            stmt1.executeUpdate();
            System.out.println("\nCarro Deletado do Banco de Dados\n");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            BancoConnection.closeConnection(con);
        }

    }

    //Metodo que retorna o carro com o chassi passado pelo parametro.
    public Carro achar_carro(int chassi) {

        Carro c = new Carro();
        String sql = "SELECT * FROM carro WHERE numero_chassi = ?";

        try {
            con = BancoConnection.getConnection();
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
                c.setValor(rs.getDouble("valor"));
            }
            return c;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            BancoConnection.closeConnection(con);
        }
    }

    //Metodo alterar carro, onde pega as novas informações do parametro e faz o UPDATE na tabela pelo chassi do carro.
    public void alterar_carro(int chassi, String nome, String cor, int ano, int potencia, double valor) {

        String sql = "UPDATE carro SET nome = ?, cor = ?, ano = ?, potencia = ?, valor = ? WHERE numero_chassi = ?";

        try {
            con = BancoConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cor);
            stmt.setInt(3, ano);
            stmt.setInt(4, potencia);
            stmt.setDouble(5, valor);
            stmt.setInt(6, chassi);
            stmt.executeUpdate();
            System.out.println("\nCarro Editado no Banco de Dados\n");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            BancoConnection.closeConnection(con);
        }
    }

}

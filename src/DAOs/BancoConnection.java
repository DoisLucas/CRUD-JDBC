package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author plocabral
 */
public class BancoConnection {

    //Classe responsavel de abrir e fechar conexão do banco, WOLFADO PLUS!
    private static final String DRIVER = "org.postgresql.Driver";

    //ENDERECO:PORTA/NOME_BANCO
    private static final String URL = "jdbc:postgresql://localhost:5432/rentcar";

    //Usuario e senha do banco que você define quando cria o banco lá
    private static final String USER = "postgres";
    private static final String PASS = "123456789";

    //Abre conexão com o banco
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    //Fecha conexão com o banco
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
    }

}

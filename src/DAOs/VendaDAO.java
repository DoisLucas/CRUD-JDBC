/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Beans.Pessoa;
import Beans.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author plocabral
 */
public class VendaDAO {

    private Connection con = null;

    public VendaDAO() {
        con = BancoConnection.getConnection();
    }

    public void add_venda(Venda v) {
        String sql = "INSERT INTO venda (id_venda, data_venda, id_pessoa_fk, id_carro_fk) VALUES (DEFAULT, ?, ?, ?)";

        try {
            con = BancoConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, v.getData_venda());
            stmt.setInt(2, v.getP().getCpf());
            stmt.setInt(3, v.getC().getNumero_chassi());
            stmt.execute();
            System.out.println("\nVenda Adicionada no Banco de Dados\n");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            BancoConnection.closeConnection(con);
        }
    }

    //Outra alternativa pra listagem, printar já no metodo, sem retonar uma lista do objeto
    public void mostrar_vendas() {

        String sql = "SELECT v.id_venda, p.cpf || ' - ' || p.nome as COMPRADOR, "
                + "c.numero_chassi || ' - ' || c.nome as carro, c.valor, v.data_venda  FROM venda as v, "
                + "pessoa as p, carro as c where p.cpf = v.id_pessoa_fk and c.numero_chassi = v.id_carro_fk";

        try {
            con = BancoConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nTodas as Vendas\n");

            while (rs.next()) {
                System.out.println("Numero da venda: " + rs.getInt("id_venda"));
                System.out.println("Comprador: " + rs.getString("comprador"));
                System.out.println("Carro: " + rs.getString("carro"));
                System.out.println("Valor: " + rs.getDouble("valor"));
                System.out.println("Data da venda: " + rs.getString("data_venda"));
                System.out.print("\n");
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            BancoConnection.closeConnection(con);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Beans.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, v.getData_venda());
            stmt.setInt(2, v.getP().getCpf());
            stmt.setInt(3, v.getC().getNumero_chassi());

            stmt.execute();
            System.out.println("\nVenda Adicionada no Banco de Dados\n");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }

}

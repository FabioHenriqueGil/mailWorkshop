/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailworkshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckinDAO {

    private Connection connection;

    public CheckinDAO(String servidor, String banco, String usuario, String senha) {
        this.connection = new ConnectionFactory().getConnection(servidor, banco, usuario, senha);
    }

    public List<Checkin> getLista() {
        List<Checkin> checkins = new ArrayList<Checkin>();
        String sql = "select * from checkin";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            // executa
            ResultSet resultado = stmt.executeQuery();
            // alimenta a lista
            while (resultado.next()) {
                Checkin checkin = new Checkin();
                checkin.setID(resultado.getInt("id"));
                checkin.setNome(resultado.getString("nome"));
                checkin.setEmail(resultado.getString("email"));
                checkin.setRG(resultado.getString("rg"));
                checkin.setOcupacao(resultado.getString("ocupacao"));
                checkin.setqrCode(resultado.getString("qrCode"));
                checkin.setLatitude(resultado.getString("latitude"));
                checkin.setLongitude(resultado.getString("longitude"));
                checkin.setData(resultado.getString("data"));

                checkins.add(checkin);
            }
            // fecha conexão
            resultado.close();
            stmt.close();
            return checkins;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

package ifpr.edu.br.model.dao;

import java.sql.Statement;

import ifpr.edu.br.model.Agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgenciaDAO {
    public void salvarAgencia(Agencia agencia){
        String sqlAgencia = "INSERT INTO agencia(nome, email, cnpj) VALUES(?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement psAgencia = con.prepareStatement(sqlAgencia, Statement.RETURN_GENERATED_KEYS);

            psAgencia.setString(1, agencia.getNome());
            psAgencia.setString(2, agencia.getEmail());
            psAgencia.setString(3, agencia.getCnpj());

            ResultSet rs = psAgencia.getGeneratedKeys();
                int idAgencia = 0;
                if(rs.next()) idAgencia = rs.getInt(1);
                agencia.setAgenciaID(idAgencia);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

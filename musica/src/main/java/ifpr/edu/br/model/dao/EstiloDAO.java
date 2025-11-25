package ifpr.edu.br.model.dao;

import java.sql.Statement;

import ifpr.edu.br.model.Estilo;
import ifpr.edu.br.model.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstiloDAO {
    public void salvarEstiloHasMusica(Musica musica){
        String sqlEstilo = "INSERT INTO estilo_has_musica(estilo_idestilo, musica_idmusica) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement psEstilo = con.prepareStatement(sqlEstilo, Statement.RETURN_GENERATED_KEYS);

            for(Estilo estilo : musica.getEstilos()){
                psEstilo.setInt(1, estilo.getEstiloID());
                psEstilo.setInt(2, musica.getMusicaID());

                psEstilo.executeUpdate();

                ResultSet rs = psEstilo.getGeneratedKeys();
                int idEstilo = 0;
                if (rs.next()) idEstilo = rs.getInt(1);
                estilo.setEstiloID(idEstilo);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void salvarEstilo(Estilo estilo){
        String sqlEstilo = "INSERT INTO estilo(nome) VALUES(?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psEstilo = con.prepareStatement(sqlEstilo, Statement.RETURN_GENERATED_KEYS);

            psEstilo.setString(1, estilo.getNome());

            psEstilo.executeUpdate();

            ResultSet rs = psEstilo.getGeneratedKeys();
            int idEstilo = 0;
            if(rs.next()) idEstilo = rs.getInt(1);
            estilo.setEstiloID(idEstilo);
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

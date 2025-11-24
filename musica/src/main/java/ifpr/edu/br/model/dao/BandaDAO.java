package ifpr.edu.br.model.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifpr.edu.br.model.Agencia;
import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Musica;

public class BandaDAO {
    public void salvarBandaHasMusica(Musica musica){
        String sqlBanda = "INSERT INTO banda_has_musica(banda_idbanda, musica_idbanda) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement psBanda = con.prepareStatement(sqlBanda, Statement.RETURN_GENERATED_KEYS);

            for(Banda banda : musica.getBandas()){
                psBanda.setInt(1, banda.getBandaID());
                psBanda.setInt(2, musica.getMusicaID());

                psBanda.executeUpdate();

                ResultSet rs = psBanda.getGeneratedKeys();
                int idBanda = 0;
                if(rs.next()) idBanda = rs.getInt(1);
                banda.setBandaID(idBanda);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void salvarBanda(Banda banda, Agencia agencia){
        String sqlBanda = "INSERT INTO banda(nome, agencia_idagencia) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement psBanda = con.prepareStatement(sqlBanda);

            psBanda.setString(1, banda.getNome());
            psBanda.setInt(2, agencia.getAgenciaID());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

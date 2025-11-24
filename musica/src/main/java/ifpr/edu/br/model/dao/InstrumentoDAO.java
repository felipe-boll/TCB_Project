package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifpr.edu.br.model.Instrumento;
import ifpr.edu.br.model.Musica;

public class InstrumentoDAO {
    public void salvarInstrumentoHasMusica(Musica musica){
        String sqlInstrumento = "INSERT INTO musica_has_instrumento(musica_idmusica, instrumento_idinstrumento) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sqlInstrumento);

            for(Instrumento instrumento : musica.getInstrumentos()){
                psInstrumento.setInt(1, musica.getMusicaID());
                psInstrumento.setInt(2, instrumento.getInstrumentoID());

                psInstrumento.executeUpdate();

                ResultSet rs = psInstrumento.getGeneratedKeys();
                int idInstrumento = 0;
                if(rs.next()) idInstrumento = rs.getInt(1);
                instrumento.setInstrumentoID(idInstrumento);
            }

        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void salvarInstrumeno(Instrumento instrumento){
        String sqlInstrumento = "INSERT INTO instrumento(nome, descricao) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sqlInstrumento);

            psInstrumento.setString(1, instrumento.getNome());
            psInstrumento.setString(2, instrumento.getDescricao());

        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

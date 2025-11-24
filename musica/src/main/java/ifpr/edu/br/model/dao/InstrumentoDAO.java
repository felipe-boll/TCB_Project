package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ifpr.edu.br.model.Instrumento;
import ifpr.edu.br.model.Musica;

public class InstrumentoDAO {
    public void salvarInstrumento(Musica musica){
        String sqlInstrumento = "INSERT INTO musica_has_instrumento(musica_idmusica, instrumento_idinstrumento) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sqlInstrumento);

            for(Instrumento instrumento : musica.getInstrumentos()){
                psInstrumento.setString(1, instrumento.getNome());
                psInstrumento.setString(2, instrumento.getDescricao());

                psInstrumento.executeUpdate();
            }

        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

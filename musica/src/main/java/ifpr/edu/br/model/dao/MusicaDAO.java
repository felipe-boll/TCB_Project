package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.Estilo;
import ifpr.edu.br.model.Instrumento;
import ifpr.edu.br.model.Musica;

public class MusicaDAO {
    public void salvarMusica(Cantor cantor){
        String sqlMusica = "INSERT INTO musica(nome, dificuldade, duracao, letra) VALUES(?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psMusica = con.prepareStatement(sqlMusica, Statement.RETURN_GENERATED_KEYS);
            psMusica.setString(1, cantor.getMusicas().getNome());
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.Musica;

public class MusicaDAO {
    public void salvarMusica(Cantor cantor){
        String sqlMusica = "INSERT INTO musica(nome, dificuldade, duracao, letra) VALUES(?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psMusica = con.prepareStatement(sqlMusica, Statement.RETURN_GENERATED_KEYS);
            
            for(Musica musica : cantor.getMusicas()){
                psMusica.setString(1, musica.getNome());
                psMusica.setDouble(2, musica.getDificuldade());
                psMusica.setTime(3, musica.getDuracao());
                psMusica.setString(4, musica.getLetra());
            
                psMusica.executeUpdate();
            }
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

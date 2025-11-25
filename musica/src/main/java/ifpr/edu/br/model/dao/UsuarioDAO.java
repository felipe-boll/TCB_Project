package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ifpr.edu.br.model.Musica;
import ifpr.edu.br.model.Pessoa;
import ifpr.edu.br.model.Usuario;

public class UsuarioDAO {
    public void salvarUsuarioHasMusica(Musica musica){
        String sqlUsuario = "INSERT INTO usuario_has_musica(usuario_idusuario, musica_idmusica) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);

            for(Usuario usuario : musica.getUsuarios()){
                psUsuario.setInt(1, usuario.getUsuarioID());
                psUsuario.setInt(2, musica.getMusicaID());

                psUsuario.executeUpdate();

                ResultSet rs = psUsuario.getGeneratedKeys();
                int idUsuario = 0;
                if(rs.next()) idUsuario = rs.getInt(1);
                usuario.setUsuarioID(idUsuario);
            }
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void salvarUsuario(Usuario usuario, Pessoa pessoa){
        String sqlUsuario = "INSERT INTO usuario(pessoa_idpessoa, objetivo) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);

            psUsuario.setInt(1, pessoa.getPessoaID());
            psUsuario.setString(2, usuario.getObjetivo());

            psUsuario.executeUpdate();

            ResultSet rs = psUsuario.getGeneratedKeys();
            int idUsuario = 0;
            if(rs.next()) idUsuario = rs.getInt(1);
            usuario.setUsuarioID(idUsuario);
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}



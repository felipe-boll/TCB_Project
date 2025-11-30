package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Musica;
import ifpr.edu.br.model.Usuario;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO(){
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarUsuarioHasMusica(Musica musica){
        String sqlUsuario = "INSERT INTO usuario_has_musica(usuario_idusuario, musica_idmusica) VALUES(?, ?)";

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

    public void salvarUsuario(Usuario usuario){
        String sqlUsuario = "INSERT INTO usuario(objetivo) VALUES(?)";

        try{
            PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);

            psUsuario.setString(1, usuario.getObjetivo());

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

    public List<Usuario> listarUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try{
            PreparedStatement psUsuario = con.prepareStatement(sql);
            ResultSet rs = psUsuario.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuarioID(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setObjetivo(rs.getString("objetivo"));

                lista.add(usuario);
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao listar os usuarios");
        }
        return lista;
    }

    public void atualizarUsuario(Usuario usuario){
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, idade = ?, objetivo = ? WHERE idusuario = ?";

        try{
            PreparedStatement psUsuario = con.prepareStatement(sql);

            psUsuario.setString(1, usuario.getNome());
            psUsuario.setString(2, usuario.getCpf());
            psUsuario.setString(3, usuario.getEmail());
            psUsuario.setInt(4, usuario.getIdade());
            psUsuario.setString(5, usuario.getObjetivo());
            psUsuario.setInt(6, usuario.getUsuarioID());
        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar o usuario");
        }
    }

    public void deletarUsuario(int idUsuario){
        String sql = "DELETE FROM usuario WHERE idusuario = ?";

        try{
            PreparedStatement psUsuario = con.prepareStatement(sql);
            psUsuario.setInt(1, idUsuario);

            psUsuario.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao deletar o usuario");
        }
    }

}



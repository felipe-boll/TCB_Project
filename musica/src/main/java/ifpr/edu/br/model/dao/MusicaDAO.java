package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.Musica;

public class MusicaDAO {

    private Connection con;

    public MusicaDAO(){
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarMusica(Cantor cantor){
        String sqlMusica = "INSERT INTO musica(nome, dificuldade, duracao, letra) VALUES(?, ?, ?, ?)";

        try{
            PreparedStatement psMusica = con.prepareStatement(sqlMusica, Statement.RETURN_GENERATED_KEYS);
            
            for(Musica musica : cantor.getMusicas()){
                psMusica.setString(1, musica.getNome());
                psMusica.setDouble(2, musica.getDificuldade());
                psMusica.setTime(3, musica.getDuracao());
                psMusica.setString(4, musica.getLetra());
            
                psMusica.executeUpdate();

                ResultSet rs = psMusica.getGeneratedKeys();
                int idMusica = 0;
                if(rs.next()) idMusica = rs.getInt(1);
                musica.setMusicaID(idMusica);
            }
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Musica> listarMusicas(){
        List<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musica";

        try{
            PreparedStatement psMusica = con.prepareStatement(sql);
            ResultSet rs = psMusica.executeQuery();

            while (rs.next()) {
                Musica musica = new Musica();
                musica.setMusicaID(rs.getInt("idmusica"));
                musica.setNome(rs.getString("nome"));
                musica.setDuracao(rs.getTime("duracao"));
                musica.setDificuldade(rs.getDouble("dificuldade"));

                musica.setBandas(listarBandas(musica.getMusicaID()));
                
                lista.add(musica);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar musicas");
        }

        return lista;
    }

        private List<Banda> listarBandas(int idMusica) {
        List<Banda> bandas = new ArrayList<>();

        String sql = """
            SELECT b.idbanda, b.nome 
            FROM banda b
            JOIN banda_has_musica bhm ON b.idbanda = bhm.banda_idbanda
            WHERE bhm.musica_idmusica = ?
            """;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMusica);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Banda banda = new Banda();
                banda.setBandaID(rs.getInt("idbanda"));
                banda.setNome(rs.getString("nome"));

                bandas.add(banda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bandas;
    }

}
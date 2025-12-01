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
import ifpr.edu.br.model.Estilo;
import ifpr.edu.br.model.Instrumento;
import ifpr.edu.br.model.Musica;
import ifpr.edu.br.model.Usuario;

public class MusicaDAO {

    private Connection con;

    public MusicaDAO(){
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarMusica(Cantor cantor){
        String sqlMusica = "INSERT INTO musica(nome, dificuldade, duracao, letra) VALUES(?, ?, ?, ?)";

        try{
            PreparedStatement psMusica = con.prepareStatement(sqlMusica, Statement.RETURN_GENERATED_KEYS);
            
            for( Musica musica : cantor.getMusicas()){
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

    public void salvarEstiloHasMusica(Musica musica){
        String sql = "INSERT INTO estilo_has_musica(estilo_idestilo, musica_idmusica) VALUES(?, ?)";

        try (PreparedStatement psEstilo = con.prepareStatement(sql)) {

        for(Estilo estilo : musica.getEstilos()){
            psEstilo.setInt(1, estilo.getEstiloID());
            psEstilo.setInt(2, musica.getMusicaID());
            psEstilo.executeUpdate();
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvarBandaHasMusica(Musica musica){
        String sql = "INSERT INTO banda_has_musica(banda_idbanda, musica_idmusica) VALUES(?, ?)";

        try (PreparedStatement psBanda = con.prepareStatement(sql)) {

            for(Banda banda : musica.getBandas()){
                psBanda.setInt(1, banda.getBandaID());
                psBanda.setInt(2, musica.getMusicaID());
                psBanda.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvarInstrumentoHasMusica(Musica musica){
        String sql = "INSERT INTO musica_has_instrumento(musica_idmusica, instrumento_idinstrumento) VALUES(?, ?)";

        try (PreparedStatement psInstrumento = con.prepareStatement(sql)) {

            for(Instrumento instrumento : musica.getInstrumentos()){
                psInstrumento.setInt(1, musica.getMusicaID());
                psInstrumento.setInt(2, instrumento.getInstrumentoID());
                psInstrumento.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void salvarUsuarioHasMusica(Musica musica){
        String sql = "INSERT INTO usuario_has_musica(usuario_idusuario, musica_idmusica) VALUES(?, ?)";

        try (PreparedStatement psUsuario = con.prepareStatement(sql)) {

            for(Usuario usuario : musica.getUsuarios()){
                psUsuario.setInt(1, usuario.getUsuarioID());
                psUsuario.setInt(2, musica.getMusicaID());
                psUsuario.executeUpdate();
            }

        } catch (SQLException e) {
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

                musica.setBandas(listarBandasPorMusica(musica.getMusicaID()));
                musica.setEstilos(listarEstilosPorMusica(musica.getMusicaID()));
                musica.setInstrumentos(listarInstrumentosPorMusica(musica.getMusicaID()));
                
                musica.setLetra(rs.getString("letra"));

                lista.add(musica);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar as musicas");
        }

        return lista;
    }

    public void atualizarMusica(Musica musica) {
        String sql = "UPDATE musica SET nome = ?, duracao = ?, dificuldade = ?, letra = ? WHERE idmusica = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, musica.getNome());
            ps.setTime(2, musica.getDuracao());
            ps.setDouble(3, musica.getDificuldade());
            ps.setString(4, musica.getLetra());
            ps.setInt(5, musica.getMusicaID());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarBandasDaMusica(Musica musica) {

        String deleteSQL = "DELETE FROM banda_has_musica WHERE musica_idmusica = ?";
        String insertSQL = "INSERT INTO banda_has_musica(banda_idbanda, musica_idmusica) VALUES (?, ?)";

        try {
            PreparedStatement delete = con.prepareStatement(deleteSQL);
            delete.setInt(1, musica.getMusicaID());
            delete.executeUpdate();

            PreparedStatement insert = con.prepareStatement(insertSQL);

            for (Banda banda : musica.getBandas()) {
                insert.setInt(1, banda.getBandaID());
                insert.setInt(2, musica.getMusicaID());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEstilosDaMusica(Musica musica) {

        String deleteSQL = "DELETE FROM estilo_has_musica WHERE musica_idmusica = ?";
        String insertSQL = "INSERT INTO estilo_has_musica(estilo_idestilo, musica_idmusica) VALUES (?, ?)";

        try {
            PreparedStatement delete = con.prepareStatement(deleteSQL);
            delete.setInt(1, musica.getMusicaID());
            delete.executeUpdate();

            PreparedStatement insert = con.prepareStatement(insertSQL);

            for (Estilo estilo : musica.getEstilos()) {
                insert.setInt(1, estilo.getEstiloID());
                insert.setInt(2, musica.getMusicaID());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarInstrumentosDaMusica(Musica musica) {

        String deleteSQL = "DELETE FROM musica_has_instrumento WHERE musica_idmusica = ?";
        String insertSQL = "INSERT INTO musica_has_instrumento(musica_idmusica, instrumento_idinstrumento) VALUES (?, ?)";

        try {
            PreparedStatement delete = con.prepareStatement(deleteSQL);
            delete.setInt(1, musica.getMusicaID());
            delete.executeUpdate();

            PreparedStatement insert = con.prepareStatement(insertSQL);

            for (Instrumento ins : musica.getInstrumentos()) {
                insert.setInt(1, musica.getMusicaID());
                insert.setInt(2, ins.getInstrumentoID());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarUsuariosDaMusica(Musica musica) {

        String deleteSQL = "DELETE FROM usuario_has_musica WHERE musica_idmusica = ?";
        String insertSQL = "INSERT INTO usuario_has_musica(usuario_idusuario, musica_idmusica) VALUES (?, ?)";

        try {
            PreparedStatement delete = con.prepareStatement(deleteSQL);
            delete.setInt(1, musica.getMusicaID());
            delete.executeUpdate();

            PreparedStatement insert = con.prepareStatement(insertSQL);

            for (Usuario user : musica.getUsuarios()) {
                insert.setInt(1, user.getUsuarioID());
                insert.setInt(2, musica.getMusicaID());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deletarMusica(int idMusica){
        String sql = "DELETE FROM musica WHERE idmusica";

        try{
            PreparedStatement psMusica = con.prepareStatement(sql);
            psMusica.setInt(1, idMusica);

            psMusica.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao deletar a musica");
        }
    }


    private List<Banda> listarBandasPorMusica(int idMusica) {
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

    private List<Instrumento> listarInstrumentosPorMusica(int idMusica) throws SQLException {
        List<Instrumento> lista = new ArrayList<>();

        String sql = """
            SELECT i.* FROM instrumento i
            JOIN musica_has_instrumento mhi ON mhi.instrumento_idinstrumento = i.idinstrumento
            WHERE mhi.musica_idmusica = ?
        """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idMusica);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Instrumento inst = new Instrumento();
            inst.setInstrumentoID(rs.getInt("idinstrumento"));
            inst.setNome(rs.getString("nome"));
            inst.setDescricao(rs.getString("descricao"));

            lista.add(inst);
        }

        return lista;
    }


    private List<Estilo> listarEstilosPorMusica(int idMusica) throws SQLException {
        List<Estilo> lista = new ArrayList<>();

        String sql = """
            SELECT e.* FROM estilo e
            JOIN musica_has_estilo mhe ON mhe.estilo_idestilo = e.idestilo
            WHERE mhe.musica_idmusica = ?
        """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idMusica);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Estilo estilo = new Estilo();
            estilo.setEstiloID(rs.getInt("idestilo"));
            estilo.setNome(rs.getString("nome"));

            lista.add(estilo);
        }

        return lista;
    }

}
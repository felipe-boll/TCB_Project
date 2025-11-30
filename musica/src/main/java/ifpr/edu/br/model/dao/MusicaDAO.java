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

    public void atualizarMusica( Musica musica, boolean atualizarInstrumentos, boolean atualizarBandas, boolean atualizarEstilos) {
        String sql = "UPDATE musica SET nome = ?, duracao = ?, dificuldade a= ?, letra = ? WHERE idmusica = ?";

        try {
            PreparedStatement psMusica = con.prepareStatement(sql);

            psMusica.setString(1, musica.getNome());
            psMusica.setTime(2, musica.getDuracao());
            psMusica.setDouble(3, musica.getDificuldade());
            psMusica.setString(4, musica.getLetra());
            psMusica.setInt(5, musica.getMusicaID());

            psMusica.executeUpdate();

            if (atualizarInstrumentos) {
                String deleteInstr = "DELETE FROM musica_has_instrumento WHERE musica_idmusica = ?";
                PreparedStatement psDel = con.prepareStatement(deleteInstr);
                psDel.setInt(1, musica.getMusicaID());
                psDel.executeUpdate();

                String insertInstr = "INSERT INTO musica_has_instrumento(musica_idmusica, instrumento_idinstrumento) VALUES (?, ?)";
                PreparedStatement psIns = con.prepareStatement(insertInstr);

                for (Instrumento i : musica.getInstrumentos()) {
                    psIns.setInt(1, musica.getMusicaID());
                    psIns.setInt(2, i.getInstrumentoID());
                    psIns.executeUpdate();
                }
            }

            if (atualizarBandas) {
                String deleteBandas = "DELETE FROM musica_has_banda WHERE musica_idmusica = ?";
                PreparedStatement psDel = con.prepareStatement(deleteBandas);
                psDel.setInt(1, musica.getMusicaID());
                psDel.executeUpdate();

                String insertBandas = "INSERT INTO musica_has_banda(musica_idmusica, banda_idbanda) VALUES (?, ?)";
                PreparedStatement psIns = con.prepareStatement(insertBandas);

                for (Banda b : musica.getBandas()) {
                    psIns.setInt(1, musica.getMusicaID());
                    psIns.setInt(2, b.getBandaID());
                    psIns.executeUpdate();
                }
            }

            if (atualizarEstilos) {
                String deleteEstilos = "DELETE FROM musica_has_estilo WHERE musica_idmusica = ?";
                PreparedStatement psDel = con.prepareStatement(deleteEstilos);
                psDel.setInt(1, musica.getMusicaID());
                psDel.executeUpdate();

                String insertEstilos = "INSERT INTO musica_has_estilo(musica_idmusica, estilo_idestilo) VALUES (?, ?)";
                PreparedStatement psIns = con.prepareStatement(insertEstilos);

                for (Estilo e : musica.getEstilos()) {
                    psIns.setInt(1, musica.getMusicaID());
                    psIns.setInt(2, e.getEstiloID());
                    psIns.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar música");
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
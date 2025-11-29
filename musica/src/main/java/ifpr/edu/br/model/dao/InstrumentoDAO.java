package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Instrumento;
import ifpr.edu.br.model.Musica;

public class InstrumentoDAO {

    private Connection con;

    public InstrumentoDAO(){
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarInstrumentoHasMusica(Musica musica){
        String sqlInstrumento = "INSERT INTO musica_has_instrumento(musica_idmusica, instrumento_idinstrumento) VALUES(?, ?)";

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

    public void salvarInstrumento(Instrumento instrumento){
        String sqlInstrumento = "INSERT INTO instrumento(nome, descricao) VALUES(?, ?)";

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sqlInstrumento);

            psInstrumento.setString(1, instrumento.getNome());
            psInstrumento.setString(2, instrumento.getDescricao());

        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Instrumento> listarInstrumentos(){
        List<Instrumento> lista = new ArrayList<>();
        String sql = "SELECT * FROM instrumento";

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sql);
            ResultSet rs = psInstrumento.executeQuery();

            while (rs.next()) {
                Instrumento instrumento = new Instrumento();
                instrumento.setInstrumentoID(rs.getInt("idinstrumento"));
                instrumento.setNome(rs.getString("nome"));
                instrumento.setDescricao(rs.getString("descricao"));

                lista.add(instrumento);
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao listar os estilos");
        }

        return lista;
    }

    public void deletarEstilo(int idInstrumento){
        String sql = "DELETE FROM instrumento WHERE idinstrumento = ?";

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sql);
            psInstrumento.setInt(1, idInstrumento);

            psInstrumento.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao deletar o instrumento");
        }
    }
}

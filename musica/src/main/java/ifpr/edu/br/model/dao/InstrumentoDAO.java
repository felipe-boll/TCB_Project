package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Instrumento;

public class InstrumentoDAO {

    private Connection con;

    public InstrumentoDAO(){
        this.con = ConnectionFactory.getConnection();
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
            throw new RuntimeException("Erro ao listar os instrumentos");
        }

        return lista;
    }

    public void atualizarInstrumentos(Instrumento instrumento){
        String sql = "UPDATE instrumento SET nome = ?, descricao = ? WHERE idinstrumento = ?";

        try{
            PreparedStatement psIntrumento = con.prepareStatement(sql);

            psIntrumento.setString(1, instrumento.getNome());
            psIntrumento.setString(2, instrumento.getDescricao());
            psIntrumento.setInt(3, instrumento.getInstrumentoID());
        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar o instrumento");
        }
    }

    public void deletarInstrumento(int idInstrumento){
        String sql = "DELETE FROM instrumento WHERE idinstrumento = ?";

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sql);
            psInstrumento.setInt(1, idInstrumento);

            psInstrumento.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao deletar o instrumento");
        }
    }

    public Instrumento selectInstrumento(int idInstrumento){
        String sql = "SELECT * FROM instrumento WHERE idinstrumento = ?";

        try{
            PreparedStatement psInstrumento = con.prepareStatement(sql);

            psInstrumento.setInt(1, idInstrumento);

            ResultSet rs = psInstrumento.executeQuery();

            if (rs.next()) {
                Instrumento i = new Instrumento();
                i.setInstrumentoID(rs.getInt("idInstrumento"));
                i.setNome(rs.getString("nome"));
                return i;
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao buscar instrumento por ID");

        }

        return null;
    }
}

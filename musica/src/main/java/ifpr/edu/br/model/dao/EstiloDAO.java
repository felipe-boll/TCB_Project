package ifpr.edu.br.model.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Estilo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstiloDAO {

    private Connection con;

    public EstiloDAO(){
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarEstilo(Estilo estilo){
        String sqlEstilo = "INSERT INTO estilo(nome) VALUES(?)";

        try{
            PreparedStatement psEstilo = con.prepareStatement(sqlEstilo, Statement.RETURN_GENERATED_KEYS);

            psEstilo.setString(1, estilo.getNome());

            psEstilo.executeUpdate();

            ResultSet rs = psEstilo.getGeneratedKeys();
            int idEstilo = 0;
            if(rs.next()) idEstilo = rs.getInt(1);
            estilo.setEstiloID(idEstilo);
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Estilo> listarEstilos(){
        List<Estilo> lista = new ArrayList<>();
        String sql = "SELECT * FROM estilo";

        try{
            PreparedStatement psEstilo = con.prepareStatement(sql);
            ResultSet rs = psEstilo.executeQuery();

            while (rs.next()) {
                Estilo estilo = new Estilo();
                estilo.setEstiloID(rs.getInt("idestilo"));
                estilo.setNome(rs.getString("nome"));

                lista.add(estilo);
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao listar estilos");
        }
        return lista;
    }

    public void atualizarEstilos(Estilo estilo){
        String sql = "UPDATE estilo SET nome = ? WHERE idestilo = ?";

        try{
            PreparedStatement psEstilo = con.prepareStatement(sql);

            psEstilo.setString(1, estilo.getNome());
            psEstilo.setInt(2, estilo.getEstiloID());

            psEstilo.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar o estilo");
        }
    }

    public void deletarEstilo(int idEstilo){
        String sql = "DELETE FROM estilo WHERE idestilo = ?";

        try{
            PreparedStatement psEstilo = con.prepareStatement(sql);
            psEstilo.setInt(1, idEstilo);

            psEstilo.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao deletar o estilo");
        }
    }

    public Estilo selectEstilo(int idEstilo){
        String sql = "SELECT * FROM estilo WHERE idestilo = ?";

        try{
            PreparedStatement psEstilo = con.prepareStatement(sql);

            psEstilo.setInt(1, idEstilo);

            ResultSet rs = psEstilo.executeQuery();

            if (rs.next()) {
                Estilo e = new Estilo();
                e.setEstiloID(rs.getInt("idEstilo"));
                e.setNome(rs.getString("nome"));
                return e;
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao buscar estilo por ID");
        }

        return null;
    }
}

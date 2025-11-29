package ifpr.edu.br.model.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Estilo;
import ifpr.edu.br.model.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstiloDAO {

    private Connection con;

    public EstiloDAO(){
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarEstiloHasMusica(Musica musica){
        String sqlEstilo = "INSERT INTO estilo_has_musica(estilo_idestilo, musica_idmusica) VALUES(?, ?)";

        try {
            PreparedStatement psEstilo = con.prepareStatement(sqlEstilo, Statement.RETURN_GENERATED_KEYS);

            for(Estilo estilo : musica.getEstilos()){
                psEstilo.setInt(1, estilo.getEstiloID());
                psEstilo.setInt(2, musica.getMusicaID());

                psEstilo.executeUpdate();

                ResultSet rs = psEstilo.getGeneratedKeys();
                int idEstilo = 0;
                if (rs.next()) idEstilo = rs.getInt(1);
                estilo.setEstiloID(idEstilo);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
}

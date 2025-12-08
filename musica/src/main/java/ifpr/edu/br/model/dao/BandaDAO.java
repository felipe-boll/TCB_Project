package ifpr.edu.br.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ifpr.edu.br.model.Agencia;
import ifpr.edu.br.model.Banda;

public class BandaDAO {

    private Connection con;

    public BandaDAO(){
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarBanda(Banda banda){
        String sqlBanda = "INSERT INTO banda(nome, agencia_idagencia) VALUES(?, ?)";

        try {
            PreparedStatement psBanda = con.prepareStatement(sqlBanda, Statement.RETURN_GENERATED_KEYS);

            psBanda.setString(1, banda.getNome());
            psBanda.setInt(2, banda.getAgenciaID());

            psBanda.executeUpdate();

            ResultSet rs = psBanda.getGeneratedKeys();
            int idBanda = 0;
            if(rs.next()) idBanda = rs.getInt(1);
            banda.setBandaID(idBanda);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a banda: " + e.getMessage());
        }
    }

    public List<Banda> listarBanda(){
        List<Banda> lista = new ArrayList<>();
        String sql = "SELECT * FROM banda";

        try{
            PreparedStatement psBanda = con.prepareStatement(sql);
            ResultSet rs = psBanda.executeQuery();

            CantorDAO cantorDAO = new CantorDAO();

            while (rs.next()) {
                Banda banda = new Banda();
                banda.setBandaID(rs.getInt("idbanda"));
                banda.setNome(rs.getString("nome"));

                Agencia agencia = new Agencia();
                int idAgencia = rs.getInt("agencia_idagencia");

                agencia.setAgenciaID(idAgencia);
                banda.setAgenciaID(idAgencia);

                banda.setCantores(cantorDAO.listarCantoresPorBanda(banda.getBandaID()));

                lista.add(banda);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar bandas");
        }
        
        return lista;
    }

    public void atualizarNomeBanda(Banda banda){
        String sql = "UPDATE banda SET nome = ? WHERE idbanda = ?";

        try{
            PreparedStatement psBanda = con.prepareStatement(sql);

            psBanda.setString(1, banda.getNome());

            psBanda.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao mudar o nome da banda");
        }
    }

    public void atualizarAgenciaBanda(Banda banda){
        String sql = "UPDATE banda SET agencia_idagencia = ? WHERE idbanda = ?";

        try{
            PreparedStatement psBanda = con.prepareStatement(sql);

            psBanda.setInt(1, banda.getAgenciaID());

            psBanda.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao mudar a agencia da banda");
        }
    }

    public void deletarBanda(int idBanda){
        String sql = "DELETE FROM banda WHERE idbanda = ?";

        try{
            PreparedStatement psBanda = con.prepareStatement(sql);
            psBanda.setInt(1, idBanda);

            psBanda.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Erro ao deletar a banda");
        }
    }

    public Banda selectBanda(int idBanda){
        String sql = "SELECT * FROM banda WHERE idbanda = ?";

        try{
            PreparedStatement psBanda = con.prepareStatement(sql);

            psBanda.setInt(1, idBanda);
            ResultSet rs = psBanda.executeQuery();

            if (rs.next()) {
                Banda b = new Banda();
                b.setBandaID(rs.getInt("idBanda"));
                b.setNome(rs.getString("nome"));
                return b;
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao buscar banda por ID");
        }

        return null;
    }

    public void removerCantorDaBanda(int idBanda, int idCantor) {
        String sql = "DELETE FROM banda_has_cantor WHERE banda_idbanda = ? AND cantor_idcantor = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBanda);
            ps.setInt(2, idCantor);
            ps.executeUpdate();
            System.out.println("Cantor removido da banda com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover cantor da banda");
        }
    }


}

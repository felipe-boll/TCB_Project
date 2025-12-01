package ifpr.edu.br.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            PreparedStatement psBanda = con.prepareStatement(sqlBanda);

            psBanda.setString(1, banda.getNome());
            psBanda.setInt(2, banda.getAgenciaID().getAgenciaID());

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a banda");
        }
    }

    public List<Banda> listarBanda(){
        List<Banda> lista = new ArrayList<>();
        String sql = "SELECT * FROM banda";

        try{
            PreparedStatement psBanda = con.prepareStatement(sql);
            ResultSet rs = psBanda.executeQuery();

            while (rs.next()) {
                Banda banda = new Banda();
                banda.setBandaID(rs.getInt("idbanda"));
                banda.setNome(rs.getString("nome"));

                Agencia agencia = new Agencia();
                agencia.setAgenciaID(rs.getInt("agencia_idagencia"));
                banda.setAgenciaID(agencia);

                lista.add(banda);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar bandas");
        }
        
        return lista;
    }

    public void atualizarBanda(Banda banda){
        String sql = "UPDATE banda SET nome = ?, agencia_idagencia = ? WHERE idbanda = ?";

        try{
            PreparedStatement psBanda = con.prepareStatement(sql);

            psBanda.setString(1, banda.getNome());
            psBanda.setInt(2, banda.getAgenciaID().getAgenciaID());

            psBanda.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar a banda");
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



}

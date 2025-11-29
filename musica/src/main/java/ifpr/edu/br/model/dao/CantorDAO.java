package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Cantor;


public class CantorDAO {

    private Connection con;

    public CantorDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarCantor(Banda banda, Cantor cantor) {
        String sqlCantor = "INSERT INTO cantor(nome, cpf, email, idade, banda_idbanda) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement psCantor = con.prepareStatement(sqlCantor);

            psCantor.setString(1, cantor.getNome());
            psCantor.setString(2, cantor.getCpf());
            psCantor.setString(3, cantor.getEmail());
            psCantor.setInt(4, cantor.getIdade());
            psCantor.setInt(5, banda.getBandaID());

            psCantor.executeUpdate();

            ResultSet rs = psCantor.getGeneratedKeys();
                int idCantor = 0;
                if(rs.next()) idCantor = rs.getInt(1);
                cantor.setCantorID(idCantor);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Cantor> listarCantores() {
        List<Cantor> lista = new ArrayList<>();
        String sql = "SELECT * FROM cantor";

        try{
            PreparedStatement psCantor = con.prepareStatement(sql);
            ResultSet rs = psCantor.executeQuery();

            while(rs.next()){
                Cantor cantor = new Cantor();
                cantor.setCantorID(rs.getInt("idcantor"));
                cantor.setNome(rs.getString("nome"));

                cantor.setBandas(listarBandas(cantor.getCantorID()));

                lista.add(cantor);
            }
        } catch (SQLException e) {
          throw new RuntimeException("Erro ao listar cantores");
        }
        return lista;
    }

    public void atualizarCantor(String nome, int idade, int idCantor) {
        String sql = "UPDATE cantor SET nome = ?, idade = ? WHERE idcantor = ?";

        try {
            PreparedStatement psCantor = con.prepareStatement(sql);
            psCantor.setString(1, nome);
            psCantor.setInt(2, idade);
            psCantor.setInt(3, idCantor);

            psCantor.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cantor");
        }

    }

    public void deletarCantor(int idCantor) {
        String sql = "DELETE FROM cantor WHERE idcantor = ?";

        try {
            PreparedStatement psCantor = con.prepareStatement(sql);
            psCantor.setInt(1, idCantor);

            psCantor.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cantor");
        }
    }

    private List<Banda> listarBandas(int idCantor) {
        List<Banda> bandas = new ArrayList<>();
        String sql = """
            SELECT b.idbanda, b.nome
            FROM banda b
            JOIN cantor_has_banda chb ON b.idbanda = chb.banda_idbanda
            WHERE chb.cantor_idcantor = ?
        """;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCantor);
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

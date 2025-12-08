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


public class CantorDAO {

    private Connection con;

    public CantorDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarCantor(Cantor cantor, int idBanda) {
        String sqlCantor = "INSERT INTO cantor(nome, senha, email, idade, banda_idbanda) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement psCantor = con.prepareStatement(sqlCantor, Statement.RETURN_GENERATED_KEYS);

            psCantor.setString(1, cantor.getNome());
            psCantor.setString(2, cantor.getSenha());
            psCantor.setString(3, cantor.getEmail());
            psCantor.setInt(4, cantor.getIdade());
            psCantor.setInt(5, idBanda);

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
                
                int idBanda = rs.getInt("banda_idbanda");
                Banda banda = buscarBanda(idBanda);
                cantor.setBanda(banda);

                lista.add(cantor);
            }
        } catch (SQLException e) {
          throw new RuntimeException("Erro ao listar cantores");
        }
        return lista;
    }

    // analisar o banco pra saber se tem banda em cantor para atualizar
    public void atualizarCantorSenha(Cantor cantor) {
        String sql = "UPDATE cantor SET senha = ? WHERE idcantor = ?";

        try {
            PreparedStatement psCantor = con.prepareStatement(sql);
            psCantor.setString(1, cantor.getSenha());
            psCantor.setInt(2, cantor.getCantorID());

            psCantor.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cantor");
        }

    }

    public void atualizarCantorBanda(Cantor cantor, int idBanda) {
        String sql = "UPDATE cantor SET banda_idbanda = ? WHERE idcantor = ?";

        try {
            PreparedStatement psCantor = con.prepareStatement(sql);
            psCantor.setInt(1, idBanda);
            psCantor.setInt(2, cantor.getCantorID());

            psCantor.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cantor");
        }
    }

    public Cantor login(int idCantor, String senha){
        String sql = "SELECT * FROM cantor WHERE idcantor = ? AND senha = ?";

        try{
            PreparedStatement psCantor = con.prepareStatement(sql);

            psCantor.setInt(1, idCantor);
            psCantor.setString(2, senha);

            ResultSet rs = psCantor.executeQuery();

            if (rs.next()) {
                Cantor cantor = new Cantor();
                cantor.setCantorID(rs.getInt("idcantor"));
                cantor.setNome(rs.getString("nome"));
                cantor.setEmail(rs.getString("email"));
                return cantor;
            }

            return null;
        } catch(SQLException e){
            throw new RuntimeException("Erro ao fazer login");
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

    private Banda buscarBanda(int idBanda) {
        Banda banda = null;
        String sql = "SELECT * FROM banda WHERE idbanda = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBanda);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                banda = new Banda();
                banda.setBandaID(rs.getInt("idbanda"));
                banda.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return banda;
    }

    public List<Cantor> listarCantoresPorBanda(int idBanda) {
        List<Cantor> lista = new ArrayList<>();
        String sql = "SELECT * FROM cantor WHERE banda_idbanda = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBanda);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cantor cantor = new Cantor();
                    cantor.setCantorID(rs.getInt("idcantor"));
                cantor.setNome(rs.getString("nome"));
                lista.add(cantor);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cantores da banda");
        }

        return lista;
    }


}

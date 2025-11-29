package ifpr.edu.br.model.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifpr.edu.br.model.Agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgenciaDAO {

    private Connection con;

    public AgenciaDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    public void salvarAgencia(Agencia agencia){
        String sqlAgencia = "INSERT INTO agencia(nome, email, cnpj) VALUES(?, ?, ?)";

        try {
            PreparedStatement psAgencia = con.prepareStatement(sqlAgencia, Statement.RETURN_GENERATED_KEYS);

            psAgencia.setString(1, agencia.getNome());
            psAgencia.setString(2, agencia.getEmail());
            psAgencia.setString(3, agencia.getCnpj());

            ResultSet rs = psAgencia.getGeneratedKeys();
                int idAgencia = 0;
                if(rs.next()) idAgencia = rs.getInt(1);
                agencia.setAgenciaID(idAgencia);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a agência");
        }
    }

    public List<Agencia> listarAgencias(){
        List<Agencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM agencia";

        try{
            PreparedStatement psAgencia = con.prepareStatement(sql);
            ResultSet rs = psAgencia.executeQuery();

            while(rs.next()){
                Agencia agencia = new Agencia();
                agencia.setAgenciaID(rs.getInt("idagencia"));
                agencia.setNome(rs.getString("nome"));
                agencia.setEmail(rs.getString("email"));
                agencia.setCnpj(rs.getString("cnpj"));
                lista.add(agencia);
            }
        } catch (SQLException e) {
          throw new RuntimeException("Erro ao listar as agências");
        }
        return lista;
    }

    public void atualizarAgencia(Agencia agencia){
        String sql = "UPDATE agencia SET nome = ?, email = ?, cnpj = ? WHERE idagencia = ?";

        try{
            PreparedStatement psAgencia = con.prepareStatement(sql);

            psAgencia.setString(1, agencia.getNome());
            psAgencia.setString(2, agencia.getEmail());
            psAgencia.setString(3, agencia.getCnpj());
            psAgencia.setInt(4, agencia.getAgenciaID());

            psAgencia.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Erro ao atualizar a agência");
        }
    }

    public void deletarAgencia(int idAgencia){
        String sql = "DELETE FROM agencia WHERE idagencia = ?";

        try{
            PreparedStatement psAgencia = con.prepareStatement(sql);
            psAgencia.setInt(1, idAgencia);

            psAgencia.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Erro ao deletar a agência");
        }
    }
}

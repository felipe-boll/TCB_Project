package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifpr.edu.br.model.Banda;
import ifpr.edu.br.model.Cantor;
import ifpr.edu.br.model.Pessoa;

public class CantorDAO {
    public void salvarCantor(Banda banda, Pessoa pessoa, Cantor cantor){
        String sqlCantor = "INSERT INTO cantor(pessoa_idpessoa, banda_idbanda) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement psCantor = con.prepareStatement(sqlCantor);

            psCantor.setInt(2, pessoa.getPessoaID());
            psCantor.setInt(1, banda.getBandaID());

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
}

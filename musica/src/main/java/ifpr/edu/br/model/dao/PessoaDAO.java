package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ifpr.edu.br.model.Pessoa;

public class PessoaDAO {
    public void salvarPessoa(Pessoa pessoa){
        String sqlPessoa = "INSERT INTO pessoa(nome, cpf, email, idade) VALUES(?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement psPessoa = con.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);

            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getCpf());
            psPessoa.setString(3, pessoa.getEmail());
            psPessoa.setInt(4, pessoa.getIdade());

            psPessoa.executeUpdate();
            
            ResultSet rs = psPessoa.getGeneratedKeys();
            int idPessoa = 0;
            if (rs.next()) idPessoa = rs.getInt(1);
            pessoa.setPessoaID(idPessoa); 
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

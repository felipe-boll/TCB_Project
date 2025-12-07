package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory(){}

    public static Connection getConnection(){
        try{
            if (connection == null) {
                String url = "jdbc:mysql://localhost:3306/mydb";
                String user = "root";
                //String user = "aluno";
                //String password = "aluno";
                String password = "FcB@28072009";
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("conectado ao banco com sucesso");
            }
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

}

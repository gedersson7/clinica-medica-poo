package br.edu.imepac.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectarBanco {

    public static void main(String[] args) {
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/clinica_medica";
        String usuario = "root";
        String senha = "1234";

        try {
            Class.forName(DRIVER);

            Connection connection = DriverManager.getConnection(URL, usuario, senha);

            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso");

            connection.close();

        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado!\n" + erro.toString());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados\n" + erro.toString());
        }
    }
}


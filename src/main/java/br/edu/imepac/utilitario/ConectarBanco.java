package br.edu.imepac.utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectarBanco {

    // Constantes para conexão
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/clinica_medica";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Carregar o driver
            Class.forName(DRIVER);

            // Estabelecer conexão
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!");

        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado!\n" + erro.toString());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados\n" + erro.toString());
        } finally {
            // Fechar conexão, se aberta
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão\n" + erro.toString());
                }
            }
        }
    }


    public static Connection getConectar() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados\n" + erro.toString());
            return null;
        }
    }
}


package br.edu.imepac.adminsitrativo;
import java.sql.*;
import javax.swing.JOptionPane;

public class TestePacienteDao {

        public static void main(String[] args) {
                // Defina a URL do banco, usuário e senha
                String DRIVER = "com.mysql.cj.jdbc.Driver";
                String URL = "jdbc:mysql://localhost:3306/clinica_medica";
                String usuario = "root";
                String senha = "1234";

                try {
                        // Carrega o Driver JDBC
                        Class.forName(DRIVER);

                        // Estabelece a conexão com o banco de dados
                        Connection connection = DriverManager.getConnection(URL, usuario, senha);
                        JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!");

                        // Teste de inserção de um paciente
                        inserirPaciente(connection);

                        // Teste de consulta de paciente pelo CPF
                        consultarPaciente(connection, "12345678901"); // Insira o CPF de teste

                        // Fecha a conexão com o banco de dados
                        connection.close();

                } catch (ClassNotFoundException erro) {
                        JOptionPane.showMessageDialog(null, "Driver não encontrado!\n" + erro.toString());
                } catch (SQLException erro) {
                        JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados\n" + erro.toString());
                }
        }

        // Método para inserir um paciente no banco
        public static void inserirPaciente(Connection connection) {
                try {
                        // SQL de inserção
                        String sqlInsert = "INSERT INTO paciente (cpf, nome, email, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement stmtInsert = connection.prepareStatement(sqlInsert);

                        // Definindo os valores para a inserção
                        stmtInsert.setString(1, "12345678901"); // CPF
                        stmtInsert.setString(2, "João Silva");   // Nome
                        stmtInsert.setString(3, "joao@exemplo.com"); // Email
                        stmtInsert.setString(4, "987654321");     // Telefone
                        stmtInsert.setString(5, "M");             // Sexo
                        stmtInsert.setDate(6, Date.valueOf("1990-05-15")); // Data de nascimento

                        // Executa a inserção no banco
                        stmtInsert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Paciente inserido com sucesso!");

                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao inserir paciente: " + e.getMessage());
                }
        }

        // Método para consultar um paciente pelo CPF
        public static void consultarPaciente(Connection connection, String cpf) {
                try {
                        // SQL de consulta
                        String sqlSelect = "SELECT * FROM paciente WHERE cpf = ?";
                        PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
                        stmtSelect.setString(1, cpf); // CPF do paciente

                        // Executa a consulta
                        ResultSet rs = stmtSelect.executeQuery();

                        if (rs.next()) {
                                // Recupera os dados do paciente
                                String nome = rs.getString("nome");
                                String email = rs.getString("email");
                                String telefone = rs.getString("telefone");
                                String sexo = rs.getString("sexo");
                                Date dataNascimento = rs.getDate("data_nascimento");

                                // Exibe os dados do paciente
                                JOptionPane.showMessageDialog(null, "Paciente encontrado:\n" +
                                        "Nome: " + nome + "\n" +
                                        "Email: " + email + "\n" +
                                        "Telefone: " + telefone + "\n" +
                                        "Sexo: " + sexo + "\n" +
                                        "Data de Nascimento: " + dataNascimento);
                        } else {
                                JOptionPane.showMessageDialog(null, "Paciente não encontrado com o CPF: " + cpf);
                        }

                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao consultar paciente: " + e.getMessage());
                }
        }
}
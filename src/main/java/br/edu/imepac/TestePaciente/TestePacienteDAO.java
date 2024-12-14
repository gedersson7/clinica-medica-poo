package br.edu.imepac.TestePaciente;

import br.edu.imepac.daos.PacienteDAO;
import br.edu.imepac.entidades.Paciente;
import br.edu.imepac.utilitario.ConectarBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

class TestePacienteDAO {

    public static void main(String[] args) {
        // Criar paciente para teste
        Paciente paciente = new Paciente();
        paciente.setNome("Jose");
        paciente.setCpf("123.456.777-00");
        paciente.setEmail("joao.aaa@gmail.com");
        paciente.setDatanasc(LocalDate.parse("1990-01-01"));
        paciente.setTelefone("999999999");
        paciente.setSexo("M");

        // Criar DAO para manipulação
        PacienteDAO pacienteDAO = new PacienteDAO();

        // Teste de cadastro
        pacienteDAO.cadastrar(paciente);

        // Verifique no banco de dados se o paciente foi inserido
        verificarCadastroNoBanco(paciente);
    }

    public static void verificarCadastroNoBanco(Paciente paciente) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement smt = con.prepareStatement(sql)) {

            smt.setString(1, paciente.getCpf());
            ResultSet resultado = smt.executeQuery();

            if (resultado.next()) {
                // Se encontrou o paciente no banco, a inserção foi bem-sucedida
                System.out.println("Paciente encontrado no banco de dados:");
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("CPF: " + resultado.getString("cpf"));
                System.out.println("Email: " + resultado.getString("email"));
                System.out.println("Data de Nascimento: " + resultado.getString("dataNascimento"));
                System.out.println("Telefone: " + resultado.getString("telefone"));
                System.out.println("Sexo: " + resultado.getString("sexo"));
            } else {
                // Se não encontrou o paciente
                System.out.println("Paciente não encontrado no banco de dados.");
            }

        } catch (Exception ex) {
            System.out.println("Erro ao verificar o paciente no banco de dados: " + ex.getMessage());
        }
    }
}

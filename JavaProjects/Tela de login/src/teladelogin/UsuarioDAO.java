package teladelogin; // Define o pacote da classe

import java.sql.Connection; // Importa a classe de conexão com o banco
import java.sql.PreparedStatement; // Importa a classe para comandos SQL com parâmetros
import java.sql.ResultSet; // Importa a classe que armazena resultados de consultas
import java.sql.SQLException; // Importa a classe para tratar erros SQL

public class UsuarioDAO { // Classe responsável por acessar os dados dos usuários no banco
    private static final int LIMITE_TENTATIVAS = 3;
    private static final long TEMPO_BLOQUEIO = 3 * 60 * 1000;

    public Usuario buscarPorUsuario(String nomeUsuario) { // Método que busca um usuário pelo login informado

        String sql = "SELECT * FROM usuarios WHERE usuario = ?"; // Consulta SQL com parâmetro para evitar SQL Injection

        try (Connection conn = BancoDados.conectar(); // Abre conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara o comando SQL

            stmt.setString(1, nomeUsuario); // Substitui o primeiro ? pelo nome do usuário digitado

            ResultSet rs = stmt.executeQuery(); // Executa a consulta e guarda o resultado

            if (rs.next()) { // Verifica se encontrou algum usuário

                Usuario usuario = new Usuario(); // Cria um objeto Usuario

                usuario.setId(rs.getInt("id")); // Preenche o id com o valor vindo do banco

                usuario.setUsuario(rs.getString("usuario")); // Preenche o login com o valor vindo do banco

                usuario.setSenha(rs.getString("senha")); // Preenche a senha com o valor vindo do banco
                usuario.setTentativas(rs.getInt("tentativas"));
                usuario.setBloqueadoAte(rs.getLong("bloqueado_ate"));

                return usuario; // Retorna o usuário encontrado
            }

        } catch (SQLException e) { // Captura erros de banco de dados

            System.out.println("Erro ao buscar usuário: " + e.getMessage()); // Mostra o erro no console
        }

        return null; // Retorna null caso o usuário não seja encontrado
    }

    public boolean validarLogin(String nomeUsuario, String senhaDigitada) { // Método que valida usuário e senha

        Usuario usuario = buscarPorUsuario(nomeUsuario); // Busca o usuário no banco de dados

        if (usuario == null) { // Verifica se o usuário não foi encontrado

            return false; // Retorna falso porque o usuário não existe
        }

        return usuario.getSenha().equals(senhaDigitada); // Compara a senha digitada com a senha salva no banco
    }
    public long segundosRestantesBloqueio(Usuario usuario) {
    long restante = usuario.getBloqueadoAte() - System.currentTimeMillis();

    if (restante <= 0) {
        return 0;
    }

    return (restante + 999) / 1000;
}

public void zerarTentativas(String nomeUsuario) {
    String sql = "UPDATE usuarios SET tentativas = 0, bloqueado_ate = 0 WHERE usuario = ?";

    try (Connection conn = BancoDados.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);
        stmt.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Erro ao zerar tentativas: " + e.getMessage());
    }
}

public int registrarTentativaIncorreta(String nomeUsuario) {
        Usuario usuario = buscarPorUsuario(nomeUsuario);

        if (usuario == null) {
            return 0;
        }

        int novasTentativas = usuario.getTentativas() + 1;
        long bloqueadoAte = 0;

        if (novasTentativas >= LIMITE_TENTATIVAS) {
            bloqueadoAte = System.currentTimeMillis() + TEMPO_BLOQUEIO;
            novasTentativas = 0;
        }

        String sql = "UPDATE usuarios SET tentativas = ?, bloqueado_ate = ? WHERE usuario = ?";

        try (Connection conn = BancoDados.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novasTentativas);
            stmt.setLong(2, bloqueadoAte);
            stmt.setString(3, nomeUsuario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao registrar tentativa: " + e.getMessage());
        }

        if (bloqueadoAte > 0) {
            return LIMITE_TENTATIVAS;
        }

        return novasTentativas;
    }
}
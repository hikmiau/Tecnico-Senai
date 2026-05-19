package lojacarrosweb;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
public class HistoricoService {

    private static Deque<String> historico = new ArrayDeque<>();

    // Método responsável por registrar uma página visitada.
    public static void registrarPagina(String pagina) {
        
        // Evita registrar valores vazios no histórico.
        if (pagina == null || pagina.trim().isEmpty()) {
            return;
        }

        // Evita registrar a mesma página duas vezes seguidas.
        // Isso ajuda a impedir repetições causadas por carregamentos extras do navegador.
        if (!historico.isEmpty() && historico.peek().equals(pagina)) {
            return;
        }

        // Adiciona a página no topo da pilha.
        historico.push(pagina);
    }

    // Método responsável por listar o histórico.
    public static List<String> listarHistorico() {

        return new ArrayList<>(historico);
    }

    // Método responsável por simular o botão de voltar.
    public static String voltarPagina() {

        // Verifica se a pilha está vazia antes de remover.
        if (historico.isEmpty()) {

            // Retorna null se não houver página para remover.
            return null;
        }

        // Remove e retorna a última página adicionada, seguindo o modelo LIFO.
        return historico.pop();
    }
}

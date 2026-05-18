package lojacarrosweb;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
public class HistoricoService {

    /*
     ============================================================
     ATENÇÃO — CLASSE PRINCIPAL DO DESAFIO DOS ALUNOS
     ============================================================

     Esta classe controla o histórico de páginas visitadas.

     No momento, o histórico está usando ArrayList.

     Isso funciona apenas como uma lista simples.

     Porém, o comportamento correto para o desafio é usar PILHA.

     O aluno deverá alterar esta classe para usar:

     import java.util.Deque;
     import java.util.ArrayDeque;

     Depois deverá trocar:

     private static List<String> historico = new ArrayList<>();

     por:

     private static Deque<String> historico = new ArrayDeque<>();

     A lógica correta deve seguir o modelo LIFO:

     LIFO = Last In, First Out
     Último que entra, primeiro que sai.

     Exemplo:

     1. Usuário acessa Página Inicial.
     2. Usuário acessa Sedan Executivo.
     3. Usuário acessa SUV Familiar.

     Ao clicar em "Voltar Página", deve sair primeiro:
     SUV Familiar.

     Depois:
     Sedan Executivo.

     Depois:
     Página Inicial.

     Portanto, o aluno deve usar:
     push() para adicionar página;
     pop() para remover a última página;
     peek() para consultar a última página;
     isEmpty() para verificar se a pilha está vazia.
    */

    // Lista usada temporariamente para armazenar o histórico.
    // ESTA LINHA DEVERÁ SER ALTERADA PELOS ALUNOS PARA UMA PILHA.
    private static Deque<String> historico = new ArrayDeque<>();

    // Método responsável por registrar uma página visitada.
    public static void registrarPagina(String pagina) {

        /*
         ============================================================
         ALTERAÇÃO OBRIGATÓRIA PARA OS ALUNOS
         ============================================================

         Atualmente a página é adicionada ao final da lista usando add().

         Em uma pilha com Deque, o correto será usar:

         historico.push(pagina);

         Isso coloca a nova página no topo da pilha.
        */

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

        /*
         ============================================================
         ALTERAÇÃO RECOMENDADA PARA OS ALUNOS
         ============================================================

         Atualmente o método retorna uma cópia da lista.

         Quando a estrutura for trocada para Deque,
         os alunos deverão adaptar este retorno para continuar
         exibindo as páginas no navegador.

         Exemplo:

         return new ArrayList<>(historico);
        */

        return new ArrayList<>(historico);
    }

    // Método responsável por simular o botão de voltar.
    public static String voltarPagina() {

        /*
         ============================================================
         ALTERAÇÃO OBRIGATÓRIA PARA OS ALUNOS
         ============================================================

         Atualmente este método remove o último item da lista.

         Isso até parece funcionar como pilha, mas ainda não usa
         a estrutura correta de pilha.

         O objetivo da atividade é que o aluno substitua essa lógica por:

         if (!historico.isEmpty()) {
             return historico.pop();
         }

         usando Deque<String>.

         O aluno também deve cuidar para não remover quando o histórico estiver vazio.
        */

        // Verifica se a pilha está vazia antes de remover.
        if (historico.isEmpty()) {

            // Retorna null se não houver página para remover.
            return null;
        }

        // Remove e retorna a última página adicionada, seguindo o modelo LIFO.
        return historico.pop();
    }
}
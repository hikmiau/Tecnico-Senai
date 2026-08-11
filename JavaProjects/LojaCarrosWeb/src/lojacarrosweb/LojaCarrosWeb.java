package lojacarrosweb;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.util.List;

public class LojaCarrosWeb {

    // Objeto responsável por controlar o servidor web.
    private static HttpServer servidor;

    public static void main(String[] args) throws Exception {

        // Cria o servidor na porta 9091.
        servidor = HttpServer.create(new InetSocketAddress(9091), 0);

        // Rota da página inicial.
        servidor.createContext("/", exchange -> {
            try {
                paginaInicial(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Rota para exibir um carro específico.
        servidor.createContext("/carro", exchange -> {
            try {
                paginaCarro(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Rota para exibir o histórico de páginas visitadas.
        servidor.createContext("/historico", exchange -> {
            try {
                paginaHistorico(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Rota para simular o botão de voltar.
        servidor.createContext("/voltar", exchange -> {
            try {
                voltarPagina(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Rota para fechar o sistema.
        servidor.createContext("/fechar", exchange -> {
            try {
                fecharSistema(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Inicia o servidor.
        servidor.start();

        // Mostra o endereço no console.
        System.out.println("Loja de carros rodando em: http://localhost:9091");
    }

    public static void paginaInicial(HttpExchange exchange) throws Exception {

        // Registra a página inicial no histórico.
        HistoricoService.registrarPagina("Página Inicial - Loja de Carros");

        // Busca a lista de carros cadastrados.
        List<Carro> carros = CarroService.listarCarros();

        // Inicia a construção do HTML.
        String html = "";

        html += HtmlUtilCarros.inicioHtml("Loja de Carros Java");

        html += "<div class='card'>";
        html += "<h1>Loja de Carros Java</h1>";
        html += "<p>Escolha um veículo para visualizar os detalhes.</p>";

        html += "<div class='menu-topo'>";
        html += "<a class='botao secundario' href='/historico'>Ver Histórico</a>";
        html += "<a class='botao alerta' href='/voltar'>Voltar Página</a>";
        html += "<a class='botao perigo' href='/fechar'>Fechar</a>";
        html += "</div>";

        html += "<div class='grid'>";

        // Percorre todos os carros cadastrados.
        for (Carro carro : carros) {

            html += "<div class='carro-card'>";

            // Exibe uma figura simbólica do carro.
            html += "<div class='figura'>" + carro.getFigura() + "</div>";

            // Exibe o nome do carro.
            html += "<h2>" + carro.getNome() + "</h2>";

            // Exibe o ano do carro.
            html += "<p><strong>Ano:</strong> " + carro.getAno() + "</p>";

            // Exibe o preço do carro.
            html += "<p class='preco'>R$ " + String.format("%.2f", carro.getPreco()) + "</p>";

            // Link para abrir a página específica do carro.
            html += "<a class='botao' href='/carro?id=" + carro.getId() + "'>Ver Detalhes</a>";

            html += "</div>";
        }

        html += "</div>";
        html += "</div>";

        html += HtmlUtilCarros.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void paginaCarro(HttpExchange exchange) throws Exception {

        // Captura a URL acessada.
        String query = exchange.getRequestURI().getQuery();

        // Variável que receberá o ID do carro.
        int id = 0;

        // Verifica se existe parâmetro na URL.
        if (query != null && query.startsWith("id=")) {

            // Extrai o número do ID.
            id = Integer.parseInt(query.replace("id=", ""));
        }

        // Busca o carro pelo ID.
        Carro carro = CarroService.buscarPorId(id);

        // Se o carro não for encontrado, mostra mensagem de erro.
        if (carro == null) {

            String html = "";

            html += HtmlUtilCarros.inicioHtml("Carro não encontrado");
            html += "<div class='card central'>";
            html += "<h1>Carro não encontrado</h1>";
            html += "<p>O veículo solicitado não existe.</p>";
            html += "<a class='botao' href='/'>Voltar</a>";
            html += "</div>";
            html += HtmlUtilCarros.fimHtml();

            enviarResposta(exchange, html);
            return;
        }

        // Registra a página do carro no histórico.
        HistoricoService.registrarPagina("Carro visitado: " + carro.getNome());

        String html = "";

        html += HtmlUtilCarros.inicioHtml(carro.getNome());

        html += "<div class='card central'>";
        html += "<div class='figura grande'>" + carro.getFigura() + "</div>";
        html += "<h1>" + carro.getNome() + "</h1>";
        html += "<p><strong>Ano:</strong> " + carro.getAno() + "</p>";
        html += "<p><strong>Preço:</strong> R$ " + String.format("%.2f", carro.getPreco()) + "</p>";
        html += "<p class='descricao'>" + carro.getDescricao() + "</p>";

        html += "<div class='menu-topo'>";
        html += "<a class='botao' href='/'>Voltar para Loja</a>";
        html += "<a class='botao secundario' href='/historico'>Ver Histórico</a>";
        html += "<a class='botao alerta' href='/voltar'>Voltar Página</a>";
        html += "</div>";

        html += "</div>";

        html += HtmlUtilCarros.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void paginaHistorico(HttpExchange exchange) throws Exception {

        /*
         ============================================================
         ATENÇÃO — PARTE CENTRAL DO DESAFIO DOS ALUNOS
         ============================================================

         Este sistema possui um histórico de páginas visitadas.

         Porém, o desafio dos alunos é analisar se esse histórico está
         funcionando corretamente como uma PILHA.

         Uma pilha segue o modelo LIFO:

         LIFO = Last In, First Out
         Último que entra, primeiro que sai.

         Em um navegador, quando o usuário clica em "voltar",
         a última página acessada deve ser a primeira a sair do histórico.

         Os alunos deverão verificar e implementar corretamente:

         1. Como registrar cada página acessada;
         2. Como guardar as páginas em uma estrutura de pilha;
         3. Como remover a última página ao clicar em "Voltar Página";
         4. Como exibir a sequência correta de páginas visitadas;
         5. Como evitar erro quando o histórico estiver vazio.

         Estruturas recomendadas:

         Deque<String> historico = new ArrayDeque<>();

         Métodos importantes:

         push()  -> adiciona no topo da pilha
         pop()   -> remove o último item adicionado
         peek()  -> consulta o último item sem remover
         isEmpty() -> verifica se a pilha está vazia

         O aluno NÃO deve usar ordem aleatória neste desafio.
         O aluno NÃO deve remover a primeira página cadastrada.
         O aluno DEVE respeitar o comportamento LIFO.
        */

        List<String> historico = HistoricoService.listarHistorico();

        String html = "";

        html += HtmlUtilCarros.inicioHtml("Histórico de Páginas");

        html += "<div class='card'>";
        html += "<h1>Histórico de Páginas Visitadas</h1>";

        html += "<p class='aviso'>";
        html += "Esta tela mostra as páginas acessadas. O desafio é garantir que o botão Voltar funcione como uma pilha LIFO.";
        html += "</p>";

        if (historico.isEmpty()) {

            html += "<p>Nenhuma página foi registrada no histórico.</p>";

        } else {

            html += "<ol class='historico'>";

            for (String pagina : historico) {

                html += "<li>" + pagina + "</li>";
            }

            html += "</ol>";
        }

        html += "<div class='menu-topo'>";
        html += "<a class='botao' href='/'>Voltar para Loja</a>";
        html += "<a class='botao alerta' href='/voltar'>Voltar Página</a>";
        html += "</div>";

        html += "</div>";

        html += HtmlUtilCarros.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void voltarPagina(HttpExchange exchange) throws Exception {

        /*
         ============================================================
         PARTE QUE OS ALUNOS DEVERÃO ANALISAR E MELHORAR
         ============================================================

         Este método simula o botão "Voltar Página".

         Em uma pilha correta:
         - deve sair a última página visitada;
         - a estrutura deve usar LIFO;
         - se não houver página anterior, o sistema deve avisar o usuário.

         O aluno deverá verificar se o método HistoricoService.voltarPagina()
         realmente remove a última página visitada.

         Caso não esteja adequado, deverá corrigir usando:

         pop()
         peek()
         isEmpty()

         dentro de uma estrutura Deque ou Stack.
        */

        String paginaRemovida = HistoricoService.voltarPagina();

        String html = "";

        html += HtmlUtilCarros.inicioHtml("Voltar Página");

        html += "<div class='card central'>";
        html += "<h1>Simulação do Botão Voltar</h1>";

        if (paginaRemovida == null) {

            html += "<p>Não há páginas suficientes no histórico para voltar.</p>";

        } else {

            html += "<p>A seguinte página foi removida do topo do histórico:</p>";
            html += "<h2>" + paginaRemovida + "</h2>";
        }

        html += "<div class='menu-topo'>";
        html += "<a class='botao' href='/'>Voltar para Loja</a>";
        html += "<a class='botao secundario' href='/historico'>Ver Histórico</a>";
        html += "</div>";

        html += "</div>";

        html += HtmlUtilCarros.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void fecharSistema(HttpExchange exchange) throws Exception {

        String html = "";

        html += HtmlUtilCarros.inicioHtml("Sistema Encerrado");
        html += "<div class='card central'>";
        html += "<h1>Sistema encerrado</h1>";
        html += "<p>Você pode fechar esta aba do navegador.</p>";
        html += "</div>";
        html += HtmlUtilCarros.fimHtml();

        enviarResposta(exchange, html);

        servidor.stop(1);
    }

    public static void enviarResposta(HttpExchange exchange, String resposta) throws Exception {

        byte[] bytes = resposta.getBytes("UTF-8");

        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");

        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();

        os.write(bytes);

        os.close();
    }
}
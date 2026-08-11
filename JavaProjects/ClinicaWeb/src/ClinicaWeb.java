import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.util.List;

public class ClinicaWeb {

    private static HttpServer servidor;

    public static void main(String[] args) throws Exception {

        servidor = HttpServer.create(new InetSocketAddress(9090), 0);

        servidor.createContext("/", exchange -> {
            try {
                paginaInicial(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/cadastro", exchange -> {
            try {
                paginaCadastro(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/salvar", exchange -> {
            try {
                salvarPaciente(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/lista", exchange -> {
            try {
                exibirLista(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/fechar", exchange -> {
            try {
                fecharSistema(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.start();

        System.out.println("Sistema da clínica rodando em: http://localhost:9090");
    }

    public static void paginaInicial(HttpExchange exchange) throws Exception {

        String html = "";

        html += HtmlUtil.inicioHtml("Sistema de Atendimento");
        html += "<div class='card central'>";
        html += "<h1>Clínica Vida Mais</h1>";
        html += "<p>Sistema web simples para cadastro e organização de pacientes.</p>";

        html += "<div class='menu'>";
        html += "<a class='botao' href='/cadastro'>Cadastrar Paciente</a>";
        html += "<a class='botao secundario' href='/lista'>Exibir Lista</a>";
        html += "<a class='botao perigo' href='/fechar'>Fechar</a>";
        html += "</div>";

        html += "</div>";
        html += HtmlUtil.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void paginaCadastro(HttpExchange exchange) throws Exception {

        String html = "";

        html += HtmlUtil.inicioHtml("Cadastro de Paciente");

        html += "<div class='card'>";
        html += "<h1>Cadastro de Paciente</h1>";
        html += "<p>Preencha os dados do paciente para registrar no sistema.</p>";

        html += "<form method='post' action='/salvar'>";

        html += "<label>Nome:</label>";
        html += "<input type='text' name='nome' required>";

        html += "<label>Idade:</label>";
        html += "<input type='number' name='idade' required>";

        html += "<label>Peso:</label>";
        html += "<input type='text' name='peso' required>";

        html += "<label>Altura:</label>";
        html += "<input type='text' name='altura' required>";

        html += "<label>Pressão:</label>";
        html += "<input type='text' name='pressao' placeholder='Exemplo: 120/80' required>";

        html += "<label>Temperatura:</label>";
        html += "<input type='text' name='temperatura' placeholder='Exemplo: 36.5' required>";

        html += "<label>Observações:</label>";
        html += "<textarea name='observacoes'></textarea>";

        html += "<h2>Endereço</h2>";

        html += "<label>Rua:</label>";
        html += "<input type='text' name='rua' required>";

        html += "<label>Número:</label>";
        html += "<input type='text' name='numero' required>";

        html += "<label>Bairro:</label>";
        html += "<input type='text' name='bairro' required>";

        html += "<label>Cidade:</label>";
        html += "<input type='text' name='cidade' required>";

        html += "<label>UF:</label>";
        html += "<input type='text' name='uf' maxlength='2' required>";

        html += "<div class='menu'>";
        html += "<button class='botao' type='submit'>Salvar Paciente</button>";
        html += "<a class='botao secundario' href='/'>Voltar</a>";
        html += "</div>";

        html += "</form>";
        html += "</div>";

        html += HtmlUtil.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void salvarPaciente(HttpExchange exchange) throws Exception {

        Paciente paciente = PacienteService.criarPacienteDoFormulario(exchange);

        PacienteService.adicionarPaciente(paciente);

        String html = "";

        html += HtmlUtil.inicioHtml("Paciente Salvo");
        html += "<div class='card central'>";
        html += "<h1>Paciente cadastrado com sucesso!</h1>";
        html += "<p>O paciente foi adicionado à lista de atendimento.</p>";

        html += "<div class='menu'>";
        html += "<a class='botao' href='/cadastro'>Cadastrar Novo</a>";
        html += "<a class='botao secundario' href='/lista'>Exibir Lista</a>";
        html += "<a class='botao perigo' href='/'>Menu Principal</a>";
        html += "</div>";

        html += "</div>";
        html += HtmlUtil.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void exibirLista(HttpExchange exchange) throws Exception {

        List<Paciente> pacientes = PacienteService.listarPacientesAleatorios();

        String html = "";

        html += HtmlUtil.inicioHtml("Lista de Atendimento");

        html += "<div class='card'>";
        html += "<h1>Lista de Pacientes</h1>";
        html += "<p class='aviso'>A lista abaixo está organizada no modelo FIFO: o primeiro paciente cadastrado é o primeiro da fila.</p>";

        if (pacientes.isEmpty()) {

            html += "<p>Nenhum paciente cadastrado até o momento.</p>";

        } else {

            html += "<table>";
            html += "<tr>";
            html += "<th>Nome</th>";
            html += "<th>Idade</th>";
            html += "<th>Peso</th>";
            html += "<th>Altura</th>";
            html += "<th>Pressão</th>";
            html += "<th>Temperatura</th>";
            html += "<th>Endereço</th>";
            html += "<th>Observações</th>";
            html += "</tr>";

            for (Paciente p : pacientes) {

                html += "<tr>";
                html += "<td>" + p.getNome() + "</td>";
                html += "<td>" + p.getIdade() + "</td>";
                html += "<td>" + p.getPeso() + "</td>";
                html += "<td>" + p.getAltura() + "</td>";
                html += "<td>" + p.getPressao() + "</td>";
                html += "<td>" + p.getTemperatura() + "</td>";
                html += "<td>" + p.getRua() + ", " + p.getNumero() + " - " + p.getBairro() + " - " + p.getCidade() + "/" + p.getUf() + "</td>";
                html += "<td>" + p.getObservacoes() + "</td>";
                html += "</tr>";
            }

            html += "</table>";
        }

        html += "<div class='menu'>";
        html += "<a class='botao' href='/cadastro'>Cadastrar Paciente</a>";
        html += "<a class='botao secundario' href='/'>Menu Principal</a>";
        html += "</div>";

        html += "</div>";
        html += HtmlUtil.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void fecharSistema(HttpExchange exchange) throws Exception {

        String html = "";

        html += HtmlUtil.inicioHtml("Sistema Encerrado");
        html += "<div class='card central'>";
        html += "<h1>Sistema encerrado</h1>";
        html += "<p>Você pode fechar esta aba do navegador.</p>";
        html += "</div>";
        html += HtmlUtil.fimHtml();

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
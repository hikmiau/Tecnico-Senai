package cadastroweb;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.List;

public class Cadastroweb {

    private static HttpServer servidor;

    public static void main(String[] args) throws Exception {

        PessoaService.criarCadastrosIniciaisDuplicados();

        servidor = HttpServer.create(new InetSocketAddress(9095), 0);

        servidor.createContext("/", exchange -> {
            try {
                telaPrincipal(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/criar", exchange -> {
            try {
                telaCriar(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/salvar", exchange -> {
            try {
                salvarPessoa(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/pesquisar", exchange -> {
            try {
                pesquisar(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/editar", exchange -> {
            try {
                telaEditar(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/atualizar", exchange -> {
            try {
                atualizarPessoa(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/excluir", exchange -> {
            try {
                excluirPessoa(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.createContext("/sair", exchange -> {
            try {
                sair(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        servidor.start();

        System.out.println("Sistema de cadastro rodando em: http://localhost:9095");
    }

    public static void telaPrincipal(HttpExchange exchange) throws Exception {

        String html = "";

        html += HtmlCadastro.inicioHtml("Sistema de Cadastro");
        html += "<div class='card central'>";
        html += "<h1>Sistema de Cadastro</h1>";
        html += "<p>Desafio: identificar e corrigir cadastros duplicados.</p>";

        html += "<div class='menu'>";
        html += "<a class='botao' href='/criar'>Criar</a>";
        html += "<a class='botao secundario' href='/pesquisar'>Pesquisar</a>";
        html += "<a class='botao perigo' href='/sair'>Sair</a>";
        html += "</div>";

        html += "</div>";
        html += HtmlCadastro.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void telaCriar(HttpExchange exchange) throws Exception {

        String html = "";

        html += HtmlCadastro.inicioHtml("Criar Cadastro");
        html += "<div class='card'>";
        html += "<h1>Criar Cadastro</h1>";

        html += "<p class='aviso'>";
        html += "Atenção: este sistema ainda NÃO bloqueia CPF ou e-mail duplicado. ";
        html += "Essa é a parte que os alunos deverão implementar.";
        html += "</p>";

        html += formularioPessoa(null, "/salvar", "Salvar Cadastro");

        html += "</div>";
        html += HtmlCadastro.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void salvarPessoa(HttpExchange exchange) throws Exception {

        Pessoa pessoa = PessoaService.criarPessoaDoFormulario(exchange);

        boolean cpfDuplicado = false;
        boolean emailDuplicado = false;

        for (Pessoa cadastrada : PessoaService.pesquisarEmOrdemAlfabetica("")) {
            if (cadastrada.getCpf().trim().equals(pessoa.getCpf().trim())) {
                cpfDuplicado = true;
            }

            if (cadastrada.getEmail().trim().equalsIgnoreCase(pessoa.getEmail().trim())) {
                emailDuplicado = true;
            }
        }

        if (cpfDuplicado || emailDuplicado) {
            String html = "";

            html += HtmlCadastro.inicioHtml("Erro no Cadastro");
            html += "<div class='card central'>";
            html += "<h1>Cadastro não realizado</h1>";

            if (cpfDuplicado && emailDuplicado) {
                html += "<p>Já existe um cadastro com este CPF e este e-mail.</p>";
            } else if (cpfDuplicado) {
                html += "<p>Já existe um cadastro com este CPF.</p>";
            } else {
                html += "<p>Já existe um cadastro com este e-mail.</p>";
            }

            html += "<div class='menu'>";
            html += "<a class='botao' href='/criar'>Tentar Novamente</a>";
            html += "<a class='botao secundario' href='/pesquisar'>Pesquisar Cadastros</a>";
            html += "</div>";

            html += "</div>";
            html += HtmlCadastro.fimHtml();

            enviarResposta(exchange, html);
            return;
        }

        PessoaService.adicionarPessoa(pessoa);

        redirecionar(exchange, "/pesquisar");
    }

    public static void pesquisar(HttpExchange exchange) throws Exception {

        String termo = "";

        String query = exchange.getRequestURI().getQuery();

        if (query != null && query.startsWith("busca=")) {
            termo = URLDecoder.decode(query.replace("busca=", ""), "UTF-8");
        }

        List<Pessoa> pessoas = PessoaService.pesquisarEmOrdemAlfabetica(termo);

        String html = "";

        html += HtmlCadastro.inicioHtml("Pesquisar Cadastro");

        html += "<div class='card'>";
        html += "<h1>Pesquisar Cadastros</h1>";

        html += "<p class='aviso'>";
        html += "Existem dois cadastros propositalmente iguais. ";
        html += "O aluno deverá corrigir o sistema para impedir duplicidade de CPF ou e-mail.";
        html += "</p>";

        html += "<form method='get' action='/pesquisar' class='busca'>";
        html += "<input type='text' name='busca' placeholder='Buscar por nome, CPF ou e-mail' value='" + termo + "'>";
        html += "<button class='botao' type='submit'>Buscar</button>";
        html += "</form>";

        html += "<table>";
        html += "<tr>";
        html += "<th>Nome</th>";
        html += "<th>CPF</th>";
        html += "<th>E-mail</th>";
        html += "<th>Telefone</th>";
        html += "<th>Ações</th>";
        html += "</tr>";

        for (Pessoa p : pessoas) {
            html += "<tr>";
            html += "<td>" + p.getNome() + "</td>";
            html += "<td>" + p.getCpf() + "</td>";
            html += "<td>" + p.getEmail() + "</td>";
            html += "<td>" + p.getTelefone() + "</td>";
            html += "<td>";
            html += "<a class='mini editar' href='/editar?id=" + p.getId() + "'>Editar</a>";
            html += "<a class='mini excluir' href='/excluir?id=" + p.getId() + "'>Excluir</a>";
            html += "</td>";
            html += "</tr>";
        }

        html += "</table>";

        html += "<div class='menu'>";
        html += "<a class='botao' href='/criar'>Novo Cadastro</a>";
        html += "<a class='botao secundario' href='/'>Menu Principal</a>";
        html += "</div>";

        html += "</div>";
        html += HtmlCadastro.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void telaEditar(HttpExchange exchange) throws Exception {

        int id = obterId(exchange);

        Pessoa pessoa = PessoaService.buscarPorId(id);

        String html = "";

        html += HtmlCadastro.inicioHtml("Editar Cadastro");
        html += "<div class='card'>";
        html += "<h1>Editar Cadastro</h1>";

        if (pessoa == null) {
            html += "<p>Cadastro não encontrado.</p>";
            html += "<a class='botao' href='/pesquisar'>Voltar</a>";
        } else {
            html += formularioPessoa(pessoa, "/atualizar", "Atualizar Cadastro");
        }

        html += "</div>";
        html += HtmlCadastro.fimHtml();

        enviarResposta(exchange, html);
    }

    public static void atualizarPessoa(HttpExchange exchange) throws Exception {

        Pessoa pessoa = PessoaService.criarPessoaDoFormulario(exchange);

        PessoaService.atualizarPessoa(pessoa);

        redirecionar(exchange, "/pesquisar");
    }

    public static void excluirPessoa(HttpExchange exchange) throws Exception {

        int id = obterId(exchange);

        PessoaService.excluirPessoa(id);

        redirecionar(exchange, "/pesquisar");
    }

    public static void sair(HttpExchange exchange) throws Exception {

        String html = "";

        html += HtmlCadastro.inicioHtml("Sistema Encerrado");
        html += "<div class='card central'>";
        html += "<h1>Sistema encerrado</h1>";
        html += "<p>Você pode fechar esta aba do navegador.</p>";
        html += "</div>";
        html += HtmlCadastro.fimHtml();

        enviarResposta(exchange, html);

        servidor.stop(1);
    }

    public static String formularioPessoa(Pessoa p, String action, String textoBotao) {

        String id = p == null ? "0" : String.valueOf(p.getId());
        String nome = p == null ? "" : p.getNome();
        String nascimento = p == null ? "" : p.getNascimento();
        String sexo = p == null ? "" : p.getSexo();
        String cpf = p == null ? "" : p.getCpf();
        String naturalidade = p == null ? "" : p.getNaturalidade();
        String endereco = p == null ? "" : p.getEndereco();
        String numero = p == null ? "" : p.getNumero();
        String complemento = p == null ? "" : p.getComplemento();
        String bairro = p == null ? "" : p.getBairro();
        String cidade = p == null ? "" : p.getCidade();
        String uf = p == null ? "" : p.getUf();
        String telefone = p == null ? "" : p.getTelefone();
        String email = p == null ? "" : p.getEmail();

        String html = "";

        html += "<form method='post' action='" + action + "'>";
        html += "<input type='hidden' name='id' value='" + id + "'>";

        html += "<label>Nome:</label>";
        html += "<input type='text' name='nome' value='" + nome + "' required>";

        html += "<label>Nascimento:</label>";
        html += "<input type='text' name='nascimento' placeholder='dd/mm/aaaa' value='" + nascimento + "' required>";

        html += "<label>Sexo:</label>";
        html += "<select name='sexo' required>";
        html += "<option value=''>Selecione</option>";
        html += "<option value='M' " + (sexo.equals("M") ? "selected" : "") + ">M</option>";
        html += "<option value='F' " + (sexo.equals("F") ? "selected" : "") + ">F</option>";
        html += "</select>";

        html += "<label>CPF:</label>";
        html += "<input type='text' name='cpf' placeholder='XXX.XXX.XXX-XX' value='" + cpf + "' required>";

        html += "<label>Naturalidade:</label>";
        html += "<input type='text' name='naturalidade' value='" + naturalidade + "' required>";

        html += "<label>Endereço:</label>";
        html += "<input type='text' name='endereco' value='" + endereco + "' required>";

        html += "<label>Número:</label>";
        html += "<input type='text' name='numero' value='" + numero + "' required>";

        html += "<label>Complemento:</label>";
        html += "<input type='text' name='complemento' value='" + complemento + "'>";

        html += "<label>Bairro:</label>";
        html += "<input type='text' name='bairro' value='" + bairro + "' required>";

        html += "<label>Cidade:</label>";
        html += "<input type='text' name='cidade' value='" + cidade + "' required>";

        html += "<label>UF:</label>";
        html += "<input type='text' name='uf' maxlength='2' value='" + uf + "' required>";

        html += "<label>Telefone:</label>";
        html += "<input type='text' name='telefone' value='" + telefone + "' required>";

        html += "<label>E-mail:</label>";
        html += "<input type='email' name='email' value='" + email + "' required>";

        html += "<div class='menu'>";
        html += "<button class='botao' type='submit'>" + textoBotao + "</button>";
        html += "<a class='botao secundario' href='/pesquisar'>Pesquisar</a>";
        html += "<a class='botao perigo' href='/'>Cancelar</a>";
        html += "</div>";

        html += "</form>";

        return html;
    }

    public static int obterId(HttpExchange exchange) {

        String query = exchange.getRequestURI().getQuery();

        if (query != null && query.startsWith("id=")) {
            return Integer.parseInt(query.replace("id=", ""));
        }

        return 0;
    }

    public static void redirecionar(HttpExchange exchange, String destino) throws Exception {

        exchange.getResponseHeaders().add("Location", destino);
        exchange.sendResponseHeaders(302, -1);
        exchange.close();
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

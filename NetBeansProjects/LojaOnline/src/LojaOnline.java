import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class LojaOnline {

    static List<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        // Adiciona os doces que serão exibidos na loja.
        produtos.add(new Produto(1, "Brigadeiro", 3.50));
        produtos.add(new Produto(2, "Beijinho", 3.00));
        produtos.add(new Produto(3, "Brownie", 6.50));
        produtos.add(new Produto(4, "Cupcake", 7.00));
        produtos.add(new Produto(5, "Pão de Mel", 5.50));
        produtos.add(new Produto(6, "Trufa de Chocolate", 4.50));

        // Cria o servidor local na porta 8080.
        HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

        // Cria a rota principal da loja.
        servidor.createContext("/", exchange -> {
            try {
                paginaInicial(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Cria a rota usada pelo botão "Adicionar ao Carrinho".
        servidor.createContext("/adicionar", exchange -> {
            try {
                adicionarAoCarrinho(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Inicia o servidor.
        servidor.start();

        // Mostra o endereço no console.
        System.out.println("Loja online rodando em: http://localhost:8080");
    }

    public static void paginaInicial(HttpExchange exchange) throws Exception {

        // Monta o HTML da página.
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='pt-br'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<title>Doceria Online Java</title>");

        // Estilo visual da página.
        html.append("<style>");
        html.append("body { font-family: Arial; background:#fff3f3; margin:0; padding:0; }");
        html.append("header { background:#8B1E3F; color:white; padding:20px; text-align:center; }");
        html.append(".container { width:85%; margin:30px auto; display:grid; grid-template-columns:repeat(3,1fr); gap:20px; }");
        html.append(".produto { background:white; padding:20px; border-radius:12px; box-shadow:0 0 8px #d9a7b0; text-align:center; }");
        html.append(".produto h2 { color:#8B1E3F; }");
        html.append(".preco { color:#2e7d32; font-size:20px; font-weight:bold; }");
        html.append("button { background:#D81B60; color:white; border:none; padding:10px 15px; border-radius:6px; cursor:pointer; }");
        html.append("button:hover { background:#AD1457; }");
        html.append("</style>");

        html.append("</head>");
        html.append("<body>");

        html.append("<header>");
        html.append("<h1>Doceria Online Java</h1>");
        html.append("<p>Exemplo base para atividade de carrinho de compras</p>");
        html.append("</header>");

        html.append("<div class='container'>");

        // Percorre todos os doces cadastrados.
        for (Produto produto : produtos) {

            html.append("<div class='produto'>");

            // Mostra o nome do doce.
            html.append("<h2>").append(produto.getNome()).append("</h2>");

            // Mostra o preço do doce.
            html.append("<p class='preco'>R$ ").append(String.format("%.2f", produto.getPreco())).append("</p>");

            // Botão que envia o id do produto para a rota /adicionar.
            html.append("<a href='/adicionar?id=").append(produto.getId()).append("'>");
            html.append("<button>Adicionar ao Carrinho</button>");
            html.append("</a>");

            html.append("</div>");
        }

        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        enviarResposta(exchange, html.toString());
    }

    public static void adicionarAoCarrinho(HttpExchange exchange) throws Exception {

        // Pega o espaço de armazenamento da rota /adicionar.
        java.util.Map<String, Object> atributos = exchange.getHttpContext().getAttributes();

        // Recupera o carrinho, se ele já existir.
        java.util.Map<Integer, Integer> carrinho =
            (java.util.Map<Integer, Integer>) atributos.get("carrinho");

        // Se ainda não existir carrinho, cria um novo.
        if (carrinho == null) {
            carrinho = new java.util.HashMap<>();
            atributos.put("carrinho", carrinho);
        }

        // Pega os parâmetros da URL.
        String query = exchange.getRequestURI().getQuery();

        String acao = "";
        int id = 0;
        int quantidade = 1;

        if (query != null) {
            String[] parametros = query.split("&");

            for (String parametro : parametros) {
                String[] partes = parametro.split("=");

                if (partes.length == 2) {
                    if (partes[0].equals("acao")) {
                        acao = partes[1];
                    }

                    if (partes[0].equals("id")) {
                        id = Integer.parseInt(partes[1]);
                    }

                    if (partes[0].equals("quantidade")) {
                    quantidade = Integer.parseInt(partes[1]);
                    }
                }
            }
        }

        // Procura o produto pelo ID.
        Produto produtoEncontrado = null;

        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtoEncontrado = produto;
                break;
            }
        }
        
        // Adiciona produto ao carrinho.
        if (acao.equals("") && produtoEncontrado != null) {
            int quantidadeAtual = carrinho.getOrDefault(id, 0);
            carrinho.put(id, quantidadeAtual + 1);
        }

        // Remove produto do carrinho.
        if (acao.equals("remover")) {
            carrinho.remove(id);
        }

        // Altera a quantidade do produto.
        if (acao.equals("alterar")) {
            if (quantidade <= 0) {
                carrinho.remove(id);
            } else {
                carrinho.put(id, quantidade);
            }
        }

        // Finaliza a compra.
        if (acao.equals("finalizar")) {
            carrinho.clear();

            String html = """
                    <!DOCTYPE html>
                    <html lang='pt-br'>
                    <head>
                        <meta charset='UTF-8'>
                        <title>Compra Finalizada</title>
                        <style>
                            body {
                                font-family: Arial;
                                background: #fff3f3;
                                text-align: center;
                                padding-top: 80px;
                            }
                            .caixa {
                                background: white;
                                width: 500px;
                                margin: auto;
                                padding: 30px;
                                border-radius: 12px;
                                box-shadow: 0 0 8px #d9a7b0;
                            }
                            h1 {
                                color: #8B1E3F;
                            }
                            a {
                                display: inline-block;
                                margin-top: 20px;
                                text-decoration: none;
                                background: #D81B60;
                                color: white;
                                padding: 10px 20px;
                                border-radius: 6px;
                            }
                        </style>
                    </head>
                    <body>
                        <div class='caixa'>
                            <h1>Compra finalizada com sucesso!</h1>
                            <p>Obrigado por comprar na Doceria Online Java.</p>
                            <a href='/'>Voltar para a loja</a>
                        </div>
                    </body>
                    </html>
                    """;

            enviarResposta(exchange, html);
            return;
        }

    // Monta a página do carrinho.
    StringBuilder html = new StringBuilder();

    html.append("<!DOCTYPE html>");
    html.append("<html lang='pt-br'>");
    html.append("<head>");
    html.append("<meta charset='UTF-8'>");
    html.append("<title>Carrinho de Compras</title>");

    html.append("<style>");
    html.append("body { font-family: Arial; background:#fff3f3; margin:0; padding:0; text-align:center; }");
    html.append("header { background:#8B1E3F; color:white; padding:20px; }");
    html.append(".caixa { background:white; width:80%; margin:30px auto; padding:25px; border-radius:12px; box-shadow:0 0 8px #d9a7b0; }");
    html.append("table { width:100%; border-collapse:collapse; margin-top:20px; }");
    html.append("th, td { padding:12px; border-bottom:1px solid #ddd; }");
    html.append("th { background:#8B1E3F; color:white; }");
    html.append("input { width:60px; padding:5px; text-align:center; }");
    html.append("button, a.botao { background:#D81B60; color:white; border:none; padding:8px 14px; border-radius:6px; text-decoration:none; cursor:pointer; }");
    html.append("button:hover, a.botao:hover { background:#AD1457; }");
    html.append(".remover { background:#b71c1c; }");
    html.append(".total { font-size:22px; font-weight:bold; color:#2e7d32; margin-top:20px; }");
    html.append("</style>");

    html.append("</head>");
    html.append("<body>");

    html.append("<header>");
    html.append("<h1>Carrinho de Compras</h1>");
    html.append("</header>");

    html.append("<div class='caixa'>");

    if (carrinho.isEmpty()) {

        html.append("<h2>Seu carrinho está vazio.</h2>");
        html.append("<a class='botao' href='/'>Voltar para a loja</a>");

    } else {

        html.append("<table>");
        html.append("<tr>");
        html.append("<th>Produto</th>");
        html.append("<th>Preço</th>");
        html.append("<th>Quantidade</th>");
        html.append("<th>Subtotal</th>");
        html.append("<th>Ações</th>");
        html.append("</tr>");

        double total = 0;

        for (Integer idProduto : carrinho.keySet()) {

            Produto produto = null;

            for (Produto p : produtos) {
                if (p.getId() == idProduto) {
                    produto = p;
                    break;
                }
            }

            if (produto != null) {
                int qtd = carrinho.get(idProduto);
                double subtotal = produto.getPreco() * qtd;
                total += subtotal;

                html.append("<tr>");

                html.append("<td>").append(produto.getNome()).append("</td>");

                html.append("<td>R$ ")
                        .append(String.format("%.2f", produto.getPreco()))
                        .append("</td>");

                html.append("<td>");
                html.append("<form action='/adicionar' method='get'>");
                html.append("<input type='hidden' name='acao' value='alterar'>");
                html.append("<input type='hidden' name='id' value='").append(idProduto).append("'>");
                html.append("<input type='number' name='quantidade' value='").append(qtd).append("' min='1'>");
                html.append("<button type='submit'>Alterar</button>");
                html.append("</form>");
                html.append("</td>");

                html.append("<td>R$ ")
                        .append(String.format("%.2f", subtotal))
                        .append("</td>");

                html.append("<td>");
                html.append("<a class='botao remover' href='/adicionar?acao=remover&id=")
                        .append(idProduto)
                        .append("'>Remover</a>");
                html.append("</td>");

                html.append("</tr>");
            }
        }

        html.append("</table>");

        html.append("<p class='total'>Total: R$ ")
                .append(String.format("%.2f", total))
                .append("</p>");

        html.append("<a class='botao' href='/'>Continuar comprando</a> ");
        html.append("<a class='botao' href='/adicionar?acao=finalizar'>Finalizar compra</a>");
    }

    html.append("</div>");
    html.append("</body>");
    html.append("</html>");

    enviarResposta(exchange, html.toString());
}

    public static void enviarResposta(HttpExchange exchange, String resposta) throws Exception {

        // Converte o HTML em bytes.
        byte[] bytes = resposta.getBytes("UTF-8");

        // Informa ao navegador que a resposta é HTML.
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");

        // Envia o status HTTP 200, indicando sucesso.
        exchange.sendResponseHeaders(200, bytes.length);

        // Abre o canal de saída.
        OutputStream os = exchange.getResponseBody();

        // Escreve o conteúdo HTML no navegador.
        os.write(bytes);

        // Fecha o canal de saída.
        os.close();
    }
}

class Produto {

    // Identificador único do produto.
    private int id;

    // Nome do doce.
    private String nome;

    // Preço do doce.
    private double preco;

    // Construtor usado para criar cada produto.
    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Retorna o ID do produto.
    public int getId() {
        return id;
    }

    // Retorna o nome do produto.
    public String getNome() {
        return nome;
    }

    // Retorna o preço do produto.
    public double getPreco() {
        return preco;
    }
}
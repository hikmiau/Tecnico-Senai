package cadastroweb;

import com.sun.net.httpserver.HttpExchange;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PessoaService {

    private static List<Pessoa> pessoas = new ArrayList<>();

    private static int proximoId = 1;

    public static void criarCadastrosIniciaisDuplicados() {

        pessoas.add(new Pessoa(
                proximoId++,
                "Ana Silva",
                "10/05/2001",
                "F",
                "111.111.111-11",
                "Brusque",
                "Rua das Flores",
                "100",
                "Casa",
                "Centro",
                "Brusque",
                "SC",
                "(47) 99999-0000",
                "ana@email.com"
        ));

        pessoas.add(new Pessoa(
                proximoId++,
                "Carlos Souza",
                "22/09/1998",
                "M",
                "222.222.222-22",
                "Blumenau",
                "Rua XV",
                "55",
                "Apto 202",
                "Velha",
                "Blumenau",
                "SC",
                "(47) 98888-1111",
                "carlos@email.com"
        ));
    }

    public static void adicionarPessoa(Pessoa pessoa) {

        for (Pessoa cadastrada : pessoas) {
            boolean cpfIgual = cadastrada.getCpf().trim().equals(pessoa.getCpf().trim());
            boolean emailIgual = cadastrada.getEmail().trim().equalsIgnoreCase(pessoa.getEmail().trim());

            if (cpfIgual || emailIgual) {
                return;
            }
        }

        pessoa.setId(proximoId++);

        pessoas.add(pessoa);
    }

    public static List<Pessoa> pesquisarEmOrdemAlfabetica(String termo) {

        List<Pessoa> resultado = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {

            boolean encontrouNome = pessoa.getNome().toLowerCase().contains(termo.toLowerCase());
            boolean encontrouCpf = pessoa.getCpf().toLowerCase().contains(termo.toLowerCase());
            boolean encontrouEmail = pessoa.getEmail().toLowerCase().contains(termo.toLowerCase());

            if (termo.isEmpty() || encontrouNome || encontrouCpf || encontrouEmail) {
                resultado.add(pessoa);
            }
        }

        resultado.sort(Comparator.comparing(Pessoa::getNome));

        return resultado;
    }

    public static Pessoa buscarPorId(int id) {

        for (Pessoa pessoa : pessoas) {

            if (pessoa.getId() == id) {
                return pessoa;
            }
        }

        return null;
    }

    public static void atualizarPessoa(Pessoa pessoaAtualizada) {

        for (int i = 0; i < pessoas.size(); i++) {

            if (pessoas.get(i).getId() == pessoaAtualizada.getId()) {

                for (Pessoa cadastrada : pessoas) {
                    boolean mesmoCadastro = cadastrada.getId() == pessoaAtualizada.getId();
                    boolean cpfIgual = cadastrada.getCpf().trim().equals(pessoaAtualizada.getCpf().trim());
                    boolean emailIgual = cadastrada.getEmail().trim().equalsIgnoreCase(pessoaAtualizada.getEmail().trim());

                    if (!mesmoCadastro && (cpfIgual || emailIgual)) {
                        return;
                    }
                }

                pessoas.set(i, pessoaAtualizada);
                return;
            }
        }
    }

    public static void excluirPessoa(int id) {

        pessoas.removeIf(pessoa -> pessoa.getId() == id);
    }

    public static Pessoa criarPessoaDoFormulario(HttpExchange exchange) throws Exception {

        String corpo = lerCorpo(exchange);

        int id = Integer.parseInt(obterValor(corpo, "id"));

        return new Pessoa(
                id,
                obterValor(corpo, "nome"),
                obterValor(corpo, "nascimento"),
                obterValor(corpo, "sexo"),
                obterValor(corpo, "cpf"),
                obterValor(corpo, "naturalidade"),
                obterValor(corpo, "endereco"),
                obterValor(corpo, "numero"),
                obterValor(corpo, "complemento"),
                obterValor(corpo, "bairro"),
                obterValor(corpo, "cidade"),
                obterValor(corpo, "uf"),
                obterValor(corpo, "telefone"),
                obterValor(corpo, "email")
        );
    }

    private static String lerCorpo(HttpExchange exchange) throws Exception {

        InputStream entrada = exchange.getRequestBody();

        byte[] bytes = entrada.readAllBytes();

        return new String(bytes, "UTF-8");
    }

    private static String obterValor(String corpo, String campo) throws Exception {

        String[] partes = corpo.split("&");

        for (String parte : partes) {

            String[] chaveValor = parte.split("=", 2);

            if (chaveValor.length == 2) {

                String chave = URLDecoder.decode(chaveValor[0], "UTF-8");

                String valor = URLDecoder.decode(chaveValor[1], "UTF-8");

                if (chave.equals(campo)) {
                    return valor;
                }
            }
        }

        return "";
    }
}

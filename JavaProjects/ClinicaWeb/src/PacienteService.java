import com.sun.net.httpserver.HttpExchange;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacienteService {

    private static List<Paciente> pacientes = new ArrayList<>();

    public static void adicionarPaciente(Paciente paciente) {

        pacientes.add(paciente);
    }

    public static List<Paciente> listarPacientesAleatorios() {

        java.util.Queue<Paciente> fila = new java.util.LinkedList<>();

        for (Paciente paciente : pacientes) {
            fila.add(paciente);
        }

        List<Paciente> listaFIFO = new ArrayList<>();

        while (!fila.isEmpty()) {
            listaFIFO.add(fila.poll());
        }

        return listaFIFO;
        }

        public static Paciente criarPacienteDoFormulario(HttpExchange exchange) throws Exception {

            String corpo = lerCorpoDaRequisicao(exchange);

            String nome = obterValor(corpo, "nome");
            int idade = Integer.parseInt(obterValor(corpo, "idade"));
            String peso = obterValor(corpo, "peso");
            String altura = obterValor(corpo, "altura");
            String pressao = obterValor(corpo, "pressao");
            String temperatura = obterValor(corpo, "temperatura");
            String observacoes = obterValor(corpo, "observacoes");
            String rua = obterValor(corpo, "rua");
            String numero = obterValor(corpo, "numero");
            String bairro = obterValor(corpo, "bairro");
            String cidade = obterValor(corpo, "cidade");
            String uf = obterValor(corpo, "uf");

            return new Paciente(nome, idade, peso, altura, pressao, temperatura,
                observacoes, rua, numero, bairro, cidade, uf);
        }

    private static String lerCorpoDaRequisicao(HttpExchange exchange) throws Exception {

        InputStream entrada = exchange.getRequestBody();

        byte[] bytes = entrada.readAllBytes();

        return new String(bytes, "UTF-8");
    }

    private static String obterValor(String corpo, String campo) throws Exception {

        String[] partes = corpo.split("&");

        for (String parte : partes) {

            String[] chaveValor = parte.split("=");

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
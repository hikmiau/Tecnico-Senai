package lojacarrosweb;
import java.util.ArrayList;
import java.util.List;
public class CarroService {

    // Lista que armazena os carros disponíveis na loja.
    private static List<Carro> carros = new ArrayList<>();

    // Bloco estático executado uma única vez quando a classe é carregada.
    static {

        // Cadastra um carro do tipo sedan.
        carros.add(new Carro(
                1,
                "Sedan Executivo",
                2022,
                89900.00,
                "Veículo confortável, indicado para uso urbano e viagens familiares.",
                "🚘"
        ));

        // Cadastra um carro do tipo SUV.
        carros.add(new Carro(
                2,
                "SUV Familiar",
                2023,
                139900.00,
                "Modelo espaçoso, ideal para famílias que precisam de conforto e segurança.",
                "🚙"
        ));

        // Cadastra um carro esportivo.
        carros.add(new Carro(
                3,
                "Esportivo Turbo",
                2024,
                229900.00,
                "Carro de alto desempenho, voltado para quem busca potência e design moderno.",
                "🏎️"
        ));

        // Cadastra uma picape.
        carros.add(new Carro(
                4,
                "Picape Robust",
                2021,
                159900.00,
                "Veículo resistente, indicado para trabalho, transporte de carga e uso rural.",
                "🛻"
        ));

        // Cadastra um carro compacto.
        carros.add(new Carro(
                5,
                "Compacto Econômico",
                2020,
                55900.00,
                "Modelo econômico, prático para deslocamentos diários e baixo consumo.",
                "🚗"
        ));
    }

   
    // Retorna todos os carros cadastrados.
    public static List<Carro> listarCarros() {

        return carros;
    }

    // Busca um carro pelo ID.
    public static Carro buscarPorId(int id) {

        // Percorre a lista de carros.
        for (Carro carro : carros) {

            // Verifica se o ID do carro é igual ao ID procurado.
            if (carro.getId() == id) {

                // Retorna o carro encontrado.
                return carro;
            }
        }

        // Retorna null se nenhum carro for encontrado.
        return null;
    }
}
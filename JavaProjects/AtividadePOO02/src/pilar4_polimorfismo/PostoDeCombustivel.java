package pilar4_polimorfismo;

import java.util.ArrayList;

public class PostoDeCombustivel {
    public static boolean executarSimulacao() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(new Carro("Corolla", 50.0));
        veiculos.add(new Moto("CB 500", 17.0));
        veiculos.add(new Caminhao("FH 540", 300.0));

        double precoCombustivel = 6.00;

        for (Veiculo veiculo : veiculos) {
            double custo = veiculo.calcularCustoAbastecimento(precoCombustivel);
            System.out.printf("%s - custo do abastecimento: R$ %.2f%n", veiculo.getModelo(), custo);
        }

        return true;
    }
}

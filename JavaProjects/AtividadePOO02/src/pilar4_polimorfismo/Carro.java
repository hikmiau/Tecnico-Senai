package pilar4_polimorfismo;

public class Carro extends Veiculo {
    public Carro(String modelo, double tanqueCapacidade) {
        super(modelo, tanqueCapacidade);
    }

    @Override
    public double calcularCustoAbastecimento(double precoCombustivel) {
        return tanqueCapacidade * precoCombustivel;
    }
}

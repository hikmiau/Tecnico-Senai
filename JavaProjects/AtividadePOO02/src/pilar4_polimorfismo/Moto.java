package pilar4_polimorfismo;

public class Moto extends Veiculo {
    public Moto(String modelo, double tanqueCapacidade) {
        super(modelo, tanqueCapacidade);
    }

    @Override
    public double calcularCustoAbastecimento(double precoCombustivel) {
        return tanqueCapacidade * precoCombustivel * 0.90;
    }
}

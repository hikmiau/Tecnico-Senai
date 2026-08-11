package pilar4_polimorfismo;

public class Caminhao extends Veiculo {
    private static final double TAXA_AMBIENTAL_DIESEL = 50.0;

    public Caminhao(String modelo, double tanqueCapacidade) {
        super(modelo, tanqueCapacidade);
    }

    @Override
    public double calcularCustoAbastecimento(double precoCombustivel) {
        return (tanqueCapacidade * precoCombustivel) + TAXA_AMBIENTAL_DIESEL;
    }
}

package pilar4_polimorfismo;

public abstract class Veiculo {
    protected String modelo;
    protected double tanqueCapacidade;

    public Veiculo(String modelo, double tanqueCapacidade) {
        this.modelo = modelo;
        this.tanqueCapacidade = tanqueCapacidade;
    }

    public abstract double calcularCustoAbastecimento(double precoCombustivel);

    public String getModelo() {
        return modelo;
    }
}

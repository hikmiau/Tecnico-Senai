package pilar3_heranca;

import java.time.Year;

public class CarroEletrico extends Veiculo {
    private int capacidadeBateria; // em kWh

    public CarroEletrico(String marca, String modelo, Year ano, int capacidadeBateria) {
        super(marca, modelo, ano);
        this.capacidadeBateria = capacidadeBateria;
    }

    public void carregarBateria() {
        System.out.println("Carregando a bateria do carro elétrico...");
    }

    public void exibirStatus() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Capacidade da bateria: " + capacidadeBateria + " kWh");
    }
    
    
}

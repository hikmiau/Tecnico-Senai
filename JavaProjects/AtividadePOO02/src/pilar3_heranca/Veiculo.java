package pilar3_heranca;

import java.time.Year;

public class Veiculo {
    protected String marca;
    protected String modelo;
    protected Year ano;

    public Veiculo(String marca, String modelo, Year ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }
    
    public void ligar(){
        System.out.println("O veículo está ligado.");
    }
}

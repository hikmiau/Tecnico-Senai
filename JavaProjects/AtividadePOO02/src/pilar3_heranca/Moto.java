package pilar3_heranca;

import java.time.Year;

public class Moto extends Veiculo{
    public int cilindradas;
    public Moto(String marca, String modelo, Year ano, int cilindradas) {
        super(marca, modelo, ano);
        this.cilindradas = cilindradas;
    }
    
    public void empinar() {
        System.out.println("A moto está empinando!");
    }
    
}

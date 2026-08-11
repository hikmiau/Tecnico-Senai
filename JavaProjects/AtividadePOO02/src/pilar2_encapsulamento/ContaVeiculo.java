package pilar2_encapsulamento;

import java.util.Scanner;

public class ContaVeiculo {
    
    private String Chassi;
    private String Modelo;
    private double Quilometragem;

    public ContaVeiculo(String chassi, String modelo, double quilometragem) {
        Chassi = chassi;
        Modelo = modelo;
        Quilometragem = quilometragem;
    }

    public String getChassi() {
        return Chassi;
    }

    public String getModelo() {
        return Modelo;
    }

    public double getQuilometragem() {
        return Quilometragem;
    }

    public void setChassi(String chassi) {
        Chassi = chassi;
    }

    public void setQuilometragem(double quilometragem) {
        Quilometragem = quilometragem;
    }
    
    public void registrarViagem(double km){
        
        Scanner s = new Scanner(System.in);

        System.out.println("Digite a quilometragem da viagem: ");
        km = Double.parseDouble(s.nextLine());
        
        if (km <= 0) {
            System.out.println("A quilometragem a registrar deve ser maior que zero.");
        } else {
            Quilometragem += km;
            System.out.println("Viagem registrada com sucesso. Quilometragem atual: " + Quilometragem + " km");
        }
    }
}

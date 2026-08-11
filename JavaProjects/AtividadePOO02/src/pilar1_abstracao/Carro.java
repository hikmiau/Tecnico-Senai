package pilar1_abstracao;

import java.util.Scanner;

public class Carro {
    public String marca;
    public String modelo;
    public int velocidade;
    
    public void criarCarro(){
        Scanner s = new Scanner(System.in);

        System.out.println("Digite a marca: ");
        marca = s.nextLine();

        System.out.println("Digite o modelo: ");
        modelo = s.nextLine();
    }
    
    public void acelerar(int incremento){
        Scanner s = new Scanner(System.in);
        
        while (true) {
            System.out.println("Quanto voce quer acelerar?");
            incremento = Integer.parseInt(s.nextLine());
            
            if (incremento >= 0) {
                break;
            }
            
            System.out.println("Numeros negativos nao sao permitidos. Tente novamente.");
        }

        velocidade += incremento;

        System.out.println("Velocidade: " + velocidade);
    }
    
    public void frear(int decremento){
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("Quanto voce quer desacelerar?");
            decremento = Integer.parseInt(s.nextLine());

            if (decremento >= 0) {
                break;
            }

            System.out.println("Numeros negativos nao sao permitidos. Tente novamente.");
        }

        velocidade -= decremento;

        System.out.println("Velocidade: " + velocidade);
    }
    
    public void exibirStatus(){
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Velocidade: " + velocidade);
    }
}


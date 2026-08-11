import java.util.Scanner;
public class Veiculo {
    private String modelo;
    private String cor;
    private double combustivel;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(double combustivel) {
        this.combustivel = combustivel;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o modelo: ");
        modelo = scanner.nextLine();

        System.out.println("Digite a cor: ");
        cor = scanner.nextLine();
    }
    
    public void Abastecer(){
        Scanner scanner = new Scanner(System.in);
        
        combustivel += Double.parseDouble(scanner.nextLine());
        if (combustivel > 100) {
            combustivel = 100;
        }
    }
}

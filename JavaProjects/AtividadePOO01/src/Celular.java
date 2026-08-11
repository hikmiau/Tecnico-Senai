import java.util.Scanner;
public class Celular {
    private String Marca;
    private String Modelo;
    private int Bateria = 100;

    public void setBateria(int bateria) {
        Bateria = bateria;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getMarca() {
        return Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public int getBateria() {
        return Bateria;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite a marca: ");
        Marca = scanner.nextLine();

        System.out.println("Digite o modelo: ");
        Modelo = scanner.nextLine();
    }
    
    public void Descarregar(){
        Scanner scanner = new Scanner(System.in);
        
        Bateria -= Integer.parseInt(scanner.nextLine());
        
        if (Bateria <= 0) {
            Bateria = 0;
        } else if (Bateria >= 100) {
            Bateria = 100;
        }
    }
    
    public void Carregar(){
        Scanner scanner = new Scanner(System.in);
        
        Bateria += Integer.parseInt(scanner.nextLine());
        if (Bateria >= 100) {
            Bateria = 100;
        } else if (Bateria <= 0) {
            Bateria = 0;
        }
    }
}
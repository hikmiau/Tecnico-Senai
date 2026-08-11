import java.util.Scanner;
public class Residencia {
    private String endereco;
    private int numero;
    private String tipo;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o endereco: ");
        endereco = scanner.nextLine();

        System.out.println("Digite o numero: ");
        numero = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o tipo: ");
        tipo = scanner.nextLine();
    }
    
    public void MostrarDados(){
        System.out.println("\n*** RESIDENCIA***\n");
        System.out.println("Endereco: " + endereco);
        System.out.println("Numero: " + numero);
        System.out.println("Tipo: " + tipo);
    }
}

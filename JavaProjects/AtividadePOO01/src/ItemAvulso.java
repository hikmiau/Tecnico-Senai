import java.util.Scanner;
public class ItemAvulso {
    private String Descricao;
    private String Categoria;
    private double Peso;

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }

    public void setDescricao(String descricao) {
        this.Descricao = descricao;
    }

    public String getDescricao() {
        return Descricao;
    }

    public String getCategoria() {
        return Categoria;
    }

    public double getPeso() {
        return Peso;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a descricao: ");
        Descricao = scanner.nextLine();

        System.out.println("Digite a categoria: ");
        Categoria = scanner.nextLine();

        System.out.println("Digite o peso (kg): ");
        Peso = Double.parseDouble(scanner.nextLine());
    }
    
    public void MostrarDados(){
        System.out.println("\n*** ITEM ***\n");
        System.out.println("Descricao: " + Descricao);
        System.out.println("Categoria: " + Categoria);
        System.out.println("Peso: " + Peso + "KG");
    }
}

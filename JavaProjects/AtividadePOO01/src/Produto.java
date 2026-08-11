import java.util.Scanner;
public class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o nome: ");
        nome = scanner.nextLine();

        System.out.println("Quanto custa? ");
        preco = Double.parseDouble(scanner.nextLine());

        System.out.println("Quantos(as) tem no estoque? ");
        quantidade = Integer.parseInt(scanner.nextLine());
    }
    public void MostrarDados(){
        System.out.println("\n*** PRODUTO ***\n");
        System.out.println("Nome: " + nome);
        System.out.println("Preco: " + preco);
        System.out.println("Quantidade: " + quantidade);
    }
}

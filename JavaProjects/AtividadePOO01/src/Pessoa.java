import java.util.Scanner;
public class Pessoa {
     private String nome;
     private String telefone;
     private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void Cadastrar(){
         Scanner scanner = new Scanner(System.in);
         
         System.out.println("Digite o nome: ");
         nome = scanner.nextLine();

         System.out.println("Digite o telefone: ");
         telefone = scanner.nextLine();

         System.out.println("Digite o endereco: ");
         endereco = scanner.nextLine();
     }
     
     public void MostrarDados(){
         System.out.println("\n*** PESSOA ***\n");
         System.out.println("Nome: " + nome);
         System.out.println("Telefone: " + telefone);
         System.out.println("Endereco: " + endereco);
     }
}

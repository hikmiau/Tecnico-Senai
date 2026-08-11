import java.util.Scanner;

public class Animal {
    private String especie;
    private String nome;
    private int idade;

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void Cadastrar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a especie: ");
        especie = scanner.nextLine();

        System.out.println("Digite o nome: ");
        nome = scanner.nextLine();

        System.out.println("Digite a idade: ");
        idade = Integer.parseInt(scanner.nextLine());
    }
    
    public void MostrarDados(){
        System.out.println("\n*** ANIMAL ***\n");
        System.out.println("Especie: " + especie);
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

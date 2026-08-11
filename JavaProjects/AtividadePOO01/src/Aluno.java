import java.util.Scanner;
public class Aluno {
    private String nome;
    private String matricula;
    private double media;

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getMedia() {
        return media;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o nome: ");
        nome = scanner.nextLine();

        System.out.println("Digite a matricula: ");
        matricula = scanner.nextLine();
    }
    
    public void CalcularMedia(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDigite as notas:\n");
        System.out.println("Nota 1: ");
        double nota1 = Double.parseDouble(scanner.nextLine());
        System.out.println("Nota 2: ");
        double nota2 = Double.parseDouble(scanner.nextLine());
        System.out.println("Nota 3: ");
        double nota3 = Double.parseDouble(scanner.nextLine());

        System.out.println("\nCalculando a media...\n");
        media = ((nota1 + nota2 + nota3) / 3);
        System.out.println("Media: " + media);
    }
}

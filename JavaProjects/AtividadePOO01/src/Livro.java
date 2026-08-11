import java.util.Scanner;
public class Livro {
    private String Titulo;
    private String Autor;
    private int Paginas;

    public void setAutor(String autor) {
        Autor = autor;
    }

    public void setPaginas(int paginas) {
        Paginas = paginas;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public int getPaginas() {
        return Paginas;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o titulo: ");
        Titulo = scanner.nextLine();

        System.out.println("Digite o autor: ");
        Autor = scanner.nextLine();

        System.out.println("Digite a quantia de paginas: ");
        Paginas = Integer.parseInt(scanner.nextLine());
    }
    
    public void MostrarDados(){
        System.out.println("\n*** LIVRO ***\n");
        System.out.println("Titulo: " + Titulo);
        System.out.println("Autor: " + Autor);
        System.out.println("Qtd. Paginas: " + Paginas);
    }
}

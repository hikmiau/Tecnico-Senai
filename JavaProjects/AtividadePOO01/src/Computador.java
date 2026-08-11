import java.util.Scanner;
public class Computador {
    private String processador;
    private String sistema;
    private int memoria;

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public void Cadastrar(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o processador: ");
        processador = scanner.nextLine();

        System.out.println("Digite o sistema: ");
        sistema = scanner.nextLine();

        System.out.println("Digite a quantidade de memoria (em GB): ");
        memoria = Integer.parseInt(scanner.nextLine());
    }
    
    public void MostrarDados(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n*** COMPUTADOR ***\n");
        System.out.println("Processador " + processador);
        System.out.println("Sistema: " + sistema);
        System.out.println("Memoria: " + memoria + "GB");
    }
}

package atividade.pkg1;


// Importa a classe Scanner, que permite ler dados digitados pelo usuário
import java.util.Scanner;

// Declaração da classe principal do programa
public class Atividade1 {
    
    // Método principal: ponto de entrada do programa Java
    public static void main(String[] args) {
        
        // Declara uma variável do tipo String para armazenar o nome
        try ( // Cria um objeto Scanner associado à entrada padrão (teclado)
                Scanner scanner = new Scanner(System.in)) {
            // Declara uma variável do tipo String para armazenar o nome
            String nome;
            // Declara uma variável do tipo float para armazenar o peso
            float peso;
            // Declara uma variável do tipo float para armazenar a altura
            float altura;
            // Declara uma variável do tipo int para armazenar a idade
            int idade;
            // Exibe uma mensagem solicitando o nome do usuário
            System.out.print("Digite o nome: ");
            // Lê uma linha de texto digitada pelo usuário e armazena a variável nome
            nome = scanner.nextLine();
            // Exibe uma mensagem solicitando o peso do usuário
            System.out.print("Digite o peso (em kg): ");
            // Lê um número decimal do tipo float digitado pelo usuário
            peso = scanner.nextFloat();
            // Exibe uma mensagem solicitando a altura do usuário
            System.out.print("Digite a altura (em metros): ");
            // Lê um número decimal do tipo float digitado pelo usuário
            altura = scanner.nextFloat();
            // Exibe uma mensagem solicitando a idade do usuário
            System.out.print("Digite a idade: ");
            // Lê um número inteiro digitado pelo usuário
            idade = scanner.nextInt();
            // Pula uma linha no console para melhorar a organização da saída
            System.out.println();
            // Exibe um título indicando o início da apresentação dos dados
            System.out.println("--- DADOS DO USUÁRIO ---");
            // Exibe o nome informado pelo usuário
            System.out.println("Nome: " + nome);
            // Exibe o peso informado, concatenando texto com a variável peso
            System.out.println("Peso: " + peso + " kg");
            // Exibe a altura informada, concatenando texto com a variável altura
            System.out.println("Altura: " + altura + " m");
            // Exibe a altura informada, concatenando texto com a variável idade
            System.out.println("Idade: " + idade + " anos");
            // Fecha o objeto Scanner para liberar recursos do sistema
        }
    }
}
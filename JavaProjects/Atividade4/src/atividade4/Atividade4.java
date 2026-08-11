package atividade4;

// Importa a classe JOptionPane para criação de janelas gráficas
import javax.swing.JOptionPane;


// Declara a classe principal do programa
public class Atividade4 {
    
    // Método principal: ponto inicial de execução do programa
    public static void main(String[] args) {
        
        // Declaração das variáveis
        String nome;
        float peso;
        float altura;
        int idade;
        float imc;
        String classificacao;
        
        // Estrutura de repetição que permite editar os dados
        while (true) {
            
            // Janela para entrada do nome
            nome = JOptionPane.showInputDialog("Digite seu nome:");
            
            // Verifica se o usuário cancelou
            if (nome == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            // Remove espaços extras
            nome = nome.trim();
            
            // Janela para entrada do peso
            peso = Float.parseFloat(
            JOptionPane.showInputDialog("Digite seu peso(kg):").replace(",", "."));
            
            // Janela para entrada da altura
            altura = Float.parseFloat(
            JOptionPane.showInputDialog("Digite sua altura (m):").replace(",", "."));
            
            // Janela para entrada da idade
            idade = Integer.parseInt(
            JOptionPane.showInputDialog("Digite sua idade:"));
            
            // Calcula o IMC
            imc = peso / (altura * altura);
            
            // Classificação do IMC
            if (imc < 18.5) {
                classificacao = "Abaixo do peso";
            } else if (imc < 25) {
                classificacao = "Peso normal";
            } else if (imc < 30) {
                classificacao = "Sobrepeso";
            } else {
                classificacao = "Obesidade";
            }
            
            // Janela de confirmação com três opções
            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "CONFIRMAR_INFORMACOES:\n\n"
                    + "Nome: " + nome
                    + "\nIdade: " + idade
                    + "\nPeso: " + String.format("%.2f", peso) + " kg"
                    + "\nAltura: " + String.format("%.2f", altura) + " m"
                    + "\n\nDeseja confirmar essas informações?",
                    "Confirmação",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Confirmar", "Editar", "Cancelar"},
                    "Confirmar"
            );
            
            // Se o usuário clicar em Confirmar
            if (opcao == 0) {
                
                // Exibe o resultado final
                JOptionPane.showMessageDialog(
                        null,
                        "RESULTADO_FINAL\n\n"
                        + "Nome: " + nome
                        + "\nIdade: " + idade
                        + "\nIMC: " + String.format("%.2f", imc)
                        + "\nClassificação: " + classificacao,
                        "Resultado do IMC",
                        JOptionPane.INFORMATION_MESSAGE
                );
                
                // Encerra o programa
                System.exit(0);
            }
            
            // Se o usuário clicar em Editar, o loop reinicia
            if (opcao == 1) {
                continue;
            }
            
            // Se o usuário clicar em Cancelar ou fechar a janela
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
    
}

package atividade.pkg3;

// Importa JOptionPane para usar janelas (GUI)
import javax.swing.JOptionPane;

// Classe principal do programa
public class Atividade3 {
    public static void main(String[] args) {
        String nome = lerString("Digite seu nome:");
        
        // Se o usuário cancelou a entrada do nome, encerra o programa
        if (nome == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }

        float peso = lerFloat("Digite seu peso (kg):");
        // Lê a altura (float) com validação (aceita vírgula e ponto)
        float altura = lerFloat("Digite sua altura(m):");
        // Lê a idade (int) com validação
        int idade = lerInt("Digite sua idade:");

        // Calcula o IMC (peso / altura^2)
        float imc = peso / (altura * altura);

        // Variável que receberá a classificação do IMC
        String classificacao;

        // Classifica o IMC por faixas
        if (imc < 18.5f) {
            classificacao = "Abaixo do peso";
        } else if (imc < 25f) {
            classificacao = "Peso normal";
        } else if (imc < 30f) {
            classificacao = "Sobrepeso";
        } else {
            classificacao = "Obesidade";
        }

        // Exibe o resultado final em uma janela
        JOptionPane.showMessageDialog(
                null,
                "Nome: " + nome +
                "\nIdade: " + idade +
                "\nPeso: " + String.format("%.2f", peso) + " kg" +
                "\nAltura: " + String.format("%.2f", altura) + " m" +
                "\nIMC: " + String.format("%.2f", imc) +
                "\nClassificação: " + classificacao,
                "Resultado do IMC",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Encerra o programa
        System.exit(0);
    }

    // Função para ler texto (String) com janela
    private static String lerString(String mensagem) {
        // Abre janela de entrada
        String valor = JOptionPane.showInputDialog(null, mensagem, "Entrada", JOptionPane.QUESTION_MESSAGE);
        // Se usuário fechou a janela, retorna null
        if (valor == null) return null;
        // Remove espaços do começo e do fim
        valor = valor.trim();
        // Enquanto estiver vazio, pede novamente
        while (valor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Este campo não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            valor = JOptionPane.showInputDialog(null, mensagem, "Entrada", JOptionPane.QUESTION_MESSAGE);
            if (valor == null) return null;
            valor = valor.trim();
        }
        // Retorna a String válida
        return valor;
    }

    // Função para ler float com validação e aceitando vírgula
    private static float lerFloat(String mensagem) {
        while (true) {
            // Lê como String
            String valor = JOptionPane.showInputDialog(null, mensagem, "Entrada", JOptionPane.QUESTION_MESSAGE);
            // Se usuário fechou a janela, encerra o programa
            if (valor == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            // Remove espaços
            valor = valor.trim();
            // Troca vírgula por ponto (Brasil -> padrão do parseFloat)
            valor = valor.replace(",", ".");
            try {
                // Converte String para float
                float numero = Float.parseFloat(valor);
                // Valida se é maior que zero
                if (numero <= 0) {
                    JOptionPane.showMessageDialog(null, "Digite um número maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                // Retorna o número válido
                return numero;
            } catch (NumberFormatException e) {
                // Caso digite algo inválido (texto, vazio, etc.)
                JOptionPane.showMessageDialog(null,
                        "Valor inválido.\nExemplos válidos:\n- 72.5\n- 72,5\n- 1.75\n- 1,75",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Função para ler int com validação
    private static int lerInt(String mensagem) {
        while (true) {
            // Lê como String
            String valor = JOptionPane.showInputDialog(null, mensagem, "Entrada", JOptionPane.QUESTION_MESSAGE);
            // Se usuário fechou a janela, encerra
            if (valor == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            // Remove espaços
            valor = valor.trim();
            try {
                // Converte String para int
                int numero = Integer.parseInt(valor);
                if (numero < 0) {
                    JOptionPane.showMessageDialog(null, "A idade não pode ser negativa.", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                // Retorna o número válido
                return numero;
            } catch (NumberFormatException e) {
                // Caso não seja inteiro válido
                JOptionPane.showMessageDialog(null, "Idade inválida. Digite um número inteiro (ex.: 18).", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
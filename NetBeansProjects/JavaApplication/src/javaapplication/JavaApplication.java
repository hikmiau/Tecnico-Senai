package javaapplication;
import javax.swing.JOptionPane;

public class JavaApplication {
    public static void main(String[] args) {
        int idade = lerInt("Digite sua idade:");
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
                if (numero < 123 ) {
                    JOptionPane.showMessageDialog(null, "Essa idade já está sendo utilizada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                if (numero >= 123) {
                    JOptionPane.showMessageDialog(null, "Essa idade não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
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
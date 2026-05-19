// Importa a biblioteca Swing, que contém componentes como janelas e botões
import javax.swing.*; 
// Importa a biblioteca de eventos, necessária para detectar o clique do botão
import java.awt.event.*; 

public class ExemploBotao {
    public static void main(String[] args) {
        
        // Cria um objeto de janela (JFrame) com o título "Minha Janela"
        JFrame janela = new JFrame("Minha Janela");
        
        // Define o tamanho da janela: 300 pixels de largura por 200 de altura
        janela.setSize(300, 200); 
        
        // Garante que o programa pare de rodar quando você fechar a janela no "X"
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Desativa o gerenciador de layout automático para posicionarmos o botão manualmente
        janela.setLayout(null); 

        // Cria o objeto do botão (JButton) com o texto "Pressione" escrito nele
        JButton botao = new JButton("Pressione");
        
        // Define a posição (x=80, y=60) e o tamanho (largura=120, altura=40) do botão
        botao.setBounds(80, 60, 120, 40); 

        // Adiciona um "ouvinte de ação" ao botão para detectar quando ele for clicado
        botao.addActionListener((ActionEvent e) -> {
            // Exibe uma pequena caixa de diálogo (pop-up) com a mensagem desejada
            JOptionPane.showMessageDialog(janela, "Você pressionou o botão!");
        } // Este método é executado automaticamente no momento do clique
        );

        // Coloca o botão dentro da janela que criamos anteriormente
        janela.add(botao);
        
        // Torna a janela visível na tela (por padrão, elas começam invisíveis)
        janela.setVisible(true);
    }
}

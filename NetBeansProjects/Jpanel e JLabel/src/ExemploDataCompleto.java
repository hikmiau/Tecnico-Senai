import javax.swing.*; // Importa componentes de interface (Janelas, Botões, Listas)
import java.awt.*;    // Importa classes de layout e design
import java.awt.event.ActionEvent; // Classe que representa o evento de um clique
import java.awt.event.ActionListener; // Interface para "escutar" o clique do botão

// Definimos a classe principal que estende JFrame (uma janela do sistema)
public class ExemploDataCompleto extends JFrame {

    // Construtor da classe: aqui configuramos tudo que aparece na tela
    public ExemploDataCompleto() {
        
        // --- CONFIGURAÇÕES DA JANELA PRINCIPAL ---
        setTitle("Selecionador de Data Profissional"); // Define o texto da barra de título
        setSize(550, 150); // Define largura (550) e altura (150) em pixels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Finaliza o programa ao fechar a janela
        setLocationRelativeTo(null); // Faz a janela abrir centralizada na tela do monitor

        // --- CRIAÇÃO DO CONTAINER (PAINEL) ---
        // O JPanel organiza os componentes dentro da janela
        JPanel painel = new JPanel(); 
        painel.setLayout(new FlowLayout()); // Organiza os itens em linha (um após o outro)

        // --- CONFIGURAÇÃO DOS COMBOBOX (DIAS) ---
        String[] dias = new String[31]; // Cria um array (lista) de 31 posições
        for (int i = 0; i < 31; i++) {
            // Preenche o array de 01 a 31. O %02d coloca o zero à esquerda se for menor que 10
            dias[i] = String.format("%02d", i + 1); 
        }
        JComboBox<String> cbDia = new JComboBox<>(dias); // Cria a caixa de seleção com os dias

        // --- CONFIGURAÇÃO DOS COMBOBOX (MESES) ---
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        JComboBox<String> cbMes = new JComboBox<>(meses); // Cria a caixa de seleção com os meses

        // --- CONFIGURAÇÃO DOS COMBOBOX (ANOS) ---
        String[] anos = new String[27]; // Array para guardar de 2000 até 2026 (27 anos no total)
        for (int i = 0; i <= 26; i++) {
            anos[i] = String.valueOf(2000 + i); // Converte o número do ano (2000, 2001...) para texto
        }
        JComboBox<String> cbAno = new JComboBox<>(anos); // Cria a caixa de seleção com os anos

        // --- CRIAÇÃO DO BOTÃO E DO CHECKBOX ---
        JButton btnConfirmar = new JButton("Confirmar"); // Cria o botão de ação
        JCheckBox chkResultado = new JCheckBox("Data selecionada"); // Cria o checkbox de exibição

        // --- LÓGICA DO BOTÃO (O QUE ACONTECE AO CLICAR) ---
        btnConfirmar.addActionListener((ActionEvent e) -> {
            // Pega o texto que está selecionado em cada uma das 3 listas
            String d = (String) cbDia.getSelectedItem();
            String m = (String) cbMes.getSelectedItem();
            String a = (String) cbAno.getSelectedItem();
            
            // Une as partes para formar o texto "dd/mm/aaaa"
            String dataFinal = d + "/" + m + "/" + a;
            
            // 1. Atualiza o texto do Checkbox com a data montada
            chkResultado.setText(dataFinal);
            
            // 2. Marca o Checkbox visualmente (coloca o "check")
            chkResultado.setSelected(true);
            
            // 3. Exibe a janela flutuante com a mensagem solicitada
            JOptionPane.showMessageDialog(null, "Essa foi a data escolhida: " + dataFinal);
        });

        // --- ADICIONANDO OS COMPONENTES AO PAINEL ---
        painel.add(new JLabel("Dia:"));     // Adiciona etiqueta "Dia:"
        painel.add(cbDia);                  // Adiciona a lista de dias
        painel.add(new JLabel("Mês:"));    // Adiciona etiqueta "Mês:"
        painel.add(cbMes);                  // Adiciona a lista de meses
        painel.add(new JLabel("Ano:"));     // Adiciona etiqueta "Ano:"
        painel.add(cbAno);                  // Adiciona a lista de anos
        painel.add(btnConfirmar);           // Adiciona o botão
        painel.add(chkResultado);           // Adiciona o checkbox de resultado

        // --- FINALIZAÇÃO ---
        add(painel); // Coloca o painel preenchido dentro da janela principal (JFrame)
    }

    // Método principal: o ponto de partida onde o Java inicia a execução
    public static void main(String[] args) {
        // Inicia a interface gráfica em uma thread (tarefa) segura para o sistema
        SwingUtilities.invokeLater(() -> {
            new ExemploDataCompleto().setVisible(true); // Cria o objeto da janela e a mostra na tela
        });
    }
}

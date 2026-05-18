package atividade5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Atividade5 extends JFrame {
    
    private JTextField txtNome;
    private JTextField txtPeso;
    private JTextField txtAltura;
    private JTextField txtIdade;
    
    private JTextArea txtResultado;
    
    private JButton btnCalcular;
    private JButton btnLimpar;
    private JButton btnSair;
    
    public Atividade5() {
        setTitle("Atividade 5 - Calculo de IMC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);
        
        inicializarComponentes();
        
        setVisible(true);
    }
    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblPeso = new JLabel("Peso (kg):");
        JLabel lblAltura = new JLabel("Altura (m):");
        JLabel lblIdade = new JLabel("Idade");
        
        txtNome = new JTextField(30);
        txtPeso = new JTextField(8);
        txtAltura = new JTextField(8);
        txtIdade = new JTextField(5);
        
        txtResultado = new JTextArea(6, 20);
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        
        btnCalcular = new JButton("Calcular");
        btnLimpar = new JButton("Editar/Limpar");
        btnSair = new JButton("Cancelar/Sair");
        
        // Linha 0: Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        painel.add(lblNome, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(txtNome, gbc);
        
        // Linha 1: Peso (nao expande)
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        painel.add(lblPeso, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        painel.add(txtPeso, gbc);
        
        // Linha 2: Altura (nao expande)
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        painel.add(lblAltura, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        painel.add(txtAltura, gbc);
        
        // Linha 3: Idade (nao expande)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        painel.add(lblIdade, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        painel.add(txtIdade, gbc);
        
        // Linha 4: Resultado (ocupa 2 colunas)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.weightx = 1; gbc.weighty = 1; gbc.fill = GridBagConstraints.BOTH;
        painel.add(scrollResultado, gbc);
        
        // Linha 5: Botoes
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.weightx = 1; gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(painelBotoes, gbc);
        
        setContentPane(painel);
        
        btnCalcular.addActionListener(this::acaoCalcular);
        btnLimpar.addActionListener((ActionEvent e) -> {
            limparCampos();
        });
        btnSair.addActionListener((ActionEvent e) -> {
            sairPrograma();
        });
    }
    
    private void acaoCalcular(ActionEvent e) {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) { mostrarErro("O campo Nome nao pode ficar vazio."); return; }
        
        Float peso = lerFloat(txtPeso.getText(), "Peso");
        if (peso == null) return;
        
        Float altura = lerFloat(txtAltura.getText(), "Altura");
        if (altura == null) return;
        
        Integer idade = lerInt(txtIdade.getText(), "Idade");
        if (idade == null) return;
        
        if (peso <= 0) { mostrarErro("Peso deve ser maior que zero."); return; }
        if (altura <= 0) { mostrarErro("Altura deve ser maior que zero."); return; }
        if (idade < 0) { mostrarErro("Idade nao pode ser negativa."); return; }
        
        float imc = peso / (altura * altura);
        
        String classificacao;
        if (imc < 18.5f) classificacao = "Abaixo do peso";
        else if (imc < 25f) classificacao = "Peso normal";
        else if (imc < 30f) classificacao = "Sobrepreso";
        else classificacao = "Obesidade";
        
        String resultado =
                "DADOS INFORMADOS\n" +
                "Nome: " + nome + "\n" +
                "Idade: " + idade + "\n" +
                "Peso: " + String.format("%.2f", peso) + " kg\n" +
                "Altura: " + String.format("%.2f", altura) + " m\n\n" +
                "RESULTADO\n" +
                "IMC: " + String.format("%.2f", imc) + "\n" +
                "Classificacao: " + classificacao;
        
        txtResultado.setText(resultado);
        
        int opcao = JOptionPane.showOptionDialog(
                this,
                "Deseja confirmar as informacoes?",
                "Confirmacao",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Confirmar", "Editar", "Cancelar"},
                "Confirmar"
        );
        
        switch(opcao) {
            case 0 -> JOptionPane.showMessageDialog(this, "Informacoes confirmadas com sucesso.", "OK", JOptionPane.INFORMATION_MESSAGE);
            case 1 -> txtResultado.append("\n\nModo edicao: ajuste os campos e clique em Calcular novamente.");
            default -> sairPrograma();
        }
    }
    
    private Float lerFloat(String texto, String nomeCampo) {
        try {
            String t = texto.trim().replace(",", ".");
            if (t.isEmpty()) { mostrarErro("O campo " + nomeCampo + " nao pode ficar vazio."); return null; }
            return Float.valueOf(t);
        } catch (NumberFormatException ex) {
            mostrarErro(nomeCampo + " invalido. Exemplos: 72.5 ou 72,5");
            return null;
        }
    }
    
    private Integer lerInt(String texto, String nomeCampo) {
        try {
            String t = texto.trim();
            if (t.isEmpty()) { mostrarErro("O campo " + nomeCampo + "nao pode ficar vazio."); return null; }
            return Integer.valueOf(t);
        } catch (NumberFormatException ex) {
            mostrarErro(nomeCampo + " invalido. Digite um numero inteiro (ex.: 18;");
            return null;
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        txtIdade.setText("");
        txtResultado.setText("");
        txtNome.requestFocus();
    }
    
    private void mostrarErro(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    private void sairPrograma() {
        int confirmar = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) System.exit(0);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Atividade5::new);
    }
}

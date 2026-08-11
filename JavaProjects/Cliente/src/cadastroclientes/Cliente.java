package cadastroclientes;

import javax.swing.JOptionPane;

public class Cliente {
	String cpfCliente;
	String nomeCliente;
	String emailCliente;
	int    idadeCliente;

	public void imprimirCliente2() {
		JOptionPane.showMessageDialog(null,
			 "CPF.....: " + this.cpfCliente  + "\n" +
			 "Nome....: " + this.nomeCliente + "\n" +
			 "Email...: " + this.emailCliente + "\n" +
		         "Idade...: " + this.idadeCliente);
	}
}
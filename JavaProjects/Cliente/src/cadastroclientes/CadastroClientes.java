package cadastroclientes;

import javax.swing.JOptionPane;

public class CadastroClientes {
	public static void main(String[] args) {

		//Scanner sc = new Scanner(System.in);
		Cliente clil = new Cliente();
		//System.out.println("Informe o cpf do cliente");
		//clil.cpfCliente = sc.next();
		//System.out.println("Informe o nome do cliente");
		//clil.nomeCliente = sc.next();
		//System.out.println("Informe o email do cliente");
		//clil.emailCliente = sc.next();

		clil.cpfCliente = JOptionPane.showInputDialog("Informe o Cpf do cliente");
		clil.nomeCliente = JOptionPane.showInputDialog("Informe o nome do cliente");
		clil.emailCliente = JOptionPane.showInputDialog("Informe o email do cliente");
		clil.idadeCliente = Integer.parseInt(JOptionPane.showInputDialog("Informe a idade do cliente"));
		clil.imprimirCliente2();
		
	}

}
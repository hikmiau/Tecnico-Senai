package cadastroproduto;

import javax.swing.JOptionPane;

public class CadastroProduto {
	public static void main(String[] args) {
		// Instanciando a classe Produto
		Produto p1 = new Produto();
		// Entrada de dados
		p1.codProduto   = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do produto"));
		p1.descProduto  = JOptionPane.showInputDialog("Informe a descricao do produto");
		p1.unidMedida   = JOptionPane.showInputDialog("Informe a unidade de medida do produto");
		p1.precoProduto = Float.parseFloat(JOptionPane.showInputDialog("Informe o preco do produto"));
		p1.qtdeEstoque  = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de estoque"));

		// Imprimindo os dados
                JOptionPane.showMessageDialog(null, "Codigo...: " + p1.codProduto   + "\n" +
			                            "Descricao: " + p1.descProduto  + "\n" +
		                                    "Un.Med...: " + p1.unidMedida   + "\n" +
			                            "Preco....: " + p1.precoProduto + "\n" +
			                            "Saldo Est: " + p1.qtdeEstoque );
	}
	
}

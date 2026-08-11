package cadastroproduto;

import javax.swing.JOptionPane;

public class CadastroProduto {
	public static void main(String[] args) {
		double valorCusto = Double.parseDouble(JOptionPane.showInputDialog("Custo"));
		String codProduto = JOptionPane.showInputDialog("Codigo");
		String descProduto = JOptionPane.showInputDialog("Descricao");

		Produto p1 = new Produto(codProduto, descProduto, valorCusto);
	}
}

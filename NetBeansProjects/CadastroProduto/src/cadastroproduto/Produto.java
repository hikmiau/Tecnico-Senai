package cadastroproduto;

public class Produto {
	private String codProduto;
	private String descProduto;
	private double valorCusto;

	public Produto(String descProduto, double valorCusto, String codProduto) {
		this.descProduto = descProduto;
		this.valorCusto = valorCusto;
		this.codProduto = codProduto;
	}
}
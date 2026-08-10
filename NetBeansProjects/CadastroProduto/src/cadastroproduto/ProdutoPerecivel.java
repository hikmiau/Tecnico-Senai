package cadastroproduto;

public class ProdutoPerecivel extends Produto {
    private int diasValidade;
    public ProdutoPerecivel(String codProduto,
                            String descProduto,
                            double valorCusto,
                            int diasValidade){
        super(codProduto, descProduto, valorCusto);
        this.diasValidade = diasValidade;
    }

    public void listaProdutoPerecivel(){
        System.out.println("Dias vencimento: " + this.diasValidade);
    }
}


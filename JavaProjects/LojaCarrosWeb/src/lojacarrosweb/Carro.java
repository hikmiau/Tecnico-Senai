package lojacarrosweb;

public class Carro {

    // Código identificador do carro.
    private int id;

    // Nome comercial do carro.
    private String nome;

    // Ano de fabricação/modelo.
    private int ano;

    // Preço do carro.
    private double preco;

    // Descrição do carro.
    private String descricao;

    // Figura simbólica usada no site.
    private String figura;

    // Construtor da classe Carro.
    public Carro(int id, String nome, int ano, double preco, String descricao, String figura) {

        // Armazena o ID recebido.
        this.id = id;

        // Armazena o nome recebido.
        this.nome = nome;

        // Armazena o ano recebido.
        this.ano = ano;

        // Armazena o preço recebido.
        this.preco = preco;

        // Armazena a descrição recebida.
        this.descricao = descricao;

        // Armazena a figura recebida.
        this.figura = figura;
    }

    // Retorna o ID do carro.
    public int getId() {
        return id;
    }

    // Retorna o nome do carro.
    public String getNome() {
        return nome;
    }

    // Retorna o ano do carro.
    public int getAno() {
        return ano;
    }

    // Retorna o preço do carro.
    public double getPreco() {
        return preco;
    }

    // Retorna a descrição do carro.
    public String getDescricao() {
        return descricao;
    }

    // Retorna a figura simbólica do carro.
    public String getFigura() {
        return figura;
    }
}
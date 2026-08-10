public class Paciente {

    private String nome;
    private int idade;
    private String peso;
    private String altura;
    private String pressao;
    private String temperatura;
    private String observacoes;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;

    public Paciente(String nome, int idade, String peso, String altura, String pressao,
                    String temperatura, String observacoes, String rua, String numero,
                    String bairro, String cidade, String uf) {

        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.pressao = pressao;
        this.temperatura = temperatura;
        this.observacoes = observacoes;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getPeso() {
        return peso;
    }

    public String getAltura() {
        return altura;
    }

    public String getPressao() {
        return pressao;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }
}
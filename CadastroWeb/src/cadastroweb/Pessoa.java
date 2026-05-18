package cadastroweb;

public class Pessoa {

    private int id;
    private String nome;
    private String nascimento;
    private String sexo;
    private String cpf;
    private String naturalidade;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String telefone;
    private String email;

    public Pessoa(int id, String nome, String nascimento, String sexo, String cpf,
                  String naturalidade, String endereco, String numero, String complemento,
                  String bairro, String cidade, String uf, String telefone, String email) {

        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.naturalidade = naturalidade;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
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

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
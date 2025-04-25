package model;

public abstract class Pessoa {
    protected String nome;
    protected String telefone;
    protected String endereco;
    protected String email;

    public Pessoa(String nome, String telefone, String endereco, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
               ", Telefone: " + telefone +
               ", Endereço: " + endereco +
               ", Email: " + email;
    }
}

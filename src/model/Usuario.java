package model;

public class Usuario extends Pessoa {
    private static int contador = 1;
    private int id;

    public Usuario(String nome, String telefone, String endereco, String email) {
        super(nome, telefone, endereco, email);
        this.id = contador++;
    }

    public Usuario(String nome, String email) {
        super(nome, "", "", email);
        this.id = contador++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + super.toString();
    }
}

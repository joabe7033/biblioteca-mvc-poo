package model;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int exemplares;
    private String categoria;

    public Livro(String titulo, String autor, int anoPublicacao, int exemplares, String categoria) {
        this.id = 0; // O ID será gerado automaticamente
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = exemplares;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getExemplares() {
        return exemplares;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Título: " + titulo +
               ", Autor: " + autor +
               ", Ano: " + anoPublicacao +
               ", Exemplares: " + exemplares +
               ", Categoria: " + categoria;
    }
}

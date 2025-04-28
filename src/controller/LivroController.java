package controller;

import repository.ILivroRepository;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroController implements ILivroRepository {

    private List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void cadastrarLivro(Livro livro) {
        livro.setId(proximoId++);
        livros.add(livro);
    }

    @Override
    public Livro buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) return livro;
        }
        return null;
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) resultado.add(livro);
        }
        return resultado;
    }

    @Override
    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) resultado.add(livro);
        }
        return resultado;
    }

    @Override
    public List<Livro> buscarPorCategoria(String categoria) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getCategoria().equalsIgnoreCase(categoria)) resultado.add(livro);
        }
        return resultado;
    }

    public List<Livro> getTodosLivros() {
        return livros;
    }

    public void adicionarLivro(Livro livro) {
        cadastrarLivro(livro);
    }
}

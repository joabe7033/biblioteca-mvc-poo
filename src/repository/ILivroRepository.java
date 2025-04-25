package repository;

import model.Livro;
import java.util.List;

public interface ILivroRepository {
    Livro buscarPorId(int id);
    List<Livro> buscarPorTitulo(String titulo);
    List<Livro> buscarPorAutor(String autor);
    List<Livro> buscarPorCategoria(String categoria);
    void cadastrarLivro(Livro livro);
}

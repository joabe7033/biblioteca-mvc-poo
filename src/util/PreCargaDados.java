package util;

import controller.EmprestimoController;
import controller.LivroController;
import controller.UsuarioController;
import model.Livro;
import model.Usuario;

import java.time.LocalDate;

public abstract class PreCargaDados {

    public static void carregar(UsuarioController usuarioController, LivroController livroController, EmprestimoController emprestimoController) {
        // Pré-cadastrando usuários
        Usuario usuario1 = new Usuario("Eduardo", "eduardo@email.com");
        Usuario usuario2 = new Usuario("Amanda", "amanda@email.com");
        Usuario usuario3 = new Usuario("Carlos", "carlos@email.com");

        usuarioController.adicionarUsuario(usuario1);
        usuarioController.adicionarUsuario(usuario2);
        usuarioController.adicionarUsuario(usuario3);

        // Pré-cadastrando livros
        Livro livro1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 5);
        Livro livro2 = new Livro("1984", "George Orwell", 3);
        Livro livro3 = new Livro("Dom Casmurro", "Machado de Assis", 2);

        livroController.adicionarLivro(livro1);
        livroController.adicionarLivro(livro2);
        livroController.adicionarLivro(livro3);

        // Realizando empréstimos
        emprestimoController.realizarEmprestimo(usuario1, livro1); // Eduardo pega Senhor dos Anéis
        emprestimoController.realizarEmprestimo(usuario2, livro2); // Amanda pega 1984

        // Simulando devolução de um livro
        emprestimoController.devolverLivro(usuario2, livro2); // Amanda devolve 1984

        // Criando um empréstimo com atraso proposital
        emprestimoController.realizarEmprestimo(usuario3, livro3); // Carlos pega Dom Casmurro
        emprestimoController.forcarAtrasoParaTeste(usuario3, livro3, LocalDate.now().minusDays(10));
    }
}

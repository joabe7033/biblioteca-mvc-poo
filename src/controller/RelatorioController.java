package controller;

import model.Emprestimo;
import model.Livro;

import java.util.*;
import java.util.stream.Collectors;

public class RelatorioController {
    private EmprestimoController emprestimoController;

    public RelatorioController(EmprestimoController emprestimoController) {
        this.emprestimoController = emprestimoController;
    }

    public void listarLivrosEmprestados() {
        System.out.println("\n--- Livros Atualmente Emprestados ---");
        for (Emprestimo e : emprestimoController.getEmprestimos()) {
            if (e.isAtivo()) {
                System.out.println("Livro: " + e.getLivro().getTitulo() + " | Usuário: " + e.getUsuario().getNome());
            }
        }
    }

    public void listarUsuariosComAtraso() {
        System.out.println("\n--- Usuários com Devoluções em Atraso ---");
        for (Emprestimo e : emprestimoController.getEmprestimos()) {
            if (!e.isAtivo() && e.getDataDevolucaoEfetiva().isAfter(e.getDataDevolucaoPrevista())) {
                System.out.println("Usuário: " + e.getUsuario().getNome() + " | Livro: " + e.getLivro().getTitulo() +
                                   " | Devolvido em: " + e.getDataDevolucaoEfetiva() +
                                   " | Deveria devolver até: " + e.getDataDevolucaoPrevista());
            }
        }
    }

    public void listarLivrosMaisPopulares() {
        System.out.println("\n--- Livros Mais Populares ---");
        Map<Livro, Long> contagem = emprestimoController.getEmprestimos()
                .stream()
                .collect(Collectors.groupingBy(Emprestimo::getLivro, Collectors.counting()));

        contagem.entrySet().stream()
                .sorted(Map.Entry.<Livro, Long>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println("Livro: " + entry.getKey().getTitulo() + " | Empréstimos: " + entry.getValue()));
    }
}

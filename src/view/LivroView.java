package view;

import controller.LivroController;
import model.Livro;
import java.util.Scanner;

public class LivroView {
    private Scanner scanner = new Scanner(System.in);
    private LivroController controller = new LivroController();

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Pesquisar livro");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> pesquisarLivro();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = lerInteiro();
        System.out.print("Exemplares: ");
        int exemplares = lerInteiro();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, ano, exemplares, categoria);
        controller.cadastrarLivro(livro);
        System.out.println("Livro cadastrado!");
    }

    private void pesquisarLivro() {
        System.out.println("Pesquisar por:");
        System.out.println("1. ID");
        System.out.println("2. Título");
        System.out.println("3. Autor");
        System.out.println("4. Categoria");

        int opcao = lerInteiro();
        scanner.nextLine(); // Limpa buffer

        System.out.print("Digite o termo de busca: ");
        String termo = scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                try {
                    int id = Integer.parseInt(termo);
                    Livro livro = controller.buscarPorId(id);
                    System.out.println(livro != null ? livro : "Livro não encontrado.");
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido.");
                }
            }
            case 2 -> controller.buscarPorTitulo(termo).forEach(System.out::println);
            case 3 -> controller.buscarPorAutor(termo).forEach(System.out::println);
            case 4 -> controller.buscarPorCategoria(termo).forEach(System.out::println);
            default -> System.out.println("Opção inválida.");
        }
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            return -1;
        }
    }
}

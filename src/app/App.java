package app;

import controller.EmprestimoController;
import controller.LivroController;
import controller.UsuarioController;
import model.Livro;
import model.Usuario;
import util.PreCargaDados;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController();
        LivroController livroController = new LivroController();
        EmprestimoController emprestimoController = new EmprestimoController();

        PreCargaDados.carregar(usuarioController, livroController, emprestimoController);

        int opcao;
        do {
            System.out.println("\n=== MENU BIBLIOTECA ===");
            System.out.println("1. Cadastrar novo usuário");
            System.out.println("2. Cadastrar novo livro");
            System.out.println("3. Realizar empréstimo");
            System.out.println("4. Devolver livro");
            System.out.println("5. Listar empréstimos");
            System.out.println("6. Listar atrasos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email do usuário: ");
                    String email = scanner.nextLine();
                    Usuario novoUsuario = new Usuario(nome, email);
                    usuarioController.adicionarUsuario(novoUsuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Quantidade de exemplares: ");
                    int exemplares = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha
                    Livro novoLivro = new Livro(titulo, autor, exemplares);
                    livroController.cadastrarLivro(novoLivro);
                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.print("Nome do usuário para empréstimo: ");
                    String nomeUsuarioEmprestimo = scanner.nextLine();
                    System.out.print("Título do livro para empréstimo: ");
                    String tituloLivroEmprestimo = scanner.nextLine();

                    Usuario usuarioEmprestimo = usuarioController.buscarPorNome(nomeUsuarioEmprestimo);
                    Livro livroEmprestimo = livroController.buscarPorTitulo(tituloLivroEmprestimo).stream().findFirst().orElse(null);

                    if (usuarioEmprestimo != null && livroEmprestimo != null) {
                        emprestimoController.realizarEmprestimo(usuarioEmprestimo, livroEmprestimo);
                    } else {
                        System.out.println("Usuário ou livro não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Nome do usuário para devolução: ");
                    String nomeUsuarioDevolucao = scanner.nextLine();
                    System.out.print("Título do livro para devolução: ");
                    String tituloLivroDevolucao = scanner.nextLine();

                    Usuario usuarioDevolucao = usuarioController.buscarPorNome(nomeUsuarioDevolucao);
                    Livro livroDevolucao = livroController.buscarPorTitulo(tituloLivroDevolucao).stream().findFirst().orElse(null);

                    if (usuarioDevolucao != null && livroDevolucao != null) {
                        emprestimoController.devolverLivro(usuarioDevolucao, livroDevolucao);
                    } else {
                        System.out.println("Usuário ou livro não encontrado.");
                    }
                    break;

                case 5:
                    emprestimoController.listarEmprestimos();
                    break;

                case 6:
                    emprestimoController.listarAtrasos();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}

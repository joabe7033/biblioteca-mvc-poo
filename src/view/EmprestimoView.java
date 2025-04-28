package view;

import controller.EmprestimoController;
import controller.LivroController;
import controller.UsuarioController;
import controller.RelatorioController;
import model.Livro;
import model.Usuario;

import java.util.Scanner;

public class EmprestimoView {
    private Scanner scanner = new Scanner(System.in);
    private EmprestimoController emprestimoController = new EmprestimoController();
    private LivroController livroController = new LivroController();
    private UsuarioController usuarioController = new UsuarioController();
    private RelatorioController relatorioController = new RelatorioController(emprestimoController); 

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n====== MENU EMPRÉSTIMOS ======");
            System.out.println("1. Realizar empréstimo");
            System.out.println("2. Listar empréstimos");
            System.out.println("3. Devolver livro");
            System.out.println("4. Listar empréstimos com atraso");
            System.out.println("5. Gerar relatórios");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> realizarEmprestimo();
                case 2 -> emprestimoController.listarEmprestimos();
                case 3 -> devolverLivro();
                case 4 -> listarAtrasos();
                case 5 -> gerarRelatorios();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void realizarEmprestimo() {
        System.out.print("ID do usuário: ");
        int idUsuario = lerInteiro();
        Usuario usuario = usuarioController.buscarUsuarioPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("ID do livro: ");
        int idLivro = lerInteiro();
        Livro livro = livroController.buscarPorId(idLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        emprestimoController.realizarEmprestimo(usuario, livro);
    }

    private void devolverLivro() {
        System.out.print("ID do usuário: ");
        int idUsuario = lerInteiro();
        Usuario usuario = usuarioController.buscarUsuarioPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("ID do livro: ");
        int idLivro = lerInteiro();
        Livro livro = livroController.buscarPorId(idLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        emprestimoController.devolverLivro(usuario, livro);
    }

    private void listarAtrasos() {
        emprestimoController.listarAtrasos();
    }

    private void gerarRelatorios() {
        int opcao;
        do {
            System.out.println("\n====== RELATÓRIOS ======");
            System.out.println("1. Livros atualmente emprestados");
            System.out.println("2. Usuários com devoluções em atraso");
            System.out.println("3. Livros mais populares");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> relatorioController.listarLivrosEmprestados();
                case 2 -> relatorioController.listarUsuariosComAtraso();
                case 3 -> relatorioController.listarLivrosMaisPopulares();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
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

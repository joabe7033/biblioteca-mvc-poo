package view;

import controller.UsuarioController;
import model.Usuario;

import java.util.Scanner;

public class UsuarioView {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController controller = new UsuarioController();

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n====== MENU USUÁRIO ======");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> controller.listarUsuarios().forEach(System.out::println);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, telefone, endereco, email);
        controller.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado!");
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

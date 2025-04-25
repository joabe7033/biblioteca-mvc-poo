//Joabe Ramos Leal 38956047
//Eduardo Jose dos Santos Severnini 8838568004

import view.LivroView;
import view.UsuarioView;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivroView livroView = new LivroView();
        UsuarioView usuarioView = new UsuarioView();

        int opcao;
        do {
            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1. Menu Livros");
            System.out.println("2. Menu Usuários");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> livroView.exibirMenu();
                case 2 -> usuarioView.exibirMenu();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}

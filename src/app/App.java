//Joabe Ramos Leal 38956047
//Eduardo Jose dos Santos Severnini 8838568004
 package app;

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
             System.out.println("1. Gerenciar Livros");
             System.out.println("2. Gerenciar Usuários");
             System.out.println("0. Sair");
             System.out.print("Opção: ");
             opcao = lerInteiroValido(scanner);
 
             switch (opcao) {
                 case 1 -> livroView.exibirMenu();
                 case 2 -> usuarioView.exibirMenu();
                 case 0 -> System.out.println("Encerrando o sistema...");
                 default -> System.out.println("Opção inválida. Tente novamente.");
             }
 
         } while (opcao != 0);
 
         scanner.close();
     }
 
     // Método que lê um número inteiro válido do usuário
     private static int lerInteiroValido(Scanner scanner) {
         while (true) {
             try {
                 int valor = Integer.parseInt(scanner.nextLine());
                 if (valor >= 0) {  // Aqui validamos se é um número válido e positivo
                     return valor;
                 } else {
                     System.out.println("⚠ O número deve ser positivo.");
                 }
             } catch (NumberFormatException e) {
                 System.out.println("⚠ Digite um número inteiro válido.");
             }
         }
     }
 }
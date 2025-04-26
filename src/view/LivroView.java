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
            opcao = lerInteiroValido("Opção: ");

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> pesquisarLivro();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        if (titulo.isBlank()) {
            System.out.println("Título não pode estar vazio.");
            return;
        }
    
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        if (autor.isBlank()) {
            System.out.println("Autor não pode estar vazio.");
            return;
        }
    
        int ano = lerInteiroValido("Ano: ");
        int exemplares = lerInteiroValido("Exemplares: ");
    
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        if (categoria.isBlank()) {
            System.out.println("Categoria não pode estar vazia.");
            return;
        }
    
        Livro livro = new Livro(titulo, autor, ano, exemplares, categoria);
        controller.cadastrarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }
    

    private void pesquisarLivro() {
        System.out.println("\nPesquisar por:");
        System.out.println("1. ID");
        System.out.println("2. Título");
        System.out.println("3. Autor");
        System.out.println("4. Categoria");
    
        int opcao = lerInteiroValido("Escolha uma opção: ");
        if (opcao < 1 || opcao > 4) {
            System.out.println("Opção inválida. Escolha entre 1 e 4.");
            return;
        }
    
        System.out.print("Digite o termo de busca: ");
        String termo = scanner.nextLine();
    
        switch (opcao) {
            case 1 -> {
                try {
                    int id = Integer.parseInt(termo);
                    Livro livro = controller.buscarPorId(id);
                    if (livro != null) {
                        System.out.println(livro);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido. Deve ser um número inteiro.");
                }
            }
            case 2 -> {
                var resultados = controller.buscarPorTitulo(termo);
                if (resultados.isEmpty()) {
                    System.out.println("Nenhum livro encontrado com esse título.");
                } else {
                    resultados.forEach(System.out::println);
                }
            }
            case 3 -> {
                var resultados = controller.buscarPorAutor(termo);
                if (resultados.isEmpty()) {
                    System.out.println("Nenhum livro encontrado com esse autor.");
                } else {
                    resultados.forEach(System.out::println);
                }
            }
            case 4 -> {
                var resultados = controller.buscarPorCategoria(termo);
                if (resultados.isEmpty()) {
                    System.out.println("Nenhum livro encontrado com essa categoria.");
                } else {
                    resultados.forEach(System.out::println);
                }
            }
        }
    }
    

    private int lerInteiroValido(String mensagem) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                if (valor >= 0) return valor;
                System.out.println("O número deve ser positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
}

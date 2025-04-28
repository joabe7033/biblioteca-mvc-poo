package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

public class EmprestimoController {
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Emprestimo> emprestimosComAtraso = new ArrayList<>();

    public boolean realizarEmprestimo(Usuario usuario, Livro livro) {
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(usuario) && e.isAtivo()) {
                System.out.println("Usuário já possui um empréstimo ativo!");
                return false;
            }
        }

        if (livro.getExemplaresDisponiveis() <= 0) {
            System.out.println("Livro indisponível para empréstimo!");
            return false;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate devolucaoPrevista = hoje.plusDays(7);

        Emprestimo novoEmprestimo = new Emprestimo(usuario, livro, hoje, devolucaoPrevista);
        emprestimos.add(novoEmprestimo);

        livro.setExemplaresDisponiveis(livro.getExemplaresDisponiveis() - 1);

        System.out.println("Empréstimo realizado com sucesso!");
        return true;
    }

    public void devolverLivro(Usuario usuario, Livro livro) {
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(usuario) && e.getLivro().equals(livro) && e.isAtivo()) {
                e.devolverLivro();
                livro.setExemplaresDisponiveis(livro.getExemplaresDisponiveis() + 1);

                long diasAtraso = e.calcularDiasAtraso();
                if (diasAtraso > 0) {
                    emprestimosComAtraso.add(e);
                    System.out.println("Atraso de " + diasAtraso + " dias.");
                }

                long diasEmprestado = e.calcularDiasEmprestado();
                System.out.println("Livro devolvido com sucesso!");
                System.out.println("Número de dias que o livro ficou emprestado: " + diasEmprestado);
                return;
            }
        }
        System.out.println("Empréstimo não encontrado ou já devolvido.");
    }

    public void listarAtrasos() {
        if (emprestimosComAtraso.isEmpty()) {
            System.out.println("Não há empréstimos com atraso.");
            return;
        }

        System.out.println("Empréstimos com Atraso:");
        for (Emprestimo e : emprestimosComAtraso) {
            System.out.println(e);
        }
    }

    public void listarEmprestimos() {
        for (Emprestimo e : emprestimos) {
            System.out.println(e);
        }
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Emprestimo> getEmprestimosComAtraso() {
        return emprestimosComAtraso;
    }

    // Método para o PreCargaDados adicionar empréstimos direto
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    // ✅ Método novo para forçar atraso em teste
    public void forcarAtrasoParaTeste(Usuario usuario, Livro livro, LocalDate novaDataPrevistaDevolucao) {
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(usuario) && e.getLivro().equals(livro) && e.isAtivo()) {
                e.setDataDevolucaoPrevista(novaDataPrevistaDevolucao);  // ⚡ Correção aqui
                System.out.println("Data de devolução forçada para: " + novaDataPrevistaDevolucao);
                return;
            }
        }
        System.out.println("Empréstimo não encontrado para forçar atraso.");
    }
}

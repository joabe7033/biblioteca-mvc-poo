package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoEfetiva;
    private boolean ativo;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.ativo = true;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void devolverLivro() {
        this.ativo = false;
        this.dataDevolucaoEfetiva = LocalDate.now();
    }

    public long calcularDiasEmprestado() {
        if (dataDevolucaoEfetiva != null) {
            return ChronoUnit.DAYS.between(dataEmprestimo, dataDevolucaoEfetiva);
        } else {
            return ChronoUnit.DAYS.between(dataEmprestimo, LocalDate.now());
        }
    }

    public long calcularDiasAtraso() {
        if (dataDevolucaoEfetiva != null && dataDevolucaoEfetiva.isAfter(dataDevolucaoPrevista)) {
            return ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoEfetiva);
        }
        return 0;
    }

    public void setDataDevolucaoPrevista(LocalDate novaDataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = novaDataDevolucaoPrevista;
    }

    @Override
    public String toString() {
        return "Empréstimo [Usuário=" + usuario.getNome() +
               ", Livro=" + livro.getTitulo() +
               ", Data de Empréstimo=" + dataEmprestimo +
               ", Data de Devolução Prevista=" + dataDevolucaoPrevista +
               ", Data de Devolução Efetiva=" + (dataDevolucaoEfetiva != null ? dataDevolucaoEfetiva : "Ainda não devolvido") +
               ", Ativo=" + ativo + ", Atraso=" + calcularDiasAtraso() + " dias]";
    }
}

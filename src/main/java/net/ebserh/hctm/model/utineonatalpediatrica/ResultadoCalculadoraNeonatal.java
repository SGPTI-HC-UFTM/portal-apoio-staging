package net.ebserh.hctm.model.utineonatalpediatrica;

import java.time.LocalDate;
import java.util.List;

public class ResultadoCalculadoraNeonatal {
    private String nomeRecemNascido;
    private String nomeMae;
    private LocalDate dataNascimentoRecemNascido;
    private CategoriaClinica categoriaClinica;
    private List<Amostra> amostras;

    public ResultadoCalculadoraNeonatal() {
    }

    public ResultadoCalculadoraNeonatal(String nomeRecemNascido, String nomeMae, LocalDate dataNascimentoRecemNascido,
            CategoriaClinica categoriaClinica, List<Amostra> amostras) {
        this.nomeRecemNascido = nomeRecemNascido;
        this.nomeMae = nomeMae;
        this.dataNascimentoRecemNascido = dataNascimentoRecemNascido;
        this.categoriaClinica = categoriaClinica;
        this.amostras = amostras;
    }

    public String getNomeRecemNascido() {
        return nomeRecemNascido;
    }

    public void setNomeRecemNascido(String nomeRecemNascido) {
        this.nomeRecemNascido = nomeRecemNascido;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public LocalDate getDataNascimentoRecemNascido() {
        return dataNascimentoRecemNascido;
    }

    public void setDataNascimentoRecemNascido(LocalDate dataNascimentoRecemNascido) {
        this.dataNascimentoRecemNascido = dataNascimentoRecemNascido;
    }

    public CategoriaClinica getCategoriaClinica() {
        return categoriaClinica;
    }

    public void setCategoriaClinica(CategoriaClinica categoriaClinica) {
        this.categoriaClinica = categoriaClinica;
    }

    public List<Amostra> getAmostras() {
        return amostras;
    }

    public void setAmostras(List<Amostra> amostras) {
        this.amostras = amostras;
    }

}

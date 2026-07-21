package net.ebserh.hctm.model.utineonatalpediatrica;

import java.io.Serializable;
import java.time.LocalDate;

public class CalculoTestePezinho implements Serializable {

    private String nomeRecemNascido;
    private String nomeMae;
    private LocalDate dtNascimento;
    private ClassificacaoEstabilidade estabilidadeRecemNascido;
    private ClassificacaoPeso pesoNascimento;
    private ClassificacaoIdadeGestacional idadeGestacional;

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

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public ClassificacaoEstabilidade getEstabilidadeRecemNascido() {
        return estabilidadeRecemNascido;
    }

    public void setEstabilidadeRecemNascido(ClassificacaoEstabilidade estabilidadeRecemNascido) {
        this.estabilidadeRecemNascido = estabilidadeRecemNascido;
    }

    public ClassificacaoPeso getPesoNascimento() {
        return pesoNascimento;
    }

    public void setPesoNascimento(ClassificacaoPeso pesoNascimento) {
        this.pesoNascimento = pesoNascimento;
    }

    public ClassificacaoIdadeGestacional getIdadeGestacional() {
        return idadeGestacional;
    }

    public void setIdadeGestacional(ClassificacaoIdadeGestacional idadeGestacional) {
        this.idadeGestacional = idadeGestacional;
    }

}

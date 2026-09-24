package net.ebserh.hctm.model.utineonatalpediatrica;

import java.time.LocalDate;
import java.util.List;

public class CalculadoraNeonatal {
    private String nomeRecemNascido;
    private String nomeMae;
    private LocalDate dataNascimentoRecemNascido;
    private boolean estabilidadeRecemNascido;
    private boolean pesoAoNascer;
    private boolean idadeGestacional;
    private boolean hemotransfusaoAntesPrimeiraColeta;
    private boolean hemotransfusaoAposPrimeiraColeta;
    private LocalDate dataUltimaHemotransfusao;
    private List<LocalDate> coletasRealizadas;

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

    public boolean isEstabilidadeRecemNascido() {
        return estabilidadeRecemNascido;
    }

    public void setEstabilidadeRecemNascido(boolean estabilidadeRecemNascido) {
        this.estabilidadeRecemNascido = estabilidadeRecemNascido;
    }

    public boolean isPesoAoNascer() {
        return pesoAoNascer;
    }

    public void setPesoAoNascer(boolean pesoAoNascer) {
        this.pesoAoNascer = pesoAoNascer;
    }

    public boolean isIdadeGestacional() {
        return idadeGestacional;
    }

    public void setIdadeGestacional(boolean idadeGestacional) {
        this.idadeGestacional = idadeGestacional;
    }

    public boolean isHemotransfusaoAntesPrimeiraColeta() {
        return hemotransfusaoAntesPrimeiraColeta;
    }

    public void setHemotransfusaoAntesPrimeiraColeta(boolean hemotransfusaoAntesPrimeiraColeta) {
        this.hemotransfusaoAntesPrimeiraColeta = hemotransfusaoAntesPrimeiraColeta;
    }

    public boolean isHemotransfusaoAposPrimeiraColeta() {
        return hemotransfusaoAposPrimeiraColeta;
    }

    public void setHemotransfusaoAposPrimeiraColeta(boolean hemotransfusaoAposPrimeiraColeta) {
        this.hemotransfusaoAposPrimeiraColeta = hemotransfusaoAposPrimeiraColeta;
    }

    public LocalDate getDataUltimaHemotransfusao() {
        return dataUltimaHemotransfusao;
    }

    public void setDataUltimaHemotransfusao(LocalDate dataUltimaHemotransfusao) {
        this.dataUltimaHemotransfusao = dataUltimaHemotransfusao;
    }

    public List<LocalDate> getColetasRealizadas() {
        return coletasRealizadas;
    }

    public void setColetasRealizadas(List<LocalDate> coletasRealizadas) {
        this.coletasRealizadas = coletasRealizadas;
    }

}
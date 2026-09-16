package net.ebserh.hctm.model.utineonatalpediatrica;

import java.time.LocalDate;

public class Amostra {
    private Integer numero;
    private LocalDate inicio;
    private LocalDate fim;
    private LocalDate dataRealizada;
    private String status;

    public Amostra(){}

    public Amostra(Integer numero, LocalDate inicio, LocalDate fim, LocalDate dataRealizada,
            String status) {
        this.numero = numero;
        this.inicio = inicio;
        this.fim = fim;
        this.dataRealizada = dataRealizada;
        this.status = status;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public LocalDate getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(LocalDate dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

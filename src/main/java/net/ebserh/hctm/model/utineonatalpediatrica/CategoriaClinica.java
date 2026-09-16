package net.ebserh.hctm.model.utineonatalpediatrica;

public class CategoriaClinica {
    private Integer numero;
    private String criterio;
    private int quantidadeAmostras;

    public CategoriaClinica(Integer numero, String criterio, int quantidadeAmostras) {
        this.numero = numero;
        this.criterio = criterio;
        this.quantidadeAmostras = quantidadeAmostras;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public int getQuantidadeAmostras() {
        return quantidadeAmostras;
    }

    public void setQuantidadeAmostras(int quantidadeAmostras) {
        this.quantidadeAmostras = quantidadeAmostras;
    }

}

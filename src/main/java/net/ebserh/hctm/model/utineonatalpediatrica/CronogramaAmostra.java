package net.ebserh.hctm.model.utineonatalpediatrica;

public enum CronogramaAmostra {

    PRIMEIRA(1, 2, 4), 
    SEGUNDA(2, 9, 9), 
    TERCEIRA(3, 29, 29), 
    QUARTA(4, 180, 180);

    private final int numero;
    private final int diasParaInicio;
    private final int diasParaFim;

    CronogramaAmostra(int numero, int diasParaInicio, int diasParaFim) {
        this.numero = numero;
        this.diasParaInicio = diasParaInicio;
        this.diasParaFim = diasParaFim;
    }

    public int getNumero() {
        return numero;
    }

    public int getDiasParaInicio() {
        return diasParaInicio;
    }

    public int getDiasParaFim() {
        return diasParaFim;
    }

    public static CronogramaAmostra porNumero(int numero) {
        for (CronogramaAmostra c : values()) {
            if (c.getNumero() == numero)
                return c;
        }
        throw new IllegalArgumentException("Amostra inválida: " + numero);
    }
}

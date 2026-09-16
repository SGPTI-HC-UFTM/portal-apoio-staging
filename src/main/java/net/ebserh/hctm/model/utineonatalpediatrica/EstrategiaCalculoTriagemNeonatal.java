package net.ebserh.hctm.model.utineonatalpediatrica;

public interface EstrategiaCalculoTriagemNeonatal {
    public boolean transfundido(CalculadoraNeonatal dados);
    public ResultadoCalculadoraNeonatal calcular(CalculadoraNeonatal dados);    
}
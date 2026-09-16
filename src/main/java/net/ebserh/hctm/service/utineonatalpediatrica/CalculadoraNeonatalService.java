package net.ebserh.hctm.service.utineonatalpediatrica;


import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.utineonatalpediatrica.CalculadoraNeonatal;
import net.ebserh.hctm.model.utineonatalpediatrica.EstrategiaCalculoTriagemNeonatal;
import net.ebserh.hctm.model.utineonatalpediatrica.ResultadoCalculadoraNeonatal;

@Stateless
public class CalculadoraNeonatalService {
    @Inject
    private Instance<EstrategiaCalculoTriagemNeonatal> estrategias;

    public ResultadoCalculadoraNeonatal calcular(CalculadoraNeonatal dados) {
        return estrategias.stream()
        .filter( e -> e.transfundido(dados))
        .findFirst()
        .orElseThrow(()-> new CustomRuntimeException("Nenhuma estratégia de cálculo encontrada"))
        .calcular(dados);
    }
}

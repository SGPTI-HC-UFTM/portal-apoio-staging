package net.ebserh.hctm.model.utineonatalpediatrica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import net.ebserh.hctm.service.utineonatalpediatrica.ClassificadorClinicoService;

public class CalculoProtocoloHemotransfusao implements EstrategiaCalculoTriagemNeonatal {

    @Inject
    private ClassificadorClinicoService classificadorClinicoService;

    @Override
    public boolean transfundido(CalculadoraNeonatal dados) {
        return dados.isHemotransfusaoAntesPrimeiraColeta() || dados.isHemotransfusaoAposPrimeiraColeta();
    }

    @Override
    public ResultadoCalculadoraNeonatal calcular(CalculadoraNeonatal dados) {
        CategoriaClinica categoriaClinica = classificadorClinicoService.avaliar(dados);

        int quantidadeAmostras = categoriaClinica.getQuantidadeAmostras();
        LocalDate dataReferencia = dados.getDataNascimentoRecemNascido();
        List<Amostra> amostras = new ArrayList<>();

        for (int i = 1; i <= quantidadeAmostras; i++) {
            CronogramaAmostra regra = CronogramaAmostra.porNumero(i);
            LocalDate dataRealizada = dados.getColetasRealizadas().get(i - 1);
            Amostra amostra = new Amostra();
            amostra.setNumero(i);

            LocalDate inicioCalculado;
            LocalDate fimCalculado;

            if (dataRealizada != null) {
                inicioCalculado = dataReferencia.plusDays(regra.getDiasParaInicio());
                fimCalculado = dataReferencia.plusDays(regra.getDiasParaFim());

                amostra.setDataRealizada(dataRealizada);
                amostra.setStatus("Realizada");
                amostra.setInicio(inicioCalculado);
                amostra.setFim(fimCalculado);
            } else {

                LocalDate novaData = LocalDate.now();
                if (amostra.getNumero() == 2 || amostra.getNumero() == 3) {
                    novaData = dados.getDataUltimaHemotransfusao().plusDays(11);
                }

                if (amostra.getNumero() == 4) {
                    novaData = dados.getDataUltimaHemotransfusao().plusDays(91);
                }

                inicioCalculado = dataReferencia.plusDays(regra.getDiasParaInicio());
                fimCalculado = dataReferencia.plusDays(regra.getDiasParaFim());

                if (fimCalculado.isBefore(novaData)) {
                    inicioCalculado = novaData;
                    fimCalculado = novaData;
                }

                LocalDate dataHoje = LocalDate.now();
                if (dataRealizada == null && fimCalculado.equals(dataHoje) || fimCalculado.isAfter(dataHoje)) {
                    amostra.setStatus("Pendente");
                }
                if (dataRealizada == null && fimCalculado.isBefore(dataHoje)) {
                    amostra.setStatus("Atrasada");
                }
                amostra.setInicio(inicioCalculado);
                amostra.setFim(fimCalculado);
                amostra.setDataRealizada(null);
            }

            amostras.add(amostra);
        }

        return new ResultadoCalculadoraNeonatal(
                dados.getNomeRecemNascido(), dados.getNomeMae(), dados.getDataNascimentoRecemNascido(),
                categoriaClinica, amostras);
    }

}

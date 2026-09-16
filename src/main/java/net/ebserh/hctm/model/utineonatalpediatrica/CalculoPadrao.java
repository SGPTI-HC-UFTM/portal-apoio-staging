package net.ebserh.hctm.model.utineonatalpediatrica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import net.ebserh.hctm.service.utineonatalpediatrica.ClassificadorClinicoService;

public class CalculoPadrao implements EstrategiaCalculoTriagemNeonatal {

        @Inject
        private ClassificadorClinicoService classificadorClinicoService;

        @Override
        public boolean transfundido(CalculadoraNeonatal dados) {
                return !dados.isHemotransfusaoAntesPrimeiraColeta() && !dados.isHemotransfusaoAposPrimeiraColeta();
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

                        if (dataRealizada != null) {
                                LocalDate inicioCalculado = dataReferencia.plusDays(regra.getDiasParaInicio());
                                LocalDate fimCalculado = dataReferencia.plusDays(regra.getDiasParaFim());

                                amostra.setDataRealizada(dataRealizada);
                                amostra.setStatus("Realizado");
                                amostra.setInicio(inicioCalculado);
                                amostra.setFim(fimCalculado);
                        } else {

                                LocalDate inicioCalculado = dataReferencia.plusDays(regra.getDiasParaInicio());
                                LocalDate fimCalculado = dataReferencia.plusDays(regra.getDiasParaFim());

                                amostra.setInicio(inicioCalculado);
                                amostra.setFim(fimCalculado);
                                amostra.setDataRealizada(null);
                                amostra.setStatus("Pendente");

                        }
                        amostras.add(amostra);
                }

                return new ResultadoCalculadoraNeonatal(
                                dados.getNomeRecemNascido(), dados.getNomeMae(), dados.getDataNascimentoRecemNascido(),
                                categoriaClinica, amostras, "");
        }
}

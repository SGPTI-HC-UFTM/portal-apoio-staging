package net.ebserh.hctm.service.aghu.prescricoes;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.indicadores.PrescricoesEletronicasManuaisDto;
import net.ebserh.hctm.model.aghu.UnidadeFuncional;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class PrescricoesEletronicasService {

    private static final Logger logger = Logger.getAnonymousLogger();
    
    @PersistenceContext
    private EntityManager entityManager;

    private static final String QUERY_PRESCRICOES_ELETRONICAS_MANUAIS = "SELECT sigla, "
        + " SUM(prescricao_nao_eletronica) AS manuais, "
        + " SUM(prescricao_eletronica) AS eletronicas "
        + "FROM ( "
        + " SELECT DISTINCT "
        + "  COALESCE(unf1.sigla, unf2.sigla, unf3.sigla) AS sigla, "
        + "  i.seq, "
        + "  CASE WHEN mpm.seq IS NULL THEN 1 ELSE 0 END AS prescricao_nao_eletronica, "
        + "  CASE WHEN mpm.seq IS NOT NULL THEN 1 ELSE 0 END AS prescricao_eletronica "
        + " FROM agh.ain_internacoes i "
        + "  JOIN agh.agh_atendimentos atd ON atd.int_seq = i.seq "
        + "  LEFT JOIN agh.agh_unidades_funcionais unf1 ON i.unf_seq = unf1.seq "
        + "  LEFT JOIN agh.ain_leitos l ON i.lto_lto_id = l.lto_id "
        + "  LEFT JOIN agh.agh_unidades_funcionais unf2 ON l.unf_seq = unf2.seq "
        + "  LEFT JOIN agh.ain_quartos q ON i.qrt_numero = q.numero "
        + "  LEFT JOIN agh.agh_unidades_funcionais unf3 ON q.unf_seq = unf3.seq "
        + "  LEFT JOIN agh.mpm_prescricao_medicas mpm ON mpm.atd_seq = atd.seq and :dataReferencia = DATE(mpm.dthr_inicio) "
        + " WHERE :dataReferencia BETWEEN DATE(i.dthr_internacao) AND DATE(i.dthr_alta_medica) "
        + ") AS tmp "
        + "GROUP BY sigla";

    private static final String QUERY_PRESCRICOES_ELETRONICAS_MANUAIS_UNIDADE = "SELECT tmp.sigla, "
        + " SUM(prescricao_nao_eletronica) AS manuais, "
        + " SUM(prescricao_eletronica) AS eletronicas "
        + "FROM ( "
        + " SELECT DISTINCT "
        + "  COALESCE(unf1.sigla, unf2.sigla, unf3.sigla) AS sigla, "
        + "  i.seq, "
        + "  CASE WHEN mpm.seq IS NULL THEN 1 ELSE 0 END AS prescricao_nao_eletronica, "
        + "  CASE WHEN mpm.seq IS NOT NULL THEN 1 ELSE 0 END AS prescricao_eletronica "
        + " FROM agh.ain_internacoes i "
        + "  JOIN agh.agh_atendimentos atd ON atd.int_seq = i.seq "
        + "  LEFT JOIN agh.agh_unidades_funcionais unf1 ON i.unf_seq = unf1.seq "
        + "  LEFT JOIN agh.ain_leitos l ON i.lto_lto_id = l.lto_id "
        + "  LEFT JOIN agh.agh_unidades_funcionais unf2 ON l.unf_seq = unf2.seq "
        + "  LEFT JOIN agh.ain_quartos q ON i.qrt_numero = q.numero "
        + "  LEFT JOIN agh.agh_unidades_funcionais unf3 ON q.unf_seq = unf3.seq "
        + "  LEFT JOIN agh.mpm_prescricao_medicas mpm ON mpm.atd_seq = atd.seq and :dataReferencia = DATE(mpm.dthr_inicio) "
        + " WHERE (unf1.sigla = :siglaUnidade OR unf2.sigla = :siglaUnidade OR unf3.sigla = :siglaUnidade) "
        + "  AND :dataReferencia BETWEEN DATE(i.dthr_internacao) "
        + "  AND DATE(i.dthr_alta_medica) "
        + ") AS tmp "
        + "GROUP BY tmp.sigla";

    public List<PrescricoesEletronicasManuaisDto> buscaPrescricoesEletronicasManuais(LocalDate dataReferencia) {
        try {
            Map<String, PrescricoesEletronicasManuaisDto> mapaPrescricoes = new HashMap<>();
            for (LocalDate d = dataReferencia.withDayOfMonth(1); 
                d.getMonth().equals(dataReferencia.getMonth());
                d = d.plusDays(1)) {

                @SuppressWarnings("unchecked")
                List<Object[]> rset = entityManager
                    .createNativeQuery(QUERY_PRESCRICOES_ELETRONICAS_MANUAIS)
                    .setParameter("dataReferencia", d)
                    .getResultList();

                for (Object[] r : rset) {
                    String unidade = (String) r[0];
                    BigInteger manuais = (BigInteger) r[1];
                    BigInteger eletronicas = (BigInteger) r[2];

                    if (!mapaPrescricoes.containsKey(unidade)) {
                        mapaPrescricoes.put(unidade,
                            new PrescricoesEletronicasManuaisDto(unidade, 0, 0));
                    }

                    mapaPrescricoes.get(unidade).setPrescricoesEletronicas(
                        mapaPrescricoes.get(unidade).getPrescricoesEletronicas() + eletronicas.intValue()
                    );

                    mapaPrescricoes.get(unidade).setPrescricoesManuais(
                        mapaPrescricoes.get(unidade).getPrescricoesManuais() + manuais.intValue()
                    );
                }
            }

            List<PrescricoesEletronicasManuaisDto> lista = new ArrayList<>();
            for (String key : mapaPrescricoes.keySet()) {
                lista.add(mapaPrescricoes.get(key));
            }

            Collections.sort(lista, new Comparator<PrescricoesEletronicasManuaisDto>() {
                public int compare(PrescricoesEletronicasManuaisDto p1, PrescricoesEletronicasManuaisDto p2) {
                    return p1.getSiglaUnidade().compareTo(p2.getSiglaUnidade());
                }
            });

            return lista;
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as prescrições.");
        }
    }

    public PrescricoesEletronicasManuaisDto buscaPrescricoesPorUnidade(UnidadeFuncional unidadeFuncional, LocalDate dataReferencia) {
        try {
            @SuppressWarnings("unchecked")
            List<Object[]> rset = entityManager
                .createNativeQuery(QUERY_PRESCRICOES_ELETRONICAS_MANUAIS_UNIDADE)
                .setParameter("dataReferencia", dataReferencia)
                .setParameter("siglaUnidade", unidadeFuncional.getSigla())
                .getResultList();

            if (rset.isEmpty())
                throw new CustomRuntimeException("Nenhum resultado encontrado para a unidade e data informados.");

            if (rset.size() != 1)
                throw new CustomRuntimeException("Mais de um resultado encontrado para a unidade e data informados.");

            String unidade = (String) rset.get(0)[0];
            BigInteger manuais = (BigInteger) rset.get(0)[1];
            BigInteger eletronicas = (BigInteger) rset.get(0)[2];

            return new PrescricoesEletronicasManuaisDto(unidade, eletronicas.intValue(), manuais.intValue());
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as prescrições.");
        }
    }

}

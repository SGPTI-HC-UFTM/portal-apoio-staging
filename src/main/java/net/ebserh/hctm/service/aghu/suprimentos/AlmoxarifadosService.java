package net.ebserh.hctm.service.aghu.suprimentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.suprimentos.EstoqueAlmoxarifadoDto;
import net.ebserh.hctm.model.aghu.suprimentos.Almoxarifado;
import net.ebserh.hctm.model.aghu.suprimentos.GrupoMaterial;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class AlmoxarifadosService {

    private static final String QUERY_ESTOQUE_POR_ALMOXARIFADO = "SELECT "
	    + " gm.descricao nome_grupo, "
        + " mat.codigo codigo_material, "
	    + " mat.nome nome_material, "
        + " sea.alm_seq codigo_almoxarifado, "
        + " eg.qtde quantidade_geral, "
        + " sea.qtde_disponivel quantidade_almoxarifado, "
        + " COALESCE(tab_med_12.media_12_meses, NULL, 0) media_12_meses "
        + "FROM "
        + " agh.sco_materiais mat "
        + " JOIN agh.sce_estq_gerais eg ON mat.codigo = eg.mat_codigo "
        + " JOIN agh.sce_estq_almoxs sea ON mat.codigo = sea.mat_codigo "
        + " JOIN agh.sco_grupos_materiais gm ON gm.codigo = mat.gmt_codigo "
        + " LEFT JOIN ( "
		+ "  SELECT  "
        + "   codigo_material, "
        + "   codigo_almoxarifado, "
        + "   ROUND(SUM(quantidade)/COUNT(ano_mes),2) media_12_meses "
		+ "  FROM ( "
        + "   SELECT "
        + "    codigo_material, "
        + "    codigo_almoxarifado, "
        + "    TO_DATE(TO_CHAR(data_geracao, 'YYYY-MM'), 'YYYY-MM-DD') ano_mes, "
        + "    SUM(quantidade_entregue) quantidade "
        + "   FROM ( "
        + "    SELECT "
        + "     mat.codigo codigo_material, "
        + "     mat.nome nome_material, "
        + "     gm.descricao grupo, "
        + "     smm.nro_doc_geracao, "
        + "     fcc.descricao nome_setor, "
        + "     smm.dt_geracao data_geracao, "
        + "     smm.alm_seq codigo_almoxarifado, "
        + "     smm.quantidade*(-1) quantidade_entregue, "
        + "     smm.umd_codigo unidade, "
        + "     smm.custo_medio_ponderado_ger valor_unitario, "
        + "     smm.valor*(-1) valor, "
        + "     pac.prontuario, "
        + "     tmed.descricao tipo_medicamento "
        + "    FROM "
        + "     agh.sce_movimento_materiais smm "
        + "     JOIN agh.sco_materiais mat ON mat.codigo = smm.mat_codigo "
        + "     JOIN agh.sco_grupos_materiais gm ON gm.codigo = mat.gmt_codigo "
        + "     LEFT JOIN agh.fcc_centro_custos fcc ON smm.cct_codigo = fcc.codigo "
        + "     LEFT JOIN agh.sce_req_materiais rm ON smm.nro_doc_geracao = rm.seq "
        + "     LEFT JOIN agh.afa_medicamentos med ON med.mat_codigo = mat.codigo "
        + "     LEFT JOIN agh.afa_tipo_uso_mdtos tmed ON tmed.sigla = med.tum_sigla "
        + "     LEFT JOIN agh.agh_atendimentos atd ON atd.seq = rm.atd_seq "
        + "     LEFT JOIN agh.aip_pacientes pac ON pac.codigo = atd.pac_codigo "
        + "    WHERE "
        + "     smm.tmv_seq = 3 "
        + "    UNION ALL "
        + "    SELECT "
        + "     mat.codigo codigo_material, "
        + "     mat.nome nome_material, "
        + "     gm.descricao grupo, "
        + "     smm.nro_doc_geracao, "
        + "     fcc.descricao nome_setor, "
        + "     smm.dt_geracao data_geracao, "
        + "     smm.alm_seq codigo_almoxarifado, "
        + "     smm.quantidade quantidade_entregue, "
        + "     smm.umd_codigo unidade, "
        + "     smm.custo_medio_ponderado_ger valor_unitario, "
        + "     smm.valor valor, "
        + "     pac.prontuario, "
        + "     tmed.descricao tipo_medicamento "
        + "    FROM "
        + "     agh.sce_movimento_materiais smm "
        + "     JOIN agh.sco_materiais mat ON mat.codigo = smm.mat_codigo "
        + "     JOIN agh.sco_grupos_materiais gm ON gm.codigo = mat.gmt_codigo "
        + "     LEFT JOIN agh.fcc_centro_custos fcc ON smm.cct_codigo = fcc.codigo "
        + "     LEFT JOIN agh.sce_req_materiais rm ON smm.nro_doc_geracao = rm.seq "
        + "     LEFT JOIN agh.afa_medicamentos med ON med.mat_codigo = mat.codigo "
        + "     LEFT JOIN agh.afa_tipo_uso_mdtos tmed ON tmed.sigla = med.tum_sigla "
        + "     LEFT JOIN agh.agh_atendimentos atd ON atd.seq = rm.atd_seq "
        + "     LEFT JOIN agh.aip_pacientes pac ON pac.codigo = atd.pac_codigo "
        + "    WHERE smm.tmv_seq = 5 "
        + "   ) tab "
        + "   WHERE "
        + "    data_geracao BETWEEN CAST((TO_DATE(TO_CHAR(NOW(),'YYYY-MM'),'YYYY-MM') - INTERVAL '12 MONTHS') AS DATE) "
        + "    AND CAST((TO_DATE(TO_CHAR(NOW(),'YYYY-MM'),'YYYY-MM') - INTERVAL '1 DAY') AS DATE) "
        + "   GROUP BY "
        + "    codigo_material, codigo_almoxarifado, TO_DATE(TO_CHAR(data_geracao, 'YYYY-MM'), 'YYYY-MM-DD') "
        + "   HAVING SUM(quantidade_entregue) > 0 "
        + "   ORDER BY 1,2 "
		+ "  ) tab2 "
		+ "  GROUP BY codigo_material, codigo_almoxarifado "
        + " ) tab_med_12 ON tab_med_12.codigo_material = mat.codigo AND tab_med_12.codigo_almoxarifado = sea.alm_seq "
        + "WHERE "
        + "  mat.ind_situacao = 'A' "
        + "  %s " // Filtros
        + "  AND DATE_PART('YEAR', eg.dt_competencia) = DATE_PART('YEAR', NOW()) "
	    + "  AND DATE_PART('MONTH', eg.dt_competencia) = DATE_PART('MONTH', NOW()) "
        + "  AND sea.qtde_disponivel < COALESCE(tab_med_12.media_12_meses, NULL, 0) "
        + "ORDER BY mat.nome, sea.alm_seq" ;

        private static final String FILTRO_ALMOXARIFADO = " AND sea.alm_seq = :codAlmoxarifado ";

        private static final String FILTRO_GRUPO = " AND gm.codigo = :codGrupo ";

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Almoxarifado> buscaAtivos() {
        try {
            return entityManager.createNamedQuery("Almoxarifado.findAtivos", Almoxarifado.class)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os almoxarifados.");
        }
    }

    public List<EstoqueAlmoxarifadoDto> buscaEstoquesGrupoPorAlmoxarifado(GrupoMaterial grupoMaterial, 
        Almoxarifado almoxarifado) {

        if (grupoMaterial == null)
            throw new CustomRuntimeException("É necessário informar o grupo.");

        if (almoxarifado == null)
            throw new CustomRuntimeException("É necessário informar o almoxarifado.");

        List<EstoqueAlmoxarifadoDto> lista = new ArrayList<>();
        try {
            String query = String.format(QUERY_ESTOQUE_POR_ALMOXARIFADO, FILTRO_ALMOXARIFADO + FILTRO_GRUPO);
            @SuppressWarnings("unchecked")
            List<Object[]> rset = (List<Object[]>) entityManager.createNativeQuery(query)
                .setParameter("codAlmoxarifado", almoxarifado.getSeq())
                .setParameter("codGrupo", grupoMaterial.getCodigo())
                .getResultList();

            for (Object[] r : rset) {
                EstoqueAlmoxarifadoDto dto = new EstoqueAlmoxarifadoDto();
                dto.setGrupo((String) r[0]);
                dto.setCodMaterial((Integer) r[1]);
                dto.setMaterial((String) r[2]);
                dto.setCodAlmoxarifado(((Short) r[3]).intValue());
                dto.setQuantidadeGeral((Integer) r[4]);
                dto.setQuantidadeAlmoxarifado((Integer) r[5]);
                dto.setMediaMensal((BigDecimal) r[6]);

                lista.add(dto);
            }

            return lista;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o estoque.");
        }
    }

    public List<EstoqueAlmoxarifadoDto> buscaEstoquesPorGrupo(GrupoMaterial grupoMaterial) {
        if (grupoMaterial == null)
            throw new CustomRuntimeException("É necessário informar o grupo.");


        List<EstoqueAlmoxarifadoDto> lista = new ArrayList<>();
        try {
            String query = String.format(QUERY_ESTOQUE_POR_ALMOXARIFADO, FILTRO_GRUPO);
            @SuppressWarnings("unchecked")
            List<Object[]> rset = (List<Object[]>) entityManager.createNativeQuery(query)
                .setParameter("codGrupo", grupoMaterial.getCodigo())
                .getResultList();

            for (Object[] r : rset) {
                EstoqueAlmoxarifadoDto dto = new EstoqueAlmoxarifadoDto();
                dto.setGrupo((String) r[0]);
                dto.setCodMaterial((Integer) r[1]);
                dto.setMaterial((String) r[2]);
                dto.setCodAlmoxarifado(((Short) r[3]).intValue());
                dto.setQuantidadeGeral((Integer) r[4]);
                dto.setQuantidadeAlmoxarifado((Integer) r[5]);
                dto.setMediaMensal((BigDecimal) r[6]);

                lista.add(dto);
            }

            return lista;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o estoque.");
        }
    }

    public List<EstoqueAlmoxarifadoDto> buscaEstoquesPorAlmoxarifado(Almoxarifado almoxarifado) {
        if (almoxarifado == null)
            throw new CustomRuntimeException("É necessário informar o almoxarifado.");

        List<EstoqueAlmoxarifadoDto> lista = new ArrayList<>();
        try {
            String query = String.format(QUERY_ESTOQUE_POR_ALMOXARIFADO, FILTRO_ALMOXARIFADO);
            @SuppressWarnings("unchecked")
            List<Object[]> rset = (List<Object[]>) entityManager.createNativeQuery(query)
                .setParameter("codAlmoxarifado", almoxarifado.getSeq())
                .getResultList();

            for (Object[] r : rset) {
                EstoqueAlmoxarifadoDto dto = new EstoqueAlmoxarifadoDto();
                dto.setGrupo((String) r[0]);
                dto.setCodMaterial((Integer) r[1]);
                dto.setMaterial((String) r[2]);
                dto.setCodAlmoxarifado(((Short) r[3]).intValue());
                dto.setQuantidadeGeral((Integer) r[4]);
                dto.setQuantidadeAlmoxarifado((Integer) r[5]);
                dto.setMediaMensal((BigDecimal) r[6]);

                lista.add(dto);
            }

            return lista;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o estoque.");
        }
    }
   
}

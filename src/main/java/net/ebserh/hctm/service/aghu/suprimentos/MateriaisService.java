package net.ebserh.hctm.service.aghu.suprimentos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.suprimentos.ConsumoMaterialDto;
import net.ebserh.hctm.model.aghu.suprimentos.Material;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class MateriaisService {

    private static final String QUERY_CONSUMO_POR_PERIODO = "SELECT "
        + "  ctm.mat_codigo as codigo_material, "
	    + "  gm.descricao as grupo, "
	    + "  mat.nome as material, "
	    + "  um.descricao as unidade, "
	    + "  ctm.valor as valor_unitario, "
	    + "  ctm.quantidade as quantidade, "
	    + "  cc.descricao as centro_custo, "
	    + "  TO_CHAR(ctm.dt_competencia, 'MM/YYYY') as data_competencia, "
	    + "  gm.codigo as codigo_grupo "
        + "FROM "
	    + "  agh.sce_consumo_total_materiais ctm "
	    + "  JOIN agh.sco_materiais mat ON ctm.mat_codigo = mat.codigo "
	    + "  JOIN agh.sco_grupos_materiais gm ON mat.gmt_codigo = gm.codigo "
	    + "  JOIN agh.sco_unidades_medida um ON mat.umd_codigo = um.codigo "
	    + "  JOIN agh.fcc_centro_custos cc ON ctm.cct_codigo = cc.codigo "
        + "WHERE "
	    + "  TO_CHAR(ctm.dt_competencia, 'YYYY-MM') BETWEEN :dataInicial AND :dataFinal "
	    + "  AND ctm.mat_codigo IN :codMateriais "
        + "ORDER BY "
	    + "  codigo_material, data_competencia, centro_custo";

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Material> buscaMateriais() {
        try {
            return entityManager.createNamedQuery("Material.findAll", Material.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os materiais");
        }
    }

    public Material buscaPorCodigo(Integer codigo) {
        try {
            return entityManager.find(Material.class, codigo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar material");
        }
    }

    public List<ConsumoMaterialDto> buscaConsumoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Material> materiais) {
        if (dataInicial == null || dataFinal == null)
            throw new CustomRuntimeException("É necessário informar as datas inicial e final.");

        if (dataInicial.isAfter(dataFinal))
            throw new CustomRuntimeException("Data final deve ser posterior à data inicial.");

        if (materiais == null || materiais.isEmpty())
            throw new CustomRuntimeException("É necessário informar os materiais desejados para emissão do relatório.");

        try {
            List<Integer> codMateriais = new ArrayList<>();
            for (Material material : materiais) 
                codMateriais.add(material.getCodigo());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM");

            @SuppressWarnings("unchecked")
            List<Object[]> rset = entityManager.createNativeQuery(QUERY_CONSUMO_POR_PERIODO)
                .setParameter("dataInicial", formatter.format(dataInicial))
                .setParameter("dataFinal", formatter.format(dataFinal))
                .setParameter("codMateriais", codMateriais)
                .getResultList();

            List<ConsumoMaterialDto> lista = new ArrayList<>();
            for (Object[] r : rset) {
                ConsumoMaterialDto dto = new ConsumoMaterialDto();
                dto.setCodigoMaterial((Integer) r[0]);
                dto.setGrupo((String) r[1]);
                dto.setMaterial((String) r[2]);
                dto.setUnidade((String) r[3]);
                dto.setValorUnitario(new BigDecimal((Double) r[4]));
                dto.setQuantidadeConsumida((Integer) r[5] > 0 ? (Integer) r[5] : 0);
                dto.setCentroCusto((String) r[6]);
                dto.setDataCompetencia((String) r[7]);
                dto.setCodigoGrupo(Integer.valueOf((Short) r[8]));

                lista.add(dto);
            }

            return lista;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o consumo de materiais.");
        }
    }

}

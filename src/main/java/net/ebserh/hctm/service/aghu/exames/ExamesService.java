package net.ebserh.hctm.service.aghu.exames;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.exames.ExameFaturamentoDto;
import net.ebserh.hctm.dto.aghu.exames.ProfissionalExecutanteDto;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class ExamesService {

	private static final String QUERY_EXAMES_EXCLUINDO_AIH = "SELECT "
			+ "	DISTINCT ON (data_exame, solicitacao, item_solicitacao) "
			+ "	aise.soe_seq AS solicitacao, "
			+ "	aise.seqp AS item_solicitacao, "
			+ "	ae.sigla AS exame, "
			+ "	fiph.cod_tabela AS sigtap, "
			+ "	rpti1.valor AS cbo, "
			+ "	rpti2.valor AS cns, "
			+ "	aise.sit_codigo AS situacao, "
			+ "	ap.prontuario AS prontuario_paciente, "
			+ "	ap.nome AS nome_paciente, "
			+ "	ap.sexo AS sexo_paciente, "
			+ "	ap.dt_nascimento AS data_nascimento_paciente, "
			+ "	DATE_PART('YEAR', AGE(ap.dt_nascimento)) AS idade_paciente, "
			+ "	ap.cor, "
			+ "	ap.nro_cartao_saude AS cartao_sus, "
			+ "	COALESCE(ac.cod_ibge, ac2.cod_ibge) AS codigo_ibge, "
			+ "	COALESCE(aep.bcl_clo_cep, aep.cep) AS cep_paciente, "
			+ "	atl.codigo_base_sus AS tipo_logradouro, "
			+ "	COALESCE(al.nome, aep.logradouro) AS nome_logradouro, "
			+ "	aep.nro_logradouro AS numero, "
			+ "	aep.compl_logradouro AS complemento_logradouro, "
			+ "	COALESCE(ab.descricao, aep.bairro) AS nome_bairro, "
			+ "	aep.seqp AS seq_endereco, "
			+ "	ap.nac_codigo AS nacionalidade, "
			+ "	MIN(aeis.dthr_evento) AS data_exame "
			+ "FROM "
			+ "	agh.ael_resultados_exames are2 "
			+ "	JOIN agh.ael_item_solicitacao_exames aise ON are2.ise_soe_seq = aise.soe_seq AND are2.ise_seqp = aise.seqp "
			+ "	JOIN agh.ael_extrato_item_solics aeis ON aeis.ise_soe_seq = aise.soe_seq AND aeis.ise_seqp = aise.seqp "
			+ "	JOIN agh.ael_solicitacao_exames ase ON aise.soe_seq = ase.seq "
			+ "	JOIN agh.agh_atendimentos aa ON ase.atd_seq = aa.seq "
			+ "	JOIN agh.ael_exames ae ON aise.ufe_ema_exa_sigla = ae.sigla "
			+ "	JOIN agh.aip_pacientes ap ON aa.pac_codigo = ap.codigo "
			+ "	JOIN agh.rap_servidores rs ON aise.ser_matricula_eh_responsabilid = rs.matricula AND aise.ser_vin_codigo_eh_responsabili = rs.vin_codigo "
			+ "	LEFT JOIN agh.rap_pessoas_fisicas rpf ON rs.pes_codigo = rpf.codigo "
			+ "	LEFT JOIN agh.rap_qualificacoes rq ON rq.pes_codigo = rpf.codigo "
			+ "	LEFT JOIN agh.rap_pessoa_tipo_informacoes rpti1 ON rpf.codigo = rpti1.pes_codigo AND rpti1.tii_seq = 2 "
			+ "	LEFT JOIN agh.rap_pessoa_tipo_informacoes rpti2 ON rpf.codigo = rpti2.pes_codigo AND rpti2.tii_seq = 7 "
			+ "	LEFT JOIN agh.fat_proced_hosp_internos fphi ON ae.sigla = fphi.ema_exa_sigla "
			// Plano ambulatório (fat_conv_saude_planos)
			+ "	LEFT JOIN agh.fat_conv_grupo_itens_proced fcgip ON fphi.seq = fcgip.phi_seq AND fcgip.cpg_cph_csp_seq = 2 "
			+ "	LEFT JOIN agh.fat_itens_proced_hospitalar fiph ON fcgip.iph_pho_seq = fiph.pho_seq AND fcgip.iph_seq = fiph.seq "
			+ "	LEFT JOIN agh.aip_enderecos_pacientes aep ON ap.codigo = aep.pac_codigo "
			+ "	LEFT JOIN agh.aip_logradouros al ON aep.bcl_clo_lgr_codigo = al.codigo "
			+ "	LEFT JOIN agh.aip_tipo_logradouros atl ON atl.codigo = al.tlg_codigo "
			+ "	LEFT JOIN agh.aip_bairros ab ON aep.bcl_bai_codigo = ab.codigo "
			+ "	LEFT JOIN agh.aip_cidades ac ON al.cdd_codigo = ac.codigo "
			+ "	LEFT JOIN agh.aip_cidades ac2 ON aep.cdd_codigo = ac2.codigo "
			+ "WHERE "
			+ "	aa.origem IN ('A', 'X') "
			+ "	AND aise.sit_codigo = 'LI' "
			+ "	AND are2.ind_anulacao_laudo = 'N' "
			+ "	AND aeis.sit_codigo = 'AE' "
			+ "	AND CAST (aeis.dthr_evento AS DATE) BETWEEN :dataInicial AND :dataFinal "
			+ "	AND (aise.soe_seq, aise.seqp) NOT IN ( "
			+ "		SELECT DISTINCT "
			+ "			aise.soe_seq solicitacao, "
			+ "			aise.seqp item_solicitacao "
			+ "		FROM "
			+ "			agh.ael_item_solicitacao_exames aise "
			+ "			JOIN agh.ael_extrato_item_solics aeis ON aeis.ise_soe_seq = aise.soe_seq AND aeis.ise_seqp = aise.seqp "
			+ "			JOIN agh.ael_solicitacao_exames ase ON aise.soe_seq = ase.seq "
			+ "			JOIN agh.agh_atendimentos aa ON ase.atd_seq = aa.seq "
			+ "			JOIN agh.aip_pacientes ap ON aa.pac_codigo = ap.codigo "
			+ "			JOIN agh.ain_internacoes ai ON ai.pac_codigo = ap.codigo "
			+ "			JOIN agh.ain_tipos_alta_medica atam ON atam.codigo = ai.tam_codigo"
			+ "		WHERE "
			+ "			aa.origem IN ('A', 'X') "
			+ "			AND aise.sit_codigo = 'LI' "
			+ "			AND aeis.sit_codigo = 'AE' "
			+ "			AND CAST(aeis.dthr_evento AS DATE) BETWEEN :dataInicial AND :dataFinal "
			+ "			AND ("
			// 1: internação com menos de 24 horas, alta por evasão ou óbito
			+ "				( "
			+ "					ai.dthr_alta_medica > aeis.dthr_evento "
			+ "					AND ai.dthr_alta_medica - ai.dthr_internacao < INTERVAL '1 day' "
			+ "					AND ai.dthr_internacao - aeis.dthr_evento < INTERVAL '30 days' "
			+ "					AND ai.tam_codigo IN ('C', 'D', 'O', 'E', 'I', 'F') "
			+ "				) "
			+ "				OR "
			// 2: internação com mais de 24 horas, ocorrendo até 30 dias após o exame
			+ "				( "
			+ "					ai.dthr_alta_medica > aeis.dthr_evento "
			+ "					AND ai.dthr_alta_medica - ai.dthr_internacao > INTERVAL '1 day' "
			+ "					AND ai.dthr_internacao - aeis.dthr_evento < INTERVAL '30 days' "
			+ "				) "
			+ "			) "
			+ "		) "
			+ "GROUP BY "
			+ "	solicitacao, item_solicitacao, exame, sigtap, cbo, cns, situacao, prontuario_paciente, nome_paciente, "
			+ "	sexo_paciente, data_nascimento_paciente, idade_paciente, cor,  cartao_sus, codigo_ibge, cep_paciente, "
			+ "	tipo_logradouro, nome_logradouro, numero, complemento_logradouro, nome_bairro, seq_endereco, nacionalidade "
			+ "ORDER BY "
			+ "	data_exame, solicitacao, item_solicitacao, aep.seqp DESC";

	private static final String QUERY_EXAMES_FATURADOS_AIH = "SELECT "
			+ "	aise.soe_seq solicitacao, "
			+ "	aise.seqp item_solicitacao, "
			+ "	aa.origem origem_pedido, "
			+ " ap.prontuario prontuario_paciente, "
			+ "	ai.dthr_internacao dthr_internacao, "
			+ "	ai.dthr_alta_medica dthr_alta, "
			+ "	atam.descricao motivo_alta, "
			+ "	MIN(aeis.dthr_evento) data_exame "
			+ "FROM "
			+ "	agh.ael_item_solicitacao_exames aise "
			+ "	JOIN agh.ael_extrato_item_solics aeis ON aeis.ise_soe_seq = aise.soe_seq AND aeis.ise_seqp = aise.seqp "
			+ "	JOIN agh.ael_solicitacao_exames ase ON aise.soe_seq = ase.seq "
			+ " 	JOIN agh.agh_atendimentos aa ON ase.atd_seq = aa.seq "
			+ " 	JOIN agh.aip_pacientes ap ON aa.pac_codigo = ap.codigo "
			+ " 	JOIN agh.ain_internacoes ai ON ai.pac_codigo = ap.codigo "
			+ " 	JOIN agh.ain_tipos_alta_medica atam ON atam.codigo = ai.tam_codigo "
			+ "WHERE "
			+ "	aa.origem IN ('A', 'X') "
			+ "	AND aise.sit_codigo = 'LI' "
			+ "	AND aeis.sit_codigo = 'AE' "
			+ "	AND CAST(aeis.dthr_evento AS DATE) BETWEEN :dataInicial AND :dataFinal "
			+ "	AND ( "
			// 1: internação com menos de 24 horas, alta por evasão ou óbito
			+ "		( "
			+ "			ai.dthr_alta_medica > aeis.dthr_evento "
			+ "			AND ai.dthr_alta_medica - ai.dthr_internacao < INTERVAL '1 day' "
			+ "			AND ai.dthr_internacao - aeis.dthr_evento < INTERVAL '30 days' "
			+ "			AND ai.tam_codigo IN ('C', 'D', 'O', 'E', 'I', 'F') "
			+ "		) "
			+ "		OR "
			// 2: internação com mais de 24 horas, ocorrendo até 30 dias após o exame
			+ "		( "
			+ "			ai.dthr_alta_medica > aeis.dthr_evento "
			+ "			AND ai.dthr_alta_medica - ai.dthr_internacao > INTERVAL '1 day' "
			+ "			AND ai.dthr_internacao - aeis.dthr_evento < INTERVAL '30 days' "
			+ "		) "
			+ "	) "
			+ "GROUP BY "
			+ "	solicitacao, item_solicitacao, origem_pedido, dthr_internacao, dthr_alta, motivo_alta, prontuario_paciente "
			+ "ORDER BY "
			+ "	data_exame";

	private static final String QUERY_EXAMES_EXCLUINDO_AIH_LOTE = "SELECT "
			+ "	DISTINCT ON (solicitacao, item_solicitacao) "
			+ "	aise.soe_seq AS solicitacao, "
			+ "	aise.seqp AS item_solicitacao, "
			+ "	ae.sigla AS exame, "
			+ "	fiph.cod_tabela AS sigtap, "
			+ "	aise.sit_codigo AS situacao, "
			+ "	ap.prontuario AS prontuario_paciente, "
			+ "	ap.nome AS nome_paciente, "
			+ "	ap.sexo AS sexo_paciente, "
			+ "	ap.dt_nascimento AS data_nascimento_paciente, "
			+ "	DATE_PART('YEAR', AGE(ap.dt_nascimento)) AS idade_paciente, "
			+ "	ap.cor, "
			+ "	ap.nro_cartao_saude AS cartao_sus, "
			+ "	COALESCE(ac.cod_ibge, ac2.cod_ibge) AS codigo_ibge, "
			+ "	COALESCE(aep.bcl_clo_cep, aep.cep) AS cep_paciente, "
			+ " COALESCE(atl.codigo_base_sus, 81) AS tipo_logradouro, "
			+ "	COALESCE(al.nome, aep.logradouro) AS nome_logradouro, "
			+ "	aep.nro_logradouro AS numero, "
			+ "	aep.compl_logradouro AS complemento_logradouro, "
			+ "	COALESCE(ab.descricao, aep.bairro) AS nome_bairro, "
			+ "	aep.seqp AS seq_endereco, "
			+ "	ap.nac_codigo AS nacionalidade "
			+ "FROM "
			+ "	agh.ael_item_solicitacao_exames aise "
			+ "	JOIN agh.ael_solicitacao_exames ase ON aise.soe_seq = ase.seq "
			+ "	JOIN agh.agh_atendimentos aa ON ase.atd_seq = aa.seq "
			+ "	JOIN agh.ael_exames ae ON aise.ufe_ema_exa_sigla = ae.sigla "
			+ "	JOIN agh.aip_pacientes ap ON aa.pac_codigo = ap.codigo "
			+ "	LEFT JOIN agh.fat_proced_hosp_internos fphi ON ae.sigla = fphi.ema_exa_sigla "
			// Plano ambulatório (fat_conv_saude_planos)
			+ "	LEFT JOIN agh.fat_conv_grupo_itens_proced fcgip ON fphi.seq = fcgip.phi_seq AND fcgip.cpg_cph_csp_seq = 2 "
			+ "	LEFT JOIN agh.fat_itens_proced_hospitalar fiph ON fcgip.iph_pho_seq = fiph.pho_seq AND fcgip.iph_seq = fiph.seq "
			+ "	LEFT JOIN agh.aip_enderecos_pacientes aep ON ap.codigo = aep.pac_codigo "
			+ "	LEFT JOIN agh.aip_logradouros al ON aep.bcl_clo_lgr_codigo = al.codigo "
			+ "	LEFT JOIN agh.aip_tipo_logradouros atl ON atl.codigo = al.tlg_codigo "
			+ "	LEFT JOIN agh.aip_bairros ab ON aep.bcl_bai_codigo = ab.codigo "
			+ "	LEFT JOIN agh.aip_cidades ac ON al.cdd_codigo = ac.codigo "
			+ "	LEFT JOIN agh.aip_cidades ac2 ON aep.cdd_codigo = ac2.codigo "
			+ "WHERE "
			+ "	aa.origem IN ('A', 'X') "
			+ "	AND ase.seq IN :solicitacoes";

	private static final String QUERY_PROFISSIONAIS_EXECUTANTES_LOTE = "SELECT "
			+ " DISTINCT ON (rpf.cpf) "
			+ "rpf.nome, rpf.cpf, rpti1.valor AS cbo, rpti2.valor AS cns, regexp_replace(rq.nro_reg_conselho, '[^0-9]+', '', 'g') AS crm "
			+ "FROM agh.rap_pessoas_fisicas rpf "
			+ "JOIN agh.rap_servidores rs ON rs.pes_codigo = rpf.codigo "
			+ "LEFT JOIN agh.rap_qualificacoes rq ON rq.pes_codigo = rpf.codigo "
			+ "LEFT JOIN agh.rap_pessoa_tipo_informacoes rpti1 ON rpf.codigo = rpti1.pes_codigo AND rpti1.tii_seq = 2 "
			+ "LEFT JOIN agh.rap_pessoa_tipo_informacoes rpti2 ON rpf.codigo = rpti2.pes_codigo AND rpti2.tii_seq = 7 "
			+ "WHERE rpf.cpf IN :cpfs";

	private static final String QUERY_PROFISSIONAIS_EXECUTANTES_CRM_LOTE = "SELECT "
			+ " DISTINCT ON (rq.nro_reg_conselho) "
			+ "rpf.nome, rpf.cpf, rpti1.valor AS cbo, rpti2.valor AS cns, regexp_replace(rq.nro_reg_conselho, '[^0-9]+', '', 'g') AS crm "
			+ "FROM agh.rap_pessoas_fisicas rpf "
			+ "JOIN agh.rap_servidores rs ON rs.pes_codigo = rpf.codigo "
			+ "JOIN agh.rap_qualificacoes rq ON rq.pes_codigo = rpf.codigo "
			+ "LEFT JOIN agh.rap_pessoa_tipo_informacoes rpti1 ON rpf.codigo = rpti1.pes_codigo AND rpti1.tii_seq = 2 "
			+ "LEFT JOIN agh.rap_pessoa_tipo_informacoes rpti2 ON rpf.codigo = rpti2.pes_codigo AND rpti2.tii_seq = 7 "
			+ "WHERE regexp_replace(rq.nro_reg_conselho, '[^0-9]+', '', 'g') IN :crms";

	private static final Logger logger = Logger.getAnonymousLogger();

	@PersistenceContext
	private EntityManager entityManager;

	public List<ExameFaturamentoDto> buscaExamesFaturamento(LocalDate dataReferencia) {
		try {
			LocalDate dataInicial = dataReferencia.withDayOfMonth(1);
			LocalDate dataFinal = dataReferencia.withDayOfMonth(dataReferencia.lengthOfMonth());

			@SuppressWarnings("unchecked")
			List<Object[]> l = entityManager.createNativeQuery(QUERY_EXAMES_EXCLUINDO_AIH)
					.setParameter("dataInicial", dataInicial)
					.setParameter("dataFinal", dataFinal)
					.getResultList();

			List<ExameFaturamentoDto> exames = new ArrayList<>();
			for (Object[] r : l) {
				ExameFaturamentoDto dto = new ExameFaturamentoDto();
				dto.setSolicitacao((Integer) r[0]);
				dto.setItemSolicitacao(((Short) r[1]).intValue());
				dto.setExame((String) r[2]);
				if (r[3] != null)
					dto.setSigtap(((BigInteger) r[3]).longValue());

				dto.setCbo((String) r[4]);
				dto.setCns((String) r[5]);
				dto.setSituacao((String) r[6]);
				dto.setProntuario((Integer) r[7]);
				dto.setNome((String) r[8]);
				dto.setSexo((String) r[9]);
				if (r[10] != null)
					dto.setDataNascimento(((Timestamp) r[10]).toLocalDateTime().toLocalDate());

				if (r[11] != null)
					dto.setIdade(((Double) r[11]).intValue());

				dto.setCor((String) r[12]);
				if (r[13] != null)
					dto.setCartaoSus(((BigInteger) r[13]).longValue());

				dto.setCodigoIbge((Integer) r[14]);
				dto.setCep((Integer) r[15]);
				dto.setTipoLogradouro((Integer) r[16]);
				dto.setLogradouro((String) r[17]);
				dto.setNumero((Integer) r[18]);
				dto.setComplemento((String) r[19]);
				dto.setBairro((String) r[20]);
				if (r[21] != null)
					dto.setCodigoEndereco(((Short) r[21]).intValue());

				if (r[22] != null)
					dto.setNacionalidade(((Short) r[22]).intValue());

				if (r[23] != null)
					dto.setDthrExame(((Timestamp) r[23]).toLocalDateTime());

				exames.add(dto);
			}

			return exames;
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os exames.");
		}
	}

	public List<ExameFaturamentoDto> buscaExamesFaturamentoAih(LocalDate dataReferencia) {
		try {
			LocalDate dataInicial = dataReferencia.withDayOfMonth(1);
			LocalDate dataFinal = dataReferencia.withDayOfMonth(dataReferencia.lengthOfMonth());

			@SuppressWarnings("unchecked")
			List<Object[]> l = entityManager.createNativeQuery(QUERY_EXAMES_FATURADOS_AIH)
					.setParameter("dataInicial", dataInicial)
					.setParameter("dataFinal", dataFinal)
					.getResultList();

			List<ExameFaturamentoDto> exames = new ArrayList<>();
			for (Object[] r : l) {
				ExameFaturamentoDto dto = new ExameFaturamentoDto();
				dto.setSolicitacao((Integer) r[0]);
				dto.setItemSolicitacao(((Short) r[1]).intValue());
				dto.setOrigemPedido((String) r[2]);
				dto.setProntuario((Integer) r[3]);
				if (r[4] != null)
					dto.setDthrInternacao(((Timestamp) r[4]).toLocalDateTime());

				if (r[5] != null)
					dto.setDthrAlta(((Timestamp) r[5]).toLocalDateTime());

				dto.setMotivoAlta((String) r[6]);
				if (r[7] != null)
					dto.setDthrExame(((Timestamp) r[7]).toLocalDateTime());

				exames.add(dto);
			}

			return exames;
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os exames.");
		}
	}

	public List<ExameFaturamentoDto> buscaLoteExamesFaturamento(List<Integer> solicitacoes, Map<Integer, List<Integer>> itensPorSolicitacao) {
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> l = entityManager.createNativeQuery(QUERY_EXAMES_EXCLUINDO_AIH_LOTE) 
				.setParameter("solicitacoes", solicitacoes)
				.getResultList();

			List<ExameFaturamentoDto> exames = new ArrayList<>();
			for (Object[] r : l) {
				Integer codigoSolicitacao = (Integer) r[0];
				Integer codigoItemSolicitacao = ((Short) r[1]).intValue();

				if (codigoSolicitacao == null || codigoItemSolicitacao == null)
					throw new CustomRuntimeException("Exame com código incompleto no AGHU");

				if (!itensPorSolicitacao.containsKey(codigoSolicitacao))
					continue;

				if (!itensPorSolicitacao.get(codigoSolicitacao).contains(codigoItemSolicitacao))
					continue;

				ExameFaturamentoDto dto = new ExameFaturamentoDto();
				dto.setSolicitacao(codigoSolicitacao);
				dto.setItemSolicitacao(codigoItemSolicitacao);
				dto.setExame((String) r[2]);
				if (r[3] != null)
					dto.setSigtap(((BigInteger) r[3]).longValue());

				dto.setSituacao((String) r[4]);
				dto.setProntuario((Integer) r[5]);
				dto.setNome((String) r[6]);
				dto.setSexo((String) r[7]);
				if (r[8] != null)
					dto.setDataNascimento(((Timestamp) r[8]).toLocalDateTime().toLocalDate());

				if (r[9] != null)
					dto.setIdade(((Double) r[9]).intValue());

				dto.setCor((String) r[10]);
				if (r[11] != null)
					dto.setCartaoSus(((BigInteger) r[11]).longValue());

				dto.setCodigoIbge((Integer) r[12]);
				dto.setCep((Integer) r[13]);
				dto.setTipoLogradouro((Integer) r[14]);
				dto.setLogradouro((String) r[15]);
				dto.setNumero((Integer) r[16]);
				dto.setComplemento((String) r[17]);
				dto.setBairro((String) r[18]);
				if (r[19] != null)
					dto.setCodigoEndereco(((Short) r[19]).intValue());

				if (r[20] != null)
					dto.setNacionalidade(((Short) r[20]).intValue());

				exames.add(dto);
			}

			return exames;
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os exames.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProfissionalExecutanteDto> buscaLoteProfissionaisExecutantes(List<Long> cpfsProfissionais) {
		if (cpfsProfissionais == null || cpfsProfissionais.isEmpty())
			return new ArrayList<>();

		try {
			List<Object[]> l = entityManager.createNativeQuery(QUERY_PROFISSIONAIS_EXECUTANTES_LOTE)
					.setParameter("cpfs", cpfsProfissionais)
					.getResultList();

			List<ProfissionalExecutanteDto> profissionais = new ArrayList<>();

			for (Object[] r : l) {
				ProfissionalExecutanteDto dto = new ProfissionalExecutanteDto();
				BigInteger cpf = (BigInteger) r[1];
				dto.setNome((String) r[0]);
				dto.setCpf(cpf.longValue());
				dto.setCbo((String) r[2]);
				dto.setCns((String) r[3]);
				
				if (r[4] != null) {
					Integer crm = Integer.parseInt(((String) r[4]).replaceAll("[^\\d]", ""));
					dto.setCrm(crm);
				}

				profissionais.add(dto);
			}

			return profissionais;

		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os profissionais.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProfissionalExecutanteDto> buscaLoteProfissionaisExecutantesCrm(List<Integer> crmsProfissionais) {
		if (crmsProfissionais == null || crmsProfissionais.isEmpty())
			return new ArrayList<>();

		try {
			List<String> crmsStr = new ArrayList<>();
			crmsProfissionais.forEach(crm -> crmsStr.add(String.valueOf(crm)));
			List<Object[]> l = entityManager.createNativeQuery(QUERY_PROFISSIONAIS_EXECUTANTES_CRM_LOTE)
					.setParameter("crms", crmsStr)
					.getResultList();

			List<ProfissionalExecutanteDto> profissionais = new ArrayList<>();

			for (Object[] r : l) {
				ProfissionalExecutanteDto dto = new ProfissionalExecutanteDto();
				BigInteger cpf = (BigInteger) r[1];
				dto.setNome((String) r[0]);
				dto.setCpf(cpf != null ? cpf.longValue() : null);
				dto.setCbo((String) r[2]);
				dto.setCns((String) r[3]);

				if (r[4] != null) {
					Integer crm = Integer.parseInt(((String) r[4]).replaceAll("[^\\d]", ""));
					dto.setCrm(crm);
				}

				profissionais.add(dto);
			}

			return profissionais;

		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os profissionais.");
		}
	}

}

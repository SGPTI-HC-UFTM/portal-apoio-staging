package net.ebserh.hctm.service.aghu.pacientes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.pacientes.EnderecoPaciente;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;

@Stateless
public class PacientesService {
	
	private static final Logger logger = Logger.getLogger(PacientesService.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Paciente buscaPorProntuario(Integer prontuario) {
		try {
			return entityManager
					.createNamedQuery("Paciente.findByProntuario", Paciente.class)
					.setParameter("prontuario", prontuario)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar o paciente.");
		}
	}

	public List<Paciente> buscaPorProntuarioIn(List<Integer> prontuarios) {
		if (prontuarios == null || prontuarios.isEmpty())
			return new ArrayList<>();

		try {
			return entityManager
					.createNamedQuery("Paciente.findByProntuarioIn", Paciente.class)
					.setParameter("prontuarios", prontuarios)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes.");
		}
	}

	public EnderecoPaciente getEnderecoPaciente(Integer prontuario) {
		try {
			List<EnderecoPaciente> enderecos = entityManager
					.createNamedQuery("EnderecoPaciente.findByProntuario", EnderecoPaciente.class)
					.setParameter("prontuario", prontuario)
					.getResultList();
			
			if (!enderecos.isEmpty())
				return enderecos.get(0);
			
			return null;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erro buscando endereços", e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os endereços do paciente.");
		}
	}

	public String getEnderecoFormatado(Paciente paciente) {
		if (paciente == null || paciente.getProntuario() == null)
			return "";

		try {
			List<EnderecoPaciente> enderecos = entityManager
					.createNamedQuery("EnderecoPaciente.findByProntuario", EnderecoPaciente.class)
					.setParameter("prontuario", paciente.getProntuario())
					.getResultList();
			
			if (enderecos.isEmpty())
				return "";

			String tipoLogradouro = "";
			if (enderecos.get(0).getBairroCepLogradouro() != null)
				tipoLogradouro = enderecos.get(0).getBairroCepLogradouro().getCepLogradouro().getLogradouro().getTipoLogradouro().getDescricao();
			else if (enderecos.get(0).getLogradouro() != null)
				tipoLogradouro = enderecos.get(0).getLogradouro().getTipoLogradouro().getDescricao();

			String logradouro = "";
			if (enderecos.get(0).getBairroCepLogradouro() != null)
				logradouro = enderecos.get(0).getBairroCepLogradouro().getCepLogradouro().getLogradouro().getNome();
			else if (enderecos.get(0).getLogradouro() != null)
				logradouro = enderecos.get(0).getLogradouro().getNome();
			else if (enderecos.get(0).getEndereco() != null && !enderecos.get(0).getEndereco().isBlank())
				logradouro = enderecos.get(0).getEndereco();

			Integer numero = enderecos.get(0).getNumero();

			String bairro = "";
			if (enderecos.get(0).getBairroCepLogradouro() != null)
				bairro = enderecos.get(0).getBairroCepLogradouro().getBairro().getDescricao();
			else if (enderecos.get(0).getBairro() != null)
				bairro = enderecos.get(0).getBairro().getDescricao();
			else if (enderecos.get(0).getBairroNC() != null && !enderecos.get(0).getBairroNC().isBlank())
				bairro = enderecos.get(0).getBairroNC();

			return String.format("%s %s, %d, %s", tipoLogradouro.toUpperCase(), logradouro.toUpperCase(), numero, bairro.toUpperCase());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao formatad o endereço do paciente.");
		}
	}

	public String getCidadeResidencia(Paciente paciente) {
		if (paciente == null || paciente.getProntuario() == null)
			return "";

		try {
			List<EnderecoPaciente> enderecos = entityManager
					.createNamedQuery("EnderecoPaciente.findByProntuario", EnderecoPaciente.class)
					.setParameter("prontuario", paciente.getProntuario())
					.getResultList();
			
			if (enderecos.isEmpty())
				return "";

			String cidade = "";
			if (enderecos.get(0).getBairroCepLogradouro() != null)
				cidade = enderecos.get(0).getBairroCepLogradouro().getCepLogradouro().getLogradouro().getCidade().getNome() 
					+ "/"
					+ enderecos.get(0).getBairroCepLogradouro().getCepLogradouro().getLogradouro().getCidade().getUf();
			else if (enderecos.get(0).getLogradouro() != null)
				cidade = enderecos.get(0).getLogradouro().getCidade().getNome()
					+ "/"
					+ enderecos.get(0).getLogradouro().getCidade().getUf();
			else if (enderecos.get(0).getCidade() != null)
				cidade = enderecos.get(0).getCidade().getNome()
					+ "/"
					+ enderecos.get(0).getCidade().getUf();

			return cidade;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao formatad o endereço do paciente.");
		}
	}

}

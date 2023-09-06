package net.ebserh.hctm.controller.administracao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.util.Aviso;
import net.ebserh.hctm.service.administracao.AvisosService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class AvisosController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	@Inject
	private AvisosService avisosService;
	
	private String textoPesquisa;
	
	private List<Aviso> avisos;

	private List<Aviso> avisosDia;
	
	private Aviso aviso;
	
	@PostConstruct
	public void init() {
		avisos = avisosService.buscaTodos();
		avisosDia = avisosService.buscaAvisosDia();
	}
	
	public List<Aviso> getAvisosDia() {
		return avisosDia;
	}

	public void setAvisosDia(List<Aviso> avisosDia) {
		this.avisosDia = avisosDia;
	}

	public void openDialogNovoAviso() {
		aviso = new Aviso();
		PrimeFaces.current().executeScript("PF('dlgNovoAviso').show()");
	}

	public void openDialogAviso(Aviso avisoSelecionado) {
		aviso = avisoSelecionado;
		PrimeFaces.current().executeScript("PF('dlgNovoAviso').show()");
	}
	
	public void salvaAviso() {
		if (aviso == null) {
			FacesUtils.showError("É necessário selecionar um aviso.");
			return;
		}
		
		try {
			avisosService.salva(aviso);
			avisos = avisosService.buscaTodos();
			PrimeFaces.current().executeScript("PF('dlgNovoAviso').hide()");
			FacesUtils.showInfo("Aviso salvo com sucesso!");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao salvar o aviso.");
		}
	}

	public void excluiAviso() {
		try {
			avisosService.excluir(aviso);
			avisos = avisosService.buscaTodos();
			PrimeFaces.current().executeScript("PF('dlgNovoAviso').hide()");
			FacesUtils.showInfo("Aviso removido com sucesso!");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao excluir o aviso.");
		}
	}

	/*
	 * Auto-generated
	 */
	public List<Aviso> getAvisos() {
		return avisos;
	}

	public void setAvisos(List<Aviso> avisos) {
		this.avisos = avisos;
	}

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	public String getTextoPesquisa() {
		return textoPesquisa;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aviso == null) ? 0 : aviso.hashCode());
		result = prime * result + ((avisos == null) ? 0 : avisos.hashCode());
		result = prime * result + ((avisosService == null) ? 0 : avisosService.hashCode());
		result = prime * result + ((logger == null) ? 0 : logger.hashCode());
		result = prime * result + ((textoPesquisa == null) ? 0 : textoPesquisa.hashCode());
		return result;
	}
}

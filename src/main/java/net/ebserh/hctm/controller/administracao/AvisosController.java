package net.ebserh.hctm.controller.administracao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.security.enterprise.SecurityContext;
import org.primefaces.PrimeFaces;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.util.Aviso;
import net.ebserh.hctm.service.administracao.AvisosService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class AvisosController implements Serializable {
	
	private static final Logger logger = Logger.getAnonymousLogger();
	
	@Inject
	private AvisosService avisosService;

    @Inject
    SecurityContext securityContext;
	
	private String textoPesquisa;
	
	private List<Aviso> avisos;

	private List<Aviso> avisosDia;
	
	private Aviso aviso;
	
	@PostConstruct
	public void init() {
		avisos = avisosService.buscaTodos();
		avisosDia = avisosService.buscaAvisosDia();
	}

    public Boolean userInRole() {
        return securityContext.isCallerInRole("ADMIN");
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

}

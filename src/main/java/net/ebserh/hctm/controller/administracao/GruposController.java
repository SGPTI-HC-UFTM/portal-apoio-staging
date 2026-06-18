package net.ebserh.hctm.controller.administracao;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.auth.Grupo;
import net.ebserh.hctm.service.administracao.GruposService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class GruposController implements Serializable {

    private final static  Logger logger = Logger.getAnonymousLogger();

    @Inject
    private GruposService gruposService;

    private List<Grupo> grupos;

    private Grupo grupo;

    @PostConstruct
    public void init() {
        try {
            grupos = gruposService.buscaTodos();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogGrupos() {
        grupo = new Grupo();
        PrimeFaces.current().executeScript("PF('dialogGrupos').show()");
    }

    public void openDialogGrupos(Grupo grupo) {
        if (grupo == null) {
            FacesUtils.showError("É necessário selecionar um grupo.");
            return;
        }

        this.grupo = grupo;
        PrimeFaces.current().executeScript("PF('dialogGrupos').show()");
    }

    public void salvaGrupo() {
        if (grupo == null) {
            FacesUtils.showError("É necessário informar um grupo.");
            return;
        }

        try {
            gruposService.salvaGrupo(grupo);
            grupos = gruposService.buscaTodos();
            PrimeFaces.current().executeScript("PF('dialogGrupos').hide()");
            FacesUtils.showInfo("Grupo salvo com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar o grupo.");
        }
    }


    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}

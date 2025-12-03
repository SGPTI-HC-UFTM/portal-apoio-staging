package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.Cbo;
import net.ebserh.hctm.service.pesquisa.CbosService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CbosController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private CbosService cbosService;
    private List<Cbo> cbos = new ArrayList<>();
    private Cbo cbo;

    @PostConstruct
    public void init() {
        try {
            cbos = cbosService.buscaCbos();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNova() {
        cbo = new Cbo();
        PrimeFaces.current().executeScript("PF('dialogCbo').show()");
    }

    public void editaCbo(Cbo cbo) {
        if (cbo == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }
        this.cbo = cbo;
        PrimeFaces.current().executeScript("PF('dialogCbo').show()");
    }

    public void salva() {
        if (cbo == null) {
            FacesUtils.showError("É necessário informar os dados do CBO.");
            return;
        }
        try {
            cbosService.salvaCbo(cbo);
            cbos = cbosService.buscaCbos();
            PrimeFaces.current().executeScript("PF('dialogCbo').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar os dados.");
        }
    }

    public List<Cbo> getCbos() {
        return cbos;
    }

    public void setCbos(List<Cbo> cbos) {
        this.cbos = cbos;
    }

    public Cbo getCbo() {
        return cbo;
    }

    public void setCbo(Cbo cbo) {
        this.cbo = cbo;
    }
}

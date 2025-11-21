package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.Cbo;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CbosController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private List<Cbo> cbos = new ArrayList<>();
    private Cbo cbo;

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
        LOGGER.severe("DBG Antes: " + cbos.size());
        cbos.add(cbo);
        LOGGER.severe("DBG Depois: " + cbos.size());
        FacesUtils.showInfo("Dados salvos com sucesso!");
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

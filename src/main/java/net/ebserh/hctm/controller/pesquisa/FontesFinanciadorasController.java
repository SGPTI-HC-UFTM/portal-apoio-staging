package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
import net.ebserh.hctm.model.pesquisa.FonteFinanciadora;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FontesFinanciadorasController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private List<FonteFinanciadora> fontes = new ArrayList<>();

    private FonteFinanciadora fonteFinanciadora = new FonteFinanciadora();

    public void openDialogNova() {
        fonteFinanciadora = new FonteFinanciadora();
        PrimeFaces.current().executeScript("PF('dialogFonteFinanciadora').show()");
    }

    public void editaFonte(FonteFinanciadora fonteFinanciadora) {
        if (fonteFinanciadora == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.fonteFinanciadora = fonteFinanciadora;
        PrimeFaces.current().executeScript("PF('dialogFonteFinanciadora').show()");
    }

    public void salva() {
        LOGGER.severe("DBG Antes: " + fontes.size());
        fontes.add(fonteFinanciadora);
        LOGGER.severe("DBG Depois: " + fontes.size());
        PrimeFaces.current().executeScript("PF('dialogFonteFinanciadora').hide()");
        FacesUtils.showInfo("Dados salvos com sucesso!");
    }

    public List<FonteFinanciadora> getFontes() {
        return fontes;
    }

    public void setFontes(List<FonteFinanciadora> fontes) {
        this.fontes = fontes;
    }

    public FonteFinanciadora getFonteFinanciadora() {
        return fonteFinanciadora;
    }

    public void setFonteFinanciadora(FonteFinanciadora fonteFinanciadora) {
        this.fonteFinanciadora = fonteFinanciadora;
    }

}

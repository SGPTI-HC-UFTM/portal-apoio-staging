package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.FonteFinanciadora;
import net.ebserh.hctm.service.pesquisa.FontesFinanciadorasService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FontesFinanciadorasController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private FontesFinanciadorasService fontesFinanciadorasService;

    private List<FonteFinanciadora> fontes = new ArrayList<>();

    private FonteFinanciadora fonteFinanciadora = new FonteFinanciadora();

    @PostConstruct
    public void init() {
        try {
            fontes = fontesFinanciadorasService.buscaFontes();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

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
        if (Objects.isNull(fonteFinanciadora)) {
            FacesUtils.showError("É necessário informar os dados da fonte financiadora.");
            return;
        }

        try {
            fontesFinanciadorasService.salvaFonteFinanciadora(fonteFinanciadora);
            fontes = fontesFinanciadorasService.buscaFontes();
            PrimeFaces.current().executeScript("PF('dialogFonteFinanciadora').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar os dados.");
        }
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

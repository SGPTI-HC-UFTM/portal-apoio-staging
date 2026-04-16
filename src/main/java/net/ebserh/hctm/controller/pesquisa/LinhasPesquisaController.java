package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.LinhaPesquisa;
import net.ebserh.hctm.service.pesquisa.LinhasPesquisaService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class LinhasPesquisaController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private LinhasPesquisaService linhasPesquisaService;

    private List<LinhaPesquisa> linhasPesquisa = new ArrayList<>();

    private LinhaPesquisa linhaPesquisa;

    @PostConstruct
    public void init() {
        try {
            linhasPesquisa = linhasPesquisaService.buscaLinhasPesquisa();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNova() {
        linhaPesquisa = new LinhaPesquisa();
        PrimeFaces.current().executeScript("PF('dialogLinhaPesquisa').show()");
    }

    public void editaLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
        if (linhaPesquisa == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.linhaPesquisa = linhaPesquisa;
        PrimeFaces.current().executeScript("PF('dialogLinhaPesquisa').show()");
    }

    public void salva() {
        if (linhaPesquisa == null) {
            FacesUtils.showError("É necessário informar os dados da linha de pesquisa.");
            return;
        }

        try {
            linhasPesquisaService.salvaLinhaPesquisa(linhaPesquisa);
            linhasPesquisa = linhasPesquisaService.buscaLinhasPesquisa();
            PrimeFaces.current().executeScript("PF('dialogLinhaPesquisa').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e,  "Ocorreu um erro ao salvar a linha de pesquisa.");
        }
    }
    /* 
    public List<LinhaPesquisa> completaLinhaPesquisa(String query) {
        return linhasPesquisa.stream()
                .filter(l -> l.getDescricao().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    */
    public List<LinhaPesquisa> getLinhasPesquisa() {
        return linhasPesquisa;
    }

    public void setLinhasPesquisa(List<LinhaPesquisa> linhasPesquisa) {
        this.linhasPesquisa = linhasPesquisa;
    }

    public LinhaPesquisa getLinhaPesquisa() {
        return linhaPesquisa;
    }

    public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
        this.linhaPesquisa = linhaPesquisa;
    }

}

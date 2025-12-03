package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Positive;
import net.ebserh.hctm.model.pesquisa.OrgaoFinanciador;
import net.ebserh.hctm.service.pesquisa.OrgaosFinanciadoresService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class OrgaosFinanciadoresController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private OrgaosFinanciadoresService orgaosFinanciadoresService;
    private List<OrgaoFinanciador> orgaos = new ArrayList<>();
    private OrgaoFinanciador orgaoFinanciador;

    @PostConstruct
    public void init() {
        try {
            orgaos = orgaosFinanciadoresService.buscaOrgaos();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNova() {
        orgaoFinanciador = new OrgaoFinanciador();
        PrimeFaces.current().executeScript("PF('dialogOrgaoFinanciador').show()");
    }

    public void editaOrgao(OrgaoFinanciador orgaoFinanciador) {
        if (orgaoFinanciador == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.orgaoFinanciador = orgaoFinanciador;
        PrimeFaces.current().executeScript("PF('dialogOrgaoFinanciador').show()");
    }

    public void salva() {
        if (orgaoFinanciador == null) {
            FacesUtils.showError("É necessário informar os dados da bolsa!");
        }

        try {
            orgaosFinanciadoresService.salvaOrgaoFinanciador(orgaoFinanciador);
            orgaos = orgaosFinanciadoresService.buscaOrgaos();
            PrimeFaces.current().executeScript("PF('dialogOrgaoFinanciador').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar os dados!");
        }
    }

    public List<OrgaoFinanciador> getOrgaos() {
        return orgaos;
    }

    public void setOrgaos(List<OrgaoFinanciador> orgaos) {
        this.orgaos = orgaos;
    }

    public OrgaoFinanciador getOrgaoFinanciador() {
        return orgaoFinanciador;
    }

    public void setOrgaoFinanciador(OrgaoFinanciador orgaoFinanciador) {
        this.orgaoFinanciador = orgaoFinanciador;
    }
}

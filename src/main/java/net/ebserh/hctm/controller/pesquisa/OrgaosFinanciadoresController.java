package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.OrgaoFinanciador;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class OrgaosFinanciadoresController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private List<OrgaoFinanciador> orgaos = new ArrayList<>();

    private OrgaoFinanciador orgaoFinanciador = new OrgaoFinanciador();

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
        LOGGER.severe("DBG Antes: " + orgaos.size());
        orgaos.add(orgaoFinanciador);
        LOGGER.severe("DBG Depois: " + orgaos.size());
        PrimeFaces.current().executeScript("PF('dialogOrgaoFinanciador').hide()");
        FacesUtils.showInfo("Dados salvos com sucesso!");
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

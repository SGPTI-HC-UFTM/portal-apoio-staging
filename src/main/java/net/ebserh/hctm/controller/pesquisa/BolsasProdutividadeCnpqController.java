package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
//import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class BolsasProdutividadeCnpqController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    //@Inject
    //private PesquisasService pesquisasService;

    private List<BolsaProdutividadeCnpq> bolsas = new ArrayList<>();

    private BolsaProdutividadeCnpq bolsaProdutividadeCnpq;

    /*
    @PostConstruct
    public void init() {
        try {
            bolsas = pesquisasService.buscaBolsas();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    */

    public void openDialogNova() {
        bolsaProdutividadeCnpq = new BolsaProdutividadeCnpq();
        PrimeFaces.current().executeScript("PF('dialogBolsaProdutividade').show()");
    }

    public void editaBolsa(BolsaProdutividadeCnpq bolsaProdutividadeCnpq) {
        if (bolsaProdutividadeCnpq == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.bolsaProdutividadeCnpq = bolsaProdutividadeCnpq;
        PrimeFaces.current().executeScript("PF('dialogBolsaProdutividade').show()");
    }

    public void salva() {
        LOGGER.severe("DBG Antes: " + bolsas.size());
        bolsas.add(bolsaProdutividadeCnpq);
        LOGGER.severe("DBG Depois: " + bolsas.size());
        FacesUtils.showInfo("Dados salvos com sucesso!");
    /*
        if (bolsaProdutividadeCnpq == null) {
            FacesUtils.showError("É necessário informar os dados da bolsa.");
            return;
        }

        try {
            pesquisasService.salvaBolsaProdutividade(bolsaProdutividadeCnpq);
            bolsas = pesquisasService.buscaBolsas();
            PrimeFaces.current().executeScript("PF('dialogBolsaProdutividade').hide()");
            FacesUtils.showInfo("Bolsa salva com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, logger, "Ocorreu um erro ao salvar a bolsa.");
        }
    */
    }

    public List<BolsaProdutividadeCnpq> getBolsas() {
        return bolsas;
    }

    public void setBolsas(List<BolsaProdutividadeCnpq> bolsas) {
        this.bolsas = bolsas;
    }

    public BolsaProdutividadeCnpq getBolsaProdutividadeCnpq() {
        return bolsaProdutividadeCnpq;
    }

    public void setBolsaProdutividadeCnpq(BolsaProdutividadeCnpq bolsaProdutividadeCnpq) {
        this.bolsaProdutividadeCnpq = bolsaProdutividadeCnpq;
    }


}

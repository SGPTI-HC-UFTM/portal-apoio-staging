package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
//import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.service.pesquisa.BolsasProdutividadeService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class BolsasProdutividadeCnpqController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private BolsasProdutividadeService bolsasProdutividadeService;

    private List<BolsaProdutividadeCnpq> bolsas = new ArrayList<>();

    private Boolean adicionar = true;

    private BolsaProdutividadeCnpq bolsaProdutividadeCnpq;

    @PostConstruct
    public void init() {
        try {
            bolsas = bolsasProdutividadeService.buscaBolsas();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNova() {
        bolsaProdutividadeCnpq = new BolsaProdutividadeCnpq();
        PrimeFaces.current().executeScript("PF('dialogBolsaProdutividade').show()");
    }

    public void editaBolsa(BolsaProdutividadeCnpq bolsaProdutividadeCnpq) {
        if (bolsaProdutividadeCnpq == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }
        adicionar = false;
        this.bolsaProdutividadeCnpq = bolsaProdutividadeCnpq;
        PrimeFaces.current().executeScript("PF('dialogBolsaProdutividade').show()");
    }

    public void salva() {
        if (bolsaProdutividadeCnpq == null) {
            FacesUtils.showError("É necessário informar os dados da bolsa.");
            return;
        }

        try {
            bolsasProdutividadeService.salvaBolsaProdutividade(bolsaProdutividadeCnpq);
            bolsas = bolsasProdutividadeService.buscaBolsas();
            PrimeFaces.current().executeScript("PF('dialogBolsaProdutividade').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar os dados.");
        }
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

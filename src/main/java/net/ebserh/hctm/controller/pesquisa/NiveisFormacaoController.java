package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
//import net.ebserh.hctm.model.pesquisa.NivelFormacao;
//import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class NiveisFormacaoController implements Serializable {

    /*
    @Inject
    private Logger logger;

    @Inject
    private PesquisasService pesquisasService;

    private List<NivelFormacao> niveisFormacao = new ArrayList<>();

    private NivelFormacao nivelFormacao;

    @PostConstruct
    public void init() {
        try {
            niveisFormacao = pesquisasService.buscaNiveisFormacao();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNovo() {
        nivelFormacao = new NivelFormacao();
        PrimeFaces.current().executeScript("PF('dialogNivelFormacao').show()");
    }

    public void editaNivelFormacao(NivelFormacao nivelFormacao) {
        if (nivelFormacao == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.nivelFormacao = nivelFormacao;
        PrimeFaces.current().executeScript("PF('dialogNivelFormacao').show()");
    }

    public void salva() {
        if (nivelFormacao == null) {
            FacesUtils.showError("É necessário informar os dados do nível de formação.");
            return;
        }

        try {
            pesquisasService.salvaNivelFormacao(nivelFormacao);
            niveisFormacao = pesquisasService.buscaNiveisFormacao();
            PrimeFaces.current().executeScript("PF('dialogNivelFormacao').hide()");
            FacesUtils.showInfo("Linha de formação salva com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, logger, "Ocorreu um erro ao salvar o nível de formação.");
        }
    }

    public List<NivelFormacao> completaNivelFormacao(String query) {
        return niveisFormacao.stream()
                .filter(n -> n.getNivel().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<NivelFormacao> getNiveisFormacao() {
        return niveisFormacao;
    }

    public void setNiveisFormacao(List<NivelFormacao> niveisFormacao) {
        this.niveisFormacao = niveisFormacao;
    }

    public NivelFormacao getNivelFormacao() {
        return nivelFormacao;
    }

    public void setNivelFormacao(NivelFormacao nivelFormacao) {
        this.nivelFormacao = nivelFormacao;
    }

     */

}

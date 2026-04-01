package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
//import net.ebserh.hctm.model.pesquisa.NivelFormacao;
//import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.model.pesquisa.NivelFormacao;
import net.ebserh.hctm.service.pesquisa.NiveisFormacaoService;
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

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private NiveisFormacaoService niveisFormacaoService;
    private List<NivelFormacao> niveis = new ArrayList<>();
    private NivelFormacao nivelFormacao;

    @PostConstruct
    public void init() {
        try {
            niveis = niveisFormacaoService.buscaNiveis();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNovo() {
        nivelFormacao = new NivelFormacao();
        PrimeFaces.current().executeScript("PF('dialogNivelFormacao').show()");
    }

    public void editaNivel(NivelFormacao nivelFormacao) {
        if (nivelFormacao == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição!");
            return;
        }
        this.nivelFormacao = nivelFormacao;
        PrimeFaces.current().executeScript("PF('dialogNivelFormacao').show()");
    }

    public void salva() {
        if (nivelFormacao == null) {
            FacesUtils.showError("É necessário informar a descrição do nível de formação!");
            return;
        }
        try {
            niveisFormacaoService.salvaNivelFormacao(nivelFormacao);
            niveis = niveisFormacaoService.buscaNiveis();
            PrimeFaces.current().executeScript("PF('dialogNivelFormacao').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar os dados!");
        }
    }

    public List<NivelFormacao> completaNivelFormacao(String query) {
        return niveis.stream()
                .filter(n -> n.getNivel().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<NivelFormacao> getNiveis() {
        return niveis;
    }

    public void setNiveis(List<NivelFormacao> niveis) {
        this.niveis = niveis;
    }

    public NivelFormacao getNivelFormacao() {
        return nivelFormacao;
    }

    public void setNivelFormacao(NivelFormacao nivelFormacao) {
        this.nivelFormacao = nivelFormacao;
    }
}

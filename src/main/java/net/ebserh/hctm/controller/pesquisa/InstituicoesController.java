package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
//import net.ebserh.hctm.model.pesquisa.Instituicao;
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
public class InstituicoesController implements Serializable {

    /*
    @Inject
    private Logger logger;

    @Inject
    private PesquisasService pesquisasService;

    private List<Instituicao> instituicoes = new ArrayList<>();

    private Instituicao instituicao;

    @PostConstruct
    public void init() {
        try {
            instituicoes = pesquisasService.buscaInstituicoes();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNova() {
        instituicao = new Instituicao();
        PrimeFaces.current().executeScript("PF('dialogInstituicao').show()");
    }

    public void editaInstituicao(Instituicao instituicao) {
        if (instituicao == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.instituicao = instituicao;
        PrimeFaces.current().executeScript("PF('dialogInstituicao').show()");
    }

    public void salva() {
        if (instituicao == null) {
            FacesUtils.showError("É necessário informar os dados da instituição.");
            return;
        }

        try {
            pesquisasService.salvaInstituicao(instituicao);
            instituicoes = pesquisasService.buscaInstituicoes();
            PrimeFaces.current().executeScript("PF('dialogInstituicao').hide()");
            FacesUtils.showInfo("Instituição salva com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, logger, "Ocorreu um erro ao salvar a instituição.");
        }
    }

    public List<Instituicao> completaInstituicao(String query) {
        return instituicoes.stream()
                .filter(i -> i.getNome().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

     */

}

package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.Instituicao;
import net.ebserh.hctm.service.pesquisa.InstituicoesService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class InstituicoesController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private InstituicoesService instituicoesService;

    private Instituicao instituicao;

    private List<Instituicao> instituicoes = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            instituicoes = instituicoesService.buscaInstituicoes();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
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
            instituicoesService.salvaInstituicao(instituicao);
            instituicoes = instituicoesService.buscaInstituicoes();
            PrimeFaces.current().executeScript("PF('dialogInstituicao').hide()");
            FacesUtils.showInfo("Instituição salva com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar a instituição.");
        }
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }
}

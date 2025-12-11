package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
//import net.ebserh.hctm.model.pesquisa.FormacaoAcademica;
//import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.model.pesquisa.FormacaoAcademica;
import net.ebserh.hctm.service.pesquisa.FormacoesAcademicasService;
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
public class FormacoesAcademicasController implements Serializable {

    private static final Logger logger = Logger.getAnonymousLogger();

    @Inject
    private FormacoesAcademicasService formacoesAcademicasService;

    private List<FormacaoAcademica> formacoes = new ArrayList<>();

    private FormacaoAcademica formacaoAcademica;

    @PostConstruct
    public void init() {
        try {
            formacoes = formacoesAcademicasService.buscaFormacoes();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNova() {
        formacaoAcademica = new FormacaoAcademica();
        PrimeFaces.current().executeScript("PF('dialogFormacaoAcademica').show()");
    }

    public void editaFormacao(FormacaoAcademica formacaoAcademica) {
        if (formacaoAcademica == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.formacaoAcademica = formacaoAcademica;
        PrimeFaces.current().executeScript("PF('dialogFormacaoAcademica').show()");
    }

    public void salva() {
        if (formacaoAcademica == null) {
            FacesUtils.showError("É necessário informar os dados da formação.");
            return;
        }

        try {
            formacoesAcademicasService.salvaFormacaoAcademica(formacaoAcademica);
            formacoes = formacoesAcademicasService.buscaFormacoes();
            PrimeFaces.current().executeScript("PF('dialogFormacaoAcademica').hide()");
            FacesUtils.showInfo("Formação salva com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar a formação.");
        }
    }

    public List<FormacaoAcademica> completaFormacaoAcademica(String query) {
        return formacoes.stream()
                .filter(f -> f.getNome().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<FormacaoAcademica> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<FormacaoAcademica> formacoes) {
        this.formacoes = formacoes;
    }

    public FormacaoAcademica getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

}

package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
//import net.ebserh.hctm.model.pesquisa.ProgramaPosGraduacao;
//import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class ProgramasPosGraduacaoController implements Serializable {

    /*

    @Inject
    private Logger logger;

    @Inject
    private PesquisasService pesquisasService;

    private List<ProgramaPosGraduacao> programasPosGraduacao = new ArrayList<>();

    private ProgramaPosGraduacao programaPosGraduacao;

    @PostConstruct
    public void init() {
        try {
            programasPosGraduacao = pesquisasService.buscaProgramasPosGraduacao();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNovo() {
        programaPosGraduacao = new ProgramaPosGraduacao();
        PrimeFaces.current().executeScript("PF('dialogProgramaPosGraduacao').show()");
    }

    public void editaProgramaPosGraduacao(ProgramaPosGraduacao programaPosGraduacao) {
        if (programaPosGraduacao == null) {
            FacesUtils.showError("É necessário selecionar um registro para edição.");
            return;
        }

        this.programaPosGraduacao = programaPosGraduacao;
        PrimeFaces.current().executeScript("PF('dialogProgramaPosGraduacao').show()");
    }

    public void salva() {
        if (programaPosGraduacao == null) {
            FacesUtils.showError("É necessário informar os dados do nível de formação.");
            return;
        }

        try {
            pesquisasService.salvaProgramaPosGraduacao(programaPosGraduacao);
            programasPosGraduacao = pesquisasService.buscaProgramasPosGraduacao();
            PrimeFaces.current().executeScript("PF('dialogProgramaPosGraduacao').hide()");
            FacesUtils.showInfo("Programa de pós graduação salvo com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, logger, "Ocorreu um erro ao salvar o programa de pós graduação.");
        }
    }

    public List<ProgramaPosGraduacao> getProgramasPosGraduacao() {
        return programasPosGraduacao;
    }

    public void setProgramasPosGraduacao(List<ProgramaPosGraduacao> programasPosGraduacao) {
        this.programasPosGraduacao = programasPosGraduacao;
    }

    public ProgramaPosGraduacao getProgramaPosGraduacao() {
        return programaPosGraduacao;
    }

    public void setProgramaPosGraduacao(ProgramaPosGraduacao programaPosGraduacao) {
        this.programaPosGraduacao = programaPosGraduacao;
    }

     */

}

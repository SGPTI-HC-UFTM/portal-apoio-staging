package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.FonteFinanciadora;
import net.ebserh.hctm.model.pesquisa.Projeto;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.model.pesquisa.TipoProjeto;
import net.ebserh.hctm.service.pesquisa.ProjetosService;
import net.ebserh.hctm.util.FacesUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class ProjetosController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private ProjetosService projetosService;

    private String titulo;

    private List<Projeto> projetos;

    private Projeto projeto;

    private List<TipoProjeto> tipos = new ArrayList<>();

    private List<StatusProjeto> status = new ArrayList<>();

    private List<FonteFinanciadora> fontesFinanciadoras = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            tipos = projetosService.buscaTiposProjeto();
            status = projetosService.buscaStatusProjeto();
            fontesFinanciadoras = projetosService.buscaFontesFinanciadoras();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNovo() {
        projeto = new Projeto();
        PrimeFaces.current().executeScript("PF('dialogProjeto').show()");
    }

    public void edita(Projeto projeto) {
        if (projeto == null) {
            FacesUtils.showError("É necessário selecionar um pesquisador.");
            return;
        }

        this.projeto = projeto;
        PrimeFaces.current().executeScript("PF('dialogProjeto').show()");
    }

    public void salva() {
        if (projeto == null) {
            FacesUtils.showError("É necessário informar os dados do pesquisador.");
            return;
        }

        try {
            projetosService.salvaProjeto(projeto);
            PrimeFaces.current().executeScript("PF('dialogProjeto').hide()");
            FacesUtils.showInfo("Projeto salvo com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar o projeto.");
        }
    }

    public void pesquisa() {
        if (StringUtils.isBlank(titulo)) {
            FacesUtils.showError("É necessário informar o título para pesquisa.");
            return;
        }

        try {
            projetos = projetosService.buscaProjetosPorTitulo(titulo);
            if(projetos.isEmpty())
                FacesUtils.showError("Nenhum projeto encontrado com os critérios informados.");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao pesquisar os projetos.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<TipoProjeto> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoProjeto> tipos) {
        this.tipos = tipos;
    }

    public List<StatusProjeto> getStatus() {
        return status;
    }

    public void setStatus(List<StatusProjeto> status) {
        this.status = status;
    }

    public List<FonteFinanciadora> getFontesFinanciadoras() {
        return fontesFinanciadoras;
    }

    public void setFontesFinanciadoras(List<FonteFinanciadora> fontesFinanciadoras) {
        this.fontesFinanciadoras = fontesFinanciadoras;
    }

}

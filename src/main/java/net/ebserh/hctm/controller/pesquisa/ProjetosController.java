package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.*;
import net.ebserh.hctm.service.pesquisa.FontesFinanciadorasService;
import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.service.pesquisa.ProjetosService;
import net.ebserh.hctm.service.pesquisa.StatusProjetoService;
import net.ebserh.hctm.util.FacesUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class ProjetosController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private ProjetosService projetosService;

    @Inject
    private StatusProjetoService statusProjetoService;

    @Inject
    private FontesFinanciadorasService fontesFinanciadorasService;

    @Inject
    private PesquisasService pesquisasService;

    private String titulo;

    private List<Projeto> projetos;

    private Projeto projeto = new Projeto();

    private List<TipoProjeto> tipos = new ArrayList<>();

    private List<StatusProjeto> status = new ArrayList<>();

    private List<FonteFinanciadora> fontesFinanciadoras = new ArrayList<>();

    private String nomePesquisador;

    private List<ProjetoPesquisador> pesquisadores = new ArrayList<>();

    private List<ProjetoPesquisador> equipe = new ArrayList<>();

    private String funcao;

    @PostConstruct
    public void init() {
        try {
            tipos = projetosService.buscaTiposProjeto();
            status = statusProjetoService.buscaStatusProjetos();
            fontesFinanciadoras = fontesFinanciadorasService.buscaFontes();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNovo() {
        projeto = new Projeto();
        nomePesquisador = null;
        pesquisadores = new ArrayList<>();
        equipe = new ArrayList<>();
        PrimeFaces.current().executeScript("PF('dialogProjeto').show()");
    }

    public void edita(Projeto projeto) {
        if (projeto == null) {
            FacesUtils.showError("É necessário selecionar um projeto.");
            return;
        }

        try {
            this.projeto = projeto;
            nomePesquisador = null;
            pesquisadores = new ArrayList<>();
            equipe = projetosService.buscaEquipe(projeto);
            PrimeFaces.current().executeScript("PF('dialogProjeto').show()");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao carregar os dados da equipe.");
        }
    }

    public void salva() {
        if (projeto == null) {
            FacesUtils.showError("É necessário informar os dados do projeto.");
            return;
        }

        if (Objects.isNull(equipe) || equipe.isEmpty()) {
            FacesUtils.showError("É necessário informar os dados da equipe.");
            return;
        }

        try {
            projetosService.salvaProjeto(projeto, equipe);
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

    public void buscaPesquisador() {
        if (StringUtils.isBlank(nomePesquisador)) {
            FacesUtils.showError("É necessário informar o nome para pesquisa.");
            return;
        }

        try {
            pesquisadores = new ArrayList<>();
            List<Pesquisador> listaPesquisadores = pesquisasService.buscaPesquisadoresPorNome(nomePesquisador);
            if (listaPesquisadores.isEmpty()) {
                FacesUtils.showError("Nenhum pesquisador encontrado com os critérios informados.");
                return;
            }

            for (Pesquisador p : listaPesquisadores) {
                ProjetoPesquisador pp = new ProjetoPesquisador();
                pp.setPesquisador(p);
                pp.setProjeto(projeto);

                pesquisadores.add(pp);
            }
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao buscar os pesquisadores.");
        }
    }

    public void acrescentaPesquisador(ProjetoPesquisador projetoPesquisador) {
        if (Objects.isNull(projetoPesquisador)) {
            FacesUtils.showError("É necessário selecionar um pesquisador.");
            return;
        }

        try {
            equipe.add(projetoPesquisador);
            FacesUtils.showInfo("Pesquisador incluído com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao incluir o pesquisador.");
        }
    }

    public void removePesquisador(ProjetoPesquisador projetoPesquisador) {
        if (Objects.isNull(projetoPesquisador))
            return;

        for (int i = 0; i < equipe.size(); ++i) {
            ProjetoPesquisador pp = equipe.get(i);
            if (pp.getPesquisador().equals(projetoPesquisador.getPesquisador()) &&
                    pp.getFuncao().equals(projetoPesquisador.getFuncao())) {
                equipe.remove(i);
                return;
            }
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

    public String getNomePesquisador() {
        return nomePesquisador;
    }

    public void setNomePesquisador(String nomePesquisador) {
        this.nomePesquisador = nomePesquisador;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<ProjetoPesquisador> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<ProjetoPesquisador> equipe) {
        this.equipe = equipe;
    }

    public List<ProjetoPesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<ProjetoPesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

}

package net.ebserh.hctm.controller.pesquisa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.*;
import net.ebserh.hctm.service.pesquisa.*;
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
public class EstudosClinicosController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private EstudosClinicosService estudosClinicosService;

    @Inject
    private PesquisasService pesquisasService;

    private String titulo;

    private List<EstudoClinico> estudos;

    private EstudoClinico estudoClinico = new EstudoClinico();

    private List<StatusEstudoClinico> status = new ArrayList<>();

    private String nomePesquisador;

    private List<Pesquisador> pesquisadores = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            status = estudosClinicosService.buscaStatusEstudoClinico();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNovo() {
        estudoClinico = new EstudoClinico();
        nomePesquisador = null;
        pesquisadores = new ArrayList<>();
        PrimeFaces.current().executeScript("PF('dialogEstudoClinico').show()");
    }

    public void edita(EstudoClinico estudo) {
        if (estudo == null) {
            FacesUtils.showError("É necessário selecionar um estudo.");
            return;
        }

        try {
            this.estudoClinico = estudo;
            nomePesquisador = null;
            pesquisadores = new ArrayList<>();
            PrimeFaces.current().executeScript("PF('dialogEstudoClinico').show()");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao carregar os dados do estudo clínico.");
        }
    }

    public void salva() {
        if (Objects.isNull(estudoClinico)) {
            FacesUtils.showError("É necessário informar os dados do estudo clínico.");
            return;
        }

        try {
            estudosClinicosService.salvaEstudoClinico(estudoClinico);
            PrimeFaces.current().executeScript("PF('dialogEstudoClinico').hide()");
            FacesUtils.showInfo("Estudo clínico salvo com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar o estudo clínico.");
        }
    }

    public void pesquisa() {
        if (StringUtils.isBlank(titulo)) {
            FacesUtils.showError("É necessário informar o título para pesquisa.");
            return;
        }

        try {
            estudos = estudosClinicosService.buscaEstudosPorTitulo(titulo);
            if(estudos.isEmpty())
                FacesUtils.showError("Nenhum estudo encontrado com o título informado.");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao pesquisar os estudos.");
        }
    }

    public void buscaPesquisador() {
        if (StringUtils.isBlank(nomePesquisador)) {
            FacesUtils.showError("É necessário informar o nome para pesquisa.");
            return;
        }

        try {
            pesquisadores = pesquisasService.buscaPesquisadoresPorNome(nomePesquisador);
            if (pesquisadores.isEmpty())
                FacesUtils.showError("Nenhum pesquisador encontrado com os critérios informados.");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao buscar os pesquisadores.");
        }
    }

    public void selecionaResponsavel(Pesquisador pesquisador) {
        if (Objects.isNull(pesquisador)) {
            FacesUtils.showError("É necessário selecionar um pesquisador.");
            return;
        }

        try {
            estudoClinico.setResponsavel(pesquisador);
            FacesUtils.showInfo("Pesquisador selecionado com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao incluir o pesquisador.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<EstudoClinico> getEstudos() {
        return estudos;
    }

    public void setEstudos(List<EstudoClinico> estudos) {
        this.estudos = estudos;
    }

    public EstudoClinico getEstudoClinico() {
        return estudoClinico;
    }

    public void setEstudoClinico(EstudoClinico estudoClinico) {
        this.estudoClinico = estudoClinico;
    }

    public List<StatusEstudoClinico> getStatus() {
        return status;
    }

    public void setStatus(List<StatusEstudoClinico> status) {
        this.status = status;
    }

    public String getNomePesquisador() {
        return nomePesquisador;
    }

    public void setNomePesquisador(String nomePesquisador) {
        this.nomePesquisador = nomePesquisador;
    }

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }
}

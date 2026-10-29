package net.ebserh.hctm.controller.pesquisa;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.Pesquisador;
import net.ebserh.hctm.model.pesquisa.ProjetoPesquisador;
import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.util.FacesUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class PesquisadoresController implements Serializable {

    @Inject
    private PesquisasService pesquisasService;

    private String nome;

    private List<Pesquisador> pesquisadores;

    private Pesquisador pesquisador;

    private List<ProjetoPesquisador> projetosPesquisador = new ArrayList<>();

    public void openDialogNovo() {
        pesquisador = new Pesquisador();
        projetosPesquisador = new ArrayList<>();
        PrimeFaces.current().executeScript("PF('dialogPesquisador').show()");
    }

    public void edita(Pesquisador pesquisador) {
        if (Objects.isNull(pesquisador)) {
            FacesUtils.showError("É necessário selecionar um pesquisador.");
            return;
        }

        this.pesquisador = pesquisador;
        buscaProjetos();
        PrimeFaces.current().executeScript("PF('dialogPesquisador').show()");
    }

    public void salva() {
        if (Objects.isNull(pesquisador)) {
            FacesUtils.showError("É necessário informar os dados do pesquisador.");
            return;
        }

        try {
            pesquisasService.salvaPesquisador(pesquisador);
            PrimeFaces.current().executeScript("PF('dialogPesquisador').hide()");
            FacesUtils.showInfo("Pesquisador salvo com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar o pesquisador.");
        }
    }

    public void pesquisa() {
        if (StringUtils.isBlank(nome)) {
            FacesUtils.showError("É necessário informar o nome para pesquisa.");
            return;
        }

        try {
            pesquisadores = pesquisasService.buscaPesquisadoresPorNome(nome);
            if(pesquisadores.isEmpty()){
                FacesUtils.showError("Nenhum pesquisador encontrado com os critérios informados.");
                return;
            }
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao pesquisar os pesquisadores.");
        }
    }

    public void buscaProjetos(){ 
        if (Objects.isNull(pesquisador)) {
            FacesUtils.showError("É necessário informar os dados do pesquisador.");
            return;
        }
        try {
            List<ProjetoPesquisador> projetos = pesquisasService.buscaProjetos(this.pesquisador.getId());
            this.projetosPesquisador = projetos;
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao buscar os projetos do pesquisador.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public List<ProjetoPesquisador> getProjetosPesquisador() {
        return projetosPesquisador;
    }

    public void setProjetosPesquisador(List<ProjetoPesquisador> projetosPesquisador) {
        this.projetosPesquisador = projetosPesquisador;
    }

}

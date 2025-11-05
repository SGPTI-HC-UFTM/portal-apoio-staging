package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
//import net.ebserh.hctm.model.pesquisa.Pesquisador;
//import net.ebserh.hctm.service.pesquisa.PesquisasService;
import net.ebserh.hctm.util.FacesUtils;
//import org.apache.commons.lang.StringUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class PesquisadoresController implements Serializable {

    /*

    @Inject
    private Logger logger;

    @Inject
    private PesquisasService pesquisasService;

    private String nome;

    private List<Pesquisador> pesquisadores;

    private Pesquisador pesquisador;

    public void openDialogNovo() {
        pesquisador = new Pesquisador();
        PrimeFaces.current().executeScript("PF('dialogPesquisador').show()");
    }

    public void edita(Pesquisador pesquisador) {
        if (pesquisador == null) {
            FacesUtils.showError("É necessário selecionar um pesquisador.");
            return;
        }

        this.pesquisador = pesquisador;
        PrimeFaces.current().executeScript("PF('dialogPesquisador').show()");
    }

    public void salva() {
        if (pesquisador == null) {
            FacesUtils.showError("É necessário informar os dados do pesquisador.");
            return;
        }

        try {
            pesquisasService.salvaPesquisador(pesquisador);
            FacesUtils.showInfo("Pesquisador salvo com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, logger, "Ocorreu um erro ao salvar o pesquisador.");
        }
    }

    public void pesquisa() {
        if (StringUtils.isBlank(nome)) {
            FacesUtils.showError("É necessário informar o nome para pesquisa.");
            return;
        }

        try {
            pesquisadores = pesquisasService.buscaPesquisadoresPorNome(nome);
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, logger, "Ocorreu um erro ao pesquisar os pesquisadores.");
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

     */

}

package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.TipoProjeto;
import net.ebserh.hctm.service.pesquisa.TiposProjetoService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class TiposProjetoController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private TiposProjetoService tiposProjetoService;

    private List<TipoProjeto> tipos = new ArrayList<>();

    private TipoProjeto tipoProjeto;
   
    @PostConstruct
    public void init(){
        try{
            tipos = tiposProjetoService.buscaTiposProjeto();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNovo(){
        tipoProjeto = new TipoProjeto();
        PrimeFaces.current().executeScript("PF('dialogTipoProjeto').show()");
    }

    public void editaTipo(TipoProjeto tipoProjeto){
        if(tipoProjeto == null){
            FacesUtils.showError("Selecione um registro para editar.");
            return;
        }
        
        this.tipoProjeto = tipoProjeto;
        PrimeFaces.current().executeScript("PF('dialogTipoProjeto').show()");
    }

    public void salvaTipo(){
        if(tipoProjeto.getDescricao() == null || tipoProjeto.getDescricao().isBlank()){
            FacesUtils.showError("Escreva uma descrição válida(1 a 100 caracteres)");
            return;
        }
      
        try{
            tiposProjetoService.salvaTipoProjeto(tipoProjeto);
            tipos = tiposProjetoService.buscaTiposProjeto();
            PrimeFaces.current().executeScript("PF('dialogTipoProjeto').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        }catch(Exception e){
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar o tipo de projeto");
        }
    }

    public void excluiTipo(TipoProjeto tipoProjeto){
        if (Objects.isNull(tipoProjeto)) {
            FacesUtils.showError("É necessário selecionar um tipo.");
            return;
        }

        try {
            tiposProjetoService.excluiTipoProjeto(tipoProjeto);
            tipos = tiposProjetoService.buscaTiposProjeto();
            FacesUtils.showInfo("Tipo de projeto excluído com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao excluir o tipo de projeto");
        }
    }

    public List<TipoProjeto> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoProjeto> tipos) {
        this.tipos = tipos;
    }

    public TipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    public void setTipoProjeto(TipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

}

package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.service.pesquisa.StatusProjetoService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class StatusProjetoController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    //private Boolean adicionar = true;
    private StatusProjetoService statusProjetoService;

    private List<StatusProjeto> status = new ArrayList<>();

    private StatusProjeto statusProjeto;
   
    @PostConstruct
    public void init(){
        try{
            status = statusProjetoService.buscaStatusProjetos();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void openDialogNova(){
        statusProjeto = new StatusProjeto();
        PrimeFaces.current().executeScript("PF('dialogStatusProjeto').show()");
    }

    public void editaStatus(StatusProjeto statusProjeto){
        if(statusProjeto == null){
            FacesUtils.showError("Selecione um registro para editar.");
            return;
        }
        
        this.statusProjeto = statusProjeto;
        //adicionar = false;
        PrimeFaces.current().executeScript("PF('dialogStatusProjeto').show()");
    }

    public void salvaStatus(){
        if(statusProjeto.getDescricao() == null || statusProjeto.getDescricao().isBlank()){
            FacesUtils.showError("Escreva uma descrição válida(1 a 100 caracteres)");
            return;
        }
      
        try{
            statusProjetoService.salvaStatus(statusProjeto);
            status = statusProjetoService.buscaStatusProjetos();
            PrimeFaces.current().executeScript("PF('dialogStatusProjeto').hide()");
            FacesUtils.showInfo("Dados salvos com sucesso!");
        }catch(Exception e){
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar os dados.");
        }
    }

    public List<StatusProjeto> getStatus() {
        return status;
    }

    public void setStatus(List<StatusProjeto> status) {
        this.status = status;
    }

    public StatusProjeto getStatusProjeto() {
        return statusProjeto;
    }

    public void setStatusProjeto(StatusProjeto statusProjeto) {
        this.statusProjeto = statusProjeto;
    }

}

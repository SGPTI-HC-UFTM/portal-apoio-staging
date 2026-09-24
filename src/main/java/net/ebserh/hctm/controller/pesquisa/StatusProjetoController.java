package net.ebserh.hctm.controller.pesquisa;

import jakarta.faces.view.ViewScoped;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.service.pesquisa.StatusProjetoService;
import net.ebserh.hctm.util.FacesUtils;
import org.primefaces.PrimeFaces;

import java.util.Objects;
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

    public void openDialogNovo(){
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
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao salvar o status de projeto");
        }
    }

    public void excluiStatus(StatusProjeto statusProjeto){
        if (Objects.isNull(statusProjeto)) {
            FacesUtils.showError("É necessário selecionar um status.");
            return;
        }

        try {
            statusProjetoService.excluiStatus(statusProjeto);
            status = statusProjetoService.buscaStatusProjetos();
            FacesUtils.showInfo("Status excluído com sucesso!");
        } catch (Exception e) {
            FacesUtils.processaExcecao(e, "Ocorreu um erro ao excluir o status.");
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

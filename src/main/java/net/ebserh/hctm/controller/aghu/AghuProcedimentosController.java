package net.ebserh.hctm.controller.aghu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.faturamento.ItemProcedimentoHospitalar;
import net.ebserh.hctm.service.aghu.faturamento.ProcedimentosService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@RequestScoped
public class AghuProcedimentosController {

    @Inject
    private Logger logger;

    @Inject
    private ProcedimentosService procedimentosService;

    private List<ItemProcedimentoHospitalar> procedimentos = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            procedimentos = procedimentosService.buscaItensProcedimentoHospitalar();
        } catch (CustomRuntimeException e) {
            FacesUtils.showError(e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            FacesUtils.showError("Ocorreu um erro ao inicializar o leito.");
        }
    }

    public List<ItemProcedimentoHospitalar> completaProcedimento(String query) {
        return procedimentos.stream()
            .filter(p -> String.valueOf(p.getCodTabela()).toLowerCase().contains(query.toLowerCase()) ||
                p.getDescricao().toLowerCase().contains(query.toLowerCase())) 
            .collect(Collectors.toList());
    }

    public List<ItemProcedimentoHospitalar> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<ItemProcedimentoHospitalar> procedimentos) {
        this.procedimentos = procedimentos;
    }
    
}

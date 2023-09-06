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
import net.ebserh.hctm.model.aghu.internacoes.Leito;
import net.ebserh.hctm.service.aghu.internacoes.LeitosService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@RequestScoped
public class AghuLeitosController {

    @Inject
    private Logger logger;

    @Inject
    private LeitosService leitosService;

    private List<Leito> leitos = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            leitos = leitosService.buscaLeitos();
        } catch (CustomRuntimeException e) {
            FacesUtils.showError(e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            FacesUtils.showError("Ocorreu um erro ao inicializar o leito.");
        }
    }

    public List<Leito> completaLeito(String query) {
        return leitos.stream()
            .filter(e -> e.getId().toLowerCase().contains(query.toLowerCase())) 
            .collect(Collectors.toList());
    }

    public List<Leito> getLeitos() {
        return leitos;
    }

    public void setLeitos(List<Leito> leitos) {
        this.leitos = leitos;
    }
    
}

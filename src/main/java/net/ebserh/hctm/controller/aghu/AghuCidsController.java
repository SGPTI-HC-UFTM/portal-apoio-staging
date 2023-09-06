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
import net.ebserh.hctm.model.aghu.Cid;
import net.ebserh.hctm.service.aghu.faturamento.CidsService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@RequestScoped
public class AghuCidsController {

    @Inject
    private Logger logger;

    @Inject
    private CidsService cidsService;

    private List<Cid> cids = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            cids = cidsService.buscaCids();
        } catch (CustomRuntimeException e) {
            FacesUtils.showError(e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            FacesUtils.showError("Ocorreu um erro ao inicializar o leito.");
        }
    }

    public List<Cid> completaCid(String query) {
        return cids.stream()
            .filter(c -> String.valueOf(c.getCodigo()).toLowerCase().contains(query.toLowerCase()) ||
                c.getDescricao().toLowerCase().contains(query.toLowerCase(null))) 
            .collect(Collectors.toList());
    }

    public List<Cid> getCids() {
        return cids;
    }

    public void setCids(List<Cid> cids) {
        this.cids = cids;
    }
    
}

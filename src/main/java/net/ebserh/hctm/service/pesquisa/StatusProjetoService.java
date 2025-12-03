package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.util.MockDb;
import java.util.List;

@Stateless
public class StatusProjetoService {

    @Inject
    private MockDb mockDb;

    public List<StatusProjeto> buscaStatusProjetos(){
        return mockDb.getStatusProjetoCadastrados();
    }

    public void salvaStatus(StatusProjeto statusProjeto){
        throw new CustomRuntimeException("Em construção...");
    }

}

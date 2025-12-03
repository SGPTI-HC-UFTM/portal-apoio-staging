package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
import net.ebserh.hctm.model.pesquisa.Cbo;
import net.ebserh.hctm.util.MockDb;

import java.util.List;

@Stateless
public class CbosService {

    @Inject
    private MockDb mockDb;

    public List<Cbo> buscaCbos() {
        return mockDb.getCbosCadastrados();
    }

    public void salvaCbo(Cbo cbo) {
        throw new CustomRuntimeException("Em construção...");
    }
}

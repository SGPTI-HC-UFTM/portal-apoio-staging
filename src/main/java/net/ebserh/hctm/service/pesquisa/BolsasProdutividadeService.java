package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
import net.ebserh.hctm.util.MockDb;

import java.util.List;

@Stateless
public class BolsasProdutividadeService {

    @Inject
    private MockDb mockDb;

    public List<BolsaProdutividadeCnpq> buscaBolsas() {
        return mockDb.getBolsasCadastradas();
    }

    public void salvaBolsaProdutividade(BolsaProdutividadeCnpq bolsaProdutividadeCnpq) {
        throw new CustomRuntimeException("Em construção...");
    }

}

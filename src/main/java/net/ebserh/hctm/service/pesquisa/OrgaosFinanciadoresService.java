package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.OrgaoFinanciador;
import net.ebserh.hctm.util.MockDb;

import java.util.Arrays;
import java.util.List;

@Stateless
public class OrgaosFinanciadoresService {

    @Inject
    private MockDb mockDb;

    public List<OrgaoFinanciador> buscaOrgaos() {
        return mockDb.getOrgaosFinanciadoresCadastrados();
    }

    public void salvaOrgaoFinanciador(OrgaoFinanciador orgaoFinanciador) {
        throw new CustomRuntimeException("Em construção...");
    }
}

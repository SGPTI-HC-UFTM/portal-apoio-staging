package net.ebserh.hctm.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
import net.ebserh.hctm.model.pesquisa.Cbo;
import net.ebserh.hctm.model.pesquisa.OrgaoFinanciador;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MockDb {

    private List<BolsaProdutividadeCnpq> bolsasCadastradas = new ArrayList<>();

    private List<Cbo> cbosCadastrados = new ArrayList<>();

    private List<OrgaoFinanciador> orgaosFinanciadoresCadastrados = new ArrayList<>();

    private List<StatusProjeto> statusProjetoCadastrados = new ArrayList<>();

    @PostConstruct
    public void init() {
        BolsaProdutividadeCnpq bolsa1 = new BolsaProdutividadeCnpq();
        bolsa1.setId(1);
        bolsa1.setDescricao("Mestrado");
        bolsasCadastradas.add(bolsa1);

        BolsaProdutividadeCnpq bolsa2 = new BolsaProdutividadeCnpq();
        bolsa2.setId(2);
        bolsa2.setDescricao("IC");
        bolsasCadastradas.add(bolsa2);

        Cbo cbo1 = new Cbo();
        cbo1.setId(1);
        cbo1.setDescricao("ANALISTA DE TI");
        cbosCadastrados.add(cbo1);

        OrgaoFinanciador o1 = new OrgaoFinanciador();
        o1.setId(1);
        o1.setDescricao("CAPES");
        orgaosFinanciadoresCadastrados.add(o1);

        StatusProjeto s1 = new StatusProjeto();
        s1.setId(1);
        s1.setDescricao("EM ANDAMENTO");
        statusProjetoCadastrados.add(s1);
    }

    public List<BolsaProdutividadeCnpq> getBolsasCadastradas() {
        return bolsasCadastradas;
    }

    public void setBolsasCadastradas(List<BolsaProdutividadeCnpq> bolsasCadastradas) {
        this.bolsasCadastradas = bolsasCadastradas;
    }

    public List<Cbo> getCbosCadastrados() {
        return cbosCadastrados;
    }

    public void setCbosCadastrados(List<Cbo> cbosCadastrados) {
        this.cbosCadastrados = cbosCadastrados;
    }

    public List<OrgaoFinanciador> getOrgaosFinanciadoresCadastrados() {
        return orgaosFinanciadoresCadastrados;
    }

    public void setOrgaosFinanciadoresCadastrados(List<OrgaoFinanciador> orgaosFinanciadoresCadastrados) {
        this.orgaosFinanciadoresCadastrados = orgaosFinanciadoresCadastrados;
    }

    public List<StatusProjeto> getStatusProjetoCadastrados() {
        return statusProjetoCadastrados;
    }

    public void setStatusProjetoCadastrados(List<StatusProjeto> statusProjetoCadastrados) {
        this.statusProjetoCadastrados = statusProjetoCadastrados;
    }

}

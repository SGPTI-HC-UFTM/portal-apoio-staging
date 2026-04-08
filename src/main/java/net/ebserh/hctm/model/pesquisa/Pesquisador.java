package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "pesquisadores")
@NamedQuery(name = "Pesquisador.findByNomeLike",
        query = "SELECT p "
                + "FROM Pesquisador p "
                + "WHERE LOWER(p.nome) LIKE :nome "
                + "ORDER BY p.nome")
public class Pesquisador  extends AbstractEntity {

    @Size(max = 100)
    private String nome;

    @Size(max = 200)
    private String lattes;

    @Size(max = 20)
    private String orcid;

    @Size(max = 20)
    private String telefone;

    private Integer ramal;

    @Size(max = 100)
    @Column(name = "e_mail")
    private String eMail;

    @Size(max = 100)
    @Column(name = "e_mail_alternativo")
    private String eMailAlternativo;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @ManyToOne
    @JoinColumn(name = "formacao_academica_id")
    private FormacaoAcademica formacaoAcademica;

    @ManyToOne
    @JoinColumn(name = "nivel_formacao_id")
    private NivelFormacao nivelFormacao;

    @ManyToOne
    @JoinColumn(name = "linha_pesquisa_id")
    private LinhaPesquisa linhaPesquisa;

    @ManyToOne
    @JoinColumn(name = "bolsa_produtividade_cnpq_id")
    private BolsaProdutividadeCnpq bolsaProdutividadeCnpq;

    @ManyToOne
    @JoinColumn(name = "programa_pos_graduacao_id")
    private ProgramaPosGraduacao programaPosGraduacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getRamal() {
        return ramal;
    }

    public void setRamal(Integer ramal) {
        this.ramal = ramal;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String geteMailAlternativo() {
        return eMailAlternativo;
    }

    public void seteMailAlternativo(String eMailAlternativo) {
        this.eMailAlternativo = eMailAlternativo;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public FormacaoAcademica getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public NivelFormacao getNivelFormacao() {
        return nivelFormacao;
    }

    public void setNivelFormacao(NivelFormacao nivelFormacao) {
        this.nivelFormacao = nivelFormacao;
    }

    public BolsaProdutividadeCnpq getBolsaProdutividadeCnpq() {
        return bolsaProdutividadeCnpq;
    }

    public void setBolsaProdutividadeCnpq(BolsaProdutividadeCnpq bolsaProdutividadeCnpq) {
        this.bolsaProdutividadeCnpq = bolsaProdutividadeCnpq;
    }

    public ProgramaPosGraduacao getProgramaPosGraduacao() {
        return programaPosGraduacao;
    }

    public void setProgramaPosGraduacao(ProgramaPosGraduacao programaPosGraduacao) {
        this.programaPosGraduacao = programaPosGraduacao;
    }

    public LinhaPesquisa getLinhaPesquisa() {
        return linhaPesquisa;
    }

    public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
        this.linhaPesquisa = linhaPesquisa;
    }

}

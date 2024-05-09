package net.ebserh.hctm.model.triagens;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.ebserh.hctm.model.aghu.internacoes.Internacao;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;
import net.ebserh.hctm.model.aghu.prescricoes.PrescricaoDietaAghu;

@Entity
@Table(schema = "triagens", name = "neonatal")
public class TriagemNeonatal {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer seq;

    @ManyToOne
    @JoinColumn(name = "pac_codigo")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "inter_codigo")
    private Internacao internacao;
    private String diagnosticos;
    private String comorbidades;
    private String idadeCron;
    private String idadeCorr;
    private String ig;
    private String alergiaIntoleranciaAlimentar;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "presc_codigo", referencedColumnName = "seq"),
        @JoinColumn(name = "atd_codigo", referencedColumnName = "atd_seq")
    })
    private PrescricaoDietaAghu prescricaoDieta;
    private String idadeGestacional;
    private String doencaCondicaoClinica;
    private String terapiaNutricional;
    private String riscoNutricional;
    private String nivelAssistenciaNutricional;
    private Integer escoreTotal;

    

    public TriagemNeonatal(Integer seq, Paciente paciente, Internacao internacao, String diagnosticos,
            String comorbidades, String idadeCron, String idadeCorr, String ig, String alergiaIntoleranciaAlimentar,
            PrescricaoDietaAghu prescricaoDieta, String idadeGestacional,
            String doencaCondicaoClinica, String terapiaNutricional, String riscoNutricional,
            String nivelAssistenciaNutricional, Integer escoreTotal) {
        this.seq = seq;
        this.paciente = paciente;
        this.internacao = internacao;
        this.diagnosticos = diagnosticos;
        this.comorbidades = comorbidades;
        this.idadeCron = idadeCron;
        this.idadeCorr = idadeCorr;
        this.ig = ig;
        this.alergiaIntoleranciaAlimentar = alergiaIntoleranciaAlimentar;
        this.prescricaoDieta = prescricaoDieta;
        this.idadeGestacional = idadeGestacional;
        this.doencaCondicaoClinica = doencaCondicaoClinica;
        this.terapiaNutricional = terapiaNutricional;
        this.riscoNutricional = riscoNutricional;
        this.nivelAssistenciaNutricional = nivelAssistenciaNutricional;
        this.escoreTotal = escoreTotal;
    }

    

    public TriagemNeonatal(Paciente paciente, Internacao internacao, String diagnosticos, String comorbidades,
            String idadeCron, String idadeCorr, String ig, String alergiaIntoleranciaAlimentar,
            PrescricaoDietaAghu prescricaoDieta, String idadeGestacional,
            String doencaCondicaoClinica, String terapiaNutricional, String riscoNutricional,
            String nivelAssistenciaNutricional, Integer escoreTotal) {
        this.paciente = paciente;
        this.internacao = internacao;
        this.diagnosticos = diagnosticos;
        this.comorbidades = comorbidades;
        this.idadeCron = idadeCron;
        this.idadeCorr = idadeCorr;
        this.ig = ig;
        this.alergiaIntoleranciaAlimentar = alergiaIntoleranciaAlimentar;
        this.prescricaoDieta = prescricaoDieta;
        this.idadeGestacional = idadeGestacional;
        this.doencaCondicaoClinica = doencaCondicaoClinica;
        this.terapiaNutricional = terapiaNutricional;
        this.riscoNutricional = riscoNutricional;
        this.nivelAssistenciaNutricional = nivelAssistenciaNutricional;
        this.escoreTotal = escoreTotal;
    }



    public Integer getSeq() {
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Internacao getInternacao() {
        return internacao;
    }
    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
    }
    public String getDiagnosticos() {
        return diagnosticos;
    }
    public void setDiagnosticos(String diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
    public String getComorbidades() {
        return comorbidades;
    }
    public void setComorbidades(String comorbidades) {
        this.comorbidades = comorbidades;
    }
    public String getIdadeCron() {
        return idadeCron;
    }
    public void setIdadeCron(String idadeCron) {
        this.idadeCron = idadeCron;
    }
    public String getIdadeCorr() {
        return idadeCorr;
    }
    public void setIdadeCorr(String idadeCorr) {
        this.idadeCorr = idadeCorr;
    }
    public String getIg() {
        return ig;
    }
    public void setIg(String ig) {
        this.ig = ig;
    }
    public String getAlergiaIntoleranciaAlimentar() {
        return alergiaIntoleranciaAlimentar;
    }
    public void setAlergiaIntoleranciaAlimentar(String alergiaIntoleranciaAlimentar) {
        this.alergiaIntoleranciaAlimentar = alergiaIntoleranciaAlimentar;
    }
    public PrescricaoDietaAghu getPrescricaoDieta() {
        return prescricaoDieta;
    }
    public void setPrescricaoDieta(PrescricaoDietaAghu prescricaoDieta) {
        this.prescricaoDieta = prescricaoDieta;
    }
    public String getIdadeGestacional() {
        return idadeGestacional;
    }
    public void setIdadeGestacional(String idadeGestacional) {
        this.idadeGestacional = idadeGestacional;
    }
    public String getDoencaCondicaoClinica() {
        return doencaCondicaoClinica;
    }
    public void setDoencaCondicaoClinica(String doencaCondicaoClinica) {
        this.doencaCondicaoClinica = doencaCondicaoClinica;
    }
    public String getTerapiaNutricional() {
        return terapiaNutricional;
    }
    public void setTerapiaNutricional(String terapiaNutricional) {
        this.terapiaNutricional = terapiaNutricional;
    }
    public String getRiscoNutricional() {
        return riscoNutricional;
    }
    public void setRiscoNutricional(String riscoNutricional) {
        this.riscoNutricional = riscoNutricional;
    }
    public String getNivelAssistenciaNutricional() {
        return nivelAssistenciaNutricional;
    }
    public void setNivelAssistenciaNutricional(String nivelAssistenciaNutricional) {
        this.nivelAssistenciaNutricional = nivelAssistenciaNutricional;
    }
    public Integer getEscoreTotal() {
        return escoreTotal;
    }
    public void setEscoreTotal(Integer escoreTotal) {
        this.escoreTotal = escoreTotal;
    }
    
    

}

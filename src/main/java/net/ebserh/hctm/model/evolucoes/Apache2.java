package net.ebserh.hctm.model.evolucoes;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "evolucoes", name = "apache2")
public class Apache2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer prontuario;

    @Column(name = "dthr_preenchimento")
    private LocalDateTime dthrPreenchimento;

    @ManyToOne
    @JoinColumn(name = "temperatura")
    private Apache2Opcao temperatura;
    
    @ManyToOne
    @JoinColumn(name = "pressao_arterial_media")
    private Apache2Opcao pressaoArterialMedia;

    @ManyToOne
    @JoinColumn(name = "frequencia_cardiaca")
    private Apache2Opcao frequenciaCardiaca;

    @ManyToOne
    @JoinColumn(name = "frequencia_respiratoria")
    private Apache2Opcao frequenciaRespiratoria;
 
    @ManyToOne
    @JoinColumn(name = "aapo2")
    private Apache2Opcao aapo2;
 
    @ManyToOne
    @JoinColumn(name = "ph")
    private Apache2Opcao ph;
 
    @ManyToOne
    @JoinColumn(name = "na_serico")
    private Apache2Opcao naSerico;
 
    @ManyToOne
    @JoinColumn(name = "k_serico")
    private Apache2Opcao kSerico;
 
    @ManyToOne
    @JoinColumn(name = "creatinina")
    private Apache2Opcao creatinina;
 
    @ManyToOne
    @JoinColumn(name = "hematocrito")
    private Apache2Opcao hematocrito;
 
    @ManyToOne
    @JoinColumn(name = "wbc")
    private Apache2Opcao wbc;
 
    @ManyToOne
    @JoinColumn(name = "glasgow")
    private Apache2Opcao glasgow;
 
    @ManyToOne
    @JoinColumn(name = "idade")
    private Apache2Opcao idade;
 
    @ManyToOne
    @JoinColumn(name = "problemas_saude_cronicos")
    private Apache2Opcao problemasSaudeCronicos;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Apache2 other = (Apache2) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Apache2 [id=" + id + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    public LocalDateTime getDthrPreenchimento() {
        return dthrPreenchimento;
    }

    public void setDthrPreenchimento(LocalDateTime dthrPreenchimento) {
        this.dthrPreenchimento = dthrPreenchimento;
    }

    public Apache2Opcao getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Apache2Opcao temperatura) {
        this.temperatura = temperatura;
    }

    public Apache2Opcao getPressaoArterialMedia() {
        return pressaoArterialMedia;
    }

    public void setPressaoArterialMedia(Apache2Opcao pressaoArterialMedia) {
        this.pressaoArterialMedia = pressaoArterialMedia;
    }

    public Apache2Opcao getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(Apache2Opcao frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public Apache2Opcao getFrequenciaRespiratoria() {
        return frequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(Apache2Opcao frequenciaRespiratoria) {
        this.frequenciaRespiratoria = frequenciaRespiratoria;
    }

    public Apache2Opcao getAapo2() {
        return aapo2;
    }

    public void setAapo2(Apache2Opcao aapo2) {
        this.aapo2 = aapo2;
    }

    public Apache2Opcao getPh() {
        return ph;
    }

    public void setPh(Apache2Opcao ph) {
        this.ph = ph;
    }

    public Apache2Opcao getNaSerico() {
        return naSerico;
    }

    public void setNaSerico(Apache2Opcao naSerico) {
        this.naSerico = naSerico;
    }

    public Apache2Opcao getkSerico() {
        return kSerico;
    }

    public void setkSerico(Apache2Opcao kSerico) {
        this.kSerico = kSerico;
    }

    public Apache2Opcao getCreatinina() {
        return creatinina;
    }

    public void setCreatinina(Apache2Opcao creatinina) {
        this.creatinina = creatinina;
    }

    public Apache2Opcao getHematocrito() {
        return hematocrito;
    }

    public void setHematocrito(Apache2Opcao hematocrito) {
        this.hematocrito = hematocrito;
    }

    public Apache2Opcao getWbc() {
        return wbc;
    }

    public void setWbc(Apache2Opcao wbc) {
        this.wbc = wbc;
    }

    public Apache2Opcao getGlasgow() {
        return glasgow;
    }

    public void setGlasgow(Apache2Opcao glasgow) {
        this.glasgow = glasgow;
    }

    public Apache2Opcao getIdade() {
        return idade;
    }

    public void setIdade(Apache2Opcao idade) {
        this.idade = idade;
    }

    public Apache2Opcao getProblemasSaudeCronicos() {
        return problemasSaudeCronicos;
    }

    public void setProblemasSaudeCronicos(Apache2Opcao problemasSaudeCronicos) {
        this.problemasSaudeCronicos = problemasSaudeCronicos;
    }
 
}

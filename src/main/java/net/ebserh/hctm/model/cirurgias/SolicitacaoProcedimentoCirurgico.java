package net.ebserh.hctm.model.cirurgias;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

import net.ebserh.hctm.model.aghu.Cid;
import net.ebserh.hctm.model.aghu.faturamento.ItemProcedimentoHospitalar;

@Entity
@Table(schema = "cirurgia", name = "solicitacoes_procedimento_cirurgico")
public class SolicitacaoProcedimentoCirurgico implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer prontuario;

    @Column(name = "local_origem_leito")
    @Size(max = 20)
    private String localOrigemLeito;

    @Column(name = "dthr_cirurgia")
    private LocalDateTime dthrCirurgia;

    @Column(name = "tempo_cirurgico_previsto")
    private LocalTime tempoCirurgiaPrevisto;

    @Column(name = "aghu_cid_seq")
    private Integer aghuCidSeq;
    
    @Column(name = "aghu_procedimento_seq")
    private Integer aghuProcedimentoSeq;
    
    @Column(name = "aghu_procedimento_pho_seq")
    private Integer aghuProcedimentoPhoSeq;
    
    private String lateralidade;

    @Column(name = "caracteristicas_cirurgia_urgencia")
    private String caracteristicasCirurgiaUrgencia;
    
    @Column(name = "caracteristicas_cirurgia_porte")
    private String caracteristicasCirurgiaPorte;

    @Column(name = "caracteristicas_cirurgia_tipo")
    private String caracteristicasCirurgiaTipo;

    @Column(name = "caracteristicas_cirurgia_limpeza")
    private String caracteristicasCirurgiaLimpeza;

    @ManyToOne
    @JoinColumn(name = "tipo_anestesia_id")
    private TipoAnestesia tipoAnestesia;
    
    @Column(name = "aghu_vin_codigo_cirurgiao")
    private Integer aghuVinCodigoCirurgiao;
    
    @Column(name = "aghu_matricula_cirurgiao")
    private Integer aghuMatriculaCirurgiao;
    
    @Column(name = "aghu_vin_codigo_auxiliar1")
    private Integer aghuVinCodigoAuxiliar1;
    
    @Column(name = "aghu_matricula_auxiliar1")
    private Integer aghuMatriculaAuxiliar1;
    
    @Column(name = "aghu_vin_codigo_auxiliar2")
    private Integer aghuVinCodigoAuxiliar2;
    
    @Column(name = "aghu_matricula_auxiliar2")
    private Integer aghuMatriculaAuxiliar2;

    @Column(name = "aghu_vin_codigo_auxiliar3")
    private Integer aghuVinCodigoAuxiliar3;
    
    @Column(name = "aghu_matricula_auxiliar3")
    private Integer aghuMatriculaAuxiliar3;
    
    @Column(name = "instrumentador_cirurgico")
    private Boolean instrumentadorCirurgico;

    @Column(name = "reserva_sangue")
    private Boolean reservaSangue;
    
    @Column(name = "reserva_leito")
    private String reservaLeito;
    
    @Column(name = "equipamentos_especificos_raio_x")
    private Boolean equipamentosEspecificosRaioX;

    @Column(name = "equipamentos_especificos_intensificador")
    private Boolean equipamentosEspecificosIntensificador;

    @Column(name = "equipamentos_especificos_torre")
    private Boolean equipamentosEspecificosTorre;

    @Column(name = "equipamentos_especificos_microscopio")
    private Boolean equipamentosEspecificosMicroscopio;

    @Column(name = "equipamentos_especificos_bisturi")
    private Boolean equipamentosEspecificosBisturi;

    @Column(name = "equipamentos_especificos_outros")
    @Size(max = 50)
    private String equipamentosEspecificosOutros;

    @Column(name = "instrumental_cirurgico_especifico")
    @Size(max = 100)
    private String instrumentalCirurgicoEspecifico;
    
    @Column(name = "material_consignado")
    @Size(max = 200)
    private String materialConsignado;

    @ManyToOne
    @JoinColumn(name = "local_origem_id")
    private LocalOrigem localOrigem;

    @ManyToOne
    @JoinColumn(name = "tipo_precaucao_id")
    private TipoPrecaucao tipoPrecaucao;

    @ManyToOne
    @JoinColumn(name = "especialidade_cirurgica_id")
    private EspecialidadeCirurgica especialidadeCirurgica;

    @Transient
    private Cid cid;

    @Transient
    private ItemProcedimentoHospitalar procedimento;

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
        SolicitacaoProcedimentoCirurgico other = (SolicitacaoProcedimentoCirurgico) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SolicitacaoProcedimentoCirurgico [id=" + id + "]";
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

    public String getLocalOrigemLeito() {
        return localOrigemLeito;
    }

    public void setLocalOrigemLeito(String localOrigemLeito) {
        this.localOrigemLeito = localOrigemLeito;
    }

    public LocalDateTime getDthrCirurgia() {
        return dthrCirurgia;
    }

    public void setDthrCirurgia(LocalDateTime dthrCirurgia) {
        this.dthrCirurgia = dthrCirurgia;
    }

    public LocalTime getTempoCirurgiaPrevisto() {
        return tempoCirurgiaPrevisto;
    }

    public void setTempoCirurgiaPrevisto(LocalTime tempoCirurgiaPrevisto) {
        this.tempoCirurgiaPrevisto = tempoCirurgiaPrevisto;
    }

    public Integer getAghuCidSeq() {
        return aghuCidSeq;
    }

    public void setAghuCidSeq(Integer aghuCidSeq) {
        this.aghuCidSeq = aghuCidSeq;
    }

    public Integer getAghuProcedimentoSeq() {
        return aghuProcedimentoSeq;
    }

    public void setAghuProcedimentoSeq(Integer aghuProcedimentoSeq) {
        this.aghuProcedimentoSeq = aghuProcedimentoSeq;
    }

    public Integer getAghuProcedimentoPhoSeq() {
        return aghuProcedimentoPhoSeq;
    }

    public void setAghuProcedimentoPhoSeq(Integer aghuProcedimentoPhoSeq) {
        this.aghuProcedimentoPhoSeq = aghuProcedimentoPhoSeq;
    }

    public String getLateralidade() {
        return lateralidade;
    }

    public void setLateralidade(String lateralidade) {
        this.lateralidade = lateralidade;
    }

    public String getCaracteristicasCirurgiaUrgencia() {
        return caracteristicasCirurgiaUrgencia;
    }

    public void setCaracteristicasCirurgiaUrgencia(String caracteristicasCirurgiaUrgencia) {
        this.caracteristicasCirurgiaUrgencia = caracteristicasCirurgiaUrgencia;
    }

    public String getCaracteristicasCirurgiaPorte() {
        return caracteristicasCirurgiaPorte;
    }

    public void setCaracteristicasCirurgiaPorte(String caracteristicasCirurgiaPorte) {
        this.caracteristicasCirurgiaPorte = caracteristicasCirurgiaPorte;
    }

    public String getCaracteristicasCirurgiaTipo() {
        return caracteristicasCirurgiaTipo;
    }

    public void setCaracteristicasCirurgiaTipo(String caracteristicasCirurgiaTipo) {
        this.caracteristicasCirurgiaTipo = caracteristicasCirurgiaTipo;
    }

    public String getCaracteristicasCirurgiaLimpeza() {
        return caracteristicasCirurgiaLimpeza;
    }

    public void setCaracteristicasCirurgiaLimpeza(String caracteristicasCirurgiaLimpeza) {
        this.caracteristicasCirurgiaLimpeza = caracteristicasCirurgiaLimpeza;
    }

    public TipoAnestesia getTipoAnestesia() {
        return tipoAnestesia;
    }

    public void setTipoAnestesia(TipoAnestesia tipoAnestesia) {
        this.tipoAnestesia = tipoAnestesia;
    }

    public Integer getAghuVinCodigoCirurgiao() {
        return aghuVinCodigoCirurgiao;
    }

    public void setAghuVinCodigoCirurgiao(Integer aghuVinCodigoCirurgiao) {
        this.aghuVinCodigoCirurgiao = aghuVinCodigoCirurgiao;
    }

    public Integer getAghuMatriculaCirurgiao() {
        return aghuMatriculaCirurgiao;
    }

    public void setAghuMatriculaCirurgiao(Integer aghuMatriculaCirurgiao) {
        this.aghuMatriculaCirurgiao = aghuMatriculaCirurgiao;
    }

    public Integer getAghuVinCodigoAuxiliar1() {
        return aghuVinCodigoAuxiliar1;
    }

    public void setAghuVinCodigoAuxiliar1(Integer aghuVinCodigoAuxiliar1) {
        this.aghuVinCodigoAuxiliar1 = aghuVinCodigoAuxiliar1;
    }

    public Integer getAghuMatriculaAuxiliar1() {
        return aghuMatriculaAuxiliar1;
    }

    public void setAghuMatriculaAuxiliar1(Integer aghuMatriculaAuxiliar1) {
        this.aghuMatriculaAuxiliar1 = aghuMatriculaAuxiliar1;
    }

    public Integer getAghuVinCodigoAuxiliar2() {
        return aghuVinCodigoAuxiliar2;
    }

    public void setAghuVinCodigoAuxiliar2(Integer aghuVinCodigoAuxiliar2) {
        this.aghuVinCodigoAuxiliar2 = aghuVinCodigoAuxiliar2;
    }

    public Integer getAghuMatriculaAuxiliar2() {
        return aghuMatriculaAuxiliar2;
    }

    public void setAghuMatriculaAuxiliar2(Integer aghuMatriculaAuxiliar2) {
        this.aghuMatriculaAuxiliar2 = aghuMatriculaAuxiliar2;
    }

    public Integer getAghuVinCodigoAuxiliar3() {
        return aghuVinCodigoAuxiliar3;
    }

    public void setAghuVinCodigoAuxiliar3(Integer aghuVinCodigoAuxiliar3) {
        this.aghuVinCodigoAuxiliar3 = aghuVinCodigoAuxiliar3;
    }

    public Integer getAghuMatriculaAuxiliar3() {
        return aghuMatriculaAuxiliar3;
    }

    public void setAghuMatriculaAuxiliar3(Integer aghuMatriculaAuxiliar3) {
        this.aghuMatriculaAuxiliar3 = aghuMatriculaAuxiliar3;
    }

    public Boolean getInstrumentadorCirurgico() {
        return instrumentadorCirurgico;
    }

    public void setInstrumentadorCirurgico(Boolean instrumentadorCirurgico) {
        this.instrumentadorCirurgico = instrumentadorCirurgico;
    }

    public Boolean getReservaSangue() {
        return reservaSangue;
    }

    public void setReservaSangue(Boolean reservaSangue) {
        this.reservaSangue = reservaSangue;
    }

    public String getReservaLeito() {
        return reservaLeito;
    }

    public void setReservaLeito(String reservaLeito) {
        this.reservaLeito = reservaLeito;
    }

    public Boolean getEquipamentosEspecificosRaioX() {
        return equipamentosEspecificosRaioX;
    }

    public void setEquipamentosEspecificosRaioX(Boolean equipamentosEspecificosRaioX) {
        this.equipamentosEspecificosRaioX = equipamentosEspecificosRaioX;
    }

    public Boolean getEquipamentosEspecificosIntensificador() {
        return equipamentosEspecificosIntensificador;
    }

    public void setEquipamentosEspecificosIntensificador(Boolean equipamentosEspecificosIntensificador) {
        this.equipamentosEspecificosIntensificador = equipamentosEspecificosIntensificador;
    }

    public Boolean getEquipamentosEspecificosTorre() {
        return equipamentosEspecificosTorre;
    }

    public void setEquipamentosEspecificosTorre(Boolean equipamentosEspecificosTorre) {
        this.equipamentosEspecificosTorre = equipamentosEspecificosTorre;
    }

    public Boolean getEquipamentosEspecificosMicroscopio() {
        return equipamentosEspecificosMicroscopio;
    }

    public void setEquipamentosEspecificosMicroscopio(Boolean equipamentosEspecificosMicroscopio) {
        this.equipamentosEspecificosMicroscopio = equipamentosEspecificosMicroscopio;
    }

    public Boolean getEquipamentosEspecificosBisturi() {
        return equipamentosEspecificosBisturi;
    }

    public void setEquipamentosEspecificosBisturi(Boolean equipamentosEspecificosBisturi) {
        this.equipamentosEspecificosBisturi = equipamentosEspecificosBisturi;
    }

    public String getEquipamentosEspecificosOutros() {
        return equipamentosEspecificosOutros;
    }

    public void setEquipamentosEspecificosOutros(String equipamentosEspecificosOutros) {
        this.equipamentosEspecificosOutros = equipamentosEspecificosOutros;
    }

    public String getInstrumentalCirurgicoEspecifico() {
        return instrumentalCirurgicoEspecifico;
    }

    public void setInstrumentalCirurgicoEspecifico(String instrumentalCirurgicoEspecifico) {
        this.instrumentalCirurgicoEspecifico = instrumentalCirurgicoEspecifico;
    }

    public String getMaterialConsignado() {
        return materialConsignado;
    }

    public void setMaterialConsignado(String materialConsignado) {
        this.materialConsignado = materialConsignado;
    }

    public LocalOrigem getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(LocalOrigem localOrigem) {
        this.localOrigem = localOrigem;
    }

    public TipoPrecaucao getTipoPrecaucao() {
        return tipoPrecaucao;
    }

    public void setTipoPrecaucao(TipoPrecaucao tipoPrecaucao) {
        this.tipoPrecaucao = tipoPrecaucao;
    }

    public EspecialidadeCirurgica getEspecialidadeCirurgica() {
        return especialidadeCirurgica;
    }

    public void setEspecialidadeCirurgica(EspecialidadeCirurgica especialidadeCirurgica) {
        this.especialidadeCirurgica = especialidadeCirurgica;
    }

    public Cid getCid() {
        return cid;
    }

    public void setCid(Cid cid) {
        this.cid = cid;
    }

    public ItemProcedimentoHospitalar getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(ItemProcedimentoHospitalar procedimento) {
        this.procedimento = procedimento;
    }

}

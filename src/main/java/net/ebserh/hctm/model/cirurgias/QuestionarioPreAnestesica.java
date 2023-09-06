package net.ebserh.hctm.model.cirurgias;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "cirurgia", name = "questionarios_pre_anestesica")
@NamedQuery(name = "QuestionarioPreAnestesica.findAll", query = "select q from QuestionarioPreAnestesica q")
public class QuestionarioPreAnestesica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "data")
    private LocalDate data;

	@Column(name = "clinica")
    private String clinica;

	@Column(name = "prontuario")
    private Integer prontuario;

	@Column(name = "nome")
    @Size(max = 255, message = "Nome deve conter no máximo 255 caracteres")
    private String nome;

	@Column(name = "idade")
    private String idade;

	@Column(name = "peso")
    private Float peso;

	@Column(name = "altura")
    private Float altura;

	@Column(name = "imc")
    private Float imc;

	@Column(name = "sexo")
    private String sexo;

	@Column(name = "cor")
    private String cor;

	@Column(name = "profissao")
    private String profissao;

	@Column(name = "diagnostico")
    @Size(max = 255, message = "Diagnóstico deve conter no máximo 255 caracteres")
    private String diagnostico;

	@Column(name = "cirurgia_proposta")
    @Size(max = 255, message = "Cirurgia proposta deve conter no máximo 255 caracteres")
    private String cirurgiaProposta;

	@Column(name = "tranquilizantes_calmantes")
    private Boolean tranquilizantesCalmantes;

	@Column(name = "problemas_neurologicos")
    private Boolean problemasNeurologicos;

	@Column(name = "avc")
    private Boolean avc;

	@Column(name = "surdez_deficit_visual")
    private Boolean surdezDeficitVisual;

	@Column(name = "doenca_cardiaca")
    private Boolean doencaCardiaca;

	@Column(name = "infarto")
    private Boolean infarto;

	@Column(name = "arritmia")
    private Boolean arritmia;

	@Column(name = "falta_ar")
    private Boolean faltaAr;

	@Column(name = "marcapasso")
    private Boolean marcapasso;

	@Column(name = "pressao_alta")
    private Boolean pressaoAlta;

	@Column(name = "cirurgia_cardiaca")
    private Boolean cirurgiaCardiaca;

	@Column(name = "met")
    private Boolean met;

    @Column(name = "valor_met")
    private Integer valorMet;

	@Column(name = "trombose")
    private Boolean trombose;

	@Column(name = "diabetes")
    private Boolean diabetes;

	@Column(name = "diabetes_tempo")
    private String diabetesTempo;

	@Column(name = "tireoide")
    private Boolean tireoide;

	@Column(name = "tireoide_tipo")
    private String tireoideTipo;

	@Column(name = "perda_peso")
    private Boolean perdaPeso;

	@Column(name = "valor_perda_peso")
    private Float valorPerdaPeso;

	@Column(name = "figado_cirrose")
    private Boolean figadoCirrose;

	@Column(name = "fumante")
    private Boolean fumante;

	@Column(name = "fumante_tempo")
    private String fumanteTempo;

	@Column(name = "fumante_quantidade_cigarros")
    private Integer fumanteQuantidadeCigarros;

	@Column(name = "infeccao_vias_aereas")
    private Boolean infeccaoViasAereas;

	@Column(name = "bronquite_asma")
    private Boolean bronquiteAsma;

	@Column(name = "alergia_drogas")
    private Boolean alergiaDrogas;

	@Column(name = "alergia_outros")
    private String alergiaOutros;

	@Column(name = "alcoolismo")
    private Boolean alcoolismo;

	@Column(name = "usa_drogas")
    private Boolean usaDrogas;

	@Column(name = "doenca_infecciosa_grave")
    private Boolean doencaInfecciosaGrave;

	@Column(name = "cirurgia_anterior")
    private Boolean cirurgiaAnterior;

	@Column(name = "cirurgia_anterior_anestesia")
    private String cirurgiaAnteriorAnestesia;

	@Column(name = "problema_anestesia")
    private Boolean problemaAnestesia;

	@Column(name = "problema_anestesia_descricao")
    @Size(max = 255, message = "Descrição do problema deve conter no máximo 255 caracteres")
    private String problemaAnestesiaDescricao;

	@Column(name = "outros_problemas")
    @Size(max = 255, message = "Campo \"Outros problemas\" deve conter no máximo 255 caracteres")
    private String outrosProblemas;

	@Column(name = "gravida")
    private Boolean gravida;

	@Column(name = "vasectomia")
    private Boolean vasectomia;

	@Column(name = "antecedentes_familiares")
    @Size(max = 255, message = "Campo \"Antecedentes familiares\" deve conter no máximo 255 caracteres")
    private String antecedentesFamiliares;

	@Column(name = "medicamentos_uso")
    @Size(max = 255, message = "Campo \"Medicamentos em uso\" deve conter no máximo 255 caracteres")
    private String medicamentosUso;

	@Column(name = "mucosa_corada")
    private Boolean mucosaCorada;

    @Column(name = "mucosa_hidratada")
    private Boolean mucosaHidratada;

    @Column(name = "mucosa_acianotica")
    private Boolean mucosaAcianotica;

    @Column(name = "mucosa_anicterica")
    private Boolean mucosaAnicterica;

    private String mucosas;

	@Column(name = "boca")
    private String boca;

	@Column(name = "dentes")
    private String dentes;

	@Column(name = "pa")
    private String pa;

	@Column(name = "fc")
    private String fc;

	@Column(name = "temperatura_afebril")
    private Boolean temperaturaAfebril;
	
    @Column(name = "temperatura_spo")
    private Boolean temperaturaSpo;

	@Column(name = "cardiovascular")
    @Size(max = 255, message = "Campo \"Outras informações cardiovasculares\" deve conter no máximo 255 caracteres")
    private String cardiovascular;

	@Column(name = "pulmoes_torax")
    @Size(max = 255, message = "Campo \"Outras informações pulmão/tórax\" deve conter no máximo 255 caracteres")
    private String pulmoesTorax;

	@Column(name = "veias")
    @Size(max = 255, message = "Campo \"Outras informações sistema venoso/arterial\" deve conter no máximo 255 caracteres")
    private String veias;

	@Column(name = "aparelho_locomotor")
    @Size(max = 255, message = "Campo \"Outras informações aparelho locomotor\" deve conter no máximo 255 caracteres")
    private String aparelhoLocomotor;

	@Column(name = "teste_mallampati")
    private String testeMallampati;

	@Column(name = "distancia_mento_tireoide")
    private String distanciaMentoTireoide;

	@Column(name = "exame_complementar_data")
    private LocalDate exameComplementarData;

	@Column(name = "hemoglobina")
    private Float hemoglobina;

	@Column(name = "hematocrito")
    private Float hematocrito;

	@Column(name = "hemaceas")
    private Float hemaceas;

	@Column(name = "vcm")
    private Float vcm;

	@Column(name = "hcm")
    private Float hcm;

	@Column(name = "rdw")
    private Float rdw;

	@Column(name = "plaquetas")
    private Float plaquetas;

	@Column(name = "leucograma")
    private String leucograma;

	@Column(name = "glicose")
    private Float glicose;

	@Column(name = "ureia")
    private Float ureia;

	@Column(name = "creatina")
    private Float creatina;

	@Column(name = "eletrolitos_na")
    private Float eletrolitosNa;

	@Column(name = "eletrolitos_k")
    private Float eletrolitosK;

	@Column(name = "eletrolitos_ca")
    private Float eletrolitosCa;

	@Column(name = "eletrolitos_ci")
    private Float eletrolitosCi;

	@Column(name = "eletrolitos_mg")
    private Float eletrolitosMg;

	@Column(name = "coagulograma_ts")
    private Float coagulogramaTs;

	@Column(name = "coagulograma_tc")
    private Float coagulogramaTc;

	@Column(name = "coagulograma_tap")
    private Float coagulogramaTap;

	@Column(name = "coagulograma_ttpa")
    private Float coagulogramaTtpa;

	@Column(name = "coagulograma_inr")
    private Float coagulogramaInr;

	@Column(name = "rx_torax")
    private String rxTorax;

	@Column(name = "ecg")
    private String ecg;

	@Column(name = "outros")
    private String outros;

	@Column(name = "parecer_cardiologico")
    private String parecerCardiologico;

	@Column(name = "conclusao")
    private String conclusao;

	@Column(name = "conclusao_obs")
    @Size(max = 255, message = "Campo \"Observações\" deve conter no máximo 255 caracteres")
    private String conclusaoObs;

	@Column(name = "retorno_data")
    private LocalDate retornoData;

	@Column(name = "retorno_obs")
    @Size(max = 255, message = "Campo \"Observações do retorno\" deve conter no máximo 255 caracteres")
    private String retornoObs;

	@Column(name = "cuidados_habituais")
    private Boolean cuidadosHabituais;

	@Column(name = "manter_medicacao")
    private Boolean manterMedicacao;

	@Column(name = "tromboprofilaxia")
    private Boolean tromboprofilaxia;

	@Column(name = "pos_uti")
    private Boolean posUti;

	@Column(name = "profilaxia_endocardite")
    private Boolean profilaxiaEndocardite;

	@Column(name = "precaucoes")
    private String precaucoes;

	@Column(name = "tecnica_anestesica_geral")
    private Boolean tecnicaAnestesicaGeral;
	
    @Column(name = "tecnica_anestesica_bloqueio")
    private Boolean tecnicaAnestesicaBloqueio;

	@Column(name = "tecnica_anestesica_qual")
    private String tecnicaAnestesicaQual;

	@Column(name = "indices_risco")
    private String indicesRisco;

    @Column(name = "complemento_info_neurologica")
    private String complementoInfoNeurologica;

    private Integer saturacao;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getProntuario() {
        return this.prontuario;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return this.idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Float getPeso() {
        return this.peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return this.altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getImc() {
        return this.imc;
    }

    public void setImc(Float imc) {
        this.imc = imc;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProfissao() {
        return this.profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getDiagnostico() {
        return this.diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getCirurgiaProposta() {
        return this.cirurgiaProposta;
    }

    public void setCirurgiaProposta(String cirurgiaProposta) {
        this.cirurgiaProposta = cirurgiaProposta;
    }

    public Boolean isTranquilizantesCalmantes() {
        return this.tranquilizantesCalmantes;
    }

    public Boolean getTranquilizantesCalmantes() {
        return this.tranquilizantesCalmantes;
    }

    public void setTranquilizantesCalmantes(Boolean tranquilizantesCalmantes) {
        this.tranquilizantesCalmantes = tranquilizantesCalmantes;
    }

    public Boolean isProblemasNeurologicos() {
        return this.problemasNeurologicos;
    }

    public Boolean getProblemasNeurologicos() {
        return this.problemasNeurologicos;
    }

    public void setProblemasNeurologicos(Boolean problemasNeurologicos) {
        this.problemasNeurologicos = problemasNeurologicos;
    }

    public Boolean isAvc() {
        return this.avc;
    }

    public Boolean getAvc() {
        return this.avc;
    }

    public void setAvc(Boolean avc) {
        this.avc = avc;
    }

    public Boolean isSurdezDeficitVisual() {
        return this.surdezDeficitVisual;
    }

    public Boolean getSurdezDeficitVisual() {
        return this.surdezDeficitVisual;
    }

    public void setSurdezDeficitVisual(Boolean surdezDeficitVisual) {
        this.surdezDeficitVisual = surdezDeficitVisual;
    }

    public Boolean isDoencaCardiaca() {
        return this.doencaCardiaca;
    }

    public Boolean getDoencaCardiaca() {
        return this.doencaCardiaca;
    }

    public void setDoencaCardiaca(Boolean doencaCardiaca) {
        this.doencaCardiaca = doencaCardiaca;
    }

    public Boolean isInfarto() {
        return this.infarto;
    }

    public Boolean getInfarto() {
        return this.infarto;
    }

    public void setInfarto(Boolean infarto) {
        this.infarto = infarto;
    }

    public Boolean isArritmia() {
        return this.arritmia;
    }

    public Boolean getArritmia() {
        return this.arritmia;
    }

    public void setArritmia(Boolean arritmia) {
        this.arritmia = arritmia;
    }

    public Boolean isFaltaAr() {
        return this.faltaAr;
    }

    public Boolean getFaltaAr() {
        return this.faltaAr;
    }

    public void setFaltaAr(Boolean faltaAr) {
        this.faltaAr = faltaAr;
    }

    public Boolean isMarcapasso() {
        return this.marcapasso;
    }

    public Boolean getMarcapasso() {
        return this.marcapasso;
    }

    public void setMarcapasso(Boolean marcapasso) {
        this.marcapasso = marcapasso;
    }

    public Boolean isPressaoAlta() {
        return this.pressaoAlta;
    }

    public Boolean getPressaoAlta() {
        return this.pressaoAlta;
    }

    public void setPressaoAlta(Boolean pressaoAlta) {
        this.pressaoAlta = pressaoAlta;
    }

    public Boolean isCirurgiaCardiaca() {
        return this.cirurgiaCardiaca;
    }

    public Boolean getCirurgiaCardiaca() {
        return this.cirurgiaCardiaca;
    }

    public void setCirurgiaCardiaca(Boolean cirurgiaCardiaca) {
        this.cirurgiaCardiaca = cirurgiaCardiaca;
    }

    public Boolean isMet() {
        return this.met;
    }

    public Boolean getMet() {
        return this.met;
    }

    public void setMet(Boolean met) {
        this.met = met;
    }

    public Boolean isTrombose() {
        return this.trombose;
    }

    public Boolean getTrombose() {
        return this.trombose;
    }

    public void setTrombose(Boolean trombose) {
        this.trombose = trombose;
    }

    public Boolean isDiabetes() {
        return this.diabetes;
    }

    public Boolean getDiabetes() {
        return this.diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public String getDiabetesTempo() {
        return this.diabetesTempo;
    }

    public void setDiabetesTempo(String diabetesTempo) {
        this.diabetesTempo = diabetesTempo;
    }

    public Boolean isTireoide() {
        return this.tireoide;
    }

    public Boolean getTireoide() {
        return this.tireoide;
    }

    public void setTireoide(Boolean tireoide) {
        this.tireoide = tireoide;
    }

    public String getTireoideTipo() {
        return this.tireoideTipo;
    }

    public void setTireoideTipo(String tireoideTipo) {
        this.tireoideTipo = tireoideTipo;
    }

    public Boolean isPerdaPeso() {
        return this.perdaPeso;
    }

    public Boolean getPerdaPeso() {
        return this.perdaPeso;
    }

    public void setPerdaPeso(Boolean perdaPeso) {
        this.perdaPeso = perdaPeso;
    }

    public Float getValorPerdaPeso() {
        return this.valorPerdaPeso;
    }

    public void setValorPerdaPeso(Float valorPerdaPeso) {
        this.valorPerdaPeso = valorPerdaPeso;
    }

    public Boolean isFigadoCirrose() {
        return this.figadoCirrose;
    }

    public Boolean getFigadoCirrose() {
        return this.figadoCirrose;
    }

    public void setFigadoCirrose(Boolean figadoCirrose) {
        this.figadoCirrose = figadoCirrose;
    }

    public Boolean isFumante() {
        return this.fumante;
    }

    public Boolean getFumante() {
        return this.fumante;
    }

    public void setFumante(Boolean fumante) {
        this.fumante = fumante;
    }

    public String getFumanteTempo() {
        return this.fumanteTempo;
    }

    public void setFumanteTempo(String fumanteTempo) {
        this.fumanteTempo = fumanteTempo;
    }

    public Integer getFumanteQuantidadeCigarros() {
        return this.fumanteQuantidadeCigarros;
    }

    public void setFumanteQuantidadeCigarros(Integer fumanteQuantidadeCigarros) {
        this.fumanteQuantidadeCigarros = fumanteQuantidadeCigarros;
    }

    public Boolean isInfeccaoViasAereas() {
        return this.infeccaoViasAereas;
    }

    public Boolean getInfeccaoViasAereas() {
        return this.infeccaoViasAereas;
    }

    public void setInfeccaoViasAereas(Boolean infeccaoViasAereas) {
        this.infeccaoViasAereas = infeccaoViasAereas;
    }

    public Boolean isBronquiteAsma() {
        return this.bronquiteAsma;
    }

    public Boolean getBronquiteAsma() {
        return this.bronquiteAsma;
    }

    public void setBronquiteAsma(Boolean bronquiteAsma) {
        this.bronquiteAsma = bronquiteAsma;
    }

    public Boolean isAlergiaDrogas() {
        return this.alergiaDrogas;
    }

    public Boolean getAlergiaDrogas() {
        return this.alergiaDrogas;
    }

    public void setAlergiaDrogas(Boolean alergiaDrogas) {
        this.alergiaDrogas = alergiaDrogas;
    }

    public String getAlergiaOutros() {
        return this.alergiaOutros;
    }

    public void setAlergiaOutros(String alergiaOutros) {
        this.alergiaOutros = alergiaOutros;
    }

    public Boolean isAlcoolismo() {
        return this.alcoolismo;
    }

    public Boolean getAlcoolismo() {
        return this.alcoolismo;
    }

    public void setAlcoolismo(Boolean alcoolismo) {
        this.alcoolismo = alcoolismo;
    }

    public Boolean isUsaDrogas() {
        return this.usaDrogas;
    }

    public Boolean getUsaDrogas() {
        return this.usaDrogas;
    }

    public void setUsaDrogas(Boolean usaDrogas) {
        this.usaDrogas = usaDrogas;
    }

    public Boolean isDoencaInfecciosaGrave() {
        return this.doencaInfecciosaGrave;
    }

    public Boolean getDoencaInfecciosaGrave() {
        return this.doencaInfecciosaGrave;
    }

    public void setDoencaInfecciosaGrave(Boolean doencaInfecciosaGrave) {
        this.doencaInfecciosaGrave = doencaInfecciosaGrave;
    }

    public Boolean isCirurgiaAnterior() {
        return this.cirurgiaAnterior;
    }

    public Boolean getCirurgiaAnterior() {
        return this.cirurgiaAnterior;
    }

    public void setCirurgiaAnterior(Boolean cirurgiaAnterior) {
        this.cirurgiaAnterior = cirurgiaAnterior;
    }

    public String getCirurgiaAnteriorAnestesia() {
        return this.cirurgiaAnteriorAnestesia;
    }

    public void setCirurgiaAnteriorAnestesia(String cirurgiaAnteriorAnestesia) {
        this.cirurgiaAnteriorAnestesia = cirurgiaAnteriorAnestesia;
    }

    public Boolean isProblemaAnestesia() {
        return this.problemaAnestesia;
    }

    public Boolean getProblemaAnestesia() {
        return this.problemaAnestesia;
    }

    public void setProblemaAnestesia(Boolean problemaAnestesia) {
        this.problemaAnestesia = problemaAnestesia;
    }

    public String getProblemaAnestesiaDescricao() {
        return this.problemaAnestesiaDescricao;
    }

    public void setProblemaAnestesiaDescricao(String problemaAnestesiaDescricao) {
        this.problemaAnestesiaDescricao = problemaAnestesiaDescricao;
    }

    public String getOutrosProblemas() {
        return this.outrosProblemas;
    }

    public void setOutrosProblemas(String outrosProblemas) {
        this.outrosProblemas = outrosProblemas;
    }

    public Boolean isGravida() {
        return this.gravida;
    }

    public Boolean getGravida() {
        return this.gravida;
    }

    public void setGravida(Boolean gravida) {
        this.gravida = gravida;
    }

    public Boolean isVasectomia() {
        return this.vasectomia;
    }

    public Boolean getVasectomia() {
        return this.vasectomia;
    }

    public void setVasectomia(Boolean vasectomia) {
        this.vasectomia = vasectomia;
    }

    public String getAntecedentesFamiliares() {
        return this.antecedentesFamiliares;
    }

    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public String getMedicamentosUso() {
        return this.medicamentosUso;
    }

    public void setMedicamentosUso(String medicamentosUso) {
        this.medicamentosUso = medicamentosUso;
    }

    public String getBoca() {
        return this.boca;
    }

    public void setBoca(String boca) {
        this.boca = boca;
    }

    public String getPa() {
        return this.pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getFc() {
        return this.fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getCardiovascular() {
        return this.cardiovascular;
    }

    public void setCardiovascular(String cardiovascular) {
        this.cardiovascular = cardiovascular;
    }

    public String getPulmoesTorax() {
        return this.pulmoesTorax;
    }

    public void setPulmoesTorax(String pulmoesTorax) {
        this.pulmoesTorax = pulmoesTorax;
    }

    public String getVeias() {
        return this.veias;
    }

    public void setVeias(String veias) {
        this.veias = veias;
    }

    public String getAparelhoLocomotor() {
        return this.aparelhoLocomotor;
    }

    public void setAparelhoLocomotor(String aparelhoLocomotor) {
        this.aparelhoLocomotor = aparelhoLocomotor;
    }

    public String getTesteMallampati() {
        return this.testeMallampati;
    }

    public void setTesteMallampati(String testeMallampati) {
        this.testeMallampati = testeMallampati;
    }

    public String getDistanciaMentoTireoide() {
        return this.distanciaMentoTireoide;
    }

    public void setDistanciaMentoTireoide(String distanciaMentoTireoide) {
        this.distanciaMentoTireoide = distanciaMentoTireoide;
    }

    public LocalDate getExameComplementarData() {
        return this.exameComplementarData;
    }

    public void setExameComplementarData(LocalDate exameComplementarData) {
        this.exameComplementarData = exameComplementarData;
    }

    public Float getHemoglobina() {
        return this.hemoglobina;
    }

    public void setHemoglobina(Float hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public Float getHematocrito() {
        return this.hematocrito;
    }

    public void setHematocrito(Float hematocrito) {
        this.hematocrito = hematocrito;
    }

    public Float getHemaceas() {
        return this.hemaceas;
    }

    public void setHemaceas(Float hemaceas) {
        this.hemaceas = hemaceas;
    }

    public Float getVcm() {
        return this.vcm;
    }

    public void setVcm(Float vcm) {
        this.vcm = vcm;
    }

    public Float getHcm() {
        return this.hcm;
    }

    public void setHcm(Float hcm) {
        this.hcm = hcm;
    }

    public Float getRdw() {
        return this.rdw;
    }

    public void setRdw(Float rdw) {
        this.rdw = rdw;
    }

    public Float getPlaquetas() {
        return this.plaquetas;
    }

    public void setPlaquetas(Float plaquetas) {
        this.plaquetas = plaquetas;
    }

    public String getLeucograma() {
        return this.leucograma;
    }

    public void setLeucograma(String leucograma) {
        this.leucograma = leucograma;
    }

    public Float getGlicose() {
        return this.glicose;
    }

    public void setGlicose(Float glicose) {
        this.glicose = glicose;
    }

    public Float getUreia() {
        return this.ureia;
    }

    public void setUreia(Float ureia) {
        this.ureia = ureia;
    }

    public Float getCreatina() {
        return this.creatina;
    }

    public void setCreatina(Float creatina) {
        this.creatina = creatina;
    }

    public Float getEletrolitosNa() {
        return this.eletrolitosNa;
    }

    public void setEletrolitosNa(Float eletrolitosNa) {
        this.eletrolitosNa = eletrolitosNa;
    }

    public Float getEletrolitosK() {
        return this.eletrolitosK;
    }

    public void setEletrolitosK(Float eletrolitosK) {
        this.eletrolitosK = eletrolitosK;
    }

    public Float getEletrolitosCa() {
        return this.eletrolitosCa;
    }

    public void setEletrolitosCa(Float eletrolitosCa) {
        this.eletrolitosCa = eletrolitosCa;
    }

    public Float getEletrolitosCi() {
        return this.eletrolitosCi;
    }

    public void setEletrolitosCi(Float eletrolitosCi) {
        this.eletrolitosCi = eletrolitosCi;
    }

    public Float getEletrolitosMg() {
        return this.eletrolitosMg;
    }

    public void setEletrolitosMg(Float eletrolitosMg) {
        this.eletrolitosMg = eletrolitosMg;
    }

    public Float getCoagulogramaTs() {
        return this.coagulogramaTs;
    }

    public void setCoagulogramaTs(Float coagulogramaTs) {
        this.coagulogramaTs = coagulogramaTs;
    }

    public Float getCoagulogramaTc() {
        return this.coagulogramaTc;
    }

    public void setCoagulogramaTc(Float coagulogramaTc) {
        this.coagulogramaTc = coagulogramaTc;
    }

    public Float getCoagulogramaTap() {
        return this.coagulogramaTap;
    }

    public void setCoagulogramaTap(Float coagulogramaTap) {
        this.coagulogramaTap = coagulogramaTap;
    }

    public Float getCoagulogramaTtpa() {
        return this.coagulogramaTtpa;
    }

    public void setCoagulogramaTtpa(Float coagulogramaTtpa) {
        this.coagulogramaTtpa = coagulogramaTtpa;
    }

    public Float getCoagulogramaInr() {
        return this.coagulogramaInr;
    }

    public void setCoagulogramaInr(Float coagulogramaInr) {
        this.coagulogramaInr = coagulogramaInr;
    }

    public String getRxTorax() {
        return this.rxTorax;
    }

    public void setRxTorax(String rxTorax) {
        this.rxTorax = rxTorax;
    }

    public String getEcg() {
        return this.ecg;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }

    public String getOutros() {
        return this.outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }

    public String getParecerCardiologico() {
        return this.parecerCardiologico;
    }

    public void setParecerCardiologico(String parecerCardiologico) {
        this.parecerCardiologico = parecerCardiologico;
    }

    public String getConclusao() {
        return this.conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getConclusaoObs() {
        return this.conclusaoObs;
    }

    public void setConclusaoObs(String conclusaoObs) {
        this.conclusaoObs = conclusaoObs;
    }

    public LocalDate getRetornoData() {
        return this.retornoData;
    }

    public void setRetornoData(LocalDate retornoData) {
        this.retornoData = retornoData;
    }

    public String getRetornoObs() {
        return this.retornoObs;
    }

    public void setRetornoObs(String retornoObs) {
        this.retornoObs = retornoObs;
    }

    public Boolean isCuidadosHabituais() {
        return this.cuidadosHabituais;
    }

    public Boolean getCuidadosHabituais() {
        return this.cuidadosHabituais;
    }

    public void setCuidadosHabituais(Boolean cuidadosHabituais) {
        this.cuidadosHabituais = cuidadosHabituais;
    }

    public Boolean isManterMedicacao() {
        return this.manterMedicacao;
    }

    public Boolean getManterMedicacao() {
        return this.manterMedicacao;
    }

    public void setManterMedicacao(Boolean manterMedicacao) {
        this.manterMedicacao = manterMedicacao;
    }

    public Boolean isTromboprofilaxia() {
        return this.tromboprofilaxia;
    }

    public Boolean getTromboprofilaxia() {
        return this.tromboprofilaxia;
    }

    public void setTromboprofilaxia(Boolean tromboprofilaxia) {
        this.tromboprofilaxia = tromboprofilaxia;
    }

    public Boolean isPosUti() {
        return this.posUti;
    }

    public Boolean getPosUti() {
        return this.posUti;
    }

    public void setPosUti(Boolean posUti) {
        this.posUti = posUti;
    }

    public Boolean isProfilaxiaEndocardite() {
        return this.profilaxiaEndocardite;
    }

    public Boolean getProfilaxiaEndocardite() {
        return this.profilaxiaEndocardite;
    }

    public void setProfilaxiaEndocardite(Boolean profilaxiaEndocardite) {
        this.profilaxiaEndocardite = profilaxiaEndocardite;
    }

    public String getPrecaucoes() {
        return this.precaucoes;
    }

    public void setPrecaucoes(String precaucoes) {
        this.precaucoes = precaucoes;
    }

    public String getTecnicaAnestesicaQual() {
        return this.tecnicaAnestesicaQual;
    }

    public void setTecnicaAnestesicaQual(String tecnicaAnestesicaQual) {
        this.tecnicaAnestesicaQual = tecnicaAnestesicaQual;
    }

    public String getIndicesRisco() {
        return this.indicesRisco;
    }

    public void setIndicesRisco(String indicesRisco) {
        this.indicesRisco = indicesRisco;
    }

    public String getDentes() {
        return this.dentes;
    }

    public void setDentes(String dentes) {
        this.dentes = dentes;
    }

    public String getClinica() {
        return this.clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getComplementoInfoNeurologica() {
        return this.complementoInfoNeurologica;
    }

    public void setComplementoInfoNeurologica(String complementoInfoNeurologica) {
        this.complementoInfoNeurologica = complementoInfoNeurologica;
    }


    public Boolean isMucosaCorada() {
        return this.mucosaCorada;
    }

    public Boolean getMucosaCorada() {
        return this.mucosaCorada;
    }

    public void setMucosaCorada(Boolean mucosaCorada) {
        this.mucosaCorada = mucosaCorada;
    }

    public Boolean isMucosaHidratada() {
        return this.mucosaHidratada;
    }

    public Boolean getMucosaHidratada() {
        return this.mucosaHidratada;
    }

    public void setMucosaHidratada(Boolean mucosaHidratada) {
        this.mucosaHidratada = mucosaHidratada;
    }

    public Boolean isMucosaAcianotica() {
        return this.mucosaAcianotica;
    }

    public Boolean getMucosaAcianotica() {
        return this.mucosaAcianotica;
    }

    public void setMucosaAcianotica(Boolean mucosaAcianotica) {
        this.mucosaAcianotica = mucosaAcianotica;
    }

    public Boolean isMucosaAnicterica() {
        return this.mucosaAnicterica;
    }

    public Boolean getMucosaAnicterica() {
        return this.mucosaAnicterica;
    }

    public void setMucosaAnicterica(Boolean mucosaAnicterica) {
        this.mucosaAnicterica = mucosaAnicterica;
    }

    public Boolean isTemperaturaAfebril() {
        return this.temperaturaAfebril;
    }

    public Boolean getTemperaturaAfebril() {
        return this.temperaturaAfebril;
    }

    public void setTemperaturaAfebril(Boolean temperaturaAfebril) {
        this.temperaturaAfebril = temperaturaAfebril;
    }

    public Boolean isTemperaturaSpo() {
        return this.temperaturaSpo;
    }

    public Boolean getTemperaturaSpo() {
        return this.temperaturaSpo;
    }

    public void setTemperaturaSpo(Boolean temperaturaSpo) {
        this.temperaturaSpo = temperaturaSpo;
    }

    public Integer getSaturacao() {
        return this.saturacao;
    }

    public void setSaturacao(Integer saturacao) {
        this.saturacao = saturacao;
    }

    public Boolean isTecnicaAnestesicaGeral() {
        return this.tecnicaAnestesicaGeral;
    }

    public Boolean getTecnicaAnestesicaGeral() {
        return this.tecnicaAnestesicaGeral;
    }

    public void setTecnicaAnestesicaGeral(Boolean tecnicaAnestesicaGeral) {
        this.tecnicaAnestesicaGeral = tecnicaAnestesicaGeral;
    }

    public Boolean isTecnicaAnestesicaBloqueio() {
        return this.tecnicaAnestesicaBloqueio;
    }

    public Boolean getTecnicaAnestesicaBloqueio() {
        return this.tecnicaAnestesicaBloqueio;
    }

    public void setTecnicaAnestesicaBloqueio(Boolean tecnicaAnestesicaBloqueio) {
        this.tecnicaAnestesicaBloqueio = tecnicaAnestesicaBloqueio;
    }

    public String getMucosas() {
        return this.mucosas;
    }

    public void setMucosas(String mucosas) {
        this.mucosas = mucosas;
    }

    public Integer getValorMet() {
        return valorMet;
    }

    public void setValorMet(Integer valorMet) {
        this.valorMet = valorMet;
    }

}

package net.ebserh.hctm.controller.triagens;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.Atendimento;
import net.ebserh.hctm.model.aghu.internacoes.Internacao;
import net.ebserh.hctm.model.aghu.prescricoes.PrescricaoDietaAghu;
import net.ebserh.hctm.model.triagens.TriagemNeonatal;
import net.ebserh.hctm.service.aghu.AtendimentosService;
import net.ebserh.hctm.service.aghu.internacoes.InternacoesService;
import net.ebserh.hctm.service.aghu.prescricoes.PrescricoesDietasAghuService;
import net.ebserh.hctm.service.triagens.TriagemNeonatalService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class NeonatalController implements Serializable{

    private static final Logger logger = Logger.getAnonymousLogger();

    @Inject
    private InternacoesService internacoesService;

    @Inject
    private AtendimentosService atendimentosService;

    @Inject
    private PrescricoesDietasAghuService prescricoesDietasAghuService;

    @Inject
    private TriagemNeonatalService triagemNeonatalService;

    private PrescricaoDietaAghu prescricaoDietaAghu;

    private Atendimento atendimento;

    private Internacao internacao;

    private Integer prontuario;

    private LocalDate dataTriagem;

    private String diagnosticos;

    private String comorbidades;

    private String idadeCron;

    private String idadeCorr;

    private String ig;

    private String alergiaIntoleranciaAlimentar;

    private Integer escoreTotal;

    private String idadeGestacional;

    private String doencaCondicaoClinica;

    private String terapiaNutricional;

    private String riscoNutricional;

    private String assistenciaNutricional;

    private String exportaTriagem;

    private boolean flag;

    private TriagemNeonatal triagemNeonatal;

    public void buscaDados() {
		try {
            exportaTriagem = null;

            internacao = internacoesService.buscaAtivaPorProntuario(prontuario);
            dataTriagem = LocalDate.now();
            escoreTotal = 0;
            atendimento = atendimentosService.buscaPorProntuario(prontuario);
            
            
            diagnosticos = "";

            comorbidades = "";

            idadeCron = "";

            idadeCorr = "";

            ig = "";

            alergiaIntoleranciaAlimentar = "";

            flag = true;

            if (internacao == null) {
                FacesUtils.showError("Nenhuma Internação encontrado com o prontuário informado.");
                return;
            }

            if(atendimento == null){
                FacesUtils.showError("Nenhum atendimento encontrado com o prontuário informado.");
                internacao = null;
                return;
            }

            prescricaoDietaAghu = prescricoesDietasAghuService.buscaPrescricaoPorAtendimento(atendimento.getSeq());

            
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao pesquisar os dados."+e.getMessage());
		}
    }

    public void calculaEscore(){
        
    
        try{
                escoreTotal = 0;
                switch (idadeGestacional) {
    
                    case "Recém-nascido pré-termo (>= 28 até abaixo de 37 semanas)":
                        escoreTotal++;
                        break;
    
                    case "Recém-nascido pré-termo extremo (abaixo de 28 semanas)":
                        escoreTotal += 2;
                        break;
                
                    default:
                        break;
                }
    
                
    
                if(doencaCondicaoClinica.equals("Sim")){
                    escoreTotal += 2;
                }
    
                switch (terapiaNutricional) {
    
                    case "Terapia nutricional enteral exclusiva ou mista":
                        escoreTotal++;
                        break;
    
                    case "Terapia nutricional parental exclusiva":
                        escoreTotal += 2;
                        break;
                    
                    case "Sem terapia nutricional":
                        escoreTotal += 3;
                        break;
                
                    default:
                        break;
                }
    
                if(escoreTotal == 0){
                    riscoNutricional = "Baixo risco";
                }else if(escoreTotal >= 4){
                    riscoNutricional = "Alto risco";
                }else{
                    riscoNutricional = "Médio risco";
                }
                
                flag = false;

        } catch (Exception e) {
            FacesUtils.showError("Preencha todos os campos da seção formulário");
        }    
    }

    public String verificaString(String string){
        if(string == null){
            return "---";
        }

        return string;
    }

    public void salva(){

        try {

            if(this.flag){
                FacesUtils.showError("Calcule o Escore!");
            }else{

                if(diagnosticos.isBlank())
                    diagnosticos = "---";

                if(alergiaIntoleranciaAlimentar.isBlank())
                    alergiaIntoleranciaAlimentar = "---";

                if(comorbidades.isBlank())
                    comorbidades = "---";
                
                if(idadeCron.isBlank())
                    idadeCron = "---";
                
                if(idadeCorr.isBlank())
                    idadeCorr = "---";
                
                if(ig.isBlank())
                    ig = "---";

                this.exportaTriagem = "#Avaliação do Risco Nutricional Neonatal - FARNNeo#\n\n"
                +"Data da Triagem: "+ this.dataTriagem.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()+"  Data Internação: " +internacao.getDthrInternacao().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
                +"\nClínica: " + this.internacao.getUnidadeFuncional().getSigla() +" Leito: "+this.internacao.getLeito().getId().toString()+ "  Especialidade: "+ this.internacao.getEspecialidade().getNome() +"\n"
                +"Prontuário: "+this.internacao.getPaciente().getProntuario().toString()  +"  Nome: "+this.internacao.getPaciente().getNome().toString()+ "  Data de Nascimento: "+"\n"
                +"Idade Cron.: " + this.idadeCron.toString() +"  Idade Corr: " + this.idadeCorr.toString() + "  IG: "+ this.ig.toString()+"\n"
                +"Diagnósticos: \n" + this.diagnosticos.toString()+"\n\n"
                +"Comorbidades: \n"+this.comorbidades.toString()+"\n\n"
                +"Alergia/Intolerância Alimentar: \n"+this.alergiaIntoleranciaAlimentar.toString()
                +"\n\n#Prescrição Dietética#\n\n"+this.prescricaoDietaAghu.toString()+"\n"
                +"\n\n#Idade gestacional de nascimento#\n\n"+this.idadeGestacional+"\n"
                +"\n#Doença e/ou condição clínica (com alto risco nutricional)#\n\n"
                +this.doencaCondicaoClinica
                +"\n\n#Terapia nutricional#\n\n"+this.terapiaNutricional
                +"\n\n#Classificação#\n"
                +"\nEscore total: "+this.escoreTotal
                +"\nRisco nutricional: "+this.riscoNutricional
                +"\nNivel de assistência nutricional: "+ this.assistenciaNutricional;

                triagemNeonatal = new TriagemNeonatal(internacao.getPaciente(), internacao, diagnosticos, comorbidades, idadeCron, idadeCorr, ig, alergiaIntoleranciaAlimentar, prescricaoDietaAghu, idadeGestacional, doencaCondicaoClinica, terapiaNutricional, riscoNutricional, assistenciaNutricional, escoreTotal);
                
                triagemNeonatalService.salvaTriagem(triagemNeonatal);
                
                this.internacao = null;
            }
            
        } catch (Exception e) {
            FacesUtils.showError(e.getMessage());
        }

        

        
        
    }


    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }


    public LocalDate getDataTriagem() {
        return dataTriagem;
    }

    public void setDataTriagem(LocalDate dataTriagem) {
        this.dataTriagem = dataTriagem;
    }


    public Integer getEscoreTotal() {
        return escoreTotal;
    }


    public void setEscoreTotal(Integer escoreTotal) {
        this.escoreTotal = escoreTotal;
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

    public String getAssistenciaNutricional() {
        return assistenciaNutricional;
    }

    public void setAssistenciaNutricional(String assistenciaNutricional) {
        this.assistenciaNutricional = assistenciaNutricional;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public PrescricaoDietaAghu getPrescricaoDietaAghu() {
        return prescricaoDietaAghu;
    }

    public void setPrescricaoDietaAghu(PrescricaoDietaAghu prescricaoDietaAghu) {
        this.prescricaoDietaAghu = prescricaoDietaAghu;
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

    public String getExportaTriagem() {
        return exportaTriagem;
    }

    public void setExportaTriagem(String exportaTriagem) {
        this.exportaTriagem = exportaTriagem;
    }
    

}
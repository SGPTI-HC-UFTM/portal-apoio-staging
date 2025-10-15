package net.ebserh.hctm.controller.enfermagem;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.commons.lang3.StringUtils;

import net.ebserh.hctm.dto.enfermagem.EscalaGlasgowDto;
import net.ebserh.hctm.model.aghu.internacoes.Internacao;
import net.ebserh.hctm.model.enfermagem.AlergiaMedicamento;
import net.ebserh.hctm.model.enfermagem.ComunicacaoPercepcao;
import net.ebserh.hctm.model.enfermagem.CuidadoCorporal;
import net.ebserh.hctm.model.enfermagem.DispositivoInvasivo;
import net.ebserh.hctm.model.enfermagem.Eliminacao;
import net.ebserh.hctm.model.enfermagem.Evolucao;
import net.ebserh.hctm.model.enfermagem.Mobilidade;
import net.ebserh.hctm.model.enfermagem.NecessidadePsicossocial;
import net.ebserh.hctm.model.enfermagem.NutricaoHidratacao;
import net.ebserh.hctm.model.enfermagem.Oxigenacao;
import net.ebserh.hctm.model.enfermagem.RegulacaoCardiovascular;
import net.ebserh.hctm.model.enfermagem.RegulacaoNeurologica;
import net.ebserh.hctm.model.enfermagem.RegulacaoTermica;
import net.ebserh.hctm.service.aghu.internacoes.InternacoesService;
import net.ebserh.hctm.util.FacesUtils;
import net.ebserh.hctm.util.Standardize;

@Named
@ViewScoped
public class DialogExportaEvolucaoController implements Serializable {

    private static final Logger logger = Logger.getAnonymousLogger();

    @Inject
    private InternacoesService internacoesService;

    private String textoEvolucao;

    public void atualiza(Evolucao evolucao) {
        if (evolucao == null) {
            FacesUtils.showError("É necessário selecionar uma evolução.");
            return;
        }

        try {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("Código da evolução: %d\n", evolucao.getId()));
            builder.append(String.format("Prontuário: %d\n", evolucao.getPaciente().getProntuario()));
            builder.append(String.format("Paciente: %s\n", evolucao.getPaciente().getNome()));

            acrescentaDadosEvolucao(builder, evolucao);
            if (evolucao.getDispositivoInvasivo() != null)
                acrescentaDadosDispositivosInvasivos(builder, evolucao.getDispositivoInvasivo());

            if (evolucao.getRegulacaoNeurologica() != null)
                acrescentaDadosRegulacaoNeurologica(builder, evolucao.getRegulacaoNeurologica());

            if (evolucao.getOxigenacao() != null)
                acrescentaDadosOxigenacao(builder, evolucao.getOxigenacao());

            if (evolucao.getRegulacaoCardiovascular() != null)
                acrescentaDadosRegulacaoCardiovascular(builder, evolucao.getRegulacaoCardiovascular());

            if (evolucao.getNutricaoHidratacao() != null)
                acrescentaDadosNutricao(builder, evolucao.getNutricaoHidratacao());

            if (evolucao.getEliminacao() != null)
                acrescentaDadosEliminacao(builder, evolucao.getEliminacao());

            if (evolucao.getRegulacaoTermica() != null)
                acrescentaDadosRegulacaoTermica(builder, evolucao.getRegulacaoTermica());

            if (evolucao.getComunicacaoPercepcao() != null)
                acrescentaDadosComunicacaoPercepcao(builder, evolucao.getComunicacaoPercepcao());

            if (evolucao.getCuidadoCorporal() != null)
                acrescentaDadosCuidadoCorporal(builder, evolucao.getCuidadoCorporal());

            if (evolucao.getMobilidade() != null)
                acrescentaMobilidade(builder, evolucao.getMobilidade());

            if (evolucao.getNecessidadePsicossocial() != null)
                acrescentaNecessidadePsicossocial(builder, evolucao.getNecessidadePsicossocial());

            if (evolucao.getAlergiaMedicamento() != null)
                acrescentaAlergiaMedicamento(builder, evolucao.getAlergiaMedicamento());

            if (StringUtils.isNotBlank(evolucao.getInformacoesComplementares()))
                acrescentaInformacoesComplementares(builder, evolucao.getInformacoesComplementares());

            if (StringUtils.isNotBlank(evolucao.getProcedimentosRealizados()))
                acrescentaProcedimentosRealizados(builder, evolucao.getProcedimentosRealizados());

            if (StringUtils.isNotBlank(evolucao.getDiagnosticosEnfermagem()))
                acrescentaDiagnosticosEnfermagem(builder, evolucao.getDiagnosticosEnfermagem());

            if (StringUtils.isNotBlank(evolucao.getCondutas()))
                acrescentaCondutas(builder, evolucao.getCondutas());

            textoEvolucao = builder.toString();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            FacesUtils.showError("Ocorreu um erro ao exportar a evolução.");
        }
    }

    private void acrescentaStringLabel(StringBuilder builder, String s, String label, boolean quebraDelinha) {
        if (StringUtils.isNotBlank(s)) {
            builder.append(String.format("%s %s", label, s));
            if (quebraDelinha)
                builder.append("\n");
        }
    }

    private void acrescentaDadosEvolucao(StringBuilder builder, Evolucao evolucao) {
        builder.append("\n");
        builder.append("*** INTERNAÇÃO ***\n");

        String separadorOpcoes = " ";

        try {
            Internacao internacao = internacoesService.buscaAtivaPorProntuario(evolucao.getPaciente().getProntuario());
            builder.append("DAH: " + Standardize.formatarDataHoraParaBR(internacao.getDthrInternacao()));
            builder.append("    DX Médico: " + internacao.cidsStr() + "\n");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        if (evolucao.getEscalaFugulin() != null && !evolucao.getEscalaFugulin().isBlank()) {

            switch (evolucao.getEscalaFugulin()) {
                case "IS":
                    builder.append(String.format("Fugulin: %s; ", "intensivo (Acima de 34)"));
                    break;
                case "SI":
                    builder.append(String.format("Fugulin: %s; ", "semi-intensivo (29-34)"));
                    break;
                case "AD":
                    builder.append(String.format("Fugulin: %s; ", "alta dependência (23-28)"));
                    break;
                case "IM":
                    builder.append(String.format("Fugulin: %s; ", "intermediário (18-22)"));
                    break;
                case "MI":
                    builder.append(String.format("Fugulin: %s; ", "mínimo (12-17)"));
                    break;
                default:
                    break;
            }
        }

        if (evolucao.getIndPrecaucaoAerossois() || evolucao.getIndPrecaucaoContato() ||
                evolucao.getIndPrecaucaoGoticulas() || evolucao.getIndPrecaucaoPadrao() ||
                evolucao.getIndPrecaucaoReversa()) {

            builder.append("Precauções:");
            if (evolucao.getIndPrecaucaoAerossois()) {
                builder.append(" aerossóis");
                separadorOpcoes = ", ";
            }

            if (evolucao.getIndPrecaucaoContato()) {
                builder.append(separadorOpcoes + "contato");
                separadorOpcoes = ", ";
            }

            if (evolucao.getIndPrecaucaoGoticulas()) {
                builder.append(separadorOpcoes + "gotículas");
                separadorOpcoes = ", ";
            }

            if (evolucao.getIndPrecaucaoPadrao()) {
                builder.append(separadorOpcoes + "padrão");
                separadorOpcoes = ", ";
            }

            if (evolucao.getIndPrecaucaoReversa()) {
                builder.append(separadorOpcoes + "reversa");
            }

            builder.append("; ");
        }

        if (evolucao.getObservacaoPrecaucao() != null && !evolucao.getObservacaoPrecaucao().isBlank())
            builder.append("Obs.: " + evolucao.getObservacaoPrecaucao() + "; ");
    }

    private void acrescentaDadosDispositivosInvasivos(StringBuilder builder, DispositivoInvasivo dispositivoInvasivo) {

        StringBuilder localBuilder = new StringBuilder();

        if (dispositivoInvasivo.getIndCateterVenosoPeriferico() != null
                && dispositivoInvasivo.getIndCateterVenosoPeriferico()) {
            localBuilder.append("Cateter venoso periférico - ");
            if (dispositivoInvasivo.getDataCateterVenosoPeriferico() != null)
                localBuilder.append("Data: " + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(dispositivoInvasivo.getDataCateterVenosoPeriferico()));

            if (dispositivoInvasivo.getLocalCateterVenosoPeriferico() != null
                    && !dispositivoInvasivo.getLocalCateterVenosoPeriferico().isBlank())
                localBuilder.append(" - " + dispositivoInvasivo.getLocalCateterVenosoPeriferico());

            if (dispositivoInvasivo.getObsCateterVenosoPeriferico() != null
                    && !dispositivoInvasivo.getObsCateterVenosoPeriferico().isBlank())
                localBuilder.append(", Obs.: " + dispositivoInvasivo.getObsCateterVenosoPeriferico());
        }

        if (dispositivoInvasivo.getIndCateterVenosoCentral() != null
                && dispositivoInvasivo.getIndCateterVenosoCentral()) {
            localBuilder.append("Cateter venoso central - ");
            if (dispositivoInvasivo.getDataCateterVenosoCentral() != null)
                localBuilder.append("Data: " + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(dispositivoInvasivo.getDataCateterVenosoCentral()));

            if (dispositivoInvasivo.getTipoCateterVenosoCentral() != null
                    && !dispositivoInvasivo.getTipoCateterVenosoCentral().isBlank())
                localBuilder.append(" - " + dispositivoInvasivo.getTipoCateterVenosoCentral());

            if (dispositivoInvasivo.getObservacaoCateterVenosoCentral() != null
                    && !dispositivoInvasivo.getObservacaoCateterVenosoCentral().isBlank())
                localBuilder.append("obs.: " + dispositivoInvasivo.getObservacaoCateterVenosoCentral());

            localBuilder.append("; ");
        }

        if (dispositivoInvasivo.getIndOutroCateterGastrico() != null
                && dispositivoInvasivo.getIndOutroCateterGastrico())
            localBuilder.append("Cateter gástrico; ");

        if (dispositivoInvasivo.getIndOutroCateterEnteral() != null && dispositivoInvasivo.getIndOutroCateterEnteral())
            localBuilder.append("Cateter enteral; ");

        if (dispositivoInvasivo.getObservacaoOutroCateter() != null
                && !dispositivoInvasivo.getObservacaoOutroCateter().isBlank())
            localBuilder.append("Obs.: " + dispositivoInvasivo.getObservacaoOutroCateter() + "; ");

        if (dispositivoInvasivo.getIndCateterVesical() != null && dispositivoInvasivo.getIndCateterVesical()) {
            localBuilder.append("Cateter vesical -");
            if (dispositivoInvasivo.getIndCateterVesicalDemora() != null
                    && dispositivoInvasivo.getIndCateterVesicalDemora())
                localBuilder.append(" demora");

            if (dispositivoInvasivo.getIndCateterVesicalIntermitente() != null
                    && dispositivoInvasivo.getIndCateterVesicalIntermitente())
                localBuilder.append(" intermitente");

            if (dispositivoInvasivo.getIndCateterVesicalCistostomia() != null
                    && dispositivoInvasivo.getIndCateterVesicalCistostomia())
                localBuilder.append(" cistostomia");

            if (dispositivoInvasivo.getObservacaoCateterVesical() != null
                    && !dispositivoInvasivo.getObservacaoCateterVesical().isBlank())
                localBuilder.append(" obs.: " + dispositivoInvasivo.getObservacaoCateterVesical());

            localBuilder.append("; ");
        }

        if (dispositivoInvasivo.getObservacaoCanulaTraqueal() != null
                && !dispositivoInvasivo.getObservacaoCanulaTraqueal().isBlank())
            localBuilder.append("Cânula traqueal: " + dispositivoInvasivo.getObservacaoCanulaTraqueal() + "; ");

        if (dispositivoInvasivo.getDispositivosOrtopedicos() != null
                && !dispositivoInvasivo.getDispositivosOrtopedicos().isBlank())
            localBuilder.append("Dispositivos ortopédicos: " + dispositivoInvasivo.getDispositivosOrtopedicos() + "; ");

        if (dispositivoInvasivo.getDrenos() != null && !dispositivoInvasivo.getDrenos().isBlank())
            localBuilder.append("Drenos: " + dispositivoInvasivo.getDrenos() + "; ");

        if (dispositivoInvasivo.getOutros() != null && !dispositivoInvasivo.getOutros().isBlank())
            localBuilder.append("Outros: " + dispositivoInvasivo.getOutros());

        if (localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** DISPOSITIVOS INVASIVOS ***\n");
            builder.append(localBuilder.toString());
        }
    }

    private void acrescentaDadosRegulacaoNeurologica(StringBuilder builder, RegulacaoNeurologica regulacaoNeurologica) {

        StringBuilder localBuilder = new StringBuilder();

        if (regulacaoNeurologica.getGlasgow() != null) {
            EscalaGlasgowDto escalaGlasgow = new EscalaGlasgowDto();
            escalaGlasgow.setTotal(regulacaoNeurologica.getGlasgow());
            localBuilder.append("Glasgow: " + String.valueOf(regulacaoNeurologica.getGlasgow()) + " ("
                    + escalaGlasgow.getRespostaEscrita() + "); ");
        }

        if (regulacaoNeurologica.getRass() != null && !regulacaoNeurologica.getRass().isBlank())
            localBuilder.append("RASS: " + regulacaoNeurologica.getRass() + "; ");

        if (StringUtils.isNotBlank(regulacaoNeurologica.getNivelConsciencia())) {
            localBuilder.append("Nível de consciência: ");
            switch (regulacaoNeurologica.getNivelConsciencia()) {
                case "VI":
                    localBuilder.append("vigil; ");
                    break;
                case "AG":
                    localBuilder.append("agitado; ");
                    break;
                case "SO":
                    localBuilder.append("sonolento; ");
                    break;
                case "OB":
                    localBuilder.append("obnubilado; ");
                    break;
                case "TO":
                    localBuilder.append("torpor; ");
                    break;
                case "CO":
                    localBuilder.append("comatoso; ");
                    break;
                case "NA":
                    localBuilder.append("não aplica; ");
                    break;
                default:
                    localBuilder.append("desconhecido; ");
            }
        }

        if (StringUtils.isNotBlank(regulacaoNeurologica.getOrientacao())) {
            localBuilder.append("Orientação: ");
            switch (regulacaoNeurologica.getOrientacao()) {
                case "OR":
                    localBuilder.append("orientado");
                    break;
                case "DE":
                    localBuilder.append("desorientado");
                    break;
                case "NM":
                    localBuilder.append("não Mensurável");
                    break;
                default:
                    localBuilder.append("desconhecida");
            }
            if (regulacaoNeurologica.getOrientacaoAlo() != null && regulacaoNeurologica.getOrientacaoAlo())
                localBuilder.append(" - alo");

            if (regulacaoNeurologica.getOrientacaoAuto() != null && regulacaoNeurologica.getOrientacaoAuto())
                localBuilder.append(" - auto");

            if (regulacaoNeurologica.getOrientacaoObservacao() != null
                    && !regulacaoNeurologica.getOrientacaoObservacao().isBlank())
                localBuilder.append(" obs.: " + regulacaoNeurologica.getOrientacaoObservacao());

            localBuilder.append("; ");
        }

        if (regulacaoNeurologica.getSedacao() != null) {
            localBuilder.append("Sedação: ");
            switch (regulacaoNeurologica.getSedacao()) {
                case "CO":
                    localBuilder.append("combativo");
                    break;
                case "MA":
                    localBuilder.append("muito agitado");
                    break;
                case "AG":
                    localBuilder.append("agitado");
                    break;
                case "IN":
                    localBuilder.append("inquieto");
                    break;
                case "CA":
                    localBuilder.append("calmo");
                    break;
                case "SE":
                    localBuilder.append("sedado");
                    break;
                case "NA":
                    localBuilder.append("não Aplica");
                    break;
                default:
                    localBuilder.append("desconhecida");
            }

            localBuilder.append("; ");
        }

        if (regulacaoNeurologica.getPupilas() != null && !regulacaoNeurologica.getPupilas().isBlank()) {
            localBuilder.append("Pupilas:");

            if (regulacaoNeurologica.getPupilas().equals("AL")) {
                if (regulacaoNeurologica.getPupilasAnisocoricas1() != null &&
                        !regulacaoNeurologica.getPupilasAnisocoricas1().isBlank() &&
                        regulacaoNeurologica.getPupilasAnisocoricas2() != null &&
                        !regulacaoNeurologica.getPupilasAnisocoricas2().isBlank()) {

                    localBuilder.append(
                            String.format(" anisocóricas: %s > %s", regulacaoNeurologica.getPupilasAnisocoricas1(),
                                    regulacaoNeurologica.getPupilasAnisocoricas2()));
                }

                if (Boolean.TRUE.equals(regulacaoNeurologica.getPupilasMidriaticas()))
                    localBuilder.append(" midriáticas");

                if (Boolean.TRUE.equals(regulacaoNeurologica.getPupilasMioticas()))
                    localBuilder.append(" mióticas");

                if (Boolean.TRUE.equals(regulacaoNeurologica.getPupilasNaoReagentesLuz()))
                    localBuilder.append(" não reagente à luz");

                localBuilder.append("; ");
            } else {
                localBuilder.append(" preservadas; ");
            }
        }

        if (regulacaoNeurologica.getAberturaOlhos() != null && regulacaoNeurologica.getAberturaOlhos().isBlank()) {
            localBuilder.append("Abertura dos olhos: ");
            switch (regulacaoNeurologica.getAberturaOlhos()) {
                case "ES":
                    localBuilder.append("espontânea");
                    break;
                case "CV":
                    localBuilder.append("ao comando verbal");
                    break;
                case "AD":
                    localBuilder.append("à dor");
                    break;
                case "SR":
                    localBuilder.append("sem resposta");
                    break;
                default:
                    localBuilder.append("não informada");
            }

            localBuilder.append(";");
        }

        if (regulacaoNeurologica.getRespostaMotora() != null && regulacaoNeurologica.getRespostaMotora().isBlank()) {
            localBuilder.append("Resposta motora: ");
            switch (regulacaoNeurologica.getAberturaOlhos()) {
                case "LE":
                    localBuilder.append("localiza estímulo");
                    break;
                case "CV":
                    localBuilder.append("comando verbal");
                    break;
                case "RI":
                    localBuilder.append("retirada inespecífica");
                    break;
                case "FA":
                    localBuilder.append("flexão anormal");
                    break;
                case "EA":
                    localBuilder.append("extensão anormal");
                    break;
                case "SR":
                    localBuilder.append("sem resposta");
                    break;
                default:
                    localBuilder.append("não informada");
            }

            localBuilder.append("; ");
        }

        if (localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** REGULAÇÃO NEUROLÓGICA ***\n");
            builder.append(localBuilder.toString());
        }
    }

    private void acrescentaDadosOxigenacao(StringBuilder builder, Oxigenacao oxigenacao) {

        StringBuilder localBuilder = new StringBuilder();

        String separadorOpcoes = "Ventilação: ";

        if (Boolean.TRUE.equals(oxigenacao.getVentilacaoEspontanea())) {
            localBuilder.append(separadorOpcoes + "espontânea");
            separadorOpcoes = ", ";
        }

        if (Boolean.TRUE.equals(oxigenacao.getVentilacaoEspontaneaAr())) {
            localBuilder.append(separadorOpcoes + "ar");
            separadorOpcoes = ", ";
        }

        if (Boolean.TRUE.equals(oxigenacao.getVentilacaoEspontaneaO2())) {
            localBuilder.append(separadorOpcoes + "O2");
            separadorOpcoes = ", ";
        }

        if (Boolean.TRUE.equals(oxigenacao.getVentilacaoEspontaneaO2())) {
            localBuilder.append(separadorOpcoes + "O2");
            separadorOpcoes = ", ";
        }

        if (oxigenacao.getVentilacaoEspontaneaFl() != null && !oxigenacao.getVentilacaoEspontaneaFl().isBlank()) {
            localBuilder.append(separadorOpcoes + String.format(" %s fl.", oxigenacao.getVentilacaoEspontaneaFl()));
            separadorOpcoes = ", ";
        }

        if (oxigenacao.getVentilacaoEspontaneaLMin() != null && !oxigenacao.getVentilacaoEspontaneaLMin().isBlank()) {
            localBuilder.append(separadorOpcoes + String.format(" %s L/min", oxigenacao.getVentilacaoEspontaneaLMin()));
            separadorOpcoes = ", ";
        }

        if (Boolean.TRUE.equals(oxigenacao.getVentilacaoMecanica())) {
            localBuilder.append(separadorOpcoes + "VM");
            separadorOpcoes = ", ";
        }

        if (Boolean.TRUE.equals(oxigenacao.getVentilacaoMecanicaInvasiva())) {
            localBuilder.append(separadorOpcoes + "invasiva");
            separadorOpcoes = ", ";
        }

        if (Boolean.TRUE.equals(oxigenacao.getVentilacaoMecanicaVni())) {
            localBuilder.append(separadorOpcoes + "VNI");
            separadorOpcoes = ", ";
        }

        if (separadorOpcoes.equals(", ")) {
            localBuilder.append("; ");
        }

        if (oxigenacao.getVentilacaoMecanicaModo() != null && !oxigenacao.getVentilacaoMecanicaModo().isBlank()) {
            localBuilder.append("Modo: " + oxigenacao.getVentilacaoMecanicaModo() + "; ");
        }

        if (oxigenacao.getVentilacaoMecanicaPeep() != null && !oxigenacao.getVentilacaoMecanicaPeep().isBlank())
            localBuilder.append("PEEP: " + oxigenacao.getVentilacaoMecanicaPeep() + "; ");

        if (oxigenacao.getVentilacaoMecanicaFio2() != null && !oxigenacao.getVentilacaoMecanicaFio2().isBlank())
            localBuilder.append("FiO2: " + oxigenacao.getVentilacaoMecanicaFio2() + "; ");

        if (oxigenacao.getVentilacaoMecanicaVtP() != null && !oxigenacao.getVentilacaoMecanicaVtP().isBlank())
            localBuilder.append("Vt/P: " + oxigenacao.getVentilacaoMecanicaFio2() + "; ");

        if (StringUtils.isNotBlank(oxigenacao.getPadraoRespiratorio())
                || StringUtils.isNotBlank(oxigenacao.getPadraoRespiratorioOutro())) {

            if (oxigenacao.getPadraoRespiratorio() != null) {
                localBuilder.append("Padrão respiratório:");

                switch (oxigenacao.getPadraoRespiratorio()) {
                    case "EU":
                        localBuilder.append(" eupneia; ");
                        break;
                    case "TA":
                        localBuilder.append(" taquinopneia; ");
                        break;
                    case "BR":
                        localBuilder.append(" bradipneia; ");
                        break;
                    case "DI":
                        localBuilder.append(" dispneia; ");
                        break;
                    case "OU":
                        localBuilder.append(" outro; ");
                        break;
                    default:
                        localBuilder.append(" desconhecido; ");
                }
            }

            if (oxigenacao.getPadraoRespiratorioOutro() != null && !oxigenacao.getPadraoRespiratorioOutro().isBlank())
                localBuilder.append("Outro: " + oxigenacao.getPadraoRespiratorioOutro() + "; ");
        }

        if (StringUtils.isNotBlank(oxigenacao.getDeltaFr())) {
            acrescentaStringLabel(localBuilder, oxigenacao.getDeltaFr() + "; ", "Δ FR:", false);
        }

        if (oxigenacao.getTosse() != null && !oxigenacao.getTosse().isBlank()) {
            localBuilder.append("Tosse: ");
            switch (oxigenacao.getTosse()) {
                case "AU":
                    localBuilder.append("ausente; ");
                    break;
                case "SE":
                    localBuilder.append("seca; ");
                    break;
                case "PR":
                    localBuilder.append("produtiva; ");
                    break;
                case "NM":
                    localBuilder.append("não Mensurável; ");
                    break;
                default:
                    localBuilder.append("desconhecida; ");
            }
        }

        if (Boolean.TRUE.equals(oxigenacao.getReflexoTosse())
                || Boolean.TRUE.equals(oxigenacao.getReflexoTosseEficaz())
                || Boolean.TRUE.equals(oxigenacao.getReflexoTosseIneficaz())
                || Boolean.TRUE.equals(oxigenacao.getReflexoTosseNao())) {

            separadorOpcoes = "Reflexo de tosse: ";

            if (Boolean.TRUE.equals(oxigenacao.getReflexoTosse())) {
                localBuilder.append(separadorOpcoes + "reflexo de tosse");
                separadorOpcoes = ", ";
            }

            if (Boolean.TRUE.equals(oxigenacao.getReflexoTosseEficaz())) {
                localBuilder.append(separadorOpcoes + "eficaz");
                separadorOpcoes = ", ";
            }

            if (Boolean.TRUE.equals(oxigenacao.getReflexoTosseIneficaz())) {
                localBuilder.append(separadorOpcoes + "ineficaz");
                separadorOpcoes = ", ";
            }

            if (Boolean.TRUE.equals(oxigenacao.getReflexoTosseNao())) {
                localBuilder.append(separadorOpcoes + "não");
                separadorOpcoes = ", ";
            }

            localBuilder.append("; ");
        }

        if (oxigenacao.getSecrecaoViasAereas() != null) {
            localBuilder.append("Secreção de vias aéreas/escarro:");
            localBuilder.append(Boolean.TRUE.equals(oxigenacao.getSecrecaoViasAereas()) ? " sim; " : " não; ");
        }

        acrescentaStringLabel(localBuilder, oxigenacao.getSecrecaoViasAereasObs(), "Obs.: ", false);
        if (oxigenacao.getDeltaSpo2() != null && !oxigenacao.getDeltaSpo2().isBlank()) {
            localBuilder.append("SpO2 Δ: ");
            localBuilder.append(oxigenacao.getDeltaSpo2());
            localBuilder.append(" %; ");
        }

        if (oxigenacao.getTorax() != null && !oxigenacao.getTorax().isBlank())
            localBuilder.append("Tórax: " + ("SA".equals(oxigenacao.getTorax()) ? "sem alteração; " : "alterado; "));

        acrescentaStringLabel(localBuilder, oxigenacao.getToraxInspecao(), "Inspeção/ausculta/percussão", false);

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** OXIGENAÇÃO ***\n");
            builder.append(localBuilder.toString());
        }
    }

    private void acrescentaDadosRegulacaoCardiovascular(StringBuilder builder,
            RegulacaoCardiovascular regulacaoCardiovascular) {

        StringBuilder localBuilder = new StringBuilder();

        if (regulacaoCardiovascular.getFrequenciaCardiaca1() != null ||
                regulacaoCardiovascular.getFrequenciaCardiaca2() != null) {

            localBuilder.append("Frequência cardíaca:");

            if (regulacaoCardiovascular.getFrequenciaCardiaca1() != null)
                localBuilder.append(" Δ " + regulacaoCardiovascular.getFrequenciaCardiaca1());

            if (regulacaoCardiovascular.getFrequenciaCardiaca2() != null)
                localBuilder.append(" - " + regulacaoCardiovascular.getFrequenciaCardiaca2());

            localBuilder.append(" bpm; ");
        }

        if (StringUtils.isNotBlank(regulacaoCardiovascular.getFrequenciaCardiacaObs())) {
            acrescentaStringLabel(localBuilder, regulacaoCardiovascular.getFrequenciaCardiacaObs(), "Obs.: ", false);
            localBuilder.append("; ");
        }

        if (regulacaoCardiovascular.getPas1() != null ||
                regulacaoCardiovascular.getPas2() != null ||
                regulacaoCardiovascular.getPad1() != null ||
                regulacaoCardiovascular.getPad2() != null) {

            localBuilder.append("Pressão arterial:");
            if (regulacaoCardiovascular.getPas1() != null && regulacaoCardiovascular.getPas2() != null)
                localBuilder.append(String.format(" Δ PAS: %d - %d mmHg",
                        regulacaoCardiovascular.getPas1(),
                        regulacaoCardiovascular.getPas2()));

            if (regulacaoCardiovascular.getPad1() != null && regulacaoCardiovascular.getPad2() != null)
                localBuilder.append(String.format(" Δ PAD: %d - %d mmHg",
                        regulacaoCardiovascular.getPad1(),
                        regulacaoCardiovascular.getPad2()));

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(regulacaoCardiovascular.getPaObs())) {
            acrescentaStringLabel(localBuilder, regulacaoCardiovascular.getPaObs(), "obs.: ", false);
            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(regulacaoCardiovascular.getPulsosPerifericos())) {
            acrescentaStringLabel(localBuilder, regulacaoCardiovascular.getPulsosPerifericos(),
                    "Pulsos periféricos: ", false);
            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(regulacaoCardiovascular.getPerfusaoPeriferica())) {
            localBuilder.append("Perfusão periférica:");
            switch (regulacaoCardiovascular.getPerfusaoPeriferica()) {
                case "PR":
                    localBuilder.append(" preservada");
                    break;
                case "DI":
                    localBuilder.append(" diminuída (maior ou igual a 3s)");
                    break;
                default:
                    localBuilder.append(" desconhecida");
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(regulacaoCardiovascular.getRedeVascular())) {
            localBuilder.append("Rede vascular: ");
            switch (regulacaoCardiovascular.getRedeVascular()) {
                case "PS":
                    localBuilder.append("preservada");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada");
                    break;
                default:
                    localBuilder.append("desconhecida");
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(regulacaoCardiovascular.getRedeVascularObs())) {
            acrescentaStringLabel(localBuilder, regulacaoCardiovascular.getRedeVascularObs(), "obs.: ", false);
            localBuilder.append("; ");
        }

        if (regulacaoCardiovascular.getEdema() != null) {
            localBuilder.append("Edema:");
            if (Boolean.TRUE.equals(regulacaoCardiovascular.getEdema()))
                localBuilder.append(" sim");
            else
                localBuilder.append(" não");

            if (regulacaoCardiovascular.getEdemaLocalCacifo() != null) {
                acrescentaStringLabel(localBuilder, regulacaoCardiovascular.getEdemaLocalCacifo(), " obs.: ", false);
            }
            localBuilder.append("; ");
        }

        if (localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** REGULAÇÃO CARDIOVASCULAR ***\n");
            builder.append(localBuilder.toString());
        }
    }

    private void acrescentaDadosNutricao(StringBuilder builder, NutricaoHidratacao nutricaoHidratacao) {

        StringBuilder localBuilder = new StringBuilder();

        if (nutricaoHidratacao.getPeso() != null && nutricaoHidratacao.getEstatura() != null)
            localBuilder.append(String.format("Peso: %d Kg - Estatura: %d cm; ",
                    nutricaoHidratacao.getPeso(), nutricaoHidratacao.getEstatura()));

        if (Boolean.TRUE.equals(nutricaoHidratacao.getDietaOral())
                || Boolean.TRUE.equals(nutricaoHidratacao.getDietaEnteral()) ||
                Boolean.TRUE.equals(nutricaoHidratacao.getDietaParenteral())
                || Boolean.TRUE.equals(nutricaoHidratacao.getDietaZero())) {
            localBuilder.append("Dieta: ");
        }
        if (Boolean.TRUE.equals(nutricaoHidratacao.getDietaOral())) {
            localBuilder.append("oral");
            if (StringUtils.isNotBlank(nutricaoHidratacao.getDietaAceitacao())) {
                localBuilder.append(", aceitação ");
                switch (nutricaoHidratacao.getDietaAceitacao()) {
                    case "BO":
                        localBuilder.append("boa");
                        break;
                    case "RE":
                        localBuilder.append("regular");
                        break;
                    case "RU":
                        localBuilder.append("ruim");
                        break;
                    default:
                        localBuilder.append("desconhecida");
                }
            }

            acrescentaStringLabel(localBuilder, nutricaoHidratacao.getDietaObs(), " obs.: ", false);
            localBuilder.append("; ");
        }

        if (Boolean.TRUE.equals(nutricaoHidratacao.getDietaEnteral())) {
            localBuilder.append("Enteral: ");
            if (nutricaoHidratacao.getDietaEnteralVolume() != null)
                localBuilder.append(String.valueOf(nutricaoHidratacao.getDietaEnteralVolume()) + " ml/h");
            localBuilder.append("; ");
        }

        if (Boolean.TRUE.equals(nutricaoHidratacao.getDietaParenteral())) {
            localBuilder.append("Parenteral: ");
            if (nutricaoHidratacao.getDietaParenteralVolume() != null)
                localBuilder.append(String.valueOf(nutricaoHidratacao.getDietaParenteralVolume()) + " ml/h");
            localBuilder.append("; ");
        }

        if (Boolean.TRUE.equals(nutricaoHidratacao.getDietaZero())) {
            localBuilder.append("Zero: ");
            if (StringUtils.isNotBlank(nutricaoHidratacao.getDietaZeroObs()))
                localBuilder.append(nutricaoHidratacao.getDietaZeroObs());
            localBuilder.append("; ");
        }

        if (Boolean.TRUE.equals(nutricaoHidratacao.getDegluticaoPreservada())) {
            acrescentaStringLabel(localBuilder, "Preservada;", "Deglutição: ", false);
        } else if (Boolean.FALSE.equals(nutricaoHidratacao.getDegluticaoPreservada())) {
            localBuilder.append("Deglutição Alterada:");
            if (Boolean.TRUE.equals(nutricaoHidratacao.getDegluticaoDisfagia()))
                localBuilder.append(" disfagia");

            if (Boolean.TRUE.equals(nutricaoHidratacao.getDegluticaoEngasgos()))
                localBuilder.append(" engasgos");

            if (Boolean.TRUE.equals(nutricaoHidratacao.getDegluticaoEstase()))
                localBuilder.append(" estase alimentar bucal");

            if (StringUtils.isNotBlank(nutricaoHidratacao.getDegluticaoObs()))
                localBuilder.append(" " + nutricaoHidratacao.getDegluticaoObs());

            if (Boolean.TRUE.equals(nutricaoHidratacao.getDegluticaoNa()))
                localBuilder.append(" não aplica");

            localBuilder.append("; ");
        }

        if (Boolean.TRUE.equals(nutricaoHidratacao.getAbdomePreservado())) {
            acrescentaStringLabel(localBuilder, "Sem Alteração", "Abdome", true);
        } else if (Boolean.FALSE.equals(nutricaoHidratacao.getAbdomePreservado())) {
            localBuilder.append("Abdome Alterado:");
            if (Boolean.TRUE.equals(nutricaoHidratacao.getAbdomeDistendido()))
                localBuilder.append(" distendido");

            if (Boolean.TRUE.equals(nutricaoHidratacao.getAbdomeTenso()))
                localBuilder.append(" tenso");

            if (Boolean.TRUE.equals(nutricaoHidratacao.getAbdomeMassaPalpavel()))
                localBuilder.append(" massa palpável");

            if (Boolean.TRUE.equals(nutricaoHidratacao.getAbdomeRuido()))
                localBuilder.append(" ruido");

            if (StringUtils.isNotBlank(nutricaoHidratacao.getAbdomeObs()))
                localBuilder.append(" " + nutricaoHidratacao.getAbdomeObs());

            localBuilder.append("; ");
        }

        if (nutricaoHidratacao.getVomito() != null) {
            localBuilder
                    .append(Boolean.TRUE.equals(nutricaoHidratacao.getVomito()) ? "Vômito: sim; " : "Vômito: não; ");
            acrescentaStringLabel(localBuilder, nutricaoHidratacao.getVomitoObs(), "Obs.: ", false);
        }

        if (nutricaoHidratacao.getNausea() != null) {
            localBuilder
                    .append(Boolean.TRUE.equals(nutricaoHidratacao.getNausea()) ? "Náusea: sim; " : "Náusea: não; ");
        }

        if (nutricaoHidratacao.getRestricaoHidrica() != null) {
            localBuilder
                    .append(Boolean.TRUE.equals(nutricaoHidratacao.getRestricaoHidrica()) ? "Restrição Hídrica: sim; "
                            : "Restrição Hídrica: não; ");
        }

        if (nutricaoHidratacao.getRestricaoHidricaVolume() != null)
            localBuilder.append(String.format("%d mL ", nutricaoHidratacao.getRestricaoHidricaVolume()) + "; ");

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** NUTRIÇÃO/HIDRATAÇÃO ***\n");
            builder.append(localBuilder.toString());
        }
    }

    private void acrescentaDadosEliminacao(StringBuilder builder, Eliminacao eliminacao) {

        StringBuilder localBuilder = new StringBuilder();

        if (eliminacao.getBalancoHidricoTotal() != null)
            localBuilder.append(String.format("Balanço hídrico total: %d ml; ", eliminacao.getBalancoHidricoTotal()));

        if (Boolean.TRUE.equals(eliminacao.getMiccaoEspontanea()) || Boolean.TRUE.equals(eliminacao.getCateter())) {
            localBuilder.append("Eliminação Urinária:");
            if (Boolean.TRUE.equals(eliminacao.getMiccaoEspontanea()))
                localBuilder.append(String.format(" micção espontânea em %s", eliminacao.getMiccaoEspontaneaObs()));

            if (Boolean.TRUE.equals(eliminacao.getCateter()))
                localBuilder.append(String.format(" cateter - volume: %d ml em %d horas", eliminacao.getVolume(),
                        eliminacao.getVolumeHoras()));
            localBuilder.append("; ");
        }

        if (eliminacao.getPadraoPreservado() != null)
            localBuilder.append("Padrão: "
                    + (Boolean.TRUE.equals(eliminacao.getPadraoPreservado()) ? "preservado; " : "alterado; "));

        if (eliminacao.getPadraoCaracteristicas() != null && !eliminacao.getPadraoCaracteristicas().isBlank())
            localBuilder.append("Características: " + eliminacao.getPadraoCaracteristicas() + "; ");

        if (Boolean.TRUE.equals(eliminacao.getEliminacaoEspontanea()) || Boolean.TRUE.equals(eliminacao.getColostomia())
                || Boolean.TRUE.equals(eliminacao.getIleostomia())) {

            localBuilder.append("Eliminação Intestinal:");
            if (Boolean.TRUE.equals(eliminacao.getEliminacaoEspontanea()))
                localBuilder
                        .append(String.format(" espontânea em %s", eliminacao.getEliminacaoEspontaneaObs()));

            if (Boolean.TRUE.equals(eliminacao.getColostomia()))
                localBuilder.append(" colostomia");

            if (Boolean.TRUE.equals(eliminacao.getIleostomia()))
                localBuilder.append(" ileostomia");

            if (eliminacao.getPadraoCaracteristicas() != null && !eliminacao.getPadraoCaracteristicas().isBlank())
                localBuilder.append(" padrão (caract./freq./vol.): " + eliminacao.getPadraoCaracteristicas());

            localBuilder.append("; ");
        }

        if (eliminacao.getEfluentes() != null
                || (eliminacao.getEfluentesObs() != null && !eliminacao.getEfluentesObs().isBlank())) {

            localBuilder.append("Efluentes:");
            if (eliminacao.getEfluentes() != null)
                localBuilder.append(Boolean.TRUE.equals(eliminacao.getEfluentes()) ? " sim" : " não");

            if (eliminacao.getEfluentesObs() != null && !eliminacao.getEfluentesObs().isBlank())
                localBuilder.append(" " + eliminacao.getEfluentesObs());

            localBuilder.append("; ");
        }

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** ELIMINAÇÃO ***\n");
            builder.append(localBuilder.toString());
        }
    }

    private void acrescentaDadosRegulacaoTermica(StringBuilder builder, RegulacaoTermica regulacaoTermica) {

        StringBuilder localBuilder = new StringBuilder();

        String separadorOpcoes = "";

        if (regulacaoTermica.getBraden() != null && !regulacaoTermica.getBraden().isBlank()) {
            localBuilder.append("Braden: ");
            switch (regulacaoTermica.getBraden()) {
                case "SR":
                    localBuilder.append("sem risco; ");
                    break;
                case "LE":
                    localBuilder.append("leve; ");
                    break;
                case "MO":
                    localBuilder.append("moderado; ");
                    break;
                case "AL":
                    localBuilder.append("alto; ");
                    break;
                default:
                    localBuilder.append("desconhecido; ");
            }
        }

        if (regulacaoTermica.getTemperaturaDelta1() != null && regulacaoTermica.getTemperaturaDelta2() != null) {
            localBuilder.append("Temperatura Corporal: ");
            localBuilder.append(String.format("Δ: %.01f - %.01f °C; ", regulacaoTermica.getTemperaturaDelta1(),
                    regulacaoTermica.getTemperaturaDelta2()));
        }
        acrescentaStringLabel(localBuilder, regulacaoTermica.getTemperaturaObs(), "Obs.:", true);

        if (Boolean.TRUE.equals(regulacaoTermica.getPeleAlterada())
                || Boolean.FALSE.equals(regulacaoTermica.getPeleAlterada())) {

            localBuilder.append("Pele: ");
            if (Boolean.TRUE.equals(regulacaoTermica.getPeleAlterada()))
                localBuilder.append("alterada ");
            else if (Boolean.FALSE.equals(regulacaoTermica.getPeleAlterada()))
                localBuilder.append("sem alteração");

            if (Boolean.TRUE.equals(regulacaoTermica.getPelePalidez())) {
                separadorOpcoes = ", ";
                localBuilder.append("palidez");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPeleCianose())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("cianose");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPeleRubor())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("rubor");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPeleFria())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("fria");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPeleIctericia())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("icterícia");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPelePrurido())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("prurido");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPeleSudorese())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("sudorese");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPeleRessecada())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("ressecada");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getPeleLesoes())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("lesões elementares");
            }

            localBuilder.append("; ");
            acrescentaStringLabel(localBuilder, regulacaoTermica.getPeleObs(), "Observação:", true);
        }

        if (Boolean.TRUE.equals(regulacaoTermica.getConjuntivaAlterada())
                || Boolean.FALSE.equals(regulacaoTermica.getConjuntivaAlterada())) {

            localBuilder.append("Conjuntiva: ");
            if (Boolean.TRUE.equals(regulacaoTermica.getConjuntivaAlterada()))
                localBuilder.append("alterada ");
            else if (Boolean.FALSE.equals(regulacaoTermica.getConjuntivaAlterada()))
                localBuilder.append("sem alteração");

            separadorOpcoes = "";
            if (Boolean.TRUE.equals(regulacaoTermica.getConjuntivaHipocorada())) {
                separadorOpcoes = ", ";
                localBuilder.append("hipocorada");
            }

            if (regulacaoTermica.getConjuntivaHipocoradaValor() != null) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append(String.format("%d /+4", regulacaoTermica.getConjuntivaHipocoradaValor()));
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getConjuntivaPrurido())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("prurido");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getConjuntivaHiperemia())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("hiperemia");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getConjuntivaIctericia())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("icterícia");
            }

            if (Boolean.TRUE.equals(regulacaoTermica.getConjuntivaRessecada())) {
                localBuilder.append(separadorOpcoes);
                separadorOpcoes = ", ";
                localBuilder.append("ressecada");
            }

            localBuilder.append("; ");

            acrescentaStringLabel(localBuilder, regulacaoTermica.getConjuntivaObs(), "Observação: ", false);
            acrescentaStringLabel(localBuilder, regulacaoTermica.getConjuntivaOutro(), "Outro: ", false);
        }

        if (Boolean.TRUE.equals(regulacaoTermica.getCavidadeBucalAlterada())
                || Boolean.FALSE.equals(regulacaoTermica.getCavidadeBucalAlterada())) {
            localBuilder.append("Cavidade Bucal: ");
            if (Boolean.TRUE.equals(regulacaoTermica.getCavidadeBucalAlterada()))
                localBuilder.append("alterada; ");
            else if (Boolean.FALSE.equals(regulacaoTermica.getCavidadeBucalAlterada()))
                localBuilder.append("íntegra; ");
            acrescentaStringLabel(localBuilder, regulacaoTermica.getCavidadeBucalObs(), "Observação: ", false);
        }

        if (Boolean.TRUE.equals(regulacaoTermica.getGenitaliaAlterada())
                || Boolean.FALSE.equals(regulacaoTermica.getGenitaliaAlterada())) {
            localBuilder.append("Genitália: ");
            if (Boolean.TRUE.equals(regulacaoTermica.getGenitaliaAlterada()))
                localBuilder.append("alterada; ");
            else if (Boolean.FALSE.equals(regulacaoTermica.getGenitaliaAlterada()))
                localBuilder.append("íntegra; ");
            acrescentaStringLabel(localBuilder, regulacaoTermica.getGenitaliaObs(), "Observação: ", false);
        }

        if (Boolean.TRUE.equals(regulacaoTermica.getFerida()) || Boolean.FALSE.equals(regulacaoTermica.getFerida())) {
            localBuilder.append("Ferida: ");
            if (Boolean.TRUE.equals(regulacaoTermica.getFerida()))
                localBuilder.append("sim; ");
            else if (Boolean.FALSE.equals(regulacaoTermica.getFerida()))
                localBuilder.append("não; ");
            acrescentaStringLabel(localBuilder, regulacaoTermica.getFeridaObs(), "Ip:",
                    false);
        }

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** REGULAÇÃO TÉRMICA E INTEGRIDADE CUTANEOMUCOSA ***\n");
            builder.append(localBuilder.toString());
        }

    }

    private void acrescentaDadosComunicacaoPercepcao(StringBuilder builder, ComunicacaoPercepcao comunicacao) {

        StringBuilder localBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(comunicacao.getFalaPreservada())) {
            localBuilder.append("Fala: ");
            switch (comunicacao.getFalaPreservada()) {
                case "PS":
                    localBuilder.append("preservada");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada");
                    break;
                default:
                    localBuilder.append("não aplica");
            }
            if (StringUtils.isNotBlank(comunicacao.getFalaObs())) {
                localBuilder.append(" , obs.: " + comunicacao.getFalaObs());
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(comunicacao.getAcuidadeVisualPreservada())) {
            localBuilder.append("Acuidade visual: ");
            switch (comunicacao.getAcuidadeVisualPreservada()) {
                case "PS":
                    localBuilder.append("preservada");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada");
                    if (Boolean.TRUE.equals(comunicacao.getAcuidadeVisualPrejudicadaD())) {
                        localBuilder.append(" D");
                    }
                    if (Boolean.TRUE.equals(comunicacao.getAcuidadeVisualPrejudicadaE())) {
                        localBuilder.append(" E");
                    }
                    break;
                case "NM":
                    localBuilder.append("não mensurável");
                    break;
                default:
                    localBuilder.append("não aplica");
            }

            if (StringUtils.isNotBlank(comunicacao.getAcuidadeVisualObs())) {
                localBuilder.append(" , obs.: " + comunicacao.getAcuidadeVisualObs());
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(comunicacao.getAcuidadeAuditivaPreservada())) {

            localBuilder.append("Acuidade auditiva: ");
            switch (comunicacao.getAcuidadeAuditivaPreservada()) {
                case "PS":
                    localBuilder.append("preservada");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada");
                    if (Boolean.TRUE.equals(comunicacao.getAcuidadeAuditivaPrejudicadaD())) {
                        localBuilder.append(" D");
                    }
                    if (Boolean.TRUE.equals(comunicacao.getAcuidadeAuditivaPrejudicadaE())) {
                        localBuilder.append(" E");
                    }
                    break;
                case "NM":
                    localBuilder.append("não mensurável");
                    break;
                default:
                    localBuilder.append("não aplica");
            }

            if (StringUtils.isNotBlank(comunicacao.getAcuidadeAuditivaObs())) {
                localBuilder.append(" , obs.: " + comunicacao.getAcuidadeAuditivaObs());
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(comunicacao.getDor())) {

            localBuilder.append("Dor: ");
            switch (comunicacao.getDor()) {
                case "AU":
                    localBuilder.append("ausente");
                    break;
                case "PR":
                    localBuilder.append("presente");
                    break;
                case "NM":
                    localBuilder.append("não mensurável");
                    break;
                default:
                    localBuilder.append("não aplica");
            }

            if (StringUtils.isNotBlank(comunicacao.getDorObs())) {
                localBuilder.append(" , obs.: " + comunicacao.getDorObs());
            }

            localBuilder.append("; ");
        }

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** COMUNICAÇÃO E PERCEPÇÃO ***\n");
            builder.append(localBuilder.toString());
        }
    }

    public void acrescentaDadosCuidadoCorporal(StringBuilder builder, CuidadoCorporal cuidado) {

        StringBuilder localBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(cuidado.getAutocuidadoPreservado())) {
            localBuilder.append("Capacidade para Autocuidado: ");

            switch (cuidado.getAutocuidadoPreservado()) {
                case "PS":
                    localBuilder.append("preservada");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada");
                    String separadorOpcoes = " ";
                    if (Boolean.TRUE.equals(cuidado.getAlimentacao())) {
                        localBuilder.append(" alimentação");
                        separadorOpcoes = ", ";
                    }
                    if (Boolean.TRUE.equals(cuidado.getBanho())) {
                        localBuilder.append(separadorOpcoes + "banho");
                        separadorOpcoes = ", ";
                    }
                    if (Boolean.TRUE.equals(cuidado.getHigieneIntima())) {
                        localBuilder.append(separadorOpcoes + "higiene íntima");
                        separadorOpcoes = ", ";
                    }
                    if (Boolean.TRUE.equals(cuidado.getVestir())) {
                        localBuilder.append(separadorOpcoes + "vestir-se");
                    }
                    break;
                default:
                    localBuilder.append("não aplica");
                    break;
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(cuidado.getPadraoSono())) {
            localBuilder.append("Padrão do Sono e Repouso: ");

            switch (cuidado.getPadraoSono()) {
                case "PS":
                    localBuilder.append("preservada");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada");
                    break;
                default:
                    localBuilder.append("não aplica");
                    break;
            }

            if (StringUtils.isNotBlank(cuidado.getPadraoSonoObs())) {
                localBuilder.append(" , obs.: " + cuidado.getPadraoSonoObs());
            }
            localBuilder.append("; ");
        }

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** CUIDADO CORPORAL, SONO E REPOUSO ***\n");
            builder.append(localBuilder.toString());
        }

    }

    public void acrescentaMobilidade(StringBuilder builder, Mobilidade mobilidade) {

        StringBuilder localBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(mobilidade.getMorse())) {
            localBuilder.append("MORSE: ");
            switch (mobilidade.getMorse()) {
                case "BR":
                    localBuilder.append("baixo risco (0-24); ");
                    break;
                case "MO":
                    localBuilder.append("moderado (25-44); ");
                    break;
                case "EL":
                    localBuilder.append("elevado (&#8805;45); ");
                    break;
                default:
                    break;
            }
        }

        if (StringUtils.isNotBlank(mobilidade.getLocomocao())) {
            localBuilder.append("Locomoção: ");
            switch (mobilidade.getLocomocao()) {
                case "DS":
                    localBuilder.append("deambula sem auxílio");
                    break;
                case "DA":
                    localBuilder.append("deambula com auxílio");
                    break;
                case "AC":
                    localBuilder.append("acamado");
                    break;
                default:
                    break;
            }

            if (Boolean.TRUE.equals(mobilidade.getDependenciaParcial())) {
                localBuilder.append(", dependência parcial");
            }

            if (Boolean.TRUE.equals(mobilidade.getDependenciaTotal())) {
                localBuilder.append(", dependência total");
            }

            if (StringUtils.isNotBlank(mobilidade.getLocomocaoObs())) {
                localBuilder.append(", obs.: ");
                localBuilder.append(mobilidade.getLocomocaoObs());
            }
            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(mobilidade.getMarcha())) {
            localBuilder.append("Marcha: ");
            switch (mobilidade.getMarcha()) {
                case "NO":
                    localBuilder.append("normal; ");
                    break;
                case "LE":
                    localBuilder.append("lenta; ");
                    break;
                case "CO":
                    localBuilder.append("comprometida; ");
                    break;
                default:
                    localBuilder.append("não aplica; ");
                    break;
            }
        }

        if (StringUtils.isNotBlank(mobilidade.getEquilibrio())) {
            localBuilder.append("Equilíbrio: ");
            switch (mobilidade.getEquilibrio()) {
                case "PS":
                    localBuilder.append("preservado; ");
                    break;
                case "PJ":
                    localBuilder.append("prejudicado; ");
                    break;
                default:
                    localBuilder.append("não aplica; ");
                    break;
            }
        }

        if (StringUtils.isNotBlank(mobilidade.getForcaMuscular())) {
            localBuilder.append("Força muscular: ");
            switch (mobilidade.getForcaMuscular()) {
                case "SA":
                    localBuilder.append("sem alteração");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada");
                    break;
                default:
                    localBuilder.append("não aplica");
                    break;
            }

            if (Boolean.TRUE.equals(mobilidade.getFraqueza())) {
                localBuilder.append(", fraqueza");
                if (StringUtils.isNotBlank(mobilidade.getFraquezaObs())) {
                    localBuilder.append(", Obs.: ");
                    localBuilder.append(mobilidade.getFraquezaObs());
                }

                if (Boolean.TRUE.equals(mobilidade.getPlegia())) {
                    localBuilder.append(", plegia");
                    if (StringUtils.isNotBlank(mobilidade.getPlegiaObs())) {
                        localBuilder.append(", Obs.: ");
                        localBuilder.append(mobilidade.getPlegiaObs());
                    }
                }
            }

            localBuilder.append("; ");
        }

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** MOBILIDADE ***\n");
            builder.append(localBuilder.toString());
        }
    }

    public void acrescentaNecessidadePsicossocial(StringBuilder builder, NecessidadePsicossocial necessidade) {

        StringBuilder localBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(necessidade.getEstadoEmocional())) {
            localBuilder.append("Estado emocional: ");
            switch (necessidade.getEstadoEmocional()) {
                case "CM":
                    localBuilder.append("calmo");
                    break;
                case "AN":
                    localBuilder.append("ansioso");
                    break;
                case "AG":
                    localBuilder.append("agressivo");
                    break;
                case "CL":
                    localBuilder.append("calado");
                    break;
                case "CH":
                    localBuilder.append("choroso");
                    break;
                default:
                    localBuilder.append("não aplica");
                    break;
            }

            if (StringUtils.isNotBlank(necessidade.getEstadoEmocionalOutro())) {
                localBuilder.append(", outro: " + necessidade.getEstadoEmocionalOutro());
            }

            if (StringUtils.isNotBlank(necessidade.getEstadoEmocionalObs())) {
                localBuilder.append(", obs.: " + necessidade.getEstadoEmocionalObs());
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(necessidade.getNivelColaboracao())) {
            localBuilder.append("Nível de Colaboração: ");

            switch (necessidade.getNivelColaboracao()) {
                case "TO":
                    localBuilder.append("total; ");
                    break;
                case "PA":
                    localBuilder.append("parcial; ");
                    break;
                case "NE":
                    localBuilder.append("nenhum; ");
                    break;
                default:
                    localBuilder.append("não aplica; ");
                    break;
            }
        }

        if (StringUtils.isNotBlank(necessidade.getAprendizagem())) {
            localBuilder.append("Aprendizagem: ");

            switch (necessidade.getNivelColaboracao()) {
                case "PS":
                    localBuilder.append("preservada; ");
                    break;
                case "PJ":
                    localBuilder.append("prejudicada; ");
                    break;
                default:
                    localBuilder.append("não aplica; ");
                    break;
            }
        }

        if (necessidade.getAcompanhante() != null) {
            localBuilder.append("Acompanhante: ");
            if (Boolean.TRUE.equals(necessidade.getAcompanhante())) {
                localBuilder.append("sim");
            } else {
                localBuilder.append("não");
            }

            if (necessidade.getAcompanhantePernoite() != null) {
                localBuilder.append(", pernoite: ");
                if (Boolean.TRUE.equals(necessidade.getAcompanhantePernoite())) {
                    localBuilder.append("sim");
                } else {
                    localBuilder.append("não");
                }
            }

            if (StringUtils.isNotBlank(necessidade.getAcompanhanteObs())) {
                localBuilder.append(", obs.: " + necessidade.getAcompanhanteObs());
            }

            localBuilder.append("; ");
        }

        if (necessidade.getVisita() != null) {
            localBuilder.append("Visita: ");
            if (Boolean.TRUE.equals(necessidade.getVisita())) {
                localBuilder.append("sim; ");
            } else {
                localBuilder.append("não; ");
            }
        }

        if (necessidade.getNecessidadePsicoespiritual() != null) {
            localBuilder.append("Necessidade Psicoespiritual: ");
            if (Boolean.TRUE.equals(necessidade.getNecessidadePsicoespiritual())) {
                localBuilder.append("sim");
            } else {
                localBuilder.append("não");
            }

            if (StringUtils.isNotBlank(necessidade.getNecessidadePsicoespiritualObs())) {
                localBuilder.append(", obs.: " + necessidade.getNecessidadePsicoespiritualObs());
            }
            localBuilder.append("; ");
        }

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** NECESSIDADES PSICOSSOCIAL E PSICOESPIRITUAL ***\n");
            builder.append(localBuilder.toString());
        }
    }

    public void acrescentaAlergiaMedicamento(StringBuilder builder, AlergiaMedicamento alergia) {

        StringBuilder localBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(alergia.getAlergias())) {
            localBuilder.append("Alergias: ");
            switch (alergia.getAlergias()) {
                case "NA":
                    localBuilder.append("não");
                    break;
                case "SI":
                    localBuilder.append("sim");
                    break;
                default:
                    localBuilder.append("não documentado/relatado");
                    break;
            }

            if (StringUtils.isNotBlank(alergia.getAlergiasObs())) {
                localBuilder.append(", obs.: " + alergia.getAlergiasObs());
            }

            localBuilder.append("; ");
        }

        if (StringUtils.isNotBlank(alergia.getMedicamentosVigilancia())) {
            localBuilder.append("Medicamentos Alta Vigilância: " + alergia.getMedicamentosVigilancia() + "; ");
        }

        if (localBuilder != null && localBuilder.length() > 0) {
            builder.append("\n\n");
            builder.append("*** ALERGIAS E MEDICAMENTOS ***\n");
            builder.append(localBuilder.toString());
        }
    }

    public void acrescentaInformacoesComplementares(StringBuilder builder, String informacoes) {
        builder.append("\n\n");
        builder.append("*** OUTROS DADOS E INVESTIGAÇÃO ***\n");

        builder.append(informacoes + ";");
    }

    public void acrescentaProcedimentosRealizados(StringBuilder builder, String procedimentos) {
        builder.append("\n\n");
        builder.append("*** PROCEDIMENTOS REALIZADOS, ORIENTAÇÕES E RESPOSTA DO PACIENTE ***\n");

        builder.append(procedimentos + ";");
    }

    public void acrescentaDiagnosticosEnfermagem(StringBuilder builder, String diagnosticos) {
        builder.append("\n\n");
        builder.append("*** DIAGNÓSTICOS DE ENFERMAGEM PRIORITÁRIOS ***\n");

        builder.append(diagnosticos + ";");
    }

    public void acrescentaCondutas(StringBuilder builder, String condutas) {
        builder.append("\n\n");
        builder.append("*** CONDUTAS ***\n");

        builder.append(condutas + ";");
    }

    public String getTextoEvolucao() {
        return textoEvolucao;
    }

    public void setTextoEvolucao(String textoEvolucao) {
        this.textoEvolucao = textoEvolucao;
    }

}

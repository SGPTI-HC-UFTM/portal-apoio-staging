package net.ebserh.hctm.controller.utineonatalpediatrica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.model.utineonatalpediatrica.CalculadoraNeonatal;
import net.ebserh.hctm.model.utineonatalpediatrica.ResultadoCalculadoraNeonatal;
import net.ebserh.hctm.service.utineonatalpediatrica.CalculadoraNeonatalService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class CalculadoraNeonatalController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private CalculadoraNeonatalService calculadoraNeonatalService;
    private CalculadoraNeonatal calculadoraNeonatal;
    private ResultadoCalculadoraNeonatal resultadoCalculadoraNeonatal;
    private LocalDate dataHoje;

    @PostConstruct
    public void init() {
        calculadoraNeonatal = new CalculadoraNeonatal();
        resultadoCalculadoraNeonatal = new ResultadoCalculadoraNeonatal();

        calculadoraNeonatal.setHemotransfusaoAntesPrimeiraColeta(false);
        calculadoraNeonatal.setHemotransfusaoAposPrimeiraColeta(false);
        calculadoraNeonatal.setColetasRealizadas(new ArrayList<>(Collections.nCopies(4, null)));

        dataHoje = LocalDate.now();
    }

    public void calcular() {

        if (this.calculadoraNeonatal == null) {
            FacesUtils.showError("É necessário preencher o formulário.");
        }

        resultadoCalculadoraNeonatal = calculadoraNeonatalService.calcular(calculadoraNeonatal);
    }

    public CalculadoraNeonatal getCalculadoraNeonatal() {
        return calculadoraNeonatal;
    }

    public void setCalculadoraNeonatal(CalculadoraNeonatal calculadoraNeonatal) {
        this.calculadoraNeonatal = calculadoraNeonatal;
    }

    public ResultadoCalculadoraNeonatal getResultadoCalculadoraNeonatal() {
        return resultadoCalculadoraNeonatal;
    }

    public void setResultadoCalculadoraNeonatal(ResultadoCalculadoraNeonatal resultadoCalculadoraNeonatal) {
        this.resultadoCalculadoraNeonatal = resultadoCalculadoraNeonatal;
    }

    public LocalDate getDataHoje() {
        return dataHoje;
    }
}

package net.ebserh.hctm.controller.utineonatalpediatrica;

import java.io.Serializable;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import net.ebserh.hctm.model.utineonatalpediatrica.CalculoTestePezinho;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class CalculadoraTestePezinhoController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private CalculoTestePezinho calculoTestePezinho;

    @PostConstruct
    public void init() {
        calculoTestePezinho = new CalculoTestePezinho();
    }

    public CalculoTestePezinho getCalculoTestePezinho() {
        return calculoTestePezinho;
    }

    public void setCalculoTestePezinho(CalculoTestePezinho calculoTestePezinho) {
        this.calculoTestePezinho = calculoTestePezinho;
    }

    public void calcularDatas() {
        if (calculoTestePezinho == null) {
            FacesUtils.showError("É necessário preencher todos so campos obrigatórios.");
            return;
        }
        FacesUtils.showInfo("Calculo realizado com sucesso!");
    }
}

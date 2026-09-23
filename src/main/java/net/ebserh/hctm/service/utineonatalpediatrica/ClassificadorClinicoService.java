package net.ebserh.hctm.service.utineonatalpediatrica;

import net.ebserh.hctm.model.utineonatalpediatrica.CalculadoraNeonatal;
import net.ebserh.hctm.model.utineonatalpediatrica.CategoriaClinica;

public class ClassificadorClinicoService {

    public CategoriaClinica avaliar(CalculadoraNeonatal dados) {
        if (!dados.isIdadeGestacional()) {
            return new CategoriaClinica(4, "RN ESTÁVEL ou INSTÁVEL com IG < 32 semanas", 4);
        }

        if (dados.isIdadeGestacional() && !dados.isPesoAoNascer()) {
            return new CategoriaClinica(3, "RN ESTÁVEL ou INSTÁVEL com peso < 1500g e IG ≥ 32 semanas", 3);
        }

        if (dados.isIdadeGestacional() && dados.isPesoAoNascer() && !dados.isEstabilidadeRecemNascido()) {
            return new CategoriaClinica(2, "RN INSTÁVEL com peso ≥ 1500g e IG ≥ 32 semanas", 2);
        }

        return new CategoriaClinica(1, "RN ESTÁVEL com peso ≥ 1500g e IG ≥ 32 semanas", 1);
    }
}

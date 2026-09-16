package net.ebserh.hctm.service.utineonatalpediatrica;

import net.ebserh.hctm.model.utineonatalpediatrica.CalculadoraNeonatal;
import net.ebserh.hctm.model.utineonatalpediatrica.CategoriaClinica;

public class ClassificadorClinicoService {

    public CategoriaClinica avaliar(CalculadoraNeonatal dados) {

        // Idade gestacional menor que 32 semanas
        if (!dados.isIdadeGestacional()) {
            return new CategoriaClinica(4, "RN ESTÁVEL ou INSTÁVEL com IG < 32 semanas", 4);
        }

        // Idade gestacional maior ou igual a 32 semanas e
        // peso ao nascer menor que 1500g
        if (dados.isIdadeGestacional() && !dados.isPesoAoNascer()) {
            return new CategoriaClinica(3, "RN ESTÁVEL ou INSTÁVEL com peso < 1500g e IG ≥ 32 semanas", 3);
        }

        // Idade gestacional maior ou igual a 32 semanas e
        // peso ao nascer maior ou igual a 1500g e RN instável
        if (dados.isIdadeGestacional() && dados.isPesoAoNascer() && !dados.isEstabilidadeRecemNascido()) {
            return new CategoriaClinica(2, "RN INSTÁVEL com peso ≥ 1500g e IG ≥ 32 semanas", 2);
        }

        // Idade gestacional maior ou igual a 32 semanas e
        // peso ao nascer maior ou igual a 1500g e RN estável
        return new CategoriaClinica(1, "RN ESTÁVEL com peso ≥ 1500g e IG ≥ 32 semanas", 1);
    }
}

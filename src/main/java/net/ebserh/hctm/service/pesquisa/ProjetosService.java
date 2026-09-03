package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.FonteFinanciadora;
import net.ebserh.hctm.model.pesquisa.Projeto;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.model.pesquisa.TipoProjeto;

import java.util.List;

@Stateless
public class ProjetosService {

    public void salvaProjeto(Projeto projeto) {
        throw new CustomRuntimeException("Em construção...");
    }

    public List<Projeto> buscaProjetosPorTitulo(String titulo) {
        throw new CustomRuntimeException("Em construção...");
    }

    public List<TipoProjeto> buscaTiposProjeto() {
        throw new CustomRuntimeException("Em construção...");
    }

    public List<StatusProjeto> buscaStatusProjeto() {
        throw new CustomRuntimeException("Em construção...");
    }

    public List<FonteFinanciadora> buscaFontesFinanciadoras() {
        throw new CustomRuntimeException("Em construção...");
    }

}

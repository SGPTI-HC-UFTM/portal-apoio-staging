package net.ebserh.hctm.dto.aghu.exames;

public class ProfissionalExecutanteDto {
    
    private String nome;

    private String cbo;

    private String cns;

    private Long cpf;

    private Integer crm;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCbo() {
        return this.cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }

    public String getCns() {
        return this.cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public Long getCpf() {
        return this.cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

}

CREATE SCHEMA triagens;

CREATE TABLE triagens.neonatal
(
    seq SERIAL NOT NULL,
    alergiaintoleranciaalimentar character varying(255) COLLATE pg_catalog."default",
    comorbidades character varying(255) COLLATE pg_catalog."default",
    diagnosticos character varying(255) COLLATE pg_catalog."default",
    doencacondicaoclinica character varying(255) COLLATE pg_catalog."default",
    escoretotal integer,
    idadecorr character varying(255) COLLATE pg_catalog."default",
    idadecron character varying(255) COLLATE pg_catalog."default",
    idadegestacional character varying(255) COLLATE pg_catalog."default",
    ig character varying(255) COLLATE pg_catalog."default",
    nivelassistencianutricional character varying(255) COLLATE pg_catalog."default",
    pesonascimento character varying(255) COLLATE pg_catalog."default",
    risconutricional character varying(255) COLLATE pg_catalog."default",
    terapianutricional character varying(255) COLLATE pg_catalog."default",
    inter_codigo integer,
    pac_codigo integer,
    atd_codigo integer,
    presc_codigo integer,
    PRIMARY KEY (seq),
    FOREIGN KEY (atd_codigo, presc_codigo) REFERENCES agh.mpm_prescricao_dietas (atd_seq, seq),
    FOREIGN KEY (inter_codigo) REFERENCES agh.ain_internacoes (seq),
    FOREIGN KEY (pac_codigo) REFERENCES agh.aip_pacientes (codigo)
)
IF (NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'aplicacoes')) 
BEGIN
    EXEC ('CREATE SCHEMA [aplicacoes]')
END

create table aplicacoes.aplicacao(
	id       	    bigint    primary key,
	id_vaga         bigint       not null,
	id_candidato	bigint       not null

	);
	
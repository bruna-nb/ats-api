IF (NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'candidatos')) 
BEGIN
    EXEC ('CREATE SCHEMA [candidatos]')
END

create table candidatos.candidato(
	id       	    bigint    primary key,
	nome    	    varchar(100) not null,
	senha			varchar(max) not null,
	cpf      	    varchar(11)  not null,
	email 	 	    varchar(100) not null,
	telefone 	    varchar(20),
	data_nascimento timestamp,
	curriculo		varbinary(max)
	);

create table candidatos.experiencia(
	candidato_id 	bigint    REFERENCES candidatos.candidato(id),
	experiencia_id  varchar(100)  not null,
	cargo    	    varchar(200)  not null,
	nomeEmpresa 	varchar(200)  not null,
	dataInicio 	    datetime,
	dataFim   		datetime,
	descricao 	    varchar(max),
	);
	
create table candidatos.referencia(
	candidato_id    bigint	     REFERENCES candidatos.candidato(id),
	referencia_id   varchar(100) not null,
	nome			varchar(100) not null,
	email			varchar(100) not null
	);
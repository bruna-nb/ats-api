IF (NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'vagas')) 
BEGIN
    EXEC ('CREATE SCHEMA [vagas]')
END

create table vagas.vaga(
	id       	    bigint    primary key,
	id_recrutador   bigint       not null,
	titulo			varchar(100) not null,
	descricao   	varchar(max),
	requisitos   	varchar(max)

	);
	
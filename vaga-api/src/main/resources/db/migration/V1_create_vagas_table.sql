IF (NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'recrutadores')) 
BEGIN
    EXEC ('CREATE SCHEMA [recrutadores]')
END

create table recrutadores.recrutador(
	id       	    bigint    primary key,
	id_recrutador   bigint       not null,
	titulo			varchar(100) not null,
	descricao   	varchar(max),
	requisitos   	varchar(max)

	);
	
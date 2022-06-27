IF (NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'recrutadores')) 
BEGIN
    EXEC ('CREATE SCHEMA [recrutadores]')
END

create table recrutadores.recrutador(
	id       	    bigint    primary key,
	nome    	    varchar(100) not null,
	senha			varchar(max) not null,
	email 	 	    varchar(100) not null,
	);
	
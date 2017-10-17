CREATE DATABASE IF NOT EXISTS pizzaria;

USE pizzaria;

DROP TABLE `cliente`;

CREATE IF NOT EXISTS TABLE `cliente` ( 
	`id` Int( 11 ) UNSIGNED NOT NULL,
	`nome` VarChar( 127 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
	`endereco` VarChar( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
	`telefone` VarChar( 15 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
	`cpf` VarChar( 15 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
	PRIMARY KEY ( `id` ) )
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;

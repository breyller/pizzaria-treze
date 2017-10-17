CREATE DATABASE IF NOT EXISTS pizzaria;

USE pizzaria;

DROP TABLE IF EXISTS `cliente` CASCADE;

CREATE IF NOT EXISTS TABLE `cliente` ( 
	`id` INT(11) UNSIGNED NOT NULL,
	`nome` VARCHAR(127) NOT NULL,
	`endereco` VARCHAR(255) NOT NULL,
	`telefone` VARCHAR(15) NOT NULL,
	`cpf` VARCHAR(15) NOT NULL,
	PRIMARY KEY ( `id` ) )
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;


DROP TABLE IF EXISTS `funcionario` CASCADE;

CREATE TABLE `funcionario` ( 
	`id` INT(11) UNSIGNED NOT NULL,
	`nome` VARCHAR( 127 ) NOT NULL,
	`endereco` VARCHAR( 255 ) NOT NULL,
	`telefone` VARCHAR( 15 ) NOT NULL,
	`cpf` VARCHAR( 15 ) NOT NULL,
	`cargo` VARCHAR( 31 ) NOT NULL,
	PRIMARY KEY ( `id` ) )
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;

DROP TABLE IF EXISTS `mesa` CASCADE;

CREATE TABLE `mesa` ( 
	`id` INT(11) UNSIGNED NOT NULL,
	`numero` SMALLINT(5) UNSIGNED NOT NULL,
	`qtd_lugares` TINYINT(3) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`))
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;


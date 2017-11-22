CREATE DATABASE IF NOT EXISTS pizzaria;

USE pizzaria;

DROP TABLE IF EXISTS `cliente` CASCADE;

CREATE TABLE `cliente` (
	`id` INT(11) UNSIGNED NOT NULL,
	`nome` VARCHAR(127) NOT NULL,
	`endereco` VARCHAR(255) NOT NULL,
	`telefone` VARCHAR(15) NOT NULL,
	`cpf` VARCHAR(15) NOT NULL,
	PRIMARY KEY (`id`))
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;


DROP TABLE IF EXISTS `funcionario` CASCADE;

CREATE TABLE `funcionario` (
	`id` INT(11) UNSIGNED NOT NULL,
	`nome` VARCHAR(127) NOT NULL,
	`endereco` VARCHAR(255) NOT NULL,
	`telefone` VARCHAR(15) NOT NULL,
	`cpf` VARCHAR(15) NOT NULL,
	`cargo` VARCHAR(31) NOT NULL,
	PRIMARY KEY (`id`))
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

DROP TABLE IF EXISTS `reserva` CASCADE;

CREATE TABLE `reserva` (
	`id` INT(11) UNSIGNED NOT NULL,
	`data` DATETIME NOT NULL,
	`status` TINYINT(3) NOT NULL,
	`composicao` VARCHAR(511) NOT NULL,
	PRIMARY KEY (`id`))
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;

DROP TABLE IF EXISTS `ingrediente` CASCADE;

CREATE TABLE `ingrediente` (
	`id` INT(11) UNSIGNED NOT NULL,
	`preco` DOUBLE(2, 0) NOT NULL,
	`descricao` VARCHAR(255) NOT NULL,
	`quantidade` INT(11) UNSIGNED NOT NULL,
	`nome` VARCHAR(63) NOT NULL,
	PRIMARY KEY (`id`))
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;

DROP TABLE IF EXISTS `produto` CASCADE;

CREATE TABLE `produto` (
	`id` INT(11) UNSIGNED NOT NULL,
	`preco` DOUBLE(2, 0) NOT NULL,
	`nome` VARCHAR(127) NOT NULL,
	`composicao` VARCHAR(511) NOT NULL,
	PRIMARY KEY (`id`))
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;

DROP TABLE IF EXISTS `receita` CASCADE;

CREATE TABLE `receita` (
	`id` INT(11) UNSIGNED NOT NULL,
	`id_produto` INT(11) UNSIGNED NOT NULL,
	`id_ingrediente` INT(11) UNSIGNED NOT NULL,
	`quantidade` INT(11) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`))
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;

DROP TABLE IF EXISTS `pedido` CASCADE;

CREATE TABLE `pedido` (
	`id` BIGINT(20) UNSIGNED NOT NULL,
	`data` DATETIME NOT NULL,
	`status` TINYINT(3) NOT NULL,
	`descricao` VARCHAR(255) NOT NULL,
	`tipo` TINYINT(3) NOT NULL,
	`preco` DOUBLE(2, 0) NOT NULL,
	`id_cliente` INT(11) UNSIGNED NOT NULL,
	`id_funcionario` INT(11) UNSIGNED NOT NULL,
	`composicao` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`))
CHARACTER SET = latin1
COLLATE = latin1_swedish_ci
ENGINE = InnoDB;

ALTER TABLE `receita`
	ADD CONSTRAINT `fk_ingrediente_receita` FOREIGN KEY (`id_ingrediente`)
	REFERENCES `ingrediente`(`id`)
	ON DELETE CASCADE
	ON UPDATE CASCADE;

ALTER TABLE `receita`
	ADD CONSTRAINT `fk_produto_receita` FOREIGN KEY (`id_produto`)
	REFERENCES `produto`(`id`)
	ON DELETE CASCADE
	ON UPDATE CASCADE;

ALTER TABLE `pedido`
	ADD CONSTRAINT `fk_cliente_pedido` FOREIGN KEY (`id_cliente`)
	REFERENCES `cliente`(`id`)
	ON DELETE CASCADE
	ON UPDATE CASCADE;


ALTER TABLE `pedido`
	ADD CONSTRAINT `fk_funcionario_pedido` FOREIGN KEY (`id_funcionario`)
	REFERENCES `funcionario`(`id`)
	ON DELETE CASCADE
	ON UPDATE CASCADE;

ALTER TABLE `funcionario` ADD COLUMN `salario` DOUBLE NOT NULL;

ALTER TABLE `mesa` ADD COLUMN `reservas` TEXT NULL;

ALTER TABLE `reserva` ADD COLUMN `nome` VARCHAR(255) NOT NULL;

ALTER TABLE `produto` ADD COLUMN `descricao` VarChar(255) NULL;
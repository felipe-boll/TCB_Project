-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`table1` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pessoa` (
  `idpessoa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `cpf` VARCHAR(16) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `idade` INT NOT NULL,
  PRIMARY KEY (`idpessoa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `pessoa_idpessoa` INT NOT NULL,
  PRIMARY KEY (`idusuario`),
  INDEX `fk_usuario_pessoa_idx` (`pessoa_idpessoa` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_pessoa`
    FOREIGN KEY (`pessoa_idpessoa`)
    REFERENCES `mydb`.`pessoa` (`idpessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`estilo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estilo` (
  `idestilo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idestilo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`musica` (
  `idmusica` INT NOT NULL AUTO_INCREMENT,
  `dificuldade` INT NOT NULL,
  `duracao` TIME NOT NULL,
  `letra` VARCHAR(40200) NOT NULL,
  `estilo_idestilo` INT NOT NULL,
  PRIMARY KEY (`idmusica`),
  INDEX `fk_musica_estilo1_idx` (`estilo_idestilo` ASC) VISIBLE,
  CONSTRAINT `fk_musica_estilo1`
    FOREIGN KEY (`estilo_idestilo`)
    REFERENCES `mydb`.`estilo` (`idestilo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`instrumento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`instrumento` (
  `idinstrumento` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(1000) NULL,
  `musica_idmusica` INT NOT NULL,
  PRIMARY KEY (`idinstrumento`),
  INDEX `fk_instrumento_musica1_idx` (`musica_idmusica` ASC) VISIBLE,
  CONSTRAINT `fk_instrumento_musica1`
    FOREIGN KEY (`musica_idmusica`)
    REFERENCES `mydb`.`musica` (`idmusica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`agencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`agencia` (
  `idagencia` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idagencia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`banda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`banda` (
  `idbanda` INT NOT NULL AUTO_INCREMENT,
  `agencia_idagencia` INT NOT NULL,
  PRIMARY KEY (`idbanda`),
  INDEX `fk_banda_agencia1_idx` (`agencia_idagencia` ASC) VISIBLE,
  CONSTRAINT `fk_banda_agencia1`
    FOREIGN KEY (`agencia_idagencia`)
    REFERENCES `mydb`.`agencia` (`idagencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cantor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cantor` (
  `idcantor` INT NOT NULL AUTO_INCREMENT,
  `pessoa_idpessoa` INT NOT NULL,
  `banda_idbanda` INT NOT NULL,
  PRIMARY KEY (`idcantor`),
  INDEX `fk_cantor_pessoa1_idx` (`pessoa_idpessoa` ASC) VISIBLE,
  INDEX `fk_cantor_banda1_idx` (`banda_idbanda` ASC) VISIBLE,
  CONSTRAINT `fk_cantor_pessoa1`
    FOREIGN KEY (`pessoa_idpessoa`)
    REFERENCES `mydb`.`pessoa` (`idpessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cantor_banda1`
    FOREIGN KEY (`banda_idbanda`)
    REFERENCES `mydb`.`banda` (`idbanda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`banda_has_musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`banda_has_musica` (
  `banda_idbanda` INT NOT NULL,
  `musica_idmusica` INT NOT NULL,
  PRIMARY KEY (`banda_idbanda`, `musica_idmusica`),
  INDEX `fk_banda_has_musica_musica1_idx` (`musica_idmusica` ASC) VISIBLE,
  INDEX `fk_banda_has_musica_banda1_idx` (`banda_idbanda` ASC) VISIBLE,
  CONSTRAINT `fk_banda_has_musica_banda1`
    FOREIGN KEY (`banda_idbanda`)
    REFERENCES `mydb`.`banda` (`idbanda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_banda_has_musica_musica1`
    FOREIGN KEY (`musica_idmusica`)
    REFERENCES `mydb`.`musica` (`idmusica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

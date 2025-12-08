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
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`agencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`agencia` (
  `idagencia` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `cnpj` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`idagencia`))
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`banda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`banda` (
  `idbanda` INT NOT NULL AUTO_INCREMENT,
  `agencia_idagencia` INT NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idbanda`),
  INDEX `fk_banda_agencia1_idx` (`agencia_idagencia` ASC) VISIBLE,
  CONSTRAINT `fk_banda_agencia1`
    FOREIGN KEY (`agencia_idagencia`)
    REFERENCES `mydb`.`agencia` (`idagencia`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`musica` (
  `idmusica` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `dificuldade` DOUBLE NOT NULL,
  `duracao` TIME NOT NULL,
  `letra` TEXT NOT NULL,
  PRIMARY KEY (`idmusica`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


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
    REFERENCES `mydb`.`banda` (`idbanda`),
  CONSTRAINT `fk_banda_has_musica_musica1`
    FOREIGN KEY (`musica_idmusica`)
    REFERENCES `mydb`.`musica` (`idmusica`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`cantor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cantor` (
  `idcantor` INT NOT NULL AUTO_INCREMENT,
  `banda_idbanda` INT NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `idade` INT NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcantor`),
  INDEX `fk_cantor_banda1_idx` (`banda_idbanda` ASC) VISIBLE,
  CONSTRAINT `fk_cantor_banda1`
    FOREIGN KEY (`banda_idbanda`)
    REFERENCES `mydb`.`banda` (`idbanda`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`estilo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estilo` (
  `idestilo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idestilo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`estilo_has_musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estilo_has_musica` (
  `estilo_idestilo` INT NOT NULL,
  `musica_idmusica` INT NOT NULL,
  PRIMARY KEY (`estilo_idestilo`, `musica_idmusica`),
  INDEX `fk_estilo_has_musica_musica1_idx` (`musica_idmusica` ASC) VISIBLE,
  INDEX `fk_estilo_has_musica_estilo1_idx` (`estilo_idestilo` ASC) VISIBLE,
  CONSTRAINT `fk_estilo_has_musica_estilo1`
    FOREIGN KEY (`estilo_idestilo`)
    REFERENCES `mydb`.`estilo` (`idestilo`),
  CONSTRAINT `fk_estilo_has_musica_musica1`
    FOREIGN KEY (`musica_idmusica`)
    REFERENCES `mydb`.`musica` (`idmusica`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`instrumento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`instrumento` (
  `idinstrumento` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`idinstrumento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`musica_has_instrumento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`musica_has_instrumento` (
  `musica_idmusica` INT NOT NULL,
  `instrumento_idinstrumento` INT NOT NULL,
  PRIMARY KEY (`musica_idmusica`, `instrumento_idinstrumento`),
  INDEX `fk_musica_has_instrumento_instrumento1_idx` (`instrumento_idinstrumento` ASC) VISIBLE,
  INDEX `fk_musica_has_instrumento_musica1_idx` (`musica_idmusica` ASC) VISIBLE,
  CONSTRAINT `fk_musica_has_instrumento_instrumento1`
    FOREIGN KEY (`instrumento_idinstrumento`)
    REFERENCES `mydb`.`instrumento` (`idinstrumento`),
  CONSTRAINT `fk_musica_has_instrumento_musica1`
    FOREIGN KEY (`musica_idmusica`)
    REFERENCES `mydb`.`musica` (`idmusica`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `objetivo` VARCHAR(450) NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `idade` INT NOT NULL,
  PRIMARY KEY (`idusuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`usuario_has_musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario_has_musica` (
  `usuario_idusuario` INT NOT NULL,
  `musica_idmusica` INT NOT NULL,
  PRIMARY KEY (`usuario_idusuario`, `musica_idmusica`),
  INDEX `fk_usuario_has_musica_musica1_idx` (`musica_idmusica` ASC) VISIBLE,
  INDEX `fk_usuario_has_musica_usuario1_idx` (`usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_musica_musica1`
    FOREIGN KEY (`musica_idmusica`)
    REFERENCES `mydb`.`musica` (`idmusica`),
  CONSTRAINT `fk_usuario_has_musica_usuario1`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `mydb`.`usuario` (`idusuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

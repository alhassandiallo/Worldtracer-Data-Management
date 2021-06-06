SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema litige
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `litige` ;

-- -----------------------------------------------------
-- Schema litige
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `litige` DEFAULT CHARACTER SET utf8 ;
USE `litige` ;

-- -----------------------------------------------------
-- Table `litige`.`Agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`Agent` ;

CREATE TABLE IF NOT EXISTS `litige`.`Agent` (
  `agentid` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`agentid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `litige`.`Claim`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`Claim` ;

CREATE TABLE IF NOT EXISTS `litige`.`Claim` (
  `claimid` VARCHAR(10) NOT NULL,
  `passengername` VARCHAR(45) NULL,
  `dateclaim` DATETIME NOT NULL,
  `numberofbags` INT NOT NULL,
  `Agent_agentid` INT NOT NULL,
  `Delivery_deliveryID` INT NULL,
  PRIMARY KEY (`claimid`),
  INDEX `fk_ClaimReport_Agent1_idx` (`Agent_agentid` ASC) VISIBLE,
  INDEX `fk_ClaimReport_Delivery1_idx` (`Delivery_deliveryID` ASC) VISIBLE,
  CONSTRAINT `fk_ClaimReport_Agent1`
    FOREIGN KEY (`Agent_agentid`)
    REFERENCES `litige`.`Agent` (`agentid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ClaimReport_Delivery1`
    FOREIGN KEY (`Delivery_deliveryID`)
    REFERENCES `litige`.`Delivery` (`deliveryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `litige`.`Delivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`Delivery` ;

CREATE TABLE IF NOT EXISTS `litige`.`Delivery` (
  `deliveryID` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `tagNumber` VARCHAR(10) NULL,
  `weight` DECIMAL(10) NOT NULL,
  `nameDelivered` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(15) NULL,
  `address` VARCHAR(45) NOT NULL,
  `Agent_agentid` INT NOT NULL,
  PRIMARY KEY (`deliveryID`, `date`),
  INDEX `fk_Delivery_Agent1_idx` (`Agent_agentid` ASC) VISIBLE,
  CONSTRAINT `fk_Delivery_Agent1`
    FOREIGN KEY (`Agent_agentid`)
    REFERENCES `litige`.`Agent` (`agentid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `litige`.`Destination`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`Destination` ;

CREATE TABLE IF NOT EXISTS `litige`.`Destination` (
  `destID` INT NOT NULL,
  `codest` VARCHAR(10) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`destID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `litige`.`Flight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`Flight` ;

CREATE TABLE IF NOT EXISTS `litige`.`Flight` (
  `codeflight` VARCHAR(10) NOT NULL,
  `dateflight` DATETIME NOT NULL,
  `numberofbag` INT NOT NULL,
  PRIMARY KEY (`codeflight`, `dateflight`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `litige`.`Luggage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`Luggage` ;

CREATE TABLE IF NOT EXISTS `litige`.`Luggage` (
  `numBag` INT NOT NULL,
  `tagNumber` VARCHAR(10) NULL,
  `nameOnTag` VARCHAR(45) NULL,
  `receivedweight` DECIMAL(5) NOT NULL,
  `typeOfBag` VARCHAR(10) NOT NULL,
  `color` VARCHAR(10) NOT NULL,
  `stateOfBag` VARCHAR(45) NOT NULL,
  `claimID` VARCHAR(10) NULL,
  `coFlight` VARCHAR(10) NOT NULL,
  `codePilf` VARCHAR(10) NULL,
  `agentID` INT NOT NULL,
  `deliveryID` INT NULL,
  `coSendBag` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`numBag`),
  INDEX `fk_Bagage_ClaimReport1_idx` (`claimID` ASC) VISIBLE,
  INDEX `fk_Bagage_Flight1_idx` (`coFlight` ASC) VISIBLE,
  INDEX `fk_Bagage_PilferageReport1_idx` (`codePilf` ASC) VISIBLE,
  INDEX `fk_Bagage_Agent1_idx` (`agentID` ASC) VISIBLE,
  INDEX `fk_Luggage_Delivery1_idx` (`deliveryID` ASC) VISIBLE,
  INDEX `fk_Luggage_SendBag1_idx` (`coSendBag` ASC) VISIBLE,
  CONSTRAINT `fk_Bagage_ClaimReport1`
    FOREIGN KEY (`claimID`)
    REFERENCES `litige`.`Claim` (`claimid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bagage_Flight1`
    FOREIGN KEY (`coFlight`)
    REFERENCES `litige`.`Flight` (`codeflight`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bagage_PilferageReport1`
    FOREIGN KEY (`codePilf`)
    REFERENCES `litige`.`Pilferage` (`codepilf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bagage_Agent1`
    FOREIGN KEY (`agentID`)
    REFERENCES `litige`.`Agent` (`agentid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Luggage_Delivery1`
    FOREIGN KEY (`deliveryID`)
    REFERENCES `litige`.`Delivery` (`deliveryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Luggage_SendBag1`
    FOREIGN KEY (`coSendBag`)
    REFERENCES `litige`.`SendBag` (`codeSendBag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `litige`.`Pilferage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`Pilferage` ;

CREATE TABLE IF NOT EXISTS `litige`.`Pilferage` (
  `codepilf` VARCHAR(10) NOT NULL,
  `passengername` VARCHAR(45) NOT NULL,
  `tagNumber` VARCHAR(10) NOT NULL,
  `weight` DECIMAL(5) NOT NULL,
  `deliveredWeight` DECIMAL(5) NOT NULL,
  `Agent_agentid` INT NOT NULL,
  `reason` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codepilf`),
  INDEX `fk_PilferageReport_Agent1_idx` (`Agent_agentid` ASC) VISIBLE,
  CONSTRAINT `fk_PilferageReport_Agent1`
    FOREIGN KEY (`Agent_agentid`)
    REFERENCES `litige`.`Agent` (`agentid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `litige`.`SendBag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `litige`.`SendBag` ;

CREATE TABLE IF NOT EXISTS `litige`.`SendBag` (
  `codeSendBag` VARCHAR(10) NOT NULL,
  `dateReturn` DATETIME NOT NULL,
  `tagRush` VARCHAR(10) NOT NULL,
  `weight` DECIMAL(5) NOT NULL,
  `Destination_codest` VARCHAR(10) NOT NULL,
  `Agent_agentid` INT NOT NULL,
  PRIMARY KEY (`codeSendBag`),
  INDEX `fk_ReturnBag_Destination1_idx` (`Destination_codest` ASC) VISIBLE,
  INDEX `fk_ReturnBag_Agent1_idx` (`Agent_agentid` ASC) VISIBLE,
  CONSTRAINT `fk_ReturnBag_Destination1`
    FOREIGN KEY (`Destination_codest`)
    REFERENCES `litige`.`Destination` (`codest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReturnBag_Agent1`
    FOREIGN KEY (`Agent_agentid`)
    REFERENCES `litige`.`Agent` (`agentid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

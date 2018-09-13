create database springmvcdb;

use springmvcdb;

CREATE TABLE `combo_fields` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `combo_options` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SEC` int(11) DEFAULT NULL,
  `VALUE` varchar(255) DEFAULT NULL,
  `comboBoxField_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKs3fj2od3vrebabigf1qstxa20` (`comboBoxField_ID`)
);

CREATE TABLE `combo_prop_values` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OBJECT_ID` int(11) DEFAULT NULL,
  `PROP_ID` int(11) DEFAULT NULL,
  `VALUE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `combo_props` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `SEC` int(11) DEFAULT NULL,
  `comboBoxField_ID` int(11) DEFAULT NULL,
  `stage_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKtfpntmd9647lguwswnaeu1la7` (`comboBoxField_ID`),
  KEY `FKbchv0dxu4gu7vkvmvidnyrkpp` (`stage_ID`)
);

CREATE TABLE `date_prop_values` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OBJECT_ID` int(11) DEFAULT NULL,
  `PROP_ID` int(11) DEFAULT NULL,
  `VALUE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `date_props` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `SEC` int(11) DEFAULT NULL,
  `stage_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKrx6n8009edoorkni6owh5eg` (`stage_ID`)
);

CREATE TABLE `objects` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTIVE_STAGE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `stages` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `SEC` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `text_prop_values` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OBJECT_ID` int(11) DEFAULT NULL,
  `PROP_ID` int(11) DEFAULT NULL,
  `VALUE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `text_props` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LENGTH` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `SEC` int(11) DEFAULT NULL,
  `stage_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKqm8j42mj4v34qb4vd4dewj7vt` (`stage_ID`)
);

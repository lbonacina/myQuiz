-- phpMyAdmin SQL Dump
-- version 3.3.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generato il: 07 mar, 2013 at 04:11 AM
-- Versione MySQL: 5.1.36
-- Versione PHP: 5.3.14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `quiz`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `access_log`
--

DROP TABLE IF EXISTS `access_log`;
CREATE TABLE IF NOT EXISTS `access_log` (
  `id`  INT NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `fullUserName` varchar(255) DEFAULT NULL,
  `event` varchar(255) NOT NULL,  
  `reason` varchar(255) DEFAULT NULL,
  `result` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `access_log`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `answer`
--

--DROP TABLE IF EXISTS `answer`;
--CREATE TABLE IF NOT EXISTS `answer` (
--  `id`  INT NOT NULL AUTO_INCREMENT,
--  `id_answer`  INT DEFAULT NULL,
--  `id_question`  INT DEFAULT NULL,
--  `quiz_submission_id`  INT DEFAULT NULL,
--  PRIMARY KEY (`id`),
--  KEY `FKABCA3FBE82B4AAAC` (`id_answer`),
--  KEY `FKABCA3FBEEE66F56B` (`id_question`),
--  KEY `FKABCA3FBE5D2014C0` (`quiz_submission_id`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `answer`
--



-- --------------------------------------------------------

--
-- Struttura della tabella `permission`
--

DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `id`  INT NOT NULL,
  `permission` varchar(100) NOT NULL,
  `description` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `possible_answer`
--

DROP TABLE IF EXISTS `possible_answer`;
CREATE TABLE IF NOT EXISTS `possible_answer` (
  `id`  INT NOT NULL AUTO_INCREMENT,
  `text` longtext NOT NULL,
  `correct` tinyint(1) NOT NULL,
  `id_question`  INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK44AC824CE97BEAF5` (`id_question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `possible_answer`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id`  INT NOT NULL AUTO_INCREMENT,
  `type` varchar(5) NOT NULL,
  `text` longtext NOT NULL,
  `id_quiz`  INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBA823BE697775615` (`id_quiz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `question`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `quiz`
--

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE IF NOT EXISTS `quiz` (
  `id`  INT NOT NULL AUTO_INCREMENT,
  `name` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `quiz`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` longtext NOT NULL,
  `status` int(1) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `id_quiz` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76508296CC63B15` (`id_quiz`),
  CONSTRAINT `FK76508296CC63B15` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Dump dei dati per la tabella `session`
--


-- --------------------------------------------------------



--
-- Struttura della tabella `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id`  INT NOT NULL,
  `role` varchar(100) NOT NULL,
  `description` longtext NOT NULL,  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `id_role`  INT NOT NULL,
  `id_permission`  INT NOT NULL,
  PRIMARY KEY (`id_role`,`id_permission`),
  KEY `FKBD40D538625146BF` (`id_role`),
  KEY `FKBD40D538CC9F345F` (`id_permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id`  INT NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(25) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(200) NOT NULL,
  `decryptedPassword` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `failed_login_attempt_count` int(11) NOT NULL,
  `pwd_change_on_next_login` tinyint(1) NOT NULL,
  `superadmin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `user`
--

insert into `user`(`id`,`firstName`,`lastName`,`email`,`phone`,`username`,`password`,`decryptedPassword`,`enabled`,`failed_login_attempt_count`,`pwd_change_on_next_login`,`superadmin`) values (1,'Jane','Doe','jane@super.com','2125551212','wakka','$shiro1$SHA-256$500000$no8bF4L6ou/rwcuv0/7qNw==$qOuLZWUSVBw6KJ+acoajJuDPeeoYoU33bdiYpQivvvo=',null,1,0,0,1);
insert into `user`(`id`,`firstName`,`lastName`,`email`,`phone`,`username`,`password`,`decryptedPassword`,`enabled`,`failed_login_attempt_count`,`pwd_change_on_next_login`,`superadmin`) values (2,'Paul','Smith','admin@mail.com','2125551212','admin','$shiro1$SHA-256$500000$yZnmkAQSFkP6MnlRAOisng==$LW6he2w5PcPhJC0CBLBtnH4QrkgxPb8Xt9/U0y0/dfc=',null,1,0,0,0);
insert into `user`(`id`,`firstName`,`lastName`,`email`,`phone`,`username`,`password`,`decryptedPassword`,`enabled`,`failed_login_attempt_count`,`pwd_change_on_next_login`,`superadmin`) values (3,'Teresa','Pinkerton','pinkerton@mail.com','1234567890','tpink','$shiro1$SHA-256$500000$JFXIxeg8LKeae6exWrYNWw==$s8x81SjM0OZWKs9AfEp287FHHGvoOyOczTt/inDZs9Q=',null,1,0,0,0);
insert into `user`(`id`,`firstName`,`lastName`,`email`,`phone`,`username`,`password`,`decryptedPassword`,`enabled`,`failed_login_attempt_count`,`pwd_change_on_next_login`,`superadmin`) values (4,'Pippo','Inzaghi','pippo@gmail.com','1234567890','superpippo','$shiro1$SHA-256$500000$4Cn/pAeiCi4KwG7D596qDw==$jDY+91MnHDKviufik3myf2b9lS0bRrD8F+MWDb0B/Gk=','12345678',1,0,1,0);
insert into `user`(`id`,`firstName`,`lastName`,`email`,`phone`,`username`,`password`,`decryptedPassword`,`enabled`,`failed_login_attempt_count`,`pwd_change_on_next_login`,`superadmin`) values (5,'Gino','Pilotino','gino@gmail.com','1234567890','geeno','$shiro1$SHA-256$500000$cic+lbIFXBGLwSfkU6SwYw==$jZKoOFwQiQskdZfzrorarfUWjeDEsdkFhC9RWcbacnk=','12345678',1,0,1,0);

-- --------------------------------------------------------

--
-- Struttura della tabella `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id_user`  INT NOT NULL,
  `id_role`  INT NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `FK143BF46A625146BF` (`id_role`),
  KEY `FK143BF46A77C0A9F` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_session`;
CREATE TABLE `user_session` (
  `id_session` INT NOT NULL,
  `id_user` INT NOT NULL,
  KEY `FKD1401A22A638D7AD` (`id_session`),
  KEY `FKD1401A2272C5ACFF` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Struttura della tabella `quiz_submission`
--

DROP TABLE IF EXISTS `submission`;
CREATE TABLE `submission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` int(1) NOT NULL,
  `final_score` double DEFAULT NULL,
  `start_timestamp` datetime DEFAULT NULL,
  `end_timestamp` datetime DEFAULT NULL,
  `id_session` INT DEFAULT NULL,
  `id_user` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK84363B4C2E69F2E1` (`id_session`),
  KEY `FK84363B4CACD97E4B` (`id_user`),
  CONSTRAINT `FK84363B4CACD97E4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK84363B4C2E69F2E1` FOREIGN KEY (`id_session`) REFERENCES `session` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dump dei dati per la tabella `submission`
--


-- --------------------------------------------------------



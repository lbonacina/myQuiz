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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
--) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `permission`
--

INSERT INTO `permission` (`id`, `description`, `permission`) VALUES
(1, 'desc', 'menu:user'),
(2, 'desc', 'menu:personal_info'),
(3, 'desc', 'menu:account'),
(4, 'desc', 'user:new'),
(5, 'desc', 'user:view'),
(6, 'desc', 'user:edit'),
(7, 'desc', 'user:delete'),
(8, 'desc', 'user:undelete'),
(9, 'desc', 'user:enable'),
(10, 'desc', 'user:disable'),
(11, 'desc', 'user:unlock'),
(12, 'desc', 'user:list_superadmin'),
(13, 'desc', 'user:list_enabled'),
(14, 'desc', 'menu:logs');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `quiz`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `quiz_submission`
--

DROP TABLE IF EXISTS `submission`;
CREATE TABLE IF NOT EXISTS `submission` (
  `id`  INT NOT NULL AUTO_INCREMENT,
  `id_quiz`  INT NOT NULL,
  `id_user`  INT NOT NULL,
  `status`  INT NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `final_score` double(3,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27B2177672C5ACFF` (`id_user`),
  KEY `FK27B21776D2B269C9` (`id_quiz`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `submission`
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `role`
--

INSERT INTO `role` (`id`, `description`, `role`) VALUES
(1, 'desc', 'Superadmin'),
(2, 'desc', 'Admin'),
(3, 'desc', 'User');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `role_permission`
--

INSERT INTO `role_permission` (`id_role`, `id_permission`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 8),
(2, 9),
(2, 10),
(3, 2),
(3, 3);

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id`, `decryptedPassword`, `email`, `enabled`, `failed_login_attempt_count`, `firstName`, `pwd_change_on_next_login`, `lastName`, `password`, `phone`, `superadmin`, `username`) VALUES
(1, NULL, 'jane@super.com', 1, 0, 'Jane', 0, 'Doe', '$shiro1$SHA-256$500000$no8bF4L6ou/rwcuv0/7qNw==$qOuLZWUSVBw6KJ+acoajJuDPeeoYoU33bdiYpQivvvo=', '2125551212', 1, 'wakka'),
(2, NULL, 'admin@mail.com', 1, 0, 'Paul', 0, 'Smith', '$shiro1$SHA-256$500000$yZnmkAQSFkP6MnlRAOisng==$LW6he2w5PcPhJC0CBLBtnH4QrkgxPb8Xt9/U0y0/dfc=', '2125551212', 0, 'admin'),
(3, NULL, 'pinkerton@mail.com', 1, 0, 'Teresa', 1, 'Pinkerton', '$shiro1$SHA-256$500000$vOVu8tgm+Z6ameq9ba7OvQ==$u1oFuOyrP6jq+vwiVpT57Cx+mxX4F44hXHX3Df4G+bI=', '1234567890', 0, 'tpink');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `user_role`
--

INSERT INTO `user_role` (`id_user`, `id_role`) VALUES
(1, 1),
(2, 2),
(3, 3);

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` longtext NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `id_quiz` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76508296D2B269C9` (`id_quiz`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_session`;
CREATE TABLE `user_session` (
  `id_session` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  KEY `FKD1401A22A638D7AD` (`id_session`),
  KEY `FKD1401A2272C5ACFF` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



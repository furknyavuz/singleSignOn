CREATE DATABASE `ssoproject`;

USE `ssoproject`;

CREATE TABLE `activeusers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(100) NOT NULL,
  `credentials` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`token`,`credentials`),
  UNIQUE KEY `credentials_UNIQUE` (`credentials`),
  UNIQUE KEY `token_UNIQUE` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=70;


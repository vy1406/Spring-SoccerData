CREATE DATABASE  IF NOT EXISTS `shop`;
USE `shop`;


DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookTitle` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `ISBN` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


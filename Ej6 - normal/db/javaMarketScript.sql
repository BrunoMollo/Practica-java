CREATE DATABASE /*!32312 IF NOT EXISTS*/ `javaMarket` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `javaMarket`;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` double(10,2) unsigned NOT NULL,
  `stock` int(10) unsigned NOT NULL,
  `shippingIncluded` tinyint(1) NOT NULL,
  `disableOn` datetime NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `product` VALUES (1,'mesa','es una mesa',100.2,10,1, '2001-01-01 20:00:00'),(2,'silla','es una silla',54.8, 25, 1,NULL),(3,'elefante','a veces ladra porque es biligue',6.2, 94, 0,NULL);

create user java@'%' identified by 'himitsu';
grant select, insert, update, delete on javaMarket.* to java@'%';


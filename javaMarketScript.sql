CREATE DATABASE /*!32312 IF NOT EXISTS*/ `javaMarket` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `javaMarket`;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
#id:int, name:String, description:String, price:double, stock: int, shippingIncluded: boolean
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` double(10,2) unsigned NOT NULL,
  `stock` int(10) unsigned NOT NULL,
  `shippingIncluded` tinyint(1) NOT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `product` VALUES (1,'mesa','┬─┬',100.2,10,1),(2,'silla','es una silla',54.8, 25, 1),(3,'elefante','a veces ladra porque es biligue',6.2, 94, 0);

create user bruno@'%' identified by 'constraseña copada 123';

grant select, insert, update, delete on javaTest.* to java@'%';
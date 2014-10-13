CREATE DATABASE  IF NOT EXISTS `drs` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `drs`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: drs
-- ------------------------------------------------------
-- Server version	5.6.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_police`
--

DROP TABLE IF EXISTS `t_police`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_police` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `police_type_id` int(11) DEFAULT NULL,
  `name` varchar(450) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `idcardno` varchar(500) DEFAULT NULL,
  `number` varchar(500) DEFAULT NULL,
  `title` varchar(450) DEFAULT NULL,
  `mobile` varchar(450) DEFAULT NULL,
  `mobile_short` varchar(450) DEFAULT NULL,
  `intercom_group` varchar(450) DEFAULT NULL,
  `intercom_person` varchar(450) DEFAULT NULL,
  `gps_id` int(11) DEFAULT NULL,
  `gps_name` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_police`
--

LOCK TABLES `t_police` WRITE;
/*!40000 ALTER TABLE `t_police` DISABLE KEYS */;
INSERT INTO `t_police` VALUES (2,3,'王五',2,'512301198506234875','51007816','科长','13568865179','6179','ssss','22222',5,'一号手机定位');
/*!40000 ALTER TABLE `t_police` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-11 12:27:25

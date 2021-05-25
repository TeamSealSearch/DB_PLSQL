CREATE DATABASE  IF NOT EXISTS `sealdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sealdb`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: sealsearch.mysql.database.azure.com    Database: sealdb
-- ------------------------------------------------------
-- Server version	5.6.47.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `admin_hashedID` varchar(128) NOT NULL,
  `admin_username` varchar(25) DEFAULT NULL,
  `admin_fname` varchar(25) DEFAULT NULL,
  `admin_lname` varchar(25) DEFAULT NULL,
  `admin_dob` date DEFAULT NULL,
  PRIMARY KEY (`admin_hashedID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
  `a_hashedID` varchar(128) NOT NULL,
  `a_username` varchar(25) DEFAULT NULL,
  `a_fname` varchar(25) DEFAULT NULL,
  `a_lname` varchar(25) DEFAULT NULL,
  `a_dob` date DEFAULT NULL,
  `a_resumePDF` longblob,
  `a_androidEncode` varchar(21844) DEFAULT NULL,
  `a_tech_yearsOfExp` int(11) DEFAULT '0',
  `a_tech_problemSolving` int(11) DEFAULT '0',
  `a_tech_degree` int(11) DEFAULT NULL,
  `a_busi_jobType` int(11) DEFAULT '0',
  `a_busi_growthOpp` int(11) DEFAULT '0',
  `a_busi_companySize` int(11) DEFAULT '0',
  `a_cult_consistency` int(11) DEFAULT '0',
  `a_cult_communication` int(11) DEFAULT '0',
  `a_cult_leadership` int(11) DEFAULT '0',
  PRIMARY KEY (`a_hashedID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `e_hashedID` varchar(128) NOT NULL,
  `e_companyName` varchar(25) DEFAULT NULL,
  `e_username` varchar(25) DEFAULT NULL,
  `e_fname` varchar(25) DEFAULT NULL,
  `e_lname` varchar(25) DEFAULT NULL,
  `e_dob` date DEFAULT NULL,
  `e_jobListingPDF` longblob,
  `e_androidEncode` varchar(21844) DEFAULT NULL,
  `e_tech_yearsOfExp` int(11) DEFAULT NULL,
  `e_tech_problemSolving` int(11) DEFAULT NULL,
  `e_tech_degree` int(11) DEFAULT NULL,
  `e_busi_jobType` int(11) DEFAULT NULL,
  `e_busi_growthOpp` int(11) DEFAULT NULL,
  `e_busi_companySize` int(11) DEFAULT NULL,
  `e_cult_consistency` int(11) DEFAULT NULL,
  `e_cult_communication` int(11) DEFAULT NULL,
  `e_cult_leadership` int(11) DEFAULT NULL,
  PRIMARY KEY (`e_hashedID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'sealdb'
--

--
-- Dumping routines for database 'sealdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-24 23:19:41

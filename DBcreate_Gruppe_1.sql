CREATE DATABASE  IF NOT EXISTS `alphasolutions` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alphasolutions`;
-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: alphasolutions
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `idprojects` int NOT NULL AUTO_INCREMENT,
  `projectname` varchar(100) NOT NULL,
  PRIMARY KEY (`idprojects`),
  UNIQUE KEY `idprojects_UNIQUE` (`idprojects`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (59,'Byg parcelhus');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sptasks`
--

DROP TABLE IF EXISTS `sptasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sptasks` (
  `idsptasks` int NOT NULL AUTO_INCREMENT,
  `taskName` varchar(45) NOT NULL,
  `taskHours` int NOT NULL,
  `taskEmployees` int NOT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `projectid` int NOT NULL,
  `subprojectid` int NOT NULL,
  `taskStatus` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idsptasks`),
  UNIQUE KEY `idsptasks_UNIQUE` (`idsptasks`),
  KEY `FK_SPID_idx` (`subprojectid`),
  CONSTRAINT `FK_SPID` FOREIGN KEY (`subprojectid`) REFERENCES `subprojects` (`idsubprojects`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sptasks`
--

LOCK TABLES `sptasks` WRITE;
/*!40000 ALTER TABLE `sptasks` DISABLE KEYS */;
INSERT INTO `sptasks` VALUES (45,'Bland cement',4,1,'2020-12-02 00:00:00','2020-12-02 00:00:00',59,100021,0),(46,'Marker område der skal støbes på',1,1,'2020-12-02 00:00:00','2020-12-02 00:00:00',59,100021,0),(47,'Forbered hvad der forberedes skal',1,1,'2020-12-02 00:00:00','2020-12-02 00:00:00',59,100021,0),(48,'Støb det',8,1,'2020-10-02 00:00:00','2020-10-02 00:00:00',59,100021,0),(49,'Bland cement',4,1,'2020-12-04 00:00:00','2020-12-04 00:00:00',59,100022,0),(50,'Hent mursten',2,2,'2020-12-04 00:00:00','2020-12-04 00:00:00',59,100022,0),(51,'Sæt mursten ovenpå hinanden',20,2,'2020-12-04 00:00:00','2020-12-08 00:00:00',59,100022,0),(52,'Byg lægter og gavl',30,2,'2020-12-06 00:00:00','2020-12-08 00:00:00',59,100023,0),(53,'Læg tagsten',40,4,'2020-12-06 00:00:00','2020-12-08 00:00:00',59,100023,0),(54,'Sæt vinduer og døre i',20,2,'2020-12-08 00:00:00','2020-12-10 00:00:00',59,100024,0);
/*!40000 ALTER TABLE `sptasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subprojects`
--

DROP TABLE IF EXISTS `subprojects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subprojects` (
  `idsubprojects` int NOT NULL AUTO_INCREMENT,
  `subprojectName` varchar(100) NOT NULL,
  `subprojectHours` int NOT NULL DEFAULT '0',
  `subProjectEmployees` int NOT NULL DEFAULT '0',
  `projectid` int NOT NULL,
  `SPstartDate` datetime DEFAULT '2050-01-01 00:00:00',
  `SPendDate` datetime DEFAULT '1950-01-01 00:00:00',
  PRIMARY KEY (`idsubprojects`),
  UNIQUE KEY `idsubprojects_UNIQUE` (`idsubprojects`),
  KEY `FK_SPprojectID` (`projectid`),
  CONSTRAINT `FK_SPprojectID` FOREIGN KEY (`projectid`) REFERENCES `projects` (`idprojects`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100025 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subprojects`
--

LOCK TABLES `subprojects` WRITE;
/*!40000 ALTER TABLE `subprojects` DISABLE KEYS */;
INSERT INTO `subprojects` VALUES (100021,'Støb fundament',0,0,59,'2020-10-02 00:00:00','2020-12-02 00:00:00'),(100022,'Mur vægge op',0,0,59,'2020-12-04 00:00:00','2020-12-08 00:00:00'),(100023,'Byg tag',0,0,59,'2020-12-06 00:00:00','2020-12-08 00:00:00'),(100024,'Vinduer og døre',0,0,59,'2020-12-08 00:00:00','2020-12-10 00:00:00');
/*!40000 ALTER TABLE `subprojects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `idtasks` int NOT NULL AUTO_INCREMENT,
  `taskName` varchar(45) NOT NULL,
  `taskHours` int NOT NULL,
  `taskEmployees` int NOT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `projectid` int NOT NULL,
  `taskStatus` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idtasks`),
  UNIQUE KEY `idtasks_UNIQUE` (`idtasks`),
  KEY `FK_projectID` (`projectid`),
  CONSTRAINT `FK_projectID` FOREIGN KEY (`projectid`) REFERENCES `projects` (`idprojects`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (111,'Bestil materialer',2,1,'2020-12-01 00:00:00','2020-12-01 00:00:00',59,0);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idusers`),
  UNIQUE KEY `idusers_UNIQUE` (`idusers`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (13,'Rasmus Møller','rm@kea.dk','rasmus'),(14,'Rasmus Trap','rt@kea.dk','rasmus'),(15,'Jesper Legaard','jl@kea.dk','jesper'),(16,'Jean-Marie Babonneau','jmb@kea.dk','jean'),(17,'Oskar Tuska','ostu@kea.dk','oskar'),(18,'Nicklas Frederiksen','nifr@kea.dk','nicklas'),(19,'Marianne Stougaard Nielsen','mn@kea.dk','marianne');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-20 16:42:14

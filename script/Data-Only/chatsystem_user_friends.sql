-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chatsystem
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Dumping data for table `user_friends`
--

LOCK TABLES `user_friends` WRITE;
/*!40000 ALTER TABLE `user_friends` DISABLE KEYS */;
INSERT INTO `user_friends` VALUES (1,2,'friends','2024-12-14 15:50:03'),(1,14,'block_1_2','2024-12-12 08:22:08'),(1,15,'pending_2_1','2024-12-14 15:49:40'),(1,16,'block_1_2','2024-12-18 18:22:49'),(1,17,'block_1_2','2024-12-13 07:19:44'),(1,18,'pending_2_1','2024-12-14 11:00:59'),(1,21,'block_1_2','2024-12-18 19:03:17'),(1,31,'friends','2024-12-22 10:53:07'),(2,14,'friends','2024-12-22 02:57:48'),(2,22,'friends','2024-12-22 02:48:37'),(3,22,'friends','2024-12-22 02:53:37'),(14,21,'pending_2_1','2024-12-18 20:08:38'),(14,22,'friends','2024-12-22 02:55:57'),(15,22,'friends','2024-12-22 10:04:30'),(16,21,'friends','2024-12-18 20:05:40'),(17,21,'pending_2_1','2024-12-18 20:08:40'),(17,22,'pending_2_1','2024-12-22 02:05:03'),(21,22,'friends','2024-12-22 02:42:49');
/*!40000 ALTER TABLE `user_friends` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-22 18:50:25
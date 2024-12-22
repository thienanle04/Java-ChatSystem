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
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ltan22','ltan22@clc.fitus.edu.vn','$2a$10$2y7eLmgGAqEpCXonCHLHeuYh1XjKgDIKtMXUqsorc7HqNH9jyHM..','Tran Van Dang','2004-06-11','Male','2024-12-08 16:33:17','pXbG459D','online','user',0,'ltan22'),(2,'hpn','hpnghia22@gmail.com','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Ha Noi','2004-10-16','Male','2024-12-08 16:33:17',NULL,'offline','user',0,'hpn'),(3,'vtkngan','vtkngan23@gmail.com','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Ninh Thuan','2005-05-09','Female','2024-12-08 16:33:17',NULL,'offline','user',0,'Van Thi Kim Ngan'),(14,'penny','penny@gmail.com','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Uc','2004-10-15','Female','2024-12-10 17:42:45',NULL,'offline','user',0,'Nguyen Nhu Ngoc'),(15,'lpttrinh','lpttrinh05@gmail.com','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Phan Rang','2005-12-06','Female','2024-12-12 13:56:37',NULL,'offline','user',0,'Le Pham Thuy Trinh'),(16,'dmkhoi','dmkhoi04@gmail.com','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Binh Dinh','2004-06-24','Male','2024-12-12 14:02:10',NULL,'offline','user',0,'Dang Minh Khoi'),(17,'nttkiet22','nttkiet22@gmail.com','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Da Lat','2004-09-10','Male','2024-12-12 14:02:10',NULL,'offline','user',0,'Nguyen Thanh Tuan Kiet'),(18,'lptuyen22','lptuyen22@clc.fitus.edu.vn','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Da Lat','2004-04-07','Female','2024-12-14 11:00:31',NULL,'offline','user',0,'Le Pham Thuy Uyen'),(19,'admin','admin@system.com','$2a$10$M7BxJYe6YXai8L98iwy6z.FM2Wy5EHDe4iZHwOwz5841NHsAfI/be','None','2024-01-01','Other','2024-12-17 09:34:57',NULL,'offline','admin',0,'ADMIN'),(21,'ltbanh04','letranbaoanh@gmail.com','$2a$10$V6pOkdJnffzMLaMVhHMxzuID87AWuxukisrjM6W4loPQCVF4fCN92','Ho Chi Minh','2024-07-14','Female','2024-12-18 16:55:06',NULL,'offline','user',0,'Le Tran Bao Anh'),(22,'tmnghi04','tmnghi@gmail.com','$2a$10$M7BxJYe6YXai8L98iwy6z.FM2Wy5EHDe4iZHwOwz5841NHsAfI/be','Ho Chi Minh',NULL,'Female','2024-12-18 16:55:06',NULL,'offline','user',0,'Tong Minh Nghi'),(31,'lethienan1106','lethienan1106@gmail.com','$2a$10$lesntOnbd5hgskLGTsS9uuOu8S3tNbUaP06jwKq/USNn2lSE9I90e','Ho Chi Minh','2004-06-11','Male','2024-12-22 10:51:11','RIoUYT22','online','user',0,'lethienan1106');
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

-- Dump completed on 2024-12-22 18:50:26

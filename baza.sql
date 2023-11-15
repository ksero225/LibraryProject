-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `BookID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `Author` varchar(255) NOT NULL,
  `Genre` varchar(100) DEFAULT NULL,
  `TotalCopies` int NOT NULL,
  `AvailableCopies` int NOT NULL,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Harry Potter and the Philosopher\'s Stone','J.K. Rowling','Fiction',20,10),(2,'The Great Gatsby','F. Scott Fitzgerald','Mystery',15,3),(3,'To Kill a Mockingbird','Harper Lee','Science Fiction',30,20),(4,'1984','George Orwell','Non-Fiction',25,7),(5,'Pride and Prejudice','Jane Austen','Fantasy',10,5),(6,'The Catcher in the Rye','J.D. Salinger','Romance',12,8),(7,'The Hobbit','J.R.R. Tolkien','Thriller',18,12),(8,'Brave New World','Aldous Huxley','Biography',8,2),(9,'The Lord of the Rings','J.R.R. Tolkien','History',22,15),(10,'The Da Vinci Code','Dan Brown','Adventure',14,7);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_books`
--

DROP TABLE IF EXISTS `user_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_books` (
  `userBooksID` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `bookID` int DEFAULT NULL,
  `borrow_date` date DEFAULT NULL,
  PRIMARY KEY (`userBooksID`),
  KEY `userID` (`userID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `user_books_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `user_books_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `books` (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_books`
--

LOCK TABLES `user_books` WRITE;
/*!40000 ALTER TABLE `user_books` DISABLE KEYS */;
INSERT INTO `user_books` VALUES (1,1,4,'2023-10-28'),(2,3,2,'2023-10-28'),(3,1,4,'2023-10-28'),(4,1,4,'2023-10-28'),(5,1,2,'2023-10-28');
/*!40000 ALTER TABLE `user_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userFirstName` varchar(255) DEFAULT NULL,
  `userLastName` varchar(255) DEFAULT NULL,
  `phoneNumber` int DEFAULT NULL,
  `registrationDate` date DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin','admin',123123123,'2023-10-27'),(2,'Kamilek','zaq1@WX','Kamil','Nowak',123123123,'2023-10-27'),(3,'Maciek','zaq1@WSX','Maciek','Nowak',123123123,'2023-10-27'),(4,'Karolitka','zaq1@WSX','Karol','Nowak',123123123,'2023-10-27'),(5,'Babielato','cde3$RFV','Urszula','Kowalczyk',513731236,'2023-10-27'),(6,'Woda','bgt5^YHN','Pawel','Micha',192837465,'2023-10-27'),(8,'kakao','zaq1@WSX','pawel','micha',312582312,'2023-11-12');
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

-- Dump completed on 2023-11-12 18:04:11

-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: dbsales
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `BaID` int NOT NULL AUTO_INCREMENT,
  `BaName` varchar(45) NOT NULL,
  PRIMARY KEY (`BaID`),
  UNIQUE KEY `BaName_UNIQUE` (`BaName`)
) ENGINE=InnoDB AUTO_INCREMENT=50000014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (50000001,'Metrobank'),(50000010,'RCBC');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_info`
--

DROP TABLE IF EXISTS `business_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_info` (
  `BuID` int NOT NULL AUTO_INCREMENT,
  `BuName` varchar(45) NOT NULL,
  `BuTIN` varchar(45) NOT NULL,
  `BuAddress` varchar(45) NOT NULL,
  `BuEmail` varchar(45) NOT NULL,
  PRIMARY KEY (`BuID`)
) ENGINE=InnoDB AUTO_INCREMENT=90010001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_info`
--

LOCK TABLES `business_info` WRITE;
/*!40000 ALTER TABLE `business_info` DISABLE KEYS */;
INSERT INTO `business_info` VALUES (20232001,'DanPel Elegance','123456789','Sta.Mesa, Manila','danpelelegance@gmail.com');
/*!40000 ALTER TABLE `business_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `InID` int NOT NULL AUTO_INCREMENT,
  `ScID` int NOT NULL,
  `BuID` int NOT NULL,
  `InStatus` varchar(45) NOT NULL,
  `InAmount` double NOT NULL,
  `InDate` varchar(45) NOT NULL,
  PRIMARY KEY (`InID`),
  KEY `BuID_idx` (`BuID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,20000001,20232001,'Unpaid',1398,'14/02/2023'),(2,20000001,20232001,'Unpaid',4700,'14/02/2023'),(3,20000001,20232001,'Unpaid',5388,'14/02/2023'),(4,20000001,20232001,'Unpaid',4900,'14/02/2023'),(5,20000001,20232001,'Unpaid',579,'18/02/2023'),(6,20000005,20232001,'Unpaid',246,'18/02/2023'),(7,20000006,20232001,'Unpaid',912,'18/02/2023'),(8,20000001,20232001,'Unpaid',492,'18/02/2023'),(9,20000001,20232001,'Unpaid',492,'18/02/2023'),(10,20000005,20232001,'Unpaid',1,'18/02/2023'),(11,20000001,20232001,'Unpaid',1,'18/02/2023'),(12,20000005,20232001,'Unpaid',1,'18/02/2023'),(13,20000005,20232001,'Unpaid',1,'18/02/2023'),(14,20000005,20232001,'Unpaid',1,'18/02/2023'),(15,20000001,20232001,'Unpaid',1,'18/02/2023'),(16,20000005,20232001,'Unpaid',1,'18/02/2023'),(17,20000005,20232001,'Unpaid',1,'18/02/2023'),(18,20000005,20232001,'Unpaid',1,'18/02/2023'),(19,20000005,20232001,'Unpaid',1,'18/02/2023'),(20,20000005,20232001,'Unpaid',1,'18/02/2023'),(21,20000005,20232001,'Unpaid',1,'18/02/2023'),(22,20000005,20232001,'Unpaid',1,'18/02/2023'),(23,20000005,20232001,'Unpaid',1,'18/02/2023'),(24,20000005,20232001,'Unpaid',1,'18/02/2023'),(25,20000005,20232001,'Unpaid',1,'18/02/2023'),(26,20000005,20232001,'Unpaid',1,'18/02/2023'),(27,20000005,20232001,'Unpaid',1,'18/02/2023'),(28,20000005,20232001,'Unpaid',1,'18/02/2023'),(29,20000005,20232001,'Unpaid',1,'18/02/2023'),(30,20000005,20232001,'Unpaid',1,'18/02/2023'),(31,20000005,20232001,'Unpaid',1,'18/02/2023'),(32,20000005,20232001,'Unpaid',1,'18/02/2023'),(33,20000005,20232001,'Unpaid',1,'18/02/2023'),(34,20000005,20232001,'Unpaid',1,'18/02/2023'),(35,20000005,20232001,'Unpaid',1,'18/02/2023'),(36,20000005,20232001,'Unpaid',1,'18/02/2023'),(37,20000005,20232001,'Unpaid',1,'18/02/2023'),(38,20000005,20232001,'Unpaid',1,'18/02/2023'),(39,20000005,20232001,'Unpaid',1,'18/02/2023'),(40,20000005,20232001,'Unpaid',1,'18/02/2023'),(41,20000001,20232001,'Unpaid',1,'18/02/2023'),(42,20000001,20232001,'Unpaid',5,'18/02/2023'),(43,20000001,20232001,'Unpaid',5,'18/02/2023'),(44,20000001,20232001,'Paid',5,'18/02/2023'),(45,20000001,20232001,'Paid',5,'27/02/2023'),(46,20000005,20232001,'Paid',48,'27/02/2023');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `losses`
--

DROP TABLE IF EXISTS `losses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `losses` (
  `LossID` int NOT NULL,
  `PrID` int NOT NULL,
  `LossQuantity` int NOT NULL,
  `LossTotalCost` int NOT NULL,
  `LossDate` varchar(45) NOT NULL,
  PRIMARY KEY (`LossID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `losses`
--

LOCK TABLES `losses` WRITE;
/*!40000 ALTER TABLE `losses` DISABLE KEYS */;
/*!40000 ALTER TABLE `losses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `official_receipt`
--

DROP TABLE IF EXISTS `official_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `official_receipt` (
  `OfrID` int NOT NULL AUTO_INCREMENT,
  `InID` varchar(45) NOT NULL,
  `PayID` varchar(45) DEFAULT NULL,
  `BaID` int NOT NULL,
  `OfrNumber` int NOT NULL,
  PRIMARY KEY (`OfrID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `official_receipt`
--

LOCK TABLES `official_receipt` WRITE;
/*!40000 ALTER TABLE `official_receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `official_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `OrID` int NOT NULL AUTO_INCREMENT,
  `UID` int NOT NULL,
  `PayID` int NOT NULL,
  `OrNumber` int NOT NULL,
  `OrType` varchar(45) NOT NULL,
  `OrLessVAT` int DEFAULT NULL,
  `OrNet_of_VAT` int DEFAULT NULL,
  `OrTotalAmount` int DEFAULT NULL,
  `OrChange` int DEFAULT NULL,
  `OrDate` timestamp(6) NULL DEFAULT NULL,
  `OrTime` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`OrID`),
  KEY `PayID_idx` (`PayID`),
  KEY `UID_idx` (`UID`),
  CONSTRAINT `PayID` FOREIGN KEY (`PayID`) REFERENCES `payment` (`PayID`),
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `OrDetID` int NOT NULL AUTO_INCREMENT,
  `OrNumber` int NOT NULL,
  `PrID` int NOT NULL,
  `OrDetQuantity` int NOT NULL,
  `OrDetCostTotal` int NOT NULL,
  `OrDetPriceTotal` int NOT NULL,
  PRIMARY KEY (`OrDetID`)
) ENGINE=InnoDB AUTO_INCREMENT=13000048 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (13000025,11000001,10010001,1,10,14),(13000026,11000001,10010002,1,9,12),(13000027,11000001,10010003,1,12,15),(13000028,11000001,10010004,1,7,10),(13000029,11000001,10010005,1,20,25),(13000030,11000002,10010002,1,9,12),(13000031,11000003,10010001,1,10,14),(13000032,11000004,10010004,1,7,10),(13000033,11000005,10010005,1,20,25),(13000034,11000006,10020003,1,800,990),(13000035,11000007,10040001,2,6,10),(13000036,11000008,10010006,1,30,40),(13000037,11000009,10030002,1,1100,1400),(13000038,11000010,10010002,1,9,12),(13000039,11000010,10010006,1,30,40),(13000040,11000010,10030001,2,2200,2800),(13000041,11000011,10010006,2,60,80),(13000042,11000011,10020002,1,800,990),(13000043,11000011,10040001,1,3,5),(13000044,11000012,10010005,1,20,25),(13000045,11000013,10010005,1,20,25),(13000046,11000014,10010005,1,20,25),(13000047,11000015,10010001,1,10,14);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrID` int NOT NULL,
  `UID` int NOT NULL,
  `PayID` int DEFAULT NULL,
  `OrNumber` int NOT NULL,
  `OrType` varchar(45) NOT NULL,
  `OrLessVAT` double DEFAULT NULL,
  `OrNet_of_VAT` double DEFAULT NULL,
  `OrCostTotal` int DEFAULT NULL,
  `OrTotalAmount` int DEFAULT NULL,
  `OrChange` int DEFAULT NULL,
  `OrDate` varchar(45) DEFAULT NULL,
  `OrTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (20230001,90000001,12000015,11000001,'Retail',8.14,67.86,58,76,24,'29/01/2023','18:16:20'),(20230002,90000001,12000016,11000002,'Retail',1.29,10.71,9,12,3,'29/01/2023','18:19:05'),(20230003,90000001,12000017,11000003,'Retail',1.5,12.5,10,14,1,'29/01/2023','18:19:12'),(20230004,90000001,12000018,11000004,'Retail',1.07,8.93,7,10,10,'29/01/2023','18:19:19'),(20230005,90000001,12000019,11000005,'Retail',2.68,22.32,20,25,5,'29/01/2023','18:19:28'),(20230006,90000001,12000020,11000006,'Retail',106.07,883.93,800,990,10,'29/01/2023','18:19:36'),(20230007,90000001,12000021,11000007,'Retail',1.07,8.93,6,10,10,'29/01/2023','18:19:55'),(20230008,90000001,12000022,11000008,'Retail',4.29,35.71,30,40,10,'29/01/2023','18:20:06'),(20230009,90000001,12000023,11000009,'Retail',150,1250,1100,1400,100,'29/01/2023','18:20:14'),(20230010,90000001,12000024,11000010,'Retail',305.57,2546.43,2239,2852,148,'29/01/2023','18:32:03'),(20230011,90000001,12000025,11000011,'Retail',115.18,959.82,863,1075,125,'29/01/2023','18:34:57'),(20230012,90000001,12000026,11000012,'Retail',2.68,22.32,20,25,5,'31/01/2023','16:00:46'),(20230013,0,12000027,11000013,'Retail',2.68,22.32,20,25,5,'31/01/2023','16:02:49'),(20230014,0,12000028,11000014,'Retail',2.68,22.32,20,25,5,'08/02/2023','00:00:22'),(20230015,0,12000029,11000015,'Retail',1.5,12.5,10,14,20,'16/02/2023','14:34:02');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PayID` int NOT NULL AUTO_INCREMENT,
  `OrID` int NOT NULL,
  `PayDescription` varchar(45) NOT NULL,
  `PayAmount` int DEFAULT NULL,
  `PayDate` varchar(45) DEFAULT NULL,
  `PayCheckNum` int DEFAULT NULL,
  PRIMARY KEY (`PayID`)
) ENGINE=InnoDB AUTO_INCREMENT=12000030 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (12000015,20230001,'Cash',100,'29/01/2023',0),(12000016,20230002,'Cash',15,'29/01/2023',0),(12000017,20230003,'Cash',15,'29/01/2023',0),(12000018,20230004,'Cash',20,'29/01/2023',0),(12000019,20230005,'Cash',30,'29/01/2023',0),(12000020,20230006,'Cash',1000,'29/01/2023',0),(12000021,20230007,'Cash',20,'29/01/2023',0),(12000022,20230008,'Cash',50,'29/01/2023',0),(12000023,20230009,'Cash',1500,'29/01/2023',0),(12000024,20230010,'Cash',3000,'29/01/2023',0),(12000025,20230011,'Cash',1200,'29/01/2023',0),(12000026,20230012,'Cash',30,'31/01/2023',0),(12000027,20230013,'Cash',30,'31/01/2023',0),(12000028,20230014,'Cash',30,'08/02/2023',0),(12000029,20230015,'Cash',34,'16/02/2023',0);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penalties`
--

DROP TABLE IF EXISTS `penalties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `penalties` (
  `PenalID` int NOT NULL,
  `PenalDescription` varchar(45) NOT NULL,
  `PenalAmount` int NOT NULL,
  `PenalDate` varchar(45) NOT NULL,
  PRIMARY KEY (`PenalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penalties`
--

LOCK TABLES `penalties` WRITE;
/*!40000 ALTER TABLE `penalties` DISABLE KEYS */;
/*!40000 ALTER TABLE `penalties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `PrID` int NOT NULL,
  `CaID` int NOT NULL,
  `PrBarcode` int NOT NULL,
  `PrName` varchar(45) NOT NULL,
  `PrCost` int NOT NULL,
  `PrUnitPrice` int NOT NULL,
  `PrVAT` double DEFAULT NULL,
  `PrStock` int NOT NULL,
  PRIMARY KEY (`PrID`),
  UNIQUE KEY `PrBarcode_UNIQUE` (`PrBarcode`),
  KEY `CaID_idx` (`CaID`),
  CONSTRAINT `CaID` FOREIGN KEY (`CaID`) REFERENCES `product_category` (`CaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (10010001,1001,555384726,'Mongol Pencil No. 2',10,14,NULL,13),(10010002,1001,293528308,'Mongol Pencil No. 1',9,12,NULL,19),(10010003,1001,538141458,'Mongol Pencil No. 3',12,15,NULL,16),(10010004,1001,715752019,'Panda Ballpen',7,10,NULL,13),(10010005,1001,744947473,' Pilot G-Tec Ballpen',20,25,NULL,4),(10010006,1001,262531696,'Green Apple Notebook',30,40,NULL,14),(10020001,1002,517130494,'Uniqlo Black T-Shirt (S)',800,990,NULL,23),(10020002,1002,217825594,'Uniqlo Black T-Shirt (M)',800,990,NULL,15),(10020003,1002,589510316,'Uniqlo Black T-Shirt (L)',800,990,NULL,18),(10020004,1002,563282020,'Uniqlo Black T-Shirt (XL)',800,990,NULL,23),(10020005,1002,608764271,'Uniqlo Black T-Shirt (XXL)',800,990,NULL,23),(10030001,1003,488493579,'Lego Police Station',1100,1400,NULL,22),(10030002,1003,629708126,'Lego Fire Station',1100,1400,NULL,21),(10030003,1003,648359000,'Lego Construction Set',800,1100,117.86,5),(10040001,1004,230085504,'BYD Care Face Mask ',3,5,NULL,13),(10050001,1005,618349150,'Hammer',60,75,8.04,20);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `CaID` int NOT NULL AUTO_INCREMENT,
  `CaDescription` varchar(45) NOT NULL,
  PRIMARY KEY (`CaID`),
  UNIQUE KEY `CaDescription_UNIQUE` (`CaDescription`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1002,'Clothing'),(1004,'Health and Household'),(1006,'Office Products'),(1001,'School Supplies'),(1005,'Tools '),(1003,'Toys');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_sold`
--

DROP TABLE IF EXISTS `product_sold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_sold` (
  `PrSoldID` int NOT NULL,
  `OrDetID` int NOT NULL,
  `PrSoldQuantity` int NOT NULL,
  `PrSoldDate` timestamp(6) NOT NULL,
  PRIMARY KEY (`PrSoldID`),
  KEY `OrDetID_idx` (`OrDetID`),
  CONSTRAINT `OrDetID` FOREIGN KEY (`OrDetID`) REFERENCES `order_details` (`OrDetID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_sold`
--

LOCK TABLES `product_sold` WRITE;
/*!40000 ALTER TABLE `product_sold` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_sold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `SalesID` int NOT NULL AUTO_INCREMENT,
  `SalesGrossIncome` int NOT NULL,
  `SalesTotalLoss` int NOT NULL,
  `SalesTotalVAT` double NOT NULL,
  `SalesTotalCost` int NOT NULL,
  `SalesTotalPenalties` int NOT NULL,
  `SalesNetIncome` double NOT NULL,
  `SalesDate` varchar(45) NOT NULL,
  PRIMARY KEY (`SalesID`)
) ENGINE=InnoDB AUTO_INCREMENT=11100009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (11100005,6504,0,696.86,5142,0,665.14,'29/01/2023'),(11100006,50,0,5.36,40,0,4.64,'31/01/2023'),(11100007,25,0,2.68,20,0,2.32,'08/02/2023'),(11100008,14,0,1.5,10,0,2.5,'16/02/2023');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_client`
--

DROP TABLE IF EXISTS `school_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_client` (
  `ScID` int NOT NULL AUTO_INCREMENT,
  `ScName` varchar(45) NOT NULL,
  `ScContactName` varchar(45) NOT NULL,
  `ScPhoneNumber` bigint NOT NULL,
  `ScAddress` varchar(45) NOT NULL,
  `ScEmail` varchar(45) NOT NULL,
  `ScTIN` bigint NOT NULL,
  PRIMARY KEY (`ScID`),
  UNIQUE KEY `ScTIN_UNIQUE` (`ScTIN`),
  UNIQUE KEY `ScAddress_UNIQUE` (`ScAddress`),
  UNIQUE KEY `ScPhoneNumber_UNIQUE` (`ScPhoneNumber`),
  UNIQUE KEY `ScName_UNIQUE` (`ScName`)
) ENGINE=InnoDB AUTO_INCREMENT=20000009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_client`
--

LOCK TABLES `school_client` WRITE;
/*!40000 ALTER TABLE `school_client` DISABLE KEYS */;
INSERT INTO `school_client` VALUES (20000001,'PUP','Emman',2147483647,'Sta Mesa, Manila','PUP@gmail.com',123456789),(20000005,'FEU','Emman Tapaar',9608453214,'Sta. Mesa, Manilaa','taparemmaaan@gmail.com',954651264),(20000006,'UE','Emmaan Taapar',9608452143,'Manila','taparremman@gmail.com',123456987),(20000008,'UP','Emman Tapar',789546248,'Quezon City, Metro Manila','UP@gmail.com',546978321);
/*!40000 ALTER TABLE `school_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tape_receipt`
--

DROP TABLE IF EXISTS `tape_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tape_receipt` (
  `TaID` int NOT NULL AUTO_INCREMENT,
  `BuID` int NOT NULL,
  `OrID` int NOT NULL,
  `OrDetID` int NOT NULL,
  PRIMARY KEY (`TaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tape_receipt`
--

LOCK TABLES `tape_receipt` WRITE;
/*!40000 ALTER TABLE `tape_receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `tape_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toga`
--

DROP TABLE IF EXISTS `toga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `toga` (
  `TogaID` int NOT NULL AUTO_INCREMENT,
  `TogaDescription` varchar(45) NOT NULL,
  `TogaStock` int NOT NULL,
  `ScID` int NOT NULL,
  PRIMARY KEY (`TogaID`),
  KEY `ScID` (`ScID`),
  CONSTRAINT `toga_ibfk_1` FOREIGN KEY (`ScID`) REFERENCES `school_client` (`ScID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toga`
--

LOCK TABLES `toga` WRITE;
/*!40000 ALTER TABLE `toga` DISABLE KEYS */;
INSERT INTO `toga` VALUES (6,'Toga for IT Students	',368,20000001),(7,'Toga for Tourism Students',18,20000005),(8,'Toga for Engineering Students',43,20000006),(9,'Toga for CS Students',42,20000001);
/*!40000 ALTER TABLE `toga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toga_rented`
--

DROP TABLE IF EXISTS `toga_rented`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `toga_rented` (
  `RentalID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`RentalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toga_rented`
--

LOCK TABLES `toga_rented` WRITE;
/*!40000 ALTER TABLE `toga_rented` DISABLE KEYS */;
/*!40000 ALTER TABLE `toga_rented` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `togaordertable`
--

DROP TABLE IF EXISTS `togaordertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `togaordertable` (
  `Invoice_ID` int NOT NULL,
  `School` varchar(45) NOT NULL,
  `Description` varchar(45) NOT NULL,
  `Unit_Price` double NOT NULL,
  `Quantity` int NOT NULL,
  `Total_Amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `togaordertable`
--

LOCK TABLES `togaordertable` WRITE;
/*!40000 ALTER TABLE `togaordertable` DISABLE KEYS */;
INSERT INTO `togaordertable` VALUES (45,'PUP','Toga for IT Students	',1,1,1),(45,'PUP','Toga for CS Students',2,2,4),(46,'FEU','Toga for Tourism Students',12,4,48);
/*!40000 ALTER TABLE `togaordertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UID` int NOT NULL AUTO_INCREMENT,
  `UFname` varchar(45) NOT NULL,
  `ULname` varchar(45) NOT NULL,
  `Uname` varchar(45) NOT NULL,
  `Upass` varchar(45) NOT NULL,
  `ULevel` int NOT NULL,
  `UDesignation` varchar(45) NOT NULL,
  `UEmail` varchar(45) NOT NULL,
  `UPhoneNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `Uname_UNIQUE` (`Uname`),
  UNIQUE KEY `Upass_UNIQUE` (`Upass`),
  UNIQUE KEY `UPhoneNumber_UNIQUE` (`UPhoneNumber`),
  UNIQUE KEY `UEmail_UNIQUE` (`UEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=90000003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (90000001,'Emmanuel','Tapar','slackerr','Daslife@0921',2,'Owner','taparemman@outlook.com','9608453214'),(90000002,'Juan Miguel','Ng','Kibored72','thisisapassword',2,'Owner','clammitty@gmail.com','09497673632');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-27 22:27:24

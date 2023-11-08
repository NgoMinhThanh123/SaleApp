-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: saledb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Điện thoại ',NULL),(2,'Laptop',NULL),(10,'Phụ kiện','');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_idx` (`product_id`),
  KEY `fk_user_idx` (`user_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (6,'alo',16,28,'2023-11-01 23:44:03'),(7,'alo',16,28,'2023-11-01 23:44:13'),(8,'test',16,28,'2023-11-01 23:45:45'),(9,'đẹp quá',16,49,'2023-11-02 22:17:56'),(10,'ok đó mọi người',16,49,'2023-11-02 22:18:06'),(11,'alo',16,28,'2023-11-03 01:11:12'),(12,'alo ok',18,32,'2023-11-03 01:30:55'),(13,'alo',18,37,'2023-11-03 01:33:19'),(14,'đẹp đó',18,37,'2023-11-03 01:43:46'),(15,'test nè',18,37,'2023-11-03 01:46:05'),(16,'alo',16,28,'2023-11-03 01:46:24'),(17,'alo',16,28,'2023-11-03 01:47:27'),(18,'alo',16,28,'2023-11-03 01:49:07'),(19,'alo',16,28,'2023-11-03 01:50:56'),(20,'alo',16,28,'2023-11-03 01:53:06'),(21,'alo',16,28,'2023-11-03 01:55:25'),(22,'lll',18,37,'2023-11-03 01:55:38'),(23,'â',18,28,'2023-11-03 02:20:19'),(24,'aaaa',18,28,'2023-11-03 02:20:31'),(25,'s',18,28,'2023-11-03 02:22:54'),(26,'111',18,37,'2023-11-03 02:23:51'),(27,'111',18,37,'2023-11-03 02:25:14'),(28,'ok đó nha',18,30,'2023-11-03 02:29:10'),(29,'OKla đó',16,29,'2023-11-04 03:36:52'),(30,'11',16,29,'2023-11-04 03:38:42'),(31,'1123',16,29,'2023-11-04 03:39:10'),(32,'alo',16,28,'2023-11-04 03:40:41'),(33,'test',16,29,'2023-11-04 03:40:57'),(34,'okla',16,28,'2023-11-04 23:22:01'),(35,'1 sao',16,28,'2023-11-04 23:24:47'),(36,'a',16,28,'2023-11-04 23:32:17'),(37,'2 sao',16,28,'2023-11-04 23:37:06'),(38,'1 sao',16,35,'2023-11-04 23:43:43'),(39,'a',16,35,'2023-11-04 23:46:22'),(40,'ư',16,35,'2023-11-04 23:48:56'),(41,'1',16,46,'2023-11-08 09:56:34');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `unit_price` decimal(10,0) DEFAULT '0',
  `num` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_ORDERDETAIL_ORDER_idx` (`order_id`),
  KEY `FK_ORDERDETAIL_PRODUCT_idx` (`product_id`),
  CONSTRAINT `FK_ORDERDETAIL_ORDER` FOREIGN KEY (`order_id`) REFERENCES `sale_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDERDETAIL_PRODUCT` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (27,15,28,40000000,'2'),(28,16,28,40000000,'2'),(29,17,28,40000000,'2');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preview`
--

DROP TABLE IF EXISTS `preview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preview` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `preview` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `comment_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user1_id_idx` (`user_id`),
  KEY `comment_id_idx` (`comment_id`),
  CONSTRAINT `comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `user1_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preview`
--

LOCK TABLES `preview` WRITE;
/*!40000 ALTER TABLE `preview` DISABLE KEYS */;
INSERT INTO `preview` VALUES (1,'2023-11-02 00:00:00',4,16,15),(2,'2023-11-03 01:55:25',5,16,21),(3,'2023-11-03 01:55:38',NULL,18,22),(4,'2023-11-03 02:20:19',NULL,18,23),(5,'2023-11-03 02:20:31',NULL,18,24),(6,'2023-11-03 02:22:54',NULL,18,25),(7,'2023-11-03 02:23:51',NULL,18,26),(8,'2023-11-03 02:25:14',NULL,18,27),(9,'2023-11-03 02:29:10',NULL,18,28),(10,'2023-11-04 03:36:52',4,16,29),(11,'2023-11-04 03:38:42',NULL,16,30),(12,'2023-11-04 03:39:10',NULL,16,31),(13,'2023-11-04 03:40:41',5,16,32),(14,'2023-11-04 03:40:57',4,16,33),(15,'2023-11-04 23:22:01',5,16,34),(16,'2023-11-04 23:24:47',1,16,35),(17,'2023-11-04 23:32:17',3,16,36),(18,'2023-11-04 23:37:06',2,16,37),(19,'2023-11-04 23:43:43',1,16,38),(20,'2023-11-04 23:46:22',2,16,39),(21,'2023-11-04 23:48:56',4,16,40),(22,'2023-11-08 09:56:34',2,16,41);
/*!40000 ALTER TABLE `preview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prod_tag`
--

DROP TABLE IF EXISTS `prod_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prod_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `tag_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f1_idx` (`product_id`),
  KEY `f2_idx` (`tag_id`),
  CONSTRAINT `f1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_tag`
--

LOCK TABLES `prod_tag` WRITE;
/*!40000 ALTER TABLE `prod_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `prod_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `price` decimal(10,0) DEFAULT '0',
  `manufacturer` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `active` tinyint DEFAULT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRODUCE_CATEGORY_idx` (`category_id`),
  CONSTRAINT `FK_PRODUCE_CATEGORY` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (28,'Iphone 15 PROMAX Titan Trắng','iPhone 15 Pro Max 512 GB tại sự kiện ra mắt thường niên với nhiều điểm đáng chú ý, nổi bật trong số đó có thể kể đến như sự góp mặt của chipset Apple A17 Pro có trên máy, thiết kế khung titan hay cổng Type-C lần đầu có mặt trên điện thoại iPhone.',40000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782201/xwocvxxtihzuwaydlus5.jpg','2023-11-01 00:00:00',1,1),(29,'Iphone 15 PROMAX TitanTự Nhiên','iPhone 15 Pro Max 512 GB tại sự kiện ra mắt thường niên với nhiều điểm đáng chú ý, nổi bật trong số đó có thể kể đến như sự góp mặt của chipset Apple A17 Pro có trên máy, thiết kế khung titan hay cổng Type-C lần đầu có mặt trên điện thoại iPhone.',40000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783646/o06dv4ajmfkq5oi0jjjl.jpg','2023-11-01 00:00:00',1,1),(30,'Iphone 15 PROMAX Titan Xanh','iPhone 15 Pro Max 512 GB tại sự kiện ra mắt thường niên với nhiều điểm đáng chú ý, nổi bật trong số đó có thể kể đến như sự góp mặt của chipset Apple A17 Pro có trên máy, thiết kế khung titan hay cổng Type-C lần đầu có mặt trên điện thoại iPhone.',40000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783673/rbkj8aufhrhpwbxtnqoz.jpg','2023-11-01 00:00:00',1,1),(31,'Iphone 14 PROMAX Tím','Iphone 14 PROMAX Tím',30000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782287/zewijvit7raeoofqmxti.jpg','2023-11-01 00:00:00',1,1),(32,'Iphone 14 PROMAX Gold','Iphone 14 PROMAX Gold',30000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782311/bdlkuisdywomfmiqon8k.jpg','2023-11-01 00:00:00',1,1),(33,'Iphone 14 PROMAX Black','Iphone 14 PROMAX Black',30000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783708/cbe568sqvvviyiqhtuj0.jpg','2023-11-01 00:00:00',1,1),(34,'Iphone 13 PROMAX Blue','Iphone 13 PROMAX Blue',25000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782373/nzyl5cjqpow8v0ey1lgs.png','2023-11-01 00:00:00',1,1),(35,'Iphone 13 PROMAX Grey','Iphone 13 PROMAX Grey',250000000,'Iphone','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782394/cmtecqihmai0hurkzahn.png','2023-11-01 00:00:00',1,1),(37,'SamSung Galaxy ZFold 5','SamSung Galaxy ZFold 5',40000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782477/m2fv2xmlgb7jaomhvoqr.jpg','2023-11-01 00:00:00',1,1),(38,'SamSung Galaxy ZFold 2','SamSung Galaxy ZFold 2',35000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783726/imj9wue86v9cd0gwiwlx.jpg','2023-11-01 00:00:00',1,1),(39,'SamSung Galaxy Z Flip4 ','SamSung Galaxy Z Flip4 ',30000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782554/rlk39gvssxpclpiqca87.jpg','2023-11-01 00:00:00',1,1),(40,'SamSung Galaxy Z Flip3','SamSung Galaxy Z Flip3',25000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782600/bispvna5e6qmujzanvug.jpg','2023-11-01 00:00:00',1,1),(41,'SamSung Galaxy S10','SamSung Galaxy S10',15000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782629/uhvf0jdqqmym1przip8b.jpg','2023-11-01 00:00:00',1,1),(42,'SamSung Galaxy A52','SamSung Galaxy A52',12000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782659/z58ys7cbfw9v8rfaiymf.jpg','2023-11-01 00:00:00',1,1),(43,'SamSung Galaxy A51','SamSung Galaxy A51',12000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782678/yjy6sxw1fnntu5jemocm.jpg','2023-11-01 00:00:00',1,1),(44,'SamSung Galaxy A50','SamSung Galaxy A50',10000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782700/hdpta0hdbj5svpgcvshj.jpg','2023-11-01 00:00:00',1,1),(45,'SamSung Galaxy A12','SamSung Galaxy A12',7000000,'SamSung','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782736/rpchbymvcswf7p9z9bso.jpg','2023-11-01 00:00:00',1,1),(46,'Oppo A77','Oppo A77',10000000,'Oppo','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782799/enqrs6hxzw49hwtswysx.jpg','2023-11-01 00:00:00',1,1),(47,'Oppo Reno8','Oppo Reno8',12000000,'Oppo','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782825/xibxjwribtahkl0ufbx2.jpg','2023-11-01 00:00:00',1,1),(48,'Oppo Reno10','Oppo Reno10',12000000,'Oppo','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782842/mtaxsovkwjt8lk0zqp6k.jpg','2023-11-01 00:00:00',1,1),(49,'Chuột bluetooth apple mk2e3','Chuột bluetooth apple mk2e3',200000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782925/sxmjcmnmel6qyafblsiz.jpg','2023-11-01 00:00:00',1,10),(50,'Chuột bluetooth microsoft modern mobile','Chuột bluetooth microsoft modern mobile',500000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782953/zvpf13js974pcvipkufq.jpg','2023-11-01 00:00:00',1,10),(51,'Chuột bluetooth silent logitech','Chuột bluetooth silent logitech',600000,'Logitech','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698782986/qollt7epduokwmnctxnq.jpg','2023-11-01 00:00:00',1,10),(52,'Chuột bluetooth silent rapoo','Chuột bluetooth silent rapoo',700000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783041/iwckddyaarpahc7spkh8.jpg','2023-11-01 00:00:00',1,10),(53,'Chuột có dây gaming lazer deathadder','Chuột có dây gaming lazer deathadder',700000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783070/pkkp51pcpmat77fbphiu.jpg','2023-11-01 00:00:00',1,10),(54,'Chuột gaming asus','Chuột gaming asus',1500000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783092/ckgnrhwlhnbvc3rer6hp.jpg','2023-11-01 00:00:00',1,10),(55,'Chuột gaming hyoerx pulsefire','Chuột gaming hyoerx pulsefire',2000000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783130/dns9nb5q5apxwhv8ibzz.jpg','2023-11-01 00:00:00',1,10),(56,'Bàn phím bluetooth A4Tech','Bàn phím bluetooth A4Tech',1500000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783262/jfohzlqhqxuo83cetnvl.jpg','2023-11-01 00:00:00',1,10),(57,'Bàn phím bluetooth Logitech380','Bàn phím bluetooth Logitech380',2000000,'Logitech','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783288/pq1o7gos6pse3wwvtqvv.jpg','2023-11-01 00:00:00',1,10),(58,'Bàn phím cơ bluetooth dareu e75 pro','Bàn phím cơ bluetooth dareu e75 pro',2000000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783322/xtxic2rfnjpaao9n6mux.jpg','2023-11-02 00:00:00',1,1),(59,'Sạc type C cho Iphone','Sạc type C cho Iphone',500000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783363/x9viimvc8tsaenbsfk2w.jpg','2023-11-01 00:00:00',1,10),(60,'Adapter Sạc Type C ','Adapter Sạc Type C ',300000,'','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698783393/xemoftxl59q9xfa3drbj.jpg','2023-11-01 00:00:00',1,10),(61,'Lenovo Legion 7','Lenovo Legion 7',35000000,'Lenovo','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869857/nymd0wt90cggcfgn50lt.jpg','2023-11-02 00:00:00',1,2),(62,'Laptop Acer Aspire 3 A351-57-379K i3','Laptop Acer Aspire 3 A351-57-379K i3',20000000,'Acer','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869273/bygnupcb7ckil4fcmapz.jpg','2023-11-02 00:00:00',1,2),(63,'Laptop  Acer Nitro 5 AN515-57-53f9 i5','Laptop  Acer Nitro 5 AN515-57-53f9 i5',27000000,'Acer','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869635/monuxbjysif2wvm6vras.jpg','2023-11-02 00:00:00',1,2),(64,'Laptop DELL latitude 3420','Laptop DELL latitude 3420',20000000,'DELL','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869364/bkzzo7lnn7m2h9kgxxau.jpg','2023-11-02 00:00:00',1,2),(65,'Laptop DELL inspiron 3520 i3 ','Laptop DELL inspiron 3520 i3 ',15000000,'DELL','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869896/rtujrgcp8qolosplgkkn.jpg','2023-11-02 00:00:00',1,2),(66,'Laptop DELL XPS 15-7590','Laptop DELL XPS 15-7590',40000000,'DELL','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869873/d0kpjbftermq75laaygx.jpg','2023-11-02 00:00:00',1,2),(67,'Laptop Lenovo Legion 5 pro','Laptop Lenovo Legion 5 pro',5000000,'Lenovo','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869473/ie77zyw4qmghioufdwfp.png','2023-11-02 00:00:00',1,2),(68,'Macbook Air M1','Macbook Air M1',27000000,'Apple','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869509/rons4hsh6ol9hpzbawx2.png','2023-11-02 00:00:00',1,2),(69,'Macbook Air Pro','Macbook Air Pro',50000000,'Apple','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869541/hhjnwltfgplxw91gw17g.jpg','2023-11-02 00:00:00',1,2),(70,'Laptop Lenovo Thinkpad e15-gen-4 i7','Laptop Lenovo Thinkpad e15-gen-4 i7',30000000,'Lenovo','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698869914/ljrpg9xmgf46gglqyate.jpg','2023-11-02 00:00:00',1,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_order`
--

DROP TABLE IF EXISTS `sale_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,0) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ORDER_USER_idx` (`user_id`),
  CONSTRAINT `FK_ORDER_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_order`
--

LOCK TABLES `sale_order` WRITE;
/*!40000 ALTER TABLE `sale_order` DISABLE KEYS */;
INSERT INTO `sale_order` VALUES (4,50500000,'2020-02-03 00:00:00',7),(5,40040000,'2020-02-03 00:00:00',7),(6,21000000,'2020-02-03 00:00:00',7),(7,57570000,'2020-02-04 00:00:00',7),(8,71500000,'2020-02-05 00:00:00',6),(9,54500000,'2020-02-05 00:00:00',7),(10,121000000,'2020-02-07 00:00:00',6),(11,84000000,'2020-02-07 00:00:00',8),(12,100,'2020-11-17 18:43:31',NULL),(13,100,'2020-11-17 18:48:11',NULL),(14,NULL,'2023-10-26 18:17:08',16),(15,NULL,'2023-11-02 03:05:55',16),(16,NULL,'2023-11-02 22:22:01',18),(17,NULL,'2023-11-02 23:52:53',18);
/*!40000 ALTER TABLE `sale_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tagcol` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `active` tinyint DEFAULT NULL,
  `user_role` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'Thanh','Duong','thanh.dh@ou.edu.vn','0984461467','dhthanh','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO',1,'ROLE_ADMIN',NULL),(7,'Thanh','Duong','dhthanhqa@gmail.com','0984461461','thanhduong','$2a$10$RL0rTJd2ThLmCzYHMhz9aOBBZfA8ybYpa3Ugl9ds.Pkb8AjtSHWua',1,'ROLE_USER',NULL),(8,'Doremon','Mr','mon@gmail.com','1111111111','doremon','$2a$10$qv8SsUwRnp/YhPWTPqdgp.MXJ01hcW4ji6wKvP6.qkWWx1ZxhqxyG',1,'ROLE_USER',NULL),(9,'Tester','From React','abc@gmail.com','1234567890','thanhduong999','$2a$10$DT8XCG5IEd0RS0iKleeJ9.1LXWezuUj/qY2SLq/Vy64zUlxHHK9rG',NULL,'ROLE_USER','https://res.cloudinary.com/dxxwcby8l/image/upload/v1692330009/vuyk886cdgjykoi6qs3f.png'),(11,'test','test','test@gmail.com','1234567890','test','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO',1,'ROLE_USER',NULL),(12,'thanh','ngo','thanhtest@gmail.com','0354472852','thanh1','$2a$10$suBAywLiJTugOG7ma1NI9eLWQ1rotRGpnQqQ4qG9vnWu539AcU.oO',NULL,'ROLE_ADMIN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1697448096/zaus2enqkc7vylzo7ezu.png'),(13,'thanh','ngo','thanhtes1t@gmail.com','0354472852','thanh2','$2a$10$sr18aBFYMBnO2D319VxXsuCIrats36blgYMPDsHQp5.3FjGfqznwO',NULL,'ROLE_USER','https://res.cloudinary.com/dp1am0vsj/image/upload/v1697448174/wi47gwauxynmnm5pdmqm.png'),(14,'test21','test2','test2@gmail.com','0976106185','test2','test2',NULL,'ROLE_USER','https://res.cloudinary.com/dp1am0vsj/image/upload/v1697553823/y0l7unmeaoz9zh4rbkdc.png'),(15,'thanh','ngo','thanhtes1t@gmail.com','0354472852','thanh2','$2a$10$R7FvW4uwvKD/K/knEQvMD./YWj.a3ylxP9PvMvnWOtQMemuZOgNQK',NULL,'ROLE_USER','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698316391/okdz3n91phljltbzp3mq.png'),(16,'thanh','ngo','thanhtes1t@gmail.com','0354472852','thanh3','$2a$10$JjaUbBZNkN/.tzfCOX67c.0OVtRx8fUH4f3fxTlAGJhedHNcGfVp2',NULL,'ROLE_ADMIN','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698316473/s8tjcgdb7yjhfwpw7c8a.png'),(17,'test6','test6','test6@gmail.com','0354472852','test6','$2a$10$euKZyemARiPbdYozSnAZZeBs53Dr//xR0xugChSzcglxvaDXFX.Ge',NULL,'ROLE_USER','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698529791/kshctbp24nfendss5owx.webp'),(18,'Thành','Ngô Minh','ngominhthanh852@gmail.com','0354472852','ngominhthanh852','$2a$10$C5AbwEvIccT7m5.SJFTunOwO33QJq1hbGG5Jw9gJWycKtk5tL9CPi',NULL,'ROLE_USER','https://res.cloudinary.com/dp1am0vsj/image/upload/v1698938399/sz0kpts4y8w2ayhu7r6k.png');
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

-- Dump completed on 2023-11-08 10:48:26

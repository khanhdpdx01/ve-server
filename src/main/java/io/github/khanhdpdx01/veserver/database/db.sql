-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: vecert
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `diploma`
--

DROP TABLE IF EXISTS `diploma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diploma` (
  `serial_number` varchar(255) NOT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `date_of_enrollment` varchar(255) DEFAULT NULL,
  `date_of_graduation` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `gpa` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `graduation` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `mode_of_study` varchar(255) DEFAULT NULL,
  `place_of_birth` varchar(255) DEFAULT NULL,
  `ranking` varchar(255) DEFAULT NULL,
  `ref_number` varchar(255) DEFAULT NULL,
  `session` varchar(255) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `total_credits` varchar(255) DEFAULT NULL,
  `training_language` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `diploma_link` varchar(255) DEFAULT NULL,
  `appendix_link` varchar(255) DEFAULT NULL,
  `hash` text,
  `transaction_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diploma`
--

LOCK TABLES `diploma` WRITE;
/*!40000 ALTER TABLE `diploma` DISABLE KEYS */;
INSERT INTO `diploma` VALUES ('A123456','01-08-2001','01-08-2019','01-08-2023','Nghĩa','Nữ','8.0','19TCLC_DT2','815/QĐ-HV','Trần Văn','Kỹ sư','Công nghệ thông tin','Chính quy','Quảng Nam','Giỏi','240/2015/Q5','2019-2023','Công nghệ phần mềm',1,'4.0','150','Việt nam','102190100','QmP8etSaACDsuuqEswZDHBTQu8EKMVvp7UNkDDP4aKX9Ga','QmRhMvDuNYjL7tZ2uQQRSSeBj7XD8qHEcw4d7Yy3iKbc8U','��@3�WVs�Q?���k��Ǡ�[S\n��M�5M��',NULL),('A123457','03-08-2000','01-08-2019','01-08-2022','Ngọc','Nữ','8.0','18Nhat2','815/QĐ-HV','Nguyễn Văn','Kỹ sư','Công nghệ thông tin','Chính quy','Quảng Nam','Giỏi','240/2015/Q5','2019-2023','Công nghệ phần mềm',1,'4.0','150','Nhật bản','102190101','QmP8etSaACDsuuqEswZDHBTQu8EKMVvp7UNkDDP4aKX9Ga','QmRhMvDuNYjL7tZ2uQQRSSeBj7XD8qHEcw4d7Yy3iKbc8U','�fEnӮ�j6�{ׅ}�:Ì9<-\0��<�q�P�',NULL),('A123458','01-08-2001','01-08-2019','01-08-2022','Tuyết','Nữ','8.5','19TCLC_DT2','815/QĐ-HV','Bùi Thị','Kỹ sư','Công nghệ thông tin','Chính quy','Đà Nẵng','Giỏi','240/2015/Q5','2019-2023','Hệ thống thông tin',1,'4.0','150','Việt nam','102190102','QmP8etSaACDsuuqEswZDHBTQu8EKMVvp7UNkDDP4aKX9Ga','QmRhMvDuNYjL7tZ2uQQRSSeBj7XD8qHEcw4d7Yy3iKbc8U','J����q&��;�I�ϝ�ǌ��R�6�<�Q',NULL),('A123459','02-01-2001','01-08-2019','01-08-2023','Thảo','Nữ','8.6','19TCLC_DT2','815/QĐ-HV','Nguyễn Thị','Kỹ sư','Công nghệ thông tin','Chính quy','Quảng Trị','Giỏi','240/2015/Q5','2019-2023','Hệ thống thông tin',2,'4.0','151','Việt nam','102190103','QmP8etSaACDsuuqEswZDHBTQu8EKMVvp7UNkDDP4aKX9Ga','QmRhMvDuNYjL7tZ2uQQRSSeBj7XD8qHEcw4d7Yy3iKbc8U',NULL,NULL),('A123460','01-01-2001','01-08-2019','01-08-2023','Hải','Nữ','8.5','19TCLC_DT2','815/QĐ-HV','Đặng Ngọc','Kỹ sư','Công nghệ thông tin','Chính quy','Duy Xuyên, Quảng Nam','Giỏi','240/2020/Q1','2019-2023','Công nghệ phần mềm',3,'4.0','150','Việt nam','102190104','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf',NULL,NULL),('A123562','01-08-2001','01-08-2019','01-08-2022','Thịnh','Nữ','8.7','19TCLC_DT5','815/QĐ-HV','Đặng Ngọc','Kỹ sư','Công nghệ thông tin','Chính quy','Thừa Thiên Huế','Giỏi','240/2020/Q1','2019-2023','Mạng máy tính',2,'4.0','150','Việt nam','102190106','QmP8etSaACDsuuqEswZDHBTQu8EKMVvp7UNkDDP4aKX9Ga','QmRhMvDuNYjL7tZ2uQQRSSeBj7XD8qHEcw4d7Yy3iKbc8U',NULL,NULL),('A123563','01-08-2022','01-08-2019','01-08-2022','Vương','Nữ','9.0','19TCLC_DT4','815/QĐ-HV','Nguyễn Văn','Kỹ sư','Công nghệ thông tin','Chính quy','Duy Xuyên, Quảng Nam','Giỏi','240/2020/Q1','2019-2023','Công nghệ phần mềm',3,'4.0','150','Việt nam','102190120','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf',NULL,NULL),('A123565','01-08-2001','01-08-2019','01-08-2022','Tâm','Nữ','8.0','19TCLC_DT4','815/QĐ-HV','Đặng Ngọc','Kỹ sư','Công nghệ thông tin','Chính quy','Thừa Thiên Huế','Giỏi','240/2020/Q1','2019-2023','Mạng máy tính',1,'4.0','150','Việt nam','102190200','QmP8etSaACDsuuqEswZDHBTQu8EKMVvp7UNkDDP4aKX9Ga','QmRhMvDuNYjL7tZ2uQQRSSeBj7XD8qHEcw4d7Yy3iKbc8U',NULL,NULL);
/*!40000 ALTER TABLE `diploma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'Công nghệ thông tin'),(2,'Công nghệ tiên tiến Việt-Pháp'),(3,'Điện tử viễn thông'),(4,'Tự động hóa');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speciality`
--

DROP TABLE IF EXISTS `speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speciality` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKepxaa9lqom6xpj9fcx6g7qme9` (`major_id`),
  CONSTRAINT `FKepxaa9lqom6xpj9fcx6g7qme9` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speciality`
--

LOCK TABLES `speciality` WRITE;
/*!40000 ALTER TABLE `speciality` DISABLE KEYS */;
INSERT INTO `speciality` VALUES (1,'Công nghệ phần mềm',1),(2,'Mạng máy tính',1),(3,'Hệ thống thông tin',1),(4,'Khoa học dữ liệu và trí tuệ nhân tạo',1),(5,'An toàn thông tin',1),(6,'Công nghệ thông tin',2),(7,'Hệ thống thông tin',2),(8,'Công nghệ thông tin',3),(9,'Trí tuệ nhân tạo',3),(10,'Trí tuệ nhân tạo',4),(11,'Hệ thống nhúng',4);
/*!40000 ALTER TABLE `speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `v_user`
--

DROP TABLE IF EXISTS `v_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `v_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `v_user`
--

LOCK TABLES `v_user` WRITE;
/*!40000 ALTER TABLE `v_user` DISABLE KEYS */;
INSERT INTO `v_user` VALUES (1,'Nguyễn Tấn Khanh',_binary '','$2a$12$mvL0XyKk406LEwcnoj226uUCGdREwu6pBxf3CCGOUmEvH8YqXqdSi','0966257231','ROLE_ADMIN','khanh'),(2,'Nguyễn Thị A',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','0912345678','ROLE_ADMIN','nguyenthia'),(3,'admin',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','N/A','ROLE_SUPERADMIN','admin'),(4,'Hiệu trưởng',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','N/A','ROLE_PRINCIPAL','admin1');
/*!40000 ALTER TABLE `v_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-06 14:14:17

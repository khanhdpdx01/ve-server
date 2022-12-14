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
INSERT INTO `diploma` VALUES ('A123456','01-08-2001','01-08-2019','01-08-2023','Ngh??a','N???','8.0','19TCLC_DT2','815/Q??-HV','Tr???n V??n','K??? s??','C??ng ngh??? th??ng tin','Ch??nh quy','Qu???ng Nam','Gi???i','240/2015/Q5','2019-2023','C??ng ngh??? ph???n m???m',3,'4.0','150','Vi???t nam','102190100','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','??????@3???WVs???Q??????????k???????????[S\n??????M???5M??????',NULL),('A123457','03-08-2000','01-08-2019','01-08-2022','Ng???c','N???','8.0','18Nhat2','815/Q??-HV','Nguy???n V??n','K??? s??','C??ng ngh??? th??ng tin','Ch??nh quy','Qu???ng Nam','Gi???i','240/2015/Q5','2019-2023','C??ng ngh??? ph???n m???m',3,'4.0','150','Nh???t b???n','102190101','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','???fEn?????j6???{??}???:??9<-\0??????<???q???P???',NULL),('A123458','01-08-2001','01-08-2019','01-08-2022','Tuy???t','N???','8.5','19TCLC_DT2','815/Q??-HV','B??i Th???','K??? s??','C??ng ngh??? th??ng tin','Ch??nh quy','???? N???ng','Gi???i','240/2015/Q5','2019-2023','H??? th???ng th??ng tin',3,'4.0','150','Vi???t nam','102190102','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','???eH?????????6T?????????????????2?????????o???d????????c???',NULL),('A123460','01-01-2001','01-08-2019','01-08-2023','H???i','N???','8.5','19TCLC_DT2','815/Q??-HV','?????ng Ng???c','K??? s??','C??ng ngh??? th??ng tin','Ch??nh quy','Duy Xuy??n, Qu???ng Nam','Gi???i','240/2020/Q1','2019-2023','C??ng ngh??? ph???n m???m',3,'4.0','150','Vi???t nam','102190104','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','??????SR???1\"~<???\r4F??????9P@???^??????6???B#^:???',NULL),('A123480','04-09-2001','01-09-2019','01-09-2023','Tri???u','N???','8.0','19TCLC_DT3','815/Q??-HV','Nguy???n Qu??','K??? s??','C??ng ngh??? th??ng tin','Ch??nh quy','Qu???ng Nam','Gi???i','240/2015/Q5','2019-2023','M???ng m??y t??nh',3,'4.0','150','Vi???t nam','102190079','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','??????\n???;X&c??????????????????q????????????r???v??????v2T9n??????',NULL),('A123563','01-08-2022','01-08-2019','01-08-2022','V????ng','N???','9.0','19TCLC_DT4','815/Q??-HV','Nguy???n V??n','K??? s??','C??ng ngh??? th??ng tin','Ch??nh quy','Duy Xuy??n, Qu???ng Nam','Gi???i','240/2020/Q1','2019-2023','C??ng ngh??? ph???n m???m',3,'4.0','150','Vi???t nam','102190120','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','??????9??????f???B??????p????7??????qs??????_??????`',NULL),('A123565','01-08-2001','01-08-2019','01-08-2022','T??m','N???','8.0','19TCLC_DT4','815/Q??-HV','?????ng Ng???c','K??? s??','C??ng ngh??? th??ng tin','Ch??nh quy','Th???a Thi??n Hu???','Gi???i','240/2020/Q1','2019-2023','M???ng m??y t??nh',3,'4.0','150','Vi???t nam','102190200','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','\\?????????R???\r???n?????OB/*\0??? #)???Y4h\"??.???L???',NULL);
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
INSERT INTO `major` VALUES (1,'C??ng ngh??? th??ng tin'),(2,'C??ng ngh??? ti??n ti???n Vi???t-Ph??p'),(3,'??i???n t??? vi???n th??ng'),(4,'T??? ?????ng h??a');
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
INSERT INTO `speciality` VALUES (1,'C??ng ngh??? ph???n m???m',1),(2,'M???ng m??y t??nh',1),(3,'H??? th???ng th??ng tin',1),(4,'Khoa h???c d??? li???u v?? tr?? tu??? nh??n t???o',1),(5,'An to??n th??ng tin',1),(6,'C??ng ngh??? th??ng tin',2),(7,'H??? th???ng th??ng tin',2),(8,'C??ng ngh??? th??ng tin',3),(9,'Tr?? tu??? nh??n t???o',3),(10,'Tr?? tu??? nh??n t???o',4),(11,'H??? th???ng nh??ng',4);
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
INSERT INTO `v_user` VALUES (1,'Nguy???n T???n Khanh',_binary '','$2a$12$mvL0XyKk406LEwcnoj226uUCGdREwu6pBxf3CCGOUmEvH8YqXqdSi','0966257231','ROLE_ADMIN','khanh'),(2,'Nguy???n Th??? A',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','0912345678','ROLE_ADMIN','nguyenthia'),(3,'admin',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','N/A','ROLE_SUPERADMIN','admin'),(4,'Hi???u tr?????ng',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','N/A','ROLE_PRINCIPAL','admin1');
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

-- Dump completed on 2022-09-16 10:11:29

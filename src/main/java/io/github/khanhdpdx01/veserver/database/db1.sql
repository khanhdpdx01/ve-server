
DROP TABLE IF EXISTS `diploma`;

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
);

INSERT INTO `diploma` VALUES ('A123456','01-08-2001','01-08-2019','01-08-2023','Nghĩa','Nữ','8.0','19TCLC_DT2','815/QĐ-HV','Trần Văn','Kỹ sư','Công nghệ thông tin','Chính quy','Quảng Nam','Giỏi','240/2015/Q5','2019-2023','Công nghệ phần mềm',3,'4.0','150','Việt nam','102190100','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','��@3�WVs�Q?���k��Ǡ�[S\n��M�5M��',NULL),('A123457','03-08-2000','01-08-2019','01-08-2022','Ngọc','Nữ','8.0','18Nhat2','815/QĐ-HV','Nguyễn Văn','Kỹ sư','Công nghệ thông tin','Chính quy','Quảng Nam','Giỏi','240/2015/Q5','2019-2023','Công nghệ phần mềm',3,'4.0','150','Nhật bản','102190101','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','�fEnӮ�j6�{ׅ}�:Ì9<-\0��<�q�P�',NULL),('A123458','01-08-2001','01-08-2019','01-08-2022','Tuyết','Nữ','8.5','19TCLC_DT2','815/QĐ-HV','Bùi Thị','Kỹ sư','Công nghệ thông tin','Chính quy','Đà Nẵng','Giỏi','240/2015/Q5','2019-2023','Hệ thống thông tin',3,'4.0','150','Việt nam','102190102','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','�eH���6T��ܮ���2���o�d�÷�c�',NULL),('A123460','01-01-2001','01-08-2019','01-08-2023','Hải','Nữ','8.5','19TCLC_DT2','815/QĐ-HV','Đặng Ngọc','Kỹ sư','Công nghệ thông tin','Chính quy','Duy Xuyên, Quảng Nam','Giỏi','240/2020/Q1','2019-2023','Công nghệ phần mềm',3,'4.0','150','Việt nam','102190104','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','��SR�1\"~<�\r4F��9P@�^��6�B#^:�',NULL),('A123480','04-09-2001','01-09-2019','01-09-2023','Triều','Nữ','8.0','19TCLC_DT3','815/QĐ-HV','Nguyễn Quý','Kỹ sư','Công nghệ thông tin','Chính quy','Quảng Nam','Giỏi','240/2015/Q5','2019-2023','Mạng máy tính',3,'4.0','150','Việt nam','102190079','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','��\n�;X&c������q����r�v��v2T9n��',NULL),('A123563','01-08-2022','01-08-2019','01-08-2022','Vương','Nữ','9.0','19TCLC_DT4','815/QĐ-HV','Nguyễn Văn','Kỹ sư','Công nghệ thông tin','Chính quy','Duy Xuyên, Quảng Nam','Giỏi','240/2020/Q1','2019-2023','Công nghệ phần mềm',3,'4.0','150','Việt nam','102190120','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','��9��f�B��p�?7��qs��_��`',NULL),('A123565','01-08-2001','01-08-2019','01-08-2022','Tâm','Nữ','8.0','19TCLC_DT4','815/QĐ-HV','Đặng Ngọc','Kỹ sư','Công nghệ thông tin','Chính quy','Thừa Thiên Huế','Giỏi','240/2020/Q1','2019-2023','Mạng máy tính',3,'4.0','150','Việt nam','102190200','VBCC_Nguyen_Van_A.pdf','VBCC_Phu_luc_Nguyen_Van_A.pdf','\\���R�\r�nߵ�OB/*\0� #)�Y4h\"ߜ.�L�',NULL);

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `major` VALUES (1,'Công nghệ thông tin'),(2,'Công nghệ tiên tiến Việt-Pháp'),(3,'Điện tử viễn thông'),(4,'Tự động hóa');

CREATE TABLE `speciality` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKepxaa9lqom6xpj9fcx6g7qme9` (`major_id`),
  CONSTRAINT `FKepxaa9lqom6xpj9fcx6g7qme9` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)
);

INSERT INTO `speciality` VALUES (1,'Công nghệ phần mềm',1),(2,'Mạng máy tính',1),(3,'Hệ thống thông tin',1),(4,'Khoa học dữ liệu và trí tuệ nhân tạo',1),(5,'An toàn thông tin',1),(6,'Công nghệ thông tin',2),(7,'Hệ thống thông tin',2),(8,'Công nghệ thông tin',3),(9,'Trí tuệ nhân tạo',3),(10,'Trí tuệ nhân tạo',4),(11,'Hệ thống nhúng',4);

CREATE TABLE `v_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

INSERT INTO `v_user` VALUES (1,'Nguyễn Tấn Khanh',_binary '','$2a$12$mvL0XyKk406LEwcnoj226uUCGdREwu6pBxf3CCGOUmEvH8YqXqdSi','0966257231','ROLE_ADMIN','khanh'),(2,'Nguyễn Thị A',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','0912345678','ROLE_ADMIN','nguyenthia'),(3,'admin',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','N/A','ROLE_SUPERADMIN','admin'),(4,'Hiệu trưởng',_binary '','$2a$12$Xx1QV2BZy/Ceo8NH.m.BhOZAdjHL1xaLKVEDkKhMVdY5suRJD6uri','N/A','ROLE_PRINCIPAL','admin1');

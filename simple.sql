-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: simple
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `mac` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'MAC',
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '设备名称',
  `open` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '开关:''0'' 关闭, ''1'' 打开',
  `type` char(2) COLLATE utf8_bin NOT NULL DEFAULT '00' COMMENT '设备类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifytime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`mac`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES ('20210311001','TEST1','0','00','设备1','2021-03-11 02:42:33','2021-03-11 02:42:33'),('20210311002','TEST2','0','00','设备2','2021-03-11 06:26:28','2021-03-11 06:35:16');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int DEFAULT NULL COMMENT '父ID',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `type` char(2) DEFAULT NULL COMMENT '类型 ''01''  菜单,''02'' 链接 ''03'' 按钮 ',
  `url` varchar(50) DEFAULT NULL COMMENT '路径',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `orders` int DEFAULT '0' COMMENT '排序',
  `icon` varchar(45) DEFAULT NULL COMMENT '资源bootstrap图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,0,'系统管理','01','','',1,'glyphicon glyphicon-cog'),(2,1,'人员管理','02','user/listUser','',1,''),(3,1,'角色管理','02','role/listRole','',2,NULL),(4,1,'资源管理','02','permission/listPermission','',3,NULL),(14,4,'添加资源','03','permission/addPermission','',1,NULL),(15,4,'修改资源','03','permission/editPermission','',2,NULL),(16,4,'删除资源','03','permission/deletePermission','',3,NULL),(17,2,'添加用户','03','user/addUser','',1,NULL),(19,2,'删除用户','03','user/deleteUser','',3,NULL),(20,2,'批量删除用户','03','user/batchDeleteUser','',4,NULL),(21,3,'添加角色','03','role/addRole','',1,NULL),(22,3,'修改角色','03','role/editRole','',2,NULL),(23,3,'删除角色','03','role/deleteRole','',3,NULL),(25,3,'角色赋权','03','role/authorPermission','',4,NULL),(48,2,'修改用户','03','user/editUser','',2,NULL),(49,0,'设备管理','01','','',2,NULL),(50,49,'开关设备','02','device/listDevice','',1,NULL),(51,50,'添加设备','03','device/addDevice','',1,NULL),(52,50,'修改设备','03','device/editDevice','',2,NULL),(53,50,'删除设备','03','device/deleteDevice','',3,NULL);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('manager','管理员','管理'),('user','用户','简单查看'),('worker','员工','简单管理');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_id` varchar(50) NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '资源ID',
  UNIQUE KEY `UK_role_permission` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES ('manager',1),('manager',2),('manager',3),('manager',4),('manager',14),('manager',15),('manager',16),('manager',17),('manager',19),('manager',20),('manager',21),('manager',22),('manager',23),('manager',25),('manager',48),('manager',49),('manager',50),('manager',51),('manager',52),('manager',53),('user',49),('user',50),('worker',49),('worker',50),('worker',53);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `salt` varchar(45) DEFAULT NULL COMMENT '盐',
  `enabled` char(1) DEFAULT '1' COMMENT '使能 ''0'' 无效 ''1'' 有效',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `lasttime` datetime DEFAULT NULL COMMENT '最近登陆时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','admin','e10adc3949ba59abbe56e057f20f883e','18224516628@163.com',NULL,'1',NULL,'2021-03-11 08:58:26'),('e173e878c7ac4ddba03ff50a3e9adb09','wulianwei','e10adc3949ba59abbe56e057f20f883e','18224516628@163.com',NULL,'1','2021-02-06 01:14:51','2021-03-11 08:57:53'),('e21af46366e84f9688014c1c3b010726','lurenjia','e10adc3949ba59abbe56e057f20f883e','18224516628@163.com',NULL,'1','2021-03-11 06:46:52','2021-03-11 06:47:09');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `role_id` varchar(50) NOT NULL COMMENT '角色ID',
  UNIQUE KEY `UK_user_role` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('1','manager'),('3b97dc27ddf546a387d3d6864d17c9fd','manager'),('642a6d6e2d50441c90770027d91f42f1','manager'),('898c852ca04740b5ad334d6db3789fea','user'),('898c852ca04740b5ad334d6db3789fea','worker'),('e173e878c7ac4ddba03ff50a3e9adb09','worker'),('e21af46366e84f9688014c1c3b010726','user');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-11 17:02:43

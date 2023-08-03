DROP DATABASE IF EXISTS dice;
CREATE DATABASE dice CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE dice;

-- MySQL dump 10.13  Distrib 8.0.33, for macos13.3 (arm64)
--
-- Host: 127.0.0.1    Database: dice
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `title` varchar(255) NOT NULL COMMENT '内容标题',
                           `content` mediumtext COMMENT '内容',
                           `hits` int NOT NULL DEFAULT '0' COMMENT '点击量',
                           `tags` varchar(255) DEFAULT NULL COMMENT '标签列表',
                           `category` varchar(255) DEFAULT NULL COMMENT '文章分类',
                           `status` smallint DEFAULT NULL COMMENT '内容状态',
                           `type` varchar(32) DEFAULT NULL COMMENT '内容类别',
                           `allow_comment` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否允许评论',
                           `comment_count` int NOT NULL DEFAULT '0' COMMENT '评论数量',
                           `priority` int DEFAULT '0' COMMENT '文章优先级',
                           `create_time` timestamp NOT NULL COMMENT '创建时间',
                           `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `creator` int DEFAULT NULL COMMENT '创建人',
                           `modifier` int DEFAULT NULL COMMENT '修改人',
                           `deleted` int DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `title`, `content`, `hits`, `tags`, `category`, `status`, `type`, `allow_comment`, `comment_count`, `priority`, `create_time`, `update_time`, `creator`, `modifier`, `deleted`) VALUES (1,'Hello world','\n欢迎使用[Dice](https://github.com/bihell/Dice)! 这是你的第一篇博客。快点来写点什么吧\n\n```java\npublic static void main(String[] args){\n    System.out.println(\"Hello world\");\n}\n```\n\n> 想要了解更多详细信息，可以查看[文档](https://github.com/bihell/Dice/blob/master/README.md)。',0,'First','New',0,'post',1,0,0,'2023-06-06 03:10:30','2023-06-06 03:10:30',1,NULL,0);
INSERT INTO `article` (`id`, `title`, `content`, `hits`, `tags`, `category`, `status`, `type`, `allow_comment`, `comment_count`, `priority`, `create_time`, `update_time`, `creator`, `modifier`, `deleted`) VALUES (2,'关于','# About me\n### Hello word\n这是关于我的页面\n\n* [Github](https://github.com/bihell)\n* [哔哩哔哩](https://space.bilibili.com/88900889)\n\n### 也可以设置别的页面\n* 比如友链页面',0,NULL,NULL,0,'page',1,0,0,'2023-06-06 03:10:30','2023-06-06 03:10:30',1,NULL,0);

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `article_id` int NOT NULL,
                           `p_id` int DEFAULT NULL,
                           `content` text NOT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `website` varchar(255) DEFAULT NULL,
                           `agree` int NOT NULL DEFAULT '0',
                           `disagree` int NOT NULL DEFAULT '0',
                           `ip` varchar(255) DEFAULT NULL,
                           `agent` varchar(255) DEFAULT NULL,
                           `status` int NOT NULL DEFAULT '0',
                           `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `article_id`, `p_id`, `content`, `name`, `email`, `website`, `agree`, `disagree`, `ip`, `agent`, `status`, `create_time`) VALUES (1,1,NULL,'## 测试评论\n这是我的网址[Dice](http://bihell.com)','tpxcer','tpxcer@outlook.com','https://bihell.com',1,0,'0.0.0.1','',0,'2023-06-06 03:10:30');

--
-- Table structure for table `ip_address`
--

DROP TABLE IF EXISTS `ip_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ip_address` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `ip_start` varchar(15) COLLATE utf8mb4_bin NOT NULL,
                              `ip_end` varchar(15) COLLATE utf8mb4_bin NOT NULL,
                              `area` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区域',
                              `operator` varchar(6) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运营商',
                              `ip_start_num` bigint NOT NULL,
                              `ip_end_num` bigint NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `ip_address_ip_end_num_index` (`ip_end_num`),
                              KEY `ip_address_ip_start_num_index` (`ip_start_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IP地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_address`
--


--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
                       `id` int NOT NULL AUTO_INCREMENT,
                       `action` varchar(255) DEFAULT NULL,
                       `data` text,
                       `message` varchar(255) DEFAULT NULL,
                       `type` varchar(255) DEFAULT NULL,
                       `ip` varchar(255) DEFAULT NULL,
                       `user_id` int DEFAULT NULL,
                       `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--


--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `name` varchar(255) NOT NULL,
                         `url` varchar(1023) NOT NULL,
                         `thumb_url` varchar(1023) DEFAULT NULL,
                         `suffix` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--


--
-- Table structure for table `meta`
--

DROP TABLE IF EXISTS `meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meta` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `type` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta`
--

INSERT INTO `meta` (`id`, `name`, `type`) VALUES (1,'First','tag');
INSERT INTO `meta` (`id`, `name`, `type`) VALUES (2,'New','category');

--
-- Table structure for table `middle`
--

DROP TABLE IF EXISTS `middle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `middle` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `a_id` int NOT NULL,
                          `m_id` int NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middle`
--

INSERT INTO `middle` (`id`, `a_id`, `m_id`) VALUES (1,1,1);
INSERT INTO `middle` (`id`, `a_id`, `m_id`) VALUES (2,1,2);

--
-- Table structure for table `nav_detail`
--

DROP TABLE IF EXISTS `nav_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nav_detail` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '导航明细项id',
                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `creator` int DEFAULT NULL COMMENT '创建人',
                              `modifier` int DEFAULT NULL COMMENT '修改人',
                              `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标识（0.未删除,1.已删除）',
                              `is_public` tinyint DEFAULT '1' COMMENT '是否公开',
                              `icon` varchar(255) DEFAULT NULL COMMENT '图标',
                              `description` varchar(255) DEFAULT NULL COMMENT '简介',
                              `name` varchar(255) DEFAULT NULL COMMENT '导航地址名称',
                              `type_id` int DEFAULT NULL COMMENT '类型id',
                              `status` tinyint DEFAULT NULL COMMENT '状态（1启用，0禁用）',
                              `url` varchar(255) DEFAULT NULL COMMENT '导航网址',
                              `sort` int DEFAULT NULL COMMENT '排序',
                              PRIMARY KEY (`id`),
                              KEY `nav_detail_creator_index` (`creator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='导航明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nav_detail`
--


--
-- Table structure for table `nav_type`
--

DROP TABLE IF EXISTS `nav_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nav_type` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '导航类型id',
                            `name` varchar(50) DEFAULT NULL COMMENT '导航类型名称',
                            `creator` int DEFAULT NULL COMMENT '创建人',
                            `modifier` int DEFAULT NULL COMMENT '修改人',
                            `is_public` int DEFAULT '1' COMMENT '是否公开：1是，0否',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `icon` varchar(255) DEFAULT NULL COMMENT '类型图标',
                            `status` tinyint DEFAULT '1' COMMENT '状态（1启用，0禁用）',
                            `parent_id` int DEFAULT NULL,
                            `level` int DEFAULT NULL,
                            `sort` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `nav_type_creator_index` (`creator`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nav_type`
--

INSERT INTO `nav_type` (`id`, `name`, `creator`, `modifier`, `is_public`, `create_time`, `update_time`, `icon`, `status`, `parent_id`, `level`, `sort`) VALUES (1,'test',NULL,NULL,1,'2023-06-14 09:53:47','2023-06-14 09:53:47','ant-design:account-book-filled',1,NULL,NULL,1);

--
-- Table structure for table `snippet_file`
--

DROP TABLE IF EXISTS `snippet_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `snippet_file` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `title` varchar(255) COLLATE utf8mb4_bin NOT NULL,
                                `content` text COLLATE utf8mb4_bin,
                                `language` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
                                `tabs` int DEFAULT NULL,
                                `snippet_id` int DEFAULT NULL,
                                `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                KEY `index_snippet_files_on_snippet_id` (`snippet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `snippet_file`
--


--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `dept_name` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
                            `parent_id` bigint DEFAULT NULL COMMENT '父id',
                            `level` int DEFAULT NULL COMMENT '部门层级',
                            `status` int NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
                            `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
                            `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            `version` int NOT NULL DEFAULT '0' COMMENT '版本',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `sys_department_name_uindex` (`dept_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `level`, `status`, `sort`, `remark`, `version`, `create_time`, `update_time`) VALUES (1,'管理组',NULL,NULL,1,1,'拥有至高无上的权利',0,'2021-07-06 08:51:04',NULL);

create table sys_log_login
(
    id         bigint auto_increment comment '日志ID'
        primary key,
    user_id    bigint                  null comment '用户ID',
    account    varchar(128) default '' null comment '登陆账号',
    login_type varchar(32)  default '' null comment '登陆类型',
    os         varchar(64)  default '' null comment '操作系统',
    browser    varchar(64)  default '' null comment '浏览器类型',
    ip         varchar(64)  default '' null comment '登录IP地址',
    location   varchar(64)  default '' null comment '登录地点',
    login_time datetime                null comment '登录时间',
    success    char(2)      default '' null comment '是否成功',
    message    varchar(128) default '' null comment '返回消息'
)
    comment '登陆日志表';

--
-- Table structure for table `sys_login_log`
--

DROP TABLE IF EXISTS `sys_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_login_log` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `request_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求ID',
                                 `username` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
                                 `ip` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IP',
                                 `area` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区域',
                                 `operator` varchar(6) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运营商',
                                 `token` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'tokenMd5值',
                                 `type` int DEFAULT NULL COMMENT '1:登录，2：登出',
                                 `success` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否成功 true:成功/false:失败',
                                 `code` int DEFAULT NULL COMMENT '响应码',
                                 `exception_message` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '失败消息记录',
                                 `user_agent` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
                                 `browser_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
                                 `browser_version` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器版本',
                                 `engine_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器引擎名称',
                                 `engine_version` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器引擎版本',
                                 `os_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '系统名称',
                                 `platform_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台名称',
                                 `mobile` tinyint(1) DEFAULT NULL COMMENT '是否是手机,0:否,1:是',
                                 `device_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '移动端设备名称',
                                 `device_model` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '移动端设备型号',
                                 `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                 `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标识（0.未删除,1.已删除）',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统登录日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_login_log`
--


--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限名称',
                            `parent_id` bigint DEFAULT NULL COMMENT '父id',
                            `route_path` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路径',
                            `code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一编码',
                            `icon` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图标',
                            `type` int NOT NULL COMMENT '类型，0：目录，1：菜单，2：按钮',
                            `level` int NOT NULL COMMENT '层级，1：第一级，2：第二级，N：第N级',
                            `status` int NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
                            `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
                            `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            `version` int NOT NULL DEFAULT '0' COMMENT '版本',
                            `component` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `is_show` int DEFAULT NULL,
                            `keep_alive` int DEFAULT NULL,
                            `is_ext` int DEFAULT NULL,
                            `frame` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `sys_permission_code_uindex` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (25,'系统',NULL,'system',NULL,'ri:admin-line',1,1,1,999,NULL,0,NULL,'2021-07-12 03:49:58','2021-07-13 13:13:18',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (26,'用户管理',25,'user','sys:user:management',NULL,2,2,1,1,NULL,0,'/sys/account/index','2021-07-12 03:51:44','2021-07-12 09:33:54',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (27,'用户新增',26,NULL,'sys:user:add',NULL,3,3,1,1,NULL,0,NULL,'2021-07-12 03:52:19','2021-07-12 04:01:48',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (28,'用户修改',26,NULL,'sys:user:update',NULL,3,3,1,2,NULL,0,NULL,'2021-07-12 08:57:31','2021-07-12 08:57:31',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (29,'用户删除',26,NULL,'sys:user:delete',NULL,3,3,1,3,NULL,0,NULL,'2021-07-12 08:57:52','2021-07-12 08:57:52',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (30,'用户详情',26,NULL,'sys:user:info',NULL,3,3,1,4,NULL,0,NULL,'2021-07-12 08:58:11','2021-07-12 08:58:11',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (31,'用户分页列表',26,NULL,'sys:user:page',NULL,3,3,1,5,NULL,0,NULL,'2021-07-12 08:58:28','2021-07-12 08:58:28',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (32,'用户修改密码',26,NULL,'sys:user:update:password',NULL,3,3,1,6,NULL,0,NULL,'2021-07-12 08:58:54','2021-07-12 08:58:54',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (33,'用户修改头像',26,NULL,'sys:user:update:head',NULL,3,3,1,7,NULL,0,NULL,'2021-07-12 08:59:12','2021-07-12 08:59:12',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (34,'用户重置密码',26,NULL,'sys:user:reset:password',NULL,3,3,1,8,NULL,0,NULL,'2021-07-12 08:59:37','2021-07-12 08:59:37',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (35,'角色管理',25,'role','sys:role:management',NULL,2,2,1,2,NULL,0,'/sys/role/index','2021-07-12 09:13:18','2021-07-12 09:13:18',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (36,'权限管理',25,'permission','sys:permission:management',NULL,2,2,1,3,NULL,0,'/sys/menu/index','2021-07-12 09:14:31','2021-07-12 09:14:31',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (37,'部门管理',25,'department','sys:department:management',NULL,2,2,1,4,NULL,0,'/sys/dept/index','2021-07-12 09:16:07','2021-07-12 09:16:07',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (38,'日志管理',25,'log','sys:log:manager',NULL,2,2,1,5,NULL,0,NULL,'2021-07-12 09:16:39','2021-07-12 09:16:39',0,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (39,'角色新增',35,NULL,'sys:role:add',NULL,3,3,1,1,NULL,0,NULL,'2021-07-12 09:28:18','2021-07-12 09:28:18',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (40,'角色修改',35,NULL,'sys:role:update',NULL,3,3,1,2,NULL,0,NULL,'2021-07-12 09:28:33','2021-07-12 09:28:33',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (41,'角色删除',35,NULL,'sys:role:delete',NULL,3,3,1,3,NULL,0,NULL,'2021-07-12 09:28:50','2021-07-12 09:28:50',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (42,'角色详情',35,NULL,'sys:role:info',NULL,3,3,1,4,NULL,0,NULL,'2021-07-12 09:29:08','2021-07-12 09:29:08',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (43,'角色分页列表',35,NULL,'sys:role:page',NULL,3,3,1,5,NULL,0,NULL,'2021-07-12 09:29:21','2021-07-12 09:29:21',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (44,'角色列表',35,NULL,'sys:role:list',NULL,3,3,1,6,NULL,0,NULL,'2021-07-12 09:29:37','2021-07-12 09:29:37',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (45,'角色权限ID列表',35,NULL,'sys:permission:three-ids-by-role-id',NULL,3,3,1,7,NULL,0,NULL,'2021-07-12 09:29:56','2021-07-12 09:29:56',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (46,'权限新增',36,NULL,'sys:permission:add',NULL,3,3,1,1,NULL,0,NULL,'2021-07-12 09:37:01','2021-07-12 09:37:01',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (47,'权限修改',36,NULL,'sys:permission:update',NULL,3,3,1,2,NULL,0,NULL,'2021-07-12 09:37:14','2021-07-12 09:37:14',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (48,'权限删除',36,NULL,'sys:permission:delete',NULL,3,3,1,3,NULL,0,NULL,'2021-07-12 09:37:26','2021-07-12 09:37:26',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (49,'权限详情',36,NULL,'sys:permission:info',NULL,3,3,1,4,NULL,0,NULL,'2021-07-12 09:37:48','2021-07-12 09:37:48',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (50,'权限分页列表',36,NULL,'sys:permission:page',NULL,3,3,1,5,NULL,0,NULL,'2021-07-12 09:38:06','2021-07-12 09:38:06',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (51,'权限所有列表',36,NULL,'sys:permission:all:menu:list',NULL,3,3,1,6,NULL,0,NULL,'2021-07-12 09:38:27','2021-07-12 09:38:27',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (52,'权限所有树形列表',36,NULL,'sys:permission:all:menu:tree',NULL,3,3,1,7,NULL,0,NULL,'2021-07-12 09:38:46','2021-07-12 09:38:46',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (53,'权限用户列表',36,NULL,'sys:permission:menu:list',NULL,3,3,1,8,NULL,0,NULL,'2021-07-12 09:38:58','2021-07-12 09:38:58',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (54,'权限用户树形列表',36,NULL,'sys:permission:menu:tree',NULL,3,3,1,9,NULL,0,NULL,'2021-07-12 09:39:12','2021-07-12 09:39:12',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (55,'权限用户代码列表',36,NULL,'sys:permission:codes',NULL,3,3,1,19,NULL,0,NULL,'2021-07-12 09:39:26','2021-07-12 09:39:26',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (56,'导航菜单',36,NULL,'sys:permission:nav-menu',NULL,3,3,1,11,NULL,0,NULL,'2021-07-12 09:39:41','2021-07-12 09:39:41',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (57,'角色权限修改',36,NULL,'sys:role-permission:update',NULL,3,3,1,12,NULL,0,NULL,'2021-07-12 09:40:11','2021-07-12 09:40:11',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (58,'部门新增',37,NULL,'sys:department:add',NULL,3,3,1,1,NULL,0,NULL,'2021-07-12 09:41:41','2021-07-12 09:41:41',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (59,'部门修改',37,NULL,'sys:department:update',NULL,3,3,1,2,NULL,0,NULL,'2021-07-12 09:41:53','2021-07-12 09:41:53',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (60,'部门删除',37,NULL,'sys:department:delete',NULL,3,3,1,3,NULL,0,NULL,'2021-07-12 09:42:06','2021-07-12 09:42:06',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (61,'部门详情',37,NULL,'sys:department:info',NULL,3,3,1,4,NULL,0,NULL,'2021-07-12 09:42:17','2021-07-12 09:42:17',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (62,'部门分页列表',37,NULL,'sys:department:page',NULL,3,3,1,5,NULL,0,NULL,'2021-07-12 09:42:44','2021-07-12 09:42:44',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (63,'部门列表',37,NULL,'sys:department:list',NULL,3,3,1,6,NULL,0,NULL,'2021-07-12 09:43:13','2021-07-12 09:43:13',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (64,'部门树形列表',37,NULL,'sys:department:all:tree',NULL,3,3,1,7,NULL,0,NULL,'2021-07-12 09:43:28','2021-07-12 09:43:28',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (65,'操作日志列表',38,NULL,'sys:operation:log:page',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 03:01:25','2021-07-13 03:01:25',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (66,'登录日志列表',38,NULL,'sys:login:log:page',NULL,3,3,1,2,NULL,0,NULL,'2021-07-13 03:01:37','2021-07-13 03:01:37',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (67,'博客',NULL,'blog',NULL,'carbon:blog',1,1,1,1,NULL,0,NULL,'2021-07-13 03:03:01','2021-07-13 11:06:14',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (68,'文章列表',67,'posts','',NULL,2,2,1,1,NULL,0,'/blog/post/PostList','2021-07-13 03:05:31','2021-07-13 06:58:06',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (69,'编辑文章',67,'postEdit',NULL,NULL,2,2,1,2,NULL,0,'/blog/post/PostEdit','2021-07-13 03:07:03','2021-07-13 03:17:57',0,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (70,'评论列表',67,'comments',NULL,NULL,2,2,1,3,NULL,0,'/blog/comment/CommentList','2021-07-13 03:08:41','2021-07-13 03:08:41',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (71,'标签分类',67,'tags',NULL,NULL,2,2,1,4,NULL,0,'/blog/meta/Tag','2021-07-13 03:09:51','2021-07-13 03:09:51',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (72,'页面列表',67,'pages',NULL,NULL,2,2,1,5,NULL,0,'/blog/page/PageList','2021-07-13 03:11:01','2021-07-13 03:11:01',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (73,'编辑页面',67,'pageEdit',NULL,NULL,2,2,1,6,NULL,0,'/blog/page/PageEdit','2021-07-13 03:11:59','2021-07-13 03:11:59',0,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (74,'博客设置',67,'blogSetting',NULL,NULL,2,2,1,7,NULL,0,'/blog/setting/index','2021-07-13 03:12:30','2021-07-13 03:12:30',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (75,'导航',NULL,'nav',NULL,'ri:navigation-line',1,1,1,2,NULL,0,NULL,'2021-07-13 03:19:34','2021-07-13 13:13:37',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (76,'导航分类',75,'navType',NULL,NULL,2,2,1,1,NULL,0,'/nav/type/index','2021-07-13 03:20:33','2021-07-13 03:20:33',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (77,'导航清单',75,'navDetail',NULL,NULL,2,2,1,2,NULL,0,'/nav/list/index','2021-07-13 03:21:14','2021-07-13 03:21:14',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (78,'新增文章',68,NULL,'blog:posts:add',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 05:31:42','2021-07-13 07:06:29',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (79,'编辑文章',68,NULL,'blog:posts:update',NULL,3,3,1,2,NULL,0,NULL,'2021-07-13 05:32:58','2021-07-13 07:06:38',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (80,'删除文章',68,NULL,'blog:posts:delete',NULL,3,3,1,3,NULL,0,NULL,'2021-07-13 05:33:19','2021-07-13 07:06:44',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (81,'文章列表',68,NULL,'blog:posts:page',NULL,3,3,1,4,NULL,0,NULL,'2021-07-13 05:37:39','2021-07-13 07:07:00',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (82,'分类列表',76,NULL,'nav:type:list',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 06:03:06','2021-07-13 06:03:06',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (83,'新增导航',76,NULL,'nav:type:add',NULL,3,3,1,2,NULL,0,NULL,'2021-07-13 06:03:29','2021-07-13 06:03:29',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (84,'设置列表',74,NULL,'blog:option:list',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 06:51:13','2021-07-13 06:51:13',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (85,'设置更新',74,NULL,'blog:option:update',NULL,3,3,1,2,NULL,0,NULL,'2021-07-13 06:51:34','2021-07-13 06:51:34',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (86,'评论列表',70,NULL,'blog:comment:list',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 06:52:53','2021-07-13 06:52:53',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (87,'新增导航',77,NULL,'nav:detail:add',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 07:19:18','2021-07-13 07:19:18',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (88,'删除导航',77,NULL,'nav:detail:delete',NULL,3,3,1,2,NULL,0,NULL,'2021-07-13 07:19:45','2021-07-13 07:19:45',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (89,'导航列表',77,NULL,'nav:detail:page',NULL,3,3,1,3,NULL,0,NULL,'2021-07-13 07:20:08','2021-07-13 07:20:08',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (90,'标签清单',71,NULL,'blog:tag:list',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 07:24:45','2021-07-13 07:24:45',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (91,'标签更新',71,NULL,'blog:tag:update',NULL,3,3,1,2,NULL,0,NULL,'2021-07-13 07:25:26','2021-07-13 07:25:26',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (92,'标签删除',71,NULL,'blog:tag:delete',NULL,3,3,1,3,NULL,0,NULL,'2021-07-13 07:25:46','2021-07-13 07:25:46',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (93,'页面列表',72,NULL,'blog:pages:page',NULL,3,3,1,1,NULL,0,NULL,'2021-07-13 07:32:13','2021-07-13 07:32:13',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (94,'页面新增',72,NULL,'blog:pages:add',NULL,3,3,1,2,NULL,0,NULL,'2021-07-13 07:32:41','2021-07-13 07:32:41',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (95,'页面删除',72,NULL,'blog:pages:delete',NULL,3,3,1,3,NULL,0,NULL,'2021-07-13 07:33:02','2021-07-13 07:33:02',1,1,0,NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `route_path`, `code`, `icon`, `type`, `level`, `status`, `sort`, `remark`, `version`, `component`, `create_time`, `update_time`, `is_show`, `keep_alive`, `is_ext`, `frame`) VALUES (96,'页面更新',72,NULL,'blog:pages:update',NULL,3,3,1,4,NULL,0,NULL,'2021-07-13 07:33:23','2021-07-13 07:33:23',1,1,0,NULL);

--
-- Table structure for table `sys_operation_log`
--

DROP TABLE IF EXISTS `sys_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_operation_log` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `request_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求ID',
                                     `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                     `user_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
                                     `name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志名称',
                                     `ip` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IP',
                                     `area` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区域',
                                     `operator` varchar(6) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运营商',
                                     `path` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '全路径',
                                     `module` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模块名称',
                                     `class_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类名',
                                     `method_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '方法名称',
                                     `request_method` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求方式，GET/POST',
                                     `content_type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容类型',
                                     `request_body` tinyint(1) DEFAULT NULL COMMENT '是否是JSON请求映射参数',
                                     `param` text COLLATE utf8mb4_bin COMMENT '请求参数',
                                     `token` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'tokenMd5值',
                                     `type` int DEFAULT NULL COMMENT '0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件',
                                     `success` tinyint(1) DEFAULT NULL COMMENT '0:失败,1:成功',
                                     `code` int DEFAULT NULL COMMENT '响应结果状态码',
                                     `message` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '响应结果消息',
                                     `exception_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '异常类名称',
                                     `exception_message` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '异常信息',
                                     `browser_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
                                     `browser_version` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器版本',
                                     `engine_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器引擎名称',
                                     `engine_version` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器引擎版本',
                                     `os_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '系统名称',
                                     `platform_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台名称',
                                     `mobile` tinyint(1) DEFAULT NULL COMMENT '是否是手机,0:否,1:是',
                                     `device_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '移动端设备名称',
                                     `device_model` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '移动端设备型号',
                                     `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                     `deleted` int DEFAULT '0' COMMENT '逻辑删除，0：未删除，1：已删除',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_operation_log`
--


--
-- Table structure for table `sys_option`
--

DROP TABLE IF EXISTS `sys_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_option` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `option_key` varchar(100) NOT NULL,
                              `option_value` varchar(1023) NOT NULL,
                              `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `option_key` (`option_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_option`
--

INSERT INTO `sys_option` (`id`, `option_key`, `option_value`, `created`, `modified`) VALUES (1,'dice_init','true','2023-06-06 03:10:30','2023-06-14 09:17:20');
INSERT INTO `sys_option` (`id`, `option_key`, `option_value`, `created`, `modified`) VALUES (2,'email_username','tpxcer@outlook.com','2023-06-14 09:17:19','2023-06-14 09:17:19');

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `role_name` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
                            `role_code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色唯一编码',
                            `type` int DEFAULT NULL COMMENT '角色类型',
                            `status` int NOT NULL DEFAULT '1' COMMENT '角色状态，0：禁用，1：启用',
                            `description` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
                            `version` int NOT NULL DEFAULT '0' COMMENT '版本',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `is_deleted` int DEFAULT '0' COMMENT '逻辑删除 默认效果 0 没有删除 1 已经删除',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `sys_role_name_uindex` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `type`, `status`, `description`, `version`, `create_time`, `update_time`, `is_deleted`) VALUES (1,'管理员','admin',NULL,1,'神的存在',0,'2021-07-01 18:53:59','2021-07-01 18:57:54',0);
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `type`, `status`, `description`, `version`, `create_time`, `update_time`, `is_deleted`) VALUES (3,'Demo用户','Demo',NULL,1,NULL,0,'2021-07-07 15:48:20','2021-07-07 15:48:20',0);

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `role_id` bigint NOT NULL COMMENT '角色id',
                                 `permission_id` bigint NOT NULL COMMENT '权限id',
                                 `status` int NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
                                 `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                 `version` int NOT NULL DEFAULT '0' COMMENT '版本',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                 PRIMARY KEY (`id`),
                                 KEY `permission_id` (`permission_id`),
                                 KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色权限关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (28,1,25,1,NULL,0,'2021-07-12 05:23:33',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (29,1,26,1,NULL,0,'2021-07-12 05:23:33',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (30,1,27,1,NULL,0,'2021-07-12 05:23:33',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (31,1,32,1,NULL,0,'2021-07-12 09:03:37',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (32,1,33,1,NULL,0,'2021-07-12 09:03:37',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (33,1,34,1,NULL,0,'2021-07-12 09:03:37',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (34,1,28,1,NULL,0,'2021-07-12 09:03:37',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (35,1,29,1,NULL,0,'2021-07-12 09:03:37',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (36,1,30,1,NULL,0,'2021-07-12 09:03:37',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (37,1,31,1,NULL,0,'2021-07-12 09:03:37',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (38,1,35,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (39,1,39,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (40,1,40,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (41,1,41,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (42,1,42,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (43,1,43,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (44,1,44,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (45,1,45,1,NULL,0,'2021-07-12 09:30:21',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (49,3,43,1,NULL,0,'2021-07-12 09:32:04',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (50,3,31,1,NULL,0,'2021-07-12 09:32:04',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (51,1,36,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (52,1,46,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (53,1,47,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (54,1,48,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (55,1,49,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (56,1,50,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (57,1,51,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (58,1,52,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (59,1,53,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (60,1,54,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (61,1,55,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (62,1,56,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (63,1,57,1,NULL,0,'2021-07-12 09:40:27',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (64,1,64,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (65,1,67,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (66,1,68,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (67,1,69,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (68,1,70,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (69,1,71,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (70,1,72,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (71,1,73,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (72,1,74,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (73,1,37,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (74,1,58,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (75,1,59,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (76,1,60,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (77,1,61,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (78,1,62,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (79,1,63,1,NULL,0,'2021-07-13 03:13:10',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (80,1,75,1,NULL,0,'2021-07-13 03:21:30',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (81,1,76,1,NULL,0,'2021-07-13 03:21:30',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (82,1,77,1,NULL,0,'2021-07-13 03:21:30',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (83,3,81,1,NULL,0,'2021-07-13 05:37:58',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (87,3,69,1,NULL,0,'2021-07-13 05:39:05',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (89,3,50,1,NULL,0,'2021-07-13 05:39:05',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (90,3,62,1,NULL,0,'2021-07-13 05:39:05',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (93,3,82,1,NULL,0,'2021-07-13 06:03:39',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (95,3,86,1,NULL,0,'2021-07-13 06:53:11',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (97,3,89,1,NULL,0,'2021-07-13 07:22:01',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (99,3,90,1,NULL,0,'2021-07-13 07:28:14',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (101,3,93,1,NULL,0,'2021-07-13 07:33:49',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (102,1,38,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (103,1,65,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (104,1,66,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (105,1,78,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (106,1,79,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (107,1,80,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (108,1,81,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (109,1,82,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (110,1,83,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (111,1,84,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (112,1,85,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (113,1,86,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (114,1,87,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (115,1,88,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (116,1,89,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (117,1,90,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (118,1,91,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (119,1,92,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (120,1,93,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (121,1,94,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (122,1,95,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (123,1,96,1,NULL,0,'2021-07-13 07:39:32',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (124,3,67,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (125,3,68,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (126,3,70,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (127,3,71,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (128,3,72,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (129,3,73,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (130,3,75,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (131,3,76,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (132,3,77,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (133,3,25,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (134,3,26,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (135,3,35,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (136,3,36,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (137,3,37,1,NULL,0,'2021-07-13 07:42:26',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (138,3,51,1,NULL,0,'2021-07-13 07:45:47',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (139,3,52,1,NULL,0,'2021-07-13 07:45:47',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (140,3,53,1,NULL,0,'2021-07-13 07:45:47',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (141,3,54,1,NULL,0,'2021-07-13 07:45:47',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (142,3,55,1,NULL,0,'2021-07-13 07:45:47',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (143,3,56,1,NULL,0,'2021-07-13 07:45:47',NULL);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `permission_id`, `status`, `remark`, `version`, `create_time`, `update_time`) VALUES (144,3,64,1,NULL,0,'2023-06-21 05:55:58',NULL);

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `dept_id` int DEFAULT NULL,
                            `username` varchar(32) NOT NULL COMMENT '用户名',
                            `real_name` varchar(32) DEFAULT NULL COMMENT '真名',
                            `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
                            `email` varchar(45) DEFAULT NULL,
                            `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
                            `gender` tinyint DEFAULT '0' COMMENT '性别{0=保密, 1=男, 2=女}',
                            `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                            `password` varchar(64) NOT NULL,
                            `status` int NOT NULL DEFAULT '1' COMMENT '状态',
                            `is_deleted` tinyint DEFAULT '0' COMMENT '删除标志{0=正常, 1=删除}',
                            `login_ip` varchar(128) DEFAULT NULL COMMENT '最后登录IP',
                            `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
                            `remark` varchar(512) DEFAULT NULL,
                            `create_ty` varchar(32) DEFAULT NULL COMMENT '创建者',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

INSERT INTO `sys_user` (`id`, `dept_id`, `username`, `real_name`, `nickname`, `email`, `phone`, `gender`, `avatar`, `password`, `status`, `is_deleted`, `login_ip`, `login_date`, `remark`, `create_ty`, `create_time`, `update_by`, `update_time`) VALUES (1,1,'dice',NULL,NULL,'tpxcer@outlook.com',NULL,0,'https://avatars.githubusercontent.com/u/19926035?v=4','$2a$10$QclgMfXBXPoXX6BEJxNyz.aARYqwyOwCnikxWiRK0v4c8zR9/kuQu',1,0,'127.0.0.1','2023-06-26 15:13:43',NULL,NULL,'2019-05-16 02:24:35',NULL,'2023-06-26 15:13:43');
INSERT INTO `sys_user` (`id`, `dept_id`, `username`, `real_name`, `nickname`, `email`, `phone`, `gender`, `avatar`, `password`, `status`, `is_deleted`, `login_ip`, `login_date`, `remark`, `create_ty`, `create_time`, `update_by`, `update_time`) VALUES (2,1,'demo',NULL,'adf','demo@bihell.com','18888888888',0,'https://avatars.githubusercontent.com/u/19926035?v=4','$2a$10$QclgMfXBXPoXX6BEJxNyz.aARYqwyOwCnikxWiRK0v4c8zR9/kuQu',1,0,'127.0.0.1','2023-06-28 13:37:08','演示用户',NULL,'2019-12-27 15:34:01',NULL,'2023-06-28 13:37:07');

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `user_id` bigint DEFAULT NULL COMMENT '用户编号',
                                 `role_id` bigint DEFAULT NULL COMMENT '角色编号',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (6,1,1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (7,1,3);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (11,2,3);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (12,6,1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (13,7,1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (14,8,1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (15,9,1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (16,10,1);

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `concurrent` tinyint DEFAULT NULL COMMENT '是否允许并发',
                        `cron` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '定时规则',
                        `data` text COLLATE utf8mb4_bin COMMENT '执行参数',
                        `exec_at` datetime DEFAULT NULL COMMENT '执行时间',
                        `exec_result` text COLLATE utf8mb4_bin COMMENT '执行结果',
                        `job_class` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行类',
                        `job_group` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务组名',
                        `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名',
                        `note` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务说明',
                        `status` int DEFAULT NULL COMMENT '状态（0无效1有效）',
                        `creator` int DEFAULT NULL COMMENT '创建人',
                        `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间/注册时间',
                        `modifier` int DEFAULT NULL COMMENT '最后更新人',
                        `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
                        `is_deleted` int DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `concurrent`, `cron`, `data`, `exec_at`, `exec_result`, `job_class`, `job_group`, `name`, `note`, `status`, `creator`, `create_time`, `modifier`, `update_time`, `is_deleted`) VALUES (1,0,'0/6 * * * * ?','{\n\"appname\": \"dice\",\n\"version\":2\n}','2020-01-27 14:54:24','执行成功','com.bihell.dice.service.task.job.HelloJob','default','测试任务1','测试任务1',0,1,'2018-12-28 01:54:00',-1,'2019-03-27 03:47:11',0);

--
-- Table structure for table `task_log`
--

DROP TABLE IF EXISTS `task_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_log` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `exec_at` datetime DEFAULT NULL COMMENT '执行时间',
                            `exec_success` int DEFAULT NULL COMMENT '执行结果（成功:1、失败:0)',
                            `id_task` bigint DEFAULT NULL,
                            `job_exception` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '抛出异常',
                            `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_log`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 14:34:09

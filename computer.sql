/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : computer

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-01-23 10:48:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for computer_project_trigger
-- ----------------------------
DROP TABLE IF EXISTS `computer_project_trigger`;
CREATE TABLE `computer_project_trigger` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `code` varchar(32) NOT NULL DEFAULT '',
  `q` int(1) NOT NULL DEFAULT 0,
  `n_q` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of computer_project_trigger
-- ----------------------------
INSERT INTO `computer_project_trigger` VALUES ('1', '0', '0', '1');
INSERT INTO `computer_project_trigger` VALUES ('2', '1', '0', '1');

-- ----------------------------
-- Table structure for stop
-- ----------------------------
DROP TABLE IF EXISTS `stop`;
CREATE TABLE `stop` (
  `id` varchar(32) NOT NULL,
  `code` varchar(32) DEFAULT NULL,
  `stop` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stop
-- ----------------------------
INSERT INTO `stop` VALUES ('1', '1', '0');

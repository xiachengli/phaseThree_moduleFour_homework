/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 23/07/2020 18:52:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验证码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES (-4761121918190597489, '1324952217@qq.com', '488399', '2020-07-22 23:54:04', '2020-07-30 10:04:04');
INSERT INTO `code` VALUES (1, '1324952217@qq.com', '123456', '2020-07-22 22:27:39', '2020-07-22 22:37:43');
INSERT INTO `code` VALUES (2, '13@qq.com', '12344', '2020-07-22 22:45:27', '2020-07-22 22:45:37');
INSERT INTO `code` VALUES (3, '1324952217@qq.com', '319793', '2020-07-22 23:00:41', '2020-07-22 23:10:41');
INSERT INTO `code` VALUES (4, '1324952217@qq.com', '257543', '2020-07-22 23:31:47', '2020-07-22 23:41:47');
INSERT INTO `code` VALUES (5, '1324952217@qq.com', '909544', '2020-07-22 23:34:58', '2020-07-22 23:44:58');
INSERT INTO `code` VALUES (6, '1324952217@qq.com', '538795', '2020-07-22 23:35:00', '2020-07-22 23:45:00');
INSERT INTO `code` VALUES (7, '1324952217@qq.com', '929191', '2020-07-22 23:36:56', '2020-07-22 23:46:56');

SET FOREIGN_KEY_CHECKS = 1;

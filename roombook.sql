/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : roombook

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-08-15 08:59:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_record`
-- ----------------------------
DROP TABLE IF EXISTS `book_record`;
CREATE TABLE `book_record` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `title` varchar(100) NOT NULL COMMENT '主题',
  `room_no` varchar(40) NOT NULL COMMENT '会议室编号',
  `book_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '预约日期',
  `time_quantum` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '时间段',
  `use_remark` text NOT NULL COMMENT '使用说明',
  `audit_type` varchar(20) DEFAULT NULL,
  `book_user` varchar(40) NOT NULL,
  `is_open` varchar(20) DEFAULT NULL,
  `main_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of book_record
-- ----------------------------
INSERT INTO `book_record` VALUES ('4BE764A21A9C4261B2A0502504354D54', 'C语言', '2', '2019-08-07 15:37:54', '2019-08-07 19:01:59', '111', '2', '3ffcdfb2ab7e4721ab19acf301f4b90e', '1', '1111');
INSERT INTO `book_record` VALUES ('6E93E66EED124A65A0CCEC90FAE7B65A', 'PHP', '3', '2019-08-09 08:41:53', '2019-08-09 10:41:55', '测试用', '2', '3ffcdfb2ab7e4721ab19acf301f4b90e', '1', '何老师');
INSERT INTO `book_record` VALUES ('F8528CE65BF04D2CB501CFF8A25AFB96', 'java课', '1', '2019-08-07 15:00:46', '2019-08-07 19:44:42', '张老师', '2', '35642281E87B429A8FF4A4CD9D271581', '1', '张老师');

-- ----------------------------
-- Table structure for `db_student`
-- ----------------------------
DROP TABLE IF EXISTS `db_student`;
CREATE TABLE `db_student` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `student_no` varchar(30) NOT NULL COMMENT '学号',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `sex` varchar(20) NOT NULL COMMENT '性别',
  `college_room` varchar(100) NOT NULL COMMENT '学院',
  `major` varchar(40) NOT NULL COMMENT '专业',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(20) NOT NULL COMMENT '联系电话',
  `content` text COMMENT '简介',
  `cardnumber` varchar(50) DEFAULT NULL COMMENT '磁卡卡号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of db_student
-- ----------------------------
INSERT INTO `db_student` VALUES ('14EE2A42404248DEBFEC8924F54440B8', '110', '张三', '男', '信息学院', '计算机技术', '132@qq.com', '123123123123', '21212121', '0003885365');
INSERT INTO `db_student` VALUES ('187D8E151DA64E6CAD93193910728877', '120', '李四', '女', '信息学院', '计算机技术', '1@qq.com', '15555555555', '1111', '0003894520');
INSERT INTO `db_student` VALUES ('DFF447F74D73405C940800740AE32CA6', '130', '啊啊啊', '男', '信息学院', '计算机技术', '232@qq.com', '16666666666', '111', '0');
INSERT INTO `db_student` VALUES ('E0A5A0C9855E4ADE9DC2CA0728A8FB60', '1821310082', '李鸿熙', '男', '信息学院', '计算机技术与物联网工程', '1452006165@qq.com', '13011110095', '我是一个学生', '0003816832');

-- ----------------------------
-- Table structure for `db_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `db_teacher`;
CREATE TABLE `db_teacher` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `teacher_no` varchar(40) NOT NULL COMMENT '教师编号',
  `teacher_name` varchar(40) NOT NULL COMMENT '教师姓名',
  `tea_title` varchar(40) NOT NULL COMMENT '教师职称',
  `sex` varchar(20) NOT NULL COMMENT '性别',
  `tel` varchar(20) NOT NULL COMMENT '联系电话',
  `content` text COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of db_teacher
-- ----------------------------
INSERT INTO `db_teacher` VALUES ('35642281E87B429A8FF4A4CD9D271581', 'T111', '邓老师', '高级教授', '男', '15678787979', '测试');

-- ----------------------------
-- Table structure for `db_user`
-- ----------------------------
DROP TABLE IF EXISTS `db_user`;
CREATE TABLE `db_user` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(20) DEFAULT NULL COMMENT '性别',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系方式',
  `role` varchar(255) DEFAULT NULL,
  `content` text COMMENT '自我简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of db_user
-- ----------------------------
INSERT INTO `db_user` VALUES ('14EE2A42404248DEBFEC8924F54440B8', '110', '123456', '张三', '男', '123123123123', '3', '21212121');
INSERT INTO `db_user` VALUES ('187D8E151DA64E6CAD93193910728877', '120', '123456', '李四', '女', '15555555555', '3', '1111');
INSERT INTO `db_user` VALUES ('35642281E87B429A8FF4A4CD9D271581', 'T111', '123456', '邓老师', '男', '15678787979', '2', '我是一个老师');
INSERT INTO `db_user` VALUES ('3ffcdfb2ab7e4721ab19acf301f4b90e', 'admin', '123456', '系统管理员', '男', '110', '1', '我是一个管理员');
INSERT INTO `db_user` VALUES ('DFF447F74D73405C940800740AE32CA6', '130', '123456', '啊啊啊', '男', '16666666666', '3', '111');
INSERT INTO `db_user` VALUES ('E0A5A0C9855E4ADE9DC2CA0728A8FB60', '1821310082', '123456', '李木子', '男', '13011110095', '3', '我是一个学生');

-- ----------------------------
-- Table structure for `room_sing_in`
-- ----------------------------
DROP TABLE IF EXISTS `room_sing_in`;
CREATE TABLE `room_sing_in` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `student_id` varchar(40) NOT NULL COMMENT '学生编号',
  `sign_time` varchar(30) NOT NULL COMMENT '签到时间',
  `room_book_id` varchar(40) NOT NULL COMMENT '会议ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of room_sing_in
-- ----------------------------
INSERT INTO `room_sing_in` VALUES ('2C7A6C735F7845558A1A51283217F8D1', 'E0A5A0C9855E4ADE9DC2CA0728A8FB60', '2019-08-09 08:51:55', '6E93E66EED124A65A0CCEC90FAE7B65A');
INSERT INTO `room_sing_in` VALUES ('30F725ED3654422AB2EDF694998B18E7', '187D8E151DA64E6CAD93193910728877', '2019-08-09 08:51:57', '6E93E66EED124A65A0CCEC90FAE7B65A');
INSERT INTO `room_sing_in` VALUES ('5C23D2AB206C42818480A25380BAD2D3', '14EE2A42404248DEBFEC8924F54440B8', '2019-08-09 08:51:59', '6E93E66EED124A65A0CCEC90FAE7B65A');

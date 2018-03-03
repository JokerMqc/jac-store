/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : yph

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-01-20 16:49:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dict_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_res`;
CREATE TABLE `sys_dict_res` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `type_id` bigint(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `flag` tinyint(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dict_res
-- ----------------------------
INSERT INTO `sys_dict_res` VALUES ('1', '1', '注册邮件', 'REGIST_RES', '0', '2018-01-18 16:07:25');
INSERT INTO `sys_dict_res` VALUES ('2', '1', '修改密码邮件', 'MODIFY_RES', '0', '2018-01-18 16:09:33');
INSERT INTO `sys_dict_res` VALUES ('3', '1', 'Exception通知邮件', 'EXCEPTION_RES', '0', '2018-01-18 16:09:36');
INSERT INTO `sys_dict_res` VALUES ('4', '1', '活动邮件', 'ACTIVE_RES', '0', '2018-01-18 16:09:41');
INSERT INTO `sys_dict_res` VALUES ('5', '1', '提醒邮件', 'NOTI_RES', '0', '2018-01-18 16:09:38');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `flag` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', 'MAIL_TYPE', '邮件类型', '0', '2018-01-18 16:06:24');

-- ----------------------------
-- Table structure for sys_email
-- ----------------------------
DROP TABLE IF EXISTS `sys_email`;
CREATE TABLE `sys_email` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_email` varchar(255) DEFAULT NULL,
  `from_name` varchar(255) DEFAULT NULL,
  `to_emails` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `context` varchar(255) DEFAULT NULL,
  `send_status` tinyint(2) DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  `flag` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_email
-- ----------------------------
INSERT INTO `sys_email` VALUES ('1', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('2', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('3', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('4', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('5', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('6', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('7', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('8', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('9', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('10', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('11', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('12', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('13', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('14', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('15', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('16', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '1', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('17', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '0', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('18', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('19', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('20', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('21', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('22', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('23', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('24', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('25', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('26', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('27', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('28', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('29', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('30', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('31', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('32', 'jon8957@163.com', '黄湛', '2016271115@qq.com', '这是邮件主题 --------->', 'hello  这是测试', '1', '0', '0', '2018-01-18 10:14:30');
INSERT INTO `sys_email` VALUES ('33', 'jon8957@163.com', '云品汇公司', '2016271115@qq.com', '这是邮件测试', '这是一份测试的邮件发送。。。。。。。。。', '1', '0', '0', '2018-01-19 10:37:53');
INSERT INTO `sys_email` VALUES ('34', 'jon8957@163.com', '云品汇公司', '2016271115@qq.com', '这是邮件测试', '这是一份测试的邮件发送。。。。。。。。。', '1', '0', '0', '2018-01-19 10:39:13');
INSERT INTO `sys_email` VALUES ('35', 'jon8957@163.com', '云品汇公司', '', '这是邮件测试', '这是一份测试的邮件发送。。。。。。。。。', '1', '0', '0', '2018-01-19 10:50:34');
INSERT INTO `sys_email` VALUES ('36', 'jon8957@163.com', '云品汇公司', '849538026@qq.com', '这是邮件测试', '这是一份测试的邮件发送。。。。。。。。。', '1', '0', '0', '2018-01-19 12:01:20');
INSERT INTO `sys_email` VALUES ('37', 'jon8957@163.com', '云品汇公司', '2016271115@qq.com', '这是邮件测试', '这是一份测试的邮件发送。。。。。。。。。', '1', '0', '0', '2018-01-19 13:54:58');
INSERT INTO `sys_email` VALUES ('38', 'jon8957@163.com', '云品汇公司', '2016271115@qq.com', '这是邮件测试', '这是一份测试的邮件发送。。。。。。。。。', '1', '0', '0', '2018-01-19 13:56:50');
INSERT INTO `sys_email` VALUES ('39', 'jon8957@163.com', ' 系统邮件 ', '2016271115@qq.com', '  [系统异常]  通知邮件 ', 'SysExceptionLog(id=null, calzzName=com.yph.service.mail.impl.SysMailTemplateServiceImpl, methodName=findSysMailTemplateByPage, exceptionMsg= 分页出错! , flag=0, createTime=Fri Jan 19 17:01:33 CST 2018)', '1', '0', '0', '2018-01-19 17:01:44');
INSERT INTO `sys_email` VALUES ('40', 'jon8957@163.com', ' 系统邮件 ', '2016271115@qq.com', '  [系统异常]  通知邮件 ', 'SysExceptionLog(id=null, calzzName=com.yph.controller.email.SysMailTemplateController, methodName=findSysMailTemplateByPage, exceptionMsg= 分页出错! , flag=0, createTime=Fri Jan 19 17:20:53 CST 2018)', '1', '0', '0', '2018-01-19 17:20:54');
INSERT INTO `sys_email` VALUES ('41', 'jon8957@163.com', ' 系统邮件 ', '2016271115@qq.com', '  [系统异常]  通知邮件 ', ' 邮件通知！ 以下代码产生异常，请安排时间进行测试与修复 !   以下是详情信息： ', '1', '0', '0', '2018-01-20 09:42:05');
INSERT INTO `sys_email` VALUES ('42', 'jon8957@163.com', ' 系统邮件 ', '2016271115@qq.com', '  [系统异常]  通知邮件 ', ' 邮件通知！ 以下代码产生异常，请安排时间进行测试与修复 !   以下是详情信息：异常类 : com.yph.controller.email.SysMailTemplateController 异常方法 : findSysMailTemplateByPage 异常原因 : Hello to you ！', '1', '0', '0', '2018-01-20 09:45:02');
INSERT INTO `sys_email` VALUES ('43', '云品汇', null, '2016271115@qq.com', '找回密码', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '0', '2018-01-20 13:55:03');
INSERT INTO `sys_email` VALUES ('44', 'jon8957@163.com', '云品汇', '2016271115@qq.com', '找回密码', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '0', '2018-01-20 13:56:14');
INSERT INTO `sys_email` VALUES ('45', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址： {{ href }}', '1', '0', '0', '2018-01-20 14:31:02');
INSERT INTO `sys_email` VALUES ('46', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址： {{ href }}', '1', '0', '0', '2018-01-20 14:38:33');
INSERT INTO `sys_email` VALUES ('47', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址： localhost:8082/admin/toModifyNewPasswordView.do?uid=a054bba3-63be-4398-b406-2ef906719933', '1', '0', '0', '2018-01-20 14:45:23');
INSERT INTO `sys_email` VALUES ('48', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址： localhost:8082/admin/toModifyNewPasswordView.do?uid=0fd2ff90-8150-49eb-aeda-ccda787e56af', '1', '0', '0', '2018-01-20 14:50:54');
INSERT INTO `sys_email` VALUES ('49', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=0c117e56-6e89-41d2-aeae-9878cf29156c\'>点击我</a>', '1', '0', '0', '2018-01-20 14:54:43');
INSERT INTO `sys_email` VALUES ('50', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=d989dfea-ff4e-4309-935d-8857b5ce1681\'>点击我</a>', '1', '0', '0', '2018-01-20 14:56:54');
INSERT INTO `sys_email` VALUES ('51', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：localhost:8082/admin/toModifyNewPasswordView.do?uid=3c98386e-275a-4fe9-9143-1401c7a0c5b6', '1', '0', '0', '2018-01-20 14:59:51');
INSERT INTO `sys_email` VALUES ('52', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=44622d47-730d-4ab9-b295-258a3c969f72\'>localhost:8082/admin/toModifyNewPasswordView.do?uid=44622d47-730d-4ab9-b295-258a3c969f72</a>', '1', '0', '0', '2018-01-20 15:03:35');
INSERT INTO `sys_email` VALUES ('53', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=2f272a95-0822-4010-9507-493ebb92fe07\'>localhost:8082/admin/toModifyNewPasswordView.do?uid=2f272a95-0822-4010-9507-493ebb92fe07</a>', '1', '0', '0', '2018-01-20 15:04:14');
INSERT INTO `sys_email` VALUES ('54', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=e956404e-bd44-4530-93a9-63c883aa9d0a\'>localhost:8082/admin/toModifyNewPasswordView.do?uid=e956404e-bd44-4530-93a9-63c883aa9d0a</a>', '1', '0', '0', '2018-01-20 15:06:56');
INSERT INTO `sys_email` VALUES ('55', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=525dc251-3289-45ea-b337-bde0f8600f13\'>localhost:8082/admin/toModifyNewPasswordView.do?uid=525dc251-3289-45ea-b337-bde0f8600f13</a>', '1', '0', '0', '2018-01-20 15:09:54');
INSERT INTO `sys_email` VALUES ('56', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=7762be68-d8e5-46fb-b30d-c0929dd53732\'>localhost:8082/admin/toModifyNewPasswordView.do?uid=7762be68-d8e5-46fb-b30d-c0929dd53732</a>', '1', '0', '0', '2018-01-20 15:17:38');
INSERT INTO `sys_email` VALUES ('57', 'jon8957@163.com', '云品汇   ', '2016271115@qq.com', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'localhost:8082/admin/toModifyNewPasswordView.do?uid=4568e73b-9b10-4451-aa09-ad3eb1867de4\'>localhost:8082/admin/toModifyNewPasswordView.do?uid=4568e73b-9b10-4451-aa09-ad3eb1867de4</a>', null, null, '1', '2018-01-20 15:51:30');

-- ----------------------------
-- Table structure for sys_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `calzz_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `exception_msg` varchar(255) DEFAULT NULL,
  `flag` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_exception_log
-- ----------------------------
INSERT INTO `sys_exception_log` VALUES ('1', 'com.yph.service.mail.impl.SysMailTemplateServiceImpl', 'findSysMailTemplateByPage', ' 分页出错! ', '0', '2018-01-19 17:01:33');
INSERT INTO `sys_exception_log` VALUES ('2', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', ' 分页出错! ', '0', '2018-01-19 17:20:53');
INSERT INTO `sys_exception_log` VALUES ('3', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', 'Hello to you ！', '0', '2018-01-20 09:30:23');
INSERT INTO `sys_exception_log` VALUES ('4', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', 'Hello to you ！', '0', '2018-01-20 09:30:53');
INSERT INTO `sys_exception_log` VALUES ('5', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', 'Hello to you ！', '0', '2018-01-20 09:32:10');
INSERT INTO `sys_exception_log` VALUES ('6', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', 'Hello to you ！', '0', '2018-01-20 09:34:23');
INSERT INTO `sys_exception_log` VALUES ('7', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', 'Hello to you ！', '0', '2018-01-20 09:42:05');
INSERT INTO `sys_exception_log` VALUES ('8', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', 'Hello to you ！', '0', '2018-01-20 09:43:38');
INSERT INTO `sys_exception_log` VALUES ('9', 'com.yph.controller.email.SysMailTemplateController', 'findSysMailTemplateByPage', 'Hello to you ！', '0', '2018-01-20 09:44:49');
INSERT INTO `sys_exception_log` VALUES ('10', 'com.yph.service.mail.impl.SysMailTemplateServiceImpl', 'updateDefaultTemplageById', 'java.lang.NullPointerException', '0', '2018-01-20 10:15:32');
INSERT INTO `sys_exception_log` VALUES ('11', 'org.apache.ibatis.binding.MapperMethod', 'execute', 'Mapper method \'com.yph.mapper.mail.SysMailTemplateMapper.selectDefaultTemplageById attempted to return null from a method with a primitive return type (int).', '0', '2018-01-20 10:17:47');
INSERT INTO `sys_exception_log` VALUES ('12', 'com.yph.service.mail.impl.SysMailServiceImpl', 'sendForgetPassWordMail', 'java.lang.NullPointerException', '0', '2018-01-20 13:52:59');
INSERT INTO `sys_exception_log` VALUES ('13', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: javax.mail.internet.AddressException: Local address contains control or whitespace in string ``云品汇\'\'', '0', '2018-01-20 13:55:07');
INSERT INTO `sys_exception_log` VALUES ('14', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp7,C8CowADncqaV5WJasUAJDg--.26719S2 1516430742,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp7&time=1516430742\n', '0', '2018-01-20 14:45:24');
INSERT INTO `sys_exception_log` VALUES ('15', 'com.yph.utils.ShiroUtils', 'getKaptcha', 'java.lang.NullPointerException', '0', '2018-01-20 14:50:23');
INSERT INTO `sys_exception_log` VALUES ('16', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp7,C8CowACn1f7I52JaMCAKDg--.24644S2 1516431304,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp7&time=1516431304\n', '0', '2018-01-20 14:54:47');
INSERT INTO `sys_exception_log` VALUES ('17', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp7,C8CowAB3PP7Z6WJarNMKDg--.26454S2 1516431833,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp7&time=1516431833\n', '0', '2018-01-20 15:03:35');
INSERT INTO `sys_exception_log` VALUES ('18', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp7,C8CowADHfqIA6mJaZuEKDg--.26710S2 1516431872,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp7&time=1516431872\n', '0', '2018-01-20 15:04:14');
INSERT INTO `sys_exception_log` VALUES ('19', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp7,C8CowAC3OaWj6mJaeiELDg--.27087S2 1516432035,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp7&time=1516432035\n', '0', '2018-01-20 15:06:57');
INSERT INTO `sys_exception_log` VALUES ('20', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp12,EMCowABnQQBZ62Ja64LaDQ--.48331S2 1516432218,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp12&time=1516432218\n', '0', '2018-01-20 15:10:00');
INSERT INTO `sys_exception_log` VALUES ('21', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp12,EMCowAA3Ne8k7WJadi_bDQ--.21397S2 1516432677,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp12&time=1516432677\n', '0', '2018-01-20 15:17:39');
INSERT INTO `sys_exception_log` VALUES ('22', 'org.springframework.mail.javamail.JavaMailSenderImpl', 'doSend', 'Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp12,EMCowADH2NwV9WJany7eDQ--.21495S2 1516434709,please see http://mail.163.com/help/help_spam_16.htm?ip=119.131.153.162&hostid=smtp12&time=1516434709\n', '0', '2018-01-20 15:51:32');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_mail_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_mail_template`;
CREATE TABLE `sys_mail_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` bigint(3) DEFAULT NULL,
  `flag` tinyint(2) DEFAULT NULL,
  `status_flag` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_mail_template
-- ----------------------------
INSERT INTO `sys_mail_template` VALUES ('1', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '0', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('2', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('3', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('4', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('5', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('6', '系统Exception异常', '异常邮件通知', ' 邮件通知！ 以下代码产生异常，请安排时间进行测试与修复 !   以下是详情信息：{{ exceptionMsg }}', '3', '0', '0', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('7', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '3', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('8', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '3', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('9', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '3', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('10', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '2', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('11', '忘记密码，找回密码', '找回密码', '你正在操作修改你的帐号密码，请点击以下的地址跳转到修改密码页面。如果不是你本人操作，则可以忽略该邮件。  地址：<a href=\'{{href}}\'>{{href}}</a>', '2', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('12', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '2', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('13', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '2', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('14', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '2', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('15', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '4', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('16', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '4', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('17', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '4', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('18', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '4', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('19', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '4', '0', '0', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('20', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '5', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('21', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '5', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('22', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '5', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('23', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '5', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('24', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '5', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('25', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '5', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('26', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('27', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '1', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('28', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '2', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('29', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '2', '0', '0', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('30', '注册邮件通知模版', '注册成功 !', '注册成功 ! 请点击以下链接进行激活帐号 !', '2', '0', '1', '2018-01-18 11:38:41');
INSERT INTO `sys_mail_template` VALUES ('31', null, null, null, '2', null, '1', null);
INSERT INTO `sys_mail_template` VALUES ('32', null, null, null, '2', null, '1', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：头部导航   1：左侧导航   2：子菜单',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `flag` tinyint(4) DEFAULT NULL COMMENT '0 : 未删除 1:删除',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_id_name_parentId` (`id`,`parent_id`,`name`),
  KEY `index_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统设置', null, null, '0', '1', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('2', '1', '菜单管理', '', '', '1', '1', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('3', '1', '用户管理', '', '', '1', '2', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('4', '1', '角色管理', '', '', '1', '3', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('5', '1', '消息管理', '', '', '1', '4', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('6', '1', '日志管理', '', '', '1', '5', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('7', '2', '菜单列表', 'sys/view/eidtSysMenuView.do', '', '2', '1', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('8', '8', '用户列表', '/user/userlist.do', '', '2', '1', '0', '2018-01-03 14:18:28');
INSERT INTO `sys_menu` VALUES ('9', '4', '角色列表', 'sys/view/toSysRoleListView.do', null, '2', '1', '0', '2018-01-06 09:27:25');
INSERT INTO `sys_menu` VALUES ('10', '4', '角色菜单', 'sys/view/toEditRoleMenuView.do', null, '2', '2', '0', '2018-01-08 14:32:25');
INSERT INTO `sys_menu` VALUES ('11', '0', '订单系统', '', null, '0', '2', '0', '2018-01-08 15:14:54');
INSERT INTO `sys_menu` VALUES ('12', '3', '用户列表', 'sys/view/toSysUserView.do', null, '2', '1', '0', '2018-01-10 11:57:15');
INSERT INTO `sys_menu` VALUES ('13', '1', '数据库管理', '', null, '1', '6', '0', '2018-01-11 11:26:54');
INSERT INTO `sys_menu` VALUES ('14', '13', '数据库管控', 'druid/index.do', null, '2', '1', '0', '2018-01-11 11:30:43');
INSERT INTO `sys_menu` VALUES ('15', '5', '邮件模版', 'sys/view/toSysEmailView.do', null, '2', '1', '0', '2018-01-11 11:36:19');
INSERT INTO `sys_menu` VALUES ('16', '5', '短信配置', '', null, '2', '2', '0', '2018-01-11 11:36:32');
INSERT INTO `sys_menu` VALUES ('17', '5', '站内消息配置', '', null, '2', '3', '0', '2018-01-11 11:36:58');
INSERT INTO `sys_menu` VALUES ('18', '6', '系统日志列表', '', null, '2', '1', '0', '2018-01-11 11:37:22');
INSERT INTO `sys_menu` VALUES ('19', '6', '错误日志', '', null, '2', '2', '0', '2018-01-11 11:38:01');
INSERT INTO `sys_menu` VALUES ('20', '11', '订单管理', '', null, '1', '1', '0', '2018-01-11 14:36:04');
INSERT INTO `sys_menu` VALUES ('21', '20', '订单列表', '', null, '2', '1', '0', '2018-01-11 14:37:00');
INSERT INTO `sys_menu` VALUES ('22', '0', '财务系统', '', null, '0', '3', '0', '2018-01-11 16:07:11');
INSERT INTO `sys_menu` VALUES ('23', '0', '营销系统', '', null, '0', '4', '0', '2018-01-11 16:07:33');
INSERT INTO `sys_menu` VALUES ('24', '0', '授权系统', '', null, '0', '5', '0', '2018-01-11 16:07:47');
INSERT INTO `sys_menu` VALUES ('25', '0', '防伪系统', '', null, '0', '6', '0', '2018-01-11 16:08:32');
INSERT INTO `sys_menu` VALUES ('26', '22', '财务管理', '', null, '1', '6', '0', '2018-01-11 16:10:47');
INSERT INTO `sys_menu` VALUES ('27', '24', '授权管理', '', null, '1', '1', '0', '2018-01-11 16:11:02');
INSERT INTO `sys_menu` VALUES ('28', '25', '防伪码管理', '', null, '1', '1', '0', '2018-01-11 16:11:19');
INSERT INTO `sys_menu` VALUES ('29', '23', '营销管理', '', null, '1', '1', '0', '2018-01-11 16:11:34');
INSERT INTO `sys_menu` VALUES ('30', '5', '消息设置', '', null, '2', '3', '0', '2018-01-12 11:10:31');
INSERT INTO `sys_menu` VALUES ('32', '27', '授权列表', '', null, '2', '1', '0', '2018-01-12 16:49:40');
INSERT INTO `sys_menu` VALUES ('33', '5', '邮件列表', 'sys/view/toSysEmailListView.do', null, '2', '5', '0', '2018-01-18 10:11:37');
INSERT INTO `sys_menu` VALUES ('34', '1', '字典管理', '', null, '1', '7', '0', '2018-01-18 15:41:35');
INSERT INTO `sys_menu` VALUES ('35', '34', '字典介绍', 'sys/view/toDictInfoView.do', null, '2', '1', '0', '2018-01-18 15:45:32');
INSERT INTO `sys_menu` VALUES ('36', '34', '字典类型', 'sys/view/toDictTypeListView.do', null, '2', '2', '0', '2018-01-18 15:45:43');
INSERT INTO `sys_menu` VALUES ('37', '34', '字典资源', 'sys/view/toDictResListView.do', null, '2', '3', '0', '2018-01-18 15:45:55');
INSERT INTO `sys_menu` VALUES ('38', '5', '邮件控制台', 'sys/view/toSysEmailContrlView.do', null, '2', '6', '0', '2018-01-19 09:42:55');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `flag` tinyint(2) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '测试用', '0', '1', '2018-01-03 14:25:18');
INSERT INTO `sys_role` VALUES ('2', '系统管理员', '测试用', '0', '1', '2018-01-03 14:25:18');
INSERT INTO `sys_role` VALUES ('3', 'vip会员', '测试用', '0', '1', '2018-01-03 14:25:18');
INSERT INTO `sys_role` VALUES ('4', '会员', '测试用', '0', '1', '2018-01-03 14:25:18');
INSERT INTO `sys_role` VALUES ('5', '运维工程师', '这是测试用的', '0', '1', '2018-01-06 14:17:05');
INSERT INTO `sys_role` VALUES ('6', '超级管理员', '测试用', '0', '1', '2018-01-06 14:26:52');
INSERT INTO `sys_role` VALUES ('7', '内部员工使用', '这是内部员工使用的权限', '0', '1', '2018-01-06 14:44:55');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=415 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1', '0');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2', '0');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3', '0');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4', '0');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5', '0');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6', '0');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7', '0');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8', '0');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '9', '0');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '10', '0');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '11', '0');
INSERT INTO `sys_role_menu` VALUES ('379', '2', '1', '0');
INSERT INTO `sys_role_menu` VALUES ('380', '2', '2', '0');
INSERT INTO `sys_role_menu` VALUES ('381', '2', '7', '0');
INSERT INTO `sys_role_menu` VALUES ('382', '2', '3', '0');
INSERT INTO `sys_role_menu` VALUES ('383', '2', '12', '0');
INSERT INTO `sys_role_menu` VALUES ('384', '2', '4', '0');
INSERT INTO `sys_role_menu` VALUES ('385', '2', '9', '0');
INSERT INTO `sys_role_menu` VALUES ('386', '2', '10', '0');
INSERT INTO `sys_role_menu` VALUES ('387', '2', '5', '0');
INSERT INTO `sys_role_menu` VALUES ('388', '2', '15', '0');
INSERT INTO `sys_role_menu` VALUES ('389', '2', '16', '0');
INSERT INTO `sys_role_menu` VALUES ('390', '2', '17', '0');
INSERT INTO `sys_role_menu` VALUES ('391', '2', '30', '0');
INSERT INTO `sys_role_menu` VALUES ('392', '2', '33', '0');
INSERT INTO `sys_role_menu` VALUES ('393', '2', '38', '0');
INSERT INTO `sys_role_menu` VALUES ('394', '2', '6', '0');
INSERT INTO `sys_role_menu` VALUES ('395', '2', '18', '0');
INSERT INTO `sys_role_menu` VALUES ('396', '2', '19', '0');
INSERT INTO `sys_role_menu` VALUES ('397', '2', '13', '0');
INSERT INTO `sys_role_menu` VALUES ('398', '2', '14', '0');
INSERT INTO `sys_role_menu` VALUES ('399', '2', '34', '0');
INSERT INTO `sys_role_menu` VALUES ('400', '2', '35', '0');
INSERT INTO `sys_role_menu` VALUES ('401', '2', '36', '0');
INSERT INTO `sys_role_menu` VALUES ('402', '2', '37', '0');
INSERT INTO `sys_role_menu` VALUES ('403', '2', '11', '0');
INSERT INTO `sys_role_menu` VALUES ('404', '2', '20', '0');
INSERT INTO `sys_role_menu` VALUES ('405', '2', '21', '0');
INSERT INTO `sys_role_menu` VALUES ('406', '2', '22', '0');
INSERT INTO `sys_role_menu` VALUES ('407', '2', '26', '0');
INSERT INTO `sys_role_menu` VALUES ('408', '2', '23', '0');
INSERT INTO `sys_role_menu` VALUES ('409', '2', '29', '0');
INSERT INTO `sys_role_menu` VALUES ('410', '2', '24', '0');
INSERT INTO `sys_role_menu` VALUES ('411', '2', '27', '0');
INSERT INTO `sys_role_menu` VALUES ('412', '2', '32', '0');
INSERT INTO `sys_role_menu` VALUES ('413', '2', '25', '0');
INSERT INTO `sys_role_menu` VALUES ('414', '2', '28', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：zheng   1：正常',
  `flag` tinyint(2) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '231232312@qq.com', '123123123', '0', '0', '1', '2018-01-03 09:27:52');
INSERT INTO `sys_user` VALUES ('2', 'root', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '231232312@qq.com', '123123123', '0', '0', '1', '2018-01-03 09:27:52');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : hui_ah

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2014-06-16 10:51:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `admin_name` varchar(120) NOT NULL DEFAULT '' COMMENT '管理员名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(120) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '电话',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `delable` int(1) DEFAULT '0' COMMENT '能否删除：0-能；1-不能；',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-正常；1-锁定；2-删除；',
  `auto_unlock` int(10) DEFAULT 0,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_name_UNIQUE` (`admin_name`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '96e79218965eb72c92a549dd5a330112', null, null, 'admin@hui.com', '1', '0', 0);
INSERT INTO `admin` VALUES ('2', '管理员', 'f379eaf3c831b04de153469d1bec345e', null, null, null, '0', '0', 0);

-- ----------------------------
-- Table structure for adminrole
-- ----------------------------
DROP TABLE IF EXISTS `adminrole`;
CREATE TABLE `adminrole` (
  `ar_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `admin_id` int(10) unsigned NOT NULL COMMENT '管理员id',
  `role_id` int(10) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`ar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员、权限  中间表';

-- ----------------------------
-- Records of adminrole
-- ----------------------------
INSERT INTO `adminrole` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `a_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `question_id` int(10) DEFAULT '0' COMMENT '问题id',
  `answer_desc` varchar(255) DEFAULT NULL COMMENT '回答描述',
  `praise_num` int(5) DEFAULT '0' COMMENT '点赞数',
  `client_style` varchar(64) DEFAULT NULL COMMENT '客户端型号',
  `create_time` int(10) DEFAULT '0' COMMENT '创建时间',
  `has_img` int(1) DEFAULT '0' COMMENT '有无图片 0-无 1-有',
  `is_favorate` int(1) DEFAULT '0' COMMENT '是否为最佳答案：0-否 1-是',
  `status` int(1) DEFAULT '1' COMMENT '状态 0-废弃 1-正常',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='回答';

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '83505213', '1', '回答描述1', '0', 'iphone5s', '1399961509', '0', '1', '1');
INSERT INTO `answer` VALUES ('2', '83505213', '2', '回答描述2', '0', 'iphone5C', '1400001111', '0', '1', '1');
INSERT INTO `answer` VALUES ('3', '83505213', '2', '回答描述3', '0', 'iphone3GS', '1400004444', '0', '0', '1');
INSERT INTO `answer` VALUES ('4', '83505213', '1', '这是个答案', '1', '大神F1', '1400040000', '0', '0', '1');
INSERT INTO `answer` VALUES ('5', '83505213', '14', '这是我的答案，希望你能满意', '0', 'PC', '1400834650', '0', '0', '1');
INSERT INTO `answer` VALUES ('6', '83505213', '12', ' 这是我的回答！', '1', 'PC', '1400835556', '0', '0', '1');
INSERT INTO `answer` VALUES ('7', '83505213', '13', '再来一个回答！', '3', 'PC', '1400836306', '0', '1', '1');
INSERT INTO `answer` VALUES ('8', '32555577', '19', '这是我的回答', '0', 'PC', '1401070089', '0', '1', '1');
INSERT INTO `answer` VALUES ('9', '83505213', '1', 'dddd', '0', 'PC', '1401104345', '0', '0', '1');
INSERT INTO `answer` VALUES ('10', '83505213', '36', '我的回答，有图片哦', '0', 'PC', '1401201100', '0', '0', '1');
INSERT INTO `answer` VALUES ('11', '83505213', '36', '这回真有图片了', '0', 'PC', '1401201461', '1', '1', '1');
INSERT INTO `answer` VALUES ('12', '83505213', '36', '再来回答一次', '0', 'PC', '1401201902', '0', '0', '1');
INSERT INTO `answer` VALUES ('13', '83505213', '36', '再回答一次', '0', 'PC', '1401202033', '1', '0', '1');
INSERT INTO `answer` VALUES ('14', '83505213', '37', '回答也是有图片的哦', '0', 'PC', '1401239055', '1', '0', '1');
INSERT INTO `answer` VALUES ('15', '83505213', '39', ' 我的回答', '0', 'PC', '1401336426', '1', '0', '1');
INSERT INTO `answer` VALUES ('16', '83505213', '40', '', '0', 'PC', '1401357367', '1', '0', '1');
INSERT INTO `answer` VALUES ('17', '83505213', '39', '', '0', 'PC', '1401359509', '1', '0', '1');
INSERT INTO `answer` VALUES ('18', '83505213', '39', '', '0', 'PC', '1401359697', '1', '0', '1');
INSERT INTO `answer` VALUES ('19', '32555577', '42', '这是我的回答', '0', 'PC', '1401416685', '0', '0', '1');
INSERT INTO `answer` VALUES ('20', '32555577', '42', '', '0', 'PC', '1401417263', '0', '0', '1');
INSERT INTO `answer` VALUES ('21', '83505213', '49', '', '0', 'PC', '1401781256', '1', '1', '1');
INSERT INTO `answer` VALUES ('22', '83505213', '50', '我要回答', '0', 'PC', '1401791788', '1', '0', '1');
INSERT INTO `answer` VALUES ('23', '83505213', '50', '', '0', 'PC', '1401791827', '1', '0', '1');
INSERT INTO `answer` VALUES ('24', '83505213', '50', '', '0', 'PC', '1401791854', '1', '0', '1');
INSERT INTO `answer` VALUES ('25', '03662147', '51', '陈小春', '1', 'PC', '1401806042', '1', '1', '1');
INSERT INTO `answer` VALUES ('26', '32555577', '50', 'ttt', '2', 'PC', '1401884743', '0', '0', '1');
INSERT INTO `answer` VALUES ('27', '32555577', '52', 'taijia', '0', 'PC', '1401893565', '0', '0', '1');
INSERT INTO `answer` VALUES ('28', '05564291', '55', 'ddd', '15', 'PC', '1401973807', '0', '0', '1');
INSERT INTO `answer` VALUES ('29', '62038424', '60', '老师回答', '0', 'PC', '1402849704', '0', '0', '1');

-- ----------------------------
-- Table structure for beanflow
-- ----------------------------
DROP TABLE IF EXISTS `beanflow`;
CREATE TABLE `beanflow` (
  `bf_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` int(10) DEFAULT NULL COMMENT '汇答号',
  `bf_sn` varchar(20) DEFAULT NULL COMMENT '汇豆流水编号',
  `flow_time` int(10) DEFAULT NULL COMMENT '流水时间',
  `bean_num` int(5) DEFAULT NULL COMMENT '数量',
  `in_out` int(1) DEFAULT NULL COMMENT '流入、流出：0-流出，1-流入，2-冻结',
  `status` int(11) DEFAULT NULL COMMENT '状态：0-废弃 1-正常',
  PRIMARY KEY (`bf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='汇豆流水表';

-- ----------------------------
-- Records of beanflow
-- ----------------------------
INSERT INTO `beanflow` VALUES ('1', '83505213', '2014052400001', '1400000000', '500', '0', '1');
INSERT INTO `beanflow` VALUES ('2', '83505213', 'EDA1D759B5', '1401283744', '5', '1', '1');
INSERT INTO `beanflow` VALUES ('3', '32555577', '376AB8D9FA', '1401283744', '5', '1', '1');
INSERT INTO `beanflow` VALUES ('4', '83505213', '9CC84F7119', '1401283845', '5', '1', '1');
INSERT INTO `beanflow` VALUES ('5', '32555577', 'A26ADE0333', '1401336377', '3', '1', '1');
INSERT INTO `beanflow` VALUES ('6', '83505213', '7D45BED42F', '1401412898', '5', '1', '1');
INSERT INTO `beanflow` VALUES ('7', '83505213', '0CEEDB65EC', '1401413570', '4', '1', '1');
INSERT INTO `beanflow` VALUES ('8', '83505213', '6F124A4795', '1401762074', '1', '1', '1');
INSERT INTO `beanflow` VALUES ('9', '83505213', '7044DDFE71', '1402023862', '5', '1', '1');
INSERT INTO `beanflow` VALUES ('10', '83505213', 'AA7D449365', '1402043593', '5', '1', '1');

-- ----------------------------
-- Table structure for bug
-- ----------------------------
DROP TABLE IF EXISTS `bug`;
CREATE TABLE `bug` (
  `bug_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名（选填）',
  `qq` int(20) DEFAULT NULL COMMENT 'QQ（选填）',
  `email` varchar(64) DEFAULT NULL COMMENT 'email(选填)',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系方式（选填）',
  `create_time` int(10) DEFAULT NULL COMMENT '创建时间',
  `client_style` varchar(64) DEFAULT NULL COMMENT '客户端',
  `bug_desc` varchar(1024) DEFAULT NULL COMMENT 'bug详情',
  `status` int(1) DEFAULT NULL COMMENT '状态：0-废弃 1-正常',
  PRIMARY KEY (`bug_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='软件问题';

-- ----------------------------
-- Records of bug
-- ----------------------------
INSERT INTO `bug` VALUES ('1', '83505213', null, null, null, null, '1400300000', '酷派大神', '这个软件貌似有点问题', '1');
INSERT INTO `bug` VALUES ('2', null, '汤', '1034460365', 'tangtaijia@qq.com', '15062294657', '1400746631', 'PC', '这个网站还不错哦', '1');
INSERT INTO `bug` VALUES ('3', null, '太佳', '1034460365', 'tangtaijia@qq.com', '15062294657', '1400746696', 'PC', '这个网站挺好的', '1');
INSERT INTO `bug` VALUES ('4', null, '太佳', '1034460365', 'tangtaijia@qq.com', '15062294657', '1400746701', 'PC', '这个网站挺好的', '1');
INSERT INTO `bug` VALUES ('5', '83505213', 'taijia', '1034460365', 'tangtaijia@qq.com', '15062294657', '1400747049', 'PC', '我的内容', '1');

-- ----------------------------
-- Table structure for cashflow
-- ----------------------------
DROP TABLE IF EXISTS `cashflow`;
CREATE TABLE `cashflow` (
  `cf_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` int(10) DEFAULT NULL COMMENT '汇答号',
  `cf_sn` varchar(20) DEFAULT NULL COMMENT '现金流水编号',
  `flow_time` int(10) DEFAULT NULL COMMENT '发生时间',
  `flow_amount` int(5) DEFAULT NULL COMMENT '金额',
  `flow_source` int(1) DEFAULT NULL COMMENT '发生来源 1-支付宝',
  `in_out` int(1) DEFAULT NULL COMMENT '0-流出，1-流入',
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`cf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='现金记录表';

-- ----------------------------
-- Records of cashflow
-- ----------------------------
INSERT INTO `cashflow` VALUES ('1', '83505213', '2014052400002', '1400001111', '480', '1', '1', '1');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` varchar(8) DEFAULT '',
  `data_id` int(10) DEFAULT '0',
  `type` tinyint(1) DEFAULT '0' COMMENT '收藏种类:0问题1回答',
  `create_time` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='收藏';

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('1', '83505213', '60', '0', '1402854919');

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `fans_id` int(10) NOT NULL,
  `from_no` int(10) DEFAULT NULL COMMENT '关注者汇豆号',
  `to_no` int(10) DEFAULT NULL COMMENT '被关注者汇豆号',
  `fans_time` int(10) DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`fans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注表';

-- ----------------------------
-- Records of fans
-- ----------------------------
INSERT INTO `fans` VALUES ('2', '3668287', '83505213', '1400070429');
INSERT INTO `fans` VALUES ('4', '62038424', '83505213', '1400022222');
INSERT INTO `fans` VALUES ('14', '83505213', '32555575', '1401071770');
INSERT INTO `fans` VALUES ('16', '83505213', '82977595', '1401793449');
INSERT INTO `fans` VALUES ('17', '83505213', '83319476', '1401793452');
INSERT INTO `fans` VALUES ('18', '3662147', '83505213', '1401804838');
INSERT INTO `fans` VALUES ('19', '83505213', '32555577', '1401887578');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `grade_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(30) DEFAULT NULL COMMENT '年级名',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='年级';

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '小学');
INSERT INTO `grade` VALUES ('2', '初一');
INSERT INTO `grade` VALUES ('3', '初二');
INSERT INTO `grade` VALUES ('4', '初三');
INSERT INTO `grade` VALUES ('5', '高一');
INSERT INTO `grade` VALUES ('6', '高二');
INSERT INTO `grade` VALUES ('7', '高三');

-- ----------------------------
-- Table structure for gradesubject
-- ----------------------------
DROP TABLE IF EXISTS `gradesubject`;
CREATE TABLE `gradesubject` (
  `gs_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grade_id` int(10) DEFAULT NULL,
  `subject_id` int(10) DEFAULT NULL COMMENT '科目id',
  PRIMARY KEY (`gs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='年级、科目 中间表';

-- ----------------------------
-- Records of gradesubject
-- ----------------------------
INSERT INTO `gradesubject` VALUES ('1', '1', '1');
INSERT INTO `gradesubject` VALUES ('2', '1', '2');
INSERT INTO `gradesubject` VALUES ('3', '1', '3');
INSERT INTO `gradesubject` VALUES ('4', '2', '1');
INSERT INTO `gradesubject` VALUES ('5', '2', '2');
INSERT INTO `gradesubject` VALUES ('6', '2', '3');
INSERT INTO `gradesubject` VALUES ('7', '2', '4');
INSERT INTO `gradesubject` VALUES ('8', '2', '5');
INSERT INTO `gradesubject` VALUES ('9', '2', '6');
INSERT INTO `gradesubject` VALUES ('10', '2', '7');
INSERT INTO `gradesubject` VALUES ('13', '3', '1');
INSERT INTO `gradesubject` VALUES ('14', '3', '2');
INSERT INTO `gradesubject` VALUES ('15', '3', '3');
INSERT INTO `gradesubject` VALUES ('16', '3', '4');
INSERT INTO `gradesubject` VALUES ('17', '3', '5');
INSERT INTO `gradesubject` VALUES ('18', '3', '6');
INSERT INTO `gradesubject` VALUES ('19', '3', '7');
INSERT INTO `gradesubject` VALUES ('20', '3', '8');
INSERT INTO `gradesubject` VALUES ('22', '4', '1');
INSERT INTO `gradesubject` VALUES ('23', '4', '2');
INSERT INTO `gradesubject` VALUES ('24', '4', '3');
INSERT INTO `gradesubject` VALUES ('25', '4', '4');
INSERT INTO `gradesubject` VALUES ('26', '4', '5');
INSERT INTO `gradesubject` VALUES ('27', '4', '6');
INSERT INTO `gradesubject` VALUES ('28', '4', '7');
INSERT INTO `gradesubject` VALUES ('29', '4', '8');
INSERT INTO `gradesubject` VALUES ('30', '4', '9');
INSERT INTO `gradesubject` VALUES ('31', '5', '1');
INSERT INTO `gradesubject` VALUES ('32', '5', '2');
INSERT INTO `gradesubject` VALUES ('33', '5', '3');
INSERT INTO `gradesubject` VALUES ('34', '5', '4');
INSERT INTO `gradesubject` VALUES ('35', '5', '5');
INSERT INTO `gradesubject` VALUES ('36', '5', '6');
INSERT INTO `gradesubject` VALUES ('37', '5', '7');
INSERT INTO `gradesubject` VALUES ('38', '5', '8');
INSERT INTO `gradesubject` VALUES ('39', '5', '9');
INSERT INTO `gradesubject` VALUES ('40', '6', '1');
INSERT INTO `gradesubject` VALUES ('41', '6', '2');
INSERT INTO `gradesubject` VALUES ('42', '6', '3');
INSERT INTO `gradesubject` VALUES ('43', '6', '4');
INSERT INTO `gradesubject` VALUES ('44', '6', '5');
INSERT INTO `gradesubject` VALUES ('45', '6', '6');
INSERT INTO `gradesubject` VALUES ('46', '6', '7');
INSERT INTO `gradesubject` VALUES ('47', '6', '8');
INSERT INTO `gradesubject` VALUES ('48', '6', '9');
INSERT INTO `gradesubject` VALUES ('49', '7', '1');
INSERT INTO `gradesubject` VALUES ('50', '7', '2');
INSERT INTO `gradesubject` VALUES ('51', '7', '3');
INSERT INTO `gradesubject` VALUES ('52', '7', '4');
INSERT INTO `gradesubject` VALUES ('53', '7', '5');
INSERT INTO `gradesubject` VALUES ('54', '7', '6');
INSERT INTO `gradesubject` VALUES ('55', '7', '7');
INSERT INTO `gradesubject` VALUES ('56', '7', '8');
INSERT INTO `gradesubject` VALUES ('57', '7', '9');

-- ----------------------------
-- Table structure for help
-- ----------------------------
DROP TABLE IF EXISTS `help`;
CREATE TABLE `help` (
  `help_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `help_title` varchar(256) DEFAULT NULL,
  `help_desc` varchar(1024) DEFAULT NULL COMMENT '帮助文字描述',
  PRIMARY KEY (`help_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='帮助';

-- ----------------------------
-- Records of help
-- ----------------------------
INSERT INTO `help` VALUES ('1', '如何进行问题补充？', '对于自己的提问们能够拍照或者输入文字进行补充，问题补充次数最多为3次。目前暂未开放该功能。');
INSERT INTO `help` VALUES ('2', '如何向学霸提问？', '在“我要提问”页面，选择向学霸提问，然后拍照上传你的问题或进行问题描述，然后选择@学霸，选择你求助的学霸好友');
INSERT INTO `help` VALUES ('3', '悬赏有什么作用？', '悬赏是对回答者的奖励。提问时悬赏汇豆，将会提高题目的曝光率和被解答率，也会增大题目被解答的速度和质量。如果对回答者的答案满意，记得一定要采纳哦。');
INSERT INTO `help` VALUES ('4', '如何回答问题？', '在“全部问题”页面，选择自己擅长的年级和学科的问题进行解答。答案被采纳之后，将有机会获得汇豆哦。');
INSERT INTO `help` VALUES ('5', '什么样的答案能被评为最佳答案？', '提问人对回答自己的所有答案可以进行最佳答案选择，如果答案越符合提问者的要求，被评为最佳答案的几率越高。');
INSERT INTO `help` VALUES ('6', '什么是汇豆，如何赚汇豆？', '汇豆是汇答题中用于提问和兑换道具奖品的一种货币，汇豆可以用于提问悬赏，也可进行道具奖品的购买兑换，目前兑换道具奖品功能暂未开放。每天登录汇答题可获得20汇豆，回答问题可获得悬赏的汇豆。');
INSERT INTO `help` VALUES ('7', '审核规定', '我们鼓励同学们在汇答题上进行学习讨论，为了营造一个良好的学习交流环境，一下内容将无法通过审核：<br>①、内容包含暴力、色情等方面的敏感词；<br>②、提问和回答的内容与学习无关；<br>、图片拍摄不清晰或不完整；');
INSERT INTO `help` VALUES ('8', '作弊刷豆的处理和禁言规定', '汇答题鼓励大家积极地进行学习交流，通过以下非公平行为刷豆的同学，我们将酌情进行汇豆和禁言处罚：<br>①、任何形式的大小号自问自答，将小号的汇豆转移到大号；<br>②、进行大量无意义的回答<br>③、大量复制粘贴同一提问下其他者的答案。');

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `msg_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `msginfo_id` int(10) DEFAULT NULL COMMENT '被推送的消息id',
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `msg_type` int(1) DEFAULT NULL COMMENT '消息种类：1-回答我的 2-求助回答 3-回答被采纳 4-系统消息',
  `msg_content` varchar(256) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL,
  `is_read` int(1) DEFAULT '0' COMMENT '是否已读 0-未读  1-已读',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COMMENT='消息';

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('1', '1', '83505213', '1', '回答我的消息内容', '1399963809', '1');
INSERT INTO `msg` VALUES ('2', '1', '83505213', '2', '求助回答的消息内容', '1399963109', '1');
INSERT INTO `msg` VALUES ('3', '1', '83505213', '3', '回答被采纳的消息内容', '1399963209', '1');
INSERT INTO `msg` VALUES ('4', '1', '83505213', '4', '系统消息的消息内容', '1399962809', '1');
INSERT INTO `msg` VALUES ('5', '2', '83505213', '4', '系统消息的消息内容', '1399963709', '1');
INSERT INTO `msg` VALUES ('6', '3', '83505213', '4', '系统消息的消息内容', '1399961809', '1');
INSERT INTO `msg` VALUES ('7', '4', '83505213', '4', '系统消息的消息内容', '1399961509', '1');
INSERT INTO `msg` VALUES ('8', '18', '03668287', '2', 'taijia求助了您“这道物理题怎么写啊？？？？”的问题', '1400765056', '1');
INSERT INTO `msg` VALUES ('9', '18', '83505213', '2', 'taijia求助了您“这道物理题怎么写啊？？？？”的问题', '1400765056', '1');
INSERT INTO `msg` VALUES ('10', '19', '32555577', '2', 'taijia求助了您“这道历史题怎么写？？？？”的问题', '1400765566', '1');
INSERT INTO `msg` VALUES ('11', '19', '83505213', '2', 'taijia求助了您“这道历史题怎么写？？？？”的问题', '1400765566', '0');
INSERT INTO `msg` VALUES ('12', '1', '83505213', '3', 'taijia采纳了您“回答描述1”的回答', '1400824809', '1');
INSERT INTO `msg` VALUES ('13', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400832466', '1');
INSERT INTO `msg` VALUES ('14', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400833861', '1');
INSERT INTO `msg` VALUES ('15', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834025', '1');
INSERT INTO `msg` VALUES ('16', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834081', '0');
INSERT INTO `msg` VALUES ('17', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834144', '0');
INSERT INTO `msg` VALUES ('18', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834198', '0');
INSERT INTO `msg` VALUES ('19', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834235', '0');
INSERT INTO `msg` VALUES ('20', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834283', '0');
INSERT INTO `msg` VALUES ('21', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834382', '0');
INSERT INTO `msg` VALUES ('22', '14', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400834650', '0');
INSERT INTO `msg` VALUES ('23', '12', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400835556', '0');
INSERT INTO `msg` VALUES ('24', '12', '83505213', '3', 'chosen1采纳了您“ 这是我的回答！”的回答', '1400835562', '0');
INSERT INTO `msg` VALUES ('25', '13', '03668287', '1', 'taijia回答了您“问题描述”的提问', '1400836306', '0');
INSERT INTO `msg` VALUES ('26', '13', '83505213', '3', 'chosen1采纳了您“再来一个回答！”的回答', '1400836360', '1');
INSERT INTO `msg` VALUES ('27', '20', '32555576', '2', 'taijia求助了您“这是一个大问题”的问题', '1401069856', '0');
INSERT INTO `msg` VALUES ('28', '20', '32555577', '2', 'taijia求助了您“这是一个大问题”的问题', '1401069856', '1');
INSERT INTO `msg` VALUES ('29', '19', '83505213', '1', 'tang回答了您“这道历史题怎么写？？？？”的提问', '1401070089', '1');
INSERT INTO `msg` VALUES ('30', '19', '32555577', '3', 'taijia采纳了您“这是我的回答”的回答', '1401070122', '0');
INSERT INTO `msg` VALUES ('31', '1', '83505213', '1', 'taijia回答了您“问题描述1”的提问', '1401104345', '0');
INSERT INTO `msg` VALUES ('32', '6', '83319476', '4', '好消息', '1401115043', '0');
INSERT INTO `msg` VALUES ('33', '6', '83505213', '4', '好消息', '1401115043', '1');
INSERT INTO `msg` VALUES ('34', '6', '84630875', '4', '好消息', '1401115043', '0');
INSERT INTO `msg` VALUES ('35', '6', '84745013', '4', '好消息', '1401115043', '0');
INSERT INTO `msg` VALUES ('36', '6', '84696449', '4', '好消息', '1401115043', '0');
INSERT INTO `msg` VALUES ('37', '7', '32555575', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('38', '7', '32555576', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('39', '7', '32555577', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('40', '7', '03668287', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('41', '7', '05564291', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('42', '7', '05652414', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('43', '7', '62038424', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('44', '7', '82562115', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('45', '7', '82977595', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('46', '7', '83265976', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('47', '7', '83319476', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('48', '7', '83505213', '4', '号外', '1401160513', '1');
INSERT INTO `msg` VALUES ('49', '7', '84630875', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('50', '7', '84745013', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('51', '7', '84696449', '4', '号外', '1401160513', '0');
INSERT INTO `msg` VALUES ('52', '22', '32555576', '2', 'taijia求助了您“上图片”的问题', '1401193462', '0');
INSERT INTO `msg` VALUES ('53', '22', '32555575', '2', 'taijia求助了您“上图片”的问题', '1401193462', '0');
INSERT INTO `msg` VALUES ('54', '36', '32555575', '2', 'taijia求助了您“问学霸”的问题', '1401199417', '0');
INSERT INTO `msg` VALUES ('55', '36', '32555576', '2', 'taijia求助了您“问学霸”的问题', '1401199417', '0');
INSERT INTO `msg` VALUES ('56', '36', '32555577', '2', 'taijia求助了您“问学霸”的问题', '1401199417', '0');
INSERT INTO `msg` VALUES ('57', '36', '83505213', '1', 'taijia回答了您“问学霸”的提问', '1401201100', '0');
INSERT INTO `msg` VALUES ('58', '36', '83505213', '1', 'taijia回答了您“问学霸”的提问', '1401201461', '0');
INSERT INTO `msg` VALUES ('59', '36', '83505213', '3', 'taijia采纳了您“这回真有图片了”的回答', '1401201841', '0');
INSERT INTO `msg` VALUES ('60', '36', '83505213', '1', 'taijia回答了您“问学霸”的提问', '1401201902', '0');
INSERT INTO `msg` VALUES ('61', '36', '83505213', '1', 'taijia回答了您“问学霸”的提问', '1401202033', '0');
INSERT INTO `msg` VALUES ('62', '8', '32555575', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('63', '8', '32555576', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('64', '8', '32555577', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('65', '8', '03668287', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('66', '8', '05564291', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('67', '8', '05652414', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('68', '8', '62038424', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('69', '8', '82562115', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('70', '8', '82977595', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('71', '8', '83265976', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('72', '8', '83319476', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('73', '8', '83505213', '4', '有图片的消息', '1401207422', '1');
INSERT INTO `msg` VALUES ('74', '8', '84630875', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('75', '8', '84745013', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('76', '8', '84696449', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('77', '8', '81318654', '4', '有图片的消息', '1401207422', '0');
INSERT INTO `msg` VALUES ('78', '9', '32555575', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('79', '9', '32555576', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('80', '9', '32555577', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('81', '9', '03668287', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('82', '9', '05564291', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('83', '9', '05652414', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('84', '9', '62038424', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('85', '9', '82562115', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('86', '9', '82977595', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('87', '9', '83265976', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('88', '9', '83319476', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('89', '9', '83505213', '4', '有图有真相', '1401207749', '1');
INSERT INTO `msg` VALUES ('90', '9', '84630875', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('91', '9', '84745013', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('92', '9', '84696449', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('93', '9', '81318654', '4', '有图有真相', '1401207749', '0');
INSERT INTO `msg` VALUES ('94', '37', '03668287', '2', 'taijia求助了您“有图片哦 ”的问题', '1401239027', '0');
INSERT INTO `msg` VALUES ('95', '37', '83505213', '1', 'taijia回答了您“有图片哦 ”的提问', '1401239055', '0');
INSERT INTO `msg` VALUES ('96', '39', '03668287', '2', 'tang求助了您“提问题”的问题', '1401336377', '0');
INSERT INTO `msg` VALUES ('97', '39', '32555577', '1', 'taijia回答了您“提问题”的提问', '1401336426', '0');
INSERT INTO `msg` VALUES ('98', '40', '32555577', '2', 'chosen1求助了您“问问学霸”的问题', '1401342173', '0');
INSERT INTO `msg` VALUES ('99', '40', '83505213', '2', 'chosen1求助了您“问问学霸”的问题', '1401342173', '0');
INSERT INTO `msg` VALUES ('100', '40', '03668287', '1', 'taijia回答了您“问问学霸”的提问', '1401357367', '0');
INSERT INTO `msg` VALUES ('101', '39', '32555577', '1', 'taijia回答了您“提问题”的提问', '1401359509', '0');
INSERT INTO `msg` VALUES ('102', '39', '32555577', '1', 'taijia回答了您“提问题”的提问', '1401359697', '0');
INSERT INTO `msg` VALUES ('103', '42', '83505213', '1', 'tang回答了您“请输入你的问题或上传图片”的提问', '1401416685', '0');
INSERT INTO `msg` VALUES ('104', '42', '83505213', '1', 'tang回答了您“请输入你的问题或上传图片”的提问', '1401417263', '0');
INSERT INTO `msg` VALUES ('105', '49', '83505213', '1', 'taijia回答了您“请输入你的问题或上传图片”的提问', '1401781256', '0');
INSERT INTO `msg` VALUES ('106', '49', '83505213', '3', 'taijia采纳了您“”的回答', '1401781264', '0');
INSERT INTO `msg` VALUES ('107', '50', '83505213', '1', 'taijia回答了您“”的提问', '1401791788', '0');
INSERT INTO `msg` VALUES ('108', '50', '83505213', '1', 'taijia回答了您“”的提问', '1401791827', '0');
INSERT INTO `msg` VALUES ('109', '50', '83505213', '1', 'taijia回答了您“”的提问', '1401791854', '0');
INSERT INTO `msg` VALUES ('110', '51', '03662147', '1', '低调的奢华回答了您“是清水”的提问', '1401806042', '0');
INSERT INTO `msg` VALUES ('111', '51', '03662147', '3', '低调的奢华采纳了您“陈小春”的回答', '1401806055', '0');
INSERT INTO `msg` VALUES ('112', '50', '83505213', '1', 'tang回答了您的提问', '1401884743', '1');
INSERT INTO `msg` VALUES ('113', '52', '83505213', '1', 'tang回答了您“1111”的提问', '1401893565', '0');
INSERT INTO `msg` VALUES ('114', '53', '32555575', '2', 'tang求助了您“12312”的问题', '1401895685', '0');
INSERT INTO `msg` VALUES ('115', '53', '32555576', '2', 'tang求助了您“12312”的问题', '1401895685', '0');
INSERT INTO `msg` VALUES ('116', '53', '03668287', '2', 'tang求助了您“12312”的问题', '1401895685', '0');
INSERT INTO `msg` VALUES ('117', '54', '32555575', '2', 'tang求助了您“2123”的问题', '1401895700', '0');
INSERT INTO `msg` VALUES ('118', '54', '32555576', '2', 'tang求助了您“2123”的问题', '1401895700', '0');
INSERT INTO `msg` VALUES ('119', '54', '03668287', '2', 'tang求助了您“2123”的问题', '1401895700', '0');
INSERT INTO `msg` VALUES ('120', '55', '32555576', '2', 'tang求助了您“efwe”的问题', '1401895774', '0');
INSERT INTO `msg` VALUES ('121', '55', '03668287', '2', 'tang求助了您“efwe”的问题', '1401895774', '0');
INSERT INTO `msg` VALUES ('122', '55', '05564291', '2', 'tang求助了您“efwe”的问题', '1401895774', '0');
INSERT INTO `msg` VALUES ('123', '55', '32555577', '1', 'ttt回答了您“efwe”的提问', '1401973807', '0');
INSERT INTO `msg` VALUES ('124', '60', '83505213', '1', 'xumin回答了您的提问', '1402849704', '0');
INSERT INTO `msg` VALUES ('125', '62', '32555575', '2', 'taijia求助了您“这是我提的问题”的问题', '1402882942', '0');
INSERT INTO `msg` VALUES ('126', '62', '32555576', '2', 'taijia求助了您“这是我提的问题”的问题', '1402882942', '0');
INSERT INTO `msg` VALUES ('127', '62', '32555577', '2', 'taijia求助了您“这是我提的问题”的问题', '1402882942', '0');

-- ----------------------------
-- Table structure for onlineduration
-- ----------------------------
DROP TABLE IF EXISTS `onlineduration`;
CREATE TABLE `onlineduration` (
  `od_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_id` int(10) DEFAULT NULL COMMENT '教师id',
  `begin_time` int(10) DEFAULT NULL COMMENT '开始时间',
  `end_time` int(10) DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`od_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='在线时长表';

-- ----------------------------
-- Records of onlineduration
-- ----------------------------
INSERT INTO `onlineduration` VALUES ('1', '1', '1400000000', '1400222222');
INSERT INTO `onlineduration` VALUES ('2', '1', '1400333333', '1400444444');
INSERT INTO `onlineduration` VALUES ('3', '1', '1400555555', '1400666666');
INSERT INTO `onlineduration` VALUES ('4', '1', '1400777777', '1400888888');
INSERT INTO `onlineduration` VALUES ('5', '1', '1400888889', '1400999999');
INSERT INTO `onlineduration` VALUES ('6', '1', '1401009442', '1401009443');
INSERT INTO `onlineduration` VALUES ('7', '1', '1401009444', '1401009445');
INSERT INTO `onlineduration` VALUES ('8', '1', '1401009748', '1401009750');
INSERT INTO `onlineduration` VALUES ('9', '1', '1401009753', '1401072033');
INSERT INTO `onlineduration` VALUES ('10', '2', '1401035661', '1401035667');
INSERT INTO `onlineduration` VALUES ('11', '1', '1401072034', '1401072041');
INSERT INTO `onlineduration` VALUES ('12', '1', '1401072043', null);
INSERT INTO `onlineduration` VALUES ('13', '2', '1401072043', '1401072044');
INSERT INTO `onlineduration` VALUES ('14', '3', '1401072401', '1401072405');

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `pr_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `answer_id` int(10) DEFAULT NULL COMMENT '回答id',
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  PRIMARY KEY (`pr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='点赞';

-- ----------------------------
-- Records of praise
-- ----------------------------
INSERT INTO `praise` VALUES ('2', '2', '83505213');
INSERT INTO `praise` VALUES ('3', '1', '83505213');
INSERT INTO `praise` VALUES ('4', '6', '83505213');
INSERT INTO `praise` VALUES ('5', '7', '83505213');
INSERT INTO `praise` VALUES ('6', '7', '03668287');
INSERT INTO `praise` VALUES ('7', '7', '32555577');
INSERT INTO `praise` VALUES ('8', '4', '83505213');
INSERT INTO `praise` VALUES ('9', '25', '03662147');
INSERT INTO `praise` VALUES ('10', '26', '83505213');
INSERT INTO `praise` VALUES ('11', '28', '0');
INSERT INTO `praise` VALUES ('12', '28', '0');
INSERT INTO `praise` VALUES ('13', '28', '0');
INSERT INTO `praise` VALUES ('14', '28', '0');
INSERT INTO `praise` VALUES ('15', '28', '0');
INSERT INTO `praise` VALUES ('16', '28', '0');
INSERT INTO `praise` VALUES ('17', '28', '0');
INSERT INTO `praise` VALUES ('18', '28', '0');
INSERT INTO `praise` VALUES ('19', '28', '0');
INSERT INTO `praise` VALUES ('20', '28', '0');
INSERT INTO `praise` VALUES ('21', '28', '0');
INSERT INTO `praise` VALUES ('22', '28', '0');
INSERT INTO `praise` VALUES ('23', '28', '0');
INSERT INTO `praise` VALUES ('24', '28', '0');
INSERT INTO `praise` VALUES ('25', '28', '0');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `q_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `question_desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `grade_id` int(10) DEFAULT '0' COMMENT '年级',
  `subject_id` int(10) DEFAULT '0' COMMENT '科目',
  `client_style` varchar(64) DEFAULT NULL COMMENT '客户端型号',
  `is_reward` int(1) DEFAULT '0' COMMENT '是否悬赏：0-否 1-是',
  `reward_amount` int(5) DEFAULT '0' COMMENT '悬赏豆数',
  `create_time` int(10) DEFAULT '0' COMMENT '创建时间',
  `to_teacher` int(1) DEFAULT '0' COMMENT '是否提问老师：0-否，1-是',
  `allocated` int(1) DEFAULT '0' COMMENT '是否分配给指定教师 0未分配1已分配',
  `answer_num` int(5) DEFAULT '0' COMMENT '回答数',
  `has_answer` int(1) DEFAULT '0' COMMENT '有无答案：0-无 1-有',
  `has_img` int(1) DEFAULT '0' COMMENT '是否有图片  0-无 1-有',
  `has_favorate` int(1) DEFAULT '0' COMMENT '是否有最佳答案：0-否 1-是',
  `status` int(1) DEFAULT '1' COMMENT '状态：0-废弃 1-正常',
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='问题';

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '83505213', '问题描述1', '1', '1', '诺基亚N96', '1', '5', '1400000000', '0', '0', '3', '1', '1', '1', '1');
INSERT INTO `question` VALUES ('2', '83505213', '问题描述2', '1', '2', 'IPHONE4', '0', '0', '1300000000', '0', '0', '2', '1', '0', '1', '1');
INSERT INTO `question` VALUES ('3', '83505213', '问题描述3', '1', '3', 'IPHONE5', '0', '0', '1398888888', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('4', '03668287', '这是个问题', '2', '3', 'Galaxy 4', '1', '5', '1400005555', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('5', '03668287', '问题描述', '4', '7', 'Galaxy 3', '0', '0', '1400007777', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('6', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099999', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('7', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099991', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('8', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099992', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('9', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099992', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('10', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099994', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('11', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099999', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('12', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099999', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('13', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099999', '0', '0', '1', '1', '0', '1', '1');
INSERT INTO `question` VALUES ('14', '03668287', '问题描述', '6', '6', 'Nexus 5', '0', '0', '1400099999', '0', '0', '1', '1', '0', '0', '1');
INSERT INTO `question` VALUES ('15', '83505213', '这是一个关于初二历史的问题', '3', '4', 'PC', '1', '4', '1400566081', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('16', '83505213', '高一地理问老师', '5', '5', 'PC', '1', '5', '1400569501', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('17', '83505213', '问老师  高一地理', '5', '5', 'PC', '1', '5', '1400575628', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('18', '83505213', '这道物理题怎么写啊？？？？', '5', '8', 'PC', '1', '5', '1400765056', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('19', '83505213', '这道历史题怎么写？？？？', '2', '4', 'PC', '1', '0', '1400765566', '0', '0', '1', '1', '0', '1', '1');
INSERT INTO `question` VALUES ('20', '83505213', '这是一个大问题', '1', '2', 'PC', '1', '5', '1401069856', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('21', '32555577', '这是一个问老师的问题', '3', '5', 'PC', '0', '0', '1401069994', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('22', '83505213', '上图片', '2', '2', 'PC', '1', '1', '1401193462', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('23', '83505213', '上图片', '3', '3', 'PC', '1', '0', '1401193610', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('24', '83505213', '上图片', '2', '3', 'PC', '1', '0', '1401193733', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('25', '83505213', '上图片1', '3', '4', 'PC', null, '0', '1401195305', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('26', '83505213', 'sss', '2', '2', 'PC', null, '0', '1401195361', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('27', '83505213', '上图片222', '3', '3', 'PC', null, '0', '1401196565', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('28', '83505213', '这是个问题', '6', '6', 'PC', null, '0', '1401197097', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('29', '83505213', '这是一个大大的问题', '2', '5', 'PC', null, '0', '1401198036', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('30', '83505213', '多对多', '4', '3', 'PC', null, '0', '1401198123', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('31', '83505213', '多对多的', '2', '5', 'PC', null, '0', '1401198276', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('32', '83505213', '多对多的', '4', '5', 'PC', null, '0', '1401198293', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('33', '83505213', '的举动是减肥那啥', '3', '4', 'PC', '1', '3', '1401198467', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('34', '83505213', '精神了精神了', '5', '5', 'PC', '1', '2', '1401198750', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('35', '83505213', '问老师', '4', '4', 'PC', '0', '0', '1401199110', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('36', '83505213', '问学霸', '3', '3', 'PC', '1', '4', '1401199416', '0', '0', '4', '1', '1', '1', '1');
INSERT INTO `question` VALUES ('37', '83505213', '有图片哦 ', '5', '5', 'PC', '1', '2', '1401239026', '0', '0', '1', '1', '1', '0', '1');
INSERT INTO `question` VALUES ('38', '83505213', '问老师', '1', '2', 'PC', '1', '5', '1401283845', '1', '1', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('39', '32555577', '提问题', '4', '4', 'PC', '1', '3', '1401336377', '0', '0', '3', '1', '1', '0', '1');
INSERT INTO `question` VALUES ('40', '03668287', '问问学霸', '3', '5', 'PC', null, '0', '1401342173', '0', '0', '1', '1', '1', '0', '1');
INSERT INTO `question` VALUES ('41', '83505213', '问老师的问题', '3', '4', 'PC', '1', '5', '1401412898', '1', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('42', '83505213', '请输入你的问题或上传图片', '5', '5', 'PC', '1', '4', '1401413570', '0', '0', '2', '1', '1', '0', '1');
INSERT INTO `question` VALUES ('43', '83505213', '问问题', '3', '4', 'PC', '1', '1', '1401762074', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('44', '83505213', '问个问题', '4', '5', 'PC', null, '0', '1401766584', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('45', '83505213', '问个问题', '4', '5', 'PC', null, '0', '1401766828', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('46', '83505213', '请输入你的问题或上传图片', '4', '4', 'PC', null, '0', '1401768804', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('47', '83505213', '请输入你的问题或上传图片', '1', '2', 'PC', null, '0', '1401779667', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('48', '83505213', '请输入你的问题或上传图片', '5', '5', 'PC', null, '0', '1401780016', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('49', '83505213', '请输入你的问题或上传图片', '3', '3', 'PC', null, '0', '1401780430', '0', '0', '1', '1', '1', '1', '1');
INSERT INTO `question` VALUES ('50', '83505213', '', '1', '3', 'PC', null, '0', '1401788551', '0', '0', '4', '1', '1', '0', '1');
INSERT INTO `question` VALUES ('51', '03662147', '是清水', '1', '1', 'PC', null, '0', '1401805372', '0', '0', '1', '1', '1', '1', '1');
INSERT INTO `question` VALUES ('52', '83505213', '1111', '1', '3', 'PC', null, '0', '1401893515', '0', '0', '1', '1', '0', '0', '1');
INSERT INTO `question` VALUES ('53', '32555577', '12312', '1', '3', 'PC', null, '0', '1401895685', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('54', '32555577', '2123', '1', '2', 'PC', null, '0', '1401895700', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('55', '32555577', 'efwe', '1', '2', 'PC', null, '0', '1401895745', '0', '0', '1', '1', '0', '0', '1');
INSERT INTO `question` VALUES ('56', '83505213', '111', '2', '2', 'PC', '1', '5', '1402023862', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('57', '83505213', '', '2', '3', 'PC', null, '0', '1402043461', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('58', '83505213', '', '3', '4', 'PC', null, '0', '1402043583', '0', '0', '0', '0', '1', '0', '1');
INSERT INTO `question` VALUES ('59', '83505213', '一次只能提问一道问题哦。目前老师暂时不接受英语阅读、完形填空及语文阅读的题目指导。', '1', '2', 'PC', '1', '5', '1402043593', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('60', '83505213', '', '3', '3', 'PC', null, '0', '1402043739', '0', '0', '1', '1', '1', '0', '1');
INSERT INTO `question` VALUES ('61', '05564291', '111<script>alert(25)</script>', '4', '5', 'PC', null, '0', '1402293332', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `question` VALUES ('62', '83505213', '这是我提的问题', '4', '6', 'PC', null, '0', '1402882942', '0', '0', '0', '0', '0', '0', '1');

-- ----------------------------
-- Table structure for questionuser
-- ----------------------------
DROP TABLE IF EXISTS `questionuser`;
CREATE TABLE `questionuser` (
  `qu_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `question_id` int(10) DEFAULT NULL COMMENT '问题id',
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  PRIMARY KEY (`qu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='问题对用户';

-- ----------------------------
-- Records of questionuser
-- ----------------------------
INSERT INTO `questionuser` VALUES ('1', '1', '83505213');
INSERT INTO `questionuser` VALUES ('2', '1', '03668287');
INSERT INTO `questionuser` VALUES ('3', null, '32555577');
INSERT INTO `questionuser` VALUES ('4', null, '03668287');
INSERT INTO `questionuser` VALUES ('5', null, '03668287');
INSERT INTO `questionuser` VALUES ('6', null, '83505213');
INSERT INTO `questionuser` VALUES ('7', '19', '32555577');
INSERT INTO `questionuser` VALUES ('8', '19', '83505213');
INSERT INTO `questionuser` VALUES ('9', '20', '32555576');
INSERT INTO `questionuser` VALUES ('10', '20', '32555577');
INSERT INTO `questionuser` VALUES ('11', '22', '32555576');
INSERT INTO `questionuser` VALUES ('12', '22', '32555575');
INSERT INTO `questionuser` VALUES ('13', '36', '32555575');
INSERT INTO `questionuser` VALUES ('14', '36', '32555576');
INSERT INTO `questionuser` VALUES ('15', '36', '32555577');
INSERT INTO `questionuser` VALUES ('16', '37', '03668287');
INSERT INTO `questionuser` VALUES ('17', '38', '32555575');
INSERT INTO `questionuser` VALUES ('18', '39', '03668287');
INSERT INTO `questionuser` VALUES ('19', '40', '32555577');
INSERT INTO `questionuser` VALUES ('20', '40', '83505213');
INSERT INTO `questionuser` VALUES ('21', '53', '32555575');
INSERT INTO `questionuser` VALUES ('22', '53', '32555576');
INSERT INTO `questionuser` VALUES ('23', '53', '03668287');
INSERT INTO `questionuser` VALUES ('24', '54', '32555575');
INSERT INTO `questionuser` VALUES ('25', '54', '32555576');
INSERT INTO `questionuser` VALUES ('26', '54', '03668287');
INSERT INTO `questionuser` VALUES ('27', '55', '32555576');
INSERT INTO `questionuser` VALUES ('28', '55', '03668287');
INSERT INTO `questionuser` VALUES ('29', '55', '05564291');
INSERT INTO `questionuser` VALUES ('30', '62', '32555575');
INSERT INTO `questionuser` VALUES ('31', '62', '32555576');
INSERT INTO `questionuser` VALUES ('32', '62', '32555577');

-- ----------------------------
-- Table structure for rechargepackage
-- ----------------------------
DROP TABLE IF EXISTS `rechargepackage`;
CREATE TABLE `rechargepackage` (
  `rcp_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rcp_amount` int(5) DEFAULT NULL COMMENT '充值金额',
  `rcp_num` int(5) DEFAULT NULL COMMENT '充值数量',
  `status` int(1) DEFAULT '0' COMMENT '0-废弃 1-正常',
  PRIMARY KEY (`rcp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='汇豆充值套餐表';

-- ----------------------------
-- Records of rechargepackage
-- ----------------------------
INSERT INTO `rechargepackage` VALUES ('1', '10', '10', '1');
INSERT INTO `rechargepackage` VALUES ('2', '19', '20', '1');
INSERT INTO `rechargepackage` VALUES ('3', '27', '30', '1');
INSERT INTO `rechargepackage` VALUES ('4', '44', '50', '1');
INSERT INTO `rechargepackage` VALUES ('5', '85', '100', '1');

-- ----------------------------
-- Table structure for r_city
-- ----------------------------
DROP TABLE IF EXISTS `r_city`;
CREATE TABLE `r_city` (
  `city_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city_name` varchar(64) DEFAULT NULL,
  `province_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=659001 DEFAULT CHARSET=utf8 COMMENT='城市';

-- ----------------------------
-- Records of r_city
-- ----------------------------
INSERT INTO `r_city` VALUES ('110100', '市辖区', '110000');
INSERT INTO `r_city` VALUES ('110200', '县', '110000');
INSERT INTO `r_city` VALUES ('120100', '市辖区', '120000');
INSERT INTO `r_city` VALUES ('120200', '县', '120000');
INSERT INTO `r_city` VALUES ('130100', '石家庄市', '130000');
INSERT INTO `r_city` VALUES ('130200', '唐山市', '130000');
INSERT INTO `r_city` VALUES ('130300', '秦皇岛市', '130000');
INSERT INTO `r_city` VALUES ('130400', '邯郸市', '130000');
INSERT INTO `r_city` VALUES ('130500', '邢台市', '130000');
INSERT INTO `r_city` VALUES ('130600', '保定市', '130000');
INSERT INTO `r_city` VALUES ('130700', '张家口市', '130000');
INSERT INTO `r_city` VALUES ('130800', '承德市', '130000');
INSERT INTO `r_city` VALUES ('130900', '沧州市', '130000');
INSERT INTO `r_city` VALUES ('131000', '廊坊市', '130000');
INSERT INTO `r_city` VALUES ('131100', '衡水市', '130000');
INSERT INTO `r_city` VALUES ('140100', '太原市', '140000');
INSERT INTO `r_city` VALUES ('140200', '大同市', '140000');
INSERT INTO `r_city` VALUES ('140300', '阳泉市', '140000');
INSERT INTO `r_city` VALUES ('140400', '长治市', '140000');
INSERT INTO `r_city` VALUES ('140500', '晋城市', '140000');
INSERT INTO `r_city` VALUES ('140600', '朔州市', '140000');
INSERT INTO `r_city` VALUES ('140700', '晋中市', '140000');
INSERT INTO `r_city` VALUES ('140800', '运城市', '140000');
INSERT INTO `r_city` VALUES ('140900', '忻州市', '140000');
INSERT INTO `r_city` VALUES ('141000', '临汾市', '140000');
INSERT INTO `r_city` VALUES ('141100', '吕梁市', '140000');
INSERT INTO `r_city` VALUES ('150100', '呼和浩特市', '150000');
INSERT INTO `r_city` VALUES ('150200', '包头市', '150000');
INSERT INTO `r_city` VALUES ('150300', '乌海市', '150000');
INSERT INTO `r_city` VALUES ('150400', '赤峰市', '150000');
INSERT INTO `r_city` VALUES ('150500', '通辽市', '150000');
INSERT INTO `r_city` VALUES ('150600', '鄂尔多斯市', '150000');
INSERT INTO `r_city` VALUES ('150700', '呼伦贝尔市', '150000');
INSERT INTO `r_city` VALUES ('150800', '巴彦淖尔市', '150000');
INSERT INTO `r_city` VALUES ('150900', '乌兰察布市', '150000');
INSERT INTO `r_city` VALUES ('152200', '兴安盟', '150000');
INSERT INTO `r_city` VALUES ('152500', '锡林郭勒盟', '150000');
INSERT INTO `r_city` VALUES ('152900', '阿拉善盟', '150000');
INSERT INTO `r_city` VALUES ('210100', '沈阳市', '210000');
INSERT INTO `r_city` VALUES ('210200', '大连市', '210000');
INSERT INTO `r_city` VALUES ('210300', '鞍山市', '210000');
INSERT INTO `r_city` VALUES ('210400', '抚顺市', '210000');
INSERT INTO `r_city` VALUES ('210500', '本溪市', '210000');
INSERT INTO `r_city` VALUES ('210600', '丹东市', '210000');
INSERT INTO `r_city` VALUES ('210700', '锦州市', '210000');
INSERT INTO `r_city` VALUES ('210800', '营口市', '210000');
INSERT INTO `r_city` VALUES ('210900', '阜新市', '210000');
INSERT INTO `r_city` VALUES ('211000', '辽阳市', '210000');
INSERT INTO `r_city` VALUES ('211100', '盘锦市', '210000');
INSERT INTO `r_city` VALUES ('211200', '铁岭市', '210000');
INSERT INTO `r_city` VALUES ('211300', '朝阳市', '210000');
INSERT INTO `r_city` VALUES ('211400', '葫芦岛市', '210000');
INSERT INTO `r_city` VALUES ('220100', '长春市', '220000');
INSERT INTO `r_city` VALUES ('220200', '吉林市', '220000');
INSERT INTO `r_city` VALUES ('220300', '四平市', '220000');
INSERT INTO `r_city` VALUES ('220400', '辽源市', '220000');
INSERT INTO `r_city` VALUES ('220500', '通化市', '220000');
INSERT INTO `r_city` VALUES ('220600', '白山市', '220000');
INSERT INTO `r_city` VALUES ('220700', '松原市', '220000');
INSERT INTO `r_city` VALUES ('220800', '白城市', '220000');
INSERT INTO `r_city` VALUES ('222400', '延边朝鲜族自治州', '220000');
INSERT INTO `r_city` VALUES ('230100', '哈尔滨市', '230000');
INSERT INTO `r_city` VALUES ('230200', '齐齐哈尔市', '230000');
INSERT INTO `r_city` VALUES ('230300', '鸡西市', '230000');
INSERT INTO `r_city` VALUES ('230400', '鹤岗市', '230000');
INSERT INTO `r_city` VALUES ('230500', '双鸭山市', '230000');
INSERT INTO `r_city` VALUES ('230600', '大庆市', '230000');
INSERT INTO `r_city` VALUES ('230700', '伊春市', '230000');
INSERT INTO `r_city` VALUES ('230800', '佳木斯市', '230000');
INSERT INTO `r_city` VALUES ('230900', '七台河市', '230000');
INSERT INTO `r_city` VALUES ('231000', '牡丹江市', '230000');
INSERT INTO `r_city` VALUES ('231100', '黑河市', '230000');
INSERT INTO `r_city` VALUES ('231200', '绥化市', '230000');
INSERT INTO `r_city` VALUES ('232700', '大兴安岭地区', '230000');
INSERT INTO `r_city` VALUES ('310100', '市辖区', '310000');
INSERT INTO `r_city` VALUES ('310200', '县', '310000');
INSERT INTO `r_city` VALUES ('320100', '南京市', '320000');
INSERT INTO `r_city` VALUES ('320200', '无锡市', '320000');
INSERT INTO `r_city` VALUES ('320300', '徐州市', '320000');
INSERT INTO `r_city` VALUES ('320400', '常州市', '320000');
INSERT INTO `r_city` VALUES ('320500', '苏州市', '320000');
INSERT INTO `r_city` VALUES ('320600', '南通市', '320000');
INSERT INTO `r_city` VALUES ('320700', '连云港市', '320000');
INSERT INTO `r_city` VALUES ('320800', '淮安市', '320000');
INSERT INTO `r_city` VALUES ('320900', '盐城市', '320000');
INSERT INTO `r_city` VALUES ('321000', '扬州市', '320000');
INSERT INTO `r_city` VALUES ('321100', '镇江市', '320000');
INSERT INTO `r_city` VALUES ('321200', '泰州市', '320000');
INSERT INTO `r_city` VALUES ('321300', '宿迁市', '320000');
INSERT INTO `r_city` VALUES ('330100', '杭州市', '330000');
INSERT INTO `r_city` VALUES ('330200', '宁波市', '330000');
INSERT INTO `r_city` VALUES ('330300', '温州市', '330000');
INSERT INTO `r_city` VALUES ('330400', '嘉兴市', '330000');
INSERT INTO `r_city` VALUES ('330500', '湖州市', '330000');
INSERT INTO `r_city` VALUES ('330600', '绍兴市', '330000');
INSERT INTO `r_city` VALUES ('330700', '金华市', '330000');
INSERT INTO `r_city` VALUES ('330800', '衢州市', '330000');
INSERT INTO `r_city` VALUES ('330900', '舟山市', '330000');
INSERT INTO `r_city` VALUES ('331000', '台州市', '330000');
INSERT INTO `r_city` VALUES ('331100', '丽水市', '330000');
INSERT INTO `r_city` VALUES ('340100', '合肥市', '340000');
INSERT INTO `r_city` VALUES ('340200', '芜湖市', '340000');
INSERT INTO `r_city` VALUES ('340300', '蚌埠市', '340000');
INSERT INTO `r_city` VALUES ('340400', '淮南市', '340000');
INSERT INTO `r_city` VALUES ('340500', '马鞍山市', '340000');
INSERT INTO `r_city` VALUES ('340600', '淮北市', '340000');
INSERT INTO `r_city` VALUES ('340700', '铜陵市', '340000');
INSERT INTO `r_city` VALUES ('340800', '安庆市', '340000');
INSERT INTO `r_city` VALUES ('341000', '黄山市', '340000');
INSERT INTO `r_city` VALUES ('341100', '滁州市', '340000');
INSERT INTO `r_city` VALUES ('341200', '阜阳市', '340000');
INSERT INTO `r_city` VALUES ('341300', '宿州市', '340000');
INSERT INTO `r_city` VALUES ('341400', '巢湖市', '340000');
INSERT INTO `r_city` VALUES ('341500', '六安市', '340000');
INSERT INTO `r_city` VALUES ('341600', '亳州市', '340000');
INSERT INTO `r_city` VALUES ('341700', '池州市', '340000');
INSERT INTO `r_city` VALUES ('341800', '宣城市', '340000');
INSERT INTO `r_city` VALUES ('350100', '福州市', '350000');
INSERT INTO `r_city` VALUES ('350200', '厦门市', '350000');
INSERT INTO `r_city` VALUES ('350300', '莆田市', '350000');
INSERT INTO `r_city` VALUES ('350400', '三明市', '350000');
INSERT INTO `r_city` VALUES ('350500', '泉州市', '350000');
INSERT INTO `r_city` VALUES ('350600', '漳州市', '350000');
INSERT INTO `r_city` VALUES ('350700', '南平市', '350000');
INSERT INTO `r_city` VALUES ('350800', '龙岩市', '350000');
INSERT INTO `r_city` VALUES ('350900', '宁德市', '350000');
INSERT INTO `r_city` VALUES ('360100', '南昌市', '360000');
INSERT INTO `r_city` VALUES ('360200', '景德镇市', '360000');
INSERT INTO `r_city` VALUES ('360300', '萍乡市', '360000');
INSERT INTO `r_city` VALUES ('360400', '九江市', '360000');
INSERT INTO `r_city` VALUES ('360500', '新余市', '360000');
INSERT INTO `r_city` VALUES ('360600', '鹰潭市', '360000');
INSERT INTO `r_city` VALUES ('360700', '赣州市', '360000');
INSERT INTO `r_city` VALUES ('360800', '吉安市', '360000');
INSERT INTO `r_city` VALUES ('360900', '宜春市', '360000');
INSERT INTO `r_city` VALUES ('361000', '抚州市', '360000');
INSERT INTO `r_city` VALUES ('361100', '上饶市', '360000');
INSERT INTO `r_city` VALUES ('370100', '济南市', '370000');
INSERT INTO `r_city` VALUES ('370200', '青岛市', '370000');
INSERT INTO `r_city` VALUES ('370300', '淄博市', '370000');
INSERT INTO `r_city` VALUES ('370400', '枣庄市', '370000');
INSERT INTO `r_city` VALUES ('370500', '东营市', '370000');
INSERT INTO `r_city` VALUES ('370600', '烟台市', '370000');
INSERT INTO `r_city` VALUES ('370700', '潍坊市', '370000');
INSERT INTO `r_city` VALUES ('370800', '济宁市', '370000');
INSERT INTO `r_city` VALUES ('370900', '泰安市', '370000');
INSERT INTO `r_city` VALUES ('371000', '威海市', '370000');
INSERT INTO `r_city` VALUES ('371100', '日照市', '370000');
INSERT INTO `r_city` VALUES ('371200', '莱芜市', '370000');
INSERT INTO `r_city` VALUES ('371300', '临沂市', '370000');
INSERT INTO `r_city` VALUES ('371400', '德州市', '370000');
INSERT INTO `r_city` VALUES ('371500', '聊城市', '370000');
INSERT INTO `r_city` VALUES ('371600', '滨州市', '370000');
INSERT INTO `r_city` VALUES ('371700', '荷泽市', '370000');
INSERT INTO `r_city` VALUES ('410100', '郑州市', '410000');
INSERT INTO `r_city` VALUES ('410200', '开封市', '410000');
INSERT INTO `r_city` VALUES ('410300', '洛阳市', '410000');
INSERT INTO `r_city` VALUES ('410400', '平顶山市', '410000');
INSERT INTO `r_city` VALUES ('410500', '安阳市', '410000');
INSERT INTO `r_city` VALUES ('410600', '鹤壁市', '410000');
INSERT INTO `r_city` VALUES ('410700', '新乡市', '410000');
INSERT INTO `r_city` VALUES ('410800', '焦作市', '410000');
INSERT INTO `r_city` VALUES ('410900', '濮阳市', '410000');
INSERT INTO `r_city` VALUES ('411000', '许昌市', '410000');
INSERT INTO `r_city` VALUES ('411100', '漯河市', '410000');
INSERT INTO `r_city` VALUES ('411200', '三门峡市', '410000');
INSERT INTO `r_city` VALUES ('411300', '南阳市', '410000');
INSERT INTO `r_city` VALUES ('411400', '商丘市', '410000');
INSERT INTO `r_city` VALUES ('411500', '信阳市', '410000');
INSERT INTO `r_city` VALUES ('411600', '周口市', '410000');
INSERT INTO `r_city` VALUES ('411700', '驻马店市', '410000');
INSERT INTO `r_city` VALUES ('420100', '武汉市', '420000');
INSERT INTO `r_city` VALUES ('420200', '黄石市', '420000');
INSERT INTO `r_city` VALUES ('420300', '十堰市', '420000');
INSERT INTO `r_city` VALUES ('420500', '宜昌市', '420000');
INSERT INTO `r_city` VALUES ('420600', '襄樊市', '420000');
INSERT INTO `r_city` VALUES ('420700', '鄂州市', '420000');
INSERT INTO `r_city` VALUES ('420800', '荆门市', '420000');
INSERT INTO `r_city` VALUES ('420900', '孝感市', '420000');
INSERT INTO `r_city` VALUES ('421000', '荆州市', '420000');
INSERT INTO `r_city` VALUES ('421100', '黄冈市', '420000');
INSERT INTO `r_city` VALUES ('421200', '咸宁市', '420000');
INSERT INTO `r_city` VALUES ('421300', '随州市', '420000');
INSERT INTO `r_city` VALUES ('422800', '恩施土家族苗族自治州', '420000');
INSERT INTO `r_city` VALUES ('429000', '省直辖行政单位', '420000');
INSERT INTO `r_city` VALUES ('430100', '长沙市', '430000');
INSERT INTO `r_city` VALUES ('430200', '株洲市', '430000');
INSERT INTO `r_city` VALUES ('430300', '湘潭市', '430000');
INSERT INTO `r_city` VALUES ('430400', '衡阳市', '430000');
INSERT INTO `r_city` VALUES ('430500', '邵阳市', '430000');
INSERT INTO `r_city` VALUES ('430600', '岳阳市', '430000');
INSERT INTO `r_city` VALUES ('430700', '常德市', '430000');
INSERT INTO `r_city` VALUES ('430800', '张家界市', '430000');
INSERT INTO `r_city` VALUES ('430900', '益阳市', '430000');
INSERT INTO `r_city` VALUES ('431000', '郴州市', '430000');
INSERT INTO `r_city` VALUES ('431100', '永州市', '430000');
INSERT INTO `r_city` VALUES ('431200', '怀化市', '430000');
INSERT INTO `r_city` VALUES ('431300', '娄底市', '430000');
INSERT INTO `r_city` VALUES ('433100', '湘西土家族苗族自治州', '430000');
INSERT INTO `r_city` VALUES ('440100', '广州市', '440000');
INSERT INTO `r_city` VALUES ('440200', '韶关市', '440000');
INSERT INTO `r_city` VALUES ('440300', '深圳市', '440000');
INSERT INTO `r_city` VALUES ('440400', '珠海市', '440000');
INSERT INTO `r_city` VALUES ('440500', '汕头市', '440000');
INSERT INTO `r_city` VALUES ('440600', '佛山市', '440000');
INSERT INTO `r_city` VALUES ('440700', '江门市', '440000');
INSERT INTO `r_city` VALUES ('440800', '湛江市', '440000');
INSERT INTO `r_city` VALUES ('440900', '茂名市', '440000');
INSERT INTO `r_city` VALUES ('441200', '肇庆市', '440000');
INSERT INTO `r_city` VALUES ('441300', '惠州市', '440000');
INSERT INTO `r_city` VALUES ('441400', '梅州市', '440000');
INSERT INTO `r_city` VALUES ('441500', '汕尾市', '440000');
INSERT INTO `r_city` VALUES ('441600', '河源市', '440000');
INSERT INTO `r_city` VALUES ('441700', '阳江市', '440000');
INSERT INTO `r_city` VALUES ('441800', '清远市', '440000');
INSERT INTO `r_city` VALUES ('441900', '东莞市', '440000');
INSERT INTO `r_city` VALUES ('442000', '中山市', '440000');
INSERT INTO `r_city` VALUES ('445100', '潮州市', '440000');
INSERT INTO `r_city` VALUES ('445200', '揭阳市', '440000');
INSERT INTO `r_city` VALUES ('445300', '云浮市', '440000');
INSERT INTO `r_city` VALUES ('450100', '南宁市', '450000');
INSERT INTO `r_city` VALUES ('450200', '柳州市', '450000');
INSERT INTO `r_city` VALUES ('450300', '桂林市', '450000');
INSERT INTO `r_city` VALUES ('450400', '梧州市', '450000');
INSERT INTO `r_city` VALUES ('450500', '北海市', '450000');
INSERT INTO `r_city` VALUES ('450600', '防城港市', '450000');
INSERT INTO `r_city` VALUES ('450700', '钦州市', '450000');
INSERT INTO `r_city` VALUES ('450800', '贵港市', '450000');
INSERT INTO `r_city` VALUES ('450900', '玉林市', '450000');
INSERT INTO `r_city` VALUES ('451000', '百色市', '450000');
INSERT INTO `r_city` VALUES ('451100', '贺州市', '450000');
INSERT INTO `r_city` VALUES ('451200', '河池市', '450000');
INSERT INTO `r_city` VALUES ('451300', '来宾市', '450000');
INSERT INTO `r_city` VALUES ('451400', '崇左市', '450000');
INSERT INTO `r_city` VALUES ('460100', '海口市', '460000');
INSERT INTO `r_city` VALUES ('460200', '三亚市', '460000');
INSERT INTO `r_city` VALUES ('469000', '省直辖县级行政单位', '460000');
INSERT INTO `r_city` VALUES ('500100', '市辖区', '500000');
INSERT INTO `r_city` VALUES ('500200', '县', '500000');
INSERT INTO `r_city` VALUES ('500300', '市', '500000');
INSERT INTO `r_city` VALUES ('510100', '成都市', '510000');
INSERT INTO `r_city` VALUES ('510300', '自贡市', '510000');
INSERT INTO `r_city` VALUES ('510400', '攀枝花市', '510000');
INSERT INTO `r_city` VALUES ('510500', '泸州市', '510000');
INSERT INTO `r_city` VALUES ('510600', '德阳市', '510000');
INSERT INTO `r_city` VALUES ('510700', '绵阳市', '510000');
INSERT INTO `r_city` VALUES ('510800', '广元市', '510000');
INSERT INTO `r_city` VALUES ('510900', '遂宁市', '510000');
INSERT INTO `r_city` VALUES ('511000', '内江市', '510000');
INSERT INTO `r_city` VALUES ('511100', '乐山市', '510000');
INSERT INTO `r_city` VALUES ('511300', '南充市', '510000');
INSERT INTO `r_city` VALUES ('511400', '眉山市', '510000');
INSERT INTO `r_city` VALUES ('511500', '宜宾市', '510000');
INSERT INTO `r_city` VALUES ('511600', '广安市', '510000');
INSERT INTO `r_city` VALUES ('511700', '达州市', '510000');
INSERT INTO `r_city` VALUES ('511800', '雅安市', '510000');
INSERT INTO `r_city` VALUES ('511900', '巴中市', '510000');
INSERT INTO `r_city` VALUES ('512000', '资阳市', '510000');
INSERT INTO `r_city` VALUES ('513200', '阿坝藏族羌族自治州', '510000');
INSERT INTO `r_city` VALUES ('513300', '甘孜藏族自治州', '510000');
INSERT INTO `r_city` VALUES ('513400', '凉山彝族自治州', '510000');
INSERT INTO `r_city` VALUES ('520100', '贵阳市', '520000');
INSERT INTO `r_city` VALUES ('520200', '六盘水市', '520000');
INSERT INTO `r_city` VALUES ('520300', '遵义市', '520000');
INSERT INTO `r_city` VALUES ('520400', '安顺市', '520000');
INSERT INTO `r_city` VALUES ('522200', '铜仁地区', '520000');
INSERT INTO `r_city` VALUES ('522300', '黔西南布依族苗族自治州', '520000');
INSERT INTO `r_city` VALUES ('522400', '毕节地区', '520000');
INSERT INTO `r_city` VALUES ('522600', '黔东南苗族侗族自治州', '520000');
INSERT INTO `r_city` VALUES ('522700', '黔南布依族苗族自治州', '520000');
INSERT INTO `r_city` VALUES ('530100', '昆明市', '530000');
INSERT INTO `r_city` VALUES ('530300', '曲靖市', '530000');
INSERT INTO `r_city` VALUES ('530400', '玉溪市', '530000');
INSERT INTO `r_city` VALUES ('530500', '保山市', '530000');
INSERT INTO `r_city` VALUES ('530600', '昭通市', '530000');
INSERT INTO `r_city` VALUES ('530700', '丽江市', '530000');
INSERT INTO `r_city` VALUES ('530800', '思茅市', '530000');
INSERT INTO `r_city` VALUES ('530900', '临沧市', '530000');
INSERT INTO `r_city` VALUES ('532300', '楚雄彝族自治州', '530000');
INSERT INTO `r_city` VALUES ('532500', '红河哈尼族彝族自治州', '530000');
INSERT INTO `r_city` VALUES ('532600', '文山壮族苗族自治州', '530000');
INSERT INTO `r_city` VALUES ('532800', '西双版纳傣族自治州', '530000');
INSERT INTO `r_city` VALUES ('532900', '大理白族自治州', '530000');
INSERT INTO `r_city` VALUES ('533100', '德宏傣族景颇族自治州', '530000');
INSERT INTO `r_city` VALUES ('533300', '怒江傈僳族自治州', '530000');
INSERT INTO `r_city` VALUES ('533400', '迪庆藏族自治州', '530000');
INSERT INTO `r_city` VALUES ('540100', '拉萨市', '540000');
INSERT INTO `r_city` VALUES ('542100', '昌都地区', '540000');
INSERT INTO `r_city` VALUES ('542200', '山南地区', '540000');
INSERT INTO `r_city` VALUES ('542300', '日喀则地区', '540000');
INSERT INTO `r_city` VALUES ('542400', '那曲地区', '540000');
INSERT INTO `r_city` VALUES ('542500', '阿里地区', '540000');
INSERT INTO `r_city` VALUES ('542600', '林芝地区', '540000');
INSERT INTO `r_city` VALUES ('610100', '西安市', '610000');
INSERT INTO `r_city` VALUES ('610200', '铜川市', '610000');
INSERT INTO `r_city` VALUES ('610300', '宝鸡市', '610000');
INSERT INTO `r_city` VALUES ('610400', '咸阳市', '610000');
INSERT INTO `r_city` VALUES ('610500', '渭南市', '610000');
INSERT INTO `r_city` VALUES ('610600', '延安市', '610000');
INSERT INTO `r_city` VALUES ('610700', '汉中市', '610000');
INSERT INTO `r_city` VALUES ('610800', '榆林市', '610000');
INSERT INTO `r_city` VALUES ('610900', '安康市', '610000');
INSERT INTO `r_city` VALUES ('611000', '商洛市', '610000');
INSERT INTO `r_city` VALUES ('620100', '兰州市', '620000');
INSERT INTO `r_city` VALUES ('620200', '嘉峪关市', '620000');
INSERT INTO `r_city` VALUES ('620300', '金昌市', '620000');
INSERT INTO `r_city` VALUES ('620400', '白银市', '620000');
INSERT INTO `r_city` VALUES ('620500', '天水市', '620000');
INSERT INTO `r_city` VALUES ('620600', '武威市', '620000');
INSERT INTO `r_city` VALUES ('620700', '张掖市', '620000');
INSERT INTO `r_city` VALUES ('620800', '平凉市', '620000');
INSERT INTO `r_city` VALUES ('620900', '酒泉市', '620000');
INSERT INTO `r_city` VALUES ('621000', '庆阳市', '620000');
INSERT INTO `r_city` VALUES ('621100', '定西市', '620000');
INSERT INTO `r_city` VALUES ('621200', '陇南市', '620000');
INSERT INTO `r_city` VALUES ('622900', '临夏回族自治州', '620000');
INSERT INTO `r_city` VALUES ('623000', '甘南藏族自治州', '620000');
INSERT INTO `r_city` VALUES ('630100', '西宁市', '630000');
INSERT INTO `r_city` VALUES ('632100', '海东地区', '630000');
INSERT INTO `r_city` VALUES ('632200', '海北藏族自治州', '630000');
INSERT INTO `r_city` VALUES ('632300', '黄南藏族自治州', '630000');
INSERT INTO `r_city` VALUES ('632500', '海南藏族自治州', '630000');
INSERT INTO `r_city` VALUES ('632600', '果洛藏族自治州', '630000');
INSERT INTO `r_city` VALUES ('632700', '玉树藏族自治州', '630000');
INSERT INTO `r_city` VALUES ('632800', '海西蒙古族藏族自治州', '630000');
INSERT INTO `r_city` VALUES ('640100', '银川市', '640000');
INSERT INTO `r_city` VALUES ('640200', '石嘴山市', '640000');
INSERT INTO `r_city` VALUES ('640300', '吴忠市', '640000');
INSERT INTO `r_city` VALUES ('640400', '固原市', '640000');
INSERT INTO `r_city` VALUES ('640500', '中卫市', '640000');
INSERT INTO `r_city` VALUES ('650100', '乌鲁木齐市', '650000');
INSERT INTO `r_city` VALUES ('650200', '克拉玛依市', '650000');
INSERT INTO `r_city` VALUES ('652100', '吐鲁番地区', '650000');
INSERT INTO `r_city` VALUES ('652200', '哈密地区', '650000');
INSERT INTO `r_city` VALUES ('652300', '昌吉回族自治州', '650000');
INSERT INTO `r_city` VALUES ('652700', '博尔塔拉蒙古自治州', '650000');
INSERT INTO `r_city` VALUES ('652800', '巴音郭楞蒙古自治州', '650000');
INSERT INTO `r_city` VALUES ('652900', '阿克苏地区', '650000');
INSERT INTO `r_city` VALUES ('653000', '克孜勒苏柯尔克孜自治州', '650000');
INSERT INTO `r_city` VALUES ('653100', '喀什地区', '650000');
INSERT INTO `r_city` VALUES ('653200', '和田地区', '650000');
INSERT INTO `r_city` VALUES ('654000', '伊犁哈萨克自治州', '650000');
INSERT INTO `r_city` VALUES ('654200', '塔城地区', '650000');
INSERT INTO `r_city` VALUES ('654300', '阿勒泰地区', '650000');
INSERT INTO `r_city` VALUES ('659000', '省直辖行政单位', '650000');

-- ----------------------------
-- Table structure for r_county
-- ----------------------------
DROP TABLE IF EXISTS `r_county`;
CREATE TABLE `r_county` (
  `county_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `county_name` varchar(64) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`county_id`)
) ENGINE=InnoDB AUTO_INCREMENT=659005 DEFAULT CHARSET=utf8 COMMENT='区县';

-- ----------------------------
-- Records of r_county
-- ----------------------------
INSERT INTO `r_county` VALUES ('110101', '东城区', '110100');
INSERT INTO `r_county` VALUES ('110102', '西城区', '110100');
INSERT INTO `r_county` VALUES ('110103', '崇文区', '110100');
INSERT INTO `r_county` VALUES ('110104', '宣武区', '110100');
INSERT INTO `r_county` VALUES ('110105', '朝阳区', '110100');
INSERT INTO `r_county` VALUES ('110106', '丰台区', '110100');
INSERT INTO `r_county` VALUES ('110107', '石景山区', '110100');
INSERT INTO `r_county` VALUES ('110108', '海淀区', '110100');
INSERT INTO `r_county` VALUES ('110109', '门头沟区', '110100');
INSERT INTO `r_county` VALUES ('110111', '房山区', '110100');
INSERT INTO `r_county` VALUES ('110112', '通州区', '110100');
INSERT INTO `r_county` VALUES ('110113', '顺义区', '110100');
INSERT INTO `r_county` VALUES ('110114', '昌平区', '110100');
INSERT INTO `r_county` VALUES ('110115', '大兴区', '110100');
INSERT INTO `r_county` VALUES ('110116', '怀柔区', '110100');
INSERT INTO `r_county` VALUES ('110117', '平谷区', '110100');
INSERT INTO `r_county` VALUES ('110228', '密云县', '110200');
INSERT INTO `r_county` VALUES ('110229', '延庆县', '110200');
INSERT INTO `r_county` VALUES ('120101', '和平区', '120100');
INSERT INTO `r_county` VALUES ('120102', '河东区', '120100');
INSERT INTO `r_county` VALUES ('120103', '河西区', '120100');
INSERT INTO `r_county` VALUES ('120104', '南开区', '120100');
INSERT INTO `r_county` VALUES ('120105', '河北区', '120100');
INSERT INTO `r_county` VALUES ('120106', '红桥区', '120100');
INSERT INTO `r_county` VALUES ('120107', '塘沽区', '120100');
INSERT INTO `r_county` VALUES ('120108', '汉沽区', '120100');
INSERT INTO `r_county` VALUES ('120109', '大港区', '120100');
INSERT INTO `r_county` VALUES ('120110', '东丽区', '120100');
INSERT INTO `r_county` VALUES ('120111', '西青区', '120100');
INSERT INTO `r_county` VALUES ('120112', '津南区', '120100');
INSERT INTO `r_county` VALUES ('120113', '北辰区', '120100');
INSERT INTO `r_county` VALUES ('120114', '武清区', '120100');
INSERT INTO `r_county` VALUES ('120115', '宝坻区', '120100');
INSERT INTO `r_county` VALUES ('120221', '宁河县', '120200');
INSERT INTO `r_county` VALUES ('120223', '静海县', '120200');
INSERT INTO `r_county` VALUES ('120225', '蓟　县', '120200');
INSERT INTO `r_county` VALUES ('130101', '市辖区', '130100');
INSERT INTO `r_county` VALUES ('130102', '长安区', '130100');
INSERT INTO `r_county` VALUES ('130103', '桥东区', '130100');
INSERT INTO `r_county` VALUES ('130104', '桥西区', '130100');
INSERT INTO `r_county` VALUES ('130105', '新华区', '130100');
INSERT INTO `r_county` VALUES ('130107', '井陉矿区', '130100');
INSERT INTO `r_county` VALUES ('130108', '裕华区', '130100');
INSERT INTO `r_county` VALUES ('130121', '井陉县', '130100');
INSERT INTO `r_county` VALUES ('130123', '正定县', '130100');
INSERT INTO `r_county` VALUES ('130124', '栾城县', '130100');
INSERT INTO `r_county` VALUES ('130125', '行唐县', '130100');
INSERT INTO `r_county` VALUES ('130126', '灵寿县', '130100');
INSERT INTO `r_county` VALUES ('130127', '高邑县', '130100');
INSERT INTO `r_county` VALUES ('130128', '深泽县', '130100');
INSERT INTO `r_county` VALUES ('130129', '赞皇县', '130100');
INSERT INTO `r_county` VALUES ('130130', '无极县', '130100');
INSERT INTO `r_county` VALUES ('130131', '平山县', '130100');
INSERT INTO `r_county` VALUES ('130132', '元氏县', '130100');
INSERT INTO `r_county` VALUES ('130133', '赵　县', '130100');
INSERT INTO `r_county` VALUES ('130181', '辛集市', '130100');
INSERT INTO `r_county` VALUES ('130182', '藁城市', '130100');
INSERT INTO `r_county` VALUES ('130183', '晋州市', '130100');
INSERT INTO `r_county` VALUES ('130184', '新乐市', '130100');
INSERT INTO `r_county` VALUES ('130185', '鹿泉市', '130100');
INSERT INTO `r_county` VALUES ('130201', '市辖区', '130200');
INSERT INTO `r_county` VALUES ('130202', '路南区', '130200');
INSERT INTO `r_county` VALUES ('130203', '路北区', '130200');
INSERT INTO `r_county` VALUES ('130204', '古冶区', '130200');
INSERT INTO `r_county` VALUES ('130205', '开平区', '130200');
INSERT INTO `r_county` VALUES ('130207', '丰南区', '130200');
INSERT INTO `r_county` VALUES ('130208', '丰润区', '130200');
INSERT INTO `r_county` VALUES ('130223', '滦　县', '130200');
INSERT INTO `r_county` VALUES ('130224', '滦南县', '130200');
INSERT INTO `r_county` VALUES ('130225', '乐亭县', '130200');
INSERT INTO `r_county` VALUES ('130227', '迁西县', '130200');
INSERT INTO `r_county` VALUES ('130229', '玉田县', '130200');
INSERT INTO `r_county` VALUES ('130230', '唐海县', '130200');
INSERT INTO `r_county` VALUES ('130281', '遵化市', '130200');
INSERT INTO `r_county` VALUES ('130283', '迁安市', '130200');
INSERT INTO `r_county` VALUES ('130301', '市辖区', '130300');
INSERT INTO `r_county` VALUES ('130302', '海港区', '130300');
INSERT INTO `r_county` VALUES ('130303', '山海关区', '130300');
INSERT INTO `r_county` VALUES ('130304', '北戴河区', '130300');
INSERT INTO `r_county` VALUES ('130321', '青龙满族自治县', '130300');
INSERT INTO `r_county` VALUES ('130322', '昌黎县', '130300');
INSERT INTO `r_county` VALUES ('130323', '抚宁县', '130300');
INSERT INTO `r_county` VALUES ('130324', '卢龙县', '130300');
INSERT INTO `r_county` VALUES ('130401', '市辖区', '130400');
INSERT INTO `r_county` VALUES ('130402', '邯山区', '130400');
INSERT INTO `r_county` VALUES ('130403', '丛台区', '130400');
INSERT INTO `r_county` VALUES ('130404', '复兴区', '130400');
INSERT INTO `r_county` VALUES ('130406', '峰峰矿区', '130400');
INSERT INTO `r_county` VALUES ('130421', '邯郸县', '130400');
INSERT INTO `r_county` VALUES ('130423', '临漳县', '130400');
INSERT INTO `r_county` VALUES ('130424', '成安县', '130400');
INSERT INTO `r_county` VALUES ('130425', '大名县', '130400');
INSERT INTO `r_county` VALUES ('130426', '涉　县', '130400');
INSERT INTO `r_county` VALUES ('130427', '磁　县', '130400');
INSERT INTO `r_county` VALUES ('130428', '肥乡县', '130400');
INSERT INTO `r_county` VALUES ('130429', '永年县', '130400');
INSERT INTO `r_county` VALUES ('130430', '邱　县', '130400');
INSERT INTO `r_county` VALUES ('130431', '鸡泽县', '130400');
INSERT INTO `r_county` VALUES ('130432', '广平县', '130400');
INSERT INTO `r_county` VALUES ('130433', '馆陶县', '130400');
INSERT INTO `r_county` VALUES ('130434', '魏　县', '130400');
INSERT INTO `r_county` VALUES ('130435', '曲周县', '130400');
INSERT INTO `r_county` VALUES ('130481', '武安市', '130400');
INSERT INTO `r_county` VALUES ('130501', '市辖区', '130500');
INSERT INTO `r_county` VALUES ('130502', '桥东区', '130500');
INSERT INTO `r_county` VALUES ('130503', '桥西区', '130500');
INSERT INTO `r_county` VALUES ('130521', '邢台县', '130500');
INSERT INTO `r_county` VALUES ('130522', '临城县', '130500');
INSERT INTO `r_county` VALUES ('130523', '内丘县', '130500');
INSERT INTO `r_county` VALUES ('130524', '柏乡县', '130500');
INSERT INTO `r_county` VALUES ('130525', '隆尧县', '130500');
INSERT INTO `r_county` VALUES ('130526', '任　县', '130500');
INSERT INTO `r_county` VALUES ('130527', '南和县', '130500');
INSERT INTO `r_county` VALUES ('130528', '宁晋县', '130500');
INSERT INTO `r_county` VALUES ('130529', '巨鹿县', '130500');
INSERT INTO `r_county` VALUES ('130530', '新河县', '130500');
INSERT INTO `r_county` VALUES ('130531', '广宗县', '130500');
INSERT INTO `r_county` VALUES ('130532', '平乡县', '130500');
INSERT INTO `r_county` VALUES ('130533', '威　县', '130500');
INSERT INTO `r_county` VALUES ('130534', '清河县', '130500');
INSERT INTO `r_county` VALUES ('130535', '临西县', '130500');
INSERT INTO `r_county` VALUES ('130581', '南宫市', '130500');
INSERT INTO `r_county` VALUES ('130582', '沙河市', '130500');
INSERT INTO `r_county` VALUES ('130601', '市辖区', '130600');
INSERT INTO `r_county` VALUES ('130602', '新市区', '130600');
INSERT INTO `r_county` VALUES ('130603', '北市区', '130600');
INSERT INTO `r_county` VALUES ('130604', '南市区', '130600');
INSERT INTO `r_county` VALUES ('130621', '满城县', '130600');
INSERT INTO `r_county` VALUES ('130622', '清苑县', '130600');
INSERT INTO `r_county` VALUES ('130623', '涞水县', '130600');
INSERT INTO `r_county` VALUES ('130624', '阜平县', '130600');
INSERT INTO `r_county` VALUES ('130625', '徐水县', '130600');
INSERT INTO `r_county` VALUES ('130626', '定兴县', '130600');
INSERT INTO `r_county` VALUES ('130627', '唐　县', '130600');
INSERT INTO `r_county` VALUES ('130628', '高阳县', '130600');
INSERT INTO `r_county` VALUES ('130629', '容城县', '130600');
INSERT INTO `r_county` VALUES ('130630', '涞源县', '130600');
INSERT INTO `r_county` VALUES ('130631', '望都县', '130600');
INSERT INTO `r_county` VALUES ('130632', '安新县', '130600');
INSERT INTO `r_county` VALUES ('130633', '易　县', '130600');
INSERT INTO `r_county` VALUES ('130634', '曲阳县', '130600');
INSERT INTO `r_county` VALUES ('130635', '蠡　县', '130600');
INSERT INTO `r_county` VALUES ('130636', '顺平县', '130600');
INSERT INTO `r_county` VALUES ('130637', '博野县', '130600');
INSERT INTO `r_county` VALUES ('130638', '雄　县', '130600');
INSERT INTO `r_county` VALUES ('130681', '涿州市', '130600');
INSERT INTO `r_county` VALUES ('130682', '定州市', '130600');
INSERT INTO `r_county` VALUES ('130683', '安国市', '130600');
INSERT INTO `r_county` VALUES ('130684', '高碑店市', '130600');
INSERT INTO `r_county` VALUES ('130701', '市辖区', '130700');
INSERT INTO `r_county` VALUES ('130702', '桥东区', '130700');
INSERT INTO `r_county` VALUES ('130703', '桥西区', '130700');
INSERT INTO `r_county` VALUES ('130705', '宣化区', '130700');
INSERT INTO `r_county` VALUES ('130706', '下花园区', '130700');
INSERT INTO `r_county` VALUES ('130721', '宣化县', '130700');
INSERT INTO `r_county` VALUES ('130722', '张北县', '130700');
INSERT INTO `r_county` VALUES ('130723', '康保县', '130700');
INSERT INTO `r_county` VALUES ('130724', '沽源县', '130700');
INSERT INTO `r_county` VALUES ('130725', '尚义县', '130700');
INSERT INTO `r_county` VALUES ('130726', '蔚　县', '130700');
INSERT INTO `r_county` VALUES ('130727', '阳原县', '130700');
INSERT INTO `r_county` VALUES ('130728', '怀安县', '130700');
INSERT INTO `r_county` VALUES ('130729', '万全县', '130700');
INSERT INTO `r_county` VALUES ('130730', '怀来县', '130700');
INSERT INTO `r_county` VALUES ('130731', '涿鹿县', '130700');
INSERT INTO `r_county` VALUES ('130732', '赤城县', '130700');
INSERT INTO `r_county` VALUES ('130733', '崇礼县', '130700');
INSERT INTO `r_county` VALUES ('130801', '市辖区', '130800');
INSERT INTO `r_county` VALUES ('130802', '双桥区', '130800');
INSERT INTO `r_county` VALUES ('130803', '双滦区', '130800');
INSERT INTO `r_county` VALUES ('130804', '鹰手营子矿区', '130800');
INSERT INTO `r_county` VALUES ('130821', '承德县', '130800');
INSERT INTO `r_county` VALUES ('130822', '兴隆县', '130800');
INSERT INTO `r_county` VALUES ('130823', '平泉县', '130800');
INSERT INTO `r_county` VALUES ('130824', '滦平县', '130800');
INSERT INTO `r_county` VALUES ('130825', '隆化县', '130800');
INSERT INTO `r_county` VALUES ('130826', '丰宁满族自治县', '130800');
INSERT INTO `r_county` VALUES ('130827', '宽城满族自治县', '130800');
INSERT INTO `r_county` VALUES ('130828', '围场满族蒙古族自治县', '130800');
INSERT INTO `r_county` VALUES ('130901', '市辖区', '130900');
INSERT INTO `r_county` VALUES ('130902', '新华区', '130900');
INSERT INTO `r_county` VALUES ('130903', '运河区', '130900');
INSERT INTO `r_county` VALUES ('130921', '沧　县', '130900');
INSERT INTO `r_county` VALUES ('130922', '青　县', '130900');
INSERT INTO `r_county` VALUES ('130923', '东光县', '130900');
INSERT INTO `r_county` VALUES ('130924', '海兴县', '130900');
INSERT INTO `r_county` VALUES ('130925', '盐山县', '130900');
INSERT INTO `r_county` VALUES ('130926', '肃宁县', '130900');
INSERT INTO `r_county` VALUES ('130927', '南皮县', '130900');
INSERT INTO `r_county` VALUES ('130928', '吴桥县', '130900');
INSERT INTO `r_county` VALUES ('130929', '献　县', '130900');
INSERT INTO `r_county` VALUES ('130930', '孟村回族自治县', '130900');
INSERT INTO `r_county` VALUES ('130981', '泊头市', '130900');
INSERT INTO `r_county` VALUES ('130982', '任丘市', '130900');
INSERT INTO `r_county` VALUES ('130983', '黄骅市', '130900');
INSERT INTO `r_county` VALUES ('130984', '河间市', '130900');
INSERT INTO `r_county` VALUES ('131001', '市辖区', '131000');
INSERT INTO `r_county` VALUES ('131002', '安次区', '131000');
INSERT INTO `r_county` VALUES ('131003', '广阳区', '131000');
INSERT INTO `r_county` VALUES ('131022', '固安县', '131000');
INSERT INTO `r_county` VALUES ('131023', '永清县', '131000');
INSERT INTO `r_county` VALUES ('131024', '香河县', '131000');
INSERT INTO `r_county` VALUES ('131025', '大城县', '131000');
INSERT INTO `r_county` VALUES ('131026', '文安县', '131000');
INSERT INTO `r_county` VALUES ('131028', '大厂回族自治县', '131000');
INSERT INTO `r_county` VALUES ('131081', '霸州市', '131000');
INSERT INTO `r_county` VALUES ('131082', '三河市', '131000');
INSERT INTO `r_county` VALUES ('131101', '市辖区', '131100');
INSERT INTO `r_county` VALUES ('131102', '桃城区', '131100');
INSERT INTO `r_county` VALUES ('131121', '枣强县', '131100');
INSERT INTO `r_county` VALUES ('131122', '武邑县', '131100');
INSERT INTO `r_county` VALUES ('131123', '武强县', '131100');
INSERT INTO `r_county` VALUES ('131124', '饶阳县', '131100');
INSERT INTO `r_county` VALUES ('131125', '安平县', '131100');
INSERT INTO `r_county` VALUES ('131126', '故城县', '131100');
INSERT INTO `r_county` VALUES ('131127', '景　县', '131100');
INSERT INTO `r_county` VALUES ('131128', '阜城县', '131100');
INSERT INTO `r_county` VALUES ('131181', '冀州市', '131100');
INSERT INTO `r_county` VALUES ('131182', '深州市', '131100');
INSERT INTO `r_county` VALUES ('140101', '市辖区', '140100');
INSERT INTO `r_county` VALUES ('140105', '小店区', '140100');
INSERT INTO `r_county` VALUES ('140106', '迎泽区', '140100');
INSERT INTO `r_county` VALUES ('140107', '杏花岭区', '140100');
INSERT INTO `r_county` VALUES ('140108', '尖草坪区', '140100');
INSERT INTO `r_county` VALUES ('140109', '万柏林区', '140100');
INSERT INTO `r_county` VALUES ('140110', '晋源区', '140100');
INSERT INTO `r_county` VALUES ('140121', '清徐县', '140100');
INSERT INTO `r_county` VALUES ('140122', '阳曲县', '140100');
INSERT INTO `r_county` VALUES ('140123', '娄烦县', '140100');
INSERT INTO `r_county` VALUES ('140181', '古交市', '140100');
INSERT INTO `r_county` VALUES ('140201', '市辖区', '140200');
INSERT INTO `r_county` VALUES ('140202', '城　区', '140200');
INSERT INTO `r_county` VALUES ('140203', '矿　区', '140200');
INSERT INTO `r_county` VALUES ('140211', '南郊区', '140200');
INSERT INTO `r_county` VALUES ('140212', '新荣区', '140200');
INSERT INTO `r_county` VALUES ('140221', '阳高县', '140200');
INSERT INTO `r_county` VALUES ('140222', '天镇县', '140200');
INSERT INTO `r_county` VALUES ('140223', '广灵县', '140200');
INSERT INTO `r_county` VALUES ('140224', '灵丘县', '140200');
INSERT INTO `r_county` VALUES ('140225', '浑源县', '140200');
INSERT INTO `r_county` VALUES ('140226', '左云县', '140200');
INSERT INTO `r_county` VALUES ('140227', '大同县', '140200');
INSERT INTO `r_county` VALUES ('140301', '市辖区', '140300');
INSERT INTO `r_county` VALUES ('140302', '城　区', '140300');
INSERT INTO `r_county` VALUES ('140303', '矿　区', '140300');
INSERT INTO `r_county` VALUES ('140311', '郊　区', '140300');
INSERT INTO `r_county` VALUES ('140321', '平定县', '140300');
INSERT INTO `r_county` VALUES ('140322', '盂　县', '140300');
INSERT INTO `r_county` VALUES ('140401', '市辖区', '140400');
INSERT INTO `r_county` VALUES ('140402', '城　区', '140400');
INSERT INTO `r_county` VALUES ('140411', '郊　区', '140400');
INSERT INTO `r_county` VALUES ('140421', '长治县', '140400');
INSERT INTO `r_county` VALUES ('140423', '襄垣县', '140400');
INSERT INTO `r_county` VALUES ('140424', '屯留县', '140400');
INSERT INTO `r_county` VALUES ('140425', '平顺县', '140400');
INSERT INTO `r_county` VALUES ('140426', '黎城县', '140400');
INSERT INTO `r_county` VALUES ('140427', '壶关县', '140400');
INSERT INTO `r_county` VALUES ('140428', '长子县', '140400');
INSERT INTO `r_county` VALUES ('140429', '武乡县', '140400');
INSERT INTO `r_county` VALUES ('140430', '沁　县', '140400');
INSERT INTO `r_county` VALUES ('140431', '沁源县', '140400');
INSERT INTO `r_county` VALUES ('140481', '潞城市', '140400');
INSERT INTO `r_county` VALUES ('140501', '市辖区', '140500');
INSERT INTO `r_county` VALUES ('140502', '城　区', '140500');
INSERT INTO `r_county` VALUES ('140521', '沁水县', '140500');
INSERT INTO `r_county` VALUES ('140522', '阳城县', '140500');
INSERT INTO `r_county` VALUES ('140524', '陵川县', '140500');
INSERT INTO `r_county` VALUES ('140525', '泽州县', '140500');
INSERT INTO `r_county` VALUES ('140581', '高平市', '140500');
INSERT INTO `r_county` VALUES ('140601', '市辖区', '140600');
INSERT INTO `r_county` VALUES ('140602', '朔城区', '140600');
INSERT INTO `r_county` VALUES ('140603', '平鲁区', '140600');
INSERT INTO `r_county` VALUES ('140621', '山阴县', '140600');
INSERT INTO `r_county` VALUES ('140622', '应　县', '140600');
INSERT INTO `r_county` VALUES ('140623', '右玉县', '140600');
INSERT INTO `r_county` VALUES ('140624', '怀仁县', '140600');
INSERT INTO `r_county` VALUES ('140701', '市辖区', '140700');
INSERT INTO `r_county` VALUES ('140702', '榆次区', '140700');
INSERT INTO `r_county` VALUES ('140721', '榆社县', '140700');
INSERT INTO `r_county` VALUES ('140722', '左权县', '140700');
INSERT INTO `r_county` VALUES ('140723', '和顺县', '140700');
INSERT INTO `r_county` VALUES ('140724', '昔阳县', '140700');
INSERT INTO `r_county` VALUES ('140725', '寿阳县', '140700');
INSERT INTO `r_county` VALUES ('140726', '太谷县', '140700');
INSERT INTO `r_county` VALUES ('140727', '祁　县', '140700');
INSERT INTO `r_county` VALUES ('140728', '平遥县', '140700');
INSERT INTO `r_county` VALUES ('140729', '灵石县', '140700');
INSERT INTO `r_county` VALUES ('140781', '介休市', '140700');
INSERT INTO `r_county` VALUES ('140801', '市辖区', '140800');
INSERT INTO `r_county` VALUES ('140802', '盐湖区', '140800');
INSERT INTO `r_county` VALUES ('140821', '临猗县', '140800');
INSERT INTO `r_county` VALUES ('140822', '万荣县', '140800');
INSERT INTO `r_county` VALUES ('140823', '闻喜县', '140800');
INSERT INTO `r_county` VALUES ('140824', '稷山县', '140800');
INSERT INTO `r_county` VALUES ('140825', '新绛县', '140800');
INSERT INTO `r_county` VALUES ('140826', '绛　县', '140800');
INSERT INTO `r_county` VALUES ('140827', '垣曲县', '140800');
INSERT INTO `r_county` VALUES ('140828', '夏　县', '140800');
INSERT INTO `r_county` VALUES ('140829', '平陆县', '140800');
INSERT INTO `r_county` VALUES ('140830', '芮城县', '140800');
INSERT INTO `r_county` VALUES ('140881', '永济市', '140800');
INSERT INTO `r_county` VALUES ('140882', '河津市', '140800');
INSERT INTO `r_county` VALUES ('140901', '市辖区', '140900');
INSERT INTO `r_county` VALUES ('140902', '忻府区', '140900');
INSERT INTO `r_county` VALUES ('140921', '定襄县', '140900');
INSERT INTO `r_county` VALUES ('140922', '五台县', '140900');
INSERT INTO `r_county` VALUES ('140923', '代　县', '140900');
INSERT INTO `r_county` VALUES ('140924', '繁峙县', '140900');
INSERT INTO `r_county` VALUES ('140925', '宁武县', '140900');
INSERT INTO `r_county` VALUES ('140926', '静乐县', '140900');
INSERT INTO `r_county` VALUES ('140927', '神池县', '140900');
INSERT INTO `r_county` VALUES ('140928', '五寨县', '140900');
INSERT INTO `r_county` VALUES ('140929', '岢岚县', '140900');
INSERT INTO `r_county` VALUES ('140930', '河曲县', '140900');
INSERT INTO `r_county` VALUES ('140931', '保德县', '140900');
INSERT INTO `r_county` VALUES ('140932', '偏关县', '140900');
INSERT INTO `r_county` VALUES ('140981', '原平市', '140900');
INSERT INTO `r_county` VALUES ('141001', '市辖区', '141000');
INSERT INTO `r_county` VALUES ('141002', '尧都区', '141000');
INSERT INTO `r_county` VALUES ('141021', '曲沃县', '141000');
INSERT INTO `r_county` VALUES ('141022', '翼城县', '141000');
INSERT INTO `r_county` VALUES ('141023', '襄汾县', '141000');
INSERT INTO `r_county` VALUES ('141024', '洪洞县', '141000');
INSERT INTO `r_county` VALUES ('141025', '古　县', '141000');
INSERT INTO `r_county` VALUES ('141026', '安泽县', '141000');
INSERT INTO `r_county` VALUES ('141027', '浮山县', '141000');
INSERT INTO `r_county` VALUES ('141028', '吉　县', '141000');
INSERT INTO `r_county` VALUES ('141029', '乡宁县', '141000');
INSERT INTO `r_county` VALUES ('141030', '大宁县', '141000');
INSERT INTO `r_county` VALUES ('141031', '隰　县', '141000');
INSERT INTO `r_county` VALUES ('141032', '永和县', '141000');
INSERT INTO `r_county` VALUES ('141033', '蒲　县', '141000');
INSERT INTO `r_county` VALUES ('141034', '汾西县', '141000');
INSERT INTO `r_county` VALUES ('141081', '侯马市', '141000');
INSERT INTO `r_county` VALUES ('141082', '霍州市', '141000');
INSERT INTO `r_county` VALUES ('141101', '市辖区', '141100');
INSERT INTO `r_county` VALUES ('141102', '离石区', '141100');
INSERT INTO `r_county` VALUES ('141121', '文水县', '141100');
INSERT INTO `r_county` VALUES ('141122', '交城县', '141100');
INSERT INTO `r_county` VALUES ('141123', '兴　县', '141100');
INSERT INTO `r_county` VALUES ('141124', '临　县', '141100');
INSERT INTO `r_county` VALUES ('141125', '柳林县', '141100');
INSERT INTO `r_county` VALUES ('141126', '石楼县', '141100');
INSERT INTO `r_county` VALUES ('141127', '岚　县', '141100');
INSERT INTO `r_county` VALUES ('141128', '方山县', '141100');
INSERT INTO `r_county` VALUES ('141129', '中阳县', '141100');
INSERT INTO `r_county` VALUES ('141130', '交口县', '141100');
INSERT INTO `r_county` VALUES ('141181', '孝义市', '141100');
INSERT INTO `r_county` VALUES ('141182', '汾阳市', '141100');
INSERT INTO `r_county` VALUES ('150101', '市辖区', '150100');
INSERT INTO `r_county` VALUES ('150102', '新城区', '150100');
INSERT INTO `r_county` VALUES ('150103', '回民区', '150100');
INSERT INTO `r_county` VALUES ('150104', '玉泉区', '150100');
INSERT INTO `r_county` VALUES ('150105', '赛罕区', '150100');
INSERT INTO `r_county` VALUES ('150121', '土默特左旗', '150100');
INSERT INTO `r_county` VALUES ('150122', '托克托县', '150100');
INSERT INTO `r_county` VALUES ('150123', '和林格尔县', '150100');
INSERT INTO `r_county` VALUES ('150124', '清水河县', '150100');
INSERT INTO `r_county` VALUES ('150125', '武川县', '150100');
INSERT INTO `r_county` VALUES ('150201', '市辖区', '150200');
INSERT INTO `r_county` VALUES ('150202', '东河区', '150200');
INSERT INTO `r_county` VALUES ('150203', '昆都仑区', '150200');
INSERT INTO `r_county` VALUES ('150204', '青山区', '150200');
INSERT INTO `r_county` VALUES ('150205', '石拐区', '150200');
INSERT INTO `r_county` VALUES ('150206', '白云矿区', '150200');
INSERT INTO `r_county` VALUES ('150207', '九原区', '150200');
INSERT INTO `r_county` VALUES ('150221', '土默特右旗', '150200');
INSERT INTO `r_county` VALUES ('150222', '固阳县', '150200');
INSERT INTO `r_county` VALUES ('150223', '达尔罕茂明安联合旗', '150200');
INSERT INTO `r_county` VALUES ('150301', '市辖区', '150300');
INSERT INTO `r_county` VALUES ('150302', '海勃湾区', '150300');
INSERT INTO `r_county` VALUES ('150303', '海南区', '150300');
INSERT INTO `r_county` VALUES ('150304', '乌达区', '150300');
INSERT INTO `r_county` VALUES ('150401', '市辖区', '150400');
INSERT INTO `r_county` VALUES ('150402', '红山区', '150400');
INSERT INTO `r_county` VALUES ('150403', '元宝山区', '150400');
INSERT INTO `r_county` VALUES ('150404', '松山区', '150400');
INSERT INTO `r_county` VALUES ('150421', '阿鲁科尔沁旗', '150400');
INSERT INTO `r_county` VALUES ('150422', '巴林左旗', '150400');
INSERT INTO `r_county` VALUES ('150423', '巴林右旗', '150400');
INSERT INTO `r_county` VALUES ('150424', '林西县', '150400');
INSERT INTO `r_county` VALUES ('150425', '克什克腾旗', '150400');
INSERT INTO `r_county` VALUES ('150426', '翁牛特旗', '150400');
INSERT INTO `r_county` VALUES ('150428', '喀喇沁旗', '150400');
INSERT INTO `r_county` VALUES ('150429', '宁城县', '150400');
INSERT INTO `r_county` VALUES ('150430', '敖汉旗', '150400');
INSERT INTO `r_county` VALUES ('150501', '市辖区', '150500');
INSERT INTO `r_county` VALUES ('150502', '科尔沁区', '150500');
INSERT INTO `r_county` VALUES ('150521', '科尔沁左翼中旗', '150500');
INSERT INTO `r_county` VALUES ('150522', '科尔沁左翼后旗', '150500');
INSERT INTO `r_county` VALUES ('150523', '开鲁县', '150500');
INSERT INTO `r_county` VALUES ('150524', '库伦旗', '150500');
INSERT INTO `r_county` VALUES ('150525', '奈曼旗', '150500');
INSERT INTO `r_county` VALUES ('150526', '扎鲁特旗', '150500');
INSERT INTO `r_county` VALUES ('150581', '霍林郭勒市', '150500');
INSERT INTO `r_county` VALUES ('150602', '东胜区', '150600');
INSERT INTO `r_county` VALUES ('150621', '达拉特旗', '150600');
INSERT INTO `r_county` VALUES ('150622', '准格尔旗', '150600');
INSERT INTO `r_county` VALUES ('150623', '鄂托克前旗', '150600');
INSERT INTO `r_county` VALUES ('150624', '鄂托克旗', '150600');
INSERT INTO `r_county` VALUES ('150625', '杭锦旗', '150600');
INSERT INTO `r_county` VALUES ('150626', '乌审旗', '150600');
INSERT INTO `r_county` VALUES ('150627', '伊金霍洛旗', '150600');
INSERT INTO `r_county` VALUES ('150701', '市辖区', '150700');
INSERT INTO `r_county` VALUES ('150702', '海拉尔区', '150700');
INSERT INTO `r_county` VALUES ('150721', '阿荣旗', '150700');
INSERT INTO `r_county` VALUES ('150722', '莫力达瓦达斡尔族自治旗', '150700');
INSERT INTO `r_county` VALUES ('150723', '鄂伦春自治旗', '150700');
INSERT INTO `r_county` VALUES ('150724', '鄂温克族自治旗', '150700');
INSERT INTO `r_county` VALUES ('150725', '陈巴尔虎旗', '150700');
INSERT INTO `r_county` VALUES ('150726', '新巴尔虎左旗', '150700');
INSERT INTO `r_county` VALUES ('150727', '新巴尔虎右旗', '150700');
INSERT INTO `r_county` VALUES ('150781', '满洲里市', '150700');
INSERT INTO `r_county` VALUES ('150782', '牙克石市', '150700');
INSERT INTO `r_county` VALUES ('150783', '扎兰屯市', '150700');
INSERT INTO `r_county` VALUES ('150784', '额尔古纳市', '150700');
INSERT INTO `r_county` VALUES ('150785', '根河市', '150700');
INSERT INTO `r_county` VALUES ('150801', '市辖区', '150800');
INSERT INTO `r_county` VALUES ('150802', '临河区', '150800');
INSERT INTO `r_county` VALUES ('150821', '五原县', '150800');
INSERT INTO `r_county` VALUES ('150822', '磴口县', '150800');
INSERT INTO `r_county` VALUES ('150823', '乌拉特前旗', '150800');
INSERT INTO `r_county` VALUES ('150824', '乌拉特中旗', '150800');
INSERT INTO `r_county` VALUES ('150825', '乌拉特后旗', '150800');
INSERT INTO `r_county` VALUES ('150826', '杭锦后旗', '150800');
INSERT INTO `r_county` VALUES ('150901', '市辖区', '150900');
INSERT INTO `r_county` VALUES ('150902', '集宁区', '150900');
INSERT INTO `r_county` VALUES ('150921', '卓资县', '150900');
INSERT INTO `r_county` VALUES ('150922', '化德县', '150900');
INSERT INTO `r_county` VALUES ('150923', '商都县', '150900');
INSERT INTO `r_county` VALUES ('150924', '兴和县', '150900');
INSERT INTO `r_county` VALUES ('150925', '凉城县', '150900');
INSERT INTO `r_county` VALUES ('150926', '察哈尔右翼前旗', '150900');
INSERT INTO `r_county` VALUES ('150927', '察哈尔右翼中旗', '150900');
INSERT INTO `r_county` VALUES ('150928', '察哈尔右翼后旗', '150900');
INSERT INTO `r_county` VALUES ('150929', '四子王旗', '150900');
INSERT INTO `r_county` VALUES ('150981', '丰镇市', '150900');
INSERT INTO `r_county` VALUES ('152201', '乌兰浩特市', '152200');
INSERT INTO `r_county` VALUES ('152202', '阿尔山市', '152200');
INSERT INTO `r_county` VALUES ('152221', '科尔沁右翼前旗', '152200');
INSERT INTO `r_county` VALUES ('152222', '科尔沁右翼中旗', '152200');
INSERT INTO `r_county` VALUES ('152223', '扎赉特旗', '152200');
INSERT INTO `r_county` VALUES ('152224', '突泉县', '152200');
INSERT INTO `r_county` VALUES ('152501', '二连浩特市', '152500');
INSERT INTO `r_county` VALUES ('152502', '锡林浩特市', '152500');
INSERT INTO `r_county` VALUES ('152522', '阿巴嘎旗', '152500');
INSERT INTO `r_county` VALUES ('152523', '苏尼特左旗', '152500');
INSERT INTO `r_county` VALUES ('152524', '苏尼特右旗', '152500');
INSERT INTO `r_county` VALUES ('152525', '东乌珠穆沁旗', '152500');
INSERT INTO `r_county` VALUES ('152526', '西乌珠穆沁旗', '152500');
INSERT INTO `r_county` VALUES ('152527', '太仆寺旗', '152500');
INSERT INTO `r_county` VALUES ('152528', '镶黄旗', '152500');
INSERT INTO `r_county` VALUES ('152529', '正镶白旗', '152500');
INSERT INTO `r_county` VALUES ('152530', '正蓝旗', '152500');
INSERT INTO `r_county` VALUES ('152531', '多伦县', '152500');
INSERT INTO `r_county` VALUES ('152921', '阿拉善左旗', '152900');
INSERT INTO `r_county` VALUES ('152922', '阿拉善右旗', '152900');
INSERT INTO `r_county` VALUES ('152923', '额济纳旗', '152900');
INSERT INTO `r_county` VALUES ('210101', '市辖区', '210100');
INSERT INTO `r_county` VALUES ('210102', '和平区', '210100');
INSERT INTO `r_county` VALUES ('210103', '沈河区', '210100');
INSERT INTO `r_county` VALUES ('210104', '大东区', '210100');
INSERT INTO `r_county` VALUES ('210105', '皇姑区', '210100');
INSERT INTO `r_county` VALUES ('210106', '铁西区', '210100');
INSERT INTO `r_county` VALUES ('210111', '苏家屯区', '210100');
INSERT INTO `r_county` VALUES ('210112', '东陵区', '210100');
INSERT INTO `r_county` VALUES ('210113', '新城子区', '210100');
INSERT INTO `r_county` VALUES ('210114', '于洪区', '210100');
INSERT INTO `r_county` VALUES ('210122', '辽中县', '210100');
INSERT INTO `r_county` VALUES ('210123', '康平县', '210100');
INSERT INTO `r_county` VALUES ('210124', '法库县', '210100');
INSERT INTO `r_county` VALUES ('210181', '新民市', '210100');
INSERT INTO `r_county` VALUES ('210201', '市辖区', '210200');
INSERT INTO `r_county` VALUES ('210202', '中山区', '210200');
INSERT INTO `r_county` VALUES ('210203', '西岗区', '210200');
INSERT INTO `r_county` VALUES ('210204', '沙河口区', '210200');
INSERT INTO `r_county` VALUES ('210211', '甘井子区', '210200');
INSERT INTO `r_county` VALUES ('210212', '旅顺口区', '210200');
INSERT INTO `r_county` VALUES ('210213', '金州区', '210200');
INSERT INTO `r_county` VALUES ('210224', '长海县', '210200');
INSERT INTO `r_county` VALUES ('210281', '瓦房店市', '210200');
INSERT INTO `r_county` VALUES ('210282', '普兰店市', '210200');
INSERT INTO `r_county` VALUES ('210283', '庄河市', '210200');
INSERT INTO `r_county` VALUES ('210301', '市辖区', '210300');
INSERT INTO `r_county` VALUES ('210302', '铁东区', '210300');
INSERT INTO `r_county` VALUES ('210303', '铁西区', '210300');
INSERT INTO `r_county` VALUES ('210304', '立山区', '210300');
INSERT INTO `r_county` VALUES ('210311', '千山区', '210300');
INSERT INTO `r_county` VALUES ('210321', '台安县', '210300');
INSERT INTO `r_county` VALUES ('210323', '岫岩满族自治县', '210300');
INSERT INTO `r_county` VALUES ('210381', '海城市', '210300');
INSERT INTO `r_county` VALUES ('210401', '市辖区', '210400');
INSERT INTO `r_county` VALUES ('210402', '新抚区', '210400');
INSERT INTO `r_county` VALUES ('210403', '东洲区', '210400');
INSERT INTO `r_county` VALUES ('210404', '望花区', '210400');
INSERT INTO `r_county` VALUES ('210411', '顺城区', '210400');
INSERT INTO `r_county` VALUES ('210421', '抚顺县', '210400');
INSERT INTO `r_county` VALUES ('210422', '新宾满族自治县', '210400');
INSERT INTO `r_county` VALUES ('210423', '清原满族自治县', '210400');
INSERT INTO `r_county` VALUES ('210501', '市辖区', '210500');
INSERT INTO `r_county` VALUES ('210502', '平山区', '210500');
INSERT INTO `r_county` VALUES ('210503', '溪湖区', '210500');
INSERT INTO `r_county` VALUES ('210504', '明山区', '210500');
INSERT INTO `r_county` VALUES ('210505', '南芬区', '210500');
INSERT INTO `r_county` VALUES ('210521', '本溪满族自治县', '210500');
INSERT INTO `r_county` VALUES ('210522', '桓仁满族自治县', '210500');
INSERT INTO `r_county` VALUES ('210601', '市辖区', '210600');
INSERT INTO `r_county` VALUES ('210602', '元宝区', '210600');
INSERT INTO `r_county` VALUES ('210603', '振兴区', '210600');
INSERT INTO `r_county` VALUES ('210604', '振安区', '210600');
INSERT INTO `r_county` VALUES ('210624', '宽甸满族自治县', '210600');
INSERT INTO `r_county` VALUES ('210681', '东港市', '210600');
INSERT INTO `r_county` VALUES ('210682', '凤城市', '210600');
INSERT INTO `r_county` VALUES ('210701', '市辖区', '210700');
INSERT INTO `r_county` VALUES ('210702', '古塔区', '210700');
INSERT INTO `r_county` VALUES ('210703', '凌河区', '210700');
INSERT INTO `r_county` VALUES ('210711', '太和区', '210700');
INSERT INTO `r_county` VALUES ('210726', '黑山县', '210700');
INSERT INTO `r_county` VALUES ('210727', '义　县', '210700');
INSERT INTO `r_county` VALUES ('210781', '凌海市', '210700');
INSERT INTO `r_county` VALUES ('210782', '北宁市', '210700');
INSERT INTO `r_county` VALUES ('210801', '市辖区', '210800');
INSERT INTO `r_county` VALUES ('210802', '站前区', '210800');
INSERT INTO `r_county` VALUES ('210803', '西市区', '210800');
INSERT INTO `r_county` VALUES ('210804', '鲅鱼圈区', '210800');
INSERT INTO `r_county` VALUES ('210811', '老边区', '210800');
INSERT INTO `r_county` VALUES ('210881', '盖州市', '210800');
INSERT INTO `r_county` VALUES ('210882', '大石桥市', '210800');
INSERT INTO `r_county` VALUES ('210901', '市辖区', '210900');
INSERT INTO `r_county` VALUES ('210902', '海州区', '210900');
INSERT INTO `r_county` VALUES ('210903', '新邱区', '210900');
INSERT INTO `r_county` VALUES ('210904', '太平区', '210900');
INSERT INTO `r_county` VALUES ('210905', '清河门区', '210900');
INSERT INTO `r_county` VALUES ('210911', '细河区', '210900');
INSERT INTO `r_county` VALUES ('210921', '阜新蒙古族自治县', '210900');
INSERT INTO `r_county` VALUES ('210922', '彰武县', '210900');
INSERT INTO `r_county` VALUES ('211001', '市辖区', '211000');
INSERT INTO `r_county` VALUES ('211002', '白塔区', '211000');
INSERT INTO `r_county` VALUES ('211003', '文圣区', '211000');
INSERT INTO `r_county` VALUES ('211004', '宏伟区', '211000');
INSERT INTO `r_county` VALUES ('211005', '弓长岭区', '211000');
INSERT INTO `r_county` VALUES ('211011', '太子河区', '211000');
INSERT INTO `r_county` VALUES ('211021', '辽阳县', '211000');
INSERT INTO `r_county` VALUES ('211081', '灯塔市', '211000');
INSERT INTO `r_county` VALUES ('211101', '市辖区', '211100');
INSERT INTO `r_county` VALUES ('211102', '双台子区', '211100');
INSERT INTO `r_county` VALUES ('211103', '兴隆台区', '211100');
INSERT INTO `r_county` VALUES ('211121', '大洼县', '211100');
INSERT INTO `r_county` VALUES ('211122', '盘山县', '211100');
INSERT INTO `r_county` VALUES ('211201', '市辖区', '211200');
INSERT INTO `r_county` VALUES ('211202', '银州区', '211200');
INSERT INTO `r_county` VALUES ('211204', '清河区', '211200');
INSERT INTO `r_county` VALUES ('211221', '铁岭县', '211200');
INSERT INTO `r_county` VALUES ('211223', '西丰县', '211200');
INSERT INTO `r_county` VALUES ('211224', '昌图县', '211200');
INSERT INTO `r_county` VALUES ('211281', '调兵山市', '211200');
INSERT INTO `r_county` VALUES ('211282', '开原市', '211200');
INSERT INTO `r_county` VALUES ('211301', '市辖区', '211300');
INSERT INTO `r_county` VALUES ('211302', '双塔区', '211300');
INSERT INTO `r_county` VALUES ('211303', '龙城区', '211300');
INSERT INTO `r_county` VALUES ('211321', '朝阳县', '211300');
INSERT INTO `r_county` VALUES ('211322', '建平县', '211300');
INSERT INTO `r_county` VALUES ('211324', '喀喇沁左翼蒙古族自治县', '211300');
INSERT INTO `r_county` VALUES ('211381', '北票市', '211300');
INSERT INTO `r_county` VALUES ('211382', '凌源市', '211300');
INSERT INTO `r_county` VALUES ('211401', '市辖区', '211400');
INSERT INTO `r_county` VALUES ('211402', '连山区', '211400');
INSERT INTO `r_county` VALUES ('211403', '龙港区', '211400');
INSERT INTO `r_county` VALUES ('211404', '南票区', '211400');
INSERT INTO `r_county` VALUES ('211421', '绥中县', '211400');
INSERT INTO `r_county` VALUES ('211422', '建昌县', '211400');
INSERT INTO `r_county` VALUES ('211481', '兴城市', '211400');
INSERT INTO `r_county` VALUES ('220101', '市辖区', '220100');
INSERT INTO `r_county` VALUES ('220102', '南关区', '220100');
INSERT INTO `r_county` VALUES ('220103', '宽城区', '220100');
INSERT INTO `r_county` VALUES ('220104', '朝阳区', '220100');
INSERT INTO `r_county` VALUES ('220105', '二道区', '220100');
INSERT INTO `r_county` VALUES ('220106', '绿园区', '220100');
INSERT INTO `r_county` VALUES ('220112', '双阳区', '220100');
INSERT INTO `r_county` VALUES ('220122', '农安县', '220100');
INSERT INTO `r_county` VALUES ('220181', '九台市', '220100');
INSERT INTO `r_county` VALUES ('220182', '榆树市', '220100');
INSERT INTO `r_county` VALUES ('220183', '德惠市', '220100');
INSERT INTO `r_county` VALUES ('220201', '市辖区', '220200');
INSERT INTO `r_county` VALUES ('220202', '昌邑区', '220200');
INSERT INTO `r_county` VALUES ('220203', '龙潭区', '220200');
INSERT INTO `r_county` VALUES ('220204', '船营区', '220200');
INSERT INTO `r_county` VALUES ('220211', '丰满区', '220200');
INSERT INTO `r_county` VALUES ('220221', '永吉县', '220200');
INSERT INTO `r_county` VALUES ('220281', '蛟河市', '220200');
INSERT INTO `r_county` VALUES ('220282', '桦甸市', '220200');
INSERT INTO `r_county` VALUES ('220283', '舒兰市', '220200');
INSERT INTO `r_county` VALUES ('220284', '磐石市', '220200');
INSERT INTO `r_county` VALUES ('220301', '市辖区', '220300');
INSERT INTO `r_county` VALUES ('220302', '铁西区', '220300');
INSERT INTO `r_county` VALUES ('220303', '铁东区', '220300');
INSERT INTO `r_county` VALUES ('220322', '梨树县', '220300');
INSERT INTO `r_county` VALUES ('220323', '伊通满族自治县', '220300');
INSERT INTO `r_county` VALUES ('220381', '公主岭市', '220300');
INSERT INTO `r_county` VALUES ('220382', '双辽市', '220300');
INSERT INTO `r_county` VALUES ('220401', '市辖区', '220400');
INSERT INTO `r_county` VALUES ('220402', '龙山区', '220400');
INSERT INTO `r_county` VALUES ('220403', '西安区', '220400');
INSERT INTO `r_county` VALUES ('220421', '东丰县', '220400');
INSERT INTO `r_county` VALUES ('220422', '东辽县', '220400');
INSERT INTO `r_county` VALUES ('220501', '市辖区', '220500');
INSERT INTO `r_county` VALUES ('220502', '东昌区', '220500');
INSERT INTO `r_county` VALUES ('220503', '二道江区', '220500');
INSERT INTO `r_county` VALUES ('220521', '通化县', '220500');
INSERT INTO `r_county` VALUES ('220523', '辉南县', '220500');
INSERT INTO `r_county` VALUES ('220524', '柳河县', '220500');
INSERT INTO `r_county` VALUES ('220581', '梅河口市', '220500');
INSERT INTO `r_county` VALUES ('220582', '集安市', '220500');
INSERT INTO `r_county` VALUES ('220601', '市辖区', '220600');
INSERT INTO `r_county` VALUES ('220602', '八道江区', '220600');
INSERT INTO `r_county` VALUES ('220621', '抚松县', '220600');
INSERT INTO `r_county` VALUES ('220622', '靖宇县', '220600');
INSERT INTO `r_county` VALUES ('220623', '长白朝鲜族自治县', '220600');
INSERT INTO `r_county` VALUES ('220625', '江源县', '220600');
INSERT INTO `r_county` VALUES ('220681', '临江市', '220600');
INSERT INTO `r_county` VALUES ('220701', '市辖区', '220700');
INSERT INTO `r_county` VALUES ('220702', '宁江区', '220700');
INSERT INTO `r_county` VALUES ('220721', '前郭尔罗斯蒙古族自治县', '220700');
INSERT INTO `r_county` VALUES ('220722', '长岭县', '220700');
INSERT INTO `r_county` VALUES ('220723', '乾安县', '220700');
INSERT INTO `r_county` VALUES ('220724', '扶余县', '220700');
INSERT INTO `r_county` VALUES ('220801', '市辖区', '220800');
INSERT INTO `r_county` VALUES ('220802', '洮北区', '220800');
INSERT INTO `r_county` VALUES ('220821', '镇赉县', '220800');
INSERT INTO `r_county` VALUES ('220822', '通榆县', '220800');
INSERT INTO `r_county` VALUES ('220881', '洮南市', '220800');
INSERT INTO `r_county` VALUES ('220882', '大安市', '220800');
INSERT INTO `r_county` VALUES ('222401', '延吉市', '222400');
INSERT INTO `r_county` VALUES ('222402', '图们市', '222400');
INSERT INTO `r_county` VALUES ('222403', '敦化市', '222400');
INSERT INTO `r_county` VALUES ('222404', '珲春市', '222400');
INSERT INTO `r_county` VALUES ('222405', '龙井市', '222400');
INSERT INTO `r_county` VALUES ('222406', '和龙市', '222400');
INSERT INTO `r_county` VALUES ('222424', '汪清县', '222400');
INSERT INTO `r_county` VALUES ('222426', '安图县', '222400');
INSERT INTO `r_county` VALUES ('230101', '市辖区', '230100');
INSERT INTO `r_county` VALUES ('230102', '道里区', '230100');
INSERT INTO `r_county` VALUES ('230103', '南岗区', '230100');
INSERT INTO `r_county` VALUES ('230104', '道外区', '230100');
INSERT INTO `r_county` VALUES ('230106', '香坊区', '230100');
INSERT INTO `r_county` VALUES ('230107', '动力区', '230100');
INSERT INTO `r_county` VALUES ('230108', '平房区', '230100');
INSERT INTO `r_county` VALUES ('230109', '松北区', '230100');
INSERT INTO `r_county` VALUES ('230111', '呼兰区', '230100');
INSERT INTO `r_county` VALUES ('230123', '依兰县', '230100');
INSERT INTO `r_county` VALUES ('230124', '方正县', '230100');
INSERT INTO `r_county` VALUES ('230125', '宾　县', '230100');
INSERT INTO `r_county` VALUES ('230126', '巴彦县', '230100');
INSERT INTO `r_county` VALUES ('230127', '木兰县', '230100');
INSERT INTO `r_county` VALUES ('230128', '通河县', '230100');
INSERT INTO `r_county` VALUES ('230129', '延寿县', '230100');
INSERT INTO `r_county` VALUES ('230181', '阿城市', '230100');
INSERT INTO `r_county` VALUES ('230182', '双城市', '230100');
INSERT INTO `r_county` VALUES ('230183', '尚志市', '230100');
INSERT INTO `r_county` VALUES ('230184', '五常市', '230100');
INSERT INTO `r_county` VALUES ('230201', '市辖区', '230200');
INSERT INTO `r_county` VALUES ('230202', '龙沙区', '230200');
INSERT INTO `r_county` VALUES ('230203', '建华区', '230200');
INSERT INTO `r_county` VALUES ('230204', '铁锋区', '230200');
INSERT INTO `r_county` VALUES ('230205', '昂昂溪区', '230200');
INSERT INTO `r_county` VALUES ('230206', '富拉尔基区', '230200');
INSERT INTO `r_county` VALUES ('230207', '碾子山区', '230200');
INSERT INTO `r_county` VALUES ('230208', '梅里斯达斡尔族区', '230200');
INSERT INTO `r_county` VALUES ('230221', '龙江县', '230200');
INSERT INTO `r_county` VALUES ('230223', '依安县', '230200');
INSERT INTO `r_county` VALUES ('230224', '泰来县', '230200');
INSERT INTO `r_county` VALUES ('230225', '甘南县', '230200');
INSERT INTO `r_county` VALUES ('230227', '富裕县', '230200');
INSERT INTO `r_county` VALUES ('230229', '克山县', '230200');
INSERT INTO `r_county` VALUES ('230230', '克东县', '230200');
INSERT INTO `r_county` VALUES ('230231', '拜泉县', '230200');
INSERT INTO `r_county` VALUES ('230281', '讷河市', '230200');
INSERT INTO `r_county` VALUES ('230301', '市辖区', '230300');
INSERT INTO `r_county` VALUES ('230302', '鸡冠区', '230300');
INSERT INTO `r_county` VALUES ('230303', '恒山区', '230300');
INSERT INTO `r_county` VALUES ('230304', '滴道区', '230300');
INSERT INTO `r_county` VALUES ('230305', '梨树区', '230300');
INSERT INTO `r_county` VALUES ('230306', '城子河区', '230300');
INSERT INTO `r_county` VALUES ('230307', '麻山区', '230300');
INSERT INTO `r_county` VALUES ('230321', '鸡东县', '230300');
INSERT INTO `r_county` VALUES ('230381', '虎林市', '230300');
INSERT INTO `r_county` VALUES ('230382', '密山市', '230300');
INSERT INTO `r_county` VALUES ('230401', '市辖区', '230400');
INSERT INTO `r_county` VALUES ('230402', '向阳区', '230400');
INSERT INTO `r_county` VALUES ('230403', '工农区', '230400');
INSERT INTO `r_county` VALUES ('230404', '南山区', '230400');
INSERT INTO `r_county` VALUES ('230405', '兴安区', '230400');
INSERT INTO `r_county` VALUES ('230406', '东山区', '230400');
INSERT INTO `r_county` VALUES ('230407', '兴山区', '230400');
INSERT INTO `r_county` VALUES ('230421', '萝北县', '230400');
INSERT INTO `r_county` VALUES ('230422', '绥滨县', '230400');
INSERT INTO `r_county` VALUES ('230501', '市辖区', '230500');
INSERT INTO `r_county` VALUES ('230502', '尖山区', '230500');
INSERT INTO `r_county` VALUES ('230503', '岭东区', '230500');
INSERT INTO `r_county` VALUES ('230505', '四方台区', '230500');
INSERT INTO `r_county` VALUES ('230506', '宝山区', '230500');
INSERT INTO `r_county` VALUES ('230521', '集贤县', '230500');
INSERT INTO `r_county` VALUES ('230522', '友谊县', '230500');
INSERT INTO `r_county` VALUES ('230523', '宝清县', '230500');
INSERT INTO `r_county` VALUES ('230524', '饶河县', '230500');
INSERT INTO `r_county` VALUES ('230601', '市辖区', '230600');
INSERT INTO `r_county` VALUES ('230602', '萨尔图区', '230600');
INSERT INTO `r_county` VALUES ('230603', '龙凤区', '230600');
INSERT INTO `r_county` VALUES ('230604', '让胡路区', '230600');
INSERT INTO `r_county` VALUES ('230605', '红岗区', '230600');
INSERT INTO `r_county` VALUES ('230606', '大同区', '230600');
INSERT INTO `r_county` VALUES ('230621', '肇州县', '230600');
INSERT INTO `r_county` VALUES ('230622', '肇源县', '230600');
INSERT INTO `r_county` VALUES ('230623', '林甸县', '230600');
INSERT INTO `r_county` VALUES ('230624', '杜尔伯特蒙古族自治县', '230600');
INSERT INTO `r_county` VALUES ('230701', '市辖区', '230700');
INSERT INTO `r_county` VALUES ('230702', '伊春区', '230700');
INSERT INTO `r_county` VALUES ('230703', '南岔区', '230700');
INSERT INTO `r_county` VALUES ('230704', '友好区', '230700');
INSERT INTO `r_county` VALUES ('230705', '西林区', '230700');
INSERT INTO `r_county` VALUES ('230706', '翠峦区', '230700');
INSERT INTO `r_county` VALUES ('230707', '新青区', '230700');
INSERT INTO `r_county` VALUES ('230708', '美溪区', '230700');
INSERT INTO `r_county` VALUES ('230709', '金山屯区', '230700');
INSERT INTO `r_county` VALUES ('230710', '五营区', '230700');
INSERT INTO `r_county` VALUES ('230711', '乌马河区', '230700');
INSERT INTO `r_county` VALUES ('230712', '汤旺河区', '230700');
INSERT INTO `r_county` VALUES ('230713', '带岭区', '230700');
INSERT INTO `r_county` VALUES ('230714', '乌伊岭区', '230700');
INSERT INTO `r_county` VALUES ('230715', '红星区', '230700');
INSERT INTO `r_county` VALUES ('230716', '上甘岭区', '230700');
INSERT INTO `r_county` VALUES ('230722', '嘉荫县', '230700');
INSERT INTO `r_county` VALUES ('230781', '铁力市', '230700');
INSERT INTO `r_county` VALUES ('230801', '市辖区', '230800');
INSERT INTO `r_county` VALUES ('230802', '永红区', '230800');
INSERT INTO `r_county` VALUES ('230803', '向阳区', '230800');
INSERT INTO `r_county` VALUES ('230804', '前进区', '230800');
INSERT INTO `r_county` VALUES ('230805', '东风区', '230800');
INSERT INTO `r_county` VALUES ('230811', '郊　区', '230800');
INSERT INTO `r_county` VALUES ('230822', '桦南县', '230800');
INSERT INTO `r_county` VALUES ('230826', '桦川县', '230800');
INSERT INTO `r_county` VALUES ('230828', '汤原县', '230800');
INSERT INTO `r_county` VALUES ('230833', '抚远县', '230800');
INSERT INTO `r_county` VALUES ('230881', '同江市', '230800');
INSERT INTO `r_county` VALUES ('230882', '富锦市', '230800');
INSERT INTO `r_county` VALUES ('230901', '市辖区', '230900');
INSERT INTO `r_county` VALUES ('230902', '新兴区', '230900');
INSERT INTO `r_county` VALUES ('230903', '桃山区', '230900');
INSERT INTO `r_county` VALUES ('230904', '茄子河区', '230900');
INSERT INTO `r_county` VALUES ('230921', '勃利县', '230900');
INSERT INTO `r_county` VALUES ('231001', '市辖区', '231000');
INSERT INTO `r_county` VALUES ('231002', '东安区', '231000');
INSERT INTO `r_county` VALUES ('231003', '阳明区', '231000');
INSERT INTO `r_county` VALUES ('231004', '爱民区', '231000');
INSERT INTO `r_county` VALUES ('231005', '西安区', '231000');
INSERT INTO `r_county` VALUES ('231024', '东宁县', '231000');
INSERT INTO `r_county` VALUES ('231025', '林口县', '231000');
INSERT INTO `r_county` VALUES ('231081', '绥芬河市', '231000');
INSERT INTO `r_county` VALUES ('231083', '海林市', '231000');
INSERT INTO `r_county` VALUES ('231084', '宁安市', '231000');
INSERT INTO `r_county` VALUES ('231085', '穆棱市', '231000');
INSERT INTO `r_county` VALUES ('231101', '市辖区', '231100');
INSERT INTO `r_county` VALUES ('231102', '爱辉区', '231100');
INSERT INTO `r_county` VALUES ('231121', '嫩江县', '231100');
INSERT INTO `r_county` VALUES ('231123', '逊克县', '231100');
INSERT INTO `r_county` VALUES ('231124', '孙吴县', '231100');
INSERT INTO `r_county` VALUES ('231181', '北安市', '231100');
INSERT INTO `r_county` VALUES ('231182', '五大连池市', '231100');
INSERT INTO `r_county` VALUES ('231201', '市辖区', '231200');
INSERT INTO `r_county` VALUES ('231202', '北林区', '231200');
INSERT INTO `r_county` VALUES ('231221', '望奎县', '231200');
INSERT INTO `r_county` VALUES ('231222', '兰西县', '231200');
INSERT INTO `r_county` VALUES ('231223', '青冈县', '231200');
INSERT INTO `r_county` VALUES ('231224', '庆安县', '231200');
INSERT INTO `r_county` VALUES ('231225', '明水县', '231200');
INSERT INTO `r_county` VALUES ('231226', '绥棱县', '231200');
INSERT INTO `r_county` VALUES ('231281', '安达市', '231200');
INSERT INTO `r_county` VALUES ('231282', '肇东市', '231200');
INSERT INTO `r_county` VALUES ('231283', '海伦市', '231200');
INSERT INTO `r_county` VALUES ('232721', '呼玛县', '232700');
INSERT INTO `r_county` VALUES ('232722', '塔河县', '232700');
INSERT INTO `r_county` VALUES ('232723', '漠河县', '232700');
INSERT INTO `r_county` VALUES ('310101', '黄浦区', '310100');
INSERT INTO `r_county` VALUES ('310103', '卢湾区', '310100');
INSERT INTO `r_county` VALUES ('310104', '徐汇区', '310100');
INSERT INTO `r_county` VALUES ('310105', '长宁区', '310100');
INSERT INTO `r_county` VALUES ('310106', '静安区', '310100');
INSERT INTO `r_county` VALUES ('310107', '普陀区', '310100');
INSERT INTO `r_county` VALUES ('310108', '闸北区', '310100');
INSERT INTO `r_county` VALUES ('310109', '虹口区', '310100');
INSERT INTO `r_county` VALUES ('310110', '杨浦区', '310100');
INSERT INTO `r_county` VALUES ('310112', '闵行区', '310100');
INSERT INTO `r_county` VALUES ('310113', '宝山区', '310100');
INSERT INTO `r_county` VALUES ('310114', '嘉定区', '310100');
INSERT INTO `r_county` VALUES ('310115', '浦东新区', '310100');
INSERT INTO `r_county` VALUES ('310116', '金山区', '310100');
INSERT INTO `r_county` VALUES ('310117', '松江区', '310100');
INSERT INTO `r_county` VALUES ('310118', '青浦区', '310100');
INSERT INTO `r_county` VALUES ('310119', '南汇区', '310100');
INSERT INTO `r_county` VALUES ('310120', '奉贤区', '310100');
INSERT INTO `r_county` VALUES ('310230', '崇明县', '310200');
INSERT INTO `r_county` VALUES ('320101', '市辖区', '320100');
INSERT INTO `r_county` VALUES ('320102', '玄武区', '320100');
INSERT INTO `r_county` VALUES ('320103', '白下区', '320100');
INSERT INTO `r_county` VALUES ('320104', '秦淮区', '320100');
INSERT INTO `r_county` VALUES ('320105', '建邺区', '320100');
INSERT INTO `r_county` VALUES ('320106', '鼓楼区', '320100');
INSERT INTO `r_county` VALUES ('320107', '下关区', '320100');
INSERT INTO `r_county` VALUES ('320111', '浦口区', '320100');
INSERT INTO `r_county` VALUES ('320113', '栖霞区', '320100');
INSERT INTO `r_county` VALUES ('320114', '雨花台区', '320100');
INSERT INTO `r_county` VALUES ('320115', '江宁区', '320100');
INSERT INTO `r_county` VALUES ('320116', '六合区', '320100');
INSERT INTO `r_county` VALUES ('320124', '溧水县', '320100');
INSERT INTO `r_county` VALUES ('320125', '高淳县', '320100');
INSERT INTO `r_county` VALUES ('320201', '市辖区', '320200');
INSERT INTO `r_county` VALUES ('320202', '崇安区', '320200');
INSERT INTO `r_county` VALUES ('320203', '南长区', '320200');
INSERT INTO `r_county` VALUES ('320204', '北塘区', '320200');
INSERT INTO `r_county` VALUES ('320205', '锡山区', '320200');
INSERT INTO `r_county` VALUES ('320206', '惠山区', '320200');
INSERT INTO `r_county` VALUES ('320211', '滨湖区', '320200');
INSERT INTO `r_county` VALUES ('320281', '江阴市', '320200');
INSERT INTO `r_county` VALUES ('320282', '宜兴市', '320200');
INSERT INTO `r_county` VALUES ('320301', '市辖区', '320300');
INSERT INTO `r_county` VALUES ('320302', '鼓楼区', '320300');
INSERT INTO `r_county` VALUES ('320303', '云龙区', '320300');
INSERT INTO `r_county` VALUES ('320304', '九里区', '320300');
INSERT INTO `r_county` VALUES ('320305', '贾汪区', '320300');
INSERT INTO `r_county` VALUES ('320311', '泉山区', '320300');
INSERT INTO `r_county` VALUES ('320321', '丰　县', '320300');
INSERT INTO `r_county` VALUES ('320322', '沛　县', '320300');
INSERT INTO `r_county` VALUES ('320323', '铜山县', '320300');
INSERT INTO `r_county` VALUES ('320324', '睢宁县', '320300');
INSERT INTO `r_county` VALUES ('320381', '新沂市', '320300');
INSERT INTO `r_county` VALUES ('320382', '邳州市', '320300');
INSERT INTO `r_county` VALUES ('320401', '市辖区', '320400');
INSERT INTO `r_county` VALUES ('320402', '天宁区', '320400');
INSERT INTO `r_county` VALUES ('320404', '钟楼区', '320400');
INSERT INTO `r_county` VALUES ('320405', '戚墅堰区', '320400');
INSERT INTO `r_county` VALUES ('320411', '新北区', '320400');
INSERT INTO `r_county` VALUES ('320412', '武进区', '320400');
INSERT INTO `r_county` VALUES ('320481', '溧阳市', '320400');
INSERT INTO `r_county` VALUES ('320482', '金坛市', '320400');
INSERT INTO `r_county` VALUES ('320501', '市辖区', '320500');
INSERT INTO `r_county` VALUES ('320502', '沧浪区', '320500');
INSERT INTO `r_county` VALUES ('320503', '平江区', '320500');
INSERT INTO `r_county` VALUES ('320504', '金阊区', '320500');
INSERT INTO `r_county` VALUES ('320505', '虎丘区', '320500');
INSERT INTO `r_county` VALUES ('320506', '吴中区', '320500');
INSERT INTO `r_county` VALUES ('320507', '相城区', '320500');
INSERT INTO `r_county` VALUES ('320581', '常熟市', '320500');
INSERT INTO `r_county` VALUES ('320582', '张家港市', '320500');
INSERT INTO `r_county` VALUES ('320583', '昆山市', '320500');
INSERT INTO `r_county` VALUES ('320584', '吴江市', '320500');
INSERT INTO `r_county` VALUES ('320585', '太仓市', '320500');
INSERT INTO `r_county` VALUES ('320601', '市辖区', '320600');
INSERT INTO `r_county` VALUES ('320602', '崇川区', '320600');
INSERT INTO `r_county` VALUES ('320611', '港闸区', '320600');
INSERT INTO `r_county` VALUES ('320621', '海安县', '320600');
INSERT INTO `r_county` VALUES ('320623', '如东县', '320600');
INSERT INTO `r_county` VALUES ('320681', '启东市', '320600');
INSERT INTO `r_county` VALUES ('320682', '如皋市', '320600');
INSERT INTO `r_county` VALUES ('320683', '通州市', '320600');
INSERT INTO `r_county` VALUES ('320684', '海门市', '320600');
INSERT INTO `r_county` VALUES ('320701', '市辖区', '320700');
INSERT INTO `r_county` VALUES ('320703', '连云区', '320700');
INSERT INTO `r_county` VALUES ('320705', '新浦区', '320700');
INSERT INTO `r_county` VALUES ('320706', '海州区', '320700');
INSERT INTO `r_county` VALUES ('320721', '赣榆县', '320700');
INSERT INTO `r_county` VALUES ('320722', '东海县', '320700');
INSERT INTO `r_county` VALUES ('320723', '灌云县', '320700');
INSERT INTO `r_county` VALUES ('320724', '灌南县', '320700');
INSERT INTO `r_county` VALUES ('320801', '市辖区', '320800');
INSERT INTO `r_county` VALUES ('320802', '清河区', '320800');
INSERT INTO `r_county` VALUES ('320803', '楚州区', '320800');
INSERT INTO `r_county` VALUES ('320804', '淮阴区', '320800');
INSERT INTO `r_county` VALUES ('320811', '清浦区', '320800');
INSERT INTO `r_county` VALUES ('320826', '涟水县', '320800');
INSERT INTO `r_county` VALUES ('320829', '洪泽县', '320800');
INSERT INTO `r_county` VALUES ('320830', '盱眙县', '320800');
INSERT INTO `r_county` VALUES ('320831', '金湖县', '320800');
INSERT INTO `r_county` VALUES ('320901', '市辖区', '320900');
INSERT INTO `r_county` VALUES ('320902', '亭湖区', '320900');
INSERT INTO `r_county` VALUES ('320903', '盐都区', '320900');
INSERT INTO `r_county` VALUES ('320921', '响水县', '320900');
INSERT INTO `r_county` VALUES ('320922', '滨海县', '320900');
INSERT INTO `r_county` VALUES ('320923', '阜宁县', '320900');
INSERT INTO `r_county` VALUES ('320924', '射阳县', '320900');
INSERT INTO `r_county` VALUES ('320925', '建湖县', '320900');
INSERT INTO `r_county` VALUES ('320981', '东台市', '320900');
INSERT INTO `r_county` VALUES ('320982', '大丰市', '320900');
INSERT INTO `r_county` VALUES ('321001', '市辖区', '321000');
INSERT INTO `r_county` VALUES ('321002', '广陵区', '321000');
INSERT INTO `r_county` VALUES ('321003', '邗江区', '321000');
INSERT INTO `r_county` VALUES ('321011', '郊　区', '321000');
INSERT INTO `r_county` VALUES ('321023', '宝应县', '321000');
INSERT INTO `r_county` VALUES ('321081', '仪征市', '321000');
INSERT INTO `r_county` VALUES ('321084', '高邮市', '321000');
INSERT INTO `r_county` VALUES ('321088', '江都市', '321000');
INSERT INTO `r_county` VALUES ('321101', '市辖区', '321100');
INSERT INTO `r_county` VALUES ('321102', '京口区', '321100');
INSERT INTO `r_county` VALUES ('321111', '润州区', '321100');
INSERT INTO `r_county` VALUES ('321112', '丹徒区', '321100');
INSERT INTO `r_county` VALUES ('321181', '丹阳市', '321100');
INSERT INTO `r_county` VALUES ('321182', '扬中市', '321100');
INSERT INTO `r_county` VALUES ('321183', '句容市', '321100');
INSERT INTO `r_county` VALUES ('321201', '市辖区', '321200');
INSERT INTO `r_county` VALUES ('321202', '海陵区', '321200');
INSERT INTO `r_county` VALUES ('321203', '高港区', '321200');
INSERT INTO `r_county` VALUES ('321281', '兴化市', '321200');
INSERT INTO `r_county` VALUES ('321282', '靖江市', '321200');
INSERT INTO `r_county` VALUES ('321283', '泰兴市', '321200');
INSERT INTO `r_county` VALUES ('321284', '姜堰市', '321200');
INSERT INTO `r_county` VALUES ('321301', '市辖区', '321300');
INSERT INTO `r_county` VALUES ('321302', '宿城区', '321300');
INSERT INTO `r_county` VALUES ('321311', '宿豫区', '321300');
INSERT INTO `r_county` VALUES ('321322', '沭阳县', '321300');
INSERT INTO `r_county` VALUES ('321323', '泗阳县', '321300');
INSERT INTO `r_county` VALUES ('321324', '泗洪县', '321300');
INSERT INTO `r_county` VALUES ('330101', '市辖区', '330100');
INSERT INTO `r_county` VALUES ('330102', '上城区', '330100');
INSERT INTO `r_county` VALUES ('330103', '下城区', '330100');
INSERT INTO `r_county` VALUES ('330104', '江干区', '330100');
INSERT INTO `r_county` VALUES ('330105', '拱墅区', '330100');
INSERT INTO `r_county` VALUES ('330106', '西湖区', '330100');
INSERT INTO `r_county` VALUES ('330108', '滨江区', '330100');
INSERT INTO `r_county` VALUES ('330109', '萧山区', '330100');
INSERT INTO `r_county` VALUES ('330110', '余杭区', '330100');
INSERT INTO `r_county` VALUES ('330122', '桐庐县', '330100');
INSERT INTO `r_county` VALUES ('330127', '淳安县', '330100');
INSERT INTO `r_county` VALUES ('330182', '建德市', '330100');
INSERT INTO `r_county` VALUES ('330183', '富阳市', '330100');
INSERT INTO `r_county` VALUES ('330185', '临安市', '330100');
INSERT INTO `r_county` VALUES ('330201', '市辖区', '330200');
INSERT INTO `r_county` VALUES ('330203', '海曙区', '330200');
INSERT INTO `r_county` VALUES ('330204', '江东区', '330200');
INSERT INTO `r_county` VALUES ('330205', '江北区', '330200');
INSERT INTO `r_county` VALUES ('330206', '北仑区', '330200');
INSERT INTO `r_county` VALUES ('330211', '镇海区', '330200');
INSERT INTO `r_county` VALUES ('330212', '鄞州区', '330200');
INSERT INTO `r_county` VALUES ('330225', '象山县', '330200');
INSERT INTO `r_county` VALUES ('330226', '宁海县', '330200');
INSERT INTO `r_county` VALUES ('330281', '余姚市', '330200');
INSERT INTO `r_county` VALUES ('330282', '慈溪市', '330200');
INSERT INTO `r_county` VALUES ('330283', '奉化市', '330200');
INSERT INTO `r_county` VALUES ('330301', '市辖区', '330300');
INSERT INTO `r_county` VALUES ('330302', '鹿城区', '330300');
INSERT INTO `r_county` VALUES ('330303', '龙湾区', '330300');
INSERT INTO `r_county` VALUES ('330304', '瓯海区', '330300');
INSERT INTO `r_county` VALUES ('330322', '洞头县', '330300');
INSERT INTO `r_county` VALUES ('330324', '永嘉县', '330300');
INSERT INTO `r_county` VALUES ('330326', '平阳县', '330300');
INSERT INTO `r_county` VALUES ('330327', '苍南县', '330300');
INSERT INTO `r_county` VALUES ('330328', '文成县', '330300');
INSERT INTO `r_county` VALUES ('330329', '泰顺县', '330300');
INSERT INTO `r_county` VALUES ('330381', '瑞安市', '330300');
INSERT INTO `r_county` VALUES ('330382', '乐清市', '330300');
INSERT INTO `r_county` VALUES ('330401', '市辖区', '330400');
INSERT INTO `r_county` VALUES ('330402', '秀城区', '330400');
INSERT INTO `r_county` VALUES ('330411', '秀洲区', '330400');
INSERT INTO `r_county` VALUES ('330421', '嘉善县', '330400');
INSERT INTO `r_county` VALUES ('330424', '海盐县', '330400');
INSERT INTO `r_county` VALUES ('330481', '海宁市', '330400');
INSERT INTO `r_county` VALUES ('330482', '平湖市', '330400');
INSERT INTO `r_county` VALUES ('330483', '桐乡市', '330400');
INSERT INTO `r_county` VALUES ('330501', '市辖区', '330500');
INSERT INTO `r_county` VALUES ('330502', '吴兴区', '330500');
INSERT INTO `r_county` VALUES ('330503', '南浔区', '330500');
INSERT INTO `r_county` VALUES ('330521', '德清县', '330500');
INSERT INTO `r_county` VALUES ('330522', '长兴县', '330500');
INSERT INTO `r_county` VALUES ('330523', '安吉县', '330500');
INSERT INTO `r_county` VALUES ('330601', '市辖区', '330600');
INSERT INTO `r_county` VALUES ('330602', '越城区', '330600');
INSERT INTO `r_county` VALUES ('330621', '绍兴县', '330600');
INSERT INTO `r_county` VALUES ('330624', '新昌县', '330600');
INSERT INTO `r_county` VALUES ('330681', '诸暨市', '330600');
INSERT INTO `r_county` VALUES ('330682', '上虞市', '330600');
INSERT INTO `r_county` VALUES ('330683', '嵊州市', '330600');
INSERT INTO `r_county` VALUES ('330701', '市辖区', '330700');
INSERT INTO `r_county` VALUES ('330702', '婺城区', '330700');
INSERT INTO `r_county` VALUES ('330703', '金东区', '330700');
INSERT INTO `r_county` VALUES ('330723', '武义县', '330700');
INSERT INTO `r_county` VALUES ('330726', '浦江县', '330700');
INSERT INTO `r_county` VALUES ('330727', '磐安县', '330700');
INSERT INTO `r_county` VALUES ('330781', '兰溪市', '330700');
INSERT INTO `r_county` VALUES ('330782', '义乌市', '330700');
INSERT INTO `r_county` VALUES ('330783', '东阳市', '330700');
INSERT INTO `r_county` VALUES ('330784', '永康市', '330700');
INSERT INTO `r_county` VALUES ('330801', '市辖区', '330800');
INSERT INTO `r_county` VALUES ('330802', '柯城区', '330800');
INSERT INTO `r_county` VALUES ('330803', '衢江区', '330800');
INSERT INTO `r_county` VALUES ('330822', '常山县', '330800');
INSERT INTO `r_county` VALUES ('330824', '开化县', '330800');
INSERT INTO `r_county` VALUES ('330825', '龙游县', '330800');
INSERT INTO `r_county` VALUES ('330881', '江山市', '330800');
INSERT INTO `r_county` VALUES ('330901', '市辖区', '330900');
INSERT INTO `r_county` VALUES ('330902', '定海区', '330900');
INSERT INTO `r_county` VALUES ('330903', '普陀区', '330900');
INSERT INTO `r_county` VALUES ('330921', '岱山县', '330900');
INSERT INTO `r_county` VALUES ('330922', '嵊泗县', '330900');
INSERT INTO `r_county` VALUES ('331001', '市辖区', '331000');
INSERT INTO `r_county` VALUES ('331002', '椒江区', '331000');
INSERT INTO `r_county` VALUES ('331003', '黄岩区', '331000');
INSERT INTO `r_county` VALUES ('331004', '路桥区', '331000');
INSERT INTO `r_county` VALUES ('331021', '玉环县', '331000');
INSERT INTO `r_county` VALUES ('331022', '三门县', '331000');
INSERT INTO `r_county` VALUES ('331023', '天台县', '331000');
INSERT INTO `r_county` VALUES ('331024', '仙居县', '331000');
INSERT INTO `r_county` VALUES ('331081', '温岭市', '331000');
INSERT INTO `r_county` VALUES ('331082', '临海市', '331000');
INSERT INTO `r_county` VALUES ('331101', '市辖区', '331100');
INSERT INTO `r_county` VALUES ('331102', '莲都区', '331100');
INSERT INTO `r_county` VALUES ('331121', '青田县', '331100');
INSERT INTO `r_county` VALUES ('331122', '缙云县', '331100');
INSERT INTO `r_county` VALUES ('331123', '遂昌县', '331100');
INSERT INTO `r_county` VALUES ('331124', '松阳县', '331100');
INSERT INTO `r_county` VALUES ('331125', '云和县', '331100');
INSERT INTO `r_county` VALUES ('331126', '庆元县', '331100');
INSERT INTO `r_county` VALUES ('331127', '景宁畲族自治县', '331100');
INSERT INTO `r_county` VALUES ('331181', '龙泉市', '331100');
INSERT INTO `r_county` VALUES ('340101', '市辖区', '340100');
INSERT INTO `r_county` VALUES ('340102', '瑶海区', '340100');
INSERT INTO `r_county` VALUES ('340103', '庐阳区', '340100');
INSERT INTO `r_county` VALUES ('340104', '蜀山区', '340100');
INSERT INTO `r_county` VALUES ('340111', '包河区', '340100');
INSERT INTO `r_county` VALUES ('340121', '长丰县', '340100');
INSERT INTO `r_county` VALUES ('340122', '肥东县', '340100');
INSERT INTO `r_county` VALUES ('340123', '肥西县', '340100');
INSERT INTO `r_county` VALUES ('340201', '市辖区', '340200');
INSERT INTO `r_county` VALUES ('340202', '镜湖区', '340200');
INSERT INTO `r_county` VALUES ('340203', '马塘区', '340200');
INSERT INTO `r_county` VALUES ('340204', '新芜区', '340200');
INSERT INTO `r_county` VALUES ('340207', '鸠江区', '340200');
INSERT INTO `r_county` VALUES ('340221', '芜湖县', '340200');
INSERT INTO `r_county` VALUES ('340222', '繁昌县', '340200');
INSERT INTO `r_county` VALUES ('340223', '南陵县', '340200');
INSERT INTO `r_county` VALUES ('340301', '市辖区', '340300');
INSERT INTO `r_county` VALUES ('340302', '龙子湖区', '340300');
INSERT INTO `r_county` VALUES ('340303', '蚌山区', '340300');
INSERT INTO `r_county` VALUES ('340304', '禹会区', '340300');
INSERT INTO `r_county` VALUES ('340311', '淮上区', '340300');
INSERT INTO `r_county` VALUES ('340321', '怀远县', '340300');
INSERT INTO `r_county` VALUES ('340322', '五河县', '340300');
INSERT INTO `r_county` VALUES ('340323', '固镇县', '340300');
INSERT INTO `r_county` VALUES ('340401', '市辖区', '340400');
INSERT INTO `r_county` VALUES ('340402', '大通区', '340400');
INSERT INTO `r_county` VALUES ('340403', '田家庵区', '340400');
INSERT INTO `r_county` VALUES ('340404', '谢家集区', '340400');
INSERT INTO `r_county` VALUES ('340405', '八公山区', '340400');
INSERT INTO `r_county` VALUES ('340406', '潘集区', '340400');
INSERT INTO `r_county` VALUES ('340421', '凤台县', '340400');
INSERT INTO `r_county` VALUES ('340501', '市辖区', '340500');
INSERT INTO `r_county` VALUES ('340502', '金家庄区', '340500');
INSERT INTO `r_county` VALUES ('340503', '花山区', '340500');
INSERT INTO `r_county` VALUES ('340504', '雨山区', '340500');
INSERT INTO `r_county` VALUES ('340521', '当涂县', '340500');
INSERT INTO `r_county` VALUES ('340601', '市辖区', '340600');
INSERT INTO `r_county` VALUES ('340602', '杜集区', '340600');
INSERT INTO `r_county` VALUES ('340603', '相山区', '340600');
INSERT INTO `r_county` VALUES ('340604', '烈山区', '340600');
INSERT INTO `r_county` VALUES ('340621', '濉溪县', '340600');
INSERT INTO `r_county` VALUES ('340701', '市辖区', '340700');
INSERT INTO `r_county` VALUES ('340702', '铜官山区', '340700');
INSERT INTO `r_county` VALUES ('340703', '狮子山区', '340700');
INSERT INTO `r_county` VALUES ('340711', '郊　区', '340700');
INSERT INTO `r_county` VALUES ('340721', '铜陵县', '340700');
INSERT INTO `r_county` VALUES ('340801', '市辖区', '340800');
INSERT INTO `r_county` VALUES ('340802', '迎江区', '340800');
INSERT INTO `r_county` VALUES ('340803', '大观区', '340800');
INSERT INTO `r_county` VALUES ('340811', '郊　区', '340800');
INSERT INTO `r_county` VALUES ('340822', '怀宁县', '340800');
INSERT INTO `r_county` VALUES ('340823', '枞阳县', '340800');
INSERT INTO `r_county` VALUES ('340824', '潜山县', '340800');
INSERT INTO `r_county` VALUES ('340825', '太湖县', '340800');
INSERT INTO `r_county` VALUES ('340826', '宿松县', '340800');
INSERT INTO `r_county` VALUES ('340827', '望江县', '340800');
INSERT INTO `r_county` VALUES ('340828', '岳西县', '340800');
INSERT INTO `r_county` VALUES ('340881', '桐城市', '340800');
INSERT INTO `r_county` VALUES ('341001', '市辖区', '341000');
INSERT INTO `r_county` VALUES ('341002', '屯溪区', '341000');
INSERT INTO `r_county` VALUES ('341003', '黄山区', '341000');
INSERT INTO `r_county` VALUES ('341004', '徽州区', '341000');
INSERT INTO `r_county` VALUES ('341021', '歙　县', '341000');
INSERT INTO `r_county` VALUES ('341022', '休宁县', '341000');
INSERT INTO `r_county` VALUES ('341023', '黟　县', '341000');
INSERT INTO `r_county` VALUES ('341024', '祁门县', '341000');
INSERT INTO `r_county` VALUES ('341101', '市辖区', '341100');
INSERT INTO `r_county` VALUES ('341102', '琅琊区', '341100');
INSERT INTO `r_county` VALUES ('341103', '南谯区', '341100');
INSERT INTO `r_county` VALUES ('341122', '来安县', '341100');
INSERT INTO `r_county` VALUES ('341124', '全椒县', '341100');
INSERT INTO `r_county` VALUES ('341125', '定远县', '341100');
INSERT INTO `r_county` VALUES ('341126', '凤阳县', '341100');
INSERT INTO `r_county` VALUES ('341181', '天长市', '341100');
INSERT INTO `r_county` VALUES ('341182', '明光市', '341100');
INSERT INTO `r_county` VALUES ('341201', '市辖区', '341200');
INSERT INTO `r_county` VALUES ('341202', '颍州区', '341200');
INSERT INTO `r_county` VALUES ('341203', '颍东区', '341200');
INSERT INTO `r_county` VALUES ('341204', '颍泉区', '341200');
INSERT INTO `r_county` VALUES ('341221', '临泉县', '341200');
INSERT INTO `r_county` VALUES ('341222', '太和县', '341200');
INSERT INTO `r_county` VALUES ('341225', '阜南县', '341200');
INSERT INTO `r_county` VALUES ('341226', '颍上县', '341200');
INSERT INTO `r_county` VALUES ('341282', '界首市', '341200');
INSERT INTO `r_county` VALUES ('341301', '市辖区', '341300');
INSERT INTO `r_county` VALUES ('341302', '墉桥区', '341300');
INSERT INTO `r_county` VALUES ('341321', '砀山县', '341300');
INSERT INTO `r_county` VALUES ('341322', '萧　县', '341300');
INSERT INTO `r_county` VALUES ('341323', '灵璧县', '341300');
INSERT INTO `r_county` VALUES ('341324', '泗　县', '341300');
INSERT INTO `r_county` VALUES ('341401', '市辖区', '341400');
INSERT INTO `r_county` VALUES ('341402', '居巢区', '341400');
INSERT INTO `r_county` VALUES ('341421', '庐江县', '341400');
INSERT INTO `r_county` VALUES ('341422', '无为县', '341400');
INSERT INTO `r_county` VALUES ('341423', '含山县', '341400');
INSERT INTO `r_county` VALUES ('341424', '和　县', '341400');
INSERT INTO `r_county` VALUES ('341501', '市辖区', '341500');
INSERT INTO `r_county` VALUES ('341502', '金安区', '341500');
INSERT INTO `r_county` VALUES ('341503', '裕安区', '341500');
INSERT INTO `r_county` VALUES ('341521', '寿　县', '341500');
INSERT INTO `r_county` VALUES ('341522', '霍邱县', '341500');
INSERT INTO `r_county` VALUES ('341523', '舒城县', '341500');
INSERT INTO `r_county` VALUES ('341524', '金寨县', '341500');
INSERT INTO `r_county` VALUES ('341525', '霍山县', '341500');
INSERT INTO `r_county` VALUES ('341601', '市辖区', '341600');
INSERT INTO `r_county` VALUES ('341602', '谯城区', '341600');
INSERT INTO `r_county` VALUES ('341621', '涡阳县', '341600');
INSERT INTO `r_county` VALUES ('341622', '蒙城县', '341600');
INSERT INTO `r_county` VALUES ('341623', '利辛县', '341600');
INSERT INTO `r_county` VALUES ('341701', '市辖区', '341700');
INSERT INTO `r_county` VALUES ('341702', '贵池区', '341700');
INSERT INTO `r_county` VALUES ('341721', '东至县', '341700');
INSERT INTO `r_county` VALUES ('341722', '石台县', '341700');
INSERT INTO `r_county` VALUES ('341723', '青阳县', '341700');
INSERT INTO `r_county` VALUES ('341801', '市辖区', '341800');
INSERT INTO `r_county` VALUES ('341802', '宣州区', '341800');
INSERT INTO `r_county` VALUES ('341821', '郎溪县', '341800');
INSERT INTO `r_county` VALUES ('341822', '广德县', '341800');
INSERT INTO `r_county` VALUES ('341823', '泾　县', '341800');
INSERT INTO `r_county` VALUES ('341824', '绩溪县', '341800');
INSERT INTO `r_county` VALUES ('341825', '旌德县', '341800');
INSERT INTO `r_county` VALUES ('341881', '宁国市', '341800');
INSERT INTO `r_county` VALUES ('350101', '市辖区', '350100');
INSERT INTO `r_county` VALUES ('350102', '鼓楼区', '350100');
INSERT INTO `r_county` VALUES ('350103', '台江区', '350100');
INSERT INTO `r_county` VALUES ('350104', '仓山区', '350100');
INSERT INTO `r_county` VALUES ('350105', '马尾区', '350100');
INSERT INTO `r_county` VALUES ('350111', '晋安区', '350100');
INSERT INTO `r_county` VALUES ('350121', '闽侯县', '350100');
INSERT INTO `r_county` VALUES ('350122', '连江县', '350100');
INSERT INTO `r_county` VALUES ('350123', '罗源县', '350100');
INSERT INTO `r_county` VALUES ('350124', '闽清县', '350100');
INSERT INTO `r_county` VALUES ('350125', '永泰县', '350100');
INSERT INTO `r_county` VALUES ('350128', '平潭县', '350100');
INSERT INTO `r_county` VALUES ('350181', '福清市', '350100');
INSERT INTO `r_county` VALUES ('350182', '长乐市', '350100');
INSERT INTO `r_county` VALUES ('350201', '市辖区', '350200');
INSERT INTO `r_county` VALUES ('350203', '思明区', '350200');
INSERT INTO `r_county` VALUES ('350205', '海沧区', '350200');
INSERT INTO `r_county` VALUES ('350206', '湖里区', '350200');
INSERT INTO `r_county` VALUES ('350211', '集美区', '350200');
INSERT INTO `r_county` VALUES ('350212', '同安区', '350200');
INSERT INTO `r_county` VALUES ('350213', '翔安区', '350200');
INSERT INTO `r_county` VALUES ('350301', '市辖区', '350300');
INSERT INTO `r_county` VALUES ('350302', '城厢区', '350300');
INSERT INTO `r_county` VALUES ('350303', '涵江区', '350300');
INSERT INTO `r_county` VALUES ('350304', '荔城区', '350300');
INSERT INTO `r_county` VALUES ('350305', '秀屿区', '350300');
INSERT INTO `r_county` VALUES ('350322', '仙游县', '350300');
INSERT INTO `r_county` VALUES ('350401', '市辖区', '350400');
INSERT INTO `r_county` VALUES ('350402', '梅列区', '350400');
INSERT INTO `r_county` VALUES ('350403', '三元区', '350400');
INSERT INTO `r_county` VALUES ('350421', '明溪县', '350400');
INSERT INTO `r_county` VALUES ('350423', '清流县', '350400');
INSERT INTO `r_county` VALUES ('350424', '宁化县', '350400');
INSERT INTO `r_county` VALUES ('350425', '大田县', '350400');
INSERT INTO `r_county` VALUES ('350426', '尤溪县', '350400');
INSERT INTO `r_county` VALUES ('350427', '沙　县', '350400');
INSERT INTO `r_county` VALUES ('350428', '将乐县', '350400');
INSERT INTO `r_county` VALUES ('350429', '泰宁县', '350400');
INSERT INTO `r_county` VALUES ('350430', '建宁县', '350400');
INSERT INTO `r_county` VALUES ('350481', '永安市', '350400');
INSERT INTO `r_county` VALUES ('350501', '市辖区', '350500');
INSERT INTO `r_county` VALUES ('350502', '鲤城区', '350500');
INSERT INTO `r_county` VALUES ('350503', '丰泽区', '350500');
INSERT INTO `r_county` VALUES ('350504', '洛江区', '350500');
INSERT INTO `r_county` VALUES ('350505', '泉港区', '350500');
INSERT INTO `r_county` VALUES ('350521', '惠安县', '350500');
INSERT INTO `r_county` VALUES ('350524', '安溪县', '350500');
INSERT INTO `r_county` VALUES ('350525', '永春县', '350500');
INSERT INTO `r_county` VALUES ('350526', '德化县', '350500');
INSERT INTO `r_county` VALUES ('350527', '金门县', '350500');
INSERT INTO `r_county` VALUES ('350581', '石狮市', '350500');
INSERT INTO `r_county` VALUES ('350582', '晋江市', '350500');
INSERT INTO `r_county` VALUES ('350583', '南安市', '350500');
INSERT INTO `r_county` VALUES ('350601', '市辖区', '350600');
INSERT INTO `r_county` VALUES ('350602', '芗城区', '350600');
INSERT INTO `r_county` VALUES ('350603', '龙文区', '350600');
INSERT INTO `r_county` VALUES ('350622', '云霄县', '350600');
INSERT INTO `r_county` VALUES ('350623', '漳浦县', '350600');
INSERT INTO `r_county` VALUES ('350624', '诏安县', '350600');
INSERT INTO `r_county` VALUES ('350625', '长泰县', '350600');
INSERT INTO `r_county` VALUES ('350626', '东山县', '350600');
INSERT INTO `r_county` VALUES ('350627', '南靖县', '350600');
INSERT INTO `r_county` VALUES ('350628', '平和县', '350600');
INSERT INTO `r_county` VALUES ('350629', '华安县', '350600');
INSERT INTO `r_county` VALUES ('350681', '龙海市', '350600');
INSERT INTO `r_county` VALUES ('350701', '市辖区', '350700');
INSERT INTO `r_county` VALUES ('350702', '延平区', '350700');
INSERT INTO `r_county` VALUES ('350721', '顺昌县', '350700');
INSERT INTO `r_county` VALUES ('350722', '浦城县', '350700');
INSERT INTO `r_county` VALUES ('350723', '光泽县', '350700');
INSERT INTO `r_county` VALUES ('350724', '松溪县', '350700');
INSERT INTO `r_county` VALUES ('350725', '政和县', '350700');
INSERT INTO `r_county` VALUES ('350781', '邵武市', '350700');
INSERT INTO `r_county` VALUES ('350782', '武夷山市', '350700');
INSERT INTO `r_county` VALUES ('350783', '建瓯市', '350700');
INSERT INTO `r_county` VALUES ('350784', '建阳市', '350700');
INSERT INTO `r_county` VALUES ('350801', '市辖区', '350800');
INSERT INTO `r_county` VALUES ('350802', '新罗区', '350800');
INSERT INTO `r_county` VALUES ('350821', '长汀县', '350800');
INSERT INTO `r_county` VALUES ('350822', '永定县', '350800');
INSERT INTO `r_county` VALUES ('350823', '上杭县', '350800');
INSERT INTO `r_county` VALUES ('350824', '武平县', '350800');
INSERT INTO `r_county` VALUES ('350825', '连城县', '350800');
INSERT INTO `r_county` VALUES ('350881', '漳平市', '350800');
INSERT INTO `r_county` VALUES ('350901', '市辖区', '350900');
INSERT INTO `r_county` VALUES ('350902', '蕉城区', '350900');
INSERT INTO `r_county` VALUES ('350921', '霞浦县', '350900');
INSERT INTO `r_county` VALUES ('350922', '古田县', '350900');
INSERT INTO `r_county` VALUES ('350923', '屏南县', '350900');
INSERT INTO `r_county` VALUES ('350924', '寿宁县', '350900');
INSERT INTO `r_county` VALUES ('350925', '周宁县', '350900');
INSERT INTO `r_county` VALUES ('350926', '柘荣县', '350900');
INSERT INTO `r_county` VALUES ('350981', '福安市', '350900');
INSERT INTO `r_county` VALUES ('350982', '福鼎市', '350900');
INSERT INTO `r_county` VALUES ('360101', '市辖区', '360100');
INSERT INTO `r_county` VALUES ('360102', '东湖区', '360100');
INSERT INTO `r_county` VALUES ('360103', '西湖区', '360100');
INSERT INTO `r_county` VALUES ('360104', '青云谱区', '360100');
INSERT INTO `r_county` VALUES ('360105', '湾里区', '360100');
INSERT INTO `r_county` VALUES ('360111', '青山湖区', '360100');
INSERT INTO `r_county` VALUES ('360121', '南昌县', '360100');
INSERT INTO `r_county` VALUES ('360122', '新建县', '360100');
INSERT INTO `r_county` VALUES ('360123', '安义县', '360100');
INSERT INTO `r_county` VALUES ('360124', '进贤县', '360100');
INSERT INTO `r_county` VALUES ('360201', '市辖区', '360200');
INSERT INTO `r_county` VALUES ('360202', '昌江区', '360200');
INSERT INTO `r_county` VALUES ('360203', '珠山区', '360200');
INSERT INTO `r_county` VALUES ('360222', '浮梁县', '360200');
INSERT INTO `r_county` VALUES ('360281', '乐平市', '360200');
INSERT INTO `r_county` VALUES ('360301', '市辖区', '360300');
INSERT INTO `r_county` VALUES ('360302', '安源区', '360300');
INSERT INTO `r_county` VALUES ('360313', '湘东区', '360300');
INSERT INTO `r_county` VALUES ('360321', '莲花县', '360300');
INSERT INTO `r_county` VALUES ('360322', '上栗县', '360300');
INSERT INTO `r_county` VALUES ('360323', '芦溪县', '360300');
INSERT INTO `r_county` VALUES ('360401', '市辖区', '360400');
INSERT INTO `r_county` VALUES ('360402', '庐山区', '360400');
INSERT INTO `r_county` VALUES ('360403', '浔阳区', '360400');
INSERT INTO `r_county` VALUES ('360421', '九江县', '360400');
INSERT INTO `r_county` VALUES ('360423', '武宁县', '360400');
INSERT INTO `r_county` VALUES ('360424', '修水县', '360400');
INSERT INTO `r_county` VALUES ('360425', '永修县', '360400');
INSERT INTO `r_county` VALUES ('360426', '德安县', '360400');
INSERT INTO `r_county` VALUES ('360427', '星子县', '360400');
INSERT INTO `r_county` VALUES ('360428', '都昌县', '360400');
INSERT INTO `r_county` VALUES ('360429', '湖口县', '360400');
INSERT INTO `r_county` VALUES ('360430', '彭泽县', '360400');
INSERT INTO `r_county` VALUES ('360481', '瑞昌市', '360400');
INSERT INTO `r_county` VALUES ('360501', '市辖区', '360500');
INSERT INTO `r_county` VALUES ('360502', '渝水区', '360500');
INSERT INTO `r_county` VALUES ('360521', '分宜县', '360500');
INSERT INTO `r_county` VALUES ('360601', '市辖区', '360600');
INSERT INTO `r_county` VALUES ('360602', '月湖区', '360600');
INSERT INTO `r_county` VALUES ('360622', '余江县', '360600');
INSERT INTO `r_county` VALUES ('360681', '贵溪市', '360600');
INSERT INTO `r_county` VALUES ('360701', '市辖区', '360700');
INSERT INTO `r_county` VALUES ('360702', '章贡区', '360700');
INSERT INTO `r_county` VALUES ('360721', '赣　县', '360700');
INSERT INTO `r_county` VALUES ('360722', '信丰县', '360700');
INSERT INTO `r_county` VALUES ('360723', '大余县', '360700');
INSERT INTO `r_county` VALUES ('360724', '上犹县', '360700');
INSERT INTO `r_county` VALUES ('360725', '崇义县', '360700');
INSERT INTO `r_county` VALUES ('360726', '安远县', '360700');
INSERT INTO `r_county` VALUES ('360727', '龙南县', '360700');
INSERT INTO `r_county` VALUES ('360728', '定南县', '360700');
INSERT INTO `r_county` VALUES ('360729', '全南县', '360700');
INSERT INTO `r_county` VALUES ('360730', '宁都县', '360700');
INSERT INTO `r_county` VALUES ('360731', '于都县', '360700');
INSERT INTO `r_county` VALUES ('360732', '兴国县', '360700');
INSERT INTO `r_county` VALUES ('360733', '会昌县', '360700');
INSERT INTO `r_county` VALUES ('360734', '寻乌县', '360700');
INSERT INTO `r_county` VALUES ('360735', '石城县', '360700');
INSERT INTO `r_county` VALUES ('360781', '瑞金市', '360700');
INSERT INTO `r_county` VALUES ('360782', '南康市', '360700');
INSERT INTO `r_county` VALUES ('360801', '市辖区', '360800');
INSERT INTO `r_county` VALUES ('360802', '吉州区', '360800');
INSERT INTO `r_county` VALUES ('360803', '青原区', '360800');
INSERT INTO `r_county` VALUES ('360821', '吉安县', '360800');
INSERT INTO `r_county` VALUES ('360822', '吉水县', '360800');
INSERT INTO `r_county` VALUES ('360823', '峡江县', '360800');
INSERT INTO `r_county` VALUES ('360824', '新干县', '360800');
INSERT INTO `r_county` VALUES ('360825', '永丰县', '360800');
INSERT INTO `r_county` VALUES ('360826', '泰和县', '360800');
INSERT INTO `r_county` VALUES ('360827', '遂川县', '360800');
INSERT INTO `r_county` VALUES ('360828', '万安县', '360800');
INSERT INTO `r_county` VALUES ('360829', '安福县', '360800');
INSERT INTO `r_county` VALUES ('360830', '永新县', '360800');
INSERT INTO `r_county` VALUES ('360881', '井冈山市', '360800');
INSERT INTO `r_county` VALUES ('360901', '市辖区', '360900');
INSERT INTO `r_county` VALUES ('360902', '袁州区', '360900');
INSERT INTO `r_county` VALUES ('360921', '奉新县', '360900');
INSERT INTO `r_county` VALUES ('360922', '万载县', '360900');
INSERT INTO `r_county` VALUES ('360923', '上高县', '360900');
INSERT INTO `r_county` VALUES ('360924', '宜丰县', '360900');
INSERT INTO `r_county` VALUES ('360925', '靖安县', '360900');
INSERT INTO `r_county` VALUES ('360926', '铜鼓县', '360900');
INSERT INTO `r_county` VALUES ('360981', '丰城市', '360900');
INSERT INTO `r_county` VALUES ('360982', '樟树市', '360900');
INSERT INTO `r_county` VALUES ('360983', '高安市', '360900');
INSERT INTO `r_county` VALUES ('361001', '市辖区', '361000');
INSERT INTO `r_county` VALUES ('361002', '临川区', '361000');
INSERT INTO `r_county` VALUES ('361021', '南城县', '361000');
INSERT INTO `r_county` VALUES ('361022', '黎川县', '361000');
INSERT INTO `r_county` VALUES ('361023', '南丰县', '361000');
INSERT INTO `r_county` VALUES ('361024', '崇仁县', '361000');
INSERT INTO `r_county` VALUES ('361025', '乐安县', '361000');
INSERT INTO `r_county` VALUES ('361026', '宜黄县', '361000');
INSERT INTO `r_county` VALUES ('361027', '金溪县', '361000');
INSERT INTO `r_county` VALUES ('361028', '资溪县', '361000');
INSERT INTO `r_county` VALUES ('361029', '东乡县', '361000');
INSERT INTO `r_county` VALUES ('361030', '广昌县', '361000');
INSERT INTO `r_county` VALUES ('361101', '市辖区', '361100');
INSERT INTO `r_county` VALUES ('361102', '信州区', '361100');
INSERT INTO `r_county` VALUES ('361121', '上饶县', '361100');
INSERT INTO `r_county` VALUES ('361122', '广丰县', '361100');
INSERT INTO `r_county` VALUES ('361123', '玉山县', '361100');
INSERT INTO `r_county` VALUES ('361124', '铅山县', '361100');
INSERT INTO `r_county` VALUES ('361125', '横峰县', '361100');
INSERT INTO `r_county` VALUES ('361126', '弋阳县', '361100');
INSERT INTO `r_county` VALUES ('361127', '余干县', '361100');
INSERT INTO `r_county` VALUES ('361128', '鄱阳县', '361100');
INSERT INTO `r_county` VALUES ('361129', '万年县', '361100');
INSERT INTO `r_county` VALUES ('361130', '婺源县', '361100');
INSERT INTO `r_county` VALUES ('361181', '德兴市', '361100');
INSERT INTO `r_county` VALUES ('370101', '市辖区', '370100');
INSERT INTO `r_county` VALUES ('370102', '历下区', '370100');
INSERT INTO `r_county` VALUES ('370103', '市中区', '370100');
INSERT INTO `r_county` VALUES ('370104', '槐荫区', '370100');
INSERT INTO `r_county` VALUES ('370105', '天桥区', '370100');
INSERT INTO `r_county` VALUES ('370112', '历城区', '370100');
INSERT INTO `r_county` VALUES ('370113', '长清区', '370100');
INSERT INTO `r_county` VALUES ('370124', '平阴县', '370100');
INSERT INTO `r_county` VALUES ('370125', '济阳县', '370100');
INSERT INTO `r_county` VALUES ('370126', '商河县', '370100');
INSERT INTO `r_county` VALUES ('370181', '章丘市', '370100');
INSERT INTO `r_county` VALUES ('370201', '市辖区', '370200');
INSERT INTO `r_county` VALUES ('370202', '市南区', '370200');
INSERT INTO `r_county` VALUES ('370203', '市北区', '370200');
INSERT INTO `r_county` VALUES ('370205', '四方区', '370200');
INSERT INTO `r_county` VALUES ('370211', '黄岛区', '370200');
INSERT INTO `r_county` VALUES ('370212', '崂山区', '370200');
INSERT INTO `r_county` VALUES ('370213', '李沧区', '370200');
INSERT INTO `r_county` VALUES ('370214', '城阳区', '370200');
INSERT INTO `r_county` VALUES ('370281', '胶州市', '370200');
INSERT INTO `r_county` VALUES ('370282', '即墨市', '370200');
INSERT INTO `r_county` VALUES ('370283', '平度市', '370200');
INSERT INTO `r_county` VALUES ('370284', '胶南市', '370200');
INSERT INTO `r_county` VALUES ('370285', '莱西市', '370200');
INSERT INTO `r_county` VALUES ('370301', '市辖区', '370300');
INSERT INTO `r_county` VALUES ('370302', '淄川区', '370300');
INSERT INTO `r_county` VALUES ('370303', '张店区', '370300');
INSERT INTO `r_county` VALUES ('370304', '博山区', '370300');
INSERT INTO `r_county` VALUES ('370305', '临淄区', '370300');
INSERT INTO `r_county` VALUES ('370306', '周村区', '370300');
INSERT INTO `r_county` VALUES ('370321', '桓台县', '370300');
INSERT INTO `r_county` VALUES ('370322', '高青县', '370300');
INSERT INTO `r_county` VALUES ('370323', '沂源县', '370300');
INSERT INTO `r_county` VALUES ('370401', '市辖区', '370400');
INSERT INTO `r_county` VALUES ('370402', '市中区', '370400');
INSERT INTO `r_county` VALUES ('370403', '薛城区', '370400');
INSERT INTO `r_county` VALUES ('370404', '峄城区', '370400');
INSERT INTO `r_county` VALUES ('370405', '台儿庄区', '370400');
INSERT INTO `r_county` VALUES ('370406', '山亭区', '370400');
INSERT INTO `r_county` VALUES ('370481', '滕州市', '370400');
INSERT INTO `r_county` VALUES ('370501', '市辖区', '370500');
INSERT INTO `r_county` VALUES ('370502', '东营区', '370500');
INSERT INTO `r_county` VALUES ('370503', '河口区', '370500');
INSERT INTO `r_county` VALUES ('370521', '垦利县', '370500');
INSERT INTO `r_county` VALUES ('370522', '利津县', '370500');
INSERT INTO `r_county` VALUES ('370523', '广饶县', '370500');
INSERT INTO `r_county` VALUES ('370601', '市辖区', '370600');
INSERT INTO `r_county` VALUES ('370602', '芝罘区', '370600');
INSERT INTO `r_county` VALUES ('370611', '福山区', '370600');
INSERT INTO `r_county` VALUES ('370612', '牟平区', '370600');
INSERT INTO `r_county` VALUES ('370613', '莱山区', '370600');
INSERT INTO `r_county` VALUES ('370634', '长岛县', '370600');
INSERT INTO `r_county` VALUES ('370681', '龙口市', '370600');
INSERT INTO `r_county` VALUES ('370682', '莱阳市', '370600');
INSERT INTO `r_county` VALUES ('370683', '莱州市', '370600');
INSERT INTO `r_county` VALUES ('370684', '蓬莱市', '370600');
INSERT INTO `r_county` VALUES ('370685', '招远市', '370600');
INSERT INTO `r_county` VALUES ('370686', '栖霞市', '370600');
INSERT INTO `r_county` VALUES ('370687', '海阳市', '370600');
INSERT INTO `r_county` VALUES ('370701', '市辖区', '370700');
INSERT INTO `r_county` VALUES ('370702', '潍城区', '370700');
INSERT INTO `r_county` VALUES ('370703', '寒亭区', '370700');
INSERT INTO `r_county` VALUES ('370704', '坊子区', '370700');
INSERT INTO `r_county` VALUES ('370705', '奎文区', '370700');
INSERT INTO `r_county` VALUES ('370724', '临朐县', '370700');
INSERT INTO `r_county` VALUES ('370725', '昌乐县', '370700');
INSERT INTO `r_county` VALUES ('370781', '青州市', '370700');
INSERT INTO `r_county` VALUES ('370782', '诸城市', '370700');
INSERT INTO `r_county` VALUES ('370783', '寿光市', '370700');
INSERT INTO `r_county` VALUES ('370784', '安丘市', '370700');
INSERT INTO `r_county` VALUES ('370785', '高密市', '370700');
INSERT INTO `r_county` VALUES ('370786', '昌邑市', '370700');
INSERT INTO `r_county` VALUES ('370801', '市辖区', '370800');
INSERT INTO `r_county` VALUES ('370802', '市中区', '370800');
INSERT INTO `r_county` VALUES ('370811', '任城区', '370800');
INSERT INTO `r_county` VALUES ('370826', '微山县', '370800');
INSERT INTO `r_county` VALUES ('370827', '鱼台县', '370800');
INSERT INTO `r_county` VALUES ('370828', '金乡县', '370800');
INSERT INTO `r_county` VALUES ('370829', '嘉祥县', '370800');
INSERT INTO `r_county` VALUES ('370830', '汶上县', '370800');
INSERT INTO `r_county` VALUES ('370831', '泗水县', '370800');
INSERT INTO `r_county` VALUES ('370832', '梁山县', '370800');
INSERT INTO `r_county` VALUES ('370881', '曲阜市', '370800');
INSERT INTO `r_county` VALUES ('370882', '兖州市', '370800');
INSERT INTO `r_county` VALUES ('370883', '邹城市', '370800');
INSERT INTO `r_county` VALUES ('370901', '市辖区', '370900');
INSERT INTO `r_county` VALUES ('370902', '泰山区', '370900');
INSERT INTO `r_county` VALUES ('370903', '岱岳区', '370900');
INSERT INTO `r_county` VALUES ('370921', '宁阳县', '370900');
INSERT INTO `r_county` VALUES ('370923', '东平县', '370900');
INSERT INTO `r_county` VALUES ('370982', '新泰市', '370900');
INSERT INTO `r_county` VALUES ('370983', '肥城市', '370900');
INSERT INTO `r_county` VALUES ('371001', '市辖区', '371000');
INSERT INTO `r_county` VALUES ('371002', '环翠区', '371000');
INSERT INTO `r_county` VALUES ('371081', '文登市', '371000');
INSERT INTO `r_county` VALUES ('371082', '荣成市', '371000');
INSERT INTO `r_county` VALUES ('371083', '乳山市', '371000');
INSERT INTO `r_county` VALUES ('371101', '市辖区', '371100');
INSERT INTO `r_county` VALUES ('371102', '东港区', '371100');
INSERT INTO `r_county` VALUES ('371103', '岚山区', '371100');
INSERT INTO `r_county` VALUES ('371121', '五莲县', '371100');
INSERT INTO `r_county` VALUES ('371122', '莒　县', '371100');
INSERT INTO `r_county` VALUES ('371201', '市辖区', '371200');
INSERT INTO `r_county` VALUES ('371202', '莱城区', '371200');
INSERT INTO `r_county` VALUES ('371203', '钢城区', '371200');
INSERT INTO `r_county` VALUES ('371301', '市辖区', '371300');
INSERT INTO `r_county` VALUES ('371302', '兰山区', '371300');
INSERT INTO `r_county` VALUES ('371311', '罗庄区', '371300');
INSERT INTO `r_county` VALUES ('371312', '河东区', '371300');
INSERT INTO `r_county` VALUES ('371321', '沂南县', '371300');
INSERT INTO `r_county` VALUES ('371322', '郯城县', '371300');
INSERT INTO `r_county` VALUES ('371323', '沂水县', '371300');
INSERT INTO `r_county` VALUES ('371324', '苍山县', '371300');
INSERT INTO `r_county` VALUES ('371325', '费　县', '371300');
INSERT INTO `r_county` VALUES ('371326', '平邑县', '371300');
INSERT INTO `r_county` VALUES ('371327', '莒南县', '371300');
INSERT INTO `r_county` VALUES ('371328', '蒙阴县', '371300');
INSERT INTO `r_county` VALUES ('371329', '临沭县', '371300');
INSERT INTO `r_county` VALUES ('371401', '市辖区', '371400');
INSERT INTO `r_county` VALUES ('371402', '德城区', '371400');
INSERT INTO `r_county` VALUES ('371421', '陵　县', '371400');
INSERT INTO `r_county` VALUES ('371422', '宁津县', '371400');
INSERT INTO `r_county` VALUES ('371423', '庆云县', '371400');
INSERT INTO `r_county` VALUES ('371424', '临邑县', '371400');
INSERT INTO `r_county` VALUES ('371425', '齐河县', '371400');
INSERT INTO `r_county` VALUES ('371426', '平原县', '371400');
INSERT INTO `r_county` VALUES ('371427', '夏津县', '371400');
INSERT INTO `r_county` VALUES ('371428', '武城县', '371400');
INSERT INTO `r_county` VALUES ('371481', '乐陵市', '371400');
INSERT INTO `r_county` VALUES ('371482', '禹城市', '371400');
INSERT INTO `r_county` VALUES ('371501', '市辖区', '371500');
INSERT INTO `r_county` VALUES ('371502', '东昌府区', '371500');
INSERT INTO `r_county` VALUES ('371521', '阳谷县', '371500');
INSERT INTO `r_county` VALUES ('371522', '莘　县', '371500');
INSERT INTO `r_county` VALUES ('371523', '茌平县', '371500');
INSERT INTO `r_county` VALUES ('371524', '东阿县', '371500');
INSERT INTO `r_county` VALUES ('371525', '冠　县', '371500');
INSERT INTO `r_county` VALUES ('371526', '高唐县', '371500');
INSERT INTO `r_county` VALUES ('371581', '临清市', '371500');
INSERT INTO `r_county` VALUES ('371601', '市辖区', '371600');
INSERT INTO `r_county` VALUES ('371602', '滨城区', '371600');
INSERT INTO `r_county` VALUES ('371621', '惠民县', '371600');
INSERT INTO `r_county` VALUES ('371622', '阳信县', '371600');
INSERT INTO `r_county` VALUES ('371623', '无棣县', '371600');
INSERT INTO `r_county` VALUES ('371624', '沾化县', '371600');
INSERT INTO `r_county` VALUES ('371625', '博兴县', '371600');
INSERT INTO `r_county` VALUES ('371626', '邹平县', '371600');
INSERT INTO `r_county` VALUES ('371701', '市辖区', '371700');
INSERT INTO `r_county` VALUES ('371702', '牡丹区', '371700');
INSERT INTO `r_county` VALUES ('371721', '曹　县', '371700');
INSERT INTO `r_county` VALUES ('371722', '单　县', '371700');
INSERT INTO `r_county` VALUES ('371723', '成武县', '371700');
INSERT INTO `r_county` VALUES ('371724', '巨野县', '371700');
INSERT INTO `r_county` VALUES ('371725', '郓城县', '371700');
INSERT INTO `r_county` VALUES ('371726', '鄄城县', '371700');
INSERT INTO `r_county` VALUES ('371727', '定陶县', '371700');
INSERT INTO `r_county` VALUES ('371728', '东明县', '371700');
INSERT INTO `r_county` VALUES ('410101', '市辖区', '410100');
INSERT INTO `r_county` VALUES ('410102', '中原区', '410100');
INSERT INTO `r_county` VALUES ('410103', '二七区', '410100');
INSERT INTO `r_county` VALUES ('410104', '管城回族区', '410100');
INSERT INTO `r_county` VALUES ('410105', '金水区', '410100');
INSERT INTO `r_county` VALUES ('410106', '上街区', '410100');
INSERT INTO `r_county` VALUES ('410108', '邙山区', '410100');
INSERT INTO `r_county` VALUES ('410122', '中牟县', '410100');
INSERT INTO `r_county` VALUES ('410181', '巩义市', '410100');
INSERT INTO `r_county` VALUES ('410182', '荥阳市', '410100');
INSERT INTO `r_county` VALUES ('410183', '新密市', '410100');
INSERT INTO `r_county` VALUES ('410184', '新郑市', '410100');
INSERT INTO `r_county` VALUES ('410185', '登封市', '410100');
INSERT INTO `r_county` VALUES ('410201', '市辖区', '410200');
INSERT INTO `r_county` VALUES ('410202', '龙亭区', '410200');
INSERT INTO `r_county` VALUES ('410203', '顺河回族区', '410200');
INSERT INTO `r_county` VALUES ('410204', '鼓楼区', '410200');
INSERT INTO `r_county` VALUES ('410205', '南关区', '410200');
INSERT INTO `r_county` VALUES ('410211', '郊　区', '410200');
INSERT INTO `r_county` VALUES ('410221', '杞　县', '410200');
INSERT INTO `r_county` VALUES ('410222', '通许县', '410200');
INSERT INTO `r_county` VALUES ('410223', '尉氏县', '410200');
INSERT INTO `r_county` VALUES ('410224', '开封县', '410200');
INSERT INTO `r_county` VALUES ('410225', '兰考县', '410200');
INSERT INTO `r_county` VALUES ('410301', '市辖区', '410300');
INSERT INTO `r_county` VALUES ('410302', '老城区', '410300');
INSERT INTO `r_county` VALUES ('410303', '西工区', '410300');
INSERT INTO `r_county` VALUES ('410304', '廛河回族区', '410300');
INSERT INTO `r_county` VALUES ('410305', '涧西区', '410300');
INSERT INTO `r_county` VALUES ('410306', '吉利区', '410300');
INSERT INTO `r_county` VALUES ('410307', '洛龙区', '410300');
INSERT INTO `r_county` VALUES ('410322', '孟津县', '410300');
INSERT INTO `r_county` VALUES ('410323', '新安县', '410300');
INSERT INTO `r_county` VALUES ('410324', '栾川县', '410300');
INSERT INTO `r_county` VALUES ('410325', '嵩　县', '410300');
INSERT INTO `r_county` VALUES ('410326', '汝阳县', '410300');
INSERT INTO `r_county` VALUES ('410327', '宜阳县', '410300');
INSERT INTO `r_county` VALUES ('410328', '洛宁县', '410300');
INSERT INTO `r_county` VALUES ('410329', '伊川县', '410300');
INSERT INTO `r_county` VALUES ('410381', '偃师市', '410300');
INSERT INTO `r_county` VALUES ('410401', '市辖区', '410400');
INSERT INTO `r_county` VALUES ('410402', '新华区', '410400');
INSERT INTO `r_county` VALUES ('410403', '卫东区', '410400');
INSERT INTO `r_county` VALUES ('410404', '石龙区', '410400');
INSERT INTO `r_county` VALUES ('410411', '湛河区', '410400');
INSERT INTO `r_county` VALUES ('410421', '宝丰县', '410400');
INSERT INTO `r_county` VALUES ('410422', '叶　县', '410400');
INSERT INTO `r_county` VALUES ('410423', '鲁山县', '410400');
INSERT INTO `r_county` VALUES ('410425', '郏　县', '410400');
INSERT INTO `r_county` VALUES ('410481', '舞钢市', '410400');
INSERT INTO `r_county` VALUES ('410482', '汝州市', '410400');
INSERT INTO `r_county` VALUES ('410501', '市辖区', '410500');
INSERT INTO `r_county` VALUES ('410502', '文峰区', '410500');
INSERT INTO `r_county` VALUES ('410503', '北关区', '410500');
INSERT INTO `r_county` VALUES ('410505', '殷都区', '410500');
INSERT INTO `r_county` VALUES ('410506', '龙安区', '410500');
INSERT INTO `r_county` VALUES ('410522', '安阳县', '410500');
INSERT INTO `r_county` VALUES ('410523', '汤阴县', '410500');
INSERT INTO `r_county` VALUES ('410526', '滑　县', '410500');
INSERT INTO `r_county` VALUES ('410527', '内黄县', '410500');
INSERT INTO `r_county` VALUES ('410581', '林州市', '410500');
INSERT INTO `r_county` VALUES ('410601', '市辖区', '410600');
INSERT INTO `r_county` VALUES ('410602', '鹤山区', '410600');
INSERT INTO `r_county` VALUES ('410603', '山城区', '410600');
INSERT INTO `r_county` VALUES ('410611', '淇滨区', '410600');
INSERT INTO `r_county` VALUES ('410621', '浚　县', '410600');
INSERT INTO `r_county` VALUES ('410622', '淇　县', '410600');
INSERT INTO `r_county` VALUES ('410701', '市辖区', '410700');
INSERT INTO `r_county` VALUES ('410702', '红旗区', '410700');
INSERT INTO `r_county` VALUES ('410703', '卫滨区', '410700');
INSERT INTO `r_county` VALUES ('410704', '凤泉区', '410700');
INSERT INTO `r_county` VALUES ('410711', '牧野区', '410700');
INSERT INTO `r_county` VALUES ('410721', '新乡县', '410700');
INSERT INTO `r_county` VALUES ('410724', '获嘉县', '410700');
INSERT INTO `r_county` VALUES ('410725', '原阳县', '410700');
INSERT INTO `r_county` VALUES ('410726', '延津县', '410700');
INSERT INTO `r_county` VALUES ('410727', '封丘县', '410700');
INSERT INTO `r_county` VALUES ('410728', '长垣县', '410700');
INSERT INTO `r_county` VALUES ('410781', '卫辉市', '410700');
INSERT INTO `r_county` VALUES ('410782', '辉县市', '410700');
INSERT INTO `r_county` VALUES ('410801', '市辖区', '410800');
INSERT INTO `r_county` VALUES ('410802', '解放区', '410800');
INSERT INTO `r_county` VALUES ('410803', '中站区', '410800');
INSERT INTO `r_county` VALUES ('410804', '马村区', '410800');
INSERT INTO `r_county` VALUES ('410811', '山阳区', '410800');
INSERT INTO `r_county` VALUES ('410821', '修武县', '410800');
INSERT INTO `r_county` VALUES ('410822', '博爱县', '410800');
INSERT INTO `r_county` VALUES ('410823', '武陟县', '410800');
INSERT INTO `r_county` VALUES ('410825', '温　县', '410800');
INSERT INTO `r_county` VALUES ('410881', '济源市', '410800');
INSERT INTO `r_county` VALUES ('410882', '沁阳市', '410800');
INSERT INTO `r_county` VALUES ('410883', '孟州市', '410800');
INSERT INTO `r_county` VALUES ('410901', '市辖区', '410900');
INSERT INTO `r_county` VALUES ('410902', '华龙区', '410900');
INSERT INTO `r_county` VALUES ('410922', '清丰县', '410900');
INSERT INTO `r_county` VALUES ('410923', '南乐县', '410900');
INSERT INTO `r_county` VALUES ('410926', '范　县', '410900');
INSERT INTO `r_county` VALUES ('410927', '台前县', '410900');
INSERT INTO `r_county` VALUES ('410928', '濮阳县', '410900');
INSERT INTO `r_county` VALUES ('411001', '市辖区', '411000');
INSERT INTO `r_county` VALUES ('411002', '魏都区', '411000');
INSERT INTO `r_county` VALUES ('411023', '许昌县', '411000');
INSERT INTO `r_county` VALUES ('411024', '鄢陵县', '411000');
INSERT INTO `r_county` VALUES ('411025', '襄城县', '411000');
INSERT INTO `r_county` VALUES ('411081', '禹州市', '411000');
INSERT INTO `r_county` VALUES ('411082', '长葛市', '411000');
INSERT INTO `r_county` VALUES ('411101', '市辖区', '411100');
INSERT INTO `r_county` VALUES ('411102', '源汇区', '411100');
INSERT INTO `r_county` VALUES ('411103', '郾城区', '411100');
INSERT INTO `r_county` VALUES ('411104', '召陵区', '411100');
INSERT INTO `r_county` VALUES ('411121', '舞阳县', '411100');
INSERT INTO `r_county` VALUES ('411122', '临颍县', '411100');
INSERT INTO `r_county` VALUES ('411201', '市辖区', '411200');
INSERT INTO `r_county` VALUES ('411202', '湖滨区', '411200');
INSERT INTO `r_county` VALUES ('411221', '渑池县', '411200');
INSERT INTO `r_county` VALUES ('411222', '陕　县', '411200');
INSERT INTO `r_county` VALUES ('411224', '卢氏县', '411200');
INSERT INTO `r_county` VALUES ('411281', '义马市', '411200');
INSERT INTO `r_county` VALUES ('411282', '灵宝市', '411200');
INSERT INTO `r_county` VALUES ('411301', '市辖区', '411300');
INSERT INTO `r_county` VALUES ('411302', '宛城区', '411300');
INSERT INTO `r_county` VALUES ('411303', '卧龙区', '411300');
INSERT INTO `r_county` VALUES ('411321', '南召县', '411300');
INSERT INTO `r_county` VALUES ('411322', '方城县', '411300');
INSERT INTO `r_county` VALUES ('411323', '西峡县', '411300');
INSERT INTO `r_county` VALUES ('411324', '镇平县', '411300');
INSERT INTO `r_county` VALUES ('411325', '内乡县', '411300');
INSERT INTO `r_county` VALUES ('411326', '淅川县', '411300');
INSERT INTO `r_county` VALUES ('411327', '社旗县', '411300');
INSERT INTO `r_county` VALUES ('411328', '唐河县', '411300');
INSERT INTO `r_county` VALUES ('411329', '新野县', '411300');
INSERT INTO `r_county` VALUES ('411330', '桐柏县', '411300');
INSERT INTO `r_county` VALUES ('411381', '邓州市', '411300');
INSERT INTO `r_county` VALUES ('411401', '市辖区', '411400');
INSERT INTO `r_county` VALUES ('411402', '梁园区', '411400');
INSERT INTO `r_county` VALUES ('411403', '睢阳区', '411400');
INSERT INTO `r_county` VALUES ('411421', '民权县', '411400');
INSERT INTO `r_county` VALUES ('411422', '睢　县', '411400');
INSERT INTO `r_county` VALUES ('411423', '宁陵县', '411400');
INSERT INTO `r_county` VALUES ('411424', '柘城县', '411400');
INSERT INTO `r_county` VALUES ('411425', '虞城县', '411400');
INSERT INTO `r_county` VALUES ('411426', '夏邑县', '411400');
INSERT INTO `r_county` VALUES ('411481', '永城市', '411400');
INSERT INTO `r_county` VALUES ('411501', '市辖区', '411500');
INSERT INTO `r_county` VALUES ('411502', '师河区', '411500');
INSERT INTO `r_county` VALUES ('411503', '平桥区', '411500');
INSERT INTO `r_county` VALUES ('411521', '罗山县', '411500');
INSERT INTO `r_county` VALUES ('411522', '光山县', '411500');
INSERT INTO `r_county` VALUES ('411523', '新　县', '411500');
INSERT INTO `r_county` VALUES ('411524', '商城县', '411500');
INSERT INTO `r_county` VALUES ('411525', '固始县', '411500');
INSERT INTO `r_county` VALUES ('411526', '潢川县', '411500');
INSERT INTO `r_county` VALUES ('411527', '淮滨县', '411500');
INSERT INTO `r_county` VALUES ('411528', '息　县', '411500');
INSERT INTO `r_county` VALUES ('411601', '市辖区', '411600');
INSERT INTO `r_county` VALUES ('411602', '川汇区', '411600');
INSERT INTO `r_county` VALUES ('411621', '扶沟县', '411600');
INSERT INTO `r_county` VALUES ('411622', '西华县', '411600');
INSERT INTO `r_county` VALUES ('411623', '商水县', '411600');
INSERT INTO `r_county` VALUES ('411624', '沈丘县', '411600');
INSERT INTO `r_county` VALUES ('411625', '郸城县', '411600');
INSERT INTO `r_county` VALUES ('411626', '淮阳县', '411600');
INSERT INTO `r_county` VALUES ('411627', '太康县', '411600');
INSERT INTO `r_county` VALUES ('411628', '鹿邑县', '411600');
INSERT INTO `r_county` VALUES ('411681', '项城市', '411600');
INSERT INTO `r_county` VALUES ('411701', '市辖区', '411700');
INSERT INTO `r_county` VALUES ('411702', '驿城区', '411700');
INSERT INTO `r_county` VALUES ('411721', '西平县', '411700');
INSERT INTO `r_county` VALUES ('411722', '上蔡县', '411700');
INSERT INTO `r_county` VALUES ('411723', '平舆县', '411700');
INSERT INTO `r_county` VALUES ('411724', '正阳县', '411700');
INSERT INTO `r_county` VALUES ('411725', '确山县', '411700');
INSERT INTO `r_county` VALUES ('411726', '泌阳县', '411700');
INSERT INTO `r_county` VALUES ('411727', '汝南县', '411700');
INSERT INTO `r_county` VALUES ('411728', '遂平县', '411700');
INSERT INTO `r_county` VALUES ('411729', '新蔡县', '411700');
INSERT INTO `r_county` VALUES ('420101', '市辖区', '420100');
INSERT INTO `r_county` VALUES ('420102', '江岸区', '420100');
INSERT INTO `r_county` VALUES ('420103', '江汉区', '420100');
INSERT INTO `r_county` VALUES ('420104', '乔口区', '420100');
INSERT INTO `r_county` VALUES ('420105', '汉阳区', '420100');
INSERT INTO `r_county` VALUES ('420106', '武昌区', '420100');
INSERT INTO `r_county` VALUES ('420107', '青山区', '420100');
INSERT INTO `r_county` VALUES ('420111', '洪山区', '420100');
INSERT INTO `r_county` VALUES ('420112', '东西湖区', '420100');
INSERT INTO `r_county` VALUES ('420113', '汉南区', '420100');
INSERT INTO `r_county` VALUES ('420114', '蔡甸区', '420100');
INSERT INTO `r_county` VALUES ('420115', '江夏区', '420100');
INSERT INTO `r_county` VALUES ('420116', '黄陂区', '420100');
INSERT INTO `r_county` VALUES ('420117', '新洲区', '420100');
INSERT INTO `r_county` VALUES ('420201', '市辖区', '420200');
INSERT INTO `r_county` VALUES ('420202', '黄石港区', '420200');
INSERT INTO `r_county` VALUES ('420203', '西塞山区', '420200');
INSERT INTO `r_county` VALUES ('420204', '下陆区', '420200');
INSERT INTO `r_county` VALUES ('420205', '铁山区', '420200');
INSERT INTO `r_county` VALUES ('420222', '阳新县', '420200');
INSERT INTO `r_county` VALUES ('420281', '大冶市', '420200');
INSERT INTO `r_county` VALUES ('420301', '市辖区', '420300');
INSERT INTO `r_county` VALUES ('420302', '茅箭区', '420300');
INSERT INTO `r_county` VALUES ('420303', '张湾区', '420300');
INSERT INTO `r_county` VALUES ('420321', '郧　县', '420300');
INSERT INTO `r_county` VALUES ('420322', '郧西县', '420300');
INSERT INTO `r_county` VALUES ('420323', '竹山县', '420300');
INSERT INTO `r_county` VALUES ('420324', '竹溪县', '420300');
INSERT INTO `r_county` VALUES ('420325', '房　县', '420300');
INSERT INTO `r_county` VALUES ('420381', '丹江口市', '420300');
INSERT INTO `r_county` VALUES ('420501', '市辖区', '420500');
INSERT INTO `r_county` VALUES ('420502', '西陵区', '420500');
INSERT INTO `r_county` VALUES ('420503', '伍家岗区', '420500');
INSERT INTO `r_county` VALUES ('420504', '点军区', '420500');
INSERT INTO `r_county` VALUES ('420505', '猇亭区', '420500');
INSERT INTO `r_county` VALUES ('420506', '夷陵区', '420500');
INSERT INTO `r_county` VALUES ('420525', '远安县', '420500');
INSERT INTO `r_county` VALUES ('420526', '兴山县', '420500');
INSERT INTO `r_county` VALUES ('420527', '秭归县', '420500');
INSERT INTO `r_county` VALUES ('420528', '长阳土家族自治县', '420500');
INSERT INTO `r_county` VALUES ('420529', '五峰土家族自治县', '420500');
INSERT INTO `r_county` VALUES ('420581', '宜都市', '420500');
INSERT INTO `r_county` VALUES ('420582', '当阳市', '420500');
INSERT INTO `r_county` VALUES ('420583', '枝江市', '420500');
INSERT INTO `r_county` VALUES ('420601', '市辖区', '420600');
INSERT INTO `r_county` VALUES ('420602', '襄城区', '420600');
INSERT INTO `r_county` VALUES ('420606', '樊城区', '420600');
INSERT INTO `r_county` VALUES ('420607', '襄阳区', '420600');
INSERT INTO `r_county` VALUES ('420624', '南漳县', '420600');
INSERT INTO `r_county` VALUES ('420625', '谷城县', '420600');
INSERT INTO `r_county` VALUES ('420626', '保康县', '420600');
INSERT INTO `r_county` VALUES ('420682', '老河口市', '420600');
INSERT INTO `r_county` VALUES ('420683', '枣阳市', '420600');
INSERT INTO `r_county` VALUES ('420684', '宜城市', '420600');
INSERT INTO `r_county` VALUES ('420701', '市辖区', '420700');
INSERT INTO `r_county` VALUES ('420702', '梁子湖区', '420700');
INSERT INTO `r_county` VALUES ('420703', '华容区', '420700');
INSERT INTO `r_county` VALUES ('420704', '鄂城区', '420700');
INSERT INTO `r_county` VALUES ('420801', '市辖区', '420800');
INSERT INTO `r_county` VALUES ('420802', '东宝区', '420800');
INSERT INTO `r_county` VALUES ('420804', '掇刀区', '420800');
INSERT INTO `r_county` VALUES ('420821', '京山县', '420800');
INSERT INTO `r_county` VALUES ('420822', '沙洋县', '420800');
INSERT INTO `r_county` VALUES ('420881', '钟祥市', '420800');
INSERT INTO `r_county` VALUES ('420901', '市辖区', '420900');
INSERT INTO `r_county` VALUES ('420902', '孝南区', '420900');
INSERT INTO `r_county` VALUES ('420921', '孝昌县', '420900');
INSERT INTO `r_county` VALUES ('420922', '大悟县', '420900');
INSERT INTO `r_county` VALUES ('420923', '云梦县', '420900');
INSERT INTO `r_county` VALUES ('420981', '应城市', '420900');
INSERT INTO `r_county` VALUES ('420982', '安陆市', '420900');
INSERT INTO `r_county` VALUES ('420984', '汉川市', '420900');
INSERT INTO `r_county` VALUES ('421001', '市辖区', '421000');
INSERT INTO `r_county` VALUES ('421002', '沙市区', '421000');
INSERT INTO `r_county` VALUES ('421003', '荆州区', '421000');
INSERT INTO `r_county` VALUES ('421022', '公安县', '421000');
INSERT INTO `r_county` VALUES ('421023', '监利县', '421000');
INSERT INTO `r_county` VALUES ('421024', '江陵县', '421000');
INSERT INTO `r_county` VALUES ('421081', '石首市', '421000');
INSERT INTO `r_county` VALUES ('421083', '洪湖市', '421000');
INSERT INTO `r_county` VALUES ('421087', '松滋市', '421000');
INSERT INTO `r_county` VALUES ('421101', '市辖区', '421100');
INSERT INTO `r_county` VALUES ('421102', '黄州区', '421100');
INSERT INTO `r_county` VALUES ('421121', '团风县', '421100');
INSERT INTO `r_county` VALUES ('421122', '红安县', '421100');
INSERT INTO `r_county` VALUES ('421123', '罗田县', '421100');
INSERT INTO `r_county` VALUES ('421124', '英山县', '421100');
INSERT INTO `r_county` VALUES ('421125', '浠水县', '421100');
INSERT INTO `r_county` VALUES ('421126', '蕲春县', '421100');
INSERT INTO `r_county` VALUES ('421127', '黄梅县', '421100');
INSERT INTO `r_county` VALUES ('421181', '麻城市', '421100');
INSERT INTO `r_county` VALUES ('421182', '武穴市', '421100');
INSERT INTO `r_county` VALUES ('421201', '市辖区', '421200');
INSERT INTO `r_county` VALUES ('421202', '咸安区', '421200');
INSERT INTO `r_county` VALUES ('421221', '嘉鱼县', '421200');
INSERT INTO `r_county` VALUES ('421222', '通城县', '421200');
INSERT INTO `r_county` VALUES ('421223', '崇阳县', '421200');
INSERT INTO `r_county` VALUES ('421224', '通山县', '421200');
INSERT INTO `r_county` VALUES ('421281', '赤壁市', '421200');
INSERT INTO `r_county` VALUES ('421301', '市辖区', '421300');
INSERT INTO `r_county` VALUES ('421302', '曾都区', '421300');
INSERT INTO `r_county` VALUES ('421381', '广水市', '421300');
INSERT INTO `r_county` VALUES ('422801', '恩施市', '422800');
INSERT INTO `r_county` VALUES ('422802', '利川市', '422800');
INSERT INTO `r_county` VALUES ('422822', '建始县', '422800');
INSERT INTO `r_county` VALUES ('422823', '巴东县', '422800');
INSERT INTO `r_county` VALUES ('422825', '宣恩县', '422800');
INSERT INTO `r_county` VALUES ('422826', '咸丰县', '422800');
INSERT INTO `r_county` VALUES ('422827', '来凤县', '422800');
INSERT INTO `r_county` VALUES ('422828', '鹤峰县', '422800');
INSERT INTO `r_county` VALUES ('429004', '仙桃市', '429000');
INSERT INTO `r_county` VALUES ('429005', '潜江市', '429000');
INSERT INTO `r_county` VALUES ('429006', '天门市', '429000');
INSERT INTO `r_county` VALUES ('429021', '神农架林区', '429000');
INSERT INTO `r_county` VALUES ('430101', '市辖区', '430100');
INSERT INTO `r_county` VALUES ('430102', '芙蓉区', '430100');
INSERT INTO `r_county` VALUES ('430103', '天心区', '430100');
INSERT INTO `r_county` VALUES ('430104', '岳麓区', '430100');
INSERT INTO `r_county` VALUES ('430105', '开福区', '430100');
INSERT INTO `r_county` VALUES ('430111', '雨花区', '430100');
INSERT INTO `r_county` VALUES ('430121', '长沙县', '430100');
INSERT INTO `r_county` VALUES ('430122', '望城县', '430100');
INSERT INTO `r_county` VALUES ('430124', '宁乡县', '430100');
INSERT INTO `r_county` VALUES ('430181', '浏阳市', '430100');
INSERT INTO `r_county` VALUES ('430201', '市辖区', '430200');
INSERT INTO `r_county` VALUES ('430202', '荷塘区', '430200');
INSERT INTO `r_county` VALUES ('430203', '芦淞区', '430200');
INSERT INTO `r_county` VALUES ('430204', '石峰区', '430200');
INSERT INTO `r_county` VALUES ('430211', '天元区', '430200');
INSERT INTO `r_county` VALUES ('430221', '株洲县', '430200');
INSERT INTO `r_county` VALUES ('430223', '攸　县', '430200');
INSERT INTO `r_county` VALUES ('430224', '茶陵县', '430200');
INSERT INTO `r_county` VALUES ('430225', '炎陵县', '430200');
INSERT INTO `r_county` VALUES ('430281', '醴陵市', '430200');
INSERT INTO `r_county` VALUES ('430301', '市辖区', '430300');
INSERT INTO `r_county` VALUES ('430302', '雨湖区', '430300');
INSERT INTO `r_county` VALUES ('430304', '岳塘区', '430300');
INSERT INTO `r_county` VALUES ('430321', '湘潭县', '430300');
INSERT INTO `r_county` VALUES ('430381', '湘乡市', '430300');
INSERT INTO `r_county` VALUES ('430382', '韶山市', '430300');
INSERT INTO `r_county` VALUES ('430401', '市辖区', '430400');
INSERT INTO `r_county` VALUES ('430405', '珠晖区', '430400');
INSERT INTO `r_county` VALUES ('430406', '雁峰区', '430400');
INSERT INTO `r_county` VALUES ('430407', '石鼓区', '430400');
INSERT INTO `r_county` VALUES ('430408', '蒸湘区', '430400');
INSERT INTO `r_county` VALUES ('430412', '南岳区', '430400');
INSERT INTO `r_county` VALUES ('430421', '衡阳县', '430400');
INSERT INTO `r_county` VALUES ('430422', '衡南县', '430400');
INSERT INTO `r_county` VALUES ('430423', '衡山县', '430400');
INSERT INTO `r_county` VALUES ('430424', '衡东县', '430400');
INSERT INTO `r_county` VALUES ('430426', '祁东县', '430400');
INSERT INTO `r_county` VALUES ('430481', '耒阳市', '430400');
INSERT INTO `r_county` VALUES ('430482', '常宁市', '430400');
INSERT INTO `r_county` VALUES ('430501', '市辖区', '430500');
INSERT INTO `r_county` VALUES ('430502', '双清区', '430500');
INSERT INTO `r_county` VALUES ('430503', '大祥区', '430500');
INSERT INTO `r_county` VALUES ('430511', '北塔区', '430500');
INSERT INTO `r_county` VALUES ('430521', '邵东县', '430500');
INSERT INTO `r_county` VALUES ('430522', '新邵县', '430500');
INSERT INTO `r_county` VALUES ('430523', '邵阳县', '430500');
INSERT INTO `r_county` VALUES ('430524', '隆回县', '430500');
INSERT INTO `r_county` VALUES ('430525', '洞口县', '430500');
INSERT INTO `r_county` VALUES ('430527', '绥宁县', '430500');
INSERT INTO `r_county` VALUES ('430528', '新宁县', '430500');
INSERT INTO `r_county` VALUES ('430529', '城步苗族自治县', '430500');
INSERT INTO `r_county` VALUES ('430581', '武冈市', '430500');
INSERT INTO `r_county` VALUES ('430601', '市辖区', '430600');
INSERT INTO `r_county` VALUES ('430602', '岳阳楼区', '430600');
INSERT INTO `r_county` VALUES ('430603', '云溪区', '430600');
INSERT INTO `r_county` VALUES ('430611', '君山区', '430600');
INSERT INTO `r_county` VALUES ('430621', '岳阳县', '430600');
INSERT INTO `r_county` VALUES ('430623', '华容县', '430600');
INSERT INTO `r_county` VALUES ('430624', '湘阴县', '430600');
INSERT INTO `r_county` VALUES ('430626', '平江县', '430600');
INSERT INTO `r_county` VALUES ('430681', '汨罗市', '430600');
INSERT INTO `r_county` VALUES ('430682', '临湘市', '430600');
INSERT INTO `r_county` VALUES ('430701', '市辖区', '430700');
INSERT INTO `r_county` VALUES ('430702', '武陵区', '430700');
INSERT INTO `r_county` VALUES ('430703', '鼎城区', '430700');
INSERT INTO `r_county` VALUES ('430721', '安乡县', '430700');
INSERT INTO `r_county` VALUES ('430722', '汉寿县', '430700');
INSERT INTO `r_county` VALUES ('430723', '澧　县', '430700');
INSERT INTO `r_county` VALUES ('430724', '临澧县', '430700');
INSERT INTO `r_county` VALUES ('430725', '桃源县', '430700');
INSERT INTO `r_county` VALUES ('430726', '石门县', '430700');
INSERT INTO `r_county` VALUES ('430781', '津市市', '430700');
INSERT INTO `r_county` VALUES ('430801', '市辖区', '430800');
INSERT INTO `r_county` VALUES ('430802', '永定区', '430800');
INSERT INTO `r_county` VALUES ('430811', '武陵源区', '430800');
INSERT INTO `r_county` VALUES ('430821', '慈利县', '430800');
INSERT INTO `r_county` VALUES ('430822', '桑植县', '430800');
INSERT INTO `r_county` VALUES ('430901', '市辖区', '430900');
INSERT INTO `r_county` VALUES ('430902', '资阳区', '430900');
INSERT INTO `r_county` VALUES ('430903', '赫山区', '430900');
INSERT INTO `r_county` VALUES ('430921', '南　县', '430900');
INSERT INTO `r_county` VALUES ('430922', '桃江县', '430900');
INSERT INTO `r_county` VALUES ('430923', '安化县', '430900');
INSERT INTO `r_county` VALUES ('430981', '沅江市', '430900');
INSERT INTO `r_county` VALUES ('431001', '市辖区', '431000');
INSERT INTO `r_county` VALUES ('431002', '北湖区', '431000');
INSERT INTO `r_county` VALUES ('431003', '苏仙区', '431000');
INSERT INTO `r_county` VALUES ('431021', '桂阳县', '431000');
INSERT INTO `r_county` VALUES ('431022', '宜章县', '431000');
INSERT INTO `r_county` VALUES ('431023', '永兴县', '431000');
INSERT INTO `r_county` VALUES ('431024', '嘉禾县', '431000');
INSERT INTO `r_county` VALUES ('431025', '临武县', '431000');
INSERT INTO `r_county` VALUES ('431026', '汝城县', '431000');
INSERT INTO `r_county` VALUES ('431027', '桂东县', '431000');
INSERT INTO `r_county` VALUES ('431028', '安仁县', '431000');
INSERT INTO `r_county` VALUES ('431081', '资兴市', '431000');
INSERT INTO `r_county` VALUES ('431101', '市辖区', '431100');
INSERT INTO `r_county` VALUES ('431102', '芝山区', '431100');
INSERT INTO `r_county` VALUES ('431103', '冷水滩区', '431100');
INSERT INTO `r_county` VALUES ('431121', '祁阳县', '431100');
INSERT INTO `r_county` VALUES ('431122', '东安县', '431100');
INSERT INTO `r_county` VALUES ('431123', '双牌县', '431100');
INSERT INTO `r_county` VALUES ('431124', '道　县', '431100');
INSERT INTO `r_county` VALUES ('431125', '江永县', '431100');
INSERT INTO `r_county` VALUES ('431126', '宁远县', '431100');
INSERT INTO `r_county` VALUES ('431127', '蓝山县', '431100');
INSERT INTO `r_county` VALUES ('431128', '新田县', '431100');
INSERT INTO `r_county` VALUES ('431129', '江华瑶族自治县', '431100');
INSERT INTO `r_county` VALUES ('431201', '市辖区', '431200');
INSERT INTO `r_county` VALUES ('431202', '鹤城区', '431200');
INSERT INTO `r_county` VALUES ('431221', '中方县', '431200');
INSERT INTO `r_county` VALUES ('431222', '沅陵县', '431200');
INSERT INTO `r_county` VALUES ('431223', '辰溪县', '431200');
INSERT INTO `r_county` VALUES ('431224', '溆浦县', '431200');
INSERT INTO `r_county` VALUES ('431225', '会同县', '431200');
INSERT INTO `r_county` VALUES ('431226', '麻阳苗族自治县', '431200');
INSERT INTO `r_county` VALUES ('431227', '新晃侗族自治县', '431200');
INSERT INTO `r_county` VALUES ('431228', '芷江侗族自治县', '431200');
INSERT INTO `r_county` VALUES ('431229', '靖州苗族侗族自治县', '431200');
INSERT INTO `r_county` VALUES ('431230', '通道侗族自治县', '431200');
INSERT INTO `r_county` VALUES ('431281', '洪江市', '431200');
INSERT INTO `r_county` VALUES ('431301', '市辖区', '431300');
INSERT INTO `r_county` VALUES ('431302', '娄星区', '431300');
INSERT INTO `r_county` VALUES ('431321', '双峰县', '431300');
INSERT INTO `r_county` VALUES ('431322', '新化县', '431300');
INSERT INTO `r_county` VALUES ('431381', '冷水江市', '431300');
INSERT INTO `r_county` VALUES ('431382', '涟源市', '431300');
INSERT INTO `r_county` VALUES ('433101', '吉首市', '433100');
INSERT INTO `r_county` VALUES ('433122', '泸溪县', '433100');
INSERT INTO `r_county` VALUES ('433123', '凤凰县', '433100');
INSERT INTO `r_county` VALUES ('433124', '花垣县', '433100');
INSERT INTO `r_county` VALUES ('433125', '保靖县', '433100');
INSERT INTO `r_county` VALUES ('433126', '古丈县', '433100');
INSERT INTO `r_county` VALUES ('433127', '永顺县', '433100');
INSERT INTO `r_county` VALUES ('433130', '龙山县', '433100');
INSERT INTO `r_county` VALUES ('440101', '市辖区', '440100');
INSERT INTO `r_county` VALUES ('440102', '东山区', '440100');
INSERT INTO `r_county` VALUES ('440103', '荔湾区', '440100');
INSERT INTO `r_county` VALUES ('440104', '越秀区', '440100');
INSERT INTO `r_county` VALUES ('440105', '海珠区', '440100');
INSERT INTO `r_county` VALUES ('440106', '天河区', '440100');
INSERT INTO `r_county` VALUES ('440107', '芳村区', '440100');
INSERT INTO `r_county` VALUES ('440111', '白云区', '440100');
INSERT INTO `r_county` VALUES ('440112', '黄埔区', '440100');
INSERT INTO `r_county` VALUES ('440113', '番禺区', '440100');
INSERT INTO `r_county` VALUES ('440114', '花都区', '440100');
INSERT INTO `r_county` VALUES ('440183', '增城市', '440100');
INSERT INTO `r_county` VALUES ('440184', '从化市', '440100');
INSERT INTO `r_county` VALUES ('440201', '市辖区', '440200');
INSERT INTO `r_county` VALUES ('440203', '武江区', '440200');
INSERT INTO `r_county` VALUES ('440204', '浈江区', '440200');
INSERT INTO `r_county` VALUES ('440205', '曲江区', '440200');
INSERT INTO `r_county` VALUES ('440222', '始兴县', '440200');
INSERT INTO `r_county` VALUES ('440224', '仁化县', '440200');
INSERT INTO `r_county` VALUES ('440229', '翁源县', '440200');
INSERT INTO `r_county` VALUES ('440232', '乳源瑶族自治县', '440200');
INSERT INTO `r_county` VALUES ('440233', '新丰县', '440200');
INSERT INTO `r_county` VALUES ('440281', '乐昌市', '440200');
INSERT INTO `r_county` VALUES ('440282', '南雄市', '440200');
INSERT INTO `r_county` VALUES ('440301', '市辖区', '440300');
INSERT INTO `r_county` VALUES ('440303', '罗湖区', '440300');
INSERT INTO `r_county` VALUES ('440304', '福田区', '440300');
INSERT INTO `r_county` VALUES ('440305', '南山区', '440300');
INSERT INTO `r_county` VALUES ('440306', '宝安区', '440300');
INSERT INTO `r_county` VALUES ('440307', '龙岗区', '440300');
INSERT INTO `r_county` VALUES ('440308', '盐田区', '440300');
INSERT INTO `r_county` VALUES ('440401', '市辖区', '440400');
INSERT INTO `r_county` VALUES ('440402', '香洲区', '440400');
INSERT INTO `r_county` VALUES ('440403', '斗门区', '440400');
INSERT INTO `r_county` VALUES ('440404', '金湾区', '440400');
INSERT INTO `r_county` VALUES ('440501', '市辖区', '440500');
INSERT INTO `r_county` VALUES ('440507', '龙湖区', '440500');
INSERT INTO `r_county` VALUES ('440511', '金平区', '440500');
INSERT INTO `r_county` VALUES ('440512', '濠江区', '440500');
INSERT INTO `r_county` VALUES ('440513', '潮阳区', '440500');
INSERT INTO `r_county` VALUES ('440514', '潮南区', '440500');
INSERT INTO `r_county` VALUES ('440515', '澄海区', '440500');
INSERT INTO `r_county` VALUES ('440523', '南澳县', '440500');
INSERT INTO `r_county` VALUES ('440601', '市辖区', '440600');
INSERT INTO `r_county` VALUES ('440604', '禅城区', '440600');
INSERT INTO `r_county` VALUES ('440605', '南海区', '440600');
INSERT INTO `r_county` VALUES ('440606', '顺德区', '440600');
INSERT INTO `r_county` VALUES ('440607', '三水区', '440600');
INSERT INTO `r_county` VALUES ('440608', '高明区', '440600');
INSERT INTO `r_county` VALUES ('440701', '市辖区', '440700');
INSERT INTO `r_county` VALUES ('440703', '蓬江区', '440700');
INSERT INTO `r_county` VALUES ('440704', '江海区', '440700');
INSERT INTO `r_county` VALUES ('440705', '新会区', '440700');
INSERT INTO `r_county` VALUES ('440781', '台山市', '440700');
INSERT INTO `r_county` VALUES ('440783', '开平市', '440700');
INSERT INTO `r_county` VALUES ('440784', '鹤山市', '440700');
INSERT INTO `r_county` VALUES ('440785', '恩平市', '440700');
INSERT INTO `r_county` VALUES ('440801', '市辖区', '440800');
INSERT INTO `r_county` VALUES ('440802', '赤坎区', '440800');
INSERT INTO `r_county` VALUES ('440803', '霞山区', '440800');
INSERT INTO `r_county` VALUES ('440804', '坡头区', '440800');
INSERT INTO `r_county` VALUES ('440811', '麻章区', '440800');
INSERT INTO `r_county` VALUES ('440823', '遂溪县', '440800');
INSERT INTO `r_county` VALUES ('440825', '徐闻县', '440800');
INSERT INTO `r_county` VALUES ('440881', '廉江市', '440800');
INSERT INTO `r_county` VALUES ('440882', '雷州市', '440800');
INSERT INTO `r_county` VALUES ('440883', '吴川市', '440800');
INSERT INTO `r_county` VALUES ('440901', '市辖区', '440900');
INSERT INTO `r_county` VALUES ('440902', '茂南区', '440900');
INSERT INTO `r_county` VALUES ('440903', '茂港区', '440900');
INSERT INTO `r_county` VALUES ('440923', '电白县', '440900');
INSERT INTO `r_county` VALUES ('440981', '高州市', '440900');
INSERT INTO `r_county` VALUES ('440982', '化州市', '440900');
INSERT INTO `r_county` VALUES ('440983', '信宜市', '440900');
INSERT INTO `r_county` VALUES ('441201', '市辖区', '441200');
INSERT INTO `r_county` VALUES ('441202', '端州区', '441200');
INSERT INTO `r_county` VALUES ('441203', '鼎湖区', '441200');
INSERT INTO `r_county` VALUES ('441223', '广宁县', '441200');
INSERT INTO `r_county` VALUES ('441224', '怀集县', '441200');
INSERT INTO `r_county` VALUES ('441225', '封开县', '441200');
INSERT INTO `r_county` VALUES ('441226', '德庆县', '441200');
INSERT INTO `r_county` VALUES ('441283', '高要市', '441200');
INSERT INTO `r_county` VALUES ('441284', '四会市', '441200');
INSERT INTO `r_county` VALUES ('441301', '市辖区', '441300');
INSERT INTO `r_county` VALUES ('441302', '惠城区', '441300');
INSERT INTO `r_county` VALUES ('441303', '惠阳区', '441300');
INSERT INTO `r_county` VALUES ('441322', '博罗县', '441300');
INSERT INTO `r_county` VALUES ('441323', '惠东县', '441300');
INSERT INTO `r_county` VALUES ('441324', '龙门县', '441300');
INSERT INTO `r_county` VALUES ('441401', '市辖区', '441400');
INSERT INTO `r_county` VALUES ('441402', '梅江区', '441400');
INSERT INTO `r_county` VALUES ('441421', '梅　县', '441400');
INSERT INTO `r_county` VALUES ('441422', '大埔县', '441400');
INSERT INTO `r_county` VALUES ('441423', '丰顺县', '441400');
INSERT INTO `r_county` VALUES ('441424', '五华县', '441400');
INSERT INTO `r_county` VALUES ('441426', '平远县', '441400');
INSERT INTO `r_county` VALUES ('441427', '蕉岭县', '441400');
INSERT INTO `r_county` VALUES ('441481', '兴宁市', '441400');
INSERT INTO `r_county` VALUES ('441501', '市辖区', '441500');
INSERT INTO `r_county` VALUES ('441502', '城　区', '441500');
INSERT INTO `r_county` VALUES ('441521', '海丰县', '441500');
INSERT INTO `r_county` VALUES ('441523', '陆河县', '441500');
INSERT INTO `r_county` VALUES ('441581', '陆丰市', '441500');
INSERT INTO `r_county` VALUES ('441601', '市辖区', '441600');
INSERT INTO `r_county` VALUES ('441602', '源城区', '441600');
INSERT INTO `r_county` VALUES ('441621', '紫金县', '441600');
INSERT INTO `r_county` VALUES ('441622', '龙川县', '441600');
INSERT INTO `r_county` VALUES ('441623', '连平县', '441600');
INSERT INTO `r_county` VALUES ('441624', '和平县', '441600');
INSERT INTO `r_county` VALUES ('441625', '东源县', '441600');
INSERT INTO `r_county` VALUES ('441701', '市辖区', '441700');
INSERT INTO `r_county` VALUES ('441702', '江城区', '441700');
INSERT INTO `r_county` VALUES ('441721', '阳西县', '441700');
INSERT INTO `r_county` VALUES ('441723', '阳东县', '441700');
INSERT INTO `r_county` VALUES ('441781', '阳春市', '441700');
INSERT INTO `r_county` VALUES ('441801', '市辖区', '441800');
INSERT INTO `r_county` VALUES ('441802', '清城区', '441800');
INSERT INTO `r_county` VALUES ('441821', '佛冈县', '441800');
INSERT INTO `r_county` VALUES ('441823', '阳山县', '441800');
INSERT INTO `r_county` VALUES ('441825', '连山壮族瑶族自治县', '441800');
INSERT INTO `r_county` VALUES ('441826', '连南瑶族自治县', '441800');
INSERT INTO `r_county` VALUES ('441827', '清新县', '441800');
INSERT INTO `r_county` VALUES ('441881', '英德市', '441800');
INSERT INTO `r_county` VALUES ('441882', '连州市', '441800');
INSERT INTO `r_county` VALUES ('445101', '市辖区', '445100');
INSERT INTO `r_county` VALUES ('445102', '湘桥区', '445100');
INSERT INTO `r_county` VALUES ('445121', '潮安县', '445100');
INSERT INTO `r_county` VALUES ('445122', '饶平县', '445100');
INSERT INTO `r_county` VALUES ('445201', '市辖区', '445200');
INSERT INTO `r_county` VALUES ('445202', '榕城区', '445200');
INSERT INTO `r_county` VALUES ('445221', '揭东县', '445200');
INSERT INTO `r_county` VALUES ('445222', '揭西县', '445200');
INSERT INTO `r_county` VALUES ('445224', '惠来县', '445200');
INSERT INTO `r_county` VALUES ('445281', '普宁市', '445200');
INSERT INTO `r_county` VALUES ('445301', '市辖区', '445300');
INSERT INTO `r_county` VALUES ('445302', '云城区', '445300');
INSERT INTO `r_county` VALUES ('445321', '新兴县', '445300');
INSERT INTO `r_county` VALUES ('445322', '郁南县', '445300');
INSERT INTO `r_county` VALUES ('445323', '云安县', '445300');
INSERT INTO `r_county` VALUES ('445381', '罗定市', '445300');
INSERT INTO `r_county` VALUES ('450101', '市辖区', '450100');
INSERT INTO `r_county` VALUES ('450102', '兴宁区', '450100');
INSERT INTO `r_county` VALUES ('450103', '青秀区', '450100');
INSERT INTO `r_county` VALUES ('450105', '江南区', '450100');
INSERT INTO `r_county` VALUES ('450107', '西乡塘区', '450100');
INSERT INTO `r_county` VALUES ('450108', '良庆区', '450100');
INSERT INTO `r_county` VALUES ('450109', '邕宁区', '450100');
INSERT INTO `r_county` VALUES ('450122', '武鸣县', '450100');
INSERT INTO `r_county` VALUES ('450123', '隆安县', '450100');
INSERT INTO `r_county` VALUES ('450124', '马山县', '450100');
INSERT INTO `r_county` VALUES ('450125', '上林县', '450100');
INSERT INTO `r_county` VALUES ('450126', '宾阳县', '450100');
INSERT INTO `r_county` VALUES ('450127', '横　县', '450100');
INSERT INTO `r_county` VALUES ('450201', '市辖区', '450200');
INSERT INTO `r_county` VALUES ('450202', '城中区', '450200');
INSERT INTO `r_county` VALUES ('450203', '鱼峰区', '450200');
INSERT INTO `r_county` VALUES ('450204', '柳南区', '450200');
INSERT INTO `r_county` VALUES ('450205', '柳北区', '450200');
INSERT INTO `r_county` VALUES ('450221', '柳江县', '450200');
INSERT INTO `r_county` VALUES ('450222', '柳城县', '450200');
INSERT INTO `r_county` VALUES ('450223', '鹿寨县', '450200');
INSERT INTO `r_county` VALUES ('450224', '融安县', '450200');
INSERT INTO `r_county` VALUES ('450225', '融水苗族自治县', '450200');
INSERT INTO `r_county` VALUES ('450226', '三江侗族自治县', '450200');
INSERT INTO `r_county` VALUES ('450301', '市辖区', '450300');
INSERT INTO `r_county` VALUES ('450302', '秀峰区', '450300');
INSERT INTO `r_county` VALUES ('450303', '叠彩区', '450300');
INSERT INTO `r_county` VALUES ('450304', '象山区', '450300');
INSERT INTO `r_county` VALUES ('450305', '七星区', '450300');
INSERT INTO `r_county` VALUES ('450311', '雁山区', '450300');
INSERT INTO `r_county` VALUES ('450321', '阳朔县', '450300');
INSERT INTO `r_county` VALUES ('450322', '临桂县', '450300');
INSERT INTO `r_county` VALUES ('450323', '灵川县', '450300');
INSERT INTO `r_county` VALUES ('450324', '全州县', '450300');
INSERT INTO `r_county` VALUES ('450325', '兴安县', '450300');
INSERT INTO `r_county` VALUES ('450326', '永福县', '450300');
INSERT INTO `r_county` VALUES ('450327', '灌阳县', '450300');
INSERT INTO `r_county` VALUES ('450328', '龙胜各族自治县', '450300');
INSERT INTO `r_county` VALUES ('450329', '资源县', '450300');
INSERT INTO `r_county` VALUES ('450330', '平乐县', '450300');
INSERT INTO `r_county` VALUES ('450331', '荔蒲县', '450300');
INSERT INTO `r_county` VALUES ('450332', '恭城瑶族自治县', '450300');
INSERT INTO `r_county` VALUES ('450401', '市辖区', '450400');
INSERT INTO `r_county` VALUES ('450403', '万秀区', '450400');
INSERT INTO `r_county` VALUES ('450404', '蝶山区', '450400');
INSERT INTO `r_county` VALUES ('450405', '长洲区', '450400');
INSERT INTO `r_county` VALUES ('450421', '苍梧县', '450400');
INSERT INTO `r_county` VALUES ('450422', '藤　县', '450400');
INSERT INTO `r_county` VALUES ('450423', '蒙山县', '450400');
INSERT INTO `r_county` VALUES ('450481', '岑溪市', '450400');
INSERT INTO `r_county` VALUES ('450501', '市辖区', '450500');
INSERT INTO `r_county` VALUES ('450502', '海城区', '450500');
INSERT INTO `r_county` VALUES ('450503', '银海区', '450500');
INSERT INTO `r_county` VALUES ('450512', '铁山港区', '450500');
INSERT INTO `r_county` VALUES ('450521', '合浦县', '450500');
INSERT INTO `r_county` VALUES ('450601', '市辖区', '450600');
INSERT INTO `r_county` VALUES ('450602', '港口区', '450600');
INSERT INTO `r_county` VALUES ('450603', '防城区', '450600');
INSERT INTO `r_county` VALUES ('450621', '上思县', '450600');
INSERT INTO `r_county` VALUES ('450681', '东兴市', '450600');
INSERT INTO `r_county` VALUES ('450701', '市辖区', '450700');
INSERT INTO `r_county` VALUES ('450702', '钦南区', '450700');
INSERT INTO `r_county` VALUES ('450703', '钦北区', '450700');
INSERT INTO `r_county` VALUES ('450721', '灵山县', '450700');
INSERT INTO `r_county` VALUES ('450722', '浦北县', '450700');
INSERT INTO `r_county` VALUES ('450801', '市辖区', '450800');
INSERT INTO `r_county` VALUES ('450802', '港北区', '450800');
INSERT INTO `r_county` VALUES ('450803', '港南区', '450800');
INSERT INTO `r_county` VALUES ('450804', '覃塘区', '450800');
INSERT INTO `r_county` VALUES ('450821', '平南县', '450800');
INSERT INTO `r_county` VALUES ('450881', '桂平市', '450800');
INSERT INTO `r_county` VALUES ('450901', '市辖区', '450900');
INSERT INTO `r_county` VALUES ('450902', '玉州区', '450900');
INSERT INTO `r_county` VALUES ('450921', '容　县', '450900');
INSERT INTO `r_county` VALUES ('450922', '陆川县', '450900');
INSERT INTO `r_county` VALUES ('450923', '博白县', '450900');
INSERT INTO `r_county` VALUES ('450924', '兴业县', '450900');
INSERT INTO `r_county` VALUES ('450981', '北流市', '450900');
INSERT INTO `r_county` VALUES ('451001', '市辖区', '451000');
INSERT INTO `r_county` VALUES ('451002', '右江区', '451000');
INSERT INTO `r_county` VALUES ('451021', '田阳县', '451000');
INSERT INTO `r_county` VALUES ('451022', '田东县', '451000');
INSERT INTO `r_county` VALUES ('451023', '平果县', '451000');
INSERT INTO `r_county` VALUES ('451024', '德保县', '451000');
INSERT INTO `r_county` VALUES ('451025', '靖西县', '451000');
INSERT INTO `r_county` VALUES ('451026', '那坡县', '451000');
INSERT INTO `r_county` VALUES ('451027', '凌云县', '451000');
INSERT INTO `r_county` VALUES ('451028', '乐业县', '451000');
INSERT INTO `r_county` VALUES ('451029', '田林县', '451000');
INSERT INTO `r_county` VALUES ('451030', '西林县', '451000');
INSERT INTO `r_county` VALUES ('451031', '隆林各族自治县', '451000');
INSERT INTO `r_county` VALUES ('451101', '市辖区', '451100');
INSERT INTO `r_county` VALUES ('451102', '八步区', '451100');
INSERT INTO `r_county` VALUES ('451121', '昭平县', '451100');
INSERT INTO `r_county` VALUES ('451122', '钟山县', '451100');
INSERT INTO `r_county` VALUES ('451123', '富川瑶族自治县', '451100');
INSERT INTO `r_county` VALUES ('451201', '市辖区', '451200');
INSERT INTO `r_county` VALUES ('451202', '金城江区', '451200');
INSERT INTO `r_county` VALUES ('451221', '南丹县', '451200');
INSERT INTO `r_county` VALUES ('451222', '天峨县', '451200');
INSERT INTO `r_county` VALUES ('451223', '凤山县', '451200');
INSERT INTO `r_county` VALUES ('451224', '东兰县', '451200');
INSERT INTO `r_county` VALUES ('451225', '罗城仫佬族自治县', '451200');
INSERT INTO `r_county` VALUES ('451226', '环江毛南族自治县', '451200');
INSERT INTO `r_county` VALUES ('451227', '巴马瑶族自治县', '451200');
INSERT INTO `r_county` VALUES ('451228', '都安瑶族自治县', '451200');
INSERT INTO `r_county` VALUES ('451229', '大化瑶族自治县', '451200');
INSERT INTO `r_county` VALUES ('451281', '宜州市', '451200');
INSERT INTO `r_county` VALUES ('451301', '市辖区', '451300');
INSERT INTO `r_county` VALUES ('451302', '兴宾区', '451300');
INSERT INTO `r_county` VALUES ('451321', '忻城县', '451300');
INSERT INTO `r_county` VALUES ('451322', '象州县', '451300');
INSERT INTO `r_county` VALUES ('451323', '武宣县', '451300');
INSERT INTO `r_county` VALUES ('451324', '金秀瑶族自治县', '451300');
INSERT INTO `r_county` VALUES ('451381', '合山市', '451300');
INSERT INTO `r_county` VALUES ('451401', '市辖区', '451400');
INSERT INTO `r_county` VALUES ('451402', '江洲区', '451400');
INSERT INTO `r_county` VALUES ('451421', '扶绥县', '451400');
INSERT INTO `r_county` VALUES ('451422', '宁明县', '451400');
INSERT INTO `r_county` VALUES ('451423', '龙州县', '451400');
INSERT INTO `r_county` VALUES ('451424', '大新县', '451400');
INSERT INTO `r_county` VALUES ('451425', '天等县', '451400');
INSERT INTO `r_county` VALUES ('451481', '凭祥市', '451400');
INSERT INTO `r_county` VALUES ('460101', '市辖区', '460100');
INSERT INTO `r_county` VALUES ('460105', '秀英区', '460100');
INSERT INTO `r_county` VALUES ('460106', '龙华区', '460100');
INSERT INTO `r_county` VALUES ('460107', '琼山区', '460100');
INSERT INTO `r_county` VALUES ('460108', '美兰区', '460100');
INSERT INTO `r_county` VALUES ('460201', '市辖区', '460200');
INSERT INTO `r_county` VALUES ('469001', '五指山市', '469000');
INSERT INTO `r_county` VALUES ('469002', '琼海市', '469000');
INSERT INTO `r_county` VALUES ('469003', '儋州市', '469000');
INSERT INTO `r_county` VALUES ('469005', '文昌市', '469000');
INSERT INTO `r_county` VALUES ('469006', '万宁市', '469000');
INSERT INTO `r_county` VALUES ('469007', '东方市', '469000');
INSERT INTO `r_county` VALUES ('469025', '定安县', '469000');
INSERT INTO `r_county` VALUES ('469026', '屯昌县', '469000');
INSERT INTO `r_county` VALUES ('469027', '澄迈县', '469000');
INSERT INTO `r_county` VALUES ('469028', '临高县', '469000');
INSERT INTO `r_county` VALUES ('469030', '白沙黎族自治县', '469000');
INSERT INTO `r_county` VALUES ('469031', '昌江黎族自治县', '469000');
INSERT INTO `r_county` VALUES ('469033', '乐东黎族自治县', '469000');
INSERT INTO `r_county` VALUES ('469034', '陵水黎族自治县', '469000');
INSERT INTO `r_county` VALUES ('469035', '保亭黎族苗族自治县', '469000');
INSERT INTO `r_county` VALUES ('469036', '琼中黎族苗族自治县', '469000');
INSERT INTO `r_county` VALUES ('469037', '西沙群岛', '469000');
INSERT INTO `r_county` VALUES ('469038', '南沙群岛', '469000');
INSERT INTO `r_county` VALUES ('469039', '中沙群岛的岛礁及其海域', '469000');
INSERT INTO `r_county` VALUES ('500101', '万州区', '500100');
INSERT INTO `r_county` VALUES ('500102', '涪陵区', '500100');
INSERT INTO `r_county` VALUES ('500103', '渝中区', '500100');
INSERT INTO `r_county` VALUES ('500104', '大渡口区', '500100');
INSERT INTO `r_county` VALUES ('500105', '江北区', '500100');
INSERT INTO `r_county` VALUES ('500106', '沙坪坝区', '500100');
INSERT INTO `r_county` VALUES ('500107', '九龙坡区', '500100');
INSERT INTO `r_county` VALUES ('500108', '南岸区', '500100');
INSERT INTO `r_county` VALUES ('500109', '北碚区', '500100');
INSERT INTO `r_county` VALUES ('500110', '万盛区', '500100');
INSERT INTO `r_county` VALUES ('500111', '双桥区', '500100');
INSERT INTO `r_county` VALUES ('500112', '渝北区', '500100');
INSERT INTO `r_county` VALUES ('500113', '巴南区', '500100');
INSERT INTO `r_county` VALUES ('500114', '黔江区', '500100');
INSERT INTO `r_county` VALUES ('500115', '长寿区', '500100');
INSERT INTO `r_county` VALUES ('500222', '綦江县', '500200');
INSERT INTO `r_county` VALUES ('500223', '潼南县', '500200');
INSERT INTO `r_county` VALUES ('500224', '铜梁县', '500200');
INSERT INTO `r_county` VALUES ('500225', '大足县', '500200');
INSERT INTO `r_county` VALUES ('500226', '荣昌县', '500200');
INSERT INTO `r_county` VALUES ('500227', '璧山县', '500200');
INSERT INTO `r_county` VALUES ('500228', '梁平县', '500200');
INSERT INTO `r_county` VALUES ('500229', '城口县', '500200');
INSERT INTO `r_county` VALUES ('500230', '丰都县', '500200');
INSERT INTO `r_county` VALUES ('500231', '垫江县', '500200');
INSERT INTO `r_county` VALUES ('500232', '武隆县', '500200');
INSERT INTO `r_county` VALUES ('500233', '忠　县', '500200');
INSERT INTO `r_county` VALUES ('500234', '开　县', '500200');
INSERT INTO `r_county` VALUES ('500235', '云阳县', '500200');
INSERT INTO `r_county` VALUES ('500236', '奉节县', '500200');
INSERT INTO `r_county` VALUES ('500237', '巫山县', '500200');
INSERT INTO `r_county` VALUES ('500238', '巫溪县', '500200');
INSERT INTO `r_county` VALUES ('500240', '石柱土家族自治县', '500200');
INSERT INTO `r_county` VALUES ('500241', '秀山土家族苗族自治县', '500200');
INSERT INTO `r_county` VALUES ('500242', '酉阳土家族苗族自治县', '500200');
INSERT INTO `r_county` VALUES ('500243', '彭水苗族土家族自治县', '500200');
INSERT INTO `r_county` VALUES ('500381', '江津市', '500300');
INSERT INTO `r_county` VALUES ('500382', '合川市', '500300');
INSERT INTO `r_county` VALUES ('500383', '永川市', '500300');
INSERT INTO `r_county` VALUES ('500384', '南川市', '500300');
INSERT INTO `r_county` VALUES ('510101', '市辖区', '510100');
INSERT INTO `r_county` VALUES ('510104', '锦江区', '510100');
INSERT INTO `r_county` VALUES ('510105', '青羊区', '510100');
INSERT INTO `r_county` VALUES ('510106', '金牛区', '510100');
INSERT INTO `r_county` VALUES ('510107', '武侯区', '510100');
INSERT INTO `r_county` VALUES ('510108', '成华区', '510100');
INSERT INTO `r_county` VALUES ('510112', '龙泉驿区', '510100');
INSERT INTO `r_county` VALUES ('510113', '青白江区', '510100');
INSERT INTO `r_county` VALUES ('510114', '新都区', '510100');
INSERT INTO `r_county` VALUES ('510115', '温江县', '510100');
INSERT INTO `r_county` VALUES ('510121', '金堂县', '510100');
INSERT INTO `r_county` VALUES ('510122', '双流县', '510100');
INSERT INTO `r_county` VALUES ('510124', '郫　县', '510100');
INSERT INTO `r_county` VALUES ('510129', '大邑县', '510100');
INSERT INTO `r_county` VALUES ('510131', '蒲江县', '510100');
INSERT INTO `r_county` VALUES ('510132', '新津县', '510100');
INSERT INTO `r_county` VALUES ('510181', '都江堰市', '510100');
INSERT INTO `r_county` VALUES ('510182', '彭州市', '510100');
INSERT INTO `r_county` VALUES ('510183', '邛崃市', '510100');
INSERT INTO `r_county` VALUES ('510184', '崇州市', '510100');
INSERT INTO `r_county` VALUES ('510301', '市辖区', '510300');
INSERT INTO `r_county` VALUES ('510302', '自流井区', '510300');
INSERT INTO `r_county` VALUES ('510303', '贡井区', '510300');
INSERT INTO `r_county` VALUES ('510304', '大安区', '510300');
INSERT INTO `r_county` VALUES ('510311', '沿滩区', '510300');
INSERT INTO `r_county` VALUES ('510321', '荣　县', '510300');
INSERT INTO `r_county` VALUES ('510322', '富顺县', '510300');
INSERT INTO `r_county` VALUES ('510401', '市辖区', '510400');
INSERT INTO `r_county` VALUES ('510402', '东　区', '510400');
INSERT INTO `r_county` VALUES ('510403', '西　区', '510400');
INSERT INTO `r_county` VALUES ('510411', '仁和区', '510400');
INSERT INTO `r_county` VALUES ('510421', '米易县', '510400');
INSERT INTO `r_county` VALUES ('510422', '盐边县', '510400');
INSERT INTO `r_county` VALUES ('510501', '市辖区', '510500');
INSERT INTO `r_county` VALUES ('510502', '江阳区', '510500');
INSERT INTO `r_county` VALUES ('510503', '纳溪区', '510500');
INSERT INTO `r_county` VALUES ('510504', '龙马潭区', '510500');
INSERT INTO `r_county` VALUES ('510521', '泸　县', '510500');
INSERT INTO `r_county` VALUES ('510522', '合江县', '510500');
INSERT INTO `r_county` VALUES ('510524', '叙永县', '510500');
INSERT INTO `r_county` VALUES ('510525', '古蔺县', '510500');
INSERT INTO `r_county` VALUES ('510601', '市辖区', '510600');
INSERT INTO `r_county` VALUES ('510603', '旌阳区', '510600');
INSERT INTO `r_county` VALUES ('510623', '中江县', '510600');
INSERT INTO `r_county` VALUES ('510626', '罗江县', '510600');
INSERT INTO `r_county` VALUES ('510681', '广汉市', '510600');
INSERT INTO `r_county` VALUES ('510682', '什邡市', '510600');
INSERT INTO `r_county` VALUES ('510683', '绵竹市', '510600');
INSERT INTO `r_county` VALUES ('510701', '市辖区', '510700');
INSERT INTO `r_county` VALUES ('510703', '涪城区', '510700');
INSERT INTO `r_county` VALUES ('510704', '游仙区', '510700');
INSERT INTO `r_county` VALUES ('510722', '三台县', '510700');
INSERT INTO `r_county` VALUES ('510723', '盐亭县', '510700');
INSERT INTO `r_county` VALUES ('510724', '安　县', '510700');
INSERT INTO `r_county` VALUES ('510725', '梓潼县', '510700');
INSERT INTO `r_county` VALUES ('510726', '北川羌族自治县', '510700');
INSERT INTO `r_county` VALUES ('510727', '平武县', '510700');
INSERT INTO `r_county` VALUES ('510781', '江油市', '510700');
INSERT INTO `r_county` VALUES ('510801', '市辖区', '510800');
INSERT INTO `r_county` VALUES ('510802', '市中区', '510800');
INSERT INTO `r_county` VALUES ('510811', '元坝区', '510800');
INSERT INTO `r_county` VALUES ('510812', '朝天区', '510800');
INSERT INTO `r_county` VALUES ('510821', '旺苍县', '510800');
INSERT INTO `r_county` VALUES ('510822', '青川县', '510800');
INSERT INTO `r_county` VALUES ('510823', '剑阁县', '510800');
INSERT INTO `r_county` VALUES ('510824', '苍溪县', '510800');
INSERT INTO `r_county` VALUES ('510901', '市辖区', '510900');
INSERT INTO `r_county` VALUES ('510903', '船山区', '510900');
INSERT INTO `r_county` VALUES ('510904', '安居区', '510900');
INSERT INTO `r_county` VALUES ('510921', '蓬溪县', '510900');
INSERT INTO `r_county` VALUES ('510922', '射洪县', '510900');
INSERT INTO `r_county` VALUES ('510923', '大英县', '510900');
INSERT INTO `r_county` VALUES ('511001', '市辖区', '511000');
INSERT INTO `r_county` VALUES ('511002', '市中区', '511000');
INSERT INTO `r_county` VALUES ('511011', '东兴区', '511000');
INSERT INTO `r_county` VALUES ('511024', '威远县', '511000');
INSERT INTO `r_county` VALUES ('511025', '资中县', '511000');
INSERT INTO `r_county` VALUES ('511028', '隆昌县', '511000');
INSERT INTO `r_county` VALUES ('511101', '市辖区', '511100');
INSERT INTO `r_county` VALUES ('511102', '市中区', '511100');
INSERT INTO `r_county` VALUES ('511111', '沙湾区', '511100');
INSERT INTO `r_county` VALUES ('511112', '五通桥区', '511100');
INSERT INTO `r_county` VALUES ('511113', '金口河区', '511100');
INSERT INTO `r_county` VALUES ('511123', '犍为县', '511100');
INSERT INTO `r_county` VALUES ('511124', '井研县', '511100');
INSERT INTO `r_county` VALUES ('511126', '夹江县', '511100');
INSERT INTO `r_county` VALUES ('511129', '沐川县', '511100');
INSERT INTO `r_county` VALUES ('511132', '峨边彝族自治县', '511100');
INSERT INTO `r_county` VALUES ('511133', '马边彝族自治县', '511100');
INSERT INTO `r_county` VALUES ('511181', '峨眉山市', '511100');
INSERT INTO `r_county` VALUES ('511301', '市辖区', '511300');
INSERT INTO `r_county` VALUES ('511302', '顺庆区', '511300');
INSERT INTO `r_county` VALUES ('511303', '高坪区', '511300');
INSERT INTO `r_county` VALUES ('511304', '嘉陵区', '511300');
INSERT INTO `r_county` VALUES ('511321', '南部县', '511300');
INSERT INTO `r_county` VALUES ('511322', '营山县', '511300');
INSERT INTO `r_county` VALUES ('511323', '蓬安县', '511300');
INSERT INTO `r_county` VALUES ('511324', '仪陇县', '511300');
INSERT INTO `r_county` VALUES ('511325', '西充县', '511300');
INSERT INTO `r_county` VALUES ('511381', '阆中市', '511300');
INSERT INTO `r_county` VALUES ('511401', '市辖区', '511400');
INSERT INTO `r_county` VALUES ('511402', '东坡区', '511400');
INSERT INTO `r_county` VALUES ('511421', '仁寿县', '511400');
INSERT INTO `r_county` VALUES ('511422', '彭山县', '511400');
INSERT INTO `r_county` VALUES ('511423', '洪雅县', '511400');
INSERT INTO `r_county` VALUES ('511424', '丹棱县', '511400');
INSERT INTO `r_county` VALUES ('511425', '青神县', '511400');
INSERT INTO `r_county` VALUES ('511501', '市辖区', '511500');
INSERT INTO `r_county` VALUES ('511502', '翠屏区', '511500');
INSERT INTO `r_county` VALUES ('511521', '宜宾县', '511500');
INSERT INTO `r_county` VALUES ('511522', '南溪县', '511500');
INSERT INTO `r_county` VALUES ('511523', '江安县', '511500');
INSERT INTO `r_county` VALUES ('511524', '长宁县', '511500');
INSERT INTO `r_county` VALUES ('511525', '高　县', '511500');
INSERT INTO `r_county` VALUES ('511526', '珙　县', '511500');
INSERT INTO `r_county` VALUES ('511527', '筠连县', '511500');
INSERT INTO `r_county` VALUES ('511528', '兴文县', '511500');
INSERT INTO `r_county` VALUES ('511529', '屏山县', '511500');
INSERT INTO `r_county` VALUES ('511601', '市辖区', '511600');
INSERT INTO `r_county` VALUES ('511602', '广安区', '511600');
INSERT INTO `r_county` VALUES ('511621', '岳池县', '511600');
INSERT INTO `r_county` VALUES ('511622', '武胜县', '511600');
INSERT INTO `r_county` VALUES ('511623', '邻水县', '511600');
INSERT INTO `r_county` VALUES ('511681', '华莹市', '511600');
INSERT INTO `r_county` VALUES ('511701', '市辖区', '511700');
INSERT INTO `r_county` VALUES ('511702', '通川区', '511700');
INSERT INTO `r_county` VALUES ('511721', '达　县', '511700');
INSERT INTO `r_county` VALUES ('511722', '宣汉县', '511700');
INSERT INTO `r_county` VALUES ('511723', '开江县', '511700');
INSERT INTO `r_county` VALUES ('511724', '大竹县', '511700');
INSERT INTO `r_county` VALUES ('511725', '渠　县', '511700');
INSERT INTO `r_county` VALUES ('511781', '万源市', '511700');
INSERT INTO `r_county` VALUES ('511801', '市辖区', '511800');
INSERT INTO `r_county` VALUES ('511802', '雨城区', '511800');
INSERT INTO `r_county` VALUES ('511821', '名山县', '511800');
INSERT INTO `r_county` VALUES ('511822', '荥经县', '511800');
INSERT INTO `r_county` VALUES ('511823', '汉源县', '511800');
INSERT INTO `r_county` VALUES ('511824', '石棉县', '511800');
INSERT INTO `r_county` VALUES ('511825', '天全县', '511800');
INSERT INTO `r_county` VALUES ('511826', '芦山县', '511800');
INSERT INTO `r_county` VALUES ('511827', '宝兴县', '511800');
INSERT INTO `r_county` VALUES ('511901', '市辖区', '511900');
INSERT INTO `r_county` VALUES ('511902', '巴州区', '511900');
INSERT INTO `r_county` VALUES ('511921', '通江县', '511900');
INSERT INTO `r_county` VALUES ('511922', '南江县', '511900');
INSERT INTO `r_county` VALUES ('511923', '平昌县', '511900');
INSERT INTO `r_county` VALUES ('512001', '市辖区', '512000');
INSERT INTO `r_county` VALUES ('512002', '雁江区', '512000');
INSERT INTO `r_county` VALUES ('512021', '安岳县', '512000');
INSERT INTO `r_county` VALUES ('512022', '乐至县', '512000');
INSERT INTO `r_county` VALUES ('512081', '简阳市', '512000');
INSERT INTO `r_county` VALUES ('513221', '汶川县', '513200');
INSERT INTO `r_county` VALUES ('513222', '理　县', '513200');
INSERT INTO `r_county` VALUES ('513223', '茂　县', '513200');
INSERT INTO `r_county` VALUES ('513224', '松潘县', '513200');
INSERT INTO `r_county` VALUES ('513225', '九寨沟县', '513200');
INSERT INTO `r_county` VALUES ('513226', '金川县', '513200');
INSERT INTO `r_county` VALUES ('513227', '小金县', '513200');
INSERT INTO `r_county` VALUES ('513228', '黑水县', '513200');
INSERT INTO `r_county` VALUES ('513229', '马尔康县', '513200');
INSERT INTO `r_county` VALUES ('513230', '壤塘县', '513200');
INSERT INTO `r_county` VALUES ('513231', '阿坝县', '513200');
INSERT INTO `r_county` VALUES ('513232', '若尔盖县', '513200');
INSERT INTO `r_county` VALUES ('513233', '红原县', '513200');
INSERT INTO `r_county` VALUES ('513321', '康定县', '513300');
INSERT INTO `r_county` VALUES ('513322', '泸定县', '513300');
INSERT INTO `r_county` VALUES ('513323', '丹巴县', '513300');
INSERT INTO `r_county` VALUES ('513324', '九龙县', '513300');
INSERT INTO `r_county` VALUES ('513325', '雅江县', '513300');
INSERT INTO `r_county` VALUES ('513326', '道孚县', '513300');
INSERT INTO `r_county` VALUES ('513327', '炉霍县', '513300');
INSERT INTO `r_county` VALUES ('513328', '甘孜县', '513300');
INSERT INTO `r_county` VALUES ('513329', '新龙县', '513300');
INSERT INTO `r_county` VALUES ('513330', '德格县', '513300');
INSERT INTO `r_county` VALUES ('513331', '白玉县', '513300');
INSERT INTO `r_county` VALUES ('513332', '石渠县', '513300');
INSERT INTO `r_county` VALUES ('513333', '色达县', '513300');
INSERT INTO `r_county` VALUES ('513334', '理塘县', '513300');
INSERT INTO `r_county` VALUES ('513335', '巴塘县', '513300');
INSERT INTO `r_county` VALUES ('513336', '乡城县', '513300');
INSERT INTO `r_county` VALUES ('513337', '稻城县', '513300');
INSERT INTO `r_county` VALUES ('513338', '得荣县', '513300');
INSERT INTO `r_county` VALUES ('513401', '西昌市', '513400');
INSERT INTO `r_county` VALUES ('513422', '木里藏族自治县', '513400');
INSERT INTO `r_county` VALUES ('513423', '盐源县', '513400');
INSERT INTO `r_county` VALUES ('513424', '德昌县', '513400');
INSERT INTO `r_county` VALUES ('513425', '会理县', '513400');
INSERT INTO `r_county` VALUES ('513426', '会东县', '513400');
INSERT INTO `r_county` VALUES ('513427', '宁南县', '513400');
INSERT INTO `r_county` VALUES ('513428', '普格县', '513400');
INSERT INTO `r_county` VALUES ('513429', '布拖县', '513400');
INSERT INTO `r_county` VALUES ('513430', '金阳县', '513400');
INSERT INTO `r_county` VALUES ('513431', '昭觉县', '513400');
INSERT INTO `r_county` VALUES ('513432', '喜德县', '513400');
INSERT INTO `r_county` VALUES ('513433', '冕宁县', '513400');
INSERT INTO `r_county` VALUES ('513434', '越西县', '513400');
INSERT INTO `r_county` VALUES ('513435', '甘洛县', '513400');
INSERT INTO `r_county` VALUES ('513436', '美姑县', '513400');
INSERT INTO `r_county` VALUES ('513437', '雷波县', '513400');
INSERT INTO `r_county` VALUES ('520101', '市辖区', '520100');
INSERT INTO `r_county` VALUES ('520102', '南明区', '520100');
INSERT INTO `r_county` VALUES ('520103', '云岩区', '520100');
INSERT INTO `r_county` VALUES ('520111', '花溪区', '520100');
INSERT INTO `r_county` VALUES ('520112', '乌当区', '520100');
INSERT INTO `r_county` VALUES ('520113', '白云区', '520100');
INSERT INTO `r_county` VALUES ('520114', '小河区', '520100');
INSERT INTO `r_county` VALUES ('520121', '开阳县', '520100');
INSERT INTO `r_county` VALUES ('520122', '息烽县', '520100');
INSERT INTO `r_county` VALUES ('520123', '修文县', '520100');
INSERT INTO `r_county` VALUES ('520181', '清镇市', '520100');
INSERT INTO `r_county` VALUES ('520201', '钟山区', '520200');
INSERT INTO `r_county` VALUES ('520203', '六枝特区', '520200');
INSERT INTO `r_county` VALUES ('520221', '水城县', '520200');
INSERT INTO `r_county` VALUES ('520222', '盘　县', '520200');
INSERT INTO `r_county` VALUES ('520301', '市辖区', '520300');
INSERT INTO `r_county` VALUES ('520302', '红花岗区', '520300');
INSERT INTO `r_county` VALUES ('520303', '汇川区', '520300');
INSERT INTO `r_county` VALUES ('520321', '遵义县', '520300');
INSERT INTO `r_county` VALUES ('520322', '桐梓县', '520300');
INSERT INTO `r_county` VALUES ('520323', '绥阳县', '520300');
INSERT INTO `r_county` VALUES ('520324', '正安县', '520300');
INSERT INTO `r_county` VALUES ('520325', '道真仡佬族苗族自治县', '520300');
INSERT INTO `r_county` VALUES ('520326', '务川仡佬族苗族自治县', '520300');
INSERT INTO `r_county` VALUES ('520327', '凤冈县', '520300');
INSERT INTO `r_county` VALUES ('520328', '湄潭县', '520300');
INSERT INTO `r_county` VALUES ('520329', '余庆县', '520300');
INSERT INTO `r_county` VALUES ('520330', '习水县', '520300');
INSERT INTO `r_county` VALUES ('520381', '赤水市', '520300');
INSERT INTO `r_county` VALUES ('520382', '仁怀市', '520300');
INSERT INTO `r_county` VALUES ('520401', '市辖区', '520400');
INSERT INTO `r_county` VALUES ('520402', '西秀区', '520400');
INSERT INTO `r_county` VALUES ('520421', '平坝县', '520400');
INSERT INTO `r_county` VALUES ('520422', '普定县', '520400');
INSERT INTO `r_county` VALUES ('520423', '镇宁布依族苗族自治县', '520400');
INSERT INTO `r_county` VALUES ('520424', '关岭布依族苗族自治县', '520400');
INSERT INTO `r_county` VALUES ('520425', '紫云苗族布依族自治县', '520400');
INSERT INTO `r_county` VALUES ('522201', '铜仁市', '522200');
INSERT INTO `r_county` VALUES ('522222', '江口县', '522200');
INSERT INTO `r_county` VALUES ('522223', '玉屏侗族自治县', '522200');
INSERT INTO `r_county` VALUES ('522224', '石阡县', '522200');
INSERT INTO `r_county` VALUES ('522225', '思南县', '522200');
INSERT INTO `r_county` VALUES ('522226', '印江土家族苗族自治县', '522200');
INSERT INTO `r_county` VALUES ('522227', '德江县', '522200');
INSERT INTO `r_county` VALUES ('522228', '沿河土家族自治县', '522200');
INSERT INTO `r_county` VALUES ('522229', '松桃苗族自治县', '522200');
INSERT INTO `r_county` VALUES ('522230', '万山特区', '522200');
INSERT INTO `r_county` VALUES ('522301', '兴义市', '522300');
INSERT INTO `r_county` VALUES ('522322', '兴仁县', '522300');
INSERT INTO `r_county` VALUES ('522323', '普安县', '522300');
INSERT INTO `r_county` VALUES ('522324', '晴隆县', '522300');
INSERT INTO `r_county` VALUES ('522325', '贞丰县', '522300');
INSERT INTO `r_county` VALUES ('522326', '望谟县', '522300');
INSERT INTO `r_county` VALUES ('522327', '册亨县', '522300');
INSERT INTO `r_county` VALUES ('522328', '安龙县', '522300');
INSERT INTO `r_county` VALUES ('522401', '毕节市', '522400');
INSERT INTO `r_county` VALUES ('522422', '大方县', '522400');
INSERT INTO `r_county` VALUES ('522423', '黔西县', '522400');
INSERT INTO `r_county` VALUES ('522424', '金沙县', '522400');
INSERT INTO `r_county` VALUES ('522425', '织金县', '522400');
INSERT INTO `r_county` VALUES ('522426', '纳雍县', '522400');
INSERT INTO `r_county` VALUES ('522427', '威宁彝族回族苗族自治县', '522400');
INSERT INTO `r_county` VALUES ('522428', '赫章县', '522400');
INSERT INTO `r_county` VALUES ('522601', '凯里市', '522600');
INSERT INTO `r_county` VALUES ('522622', '黄平县', '522600');
INSERT INTO `r_county` VALUES ('522623', '施秉县', '522600');
INSERT INTO `r_county` VALUES ('522624', '三穗县', '522600');
INSERT INTO `r_county` VALUES ('522625', '镇远县', '522600');
INSERT INTO `r_county` VALUES ('522626', '岑巩县', '522600');
INSERT INTO `r_county` VALUES ('522627', '天柱县', '522600');
INSERT INTO `r_county` VALUES ('522628', '锦屏县', '522600');
INSERT INTO `r_county` VALUES ('522629', '剑河县', '522600');
INSERT INTO `r_county` VALUES ('522630', '台江县', '522600');
INSERT INTO `r_county` VALUES ('522631', '黎平县', '522600');
INSERT INTO `r_county` VALUES ('522632', '榕江县', '522600');
INSERT INTO `r_county` VALUES ('522633', '从江县', '522600');
INSERT INTO `r_county` VALUES ('522634', '雷山县', '522600');
INSERT INTO `r_county` VALUES ('522635', '麻江县', '522600');
INSERT INTO `r_county` VALUES ('522636', '丹寨县', '522600');
INSERT INTO `r_county` VALUES ('522701', '都匀市', '522700');
INSERT INTO `r_county` VALUES ('522702', '福泉市', '522700');
INSERT INTO `r_county` VALUES ('522722', '荔波县', '522700');
INSERT INTO `r_county` VALUES ('522723', '贵定县', '522700');
INSERT INTO `r_county` VALUES ('522725', '瓮安县', '522700');
INSERT INTO `r_county` VALUES ('522726', '独山县', '522700');
INSERT INTO `r_county` VALUES ('522727', '平塘县', '522700');
INSERT INTO `r_county` VALUES ('522728', '罗甸县', '522700');
INSERT INTO `r_county` VALUES ('522729', '长顺县', '522700');
INSERT INTO `r_county` VALUES ('522730', '龙里县', '522700');
INSERT INTO `r_county` VALUES ('522731', '惠水县', '522700');
INSERT INTO `r_county` VALUES ('522732', '三都水族自治县', '522700');
INSERT INTO `r_county` VALUES ('530101', '市辖区', '530100');
INSERT INTO `r_county` VALUES ('530102', '五华区', '530100');
INSERT INTO `r_county` VALUES ('530103', '盘龙区', '530100');
INSERT INTO `r_county` VALUES ('530111', '官渡区', '530100');
INSERT INTO `r_county` VALUES ('530112', '西山区', '530100');
INSERT INTO `r_county` VALUES ('530113', '东川区', '530100');
INSERT INTO `r_county` VALUES ('530121', '呈贡县', '530100');
INSERT INTO `r_county` VALUES ('530122', '晋宁县', '530100');
INSERT INTO `r_county` VALUES ('530124', '富民县', '530100');
INSERT INTO `r_county` VALUES ('530125', '宜良县', '530100');
INSERT INTO `r_county` VALUES ('530126', '石林彝族自治县', '530100');
INSERT INTO `r_county` VALUES ('530127', '嵩明县', '530100');
INSERT INTO `r_county` VALUES ('530128', '禄劝彝族苗族自治县', '530100');
INSERT INTO `r_county` VALUES ('530129', '寻甸回族彝族自治县', '530100');
INSERT INTO `r_county` VALUES ('530181', '安宁市', '530100');
INSERT INTO `r_county` VALUES ('530301', '市辖区', '530300');
INSERT INTO `r_county` VALUES ('530302', '麒麟区', '530300');
INSERT INTO `r_county` VALUES ('530321', '马龙县', '530300');
INSERT INTO `r_county` VALUES ('530322', '陆良县', '530300');
INSERT INTO `r_county` VALUES ('530323', '师宗县', '530300');
INSERT INTO `r_county` VALUES ('530324', '罗平县', '530300');
INSERT INTO `r_county` VALUES ('530325', '富源县', '530300');
INSERT INTO `r_county` VALUES ('530326', '会泽县', '530300');
INSERT INTO `r_county` VALUES ('530328', '沾益县', '530300');
INSERT INTO `r_county` VALUES ('530381', '宣威市', '530300');
INSERT INTO `r_county` VALUES ('530401', '市辖区', '530400');
INSERT INTO `r_county` VALUES ('530402', '红塔区', '530400');
INSERT INTO `r_county` VALUES ('530421', '江川县', '530400');
INSERT INTO `r_county` VALUES ('530422', '澄江县', '530400');
INSERT INTO `r_county` VALUES ('530423', '通海县', '530400');
INSERT INTO `r_county` VALUES ('530424', '华宁县', '530400');
INSERT INTO `r_county` VALUES ('530425', '易门县', '530400');
INSERT INTO `r_county` VALUES ('530426', '峨山彝族自治县', '530400');
INSERT INTO `r_county` VALUES ('530427', '新平彝族傣族自治县', '530400');
INSERT INTO `r_county` VALUES ('530428', '元江哈尼族彝族傣族自治县', '530400');
INSERT INTO `r_county` VALUES ('530501', '市辖区', '530500');
INSERT INTO `r_county` VALUES ('530502', '隆阳区', '530500');
INSERT INTO `r_county` VALUES ('530521', '施甸县', '530500');
INSERT INTO `r_county` VALUES ('530522', '腾冲县', '530500');
INSERT INTO `r_county` VALUES ('530523', '龙陵县', '530500');
INSERT INTO `r_county` VALUES ('530524', '昌宁县', '530500');
INSERT INTO `r_county` VALUES ('530601', '市辖区', '530600');
INSERT INTO `r_county` VALUES ('530602', '昭阳区', '530600');
INSERT INTO `r_county` VALUES ('530621', '鲁甸县', '530600');
INSERT INTO `r_county` VALUES ('530622', '巧家县', '530600');
INSERT INTO `r_county` VALUES ('530623', '盐津县', '530600');
INSERT INTO `r_county` VALUES ('530624', '大关县', '530600');
INSERT INTO `r_county` VALUES ('530625', '永善县', '530600');
INSERT INTO `r_county` VALUES ('530626', '绥江县', '530600');
INSERT INTO `r_county` VALUES ('530627', '镇雄县', '530600');
INSERT INTO `r_county` VALUES ('530628', '彝良县', '530600');
INSERT INTO `r_county` VALUES ('530629', '威信县', '530600');
INSERT INTO `r_county` VALUES ('530630', '水富县', '530600');
INSERT INTO `r_county` VALUES ('530701', '市辖区', '530700');
INSERT INTO `r_county` VALUES ('530702', '古城区', '530700');
INSERT INTO `r_county` VALUES ('530721', '玉龙纳西族自治县', '530700');
INSERT INTO `r_county` VALUES ('530722', '永胜县', '530700');
INSERT INTO `r_county` VALUES ('530723', '华坪县', '530700');
INSERT INTO `r_county` VALUES ('530724', '宁蒗彝族自治县', '530700');
INSERT INTO `r_county` VALUES ('530801', '市辖区', '530800');
INSERT INTO `r_county` VALUES ('530802', '翠云区', '530800');
INSERT INTO `r_county` VALUES ('530821', '普洱哈尼族彝族自治县', '530800');
INSERT INTO `r_county` VALUES ('530822', '墨江哈尼族自治县', '530800');
INSERT INTO `r_county` VALUES ('530823', '景东彝族自治县', '530800');
INSERT INTO `r_county` VALUES ('530824', '景谷傣族彝族自治县', '530800');
INSERT INTO `r_county` VALUES ('530825', '镇沅彝族哈尼族拉祜族自治县', '530800');
INSERT INTO `r_county` VALUES ('530826', '江城哈尼族彝族自治县', '530800');
INSERT INTO `r_county` VALUES ('530827', '孟连傣族拉祜族佤族自治县', '530800');
INSERT INTO `r_county` VALUES ('530828', '澜沧拉祜族自治县', '530800');
INSERT INTO `r_county` VALUES ('530829', '西盟佤族自治县', '530800');
INSERT INTO `r_county` VALUES ('530901', '市辖区', '530900');
INSERT INTO `r_county` VALUES ('530902', '临翔区', '530900');
INSERT INTO `r_county` VALUES ('530921', '凤庆县', '530900');
INSERT INTO `r_county` VALUES ('530922', '云　县', '530900');
INSERT INTO `r_county` VALUES ('530923', '永德县', '530900');
INSERT INTO `r_county` VALUES ('530924', '镇康县', '530900');
INSERT INTO `r_county` VALUES ('530925', '双江拉祜族佤族布朗族傣族自治县', '530900');
INSERT INTO `r_county` VALUES ('530926', '耿马傣族佤族自治县', '530900');
INSERT INTO `r_county` VALUES ('530927', '沧源佤族自治县', '530900');
INSERT INTO `r_county` VALUES ('532301', '楚雄市', '532300');
INSERT INTO `r_county` VALUES ('532322', '双柏县', '532300');
INSERT INTO `r_county` VALUES ('532323', '牟定县', '532300');
INSERT INTO `r_county` VALUES ('532324', '南华县', '532300');
INSERT INTO `r_county` VALUES ('532325', '姚安县', '532300');
INSERT INTO `r_county` VALUES ('532326', '大姚县', '532300');
INSERT INTO `r_county` VALUES ('532327', '永仁县', '532300');
INSERT INTO `r_county` VALUES ('532328', '元谋县', '532300');
INSERT INTO `r_county` VALUES ('532329', '武定县', '532300');
INSERT INTO `r_county` VALUES ('532331', '禄丰县', '532300');
INSERT INTO `r_county` VALUES ('532501', '个旧市', '532500');
INSERT INTO `r_county` VALUES ('532502', '开远市', '532500');
INSERT INTO `r_county` VALUES ('532522', '蒙自县', '532500');
INSERT INTO `r_county` VALUES ('532523', '屏边苗族自治县', '532500');
INSERT INTO `r_county` VALUES ('532524', '建水县', '532500');
INSERT INTO `r_county` VALUES ('532525', '石屏县', '532500');
INSERT INTO `r_county` VALUES ('532526', '弥勒县', '532500');
INSERT INTO `r_county` VALUES ('532527', '泸西县', '532500');
INSERT INTO `r_county` VALUES ('532528', '元阳县', '532500');
INSERT INTO `r_county` VALUES ('532529', '红河县', '532500');
INSERT INTO `r_county` VALUES ('532530', '金平苗族瑶族傣族自治县', '532500');
INSERT INTO `r_county` VALUES ('532531', '绿春县', '532500');
INSERT INTO `r_county` VALUES ('532532', '河口瑶族自治县', '532500');
INSERT INTO `r_county` VALUES ('532621', '文山县', '532600');
INSERT INTO `r_county` VALUES ('532622', '砚山县', '532600');
INSERT INTO `r_county` VALUES ('532623', '西畴县', '532600');
INSERT INTO `r_county` VALUES ('532624', '麻栗坡县', '532600');
INSERT INTO `r_county` VALUES ('532625', '马关县', '532600');
INSERT INTO `r_county` VALUES ('532626', '丘北县', '532600');
INSERT INTO `r_county` VALUES ('532627', '广南县', '532600');
INSERT INTO `r_county` VALUES ('532628', '富宁县', '532600');
INSERT INTO `r_county` VALUES ('532801', '景洪市', '532800');
INSERT INTO `r_county` VALUES ('532822', '勐海县', '532800');
INSERT INTO `r_county` VALUES ('532823', '勐腊县', '532800');
INSERT INTO `r_county` VALUES ('532901', '大理市', '532900');
INSERT INTO `r_county` VALUES ('532922', '漾濞彝族自治县', '532900');
INSERT INTO `r_county` VALUES ('532923', '祥云县', '532900');
INSERT INTO `r_county` VALUES ('532924', '宾川县', '532900');
INSERT INTO `r_county` VALUES ('532925', '弥渡县', '532900');
INSERT INTO `r_county` VALUES ('532926', '南涧彝族自治县', '532900');
INSERT INTO `r_county` VALUES ('532927', '巍山彝族回族自治县', '532900');
INSERT INTO `r_county` VALUES ('532928', '永平县', '532900');
INSERT INTO `r_county` VALUES ('532929', '云龙县', '532900');
INSERT INTO `r_county` VALUES ('532930', '洱源县', '532900');
INSERT INTO `r_county` VALUES ('532931', '剑川县', '532900');
INSERT INTO `r_county` VALUES ('532932', '鹤庆县', '532900');
INSERT INTO `r_county` VALUES ('533102', '瑞丽市', '533100');
INSERT INTO `r_county` VALUES ('533103', '潞西市', '533100');
INSERT INTO `r_county` VALUES ('533122', '梁河县', '533100');
INSERT INTO `r_county` VALUES ('533123', '盈江县', '533100');
INSERT INTO `r_county` VALUES ('533124', '陇川县', '533100');
INSERT INTO `r_county` VALUES ('533321', '泸水县', '533300');
INSERT INTO `r_county` VALUES ('533323', '福贡县', '533300');
INSERT INTO `r_county` VALUES ('533324', '贡山独龙族怒族自治县', '533300');
INSERT INTO `r_county` VALUES ('533325', '兰坪白族普米族自治县', '533300');
INSERT INTO `r_county` VALUES ('533421', '香格里拉县', '533400');
INSERT INTO `r_county` VALUES ('533422', '德钦县', '533400');
INSERT INTO `r_county` VALUES ('533423', '维西傈僳族自治县', '533400');
INSERT INTO `r_county` VALUES ('540101', '市辖区', '540100');
INSERT INTO `r_county` VALUES ('540102', '城关区', '540100');
INSERT INTO `r_county` VALUES ('540121', '林周县', '540100');
INSERT INTO `r_county` VALUES ('540122', '当雄县', '540100');
INSERT INTO `r_county` VALUES ('540123', '尼木县', '540100');
INSERT INTO `r_county` VALUES ('540124', '曲水县', '540100');
INSERT INTO `r_county` VALUES ('540125', '堆龙德庆县', '540100');
INSERT INTO `r_county` VALUES ('540126', '达孜县', '540100');
INSERT INTO `r_county` VALUES ('540127', '墨竹工卡县', '540100');
INSERT INTO `r_county` VALUES ('542121', '昌都县', '542100');
INSERT INTO `r_county` VALUES ('542122', '江达县', '542100');
INSERT INTO `r_county` VALUES ('542123', '贡觉县', '542100');
INSERT INTO `r_county` VALUES ('542124', '类乌齐县', '542100');
INSERT INTO `r_county` VALUES ('542125', '丁青县', '542100');
INSERT INTO `r_county` VALUES ('542126', '察雅县', '542100');
INSERT INTO `r_county` VALUES ('542127', '八宿县', '542100');
INSERT INTO `r_county` VALUES ('542128', '左贡县', '542100');
INSERT INTO `r_county` VALUES ('542129', '芒康县', '542100');
INSERT INTO `r_county` VALUES ('542132', '洛隆县', '542100');
INSERT INTO `r_county` VALUES ('542133', '边坝县', '542100');
INSERT INTO `r_county` VALUES ('542221', '乃东县', '542200');
INSERT INTO `r_county` VALUES ('542222', '扎囊县', '542200');
INSERT INTO `r_county` VALUES ('542223', '贡嘎县', '542200');
INSERT INTO `r_county` VALUES ('542224', '桑日县', '542200');
INSERT INTO `r_county` VALUES ('542225', '琼结县', '542200');
INSERT INTO `r_county` VALUES ('542226', '曲松县', '542200');
INSERT INTO `r_county` VALUES ('542227', '措美县', '542200');
INSERT INTO `r_county` VALUES ('542228', '洛扎县', '542200');
INSERT INTO `r_county` VALUES ('542229', '加查县', '542200');
INSERT INTO `r_county` VALUES ('542231', '隆子县', '542200');
INSERT INTO `r_county` VALUES ('542232', '错那县', '542200');
INSERT INTO `r_county` VALUES ('542233', '浪卡子县', '542200');
INSERT INTO `r_county` VALUES ('542301', '日喀则市', '542300');
INSERT INTO `r_county` VALUES ('542322', '南木林县', '542300');
INSERT INTO `r_county` VALUES ('542323', '江孜县', '542300');
INSERT INTO `r_county` VALUES ('542324', '定日县', '542300');
INSERT INTO `r_county` VALUES ('542325', '萨迦县', '542300');
INSERT INTO `r_county` VALUES ('542326', '拉孜县', '542300');
INSERT INTO `r_county` VALUES ('542327', '昂仁县', '542300');
INSERT INTO `r_county` VALUES ('542328', '谢通门县', '542300');
INSERT INTO `r_county` VALUES ('542329', '白朗县', '542300');
INSERT INTO `r_county` VALUES ('542330', '仁布县', '542300');
INSERT INTO `r_county` VALUES ('542331', '康马县', '542300');
INSERT INTO `r_county` VALUES ('542332', '定结县', '542300');
INSERT INTO `r_county` VALUES ('542333', '仲巴县', '542300');
INSERT INTO `r_county` VALUES ('542334', '亚东县', '542300');
INSERT INTO `r_county` VALUES ('542335', '吉隆县', '542300');
INSERT INTO `r_county` VALUES ('542336', '聂拉木县', '542300');
INSERT INTO `r_county` VALUES ('542337', '萨嘎县', '542300');
INSERT INTO `r_county` VALUES ('542338', '岗巴县', '542300');
INSERT INTO `r_county` VALUES ('542421', '那曲县', '542400');
INSERT INTO `r_county` VALUES ('542422', '嘉黎县', '542400');
INSERT INTO `r_county` VALUES ('542423', '比如县', '542400');
INSERT INTO `r_county` VALUES ('542424', '聂荣县', '542400');
INSERT INTO `r_county` VALUES ('542425', '安多县', '542400');
INSERT INTO `r_county` VALUES ('542426', '申扎县', '542400');
INSERT INTO `r_county` VALUES ('542427', '索　县', '542400');
INSERT INTO `r_county` VALUES ('542428', '班戈县', '542400');
INSERT INTO `r_county` VALUES ('542429', '巴青县', '542400');
INSERT INTO `r_county` VALUES ('542430', '尼玛县', '542400');
INSERT INTO `r_county` VALUES ('542521', '普兰县', '542500');
INSERT INTO `r_county` VALUES ('542522', '札达县', '542500');
INSERT INTO `r_county` VALUES ('542523', '噶尔县', '542500');
INSERT INTO `r_county` VALUES ('542524', '日土县', '542500');
INSERT INTO `r_county` VALUES ('542525', '革吉县', '542500');
INSERT INTO `r_county` VALUES ('542526', '改则县', '542500');
INSERT INTO `r_county` VALUES ('542527', '措勤县', '542500');
INSERT INTO `r_county` VALUES ('542621', '林芝县', '542600');
INSERT INTO `r_county` VALUES ('542622', '工布江达县', '542600');
INSERT INTO `r_county` VALUES ('542623', '米林县', '542600');
INSERT INTO `r_county` VALUES ('542624', '墨脱县', '542600');
INSERT INTO `r_county` VALUES ('542625', '波密县', '542600');
INSERT INTO `r_county` VALUES ('542626', '察隅县', '542600');
INSERT INTO `r_county` VALUES ('542627', '朗　县', '542600');
INSERT INTO `r_county` VALUES ('610101', '市辖区', '610100');
INSERT INTO `r_county` VALUES ('610102', '新城区', '610100');
INSERT INTO `r_county` VALUES ('610103', '碑林区', '610100');
INSERT INTO `r_county` VALUES ('610104', '莲湖区', '610100');
INSERT INTO `r_county` VALUES ('610111', '灞桥区', '610100');
INSERT INTO `r_county` VALUES ('610112', '未央区', '610100');
INSERT INTO `r_county` VALUES ('610113', '雁塔区', '610100');
INSERT INTO `r_county` VALUES ('610114', '阎良区', '610100');
INSERT INTO `r_county` VALUES ('610115', '临潼区', '610100');
INSERT INTO `r_county` VALUES ('610116', '长安区', '610100');
INSERT INTO `r_county` VALUES ('610122', '蓝田县', '610100');
INSERT INTO `r_county` VALUES ('610124', '周至县', '610100');
INSERT INTO `r_county` VALUES ('610125', '户　县', '610100');
INSERT INTO `r_county` VALUES ('610126', '高陵县', '610100');
INSERT INTO `r_county` VALUES ('610201', '市辖区', '610200');
INSERT INTO `r_county` VALUES ('610202', '王益区', '610200');
INSERT INTO `r_county` VALUES ('610203', '印台区', '610200');
INSERT INTO `r_county` VALUES ('610204', '耀州区', '610200');
INSERT INTO `r_county` VALUES ('610222', '宜君县', '610200');
INSERT INTO `r_county` VALUES ('610301', '市辖区', '610300');
INSERT INTO `r_county` VALUES ('610302', '渭滨区', '610300');
INSERT INTO `r_county` VALUES ('610303', '金台区', '610300');
INSERT INTO `r_county` VALUES ('610304', '陈仓区', '610300');
INSERT INTO `r_county` VALUES ('610322', '凤翔县', '610300');
INSERT INTO `r_county` VALUES ('610323', '岐山县', '610300');
INSERT INTO `r_county` VALUES ('610324', '扶风县', '610300');
INSERT INTO `r_county` VALUES ('610326', '眉　县', '610300');
INSERT INTO `r_county` VALUES ('610327', '陇　县', '610300');
INSERT INTO `r_county` VALUES ('610328', '千阳县', '610300');
INSERT INTO `r_county` VALUES ('610329', '麟游县', '610300');
INSERT INTO `r_county` VALUES ('610330', '凤　县', '610300');
INSERT INTO `r_county` VALUES ('610331', '太白县', '610300');
INSERT INTO `r_county` VALUES ('610401', '市辖区', '610400');
INSERT INTO `r_county` VALUES ('610402', '秦都区', '610400');
INSERT INTO `r_county` VALUES ('610403', '杨凌区', '610400');
INSERT INTO `r_county` VALUES ('610404', '渭城区', '610400');
INSERT INTO `r_county` VALUES ('610422', '三原县', '610400');
INSERT INTO `r_county` VALUES ('610423', '泾阳县', '610400');
INSERT INTO `r_county` VALUES ('610424', '乾　县', '610400');
INSERT INTO `r_county` VALUES ('610425', '礼泉县', '610400');
INSERT INTO `r_county` VALUES ('610426', '永寿县', '610400');
INSERT INTO `r_county` VALUES ('610427', '彬　县', '610400');
INSERT INTO `r_county` VALUES ('610428', '长武县', '610400');
INSERT INTO `r_county` VALUES ('610429', '旬邑县', '610400');
INSERT INTO `r_county` VALUES ('610430', '淳化县', '610400');
INSERT INTO `r_county` VALUES ('610431', '武功县', '610400');
INSERT INTO `r_county` VALUES ('610481', '兴平市', '610400');
INSERT INTO `r_county` VALUES ('610501', '市辖区', '610500');
INSERT INTO `r_county` VALUES ('610502', '临渭区', '610500');
INSERT INTO `r_county` VALUES ('610521', '华　县', '610500');
INSERT INTO `r_county` VALUES ('610522', '潼关县', '610500');
INSERT INTO `r_county` VALUES ('610523', '大荔县', '610500');
INSERT INTO `r_county` VALUES ('610524', '合阳县', '610500');
INSERT INTO `r_county` VALUES ('610525', '澄城县', '610500');
INSERT INTO `r_county` VALUES ('610526', '蒲城县', '610500');
INSERT INTO `r_county` VALUES ('610527', '白水县', '610500');
INSERT INTO `r_county` VALUES ('610528', '富平县', '610500');
INSERT INTO `r_county` VALUES ('610581', '韩城市', '610500');
INSERT INTO `r_county` VALUES ('610582', '华阴市', '610500');
INSERT INTO `r_county` VALUES ('610601', '市辖区', '610600');
INSERT INTO `r_county` VALUES ('610602', '宝塔区', '610600');
INSERT INTO `r_county` VALUES ('610621', '延长县', '610600');
INSERT INTO `r_county` VALUES ('610622', '延川县', '610600');
INSERT INTO `r_county` VALUES ('610623', '子长县', '610600');
INSERT INTO `r_county` VALUES ('610624', '安塞县', '610600');
INSERT INTO `r_county` VALUES ('610625', '志丹县', '610600');
INSERT INTO `r_county` VALUES ('610626', '吴旗县', '610600');
INSERT INTO `r_county` VALUES ('610627', '甘泉县', '610600');
INSERT INTO `r_county` VALUES ('610628', '富　县', '610600');
INSERT INTO `r_county` VALUES ('610629', '洛川县', '610600');
INSERT INTO `r_county` VALUES ('610630', '宜川县', '610600');
INSERT INTO `r_county` VALUES ('610631', '黄龙县', '610600');
INSERT INTO `r_county` VALUES ('610632', '黄陵县', '610600');
INSERT INTO `r_county` VALUES ('610701', '市辖区', '610700');
INSERT INTO `r_county` VALUES ('610702', '汉台区', '610700');
INSERT INTO `r_county` VALUES ('610721', '南郑县', '610700');
INSERT INTO `r_county` VALUES ('610722', '城固县', '610700');
INSERT INTO `r_county` VALUES ('610723', '洋　县', '610700');
INSERT INTO `r_county` VALUES ('610724', '西乡县', '610700');
INSERT INTO `r_county` VALUES ('610725', '勉　县', '610700');
INSERT INTO `r_county` VALUES ('610726', '宁强县', '610700');
INSERT INTO `r_county` VALUES ('610727', '略阳县', '610700');
INSERT INTO `r_county` VALUES ('610728', '镇巴县', '610700');
INSERT INTO `r_county` VALUES ('610729', '留坝县', '610700');
INSERT INTO `r_county` VALUES ('610730', '佛坪县', '610700');
INSERT INTO `r_county` VALUES ('610801', '市辖区', '610800');
INSERT INTO `r_county` VALUES ('610802', '榆阳区', '610800');
INSERT INTO `r_county` VALUES ('610821', '神木县', '610800');
INSERT INTO `r_county` VALUES ('610822', '府谷县', '610800');
INSERT INTO `r_county` VALUES ('610823', '横山县', '610800');
INSERT INTO `r_county` VALUES ('610824', '靖边县', '610800');
INSERT INTO `r_county` VALUES ('610825', '定边县', '610800');
INSERT INTO `r_county` VALUES ('610826', '绥德县', '610800');
INSERT INTO `r_county` VALUES ('610827', '米脂县', '610800');
INSERT INTO `r_county` VALUES ('610828', '佳　县', '610800');
INSERT INTO `r_county` VALUES ('610829', '吴堡县', '610800');
INSERT INTO `r_county` VALUES ('610830', '清涧县', '610800');
INSERT INTO `r_county` VALUES ('610831', '子洲县', '610800');
INSERT INTO `r_county` VALUES ('610901', '市辖区', '610900');
INSERT INTO `r_county` VALUES ('610902', '汉滨区', '610900');
INSERT INTO `r_county` VALUES ('610921', '汉阴县', '610900');
INSERT INTO `r_county` VALUES ('610922', '石泉县', '610900');
INSERT INTO `r_county` VALUES ('610923', '宁陕县', '610900');
INSERT INTO `r_county` VALUES ('610924', '紫阳县', '610900');
INSERT INTO `r_county` VALUES ('610925', '岚皋县', '610900');
INSERT INTO `r_county` VALUES ('610926', '平利县', '610900');
INSERT INTO `r_county` VALUES ('610927', '镇坪县', '610900');
INSERT INTO `r_county` VALUES ('610928', '旬阳县', '610900');
INSERT INTO `r_county` VALUES ('610929', '白河县', '610900');
INSERT INTO `r_county` VALUES ('611001', '市辖区', '611000');
INSERT INTO `r_county` VALUES ('611002', '商州区', '611000');
INSERT INTO `r_county` VALUES ('611021', '洛南县', '611000');
INSERT INTO `r_county` VALUES ('611022', '丹凤县', '611000');
INSERT INTO `r_county` VALUES ('611023', '商南县', '611000');
INSERT INTO `r_county` VALUES ('611024', '山阳县', '611000');
INSERT INTO `r_county` VALUES ('611025', '镇安县', '611000');
INSERT INTO `r_county` VALUES ('611026', '柞水县', '611000');
INSERT INTO `r_county` VALUES ('620101', '市辖区', '620100');
INSERT INTO `r_county` VALUES ('620102', '城关区', '620100');
INSERT INTO `r_county` VALUES ('620103', '七里河区', '620100');
INSERT INTO `r_county` VALUES ('620104', '西固区', '620100');
INSERT INTO `r_county` VALUES ('620105', '安宁区', '620100');
INSERT INTO `r_county` VALUES ('620111', '红古区', '620100');
INSERT INTO `r_county` VALUES ('620121', '永登县', '620100');
INSERT INTO `r_county` VALUES ('620122', '皋兰县', '620100');
INSERT INTO `r_county` VALUES ('620123', '榆中县', '620100');
INSERT INTO `r_county` VALUES ('620201', '市辖区', '620200');
INSERT INTO `r_county` VALUES ('620301', '市辖区', '620300');
INSERT INTO `r_county` VALUES ('620302', '金川区', '620300');
INSERT INTO `r_county` VALUES ('620321', '永昌县', '620300');
INSERT INTO `r_county` VALUES ('620401', '市辖区', '620400');
INSERT INTO `r_county` VALUES ('620402', '白银区', '620400');
INSERT INTO `r_county` VALUES ('620403', '平川区', '620400');
INSERT INTO `r_county` VALUES ('620421', '靖远县', '620400');
INSERT INTO `r_county` VALUES ('620422', '会宁县', '620400');
INSERT INTO `r_county` VALUES ('620423', '景泰县', '620400');
INSERT INTO `r_county` VALUES ('620501', '市辖区', '620500');
INSERT INTO `r_county` VALUES ('620502', '秦城区', '620500');
INSERT INTO `r_county` VALUES ('620503', '北道区', '620500');
INSERT INTO `r_county` VALUES ('620521', '清水县', '620500');
INSERT INTO `r_county` VALUES ('620522', '秦安县', '620500');
INSERT INTO `r_county` VALUES ('620523', '甘谷县', '620500');
INSERT INTO `r_county` VALUES ('620524', '武山县', '620500');
INSERT INTO `r_county` VALUES ('620525', '张家川回族自治县', '620500');
INSERT INTO `r_county` VALUES ('620601', '市辖区', '620600');
INSERT INTO `r_county` VALUES ('620602', '凉州区', '620600');
INSERT INTO `r_county` VALUES ('620621', '民勤县', '620600');
INSERT INTO `r_county` VALUES ('620622', '古浪县', '620600');
INSERT INTO `r_county` VALUES ('620623', '天祝藏族自治县', '620600');
INSERT INTO `r_county` VALUES ('620701', '市辖区', '620700');
INSERT INTO `r_county` VALUES ('620702', '甘州区', '620700');
INSERT INTO `r_county` VALUES ('620721', '肃南裕固族自治县', '620700');
INSERT INTO `r_county` VALUES ('620722', '民乐县', '620700');
INSERT INTO `r_county` VALUES ('620723', '临泽县', '620700');
INSERT INTO `r_county` VALUES ('620724', '高台县', '620700');
INSERT INTO `r_county` VALUES ('620725', '山丹县', '620700');
INSERT INTO `r_county` VALUES ('620801', '市辖区', '620800');
INSERT INTO `r_county` VALUES ('620802', '崆峒区', '620800');
INSERT INTO `r_county` VALUES ('620821', '泾川县', '620800');
INSERT INTO `r_county` VALUES ('620822', '灵台县', '620800');
INSERT INTO `r_county` VALUES ('620823', '崇信县', '620800');
INSERT INTO `r_county` VALUES ('620824', '华亭县', '620800');
INSERT INTO `r_county` VALUES ('620825', '庄浪县', '620800');
INSERT INTO `r_county` VALUES ('620826', '静宁县', '620800');
INSERT INTO `r_county` VALUES ('620901', '市辖区', '620900');
INSERT INTO `r_county` VALUES ('620902', '肃州区', '620900');
INSERT INTO `r_county` VALUES ('620921', '金塔县', '620900');
INSERT INTO `r_county` VALUES ('620922', '安西县', '620900');
INSERT INTO `r_county` VALUES ('620923', '肃北蒙古族自治县', '620900');
INSERT INTO `r_county` VALUES ('620924', '阿克塞哈萨克族自治县', '620900');
INSERT INTO `r_county` VALUES ('620981', '玉门市', '620900');
INSERT INTO `r_county` VALUES ('620982', '敦煌市', '620900');
INSERT INTO `r_county` VALUES ('621001', '市辖区', '621000');
INSERT INTO `r_county` VALUES ('621002', '西峰区', '621000');
INSERT INTO `r_county` VALUES ('621021', '庆城县', '621000');
INSERT INTO `r_county` VALUES ('621022', '环　县', '621000');
INSERT INTO `r_county` VALUES ('621023', '华池县', '621000');
INSERT INTO `r_county` VALUES ('621024', '合水县', '621000');
INSERT INTO `r_county` VALUES ('621025', '正宁县', '621000');
INSERT INTO `r_county` VALUES ('621026', '宁　县', '621000');
INSERT INTO `r_county` VALUES ('621027', '镇原县', '621000');
INSERT INTO `r_county` VALUES ('621101', '市辖区', '621100');
INSERT INTO `r_county` VALUES ('621102', '安定区', '621100');
INSERT INTO `r_county` VALUES ('621121', '通渭县', '621100');
INSERT INTO `r_county` VALUES ('621122', '陇西县', '621100');
INSERT INTO `r_county` VALUES ('621123', '渭源县', '621100');
INSERT INTO `r_county` VALUES ('621124', '临洮县', '621100');
INSERT INTO `r_county` VALUES ('621125', '漳　县', '621100');
INSERT INTO `r_county` VALUES ('621126', '岷　县', '621100');
INSERT INTO `r_county` VALUES ('621201', '市辖区', '621200');
INSERT INTO `r_county` VALUES ('621202', '武都区', '621200');
INSERT INTO `r_county` VALUES ('621221', '成　县', '621200');
INSERT INTO `r_county` VALUES ('621222', '文　县', '621200');
INSERT INTO `r_county` VALUES ('621223', '宕昌县', '621200');
INSERT INTO `r_county` VALUES ('621224', '康　县', '621200');
INSERT INTO `r_county` VALUES ('621225', '西和县', '621200');
INSERT INTO `r_county` VALUES ('621226', '礼　县', '621200');
INSERT INTO `r_county` VALUES ('621227', '徽　县', '621200');
INSERT INTO `r_county` VALUES ('621228', '两当县', '621200');
INSERT INTO `r_county` VALUES ('622901', '临夏市', '622900');
INSERT INTO `r_county` VALUES ('622921', '临夏县', '622900');
INSERT INTO `r_county` VALUES ('622922', '康乐县', '622900');
INSERT INTO `r_county` VALUES ('622923', '永靖县', '622900');
INSERT INTO `r_county` VALUES ('622924', '广河县', '622900');
INSERT INTO `r_county` VALUES ('622925', '和政县', '622900');
INSERT INTO `r_county` VALUES ('622926', '东乡族自治县', '622900');
INSERT INTO `r_county` VALUES ('622927', '积石山保安族东乡族撒拉族自治县', '622900');
INSERT INTO `r_county` VALUES ('623001', '合作市', '623000');
INSERT INTO `r_county` VALUES ('623021', '临潭县', '623000');
INSERT INTO `r_county` VALUES ('623022', '卓尼县', '623000');
INSERT INTO `r_county` VALUES ('623023', '舟曲县', '623000');
INSERT INTO `r_county` VALUES ('623024', '迭部县', '623000');
INSERT INTO `r_county` VALUES ('623025', '玛曲县', '623000');
INSERT INTO `r_county` VALUES ('623026', '碌曲县', '623000');
INSERT INTO `r_county` VALUES ('623027', '夏河县', '623000');
INSERT INTO `r_county` VALUES ('630101', '市辖区', '630100');
INSERT INTO `r_county` VALUES ('630102', '城东区', '630100');
INSERT INTO `r_county` VALUES ('630103', '城中区', '630100');
INSERT INTO `r_county` VALUES ('630104', '城西区', '630100');
INSERT INTO `r_county` VALUES ('630105', '城北区', '630100');
INSERT INTO `r_county` VALUES ('630121', '大通回族土族自治县', '630100');
INSERT INTO `r_county` VALUES ('630122', '湟中县', '630100');
INSERT INTO `r_county` VALUES ('630123', '湟源县', '630100');
INSERT INTO `r_county` VALUES ('632121', '平安县', '632100');
INSERT INTO `r_county` VALUES ('632122', '民和回族土族自治县', '632100');
INSERT INTO `r_county` VALUES ('632123', '乐都县', '632100');
INSERT INTO `r_county` VALUES ('632126', '互助土族自治县', '632100');
INSERT INTO `r_county` VALUES ('632127', '化隆回族自治县', '632100');
INSERT INTO `r_county` VALUES ('632128', '循化撒拉族自治县', '632100');
INSERT INTO `r_county` VALUES ('632221', '门源回族自治县', '632200');
INSERT INTO `r_county` VALUES ('632222', '祁连县', '632200');
INSERT INTO `r_county` VALUES ('632223', '海晏县', '632200');
INSERT INTO `r_county` VALUES ('632224', '刚察县', '632200');
INSERT INTO `r_county` VALUES ('632321', '同仁县', '632300');
INSERT INTO `r_county` VALUES ('632322', '尖扎县', '632300');
INSERT INTO `r_county` VALUES ('632323', '泽库县', '632300');
INSERT INTO `r_county` VALUES ('632324', '河南蒙古族自治县', '632300');
INSERT INTO `r_county` VALUES ('632521', '共和县', '632500');
INSERT INTO `r_county` VALUES ('632522', '同德县', '632500');
INSERT INTO `r_county` VALUES ('632523', '贵德县', '632500');
INSERT INTO `r_county` VALUES ('632524', '兴海县', '632500');
INSERT INTO `r_county` VALUES ('632525', '贵南县', '632500');
INSERT INTO `r_county` VALUES ('632621', '玛沁县', '632600');
INSERT INTO `r_county` VALUES ('632622', '班玛县', '632600');
INSERT INTO `r_county` VALUES ('632623', '甘德县', '632600');
INSERT INTO `r_county` VALUES ('632624', '达日县', '632600');
INSERT INTO `r_county` VALUES ('632625', '久治县', '632600');
INSERT INTO `r_county` VALUES ('632626', '玛多县', '632600');
INSERT INTO `r_county` VALUES ('632721', '玉树县', '632700');
INSERT INTO `r_county` VALUES ('632722', '杂多县', '632700');
INSERT INTO `r_county` VALUES ('632723', '称多县', '632700');
INSERT INTO `r_county` VALUES ('632724', '治多县', '632700');
INSERT INTO `r_county` VALUES ('632725', '囊谦县', '632700');
INSERT INTO `r_county` VALUES ('632726', '曲麻莱县', '632700');
INSERT INTO `r_county` VALUES ('632801', '格尔木市', '632800');
INSERT INTO `r_county` VALUES ('632802', '德令哈市', '632800');
INSERT INTO `r_county` VALUES ('632821', '乌兰县', '632800');
INSERT INTO `r_county` VALUES ('632822', '都兰县', '632800');
INSERT INTO `r_county` VALUES ('632823', '天峻县', '632800');
INSERT INTO `r_county` VALUES ('640101', '市辖区', '640100');
INSERT INTO `r_county` VALUES ('640104', '兴庆区', '640100');
INSERT INTO `r_county` VALUES ('640105', '西夏区', '640100');
INSERT INTO `r_county` VALUES ('640106', '金凤区', '640100');
INSERT INTO `r_county` VALUES ('640121', '永宁县', '640100');
INSERT INTO `r_county` VALUES ('640122', '贺兰县', '640100');
INSERT INTO `r_county` VALUES ('640181', '灵武市', '640100');
INSERT INTO `r_county` VALUES ('640201', '市辖区', '640200');
INSERT INTO `r_county` VALUES ('640202', '大武口区', '640200');
INSERT INTO `r_county` VALUES ('640205', '惠农区', '640200');
INSERT INTO `r_county` VALUES ('640221', '平罗县', '640200');
INSERT INTO `r_county` VALUES ('640301', '市辖区', '640300');
INSERT INTO `r_county` VALUES ('640302', '利通区', '640300');
INSERT INTO `r_county` VALUES ('640323', '盐池县', '640300');
INSERT INTO `r_county` VALUES ('640324', '同心县', '640300');
INSERT INTO `r_county` VALUES ('640381', '青铜峡市', '640300');
INSERT INTO `r_county` VALUES ('640401', '市辖区', '640400');
INSERT INTO `r_county` VALUES ('640402', '原州区', '640400');
INSERT INTO `r_county` VALUES ('640422', '西吉县', '640400');
INSERT INTO `r_county` VALUES ('640423', '隆德县', '640400');
INSERT INTO `r_county` VALUES ('640424', '泾源县', '640400');
INSERT INTO `r_county` VALUES ('640425', '彭阳县', '640400');
INSERT INTO `r_county` VALUES ('640501', '市辖区', '640500');
INSERT INTO `r_county` VALUES ('640502', '沙坡头区', '640500');
INSERT INTO `r_county` VALUES ('640521', '中宁县', '640500');
INSERT INTO `r_county` VALUES ('640522', '海原县', '640400');
INSERT INTO `r_county` VALUES ('650101', '市辖区', '650100');
INSERT INTO `r_county` VALUES ('650102', '天山区', '650100');
INSERT INTO `r_county` VALUES ('650103', '沙依巴克区', '650100');
INSERT INTO `r_county` VALUES ('650104', '新市区', '650100');
INSERT INTO `r_county` VALUES ('650105', '水磨沟区', '650100');
INSERT INTO `r_county` VALUES ('650106', '头屯河区', '650100');
INSERT INTO `r_county` VALUES ('650107', '达坂城区', '650100');
INSERT INTO `r_county` VALUES ('650108', '东山区', '650100');
INSERT INTO `r_county` VALUES ('650121', '乌鲁木齐县', '650100');
INSERT INTO `r_county` VALUES ('650201', '市辖区', '650200');
INSERT INTO `r_county` VALUES ('650202', '独山子区', '650200');
INSERT INTO `r_county` VALUES ('650203', '克拉玛依区', '650200');
INSERT INTO `r_county` VALUES ('650204', '白碱滩区', '650200');
INSERT INTO `r_county` VALUES ('650205', '乌尔禾区', '650200');
INSERT INTO `r_county` VALUES ('652101', '吐鲁番市', '652100');
INSERT INTO `r_county` VALUES ('652122', '鄯善县', '652100');
INSERT INTO `r_county` VALUES ('652123', '托克逊县', '652100');
INSERT INTO `r_county` VALUES ('652201', '哈密市', '652200');
INSERT INTO `r_county` VALUES ('652222', '巴里坤哈萨克自治县', '652200');
INSERT INTO `r_county` VALUES ('652223', '伊吾县', '652200');
INSERT INTO `r_county` VALUES ('652301', '昌吉市', '652300');
INSERT INTO `r_county` VALUES ('652302', '阜康市', '652300');
INSERT INTO `r_county` VALUES ('652303', '米泉市', '652300');
INSERT INTO `r_county` VALUES ('652323', '呼图壁县', '652300');
INSERT INTO `r_county` VALUES ('652324', '玛纳斯县', '652300');
INSERT INTO `r_county` VALUES ('652325', '奇台县', '652300');
INSERT INTO `r_county` VALUES ('652327', '吉木萨尔县', '652300');
INSERT INTO `r_county` VALUES ('652328', '木垒哈萨克自治县', '652300');
INSERT INTO `r_county` VALUES ('652701', '博乐市', '652700');
INSERT INTO `r_county` VALUES ('652722', '精河县', '652700');
INSERT INTO `r_county` VALUES ('652723', '温泉县', '652700');
INSERT INTO `r_county` VALUES ('652801', '库尔勒市', '652800');
INSERT INTO `r_county` VALUES ('652822', '轮台县', '652800');
INSERT INTO `r_county` VALUES ('652823', '尉犁县', '652800');
INSERT INTO `r_county` VALUES ('652824', '若羌县', '652800');
INSERT INTO `r_county` VALUES ('652825', '且末县', '652800');
INSERT INTO `r_county` VALUES ('652826', '焉耆回族自治县', '652800');
INSERT INTO `r_county` VALUES ('652827', '和静县', '652800');
INSERT INTO `r_county` VALUES ('652828', '和硕县', '652800');
INSERT INTO `r_county` VALUES ('652829', '博湖县', '652800');
INSERT INTO `r_county` VALUES ('652901', '阿克苏市', '652900');
INSERT INTO `r_county` VALUES ('652922', '温宿县', '652900');
INSERT INTO `r_county` VALUES ('652923', '库车县', '652900');
INSERT INTO `r_county` VALUES ('652924', '沙雅县', '652900');
INSERT INTO `r_county` VALUES ('652925', '新和县', '652900');
INSERT INTO `r_county` VALUES ('652926', '拜城县', '652900');
INSERT INTO `r_county` VALUES ('652927', '乌什县', '652900');
INSERT INTO `r_county` VALUES ('652928', '阿瓦提县', '652900');
INSERT INTO `r_county` VALUES ('652929', '柯坪县', '652900');
INSERT INTO `r_county` VALUES ('653001', '阿图什市', '653000');
INSERT INTO `r_county` VALUES ('653022', '阿克陶县', '653000');
INSERT INTO `r_county` VALUES ('653023', '阿合奇县', '653000');
INSERT INTO `r_county` VALUES ('653024', '乌恰县', '653000');
INSERT INTO `r_county` VALUES ('653101', '喀什市', '653100');
INSERT INTO `r_county` VALUES ('653121', '疏附县', '653100');
INSERT INTO `r_county` VALUES ('653122', '疏勒县', '653100');
INSERT INTO `r_county` VALUES ('653123', '英吉沙县', '653100');
INSERT INTO `r_county` VALUES ('653124', '泽普县', '653100');
INSERT INTO `r_county` VALUES ('653125', '莎车县', '653100');
INSERT INTO `r_county` VALUES ('653126', '叶城县', '653100');
INSERT INTO `r_county` VALUES ('653127', '麦盖提县', '653100');
INSERT INTO `r_county` VALUES ('653128', '岳普湖县', '653100');
INSERT INTO `r_county` VALUES ('653129', '伽师县', '653100');
INSERT INTO `r_county` VALUES ('653130', '巴楚县', '653100');
INSERT INTO `r_county` VALUES ('653131', '塔什库尔干塔吉克自治县', '653100');
INSERT INTO `r_county` VALUES ('653201', '和田市', '653200');
INSERT INTO `r_county` VALUES ('653221', '和田县', '653200');
INSERT INTO `r_county` VALUES ('653222', '墨玉县', '653200');
INSERT INTO `r_county` VALUES ('653223', '皮山县', '653200');
INSERT INTO `r_county` VALUES ('653224', '洛浦县', '653200');
INSERT INTO `r_county` VALUES ('653225', '策勒县', '653200');
INSERT INTO `r_county` VALUES ('653226', '于田县', '653200');
INSERT INTO `r_county` VALUES ('653227', '民丰县', '653200');
INSERT INTO `r_county` VALUES ('654002', '伊宁市', '654000');
INSERT INTO `r_county` VALUES ('654003', '奎屯市', '654000');
INSERT INTO `r_county` VALUES ('654021', '伊宁县', '654000');
INSERT INTO `r_county` VALUES ('654022', '察布查尔锡伯自治县', '654000');
INSERT INTO `r_county` VALUES ('654023', '霍城县', '654000');
INSERT INTO `r_county` VALUES ('654024', '巩留县', '654000');
INSERT INTO `r_county` VALUES ('654025', '新源县', '654000');
INSERT INTO `r_county` VALUES ('654026', '昭苏县', '654000');
INSERT INTO `r_county` VALUES ('654027', '特克斯县', '654000');
INSERT INTO `r_county` VALUES ('654028', '尼勒克县', '654000');
INSERT INTO `r_county` VALUES ('654201', '塔城市', '654200');
INSERT INTO `r_county` VALUES ('654202', '乌苏市', '654200');
INSERT INTO `r_county` VALUES ('654221', '额敏县', '654200');
INSERT INTO `r_county` VALUES ('654223', '沙湾县', '654200');
INSERT INTO `r_county` VALUES ('654224', '托里县', '654200');
INSERT INTO `r_county` VALUES ('654225', '裕民县', '654200');
INSERT INTO `r_county` VALUES ('654226', '和布克赛尔蒙古自治县', '654200');
INSERT INTO `r_county` VALUES ('654301', '阿勒泰市', '654300');
INSERT INTO `r_county` VALUES ('654321', '布尔津县', '654300');
INSERT INTO `r_county` VALUES ('654322', '富蕴县', '654300');
INSERT INTO `r_county` VALUES ('654323', '福海县', '654300');
INSERT INTO `r_county` VALUES ('654324', '哈巴河县', '654300');
INSERT INTO `r_county` VALUES ('654325', '青河县', '654300');
INSERT INTO `r_county` VALUES ('654326', '吉木乃县', '654300');
INSERT INTO `r_county` VALUES ('659001', '石河子市', '659000');
INSERT INTO `r_county` VALUES ('659002', '阿拉尔市', '659000');
INSERT INTO `r_county` VALUES ('659003', '图木舒克市', '659000');
INSERT INTO `r_county` VALUES ('659004', '五家渠市', '659000');

-- ----------------------------
-- Table structure for r_province
-- ----------------------------
DROP TABLE IF EXISTS `r_province`;
CREATE TABLE `r_province` (
  `province_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `province_name` varchar(64) DEFAULT NULL COMMENT '省名',
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=820001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_province
-- ----------------------------
INSERT INTO `r_province` VALUES ('110000', '北京市');
INSERT INTO `r_province` VALUES ('120000', '天津市');
INSERT INTO `r_province` VALUES ('130000', '河北省');
INSERT INTO `r_province` VALUES ('140000', '山西省');
INSERT INTO `r_province` VALUES ('150000', '内蒙古自治区');
INSERT INTO `r_province` VALUES ('210000', '辽宁省');
INSERT INTO `r_province` VALUES ('220000', '吉林省');
INSERT INTO `r_province` VALUES ('230000', '黑龙江省');
INSERT INTO `r_province` VALUES ('310000', '上海市');
INSERT INTO `r_province` VALUES ('320000', '江苏省');
INSERT INTO `r_province` VALUES ('330000', '浙江省');
INSERT INTO `r_province` VALUES ('340000', '安徽省');
INSERT INTO `r_province` VALUES ('350000', '福建省');
INSERT INTO `r_province` VALUES ('360000', '江西省');
INSERT INTO `r_province` VALUES ('370000', '山东省');
INSERT INTO `r_province` VALUES ('410000', '河南省');
INSERT INTO `r_province` VALUES ('420000', '湖北省');
INSERT INTO `r_province` VALUES ('430000', '湖南省');
INSERT INTO `r_province` VALUES ('440000', '广东省');
INSERT INTO `r_province` VALUES ('450000', '广西壮族自治区');
INSERT INTO `r_province` VALUES ('460000', '海南省');
INSERT INTO `r_province` VALUES ('500000', '重庆市');
INSERT INTO `r_province` VALUES ('510000', '四川省');
INSERT INTO `r_province` VALUES ('520000', '贵州省');
INSERT INTO `r_province` VALUES ('530000', '云南省');
INSERT INTO `r_province` VALUES ('540000', '西藏自治区');
INSERT INTO `r_province` VALUES ('610000', '陕西省');
INSERT INTO `r_province` VALUES ('620000', '甘肃省');
INSERT INTO `r_province` VALUES ('630000', '青海省');
INSERT INTO `r_province` VALUES ('640000', '宁夏回族自治区');
INSERT INTO `r_province` VALUES ('650000', '新疆维吾尔自治区');
INSERT INTO `r_province` VALUES ('710000', '台湾省');
INSERT INTO `r_province` VALUES ('810000', '香港特别行政区');
INSERT INTO `r_province` VALUES ('820000', '澳门特别行政区');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `sub_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(30) DEFAULT NULL COMMENT '学科名',
  PRIMARY KEY (`sub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='学科';

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '语文');
INSERT INTO `subject` VALUES ('2', '数学');
INSERT INTO `subject` VALUES ('3', '英语');
INSERT INTO `subject` VALUES ('4', '历史');
INSERT INTO `subject` VALUES ('5', '地理');
INSERT INTO `subject` VALUES ('6', '政治');
INSERT INTO `subject` VALUES ('7', '生物');
INSERT INTO `subject` VALUES ('8', '物理');
INSERT INTO `subject` VALUES ('9', '化学');

-- ----------------------------
-- Table structure for sysarticle
-- ----------------------------
DROP TABLE IF EXISTS `sysarticle`;
CREATE TABLE `sysarticle` (
  `article_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_title` varchar(120) NOT NULL DEFAULT '' COMMENT '文章标题',
  `article_type` int(1) NOT NULL DEFAULT '0' COMMENT '文章分类：0-内置文章；1-网站帮助；2-网站公告；',
  `content_type` int(1) NOT NULL DEFAULT '0' COMMENT '内容类型：0-HTML；1-URL；',
  `content_value` text COMMENT ' 内容值',
  `validate_from` int(10) DEFAULT '0' COMMENT '验证from',
  `validate_to` int(10) DEFAULT '0' COMMENT '验证to',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-显示；1-隐藏；',
  `sort_order` int(3) DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统文章';

-- ----------------------------
-- Records of sysarticle
-- ----------------------------
INSERT INTO `sysarticle` VALUES ('1', '用户注册协议', '0', '0', '', '0', '0', '0', '999');
INSERT INTO `sysarticle` VALUES ('2', '网站服务流程', '1', '0', '', '0', '0', '0', '999');
INSERT INTO `sysarticle` VALUES ('3', '新的网站帮助', '1', '0', '<p><img src=\"/hui//upload/article/4a018c3a-11ca-471e-9c24-5c57eb274eee.jpg\" /></p>\r\n', '1400574370', '1464251176', '0', '999');

-- ----------------------------
-- Table structure for sysauth
-- ----------------------------
DROP TABLE IF EXISTS `sysauth`;
CREATE TABLE `sysauth` (
  `auth_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(10) unsigned NOT NULL COMMENT '权限id',
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of sysauth
-- ----------------------------
INSERT INTO `sysauth` VALUES ('9', '1', '11');
INSERT INTO `sysauth` VALUES ('10', '1', '111');
INSERT INTO `sysauth` VALUES ('11', '1', '11101');
INSERT INTO `sysauth` VALUES ('12', '1', '112');
INSERT INTO `sysauth` VALUES ('13', '1', '11201');
INSERT INTO `sysauth` VALUES ('14', '1', '11202');
INSERT INTO `sysauth` VALUES ('15', '1', '11203');
INSERT INTO `sysauth` VALUES ('16', '1', '11204');
INSERT INTO `sysauth` VALUES ('17', '1', '11205');
INSERT INTO `sysauth` VALUES ('18', '1', '11206');
INSERT INTO `sysauth` VALUES ('19', '1', '11207');
INSERT INTO `sysauth` VALUES ('20', '1', '11208');
INSERT INTO `sysauth` VALUES ('21', '1', '11209');
INSERT INTO `sysauth` VALUES ('22', '1', '11210');
INSERT INTO `sysauth` VALUES ('23', '1', '113');
INSERT INTO `sysauth` VALUES ('24', '1', '11301');
INSERT INTO `sysauth` VALUES ('25', '1', '11302');
INSERT INTO `sysauth` VALUES ('26', '1', '11303');
INSERT INTO `sysauth` VALUES ('27', '1', '11304');
INSERT INTO `sysauth` VALUES ('28', '1', '11305');
INSERT INTO `sysauth` VALUES ('29', '1', '11306');
INSERT INTO `sysauth` VALUES ('30', '1', '11307');
INSERT INTO `sysauth` VALUES ('31', '1', '11308');
INSERT INTO `sysauth` VALUES ('32', '1', '11309');
INSERT INTO `sysauth` VALUES ('33', '1', '11310');
INSERT INTO `sysauth` VALUES ('34', '1', '11311');
INSERT INTO `sysauth` VALUES ('35', '1', '11312');
INSERT INTO `sysauth` VALUES ('36', '1', '11313');
INSERT INTO `sysauth` VALUES ('37', '1', '11314');
INSERT INTO `sysauth` VALUES ('38', '1', '11315');
INSERT INTO `sysauth` VALUES ('39', '1', '12');
INSERT INTO `sysauth` VALUES ('40', '1', '121');
INSERT INTO `sysauth` VALUES ('41', '1', '12101');
INSERT INTO `sysauth` VALUES ('42', '1', '12102');
INSERT INTO `sysauth` VALUES ('43', '1', '122');
INSERT INTO `sysauth` VALUES ('44', '1', '12201');
INSERT INTO `sysauth` VALUES ('45', '1', '12202');
INSERT INTO `sysauth` VALUES ('46', '1', '12203');
INSERT INTO `sysauth` VALUES ('47', '1', '12204');
INSERT INTO `sysauth` VALUES ('48', '1', '123');
INSERT INTO `sysauth` VALUES ('49', '1', '12301');
INSERT INTO `sysauth` VALUES ('50', '1', '12302');
INSERT INTO `sysauth` VALUES ('51', '1', '12303');
INSERT INTO `sysauth` VALUES ('52', '1', '12304');
INSERT INTO `sysauth` VALUES ('53', '1', '12305');
INSERT INTO `sysauth` VALUES ('54', '1', '124');
INSERT INTO `sysauth` VALUES ('55', '1', '12401');
INSERT INTO `sysauth` VALUES ('56', '1', '12402');
INSERT INTO `sysauth` VALUES ('57', '1', '125');
INSERT INTO `sysauth` VALUES ('58', '1', '12501');
INSERT INTO `sysauth` VALUES ('59', '1', '12502');
INSERT INTO `sysauth` VALUES ('60', '1', '12503');
INSERT INTO `sysauth` VALUES ('61', '1', '13');
INSERT INTO `sysauth` VALUES ('62', '1', '131');
INSERT INTO `sysauth` VALUES ('63', '1', '13101');
INSERT INTO `sysauth` VALUES ('64', '1', '13102');
INSERT INTO `sysauth` VALUES ('65', '1', '13103');
INSERT INTO `sysauth` VALUES ('66', '1', '14');
INSERT INTO `sysauth` VALUES ('67', '1', '141');
INSERT INTO `sysauth` VALUES ('68', '1', '14101');
INSERT INTO `sysauth` VALUES ('69', '1', '142');
INSERT INTO `sysauth` VALUES ('70', '1', '14201');
INSERT INTO `sysauth` VALUES ('71', '1', '143');
INSERT INTO `sysauth` VALUES ('72', '1', '14301');
INSERT INTO `sysauth` VALUES ('73', '1', '14302');
INSERT INTO `sysauth` VALUES ('74', '1', '144');
INSERT INTO `sysauth` VALUES ('75', '1', '14401');
INSERT INTO `sysauth` VALUES ('76', '1', '14402');
INSERT INTO `sysauth` VALUES ('77', '1', '145');
INSERT INTO `sysauth` VALUES ('78', '1', '14501');

-- ----------------------------
-- Table structure for sysbackup
-- ----------------------------
DROP TABLE IF EXISTS `sysbackup`;
CREATE TABLE `sysbackup` (
  `backup_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `seq_id` int(10) unsigned NOT NULL COMMENT '批次号',
  `tab_id` int(10) unsigned NOT NULL COMMENT '系统表id',
  `backup_type` int(1) NOT NULL DEFAULT '0' COMMENT '数据备份方式：0-定期备份；1-手动备份；',
  `backup_form` int(1) NOT NULL DEFAULT '0' COMMENT '数据备份类型：0-增量；1-全量；',
  `start_items` int(10) NOT NULL DEFAULT '0',
  `end_items` int(10) NOT NULL DEFAULT '0',
  `file_path` varchar(512) NOT NULL DEFAULT '',
  `file_items` int(3) DEFAULT '0',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-待备份；1-备份中；2-备份成功；3-备份失败；',
  `backup_time` int(10) DEFAULT '0',
  `notes` varchar(256) DEFAULT '',
  PRIMARY KEY (`backup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统备份';

-- ----------------------------
-- Records of sysbackup
-- ----------------------------

-- ----------------------------
-- Table structure for syscache
-- ----------------------------
DROP TABLE IF EXISTS `syscache`;
CREATE TABLE `syscache` (
  `cache_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cache_code` varchar(64) NOT NULL DEFAULT '',
  `refresh_period` int(10) NOT NULL DEFAULT '3600' COMMENT '缓存更新周期：单位为秒，值为0则取默认值更新缓存',
  `range` varchar(64) DEFAULT '0~86400',
  `notes` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`cache_id`),
  UNIQUE KEY `cache_code_UNIQUE` (`cache_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syscache
-- ----------------------------
INSERT INTO `syscache` VALUES ('1', 'index_cache', '3600', '0~86400', '首页缓存更新周期(单位为秒)');
INSERT INTO `syscache` VALUES ('2', 'sysConfig_cache', '3600', '0~86400', '系统配置缓存更新周期(单位为秒)');
INSERT INTO `syscache` VALUES ('3', 'sysData_cache', '3600', '0~86400', '基础数据缓存更新周期(单位为秒)');
INSERT INTO `syscache` VALUES ('4', 'admin_auths_cache', '3600', '0~86400', '管理员权限缓存更新周期(单位为秒)');
INSERT INTO `syscache` VALUES ('5', 'admin_opt_auths_cache', '3600', '0~86400', '管理员操作权限缓存更新周期(单位为秒)');
INSERT INTO `syscache` VALUES ('6', 'user_auths_cache', '3600', '0~86400', '会员权限缓存更新周期(单位为秒)');

-- ----------------------------
-- Table structure for syscfg
-- ----------------------------
DROP TABLE IF EXISTS `syscfg`;
CREATE TABLE `syscfg` (
  `config_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `config_code` varchar(32) NOT NULL DEFAULT '',
  `config_value` varchar(512) NOT NULL DEFAULT '',
  `version` varchar(64) DEFAULT NULL COMMENT '版本信息',
  `value_type` varchar(16) NOT NULL DEFAULT 'string' COMMENT '值类型：string-字符串；date-日期；datetime-时间；array-数组；int-整数；',
  `notes` varchar(128) DEFAULT NULL,
  `detail` varchar(256) DEFAULT NULL,
  `range` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `config_code_UNIQUE` (`config_code`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syscfg
-- ----------------------------
INSERT INTO `syscfg` VALUES ('1', 'webstatus', '1', null, 'array', '网站状态', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('2', 'close_reason', '', null, 'string', '网站关闭原因', null, null);
INSERT INTO `syscfg` VALUES ('3', 'regable', '0', null, 'array', '开放注册', '0-是；1-否；', null);
INSERT INTO `syscfg` VALUES ('4', 'webtitle', '汇答题', null, 'string', '网站标题', null, null);
INSERT INTO `syscfg` VALUES ('5', 'webkeywords', '南京，汇答题', null, 'string', '网站关键字', null, null);
INSERT INTO `syscfg` VALUES ('6', 'webdescription', '南京汇答题', null, 'string', '网站描述', null, null);
INSERT INTO `syscfg` VALUES ('7', 'traffic_statistics', 'document.write(\"流量统计\");', null, 'string', '流量统计代码', '例如百度统计代码', null);
INSERT INTO `syscfg` VALUES ('8', 'front_login_valicode', '1', null, 'array', '前台登录验证码', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('9', 'front_reg_valicode', '1', null, 'array', '前台注册验证码', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('10', 'back_login_valicode', '1', null, 'array', '后台登录验证码', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('11', 'back_menu_count', '6', null, 'int', '后台菜单最大打开数量', null, '2~16');
INSERT INTO `syscfg` VALUES ('12', 'article_img_count', '6', null, 'int', '文章上传图片最大数量', null, '1~20');
INSERT INTO `syscfg` VALUES ('13', 'long_date_format', 'yyyy-MM-dd HH:mm:ss', null, 'string', '日期格式化', null, null);
INSERT INTO `syscfg` VALUES ('14', 'short_date_format', 'yyyy-MM-dd', null, 'string', '日期格式化', null, null);
INSERT INTO `syscfg` VALUES ('15', 'file_path', '/usr/soft/hui_files', null, 'string', '生成文件存储路径', null, null);
INSERT INTO `syscfg` VALUES ('16', 'file_max_size', '1', null, 'int', '生成单个文件最大容量(单位为MB)', null, '1~10');
INSERT INTO `syscfg` VALUES ('17', 'backup', '1', null, 'array', '定期备份数据', '定期备份数据：0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('18', 'backup_hand', '1', null, 'array', '手动备份数据', '手动备份数据：0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('19', 'backup_form', '0', null, 'array', '数据备份类型', '数据备份类型：0-增量；1-全量；', null);
INSERT INTO `syscfg` VALUES ('20', 'backup_period', '1', null, 'array', '数据备份周期', '数据备份周期：0-每天；1-每周；2-每月；3-每季度；4-每半年；5-每年；', null);
INSERT INTO `syscfg` VALUES ('21', 'incre_backup_items', '100', null, 'int', '增量数据备份记录数', null, '100~10000');
INSERT INTO `syscfg` VALUES ('22', 'next_backup_date', '1402329600', null, 'date', '下次备份数据日期', null, null);
INSERT INTO `syscfg` VALUES ('23', 'logable', '1', null, 'array', '记录日志', '记录日志：0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('24', 'logger_type', '2', null, 'array', '日志记录方式', '日志记录方式：0-文件；1-数据库；2-文件和数据库；', null);
INSERT INTO `syscfg` VALUES ('25', 'error_log', '1', null, 'array', '记录方法异常日志', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('26', 'login_log', '1', null, 'array', '记录登录登出日志', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('27', 'syscfg_log', '1', null, 'array', '记录系统配置日志', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('28', 'job_log', '1', null, 'array', '记录定时任务日志', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('29', 'file_path_prefix', 'http://113.10.158.175:8080/huiFiles', null, 'string', '文件路径前缀', null, null);
INSERT INTO `syscfg` VALUES ('30', 'file_upload_path', '/usr/soft/hui_files/images', null, 'string', '文件上传路径', null, null);
INSERT INTO `syscfg` VALUES ('32', 'file_read_path', '/huiFiles', null, 'string', '文件读取路径', null, null);

INSERT INTO `syscfg` VALUES (33,'login_fail_maxtimes','5', null,'int','登录失败锁定用户最大次数',null,'1~10');
INSERT INTO `syscfg` VALUES (34,'login_fail_lockdates','0', null,'array','登录失败锁定用户时间','登录失败锁定用户时间：0-一天；1-一周；2-一月；3-一季度；4-半年；5-一年；',null);


-- ----------------------------
-- Table structure for sysdata
-- ----------------------------
DROP TABLE IF EXISTS `sysdata`;
CREATE TABLE `sysdata` (
  `data_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data_code` varchar(32) NOT NULL DEFAULT '',
  `data_value` varchar(512) NOT NULL DEFAULT '',
  `detail` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`data_id`),
  UNIQUE KEY `data_code_UNIQUE` (`data_code`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysdata
-- ----------------------------
INSERT INTO `sysdata` VALUES ('1', 'webstatus', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '网站状态：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('2', 'regable', '[{\"0\":\"是\",\"1\":\"否\"}]', '开放注册：0-是；1-否；');
INSERT INTO `sysdata` VALUES ('3', 'front_login_valicode', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '前台登录验证码：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('4', 'front_reg_valicode', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '前台注册验证码：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('5', 'back_login_valicode', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '后台登录验证码：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('6', 'sex', '[{\"0\":\"保密\",\"1\":\"男\",\"2\":\"女\"}]', '性别：0-保密；1-男；2-女；');
INSERT INTO `sysdata` VALUES ('7', 'status', '[{\"0\":\"正常\",\"1\":\"锁定\",\"2\":\"删除\"}]', '状态：0-正常；1-锁定；2-删除；');
INSERT INTO `sysdata` VALUES ('8', 'show_status', '[{\"0\":\"显示\",\"1\":\"隐藏\"}]', '状态：0-显示；1-隐藏；');
INSERT INTO `sysdata` VALUES ('9', 'article_type', '[{\"0\":\"内置文章\",\"1\":\"网站帮助\",\"2\":\"网站公告\"}]', '文章分类：0-内置文章；1-网站帮助；2-网站公告；');
INSERT INTO `sysdata` VALUES ('10', 'content_type', '[{\"0\":\"HTML\",\"1\":\"URL\"}]', '内容类型：0-HTML；1-URL；');
INSERT INTO `sysdata` VALUES ('11', 'backup', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '定期备份数据：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('12', 'backup_hand', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '手动备份数据：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('13', 'backup_type', '[{\"0\":\"定期备份\",\"1\":\"手动备份\"}]', '数据备份方式：0-定期备份；1-手动备份；');
INSERT INTO `sysdata` VALUES ('14', 'backup_form', '[{\"0\":\"增量\",\"1\":\"全量\"}]', '数据备份类型：0-增量；1-全量；');
INSERT INTO `sysdata` VALUES ('15', 'backup_period', '[{\"0\":\"每天\",\"1\":\"每周\",\"2\":\"每月\",\"3\":\"每季度\",\"4\":\"每半年\",\"5\":\"每年\"}]', '数据备份周期：0-每天；1-每周；2-每月；3-每季度；4-每半年；5-每年；');
INSERT INTO `sysdata` VALUES ('16', 'backup_status', '[{\"0\":\"待备份\",\"1\":\"备份中\",\"2\":\"备份成功\",\"3\":\"备份失败\"}]', '状态：0-待备份；1-备份中；2-备份成功；3-备份失败；');
INSERT INTO `sysdata` VALUES ('17', 'logable', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '记录日志：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('18', 'logger_type', '[{\"0\":\"文件\",\"1\":\"数据库\",\"2\":\"文件和数据库\"}]', '日志记录方式：0-文件；1-数据库；2-文件和数据库；');
INSERT INTO `sysdata` VALUES ('19', 'operator_type', '[{\"0\":\"系统\",\"1\":\"会员\",\"2\":\"管理员\",\"3\":\"其他\"}]', '操作用户类型：0-系统；1-会员；2-管理员；3-其他；');
INSERT INTO `sysdata` VALUES ('20', 'operate_type', '[{\"0\":\"方法异常\",\"1\":\"登录登出\",\"2\":\"系统配置\",\"3\":\"定时任务\"}]', '操作类型：0-方法异常；1-登录登出；2-系统配置；3-定时任务；');
INSERT INTO `sysdata` VALUES ('21', 'error_log', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '记录方法异常日志：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('22', 'login_log', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '记录登录登出日志：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('23', 'syscfg_log', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '记录系统配置日志：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('24', 'job_log', '[{\"0\":\"关闭\",\"1\":\"开启\"}]', '记录定时任务日志：0-关闭；1-开启；');
INSERT INTO `sysdata` VALUES ('25', 'log_level', '[{\"0\":\"INFO\",\"1\":\"ERROR\"}]', '日志级别：0-INFO；1-ERROR；');
INSERT INTO `sysdata` VALUES ('26', 'code_location', '[{\"url\":\"http://113.10.158.175:8080/huiFiles/code2.png\"}]', '二维码地址');
INSERT INTO `sysdata` VALUES ('27', 'soft_version', '[{\"type\":\"ios\",\"version\":\"1.1\"},{\"type\":\"android\",\"version\":\"1.5\"}]', '软件版本');
INSERT INTO `sysdata` VALUES ('28', 'soft_download', '[{\"url\":\"http://113.10.158.175:8080/huiFiles/HDT.apk \"}]', '软件下载地址');
INSERT INTO `sysdata` VALUES ('29', 'in_out', '[{\"0\":\"流入\",\"1\":\"流出\"}]', '流水方向');
INSERT INTO `sysdata` VALUES ('30', 'flow_source', '[{\"0\":\"未知\",\"1\":\"支付宝\"}]', '流水来源');
INSERT INTO `sysdata` VALUES ('31', 'online_status', '[{\"0\":\"离线\",\"1\":\"在线\"}]', '在线状态');
INSERT INTO `sysdata` VALUES ('32', 'teacher_title', '[{\"0\":\"家教\",\"1\":\"教师\",\"2\":\"高级教师\",\"3\":\"特级教师\"}]', '教师头衔');
INSERT INTO `sysdata` VALUES ('34', 'sysmsg_status', '[{\"0\":\"废弃\",\"1\":\"正常\",\"2\":\"冻结\"}]', '系统消息状态');
INSERT INTO `sysdata` VALUES ('35', 'allocated', '[{\"0\":\"未分配\",\"1\":\"已分配\"}]', null);
INSERT INTO `sysdata` VALUES (36,'login_fail_lockdates','[{"0":"一天","1":"一周","2":"一月","3":"一季度","4":"半年","5":"一年"}]','登录失败锁定用户时间：0-一天；1-一周；2-一月；3-一季度；4-半年；5-一年；');

-- ----------------------------
-- Table structure for sysfile
-- ----------------------------
DROP TABLE IF EXISTS `sysfile`;
CREATE TABLE `sysfile` (
  `file_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '上传用户模板，则为1；会员头像，则为会员ID；',
  `seq_id` int(3) unsigned DEFAULT '0' COMMENT '例如文章图片有多个',
  `file_name` varchar(64) NOT NULL DEFAULT '',
  `file_path` varchar(512) NOT NULL DEFAULT '',
  `file_type` int(2) NOT NULL DEFAULT '0' COMMENT '文件类型：0-下载模板；1-会员头像；2-管理员头像；3-文章图片；4-提问图片；5-回答图片；6-帮助图片;7-系统消息图片',
  `path_type` int(1) NOT NULL DEFAULT '0' COMMENT '路径类型：0-CONTEXT；1-URL；',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysfile
-- ----------------------------
INSERT INTO `sysfile` VALUES ('1', '1', '0', 'impuser.xls', '/template/excel', '0', '0');
INSERT INTO `sysfile` VALUES ('2', '1', '1', 'xxx.jpg', '/template/img', '4', '0');
INSERT INTO `sysfile` VALUES ('3', '1', '2', 'yyy.jpg', '/template/img', '4', '0');
INSERT INTO `sysfile` VALUES ('4', '1', '1', 'sysMsg.jpg', '/template/img', '7', '0');
INSERT INTO `sysfile` VALUES ('6', '3', '2', '4a018c3a-11ca-471e-9c24-5c57eb274eee.jpg', '/upload/article', '3', '0');
INSERT INTO `sysfile` VALUES ('7', '1', '0', '0a8ada1e-47aa-4ed7-957a-66290f42d7bb.jpg', '/upload/profile/admin', '2', '0');
INSERT INTO `sysfile` VALUES ('8', '93607543', '2', '93607543_original_icon.jpg', '/userPhoto/original/', '1', '0');
INSERT INTO `sysfile` VALUES ('9', '93607543', '1', '93607543_thumbnail_icon.jpg', '/userPhoto/thumbnail/', '1', '0');
INSERT INTO `sysfile` VALUES ('10', '1', '1', 'ae1aeef8-b517-481b-a7ae-4429591a5165.jpg', '/article', '3', '0');
INSERT INTO `sysfile` VALUES ('11', '24', '2', 'a6544245-1e20-4fa4-b87b-a1e8d9defbeb.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('12', '25', '2', '7ba21184-45a3-4506-945f-4fa0d21d6b2a.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('13', '26', '2', '2c05443c-c09a-4470-be5f-bb895b4d0d01.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('14', '27', '2', '16ff1295-1196-433b-b925-6ca7dbffb2a8.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('15', '28', '2', '60d6998c-aab0-458e-92fe-1cf670361cb8.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('16', '29', '2', 'c32c8f93-32e9-476d-91a5-b6a1fd62f847.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('17', '29', '1', 'c32c8f93-32e9-476d-91a5-b6a1fd62f847.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('18', '30', '2', '641018c5-f281-4fdb-b22f-b88948e355c8.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('19', '30', '1', '641018c5-f281-4fdb-b22f-b88948e355c8.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('20', '31', '2', '18353b51-b589-4093-bf5c-fb6e805ed32f.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('21', '31', '1', '18353b51-b589-4093-bf5c-fb6e805ed32f.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('22', '32', '2', '6701a314-3733-4795-8630-82570f69fa7a.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('23', '32', '1', '6701a314-3733-4795-8630-82570f69fa7a.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('24', '33', '2', '73945a84-8edf-42f6-bc66-1a1886a32ca5.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('25', '33', '1', '73945a84-8edf-42f6-bc66-1a1886a32ca5.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('26', '34', '2', '59cd8617-d044-4eb2-b9d8-36d618153780.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('27', '34', '1', '59cd8617-d044-4eb2-b9d8-36d618153780-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('28', '35', '2', '52d6bbaf-7bfb-47a8-91eb-d93f9efe1bc7.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('29', '35', '1', '52d6bbaf-7bfb-47a8-91eb-d93f9efe1bc7-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('30', '36', '2', 'e4b02e4b-288d-4d1a-8ba9-f83b3882f548.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('31', '36', '1', 'e4b02e4b-288d-4d1a-8ba9-f83b3882f548-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('32', '10', '2', 'fb1dff4b-0970-465c-8206-75d70df0ad02.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('33', '10', '1', 'fb1dff4b-0970-465c-8206-75d70df0ad02-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('34', '11', '2', 'c196f332-22ef-427e-846e-417dc7d747ab.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('35', '11', '1', 'c196f332-22ef-427e-846e-417dc7d747ab-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('36', '13', '2', '469a3046-9974-480a-9ef8-ea6ef98a3100.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('37', '13', '1', '469a3046-9974-480a-9ef8-ea6ef98a3100-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('40', '7', '2', '4a6812fb-807d-4b3c-a72e-f54ed7312e76.jpg', '/sysmsg/original', '7', '0');
INSERT INTO `sysfile` VALUES ('41', '7', '1', 'c903ed5e-02c1-43ec-ad44-d28ac1ccd7cb-Lt.jpg', '/sysmsg/thumbnail', '7', '0');
INSERT INTO `sysfile` VALUES ('42', '7', '2', 'c903ed5e-02c1-43ec-ad44-d28ac1ccd7cb.jpg', '/sysmsg/original', '7', '0');
INSERT INTO `sysfile` VALUES ('43', '7', '1', '3453d318-514d-4a35-8cab-e0c600bc5ed9-Lt.jpg', '/sysmsg/thumbnail', '7', '0');
INSERT INTO `sysfile` VALUES ('44', '7', '2', '3453d318-514d-4a35-8cab-e0c600bc5ed9.jpg', '/sysmsg/original', '7', '0');
INSERT INTO `sysfile` VALUES ('45', '8', '1', '986ef588-09f4-480b-b43f-4ae182911b46-Lt.jpg', '/sysmsg/thumbnail', '7', '0');
INSERT INTO `sysfile` VALUES ('46', '8', '2', '986ef588-09f4-480b-b43f-4ae182911b46.jpg', '/sysmsg/original', '7', '0');
INSERT INTO `sysfile` VALUES ('47', '8', '1', 'df1ba41a-444d-43f8-b0d7-71494c66a9f0-Lt.jpg', '/sysmsg/thumbnail', '7', '0');
INSERT INTO `sysfile` VALUES ('48', '8', '2', 'df1ba41a-444d-43f8-b0d7-71494c66a9f0.jpg', '/sysmsg/original', '7', '0');
INSERT INTO `sysfile` VALUES ('49', '8', '1', '86e9c395-0430-40a0-855c-0c9dffd73cd5-Lt.jpg', '/sysmsg/thumbnail', '7', '0');
INSERT INTO `sysfile` VALUES ('50', '8', '2', '86e9c395-0430-40a0-855c-0c9dffd73cd5.jpg', '/sysmsg/original', '7', '0');
INSERT INTO `sysfile` VALUES ('51', '9', '1', 'e03bd076-b666-4071-bb9c-7d0c6f79dc5a-Lt.jpg', '/sysmsg/thumbnail', '7', '0');
INSERT INTO `sysfile` VALUES ('52', '9', '2', 'e03bd076-b666-4071-bb9c-7d0c6f79dc5a.jpg', '/sysmsg/original', '7', '0');
INSERT INTO `sysfile` VALUES ('53', '37', '2', 'd6b30be6-ca5b-42fa-9018-2e0b093e65a4.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('54', '37', '1', 'd6b30be6-ca5b-42fa-9018-2e0b093e65a4-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('55', '14', '2', 'a6b090a1-c206-4871-8df3-a736b95bb43c.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('56', '14', '1', 'a6b090a1-c206-4871-8df3-a736b95bb43c-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('57', '38', '2', '277dd96f-95f2-403a-b2fc-404cb0646eb3.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('58', '38', '1', '277dd96f-95f2-403a-b2fc-404cb0646eb3-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('63', '3', '2', '6bb45238-f685-44f3-b0c4-bce46eb2e26c.jpg', '/profile/user/original', '1', '0');
INSERT INTO `sysfile` VALUES ('64', '3', '1', '6bb45238-f685-44f3-b0c4-bce46eb2e26c-Lt.jpg', '/profile/user/thumbnail', '1', '0');
INSERT INTO `sysfile` VALUES ('65', '39', '2', '53f229ac-0b8b-4b41-893c-b810d536416e.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('66', '39', '1', '53f229ac-0b8b-4b41-893c-b810d536416e-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('67', '15', '2', '4a8a712a-edaa-4a09-aa21-a14b476507b6.png', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('68', '15', '1', '4a8a712a-edaa-4a09-aa21-a14b476507b6-Lt.png', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('69', '4', '2', 'fdc1026f-fa14-4b45-8f9f-13e03a362f79.jpg', '/profile/user/original', '1', '0');
INSERT INTO `sysfile` VALUES ('70', '4', '1', 'fdc1026f-fa14-4b45-8f9f-13e03a362f79-Lt.jpg', '/profile/user/thumbnail', '1', '0');
INSERT INTO `sysfile` VALUES ('71', '40', '2', '39cd2d84-92df-4ada-bb52-9eaf8a59e8ad.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('72', '40', '1', '39cd2d84-92df-4ada-bb52-9eaf8a59e8ad-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('77', '16', '2', 'b0262903-3ae4-4e2b-bece-2082cca1df8b.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('78', '16', '1', 'b0262903-3ae4-4e2b-bece-2082cca1df8b-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('79', '17', '2', 'ca9cea4b-ee20-4cb5-8116-3fc59a93b8f1.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('80', '17', '1', 'ca9cea4b-ee20-4cb5-8116-3fc59a93b8f1-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('81', '18', '2', 'bdf20469-275b-4154-b4e9-7959a38581fa.png', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('82', '18', '1', 'bdf20469-275b-4154-b4e9-7959a38581fa-Lt.png', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('83', '4', '2', '44c7cab0-8da7-462b-9410-2152691657de.jpg', '/profile/user/original', '1', '0');
INSERT INTO `sysfile` VALUES ('84', '4', '1', '44c7cab0-8da7-462b-9410-2152691657de-Lt.jpg', '/profile/user/thumbnail', '1', '0');
INSERT INTO `sysfile` VALUES ('85', '4', '2', 'fc23c2af-7abb-4f71-9954-4525557d6318.jpg', '/profile/user/original', '1', '0');
INSERT INTO `sysfile` VALUES ('86', '4', '1', 'fc23c2af-7abb-4f71-9954-4525557d6318-Lt.jpg', '/profile/user/thumbnail', '1', '0');
INSERT INTO `sysfile` VALUES ('87', '4', '2', '5f7c6d51-7dd3-448e-b728-4aa90d45a7a8.png', '/profile/user/original', '1', '0');
INSERT INTO `sysfile` VALUES ('88', '4', '1', '5f7c6d51-7dd3-448e-b728-4aa90d45a7a8-Lt.png', '/profile/user/thumbnail', '1', '0');
INSERT INTO `sysfile` VALUES ('89', '41', '2', 'f61b6389-13c4-475a-9443-77d97d8d9c70.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('90', '41', '1', 'f61b6389-13c4-475a-9443-77d97d8d9c70-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('91', '42', '2', '77a5fa73-12a4-4f1a-b5c4-a8d187e666a2.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('92', '42', '1', '77a5fa73-12a4-4f1a-b5c4-a8d187e666a2-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('93', '43', '2', '0e20e0b9-58bb-45be-b338-af93995485de.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('94', '43', '1', '0e20e0b9-58bb-45be-b338-af93995485de-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('95', '46', '2', '38bc387b-73a9-4622-a203-19d87d634d42.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('96', '46', '1', '38bc387b-73a9-4622-a203-19d87d634d42-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('97', '47', '2', '1a6428a2-1a5b-46b5-87a5-be6c86535d3e.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('98', '47', '1', '1a6428a2-1a5b-46b5-87a5-be6c86535d3e-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('99', '48', '2', 'd2474f97-cfa0-4c59-a96f-2ac71a746c44.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('100', '48', '1', 'd2474f97-cfa0-4c59-a96f-2ac71a746c44-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('101', '49', '2', '5fdad18a-a180-4115-8447-2e9c66d5de40.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('102', '49', '1', '5fdad18a-a180-4115-8447-2e9c66d5de40-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('105', '21', '2', '4a3ae1a0-2dc8-4d36-894e-18d46231f74c.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('106', '21', '1', '4a3ae1a0-2dc8-4d36-894e-18d46231f74c-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('107', '50', '2', 'c639e785-c52a-420e-a5a4-c5729e436707.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('108', '50', '1', 'c639e785-c52a-420e-a5a4-c5729e436707-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('109', '22', '2', 'b19dace3-26ad-4716-8cea-566f82df257d.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('110', '22', '1', 'b19dace3-26ad-4716-8cea-566f82df257d-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('111', '23', '2', '59fcca7e-b95c-4710-8110-d0bfc0de65a7.png', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('112', '23', '1', '59fcca7e-b95c-4710-8110-d0bfc0de65a7-Lt.png', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('113', '24', '2', '91100b79-b3ea-4a45-a179-935e361070f3.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('114', '24', '1', '91100b79-b3ea-4a45-a179-935e361070f3-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('115', '51', '2', '820c4e4a-eab4-4b88-a5e8-223f71ec6973.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('116', '51', '1', '820c4e4a-eab4-4b88-a5e8-223f71ec6973-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('117', '20', '2', '494ec821-427c-46b8-9d85-b17f2d3ed612.jpg', '/profile/user/original', '1', '0');
INSERT INTO `sysfile` VALUES ('118', '20', '1', '494ec821-427c-46b8-9d85-b17f2d3ed612-Lt.jpg', '/profile/user/thumbnail', '1', '0');
INSERT INTO `sysfile` VALUES ('119', '25', '2', '1599b30f-d8e6-4d8e-9d09-8b8cd281d676.jpg', '/answer/original', '5', '0');
INSERT INTO `sysfile` VALUES ('120', '25', '1', '1599b30f-d8e6-4d8e-9d09-8b8cd281d676-Lt.jpg', '/answer/thumbnail', '5', '0');
INSERT INTO `sysfile` VALUES ('121', '5', '2', '28a1167f-f0c4-4c94-8863-4d94f416f726.jpg', '/userPhoto/original/', '1', '0');
INSERT INTO `sysfile` VALUES ('122', '5', '1', '28a1167f-f0c4-4c94-8863-4d94f416f726-Lt.jpg', '/userPhoto/thumbnail/', '1', '0');
INSERT INTO `sysfile` VALUES ('123', '58', '2', 'd9dc3ccb-3c52-42ac-8842-66e84c30f0c4.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('124', '58', '1', 'd9dc3ccb-3c52-42ac-8842-66e84c30f0c4-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('125', '60', '2', '6dd809cf-159a-45cc-a38a-7a5eb625c2dc.jpg', '/question/original', '4', '0');
INSERT INTO `sysfile` VALUES ('126', '60', '1', '6dd809cf-159a-45cc-a38a-7a5eb625c2dc-Lt.jpg', '/question/thumbnail', '4', '0');
INSERT INTO `sysfile` VALUES ('127', '12', '2', 'ac1d9fb3-146f-4fb2-b429-9a20b2ca7d69.jpg', '/userPhoto/original/', '1', '0');
INSERT INTO `sysfile` VALUES ('128', '12', '1', 'ac1d9fb3-146f-4fb2-b429-9a20b2ca7d69-Lt.jpg', '/userPhoto/thumbnail/', '1', '0');

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `remote_ip` varchar(24) DEFAULT '',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作用户类型',
  `operator_name` varchar(120) DEFAULT '',
  `operate_type` int(1) DEFAULT '0' COMMENT '操作类型',
  `log_level` int(1) DEFAULT '0' COMMENT '日志级别',
  `log_time` int(10) DEFAULT '0',
  `class_name` varchar(256) DEFAULT '',
  `method_name` varchar(256) DEFAULT '',
  `log_info` text,
  `msg_send` varchar(64) DEFAULT NULL COMMENT '短信下发记录',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysmenu
-- ----------------------------
DROP TABLE IF EXISTS `sysmenu`;
CREATE TABLE `sysmenu` (
  `menu_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(32) NOT NULL DEFAULT '',
  `menu_name` varchar(60) NOT NULL DEFAULT '',
  `menu_type` int(1) NOT NULL DEFAULT '0' COMMENT '类型：0-菜单；1-按钮；',
  `menu_url` varchar(128) DEFAULT NULL,
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0',
  `detail` varchar(256) DEFAULT NULL,
  `role_type` int(1) DEFAULT '0' COMMENT '角色类型：0-管理员；1-会员；',
  `sort_order` int(3) DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_code_UNIQUE` (`menu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=21102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysmenu
-- ----------------------------
INSERT INTO `sysmenu` VALUES ('11', 'authmgr', '权限管理', '0', null, '0', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12', 'sysmgr', '系统管理', '0', null, '0', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('13', 'selfmgr', '个人中心', '0', null, '0', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14', 'site_mgr', '网站管理', '0', null, '0', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('21', 'usrselfmgr', '个人中心', '0', null, '0', null, '1', '999');
INSERT INTO `sysmenu` VALUES ('111', 'menumgr', '菜单管理', '0', '/back/menumgr.jsp', '11', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('112', 'rolemgr', '角色管理', '0', '/back/rolemgr.jsp', '11', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('113', 'membermgr', '用户管理', '0', '/back/membermgr.jsp', '11', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('121', 'cfgmgr', '系统配置', '0', '/back/cfgmgr.jsp', '12', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('122', 'cachemgr', '缓存管理', '0', '/back/cachemgr.jsp', '12', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('123', 'articlemgr', '文章管理', '0', '/back/articlemgr.jsp', '12', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('124', 'logmgr', '日志管理', '0', '/back/logmgr.jsp', '12', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('125', 'backupmgr', '数据备份', '0', '/back/backupmgr.jsp', '12', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('131', 'admininfo', '个人信息维护', '0', '/back/admininfo.jsp', '13', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('141', 'beanflowmgr', '汇豆流水', '0', '/back/beanflowmgr.jsp', '14', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('142', 'cashflowmgr', '现金流水', '0', '/back/cashflowmgr.jsp', '14', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('143', 'teachermgr', '教师管理', '0', '/back/teachermgr.jsp', '14', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('144', 'sysmsgmgr', '系统消息', '0', '/back/sysmsgmgr.jsp', '14', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('145', 'teacher_question', '老师问题', '0', '/back/teacherquestionmgr.jsp', '14', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('211', 'usrinfo', '个人信息维护', '0', '', '21', null, '1', '999');
INSERT INTO `sysmenu` VALUES ('11101', 'menumgr_view', '查看', '1', null, '111', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11201', 'rolemgr_view', '查看', '1', null, '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11202', 'rolemgr_admin_add', '新增(管理员)', '1', 'tocreateAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11203', 'rolemgr_admin_modify', '修改(管理员)', '1', 'tomodifyAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11204', 'rolemgr_admin_del', '删除(管理员)', '1', 'todelAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11205', 'rolemgr_admin_auth', '权限(管理员)', '1', 'toauthAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11206', 'rolemgr_admin', '分配(管理员)', '1', 'toadminAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11207', 'rolemgr_usr_add', '新增(会员)', '1', 'tocreateAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11208', 'rolemgr_usr_modify', '修改(会员)', '1', 'tomodifyAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11209', 'rolemgr_usr_del', '删除(会员)', '1', 'todelAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11210', 'rolemgr_usr_auth', '权限(会员)', '1', 'toauthAjaxSysRole', '112', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11301', 'membermgr_view', '查看', '1', null, '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11302', 'adminmgr_add', '新增(管理员)', '1', 'tocreateAjaxAdmin', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11303', 'adminmgr_modify', '修改(管理员)', '1', 'tomodifyAjaxAdmin', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11304', 'adminmgr_del', '删除(管理员)', '1', 'todelAjaxAdmin', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11305', 'adminmgr_lock', '锁定(管理员)', '1', 'tolockAjaxAdmin', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11306', 'adminmgr_unlock', '解锁(管理员)', '1', 'tounlockAjaxAdmin', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11307', 'adminmgr_role', '角色(管理员)', '1', 'toroleAjaxAdmin', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11308', 'usrmgr_add', '新增(会员)', '1', 'tocreateAjaxUser', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11309', 'usrmgr_modify', '修改(会员)', '1', 'tomodifyAjaxUser', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11310', 'usrmgr_del', '删除(会员)', '1', 'todelAjaxUser', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11311', 'usrmgr_lock', '锁定(会员)', '1', 'tolockAjaxUser', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11312', 'usrmgr_unlock', '解锁(会员)', '1', 'tounlockAjaxUser', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11313', 'usrmgr_import', '导入(会员)', '1', null, '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11314', 'usrmgr_export', '导出(会员)', '1', 'toexportAjaxUser', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('11315', 'usrmgr_role', '角色(会员)', '1', 'toroleAjaxUser', '113', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12101', 'cfgmgr_view', '查看', '1', null, '121', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12102', 'cfgmgr_modify', '修改', '1', 'tomodifyAjaxSysCfg', '121', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12201', 'cachemgr_view', '查看', '1', null, '122', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12202', 'cachemgr_modify', '修改', '1', 'tomodifyAjaxSysCache', '122', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12203', 'cachemgr_flush', '刷新缓存', '1', 'toflushAjaxSysCache', '122', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12204', 'cachemgr_flushall', '全部刷新', '1', 'toflushallAjaxSysCache', '122', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12301', 'articlemgr_view', '查看', '1', null, '123', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12302', 'articlemgr_add', '新增', '1', 'tocreateAjaxAdminSysArticle', '123', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12303', 'articlemgr_modify', '修改', '1', 'tomodifyAjaxAdminSysArticle', '123', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12304', 'articlemgr_modifycontent', '编辑内容', '1', 'tomodifycontentAjaxAdminSysArticle', '123', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12305', 'articlemgr_del', '删除', '1', 'todelAjaxAdminSysArticle', '123', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12401', 'logmgr_view', '查看', '1', null, '124', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12402', 'logmgr_delall', '全部删除', '1', 'todelallAjaxSysLog', '124', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12501', 'backupmgr_view', '查看', '1', null, '125', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12502', 'backupmgr_hand', '手动备份', '1', 'tocreatehandAjaxSysBackUp', '125', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12503', 'backupmgr_rebackup', '重新备份', '1', 'torebackupAjaxSysBackUp', '125', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('13101', 'admininfo_view', '查看', '1', null, '131', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('13102', 'admininfo_modify', '修改', '1', 'tomaintainAjaxAdmin', '131', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('13103', 'admininfo_profile', '上传头像', '1', null, '131', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14101', 'beans_view', '查看', '1', null, '141', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14201', 'cash_view', '查看', '1', null, '142', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14301', 'teacher_view', '查看', '1', null, '143', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14302', 'teacher_add', '添加', '1', 'createAjaxTeacher', '143', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14401', 'sysmsg_view', '查看', '1', '', '144', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14402', 'sysmsg_add', '添加', '1', 'createAjaxSysMsg', '143', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('14501', 'teacherquestion_view', '查看', '1', null, '145', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('21101', 'usrinfo_view', '查看', '1', null, '211', null, '1', '999');

-- ----------------------------
-- Table structure for sysmsg
-- ----------------------------
DROP TABLE IF EXISTS `sysmsg`;
CREATE TABLE `sysmsg` (
  `sysmsg_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sysmsg_title` varchar(120) NOT NULL,
  `sysmsg_desc` text COMMENT '信息描述',
  `has_img` int(1) DEFAULT NULL COMMENT '是否有图 0-否 1-是',
  `status` int(1) DEFAULT NULL COMMENT '状态：0-废弃  1-正常',
  `create_time` int(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`sysmsg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='系统消息表';

-- ----------------------------
-- Records of sysmsg
-- ----------------------------
INSERT INTO `sysmsg` VALUES ('1', '系统消息', '系统消息描述', '1', '1', '1398888888');
INSERT INTO `sysmsg` VALUES ('2', '系统消息2', '系统消息描述2', '1', '1', '1398888888');
INSERT INTO `sysmsg` VALUES ('3', '系统消息3', '系统消息描述3', '1', '1', '1398888888');
INSERT INTO `sysmsg` VALUES ('4', '系统消息4', '系统消息描述4', '1', '1', '1398888888');
INSERT INTO `sysmsg` VALUES ('5', '多对多1', '多对多1', '0', '1', '1401114623');
INSERT INTO `sysmsg` VALUES ('6', '好消息', '好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息好消息', '0', '1', '1401115043');
INSERT INTO `sysmsg` VALUES ('7', '号外', '<p><strong>号外号外号外号外号外号外号外号外号外号外号外号外</strong></p>\r\n', '1', '1', '1401160513');
INSERT INTO `sysmsg` VALUES ('8', '有图片的消息', '<p>&nbsp;</p>\r\n\r\n<p>有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息有图片的消息</p>\r\n', '0', '1', '1401207422');
INSERT INTO `sysmsg` VALUES ('9', '有图有真相', '<p><strong>有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相有图有真相</strong></p>\r\n', '1', '1', '1401207748');

-- ----------------------------
-- Table structure for sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL DEFAULT '',
  `role_name` varchar(128) NOT NULL DEFAULT '',
  `role_type` int(1) DEFAULT '0' COMMENT '角色类型：0-管理员；1-会员；',
  `notes` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code_UNIQUE` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysrole
-- ----------------------------
INSERT INTO `sysrole` VALUES ('1', 'supermgr', '超级管理员', '0', null);
INSERT INTO `sysrole` VALUES ('2', 'sysmgr', '系统管理员', '0', null);
INSERT INTO `sysrole` VALUES ('3', 'user', '普通会员', '1', null);

-- ----------------------------
-- Table structure for systab
-- ----------------------------
DROP TABLE IF EXISTS `systab`;
CREATE TABLE `systab` (
  `tab_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tab_name` varchar(120) NOT NULL DEFAULT '',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-显示；1-隐藏；',
  PRIMARY KEY (`tab_id`),
  UNIQUE KEY `tab_name_UNIQUE` (`tab_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systab
-- ----------------------------
INSERT INTO `systab` VALUES ('1', 'admin', '0');
INSERT INTO `systab` VALUES ('2', 'adminrole', '0');
INSERT INTO `systab` VALUES ('3', 'sysarticle', '0');
INSERT INTO `systab` VALUES ('4', 'sysauth', '0');
INSERT INTO `systab` VALUES ('5', 'syscfg', '0');
INSERT INTO `systab` VALUES ('6', 'sysfile', '0');
INSERT INTO `systab` VALUES ('7', 'syslog', '0');
INSERT INTO `systab` VALUES ('8', 'sysrole', '0');
INSERT INTO `systab` VALUES ('9', 'user', '0');
INSERT INTO `systab` VALUES ('10', 'userrole', '0');

-- ----------------------------
-- Table structure for teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo` (
  `teacher_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `teacher_name` varchar(64) DEFAULT NULL COMMENT '老师姓名',
  `teacher_desc` varchar(512) DEFAULT NULL COMMENT '老师简介',
  `grade_id` int(10) DEFAULT NULL COMMENT '年级id',
  `subject_id` int(10) DEFAULT NULL COMMENT '学科id',
  `teacher_title` int(1) DEFAULT NULL COMMENT '教师职称:0-家教 1-教师 2-高级教师 3-特级教师',
  `online_status` int(1) DEFAULT '0' COMMENT '在线状态：0-离线；1-在线',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='教师信息';

-- ----------------------------
-- Records of teacherinfo
-- ----------------------------
INSERT INTO `teacherinfo` VALUES ('1', '03668287', '汤太佳', '专业老师', '1', '1', '2', '1');
INSERT INTO `teacherinfo` VALUES ('2', '62038424', '徐敏', '徐敏老师是位优秀的人民教师，作为南京市英语学科带头人，为南京市教育事业的发展做出了不可磨灭的贡献。', '7', '3', '1', '0');
INSERT INTO `teacherinfo` VALUES ('3', '32555575', '张小贤', '他是一个好家教', '1', '1', '2', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `password` varchar(32) NOT NULL DEFAULT '',
  `nick_name` varchar(120) DEFAULT NULL,
  `bean_num` int(5) DEFAULT NULL COMMENT '汇豆数',
  `sex` int(1) DEFAULT '0' COMMENT '性别 0保密1男2女',
  `birth_date` int(10) DEFAULT NULL COMMENT '生日',
  `mobile` varchar(11) DEFAULT NULL,
  `answer_no` int(10) DEFAULT NULL COMMENT '回答数',
  `accept_no` int(10) DEFAULT NULL COMMENT '采纳数',
  `create_time` int(10) DEFAULT NULL,
  `constellation` int(2) DEFAULT NULL COMMENT '星座',
  `home` varchar(64) DEFAULT NULL COMMENT '家乡',
  `residence` varchar(64) DEFAULT NULL COMMENT '居住地',
  `grade_id` int(10) DEFAULT NULL COMMENT '年级',
  `school_region` varchar(64) DEFAULT NULL COMMENT '学校所属区域',
  `school` varchar(64) DEFAULT NULL COMMENT '学校',
  `is_teacher` TINYINT(1) DEFAULT 0,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-正常；1-锁定；2-删除；',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `hui_no_UNIQUE` (`hui_no`) USING BTREE,
  UNIQUE KEY `nick_name_UNIQUE` (`nick_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '32555575', 'f379eaf3c831b04de153469d1bec345e', 'tiantian', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('2', '32555576', 'f379eaf3c831b04de153469d1bec345e', 'cindy', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('3', '32555577', '96e79218965eb72c92a549dd5a330112', 'tang', '4', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('4', '03668287', '96e79218965eb72c92a549dd5a330112', 'chosen1', '0', '0', '882547200', null, null, null, null, '1', '120000,120100,120102', '440000,445100,445101', '3', '440000,445100,445101', '潮州中学', '0');
INSERT INTO `user` VALUES ('5', '05564291', '96e79218965eb72c92a549dd5a330112', 'ttt', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('6', '05652414', '96e79218965eb72c92a549dd5a330112', 'tt2', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('7', '62038424', '96e79218965eb72c92a549dd5a330112', 'xumin', '0', '0', '400000000', '13821428105', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('8', '82562115', '96e79218965eb72c92a549dd5a330112', 'tttttt1', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('9', '82977595', '96e79218965eb72c92a549dd5a330112', 'tttttt2', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('10', '83265976', '96e79218965eb72c92a549dd5a330112', 'ttttt3', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('11', '83319476', '96e79218965eb72c92a549dd5a330112', 'tttttt6', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('12', '83505213', '96e79218965eb72c92a549dd5a330112', 'taijia', '443', '0', '601488000', '17788929222', null, null, null, '9', '320000,321000,321023', '440000,441600,441621', '6', '440000,445100,445102', '潮州中学', '0');
INSERT INTO `user` VALUES ('13', '84630875', '96e79218965eb72c92a549dd5a330112', 'ttttt5', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('14', '84745013', '96e79218965eb72c92a549dd5a330112', 'ttttt7', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('15', '84696449', '96e79218965eb72c92a549dd5a330112', 'lulili', null, null, null, '15333333333', '0', '0', null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('16', '81318654', '96e79218965eb72c92a549dd5a330112', 'cccccc', '0', null, null, '15062294651', '0', '0', '1401181318', null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('17', '65827555', 'c8837b23ff8aaa8a2dde915473ce0991', '小飞侠abc', '0', null, null, null, '0', '0', '1401265827', null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('18', '22091753', '96e79218965eb72c92a549dd5a330112', 'rrrrrrrr', '0', null, null, null, '0', '0', '1401422091', null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('19', '31204594', '96e79218965eb72c92a549dd5a330112', 'cccccc1', '0', null, null, null, '0', '0', '1401431204', null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('20', '03662147', '96e79218965eb72c92a549dd5a330112', '低调的奢华', '0', '1', '94665600', '请输入您的真实手机号', '0', '0', '1401803662', '1', '350000,350200,350201', '370000,371300,371323', null, null, null, '0');
INSERT INTO `user` VALUES ('21', '06527928', '96e79218965eb72c92a549dd5a330112', 'mmm', '0', null, null, '15000000000', '0', '0', '1401806527', null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `ar_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrole
-- ----------------------------

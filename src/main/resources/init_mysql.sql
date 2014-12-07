/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : hui

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2014-05-09 15:38:34
*/
use hui;
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
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_name_UNIQUE` (`admin_name`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDb AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '96e79218965eb72c92a549dd5a330112', null, null, 'admin@hui.com', '1', '0');
INSERT INTO `admin` VALUES ('2', '管理员', 'f379eaf3c831b04de153469d1bec345e', null, null, null, '0', '0');

-- ----------------------------
-- Table structure for adminrole
-- ----------------------------
DROP TABLE IF EXISTS `adminrole`;
CREATE TABLE `adminrole` (
  `ar_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `admin_id` int(10) unsigned NOT NULL COMMENT '管理员id',
  `role_id` int(10) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`ar_id`)
) ENGINE=InnoDb AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员、权限  中间表';

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
  `question_id` int(10) DEFAULT NULL COMMENT '问题id',
  `answer_desc` varchar(255) DEFAULT NULL COMMENT '回答描述',
  `praise_num` int(5) DEFAULT NULL COMMENT '点赞数',
  `client_style` varchar(64) DEFAULT NULL COMMENT '客户端型号',
  `create_time` int(10) DEFAULT NULL COMMENT '创建时间',
  `has_img` int(1) DEFAULT NULL COMMENT '有无图片 0-无 1-有',
  `is_favorate` int(1) DEFAULT NULL COMMENT '是否为最佳答案：0-否 1-是',
  `status` int(1) DEFAULT NULL COMMENT '状态 0-废弃 1-正常',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='回答';

-- ----------------------------
-- Records of answer
-- ----------------------------

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
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='汇豆流水表';

-- ----------------------------
-- Records of beanflow
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='软件问题';

-- ----------------------------
-- Records of bug
-- ----------------------------
INSERT INTO `bug` VALUES ('1', '83505213', null, null, null, null, '1400300000', '酷派大神', '这个软件貌似有点问题', '1');

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
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='现金记录表';

-- ----------------------------
-- Records of cashflow
-- ----------------------------

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
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='关注表';

-- ----------------------------
-- Records of fans
-- ----------------------------

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `grade_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(30) DEFAULT NULL COMMENT '年级名',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='年级';

-- ----------------------------
-- Records of grade
-- ----------------------------

-- ----------------------------
-- Table structure for gradesubject
-- ----------------------------
DROP TABLE IF EXISTS `gradesubject`;
CREATE TABLE `gradesubject` (
  `gs_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grade_id` int(10) DEFAULT NULL,
  `subject_id` int(10) DEFAULT NULL COMMENT '科目id',
  PRIMARY KEY (`gs_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='年级、科目 中间表';

-- ----------------------------
-- Records of gradesubject
-- ----------------------------

-- ----------------------------
-- Table structure for help
-- ----------------------------
DROP TABLE IF EXISTS `help`;
CREATE TABLE `help` (
  `help_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `help_title` varchar(256) DEFAULT NULL,
  `help_desc` varchar(1024) DEFAULT NULL COMMENT '帮助文字描述',
  PRIMARY KEY (`help_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='帮助';

-- ----------------------------
-- Records of help
-- ----------------------------

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
) ENGINE=InnoDb AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='消息';

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('1', null, '83505213', '1', '回答我的消息内容', null, '0');
INSERT INTO `msg` VALUES ('2', null, '83505213', '2', '求助回答的消息内容', null, '0');
INSERT INTO `msg` VALUES ('3', null, '83505213', '3', '回答被采纳的消息内容', null, '0');
INSERT INTO `msg` VALUES ('4', '1', '83505213', '4', '系统消息的消息内容', null, '0');
INSERT INTO `msg` VALUES ('5', '2', '83505213', '4', '系统消息的消息内容', null, '0');
INSERT INTO `msg` VALUES ('6', '3', '83505213', '4', '系统消息的消息内容', null, '0');
INSERT INTO `msg` VALUES ('7', '4', '83505213', '4', '系统消息的消息内容', null, '0');

-- ----------------------------
-- Records of msg
-- ----------------------------

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
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='在线时长表';

-- ----------------------------
-- Records of onlineduration
-- ----------------------------

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `pr_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `answer_id` int(10) DEFAULT NULL COMMENT '回答id',
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  PRIMARY KEY (`pr_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='点赞';

-- ----------------------------
-- Records of praise
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : hui

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2014-05-28 11:14:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `q_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `question_desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `grade_id` int(10) DEFAULT NULL COMMENT '年级',
  `subject_id` int(10) DEFAULT NULL COMMENT '科目',
  `client_style` varchar(64) DEFAULT NULL COMMENT '客户端型号',
  `is_reward` int(1) DEFAULT NULL COMMENT '是否悬赏：0-否 1-是',
  `reward_amount` int(5) DEFAULT NULL COMMENT '悬赏豆数',
  `create_time` int(10) DEFAULT NULL COMMENT '创建时间',
  `to_teacher` int(1) DEFAULT NULL COMMENT '是否提问老师：0-否，1-是',
  `allocated` int(1) DEFAULT '0' COMMENT '是否分配给指定教师 0未分配1已分配',
  `answer_num` int(5) DEFAULT 0 COMMENT '回答数',
  `has_answer` int(1) DEFAULT NULL COMMENT '有无答案：0-无 1-有',
  `has_img` int(1) DEFAULT NULL COMMENT '是否有图片  0-无 1-有',
  `has_favorate` int(1) DEFAULT NULL COMMENT '是否有最佳答案：0-否 1-是',
  `status` int(1) DEFAULT NULL COMMENT '状态：0-废弃 1-正常',
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='问题';

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for questionuser
-- ----------------------------
DROP TABLE IF EXISTS `questionuser`;
CREATE TABLE `questionuser` (
  `qu_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `question_id` int(10) DEFAULT NULL COMMENT '问题id',
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  PRIMARY KEY (`qu_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='问题对用户';

-- ----------------------------
-- Records of questionuser
-- ----------------------------

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
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='汇豆充值套餐表';

-- ----------------------------
-- Records of rechargepackage
-- ----------------------------

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `sub_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(30) DEFAULT NULL COMMENT '学科名',
  PRIMARY KEY (`sub_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='学科';

-- ----------------------------
-- Records of subject
-- ----------------------------

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
) ENGINE=InnoDb AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统文章';

-- ----------------------------
-- Records of sysarticle
-- ----------------------------
INSERT INTO `sysarticle` VALUES ('1', '用户注册协议', '0', '0', '', '0', '0', '0', '999');
INSERT INTO `sysarticle` VALUES ('2', '网站服务流程', '1', '0', '', '0', '0', '0', '999');

-- ----------------------------
-- Table structure for sysauth
-- ----------------------------
DROP TABLE IF EXISTS `sysauth`;
CREATE TABLE `sysauth` (
  `auth_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(10) unsigned NOT NULL COMMENT '权限id',
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDb AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of sysauth
-- ----------------------------
INSERT INTO `sysauth` VALUES ('16', '1', '11204');
INSERT INTO `sysauth` VALUES ('15', '1', '11203');
INSERT INTO `sysauth` VALUES ('14', '1', '11202');
INSERT INTO `sysauth` VALUES ('13', '1', '11201');
INSERT INTO `sysauth` VALUES ('12', '1', '112');
INSERT INTO `sysauth` VALUES ('11', '1', '11101');
INSERT INTO `sysauth` VALUES ('10', '1', '111');
INSERT INTO `sysauth` VALUES ('9', '1', '11');
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
) ENGINE=InnoDb AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统备份';

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
) ENGINE=InnoDb AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDb AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syscfg
-- ----------------------------
INSERT INTO `syscfg` VALUES ('1', 'webstatus', '1', null, 'array', '网站状态', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('2', 'close_reason', '', null, 'string', '网站关闭原因', null, null);
INSERT INTO `syscfg` VALUES ('3', 'regable', '0', null, 'array', '开放注册', '0-是；1-否；', null);
INSERT INTO `syscfg` VALUES ('4', 'webtitle', '三叶草', null, 'string', '网站标题', null, null);
INSERT INTO `syscfg` VALUES ('5', 'webkeywords', '南京，三叶草', null, 'string', '网站关键字', null, null);
INSERT INTO `syscfg` VALUES ('6', 'webdescription', '南京三叶草', null, 'string', '网站描述', null, null);
INSERT INTO `syscfg` VALUES ('7', 'traffic_statistics', 'document.write(\"流量统计\");', null, 'string', '流量统计代码', '例如百度统计代码', null);
INSERT INTO `syscfg` VALUES ('8', 'front_login_valicode', '1', null, 'array', '前台登录验证码', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('9', 'front_reg_valicode', '1', null, 'array', '前台注册验证码', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('10', 'back_login_valicode', '0', null, 'array', '后台登录验证码', '0-关闭；1-开启；', null);
INSERT INTO `syscfg` VALUES ('11', 'back_menu_count', '6', null, 'int', '后台菜单最大打开数量', null, '2~16');
INSERT INTO `syscfg` VALUES ('12', 'article_img_count', '6', null, 'int', '文章上传图片最大数量', null, '1~20');
INSERT INTO `syscfg` VALUES ('13', 'long_date_format', 'yyyy-MM-dd HH:mm:ss', null, 'string', '日期格式化', null, null);
INSERT INTO `syscfg` VALUES ('14', 'short_date_format', 'yyyy-MM-dd', null, 'string', '日期格式化', null, null);
INSERT INTO `syscfg` VALUES ('15', 'file_path', 'd:\\hui_files', null, 'string', '生成文件存储路径', null, null);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

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
INSERT INTO `sysdata` VALUES ('26', 'code_location', '[{\"url\":\"http://www.baidu.com?code\"}]', '二维码地址');
INSERT INTO `sysdata` VALUES ('27', 'soft_version', '[{\"type\":\"ios\",\"version\":\"1.1\"},{\"type\":\"android\",\"version\":\"1.5\"}]', '软件版本');
INSERT INTO `sysdata` VALUES ('28', 'soft_download', '[{\"url\":\"http://www.baidu.com?download\"}]', '软件下载地址');
INSERT INTO `sysdata` VALUES ('29', 'in_out', '[{\"0\":\"流入\",\"1\":\"流出\"}]', '流水方向');
INSERT INTO `sysdata` VALUES ('30', 'flow_source', '[{\"0\":\"未知\",\"1\":\"支付宝\"}]', '流水来源');

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
  `file_type` int(2) NOT NULL DEFAULT '0' COMMENT '文件类型：0-下载模板；1-会员头像；2-管理员头像；3-文章图片；4-提问图片；5-回答图片；6-帮助图片',
  `path_type` int(1) NOT NULL DEFAULT '0' COMMENT '路径类型：0-CONTEXT；1-URL；',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDb AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysfile
-- ----------------------------
INSERT INTO `sysfile` VALUES ('1', '1', '0', 'impuser.xls', '/template/excel', '0', '0');

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
) ENGINE=InnoDb AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syslog
-- ----------------------------

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
) ENGINE=InnoDb AUTO_INCREMENT=21102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysmenu
-- ----------------------------
INSERT INTO `sysmenu` VALUES ('11', 'authmgr', '权限管理', '0', null, '0', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('12', 'sysmgr', '系统管理', '0', null, '0', null, '0', '999');
INSERT INTO `sysmenu` VALUES ('13', 'selfmgr', '个人中心', '0', null, '0', null, '0', '999');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统消息表';

-- ----------------------------
-- Records of sysmsg
-- ----------------------------

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
) ENGINE=InnoDb AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDb AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDb AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='教师信息';

-- ----------------------------
-- Records of teacherinfo
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hui_no` varchar(8) DEFAULT NULL COMMENT '汇答号',
  `password` varchar(32) NOT NULL DEFAULT '',
  `nick_name` varchar(120) DEFAULT NULL,
  `bean_num` int(5) DEFAULT 0 COMMENT '汇豆数',
  `sex` int(1) DEFAULT '0' COMMENT '性别 0保密1男2女',
  `birth_date` int(10) DEFAULT NULL COMMENT '生日',
  `mobile` varchar(11) DEFAULT NULL,
  `answer_no` int(10) DEFAULT 0 COMMENT '回答数',
  `accept_no` int(10) DEFAULT 0 COMMENT '采纳数',
  `create_time` int(10) DEFAULT NULL,
  `constellation` int(2) DEFAULT NULL COMMENT '星座',
  `home` varchar(64) DEFAULT NULL COMMENT '家乡',
  `residence` varchar(64) DEFAULT NULL COMMENT '居住地',
  `grade_id` int(10) DEFAULT NULL COMMENT '年级',
  `school_region` varchar(64) DEFAULT NULL COMMENT '学校所属区域',
  `school` varchar(64) DEFAULT NULL COMMENT '学校',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-正常；1-锁定；2-删除；',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `hui_no_UNIQUE` (`hui_no`) USING BTREE,
  UNIQUE KEY `nick_name_UNIQUE` (`nick_name`) USING BTREE
) ENGINE=InnoDb AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user', 'f379eaf3c831b04de153469d1bec345e', null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('2', '用户', 'f379eaf3c831b04de153469d1bec345e', null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('3', '32555577', '96e79218965eb72c92a549dd5a330112', 'tang', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('4', '03668287', '7fa8282ad93047a4d6fe6111c93b308a', 'chosen1', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('5', '05564291', '96e79218965eb72c92a549dd5a330112', 'ttt', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('6', '05652414', '96e79218965eb72c92a549dd5a330112', 'tt2', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('7', '62038424', '96e79218965eb72c92a549dd5a330112', 'tangg', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('8', '82562115', '96e79218965eb72c92a549dd5a330112', 'tttttt1', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('9', '82977595', '96e79218965eb72c92a549dd5a330112', 'tttttt2', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('10', '83265976', '96e79218965eb72c92a549dd5a330112', 'ttttt3', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('11', '83319476', '96e79218965eb72c92a549dd5a330112', 'tttttt6', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('12', '83505213', '96e79218965eb72c92a549dd5a330112', 'taijia', '490', '1', '1000000000', '15062294657', null, null, null, '2', '130000,130100,130102', '130000,130100,130102', '2', '130000,130100,130102', '竹镇民族中学', '0');
INSERT INTO `user` VALUES ('13', '84630875', '96e79218965eb72c92a549dd5a330112', 'ttttt5', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `user` VALUES ('14', '84745013', '96e79218965eb72c92a549dd5a330112', 'ttttt7', '0', '0', null, null, null, null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `ar_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ar_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrole
-- ----------------------------

-- ----------------------------
-- Table structure for r_city
-- ----------------------------
DROP TABLE IF EXISTS `r_city`;
CREATE TABLE `r_city` (
  `city_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city_name` varchar(64) DEFAULT NULL,
  `province_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='城市';

-- ----------------------------
-- Records of r_city
-- ----------------------------

-- ----------------------------
-- Table structure for r_county
-- ----------------------------
DROP TABLE IF EXISTS `r_county`;
CREATE TABLE `r_county` (
  `county_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `county_name` varchar(64) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`county_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_county
-- ----------------------------

-- ----------------------------
-- Table structure for r_province
-- ----------------------------
DROP TABLE IF EXISTS `r_province`;
CREATE TABLE `r_province` (
  `province_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `province_name` varchar(64) DEFAULT NULL COMMENT '省名',
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_province
-- ----------------------------

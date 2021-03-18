/*
 Navicat Premium Data Transfer

 Source Server         : 本地库
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : pay

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 18/03/2021 10:47:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for database_info
-- ----------------------------
DROP TABLE IF EXISTS `database_info`;
CREATE TABLE `database_info`  (
  `db_id` bigint NOT NULL COMMENT '主键id',
  `db_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库名称（英文名称）',
  `jdbc_driver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc的驱动类型',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库连接的账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库连接密码',
  `jdbc_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc的url',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，摘要',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`db_id`) USING BTREE,
  UNIQUE INDEX `NAME_UNIQUE`(`db_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of database_info
-- ----------------------------
INSERT INTO `database_info` VALUES (1371827773600960513, 'master', 'com.mysql.cj.jdbc.Driver', 'root', '123123', 'jdbc:mysql://127.0.0.1:3306/pay?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT', '主数据源，项目启动数据源！', '2021-03-16 22:16:43');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性编码标识',
  `dict_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否是字典中的值',
  `dict_type_id` bigint NULL DEFAULT NULL COMMENT '字典类型的编码',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值，如果是字典中的类型，则为dict的code',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1143324237579165697, '验证码开关', 'GUNS_KAPTCHA_OPEN', 'N', 1106120265689055233, 'DISABLE', '是否开启验证码', '2019-06-24 12:46:43', 1, '2020-12-16 21:48:38', 1);
INSERT INTO `sys_config` VALUES (1143386834613694465, '阿里云短信的keyId', 'GUNS_SMS_ACCESSKEY_ID', 'N', NULL, 'xxxkey', '阿里云短信的密钥key', '2019-06-25 13:13:59', 1, '2019-06-25 13:19:21', 1);
INSERT INTO `sys_config` VALUES (1143386953933254657, '阿里云短信的secret', 'GUNS_SMS_ACCESSKEY_SECRET', 'N', NULL, 'xxxsecret', '阿里云短信的secret', '2019-06-25 13:14:28', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143387023449649154, '阿里云短信的签名', 'GUNS_SMS_SIGN_NAME', 'N', NULL, 'xxxsign', '阿里云短信的签名', '2019-06-25 13:14:44', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143387131109044225, '阿里云短信登录的模板号', 'GUNS_SMS_LOGIN_TEMPLATE_CODE', 'N', NULL, 'SMS_XXXXXX', '阿里云短信登录的模板号', '2019-06-25 13:15:10', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143387225019510785, '验证码短信失效时间', 'GUNS_SMS_INVALIDATE_MINUTES', 'N', NULL, '2', '验证码短信失效时间', '2019-06-25 13:15:32', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143468689664876546, '管理系统名称', 'GUNS_SYSTEM_NAME', 'N', NULL, 'guns后台管理系统', '管理系统名称', '2019-06-25 18:39:15', 1, '2021-03-14 19:56:06', 1);
INSERT INTO `sys_config` VALUES (1143468867767607297, '默认系统密码', 'GUNS_DEFAULT_PASSWORD', 'N', NULL, '111111', '默认系统密码', '2019-06-25 18:39:57', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143469008025133058, 'OAuth2登录用户的账号标识', 'GUNS_OAUTH2_PREFIX', 'N', NULL, 'oauth2', 'OAuth2登录用户的账号标识', '2019-06-25 18:40:31', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1145207130463191041, '顶部导航条是否开启', 'GUNS_DEFAULT_ADVERT', 'Y', 1106120265689055233, 'DISABLE', '顶部Guns广告是否开启', '2019-06-30 13:47:11', 1, '2020-12-16 21:46:53', 1);
INSERT INTO `sys_config` VALUES (1145915627211370498, 'Guns发布的编号', 'GUNS_SYSTEM_RELEASE_VERSION', 'N', NULL, '201908121123', '用于防止浏览器缓存相关的js和css', '2019-07-02 12:42:30', 1, '2020-12-23 14:13:26', 1);
INSERT INTO `sys_config` VALUES (1277511081081712641, '谷歌身份验证开关', 'GOOGLE_AUTH_OPEN', 'N', 1106120265689055233, 'DISABLE', '是否开启谷歌身份验证', '2020-06-29 15:55:51', 1, '2020-07-01 15:31:46', 1);
INSERT INTO `sys_config` VALUES (1278169611069599745, '登录密码可输错最大次数', 'LOGIN_ERROR_MAX_CNT', 'N', NULL, '5', '', '2020-07-01 11:32:37', 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL COMMENT '主键id',
  `pid` bigint NULL DEFAULT 0 COMMENT '父部门id',
  `pids` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父级ids',
  `simple_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (24, 0, '[0],', '创新三部', '数据中心三部', '经理 Mark', NULL, 1, NULL, '2020-11-07 16:10:50', NULL, 1249610692690907137);
INSERT INTO `sys_dept` VALUES (26, 24, '[0],[24],', '推广部', '数据中心三部推广部', '主管 Hunter', NULL, 2, NULL, '2020-12-05 01:29:44', NULL, 1249610385202286594);
INSERT INTO `sys_dept` VALUES (1250365551880998914, 26, '[0],[24],[26],', '推广二组', '二组', '组长 Knight', NULL, 5, '2020-04-15 18:09:13', '2020-11-07 16:38:58', 1, 1249610692690907137);
INSERT INTO `sys_dept` VALUES (1250365785243684865, 26, '[0],[24],[26],', '推广三组', '三组', '组长 Polo', NULL, 6, '2020-04-15 18:10:09', '2020-11-07 16:39:08', 1, 1249610692690907137);
INSERT INTO `sys_dept` VALUES (1250366143965728770, 26, '[0],[24],[26],', '推广五组', '五组', '组长 pisces', NULL, 7, '2020-04-15 18:11:34', '2020-11-07 16:39:15', 1, 1249610692690907137);
INSERT INTO `sys_dept` VALUES (1324984874423054338, 24, '[0],[24],', '新媒体', '新媒体', '副主管 Moran', NULL, 3, '2020-11-07 15:59:46', '2020-11-07 16:08:46', 1249610692690907137, 1249610692690907137);
INSERT INTO `sys_dept` VALUES (1324985208604225537, 24, '[0],[24],', 'seo', 'seo', '主管 Eden', NULL, 4, '2020-11-07 16:01:05', '2020-11-07 16:08:41', 1249610692690907137, 1249610692690907137);
INSERT INTO `sys_dept` VALUES (1324986718998261762, 26, '[0],[24],[26],', '推广六组', '推广六组', '组长 Owen', NULL, 8, '2020-11-07 16:07:06', '2020-11-25 21:19:19', 1249610692690907137, 1249610692690907137);
INSERT INTO `sys_dept` VALUES (1324995513614888961, 26, '[0],[24],[26],', '推广客维组', '客维组', '-', NULL, 9, '2020-11-07 16:42:02', NULL, 1249610692690907137, NULL);
INSERT INTO `sys_dept` VALUES (1324995864506167298, 0, '[0],', '创新三部离职组', '创新三部离职组', '-', NULL, 11, '2020-11-07 16:43:26', '2020-11-21 20:25:35', 1249610692690907137, 1);
INSERT INTO `sys_dept` VALUES (1326163894053470210, 26, '[0],[24],[26],', 'Mansanas组', 'Mansanas组', 'Mansanas', NULL, NULL, '2020-11-10 22:04:46', '2020-11-10 22:05:31', 1249610692690907137, 1249610692690907137);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint NOT NULL COMMENT '字典id',
  `dict_type_id` bigint NOT NULL COMMENT '所属字典类型的id',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `parent_id` bigint NOT NULL COMMENT '上级代码id',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有上级id',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ENABLE' COMMENT '状态（字典）',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典的描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1106120532442595330, 1106120208097067009, 'M', '男', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:00', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120574163337218, 1106120208097067009, 'F', '女', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:10', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120645697191938, 1106120265689055233, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:27', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120699468169217, 1106120265689055233, 'DISABLE', '禁用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:40', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120784318939137, 1106120322450571266, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:12:00', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120825993543682, 1106120322450571266, 'FREEZE', '冻结', 0, '[0]', 'ENABLE', 1, '', '2019-03-14 17:12:10', '2019-03-16 10:56:36', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120875872206849, 1106120322450571266, 'DELETED', '已删除', 0, '[0]', 'ENABLE', -1221, '', '2019-03-14 17:12:22', '2019-03-16 10:56:53', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120935070613505, 1106120388036902914, 'Y', '删除', 0, '[0]', 'ENABLE', 23333, '', '2019-03-14 17:12:36', '2019-03-16 10:58:53', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120968910258177, 1106120388036902914, 'N', '未删除', 0, '[0]', 'ENABLE', 1212211221, '', '2019-03-14 17:12:44', '2019-03-16 10:59:03', 1, 1);
INSERT INTO `sys_dict` VALUES (1149218674746355713, 1149217131989069826, 'BASE_SYSTEM', '系统管理', 0, '[0]', 'ENABLE', 1, '系统管理平台', '2019-07-11 15:27:38', '2019-07-11 20:27:14', 1, 1);
INSERT INTO `sys_dict` VALUES (1253950501588811777, 1253950044678111233, 'YB', 'YB平台', 0, '[0]', 'ENABLE', 1, 'YB平台', '2020-04-25 15:34:32', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1253950577790926849, 1253950044678111233, 'OB', 'OB平台', 0, '[0]', 'ENABLE', 2, 'OB平台', '2020-04-25 15:34:50', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_type_id` bigint NOT NULL COMMENT '字典类型id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型名称',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `system_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是否是系统字典，Y-是，N-否',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ENABLE' COMMENT '状态(字典)',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dict_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1106120208097067009, 'SEX', '性别', '', 'Y', 'ENABLE', 4, '2019-03-14 17:09:43', 1, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120265689055233, 'STATUS', '状态', '', 'Y', 'ENABLE', 3, '2019-03-14 17:09:57', 1, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120322450571266, 'ACCOUNT_STATUS', '账号状态', '', 'Y', 'ENABLE', 21112, '2019-03-14 17:10:10', 1, '2019-03-16 10:56:15', 1);
INSERT INTO `sys_dict_type` VALUES (1106120388036902914, 'DEL_FLAG', '是否删除', '', 'Y', 'ENABLE', 2, '2019-03-14 17:10:26', 1, '2019-03-27 16:26:31', 1);
INSERT INTO `sys_dict_type` VALUES (1149217131989069826, 'SYSTEM_TYPE', '系统分类', '系统所有分类的维护', 'Y', 'ENABLE', 70, '2019-07-11 15:21:30', 1, '2019-07-11 15:28:21', 1);
INSERT INTO `sys_dict_type` VALUES (1253950044678111233, 'SYSTEM_PLATFORM', '系统平台', 'SYSTEM_PLATFORM', 'N', 'ENABLE', 1, '2020-04-25 15:32:43', 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `file_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `file_bucket` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件仓库（oss仓库）',
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `file_suffix` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `file_size_kb` bigint NULL DEFAULT NULL COMMENT '文件大小kb',
  `final_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件唯一标识id',
  `file_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `file_catg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件分类',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `login_log_id` bigint NOT NULL COMMENT '主键',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint NULL DEFAULT NULL COMMENT '管理员id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否执行成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '具体消息',
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`login_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (1371076889791217665, '退出日志', 1, '2021-03-14 20:32:59', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371076924775907329, '登录日志', 1249610385202286594, '2021-03-14 20:33:07', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371077022448664577, '退出日志', 1249610385202286594, '2021-03-14 20:33:30', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371077047501242369, '登录日志', 1, '2021-03-14 20:33:36', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371087536973201409, '登录日志', 1, '2021-03-14 21:15:17', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371405385730109441, '登录日志', 1, '2021-03-15 18:18:18', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371415915798773762, '登录日志', 1, '2021-03-15 19:00:09', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371432493126828033, '登录日志', 1, '2021-03-15 20:06:01', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371435860125335553, '登录日志', 1, '2021-03-15 20:19:24', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371456342950199298, '登录日志', 1, '2021-03-15 21:40:47', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371804290708000769, '登录日志', 1, '2021-03-16 20:43:24', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371804810352840705, '登录日志', 1, '2021-03-16 20:45:28', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371805520846999553, '登录失败日志', NULL, '2021-03-16 20:48:18', '成功', '账号:admin,账号密码错误', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371805538760871937, '登录日志', 1, '2021-03-16 20:48:22', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371806435070988290, '登录日志', 1, '2021-03-16 20:51:56', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371806435251343362, '登录日志', 1, '2021-03-16 20:51:56', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371806961716273154, '登录日志', 1, '2021-03-16 20:54:01', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371807061477793794, '登录日志', 1, '2021-03-16 20:54:25', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371807976398118914, '登录日志', 1, '2021-03-16 20:58:03', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371810225950064641, '登录日志', 1, '2021-03-16 21:07:00', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371810544666865666, '登录日志', 1, '2021-03-16 21:08:16', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371811071102291970, '登录日志', 1, '2021-03-16 21:10:21', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371811262542909441, '登录日志', 1, '2021-03-16 21:11:07', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371811621181116417, '登录日志', 1, '2021-03-16 21:12:32', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371812563578318850, '登录日志', 1, '2021-03-16 21:16:17', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371819012119355393, '登录日志', 1, '2021-03-16 21:41:54', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371820632995651585, '登录日志', 1, '2021-03-16 21:48:21', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371826274082861058, '登录日志', 1, '2021-03-16 22:10:46', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (1371827889393111042, '登录日志', 1, '2021-03-16 22:17:11', '成功', NULL, '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `sort` int NULL DEFAULT NULL COMMENT '菜单排序号',
  `levels` int NULL DEFAULT NULL COMMENT '菜单层级',
  `menu_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是菜单(字典)',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'ENABLE' COMMENT '菜单状态(字典)',
  `new_page_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否打开新页面的标识(字典)',
  `open_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否打开(字典)',
  `system_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统分类(字典)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (105, 'system', '0', '[0],', '系统管理', 'layui-icon layui-icon-set', '#', 20, 1, 'Y', NULL, 'ENABLE', NULL, '1', 'BASE_SYSTEM', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 10, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (114, 'role', 'system', '[0],[system],', '角色管理', '', '/role', 20, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', '', '/menu', 50, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:25', NULL, 1);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:25', NULL, 1);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:25', NULL, 1);
INSERT INTO `sys_menu` VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:25', NULL, 1);
INSERT INTO `sys_menu` VALUES (128, 'log', 'system', '[0],[system],', '业务日志', '', '/log', 70, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, '2019-06-30 13:48:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', '', '/druid', 80, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:50:06', NULL, 1);
INSERT INTO `sys_menu` VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', '', '/dept', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:53', NULL, 1);
INSERT INTO `sys_menu` VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', '', '/dictType', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', '', '/loginLog', 60, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:29', NULL, 1);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:53', NULL, 1);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:53', NULL, 1);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:53', NULL, 1);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dictType/addItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dictType/editItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dictType/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1);
INSERT INTO `sys_menu` VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', '', '/notice', 90, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:44', NULL, 1);
INSERT INTO `sys_menu` VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:44', NULL, 1);
INSERT INTO `sys_menu` VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:44', NULL, 1);
INSERT INTO `sys_menu` VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:44', NULL, 1);
INSERT INTO `sys_menu` VALUES (145, 'sys_message', 'dashboard', '[0],[dashboard],', '消息通知', 'layui-icon layui-icon-tips', '/system/notice', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-04-08 22:49:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (149, 'api_mgr', 'dev_tools', '[0],[dev_tools],', '接口文档', 'fa-leaf', '/swagger-ui.html', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-15 18:13:00', NULL, 1);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:25', NULL, 1);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:25', NULL, 1);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:53', NULL, 1);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:53', NULL, 1);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:53', NULL, 1);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1);
INSERT INTO `sys_menu` VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:29', NULL, 1);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:29', NULL, 1);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:48:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (171, 'dev_tools', '0', '[0],', '开发管理', 'layui-icon layui-icon-code-circle', '#', 30, 1, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-05-11 13:40:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (172, 'dashboard', '0', '[0],', '主控面板', 'layui-icon layui-icon-home', '#', 10, 1, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, '2019-04-08 22:48:15', NULL, 1);
INSERT INTO `sys_menu` VALUES (1110777136265838594, 'demos_show', 'dev_tools', '[0],[dev_tools],', '模板页面', 'layui-icon layui-icon-template', '#', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-03-27 13:34:41', '2019-06-15 18:13:11', 1, 1);
INSERT INTO `sys_menu` VALUES (1110777366856089602, 'excel_import', 'demos_show', '[0],[dev_tools],[demos_show],', 'excel导入', '', '/excel/import', 10, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-03-27 13:35:36', '2019-06-15 18:13:11', 1, 1);
INSERT INTO `sys_menu` VALUES (1110777491464667137, 'excel_export', 'demos_show', '[0],[dev_tools],[demos_show],', 'excel导出', '', '/excel/export', 20, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-03-27 13:36:06', '2019-06-15 18:13:11', 1, 1);
INSERT INTO `sys_menu` VALUES (1110787391943098370, 'advanced_form', 'demos_show', '[0],[dev_tools],[demos_show],', '高级表单', '', '/egForm', 30, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-03-27 14:15:26', '2019-06-15 18:13:11', 1, 1);
INSERT INTO `sys_menu` VALUES (1110839216310329346, 'pdf_view', 'demos_show', '[0],[dev_tools],[demos_show],', '文档预览', '', '/loadPdfFile', 40, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-03-27 17:41:22', '2019-06-15 18:13:11', 1, 1);
INSERT INTO `sys_menu` VALUES (1111545968697860098, 'console', 'dashboard', '[0],[dashboard],', '项目介绍', '', '/system/console', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-03-29 16:29:45', '2019-04-09 20:57:08', 1, 1);
INSERT INTO `sys_menu` VALUES (1111546189892870145, 'console2', 'dashboard', '[0],[dashboard],', '统计报表', '', '/system/console2', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-03-29 16:30:38', '2019-04-08 22:49:48', 1, 1);
INSERT INTO `sys_menu` VALUES (1127085735660421122, 'code_generate', 'dev_tools', '[0],[dev_tools],', '代码生成', '', '/gen', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-05-11 13:39:14', '2019-06-15 18:12:45', 1, 1);
INSERT INTO `sys_menu` VALUES (1139826657964593154, 'meta_data', 'dev_tools', '[0],[dev_tools],', '系统配置', '', '#', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-06-15 17:27:07', '2019-06-15 18:12:35', 1, 1);
INSERT INTO `sys_menu` VALUES (1139827152854716418, 'data_source', 'meta_data', '[0],[dev_tools],[meta_data],', '数据源管理', '', '/databaseInfo', 10, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-06-15 17:29:05', '2019-06-15 18:12:35', 1, 1);
INSERT INTO `sys_menu` VALUES (1142957882422112257, 'SYS_CONFIG', 'meta_data', '[0],[dev_tools],[meta_data],', '参数配置', 'fa-star', '/sysConfig', 5, 3, 'Y', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2019-06-24 08:57:34', 1, 1);
INSERT INTO `sys_menu` VALUES (1142957882422112258, 'SYS_CONFIG_ADD', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置添加', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2019-06-24 08:57:34', 1, 1);
INSERT INTO `sys_menu` VALUES (1142957882422112259, 'SYS_CONFIG_EDIT', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置修改', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2019-06-24 08:57:34', 1, 1);
INSERT INTO `sys_menu` VALUES (1142957882426306562, 'SYS_CONFIG_DELETE', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置删除', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2019-06-24 08:57:34', 1, 1);
INSERT INTO `sys_menu` VALUES (1144441072852684801, 'SYS_POSITION', 'system', '[0],[system],', '职位管理', 'fa-star', '/position', 35, 2, 'Y', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1);
INSERT INTO `sys_menu` VALUES (1144441072852684802, 'SYS_POSITION_ADD', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表添加', 'fa-star', '', 999, 3, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1);
INSERT INTO `sys_menu` VALUES (1144441072852684803, 'SYS_POSITION_EDIT', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表修改', 'fa-star', '', 999, 3, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1);
INSERT INTO `sys_menu` VALUES (1144441072852684804, 'SYS_POSITION_DELETE', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表删除', 'fa-star', '', 999, 3, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1);
INSERT INTO `sys_menu` VALUES (1149955324929765378, 'system_info', 'dashboard', '[0],[dashboard],', '系统监控', 'layui-icon-star-fill', '/system/systemInfo', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2019-07-13 16:14:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1248972061080944641, 'cust_manager', '0', '[0],', '客户管理', 'layui-icon-cellphone', '#', 30, 1, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2020-04-11 21:51:59', '2021-03-14 19:54:33', 1, 1);
INSERT INTO `sys_menu` VALUES (1370961804473200642, 'LINE', 'cust_manager', '[0],[cust_manager],', '客户线路', 'layui-icon-star-fill', '/line', 99, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', '2021-03-14 12:55:40', '2021-03-14 19:54:33', 1, 1);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` bigint NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `operation_log_id` bigint NOT NULL COMMENT '主键',
  `log_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型(字典)',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '方法名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否成功(字典)',
  `message` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  PRIMARY KEY (`operation_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES (1371076143528067074, '业务日志', '清空业务日志', 1, 'cn.stylefeng.guns.sys.modular.system.controller.LogController', 'delLog', '2021-03-14 20:30:01', '成功', '主键id=null');
INSERT INTO `sys_operation_log` VALUES (1371076187421458434, '业务日志', '清空登录日志', 1, 'cn.stylefeng.guns.sys.modular.system.controller.LoginLogController', 'delLog', '2021-03-14 20:30:11', '成功', '主键id=null');
INSERT INTO `sys_operation_log` VALUES (1371076682823286785, '业务日志', '添加角色', 1, 'cn.stylefeng.guns.sys.modular.system.controller.RoleController', 'add', '2021-03-14 20:32:09', '成功', '角色名称=员工');
INSERT INTO `sys_operation_log` VALUES (1371076713647226881, '业务日志', '配置权限', 1, 'cn.stylefeng.guns.sys.modular.system.controller.RoleController', 'setAuthority', '2021-03-14 20:32:17', '成功', '角色名称=员工,资源名称=客户管理,客户线路');
INSERT INTO `sys_operation_log` VALUES (1371076828176891905, '业务日志', '分配角色', 1, 'cn.stylefeng.guns.sys.modular.system.controller.UserMgrController', 'setRole', '2021-03-14 20:32:44', '成功', '账号=test1,角色名称集合=员工');
INSERT INTO `sys_operation_log` VALUES (1371076870673580034, '业务日志', '重置管理员密码', 1, 'cn.stylefeng.guns.sys.modular.system.controller.UserMgrController', 'reset', '2021-03-14 20:32:54', '成功', '账号=test1');

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position`  (
  `position_id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位名称',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位编码',
  `sort` int NOT NULL COMMENT '顺序',
  `status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ENABLE' COMMENT '状态(字典)',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`position_id`) USING BTREE,
  UNIQUE INDEX `CODE_UNI`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职位表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_position
-- ----------------------------
INSERT INTO `sys_position` VALUES (1, '部门经理', '3', 1, 'ENABLE', '1', '2019-06-27 18:14:43', 1, '2020-04-15 18:27:36', NULL);

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `relation_id` bigint NOT NULL COMMENT '主键',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES (1317067338121486337, 1248972061080944641, 1263681344161046530);
INSERT INTO `sys_relation` VALUES (1363688166363615233, 105, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166409752577, 106, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166418141185, 107, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166418141186, 108, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166426529794, 109, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166426529795, 110, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166434918401, 111, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166434918402, 112, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166439112706, 113, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166439112707, 165, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166439112708, 166, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1363688166447501313, 167, 1363687377746382850);
INSERT INTO `sys_relation` VALUES (1365922167413346305, 105, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167417540610, 106, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167417540611, 107, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167425929218, 108, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167425929219, 109, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167434317825, 110, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167434317826, 111, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167442706433, 112, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167442706434, 113, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167451095041, 165, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167451095042, 166, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167459483650, 167, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1365922167459483651, 1248972061080944641, 1329713700818231298);
INSERT INTO `sys_relation` VALUES (1368889726207082497, 1248972061080944641, 1368816907892858882);
INSERT INTO `sys_relation` VALUES (1370272713804918786, 1248972061080944641, 1370272271863689218);
INSERT INTO `sys_relation` VALUES (1370961877235986434, 105, 1);
INSERT INTO `sys_relation` VALUES (1370961877244375041, 106, 1);
INSERT INTO `sys_relation` VALUES (1370961877244375042, 107, 1);
INSERT INTO `sys_relation` VALUES (1370961877252763649, 108, 1);
INSERT INTO `sys_relation` VALUES (1370961877252763650, 109, 1);
INSERT INTO `sys_relation` VALUES (1370961877261152257, 110, 1);
INSERT INTO `sys_relation` VALUES (1370961877261152258, 111, 1);
INSERT INTO `sys_relation` VALUES (1370961877269540866, 112, 1);
INSERT INTO `sys_relation` VALUES (1370961877269540867, 113, 1);
INSERT INTO `sys_relation` VALUES (1370961877277929473, 165, 1);
INSERT INTO `sys_relation` VALUES (1370961877277929474, 166, 1);
INSERT INTO `sys_relation` VALUES (1370961877286318082, 167, 1);
INSERT INTO `sys_relation` VALUES (1370961877294706690, 114, 1);
INSERT INTO `sys_relation` VALUES (1370961877294706691, 115, 1);
INSERT INTO `sys_relation` VALUES (1370961877303095297, 116, 1);
INSERT INTO `sys_relation` VALUES (1370961877303095298, 117, 1);
INSERT INTO `sys_relation` VALUES (1370961877311483905, 118, 1);
INSERT INTO `sys_relation` VALUES (1370961877311483906, 162, 1);
INSERT INTO `sys_relation` VALUES (1370961877319872514, 163, 1);
INSERT INTO `sys_relation` VALUES (1370961877324066818, 164, 1);
INSERT INTO `sys_relation` VALUES (1370961877324066819, 119, 1);
INSERT INTO `sys_relation` VALUES (1370961877332455425, 120, 1);
INSERT INTO `sys_relation` VALUES (1370961877340844033, 121, 1);
INSERT INTO `sys_relation` VALUES (1370961877340844034, 122, 1);
INSERT INTO `sys_relation` VALUES (1370961877340844035, 150, 1);
INSERT INTO `sys_relation` VALUES (1370961877349232641, 151, 1);
INSERT INTO `sys_relation` VALUES (1370961877349232642, 128, 1);
INSERT INTO `sys_relation` VALUES (1370961877357621249, 134, 1);
INSERT INTO `sys_relation` VALUES (1370961877357621250, 158, 1);
INSERT INTO `sys_relation` VALUES (1370961877366009857, 159, 1);
INSERT INTO `sys_relation` VALUES (1370961877370204161, 130, 1);
INSERT INTO `sys_relation` VALUES (1370961877370204162, 131, 1);
INSERT INTO `sys_relation` VALUES (1370961877378592770, 135, 1);
INSERT INTO `sys_relation` VALUES (1370961877378592771, 136, 1);
INSERT INTO `sys_relation` VALUES (1370961877386981377, 137, 1);
INSERT INTO `sys_relation` VALUES (1370961877386981378, 152, 1);
INSERT INTO `sys_relation` VALUES (1370961877395369986, 153, 1);
INSERT INTO `sys_relation` VALUES (1370961877395369987, 154, 1);
INSERT INTO `sys_relation` VALUES (1370961877403758594, 132, 1);
INSERT INTO `sys_relation` VALUES (1370961877403758595, 138, 1);
INSERT INTO `sys_relation` VALUES (1370961877412147201, 139, 1);
INSERT INTO `sys_relation` VALUES (1370961877412147202, 140, 1);
INSERT INTO `sys_relation` VALUES (1370961877420535810, 155, 1);
INSERT INTO `sys_relation` VALUES (1370961877420535811, 156, 1);
INSERT INTO `sys_relation` VALUES (1370961877428924418, 157, 1);
INSERT INTO `sys_relation` VALUES (1370961877428924419, 133, 1);
INSERT INTO `sys_relation` VALUES (1370961877437313025, 160, 1);
INSERT INTO `sys_relation` VALUES (1370961877437313026, 161, 1);
INSERT INTO `sys_relation` VALUES (1370961877437313027, 141, 1);
INSERT INTO `sys_relation` VALUES (1370961877445701634, 142, 1);
INSERT INTO `sys_relation` VALUES (1370961877445701635, 143, 1);
INSERT INTO `sys_relation` VALUES (1370961877454090242, 144, 1);
INSERT INTO `sys_relation` VALUES (1370961877454090243, 1144441072852684801, 1);
INSERT INTO `sys_relation` VALUES (1370961877454090244, 1144441072852684802, 1);
INSERT INTO `sys_relation` VALUES (1370961877462478850, 1144441072852684803, 1);
INSERT INTO `sys_relation` VALUES (1370961877462478851, 1144441072852684804, 1);
INSERT INTO `sys_relation` VALUES (1370961877470867457, 171, 1);
INSERT INTO `sys_relation` VALUES (1370961877470867458, 1127085735660421122, 1);
INSERT INTO `sys_relation` VALUES (1370961877479256065, 1139826657964593154, 1);
INSERT INTO `sys_relation` VALUES (1370961877483450369, 1139827152854716418, 1);
INSERT INTO `sys_relation` VALUES (1370961877483450370, 1142957882422112257, 1);
INSERT INTO `sys_relation` VALUES (1370961877491838977, 1142957882422112258, 1);
INSERT INTO `sys_relation` VALUES (1370961877491838978, 1142957882422112259, 1);
INSERT INTO `sys_relation` VALUES (1370961877500227585, 1142957882426306562, 1);
INSERT INTO `sys_relation` VALUES (1370961877500227586, 1248972061080944641, 1);
INSERT INTO `sys_relation` VALUES (1370961877500227587, 1370961804473200642, 1);
INSERT INTO `sys_relation` VALUES (1371076713445900289, 1248972061080944641, 1371076682718429185);
INSERT INTO `sys_relation` VALUES (1371076713487843329, 1370961804473200642, 1371076682718429185);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL COMMENT '主键id',
  `pid` bigint NULL DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `sort` int NULL DEFAULT NULL COMMENT '序号',
  `version` int NULL DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 0, '超级管理员', 'administrator', 1, 1, NULL, '2020-04-15 17:53:01', NULL, 1);
INSERT INTO `sys_role` VALUES (1371076682718429185, 1, '员工', '员工', 2, NULL, '2021-03-14 20:32:09', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别(字典)',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `staff_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工号',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id(多个逗号隔开)',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门id(多个逗号隔开)',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(字典)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  `version` int NULL DEFAULT NULL COMMENT '乐观锁',
  `gog_secret` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_err_cnt` int NULL DEFAULT 0 COMMENT '登录错误次数',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1124606971782160385', 'admin', 'f96aa420921cbb361c98b523f9b160aa', 'q6taw', 'stylefeng', '2018-11-16 00:00:00', 'M', 'sn93@qq.com', '18200000000', NULL, '1', 24, 'ENABLE', '2016-01-29 08:49:53', NULL, '2021-03-16 22:17:11', 24, 25, 'WUUBNF25IEUKVCZJ', 0);
INSERT INTO `sys_user` VALUES (1249610385202286594, NULL, 'test1', 'a87ee27ce2677e3f44bb7606cebdcadb', 'ro03n', 'Premio', '2020-04-13 00:00:00', 'M', '123@qq.com', '', NULL, '1371076682718429185', 24, 'ENABLE', '2020-04-13 16:08:27', 1, '2021-03-14 20:33:07', 1, NULL, '7NMFAYM6476TJNTH', 0);

-- ----------------------------
-- Table structure for sys_user_pos
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_pos`;
CREATE TABLE `sys_user_pos`  (
  `user_pos_id` bigint NOT NULL COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `pos_id` bigint NOT NULL COMMENT '职位id',
  PRIMARY KEY (`user_pos_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户职位关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_pos
-- ----------------------------

-- ----------------------------
-- Table structure for t_line
-- ----------------------------
DROP TABLE IF EXISTS `t_line`;
CREATE TABLE `t_line`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `line` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线路',
  `sys_platform` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统平台',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 571 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户线路表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_line
-- ----------------------------
INSERT INTO `t_line` VALUES (570, 'acsj02001', 'YB');
INSERT INTO `t_line` VALUES (568, '34534', 'werw');
INSERT INTO `t_line` VALUES (569, '34534', 'IOC');

SET FOREIGN_KEY_CHECKS = 1;

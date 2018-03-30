/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : tistools

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 30/03/2018 15:30:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ac_app
-- ----------------------------
DROP TABLE IF EXISTS `ac_app`;
CREATE TABLE `ac_app`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `APP_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用代码',
  `APP_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名称',
  `APP_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用类型 : 取值来自业务菜单： DICT_AC_APPTYPE\r\n如：本地，远程',
  `ISOPEN` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否开通 : 取值来自业务菜单： DICT_YON\r\n默认为N，新建后，必须执行应用开通操作，才被开通。',
  `OPEN_DATE` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开通时间 : 记录到时分秒',
  `URL` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址 : http://IP:PORT/service-name',
  `IP_ADDR` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP',
  `IP_PORT` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '端口',
  `APP_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应用描述',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `APP_CODE`(`APP_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用系统 : 应用系统（Application）注册表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_app_config
-- ----------------------------
DROP TABLE IF EXISTS `ac_app_config`;
CREATE TABLE `ac_app_config`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用GUID',
  `CONFIG_TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置类型',
  `CONFIG_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置名',
  `CONFIG_DICT` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置值字典',
  `CONFIG_STYLE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置风格',
  `CONFIG_VALUE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '默认配置值',
  `ENABLED` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否启用',
  `DISPLAY_ORDER` decimal(4, 0) DEFAULT NULL COMMENT '显示顺序 : 所在层次内的展示顺序',
  `CONFIG_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置描述说明',
  PRIMARY KEY (`GUID`) USING BTREE,
  CONSTRAINT `ac_app_config_ibfk_1` FOREIGN KEY (`GUID`) REFERENCES `ac_app` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '个性化配置 : 应用个性化配置项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_datascope
-- ----------------------------
DROP TABLE IF EXISTS `ac_datascope`;
CREATE TABLE `ac_datascope`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_ENTITY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实体GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `PRIV_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据范围权限名称',
  `DATA_OP_TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据操作类型 : 取值来自业务菜单：DICT_AC_DATAOPTYPE\r\n对本数据范围内的数据，可以做哪些操作：增加、修改、删除、查询\r\n如果为空，表示都不限制；\r\n多个操作用逗号分隔，如： 增加,修改,删除',
  `ENTITY_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实体名称',
  `FILTER_SQL_STRING` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '过滤SQL : 例： (orgSEQ IS NULL or orgSEQ like \'$[SessionEntity/orgSEQ]%\') \r\n通过本SQL，限定了数据范围',
  PRIMARY KEY (`GUID`) USING BTREE,
  INDEX `F_ENTITY_DATA`(`GUID_ENTITY`) USING BTREE,
  CONSTRAINT `F_ENTITY_DATA` FOREIGN KEY (`GUID_ENTITY`) REFERENCES `ac_entity` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据范围权限 : 定义能够操作某个表数据的范围' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_entity
-- ----------------------------
DROP TABLE IF EXISTS `ac_entity`;
CREATE TABLE `ac_entity`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属应用GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ENTITY_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实体名称',
  `TABLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库表名',
  `ENTITY_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实体描述',
  `DISPLAY_ORDER` decimal(4, 0) NOT NULL DEFAULT 0 COMMENT '顺序',
  `ENTITY_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '实体类型 : 取值来自业务字典：DICT_AC_ENTITYTYPE\r\n0-表\r\n1-视图\r\n2-查询实体\r\n3-内存对象（系统运行时才存在）',
  `ISADD` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否可增加 : 取值来自业务菜单： DICT_YON',
  `ISDEL` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否可删除 : 取值来自业务菜单： DICT_YON',
  `ISMODIFY` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '可修改 : 取值来自业务菜单： DICT_YON',
  `ISVIEW` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' COMMENT '可查看 : 取值来自业务菜单： DICT_YON',
  `ISPAGE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' COMMENT '是否需要分页显示 : 取值来自业务菜单： DICT_YON',
  `PAGE_LEN` decimal(5, 0) DEFAULT 10 COMMENT '每页记录数',
  `CHECK_REF` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除记录检查引用关系 : 根据引用关系定义，检查关联记录是否需要同步删除；\r\n引用关系定义格式： table.column/[Y/N];table.column/[Y/N];...\r\n举例：\r\n假如，存在实体acct，且引用关系定义如下\r\n\r\nguid:tws_abc.acct_guid/Y;tws_nnn.acctid/N;\r\n\r\n当前删除acct实体guid＝9988的记录，系统自动执行引用关系删除，逻辑如下：\r\n查找tws_abc 表，acct_guid = 9988 的记录，并删除；\r\n查找tws_nnn 表，ac',
  PRIMARY KEY (`GUID`) USING BTREE,
  INDEX `F_APP_ENTITY`(`GUID_APP`) USING BTREE,
  CONSTRAINT `F_APP_ENTITY` FOREIGN KEY (`GUID_APP`) REFERENCES `ac_app` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '实体 : 数据实体定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_entityfield
-- ----------------------------
DROP TABLE IF EXISTS `ac_entityfield`;
CREATE TABLE `ac_entityfield`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_ENTITY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属实体GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `FIELD_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名称',
  `FIELD_DESC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性描述',
  `DISPLAY_FORMAT` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '显示格式 : 如：属性为日期时，可以设置显示格式 yyyy/MM/dd；\r\n当查询出数据，返回给调用着之前生效本显示格式（返回的数据已经被格式化）；',
  `DOCLIST_CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '代码大类',
  `CHECKBOX_VALUE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'CHECKBOX_VALUE',
  `FK_INPUTURL` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外键录入URL',
  `FK_FIELDDESC` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外键描述字段名',
  `FK_COLUMNNAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外键列名',
  `FK_TABLENAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外键表名',
  `DESC_FIELDNAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述字段名',
  `REF_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '引用类型 : 0 业务字典\r\n1 其他表',
  `FIELD_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段类型 : 0 字符串\r\n1 整数\r\n2 小数\r\n3 日期\r\n4 日期时间\r\n5 CHECKBOX\r\n6 引用',
  `DISPLAY_ORDER` decimal(4, 0) NOT NULL DEFAULT 0 COMMENT '顺序',
  `COLUMN_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库列名',
  `WIDTH` decimal(4, 0) DEFAULT NULL COMMENT '宽度',
  `DEFAULT_VALUE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '缺省值',
  `MIN_VALUE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最小值',
  `MAX_VALUE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最大值',
  `LENGTH_VALUE` decimal(4, 0) DEFAULT NULL COMMENT '长度',
  `PRECISION_VALUE` decimal(4, 0) DEFAULT NULL COMMENT '小数位',
  `VALIDATE_TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '页面校验类型',
  `ISMODIFY` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' COMMENT '是否可修改 : 取值来自业务菜单： DICT_YON',
  `ISDISPLAY` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' COMMENT '是否显示 : 取值来自业务菜单： DICT_YON',
  `ISINPUT` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否必须填写 : 取值来自业务菜单： DICT_YON',
  `ISPK` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否是主键 : 取值来自业务菜单： DICT_YON',
  `ISAUTOKEY` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N' COMMENT '是否自动产生主键 : 取值来自业务菜单： DICT_YON',
  PRIMARY KEY (`GUID`) USING BTREE,
  INDEX `F_ENTITY_FILED`(`GUID_ENTITY`) USING BTREE,
  CONSTRAINT `F_ENTITY_FILED` FOREIGN KEY (`GUID_ENTITY`) REFERENCES `ac_entity` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '实体属性 : 数据实体的字段（属性）定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_func
-- ----------------------------
DROP TABLE IF EXISTS `ac_func`;
CREATE TABLE `ac_func`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属应用 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `FUNC_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '\'1\'' COMMENT '功能类型 : 取值来自业务菜单：DICT_AC_FUNCTYPE\r\nF：功能（Function）\r\nB：行为（Behave）',
  `FUNC_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能编号 : 业务上对功能的编码',
  `FUNC_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `FUNC_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能描述',
  `ISMENU` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '可否为菜单 : Y 菜单（如果作为菜单入口，则会展示在菜单树）\r\nN 非菜单',
  `ISOPEN` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否启用 : Y 启用(默认）\r\nN 停用（不出现在菜单中）',
  `ISCHECK` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否验证权限 : 取值来自业务菜单： DICT_YON\r\nN：无需验权（只要有看见菜单，所有人都能执行本功能）\r\nY：需进行权限验证（默认）',
  `GUID_FUNC` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父 : 没有父时，默认为0，否则为父功能的GUID',
  `DISPLAY_ORDER` decimal(4, 0) NOT NULL COMMENT '显示顺序 : 所在层次内的展示顺序',
  `MENU_SEQ` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单路径序列',
  `IMAGE_PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单闭合图片路径',
  `EXPAND_PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单展开图片路径 : 自定时指定，不指定则保持',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `GUID`(`GUID`) USING BTREE,
  INDEX `GUID_APP`(`GUID_APP`) USING BTREE,
  INDEX `GUID_FUNC`(`GUID_FUNC`) USING BTREE,
  CONSTRAINT `ac_func_ibfk_1` FOREIGN KEY (`GUID_APP`) REFERENCES `ac_app` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ac_func_ibfk_2` FOREIGN KEY (`GUID_FUNC`) REFERENCES `ac_func` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '功能行为 : 功能&行为，菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_func_attr
-- ----------------------------
DROP TABLE IF EXISTS `ac_func_attr`;
CREATE TABLE `ac_func_attr`  (
  `GUID_FUNC` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应功能GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ATTR_TYPE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性类型',
  `ATTR_KEY` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性名',
  `ATTR_VALUE` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性名',
  `MEMO` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  INDEX `F_FUN_RES`(`GUID_FUNC`) USING BTREE,
  CONSTRAINT `F_FUN_RES` FOREIGN KEY (`GUID_FUNC`) REFERENCES `ac_func` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '功能属性 : 功能表字段之外的属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator`;
CREATE TABLE `ac_operator`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名',
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `OPERATOR_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作员姓名 : 记录当前操作员姓名（只记录当前值，不随之改变）',
  `OPERATOR_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员状态 : 取值来自业务菜单：DICT_AC_OPERATOR_STATUS\r\n正常，挂起，注销，锁定...\r\n系统处理状态间的流转',
  `INVAL_DATE` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '密码失效日期 : 指定失效时间具体到时分秒',
  `AUTH_MODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '认证模式 : 取值来自业务菜单：DICT_AC_AUTHMODE\r\n如：本地密���认证、LDAP认证、等\r\n可以多选，以逗号分隔，且按照出现先后顺序进行认证；\r\n如：\r\npwd,captcha\r\n表示输入密码，并且还需要验证码',
  `LOCK_LIMIT` decimal(4, 0) NOT NULL DEFAULT 5 COMMENT '锁定次数限制 : 登陆错误超过本数字，系统锁定操作员，默认5次。\r\n可为操作员单独设置；',
  `ERR_COUNT` decimal(10, 0) DEFAULT NULL COMMENT '当前错误登录次数',
  `LOCK_TIME` timestamp(0) NOT NULL COMMENT '锁定时间',
  `UNLOCK_TIME` timestamp(0) NOT NULL COMMENT '解锁时间 : 当状态为锁定时，解锁的时间',
  `LAST_LOGIN` timestamp(0) NOT NULL COMMENT '最近登录时间',
  `START_DATE` date DEFAULT NULL COMMENT '有效开始日期 : 启用操作员时设置，任何时间可设置；',
  `END_DATE` date DEFAULT NULL COMMENT '有效截止日期 : 启用操作员时设置，任何时间可设置；',
  `VALID_TIME` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '允许时间范围 : 定义一个规则表达式，表示允许操作的有效时间范围，格式为：\r\n[{begin:\"HH:mm\",end:\"HH:mm\"},{begin:\"HH:mm\",end:\"HH:mm\"},...]\r\n如：\r\n[{begin:\"08:00\",end:\"11:30\"},{begin:\"14:30\",end:\"17:00\"}]\r\n表示，该操作员被允许每天有两个时间段进行系统操作，分别 早上08:00 - 11:30，下午14:30 － 17:00 ',
  `MAC_CODE` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '允许MAC码 : 允许设置多个MAC，以逗号分隔，控制操作员只能在这些机器上登陆。',
  `IP_ADDRESS` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '允许IP地址 : 允许设置多个IP地址',
  PRIMARY KEY (`GUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作员 : 系统登录用户表，一个用户只能有一个或零个操作员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator_config
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator_config`;
CREATE TABLE `ac_operator_config`  (
  `GUID_OPERATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_CONFIG` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `CONFIG_VALUE` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置值',
  INDEX `GUID_CONFIG`(`GUID_CONFIG`) USING BTREE,
  INDEX `F_OPER_CONF`(`GUID_OPERATOR`) USING BTREE,
  CONSTRAINT `F_OPER_CONF` FOREIGN KEY (`GUID_OPERATOR`) REFERENCES `ac_operator` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ac_operator_config_ibfk_1` FOREIGN KEY (`GUID_CONFIG`) REFERENCES `ac_app_config` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作员个性配置 : 操作员个性化配置\r\n如颜色配置\r\n    登录风格\r\n    是否使用重组菜单\r\n    默认身份\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator_func
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator_func`;
CREATE TABLE `ac_operator_func`  (
  `GUID_OPERATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_FUNC` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `AUTH_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权标志 : 取值来自业务菜单：DICT_AC_AUTHTYPE\r\n如：特别禁止、特别允许',
  `START_DATE` date DEFAULT NULL COMMENT '有效开始日期',
  `END_DATE` date DEFAULT NULL COMMENT '有效截至日期',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应用GUID : 冗余字段',
  INDEX `F_FUN_OPER`(`GUID_FUNC`) USING BTREE,
  INDEX `F_OPER_FUN`(`GUID_OPERATOR`) USING BTREE,
  CONSTRAINT `F_FUN_OPER` FOREIGN KEY (`GUID_FUNC`) REFERENCES `ac_func` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_OPER_FUN` FOREIGN KEY (`GUID_OPERATOR`) REFERENCES `ac_operator` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作员特殊权限配置 : 针对人员配置的特殊权限，如特别开通的功能，或者特别禁止的功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator_identity
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator_identity`;
CREATE TABLE `ac_operator_identity`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_OPERATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `IDENTITY_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份名称',
  `IDENTITY_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '默认身份标志 : 见业务字典： DICT_YON\r\n只能有一个默认身份 Y是默认身份 N不是默认身份',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '适用于应用',
  `SEQ_NO` decimal(4, 0) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`GUID`) USING BTREE,
  INDEX `F_OPER_STATUS`(`GUID_OPERATOR`) USING BTREE,
  CONSTRAINT `F_OPER_STATUS` FOREIGN KEY (`GUID_OPERATOR`) REFERENCES `ac_operator` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作员身份 : 操作员对自己的权限进行组合形成一个固定的登录身份；\r\n供登录时选项，每一个登录身份是员工操作员的权限子集' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator_identityres
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator_identityres`;
CREATE TABLE `ac_operator_identityres`  (
  `GUID_IDENTITY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `AC_RESOURCETYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型 : 资源：操作员所拥有的权限来源\r\n见业务字典： DICT_AC_RESOURCETYPE\r\nfunction 功能\r\nrole 角色\r\n',
  `GUID_AC_RESOURCE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源GUID : 根据资源类型对应到不同权限资源的GUID',
  INDEX `F_STATUS_FUN`(`GUID_IDENTITY`) USING BTREE,
  CONSTRAINT `F_STATUS_FUN` FOREIGN KEY (`GUID_IDENTITY`) REFERENCES `ac_operator_identity` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '身份权限集 : 身份是操作员权限集合的子集，限定操作员登陆某个应用时，只具备特定的功能；' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator_menu
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator_menu`;
CREATE TABLE `ac_operator_menu`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_OPERATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '适用于应用',
  `GUID_FUNC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能GUID',
  `MENU_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `MENU_LABEL` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单显示（中文）',
  `GUID_PARENTS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父菜单GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ISLEAF` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否叶子菜单 : 见业务菜单： DICT_YON',
  `UI_ENTRY` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'UI入口 : 针对EXT模式提供，例如abf_auth/function/module.xml',
  `MENU_LEVEL` decimal(4, 0) DEFAULT NULL COMMENT '菜单层次 : 原类型smallint',
  `GUID_ROOT` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '根菜单GUID',
  `DISPLAY_ORDER` decimal(4, 0) DEFAULT NULL COMMENT '显示顺序 : 原类型smallint',
  `IMAGE_PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图片路径',
  `EXPAND_PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单展开图片路径',
  `MENU_SEQ` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单路径序列',
  `OPEN_MODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '页面打开方式 : 数值取自业务菜单： DICT_AC_OPENMODE\r\n如：主窗口打开、弹出窗口打开...',
  `SUB_COUNT` decimal(10, 0) DEFAULT NULL COMMENT '子节点数',
  PRIMARY KEY (`GUID`) USING BTREE,
  INDEX `F_OPER_RMENU`(`GUID_OPERATOR`) USING BTREE,
  INDEX `F_ROM_ROM`(`GUID_PARENTS`) USING BTREE,
  CONSTRAINT `F_OPER_RMENU` FOREIGN KEY (`GUID_OPERATOR`) REFERENCES `ac_operator` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ROM_ROM` FOREIGN KEY (`GUID_PARENTS`) REFERENCES `ac_operator_menu` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作员重组菜单 : 操作员对自己在某个应用系统的菜单重组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator_role
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator_role`;
CREATE TABLE `ac_operator_role`  (
  `GUID_OPERATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_ROLE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拥有角色GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  INDEX `F_OPER_ROLE`(`GUID_OPERATOR`) USING BTREE,
  INDEX `F_ROLE_OPER`(`GUID_ROLE`) USING BTREE,
  CONSTRAINT `F_OPER_ROLE` FOREIGN KEY (`GUID_OPERATOR`) REFERENCES `ac_operator` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ROLE_OPER` FOREIGN KEY (`GUID_ROLE`) REFERENCES `ac_role` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作员与权限集（角色）对应关系 : 操作员与权限集（角色）对应关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_operator_shortcut
-- ----------------------------
DROP TABLE IF EXISTS `ac_operator_shortcut`;
CREATE TABLE `ac_operator_shortcut`  (
  `GUID_OPERATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `SHORTCUT_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '快捷按键 : 如：CTRL+1 表示启动TX010505，本字段记录 CTRL+1 这个信息',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用GUID : 冗余字段，方便为快捷键分组',
  `GUID_FUNC` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能GUID',
  `ALIAS_FUNC_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能别名',
  `ORDER_NO` decimal(4, 0) NOT NULL COMMENT '排列顺序 : 原类型smallint',
  `IMAGE_PATH` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '快捷菜单图片路径 : 操作员自定义时指定，不指定则保持',
  `EXPAND_PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单展开图片路径 : 自定时指定，不指定则保持',
  INDEX `F_OPER_QMENU`(`GUID_OPERATOR`) USING BTREE,
  CONSTRAINT `F_OPER_QMENU` FOREIGN KEY (`GUID_OPERATOR`) REFERENCES `ac_operator` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作员快捷菜单 : 针对应用系统定义快捷菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_role
-- ----------------------------
DROP TABLE IF EXISTS `ac_role`;
CREATE TABLE `ac_role`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ROLE_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色代码 : 业务上对角色的编码',
  `ROLE_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属应用GUID',
  `ENABLED` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否启用',
  `ROLE_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `ROLE_CODE`(`ROLE_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限集(角色) : 权限集（角色）定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_role_datascope
-- ----------------------------
DROP TABLE IF EXISTS `ac_role_datascope`;
CREATE TABLE `ac_role_datascope`  (
  `GUID_ROLE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_DATASCOPE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拥有数据范围GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  INDEX `F_DATA_ROLE`(`GUID_DATASCOPE`) USING BTREE,
  INDEX `F_ROLE_DATA`(`GUID_ROLE`) USING BTREE,
  CONSTRAINT `F_DATA_ROLE` FOREIGN KEY (`GUID_DATASCOPE`) REFERENCES `ac_datascope` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ROLE_DATA` FOREIGN KEY (`GUID_ROLE`) REFERENCES `ac_role` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色数据范围权限对应 : 配置角色具有的数据权限。\r\n说明角色拥有某个实体数据中哪些范围的操作权。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_role_entity
-- ----------------------------
DROP TABLE IF EXISTS `ac_role_entity`;
CREATE TABLE `ac_role_entity`  (
  `GUID_ROLE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_ENTITY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拥有实体GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ISADD` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '可增加 : 取值来自业务菜单： DICT_YON',
  `ISDEL` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '可删除 : 取值来自业务菜单： DICT_YON',
  `ISMODIFY` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '可修改 : 取值来自业务菜单： DICT_YON',
  `ISVIEW` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' COMMENT '可查看 : 取值来自业务菜单： DICT_YON',
  INDEX `F_ENTITY_ROLE`(`GUID_ENTITY`) USING BTREE,
  INDEX `F_ROLE_ENTITY`(`GUID_ROLE`) USING BTREE,
  CONSTRAINT `F_ENTITY_ROLE` FOREIGN KEY (`GUID_ENTITY`) REFERENCES `ac_entity` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ROLE_ENTITY` FOREIGN KEY (`GUID_ROLE`) REFERENCES `ac_role` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色实体关系 : 角色与数据实体的对应关系。\r\n说明角色拥有哪些实体操作权。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_role_entityfield
-- ----------------------------
DROP TABLE IF EXISTS `ac_role_entityfield`;
CREATE TABLE `ac_role_entityfield`  (
  `GUID_ROLE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_ENTITYFIELD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拥有实体属性GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ISMODIFY` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '可修改 : 取值来自业务菜单： DICT_YON',
  `ISVIEW` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y' COMMENT '可查看 : 取值来自业务菜单： DICT_YON',
  INDEX `F_FIELD_ROLE`(`GUID_ENTITYFIELD`) USING BTREE,
  INDEX `F_ROLENTY_ROLE`(`GUID_ROLE`) USING BTREE,
  CONSTRAINT `F_FIELD_ROLE` FOREIGN KEY (`GUID_ENTITYFIELD`) REFERENCES `ac_entityfield` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ROLENTY_ROLE` FOREIGN KEY (`GUID_ROLE`) REFERENCES `ac_role` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与实体属性关系 : 角色与实体字段（属性）的对应关系。\r\n说明某个角色拥有哪些属性的操作权。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ac_role_func
-- ----------------------------
DROP TABLE IF EXISTS `ac_role_func`;
CREATE TABLE `ac_role_func`  (
  `GUID_ROLE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_FUNC` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拥有功能GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用GUID : 冗余字段',
  INDEX `F_FUN_ROLE`(`GUID_FUNC`) USING BTREE,
  INDEX `F_ROLE_FUN`(`GUID_ROLE`) USING BTREE,
  CONSTRAINT `F_FUN_ROLE` FOREIGN KEY (`GUID_FUNC`) REFERENCES `ac_func` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ROLE_FUN` FOREIGN KEY (`GUID_ROLE`) REFERENCES `ac_role` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限集(角色)功能对应关系 : 角色所包含的功能清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_abf_change
-- ----------------------------
DROP TABLE IF EXISTS `log_abf_change`;
CREATE TABLE `log_abf_change`  (
  `GUID_HISTORY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全局唯一标识符（GUID，Globally Unique Identifier）',
  `CHANGE_KEY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CHANGE_VALUE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  INDEX `GUID_HISTORY`(`GUID_HISTORY`) USING BTREE,
  CONSTRAINT `log_abf_change_ibfk_1` FOREIGN KEY (`GUID_HISTORY`) REFERENCES `log_abf_history` (`GUID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_abf_history
-- ----------------------------
DROP TABLE IF EXISTS `log_abf_history`;
CREATE TABLE `log_abf_history`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '全局唯一标识符（GUID，Globally Unique Identifier）',
  `GUID_OPERATE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全局唯一标识符（GUID，Globally Unique Identifier）',
  `OBJ_FROM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OBJ_TYPE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OBJ_GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OBJ_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OBJ_VALUE` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`GUID`) USING BTREE,
  INDEX `GUID_OPERATE`(`GUID_OPERATE`) USING BTREE,
  CONSTRAINT `log_abf_history_ibfk_1` FOREIGN KEY (`GUID_OPERATE`) REFERENCES `log_abf_operate` (`GUID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '记录客户与网点系统接触的所有日志明细，这些接触行为包括：\r\n客户主动接触网点，如：使用自助设备；\r\n柜员主动接触客户，如' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_abf_keyword
-- ----------------------------
DROP TABLE IF EXISTS `log_abf_keyword`;
CREATE TABLE `log_abf_keyword`  (
  `GUID_HISTORY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全局唯一标识符（GUID，Globally Unique Identifier）',
  `PARAM` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `VALUE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  INDEX `GUID_HISTORY`(`GUID_HISTORY`) USING BTREE,
  CONSTRAINT `log_abf_keyword_ibfk_1` FOREIGN KEY (`GUID_HISTORY`) REFERENCES `log_abf_history` (`GUID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_abf_operate
-- ----------------------------
DROP TABLE IF EXISTS `log_abf_operate`;
CREATE TABLE `log_abf_operate`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '全局唯一标识符（GUID，Globally Unique Identifier）',
  `OPERATE_FROM` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OPERATE_TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '见业务字典：DICT_OPERATOR_TYPE',
  `OPERATE_TIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `OPERATE_RESULT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '见业务字典：DICT_OPERATOR_RESULT',
  `OPERATE_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OPERATOR_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '记录当前操作员姓名（只记录当前值，不随之改变）',
  `USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登陆用户id',
  `APP_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `APP_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FUNC_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务上对功能的编码',
  `FUNC_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `RESTFUL_URL` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能对应的RESTFul服务地址',
  `STACK_TRACE` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '记录异常堆栈信息，超过4000的部分被自动丢弃',
  `PROCESS_DESC` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '记录功能执行时的业务处理信息',
  PRIMARY KEY (`GUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '记录操作员对ABF系统的操作日志（交易操作日志另见： LOG_TX_TRACE）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_duty
-- ----------------------------
DROP TABLE IF EXISTS `om_duty`;
CREATE TABLE `om_duty`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `DUTY_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职务代码',
  `DUTY_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职务名称',
  `DUTY_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职务套别 : 见业务字典： DICT_OM_DUTYTYPE\r\n���如科技类，审计类等\r\n实际记录了 字典项的GUID （SYS_DITC_ITEM）',
  `GUID_PARENTS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父职务GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ISLEAF` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否叶子节点 : 取值来自业务菜单：DICT_YON',
  `SUB_COUNT` decimal(10, 0) NOT NULL COMMENT '子节点数',
  `DUTY_LEVEL` decimal(10, 0) DEFAULT NULL COMMENT '职务层次',
  `DUTY_SEQ` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职务序列 : 职务的面包屑定位信息\r\n',
  `REMARK` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `DUTY_CODE`(`DUTY_CODE`) USING BTREE,
  INDEX `F_DUTY_DUTY`(`GUID_PARENTS`) USING BTREE,
  CONSTRAINT `F_DUTY_DUTY` FOREIGN KEY (`GUID_PARENTS`) REFERENCES `om_duty` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职务定义表 : 职务及responsiblity。定义职务及上下级关系（可以把“职务”理解为岗位的岗位类型，岗位是在机构' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_emp_group
-- ----------------------------
DROP TABLE IF EXISTS `om_emp_group`;
CREATE TABLE `om_emp_group`  (
  `GUID_EMP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_GROUP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属工作组GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  INDEX `F_EMP_GROUP`(`GUID_EMP`) USING BTREE,
  INDEX `GUID_GROUP`(`GUID_GROUP`) USING BTREE,
  CONSTRAINT `F_EMP_GROUP` FOREIGN KEY (`GUID_EMP`) REFERENCES `om_employee` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `om_emp_group_ibfk_1` FOREIGN KEY (`GUID_GROUP`) REFERENCES `om_group` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人员工作组对应关系 : 定义工作组包含的人员（工作组中有哪些人员）\r\n如：某个项目组有哪些人员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_emp_org
-- ----------------------------
DROP TABLE IF EXISTS `om_emp_org`;
CREATE TABLE `om_emp_org`  (
  `GUID_EMP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_ORG` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属机构GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ISMAIN` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否主机构 : 取值来自业务菜单： DICT_YON\r\n必须有且只能有一个主机构，默认Y，人员管理时程序检查当前是否只有一条主机构；',
  INDEX `GUID_EMP`(`GUID_EMP`) USING BTREE,
  INDEX `GUID_ORG`(`GUID_ORG`) USING BTREE,
  CONSTRAINT `om_emp_org_ibfk_1` FOREIGN KEY (`GUID_EMP`) REFERENCES `om_employee` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `om_emp_org_ibfk_2` FOREIGN KEY (`GUID_ORG`) REFERENCES `om_org` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工隶属机构关系表 : 定义人员和机构的关系表（机构有哪些人员）。\r\n允许一个人员同时在多个机构，但是只能有一个主机构。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_emp_position
-- ----------------------------
DROP TABLE IF EXISTS `om_emp_position`;
CREATE TABLE `om_emp_position`  (
  `GUID_EMP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_POSITION` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在岗位GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ISMAIN` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否主岗位 : 取值来自业务菜单：DICT_YON\r\n只能有一个主岗位',
  INDEX `F_EMP_POS`(`GUID_EMP`) USING BTREE,
  INDEX `F_POS_EMP`(`GUID_POSITION`) USING BTREE,
  CONSTRAINT `F_EMP_POS` FOREIGN KEY (`GUID_EMP`) REFERENCES `om_employee` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_POS_EMP` FOREIGN KEY (`GUID_POSITION`) REFERENCES `om_position` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工岗位对应关系 : 定义人员和岗位的对应关系，需要注明，一个人员可以设定一个基本岗位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_employee
-- ----------------------------
DROP TABLE IF EXISTS `om_employee`;
CREATE TABLE `om_employee`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `EMP_CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工代码',
  `EMP_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `EMP_REALNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工全名',
  `GENDER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别 : 见业务菜单：DICT_OM_GENDER',
  `EMPSTATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工状态 : 见业务字典： DICT_OM_EMPSTATUS',
  `GUID_ORG` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机构编号 : 人员所属主机构编号（冗余设计）',
  `GUID_POSITION` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '基本岗位',
  `GUID_EMP_MAJOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '直接主管',
  `INDATE` date DEFAULT NULL COMMENT '入职日期',
  `OUTDATE` date DEFAULT NULL COMMENT '离职日期',
  `MOBILENO` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `PAPER_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件类型 : 见业务字典： DICT_SD_PAPERTYPE',
  `PAPER_NO` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件号码',
  `BIRTHDATE` date DEFAULT NULL COMMENT '出生日期',
  `HTEL` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭电话',
  `HADDRESS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭地址',
  `HZIPCODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭邮编 : 见业务字典： DICT_SD_ZIPCODE',
  `GUID_OPERATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作员编号',
  `USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作员 : 登陆用户id',
  `REMARK` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATETIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `LASTMODYTIME` timestamp(0) NOT NULL COMMENT '最新更新时间',
  `UPDATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近更新人员',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `EMP_CODE`(`EMP_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工 : 人员信息表\r\n人员至少隶属于一个机构；\r\n本表记录了：人员基本信息，人员联系信息，人员在机构中的信息，人员对应的操' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_group
-- ----------------------------
DROP TABLE IF EXISTS `om_group`;
CREATE TABLE `om_group`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GROUP_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作组代码 : 业务上对工作组的编码',
  `GROUP_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作组名称',
  `GROUP_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作组类型 : 见业务字典： DICT_OM_GROUPTYPE',
  `GROUP_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作组状态 : 见业务字典： DICT_OM_GROUPSTATUS',
  `GROUP_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作组描述',
  `GUID_EMP_MANAGER` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人 : 选择范围来自 OM_EMPLOYEE表',
  `GUID_ORG` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属机构GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_PARENTS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父工作组GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ISLEAF` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否叶子节点 : 见业务菜单： DICT_YON',
  `SUB_COUNT` decimal(10, 0) NOT NULL COMMENT '子节点数',
  `GROUP_LEVEL` decimal(4, 0) DEFAULT NULL COMMENT '工作组层次',
  `GROUP_SEQ` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作组序列 : 本工作组的面包屑定位信息',
  `START_DATE` date DEFAULT NULL COMMENT '工作组有效开始日期',
  `END_DATE` date DEFAULT NULL COMMENT '工作组有效截止日期',
  `CREATETIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `LASTUPDATE` timestamp(0) NOT NULL COMMENT '最近更新时间',
  `UPDATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近更新人员',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `GROUP_CODE`(`GROUP_CODE`) USING BTREE,
  INDEX `F_GROUP_GROUP`(`GUID_PARENTS`) USING BTREE,
  INDEX `F_ORG_GROUP`(`GUID_ORG`) USING BTREE,
  CONSTRAINT `F_GROUP_GROUP` FOREIGN KEY (`GUID_PARENTS`) REFERENCES `om_group` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ORG_GROUP` FOREIGN KEY (`GUID_ORG`) REFERENCES `om_org` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作组 : 工作组定义表，用于定义临时组、虚拟组，跨部门的项目组等。\r\n工作组实质上与机构类似，是为了将项目组、工作组等临' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_group_app
-- ----------------------------
DROP TABLE IF EXISTS `om_group_app`;
CREATE TABLE `om_group_app`  (
  `GUID_GROUP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作组GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用GUID',
  INDEX `F_GROUP_APP`(`GUID_GROUP`) USING BTREE,
  CONSTRAINT `F_GROUP_APP` FOREIGN KEY (`GUID_GROUP`) REFERENCES `om_group` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作组应用列表 : 工作组所拥有（允许操作）的应用列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_group_position
-- ----------------------------
DROP TABLE IF EXISTS `om_group_position`;
CREATE TABLE `om_group_position`  (
  `GUID_GROUP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作组GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_POSITION` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  INDEX `F_GROUP_POS`(`GUID_GROUP`) USING BTREE,
  INDEX `GUID_POSITION`(`GUID_POSITION`) USING BTREE,
  CONSTRAINT `F_GROUP_POS` FOREIGN KEY (`GUID_GROUP`) REFERENCES `om_group` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `om_group_position_ibfk_1` FOREIGN KEY (`GUID_POSITION`) REFERENCES `om_position` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作组岗位列表 : 工作组岗位列表:一个工作组允许定义多个岗位，岗位之间允许存在层次关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_org
-- ----------------------------
DROP TABLE IF EXISTS `om_org`;
CREATE TABLE `om_org`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ORG_CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构代码 : 业务上对机构实体的编码。\r\n一般根据机构等级和机构类型进行有规则的编码。',
  `ORG_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构名称',
  `ORG_TYPE` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构类型 : 见业务字典： DICT_OM_ORGTYPE\r\n如：总公司/总部部门/分公司/分公司部门...',
  `ORG_DEGREE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构等级 : 见业务字典： DICT_OM_ORGDEGREE\r\n如：总行，分行，海外分行...',
  `ORG_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构状态 : 见业务字典： DICT_OM_ORGSTATUS',
  `GUID_PARENTS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父机构GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ORG_SEQ` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构序列 : 类似面包屑导航，以“.”分割所有父机构GUID。能看出本机构的层级关系；\r\n格式： 父机构GUID.父机构GUID....本机构GUID',
  `ORG_ADDR` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构地址',
  `LINK_MAN` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人姓名',
  `LINK_TEL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `START_DATE` date DEFAULT NULL COMMENT '生效日期',
  `END_DATE` date DEFAULT NULL COMMENT '失效日期',
  `AREA` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属地域 : 见业务字典： DICT_SD_AREA',
  `SORT_NO` decimal(4, 0) DEFAULT NULL COMMENT '排列顺序编号 : 维护时，可手工指定从0开始的自然数字；如果为空，系统将按照机构代码排序。',
  `ISLEAF` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否叶子节点 : 系统根据当前是否有下级机构判断更新（见业务字典 DICT_YON）',
  `REMARK` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `LAST_UPDATE` timestamp(0) NOT NULL COMMENT '最近更新时间',
  `UPDATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近更新人员',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `ORG_CODE`(`ORG_CODE`) USING BTREE,
  INDEX `F_ORG_ORG`(`GUID_PARENTS`) USING BTREE,
  CONSTRAINT `F_ORG_ORG` FOREIGN KEY (`GUID_PARENTS`) REFERENCES `om_org` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构信息表 : 机构部门（Organization）表\r\n允许定义多个平行机构' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_position
-- ----------------------------
DROP TABLE IF EXISTS `om_position`;
CREATE TABLE `om_position`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_ORG` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属机构GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `POSITION_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位代码 : 业务上对岗位的编码',
  `POSITION_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `POSITION_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位类别 : 见业务字典： DICT_OM_POSITYPE\r\n机构岗位，工作组岗位',
  `POSITION_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位状态 : 见业务字典： DICT_OM_POSISTATUS',
  `ISLEAF` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否叶子岗位 : 见业务字典： DICT_YON',
  `SUB_COUNT` decimal(10, 0) NOT NULL COMMENT '子节点数',
  `POSITION_LEVEL` decimal(2, 0) DEFAULT NULL COMMENT '岗位层次',
  `POSITION_SEQ` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位序列 : 岗位的面包屑定位信息',
  `GUID_PARENTS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父岗位GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_DUTY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属职务GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `START_DATE` date DEFAULT NULL COMMENT '岗位有效开始日期',
  `END_DATE` date DEFAULT NULL COMMENT '岗位有效截止日期',
  `CREATETIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `LASTUPDATE` timestamp(0) NOT NULL COMMENT '最近更新时间',
  `UPDATOR` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近更新人员',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `POSITION_CODE`(`POSITION_CODE`) USING BTREE,
  INDEX `F_DUTY_POS`(`GUID_DUTY`) USING BTREE,
  INDEX `F_ORG_POS`(`GUID_ORG`) USING BTREE,
  INDEX `F_POS_POS`(`GUID_PARENTS`) USING BTREE,
  CONSTRAINT `F_DUTY_POS` FOREIGN KEY (`GUID_DUTY`) REFERENCES `om_duty` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_ORG_POS` FOREIGN KEY (`GUID_ORG`) REFERENCES `om_org` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `F_POS_POS` FOREIGN KEY (`GUID_PARENTS`) REFERENCES `om_position` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位 : 岗位定义表\r\n岗位是职务在机构上的实例化表现（某个机构／部门中对某个职务（Responsibility）的工作定' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for om_position_app
-- ----------------------------
DROP TABLE IF EXISTS `om_position_app`;
CREATE TABLE `om_position_app`  (
  `GUID_POSITION` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用GUID',
  INDEX `GUID_POSITION`(`GUID_POSITION`) USING BTREE,
  CONSTRAINT `om_position_app_ibfk_1` FOREIGN KEY (`GUID_POSITION`) REFERENCES `om_position` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位应用列表 : 岗位所拥有（允许操作）的应用列表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_channel_ctl
-- ----------------------------
DROP TABLE IF EXISTS `sys_channel_ctl`;
CREATE TABLE `sys_channel_ctl`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier）',
  `CHN_CODE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '渠道代码 : 记录接触系统对应的渠道代码',
  `CHN_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '渠道名称',
  `CHN_REMARK` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '渠道备注信息',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `GUID`(`GUID`) USING BTREE,
  UNIQUE INDEX `CHN_CODE`(`CHN_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '渠道参数控制表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `DICT_KEY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务字典',
  `DICT_TYPE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 : 见业务字典： DICT_TYPE\r\na 应用级（带业务含义的业务字典，应用开发时可扩展）\r\ns 系统级（平台自己的业务字典）',
  `DICT_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `DICT_DESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '解释说明',
  `GUID_PARENTS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父字典GUID : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `DEFAULT_VALUE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务字典默认值 : 指定某个字典项（ITEM_VALUE）为本业务字典的默认值（用于扶助View层实现展示默认值）',
  `FROM_TABLE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项来源表 : 如果业务字典用来描述某个表中的字段选项，则本字段保存表名；\r\n其他情况默认为空；',
  `USE_FOR_KEY` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作为字典项的列 : 如果业务字典用来描述某个表中的字段选项，则本字段保存字段名；\r\n其他情况默认为空；',
  `USE_FOR_NAME` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作为字典项名称的列',
  `SEQNO` decimal(12, 0) DEFAULT NULL COMMENT '顺序号 : 顺序号，从0开始排，按小到大排序',
  `SQL_FILTER` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '过滤条件',
  `FROM_TYPE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项来源类型 : 来源类型:0:来自字典项 1:来自单表  2:多表或视图',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `DICT_KEY`(`DICT_KEY`) USING BTREE,
  INDEX `GUID_PARENTS`(`GUID_PARENTS`) USING BTREE,
  CONSTRAINT `sys_dict_ibfk_1` FOREIGN KEY (`GUID_PARENTS`) REFERENCES `sys_dict` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务字典 : 业务字典表，定义系统中下拉菜单的数据（注意：仅仅包括下拉菜单中的数据，而不包括下拉菜单样式，是否多选这些与' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_DICT` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '隶属业务字典 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `ITEM_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典项名称',
  `ITEM_TYPE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项类型 : 来自 dict 字典、value 实际值',
  `ITEM_VALUE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典项',
  `SEND_VALUE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实际值 : 实际值，及选中字典项后，实际发送值给系统的数值。',
  `SEQNO` decimal(12, 0) DEFAULT NULL COMMENT '顺序号 : 顺序号，从0开始排，按小到大排序',
  `ITEM_DESC` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项说明',
  PRIMARY KEY (`GUID`) USING BTREE,
  INDEX `GUID_DICT`(`GUID_DICT`) USING BTREE,
  CONSTRAINT `sys_dict_item_ibfk_1` FOREIGN KEY (`GUID_DICT`) REFERENCES `sys_dict` (`GUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务字典项 : 业务字典内容项， 展示下拉菜单结构时，一般需要： 字典项，字典项名称，实际值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_err_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_err_code`;
CREATE TABLE `sys_err_code`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier）',
  `ERRCODE_KIND` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '错误代码分类 : 见业务字典： DICT_ERRCODE_KIND\r\nSYS 系统错误码\r\nTRANS 交易错误码\r\n',
  `ERR_CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '错误代码',
  `ERR_MSG` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '错误信息',
  PRIMARY KEY (`GUID`) USING BTREE,
  UNIQUE INDEX `GUID`(`GUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '错误码表 : 记录系统中的各种错误代码信息，如系统抛出的错误信息，交易执行时的错误码等' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator_log`;
CREATE TABLE `sys_operator_log`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier）',
  `OPERATOR_TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作类型 : 见业务字典：DICT_OPERATOR_TYPE',
  `OPERATOR_TIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `OPERATOR_RESULT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作结果 : 见业务字典：DICT_OPERATOR_RESULT',
  `OPERATOR_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作员姓名 : 记录当前操作员姓名（只记录当前值，不随之改变）',
  `USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作员 : 登陆用户id',
  `APP_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应用代码',
  `APP_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应用名称',
  `FUNC_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能编号 : 业务上对功能的编码',
  `FUNC_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能名称',
  `RESTFUL_RUL` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务地址 : 功能对应的RESTFul服务地址',
  `STACK_TRACE` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '异常堆栈 : 记录异常堆栈信息，超过4000的部分被自动丢弃',
  `PROCSS_DESC` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '处理描述 : 记录功能执行时的业务处理信息',
  PRIMARY KEY (`GUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志 : 记录操作员使用系统的操作日志（交易操作日志另见： LOG_TX_TRACE）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_run_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_run_config`;
CREATE TABLE `sys_run_config`  (
  `GUID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据主键 : 全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；',
  `GUID_APP` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用系统GUID : 用于表识一组参数属于某个应用系统 。下拉AC_APP表记录',
  `GROUP_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数组别 : 参数组别，手工输入',
  `KEY_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数键 : 参数键名称，手工输入',
  `VALUE_FROM` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值来源类型 : H：手工指定\r\n或者选择业务字典的GUID（此时存储业务字典名称 SYS_DICT.DICT_KEY)',
  `VALUE` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数值 : 当value_from为H时，手工输入任意有效字符串；\r\n当value_from为业务字典时，下拉选择；',
  `DESCRIPTION` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数描述 : 参数功能描述',
  PRIMARY KEY (`GUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统运行参数表 : 运行期系统参数表，以三段式结构进行参数存储' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_seqno
-- ----------------------------
DROP TABLE IF EXISTS `sys_seqno`;
CREATE TABLE `sys_seqno`  (
  `SEQ_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '序号资源表名称 : 序号资源的名称，如:柜员660001的交易流水序号资源',
  `SEQ_KEY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序号键值',
  `SEQ_NO` decimal(20, 0) NOT NULL DEFAULT 0 COMMENT '序号数 : 顺序增加的数字',
  `RESET` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '重置方式 : 来自业务字典： DICT_SYS_RESET\r\n如：\r\n不重置（默认）\r\n按天重置\r\n按周重置\r\n自定义重置周期（按指定时间间隔重置）\r\n...',
  `RESET_PARAMS` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '重置处理参数 : 重置程序执行时的输入参数，通过本参数指定六重置周期，重置执行时间，重置起始数字等',
  PRIMARY KEY (`SEQ_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '序号资源表 : 每个SEQ_KEY表示一个序号资源，顺序增加使用序号。' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

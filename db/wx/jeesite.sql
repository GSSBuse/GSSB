SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX corpid ON wx_callback_info;
DROP INDEX corpid ON wx_interface_info;
DROP INDEX corpid ON wx_tag;



/* Drop Tables */

DROP TABLE IF EXISTS wx_callback_info;
DROP TABLE IF EXISTS wx_interface_info;
DROP TABLE IF EXISTS wx_tag;
DROP TABLE IF EXISTS wx_user_tag;




/* Create Tables */

-- wx_callback_info
CREATE TABLE wx_callback_info
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 企业编号
	office_id varchar(64) NOT NULL COMMENT '企业编号',
	-- 微信企业ID
	corpid varchar(100) NOT NULL COMMENT '微信企业ID',
	-- 应用ID
	agent_id varchar(64) NOT NULL COMMENT '应用ID',
	-- 应用名称
	agent_name varchar(100) COMMENT '应用名称',
	-- Token
	token varchar(100) NOT NULL COMMENT 'Token',
	-- EncodingAESKey
	encoding_aeskey varchar(100) NOT NULL COMMENT 'EncodingAESKey',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = 'wx_callback_info' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- 接口验证信息表
CREATE TABLE wx_interface_info
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 企业编号
	office_id varchar(64) NOT NULL COMMENT '企业编号',
	-- corpid
	corpid varchar(100) NOT NULL COMMENT 'corpid',
	-- provider_secret
	provider_secret varchar(100) NOT NULL COMMENT 'provider_secret',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '接口验证信息表';


-- 企业微信标签表
CREATE TABLE wx_tag
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 企业编号
	office_id varchar(64) COMMENT '企业编号',
	name varchar(30) NOT NULL COMMENT '标签名称',
	enname varchar(50) COMMENT '英文名称',
	tag_type varchar(255) COMMENT '标签类型',
	data_scope char(1) COMMENT '数据范围',
	is_sys varchar(64) COMMENT '是否系统数据',
	useable varchar(64) COMMENT '是否可用',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '企业微信标签表';


-- 用户-标签
CREATE TABLE wx_user_tag
(
	user_id varchar(64) NOT NULL COMMENT '用户编号',
	tag_id varchar(64) NOT NULL COMMENT '标签编号',
	PRIMARY KEY (user_id, tag_id)
) COMMENT = '用户-标签';



/* Create Indexes */

CREATE INDEX corpid USING BTREE ON wx_callback_info (corpid ASC);
CREATE INDEX corpid ON wx_interface_info (corpid DESC);




SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX dy_client_id ON dy_client;
DROP INDEX dy_client_dyid ON dy_client;
DROP INDEX dy_client_openid ON dy_client;
DROP INDEX dy_domainname_id ON dy_domainname;
DROP INDEX dy_domainname_client_id ON dy_domainname;



/* Drop Tables */

DROP TABLE IF EXISTS dy_access_record;
DROP TABLE IF EXISTS dy_attention;
DROP TABLE IF EXISTS dy_bidhistory;
DROP TABLE IF EXISTS dy_bonus;
DROP TABLE IF EXISTS dy_cash_flow;
DROP TABLE IF EXISTS dy_finance;
DROP TABLE IF EXISTS dy_income_expense;
DROP TABLE IF EXISTS dy_news;
DROP TABLE IF EXISTS dy_client;
DROP TABLE IF EXISTS dy_domainname;
DROP TABLE IF EXISTS dy_level_setting;
DROP TABLE IF EXISTS dy_platform_account;
DROP TABLE IF EXISTS dy_platform_finance;
DROP TABLE IF EXISTS dy_platform_income;
DROP TABLE IF EXISTS dy_share;
DROP TABLE IF EXISTS dy_wxpay_result;




/* Create Tables */

-- 访问记录表
CREATE TABLE dy_access_record
(
	id varchar(64) NOT NULL COMMENT 'id',
	domainname_id varchar(64) NOT NULL COMMENT '域名Id',
	client_id varchar(64) NOT NULL COMMENT '会员Id',
	share_clientid varchar(64) NOT NULL COMMENT '分享人Id',
	access_time datetime COMMENT '访问时间',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '访问记录表' DEFAULT CHARACTER SET utf8;


-- 关注表
CREATE TABLE dy_attention
(
	id varchar(64) NOT NULL COMMENT '关注ID',
	client_id varchar(64) NOT NULL COMMENT '会员id',
	domainname_id varchar(64) NOT NULL COMMENT '被关注域名id',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date timestamp NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date timestamp NOT NULL COMMENT '更新时间',
	del_flag char(1) NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '关注表' DEFAULT CHARACTER SET utf8;


-- 出价表
CREATE TABLE dy_bidhistory
(
	id varchar(64) NOT NULL COMMENT 'id',
	domainname_id varchar(64) NOT NULL COMMENT '域名id',
	client_id varchar(64) NOT NULL COMMENT '会员id',
	bid_amount bigint NOT NULL COMMENT '出价金额',
	proxy_amount bigint COMMENT '代理竞价金额',
	type varchar(100) NOT NULL COMMENT '出价类型',
	bid_time timestamp NOT NULL COMMENT '出价时间',
	share_openid varchar(200) COMMENT '分享者openid',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '出价表' DEFAULT CHARACTER SET utf8;


-- 红包佣金表
CREATE TABLE dy_bonus
(
	id varchar(64) NOT NULL COMMENT 'id',
	domainname_id varchar(64) NOT NULL COMMENT '域名id',
	client_id varchar(64) NOT NULL COMMENT '会员id',
	money bigint NOT NULL COMMENT '金额',
	type char(2) NOT NULL COMMENT '收支类型',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '红包佣金表' DEFAULT CHARACTER SET utf8;


-- 资金流
CREATE TABLE dy_cash_flow
(
	id varchar(64) NOT NULL COMMENT 'id',
	client_id varchar(64) NOT NULL COMMENT '会员id',
	operate varchar(100) NOT NULL COMMENT '操作',
	operate_amount bigint NOT NULL COMMENT '操作金额',
	operate_time timestamp NOT NULL COMMENT '操作时间',
	confirm_result varchar(64) NOT NULL COMMENT '经纪人确认结果',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '资金流' DEFAULT CHARACTER SET utf8;


-- 会员表
CREATE TABLE dy_client
(
	id varchar(64) NOT NULL COMMENT '编号',
	dyid varchar(200) NOT NULL COMMENT '米友号',
	openid varchar(200) NOT NULL COMMENT '微信标识',
	name varchar(100) COMMENT '姓名',
	nickname varchar(100) NOT NULL COMMENT '微信昵称',
	email varchar(200) COMMENT '邮箱',
	mobile varchar(200) COMMENT '手机',
	qq varchar(200) COMMENT 'QQ号',
	wx varchar(200) COMMENT '微信号',
	photo varchar(1000) NOT NULL COMMENT '微信头像',
	vip int NOT NULL COMMENT '会员等级',
	broker_id varchar(64) NOT NULL COMMENT '所属经纪人id',
	seal_flag char(1) NOT NULL COMMENT '封号标记',
	email_flag varchar(1) DEFAULT '0' COMMENT '邮箱认证',
	authentication_mark int COMMENT '身份认证',
	default_income_expense varchar(100) COMMENT '默认收支方式',
	authentication_positive_image_url varchar(100) COMMENT '身份证正面照片',
	authentication_negative_image_url varchar(100) COMMENT '身份证反面照片',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '会员表' DEFAULT CHARACTER SET utf8;


-- 域名
CREATE TABLE dy_domainname
(
	id varchar(64) NOT NULL COMMENT '编号',
	name varchar(100) NOT NULL COMMENT '域名名称',
	client_id varchar(64) NOT NULL COMMENT '会员编号',
	begin_time timestamp COMMENT '拍卖开始时间',
	end_time timestamp NOT NULL COMMENT '拍卖结束时间',
	deposit bigint COMMENT '保证金',
	description varchar(100) COMMENT '域名描述',
	type varchar(100) COMMENT '域名类别',
	reserve_price bigint COMMENT '保留价',
	appraise_price bigint COMMENT '估价',
	increment bigint COMMENT '加价幅度',
	status char(2) NOT NULL COMMENT '状态',
	bonus_share_total bigint COMMENT '转发送红包总额',
	bonus_share_number bigint COMMENT '转发送红包份数',
	bonus_second bigint COMMENT '次高价红包',
	image1 varchar(100) COMMENT '图片1',
	image2 varchar(100) COMMENT '图片2',
	image3 varchar(100) COMMENT '图片3',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '域名' DEFAULT CHARACTER SET utf8;


-- 财务表
CREATE TABLE dy_finance
(
	id varchar(64) NOT NULL COMMENT 'id',
	client_id varchar(64) NOT NULL COMMENT '会员id',
	account_balance bigint NOT NULL COMMENT '账户余额',
	freeze_balance bigint NOT NULL COMMENT '冻结金额',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '财务表' DEFAULT CHARACTER SET utf8;


-- 收支方式
CREATE TABLE dy_income_expense
(
	id varchar(64) NOT NULL COMMENT 'id',
	client_id varchar(64) NOT NULL COMMENT '会员id',
	income_expense_account varchar(100) NOT NULL COMMENT '收支账号',
	type char(2) NOT NULL COMMENT '账号类型',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '收支方式' DEFAULT CHARACTER SET utf8;


-- 加价与保证金增幅
CREATE TABLE dy_level_setting
(
	id varchar(64) NOT NULL COMMENT 'id',
	level bigint COMMENT '出价',
	price bigint COMMENT '增幅',
	type char(1) COMMENT '类型',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '加价与保证金增幅' DEFAULT CHARACTER SET utf8;


-- 最新消息
CREATE TABLE dy_news
(
	id varchar(64) NOT NULL COMMENT 'id',
	domainname_id varchar(64) NOT NULL COMMENT '被关注域名id',
	client_id varchar(64) NOT NULL COMMENT '关注者id',
	new_bid_count int NOT NULL COMMENT '最新出价计数',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '最新消息' DEFAULT CHARACTER SET utf8;


-- 平台总账户
CREATE TABLE dy_platform_account
(
	id char(64) NOT NULL COMMENT 'id',
	total_finance bigint NOT NULL COMMENT '平台（账户/收支）总额',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '平台总账户' DEFAULT CHARACTER SET utf8;


-- 平台总账户
CREATE TABLE dy_platform_finance
(
	id char(64) NOT NULL COMMENT 'id',
	client_id char(64) NOT NULL COMMENT '会员id',
	operate char(100) NOT NULL COMMENT '操作',
	operate_amount bigint NOT NULL COMMENT '操作金额',
	total_amount bigint NOT NULL COMMENT '账户总额',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '平台总账户' DEFAULT CHARACTER SET utf8;


-- dy_platform_income
CREATE TABLE dy_platform_income
(
	id char(64) NOT NULL COMMENT 'id',
	domainname_id char(64) NOT NULL COMMENT '域名id',
	buy_client_id char(64) COMMENT '买家id',
	operate char(100) NOT NULL COMMENT '操作',
	operate_amount bigint NOT NULL COMMENT '操作金额',
	total_amount bigint NOT NULL COMMENT '总收入额',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = 'dy_platform_income' DEFAULT CHARACTER SET utf8;


-- 分享记录表
CREATE TABLE dy_share
(
	id varchar(64) NOT NULL COMMENT 'id',
	domainname_id varchar(64) NOT NULL COMMENT '域名id',
	client_id varchar(64) NOT NULL COMMENT '会员id',
	share_time datetime COMMENT '分享时间',
	redbag_amount bigint DEFAULT 0 COMMENT 'redbag_amount',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '分享记录表' DEFAULT CHARACTER SET utf8;


-- 微信支付结果表
CREATE TABLE dy_wxpay_result
(
	id varchar(64) NOT NULL COMMENT 'id',
	openid varchar(200) NOT NULL COMMENT '会员openid',
	sign varchar(32) NOT NULL COMMENT '签名',
	out_trade_no varchar(32) NOT NULL COMMENT '商户订单号',
	total_fee bigint NOT NULL COMMENT '交易总额',
	transaction_id varchar(32) COMMENT '微信支付订单号',
	time_end datetime COMMENT '支付完成时间',
	result_code varchar(10) COMMENT '支付结果',
	status varchar(1) DEFAULT '0' NOT NULL COMMENT '处理状态',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '微信支付结果表' DEFAULT CHARACTER SET utf8;



/* Create Foreign Keys */

ALTER TABLE dy_attention
	ADD FOREIGN KEY (client_id)
	REFERENCES dy_client (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_bidhistory
	ADD FOREIGN KEY (client_id)
	REFERENCES dy_client (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_bonus
	ADD FOREIGN KEY (client_id)
	REFERENCES dy_client (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_cash_flow
	ADD FOREIGN KEY (client_id)
	REFERENCES dy_client (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_finance
	ADD FOREIGN KEY (client_id)
	REFERENCES dy_client (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_income_expense
	ADD FOREIGN KEY (client_id)
	REFERENCES dy_client (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_news
	ADD FOREIGN KEY (client_id)
	REFERENCES dy_client (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_attention
	ADD FOREIGN KEY (domainname_id)
	REFERENCES dy_domainname (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_bidhistory
	ADD FOREIGN KEY (domainname_id)
	REFERENCES dy_domainname (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_bonus
	ADD FOREIGN KEY (domainname_id)
	REFERENCES dy_domainname (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dy_news
	ADD FOREIGN KEY (domainname_id)
	REFERENCES dy_domainname (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Create Indexes */

CREATE INDEX dy_client_id ON dy_client (id ASC);
CREATE INDEX dy_client_dyid ON dy_client (dyid ASC);
CREATE INDEX dy_client_openid ON dy_client (openid ASC);
CREATE INDEX dy_domainname_id ON dy_domainname (id ASC);
CREATE INDEX dy_domainname_client_id ON dy_domainname (client_id ASC);




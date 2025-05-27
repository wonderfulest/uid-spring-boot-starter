DROP DATABASE IF EXISTS tinyid;
CREATE DATABASE tinyid;
USE tinyid;
DROP TABLE IF EXISTS `tiny_id`;
CREATE TABLE IF NOT EXISTS `tiny_id` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `biz_type` varchar(63) NOT NULL DEFAULT '' COMMENT '业务类型，唯一',
  `begin_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '开始id，仅记录初始值，无其他含义。初始化时begin_id和max_id应相同',
  `max_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '当前最大id',
  `step` int(11) DEFAULT '0' COMMENT '步长',
  `delta` int(11) NOT NULL DEFAULT '1' COMMENT 'id增量，每次获取id的步长;最小为1',
  `remainder` int(11) NOT NULL DEFAULT '0' COMMENT '余数',
  `create_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '更新时间',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_biz_type` (`biz_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT 'id信息表';

DROP TABLE IF EXISTS `tiny_id_token`;
CREATE TABLE IF NOT EXISTS `tiny_id_token` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `token` varchar(255) NOT NULL DEFAULT '' COMMENT 'token',
  `biz_type` varchar(63) NOT NULL DEFAULT '' COMMENT '此token可访问的业务类型标识',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT 'token信息表';

INSERT INTO `tiny_id` (`biz_type`, `begin_id`, `max_id`, `step`, `delta`, `remainder`, `create_time`, `update_time`, `version`)
VALUES
	('product', 100000, 110000, 10000, 1, 1, NOW(), '2018-07-22 23:19:27', 1);
INSERT INTO `tiny_id_token` (`token`, `biz_type`, `remark`, `create_time`, `update_time`)
VALUES
	('0f673adf80504e2eaa552f5d791b644c', 'product', '1', '2017-12-14 16:36:46', '2017-12-14 16:36:48');

INSERT INTO `tiny_id` (`biz_type`, `begin_id`, `max_id`, `step`, `delta`, `remainder`, `create_time`, `update_time`, `version`)
VALUES
	('bundle', 100000, 110000, 10000, 1, 1, '2018-07-21 23:52:58', '2018-07-23 00:39:24', 3);
INSERT INTO `tiny_id_token` (`token`, `biz_type`, `remark`, `create_time`, `update_time`)
VALUES
	('0f673adf80504e2eaa552f5d791b644c', 'bundle', '1', '2017-12-14 16:36:46', '2017-12-14 16:36:48');


INSERT INTO tiny_id (biz_type, begin_id, max_id, step, delta, remainder, create_time, update_time)
VALUES ('order_id', 1, 1001, 1000, 1, 0, NOW(), NOW());
INSERT INTO tiny_id_token (token, biz_type, remark, create_time, update_time)
VALUES ('0f673adf80504e2eaa552f5d791b644c', 'order_id', '1', NOW(), NOW());
-- 分库分表  在不同的数据库创建不同表名(相同前缀),相同结构的多个表
-- 创建表 `t_order_info_0`
CREATE TABLE `t_order_info_0` (
  `id` bigint NOT NULL COMMENT '订单id，采用分布式id',
  `order_name` varchar(128) DEFAULT NULL COMMENT '订单名称',
  `product_id` bigint DEFAULT NULL COMMENT '购买产品id',
  `buy_num` int DEFAULT NULL COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- 创建表 `t_order_info_1`
CREATE TABLE `t_order_info_1` (
  `id` bigint NOT NULL COMMENT '订单id，采用分布式id',
  `order_name` varchar(128) DEFAULT NULL COMMENT '订单名称',
  `product_id` bigint DEFAULT NULL COMMENT '购买产品id',
  `buy_num` int DEFAULT NULL COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- 创建表 `t_order_info_2` */
CREATE TABLE `t_order_info_2` (
  `id` bigint NOT NULL COMMENT '订单id，采用分布式id',
  `order_name` varchar(128) DEFAULT NULL COMMENT '订单名称',
  `product_id` bigint DEFAULT NULL COMMENT '购买产品id',
  `buy_num` int DEFAULT NULL COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- 创建表 `undo_log`
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime NOT NULL COMMENT 'create datetime',
  `log_modified` datetime NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=406 DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';


-- 创建表 `t_order_detail_0`
CREATE TABLE `t_order_detail_0` (
  `id` bigint NOT NULL COMMENT '订单id，采用分布式id',
  `product_name` bigint DEFAULT NULL COMMENT '购买产品id',
  `amount` decimal COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单记录表';
-- 创建表 `t_order_detail_0`
CREATE TABLE `t_order_detail_0` (
  `id` bigint NOT NULL COMMENT '订单id，采用分布式id',
  `product_name` bigint DEFAULT NULL COMMENT '购买产品id',
  `amount` decimal COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单记录表';
-- 创建表 `t_order_detail_0`
CREATE TABLE `t_order_detail_0` (
  `id` bigint NOT NULL COMMENT '订单id，采用分布式id',
  `product_name` bigint DEFAULT NULL COMMENT '购买产品id',
  `amount` decimal COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单记录表';


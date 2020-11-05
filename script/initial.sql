CREATE DATABASE `usersync` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
# 创建各系统中的用户表
DROP TABLE IF EXISTS `users_info`;
CREATE TABLE `users_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自动递增主键',
  `user_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标识用户是linux、Kerberos或者ldap',
  `user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户所在组',
  `user_passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
   ip                varchar(255) null comment '用户所在ip，ldap无ip',
  `has_use` tinyint(4) NULL DEFAULT NULL COMMENT '是否在用，1表示在用，0表示停用',
  `has_sync_unix` tinyint(4) NULL DEFAULT NULL COMMENT '是否同步到系统用户中，1表示同步，0表示未同步',
  `has_sync_ldap` tinyint(4) NULL DEFAULT NULL COMMENT '是否同步到ldap中，1表示同步，0表示未同步',
  `has_sync_kerberos` tinyint(4) NULL DEFAULT NULL COMMENT '是否同步到lkerberos中，1表示同步，0表示未同步',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

# 创建用户ip表
create table usersync.cluster_ips
(  id   int auto_increment comment '自增主键'primary key,
    host_full_name varchar(50)  null comment '默认从/etc/hosts文件中获取的ip信息，全称',
    host_name      varchar(50)  null comment '默认从/etc/hosts文件中获取的ip信息，简称',
    host_ip        varchar(255) null comment '默认从/etc/hosts文件中获取的ip信息,IP'
);
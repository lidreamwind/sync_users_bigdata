package com.thriteen.bgd.usersync.utils;

/**
 * @Author: Lph
 * @Date: 2020/11/13 14:05
 * @Function:
 * @Version 1.0
 */
public enum ExceptionEnum {

    // ========== 系统级别 ==========
    SUCCESS(0, "成功"),
    SYS_ERROR(1001000, "服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR(1001001, "参数缺失"),
    FAILED(9999999, "失败"),
    // ========== Kerberos ==========
    KERBEROS_CONNECTION_FOUND(20001000, "连接名称已存在"),
    KERBEROS_CONNECTION_INSERT_SUCCESS(20001001, "插入新连接成功"),

    // ========== LDAP ==========
    LDAP_INIT_SUCCESS(3001000, "LDAP初始化成功"),
    LDAP_INIT_EXISTS(3001001, "LDAP连接名称已存在"),
    LDAP_INIT_INSERT(3001002, "LDAP插入成功"),
    LDAP_INIT_FAILED(3009999, "LDAP初始化失败"),
    LDAP_NO_USER(3002001, "LDAP服务没用户"),
    LDAP_NO_URL(3003001, "LDAP连接不存在"),
    // ========== 集群IP==========
    CLUSTER_IP_NOT_EXISTS(4001001, "IP地址不存在,请先录入对应IP信息"),

    // ========== Unix==========

    // ========== 查询==========
    ;
    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误提示
     */
    private final String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

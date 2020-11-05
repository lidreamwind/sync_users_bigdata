# sync_users_bigdata
同步用户，在ldap、kerberos和系统用户之间
首先，确保服务已经进行ssh认证，能够通过ssh访问到其他节点。
否则，需要输入ip、用户名和密码。
1、Kerberos操作使用remote远程到Kerberos服务操作
2、获取unix用户同理remote到远程服务
3、LDAP用户使用spring-boot-ldap


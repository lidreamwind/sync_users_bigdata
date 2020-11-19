package com.thriteen.bgd.usersync.utils.kerberos;

import com.thriteen.bgd.usersync.entity.dto.kerberos.KerberosInitDto;
import com.thriteen.bgd.usersync.utils.remote.CommandRemoteTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/16 18:14
 * @Function:
 * @Version 1.0
 */
public class KerberosUtils {
    private CommandRemoteTools commandRemoteTools = null;
    private KerberosInitDto kerberosInitDto;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public KerberosUtils(KerberosInitDto kerberosInitDto) {
        this.commandRemoteTools = new CommandRemoteTools(kerberosInitDto.getIp(),kerberosInitDto.getPort());
        this.kerberosInitDto = kerberosInitDto;
        boolean root = false;
        if(this.kerberosInitDto.getRemotePasswd() != null) {
            root = this.commandRemoteTools.isAuthenticated(this.kerberosInitDto.getRemoteUser(), this.kerberosInitDto.getRemotePasswd());
        }else if(this.kerberosInitDto.getSshKeyFile()!=null){
            root =this.commandRemoteTools.isAuthenticated(this.kerberosInitDto.getRemoteUser(),new File(this.kerberosInitDto.getSshKeyFile()));
        }else {
            this.logger.error(String.format("conenct to host:%s failed. user:%s or password:%s or sshFile:%s may be not right",
                    this.kerberosInitDto.getIp()),this.kerberosInitDto.getRemoteUser(),this.kerberosInitDto.getRemotePasswd(),this.kerberosInitDto.getSshKeyFile());
        }
    }

    /**
     *  基本查询工作，构建
     * @param hasAdmin  是否包含管理员账户
     * @param command 执行的 Kerberos命令
     * @return
     */
    private List<String> readALlUsers(boolean hasAdmin, String command){
        List<String> users = new ArrayList<>();
        BufferedReader bufferedReader = this.commandRemoteTools.executeCommandOnRemote(command);
        String line = "no";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("Authenticating as principal")){
                    continue;
                }
                if(hasAdmin){
                    users.add(line);
                }else {
                    if(!line.contains("/")){
                        users.add(line);
                    }
                }
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  从Kerberos中读取所有用户c
     * @param hasAdmin 是否包含所有管理员
     * @return 返回列表
     */
    public List<String> readALlUsersFromKerberos(boolean hasAdmin){
        String command = "kadmin.local -q \"listprincs\" ";
        List<String> strings = this.readALlUsers(hasAdmin, command);
        return strings;
    }
    /**
     *  从Kerberos中读取所有用户， 模糊匹配
     * @param hasAdmin 是否包含所有管理员
     * @param user  远程用户和密码
     * @param passwd
     * @param goalUser
     * @return 返回列表
     */
    public List<String> readOneUserFromKerberos(boolean hasAdmin, String user, String passwd, String goalUser){
        String command = String.format("kadmin.local -q \"listprincs\" | grep %s", goalUser);
        List<String> strings = this.readALlUsers(hasAdmin, command);
        return strings;
    }
    /**
     * 创建普通用户
     * @param userName   用户的用户名，登录用户名
     * @param passwd    Kerberos初始化的密码
     * @return
     */
    public boolean createKerberosUser(String userName, String passwd){
        String createUser = String.format("kadmin.local -q \"addprinc -pw %s %s \" ",passwd,userName );
        String generateKeyTab = String.format("kadmin.local -q \"xst -k %s/%s.keytab %s@%s\"", this.kerberosInitDto.getKeyTabPath(),
                userName,userName,this.kerberosInitDto.getKerberosDomain());

        // 创建用户
        BufferedReader bufferedReader = this.commandRemoteTools.executeCommandOnRemote(createUser);
        String createUserJudgeStr = String.format("Principal \"%s@%s\" created.", userName,this.kerberosInitDto.getKerberosDomain());
        boolean resultFromBuffer = this.getResultFromBuffer(bufferedReader, createUserJudgeStr, createUser);
        if(resultFromBuffer) {
            // 生成keytab问价
            this.logger.info(String.format("-----Principal \"%s@%s\" created.", userName,this.kerberosInitDto.getKerberosDomain()));
            BufferedReader bufferedReader1 = this.commandRemoteTools.executeCommandOnRemote(generateKeyTab);
            String generateKeyTabJudgeStr = "encryption type des-cbc-md5 added to keytab WRFILE:";
            boolean resultFromBuffer1 = this.getResultFromBuffer(bufferedReader1, generateKeyTabJudgeStr, generateKeyTab);
            if(resultFromBuffer1){
                this.logger.info(String.format("create %s/%s.keytab from %s@%s successfully.", this.kerberosInitDto.getKeyTabPath(),
                        userName,userName,this.kerberosInitDto.getKerberosDomain()));
                return  true;
            }
        }
        return false;
    }
    /**
     * 创建普通用户
     * @param userName  用户的用户名，登录用户名
     * @param passwd    Kerberos初始化的密码
     * @param adminPrincipal 管理员的principal
     * @return 返回创建是否成功
     */
    public boolean createKerberosAdminUser(String userName, String passwd, String adminPrincipal){
        String createUser = String.format("kadmin.local -q \"addprinc -pw %s %s/%s \" ",passwd,userName,adminPrincipal);
        String generateKeyTab = String.format("kadmin.local -q \"xst -k %s/%s.%s.keytab %s/%s@%s\"", this.kerberosInitDto.getKeyTabPath(),
                userName,adminPrincipal,userName,adminPrincipal,this.kerberosInitDto.getKerberosDomain());
        // 创建用户
        BufferedReader bufferedReader = this.commandRemoteTools.executeCommandOnRemote(createUser);
        String createUserJudgeStr = String.format("Principal \"%s/%s@%s\" created.", userName,adminPrincipal,this.kerberosInitDto.getKerberosDomain());
        boolean resultFromBuffer = this.getResultFromBuffer(bufferedReader, createUserJudgeStr, createUser);
        if(resultFromBuffer) {
            this.logger.info(String.format("-----Principal \"%s/%s@%s\" created.", userName,adminPrincipal,this.kerberosInitDto.getKerberosDomain()));
            // 生成keytab问价
            BufferedReader bufferedReader1 = this.commandRemoteTools.executeCommandOnRemote(generateKeyTab);
            String generateKeyTabJudgeStr = "encryption type des-cbc-md5 added to keytab WRFILE:";
            boolean resultFromBuffer1 = this.getResultFromBuffer(bufferedReader1, generateKeyTabJudgeStr, generateKeyTab);
            if(resultFromBuffer1){
                this.logger.info(String.format("create %s/%s.%s.keytab from %s@%s successfully.", this.kerberosInitDto.getKeyTabPath(),
                        userName,adminPrincipal,userName,adminPrincipal,this.kerberosInitDto.getKerberosDomain()));
                return  true;
            }
        }
        return false;
    }

    /**
     *  判断命令是否成功，判断标准是 judgeStr字符串是否正确
     * @param bufferedReader  传入的bufferedReader
     * @param judgeStr  判断是否成功的字符串
     * @param command  需要判断的命
     * @return
     */
    private boolean getResultFromBuffer(BufferedReader bufferedReader, String judgeStr, String command){
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null){
                if(line.contains(judgeStr)){
                    return true;
                }
            }
        } catch (IOException e) {
            this.logger.error(String.format("-----the command：%s may execute failed.", command));
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除普通用户
     * @param user
     * @return
     */
    public boolean deleteUser(String user){
        String deleteCommand = String.format("echo yes|kadmin.local -q \"delprinc %s@%s\"", user,this.kerberosInitDto.getKerberosDomain());
        BufferedReader bufferedReader = commandRemoteTools.executeCommandOnRemote(deleteCommand);
        String deleteJudge = String.format("Principal \"%s@%s\" deleted", user,this.kerberosInitDto.getKerberosDomain());
        boolean resultFromBuffer = getResultFromBuffer(bufferedReader, deleteJudge, deleteCommand);
        if(resultFromBuffer) {
            this.logger.info(String.format("-----Principal \"%s@%s\" deleted", user,this.kerberosInitDto.getKerberosDomain()));
            return true;
        }
        this.logger.info(String.format("Principal \"%s@%s\" failed to delete ", user,this.kerberosInitDto.getKerberosDomain()));
        return false;
    }

    /**
     *  删除管理员用户
     * @param user
     * @param adminPrincal
     * @return
     */
    public boolean deleteUser(String user, String adminPrincal){
        String deleteCommand = String.format("echo yes|kadmin.local -q \"delprinc %s/%s@%s\"", user,adminPrincal,this.kerberosInitDto.getKerberosDomain());
        BufferedReader bufferedReader = commandRemoteTools.executeCommandOnRemote(deleteCommand);
        String deleteJudge = String.format("Principal \"%s/%s@%s\" deleted", user,adminPrincal,this.kerberosInitDto.getKerberosDomain());
        boolean resultFromBuffer = getResultFromBuffer(bufferedReader, deleteJudge, deleteCommand);
        if(resultFromBuffer) {
            this.logger.info(String.format("-----Principal \"%s/%s@%s\" deleted", user,adminPrincal,this.kerberosInitDto.getKerberosDomain()));
            return true;
        }
        this.logger.info(String.format("-----Principal \"%s/%s@%s\" failed to delete", user,adminPrincal,this.kerberosInitDto.getKerberosDomain()));
        return false;
    }

}

package com.thriteen.bgd.usersync.utils.remote;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.ServerHostKeyVerifier;
import ch.ethz.ssh2.Session;
import com.thriteen.bgd.usersync.utils.ResultFormatTools;
import com.thriteen.bgd.usersync.utils.files.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: Lph
 * @Date: 2020/11/12 9:46
 * @Function:
 * @Version 1.0
 */
public class CommandRemoteTools {
    /**
     *  ssh 链接对象
     */
    private Connection conn = null;
    /**
     * ssh 连接会话
     */
    private Session session = null;
    /**
     * 链接的主机 ip
     */
    private String hostIp;
    /**
     * 链接端口，默认22
     */
    private Integer port;
    /**
     * 链接超时时间，默认500
     */
    private Integer connectTimeOut;
    private Integer kexTimeOut;
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 日志格式化工具
     */
    private ResultFormatTools rst = new ResultFormatTools();
    /**
     * 文件工具
     */
    private FileUtils fileUtils = new FileUtils();
    /**
     * 是否已经进行了认证
     */
    private  boolean isAuth = false;
    private SCPClient client = null;

    /**
     *  construster function
     * @param hostIp ： remote ip
     */
    public CommandRemoteTools(String hostIp, Integer port) {
        this.hostIp = hostIp;
        this.port = port;
        this.connectTimeOut = 500;
        this.kexTimeOut = 500;
    }
    public CommandRemoteTools(String hostIp, Integer port, Integer connectTimeOut, Integer kexTimeOut) {
        this.hostIp = hostIp;
        this.port = port;
        this.connectTimeOut = connectTimeOut;
        this.kexTimeOut = kexTimeOut;
    }

    /**
     *  get connection.
     */
    private boolean getConnection(){
        try {
            this.conn = new Connection(this.hostIp,this.port);
            this.conn.connect((ServerHostKeyVerifier)null, this.connectTimeOut, this.kexTimeOut);
            this.logger.info(rst.logHostFormat("-----Connection on host: %s is established",this.hostIp));
            return true;
        }catch (Exception e) {
            this.logger.error(rst.logHostFormat("-----Connection on host: %s establish failed.",this.hostIp));
            return false;
        }
    }

    /**
     * get remote seesion, for execute command.
     */
    private void getSession(){
        try {
            this.session = this.conn.openSession();
            this.logger.info(rst.logHostFormat("Session on host: %s is established",this.hostIp));
        } catch (IOException e) {
            String logStr = "------------- Session on host: %s is not established  -------------";
            this.logger.error(rst.logHostFormat(logStr,this.hostIp));
            e.printStackTrace();
        }
    }
    public Integer getCommandExecuteStatus(){
        if(this.session == null) {
            String logStr = "------------- Session on host: %s is not established,please connect firstly.";
            this.logger.error(rst.logHostFormat(logStr,this.hostIp));
        }
        Integer exitStatus = this.session.getExitStatus();
        return exitStatus;
    }
    public String getCommandExecuteExitSingal(){
        if(this.session == null) {
            String logStr = "------------- Session on host: %s is not established,please connect firstly.";
            this.logger.error(rst.logHostFormat(logStr,this.hostIp));
        }
        String exitSignal = this.session.getExitSignal();
        return exitSignal;
    }

    /**
     * close the session.
     */
    public void close(){
        if(this.session == null){
            this.logger.warn(rst.logHostFormat("Session on host: %s is not established",this.hostIp));
        }else {
            this.session.close();
            this.logger.info(rst.logHostFormat("Session on host: %s has closed", this.hostIp));
        }
        if(this.conn == null){
            this.logger.warn(rst.logHostFormat("-----Connection on host: %s is not established",this.hostIp));
        }else {
            this.conn.close();
            this.logger.info(rst.logHostFormat("-----Connection on host: %s has closed", this.hostIp));
        }
    }
    /**
     *  ssh免密文件登录认证
     * @param user
     * @param keyFilePath
     * @return
     */
    public boolean isAuthenticated(String user,File keyFilePath){
        if(this.isAuth){
            return this.isAuth;
        }
        boolean connection = getConnection();
        if(connection) {
            try {
                boolean isAuthenticated = this.conn.authenticateWithPublicKey(user, keyFilePath, "");
                this.isAuth = isAuthenticated;
                return isAuthenticated;
            } catch (IOException e) {
                this.logger.error(rst.logHostFormat("Authentication failed, please check the host:%s SSH key file.", this.hostIp));
                e.printStackTrace();
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     *  ssh免密文件登录认证
     * @param user
     * @param password
     * @return
     */
    public boolean isAuthenticated(String user,String password){
        if(this.isAuth){
            return this.isAuth;
        }
        boolean connection = getConnection();
        if(connection) {
            boolean isAh = false;
            try {
                isAh = this.conn.authenticateWithPassword(user, password);
                this.isAuth = isAh;
                return isAh;
            } catch (IOException e) {
                String logStr = "user:%s or password:%s is not crorect,connect to host:%s failed.";
                this.logger.error(rst.logHostFormat(logStr, this.hostIp, user, password));
                e.printStackTrace();
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     *  execute the command for result.
     * @param command  the remote command.
     * @return the command execult result
     */
    public BufferedReader executeCommandOnRemote(String command) {
        BufferedReader bufferedReader = null;
        try {
            getSession();
            this.session.execCommand(command);
            InputStream inputStream = this.session.getStdout();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            return bufferedReader;
        } catch (IOException e) {
            e.printStackTrace();
            return bufferedReader;
        }
    }

    /**
     *  将本地文件拷贝到远程，多个文件
     * @param localFile  本文文件列表
     * @param path
     */
    public boolean putFilesToRemote(String[] localFile, String path){
        if(this.isAuth) {
            if(this.client == null) {
                this.client = new SCPClient(this.conn);
            }
            try {
                this.client.put(localFile, path);
                this.logger.info(String.format("-----copyt files to remote:%s path:%s successfully.", this.hostIp, path));
                return true;
            } catch (IOException e) {
                this.logger.error(String.format("-----remote:%s path:%s failed", this.hostIp, path));
                e.printStackTrace();
                return false;
            }
        }else {
            this.logger.error("------the session has not authenticated.");
            return false;
        }
    }
    /**
     *  将本地文件拷贝到远程，单个个文件
     * @param localFile  本文文件列表
     * @param path
     */
    public boolean putFileToRemote(String localFile, String path){
        if(this.isAuth) {
            if(this.client == null) {
                this.client = new SCPClient(this.conn);
            }
            try {
                this.client.put(localFile, path);
                this.logger.info(String.format("-----copyt files to remote:%s path:%s successfully.", this.hostIp, path));
                return true;
            } catch (IOException e) {
                this.logger.error(String.format("-----remote:%s path:%s failed", this.hostIp, path));
                e.printStackTrace();
                return false;
            }
        }else {
            this.logger.error("------the session has not authenticated.");
            return false;
        }
    }
    /**
     * 从 远程下载文件到本地，多个文件
     * @param remoteFiles
     * @param localPaht
     * @return
     */
    public boolean getFilesFromRemoete(String[] remoteFiles, String localPaht){
        if(this.isAuth) {
            if(this.client == null) {
                this.client = new SCPClient(this.conn);
            }
            try {
                this.client.get(remoteFiles, localPaht);
                this.logger.info(String.format("-----copyt files to remote:%s path:%s successfully.", this.hostIp, localPaht));
                return true;
            } catch (IOException e) {
                this.logger.error(String.format("-----remote:%s path:%s failed", this.hostIp, localPaht));
                e.printStackTrace();
                return false;
            }
        }else {
            this.logger.error("------the session has not authenticated.");
            return false;
        }
    }
    /**
     * 从 远程下载文件到本地，单个文件
     * @param remoteFiles
     * @param localPaht
     * @return
     */
    public boolean getFileFromRemoete(String remoteFiles, String localPaht){
        if(this.isAuth) {
            if(this.client == null) {
                this.client = new SCPClient(this.conn);
            }
            try {
                this.client.get(remoteFiles, localPaht);
                this.logger.info(String.format("-----copyt files to remote:%s path:%s successfully.", this.hostIp, localPaht));
                return true;
            } catch (IOException e) {
                this.logger.error(String.format("-----remote:%s path:%s failed", this.hostIp, localPaht));
                e.printStackTrace();
                return false;
            }
        }else {
            this.logger.error("------the session has not authenticated.");
            return false;
        }
    }

}

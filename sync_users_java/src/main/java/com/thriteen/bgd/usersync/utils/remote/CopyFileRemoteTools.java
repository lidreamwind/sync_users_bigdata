package com.thriteen.bgd.usersync.utils.remote;

import com.thriteen.bgd.usersync.entity.dto.utils.RemoteCopyFileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Author: Lph
 * @Date: 2020/11/17 11:54
 * @Function:
 * @Version 1.0
 */
public class CopyFileRemoteTools {
    /**
     * 构建拷贝的参数，两个服务器之间
     */
    private RemoteCopyFileDto remoteCopyFileDto;
    /**
     * 远程服务
     */
    private CommandRemoteTools fromRemote = null;
    private CommandRemoteTools toRemote = null;
    /**
     * 是否进行了认证
     */
    private boolean isAuFrom = false;
    private boolean isAuTo = false;
    private final  Logger logger = LoggerFactory.getLogger(getClass());

    public CopyFileRemoteTools(RemoteCopyFileDto remoteCopyFileDto) {
        this.remoteCopyFileDto = remoteCopyFileDto;
        // 初始化服务
        if(remoteCopyFileDto.getFromConnectTimeOut() != null && remoteCopyFileDto.getFromKexTimeOut() != null) {
            fromRemote = new CommandRemoteTools(remoteCopyFileDto.getFromRemoteHostIp(), remoteCopyFileDto.getFromRemotePort(),
                    remoteCopyFileDto.getFromConnectTimeOut(),remoteCopyFileDto.getFromKexTimeOut());
            toRemote = new CommandRemoteTools(remoteCopyFileDto.getToRemoteHostIp(), remoteCopyFileDto.getToRemotePort());
        }else {
            fromRemote = new CommandRemoteTools(remoteCopyFileDto.getFromRemoteHostIp(), remoteCopyFileDto.getFromRemotePort());
        }
        if(remoteCopyFileDto.getToConnectTimeOut() != null && remoteCopyFileDto.getToKexTimeOut() != null) {
            toRemote = new CommandRemoteTools(remoteCopyFileDto.getToRemoteHostIp(), remoteCopyFileDto.getToRemotePort(),
                    remoteCopyFileDto.getToConnectTimeOut(),remoteCopyFileDto.getToKexTimeOut());
        }else {
            toRemote = new CommandRemoteTools(remoteCopyFileDto.getToRemoteHostIp(), remoteCopyFileDto.getToRemotePort());
        }
        //初始化认证
        if(remoteCopyFileDto.getFromRemotePasswd() != null){
            boolean authenticated = this.fromRemote.isAuthenticated(remoteCopyFileDto.getFromRemoteUser(), remoteCopyFileDto.getFromRemotePasswd());
            this.isAuFrom = authenticated;
        }
        if(!this.isAuFrom && remoteCopyFileDto.getFromRemoeteSShFile() != null){
            boolean authenticated = this.fromRemote.isAuthenticated(remoteCopyFileDto.getFromRemoteUser(), new File(remoteCopyFileDto.getFromRemoeteSShFile()));
            this.isAuFrom = authenticated;
        }
        if(!this.isAuFrom){
            this.logger.error("please confirm the Remoete Host variable info in class: RemoteCopyFileDto");
        }
        if(remoteCopyFileDto.getToRemotePasswd() != null){
            boolean authenticated = this.toRemote.isAuthenticated(remoteCopyFileDto.getToRemoteUser(), remoteCopyFileDto.getToRemotePasswd());
            this.isAuTo = authenticated;
        }
        if(!this.isAuTo && remoteCopyFileDto.getToRemoeteSShFile() != null){
            boolean authenticated = this.toRemote.isAuthenticated(remoteCopyFileDto.getToRemoteUser(), new File(remoteCopyFileDto.getToRemoeteSShFile()));
            this.isAuTo = authenticated;
        }
        if(!this.isAuTo){
            this.logger.error("please confirm the Remoete Host variable info in class: RemoteCopyFileDto");
        }
    }

    public boolean copyFiles(){
        boolean filesFromRemoete = this.fromRemote.getFilesFromRemoete(this.remoteCopyFileDto.getFromRemotefiles(), this.remoteCopyFileDto.getTempDir());
        if(filesFromRemoete){
            String[] localFiles = new String[2];
            boolean b = this.toRemote.putFilesToRemote(localFiles, this.remoteCopyFileDto.getToRemotePath());
            if(b){
                this.logger.info(String.format("copy file from host:%s to host:%s path:%s successfully.",this.remoteCopyFileDto.getFromRemoteHostIp(),
                        this.remoteCopyFileDto.getToRemoteHostIp(),this.remoteCopyFileDto.getToRemotePath()));
                return true;
            }
        }
        return false;
    }
    public void close(){
        this.fromRemote.close();
        this.toRemote.close();
    }
}

package com.thriteen.bgd.usersync.remote;

import ch.ethz.ssh2.Connection;
import com.thriteen.bgd.usersync.utils.remote.CommandRemoteTools;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Lph
 * @Date: 2020/11/12 11:08
 * @Function:
 * @Version 1.0
 */
public class CommandRemoteToolsTest {
    @Test
    public void testRemoteCommandUP() throws IOException {
        CommandRemoteTools commandRemote = new CommandRemoteTools("192.168.233.619",22);
        if(commandRemote.isAuthenticated("root","1")) {
            BufferedReader bufferedReader = commandRemote.executeCommandOnRemote("ls -al /");
            String line = "no";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            commandRemote.close();
        }
    }
    @Test
    public void testRemoteCommandSSH() throws IOException {
        CommandRemoteTools commandRemote = new CommandRemoteTools("192.168.233.67",22);
        if(commandRemote.isAuthenticated("root",new File("testFile/id_rsa"))) {
            BufferedReader bufferedReader = commandRemote.executeCommandOnRemote("ls -al /");
            String line = "no";
            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            commandRemote.close();
        }
    }
}

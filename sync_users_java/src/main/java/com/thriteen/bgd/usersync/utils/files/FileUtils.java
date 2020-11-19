package com.thriteen.bgd.usersync.utils.files;

import java.io.File;

/**
 * @Author: Lph
 * @Date: 2020/11/12 21:44
 * @Function:
 * @Version 1.0
 */
public class FileUtils {
    public boolean fileIfExists(String path){
        File file = new File(path);
        if(file.exists()){
            return true;
        }
        return false;
    }
}

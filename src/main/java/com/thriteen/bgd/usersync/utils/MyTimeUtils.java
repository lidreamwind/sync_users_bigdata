package com.thriteen.bgd.usersync.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Lph
 * @Date: 2020/11/13 9:42
 * @Function:
 * @Version 1.0
 */
public class MyTimeUtils {
    public Date getNowTimeYMD(){
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
}

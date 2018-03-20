package org.kevin.OwnBlog;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Kevin.Z on 2018/3/1.
 */
public class Utils {
    public static Date getGTM8(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        return new Date();
    }

    public static String replaceLineCharacter(String text){
        return text.replaceAll("\\r\\n","<br/>");
    }
}

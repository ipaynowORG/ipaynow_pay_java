package cn.ipaynow.ipaynow_pay_demo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ipaynow1130 on 2017/10/13.
 */
public class CommonUtil {

    public static Map form2Map(String s) {
        Map<String,String> result = new HashMap();
        for(String tmp : s.split("&")){
            result.put(tmp.split("=")[0],tmp.split("=")[1]);
        }
        return result;
    }
}

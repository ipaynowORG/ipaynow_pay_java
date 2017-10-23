package cn.ipaynow.util;

/**
 * Created by ipaynow0929 on 2017/10/13.
 */
public class StringUtil {

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
}

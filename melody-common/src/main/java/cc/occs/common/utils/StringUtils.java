package cc.occs.common.utils;

/**
 * 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean objectIsNull(Object obj) {
        return obj == null;
    }

    /**
     * 去除字符串空格
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

}

package com.hui.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 正则工具类
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RegUtils
{
    /**
     * 判断是否是手机号码
     * <功能详细描述>
     * @param mobiles
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isPhone(String phoneStr)
    {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneStr);
        return m.matches();
    }
    
}

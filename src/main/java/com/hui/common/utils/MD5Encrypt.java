package com.hui.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MD5Encrypt
{
    private static final String MD5_PREFIX = "";
    
    private static final ThreadLocal<MD5Encrypt> local = new ThreadLocal<MD5Encrypt>();
    
    private MD5Encrypt()
    {
        super();
    }
    
    public static MD5Encrypt getEncrypt()
    {
        MD5Encrypt encrypt = local.get();
        if (encrypt == null)
        {
            encrypt = new MD5Encrypt();
            local.set(encrypt);
        }
        return encrypt;
    }
    
    public static String encode(String s)
    {
        if (s == null)
        {
            return null;
        }
        return DigestUtils.md5Hex(MD5_PREFIX + s);
    }
    
    public static void main(String[] args)
    {
        String origStr = "12341234";
        System.out.println("加密后为： " + encode(origStr));
    }
    
}
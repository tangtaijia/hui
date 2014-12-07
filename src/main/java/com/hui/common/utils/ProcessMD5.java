package com.hui.common.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import org.apache.commons.io.IOUtils;

public class ProcessMD5
{
    
    /**
     * 算法名称
     */
    private final static String RSA = "RSA";
    
    /**
     * 加密后的字节分隔长度
     */
    private final static int encryptSepLength = 256;
    
    /**
     * 明文字节分隔长度
     */
    private final static int plainSepLneght = 100;
    
    private static byte[] encrypt(byte[] text, PublicKey pubRSA)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubRSA);
        return cipher.doFinal(text);
    }
    
    public final static String encrypt(String text, PublicKey uk)
    {
        StringBuffer sbf = new StringBuffer(200);
        try
        {
            // 用这个的原因是为了支持汉字、汉字和英文混排,解密方法中同理
            text = URLEncoder.encode(text, "UTF-8");
            byte[] plainByte = text.getBytes();
            ByteArrayInputStream bays = new ByteArrayInputStream(plainByte);
            byte[] readByte = new byte[plainSepLneght];
            int n = 0;
            while ((n = bays.read(readByte)) > 0)
            {
                if (n >= plainSepLneght)
                {
                    sbf.append(byte2hex(encrypt(readByte, uk)));
                }
                else
                {
                    byte[] tt = new byte[n];
                    for (int i = 0; i < n; i++)
                    {
                        tt[i] = readByte[i];
                    }
                    sbf.append(byte2hex(encrypt(tt, uk)));
                }
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sbf.toString();
    }
    
    public final static String decrypt(String data, PrivateKey rk)
    {
        String rrr = "";
        StringBuffer sb = new StringBuffer(100);
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
            byte[] readByte = new byte[256];
            int n = 0;
            while ((n = bais.read(readByte)) > 0)
            {
                if (n >= encryptSepLength)
                {
                    sb.append(new String(decrypt(hex2byte(readByte), rk)));
                }
            }
            rrr = URLDecoder.decode(sb.toString(), "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rrr;
    }
    
    private static byte[] decrypt(byte[] src, PrivateKey rk)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, rk);
        return cipher.doFinal(src);
    }
    
    public static String byte2hex(byte[] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++)
        {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs += ("0" + stmp);
            else
                hs += stmp;
        }
        return hs.toUpperCase();
    }
    
    public static byte[] hex2byte(byte[] b)
    {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("");
        
        byte[] b2 = new byte[b.length / 2];
        
        for (int n = 0; n < b.length; n += 2)
        {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte)Integer.parseInt(item, 16);
        }
        return b2;
    }
    
    public static PrivateKey getPrivateKey()
    {
        InputStream f = null;
        ObjectInputStream b = null;
        try
        {
            ClassLoader classLoader = ProcessMD5.class.getClassLoader();
            f = null;
            if (null != classLoader)
            {
                f = classLoader.getResourceAsStream("resources/Skey_RSA_priv.dat");
            }
            else
            {
                f = ClassLoader.getSystemResourceAsStream("resources/Skey_RSA_priv.dat");
            }
            b = new ObjectInputStream(f);
            RSAPrivateKey prk = (RSAPrivateKey)b.readObject();
            return prk;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(f);
            IOUtils.closeQuietly(b);
        }
        return null;
    }
    
    public static PublicKey getPublicKey()
    {
        InputStream f = null;
        ObjectInputStream b = null;
        try
        
        {
            ClassLoader classLoader = ProcessMD5.class.getClassLoader();
            
            if (null != classLoader)
            {
                f = classLoader.getResourceAsStream("resources/Skey_RSA_pub.dat");
            }
            else
            {
                f = ClassLoader.getSystemResourceAsStream("resources/Skey_RSA_pub.dat");
            }
            b = new ObjectInputStream(f);
            RSAPublicKey pubk = (RSAPublicKey)b.readObject();
            return pubk;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(f);
            IOUtils.closeQuietly(b);
        }
        return null;
    }
    
    public String getpwd(String despwd)
    {
        return decrypt(despwd, getPrivateKey());
    }
    
    public String getusername(String desusername)
    {
        return decrypt(desusername, getPrivateKey());
    }
    
    public static void main(String args[])
    {
        try
        {
            String plaintext = "wxmgr";
//            String plaintext = "root";
            String cipherText = encrypt(plaintext, getPublicKey());
            System.out.println("cipherText:" + cipherText);
            String plainText = decrypt(cipherText, getPrivateKey());
            System.out.println("plainText:" + plainText);
            
            plaintext = "E4RFA6kv";
//            plaintext = "taijia";
            cipherText = encrypt(plaintext, getPublicKey());
            System.out.println("cipherText:" + cipherText);
            plainText = decrypt(cipherText, getPrivateKey());
            System.out.println("plainText:" + plainText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
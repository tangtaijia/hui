package com.hui.common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.cache.SysCacheManager;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.SysConfig;
import com.hui.common.entity.SysData;

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
public class CommonUtils
{
    
    /**
     * 首页URL
     */
    public static String URL_INDEX = "index.jsp";
    
    /**
     * 个人信息URL
     */
    public static String URL_USER_INFO = "uc/executeUCenter.hui";
    
    /**
     * 日志级别
     */
    public enum LogLevel
    {
        INFO, ERROR
    }
    
    /**
     * 缓存命名前缀
     */
    public static String KEYPREFIX = "hui";
    
    /**
     * 默认缓存刷新周期(单位为秒)
     */
    public static int DEFAULT_REFRESHPERIOD = 3600;
    
    /**
     * 系统缓存
     */
    public static String SYSCACHE_CACHE_CODE = "sysCache_cache";
    
    /**
     * 首页缓存
     */
    public static String INDEX_CACHE_CODE = "index_cache";
    
    /**
     * 系统配置缓存
     */
    public static String SYSCONFIG_CACHE_CODE = "sysConfig_cache";
    
    /**
     * 基础数据缓存
     */
    public static String SYSDATA_CACHE_CODE = "sysData_cache";
    
    /**
     * 管理员权限缓存
     */
    public static String ADMIN_AUTHS_CACHE_CODE = "admin_auths_cache";
    
    /**
     * 会员权限缓存
     */
    public static String USER_AUTHS_CACHE_CODE = "user_auths_cache";
    
    /**
     * 管理员操作权限缓存
     */
    public static String ADMIN_OPT_AUTHS_CACHE_CODE = "admin_opt_auths_cache";
    
    /**
     * 非法词缓存
     */
    public static String INV_KEYWORDS_CACHE_CODE = "inv_keywords_cache";
    
    /**
     * 首页缓存是否刷新
     */
    private static boolean indexRefresh = false;
    
    /**
     * StringBuffer初始容量
     */
    public static int STRING_BUFFER_INIT = 1024;
    
    //    private static ResourceBundle sysSetting = ResourceBundle.getBundle("system");
    
    /**
     * 首页缓存是否刷新
     */
    public static boolean isIndexRefresh()
    {
        return indexRefresh;
    }
    
    /**
     * 首页缓存是否刷新
     */
    public static void setIndexRefresh(boolean indexRefresh)
    {
        CommonUtils.indexRefresh = indexRefresh;
    }
    
    /**
     * 网站标题
     */
    public static String getTitle()
    {
        return getSysConfig("webtitle").getConfigValue();
    }
    
    /**
     * 网站关键字
     */
    public static String getKeywords()
    {
        return getSysConfig("webkeywords").getConfigValue();
    }
    
    /**
     * 网站描述
     */
    public static String getDescription()
    {
        return getSysConfig("webdescription").getConfigValue();
    }
    
    /**
     * 流量统计
     */
    public static String getTrafficstatistics()
    {
        return getSysConfig("traffic_statistics").getConfigValue();
    }
    
    /**
     * 日期格式化
     */
    public static String getLongDateFormat()
    {
        return getSysConfig("long_date_format").getConfigValue();
    }
    
    /**
     * 日期格式化
     */
    public static String getShortDateFormat()
    {
        return getSysConfig("short_date_format").getConfigValue();
    }
    
    /**
     * 生成文件存储路径
     */
    public static String getFilePath()
    {
        String filePath = getSysConfig("file_path").getConfigValue();
        if (!new File(filePath).exists())
        {
            filePath = "D:/hui_files";
        }
        return filePath;
    }
    
    /**
     * 生成单个文件最大容量(单位为MB)
     */
    public static int getFileMaxSize()
    {
        return Integer.parseInt(getSysConfig("file_max_size").getConfigValue());
    }
    
    /**
     * 前台登录验证码
     */
    public static int getFLValicode()
    {
        return Integer.parseInt(getSysConfig("front_login_valicode").getConfigValue());
    }
    
    /**
     * 前台注册验证码
     */
    public static int getFRValicode()
    {
        return Integer.parseInt(getSysConfig("front_reg_valicode").getConfigValue());
    }
    
    /**
     * 后台登录验证码
     */
    public static int getBLValicode()
    {
        return Integer.parseInt(getSysConfig("back_login_valicode").getConfigValue());
    }
    
    /**
     * 后台菜单最大打开数量
     */
    public static int getBMCount()
    {
        return Integer.parseInt(getSysConfig("back_menu_count").getConfigValue());
    }
    
    /**
     * 文章上传图片最大数量
     */
    public static int getArticleImgCount()
    {
        return Integer.parseInt(getSysConfig("article_img_count").getConfigValue());
    }
    
    /**
     * 记录日志
     */
    public static int getLogable()
    {
        return Integer.parseInt(getSysConfig("logable").getConfigValue());
    }
    
    /**
     * 日志记录方式
     */
    public static int getLoggerType()
    {
        return Integer.parseInt(getSysConfig("logger_type").getConfigValue());
    }
    
    /**
     * 记录方法异常日志
     */
    public static int getErrorLog()
    {
        return Integer.parseInt(getSysConfig("error_log").getConfigValue());
    }
    
    /**
     * 记录登录登出日志
     */
    public static int getLoginLog()
    {
        return Integer.parseInt(getSysConfig("login_log").getConfigValue());
    }
    
    /**
     * 记录系统配置日志
     */
    public static int getSysCfgLog()
    {
        return Integer.parseInt(getSysConfig("syscfg_log").getConfigValue());
    }
    
    /**
     * 记录定时任务日志
     */
    public static int getJobLog()
    {
        return Integer.parseInt(getSysConfig("job_log").getConfigValue());
    }
    
    /**
     * 定期备份数据
     */
    public static int getBackup()
    {
        return Integer.parseInt(getSysConfig("backup").getConfigValue());
    }
    
    /**
     * 数据备份类型
     */
    public static int getBackupForm()
    {
        return Integer.parseInt(getSysConfig("backup_form").getConfigValue());
    }
    
    /**
     * 手动备份数据
     */
    public static int getBackupHand()
    {
        return Integer.parseInt(getSysConfig("backup_hand").getConfigValue());
    }
    
    /**
     * 数据备份周期
     */
    public static int getBackupPeriod()
    {
        return Integer.parseInt(getSysConfig("backup_period").getConfigValue());
    }
    
    /**
     * 增量数据备份记录数
     */
    public static int getIncreBackupItems()
    {
        return Integer.parseInt(getSysConfig("incre_backup_items").getConfigValue());
    }
    
    /**
     * 下次备份数据日期
     */
    public static Integer getNextBackupDate()
    {
        try
        {
            String temp = getSysConfig("next_backup_date").getConfigValue();
            if (StringUtils.isBlank(temp))
            {
                return null;
            }
            return Integer.parseInt(temp);
        }
        catch (Exception e)
        {
            
        }
        return null;
    }
    
    /**
     * 获取文件路径前缀
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getFilePathPrefix()
    {
        return getSysConfig("file_path_prefix").getConfigValue();
    }
    
    /**
     * 系统配置表数据
     */
    public static Map<String, SysConfig> getSysConfigs()
    {
        SysCacheManager cm = SysCacheManager.getInstance();
        return cm.getSysConfigs();
    }
    
    /**
     * 系统配置表数据
     */
    public static SysConfig getSysConfig(String configCode)
    {
        Map<String, SysConfig> sysConfigs = getSysConfigs();
        return sysConfigs.get(configCode);
    }
    
    /**
     * 系统数据表数据
     */
    public static Map<String, SysData> getSysDatas()
    {
        SysCacheManager cm = SysCacheManager.getInstance();
        return cm.getSysDatas();
    }
    
    /**
     * 系统数据表数据
     */
    public static SysData getSysData(String dataCode)
    {
        Map<String, SysData> sysDatas = getSysDatas();
        return sysDatas.get(dataCode);
    }
    
    /**
     * 系统数据表数据
     */
    public static List<String[]> getSysDataArray(String dataCode)
    {
        List<String[]> array = new ArrayList<String[]>();
        try
        {
            Map<String, Object> map = getSysData(dataCode).getValue();
            Iterator<String> keys = map.keySet().iterator();
            String[] row = null;
            String key = null;
            
            while (keys.hasNext())
            {
                key = keys.next();
                row = new String[] {key, (String)map.get(key)};
                array.add(row);
            }
        }
        catch (Exception e)
        {
            
        }
        return array;
    }
    
    /**
     * 缓存刷新周期
     */
    public static int getRefreshPeriod(String cacheCode)
    {
        SysCacheManager cm = SysCacheManager.getInstance();
        int refreshPeriod = cm.getSysCaches().get(cacheCode).getRefreshPeriod();
        if (0 == refreshPeriod)
        {
            return DEFAULT_REFRESHPERIOD;
        }
        return refreshPeriod;
    }
    
    /**
     * 返回当前时间
     */
    public static String getFormatCurTime(String format)
    {
        try
        {
            DateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(System.currentTimeMillis());
        }
        catch (Exception e)
        {
            return "";
        }
    }
    
    /**
     * 返回当前时间
     */
    public static Integer getCurTime()
    {
        try
        {
            return Integer.parseInt(Long.toString(System.currentTimeMillis() / 1000));
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    
    /**
     * 返回格式化的时间戳
     */
    public static String formatTimeStamp(Integer time, String format)
    {
        try
        {
            DateFormat sdf = new SimpleDateFormat(format);
            Timestamp timestamp = new Timestamp(Long.valueOf(time + "000"));
            return sdf.format(timestamp);
        }
        catch (Exception e)
        {
            return "";
        }
    }
    
    /**
     * 返回时间戳
     */
    public static Integer getTimeStamp(String time)
    {
        try
        {
            if (StringUtils.isNotBlank(time) && time.indexOf(':') < 0)
            {
                time += " 00:00:00";
            }
            Timestamp timestamp = Timestamp.valueOf(time);
            return Integer.parseInt(Long.toString(timestamp.getTime() / 1000));
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    
    public static Integer getDateInt(String s)
    {
        //    	String s = "20120824";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");//参数为你要格式化时间日期的模式
        Date date = new Date();
        try
        {
            date = df.parse(s);
            return (int)(date.getTime() / 1000);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }//将字符串按照定义的模式转换为Date对象
        return 0;
    }
    
    public static String getIntDate(Integer i)
    {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");//参数为你要格式化时间日期的模式
        String dateStr = df.format(new Date(i * 1000l));
        return dateStr;
    }
    
    /**
     * 获取客户端IP地址
     */
    public static String getRemoteIP(HttpServletRequest request)
    {
        String forwarded = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(forwarded) || "unknown".equalsIgnoreCase(forwarded))
        {
            return request.getRemoteAddr();
        }
        return forwarded.split(",")[0];
    }
    
    /**
     * 判断字符串是否是整数
     * 
     * @author 汤太佳
     * @since Aug 2, 2013
     * @param value
     * @return
     */
    public static boolean isInteger(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    
    /**
     * 判断字符串是否是浮点数
     * 
     * @author 汤太佳
     * @since Aug 2, 2013
     * @param value
     * @return
     */
    public static boolean isDouble(String value)
    {
        try
        {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    
    /**
     * 判断字符串是否是数字
     * 
     * @author 汤太佳
     * @since Aug 2, 2013
     * @param value
     * @return
     */
    public static boolean isNumber(String value)
    {
        return isInteger(value) || isDouble(value);
    }
    
    /**
     * 判断是否为空
     * 
     * @author 汤太佳
     * @since Jul 22, 2013
     * @param o
     * @return
     */
    public static boolean isEmptyOrNull(Object o)
    {
        if (null == o)
        {
            return true;
        }
        else if ("".equals(o.toString().trim()) || "null".equalsIgnoreCase(o.toString()))
        {
            return true;
        }
        else if (o.getClass().isArray() && Array.getLength(o) == 0)
        {
            return true;
        }
        else if (o instanceof Collection<?> && ((Collection<?>)o).size() == 0)
        {
            return true;
        }
        else if (o instanceof Map<?, ?> && ((Map<?, ?>)o).isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 判断是否为空或为零或为false
     * 
     * @author 汤太佳
     * @since Jul 22, 2013
     * @param o
     * @return
     */
    public static boolean isEmptyOrNullOr0OrFalse(Object o)
    {
        if (isEmptyOrNull(o))
        {
            return true;
        }
        else if (o.toString().equals("0"))
        {
            return true;
        }
        else if (o.toString().equals("false"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 判断不为空
     * @author tang_taijia
     * @since 2014-2-24
     * @param o
     * @return
     */
    public static boolean isNotEmptyOrNull(Object o)
    {
        return !isEmptyOrNull(o);
    }
    
    /**
     * 判断不为空，0和false
     * @author tang_taijia
     * @since 2014-2-24
     * @param o
     * @return
     */
    public static boolean isNotEmptyOrNullOr0OrFalse(Object o)
    {
        return !isEmptyOrNullOr0OrFalse(o);
    }
    
    /**
     * 把对象转化为字符串(在Object.toString()基础上处理null)
     * @author tang_taijia
     * @since 2014-2-26
     * @param o
     * @return
     */
    public static String toString(Object o)
    {
        if (isEmptyOrNull(o))
        {
            return null;
        }
        else
        {
            return String.valueOf(o);
        }
    }
    
    /**
     * 通过set来打乱list的顺序，不过会会导致重复数据丢失
     * 
     * @author 汤太佳
     * @since Sep 2, 2013
     * @param list
     * @return
     */
    public static List<Object> outOrderListBySet(List<Object> list)
    {
        Set<Object> set = new HashSet<Object>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
    
    /**
     * 通过map来打乱list的顺序，不过会会导致重复数据丢失
     * 
     * @author 汤太佳
     * @since Sep 2, 2013
     * @param list
     * @return
     */
    public static List<Object> outOrderListByMap(List<Object> list)
    {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listMapNew = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("obj", list.get(i));
            map.put("isInOrderList", "false");
            listMap.add(map);
        }
        
        Random r = new Random();
        while (listMap.size() > 0)
        {
            int index = r.nextInt(listMap.size());
            Map<String, Object> tempMap = listMap.get(index);
            if (tempMap.get("isInOrderList").equals("false"))
            {
                tempMap.put("isInOrderList", "true");
                listMapNew.add(tempMap);
                listMap.remove(index);
            }
        }
        
        List<Object> listNew = new ArrayList<Object>();
        for (int i = 0; i < listMapNew.size(); i++)
        {
            listNew.add(listMapNew.get(i).get("obj"));
        }
        
        return listNew;
        
    }
    
    /**
     * 阿拉伯数字转换为汉字
     * 
     * @author 汤太佳
     * @since Sep 5, 2013
     * @param num
     * @return
     */
    public static String convertNum2Charactor(String num)
    {
        if (isEmptyOrNull(num))
        {
            return null;
        }
        if (num.equals("0"))
        {
            return "零";
        }
        if (num.equals("1"))
        {
            return "一";
        }
        if (num.equals("2"))
        {
            return "二";
        }
        if (num.equals("3"))
        {
            return "三";
        }
        if (num.equals("4"))
        {
            return "四";
        }
        if (num.equals("5"))
        {
            return "五";
        }
        if (num.equals("6"))
        {
            return "六";
        }
        if (num.equals("7"))
        {
            return "七";
        }
        if (num.equals("8"))
        {
            return "八";
        }
        if (num.equals("9"))
        {
            return "九";
        }
        return "NAN";
    }
    
    /**
     * 判断map里面的元素有没有空值
     * @author 汤太佳
     * @since Jan 4, 2014
     * @param map
     * @return
     */
    public static boolean containsEmptyValue(Map<?, ?> map)
    {
        return (map.containsValue(null) || map.containsValue(""));
    }
    
    /**
     * 判断map里面的元素有没有空值或0
     * @author 汤太佳
     * @since Jan 4, 2014
     * @param map
     * @return
     */
    public static boolean containsEmptyOr0Value(Map<?, ?> map)
    {
        return (containsEmptyValue(map) || map.containsValue(0));
    }
    
    /**
     * 判断map里面所有元素都不为空值
     * @author 汤太佳
     * @since Jan 4, 2014
     * @param map
     * @return
     */
    public static boolean notContainsEmptyValue(Map<?, ?> map)
    {
        return !containsEmptyValue(map);
    }
    
    /**
     * 判断map里面所有元素都不为空值或0
     * @author 汤太佳
     * @since Jan 4, 2014
     * @param map
     * @return
     */
    public static boolean notContainsEmptyOr0Value(Map<?, ?> map)
    {
        return !containsEmptyOr0Value(map);
    }
    
    /**
     * 转码
     * @author tang_taijia
     * @since 2014-3-12
     * @param str
     * @return
     */
    public static String toUTF_8(String str)
    {
        if (str == null)
            return null;
        String retStr = str;
        byte b[];
        try
        {
            b = str.getBytes("ISO8859_1");
            for (int i = 0; i < b.length; i++)
            {
                byte b1 = b[i];
                if (b1 == 63)
                    break; // 1
                else if (b1 > 0)
                    continue;// 2
                else if (b1 < 0)
                { // 不可能为0，0为字符串结束符
                  // 小于0乱码
                    retStr = new String(b, "UTF-8");
                    break;
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return retStr;
    }
    
    /**
     * 生成相应位数的随机数（字母数字组合 1-20位）
     * @author tang_taijia
     * @since 2014-4-12
     * @param scale
     * @return
     */
    public static String randomByUUID(int scale)
    {
        String randomStr = UUID.randomUUID().toString();
        randomStr = randomStr.replaceAll("-", "");
        if (scale > 0 && scale <= 20)
        {
            return randomStr.toUpperCase().substring(0, scale);
        }
        else
        {
            return "invalid params";
        }
    }
    
    /**
     * 生成相应位数的随机数（数字 1-15位）
     * @author tang_taijia
     * @since 2014-4-12
     * @param scale
     * @return
     */
    public static String randomInt(int scale)
    {
        String randomStr = UUID.randomUUID().toString();
        randomStr = randomStr.replaceAll("-", "").replaceAll("[a-zA-Z]+", "");
        if (scale > 0 && scale <= 15)
        {
            return randomStr.toUpperCase().substring(0, scale);
        }
        else
        {
            return "invalid params";
        }
    }
    
    /**
     * 返回日期型
     * @param timeStamp
     * @return
     */
    public static Date formatTimeStamp2Date(Integer timeStamp)
    {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(Long.valueOf(timeStamp + "000"));
        Date dt = new Date(timestamp.getTime());
        return dt;
    }
    
    public static String formatDate2Str(Date dt)
    {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dt);
    }
    
    /**
     * 返回时间差文字说明
     * @param beginDate
     * @param endDate
     * @return
     */
    public static String convertDateMarginString(Date beginDate, Date endDate)
    {
        //秒
        long dayMargin = (beginDate.getTime() - endDate.getTime()) / 1000;
        String dayMarginContent = "";
        if (dayMargin < 60)
        {
            dayMarginContent = dayMargin + "秒前";
        }
        else if (dayMargin >= 60 && dayMargin < 60 * 60)
        {
            dayMarginContent = (dayMargin / 60) + "分钟前";
        }
        else if (dayMargin >= 60 * 60 && dayMargin < 60 * 60 * 24)
        {
            dayMarginContent = (dayMargin / 60 / 60) + "小时前";
        }
        else if (dayMargin >= 60 * 60 * 24)
        {
            dayMarginContent = formatDate2Str(endDate);
        }
        return dayMarginContent;
    }
    
    /**
     * 根据当前日期获取上周的周一和周日
     * @return
     */
    public static Map<String, String> getLastWeekTime()
    {
        Map<String, String> map = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String lastMonday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        cal.add(Calendar.DATE, 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String lastSunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        map.put("lastMonday", lastMonday + " 00:00:00");
        map.put("lastSunday", lastSunday + " 23:59:59");
        return map;
    }
    
    /**
     * 获得当前时间的时间戳
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Integer getNowInt()
    {
        Long nowInt = System.currentTimeMillis();
        return Integer.valueOf(nowInt.toString().substring(0, 10));
    }
    
    /**
     * 
     * @param length 生成字符串的长度  
     * @return
     */
    public static String getRandomString(int length)
    {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    private static List<String> invalidWords()
    {
        List<String> invalidWords = new ArrayList<String>();
        invalidWords.add("反共");
        invalidWords.add("反党");
        invalidWords.add("法轮功");
        invalidWords.add("天安门事件");
        invalidWords.add("89学潮");
        invalidWords.add("八九学潮");
        invalidWords.add("64风波");
        invalidWords.add("六四风波");
        invalidWords.add("操你妈");
        invalidWords.add("去你妈");
        invalidWords.add("草泥马");
        invalidWords.add("滚蛋");
        invalidWords.add("我操");
        invalidWords.add("我草");
        invalidWords.add("卧槽");
        invalidWords.add("我日");
        invalidWords.add("日你妈");
        invalidWords.add("你妈逼");
        invalidWords.add("呆逼");
        invalidWords.add("fuck");
        invalidWords.add("shit");
        return invalidWords;
    }
    
    /**
     * 检查是否含有敏感字
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean containsValidateWords(String str)
    {
        if (isNotEmptyOrNull(str))
        {
            str = str.replaceAll("\\s*", "");
        }
        for (String word : invalidWords())
        {
            if (isNotEmptyOrNull(word) && isNotEmptyOrNull(str) && str.indexOf(word) > -1)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 查询所有非法词
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<InvKeywords> getInvKeywordss()
    {
        List<InvKeywords> invKeywordss = SysCacheManager.getInstance().getInvKeywords();
        return invKeywordss;
    }
    
    /**
     * 跨站点脚本编制，SQL 盲注，通过框架钓鱼，链接注入（便于跨站请求伪造）
     * <功能详细描述>
     * @param value
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String filterDangerString(String value)
    {
        if (isEmptyOrNull(value))
        {
            return "";
        }
        value = value.toLowerCase();
        //		value = value.replaceAll("\\|", "");
        value = value.replaceAll("&", "&amp;");
        value = value.replaceAll(";", "");
        //		value = value.replaceAll("@", "");
        value = value.replaceAll("'", "");
        value = value.replaceAll("\"", "");
        value = value.replaceAll("\\'", "");
        value = value.replaceAll("\\\"", "");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        //		value = value.replaceAll("\\(", "");
        //		value = value.replaceAll("\\)", "");
        //		value = value.replaceAll("\\+", "");
        value = value.replaceAll("\r", "");
        value = value.replaceAll("\n", "");
        value = value.replaceAll("script", " script");
        value = value.replaceAll("document", " document ");
        value = value.replaceAll("window", " window ");
        value = value.replaceAll("alert", " alert ");
        value = value.replaceAll("iframe", " iframe ");
        value = value.replaceAll("frame", " frame ");
        value = value.replaceAll("html", "  html ");
        value = value.replaceAll("src", "sr c");
        value = value.replaceAll("select", "selec t");
        value = value.replaceAll("update", "updat e");
        value = value.replaceAll("delete", "delet e");
        value = value.replaceAll("insert", "inter t");
        value = value.replaceAll("truncate", "truncat e");
        //		value = value.replaceAll("join", "");
        //		value = value.replaceAll("union", "");
        //		value = value.replaceAll("exec", "");
        //		value = value.replaceAll("drop", "");
        //		value = value.replaceAll("count", "");
        value = value.replaceAll("%27", "");
        value = value.replaceAll("%22", "");
        value = value.replaceAll("%3E", "");
        value = value.replaceAll("%3C", "");
        value = value.replaceAll("%3D", "");
        value = value.replaceAll("%2F", "");
        return value;
    }
    
    /**
     * 跨站点脚本编制，SQL 盲注，通过框架钓鱼，链接注入（便于跨站请求伪造）——优雅版
     * <功能详细描述>
     * @param value
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String filterDangerStringLt(String value)
    {
        if (isEmptyOrNull(value))
        {
            return "";
        }
        value = value.toLowerCase();
        //		value = value.replaceAll("\\|", "");
        value = value.replaceAll(";", "");
        //		value = value.replaceAll("@", "");
        value = value.replaceAll("'", "");
        value = value.replaceAll("\"", "");
        value = value.replaceAll("\\'", "");
        value = value.replaceAll("\\\"", "");
        //		value = value.replaceAll("<", "&lt;");
        //		value = value.replaceAll(">", "&gt;");
        value = value.replaceAll("\r", "");
        value = value.replaceAll("\n", "");
        value = value.replaceAll("script", " script ");
        value = value.replaceAll("on", " on ");
        //		value = value.replaceAll("document", " document ");
        //		value = value.replaceAll("window", " window ");
        //		value = value.replaceAll("alert", " alert ");
        //		value = value.replaceAll("iframe", " iframe ");
        //		value = value.replaceAll("frame", " frame ");
        //		value = value.replaceAll("html", "  html ");
        //		value = value.replaceAll("src", "sr c");
        value = value.replaceAll("select", "selec t");
        value = value.replaceAll("update", "updat e");
        value = value.replaceAll("delete", "delet e");
        value = value.replaceAll("insert", "inter t");
        value = value.replaceAll("truncate", "truncat e");
        value = value.replaceAll("%27", "");
        value = value.replaceAll("%22", "");
        value = value.replaceAll("%3E", "");
        value = value.replaceAll("%3C", "");
        value = value.replaceAll("%3D", "");
        value = value.replaceAll("%2F", "");
        return value;
    }
    
    /**
     * 登录失败锁定用户最大次数
     */
    public static int getLoginFailMaxTimes()
    {
        return Integer.parseInt(getSysConfig("login_fail_maxtimes").getConfigValue());
    }
    
    /**
     * 登录失败锁定用户时间
     */
    public static int getLoginFailLockDates()
    {
        return Integer.parseInt(getSysConfig("login_fail_lockdates").getConfigValue());
    }
    
    //    /**
    //     * 文件路径
    //     * <功能详细描述>
    //     * @return
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public static String getBaseFilePath() {
    //        return sysSetting.getString("baseFilePath");
    //    }
    
    public static List<String> getHTMLEvents()
    {
        List<String> htmlEvents = new ArrayList<String>();
        
        htmlEvents.add("src");
        htmlEvents.add("href");
        htmlEvents.add("onactivate");
        htmlEvents.add("onbefore");
        htmlEvents.add("onblur");
        htmlEvents.add("onclick");
        htmlEvents.add("oncontextmenu");
        htmlEvents.add("oncontrolselect");
        htmlEvents.add("oncut");
        htmlEvents.add("ondblclick");
        htmlEvents.add("ondeactivate");
        htmlEvents.add("ondrag");
        htmlEvents.add("ondrop");
        htmlEvents.add("onfilterchange");
        htmlEvents.add("onfocus");
        htmlEvents.add("onhelp");
        htmlEvents.add("onkey");
        htmlEvents.add("onload");
        htmlEvents.add("onunload");
        htmlEvents.add("onlosecapture");
        htmlEvents.add("onmouse");
        htmlEvents.add("onmove");
        htmlEvents.add("onpaste");
        htmlEvents.add("onpropertychange");
        htmlEvents.add("onreadystatechange");
        htmlEvents.add("onresize");
        htmlEvents.add("onscroll");
        htmlEvents.add("onselectstart");
        htmlEvents.add("ontimeerror");
        
        return htmlEvents;
    }
    
}
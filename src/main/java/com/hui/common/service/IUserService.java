package com.hui.common.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hui.common.entity.Page;
import com.hui.common.entity.Trends;
import com.hui.common.entity.User;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IUserService extends IBaseService<User, User>
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids);
    
    /**
     * 绑定手机号
     * <功能详细描述>
     * @param huiNo
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer bindMobile(String huiNo,String mobile);
    
    /**
     * 更改昵称
     * <功能详细描述>
     * @param huiNo
     * @param nickName
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer resetNickName(String huiNo,String nickName);
    
//    Integer modifyPersonalData(SysFile personIcon,String nickName,);
    
    /**
     * 学霸好友分页
     * <功能详细描述>
     * @param pageNo
     * @param size
     * @param huiNo
     * @param nickName
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<User> getMyFriends(Integer pageNo,Integer size,String huiNo,String nickName);
    
    /**
     * 查询学霸好友
     * <功能详细描述>
     * @param huiNo
     * @param nickName
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<User> getMyFriends(String huiNo,String nickName);
    
    /**
     * 关注我的分页
     * <功能详细描述>
     * @param pageNo
     * @param size
     * @param huiNo
     * @param nickName
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<User> getMyFans(Integer pageNo,Integer size,String huiNo,String nickName);
    
    /**
     * 根据昵称分页查询用户
     * <功能详细描述>
     * @param pageNo
     * @param size
     * @param huiNo
     * @param nickName
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<User> getUsers(Integer pageNo,Integer size,String huiNo,String nickName);
    
    /**
     * 装配该汇答号与对方用户的关注关系(0未关注1互相关注2已关注)
     * <功能详细描述>
     * @param sourceHuiNo
     * @param targetHuiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer assembleFansToo(String sourceHuiNo,String targetHuiNo);
    
    /**
     * 获取用户信息
     * <功能详细描述>
     * @param huiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    User getUserInfo(String huiNo);
    
    /**
     * 获得他人信息
     * <功能详细描述>
     * @param huiNo
     * @param myHuiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    User getTaInfo(String huiNo,String myHuiNo);
    
    /**
     * 获取动态列表
     * <功能详细描述>
     * @param huiNo
     * @param trendsNum 动态个数
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Trends> getTrendses(String huiNo,Integer trendsNum);
    
    /**
     * 根据昵称模糊匹配用户
     * <功能详细描述>
     * @param nickName
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<User> getUsersByNickName(String nickName);
    
    /**
     * 根据昵称模糊匹配用户
     * <功能详细描述>
     * @param nickName
     * @param isTeacher
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<User> getUsersByNickName(String nickName,Integer isTeacher);
    
    /**
     * 扣减汇豆
     * <功能详细描述>
     * @param huiNo
     * @param beanNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer minusBean(String huiNo,Integer beanNum);
    
    /**
     * 添加汇豆
     * <功能详细描述>
     * @param huiNo
     * @param beanNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer plusBean(String huiNo,Integer beanNum);
    
    /**
     * 修改用户信息
     * <功能详细描述>
     * @param userId
     * @param nickName
     * @param sex
     * @param birthDate
     * @param constellation
     * @param homeStr
     * @param residenceStr
     * @param gradeId
     * @param school
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer modifyUserInfo(Integer userId, String nickName, Integer sex, Integer birthDate, Integer constellation,
        String homeStr, String residenceStr,Integer gradeId,String school);
    
    /**
     * 检查昵称是否存在
     * <功能详细描述>
     * @param nickName
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean isNicknameExists(String nickName);
    
    /**
     * 获取库表中的用户信息
     * <功能详细描述>
     * @param huiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    User getUser(String huiNo);
    
    /**
     * 一天登录一次赠送汇豆
     * <功能详细描述>
     * @param userInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer presentBean4Login1Day(User userInfo);
    
    /**
     * 清空手机验证码
     * <功能详细描述>
     * @param session
     * @see [类、类#方法、类#成员]
     */
    void removePhoneCode(HttpSession session);
    
    /**
     * 第三方登录
     * <功能详细描述>
     * @param type
     * @param name
     * @param phone
     * @param userIcon
     * @param sex
     * @param birthdate
     * @param commonkey
     * @return
     * @see [类、类#方法、类#成员]
     */
    User login3(Integer type,String name,String phone,String userIcon,String userIconLt,String sex,String birthdate,String commonkey);
    
}
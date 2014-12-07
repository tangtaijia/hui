package com.hui.common.dao;

import java.util.List;
import java.util.Map;

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
public interface IUserDao extends IBaseDao<User, User>
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids);
    
    public User queryUserInfo(Map<String,String> paramMap);
    
    public Integer isNicknameExists(Map<String,String> paramMap);
    
    public Integer isMobileExists(Map<String,String> paramMap);
    
    public void register(User user) throws Exception;
    
    public void resetUserPasword(Map<String,String> paramMap) throws Exception;
    
    public List<User> searchUserByNickName(Map<String,String> paramMap);
    
    public List<User> searchAllUserByNickName(Map<String,String> paramMap);
    
    public User queryUserInfoByHuiNo(Map<String,String> paramMap);
    
    public User getUserInfoEntity(Map<String,String> paramMap);
    
    /**
     * 查询被问题@的学霸
     * <功能详细描述>
     * @param qId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<User> getUserInfoByQId(Integer qId);
    
    /**
     * 绑定用户手机号
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer resetUserMobile(Map<String,Object> paramMap);
    
    /**
     * 更改昵称
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer resetUserNickName(Map<String,Object> paramMap);
    
    /**
     * 查询我的学霸好友
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<User> getMyFriends(Map<String,Object> paramMap);
    
    /**
     * 我的学霸好友个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer getMyFriendsCount(Map<String,Object> paramMap);
    
    /**
     * 查询关注我的列表
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<User> getMyFans(Map<String,Object> paramMap);
    
    /**
     * 关注我的列表个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer getMyFansCount(Map<String,Object> paramMap);
    /**
     * 扣减汇豆
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer minusBean(Map<String,Object> paramMap);
    /**
     * 增加汇豆
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer plusBean(Map<String,Object> paramMap);
    
    public void modifyPassword(Map<String,String> paramMap) throws Exception;
    
    public void setUserAcceptNo(Map<String,String> paramMap) throws Exception;
    
    public void setUserAnswerNo(Map<String,String> paramMap) throws Exception;
    
}
package com.hui.common.service;

import java.util.List;

import com.hui.common.dao.IQuestionDao;
import com.hui.common.entity.Answer;
import com.hui.common.entity.Page;
import com.hui.common.entity.User;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IAnswerService extends IBaseService<Answer,Answer>
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids);
    
    /**
     * 查询回答分页
     * <功能详细描述>
     * @param pageNo
     * @param huiNo
     * @param questionId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<Answer> getAnswers(Integer pageNo, String huiNo,Integer questionId);
    
    /**
     * 查询回答分页
     * <功能详细描述>
     * @param pageNo
     * @param userInfo
     * @param huiNo
     * @param questionId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<Answer> getAnswers(Integer pageNo,User userInfo, String huiNo,Integer questionId);
    
    /**
     * 查询回答
     * <功能详细描述>
     * @param huiNo
     * @param questionId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Answer> getAnswers( String huiNo,Integer questionId);
    
    /**
     * 根据主键查询回答
     * <功能详细描述>
     * @param aId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Answer getAnswer(Integer aId);
    /**
     * 查询最佳答案
     * <功能详细描述>
     * @param questionId
     * @param isFavorate
     * @return
     * @see [类、类#方法、类#成员]
     */
    Answer getFavorAnswer(Integer questionId);
    
    /**
     * 设为最佳答案
     * <功能详细描述>
     * @param answerId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer doFavorite(Integer answerId);
    
    /**
     * 添加回答
     * <功能详细描述>
     * @param answer
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer addAnswer(Answer answer);
    
    Integer addAnswer(Answer answerParam,String imgStr);
}

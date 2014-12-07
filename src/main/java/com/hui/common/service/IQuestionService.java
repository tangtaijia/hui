package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.Page;
import com.hui.common.entity.Question;

/**
 * 问题Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQuestionService extends IBaseService<Question, Question>
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids);
    
    /**
     * 问题分页
     * <功能详细描述>
     * @param pageNo
     * @param huiNo
     * @param questionDesc
     * @param isReward
     * @param hasAnswer
     * @param gradeId
     * @param subId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<Question> getQuestions(Integer pageNo, String huiNo, String questionDesc, Integer isReward, Integer hasAnswer,
        Integer gradeId, Integer subId, Integer toTeacher);
    
    /**
     * 添加
     * <功能详细描述>
     * @param question
     * @param studentHuiNos
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer add(Question question, String[] studentHuiNos);
    
    /**
     * 添加
     * <功能详细描述>
     * @param questionParam
     * @param studentHuiNos
     * @param imgStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer add(Question questionParam, String[] studentHuiNos, String imgStr);
    
    /**
     * 该问题是否有最佳答案
     * <功能详细描述>
     * @param questionId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean hasFavoriteAnswer(Integer questionId);
    
    /**
     * 获取单个问题--后台
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    Question getQuestion4Back(Integer questionId);
    
    /**
     * 获取问题列表--后台
     * <功能详细描述>
     * @param questionParam
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Question> getQuestions4Back(Question questionParam);
    
    Question getQuestion(Integer questionId);
}

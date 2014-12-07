package com.hui.common.dao;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.Question;
import com.hui.common.entity.SysFile;
import com.hui.common.webservice.EntityView.QuestionEntityView;

/**
 * 问题Dao
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQuestionDao extends IBaseDao<Question,Question>
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids);
    
    /**
     * 获取问题列表
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Question> getAll(Map<String,Object> paramMap);
    
    /**
     * 获取问题数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer getCount(Map<String,Object> paramMap);
    
    /**
     * 获取问题列表
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Question> getAll(Question questionParam);
    
    /**
     * 获取问题数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer getCount(Question questionParam);
    
    /**
     * 获取该提问的图片
     * <功能详细描述>
     * @param qId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SysFile> selectImagesByQId(Integer qId);
    
    
    public QuestionEntityView getQuestionDetailById(Map<String,String> paramMap);
    
    public List getMyQuestionsList(Map<String,String> paramMap);
    
    public List getAllQuestionPaingList(Map<String,String> paramMap);
    
    public List getLastSpeechUserList(Map<String,String> paramMap);
    
    public void updateQuestionAnswerNum(Map<String,String> paramMap);
    
    public void updateQuestionFavorate(Map<String,String> paramMap) throws Exception;
    
}

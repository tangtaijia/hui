package com.hui.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.Answer;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Praise;
import com.hui.common.entity.Question;
import com.hui.common.entity.QuestionUser;
import com.hui.common.entity.Subject;
import com.hui.common.entity.User;
import com.hui.common.service.IAnswerService;
import com.hui.common.service.ICollectionService;
import com.hui.common.service.IPraiseService;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.IQuestionUserService;
import com.hui.common.utils.CommonUtils;

public class AjaxQuestionAction extends BaseActionSupport
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5993077924327222087L;
    
    private IAnswerService answerService;
    
    private IPraiseService praiseService;
    
    private IQuestionService questionService;
    
    private ICollectionService collectionService;
    
    private AjaxResult result;
    
    private Integer answerIdParam;
    
    private Integer questionIdParam;
    
    private List<Question> questions;
    
    private IQuestionService questionServiceImpl;
    
    private IQuestionUserService questionUserService;
    
    private String rows;
    
    private String page;
    
    private Integer qId;
    
    private String huiNo;
    
    private String qryHuiNo;
    
    private String questionDesc;
    
    private Integer qryGradeId;
    
    private Integer qrySubjectId;
    
    private String qryCreateTimeFrom;
    
    private String qryCreateTimeTo;
    
    private Integer qryAllocated;
    
    private String delIds;
    
    /**
     * 点赞 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String praise()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        try
        {
            Praise praise = new Praise();
            if (CommonUtils.isNotEmptyOrNull(user))
            {
                praise.setHuiNo(user.getHuiNo());
            }
            else
            {
                praise.setHuiNo("0");
            }
            praise.setAnswerId(answerIdParam);
            praiseService.praise(praise);
            Answer answer = answerService.selectById(answerIdParam);
            Integer praiseNum = answer.getPraiseNum();
            if (CommonUtils.isEmptyOrNull(praiseNum))
            {
                praiseNum = 0;
            }
            result = new AjaxResult(true, "success", praiseNum);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 消赞 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String unPraise()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        try
        {
            Praise praise = new Praise();
            praise.setAnswerId(answerIdParam);
            praise.setHuiNo(user.getHuiNo());
            praiseService.unPraise(praise);
            result = new AjaxResult(true, "success");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 设置最佳答案 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String fitFavorite()
    {
        try
        {
            if (questionService.hasFavoriteAnswer(questionIdParam))
            {
                result = new AjaxResult(false, "对不起，已经有最佳答案了！");
            }
            else
            {
                answerService.doFavorite(answerIdParam);
                result = new AjaxResult(true, "success");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 收藏问题 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String collectQuestion()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        try
        {
            Question question = new Question();
            question.setqId(questionIdParam);
            int flag = collectionService.collectQuestion(user, question);
            if (flag == 2)
            {
                result = new AjaxResult(false, "对不起，已经收藏过了！");
            }
            else if (flag == 0)
            {
                result = new AjaxResult(false, "收藏失败！");
            }
            else
            {
                result = new AjaxResult(true, "success");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 取消收藏问题 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String unCollectQuestion()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        try
        {
            Question question = new Question();
            question.setqId(questionIdParam);
            int flag = collectionService.unCollectQuestion(user, question);
            if (flag == 0)
            {
                result = new AjaxResult(false, "取消失败！");
            }
            else
            {
                result = new AjaxResult(true, "success");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    public List<Question> getQuestions()
    {
        return questions;
    }
    
    public String tolistQuestions()
    {
        List<Question> listQuestion = questionService.list();
        questions = listQuestion;
        return SUCCESS;
    }
    
    public String toget()
    {
        try
        {
            Question question = questionService.selectById(questionIdParam);
            
			if (null != question) {
				result = new AjaxResult(true, question);
			} else {
				result = new AjaxResult(false, "数据不存在");
			}
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "查询失败");
        }
        
        return SUCCESS;
    }
    
    //这个是查所有的方法。
    public String tolist()
    {
		// 当前页   
        int intPage = Integer.parseInt((null == page || "0".equals(page)) ? "1" : page);
        // 每页显示条数   
        int size = Integer.parseInt((null == rows || "0".equals(rows)) ? "10" : rows);
        // 每页的开始记录  第一页为1  第二页为number +1    
        int start = (intPage - 1) * size;
        
        Question questionParam = new Question();
        questionParam.setHuiNo(qryHuiNo);
        questionParam.setGrade(new Grade(qryGradeId));
        questionParam.setSubject(new Subject(qrySubjectId));
        questionParam.setCreateTimeFrom(StringUtils.isBlank(qryCreateTimeFrom) ? null
            : CommonUtils.getTimeStamp(qryCreateTimeFrom));
        questionParam.setCreateTimeTo(StringUtils.isBlank(qryCreateTimeTo) ? null
            : CommonUtils.getTimeStamp(qryCreateTimeTo));
        questionParam.setAllocated(qryAllocated);
        questionParam.setStart(start);
        questionParam.setSize(size);
        List<Question> questions = questionServiceImpl.getQuestions4Back(questionParam);
        if (CommonUtils.isNotEmptyOrNull(questions))
        {
            for (Question question : questions)
            {
                if (CommonUtils.isNotEmptyOrNullOr0OrFalse(question.getCreateTime()))
                {
                    question.setCreateTimeStr((CommonUtils.formatTimeStamp(question.getCreateTime(),
                        CommonUtils.getLongDateFormat())));
                }
                if (CommonUtils.isNotEmptyOrNull(question.getAllocated()))
                {
                    if (0 == question.getAllocated())
                    {
                        question.setAllocatedStr("no");
                    }
                    if (1 == question.getAllocated())
                    {
                        question.setAllocatedStr("ok");
                    }
                }
                else
                {
                    question.setAllocatedStr("no");
                }
            }
        }
        result = new AjaxResult(questionServiceImpl.getCount(questionParam), questions);
        return SUCCESS;
    }
    
    /**
     * 检查是否自己提的问题
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String isMyQuestion()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        Question question = questionService.getQuestion(questionIdParam);
        if (question.getHuiNo().equals(user.getHuiNo()))
        {
            result = new AjaxResult(true, "对不起，不能回答自己提出的问题！");
        }
        else
        {
            result = new AjaxResult(false, "不是自己提出的问题");
        }
        return SUCCESS;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public String getRows()
    {
        return rows;
    }
    
    public void setRows(String rows)
    {
        this.rows = rows;
    }
    
    public String getPage()
    {
        return page;
    }
    
    public void setPage(String page)
    {
        this.page = page;
    }
    
    public String getHuiNo()
    {
        return huiNo;
    }
    
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    
    public String getQryHuiNo()
    {
        return qryHuiNo;
    }
    
    public void setQryHuiNo(String qryHuiNo)
    {
        this.qryHuiNo = qryHuiNo;
    }
    
    public Integer getQryGradeId()
    {
        return qryGradeId;
    }
    
    public void setQryGradeId(Integer qryGradeId)
    {
        this.qryGradeId = qryGradeId;
    }
    
    public Integer getQrySubjectId()
    {
        return qrySubjectId;
    }
    
    public void setQrySubjectId(Integer qrySubjectId)
    {
        this.qrySubjectId = qrySubjectId;
    }
    
    public String getQryCreateTimeFrom()
    {
        return qryCreateTimeFrom;
    }
    
    public void setQryCreateTimeFrom(String qryCreateTimeFrom)
    {
        this.qryCreateTimeFrom = qryCreateTimeFrom;
    }
    
    public String getQryCreateTimeTo()
    {
        return qryCreateTimeTo;
    }
    
    public void setQryCreateTimeTo(String qryCreateTimeTo)
    {
        this.qryCreateTimeTo = qryCreateTimeTo;
    }
    
    public Integer getQryAllocated()
    {
        return qryAllocated;
    }
    
    public void setQryAllocated(Integer qryAllocated)
    {
        this.qryAllocated = qryAllocated;
    }
    
    public IAnswerService getAnswerService()
    {
        return answerService;
    }
    
    public IPraiseService getPraiseService()
    {
        return praiseService;
    }
    
    public ICollectionService getCollectionService()
    {
        return collectionService;
    }
    
    public Integer getAnswerIdParam()
    {
        return answerIdParam;
    }
    
    public void setResult(AjaxResult result)
    {
        this.result = result;
    }
    
    public String allocate()
    {
        QuestionUser questionUser = new QuestionUser();
        questionUser.setHuiNo(huiNo);
        questionUser.setQuestionId(qId);
        try
        {
            questionUserService.saveEntity(questionUser);
            Question question = questionServiceImpl.selectById(qId);
            question.setAllocated(1);
            questionServiceImpl.updateEntity(question);
            result = new AjaxResult(true, "操作成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "操作失败");
        }
        return SUCCESS;
    }
    
    public String todel()
    {
        try
        {
            List<Integer> ids = new ArrayList<Integer>();
            String[] idArray = StringUtils.split(delIds, ',');
            for (String id : idArray)
            {
                ids.add(Integer.valueOf(id));
            }
            questionServiceImpl.modifyStatus(0, ids);
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
    public void setAnswerService(IAnswerService answerService)
    {
        this.answerService = answerService;
    }
    
    public void setPraiseService(IPraiseService praiseService)
    {
        this.praiseService = praiseService;
    }
    
    public void setAnswerIdParam(Integer answerIdParam)
    {
        this.answerIdParam = answerIdParam;
    }
    
    public void setQuestionService(IQuestionService questionService)
    {
        this.questionService = questionService;
    }
    
    public void setCollectionService(ICollectionService collectionService)
    {
        this.collectionService = collectionService;
    }
    
    public void setQuestionIdParam(Integer questionIdParam)
    {
        this.questionIdParam = questionIdParam;
    }
    
    public IQuestionService getQuestionServiceImpl()
    {
        return questionServiceImpl;
    }
    
    public void setQuestionServiceImpl(IQuestionService questionServiceImpl)
    {
        this.questionServiceImpl = questionServiceImpl;
    }
    
    public IQuestionUserService getQuestionUserService()
    {
        return questionUserService;
    }
    
    public void setQuestionUserService(IQuestionUserService questionUserService)
    {
        this.questionUserService = questionUserService;
    }
    
    public String getQuestionDesc()
    {
        return questionDesc;
    }
    
    public void setQuestionDesc(String questionDesc)
    {
        this.questionDesc = questionDesc;
    }
    
    public IQuestionService getQuestionService()
    {
        return questionService;
    }
    
    public Integer getQuestionIdParam()
    {
        return questionIdParam;
    }
    
    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }
    
    public String getDelIds()
    {
        return delIds;
    }
    
    public void setDelIds(String delIds)
    {
        this.delIds = delIds;
    }
    
}
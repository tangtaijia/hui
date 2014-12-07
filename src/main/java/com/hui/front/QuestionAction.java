package com.hui.front;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Answer;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.Subject;
import com.hui.common.entity.User;
import com.hui.common.service.IAnswerService;
import com.hui.common.service.ICollectionService;
import com.hui.common.service.IGradeService;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.ISubjectService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;

public class QuestionAction extends BaseActionSupport
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1342629509913844908L;
    private IQuestionService questionServiceImpl;
    private IAnswerService answerService;
    private ICollectionService collectionService;
    private IGradeService gradeServiceImpl;
    private ISubjectService subjectServiceImpl;
    /**
     * 问题
     */
    private Question questionParam;
    /**
     * 被@的学霸
     */
    private String[] studentHuiNos = new String[0];
    /**
     * 回答分页
     */
    private Page<Answer> answerPage = new Page<Answer>();
    /**
     * 问题id参数
     */
    private Integer questionIdParam;
    /**
     * 问题——前台显示
     */
    private Question questionInfo;
    
    /**
     * 回答参数
     */
    private Answer answerParam=new Answer();
    
    /**
     * 回答描述
     */
    private String answerDesc;
    
    /**
     * 图片
     */
    private String imgStr;
    
    /**
     * 添加问题
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String add() {
    	if(!"post".equalsIgnoreCase(getRequest().getMethod())) {//强制post提交
    		return "404";
    	}
    	/* 解决跨站点请求伪造 */
		if (!getRequest()
				.getSession()
				.getId()
				.toLowerCase()
				.equalsIgnoreCase(
						getRequest().getParameter(FrontUtils.FORM_AUTH_NAME))) {
			return "404";
		}
    	if(!validGradeAndSubject(questionParam.getGrade().getGradeId(),questionParam.getSubject().getSubId())) {
    		getRequest().setAttribute("tipInRequest", "对不起，您选择的年级学科不正确！");
            return "index";
    	}
        User user = (User)this.getRequest().getSession().getAttribute("user");
        questionParam.setHuiNo(user.getHuiNo());
        Integer rewardAmount = questionParam.getRewardAmount();
        if(CommonUtils.isNotEmptyOrNullOr0OrFalse(rewardAmount) && rewardAmount>user.getBeanNum()) {
            getRequest().setAttribute("tipInRequest", "对不起，您的汇豆余额不足！");
            return "index";
        }
//        if(CommonUtils.isNotEmptyOrNull(questionParam.getQuestionDesc())&&CommonUtils.containsValidateWords(questionParam.getQuestionDesc())) {
//            getRequest().getSession().setAttribute("tipInRequest", "对不起，您输入的内容包含敏感字！");
//            return "index";
//        }
        questionServiceImpl.add(questionParam, studentHuiNos, imgStr);
        return "index";
    }
    
    /**
     * 检查年级学科是否合法
     * <功能详细描述>
     * @param gradeId
     * @param subId
     * @return
     * @see [类、类#方法、类#成员]
     */
    private boolean validGradeAndSubject(Integer gradeId,Integer subId) {
    	Grade grade = gradeServiceImpl.selectById(gradeId);
    	Subject subject = subjectServiceImpl.selectById(subId);
    	if(CommonUtils.isEmptyOrNull(subject)&&CommonUtils.isEmptyOrNull(grade)) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    /**
     * 问题详情页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String questionDetail() {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        questionInfo = questionServiceImpl.selectById(questionIdParam);
        if(CommonUtils.isNotEmptyOrNull(user)&&collectionService.hasCollect(user, questionInfo)) {
            questionInfo.setCollectFlag(true);
        }
        answerPage = answerService.getAnswers(answerPage.getPage(),user, null, questionIdParam);
        return "questionDetail";
    }
    
    /**
     * 回答问题
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String addAnswer() {
    	if(!"post".equalsIgnoreCase(getRequest().getMethod())) {//强制post提交
    		return "404";
    	}
    	/* 解决跨站点请求伪造 */
		if (!getRequest()
				.getSession()
				.getId()
				.toLowerCase()
				.equalsIgnoreCase(
						getRequest().getParameter(FrontUtils.FORM_AUTH_NAME))) {
			return "404";
		}
    	User user = (User)this.getRequest().getSession().getAttribute("user");
    	answerParam.setHuiNo(user.getHuiNo());
        answerParam.setAnswerDesc(answerDesc);
        questionIdParam = answerParam.getQuestionId();
//        if(CommonUtils.isNotEmptyOrNull(answerParam.getAnswerDesc())&&CommonUtils.containsValidateWords(answerParam.getAnswerDesc())) {
//            getRequest().getSession().setAttribute("tipInRequest", "对不起，您输入的内容包含敏感字！");
//            return "questionDetail";
//        }
        answerService.addAnswer(answerParam, imgStr);
        return "addAnswer";
    }
    
    public Question getQuestionParam()
    {
        return questionParam;
    }
    public void setQuestionParam(Question questionParam)
    {
        this.questionParam = questionParam;
    }

    public String[] getStudentHuiNos()
    {
        return studentHuiNos;
    }

    public void setStudentHuiNos(String[] studentHuiNos)
    {
        this.studentHuiNos = studentHuiNos;
    }
    
    public void setQuestionServiceImpl(IQuestionService questionServiceImpl)
    {
        this.questionServiceImpl = questionServiceImpl;
    }
    
    public void setAnswerService(IAnswerService answerService)
    {
        this.answerService = answerService;
    }

    public void setCollectionService(ICollectionService collectionService)
    {
        this.collectionService = collectionService;
    }

	public void setGradeServiceImpl(IGradeService gradeServiceImpl) {
		this.gradeServiceImpl = gradeServiceImpl;
	}

	public void setSubjectServiceImpl(ISubjectService subjectServiceImpl) {
		this.subjectServiceImpl = subjectServiceImpl;
	}

	public void setQuestionIdParam(Integer questionIdParam)
    {
        this.questionIdParam = questionIdParam;
    }

    public Question getQuestionInfo()
    {
        return questionInfo;
    }

    public Page<Answer> getAnswerPage()
    {
        return answerPage;
    }

    public void setAnswerParam(Answer answerParam)
    {
        this.answerParam = answerParam;
    }

    public Integer getQuestionIdParam()
    {
        return questionIdParam;
    }

    public void setAnswerDesc(String answerDesc)
    {
        this.answerDesc = answerDesc;
    }

    public void setImgStr(String imgStr)
    {
        this.imgStr = imgStr;
    }

}

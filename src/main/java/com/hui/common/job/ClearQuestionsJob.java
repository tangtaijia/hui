package com.hui.common.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.Question;
import com.hui.common.entity.QuestionUser;
import com.hui.common.entity.User;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.service.IMessageService;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.IQuestionUserService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.LogUtils;

/**
 * 处理一个小时内教师未处理的问题
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClearQuestionsJob extends BaseJob
{

    private IQuestionService questionServiceImpl;
    private IBeanFlowService beanFlowService;
    private IUserService userService;
    private IQuestionUserService questionUserService;
    private IMessageService messageService;
    
    public void setQuestionServiceImpl(IQuestionService questionServiceImpl)
    {
        this.questionServiceImpl = questionServiceImpl;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    public void setQuestionUserService(IQuestionUserService questionUserService)
    {
        this.questionUserService = questionUserService;
    }

    public void setMessageService(IMessageService messageService)
    {
        this.messageService = messageService;
    }

    public void setBeanFlowService(IBeanFlowService beanFlowService)
    {
        this.beanFlowService = beanFlowService;
    }

   
    protected void doCheck()
    {
        try
        {
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.INFO,
                "清除过期问题定时任务",
                "定时任务启动",
                this.getClass().getName(),
                "doCheck");
            Question questionParam = new Question();
            questionParam.setOutTime(1);
            questionParam.setToTeacher(1);
            questionParam.setAllocated(1);
            List<Question> questions = questionServiceImpl.list(questionParam);
            if(CommonUtils.isNotEmptyOrNull(questions)) {
                for(Question question:questions) {
                    User userParam = new User();
                    userParam.setHuiNo(question.getHuiNo());
                    List<User> users = userService.list(userParam);
                    if(CommonUtils.isNotEmptyOrNull(users)) {
                        for(User user:users) {
                            user.setBeanNum(user.getBeanNum()+question.getRewardAmount());
                            userService.updateEntity(user);
                            BeanFlow beanFlow = new BeanFlow();
                            beanFlow.setBeanNum(question.getRewardAmount());
                            beanFlow.setBfSn(CommonUtils.randomByUUID(10));
                            beanFlow.setFlowTime(CommonUtils.getCurTime());
                            beanFlow.setHuiNo(user.getHuiNo());
                            beanFlow.setInOut(1);
                            beanFlow.setStatus(1);
                            beanFlowService.saveEntity(beanFlow);
                        }
                    }
                    question.setToTeacher(0);
                    question.setRewardAmount(0);
                    question.setIsReward(0);
                    question.setAllocated(0);
                    questionServiceImpl.updateEntity(question);
                    Map<String,Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("qId", question.getqId());
                    List<QuestionUser> questionUsers = questionUserService.listByMap(paramMap);
                    paramMap.clear();
                    paramMap.put("msgInfoId", question.getqId());
                    paramMap.put("msgType", 2);
                    if(CommonUtils.isNotEmptyOrNull(questionUsers)) {
                        for(QuestionUser questionUserTmp:questionUsers) {
                            questionUserService.delete(questionUserTmp.getQuId());
                        }
                    }
                    messageService.deleteByTypeAndInfoId(paramMap);
                }
            }
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.INFO,
                "清除过期问题定时任务",
                "定时任务完成",
                this.getClass().getName(),
                "doCheck");
        }
        catch (Exception e)
        {
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.ERROR,
                "清除过期问题定时任务",
                "定时任务异常：" + e.getMessage(),
                this.getClass().getName(),
                "doCheck");
        }
    }
    
}

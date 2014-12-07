package com.hui.common.utils;

import java.util.Calendar;

import com.hui.common.entity.Answer;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Question;
import com.hui.common.entity.SysMsg;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.User;

/**
 * 前台工具类
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FrontUtils
{
	/**
	 * 	系统消息超出加引号的字数
	 */
	public static final int MSG_STR_EX_LENGTH = 15;
	
	/**
	 * session 名称
	 */
	public static final String SESSION_NAME = "HUISESSIONID";
	
	public static final String LOGIN_KEY = "loginKey";
	public static final String LOGIN_PWD = "pwd";
	
	public static final String FORM_AUTH_NAME = "authCode";
	
	public static final String IS_LOGIN = "isLogin";
	
	public static final String NOT_LOGIN = "需要重新登录";
	
	/**
	 * app问题列表超出的问题字数
	 */
	public static final int QUESTION_DESC_EX_LENGTH = 50;
	
    /**
     * 生成8位的汇答号
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String generateHuiNo() {
        Calendar cal = Calendar.getInstance();
        long millis = cal.getTimeInMillis();
        String millisStr = String.valueOf(millis);
        millisStr = millisStr.substring(millisStr.length()-8);
        return millisStr;
    }
    
    /**
     * 处理特殊字符
     * <功能详细描述>
     * @param question
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Question handlerSpecWord(Question question) {
        if(CommonUtils.isNotEmptyOrNull(question.getQuestionDesc())) {
            String questionDesc = CommonUtils.filterDangerString(question.getQuestionDesc());
            question.setQuestionDesc(questionDesc);
        }
        return question;
            
    }
    /**
     * 处理特殊字符
     * <功能详细描述>
     * @param answer
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Answer handlerSpecWord(Answer answer) {
    	if(CommonUtils.isNotEmptyOrNull(answer.getAnswerDesc())) {
            String answerDesc = CommonUtils.filterDangerString(answer.getAnswerDesc());
            answer.setAnswerDesc(answerDesc);
        }
        return answer;
    }
    /**
     * 处理特殊字符
     * <功能详细描述>
     * @param sysMsg
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static SysMsg handlerSpecWord(SysMsg sysMsg) {
    	if(CommonUtils.isNotEmptyOrNull(sysMsg.getSysMsgDesc())) {
            String sysMsgDesc = CommonUtils.filterDangerString(sysMsg.getSysMsgDesc());
            sysMsg.setSysMsgDesc(sysMsgDesc);
        }
    	if(CommonUtils.isNotEmptyOrNull(sysMsg.getSysMsgTitle())) {
            String sysMsgTitle = CommonUtils.filterDangerString(sysMsg.getSysMsgTitle());
            sysMsg.setSysMsgTitle(sysMsgTitle);
        }
        return sysMsg;
    }
    /**
     * 处理特殊字符
     * <功能详细描述>
     * @param sysMsg
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Msg handlerSpecWord(Msg msg) {
    	if(CommonUtils.isNotEmptyOrNull(msg.getMsgContent())) {
            String msgContent = CommonUtils.filterDangerString(msg.getMsgContent());
            msg.setMsgContent(msgContent);
        }
        return msg;
    }
    /**
     * 处理特殊字符
     * <功能详细描述>
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static User handlerSpecWord(User user) {
    	if(CommonUtils.isNotEmptyOrNull(user.getNickName())) {
            String nickName = CommonUtils.filterDangerString(user.getNickName());
            user.setNickName(nickName);
        }
        return user;
    }
    /**
     * 处理特殊字符
     * <功能详细描述>
     * @param teacherInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static TeacherInfo handlerSpecWord(TeacherInfo teacherInfo) {
    	if(CommonUtils.isNotEmptyOrNull(teacherInfo.getTeacherName())) {
            String teacherName = CommonUtils.filterDangerString(teacherInfo.getTeacherName());
            teacherInfo.setTeacherName(teacherName);
        }
    	if(CommonUtils.isNotEmptyOrNull(teacherInfo.getTeacherDesc())) {
            String teacherDesc = CommonUtils.filterDangerString(teacherInfo.getTeacherDesc());
            teacherInfo.setTeacherDesc(teacherDesc);
        }
        return teacherInfo;
    }
}

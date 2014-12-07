package com.hui.common.entity;

import java.io.Serializable;

/**
 * 未读数量
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UnReadNum implements Serializable
{
    
    /**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Integer answerMeNum;
    
    private Integer askMeNum;
    
    private Integer acceptMeNum;
    
    private Integer sysMsgNum;

    public UnReadNum(){}
    
    public UnReadNum(Integer answerMeNum,Integer askMeNum,Integer acceptMeNum,Integer sysMsgNum){
        this.acceptMeNum = acceptMeNum;
        this.answerMeNum = answerMeNum;
        this.askMeNum = askMeNum;
        this.sysMsgNum = sysMsgNum;
    }
    
    public Integer getAcceptMeNum()
    {
        return acceptMeNum;
    }

    public void setAcceptMeNum(Integer acceptMeNum)
    {
        this.acceptMeNum = acceptMeNum;
    }

    public Integer getAnswerMeNum()
    {
        return answerMeNum;
    }

    public void setAnswerMeNum(Integer answerMeNum)
    {
        this.answerMeNum = answerMeNum;
    }

    public Integer getAskMeNum()
    {
        return askMeNum;
    }

    public void setAskMeNum(Integer askMeNum)
    {
        this.askMeNum = askMeNum;
    }

    public Integer getSysMsgNum()
    {
        return sysMsgNum;
    }

    public void setSysMsgNum(Integer sysMsgNum)
    {
        this.sysMsgNum = sysMsgNum;
    }
}

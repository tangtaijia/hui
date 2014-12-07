package com.hui.common.action.mobileaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.SysFile;
import com.hui.common.service.ISysFileService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.ConvertObjectField;
import com.hui.common.utils.FrontUtils;
import com.hui.common.webservice.IAnswerInfoWService;
import com.hui.common.webservice.WebServiceResult;
import com.hui.common.webservice.EntityView.AnswerEntityView;

public class AnswerInfoAction extends BaseActionSupport
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -5416332461465511158L;
    
    @Autowired
    private IAnswerInfoWService answerInfoWService;
    
    @Autowired
    private ISysFileService sysFileService;
    
    private WebServiceResult jsonResult;
    
    private List resultList;
    
    public List getResultList()
    {
        return resultList;
    }
    
    public void setResultList(List resultList)
    {
        this.resultList = resultList;
    }
    
    public WebServiceResult getJsonResult()
    {
        return jsonResult;
    }
    
    public void setJsonResult(WebServiceResult jsonResult)
    {
        this.jsonResult = jsonResult;
    }
    
    public String execute()
    {
        HttpServletRequest request = getRequest();
        String huidaNo = request.getParameter("huidaNo");
        resultList = answerInfoWService.getAnswerTrends(huidaNo);
        return SUCCESS;
    }
    
    /**
     * 获取问题的回答列表
     * @return
     */
    public String getAnswerListByQuestionId()
    {
        HttpServletRequest request = getRequest();
        String questionId = request.getParameter("questionId");
        String page = request.getParameter("page");
        resultList = answerInfoWService.getAnswerListByQuestionId(questionId, page);
        AnswerEntityView view = null;
        for (int i = 0; i < resultList.size(); i++)
        {
            view = (AnswerEntityView)resultList.get(i);
            if (view.getHasImage() == 1)
            {
                SysFile sysFile = new SysFile();
                //获取缩略图url
                sysFile.setDataId(view.getAnswerNo());
                sysFile.setFileType(5);
                sysFile.setSeqId(1);
                List ll = sysFileService.selectByKey(sysFile);
                if (ll.size() > 0)
                {
                    sysFile = (SysFile)ll.get(0);
                    if (sysFile != null)
                    {
                        String filePath = sysFile.getFilePath();
                        String fileName = sysFile.getFileName();
                        String path = CommonUtils.getFilePathPrefix() + filePath + fileName;
                        view.setThumbnailUrl(path);
                    }
                }
                //获取原图
                sysFile.setSeqId(2);
                ll = sysFileService.selectByKey(sysFile);
                if (ll.size() > 0)
                {
                    sysFile = (SysFile)ll.get(0);
                    if (sysFile != null)
                    {
                        view.setImageId(String.valueOf(sysFile.getFileId()));
                    }
                }
            }
            ConvertObjectField.convertNullValue2Empty(view);
        }
        return SUCCESS;
    }
    
    /**
     * 设为最佳答案
     * @return
     */
    public String setBestAnswer()
    {
        HttpServletRequest request = getRequest();
        if (CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user")))
        {
            jsonResult = new WebServiceResult(0, FrontUtils.NOT_LOGIN);
        }
        else
        {
            String questionId = request.getParameter("questionId");
            String answerId = request.getParameter("answerId");
            jsonResult = new WebServiceResult(1, "设置成功");
            try
            {
                answerInfoWService.setBestAnswer(questionId, answerId);
            }
            catch (Exception e)
            {
                jsonResult = new WebServiceResult(e.getMessage());
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }
    
    /**
     * 获得我回答的所有问题列表
     * @return
     */
    public String getMyAnswersList()
    {
        HttpServletRequest request = getRequest();
        if (CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user")))
        {
            jsonResult = new WebServiceResult(0, FrontUtils.NOT_LOGIN);
        }
        else
        {
            String huidaNo = request.getParameter("huidaNo");
            String page = request.getParameter("page");
            resultList = answerInfoWService.getMyAnswersPagingList(huidaNo, page);
        }
        return SUCCESS;
    }
    
    /**
     * 回答问题
     * @return
     */
    public String answerQuestion()
    {
        HttpServletRequest request = getRequest();
        if (CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user")))
        {
            jsonResult = new WebServiceResult(0, FrontUtils.NOT_LOGIN);
        }
        else
        {
            String huidaNo = request.getParameter("huidaNo");
            String questionId = request.getParameter("questionId");
            String answerDescription = request.getParameter("answerDescription");
            String image = request.getParameter("image");
            String mode = request.getParameter("mode");
            String os = request.getParameter("os");
            jsonResult = new WebServiceResult(1, "回答成功");
            if (StringUtils.isBlank(answerDescription) || StringUtils.isBlank(answerDescription.trim()))
            {
                jsonResult = new WebServiceResult("回答不能为空哦");
            }
            else
            {
                try
                {
                    answerInfoWService.answerQuestion(huidaNo, questionId, answerDescription, image, mode, os);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    jsonResult = new WebServiceResult(e.getMessage());
                }
            }
        }
        return SUCCESS;
    }
    
}
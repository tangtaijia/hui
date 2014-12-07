package com.hui.common.action.mobileaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.webservice.IPraiseWService;
import com.hui.common.webservice.WebServiceResult;

public class praiseAction extends BaseActionSupport {
	
	private Logger logger = Logger.getLogger(praiseAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8604930670821167463L;
	
	@Autowired
	private IPraiseWService praiseWService;
	
	private WebServiceResult jsonResult;
	
	public WebServiceResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(WebServiceResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	/**
	 * 点赞
	 */
	public String execute(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String answerId = request.getParameter("answerId");
			jsonResult = new WebServiceResult(1, "成功");
			try {
				praiseWService.praise(huidaNo, answerId);
			} catch (Exception e) {
				jsonResult = new WebServiceResult(e.getMessage());
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 是否已点赞
	 * @return
	 */
	public String hasPraised(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String answerId = request.getParameter("answerId");
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("answerId", answerId);
		int count = praiseWService.queryPraiseCount(paramMap);
		HttpServletResponse response = getResponse();
		if(count > 0){
			count = 1;
		}
		try {
			JSONObject json = new JSONObject();
			json.put("status", count);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得回答已点赞的个数
	 * @return
	 */
	public String getPraisedCount(){
		HttpServletRequest request = getRequest();
		String answerId = request.getParameter("answerId");
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("answerId", answerId);
		int count = praiseWService.queryPraiseCount(paramMap);
		HttpServletResponse response = getResponse();
		try {
			JSONObject json = new JSONObject();
			json.put("count", count);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}

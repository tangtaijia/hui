package com.hui.common.action.mobileaction;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Bug;
import com.hui.common.service.IBugService;
import com.hui.common.webservice.WebServiceResult;

public class SubmitBugAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 187868425530985840L;
	
	private WebServiceResult jsonResult;
	
	@Autowired
	private IBugService bugService;

	public WebServiceResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(WebServiceResult jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	public String execute(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String content = request.getParameter("content");
		Bug bug = new Bug();
		bug.setBugDesc(content);
		bug.setHuiNo(huidaNo);
		bug.setStatus(1);
		jsonResult = new WebServiceResult(1, "提交成功");
		try {
			bugService.addBug(bug);
		} catch (Exception e) {
			jsonResult = new WebServiceResult(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

}

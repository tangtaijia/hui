package com.hui.common.action.mobileaction;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.SysFile;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.ImageUtils;
import com.hui.common.webservice.IUserPhotoService;
import com.hui.common.webservice.WebServiceResult;

public class UserPhotoAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2477037128382467002L;
	
	private WebServiceResult jsonResult;
	
	@Autowired
	private IUserPhotoService userPhotoService;
	
	public WebServiceResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(WebServiceResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String execute(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String image = request.getParameter("image");
			if(image == null || image.equals("")){
				jsonResult = new WebServiceResult("图片格式不正确");
				return SUCCESS;
			}
			try 
			{
				byte[] imageByte = ImageUtils.convertBase64toImageByte(image);
				userPhotoService.createUserPhotoData(huidaNo, imageByte);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				jsonResult = new WebServiceResult(e.getMessage());
				return SUCCESS;
			}
			jsonResult = new WebServiceResult(1, "上传成功");
		}
		return SUCCESS;
	}
	
	/**
	 * 获得用户头像
	 * @return
	 */
	public String getUserIcon(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		List<SysFile> sysFileList = userPhotoService.queryUserPhotoInfo(huidaNo);
		String url = "";
		if(sysFileList.size() > 0){
			SysFile sysFile = sysFileList.get(0);
			url = CommonUtils.getFilePathPrefix() + sysFile.getFilePath() + sysFile.getFileName();
		}
		HttpServletResponse response = super.getResponse();
		try {
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			json.put("userIconUrl", url);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

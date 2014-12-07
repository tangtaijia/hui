package com.hui.common.action.mobileaction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.SysData;
import com.hui.common.service.ISysDataService;
import com.hui.common.utils.JacksonUtils;

public class SysDataAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2260111526106940894L;
	
	private static final JacksonUtils ju = JacksonUtils.getInstance();
	
	@Autowired
	private ISysDataService sysDataService;
	
	/**
	 * 获得二维码链接地址
	 */
	public String execute(){
		SysData sysData = new SysData();
		sysData.setDataCode("code_location");
		List<SysData> list = sysDataService.selectByKey(sysData);
		String url = "";
		if(list != null && list.size() > 0){
			sysData = list.get(0);
			List ll = ju.readJson2List(sysData.getDataValue());
			if(ll.size() > 0){
				Map<String,Object> map = (Map<String, Object>) ll.get(0);
				url = (String) map.get("url");
			}
		}
		HttpServletResponse response = getResponse();
		try {
			JSONObject json = new JSONObject();
			json.put("url", url);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得软件最新版本号
	 * @return
	 */
	public String getLastVersion(){
		SysData sysData = new SysData();
		sysData.setDataCode("soft_version");
		List<SysData> list = sysDataService.selectByKey(sysData);
		String version = "";
		if(list != null && list.size() > 0){
			sysData = list.get(0);
			List ll = ju.readJson2List(sysData.getDataValue());
			if(ll.size() > 0){
				Map<String,Object> map = (Map<String, Object>) ll.get(1);
				version = (String) map.get("version");
			}
		}
		HttpServletResponse response = getResponse();
		try {
			JSONObject json = new JSONObject();
			json.put("version", version);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获得最新软件的下载地址
	 * @return
	 */
	public String getSoftDownloadAddress(){
		SysData sysData = new SysData();
		sysData.setDataCode("soft_download");
		List<SysData> list = sysDataService.selectByKey(sysData);
		String url = "";
		if(list != null && list.size() > 0){
			sysData = list.get(0);
			List ll = ju.readJson2List(sysData.getDataValue());
			if(ll.size() > 0){
				Map<String,Object> map = (Map<String, Object>) ll.get(0);
				url = (String) map.get("url");
			}
		}
		HttpServletResponse response = getResponse();
		try {
			JSONObject json = new JSONObject();
			json.put("url", url);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

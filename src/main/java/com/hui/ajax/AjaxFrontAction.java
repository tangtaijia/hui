package com.hui.ajax;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.User;
import com.hui.common.service.IMessageService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class AjaxFrontAction extends BaseActionSupport {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -7682114884763925937L;
	
	private IUserService userService;
	
	private IMessageService messageService;
	
	private AjaxResult result;
	
	private String str2DesParam;
	
	private String str2Md5Param;
	
	/////////////////////////
	private String nameParam;
	private String phoneParam;
	private String userIconParam;
	private String sexParam;
	private String birthdateParam;
	private String commonkeyParam;
	private String codeParam;
	private String descriptionParam;
	/////////////////////////
	private String info;
	
	private String loginName;
	private String loginPwd;
	private String valiCode;
	public AjaxResult getResult() {
		return result;
	}
	
	public void setStr2DesParam(String str2DesParam) {
		this.str2DesParam = str2DesParam;
	}
	
	public void setStr2Md5Param(String str2Md5Param) {
		this.str2Md5Param = str2Md5Param;
	}

	public void setNameParam(String nameParam) {
		this.nameParam = nameParam;
	}

	public void setPhoneParam(String phoneParam) {
		this.phoneParam = phoneParam;
	}

	public void setUserIconParam(String userIconParam) {
		this.userIconParam = userIconParam;
	}

	public void setSexParam(String sexParam) {
		this.sexParam = sexParam;
	}

	public void setBirthdateParam(String birthdateParam) {
		this.birthdateParam = birthdateParam;
	}

	public void setCommonkeyParam(String commonkeyParam) {
		this.commonkeyParam = commonkeyParam;
	}

	public void setCodeParam(String codeParam) {
		this.codeParam = codeParam;
	}

	public void setDescriptionParam(String descriptionParam) {
		this.descriptionParam = descriptionParam;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public String getDesStr() {
		DESPlus72 des;
		try {
			des = new DESPlus72();
			result = new AjaxResult(true, des.encrypt(str2DesParam));
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false, "数据加载失败");
		}
		return SUCCESS;
	}
	
	public String getMd5Str() {
		try {
			result = new AjaxResult(true, MD5Encrypt.encode(str2Md5Param));
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false, "数据加载失败");
		}
		return SUCCESS;
	}
	
	public String login3() {
		String vali = (String) getRequest().getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

		if (StringUtils.isBlank(this.valiCode) || StringUtils.isBlank(vali)
				|| !this.valiCode.equals(vali)) {
			result = new AjaxResult(false, "提示：您输入的验证码有错误！");
			return SUCCESS;
		}
		/*非空判断*/
		if(CommonUtils.isEmptyOrNull(loginName)) {
	        result = new AjaxResult(false, "提示：请输入账号！");
	        return SUCCESS;
		}
		if(CommonUtils.isEmptyOrNull(loginPwd)) {
	        result = new AjaxResult(false, "提示：请输入密码！");
	        return SUCCESS;
		}
		try {
			String url=DBUtils.getInstance().getLoginSite();
			String ztime = MessageFormat.format("{0,date,yyyyMMddHHmmss}" ,
					new Object[]       {
					new java.sql.Date(System.currentTimeMillis())
			});
			loginPwd = MD5Encrypt.encode(loginPwd.trim());
			String zy = "{name:\""+loginName+"\",pwd:\""+loginPwd+"\"}|"+ztime;
			zy = new DESPlus72().encrypt(zy);
			Map<String,String> paramMap = new HashMap<String, String>();
			paramMap.put("zy", zy);
			String httpResult = HttpUtil.http(url, paramMap);
			JSONObject jsonobject = JSONObject.parseObject(httpResult);
			String codeParam = jsonobject.getString("code");
//			String descriptionParam = jsonobject.getString("description");
			if("1".equals(codeParam)) {
				String nameParam = jsonobject.getJSONObject("userInfo").getString("name");
				String phoneParam = jsonobject.getJSONObject("userInfo").getString("phone");
				String userIconParam = jsonobject.getJSONObject("userInfo").getString("userIcon");
				String userIconLtParam = jsonobject.getJSONObject("userInfo").getString("userIconLt");
				String sexParam = jsonobject.getJSONObject("userInfo").getString("sex");
				String birthdateParam = jsonobject.getJSONObject("userInfo").getString("birthdate");
				String commonkeyParam = jsonobject.getJSONObject("userInfo").getString("commonkey");
				User user = userService.login3(0, nameParam, phoneParam, userIconParam,userIconLtParam,  sexParam, birthdateParam, commonkeyParam);
				if(CommonUtils.isNotEmptyOrNull(user)) {
					getRequest().getSession().setAttribute("user", user);
					userService.presentBean4Login1Day(user);//一天登录一次赠送汇豆
		            user.setUserPwd("");
		            Integer unReadNum = messageService.unReadNum(user.getHuiNo());//未读消息数量
		            Map<String,Object> userInfo = new HashMap<String, Object>();
		            userInfo.put("user", user);
		            userInfo.put("unReadNum", unReadNum);
		            getRequest().getSession().setAttribute("unReadNum", unReadNum);
		            result = new AjaxResult(true, userInfo);
		            return SUCCESS;
				}
			} else {
				result = new AjaxResult(false, "提示：您输入的帐号或密码有错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false, "系统错误");
		}
		return SUCCESS;
	}
	
	public static void main(String[] args) {
//		String infoReal = "{\"age\":105,\"id\":\"testFastJson001\",\"name\":\"maks\"}";  
//		String[] infoRealArr = infoReal.split(",");
//		String nameParam = "";
//		String phoneParam = "";
//		String userIconParam = "";
//		String sexParam = "";
//		String birthdateParam = "";
//		String commonkeyParam = "";
//		String codeParam = "";
//		String descriptionParam = "";
//		if(CommonUtils.isNotEmptyOrNull(infoRealArr)) {
//			for(String info:infoRealArr) {
//				if(info.indexOf("code")>-1) {
//					codeParam = info.split("[:]")[1];
//				}
//				if(info.indexOf("description")>-1) {
//					descriptionParam = info.split("[:]")[1];
//				}
//			}
//		}
//		System.out.println(codeParam);
//		System.out.println(descriptionParam);
//		JSONObject jsonResult = new JSONObject();
//		jsonResult = JSON.parseObject(infoReal);
//		System.out.println(jsonResult.toJSONString());
//		System.out.println(jsonResult.get("age"));
		
		String ztime = MessageFormat.format("{0,date,yyyyMMddHHmmss}" ,
                new Object[]       {
                new java.sql.Date(System.currentTimeMillis())
            });
		System.out.println(ztime);
		
		String sss = "{\"userInfo\":{\"name\":\"test001\",\"phone\":\"18355443333\",\"userIcon\":\"\",\"sex\":\"\",\"birthdate\":\"\",\"commonkey\":\"AH3950F7724C84D410BB349ECCEB374698\"},\"code\":1,\"description\":\"登录成功\"}\n";
		JSONObject jsonobject = JSONObject.parseObject(sss);
		System.out.println(jsonobject.get("code"));
		System.out.println(jsonobject.getJSONObject("userInfo").get("name"));
	}
	
    
    private URL url;
    
    public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
    	this.url = url;
    }
    
    public String sendPostRequest(String content) throws Exception {
         
        HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
         
        conn.setConnectTimeout(1000);
        conn.setReadTimeout(10000000);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
         
        OutputStream output = conn.getOutputStream();
         
        output.write(content.getBytes());
        output.flush();
        output.close();
         
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn
                .getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer("");
         
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
         
        String result = buffer.toString();
         
        result = URLDecoder.decode(result, "UTF-8");
        return result;
    }

	public void setInfo(String info) {
		this.info = info;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public void setValiCode(String valiCode) {
		this.valiCode = valiCode;
	}

}

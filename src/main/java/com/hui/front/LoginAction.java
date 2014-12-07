package com.hui.front;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.ModelDrivenActionSupport;
import com.hui.common.entity.User;
import com.hui.common.service.IUserService;
import com.hui.common.utils.*;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LoginAction extends ModelDrivenActionSupport<User>
{
    
    private static final long serialVersionUID = 171926714928924158L;
    
    private IUserService userService;
    
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }
    
    private Integer remembers;
    
    private String info;
    
    public Integer getRemembers() {
		return remembers;
	}

	public void setRemembers(Integer remembers) {
		this.remembers = remembers;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String execute()
    {
//        if (1 == CommonUtils.getFLValicode())
//        {
//            String vali =
//                (String)getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//            
//            if (StringUtils.isBlank(this.getModel().getValiCode()) || StringUtils.isBlank(vali)
//                || !this.getModel().getValiCode().equals(vali))
//            {
//                addActionError("验证码错误");
//                return "frontlogin";
//            }
//        }
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
		CookieHandler.deleteCookie(getRequest(), getResponse(),FrontUtils.SESSION_NAME);
		getRequest().getSession().invalidate();
		/*非空判断*/
		if(CommonUtils.isEmptyOrNull(this.getModel().getNickName())) {
			getRequest().getSession().removeAttribute("tipInRequest");
			getRequest().getSession().removeAttribute("isLogin");
			getRequest().getSession().setAttribute("tipInRequest", "提示：请输入昵称！");
	        getRequest().getSession().setAttribute("isLogin", "false");
	        return "frontIndex";
		}
		if(CommonUtils.isEmptyOrNull(this.getModel().getUserPwd())) {
			getRequest().getSession().removeAttribute("tipInRequest");
			getRequest().getSession().removeAttribute("isLogin");
			getRequest().getSession().setAttribute("tipInRequest", "提示：请输入密码！");
	        getRequest().getSession().setAttribute("isLogin", "false");
	        return "frontIndex";
		}
        this.getModel().setUserPwd(MD5Encrypt.encode(this.getModel().getUserPwd()));
        /*判断是否为手机号码*/
        if(RegUtils.isPhone(this.getModel().getNickName())) {
            this.getModel().setMobile(this.getModel().getNickName());
            this.getModel().setNickName(null);
        }
        List<User> users = userService.selectByKey(this.getModel());
        User result = null;
        if(CommonUtils.isNotEmptyOrNull(users)) {
            result = users.get(0);
        }
        
        if (null != result)
        {
        	if(CommonUtils.isNotEmptyOrNullOr0OrFalse(remembers)&&remembers==1) {
        		CookieHandler.deleteCookie(getRequest(), getResponse(),FrontUtils.LOGIN_KEY);
            	CookieHandler.deleteCookie(getRequest(), getResponse(),FrontUtils.LOGIN_PWD);
        		Hashtable<String,String> nameValues = new Hashtable<String, String>();
        		nameValues.put(FrontUtils.LOGIN_KEY, result.getNickName());
        		nameValues.put(FrontUtils.LOGIN_PWD, result.getUserPwd());
        		CookieHandler.createCookie(getResponse(), nameValues, 15);
        	}
            this.getRequest().getSession().setAttribute("user", result);
            this.getRequest().removeAttribute("isLogin");
            this.getRequest().getSession().removeAttribute("isLogin");
            userService.presentBean4Login1Day(result);//一天登录一次赠送汇豆
            return "home";
        }
        getRequest().getSession().removeAttribute("tipInRequest");
		getRequest().getSession().removeAttribute("isLogin");
        getRequest().getSession().setAttribute("tipInRequest", "提示：您输入的帐号或密码有错误");
        getRequest().getSession().setAttribute("isLogin", "false");
        return "frontIndex";
    }
    
    public String exit()
    {
    	CookieHandler.deleteCookie(getRequest(), getResponse(),FrontUtils.LOGIN_KEY);
    	CookieHandler.deleteCookie(getRequest(), getResponse(),FrontUtils.LOGIN_PWD);
        this.getRequest().getSession().removeAttribute("user");
        getResponse().setHeader("Pragma","No-cache");
        getResponse().setHeader("Cache-Control","no-cache");
        getResponse().setDateHeader("Expires",0);
        getRequest().getSession(true).invalidate();
        return "index";
    }
    
    /**
     * 单点登录
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String xxT() {
    	if(CommonUtils.isNotEmptyOrNull(info)) {
    		DESPlus72 des;
			try {
				des = new DESPlus72();
				String infoReal = des.decrypt(info);
				infoReal = infoReal.replaceAll("\"", "\\\"");
//String ssss = "{\"userInfo\":{\"name\":\"test001\",\"phone\":\"18355443333\",\"userIcon\":\"\",\"userIconLt\":\"\",\"sex\":\"\",\"birthdate\":\"\",\"key\":5555,\"commonkey\":\"AH3950F7724C84D410BB349ECCEB374698\"},\"code\":1,\"description\":\"登录成功\"}\n";
				JSONObject jsonResult = JSONObject.parseObject(infoReal);
				String codeParam = jsonResult.get("code").toString();
//				String descriptionParam = jsonResult.get("description").toString();
System.out.println(codeParam+"   code===============================");				
				if("1".equals(codeParam)) {
					String key = jsonResult.getJSONObject("userInfo").getString("key");
					if(CommonUtils.isNotEmptyOrNull(key)) {
						String ztime = MessageFormat.format("{0,date,yyyyMMddHHmmss}" ,
								new Object[]       {
								new java.sql.Date(System.currentTimeMillis())
						});
						String validKey = "{key:\""+key+"\"}|"+ztime;
						Map<String,String> paramMap = new HashMap<String, String>();
						paramMap.put("key", des.encrypt(validKey));
						String httpResult = HttpUtil.http(DBUtils.getInstance().getLoginSite(), paramMap);
						httpResult = httpResult.replaceAll("\"", "\\\"");
						JSONObject httpResultJson = JSONObject.parseObject(httpResult);
						String validSuccess = httpResultJson.getString("code");
						System.out.println(validSuccess+"   validSuccess===============================");	
						if("1".equals(validSuccess)) {
							String nameParam = jsonResult.getJSONObject("userInfo").getString("name");
							String phoneParam = jsonResult.getJSONObject("userInfo").getString("phone");
							String userIconParam = jsonResult.getJSONObject("userInfo").getString("userIcon");
							String userIconLtParam = jsonResult.getJSONObject("userInfo").getString("userIconLt");
							String sexParam = jsonResult.getJSONObject("userInfo").getString("sex");
							String birthdateParam = jsonResult.getJSONObject("userInfo").getString("birthdate");
							String commonkeyParam = jsonResult.getJSONObject("userInfo").getString("commonkey");
							System.out.println(nameParam+"   name===============================");					
							User user = userService.login3(0, nameParam, phoneParam, userIconParam, userIconLtParam, sexParam, birthdateParam, commonkeyParam);
							if(CommonUtils.isNotEmptyOrNull(user)) {
								getRequest().getSession().setAttribute("user", user);
							}
						}
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LogUtils.getInstance().log(0,
	                    CommonUtils.LogLevel.INFO,
	                    "sss备份dddd==========================数据定时任务",
	                    e.getMessage(),
	                    this.getClass().getName(),
	                    "doCheck");
				e.printStackTrace();
			}
		}
    	return "index";
    }
    
    public static void main(String[] args) {
    	String info="d62ce29a4855e4536460f525db5a0a5d8df9a0006b5f2e9ed19df42c41e8e0430dd5c3707b5ac6d9";
//    	String info="d62ce29a4855e4536460f525db5a0a5d8df9a0006b5f2e9e8c9638f4da16e71901da9bcd2a2f3403";
//    	String info="0a1e83a79adbae87952b07b4761827064eba243d1374256e9b972ccb1c0449115021f124e22d2c77b6469863cd954878da379d1b9625ebb14e82e713b5f9807b8f7ddd25f6e54e12ead1924dcf0490a59a1fadbc1e12d927f0e0a0607c8bf2499d73386de33e91af2795d06baa425da00b2a883fa60ad0e8a903c8832282394850cd8c8c765a0c34ecebcc2d4cd30d41364b8f4d75567519dbc0917a5363f8c539fae8b29604951f1b5243899b7f826a1262b84a0057fc59dd36c17d0a862b04f95b5280999ba044aa0188890578a356";
//		String sss = "{\"code\":0,\"description\":\"未登录\"}";
		String sss = "{\"userInfo\":{\"name\":\"test111\",\"phone\":\"13658425553\",\"userIcon\":\"\",\"userIconLt\":\"http://i7.baidu.com/it/u=2430951561,3260147079&fm=96&s=8660DB145B6048115EE4E546030040B1\",\"sex\":\"\",\"birthdate\":\"19830508\",\"key\":5555,\"commonkey\":\"AH3950F7724C84D410BB349ECCEB374699\"},\"code\":1,\"description\":\"notLogin\"}\n";
		DESPlus72 des;
		String infoReal="";
		try {
			des = new DESPlus72();
			String dd = des.encrypt(sss);
			System.out.println(dd);
			infoReal = des.decrypt(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sss);
		System.out.println(infoReal);
		JSONObject jsonResult = JSONObject.parseObject(infoReal);
		String codeParam = jsonResult.get("description").toString();
		System.out.println(codeParam);
		com.alibaba.fastjson.JSONObject fastJsonObject = com.alibaba.fastjson.JSONObject.parseObject(infoReal);
		codeParam = fastJsonObject.get("description").toString();
		System.out.println(codeParam);
		
		//////////////
		String ssss = "{\"userInfo\":{\"name\":\"test001\",\"phone\":\"18355443333\",\"userIcon\":\"\",\"sex\":\"\",\"birthdate\":\"\",\"commonkey\":\"AH3950F7724C84D410BB349ECCEB374698\"},\"code\":1,\"description\":\"登录成功\"}\n";
		
	}
}
package com.hui.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.hui.common.dao.IAnswerDao;
import com.hui.common.dao.ICityDao;
import com.hui.common.dao.ICommonLoginDao;
import com.hui.common.dao.ICountyDao;
import com.hui.common.dao.IGradeDao;
import com.hui.common.dao.IProvinceDao;
import com.hui.common.dao.IQuestionDao;
import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.IUserDao;
import com.hui.common.dao.IUserFollowDao;
import com.hui.common.entity.Answer;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.BirthDate;
import com.hui.common.entity.City;
import com.hui.common.entity.CommonLogin;
import com.hui.common.entity.ConstellationEnum;
import com.hui.common.entity.County;
import com.hui.common.entity.Fans;
import com.hui.common.entity.Grade;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.Page;
import com.hui.common.entity.Province;
import com.hui.common.entity.Question;
import com.hui.common.entity.Region;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.Trends;
import com.hui.common.entity.UnReadNum;
import com.hui.common.entity.User;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.service.IMessageService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UserServiceImpl extends BaseServiceImpl<User, User> implements IUserService
{
    @Autowired
    private IUserFollowDao userFollowDao;
    
    @Autowired
    private IAnswerDao answerDao;
    
    @Autowired
    private IQuestionDao questionDao;
    
    @Autowired
    private ISysFileDao sysFileDao;
    
    @Autowired
    private IGradeDao gradeDao;
    
    @Autowired
    private IProvinceDao provinceDao;
    
    @Autowired
    private ICityDao cityDao;
    
    @Autowired
    private ICountyDao countyDao;
    
    @Autowired
    private ICommonLoginDao commonLoginDao;
    
    @Autowired
    private IMessageService messageService;
    
    @Autowired
    private IBeanFlowService beanFlowService;
    
   
    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
        return ((IUserDao)baseDao).modifyStatus(status, ids);
    }
    
   
    public Integer bindMobile(String huiNo, String mobile)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("mobile", mobile);
        return ((IUserDao)baseDao).resetUserMobile(paramMap);
    }
    
   
    public Integer resetNickName(String huiNo, String nickName)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("nickName", nickName);
        Map<String, String> param = new HashMap<String, String>();
        param.put("nickName", nickName);
        Integer hasNickName = ((IUserDao)baseDao).isNicknameExists(param);
        if(CommonUtils.isNotEmptyOrNullOr0OrFalse(hasNickName)) {
            return 0;
        }
        return ((IUserDao)baseDao).resetUserNickName(paramMap);
    }
    
   
    public Page<User> getMyFans(Integer pageNo,Integer size, String huiNo,String nickName)
    {
        Page<User> page = new Page<User>();
        page.setPage(pageNo);
        page.setSize(size);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("nickName", nickName);
        paramMap.put("isTeacher", 0);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<User> users = ((IUserDao)baseDao).getMyFans(paramMap);
        /*装配-互为关注*/
        assembleFansToo(users, huiNo);
        if(CommonUtils.isNotEmptyOrNull(users)) {
            for(User user:users) {
                /*加图片*/
                user = addIcon(user);
                user = FrontUtils.handlerSpecWord(user);
            }
        }
        Integer count = ((IUserDao)baseDao).getMyFansCount(paramMap);
        page.setDatas(users);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
   
    public Page<User> getMyFriends(Integer pageNo,Integer size, String huiNo,String nickName)
    {
        Page<User> page = new Page<User>();
        page.setPage(pageNo);
        page.setSize(size);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("nickName", nickName);
        paramMap.put("isTeacher", 0);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<User> users = ((IUserDao)baseDao).getMyFriends(paramMap);
        /*装配-互为关注*/
        assembleFansToo(users, huiNo);
        if(CommonUtils.isNotEmptyOrNull(users)) {
            for(User user:users) {
                /*加图片*/
                user = addIcon(user);
                user = FrontUtils.handlerSpecWord(user);
            }
        }
        Integer count = ((IUserDao)baseDao).getMyFriendsCount(paramMap);
        page.setDatas(users);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
    public List<User> getMyFriends(String huiNo, String nickName) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("nickName", nickName);
        paramMap.put("isTeacher", 0);
        List<User> users = ((IUserDao)baseDao).getMyFriends(paramMap);
    	return users;
    }
    
   
    public Page<User> getUsers(Integer pageNo,Integer size,String huiNo, String nickName)
    {
        Page<User> page = new Page<User>();
        page.setPage(pageNo);
        page.setSize(size);
        
        User userParam = new User();
        userParam.setNickName(nickName);
        userParam.setIsTeacher(0);
        userParam.setStart(page.getStart());
        userParam.setSize(page.getSize());
        
        List<User> users = ((IUserDao)baseDao).selectAll(userParam);
        /*装配-互为关注*/
        assembleFansToo(users, huiNo);
        if(CommonUtils.isNotEmptyOrNull(users)) {
            for(User user:users) {
                /*加图片*/
                user = addIcon(user);
                user = FrontUtils.handlerSpecWord(user);
            }
        }
        Integer count = ((IUserDao)baseDao).getCount(userParam);
        page.setDatas(users);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
    /**
     * 装配-互为关注
     * <功能详细描述>
     * @param users
     * @param huiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<User> assembleFansToo(List<User> users,String huiNo) {
        if(CommonUtils.isNotEmptyOrNull(users)) {
            for(User user:users) {
            	user.setFansToo(assembleFansToo(huiNo,user.getHuiNo()));
            }
        }
        return users;
    }
    
    public Integer assembleFansToo(String sourceHuiNo,String targetHuiNo) {
    	 Map<String,String> param = new HashMap<String, String>();
         param.put("myHuiNo", sourceHuiNo);
         param.put("otherHuiNo", targetHuiNo);
         List<Fans> fans = this.userFollowDao.searchFriends(param);
         if(CommonUtils.isNotEmptyOrNull(fans) && fans.size()>1) {
        	 return 1;//互相关注
         } else {
             List<Fans> bullyFriends = this.userFollowDao.searchBullyFriends(param);
             if(CommonUtils.isNotEmptyOrNull(bullyFriends)) {
            	 return 2;//已关注
             } else {
            	 return 0;//未关注
             }
         }
    }
    
   
    public User getUserInfo(String huiNo)
    {
        User user = getUser(huiNo);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        if(CommonUtils.isNotEmptyOrNull(user.getHomeStr())) {
            String[] regionIdStrs = user.getHomeStr().split(",");
            Province province = provinceDao.selectById(Integer.parseInt(regionIdStrs[0]));
            City city = cityDao.selectById(Integer.parseInt(regionIdStrs[1]));
            County county = countyDao.selectById(Integer.parseInt(regionIdStrs[2]));
            Region region = new Region(province, city, county);
            user.setHome(region);
        }
        if(CommonUtils.isNotEmptyOrNull(user.getResidenceStr())) {
            String[] regionIdStrs = user.getResidenceStr().split(",");
            Province province = provinceDao.selectById(Integer.parseInt(regionIdStrs[0]));
            City city = cityDao.selectById(Integer.parseInt(regionIdStrs[1]));
            County county = countyDao.selectById(Integer.parseInt(regionIdStrs[2]));
            Region region = new Region(province, city, county);
            user.setResidence(region);
        }
        if(CommonUtils.isNotEmptyOrNull(user.getSchoolRegionStr())) {
            String[] regionIdStrs = user.getSchoolRegionStr().split(",");
            Province province = provinceDao.selectById(Integer.parseInt(regionIdStrs[0]));
            City city = cityDao.selectById(Integer.parseInt(regionIdStrs[1]));
            County county = countyDao.selectById(Integer.parseInt(regionIdStrs[2]));
            Region region = new Region(province, city, county);
            user.setSchoolRegion(region);
        }
        if(CommonUtils.isNotEmptyOrNull(user.getBirthDate())) {
            user.setBirthDateStr(CommonUtils.formatTimeStamp(user.getBirthDate(), CommonUtils.getShortDateFormat()));
        }
        if(CommonUtils.isNotEmptyOrNull(user.getBirthDateStr())) {
            String[] dateStrs = user.getBirthDateStr().split("-");
            BirthDate birthDate = new BirthDate(Integer.parseInt(dateStrs[0]),Integer.parseInt(dateStrs[1]),Integer.parseInt(dateStrs[2]));
            user.setBirthDay(birthDate);
        }
        if(CommonUtils.isNotEmptyOrNull(user.getConstellation())) {
            user.setConstellationStr(ConstellationEnum.getNameByIndex(user.getConstellation()));
        }
        if(CommonUtils.isNotEmptyOrNull(user.getSex())) {
            if(0 == user.getSex()) {
                user.setSexStr("保密");
            }
            if(1 == user.getSex()) {
                user.setSexStr("男");
            }
            if(2 == user.getSex()) {
                user.setSexStr("女");
            }
        } else {
            user.setSexStr("保密");
        }
        if(CommonUtils.isNotEmptyOrNull(user.getGradeId())) {
            Grade grade = gradeDao.selectById(user.getGradeId());
            user.setGrade(grade);
        }
        paramMap.put("isTeacher", 0);
        user.setFriendsNum(((IUserDao)baseDao).getMyFriendsCount(paramMap));
        user.setFansNum(((IUserDao)baseDao).getMyFansCount(paramMap));
        UnReadNum unReadNum =
            new UnReadNum(messageService.unReadNum(huiNo, 1), messageService.unReadNum(huiNo, 2),
                messageService.unReadNum(huiNo, 3), messageService.unReadNum(huiNo, 4));
        user.setUnReadNum(unReadNum);
        
        /*加图片*/
        user = addIcon(user);
        user = FrontUtils.handlerSpecWord(user);
        return user;
    }
    
    /**
     * 加图片
     * <功能详细描述>
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    private User addIcon(User user) {
        SysFile sysFileParam = new SysFile();
        sysFileParam.setFileType(1);
        sysFileParam.setDataId(user.getUserId());
        List<SysFile> images = sysFileDao.selectAll(sysFileParam); 
        if(CommonUtils.isNotEmptyOrNull(images)) {
            ImageTwo imageTwo = new ImageTwo();
            for(SysFile image:images) {
                if(1==image.getSeqId()) {
                    imageTwo.setImgLt(image);
                }
                if(2==image.getSeqId()) {
                    imageTwo.setImgOri(image);
                }
            }
            user.setImageTwo(imageTwo);
        }
        return user;
    }
    
   
    public User getTaInfo(String huiNo, String myHuiNo)
    {
    	User user = getUser(huiNo);
    	
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("isTeacher", 0);
        user.setFriendsNum(((IUserDao)baseDao).getMyFriendsCount(paramMap));//我的学霸好友个数 
        user.setFansNum(((IUserDao)baseDao).getMyFansCount(paramMap));//关注我的列表个数
        /*判断是否已关注*/
        if(CommonUtils.isNotEmptyOrNull(myHuiNo)) {
            Map<String,String> param = new HashMap<String, String>();
            param.put("myHuiNo", myHuiNo);
            param.put("otherHuiNo", huiNo);
            List<Fans> fans = this.userFollowDao.searchFriends(param);
            if(CommonUtils.isNotEmptyOrNull(fans) && fans.size()>1) {
                user.setFansToo(1);//互相关注
            } else {
                List<Fans> bullyFriends = this.userFollowDao.searchBullyFriends(param);
                if(CommonUtils.isNotEmptyOrNull(bullyFriends)) {
                    user.setFansToo(2);//已关注
                }
            }
        } else {
            user.setFansToo(0);
        }
        /*加图片*/
        user = addIcon(user);
        return user;
    }
    
    public List<Trends> getTrendses(String huiNo, Integer trendsNum)
    {
       Map<String,Object> paramMap  = new HashMap<String, Object>();
       paramMap.put("huiNo", huiNo);
       paramMap.put("huidaNo", huiNo);
       paramMap.put("start", 0);
       paramMap.put("size", trendsNum);
       /*答案列表*/
       List<Answer> answers = this.answerDao.selectAllByMap(paramMap);
       /*设定前台展示的创建时间*/
       if(CommonUtils.isNotEmptyOrNull(answers)) {
           for(Answer answer:answers) {
               answer.setCreateTimeStr(CommonUtils.formatTimeStamp(answer.getCreateTime(), CommonUtils.getLongDateFormat()));
//               /*加图片*/
//               if(1 == answer.getHasImg()) {
//                   SysFile sysFileParam = new SysFile();
//                   sysFileParam.setFileType(5);
//                   sysFileParam.setDataId(answer.getaId());
//                   List<SysFile> images = sysFileDao.selectAll(sysFileParam);
//                   if(CommonUtils.isNotEmptyOrNull(images)) {
//                       ImageTwo imageTwo = new ImageTwo();
//                       for(SysFile image:images) {
//                           if(1==image.getSeqId()) {
//                               imageTwo.setImgLt(image);
//                           }
//                           if(2==image.getSeqId()) {
//                               imageTwo.setImgOri(image);
//                           }
//                       }
//                       answer.setImageTwo(imageTwo);
//                   }
//               }
           }
       }
       /*问题列表*/
       List<Question> questions = this.questionDao.getAll(paramMap);
       if(CommonUtils.isNotEmptyOrNull(questions)) {
           for(Question question:questions) {
               /*处理特殊字符*/
               question = FrontUtils.handlerSpecWord(question);
               /*设定前台展示的创建时间*/
               question.setCreateTimeStr(CommonUtils.formatTimeStamp(question.getCreateTime(), CommonUtils.getLongDateFormat()));
//               /*加图片*/
//               if(1 == question.getHasImg()) {
//                   List<SysFile> images = this.questionDao.selectImagesByQId(question.getqId());
//                   if(CommonUtils.isNotEmptyOrNull(images)) {
//                       ImageTwo imageTwo = new ImageTwo();
//                       for(SysFile image:images) {
//                           if(1==image.getSeqId()) {
//                               imageTwo.setImgLt(image);
//                           }
//                           if(2==image.getSeqId()) {
//                               imageTwo.setImgOri(image);
//                           }
//                       }
//                       question.setImageTwo(imageTwo);
//                   }
//               }
           }
       }
       
       List<Integer> times4Compare = new ArrayList<Integer>();
       if(CommonUtils.isNotEmptyOrNull(answers)) {
           for(Answer answer:answers) {
               times4Compare.add(answer.getCreateTime());
           }
       }
       if(CommonUtils.isNotEmptyOrNull(questions)) {
           for(Question question:questions) {
               times4Compare.add(question.getCreateTime());
           }
       }
       if(CommonUtils.isNotEmptyOrNull(times4Compare)) {
           Collections.sort(times4Compare);
           Collections.reverse(times4Compare);
       }
       
       List<Trends> trendses = new ArrayList<Trends>();
       
       if(CommonUtils.isNotEmptyOrNull(times4Compare)) {
            if (times4Compare.size() > trendsNum)
            {
                times4Compare = times4Compare.subList(0, trendsNum);
            }
            /*组装回答*/
            if (CommonUtils.isNotEmptyOrNull(answers))
            {
                for (Answer answer : answers)
                {
                    for (Integer time4Compare : times4Compare)
                    {
                        
                        if (time4Compare.equals(answer.getCreateTime()))
                        {
                            Trends trends = new Trends();
                            trends.setTrendsTitle("回答");
                            trends.setTrendsDesc(answer.getAnswerDesc());
                            if (answer.getHasImg() == 1)
                            {
                                trends.setHasImg(1);
                                SysFile sysFileParam = new SysFile();
                                sysFileParam.setFileType(5);
                                sysFileParam.setDataId(answer.getaId());
                                List<SysFile> images = sysFileDao.selectAll(sysFileParam);
                                if (CommonUtils.isNotEmptyOrNull(images))
                                {
                                    for (SysFile image : images)
                                    {
                                        if (1 == image.getSeqId())
                                        {
                                            trends.setImgLt(image);
                                        }
                                        if (2 == image.getSeqId())
                                        {
                                            trends.setImgOri(image);
                                        }
                                    }
                                }
                            }
                            trends.setCreateTimeStr(answer.getCreateTimeStr());
                            Question question  = questionDao.selectById(answer.getQuestionId());
                            trends.setGradeSubject(question.getGrade().getGradeName()+question.getSubject().getSubName());
//                            trends.setAnswerNum(question.getAnswerNum());
                            trends.setLinkUrl("questionDetailQuestion.hui?questionIdParam="+question.getqId());
                            trendses.add(trends);
                        }
                    }
                }
            }
            /*组装问题*/
            if (CommonUtils.isNotEmptyOrNull(questions))
            {
                for (Question question : questions)
                {
                    for (Integer time4Compare : times4Compare)
                    {
                        if (time4Compare.equals(question.getCreateTime()))
                        {
                            Trends trends = new Trends();
                            trends.setTrendsTitle("提问");
                            trends.setTrendsDesc(question.getQuestionDesc());
                            if (question.getHasImg() == 1)
                            {
                                trends.setHasImg(1);
                                List<SysFile> images = this.questionDao.selectImagesByQId(question.getqId());
                                if (CommonUtils.isNotEmptyOrNull(images))
                                {
                                    for (SysFile image : images)
                                    {
                                        if (1 == image.getSeqId())
                                        {
                                            trends.setImgLt(image);
                                        }
                                        if (2 == image.getSeqId())
                                        {
                                            trends.setImgOri(image);
                                        }
                                    }
                                }
                            }
                            trends.setCreateTimeStr(question.getCreateTimeStr());

                            trends.setGradeSubject(question.getGrade().getGradeName()
                                + question.getSubject().getSubName());
                            trends.setAnswerNum(question.getAnswerNum());
                            trends.setLinkUrl("questionDetailQuestion.hui?questionIdParam="+question.getqId());
                            trendses.add(trends);
                            break;
                        }
                    }
                }
            }
        }
        return trendses;
    } 
    
   
    public List<User> getUsersByNickName(String nickName)
    {
        return getUsersByNickName(nickName, null);
    }
    
    public List<User> getUsersByNickName(String nickName, Integer isTeacher) {
    	User userParam = new User();
        userParam.setNickName(nickName);
        userParam.setIsTeacher(isTeacher);
        List<User> users = ((IUserDao)baseDao).selectAll(userParam);
        if(CommonUtils.isNotEmptyOrNull(users)) {
            for(User user:users) {
                user.setUserPwd("");
                /*加图片*/
                user = addIcon(user);
                user = FrontUtils.handlerSpecWord(user);
            }
            if(users.size()>20) {
                users = users.subList(0, 20);
            }
        }
        return users;
    }
    
   
    public Integer minusBean(String huiNo, Integer beanNum)
    {
        Map<String ,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("beanNum", beanNum);
        return ((IUserDao)baseDao).minusBean(paramMap);
    }
    
   
    public Integer plusBean(String huiNo, Integer beanNum)
    {
        Map<String ,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("beanNum", beanNum);
        return ((IUserDao)baseDao).plusBean(paramMap);
    }

   
    public Integer modifyUserInfo(Integer userId, String nickName, Integer sex, Integer birthDate, Integer constellation,
        String homeStr, String residenceStr, Integer gradeId, String school)
    {
        User user = selectById(userId);
        user.setNickName(nickName);
        user.setSex(sex);
        user.setBirthDate(birthDate);
        user.setConstellation(constellation);
        user.setHomeStr(homeStr);
        user.setResidenceStr(residenceStr);
        user.setGradeId(gradeId);
        user.setSchool(school);
        return ((IUserDao)baseDao).update(user);
    }
    
   
    public Boolean isNicknameExists(String nickName)
    {
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("nickName", nickName);
        Integer count = ((IUserDao)baseDao).isNicknameExists(paramMap);
        if(CommonUtils.isNotEmptyOrNull(count) && count>0) {
            return true;
        }
        return false;
    }
    
    public User getUser(String huiNo) {
    	User userParam = new User();
        userParam.setHuiNo(huiNo);
//        userParam.setIsTeacher(0);
        List<User> users = ((IUserDao)baseDao).selectByKey(userParam);
        if(CommonUtils.isEmptyOrNull(users)) {
            return new User();
        }
        User user = users.get(0);
        if(CommonUtils.isEmptyOrNullOr0OrFalse(user.getBeanNum())) {
        	user.setBeanNum(0);
        }
    	return user;
    }
    
    public Integer presentBean4Login1Day(User userInfo) {
    	try {
			if(!beanFlowService.isRecivedToday(userInfo.getHuiNo(), 4)) {
				final int BEAN_NUM = 20;
				/*剩余汇豆*/
			    Integer leftBeanNum = beanFlowService.getLastLeftBeanNum(userInfo.getHuiNo());
			    if(CommonUtils.isEmptyOrNullOr0OrFalse(leftBeanNum)) {
			    	leftBeanNum = getUser(userInfo.getHuiNo()).getBeanNum();
			    }
			    /*添加汇豆*/
			    plusBean(userInfo.getHuiNo(), BEAN_NUM);
			    
			    /*汇豆流水*/
			    BeanFlow beanFlow = new BeanFlow();
			    beanFlow.setBeanNum(BEAN_NUM);
			    beanFlow.setLeftBeanNum(leftBeanNum+BEAN_NUM);//设置剩余汇豆
			    beanFlow.setBfSn(CommonUtils.randomByUUID(10));
			    beanFlow.setFlowTime(CommonUtils.getCurTime());
			    beanFlow.setHuiNo(userInfo.getHuiNo());
			    beanFlow.setInOut(1);
			    beanFlow.setType(4);
			    beanFlow.setStatus(1);
			    beanFlowService.saveEntity(beanFlow);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    	return 1;
    }
    
	public List<User> selectByKey(User pk) {
		List<User> users = super.selectByKey(pk);
		if(CommonUtils.isNotEmptyOrNull(users)) {
			for(User user:users) {
				/*加图片*/
		        user = addIcon(user);
			}
		}
		return users;
	}
    
    @Async
    public void removePhoneCode(HttpSession session) {
    	try {
			Thread.sleep(60000);
			session.removeAttribute("phoneCode");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Override
	public User login3(Integer type, String name, String phone, String userIcon,String userIconLt,
			String sex, String birthdate, String commonkey) {
		User user = new User();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if (CommonUtils.isEmptyOrNull(commonkey)) {
			return null;
		}
		paramMap.put("commonKey", commonkey);
		List<CommonLogin> commonLogins = commonLoginDao
				.selectAllByMap(paramMap);
		if (CommonUtils.isNotEmptyOrNull(commonLogins)) {
			String huiNo = commonLogins.get(0).getHuiNo();
			user = getUser(huiNo);
			user = addIcon(user);
			boolean isChange = false;
			String sexStr = "";
			String birthdateStr= "";
			if(CommonUtils.isNotEmptyOrNullOr0OrFalse(user.getBirthDate())) {
				birthdateStr= CommonUtils.getIntDate(user.getBirthDate());
			}
			if(1==user.getSex()) {
				sexStr = "男";
			}
			if(2==user.getSex()) {
				sexStr = "女";
			}
			if(!name.equals(user.getNickName())) {
				isChange = true;
				user.setNickName(name);
			}
			if(!phone.equals(user.getMobile())) {
				isChange = true;
				user.setMobile(phone);
			}
			if(!sex.equals("")||user.getSex()!=0) {
				if(!sexStr.equals(sex)) {
					isChange = true;
					if ("男".equals(sex)) {
						user.setSex(1);
					} else if ("女".equals(sex)) {
						user.setSex(2);
					} else {
						user.setSex(0);
					}
				}
			}
			if(!birthdate.equals("")||user.getBirthDate()!=0) {
				if(!birthdateStr.equals(birthdate)) {
					isChange = true;
					if(CommonUtils.isNotEmptyOrNull(birthdate)) {
						user.setBirthDate(CommonUtils.getDateInt(birthdate));
					} else {
						user.setBirthDate(0);
					}
				}
			}
			if(isChange) {
				updateEntity(user);
			}
			if(CommonUtils.isEmptyOrNull(userIcon)) {//头像原图
				userIcon = "";
			}
			if(!userIcon.equals(user.getImageTwo().getImgLt().getFileName())) {
				SysFile sysFile = new SysFile();
	            sysFile.setFileType(1);
	            sysFile.setDataId(user.getUserId());
	            sysFile.setSeqId(2);
	            List<SysFile> sysFiles = sysFileDao.selectByKey(sysFile);
	            if(CommonUtils.isNotEmptyOrNull(sysFiles)) {
	            	SysFile img = sysFiles.get(0);
	            	img.setFileName(userIcon);
	            	this.sysFileDao.update(img);
	            } else {
	            	SysFile img = new SysFile();
	            	img.setFileType(1);
	            	img.setDataId(user.getUserId());
	            	img.setFileName(userIcon);
	            	img.setFilePath("");
	            	img.setPathType(1);
	            	img.setSeqId(2);
	                this.sysFileDao.save(img);
	            }
			}
			if(CommonUtils.isEmptyOrNull(userIconLt)) {//头像缩略图
				userIconLt = "";
			}
			if(!userIconLt.equals(user.getImageTwo().getImgLt().getFileName())) {
				SysFile sysFile = new SysFile();
	            sysFile.setFileType(1);
	            sysFile.setDataId(user.getUserId());
	            sysFile.setSeqId(1);
	            List<SysFile> sysFiles = sysFileDao.selectByKey(sysFile);
	            if(CommonUtils.isNotEmptyOrNull(sysFiles)) {
	            	SysFile img = sysFiles.get(0);
	            	img.setFileName(userIconLt);
	            	this.sysFileDao.update(img);
	            } else {
	            	SysFile img = new SysFile();
	            	img.setFileType(1);
	            	img.setDataId(user.getUserId());
	            	img.setFileName(userIconLt);
	            	img.setFilePath("");
	            	img.setPathType(1);
	            	img.setSeqId(1);
	                this.sysFileDao.save(img);
	            }
			}
		} else {
//			User userParam = new User();
//			if(CommonUtils.isNotEmptyOrNull(name)) {//保证昵称唯一性
//				userParam.setNickName(name);
//				List<User> users = ((IUserDao)baseDao).selectByKey(userParam);
//				if(CommonUtils.isNotEmptyOrNull(users)) {
//					return null;
//				}
//			}
//			if(CommonUtils.isNotEmptyOrNull(phone)) {//保证手机号唯一性
//				userParam.setMobile(phone);
//				List<User> users = ((IUserDao)baseDao).selectByKey(userParam);
//				if(CommonUtils.isNotEmptyOrNull(users)) {
//					return null;
//				}
//			}
			String huiNo = FrontUtils.generateHuiNo();
			user.setUserPwd("--");
			user.setHuiNo(huiNo);
			user.setAcceptNo(0);
			user.setAnswerNo(0);
			user.setBeanNum(0);
			user.setCreateTime(CommonUtils.getCurTime());
			user.setSchool("");
			user.setIsTeacher(0);
			user.setStatus(0);
			user.setNickName(CommonUtils.filterDangerString(name.trim()));
			user.setMobile(CommonUtils.filterDangerString(phone.trim()));
			if ("男".equals(sex)) {
				user.setSex(1);
			} else if ("女".equals(sex)) {
				user.setSex(2);
			} else {
				user.setSex(0);
			}
			user.setBirthDate(CommonUtils.getDateInt(birthdate));
			Integer userId =  saveEntity(user);
    		
    		CommonLogin commonLoginParam = new CommonLogin();
    		commonLoginParam.setCommonKey(commonkey);
    		commonLoginParam.setCreateTime(CommonUtils.getCurTime());
    		commonLoginParam.setHuiNo(huiNo);
    		commonLoginParam.setType(type);
    		commonLoginDao.save(commonLoginParam);
    		
    		SysFile sysFile = new SysFile();
            sysFile.setFileType(1);
            sysFile.setDataId(userId);
            sysFile.setFileName(userIcon);
            sysFile.setFilePath("");
            sysFile.setPathType(1);
            sysFile.setSeqId(2);
            this.sysFileDao.save(sysFile);
            sysFile.setFileName(userIconLt);
            sysFile.setFilePath("");
            sysFile.setPathType(1);
            sysFile.setSeqId(1);
            this.sysFileDao.save(sysFile);
    	}
    	return user;
    }
    
}
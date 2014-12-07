package com.hui.common.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IAnswerDao;
import com.hui.common.dao.IOnlineDurationDao;
import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.ITeacherInfoDao;
import com.hui.common.dao.IUserDao;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.OnlineDuration;
import com.hui.common.entity.Page;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.User;
import com.hui.common.entity.teacherList;
import com.hui.common.service.ITeacherInfoService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.PaginationConfig;

@Service("teacherInfoServiceImpl")
public class TeacherInfoServiceImpl extends BaseServiceImpl<TeacherInfo, TeacherInfo> implements ITeacherInfoService
{
    @Autowired
    private ITeacherInfoDao teacherInfoDao;
    @Autowired
    private IOnlineDurationDao onlineDurationDao;
    @Autowired
    private IAnswerDao answerDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ISysFileDao sysFileDao;
    @Autowired
    private IUserService userService;
    
   
	public Page<TeacherInfo> getTeacherInfos(Integer pageNo, Integer size,
			String huiNo, String teacherName, Integer onlineStatus,
			Integer subjectId, Integer gradeId)
    {
        Page<TeacherInfo> page = new Page<TeacherInfo>();
        page.setPage(pageNo);
        page.setSize(size);
        Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("teacherName", teacherName);
        paramMap.put("onlineStatus", onlineStatus);
        paramMap.put("subjectId", subjectId);
        paramMap.put("gradeId", gradeId);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        List<TeacherInfo> teacherInfos = this.teacherInfoDao.selectAllByMap(paramMap);
        if(CommonUtils.isNotEmptyOrNull(teacherInfos)) {
            for(TeacherInfo teacherInfo:teacherInfos) {
                /*设置教师头衔*/
                if(0 == teacherInfo.getTeacherTitle()) {
                    teacherInfo.setTeacherTitleStr("家教");
                }
                if(1 == teacherInfo.getTeacherTitle()) {
                    teacherInfo.setTeacherTitleStr("教师");
                }
                if(2 == teacherInfo.getTeacherTitle()) {
                    teacherInfo.setTeacherTitleStr("高级教师");
                }
                if(3 == teacherInfo.getTeacherTitle()) {
                    teacherInfo.setTeacherTitleStr("特级教师");
                }
                
                User userParam = new User();
                userParam.setHuiNo( teacherInfo.getHuiNo());
                List<User> users = userDao.selectByKey(userParam);
                if(CommonUtils.isNotEmptyOrNull(users)) {
                    User user = users.get(0);
                    /*加图片*/
                    user = addIcon(user);
                    teacherInfo.setUser(user);
                }
                teacherInfo = FrontUtils.handlerSpecWord(teacherInfo);
            }
        }
        Integer count = this.teacherInfoDao.getCount(paramMap);
        page.setDatas(teacherInfos);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
	
	public List<TeacherInfo> getTeacherInfos(String huiNo, String teacherName,
			Integer onlineStatus, Integer subjectId, Integer gradeId) {
		Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("teacherName", teacherName);
        paramMap.put("onlineStatus", onlineStatus);
        paramMap.put("subjectId", subjectId);
        paramMap.put("gradeId", gradeId);
        List<TeacherInfo> teacherInfos = this.teacherInfoDao.selectAllByMap(paramMap);
//        if(CommonUtils.isNotEmptyOrNull(teacherInfos)) {
//        	for(TeacherInfo teacherInfo:teacherInfos) {
//        		User userParam = new User();
//                userParam.setHuiNo(teacherInfo.getHuiNo());
//                List<User> users = userDao.selectByKey(userParam);
//                if(CommonUtils.isNotEmptyOrNull(users)) {
//                    User user = users.get(0);
//                    teacherInfo.setUser(user);
//                }
//        	}
//        }
		return teacherInfos;
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
    
   
    public TeacherInfo teacherInfoDetail(Integer teacherId)
    {
        TeacherInfo teacherInfo = teacherInfoDao.selectById(teacherId);
        /*总在线时长*/
        Integer onlineTime = onlineDurationDao.countOnlineTime(teacherId);
        if(CommonUtils.isNotEmptyOrNull(onlineTime)) {
            teacherInfo.setOnlineTime(onlineTime);
            /*在线时长设置为前台显示的形式*/
            if(onlineTime<60) {
                teacherInfo.setOnlineTimeStr(onlineTime+"秒");
            } else if(onlineTime<3600) {
                if(onlineTime%60>0) {
                    teacherInfo.setOnlineTimeStr((onlineTime/60)+"分"+(onlineTime%60)+"秒");
                } else {
                    teacherInfo.setOnlineTimeStr((onlineTime/60)+"分");
                }
            } else {
                if(onlineTime%3600==0){
                    teacherInfo.setOnlineTimeStr((onlineTime/3600)+"小时");
                } else if(onlineTime%60==0){
                    teacherInfo.setOnlineTimeStr((onlineTime/3600)+"小时"+((onlineTime%3600)/60)+"分");
                } else {
                    teacherInfo.setOnlineTimeStr((onlineTime/3600)+"小时"+((onlineTime%3600)/60)+"分"+(onlineTime%60)+"秒");
                }
            }
        }
        /*设置教师头衔*/
        if(CommonUtils.isNotEmptyOrNull(teacherInfo.getTeacherTitle())) {
            if(0 == teacherInfo.getTeacherTitle()) {
                teacherInfo.setTeacherTitleStr("家教");
            }
            if(1 == teacherInfo.getTeacherTitle()) {
                teacherInfo.setTeacherTitleStr("教师");
            }
            if(2 == teacherInfo.getTeacherTitle()) {
                teacherInfo.setTeacherTitleStr("高级教师");
            }
            if(3 == teacherInfo.getTeacherTitle()) {
                teacherInfo.setTeacherTitleStr("特级教师");
            }
        }
        /*采纳率 */
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", teacherInfo.getHuiNo());
        Integer answersNum = answerDao.getCount(paramMap);
        paramMap.put("isFavorate", 1);
        Integer acceptNum = answerDao.getCount(paramMap);
        BigDecimal acceptRate = new BigDecimal(0);
        if(CommonUtils.isNotEmptyOrNullOr0OrFalse(acceptNum) && CommonUtils.isNotEmptyOrNullOr0OrFalse(answersNum)) {
            acceptRate = new BigDecimal(acceptNum).divide(new BigDecimal(answersNum),new MathContext(4, RoundingMode.HALF_DOWN));
        }
        teacherInfo.setAcceptRate(acceptRate);
        return teacherInfo;
    }
    
	
	public List<TeacherInfo> getDutyTeacherList() {
		return teacherInfoDao.getDutyTeacherList();
	}
	
	
	public Integer fitOnLine(Integer teacherId)
	{
	    try
        {
            TeacherInfo teacherInfo = teacherInfoDao.selectById(teacherId);
            teacherInfo.setOnlineStatus(1);
            teacherInfoDao.update(teacherInfo);
            OnlineDuration onlineDuration = new OnlineDuration();
            onlineDuration.setTeacherId(teacherId);
            onlineDuration.setBeginTime(CommonUtils.getCurTime());
            onlineDurationDao.save(onlineDuration);
        }
        catch (Exception e)
        {
            return 0;
        }
	    return 1;
	}
	
	
	public Integer fitOffLine(Integer teacherId)
	{
	    try
        {
            TeacherInfo teacherInfo = teacherInfoDao.selectById(teacherId);
            teacherInfo.setOnlineStatus(0);
            teacherInfoDao.update(teacherInfo);
            OnlineDuration onlineDuration = new OnlineDuration();
            onlineDuration.setTeacherId(teacherId);
            onlineDuration.setEndTime(CommonUtils.getCurTime());
            Integer beginTime = onlineDurationDao.getRecentOnlineTime(teacherId);
            onlineDuration.setBeginTime(beginTime);
            onlineDurationDao.update(onlineDuration);
        }
        catch (Exception e)
        {
            return 0;
        }
	    return 1;
	}

	
	public List getTeacherInformation(String huidaNo,String beginTime,String endTime) {
		Map<String,String> paramMap  = new HashMap<String, String>();
        paramMap.put("huidaNo", huidaNo);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
		return teacherInfoDao.getTeacherInformation(paramMap);
	}
	
	
	public List<TeacherInfo> list(TeacherInfo teacherInfo)
	{
	    List<TeacherInfo> teacherInfos = teacherInfoDao.selectAllE(teacherInfo);
        if(CommonUtils.isNotEmptyOrNull(teacherInfos)) {
            for(TeacherInfo teacher:teacherInfos) {
                /*设置教师头衔*/
                if(0 == teacher.getTeacherTitle()) {
                    teacher.setTeacherTitleStr("家教");
                }
                if(1 == teacher.getTeacherTitle()) {
                    teacher.setTeacherTitleStr("教师");
                }
                if(2 == teacher.getTeacherTitle()) {
                    teacher.setTeacherTitleStr("高级教师");
                }
                if(3 == teacher.getTeacherTitle()) {
                    teacher.setTeacherTitleStr("特级教师");
                }
                /*设置年级、学科名称*/
                teacher.setGradeStr(teacher.getGrade().getGradeName());
                teacher.setSubjectStr(teacher.getSubject().getSubName());
                /*设置在线状态*/
                if(0 == teacher.getOnlineStatus()) {
                    teacher.setOnlineStatusStr("离线");
                }
                if(1 == teacher.getOnlineStatus()) {
                    teacher.setOnlineStatusStr("在线");
                }
            }
        }
	    return teacherInfos;
	}
	
	public Integer getCount(TeacherInfo teacherInfo)
	{
	    return teacherInfoDao.getCountE(teacherInfo);
	}
	
	
	public Boolean isTeacher(String huiNo)
	{
	    Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("huiNo", huiNo);
	    TeacherInfo teacherInfo = new TeacherInfo();
	    teacherInfo.setHuiNo(huiNo);
	    Integer count = getCount(paramMap);
	    if(CommonUtils.isNotEmptyOrNullOr0OrFalse(count)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public Integer saveTeacher(TeacherInfo teacherInfo) {
		User user = userService.getUser(teacherInfo.getHuiNo());
		user.setIsTeacher(1);
		userService.updateEntity(user);//更新新指定用户为老师
		return saveEntity(teacherInfo);
	}
	
	public Integer modifyTeacher(TeacherInfo teacherInfo, String oriHuiNo) {
		User userOri = userService.getUser(oriHuiNo);//更新原来用户为不是老师
		userOri.setIsTeacher(0);
		userService.updateEntity(userOri);
		User user = userService.getUser(teacherInfo.getHuiNo());//更新新指定用户为老师
		user.setIsTeacher(1);
		userService.updateEntity(user);
		return updateEntity(teacherInfo);
	}
	
	public User pasteTeacher(User user) {
		if(CommonUtils.isNotEmptyOrNullOr0OrFalse(user.getIsTeacher())&&user.getIsTeacher() == 1) {
			Map<String,Object> paramMap  = new HashMap<String, Object>();
	        paramMap.put("huiNo", user.getHuiNo());
	        List<TeacherInfo> teacherInfos = this.teacherInfoDao.selectAllByMap(paramMap);
	        if(CommonUtils.isNotEmptyOrNull(teacherInfos)) {
	        	user.setTeacher(teacherInfos.get(0));
	        }
		}
		return user;
	}

	public List<teacherList> getTeachersList(String huidaNo, String gradeText,
			String courseText, int page) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("gradeName", gradeText);
		paramMap.put("subjectName", courseText);
		int offset = PaginationConfig.pageOffSet(Integer.valueOf(page));
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(offset));
		
		List<teacherList> teacherList= teacherInfoDao.getTeachersList(paramMap);
		return teacherList;
	}
}

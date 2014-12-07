package com.hui.common.action.mobileaction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Subject;
import com.hui.common.service.IGradeService;
import com.hui.common.service.IGradeSubjectService;
import com.hui.common.service.ISubjectService;
import com.hui.common.webservice.EntityView.GradeInfoEntityView;
import com.hui.common.webservice.EntityView.GradeSubjectEntityView;

public class GradeInfoAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1504333281471311376L;
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private IGradeSubjectService gradeSubjectService;
	
	@Autowired
	private ISubjectService subjectService;
	
	private List resultList;
	
	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	/**
	 * 获得年级列表
	 */
	public String execute(){
		List<Grade> list = gradeService.list();
		resultList = new ArrayList();
		GradeInfoEntityView view = null;
		for(Grade grade:list){
			view = new GradeInfoEntityView();
			view.setGradeId(grade.getGradeId());
			view.setText(grade.getGradeName());
			resultList.add(view);
		}
		return SUCCESS;
	}
	
	/**
	 * 获得学科列表
	 */
	public String getCourseListByGradeId(){
		HttpServletRequest request = getRequest();
		String gradeId = request.getParameter("gradeId");
		if("0".equals(gradeId)){
			//获取全部学科
			List<Subject> subjectList = subjectService.list();
			resultList = new ArrayList();
			GradeSubjectEntityView entityView = null;
			for(Subject sub : subjectList){
				entityView = new GradeSubjectEntityView();
				entityView.setCourseId(sub.getSubId());
				entityView.setText(sub.getSubName());
				resultList.add(entityView);
			}
			
		}else{
			resultList = gradeSubjectService.getCourseListByGradeId(gradeId);
		}
		return SUCCESS;
	}

}

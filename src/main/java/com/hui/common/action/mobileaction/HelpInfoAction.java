package com.hui.common.action.mobileaction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Help;
import com.hui.common.entity.SysFile;
import com.hui.common.service.ISysFileService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.ConvertObjectField;
import com.hui.common.webservice.IHelpInfoWService;
import com.hui.common.webservice.EntityView.HelpEntityView;

public class HelpInfoAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7949928660634429589L;
	
	@Autowired
	private IHelpInfoWService helpInfoWService;
	
	@Autowired
	private ISysFileService sysFileService;
	
	private List resultList;
	
	public List getResultList() {
		return resultList;
	}

	public IHelpInfoWService getHelpInfoWService() {
		return helpInfoWService;
	}

	public void setHelpInfoWService(IHelpInfoWService helpInfoWService) {
		this.helpInfoWService = helpInfoWService;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String execute(){
		HttpServletRequest request = getRequest();
		String page = request.getParameter("page");
		resultList = helpInfoWService.getHelpPagingList(page);
		return SUCCESS;
	}
	
	/**
	 * 获得帮助详细内容
	 * @return
	 */
	public String getHelpDetail(){
		HttpServletRequest request = getRequest();
		String helpId = request.getParameter("helpId");
		List helpList = helpInfoWService.getHelpDetail(helpId);
		HelpEntityView view = null;
		resultList = new ArrayList();
		for(int i=0;i<helpList.size();i++){
			view = new HelpEntityView();
			Help help = (Help) helpList.get(i);
			view.setHelpId(help.getHelpId());
			view.setHelpDescription(help.getHelpDesc());
			//查询图片url
			SysFile sysFile = new SysFile();
			sysFile.setDataId(view.getHelpId());
			sysFile.setFileType(6);
			sysFile.setSeqId(1);
			List ll = sysFileService.selectByKey(sysFile);
			if(ll.size() > 0){
				sysFile = (SysFile) ll.get(0);
				if(sysFile != null){
					String filePath = sysFile.getFilePath();
					String fileName = sysFile.getFileName();
					String path = CommonUtils.getFilePathPrefix() +filePath+"/" + fileName;
					view.setThumbnailUrl(path);
				}
			}
			//获取原图
			sysFile.setSeqId(2);
			ll = sysFileService.selectByKey(sysFile);
			if(ll.size() > 0){
				sysFile = (SysFile) ll.get(0);
				if(sysFile != null){
					view.setImageId(String.valueOf(sysFile.getFileId()));
				}
			}
			ConvertObjectField.convertNullValue2Empty(view);
			resultList.add(view);
		}
		return SUCCESS;
	}

}

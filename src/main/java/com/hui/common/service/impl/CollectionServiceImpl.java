package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.ICollectionDao;
import com.hui.common.dao.IQuestionDao;
import com.hui.common.entity.Collection;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.User;
import com.hui.common.service.ICollectionService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;

@Service("collectionService")
public class CollectionServiceImpl extends BaseServiceImpl<Collection, Collection> implements ICollectionService
{
    @Autowired
    private ICollectionDao collectionDao;
    @Autowired
    private IQuestionDao questionDao;
    
   
    public Integer collectQuestion(User user, Question question)
    {
        try
        {
            if (CommonUtils.isEmptyOrNull(user) || CommonUtils.isEmptyOrNull(question))
            {
                return 0;
            }
            if(hasCollect(user, question)) {
                return 2;//已经收藏过了
            }
            Collection collection = new Collection();
            collection.setCreateTime(CommonUtils.getCurTime());
            collection.setDataId(question.getqId());
            collection.setHuiNo(user.getHuiNo());
            collection.setType(0);
            collectionDao.save(collection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    
   
    public Integer unCollectQuestion(User user,Question question)
    {
        try
        {
            if(CommonUtils.isEmptyOrNull(user) || CommonUtils.isEmptyOrNull(question)) {
                return 0;
            }
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("huiNo", user.getHuiNo());
            paramMap.put("dataId", question.getqId());
            paramMap.put("type", 0);
            collectionDao.delete(paramMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    
   
    public Boolean hasCollect(User user, Question question)
    {
        if (CommonUtils.isEmptyOrNull(user) || CommonUtils.isEmptyOrNull(question))
        {
            return false;
        }
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huiNo", user.getHuiNo());
        paramMap.put("dataId", question.getqId());
        paramMap.put("type", 0);
        Integer count = collectionDao.getCount(paramMap);
        if(CommonUtils.isEmptyOrNullOr0OrFalse(count)) {
            return false;
        }
        return true;
    }

   
    public Page<Collection> getCollections(Integer pageNo, String huiNo)
    {
        Page<Collection> page = new Page<Collection>();
        page.setPage(pageNo);
        
        Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<Collection> collections = this.collectionDao.selectAllByMap(paramMap);
        if(CommonUtils.isNotEmptyOrNull(collections)) {
            for(Collection collection:collections) {
                Question question = questionDao.selectById(collection.getDataId());
                /*处理特殊字符*/
                question = FrontUtils.handlerSpecWord(question);
                /*加图片*/
                if(1 == question.getHasImg()) {
                    List<SysFile> images = this.questionDao.selectImagesByQId(question.getqId());
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
                        question.setImageTwo(imageTwo);
                    }
                }
                /*设定前台展示的创建时间*/
                question.setCreateTimeStr(CommonUtils.formatTimeStamp(question.getCreateTime(), CommonUtils.getLongDateFormat()));
                /*针对手机端型号处理*/
                String clientStyle = question.getClientStyle();
                if(CommonUtils.isNotEmptyOrNull(clientStyle)) {
                    String[] clientStyleArr = clientStyle.split("===");
                    if(clientStyleArr.length>1) {
                        question.setClientStyle(clientStyleArr[0]);
                    }
                }
                collection.setQuestion(question);
            }
        }
        Integer count = this.collectionDao.getCount(paramMap);
        page.setDatas(collections);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
    
}

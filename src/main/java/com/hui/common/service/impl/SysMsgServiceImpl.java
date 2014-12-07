package com.hui.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IMessageDao;
import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.ISysMsgDao;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Page;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.SysMsg;
import com.hui.common.entity.User;
import com.hui.common.runner.SendSysMsgRunner;
import com.hui.common.runner.SendSysMsgThreadPool;
import com.hui.common.service.ISysMsgService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;

@Service("sysMsgService")
public class SysMsgServiceImpl extends BaseServiceImpl<SysMsg, SysMsg> implements ISysMsgService
{
    
    @Autowired
    private ISysMsgDao sysMsgDao;
    
    @Autowired
    private ISysFileDao sysFileDao;
    
    @Autowired
    private IMessageDao messageDao;
    
    @Autowired
    private IUserService userService;
    
    public SysMsg getSysMsgDetail(Integer sysMsgId)
    {
        SysMsg sysMsg = this.sysMsgDao.selectById(sysMsgId);
        /*获取图片*/
        if (CommonUtils.isNotEmptyOrNull(sysMsg))
        {
            if (CommonUtils.isNotEmptyOrNullOr0OrFalse(sysMsg.getCreateTime()))
            {
                sysMsg.setCreateTimeStr(CommonUtils.formatTimeStamp(sysMsg.getCreateTime(),
                    CommonUtils.getLongDateFormat()));
            }
            String sysTitle = sysMsg.getSysMsgTitle();
            String sysDesc = sysMsg.getSysMsgDesc();
            sysMsg.setSysMsgTitle(CommonUtils.isEmptyOrNull(sysTitle) ? "" : CommonUtils.filterDangerStringLt(sysTitle));
            sysMsg.setSysMsgDesc(CommonUtils.isEmptyOrNull(sysDesc) ? "" : CommonUtils.filterDangerStringLt(sysDesc));
            if (1 == sysMsg.getHasImg())
            {
                SysFile sysFileParam = new SysFile();
                sysFileParam.setFileType(7);
                sysFileParam.setDataId(sysMsg.getSysMsgId());
                sysFileParam.setSeqId(null);
                List<SysFile> imgs = this.sysFileDao.selectByKey(sysFileParam);
                List<SysFile> imgOris = new ArrayList<SysFile>();
                List<SysFile> imgLts = new ArrayList<SysFile>();
                if (CommonUtils.isNotEmptyOrNull(imgs))
                {
                    for (SysFile img : imgs)
                    {
                        if (img.getSeqId() == 1)
                        {
                            imgLts.add(img);
                        }
                        if (img.getSeqId() == 2)
                        {
                            imgOris.add(img);
                        }
                    }
                    List<ImageTwo> imageTwos = new ArrayList<ImageTwo>();
                    if (CommonUtils.isNotEmptyOrNull(imgOris) && CommonUtils.isNotEmptyOrNull(imgLts))
                    {
                        for (SysFile imgOri : imgOris)
                        {
                            for (SysFile imgLt : imgLts)
                            {
                                if (imgOri.getDataId().equals(imgLt.getDataId()))
                                {
                                    ImageTwo imageTwo = new ImageTwo();
                                    imageTwo.setImgLt(imgLt);
                                    imageTwo.setImgOri(imgOri);
                                    imageTwos.add(imageTwo);
                                }
                            }
                        }
                    }
                    sysMsg.setImageTwos(imageTwos);
                }
                //                FrontUtils.handlerSpecWord(sysMsg);
            }
        }
        return sysMsg;
    }
    
    public Page<SysMsg> getSysMsgs(Integer pageNo)
    {
        Page<SysMsg> page = new Page<SysMsg>();
        page.setPage(pageNo);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<SysMsg> sysMsgs = this.sysMsgDao.selectAllByMap(paramMap);
        /*设定前台展示的创建时间*/
        if (CommonUtils.isNotEmptyOrNull(sysMsgs))
        {
            for (SysMsg sysMsg : sysMsgs)
            {
                sysMsg.setCreateTimeStr(CommonUtils.formatTimeStamp(sysMsg.getCreateTime(),
                    CommonUtils.getLongDateFormat()));
                sysMsg = FrontUtils.handlerSpecWord(sysMsg);
            }
        }
        Integer count = this.sysMsgDao.getCount(paramMap);
        page.setDatas(sysMsgs);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
    public List<SysMsg> list(SysMsg sysMsg)
    {
        List<SysMsg> sysMsgs = sysMsgDao.selectAllE(sysMsg);
        if (CommonUtils.isNotEmptyOrNull(sysMsgs))
        {
            List<String[]> sysMsgStatuses = CommonUtils.getSysDataArray("sysmsg_status");
            for (SysMsg msg : sysMsgs)
            {
                msg.setCreateTimeStr(CommonUtils.formatTimeStamp(msg.getCreateTime(), CommonUtils.getLongDateFormat()));
                for (String[] sysMsgStatus : sysMsgStatuses)
                {
                    if (String.valueOf(msg.getStatus() == null ? 0 : msg.getStatus()).equals(sysMsgStatus[0]))
                    {
                        msg.setStatusStr(sysMsgStatus[1]);
                        msg = FrontUtils.handlerSpecWord(msg);
                    }
                }
            }
        }
        return sysMsgs;
    }
    
    public Integer saveEntity(SysMsg sysMsg)
    {
        try
        {
            Integer sysMsgId = baseDao.save(sysMsg);
            sysMsg.setSysMsgId(sysMsgId);
            
            User user = new User();
            Integer cnt = userService.getCount(user);
            Integer page = 1;
            Integer size = 500;
            do
            {
                SendSysMsgThreadPool.getThreadPool().execute(new SendSysMsgRunner(this, sysMsg, page, size));
                cnt = cnt - size;
                page++;
            } while (cnt > 0);
        }
        catch (Exception e)
        {
            return 0;
        }
        return 1;
    }
    
    public void saveSysMsg2User(SysMsg sysMsg, Integer page, Integer size)
    {
        try
        {
            List<Msg> msgs = new ArrayList<Msg>();
            int start = (page - 1) * size;
            
            User temp = new User();
            temp.setStart(start);
            temp.setSize(size);
            
            List<User> users = userService.list(temp);
            Msg msg = null;
            String msgContent = null;
            for (User user : users)
            {
                msg = new Msg();
                msg.setHuiNo(user.getHuiNo());
                msg.setCreateTime(CommonUtils.getNowInt());
                msg.setIsRead(0);
                msgContent = sysMsg.getSysMsgTitle().trim();
                
                if (sysMsg.getSysMsgTitle().trim().length() > FrontUtils.MSG_STR_EX_LENGTH)
                {
                    msgContent = sysMsg.getSysMsgTitle().trim().substring(0, FrontUtils.MSG_STR_EX_LENGTH) + "…";
                }
                msg.setMsgContent(msgContent);
                msg.setMsgInfoId(sysMsg.getSysMsgId());
                msg.setMsgType(4);
                msgs.add(msg);
            }
            if (CommonUtils.isNotEmptyOrNull(msgs))
            {
                Thread.sleep(size);
                messageDao.batchSave(msgs);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public Integer getCount(SysMsg pk)
    {
        return sysMsgDao.getCountE(pk);
    }
    
}
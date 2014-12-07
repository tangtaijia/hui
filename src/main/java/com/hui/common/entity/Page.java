package com.hui.common.entity;

import java.util.List;

import com.hui.common.utils.CommonUtils;

/**
 * 分页
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Page<T>
{
    /**
     * 数据
     */
    private List<T> datas;
    /**
     * limit 起始
     */
    private Integer start;
    /**
     * limit size
     */
    private Integer size;
    
    /**
     * 起始页
     */
    private Integer page;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer totalPages;
    
    public List<T> getDatas()
    {
        return datas;
    }
    public void setDatas(List<T> datas)
    {
        this.datas = datas;
    }
    
    public void setStart(Integer start)
    {
        this.start = start;
    }
    public Integer getPage()
    {
        if(CommonUtils.isEmptyOrNullOr0OrFalse(page)) {
            page = 1;
        }
        return page;
    }
    public void setPage(Integer page)
    {
        this.page = page;
    }
    public Integer getTotal()
    {
        return total;
    }
    public void setTotal(Integer total)
    {
        this.total = total;
    }
    public Integer getTotalPages()
    {
        totalPages = total%getSize() == 0?total/getSize():(total/getSize()+1);
        return totalPages;
    }
    public void setTotalPages(Integer totalPages)
    {
        this.totalPages = totalPages;
    }
    public Integer getStart()
    {
     // 每页的开始记录  第一页为1  第二页为number +1    
        start = (getPage() - 1) * getSize();
        return start;
    }
    public Integer getSize()
    {
        if(CommonUtils.isEmptyOrNullOr0OrFalse(size)) {
            size = 5;
        }
        return size;
    }
    public void setSize(Integer size)
    {
        this.size = size;
    }
}

package com.hui.common.entity;

public enum ConstellationEnum
{
    Aquarius(1, "水瓶座","01/21-02/18"), Pisces(2, "双鱼座","02/19-03/20"), Aries(3, "白羊座","03/21-04/20"), Taurus(4, "金牛座","04/21-05/20"), Gemini(5, "双子座","05/21-06/21"), Cancer(6, "巨蟹座","06/22-07/22"), Leo(7,
        "狮子座","07/23-08/22"), Virgo(8, "处女座","08/23-09/22"), Libra(9, "天秤座","09/23-10/22"), Scorpio(10, "天蝎座","10/23-11/21"), Sagittarius(11, "射手座","11/22-12/21"), Capricornus(12, "摩羯座","12/22-01/20");
    
    private Integer index;
    
    private String name;
    
    private String range;
    
    private ConstellationEnum(Integer index, String name,String range)
    {
        this.index = index;
        this.name = name;
        this.range = range;
    }
    
    public Integer getIndex()
    {
        return index;
    }

    public void setIndex(Integer index)
    {
        this.index = index;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRange()
    {
        return range;
    }

    public void setRange(String range)
    {
        this.range = range;
    }
    
    /**
     * 根据索引获取相应的星座名称
     * <功能详细描述>
     * @param index
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getNameByIndex(Integer index)
    {
        for (ConstellationEnum constellationEnum : ConstellationEnum.values())
        {
            if (index.equals(constellationEnum.index))
            {
                return constellationEnum.name;
            }
        }
        return "";
    }

    public static void main(String[] args)
    {
//        for(ConstellationEnum constellationEnum:ConstellationEnum.values()) {
//            System.out.println(constellationEnum.index+constellationEnum.name+constellationEnum.range);
//        }
        System.out.println(getNameByIndex(1));
    }
}

package com.hui.common.service;

import com.hui.common.entity.Collection;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.User;

public interface ICollectionService extends IBaseService<Collection, Collection>
{
    Integer collectQuestion(User user,Question question);
    
    Boolean hasCollect(User user,Question question);
    
    Page<Collection> getCollections(Integer pageNo, String huiNo);
    
    Integer unCollectQuestion(User user,Question question);
}

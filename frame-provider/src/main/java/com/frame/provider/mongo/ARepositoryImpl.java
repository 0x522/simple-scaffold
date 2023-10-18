package com.frame.provider.mongo;

import cn.hutool.core.collection.CollectionUtil;
import com.frame.provider.model.entity.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: chenyuntao
 **/
@Component
public class ARepositoryImpl implements ARepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveA(A a) {
        mongoTemplate.save(a);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateA(A a) {
        Query query = new Query(Criteria.where("id").is(a.getId()));
        List<A> list = mongoTemplate.find(query, A.class);
        if (CollectionUtil.isNotEmpty(list)) {
            for (A obj : list) {
                deleteById(obj.getId());
            }
        }
        mongoTemplate.save(a);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, A.class);
    }
}

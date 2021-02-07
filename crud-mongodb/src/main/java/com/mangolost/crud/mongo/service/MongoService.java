package com.mangolost.crud.mongo.service;

import com.mangolost.crud.common.MyPage;
import com.mangolost.crud.mongo.enity.TEmployee;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class MongoService {

    @Autowired
    @Qualifier("mongoLocalTemplate")
    private MongoTemplate mongoLocalTemplate;

    /**
     *
     * @param id
     * @return
     */
    public TEmployee get(String id) {
        List<TEmployee> list = mongoLocalTemplate.query(TEmployee.class).inCollection("t_employee")
                .matching(Query.query(Criteria.where("_id").is(id).and("recordStatus").is(true)))
                .all();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public MyPage<TEmployee> list(int pageNo, int pageSize) {

        MyPage<TEmployee> myPage = new MyPage<>(pageNo, pageSize);
        long cnt = mongoLocalTemplate.query(TEmployee.class).inCollection("t_employee")
                .matching(Query.query(Criteria.where("recordStatus").is(true)))
                .count();
        if (cnt > 0) {
            int totalCount = (int) cnt;
            int totalPages = totalCount <= pageSize ? 1 : 1 + (totalCount - 1) / pageSize;
            int offset = (pageNo - 1) * pageSize;
            List<TEmployee> list = mongoLocalTemplate.query(TEmployee.class).inCollection("t_employee")
                    .matching(Query.query(Criteria.where("recordStatus").is(true))
                                    .with(Sort.by(Sort.Direction.ASC, "_id"))
                                    .skip(offset)
                                    .limit(pageSize))
                    .all();
            myPage.setTotalCount(totalCount);
            myPage.setTotalPages(totalPages);
            myPage.setRecords(list);
        }
        return myPage;
    }

    /**
     *
     * @param vo
     * @return
     */
    public TEmployee add(TEmployee vo) {
        vo.setId(null);
        vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        vo.setRecordStatus(true);
        return mongoLocalTemplate.insert(vo, "t_employee");
    }

    /**
     *
     * @param vo
     * @return
     */
    public TEmployee update(TEmployee vo) {
        String id = vo.getId();
        Document document = new Document()
                .append("createTime", vo.getCreateTime())
                .append("updateTime", new Date())
                .append("recordStatus", true)
                .append("employeeNo", vo.getEmployeeNo())
                .append("employeeName", vo.getEmployeeName())
                .append("age", vo.getAge())
                .append("position", vo.getPosition())
                .append("degree", vo.getDegree())
                .append("remark", vo.getRemark());

        mongoLocalTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)), Update.fromDocument(document), "t_employee");
        return get(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public void delete(String id) {
        mongoLocalTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)), Update.update("recordStatus", false).set("updateTime", new Date()), "t_employee");
    }

}

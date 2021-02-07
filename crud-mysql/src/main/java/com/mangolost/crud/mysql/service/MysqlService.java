package com.mangolost.crud.mysql.service;

import com.mangolost.crud.common.MyPage;
import com.mangolost.crud.mysql.entity.TEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class MysqlService {

    @Autowired
    @Qualifier("mysqlLocalTemplate")
    private JdbcTemplate mysqlLocalTemplate;

    /**
     *
     * @param id
     * @return
     */
    public TEmployee get(int id) {
        String sql = "select * from t_employee where id = ? and record_status = 1 limit 1";
        List<TEmployee> list = mysqlLocalTemplate.query(sql, new BeanPropertyRowMapper<>(TEmployee.class), id);
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
        String sqlCount = "select count(*) from t_employee where record_status = 1";
        int totalCount = mysqlLocalTemplate.queryForObject(sqlCount, Integer.class);
        if (totalCount > 0) {
            int totalPages = totalCount <= pageSize ? 1 : 1 + (totalCount - 1) / pageSize;
            String sqlData = "select * from t_employee " +
                    "where record_status = 1 " +
                    "order by id asc " +
                    "limit ? offset ?";
            int offset = (pageNo - 1) * pageSize;
            List<TEmployee> list = mysqlLocalTemplate.query(sqlData, new BeanPropertyRowMapper<>(TEmployee.class), pageSize, offset);
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
        String sql = "insert into t_employee (employee_no, employee_name, age, position, degree, remark) values (?,?,?,?,?,?)";
        Object[] args = new Object[] {
               vo.getEmployeeNo(),
               vo.getEmployeeName(),
               vo.getAge(),
               vo.getPosition(),
               vo.getDegree(),
               vo.getRemark()
        };
        mysqlLocalTemplate.update(sql, args);
        int id = mysqlLocalTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        return get(id);
    }

    /**
     *
     * @param vo
     * @return
     */
    public TEmployee update(TEmployee vo) {
        String sql = "update t_employee " +
                "set employee_no = ?, employee_name = ?, age = ?, position = ?, degree = ?, remark = ? " +
                "where id = ?";
        Object[] args = new Object[] {
                vo.getEmployeeNo(),
                vo.getEmployeeName(),
                vo.getAge(),
                vo.getPosition(),
                vo.getDegree(),
                vo.getRemark(),
                vo.getId()
        };
        mysqlLocalTemplate.update(sql, args);
        return get(vo.getId());
    }

    /**
     *
     * @param id
     * @return
     */
    public void delete(int id) {
        String sql = "update t_employee set record_status = 0 where id = ?";
        mysqlLocalTemplate.update(sql, id);
    }

}

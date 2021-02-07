package com.mangolost.crud.mysql.controller;

import com.mangolost.crud.common.CommonResult;
import com.mangolost.crud.common.MyPage;
import com.mangolost.crud.mysql.entity.TEmployee;
import com.mangolost.crud.mysql.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/api/mysql")
public class MysqlController {


    @Autowired
    private MysqlService mysqlService;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("get")
    public CommonResult get(@RequestParam int id) {
        CommonResult commonResult = new CommonResult();
        TEmployee entity = mysqlService.get(id);
        return commonResult.setData(entity);
    }

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("list")
    public CommonResult list(@RequestParam(required = false, defaultValue = "1") int pageNo,
                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        CommonResult commonResult = new CommonResult();
        MyPage<TEmployee> myPage = mysqlService.list(pageNo, pageSize);
        return commonResult.setData(myPage);
    }

    /**
     *
     * @param vo
     * @return
     */
    @PostMapping("add")
    public CommonResult add(@RequestBody TEmployee vo) {
        CommonResult commonResult = new CommonResult();
        TEmployee entity = mysqlService.add(vo);
        return commonResult.setData(entity);
    }

    /**
     *
     * @param vo
     * @return
     */
    @PostMapping("update")
    public CommonResult update(@RequestBody TEmployee vo) {
        CommonResult commonResult = new CommonResult();
        int id = vo.getId();
        TEmployee tEmployee = mysqlService.get(id);
        if (tEmployee == null) {
            return commonResult.setCodeAndMessage(404, "对象不存在");
        }
        TEmployee entity = mysqlService.update(vo);
        return commonResult.setData(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("delete")
    public CommonResult delete(@RequestParam int id) {
        CommonResult commonResult = new CommonResult();
        mysqlService.delete(id);
        return commonResult;
    }

}

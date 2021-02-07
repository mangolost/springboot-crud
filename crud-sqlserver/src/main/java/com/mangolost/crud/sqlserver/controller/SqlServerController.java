package com.mangolost.crud.sqlserver.controller;

import com.mangolost.crud.common.CommonResult;
import com.mangolost.crud.common.MyPage;
import com.mangolost.crud.sqlserver.entity.TEmployee;
import com.mangolost.crud.sqlserver.service.SqlServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/api/sqlserver")
public class SqlServerController {


    @Autowired
    private SqlServerService sqlServerService;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("get")
    public CommonResult get(@RequestParam int id) {
        CommonResult commonResult = new CommonResult();
        TEmployee entity = sqlServerService.get(id);
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
        MyPage<TEmployee> myPage = sqlServerService.list(pageNo, pageSize);
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
        TEmployee entity = sqlServerService.add(vo);
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
        TEmployee tEmployee = sqlServerService.get(id);
        if (tEmployee == null) {
            return commonResult.setCodeAndMessage(404, "对象不存在");
        }
        TEmployee entity = sqlServerService.update(vo);
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
        sqlServerService.delete(id);
        return commonResult;
    }

}

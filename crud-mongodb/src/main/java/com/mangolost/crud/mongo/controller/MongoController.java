package com.mangolost.crud.mongo.controller;

import com.mangolost.crud.common.CommonResult;
import com.mangolost.crud.common.MyPage;
import com.mangolost.crud.mongo.enity.TEmployee;
import com.mangolost.crud.mongo.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/api/mongo")
public class MongoController {

    @Autowired
    private MongoService mongoService;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("get")
    public CommonResult get(@RequestParam String id) {
        CommonResult commonResult = new CommonResult();
        TEmployee entity = mongoService.get(id);
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
        MyPage<TEmployee> myPage = mongoService.list(pageNo, pageSize);
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
        TEmployee entity = mongoService.add(vo);
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
        String id = vo.getId();
        TEmployee tEmployee = mongoService.get(id);
        if (tEmployee == null) {
            return commonResult.setCodeAndMessage(404, "对象不存在");
        }
        vo.setCreateTime(tEmployee.getCreateTime());
        TEmployee entity = mongoService.update(vo);
        return commonResult.setData(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("delete")
    public CommonResult delete(@RequestParam String id) {
        CommonResult commonResult = new CommonResult();
        mongoService.delete(id);
        return commonResult;
    }

}

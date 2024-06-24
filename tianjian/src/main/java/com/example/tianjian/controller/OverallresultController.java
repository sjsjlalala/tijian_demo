package com.example.tianjian.controller;


import com.example.tianjian.entity.Overallresult;
import com.example.tianjian.service.IOverallresultService;
import com.example.tianjian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/overallresult")
public class OverallresultController {
    @Autowired
    private IOverallresultService overallresultService;

    @RequestMapping("/getList")
    public Result getList()
    {
        Overallresult overallresult = new Overallresult();
        overallresult.setContent(null);
        overallresult.setTitle(null);
        overallresult.setOrderId(null);
        overallresult.setOrId(null);
        return Result.ok(overallresult);
    }
}

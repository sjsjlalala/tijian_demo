package com.example.tianjian.controller;


import com.example.tianjian.service.ICidetailedreportService;
import com.example.tianjian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/cidetailedreport")
public class CidetailedreportController {
    @Autowired
    private ICidetailedreportService cidetailedreportService;

    @GetMapping("/getList")
    public Result getList(Integer orderId)
    {
        return cidetailedreportService.getList(orderId);
    }
}

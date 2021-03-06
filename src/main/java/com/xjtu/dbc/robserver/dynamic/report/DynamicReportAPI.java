package com.xjtu.dbc.robserver.dynamic.report;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.dynamic.home.DynamicHomeService;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 动态举报模块的API
 * @author 陈邦昕
 */
@RestController
@RequestMapping("/dynamic/report")
public class DynamicReportAPI {

    @Resource
    private DynamicReportService dynamicReportService;

    /**
     * 举报动态或评论
     * @param dynamicReportDto
     * @return Result(msg, dynamicReportDto)
     */
    @PostMapping("")
    public Result report(@RequestBody DynamicReportDto dynamicReportDto) {

        Integer maxId = dynamicReportService.getMaxReportId();
        dynamicReportDto.setReportid(maxId + 1);
        dynamicReportDto.setReportstatus(500);  //举报状态初始设置为500

        dynamicReportService.addReport(dynamicReportDto);
        return Result.success("提交举报成功!", dynamicReportDto);
    }
}

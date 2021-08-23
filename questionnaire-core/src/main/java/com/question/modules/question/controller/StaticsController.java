package com.question.modules.question.controller;

import com.alibaba.fastjson.JSONObject;
import com.question.modules.question.service.IQuestionBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 数据统计 前端控制器
 *
 * @author 问卷星球团队
 * @since 2021-08-21
 */
@Api(tags = "数据统计相关接口")
@RestController
@Transactional
@RequestMapping("/api/v1/statics")
public class StaticsController {
    @Autowired
    private IQuestionBankService questionBankService;

    @ApiOperation("获取问卷数据")
    @PostMapping("/get")
    public JSONObject getQuestionnaireStatics(@RequestParam  Integer questionnaireId) {
        return questionBankService.createQuestionnaireStatic(questionnaireId);
    }
}

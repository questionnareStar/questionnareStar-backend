package com.question.modules.question.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.question.modules.question.entities.Questionnaire;
import com.question.modules.question.entities.req.CreateQuestionReq;
import com.question.modules.question.entities.req.QueryQuestionnairePageReq;
import com.question.modules.question.service.IQuestionBankService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 题库表 前端控制器
 *
 * @author 问卷星球团队
 * @since 2021-08-21
 */
@RestController
@RequestMapping("/question-bank")
public class QuestionBankController {
    @Autowired
    private IQuestionBankService questionBankService;

    @ApiOperation("创建问卷时新建问题，无需考虑问题次序，不用传参")
    @PostMapping("/creating/create")
    public Integer createQuestionWhenCreating(@RequestBody @Validated CreateQuestionReq req) {
        return questionBankService.createQuestion(req);
    }

    @ApiOperation("创建问卷时删除问题")
    @DeleteMapping("/creating/delete")
    public boolean deleteByIdWhenCreating(@RequestParam Integer id,@RequestParam Integer type){
        return questionBankService.deleteByIdAndTypeWhenEditing(id,type);
    }

    @ApiOperation("编辑问卷时新建问题，若插入在中间，则后面的问题顺序自动后移")
    @DeleteMapping("/editing/create")
    public Boolean createQuestionWhenEditing(@RequestBody @Validated CreateQuestionReq req) {
        return questionBankService.createQuestionWhenEditing(req);
    }

    @ApiOperation("编辑问卷时删除问题")
    @DeleteMapping("/editing/delete")
    public boolean deleteByIdWhenEditing(@RequestParam Integer id,@RequestParam Integer type){
        return questionBankService.deleteByIdAndTypeWhenEditing(id,type);
    }
}

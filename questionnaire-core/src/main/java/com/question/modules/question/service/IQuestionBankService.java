package com.question.modules.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.question.modules.question.entities.QuestionBank;
import com.question.modules.question.entities.req.CreateQuestionReq;

/**
 * 题库表 服务类
 *
 * @author 问卷星球团队
 * @since 2021-08-21
 */
public interface IQuestionBankService extends IService<QuestionBank> {

    Integer createQuestion(CreateQuestionReq req);

    boolean deleteByIdAndTypeWhenCreating(Integer id, Integer type);

    boolean deleteByIdAndTypeWhenEditing(Integer id, Integer type);

    Boolean createQuestionWhenEditing(CreateQuestionReq req);
}

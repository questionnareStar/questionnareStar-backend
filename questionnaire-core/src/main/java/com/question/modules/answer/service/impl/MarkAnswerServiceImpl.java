package com.question.modules.answer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.modules.answer.entities.MarkAnswer;
import com.question.modules.answer.mapper.MarkAnswerMapper;
import com.question.modules.answer.service.IMarkAnswerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评分题回答 服务实现类
 * </p>
 *
 * @author 问卷星球团队
 * @since 2021-08-23
 */
@Service
public class MarkAnswerServiceImpl extends ServiceImpl<MarkAnswerMapper, MarkAnswer> implements IMarkAnswerService {

}

package com.question.modules.answer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.question.modules.answer.entities.MultiChoiceAnswer;
import com.question.modules.answer.mapper.MultiChoiceAnswerMapper;
import com.question.modules.answer.service.IMultiChoiceAnswerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 多选题回答 服务实现类
 * </p>
 *
 * @author 问卷星球团队
 * @since 2021-08-23
 */
@Service
public class MultiChoiceAnswerServiceImpl extends ServiceImpl<MultiChoiceAnswerMapper, MultiChoiceAnswer> implements IMultiChoiceAnswerService {

}

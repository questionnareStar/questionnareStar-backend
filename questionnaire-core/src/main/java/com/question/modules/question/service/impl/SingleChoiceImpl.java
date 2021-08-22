package com.question.modules.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.modules.question.entities.SingleChoice;
import com.question.modules.question.mapper.SingleChoiceMapper;
import com.question.modules.question.service.ISingleChoiceService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author 问卷星球团队
 * @since 2021-08-20
 */
@Service
public class SingleChoiceImpl extends ServiceImpl<SingleChoiceMapper, SingleChoice> implements ISingleChoiceService {
}

package com.question.modules.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.modules.question.entities.MultiChoice;
import com.question.modules.question.mapper.MultiChoiceMapper;
import com.question.modules.question.service.IMultiChoiceService;
import org.springframework.stereotype.Service;

@Service
public class MultiChoiceImpl extends ServiceImpl<MultiChoiceMapper, MultiChoice> implements IMultiChoiceService {
    @Override
    public Integer InsertMultiChoice(MultiChoice multiChoice) {
        return baseMapper.insert(multiChoice);
    }
}

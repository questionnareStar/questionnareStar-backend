package com.question.modules.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.modules.question.entities.Mark;
import com.question.modules.question.mapper.MarkMapper;
import com.question.modules.question.service.IMarkService;
import org.springframework.stereotype.Service;

@Service
public class MarkImpl extends ServiceImpl<MarkMapper, Mark> implements IMarkService {
    @Override
    public Integer InsertMark(Mark mark) {
        return baseMapper.insert(mark);
    }
}

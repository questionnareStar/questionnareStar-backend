package com.question.modules.answer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.question.modules.answer.entities.SingleChoiceAnswer;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 单选题回答 Mapper 接口
 * </p>
 *
 * @author 问卷星球团队
 * @since 2021-08-23
 */
@Mapper
public interface SingleChoiceAnswerMapper extends BaseMapper<SingleChoiceAnswer> {

}

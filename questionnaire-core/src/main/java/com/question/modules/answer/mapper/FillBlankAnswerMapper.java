package com.question.modules.answer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.question.modules.answer.entities.FillBlankAnswer;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 填空题回答 Mapper 接口
 * </p>
 *
 * @author 问卷星球团队
 * @since 2021-08-23
 */
@Mapper
public interface FillBlankAnswerMapper extends BaseMapper<FillBlankAnswer> {

}

package com.question.modules.answer.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 多选题回答
 * </p>
 *
 * @author 问卷星球团队
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("multi_choice_answer")
@ApiModel(value="MultiChoiceAnswer对象", description="多选题回答")
public class MultiChoiceAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "所属题目")
    private Integer questionId;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "回答用户id")
    private Integer answerUser;

    @ApiModelProperty(value = "回答时间")
    private Date answerTime;


}

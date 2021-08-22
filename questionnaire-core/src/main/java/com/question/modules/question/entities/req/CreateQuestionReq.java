package com.question.modules.question.entities.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="创建问题请求对象", description="创建问题请求对象")
public class CreateQuestionReq {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "问题类型不能为空")
    @ApiModelProperty(value = "问题类型")
    private Integer type;

    @NotNull(message = "问题题目不能为空")
    @ApiModelProperty(value = "问题题目")
    private String question;

    @NotNull(message = "问题是否必填不能为空")
    @ApiModelProperty(value = "是否必填")
    private Boolean required;

    @ApiModelProperty(value = "问题选项")
    private List<String> choices;

    @ApiModelProperty(value = "问题总分")
    private Integer maxScore;

    @ApiModelProperty(value = "问题次序")
    private Integer sequence;

    @ApiModelProperty(value = "问题次序")
    private Integer Questionnaire;


}

package com.question.modules.question.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.modules.question.common.ChoiceConvertUtil;
import com.question.modules.question.entities.*;
import com.question.modules.question.entities.req.CreateQuestionReq;
import com.question.modules.question.mapper.QuestionBankMapper;
import com.question.modules.question.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题库表 服务实现类
 * </p>
 *
 * @author 问卷星球团队
 * @since 2021-08-21
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements IQuestionBankService {

    @Autowired
    private IFillBlankService fillBlankService;

    @Autowired
    private IMarkService markService;

    @Autowired
    private IMultiChoiceService multiChoiceService;

    @Autowired
    private ISingleChoiceService singleChoiceService;

    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Override
    public Integer createQuestion(CreateQuestionReq req) {
        switch (req.getType()){
            case 1:{
                FillBlank fillBlank = new FillBlank();
                fillBlank.setQuestion(req.getQuestion());
                fillBlank.setIsDeleted(false);
                fillBlank.setRequired(req.getRequired());
                fillBlankService.save(fillBlank);
                return fillBlank.getId();
            }
            case 2:{
                Mark mark = new Mark();
                mark.setMaxScore(req.getMaxScore());
                mark.setQuestion(req.getQuestion());
                mark.setIsDeleted(false);
                mark.setRequired(req.getRequired());
                markService.save(mark);
                return mark.getId();
            }
            case 3:{
                MultiChoice multiChoice = new MultiChoice();
                multiChoice.setQuestion(req.getQuestion());
                multiChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
                multiChoice.setIsDeleted(false);
                multiChoice.setRequired(req.getRequired());
                multiChoiceService.save(multiChoice);
                return multiChoice.getId();
            }
            case 4:{
                SingleChoice singleChoice = new SingleChoice();
                singleChoice.setQuestion(req.getQuestion());
                singleChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
                singleChoice.setIsDeleted(false);
                singleChoice.setRequired(req.getRequired());
                singleChoiceService.save(singleChoice);
                return singleChoice.getId();
            }
            default:return -1;
        }
    }

    @Override
    public Boolean createQuestionWhenEditing(CreateQuestionReq req) {
        //将顺序在该问题之后的该问卷的问题全部后移
        QueryWrapper<QuestionBank> wrapper = new QueryWrapper<QuestionBank>();
        wrapper.eq("questionnaire_id",req.getQuestionnaire());
        wrapper.ge("sequence",req.getSequence());
        List<QuestionBank> questionBanks = questionBankMapper.selectList(wrapper);
        questionBanks.forEach(questionBank -> {
            questionBank.setSequence(questionBank.getSequence()+1);
            updateById(questionBank);
        });
        //插入新的问题
        int topic_id = -1;
        switch (req.getType()){
            case 1:{
                FillBlank fillBlank = new FillBlank();
                fillBlank.setQuestion(req.getQuestion());
                fillBlank.setIsDeleted(false);
                fillBlank.setRequired(req.getRequired());
                fillBlankService.save(fillBlank);
                topic_id = fillBlank.getId();
                break;
            }
            case 2:{
                Mark mark = new Mark();
                mark.setMaxScore(req.getMaxScore());
                mark.setQuestion(req.getQuestion());
                mark.setIsDeleted(false);
                mark.setRequired(req.getRequired());
                markService.save(mark);
                topic_id = mark.getId();
                break;
            }
            case 3:{
                MultiChoice multiChoice = new MultiChoice();
                multiChoice.setQuestion(req.getQuestion());
                multiChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
                multiChoice.setIsDeleted(false);
                multiChoice.setRequired(req.getRequired());
                multiChoiceService.save(multiChoice);
                topic_id = multiChoice.getId();
                break;
            }
            case 4:{
                SingleChoice singleChoice = new SingleChoice();
                singleChoice.setQuestion(req.getQuestion());
                singleChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
                singleChoice.setIsDeleted(false);
                singleChoice.setRequired(req.getRequired());
                singleChoiceService.save(singleChoice);
                topic_id = singleChoice.getId();
                break;
            }
            default:
                return false;
        }
        QuestionBank questionBank = new QuestionBank();
        questionBank.setSequence(req.getSequence());
        questionBank.setQuestionnaireId(req.getQuestionnaire());
        questionBank.setTopicId(topic_id);
        questionBank.setType(req.getType());
        save(questionBank);
        return true;
    }

    @Override
    public boolean deleteByIdAndTypeWhenCreating(Integer id, Integer type) {
        return deleteByIdAndType(id,type);
    }

    @Override
    public boolean deleteByQuestionnaireIdAndSequence(Integer questionnaireId, Integer sequence) {
        //1. 找到该问题
        QueryWrapper<QuestionBank> wrapper = new QueryWrapper<>();
        wrapper.eq("questionnaire_id",questionnaireId);
        wrapper.eq("sequence",sequence);
        QuestionBank targetQuestionBank = getOne(wrapper);
        //2. 将顺序在该问题之后的该问卷的问题全部前移
        wrapper = new QueryWrapper<QuestionBank>();
        wrapper.eq("questionnaire_id",questionnaireId);
        wrapper.gt("sequence",sequence);
        List<QuestionBank> questionBanks = questionBankMapper.selectList(wrapper);
        questionBanks.forEach(questionBank -> {
            questionBank.setSequence(questionBank.getSequence()-1);
            updateById(questionBank);
        });
        //3. 删除问题
        if(deleteByIdAndType(targetQuestionBank.getTopicId(),targetQuestionBank.getType())){
            //4. 删除题库表对应的项
            return removeById(targetQuestionBank);
        }
        return false;
    }

    public Boolean deleteByIdAndType(Integer id, Integer type) {
        switch (type){
            case 1:{
                return fillBlankService.removeById(id);
            }
            case 2:{
                return markService.removeById(id);
            }
            case 3:{
                return multiChoiceService.removeById(id);
            }
            case 4:{
                return singleChoiceService.removeById(id);
            }
            default:return false;
        }
    }
}

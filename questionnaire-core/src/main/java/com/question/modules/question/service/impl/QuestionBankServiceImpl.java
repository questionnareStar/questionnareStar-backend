package com.question.modules.question.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.modules.answer.entities.FillBlankAnswer;
import com.question.modules.answer.entities.MarkAnswer;
import com.question.modules.answer.entities.MultiChoiceAnswer;
import com.question.modules.answer.entities.SingleChoiceAnswer;
import com.question.modules.answer.mapper.FillBlankAnswerMapper;
import com.question.modules.answer.mapper.MarkAnswerMapper;
import com.question.modules.answer.mapper.MultiChoiceAnswerMapper;
import com.question.modules.answer.mapper.SingleChoiceAnswerMapper;
import com.question.modules.question.common.ChoiceConvertUtil;
import com.question.modules.question.entities.*;
import com.question.modules.question.entities.req.CreateQuestionReq;
import com.question.modules.question.entities.statics.FillBlankStatic;
import com.question.modules.question.entities.statics.MarkStatic;
import com.question.modules.question.entities.statics.MultiChoiceStatic;
import com.question.modules.question.entities.statics.SingleChoiceStatic;
import com.question.modules.question.mapper.QuestionBankMapper;
import com.question.modules.question.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private FillBlankAnswerMapper fillBlankAnswerMapper;

    @Autowired
    private MarkAnswerMapper markAnswerMapper;

    @Autowired
    private MultiChoiceAnswerMapper multiChoiceAnswerMapper;

    @Autowired
    private SingleChoiceAnswerMapper singleChoiceAnswerMapper;

    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Autowired
    private IQuestionnaireService questionnaireService;
//
//    @Override
//    public Integer createQuestion(CreateQuestionReq req) {
//        switch (req.getType()) {
//            case 1: {
//                FillBlank fillBlank = new FillBlank();
//                fillBlank.setQuestion(req.getQuestion());
//                fillBlank.setIsDeleted(false);
//                fillBlank.setRequired(req.getRequired());
//                fillBlankService.save(fillBlank);
//                return fillBlank.getId();
//            }
//            case 2: {
//                Mark mark = new Mark();
//                mark.setMaxScore(req.getMaxScore());
//                mark.setQuestion(req.getQuestion());
//                mark.setIsDeleted(false);
//                mark.setRequired(req.getRequired());
//                markService.save(mark);
//                return mark.getId();
//            }
//            case 3: {
//                MultiChoice multiChoice = new MultiChoice();
//                multiChoice.setQuestion(req.getQuestion());
//                multiChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
//                multiChoice.setIsDeleted(false);
//                multiChoice.setRequired(req.getRequired());
//                multiChoiceService.save(multiChoice);
//                return multiChoice.getId();
//            }
//            case 4: {
//                SingleChoice singleChoice = new SingleChoice();
//                singleChoice.setQuestion(req.getQuestion());
//                singleChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
//                singleChoice.setIsDeleted(false);
//                singleChoice.setRequired(req.getRequired());
//                singleChoiceService.save(singleChoice);
//                return singleChoice.getId();
//            }
//            default:
//                return -1;
//        }
//    }
//
//    @Override
//    public Boolean createQuestionWhenEditing(CreateQuestionReq req) {
//        //将顺序在该问题之后的该问卷的问题全部后移
//        QueryWrapper<QuestionBank> wrapper = new QueryWrapper<QuestionBank>();
//        wrapper.eq("questionnaire_id", req.getQuestionnaire());
//        wrapper.ge("sequence", req.getSequence());
//        List<QuestionBank> questionBanks = questionBankMapper.selectList(wrapper);
//        questionBanks.forEach(questionBank -> {
//            questionBank.setSequence(questionBank.getSequence() + 1);
//            updateById(questionBank);
//        });
//        //插入新的问题
//        int topic_id = -1;
//        switch (req.getType()) {
//            case 1: {
//                FillBlank fillBlank = new FillBlank();
//                fillBlank.setQuestion(req.getQuestion());
//                fillBlank.setIsDeleted(false);
//                fillBlank.setRequired(req.getRequired());
//                fillBlankService.save(fillBlank);
//                topic_id = fillBlank.getId();
//                break;
//            }
//            case 2: {
//                Mark mark = new Mark();
//                mark.setMaxScore(req.getMaxScore());
//                mark.setQuestion(req.getQuestion());
//                mark.setIsDeleted(false);
//                mark.setRequired(req.getRequired());
//                markService.save(mark);
//                topic_id = mark.getId();
//                break;
//            }
//            case 3: {
//                MultiChoice multiChoice = new MultiChoice();
//                multiChoice.setQuestion(req.getQuestion());
//                multiChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
//                multiChoice.setIsDeleted(false);
//                multiChoice.setRequired(req.getRequired());
//                multiChoiceService.save(multiChoice);
//                topic_id = multiChoice.getId();
//                break;
//            }
//            case 4: {
//                SingleChoice singleChoice = new SingleChoice();
//                singleChoice.setQuestion(req.getQuestion());
//                singleChoice.setChoices(ChoiceConvertUtil.ChoicesToString(req.getChoices()));
//                singleChoice.setIsDeleted(false);
//                singleChoice.setRequired(req.getRequired());
//                singleChoiceService.save(singleChoice);
//                topic_id = singleChoice.getId();
//                break;
//            }
//            default:
//                return false;
//        }
//        QuestionBank questionBank = new QuestionBank();
//        questionBank.setSequence(req.getSequence());
//        questionBank.setQuestionnaireId(req.getQuestionnaire());
//        questionBank.setTopicId(topic_id);
//        questionBank.setType(req.getType());
//        save(questionBank);
//        return true;
//    }

    @Override
    public JSONObject createQuestionnaireStatic(Integer questionnaireId) {
        StringBuilder stringBuilder = new StringBuilder();
        //加入对应的问卷信息
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        stringBuilder.append("{\"write_num\":").append(questionnaire.getWriteNum()).append(",\"end_time\":").append(questionnaire.getEndTime().getTime());
        //对应的题目库
        QueryWrapper<QuestionBank> wrapper = new QueryWrapper<>();
        wrapper.eq("questionnaire_id", questionnaireId);
        List<QuestionBank> questionBankList = baseMapper.selectList(wrapper);
        questionBankList.sort(new Comparator<QuestionBank>() {
            @Override
            public int compare(QuestionBank o1, QuestionBank o2) {
                return o2.getSequence() - o1.getSequence();
            }
        });
        //按顺序加入所有的题目信息
        stringBuilder.append(",\"questions\":[");
        questionBankList.forEach(questionBank -> {
            switch (questionBank.getType()){
                case 1:
                    //1. 找出对应的题目
                    FillBlank fillBlank = fillBlankService.getById(questionBank.getTopicId());
                    //2. 创建改题目的数据对象
                    FillBlankStatic fillBlankStatic = new FillBlankStatic(fillBlank.getQuestion());
                    fillBlankStatic.setTopic(fillBlank.getQuestion());
                    //3. 获取填空题的所有回答
                    QueryWrapper<FillBlankAnswer> fillBlankWrapper = new QueryWrapper<>();
                    fillBlankWrapper.eq("question_id",fillBlank.getId());
                    List<FillBlankAnswer> fillBlankAnswers = fillBlankAnswerMapper.selectList(fillBlankWrapper);
                    for (FillBlankAnswer fillBlankAnswer : fillBlankAnswers) {
                        fillBlankStatic.addAnswer(fillBlankAnswer.getAnswer());
                    }
                    stringBuilder.append(fillBlankStatic).append(",");
                    break;
                case 2:
                    //1. 找出对应的题目
                    Mark mark = markService.getById(questionBank.getTopicId());
                    //2. 创建改题目的数据对象
                    MarkStatic markStatic = new MarkStatic(mark.getQuestion(),mark.getMaxScore());
                    //3. 获取评分的所有回答的数量加入static里
                    for (int i = 0; i <= mark.getMaxScore(); i++) {
                        QueryWrapper<MarkAnswer> markWrapper = new QueryWrapper<>();
                        markWrapper.eq("question_id",mark.getId());
                        markWrapper.eq("answer",i);
                        List<MarkAnswer> markAnswers = markAnswerMapper.selectList(markWrapper);
                        markStatic.addScoreCount(i,markAnswers.size());
                    }
                    stringBuilder.append(markStatic).append(",");
                    break;
                case 3:
                    //1. 找出对应的题目
                    MultiChoice multiChoice = multiChoiceService.getById(questionBank.getTopicId());
                    //2. 创建改题目的数据对象
                    MultiChoiceStatic multiChoiceStatic = new MultiChoiceStatic(multiChoice.getQuestion());
                    //3. 获取所有选项
                    List<String> choices = JSONObject.parseArray(multiChoice.getChoices(),String.class);
                    //4. 创建所有选项，初始数量都是0
                    Map<String,Integer> multiScores = new HashMap<>();
                    for (String choice : choices) {
                        multiScores.put(choice,0);
                    }
                    //5. 获取所有选项的选择次数 加入Static
                    //5.1. 获取所有回答
                    QueryWrapper<MultiChoiceAnswer> multiWrapper = new QueryWrapper<>();
                    multiWrapper.eq("question_id",multiChoice.getId());
                    //5.2 将所有回答统计起来
                    List<MultiChoiceAnswer> multiChoiceAnswers = multiChoiceAnswerMapper.selectList(multiWrapper);
                    for (MultiChoiceAnswer multiChoiceAnswer : multiChoiceAnswers) {
                        List<String> multiAnswers = JSONObject.parseArray(multiChoiceAnswer.getAnswer(),String.class);
                        for (String multiAnswer : multiAnswers) {
                            multiScores.put(multiAnswer, multiScores.get(multiAnswer)+1);
                        }
                    }
                    for(String choice:multiScores.keySet()){
                        multiChoiceStatic.append(choice,multiScores.get(choice));
                    }
                    stringBuilder.append(multiChoiceStatic).append(",");
                    break;
                case 4:
                    //1. 找出对应的题目
                    SingleChoice singleChoice = singleChoiceService.getById(questionBank.getTopicId());
                    //2. 创建改题目的数据对象
                    SingleChoiceStatic singleChoiceStatic = new SingleChoiceStatic(singleChoice.getQuestion());
                    //3. 获取所有选项
                    List<String> singleChoices = JSONObject.parseArray(singleChoice.getChoices(),String.class);
                    //4. 创建所有选项，初始数量都是0
                    QueryWrapper<SingleChoiceAnswer> singleWrapper;
                    for (String choice : singleChoices) {
                        singleWrapper= new QueryWrapper<>();
                        //5. 找到该题的所有回答 统计起来
                        singleWrapper.eq("question_id",singleChoice.getId());
                        singleWrapper.eq("answer",choice);
                        List<SingleChoiceAnswer> singleChoiceAnswers = singleChoiceAnswerMapper.selectList(singleWrapper);
                        singleChoiceStatic.append(choice,singleChoiceAnswers.size());
                    }
                    stringBuilder.append(singleChoiceStatic).append(",");
                    break;
                default:
                    break;
            }
        });
        stringBuilder.append("]}");
        return JSON.parseObject(stringBuilder.toString());
    }
    @Override
    public boolean deleteByIdAndTypeWhenCreating(Integer id, Integer type) {
        return deleteByIdAndType(id, type);
    }

    @Override
    public boolean deleteByQuestionnaireIdAndSequence(Integer questionnaireId, Integer sequence) {
        //1. 找到该问题
        QueryWrapper<QuestionBank> wrapper = new QueryWrapper<>();
        wrapper.eq("questionnaire_id", questionnaireId);
        wrapper.eq("sequence", sequence);
        QuestionBank targetQuestionBank = getOne(wrapper);
        //2. 将顺序在该问题之后的该问卷的问题全部前移
        wrapper = new QueryWrapper<QuestionBank>();
        wrapper.eq("questionnaire_id", questionnaireId);
        wrapper.gt("sequence", sequence);
        List<QuestionBank> questionBanks = questionBankMapper.selectList(wrapper);
        questionBanks.forEach(questionBank -> {
            questionBank.setSequence(questionBank.getSequence() - 1);
            updateById(questionBank);
        });
        //3. 删除问题
        if (deleteByIdAndType(targetQuestionBank.getTopicId(), targetQuestionBank.getType())) {
            //4. 删除题库表对应的项
            return removeById(targetQuestionBank);
        }
        return false;
    }

    public Boolean deleteByIdAndType(Integer id, Integer type) {
        switch (type) {
            case 1: {
                return fillBlankService.removeById(id);
            }
            case 2: {
                return markService.removeById(id);
            }
            case 3: {
                return multiChoiceService.removeById(id);
            }
            case 4: {
                return singleChoiceService.removeById(id);
            }
            default:
                return false;
        }
    }
}

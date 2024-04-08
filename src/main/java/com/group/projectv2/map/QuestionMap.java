package com.group.projectv2.map;

import com.group.projectv2.dto.QuestionDTO;
import com.group.projectv2.entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMap {
    public Question dtoToEntity(QuestionDTO questionDTO){
        Question question = new Question();
        question.setTest_id(questionDTO.getTest_id());
        question.setN_th(questionDTO.getN_th());
        question.setTitle(questionDTO.getTitle());
        question.setOption1(questionDTO.getOption1());
        question.setOption2(questionDTO.getOption2());
        question.setOption3(questionDTO.getOption3());
        question.setOption4(questionDTO.getOption4());
        return question;
    }

    public List<Question> dtoToEntity(List<QuestionDTO> questionDTOS){
        return questionDTOS.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

    public QuestionDTO entityToDto(Question question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTest_id(question.getTest_id());
        questionDTO.setN_th(question.getN_th());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setOption1(question.getOption1());
        questionDTO.setOption2(question.getOption2());
        questionDTO.setOption3(question.getOption3());
        questionDTO.setOption4(question.getOption4());
        return questionDTO;
    }

    public List<QuestionDTO> entityToDto(List<Question> questions){
        return questions.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
}

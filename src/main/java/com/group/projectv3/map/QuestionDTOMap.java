package com.group.projectv3.map;

import com.group.projectv3.dto.QuestionDTO;
import com.group.projectv3.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionDTOMap {
    @Autowired
    private ModelMapper modelMapper;

    public Question QuestionDTOToQuestion(QuestionDTO questionDTO){
        return modelMapper.map(questionDTO, Question.class);
    }

    public QuestionDTO QuestionToQuestionDTO(Question question){
        return modelMapper.map(question, QuestionDTO.class);
    }
}

package com.group.projectv3.service;

import com.group.projectv3.dto.QuestionDTO;
import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.TestDTO;
import com.group.projectv3.model.Question;

import java.util.List;

public interface QuestionService {
    public ReqRes allQuestion(QuestionDTO questionDTO);
    public ReqRes addQuestion(QuestionDTO questionDTO);
    public ReqRes editQuestion(QuestionDTO questionDTO);
    public ReqRes deleteQuestion(QuestionDTO questionDTO);
    public ReqRes deleteQuestionByTestId(QuestionDTO questionDTO);
}

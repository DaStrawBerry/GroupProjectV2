package com.group.projectv3.service.implement;

import com.group.projectv3.dto.QuestionDTO;
import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.map.QuestionDTOMap;
import com.group.projectv3.model.Question;
import com.group.projectv3.repository.QuestionRepository;
import com.group.projectv3.repository.TestRepository;
import com.group.projectv3.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    private QuestionDTOMap map;
    @Autowired
    private QuestionRepository repository;
    @Autowired
    private TestRepository testRepo;
    @Override
    public ReqRes allQuestion(QuestionDTO questionDTO) {
        ReqRes resp = new ReqRes();
        try{
            if(questionDTO.getTestcode() != null && testRepo.findByCode(questionDTO.getTestcode()).isEmpty()){
                throw new Exception("Test: " + questionDTO.getTestcode() + " does not exits");
            }
            List<Question> questions = repository.findAllByTestcode(questionDTO.getTestcode());
            resp.setMessage("Found " + questions.size() + " questions");
            resp.setData(questions);
            resp.setStatusCode(200);
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes addQuestion(QuestionDTO questionDTO) {
        ReqRes resp = new ReqRes();
        try{
            Question question = map.QuestionDTOToQuestion(questionDTO);
            if(testRepo.findByCode(question.getTestcode()).isEmpty()){
               throw new Exception("Test: " + question.getTestcode() + " does not exits");
            }

            question.setNth(repository.findAllByTestcode(questionDTO.getTestcode()).size()+1);
            question.setCode("QUES" + question.getNth() + questionDTO.getTestcode());

            resp.setMessage("Add question successfully");
            resp.setData(repository.save(question));
            resp.setStatusCode(200);
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes editQuestion(QuestionDTO questionDTO) {
        ReqRes resp = new ReqRes();
        try{
            Question question = map.QuestionDTOToQuestion(questionDTO);
            if(testRepo.findByCode(question.getTestcode()).isEmpty()){
                throw new Exception("Test: " + question.getTestcode() + " does not exits");
            }
            Optional<Question> preq = repository.findByCode(question.getCode());
            if(preq.isEmpty()){
                throw new Exception("Question: " + question.getCode() + " does not exits");
            }

            question.setId(preq.get().getId());
            question.setCode(preq.get().getCode());
            question.setNth(preq.get().getNth());

            resp.setMessage("Edit question successfully");
            resp.setData(repository.save(question));
            resp.setStatusCode(200);
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes deleteQuestion(QuestionDTO questionDTO) {
        ReqRes resp = new ReqRes();
        try{
            Question question = map.QuestionDTOToQuestion(questionDTO);
            Optional<Question> preq = repository.findByCode(question.getCode());
            if(preq.isEmpty()){
                throw new Exception("Question: " + question.getCode() + " does not exits");
            }

            question.setId(preq.get().getId());
            repository.delete(question);
            List<Question> questions = repository.findAllByTestcode(questionDTO.getTestcode());
            for (int i = 0; i < questions.size(); i++) {
                questions.get(i).setNth(repository.findAllByTestcode(questionDTO.getTestcode()).size()+1);
                questions.get(i).setCode("QUES" + question.getNth() + questionDTO.getTestcode());
            }

            resp.setMessage("Delete question successfully");
            resp.setStatusCode(200);
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes deleteQuestionByTestId(QuestionDTO questionDTO) {
        ReqRes resp = new ReqRes();
        try{
            List<Question> questions = repository.findAllByTestcode(questionDTO.getTestcode());
            if(questions.isEmpty()){
                throw new Exception("Test: " + questionDTO.getTestcode() + " does not exits");
            }
            repository.deleteAll(questions);

            resp.setMessage("Delete " + questions.size() + " questions successfully");
            resp.setStatusCode(200);
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
}

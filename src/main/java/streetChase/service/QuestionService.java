package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.Question;
import streetChase.repository.QuestionRepository;

import javax.annotation.Resource;


@Service
public class QuestionService {

    @Resource
    private QuestionRepository questionRepository;

    @Transactional
    public Question findById(int id) {
        return questionRepository.findOne(id);
    }

    @Transactional
    public Question findByControlPoint(int id) {
        return questionRepository.findByControlPoint(id);
    }
}
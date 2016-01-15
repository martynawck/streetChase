package streetChase.service.mobile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.Question;
import streetChase.repository.mobile.MobileQuestionRepository;

import javax.annotation.Resource;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class MobileQuestionServiceImpl implements MobileQuestionService {

    @Resource
    private MobileQuestionRepository questionRepository;

    @Override
    @Transactional
    public Question findById(int id) {
        return questionRepository.findOne(id);
    }

    @Override
    @Transactional
    public Question findByControlPoint(int id) {
        return questionRepository.findByControlPoint(id);
    }
}
package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.ControlPoint;
import streetChase.repository.ControlPointRepository;

import javax.annotation.Resource;

@Service
public class ControlPointService {

    @Resource
    private ControlPointRepository controlPointRepository;

    @Transactional
    public ControlPoint findInitialForGame(int id) {
        return controlPointRepository.findGameStartingPoint(id);
    }

    @Transactional
    public ControlPoint findById(int id) {
        return controlPointRepository.findOne(id);

    }


}
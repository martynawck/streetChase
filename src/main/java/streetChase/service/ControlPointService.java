package streetChase.service;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.dto.PointDto;
import streetChase.dto.StreetGameDto;
import streetChase.model.ControlPoint;
import streetChase.model.Question;
import streetChase.repository.ControlPointRepository;
import streetChase.repository.QuestionRepository;
import streetChase.repository.UserLocationRepository;
import streetChase.utils.GeometryUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ControlPointService {

    @Resource
    private ControlPointRepository controlPointRepository;

    @Resource
    private QuestionRepository questionRepository;

    @Transactional
    public ControlPoint findInitialForGame(int id) {
        return controlPointRepository.findGameStartingPoint(id);
    }

    @Transactional
    public ControlPoint findById(int id) {
        return controlPointRepository.findOne(id);
    }

    public void saveRoute(List<PointDto> route, int gameId) {
        if (route == null)
            return;

        int pointId = -1;
        for (int i = route.size()-1; i >= 0; --i) {
            PointDto dto = route.get(i);
            Point p = GeometryUtil.getPointFromStrings(dto.getLon(), dto.getLat());
            ControlPoint c = new ControlPoint(gameId, dto.getName(), pointId, p, (i == 0), dto.getHint());
            pointId = saveControlPoint(c);

            questionRepository.save(new Question(dto.getQuestion(), dto.getAnswer(), pointId));
        }
    }

    @Transactional
    public List<ControlPoint> findByGameId(int id) {
        return controlPointRepository.findByGameId(id);

    }

    private int saveControlPoint(ControlPoint controlPoint) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        ControlPointRepository repository = context.getBean(ControlPointRepository.class);
        int id = repository.save(controlPoint).getId();

        context.close();

        return id;
    }

}

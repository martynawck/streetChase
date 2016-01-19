package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.UserReachedPoint;
import streetChase.repository.UserReachedPointRepository;

import javax.annotation.Resource;

@Service
public class UserReachedPointService {

    @Resource
    private UserReachedPointRepository userReachedPointRepository;

    @Transactional
    public void addReachedPoint(UserReachedPoint s) {
          userReachedPointRepository.save(s);
    }
    @Transactional
    public UserReachedPoint findByControlPointAndUser(int user, int controlPoint) {
        return userReachedPointRepository.findByControlPointAndUser(user, controlPoint);//.save(s);
    }

    @Transactional
    public void deleteUserReachedPoint(UserReachedPoint userReachedPoint) {
        userReachedPointRepository.delete(userReachedPoint);//.findByControlPointAndUser(user, controlPoint);//.save(s);
    }

}
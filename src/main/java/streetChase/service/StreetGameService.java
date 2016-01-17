package streetChase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.dto.StreetGameDto;
import streetChase.model.StreetGame;
import streetChase.repository.StreetGameRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StreetGameService {

    @Resource
    private StreetGameRepository streetGameRepository;

    @Transactional
    public List findAll() {
        return streetGameRepository.findAll();
    }

    @Transactional
    public StreetGame findById(int id) {
        return streetGameRepository.findOne(id);
    }

    @Transactional
    public List<StreetGame> findByCreator(int id) {
        return streetGameRepository.findByCreatorId(id);
    }

    @Transactional
    public void deleteStreetGame(StreetGame game) { streetGameRepository.delete(game); }

    @Transactional
    public boolean save(StreetGameDto gameDto, int creatorId) {
        if (gameDto.getId() == 0) {
            streetGameRepository.save(new StreetGame(gameDto, creatorId));
            return true;
        }

        StreetGame streetGame = streetGameRepository.findOne(gameDto.getId());
        if (streetGame.getCreatorId() != creatorId)
            return false;

        streetGame.applyChanges(gameDto);
        streetGameRepository.save(streetGame);
        return true;
    }

    @Secured("ROLE_ADMIN")
    public void delete(int streetGameId) {
        streetGameRepository.delete(streetGameId);
    }

    @Transactional(readOnly = true)
    public List<StreetGameDto> findDtoListForCreator(int creatorId) {
        Iterable<StreetGame> gamesList = streetGameRepository.findByCreatorId(creatorId);
        List<StreetGameDto> resultList = new ArrayList<StreetGameDto>();
        for (StreetGame s : gamesList) {
            resultList.add(new StreetGameDto(s));
        }
        return resultList;
    }

    @Transactional(readOnly = true)
    public List<StreetGameDto> findAllPublic() {
        return convertToDtoList(findAllPublicGames());
    }

    @Transactional(readOnly = true)
    public List<StreetGameDto> findAllAsDtos() {
        return convertToDtoList(streetGameRepository.findAll());
    }

    private List<StreetGameDto> convertToDtoList(Iterable<StreetGame> gamesList) {
        if (gamesList == null)
            return Collections.emptyList();

        List<StreetGameDto> resultList = new ArrayList<StreetGameDto>();
        for (StreetGame s : gamesList) {
            resultList.add(new StreetGameDto(s));
        }
        return resultList;
    }

    private List<StreetGame> findAllPublicGames() {
        //return streetGameRepository.findByIs_private(false);
        return null;
    }
}
package streetChase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streetChase.model.Event;
import streetChase.model.User;
import streetChase.repository.EventRepository;
import streetChase.repository.UserRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event findByEmail(Long id){
        return eventRepository.getById(id);
    }

    public void saveEvent(Event e) {
        eventRepository.save(e);
    }
}

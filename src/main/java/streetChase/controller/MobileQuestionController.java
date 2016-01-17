package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streetChase.model.Question;
import streetChase.service.QuestionService;

@Controller
@RequestMapping(value = "/mobile")
public class MobileQuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Question> getQuestion(@PathVariable("id") int id) {

        Question question = questionService.findById(id);
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }

    @RequestMapping(value = "/question/control_point/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Question> getQuestionByControlPoint(@PathVariable("id") int id) {

        Question question = questionService.findByControlPoint(id);
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }

}
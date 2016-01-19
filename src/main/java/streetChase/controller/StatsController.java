package streetChase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/protected/stats")
public class StatsController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView stats() {
        return new ModelAndView("stats");
    }
}

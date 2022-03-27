package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index", "result", "abs");
        return view;
    }

    @RequestMapping(value = "/post.htm", method = RequestMethod.GET)
    public ModelAndView post() {
        ModelAndView view = new ModelAndView("/post", "result", "abs");
        return view;
    }

    @RequestMapping(value = "/notifyUrl.htm", method = RequestMethod.GET)
    public ModelAndView notifyUrl() {
        ModelAndView view = new ModelAndView("/notifyUrl", "result", "abs");
        return view;
    }

    @RequestMapping(value = "/backUrl.htm", method = RequestMethod.GET)
    public ModelAndView backUrl() {
        ModelAndView view = new ModelAndView("/backUrl", "result", "abs");
        return view;
    }
}

package mkoffeine.wordsunk.controller;

import mkoffeine.wordsunk.dao.UserDao;
import mkoffeine.wordsunk.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Random;

/**
 * Created by Michael on 02.04.2015.
 */
@Controller

public class Worker {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/js/wordsunk")
    public ModelAndView jsFile() {
        return getUsersModelAndView("js/wordsunk");
    }

    private ModelAndView getUsersModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("user", getUserEntityWithFile());
        return modelAndView;
    }

    @RequestMapping("/work")
    public ModelAndView work() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        modelAndView.addObject("user",userDao.getUserByName(auth.getName()));
        return getUsersModelAndView("work-init");
    }

    @RequestMapping("/users")
    public ModelAndView usersList() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users",userDao.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {

        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<html><br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result+"</html>";
    }

    @RequestMapping(value = "/saveWords", method = RequestMethod.POST)
    public @ResponseBody
    String saveWords(@RequestParam("words") String words) {
        System.out.println("-------------------starting saving words");
        UserEntity user = userDao.getUserById(1);
        user.setWords(words);
        userDao.saveUsersWords(user);
        String result = "<br>The result is <b> successful? </b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("-------------------end saving words");
        return result;
    }


    private UserEntity getLoggedUserEntity() {
        return userDao.getUserById(3);
    }

    private UserEntity getUserEntityWithFile() {
        return userDao.getUserById(1);
    }

}
